package icsbook.sample.section2.example7;

import icsbook.sample.R;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private boolean mIsTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2_7);
        
        // 内容を表示するFrameLayout があるかどうかで2 ペインかどうかを判断
        View v = findViewById(R.id.content_container);
        mIsTwoPane = (v != null);
        
        // ID からMyListFragment を取得
        MyListFragment fragment = (MyListFragment) getFragmentManager().findFragmentById(R.id.list_fragment);
        
        // リストのアイテムがタップされたときに呼び出されるリスナーをセット
        fragment.setOnListItemClickListener(new MyListFragment.OnListItemClickListener() {
            @Override
            public void onListItemClick(int position, String value) {
                
                if (mIsTwoPane) {
                    // フラグメントをFrameLayout に追加
                    ContentFragment contentFragment = ContentFragment.getInstance(value);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.content_container, contentFragment, "content");
                    ft.commit();
                    
                } else {
                    // アクティビティを開始
                    Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                    intent.putExtra("index", position);
                    intent.putExtra("text", value);
                    startActivity(intent);
                }
            }
        });
    }
}
