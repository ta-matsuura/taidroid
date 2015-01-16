package icsbook.sample.section8.example2;

import icsbook.sample.R;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    // ドラッグ＆ドロップさせる View
    private View mDragItem;

    // ドロップさせる領域の TextView
    private TextView mDropArea;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main8_1);

        mDragItem = findViewById(R.id.drag_item);
        mDropArea = (TextView) findViewById(R.id.drop_area);

        // ロングタップされたらドラッグ＆ドロップを開始
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
                    case DragEvent.ACTION_DROP:
                        
                        // DragEvent から ClipData を取得
                        ClipData clipData = event.getClipData();

                        // MIME タイプが文字列のものかチェック
                        if (!clipData.getDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                            return true;
                        }
                        
                        // ClipData がアイテムを持ってるかチェック
                        if (clipData.getItemCount() <= 0) {
                            return true;
                        }
                        
                        // ClipData から文字列を取得
                        CharSequence text = clipData.getItemAt(0).getText();
                         
                        if (!TextUtils.isEmpty(text)) {
                            mDropArea.setText(text);
                        }
                        break;
                }
                return true;
            }
        });
    }
}
