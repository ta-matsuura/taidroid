package icsbook.sample.section9.example4;

import icsbook.sample.R;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TwoListFragment を Activity に追加
        FragmentManager manager = getFragmentManager();
        TwoListFragment fragment = (TwoListFragment) manager.findFragmentByTag("fragment");
        if(fragment == null) {
            fragment = new TwoListFragment();
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(android.R.id.content, fragment, "fragment");
            ft.commit();    
        }
    }

    public static class TwoListFragment extends Fragment {

        // リスト1用の Adapter
        private SimpleCursorAdapter mAdapter1;
        // リスト2用の Adapter
        private SimpleCursorAdapter mAdapter2;

        // リスト1用の ListView
        private ListView mListView1;
        // リスト2用の ListView
        private ListView mListView2;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            // Fragment の View を生成
            View v = inflater.inflate(R.layout.list, null, false);
            // リスト1用の ListView を取得
            mListView1 = (ListView) v.findViewById(R.id.list1);
            // リスト2用の ListView を取得
            mListView2 = (ListView) v.findViewById(R.id.list2);

            return v;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            // リスト1 用の Cursor が null の Adapter を生成
            mAdapter1 = new SimpleCursorAdapter(getActivity(), 
                    android.R.layout.simple_list_item_1, null,
                    new String[] { Contacts.DISPLAY_NAME }, 
                    new int[] { android.R.id.text1 }, 
                    0);

            // リスト2 用の Cursor が null の Adapter を生成
            mAdapter2 = new SimpleCursorAdapter(getActivity(), 
                    android.R.layout.simple_list_item_2, null, 
                    new String[] { Contacts.DISPLAY_NAME, Contacts.CONTACT_STATUS }, 
                    new int[] { android.R.id.text1, android.R.id.text2 },
                    0);

            // リスト1 用の Adapter をセット
            mListView1.setAdapter(mAdapter1);
            // リスト2 用の Adapter をセット
            mListView2.setAdapter(mAdapter2);

            // リスト1 用の Cursorデータを読む LoaderCallbacks
            LoaderManager.LoaderCallbacks<Cursor> callbacks1 = new LoaderManager.LoaderCallbacks<Cursor>() {

                @Override
                public Loader<Cursor> onCreateLoader(int id, Bundle args) {

                    // コンタクト情報の Cursor を取得
                    Uri uri = Contacts.CONTENT_URI;
                    String[] projection = new String[] { Contacts._ID, Contacts.DISPLAY_NAME };
                    String select = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND (" + Contacts.DISPLAY_NAME + " != '' ))";
                    String order = Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

                    return new CursorLoader(getActivity(), uri, projection, select, null, order);
                }

                @Override
                public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                    // 取得した Cursor データを Adapter にセット
                    mAdapter1.swapCursor(data);
                }

                @Override
                public void onLoaderReset(Loader<Cursor> loader) {
                    // Cursor データとのヒモ付を外す
                    mAdapter1.swapCursor(null);
                }
            };

            // リスト2 用の Cursorデータを読む LoaderCallbacks
            LoaderManager.LoaderCallbacks<Cursor> callbacks2 = new LoaderManager.LoaderCallbacks<Cursor>() {

                @Override
                public Loader<Cursor> onCreateLoader(int id, Bundle args) {

                    // コンタクト情報の Cursor を取得
                    Uri uri = Contacts.CONTENT_URI;
                    String[] projection = new String[] { Contacts._ID, Contacts.DISPLAY_NAME, Contacts.CONTACT_STATUS };
                    String select = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND (" + Contacts.DISPLAY_NAME + " != '' ))";
                    String order = Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

                    return new CursorLoader(getActivity(), uri, projection, select, null, order);
                }

                @Override
                public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                    // 取得した Cursor データを Adapter にセット
                    mAdapter2.swapCursor(data);
                }

                @Override
                public void onLoaderReset(Loader<Cursor> loader) {
                    // Cursor データとのヒモ付を外す
                    mAdapter2.swapCursor(null);
                }
            };

            // LoaderManager を取得
            LoaderManager manager = getLoaderManager();
            // リスト1用の Loader を初期化
            manager.initLoader(0, null, callbacks1);
            // リスト2用の Loader を初期化
            manager.initLoader(1, null, callbacks2);
        }
    }
}
