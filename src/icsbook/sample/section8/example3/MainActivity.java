package icsbook.sample.section8.example3;

import icsbook.sample.R;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    // ドラッグ＆ドロップさせる View
    private View mDragItem;

    // ドロップさせる領域の TextView
    private TextView dropArea;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main8_1);

        mDragItem = findViewById(R.id.drag_item);
        dropArea = (TextView) findViewById(R.id.drop_area);

        // ロングタップされたらドラッグ＆ドロップを開始
        mDragItem.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                
                // ClipData に格納する Uri
                Uri contactUri = ContactsContract.AUTHORITY_URI;
                
                // ドラッグ＆ドロップで受け渡す ClipData を作成                
                ClipData data = ClipData.newUri(getContentResolver(), "label", contactUri);
                
                // ドラッグ＆ドロップを開始
                mDragItem.startDrag(data, new View.DragShadowBuilder(mDragItem), null, 0);

                return true;
            }
        });

        // ドラッグイベントを受け取るためのリスナーをセット
        dropArea.setOnDragListener(new View.OnDragListener() {

            @Override
            public boolean onDrag(View v, DragEvent event) {
                
                // DragEvent のアクションを取得
                int action = event.getAction();

                switch (action) {
                    case DragEvent.ACTION_DROP:
                        
                        // DragEvent から ClipData を取得
                        ClipData clipData = event.getClipData();

                        // ClipData から ClipDescription を取得
                        ClipDescription clipDescription = clipData.getDescription();
                        
                        // MIME タイプが Uri のものかチェック
                        if (!clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_URILIST)) {
                            return true;
                        }
                        
                        // ClipData がアイテムを持ってるかチェック
                        if (clipData.getItemCount() <= 0) {
                            return true;
                        }
                        
                        // ClipData から Uri を取得
                        dropArea.setText(clipDescription.getMimeType(0) + ", "
                                + clipData.getItemAt(0).getUri().toString());
                        break;
                }
                return true;
            }
        });
    }
}
