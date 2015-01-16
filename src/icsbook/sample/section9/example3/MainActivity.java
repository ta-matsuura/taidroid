package icsbook.sample.section9.example3;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // CursorListFragment を Activity に追加
        FragmentManager manager = getFragmentManager();
        CursorListFragment fragment = (CursorListFragment) manager.findFragmentByTag("fragment");
        if(fragment == null) {
            fragment = new CursorListFragment();
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(android.R.id.content, fragment, "fragment");
            ft.commit();    
        }
    }

    public static class CursorListFragment extends ListFragment {

        // コンタクト用の Adapter
        SimpleCursorAdapter mAdapter;

        // 検索用のフィルター文字列
        String mCurFilter;

        // Cursor オブジェクトの LoaderCallbacks
        LoaderManager.LoaderCallbacks<Cursor> mCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {

            // 新しい Loader を生成するときに呼ばれる
            @Override
            public CursorLoader onCreateLoader(int id, Bundle args) {
                Uri uri;
                
                // 検索用のフィルター文字列に応じて Uri を切り替え
                if (mCurFilter != null) {
                    uri = Uri.withAppendedPath(Contacts.CONTENT_FILTER_URI,
                            Uri.encode(mCurFilter));
                } else {
                    uri = Contacts.CONTENT_URI;
                }

                String[] projection = new String[] { Contacts._ID, Contacts.DISPLAY_NAME, Contacts.CONTACT_STATUS };
                String selection = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND (" + Contacts.DISPLAY_NAME + " != '' ))";
                String order = Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

                return new CursorLoader(getActivity(), uri, projection, selection, null, order);
            }

            // データの読み込みが完了したときに呼ばれる
            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                mAdapter.swapCursor(data);
            }

            // Loader リセット時に呼ばれる
            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
                mAdapter.swapCursor(null);
            }
        };

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            // リストが空のときの文字をセット
            setEmptyText("No contacts");

            // Action Bar にメニューを表示
            setHasOptionsMenu(true);

            // Cursor が null の Adapter を作成してセット
            mAdapter = new SimpleCursorAdapter(getActivity(), 
                    android.R.layout.simple_list_item_2, null, 
                    new String[] { Contacts.DISPLAY_NAME, Contacts.CONTACT_STATUS }, 
                    new int[] { android.R.id.text1, android.R.id.text2 }, 
                    0);
            setListAdapter(mAdapter);

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
                    // フィルター文字列
                    mCurFilter = !TextUtils.isEmpty(newText) ? newText : null;

                    // SearchView のテキストで Loader をリスタート
                    getLoaderManager().restartLoader(0, null, mCallbacks);
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    return true;
                }
            });
            item.setActionView(sv);
        }
    }
}
