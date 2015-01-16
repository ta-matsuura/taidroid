package icsbook.sample.section2.example7;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public class ContentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 画面が回転して横になった場合、このアクティビティは不要なので終了
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        
        if (savedInstanceState == null) {
            // 内容を表示するためのContentFragment を生成
            ContentFragment details = new ContentFragment();
            // インテントのExtras として渡されたBundle を引数として渡す
            details.setArguments(getIntent().getExtras());
            // ContentFragment をレイアウトに追加
            getFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
        }
    }
}
