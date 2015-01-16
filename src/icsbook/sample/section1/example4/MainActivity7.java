package icsbook.sample.section1.example4;

import icsbook.sample.R;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.Toast;

public class MainActivity7 extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1_4_5);
        
        findViewById(R.id.showpopup).setOnClickListener(this);
        findViewById(R.id.showpopup2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        
        // Adapter を用意
        String[] data = {"data1", "data2", "data3", "data4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        
        // ListPopupWindow を生成
        final ListPopupWindow lpw = new ListPopupWindow(this);
        
        // Adapter をセット
        lpw.setAdapter(adapter);
        
        // リストのアイテムがタップされたときのリスナーをセット
        lpw.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                String s = (String)adapter.getItemAtPosition(position);
                Toast.makeText(MainActivity7.this, s, Toast.LENGTH_SHORT).show();
                lpw.dismiss();
            }
        });
        
        // アンカービューをセット
        lpw.setAnchorView(v);
        
        
        if(v.getId() == R.id.showpopup2) {
            // ポップアップを表示する位置の水平方向のオフセット
            lpw.setHorizontalOffset(20);

            // ポップアップを表示する位置の垂直方向のオフセット
            lpw.setVerticalOffset(20);
            
            // ポップアップを表示するときのアニメーションスタイルをセット
            lpw.setAnimationStyle(R.style.ListPopupWindowAnimation);
            
            // グラデーションの drawable を生成
            GradientDrawable d = new GradientDrawable(GradientDrawable.Orientation.BR_TL, new int[] {Color.BLACK, Color.DKGRAY});
            
            // 背景画像をセット
            lpw.setBackgroundDrawable(d);
            
            // プロンプトビューを用意
            ImageView iv = new ImageView(this);
            iv.setImageResource(R.drawable.ic_launcher);
            
            // プロンプトビューをセット
            lpw.setPromptView(iv);
            
            // プロンプトビューの位置をセット
            lpw.setPromptPosition(ListPopupWindow.POSITION_PROMPT_ABOVE);
        }
        
        // ポップアップを表示
        lpw.show();
    }
}
