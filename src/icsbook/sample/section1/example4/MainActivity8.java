package icsbook.sample.section1.example4;

import icsbook.sample.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.NumberPicker;

public class MainActivity8 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1_4_6);

        NumberPicker numberPicker1 = (NumberPicker) findViewById(R.id.numberPicker1);

        // 選択範囲の最大値をセット
        numberPicker1.setMaxValue(10);

        // 選択範囲の最小値をセット
        numberPicker1.setMinValue(5);

        // デフォルト値をセット
        numberPicker1.setValue(7);

        // 最大値のあとに最小値がきて選択肢全体がつながるようにする
        numberPicker1.setWrapSelectorWheel(true);

        // アップボタン／ダウンボタンを長押ししたときに次の候補へ 100ms で遷移
        // アップボタン／ダウンボタンを長押ししたときに次の候補へ自動で遷移するまでの時間をセット
        numberPicker1.setOnLongPressUpdateInterval(100);

        
        
        NumberPicker numberPicker2 = (NumberPicker) findViewById(R.id.numberPicker2);

        // 選択候補として表示する文字列
        String[] displayedValues = { "RED", "GREEN", "BLUE", "YELLOW", "CYAN", "MAZENDA" };

        // 文字列の配列を選択候補の文字列としてセット
        numberPicker2.setDisplayedValues(displayedValues);

        // 最大値を選択候補の文字列配列の最大インデックスにセット
        numberPicker2.setMaxValue(displayedValues.length - 1);

        // 選択候補が変わった時のリスナーをセット
        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldValue, int newValue) {
                // 選択候補が変わったときに呼ばれる
                // 第２引数に前の値、第３引数に新しい値が渡される
            }
        });
    }
}
