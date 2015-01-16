package icsbook.sample.section5.example7;

import icsbook.sample.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main5_5);

        startAnimation();
    }

    private void startAnimation() {

        final View view = findViewById(R.id.button);

        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        
        anim.addListener(new AnimatorListenerAdapter() {
            
            @Override
            public void onAnimationEnd(Animator animation) {
                // アニメーションが終了したときに行いたい処理
                ViewGroup parent = (ViewGroup) view.getParent();
                parent.removeView(view);
            }            
        });

        // 継続時間を設定
        anim.setDuration(5000);

        // アニメーションを開始
        anim.start();
    }
}
