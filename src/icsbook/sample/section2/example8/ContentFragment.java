package icsbook.sample.section2.example8;

import icsbook.sample.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContentFragment extends Fragment {

    // 渡された引数をセットしたContentFragment を返す
    static ContentFragment getInstance(String value) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString("text", value);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null)
            return null;
        
        // レイアウトをインフレートし、TextView に引数として渡された文字列をセット
        View v = inflater.inflate(R.layout.content, container, false);
        Bundle args = getArguments();
        String value = args.getString("text");
        TextView tv = (TextView) v.findViewById(R.id.text);
        tv.setText(value);
        return v;
    }

}
