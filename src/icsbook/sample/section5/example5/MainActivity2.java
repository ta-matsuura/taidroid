package icsbook.sample.section5.example5;

import icsbook.sample.R;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity2 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main5_5);

        startAnimation();
    }

    private void startAnimation() {

        final Button button = (Button) findViewById(R.id.button);

        // アニメーションXMLファイルから ObjectAnimator を作成
        ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.anim2);

        // アニメーションの対象オブジェクトをセット
        anim.setTarget(button);

        // 継続時間を設定
        anim.setDuration(5000);
        anim.setFloatValues(0f, 1f, 0f, 1f);

        // アニメーションを開始
        anim.start();
    }
}
