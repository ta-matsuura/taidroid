package icsbook.sample.section7.example3;

import icsbook.sample.R;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

    // 画像サムネイルを表示するための ImageView
    private ImageView mPasteArea;

    // クリップボードに ClipData を格納するための ClipboardManager
    private ClipboardManager mClipboardManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main7_2);

        // ClipboardManager を取得
        mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        mPasteArea = (ImageView) findViewById(R.id.image);

        // 画像情報の Cursor を取得
        Cursor c = MediaStore.Images.Media.query(getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null);

        // Cursor からアダプターを作成
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_single_choice, c,
                new String[] { MediaStore.Images.Media.DISPLAY_NAME }, new int[] { android.R.id.text1 }, 0);

        final ListView listView = (ListView) findViewById(R.id.list);

        // 単一選択モードにする
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // アダプターをセット
        listView.setAdapter(adapter);

        // コピーボタンが押されたときの処理
        findViewById(R.id.copy_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // リストで選択されている位置を取得
                int position = listView.getCheckedItemPosition();

                if (position < 0) {
                    return;
                }

                // 選択されている位置のカーソルから画像のIDを取得
                Cursor c = (Cursor) adapter.getItem(position);
                int index = c.getColumnIndex(MediaStore.Images.Media._ID);
                long id = c.getLong(index);

                // ID から Uri を作成
                Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, String.valueOf(id));

                // Uri オブジェクトを１つ持つ ClipData を作成
                ClipData data = ClipData.newUri(getContentResolver(), "label", uri);

                // クリップボードに ClipData を格納
                mClipboardManager.setPrimaryClip(data);

                Toast.makeText(MainActivity.this, "Copy to clipboard : " + uri, Toast.LENGTH_SHORT).show();
            }
        });

        // ペーストボタンが押されたときの処理
        findViewById(R.id.paste_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // クリップボードにペーストするデータがあるかチェック
                if (!mClipboardManager.hasPrimaryClip()) {
                    return;
                }

                // ClipDescription を取得
                ClipDescription description = mClipboardManager.getPrimaryClipDescription();

                // ClipDescription が Uri のMIMEタイプを含んでいるかチェック
                if (!description.hasMimeType(ClipDescription.MIMETYPE_TEXT_URILIST)) {
                    return;
                }

                // ClipData を取得
                ClipData data = mClipboardManager.getPrimaryClip();

                // ClipData がアイテムを持っているかチェック
                if (data != null && data.getItemCount() > 0) {

                    // ClipData から Uri を取得
                    Uri uri = data.getItemAt(0).getUri();

                    // Uri から画像IDを取得
                    long id = Long.parseLong(uri.getLastPathSegment());
                    
                    // 画像IDからサムネイル画像を取得
                    Bitmap bm = MediaStore.Images.Thumbnails.getThumbnail(getContentResolver(), id,
                            MediaStore.Images.Thumbnails.MINI_KIND, null);
                    
                    // サムネイル画像を ImageView にセット
                    mPasteArea.setImageBitmap(bm);
                    
                    Toast.makeText(MainActivity.this, "Paste from clipboard : " + uri + ", " + bm, Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }
}
