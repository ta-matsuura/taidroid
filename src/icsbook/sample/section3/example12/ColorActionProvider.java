package icsbook.sample.section3.example12;

import icsbook.sample.R;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListPopupWindow;
import android.widget.TextView;

public class ColorActionProvider extends ActionProvider {

    public static class ColorItem {
        public String colorName;
        public int colorCode;

        public ColorItem(String name, int code) {
            colorName = name;
            colorCode = code;
        }

        @Override
        public String toString() {
            return colorName;
        }
    }

    public class ColorItemAdapter extends ArrayAdapter<ColorItem> {

        public ColorItemAdapter(Context context, int layoutId, List<ColorItem> objects) {
            super(context, layoutId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ColorItem colorItem = getItem(position);

            View v = super.getView(position, convertView, parent);
            v.setBackgroundColor(colorItem.colorCode);

            return v;
        }
    }

    public interface OnColorListSelectedListener {
        public void onColorListSelected(int color);
    }

    private OnColorListSelectedListener mListener;

    private Context mContext;

    private View mAnchorView;

    private ListPopupWindow mListPopupWindow;

    private List<ColorItem> mColorList;

    public ColorActionProvider(Context context) {
        super(context);
        mContext = context;
    }

    public void setOnColorListSelectedListener(OnColorListSelectedListener l) {
        mListener = l;
    }

    public void setColorList(List<ColorItem> colorList) {
        mColorList = colorList;
    }

    @Override
    public View onCreateActionView() {

        // LayoutInflater を取得
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        // レイアウトXMLファイルから　Action View 用の View を取得
        View view = layoutInflater.inflate(R.layout.custom_action_provider, null);

        // タップされたらドロップダウンリストで色一覧を表示
        ImageButton button = (ImageButton) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });

        mAnchorView = view;

        return view;
    }

    private ListPopupWindow getListPopupWindow() {
        if (mListPopupWindow == null) {
            mListPopupWindow = new ListPopupWindow(mContext);
            ColorItemAdapter mAdapter = new ColorItemAdapter(mContext, android.R.layout.simple_dropdown_item_1line,
                    mColorList);
            mListPopupWindow.setAdapter(mAdapter);
            mListPopupWindow.setAnchorView(mAnchorView);
            mListPopupWindow.setModal(true);
            mListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                    mListPopupWindow.dismiss();

                    String colorCode = ((TextView) view).getText().toString();
                    int color = Color.parseColor(colorCode);

                    if (mListener != null) {
                        mListener.onColorListSelected(color);
                    }
                }
            });
        }
        return mListPopupWindow;
    }

    private void showPopup() {

        ListPopupWindow popupWindow = getListPopupWindow();

        if (!popupWindow.isShowing()) {
            popupWindow.setContentWidth(200);
            popupWindow.show();
        }
    }
}
