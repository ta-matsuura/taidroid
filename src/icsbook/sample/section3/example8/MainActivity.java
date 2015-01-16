package icsbook.sample.section3.example8;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

    // ドロップダウンリストの項目が選択されたときのコールバック
    ActionBar.OnNavigationListener mNavigationCallback = new ActionBar.OnNavigationListener() {

        @Override
        public boolean onNavigationItemSelected(int itemPosition, long itemId) {
            Toast.makeText(MainActivity.this, "itemPosition : " + itemPosition, Toast.LENGTH_SHORT).show();
            return false;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // ドロップダウンリスト用の Adapter を作成
        String[] data = { "DropDownItem1", "DropDownItem2", "DropDownItem3", "DropDownItem4", 
                "DropDownItem5", "DropDownItem6", };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
                android.R.layout.simple_dropdown_item_1line, data);

        // Action Bar を取得
        ActionBar actionBar = getActionBar();
        
        // ActionBar.NAVIGATION_MODE_LIST を指定
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        
        // Adapter とドロップダウンリストの項目が選択されたときのコールバックをセット
        actionBar.setListNavigationCallbacks(adapter, mNavigationCallback);
    }
}