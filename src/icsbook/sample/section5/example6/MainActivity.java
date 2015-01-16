package icsbook.sample.section5.example6;

import icsbook.sample.R;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main5_5);

        startAnimation();
    }

    private void startAnimation() {

        final View view = findViewById(R.id.button);
        
        // X方向に拡大するアニメーション
        ObjectAnimator animScaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 2f, 1f);
        // Y方向に拡大するアニメーション
        ObjectAnimator animScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 2f, 1f);
        // Z軸に回転するアニメーション
        ObjectAnimator animRotation = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
        
        // AnimatorSet でアニメーションを組み合わせる
        AnimatorSet anim = new AnimatorSet();
        anim.playTogether(animScaleX, animScaleY, animRotation);
        
        // 継続時間を設定
        anim.setDuration(5000);

        // アニメーションを開始
        anim.start();
    }
}
