package icsbook.sample.section7.example4;

import icsbook.sample.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // クリップボードに ClipData を格納するための ClipboardManager
    private ClipboardManager mClipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main7_3);

        // ClipboardManager を取得
        mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        // PackageManager を取得
        PackageManager manager = getPackageManager();

        // Action が Intent.ACTION_MAIN で Category が Intent.GATEGORY_LAUNCHER のアプリ一覧を取得
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> apps = manager.queryIntentActivities(intent, 0);
        if (apps == null) {
            apps = new ArrayList<ResolveInfo>();
        }

        // 名前でソート
        Collections.sort(apps, new ResolveInfo.DisplayNameComparator(manager));

        // AppEntry 用のリストを作成
        List<AppEntry> data = new ArrayList<AppEntry>(apps.size());

        for (int i = 0; i < apps.size(); i++) {
            ResolveInfo info = apps.get(i);
            String packageName = info.activityInfo.applicationInfo.packageName;
            String className = info.activityInfo.name;

            if (packageName != null && className != null) {
                AppEntry entry = new AppEntry(manager, info);
                data.add(entry);
            }
        }

        final ListView listView = (ListView) findViewById(R.id.list);
        
        // 単一選択モードにする
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // アプリリスト用の Adapter をセット
        final ArrayAdapter<AppEntry> adapter = new ArrayAdapter<AppEntry>(this,
                android.R.layout.simple_list_item_single_choice, data);
        listView.setAdapter(adapter);


        // コピーボタンが押された時の処理
        findViewById(R.id.copy_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // リストで選択されている位置を取得
                int position = listView.getCheckedItemPosition();

                if (position < 0) {
                    return;
                }

                // 選択されている位置の AppEntry オブジェクトから Intent を取得
                AppEntry appEntry = (AppEntry) adapter.getItem(position);
                Intent intent = appEntry.getIntent();

                // Intent オブジェクトを1つ持つ ClipdData を作成
                ClipData data = ClipData.newIntent("label", intent);
                
                // クリップボードに ClipData を格納
                mClipboardManager.setPrimaryClip(data);
                
                Toast.makeText(MainActivity.this, "Copy to clipboard : " + intent, Toast.LENGTH_SHORT).show();
            }
        });

        // Launch Copied App ボタンが押された時の処理
        findViewById(R.id.paste_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                
                // クリップボードにペーストするデータがあるかチェック
                if (!mClipboardManager.hasPrimaryClip()) {
                    return;
                }

                // ClipDescription を取得
                ClipDescription description = mClipboardManager.getPrimaryClipDescription();

                // ClipDescription が Intent のMIMEタイプを含んでいるかチェック
                if (!description.hasMimeType(ClipDescription.MIMETYPE_TEXT_INTENT)) {
                    return;
                }

                // ClipData を取得
                ClipData data = mClipboardManager.getPrimaryClip();

                // ClipData がアイテムを持っているかチェック
                if (data != null && data.getItemCount() > 0) {
                    
                    // ClipData から Intent を取得
                    Intent intent = data.getItemAt(0).getIntent();
                    
                    // Intent から Activity を起動
                    startActivity(intent);
                    
                    Toast.makeText(MainActivity.this, "Paste from clipboard : " + intent, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * アイテム用データクラス
     */
    public static class AppEntry {
        private CharSequence mLabel;
        private Intent mIntent;

        public AppEntry(PackageManager manager, ResolveInfo info) {
            String packageName = info.activityInfo.applicationInfo.packageName;
            String className = info.activityInfo.name;
            mIntent = new Intent();
            mIntent.setComponent(new ComponentName(packageName, className));
            mIntent.setAction(Intent.ACTION_MAIN);
            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

            mLabel = info.loadLabel(manager);
            if (mLabel == null)
                mLabel = packageName;
        }

        public Intent getIntent() {
            return mIntent;
        }

        @Override
        public String toString() {
            return (String) mLabel;
        }
    }
}
