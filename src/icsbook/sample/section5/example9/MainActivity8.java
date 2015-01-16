package icsbook.sample.section5.example9;

import icsbook.sample.R;
import android.animation.LayoutTransition;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity8 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main5_9);

        // ボタンを追加する ViewGroup
        final LinearLayout ll = (LinearLayout) findViewById(R.id.container);

        // LayoutTransition を生成
        final LayoutTransition transitioner = new LayoutTransition(); 

        // ボタン1とボタン2が移動する時間 
        transitioner.setDuration(LayoutTransition.CHANGE_APPEARING, 2000); 
        // ボタン1とボタン2のアニメーション開始までのずれ 
        transitioner.setStagger(LayoutTransition.CHANGE_APPEARING, 500);         
        
        // ボタン0がフェードインする時間 
        transitioner.setDuration(LayoutTransition.APPEARING, 1000); 
        // ボタン0のフェードイン開始までの遅延時間 
        transitioner.setStartDelay(LayoutTransition.APPEARING, 2000); 

        // ViewGroup に LayoutTransition をセット
        ll.setLayoutTransition(transitioner); 
        
        final Button button0 = new Button(MainActivity8.this); 
        button0.setText("Button0"); 
                
        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() { 
            
            @Override 
            public void onClick(View v) { 
                if(ll.indexOfChild(button0) < 0)
                    ll.addView(button0, 0); 
            } 
        }); 
        
        button0.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                ll.removeView(button0);
            }
        });
    }
}
