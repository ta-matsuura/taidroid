package icsbook.sample.section5.example5;

import icsbook.sample.R;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity3 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main5_5_3);

        startAnimation();
    }
        
    private void startAnimation() {

        final CustomButton button = (CustomButton)findViewById(R.id.button);
        
        // オリジナルプロパティの ObjectAnimator を作成
        ObjectAnimator anim = ObjectAnimator.ofInt(button, "backgroundColorRed", 0, 255);

        // アニメーションの対象オブジェクトをセット
        anim.setTarget(button);
        
        // 継続時間を設定
        anim.setDuration(5000);

        // アニメーションを開始
        anim.start();
    }
}
