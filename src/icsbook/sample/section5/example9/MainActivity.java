package icsbook.sample.section5.example9;

import icsbook.sample.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main5_9);

        // ボタンを追加する ViewGroup
        final LinearLayout ll = (LinearLayout) findViewById(R.id.container);

        // 追加するボタン
        final Button button0 = new Button(this);
        button0.setText("Button0");

        // ボタン1とボタン2を移動するアニメーション
        final Animation buttonsMoveAnimation = AnimationUtils.loadAnimation(this, R.anim.move_buttons);
        // ボタン0をフェードインするアニメーション
        final Animation buttonEnterAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_button);
        buttonEnterAnimation.setFillAfter(true);

        buttonsMoveAnimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // ボタン１とボタン２の移動が終わったらボタン0を追加してフェードイン
                if (ll.indexOfChild(button0) < 0) {
                    ll.addView(button0, 0);
                    button0.startAnimation(buttonEnterAnimation);
                }
            }
        });

        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ll.indexOfChild(button0) < 0)
                    ll.startAnimation(buttonsMoveAnimation);
            }
        });
    }
}
