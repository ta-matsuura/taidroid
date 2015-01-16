package icsbook.sample.section3.example5;

import icsbook.sample.R;
import android.content.Context;
import android.view.CollapsibleActionView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MySeekBar extends LinearLayout implements CollapsibleActionView {

    public MySeekBar(Context context) {
        super(context);
        
        setGravity(Gravity.CENTER_VERTICAL);
        
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);        
        setLayoutParams(params);
        
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View child = inflater.inflate(R.layout.actionview_seekbar, this, true);

        final SeekBar seekBar = (SeekBar) child.findViewById(R.id.seekbar);
        final TextView seekValue = (TextView) child.findViewById(R.id.seek_value);
        
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekValue.setText(progress + "");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        
        seekBar.setProgress(50);
        seekValue.setText("50");
    }

    @Override
    public void onActionViewCollapsed() {
        // アクションビューが格納されたときに呼び出される

    }

    @Override
    public void onActionViewExpanded() {
        // アクションビューが展開されたときに呼び出される
    }

}
