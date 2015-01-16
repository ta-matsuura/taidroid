package icsbook.sample.section5.example5;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;

public class CustomButton extends Button {

    int mRed = 127;
    int mBlue = 127;
    int mGreen = 127;
    
    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    // オリジナルプロパティ用のセッターメソッド
    public void setBackgroundColorRed(int value) {
        if(value > 255)
            value = 255;
        if(value < 0)
            value = 0;
        
        mRed = value;
        
        int color = Color.rgb(mRed, mGreen, mBlue);
        setBackgroundColor(color);
    }
    
    // オリジナルプロパティ用のゲッターメソッド
    public int getBackgroundColorRed() {
        return mRed;
    }
}
