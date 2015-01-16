package icsbook.sample.section2.example10;

import icsbook.sample.R;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2_10);

        // FragmentManager を取得
        FragmentManager manager = getFragmentManager();
        
        // ViewPager のインスタンスを取得
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        
        // FragmentPagerAdapter を継承したクラスのアダプターを作成し、ViewPager にセット
        final MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(manager);
        viewPager.setAdapter(adapter);
        
        // First ボタンが押されたら、最初のページを表示
        findViewById(R.id.first).setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });
        
        // Last ボタンが押されたら、最後のページを表示
        findViewById(R.id.last).setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(adapter.getCount() - 1);
            }
        });
    }

    /**
     * FragmentPagerAdapter を継承したクラス
     */
    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private static final int NUM_VIEWS = 5;

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // 引数で指定された位置のページに表示する Fragment インスタンスを返す
            return ColorFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // ページ数を返す
            return NUM_VIEWS;
        }

    }
    
    public static class ColorFragment extends Fragment {
        private static final int COLOR_CYCLE = 5;
        int mNum;

        static ColorFragment newInstance(int num) {
            ColorFragment f = new ColorFragment();
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);

            return f;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            
            int num = mNum % COLOR_CYCLE;
            
            int red = num * 25;
            int green = (COLOR_CYCLE - num - 1) * 25;
            int blue = 125;

            View view = inflater.inflate(R.layout.content, container, false);
            view.setBackgroundColor(Color.rgb(red, green, blue));
            TextView text = (TextView) view.findViewById(R.id.text);
            text.setText("Page : " + mNum);
            return view;
        }
    }
}
