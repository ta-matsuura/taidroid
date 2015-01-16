package icsbook.sample.section8.example1;

import icsbook.sample.R;
import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    // ドラッグ＆ドロップさせる View
    private View mDragItem;

    // ドロップする領域の TextView
    private TextView mDropArea;
    
    // ドラッグ情報を表示するための TextView
    private TextView mDragInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main8_1);

        mDragItem = findViewById(R.id.drag_item);
        mDropArea = (TextView) findViewById(R.id.drop_area);
        mDragInfo = (TextView) findViewById(R.id.drag_info);

        // ロングタップされたらドラッグを開始
        mDragItem.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                
                // ドラッグ＆ドロップで受け渡す ClipData を作成
                ClipData data = ClipData.newPlainText("label", "DragItem");
                
                // ドラッグを開始
                mDragItem.startDrag(data, new View.DragShadowBuilder(mDragItem), null, 0);

                return true;
            }
        });

        // ドラッグイベントを受け取るためのリスナーをセット
        mDropArea.setOnDragListener(new View.OnDragListener() {

            @Override
            public boolean onDrag(View v, DragEvent event) {
                
                // DragEvent のアクションを取得
                int action = event.getAction();

                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        mDropArea.setText("ACTION_DRAG_STARTED");
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        mDropArea.setText(String.format("ACTION_DRAG_ENTERED : %8.3f, %8.3f", event.getX(), event.getY()));
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        mDropArea.setText(String.format("ACTION_DRAG_EXITED : %8.3f, %8.3f", event.getX(), event.getY()));
                        break;
                    case DragEvent.ACTION_DROP:
                        mDropArea.setText(String.format("ACTION_DROP : %8.3f, %8.3f", event.getX(), event.getY()));
                        break;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        mDropArea.setText(String.format("ACTION_DRAG_LOCATION : %8.3f, %8.3f", event.getX(),
                                event.getY()));
                        break;
                }

                return true;
            }
        });

        // ドラッグイベントを受け取るためのリスナーをセット
        mDragInfo.setOnDragListener(new View.OnDragListener() {

            @Override
            public boolean onDrag(View v, DragEvent event) {
                                
                // DragEvent のアクションを取得
                int action = event.getAction();

                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        mDragInfo.setText("ACTION_DRAG_STARTED");
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        mDragInfo.setText("ACTION_DRAG_ENDED");
                        break;
                }

                return true;
            }
        });
    }
}
