package icsbook.sample.section5.example9;

import icsbook.sample.R;
import android.animation.LayoutTransition;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity3 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main5_9);

        // ボタンを追加する ViewGroup
        final LinearLayout ll = (LinearLayout) findViewById(R.id.container);

        // LayoutTransition を生成
        final LayoutTransition transitioner = new LayoutTransition(); 

        // ViewGroup に LayoutTransition をセット
        ll.setLayoutTransition(transitioner); 
        
        final View button1 = findViewById(R.id.button1);
        final View button2 = findViewById(R.id.button2);
        
        button2.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                if(button1.getVisibility() == View.VISIBLE)
                    button1.setVisibility(View.GONE);
                else {
                    button1.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
