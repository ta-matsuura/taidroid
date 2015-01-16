package icsbook.sample.section9.example5;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.AsyncTaskLoader;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

public class AppLoader {

    /**
     * Loader のアイテム用データクラス
     */
    public static class AppEntry {

        private final PackageManager mManager;
        private final ApplicationInfo mInfo;
        
        private String mLabel;
        private Drawable mIcon;

        public AppEntry(PackageManager manager, ApplicationInfo info) {
            mManager = manager;
            mInfo = info;
        }

        public String getLabel() {
            if (mLabel == null) {
                CharSequence label = mInfo.loadLabel(mManager);
                mLabel = label != null ? label.toString() : mInfo.packageName;
            }
            return mLabel;
        }

        public Drawable getIcon() {
            if (mIcon == null) {
                mIcon = mInfo.loadIcon(mManager);
            }
            return mIcon;
        }
        
        @Override public String toString() {
            return mLabel;
        }
    }

    /**
     * AppEntry 用の Comparator
     */
    public static final Comparator<AppEntry> ALPHA_COMPARATOR = new Comparator<AppEntry>() {
        private final Collator sCollator = Collator.getInstance();

        @Override
        public int compare(AppEntry object1, AppEntry object2) {
            return sCollator.compare(object1.getLabel(), object2.getLabel());
        }
    };

    /**
     * インストールしているアプリの変更を検知する BroadcastReceiver
     */
    public static class PackageChangeReceiver extends BroadcastReceiver {
        final AppListLoader mLoader;

        public PackageChangeReceiver(AppListLoader loader) {
            mLoader = loader;

            // インストールアプリに変更を検知する IntentFilter をセット
            IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
            filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
            filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
            filter.addDataScheme("package");
            mLoader.getContext().registerReceiver(this, filter);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            // Loader にデータが変更されたことを通知
            mLoader.onContentChanged();
        }
    }    

    /**
     * インストールされているアプリ用の Loader
     */
    public static class AppListLoader extends AsyncTaskLoader<List<AppEntry>> {
        final PackageManager mPackageManager;

        List<AppEntry> mAppList;
        PackageChangeReceiver mPackageChangeReceiver;

        public AppListLoader(Context context) {
            super(context);
            mPackageManager = getContext().getPackageManager();
        }

        /**
         * バックグラウンドで Loader 用のデータを読み込む
         */
        @Override
        public List<AppEntry> loadInBackground() {

            int flags = PackageManager.GET_UNINSTALLED_PACKAGES | PackageManager.GET_DISABLED_COMPONENTS;
            List<ApplicationInfo> apps = mPackageManager.getInstalledApplications(flags);

            if (apps == null) {
                apps = new ArrayList<ApplicationInfo>();
            }

            // AppEntry 用のリストを作成
            List<AppEntry> entries = new ArrayList<AppEntry>(apps.size());

            for (int i = 0; i < apps.size(); i++) {
                AppEntry entry = new AppEntry(mPackageManager, apps.get(i));
                entries.add(entry);
            }

            // リストをソート
            Collections.sort(entries, ALPHA_COMPARATOR);

            return entries;
        }
        
        /**
         * 提供する新しいデータがあるときに呼ばれる
         */
        @Override
        public void deliverResult(List<AppEntry> apps) {
            if (isReset()) {
                // リセット時（まだ最初に読み込みが開始されていない、もしくは、
                // reset() が呼ばれた後）現在の非同期クエリを開放
                if (apps != null) {
                    onReleaseResources(apps);
                }
                return;
            }
            
            List<AppEntry> oldApps = apps;
            mAppList = apps;

            if (isStarted()) {
                // 読み込みが開始されている（startLoading() が呼ばれているが、
                // stopLoading() や reset() はまだ呼ばれていない時）なら、その結果を返す
                super.deliverResult(apps);
            }

            // この時点で、必要であれば 'oldApps' に関連するリソースを開放できる
            if (oldApps != null && oldApps != apps) {
                onReleaseResources(oldApps);
            }
        }

        /**
         * Loader の開始依頼を処理
         */
        @Override
        protected void onStartLoading() {

            if (mAppList != null) {
                // すでにアプリデータを取得している場合は、それを返す
                deliverResult(mAppList);
            }

            // アプリデータの変更を監視
            if (mPackageChangeReceiver == null) {
                mPackageChangeReceiver = new PackageChangeReceiver(this);
            }

            // アプリデータが変わっている、もしくは、アプリデータがまだ読まれていない場合
            if (takeContentChanged() || mAppList == null) {
                // データを読み込み
                forceLoad();
            }
        }

        /**
         * Loader の停止依頼を処理
         */
        @Override
        protected void onStopLoading() {
            // 現在の読み込みタスクのキャンセルを試みる
            cancelLoad();
        }

        /**
         * 読み込みのキャンセル依頼を処理
         */
        @Override
        public void onCanceled(List<AppEntry> apps) {
            super.onCanceled(apps);

            // この時点で、必要であれば 'apps' に関連するリソースを開放できる
            if(apps != null)
                onReleaseResources(apps);
        }

        /**
         * Loader の完全リセット依頼を処理
         */
        @Override
        protected void onReset() {
            super.onReset();

            // Loader を停止することを確認
            onStopLoading();

            // この時点で、必要であればアプリデータに関連するリソースを開放できる
            if (mAppList != null) {
                onReleaseResources(mAppList);
                mAppList = null;
            }

            // アプリデータ変更の監視をストップ
            if (mPackageChangeReceiver != null) {
                getContext().unregisterReceiver(mPackageChangeReceiver);
                mPackageChangeReceiver = null;
            }
        }

        /**
         * 読み込んだデータセットに関連するリソースを開放するためのヘルパーメソッド
         */
        protected void onReleaseResources(List<AppEntry> apps) {
            // Cursor の場合は閉じる
            // 単純なリスト List<> の場合は特に何もしない
        }
    }
}