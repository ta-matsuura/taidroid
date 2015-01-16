package icsbook.sample.section9.example5;

import icsbook.sample.R;
import icsbook.sample.section9.example5.AppLoader.AppEntry;
import icsbook.sample.section9.example5.AppLoader.AppListLoader;

import java.util.List;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // AppListFragment を Activity に追加
        FragmentManager manager = getFragmentManager();
        AppListFragment fragment = (AppListFragment) manager.findFragmentByTag("fragment");
        if(fragment == null) {
            fragment = new AppListFragment();
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(android.R.id.content, fragment, "fragment");
            ft.commit();    
        }
    }

    // アプリのラベルとアイコンを表示するための Adapter
    public static class AppListAdapter extends ArrayAdapter<AppEntry> {
        private final LayoutInflater mInflater;

        public AppListAdapter(Context context) {
            super(context, R.layout.applist);
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void setData(List<AppEntry> data) {
            clear();
            
            if (data != null) {
                addAll(data);
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.applist, parent, false);
            }

            AppEntry item = getItem(position);

            ((ImageView) convertView.findViewById(R.id.icon)).setImageDrawable(item.getIcon());
            ((TextView) convertView.findViewById(R.id.text)).setText(item.getLabel());

            return convertView;
        }
    }

    public static class AppListFragment extends ListFragment {

        // アプリリスト用の Adapter
        AppListAdapter mAdapter;

        // List<AppEntry> オブジェクトの LoaderCallbacks
        LoaderManager.LoaderCallbacks<List<AppEntry>> mCallbacks = new LoaderManager.LoaderCallbacks<List<AppEntry>>() {
            
            // 新しい Loader を生成するときに呼ばれる
            @Override
            public Loader<List<AppEntry>> onCreateLoader(int id, Bundle args) {
                // 端末にインストールされているアプリを取得する Loader を返す
                return new AppListLoader(getActivity());
            }

            // データの読み込みが完了したときに呼ばれる
            @Override
            public void onLoadFinished(Loader<List<AppEntry>> loader, List<AppEntry> data) {
                // 引数の１番目に Loader, ２番目に読み込んだデータが渡される

                // Adapter にデータをセット
                mAdapter.setData(data);

                if (isResumed()) {
                    // リストを表示
                    setListShown(true);
                } else {
                    // 一時停止状態だったときはアニメーションなしでリストを表示
                    setListShownNoAnimation(true);
                }
            }
            
            // Loader リセット時に呼ばれる
            @Override
            public void onLoaderReset(Loader<List<AppEntry>> loader) {
                // Adapter のデータをクリア
                mAdapter.setData(null);
            }
        };

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            // リストが空のときの文字をセット
            setEmptyText("No applications");

            // Action Bar にメニューを表示
            setHasOptionsMenu(true);

            // アプリリスト用の Adapter をセット
            mAdapter = new AppListAdapter(getActivity());
            setListAdapter(mAdapter);

            // リストが読み込まれるまでプログレスを表示
            setListShown(false);
            
            // Loader を初期化
            LoaderManager manager = getLoaderManager();
            manager.initLoader(0, null, mCallbacks);
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

            // 検索用のアクションアイテムを設定
            MenuItem item = menu.add("Search");
            item.setIcon(android.R.drawable.ic_menu_search);
            item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

            // SearchView で検索したときの処理を追加
            SearchView sv = new SearchView(getActivity());
            sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                
                // SearchView の入力文字が変わったときに呼ばれる
                @Override
                public boolean onQueryTextChange(String newText) {
                    // リスト用フィルター文字列
                    String filterText = !TextUtils.isEmpty(newText) ? newText : null;

                    // SearchView のテキストで ArrayAdapter のフィルターをセット
                    mAdapter.getFilter().filter(filterText);
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    // 検索ボタンが押されたら Loader をリスタート
                    getLoaderManager().restartLoader(0, null, mCallbacks);
                    return true;
                }
            });
            item.setActionView(sv);
        }
    }
}
