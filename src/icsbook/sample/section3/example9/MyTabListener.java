package icsbook.sample.section3.example9;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

public class MyTabListener<T extends Fragment> implements ActionBar.TabListener {

    private Fragment mFragment;
    private final Activity mActivity;
    private final String mTag;
    private final Class<T> mClass;

    public MyTabListener(Activity activity, String tag, Class<T> clz) {
        mActivity = activity;
        mTag = tag;
        mClass = clz;
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {

        // このタブに関連する Fragment がすでに生成されているかどうか調べる
        if (mFragment == null) {
        
            // まだ生成されていない場合は、Activity とクラス名から生成する
            mFragment = Fragment.instantiate(mActivity, mClass.getName());

            // android.R.id.content という ID の ViewGroup に Fragment を追加する
            ft.add(android.R.id.content, mFragment, mTag);

        } else {
            // すでに生成されている場合は、表示するために単に attach する
            ft.attach(mFragment);
        }
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        if (mFragment != null) {
            // 選択された Fragment を表示するために、この Fragment は detach する
            ft.detach(mFragment);
        }
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }
}
