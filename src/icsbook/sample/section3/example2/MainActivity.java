package icsbook.sample.section3.example2;

import icsbook.sample.R;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main3_2);

        ToggleButton toggleBtn = (ToggleButton) findViewById(R.id.toggleBtn);
        toggleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // ActionBar のインスタンスを取得
                ActionBar actionBar = getActionBar();

                if (isChecked) {
                    // Action Bar を隠す
                    actionBar.hide();
                } else {
                    // 隠れていた Action Bar を表示する
                    actionBar.show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu3_1_1, menu);
        return true;
    }
}