package icsbook.sample.section3.example9;

import icsbook.sample.section3.example7.MyTabListener;
import icsbook.sample.section3.example7.Tab1Fragment;
import icsbook.sample.section3.example7.Tab2Fragment;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.Tab;
import android.os.Bundle;

public class MainActivity12 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Action Bar を取得
        ActionBar actionBar = getActionBar();

        // ActionBar.NAVIGATION_MODE_TABS を指定
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // ActionBar にタイトルを指定するかどうか。デフォルトでは true
        // false の場合タイトルを表示しない
        actionBar.setDisplayShowTitleEnabled(true);

        // 1つ目のタブ
        Tab tab1 = actionBar.newTab();
        tab1.setText("tab1");
        tab1.setIcon(android.R.drawable.ic_menu_agenda);
        tab1.setTabListener(new MyTabListener<Tab1Fragment>(this, "tab1", Tab1Fragment.class));
        // 1つ目のタブを Action Bar に追加
        actionBar.addTab(tab1);

        Tab tab2 = actionBar.newTab();
        tab2.setText("tab2");
        tab2.setTabListener(new MyTabListener<Tab2Fragment>(this, "tab2", Tab2Fragment.class));
        // 2つ目のタブを Action Bar に追加
        actionBar.addTab(tab2);
    }

}
