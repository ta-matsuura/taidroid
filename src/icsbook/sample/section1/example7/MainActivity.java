package icsbook.sample.section1.example7;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 一覧画面のリスト用 Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                ListData.titleList);
        
        // Adapter を ListView にセット
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        
        // 詳細画面に遷移
        Intent intent = new Intent(this, DetailActivity.class);
        
        // タップされた位置を渡す
        intent.putExtra("position", position);
        startActivity(intent);
    }
}
