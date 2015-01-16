package icsbook.sample.section5.example9;

import icsbook.sample.R;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity5 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main5_9);

        // ボタンを追加する ViewGroup
        final LinearLayout ll = (LinearLayout) findViewById(R.id.container);

        // LayoutTransition を生成
        final LayoutTransition transitioner = new LayoutTransition(); 

        // フェードインのアニメーション
        PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("alpha", 0f, 1f);
        // 回転のアニメーション
        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("rotation", 0f, 360f);
        
        // フェードインと回転のアニメーションを組み合わせる
        ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder((Object)null, pvh1, pvh2);
        
        // フェードインと回転のアニメーションを LayoutTransition にセット
        transitioner.setAnimator(LayoutTransition.APPEARING, anim); 

        // ViewGroup に LayoutTransition をセット
        ll.setLayoutTransition(transitioner); 
        
        final Button button0 = new Button(MainActivity5.this); 
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
