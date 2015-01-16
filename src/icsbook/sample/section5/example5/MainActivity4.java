package icsbook.sample.section5.example5;

import icsbook.sample.R;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity4 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main5_5);

        startAnimation();
    }

    private void startAnimation() {

        final View view = findViewById(R.id.button);
        
        // X方向に拡大するアニメーション
        PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("scaleX", 1f, 2f, 1f);
        // Y方向に拡大するアニメーション
        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleY", 1f, 2f, 1f);
        // Z軸に回転するアニメーション
        PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("rotation", 0f, 360f);
        
        ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder(view, pvh1, pvh2, pvh3);
        
        // 継続時間を設定
        anim.setDuration(5000);

        // アニメーションを開始
        anim.start();
    }
}
