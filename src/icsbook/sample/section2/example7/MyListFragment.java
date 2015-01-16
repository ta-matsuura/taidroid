package icsbook.sample.section2.example7;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyListFragment extends ListFragment {
    
    // タップされた最後のアイテムの位置
    private int mCurrentPosition = -1;

    // アイテムがタップされたときのリスナー
    public interface OnListItemClickListener {
        public void onListItemClick(int position, String value);
    }

    // アイテムがタップされたときのリスナー
    private OnListItemClickListener mOnListItemClickListener;

    // アイテムがタップされたときのリスナーをセット
    public void setOnListItemClickListener(OnListItemClickListener l) {
        mOnListItemClickListener = l;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            data.add("Sample" + i);
        }
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                data);
        setListAdapter(adapter);
        
        // 保存されているタップ位置を取得
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt("currentPosition", -1);
        }
        
        // 保存されているタップ位置が有効な場合はその内容を表示
        if (mCurrentPosition != -1)
            showContent(mCurrentPosition);
    }

    // タップされた位置を保存
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentPosition", mCurrentPosition);
    }

    // リストのアイテムがタップされたときに呼び出される
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        showContent(position);
    }

    private void showContent(int position) {
        // タップされた位置を最後のタップ位置にセット
        mCurrentPosition = position;
        // タップされたときのリスナーのメソッドを呼び出す
        if (mOnListItemClickListener != null) {
            String value = (String) getListAdapter().getItem(position);
            mOnListItemClickListener.onListItemClick(position, value);
        }
    }
}
