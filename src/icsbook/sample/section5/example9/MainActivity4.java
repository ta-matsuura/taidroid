package icsbook.sample.section5.example9;

import icsbook.sample.R;
import android.animation.LayoutTransition;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity4 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main5_9);

        // ボタンを追加する ViewGroup
        final LinearLayout ll = (LinearLayout) findViewById(R.id.container);

        // LayoutTransition を生成
        final LayoutTransition transitioner = new LayoutTransition(); 
        
        // 4種類のアニメーションを全て無しにセット
        transitioner.setAnimator(LayoutTransition.APPEARING, null); 
        transitioner.setAnimator(LayoutTransition.DISAPPEARING, null); 
        transitioner.setAnimator(LayoutTransition.CHANGE_APPEARING, null); 
        transitioner.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, null); 

        // ViewGroup に LayoutTransition をセット
        ll.setLayoutTransition(transitioner); 
        
        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() { 
            
            @Override 
            public void onClick(View v) { 
                Button button0 = new Button(MainActivity4.this); 
                button0.setText("Button0"); 
                ll.addView(button0, 0); 
            } 
        }); 
    }
}
