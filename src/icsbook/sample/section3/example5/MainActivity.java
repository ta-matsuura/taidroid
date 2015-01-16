package icsbook.sample.section3.example5;

import icsbook.sample.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu3_5_1, menu);
        
        // MenuItem を取得
        MenuItem item = menu.findItem(R.id.menu_setting);
        
        // getActionView() でアクションビューを取得
        ProgressBar progressBar = (ProgressBar) item.getActionView();
        
        return super.onCreateOptionsMenu(menu);
    }
}