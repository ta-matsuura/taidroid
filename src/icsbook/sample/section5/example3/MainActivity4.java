package icsbook.sample.section5.example3;

import icsbook.sample.R;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.CycleInterpolator;

public class MainActivity4 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main5_5);

        startAnimation();
    }
    
    public class CustomInterpolater implements TimeInterpolator {

        @Override
        public float getInterpolation(float input) {
            return input * input;
        }
    }

    private void startAnimation() {

        final View v = findViewById(R.id.button);
        
        // アニメーションXMLファイルから Animator を生成
        ObjectAnimator anim = ObjectAnimator.ofFloat(v, "x", 0f, 300f);
        
        // オリジナルインターポレーターを指定
        anim.setInterpolator(new CycleInterpolator(2));

        // 継続時間を設定
        anim.setDuration(5000);

        anim.start();
    }
}
