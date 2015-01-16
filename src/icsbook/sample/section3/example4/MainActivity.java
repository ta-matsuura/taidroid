package icsbook.sample.section3.example4;

import icsbook.sample.HomeActivity;
import icsbook.sample.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // アプリケーションアイコンをホームボタンとして利用
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu3_3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        // MenuItem オブジェクトが持つID を取得
        switch(item.getItemId()) {

            // アプリケーションアイコンがタップされた場合
            case android.R.id.home:

                // ホーム画面のアクティビティを呼び出すインテント
                Intent intent = new Intent(this, HomeActivity.class);

                // スタック内でホーム画面のアクティビティの上にあるものをクリア
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
    
                // 画面遷移のアクションを指定する場合はここに書く
                
                return true;

            // その他のオプションメニューの項目を処理

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}