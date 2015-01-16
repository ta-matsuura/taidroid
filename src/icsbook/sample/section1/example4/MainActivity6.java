package icsbook.sample.section1.example4;

import icsbook.sample.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

public class MainActivity6 extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1_4_4);
        
        findViewById(R.id.showpopup).setOnClickListener(this);
        findViewById(R.id.showpopup2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        PopupMenu popupMenu = new PopupMenu(MainActivity6.this, v);
        popupMenu.inflate(R.menu.menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // メニュー項目がタップされたときに呼ばれる
                return false;
            }
        });
        popupMenu.show();
    }
}
