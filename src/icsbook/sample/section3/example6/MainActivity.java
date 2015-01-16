package icsbook.sample.section3.example6;

import icsbook.sample.R;
import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Intent queryIntent = getIntent();
        final String queryAction = queryIntent.getAction();

        // ACTION_SEARCH の Intent で呼び出された場合
        if (Intent.ACTION_SEARCH.equals(queryAction)) {
            doSearchWithIntent(queryIntent);
        }

        // Quick Search Box から呼び出された場合
        if (Intent.ACTION_VIEW.equals(queryAction)) {
            if (queryIntent.getFlags() == Intent.FLAG_ACTIVITY_NEW_TASK) {
                doSearchWithIntent(queryIntent);
            }
        }
    }

    // 検索用 Activity から呼び出されたとき
    @Override
    protected void onNewIntent(Intent intent) {
        doSearchWithIntent(intent);
    }

    private void doSearchWithIntent(final Intent queryIntent) {
        // 検索文字列は SearchManager.QUERY というキーに入っている
        final String queryString = queryIntent.getStringExtra(SearchManager.QUERY);
        
        // 検索の履歴候補に追加
        SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this, "icsbook.sample",
                MySuggestionProvider.DATABASE_MODE_QUERIES);
        suggestions.saveRecentQuery(queryString, null);
        
        doSearchWithQuery(queryString);
    }

    private void doSearchWithQuery(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu3_6, menu);

        // SearchManager を取得
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        // ID からSearchView を取得
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();

        // SearchableInfo を取得
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());

        // SearchView にSearchableInfo をセット
        searchView.setSearchableInfo(searchableInfo);

        // 検索アイコンの位置を指定（デフォルトはtrue）
        // true をセットすると、検索アイコンがヒントとしてEditText の内側に表示される
        // false をセットすると、検索アイコンがEditText の左側に表示される
        searchView.setIconifiedByDefault(false);

        // Submit ボタンを表示するかどうか（true の場合は表示）
        searchView.setSubmitButtonEnabled(false);

        return true;
    }
}