package icsbook.sample.section5.example3;

import icsbook.sample.R;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main5_3);

        startAnimation();
    }

    private void startAnimation() {

        final TextView tv = (TextView)findViewById(R.id.text);
        final StringBuffer sb = new StringBuffer();
        sb.append("Time\tFraction\tValue\n");
        
        // 開始値 0f, 終了値 10f の ValueAnimator を作成
        ValueAnimator anim = ValueAnimator.ofFloat(0f, 10f);

        // 継続時間を設定
        anim.setDuration(500);

        // アニメーション中各フレーム毎に呼ばれるリスナーをセット
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            
            // アニメーション中の値が計算された後に呼ばれる
            public void onAnimationUpdate(ValueAnimator animator) {

                // 現在の経過時間
                long time = animator.getCurrentPlayTime();

                // アニメーション中の経過割合
                float fraction = animator.getAnimatedFraction();
                
                // アニメーション中の値を取得する getAnimatedValue() は 
                // Object クラスを返すので適切なクラスにキャストする
                float value = (Float) animator.getAnimatedValue();

                sb.append(String.format("%03d\t\t%5.3f\t\t%6.3f\n", time, fraction, value));
                tv.setText(sb.toString());
            }
        });

        anim.start();
    }
}
