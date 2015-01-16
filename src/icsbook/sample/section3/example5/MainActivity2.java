package icsbook.sample.section3.example5;

import icsbook.sample.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity2 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu3_5_2, menu);
        
        // MenuItem を取得
        MenuItem item = menu.findItem(R.id.menu_setting);
        
        // getActionView() でアクションビューを取得
        MySeekBar seekBar = (MySeekBar) item.getActionView();
        
        return super.onCreateOptionsMenu(menu);
    }
}