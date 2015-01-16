package icsbook.sample.section8.example5;

import icsbook.sample.R;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private View mDragItem;

    private TextView dropArea;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main8_5);

        mDragItem = findViewById(R.id.drag_item);
        dropArea = (TextView) findViewById(R.id.drop_area);

        mDragItem.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                ClipData data = ClipData.newPlainText("label", "DragItem");
                
                // View.DragShadowBuilder を継承したオリジナルクラス MyDragShadowBuilder を
                // 第二引数に指定
                mDragItem.startDrag(data, new MyDragShadowBuilder(mDragItem, Color.RED), null, 0);
                
                return true;
            }
        });

        dropArea.setOnDragListener(new View.OnDragListener() {

            @Override
            public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();

                switch (action) {
                    case DragEvent.ACTION_DROP:
                        ClipData clipData = event.getClipData();

                        if (clipData.getDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                            if (clipData.getItemCount() > 0) {
                                CharSequence text = clipData.getItemAt(0).getText();
                                if (!TextUtils.isEmpty(text)) {
                                    dropArea.setText(text);
                                }
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }

    /**
     * オリジナルのドラッグ中の影を作るクラス
     */
    private class MyDragShadowBuilder extends View.DragShadowBuilder {
        
        // 影用の Drawable
        private Drawable mShadow;

        public MyDragShadowBuilder(View v, int c) {
            super(v);

            // 引数で指定された色の ColorDrawable を作成
            mShadow = new ColorDrawable(c);
            
            // 引数で指定された View を Drawable にひもづけ
            mShadow.setCallback(v);
            
            // Drawable の領域を View のサイズにセット
            mShadow.setBounds(0, 0, v.getWidth(), v.getHeight());
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            super.onDrawShadow(canvas);
            
            // 引数で指定された背景をキャンバスに描画
            mShadow.draw(canvas);
            
            // そのうえに元の View を描画
            getView().draw(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
            super.onProvideShadowMetrics(shadowSize, shadowTouchPoint);
        }
    }
}
