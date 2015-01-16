package icsbook.sample.section5.example10;

import icsbook.sample.R;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
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

    public void startAnimation() {

        final Button button = (Button) findViewById(R.id.button);

        // Keyframe で時間とプロパティ値の組み合わせを作成
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(.2f, 180f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 360f);

        // z軸周りの回転に Keyframe をセット
        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);

        // PropertyValueHolder から ObjectAnimator を作成
        ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder(button, pvhRotation);

        // 継続時間を設定
        anim.setDuration(5000);

        anim.start();
    }
}
