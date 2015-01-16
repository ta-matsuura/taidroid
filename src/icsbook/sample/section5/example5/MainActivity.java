package icsbook.sample.section5.example5;

import icsbook.sample.R;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main5_5);

        startAnimation();
    }

    private void startAnimation() {

        final Button button = (Button)findViewById(R.id.button);
        
        // 開始値 p0, 終了値 p1 の ValueAnimator を作成
        ObjectAnimator anim = ObjectAnimator.ofFloat(button, "alpha", 1f, 0f, 1f, 0f);
        
        // 継続時間を設定
        anim.setDuration(5000);

        // アニメーションを開始
        anim.start();
    }
}
