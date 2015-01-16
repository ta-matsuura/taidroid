package icsbook.sample.section5.example8;

import icsbook.sample.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main5_8);

        // アニメーションさせる View
        final View view = findViewById(R.id.button);
        
        // アニメーションの時間をセット
        view.animate().setDuration(2000);

        // Fade Out ボタンが押されたら、View の透明度を現在の値から透明にする
        findViewById(R.id.fadeOutBtn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                view.animate().alpha(0);
            }
        });

        // Fade Out ボタンが押されたら、View の透明度を現在の値から不透明にする
        findViewById(R.id.fadeInBtn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                view.animate().alpha(1);
            }
        });

        // Move Over ボタンが押されたら、View を右下に移動する
        findViewById(R.id.MoveOverBtn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ViewGroup container = (ViewGroup) findViewById(android.R.id.content);
                int x = container.getWidth() - view.getWidth();
                int y = container.getHeight() - view.getHeight();
                view.animate().x(x).y(y);
            }
        });
        
        // Move Back ボタンが押されたら、View を左上に移動する
        findViewById(R.id.moveBackBtn).setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                view.animate().x(0).y(0);
            }
        });
        
        // Rotate ボタンが押されたら、View をZ軸回りに2回転する
        findViewById(R.id.rotateBtn).setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                view.animate().rotationY(720);
            }
        });
    }
}
