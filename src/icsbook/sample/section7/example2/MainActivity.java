package icsbook.sample.section7.example2;

import icsbook.sample.R;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    // コピーする文字を取り出すための EditText
    private EditText mCopyArea;

    // 文字をペーストするための EditText
    private EditText mPasteArea;

    // クリップボードに ClipData を格納するための ClipboardManager
    private ClipboardManager mClipboardManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main7_1);

        // ClipboardManager を取得
        mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        mCopyArea = (EditText) findViewById(R.id.copy_text);
        mPasteArea = (EditText) findViewById(R.id.paste_text);

        // コピーボタンが押されたときの処理
        findViewById(R.id.copy_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // コピーする文字列を取得
                String copyText = mCopyArea.getText().toString();

                if (!TextUtils.isEmpty(copyText)) {

                    // 文字列を１つ持つ ClipData を作成
                    ClipData data = ClipData.newPlainText("label", copyText);

                    // クリップボードに ClipData を格納
                    mClipboardManager.setPrimaryClip(data);

                    Toast.makeText(MainActivity.this, "Copy to clipboard : " + copyText, Toast.LENGTH_SHORT).show();
                }
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

                // ClipDescription が文字列のMIMEタイプを含んでいるかチェック
                if (!description.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    return;
                }

                // ClipData を取得
                ClipData data = mClipboardManager.getPrimaryClip();

                // ClipData がアイテムを持っているかチェック
                if (data != null && data.getItemCount() > 0) {
                    
                    // ClipData から文字列を取得
                    CharSequence pasteText = data.getItemAt(0).getText();
                    
                    // EditText に取得した文字列を表示
                    mPasteArea.setText(pasteText);
                    
                    Toast.makeText(MainActivity.this, "Paste from clipboard : " + pasteText, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
