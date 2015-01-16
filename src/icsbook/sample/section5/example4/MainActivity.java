package icsbook.sample.section5.example4;

import icsbook.sample.R;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main5_3);

        startAnimation();
    }

    public class PointEvaluator implements TypeEvaluator<Point> {

        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            int x = (int) ((endValue.x - startValue.x) * fraction);
            int y = (int) ((endValue.y - startValue.y) * fraction);
            return new Point(x, y);
        }        
    }
    
    private void startAnimation() {

        final TextView tv = (TextView)findViewById(R.id.text);
        final StringBuffer sb = new StringBuffer();
        sb.append("Time\tFraction\tValue\n");
        
        Point p0 = new Point(0, 0);
        Point p1 = new Point(50, 200);
        
        // 開始値 p0, 終了値 p1 の ValueAnimator を作成
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), p0, p1);
        
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
                Point value = (Point) animator.getAnimatedValue();

                sb.append(String.format("%03d\t\t%5.3f\t\t%d, %d\n", time, fraction, value.x, value.y));
                tv.setText(sb.toString());
            }
        });

        anim.start();
    }
}
