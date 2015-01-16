package icsbook.sample.section1.example1;

import icsbook.sample.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1_1);
        
        findViewById(R.id.low_profile_btn).setOnClickListener(this);
        findViewById(R.id.hide_navigation_btn).setOnClickListener(this);
        findViewById(R.id.visible_btn).setOnClickListener(this);
        
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.fullscreen_btn);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Window window = getWindow();
                WindowManager.LayoutParams winParams = window.getAttributes();

                if (isChecked) {
                    // ステータスバーを非表示にする
                    window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                } else {
                    // ステータスバーを表示する
                    window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
                window.setAttributes(winParams);
            }
        });
    }
    
    @Override
    public void onClick(View v) {
        int id = v.getId();
        int visibility = 0;
        
        if(id == R.id.hide_navigation_btn) {
            // 一時的にナビゲーションバーを非表示
            visibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }
        else if(id == R.id.low_profile_btn) {
            // ナビゲーションコントロールを暗くする
            visibility = View.SYSTEM_UI_FLAG_LOW_PROFILE;
        }
        else if(id == R.id.visible_btn) {
            // ナビゲーションバーおよびコントロールを表示
            visibility = View.SYSTEM_UI_FLAG_VISIBLE;
        }
        
        v.setSystemUiVisibility(visibility);
    }
}
