package icsbook.sample.section1.example7;

import icsbook.sample.R;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

public class MainActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1_7);

        // TabHost をセットアップ
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        // ViewPager のインスタンスを取得
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        // ViewPager 用の Adapter を作成
        TabsAdapter tabsAdapter = new TabsAdapter(this, tabHost, viewPager);

        // タブのつまみのサイズを画面サイズから取得
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int tabWidth = metrics.widthPixels / 3;
        int tabHeight = tabWidth / 2;

        // タブを生成して ViewPager 用の Adapter に追加
        for (int i = 0; i < ListData.titleList.length; i++) {
            TabSpec spec = tabHost.newTabSpec("tab" + i).setIndicator(getTabView(tabWidth, tabHeight, "TAB" + i));
            tabsAdapter.addTab(spec, ListData.titleList[i]);
        }
    }

    // 指定されたサイズ以上の大きさの TextView を作成
    private TextView getTabView(int width, int height, String title) {
        TextView tv = new TextView(this);
        tv.setMinimumWidth(width);
        tv.setMinimumHeight(height);
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundResource(R.drawable.tab_indicator_holo);
        tv.setText(title);
        return tv;
    }

    // ViewPager と TabHost を結びつける Adapter
    public static class TabsAdapter extends PagerAdapter implements TabHost.OnTabChangeListener,
            ViewPager.OnPageChangeListener {

        private final Context mContext;
        private final TabHost mTabHost;
        private final ViewPager mViewPager;
        private final ArrayList<String> mTabs = new ArrayList<String>();

        // タブのつまみに対するダミーのコンテンツを作るクラス
        static class DummyTabFactory implements TabHost.TabContentFactory {
            private final Context mContext;

            public DummyTabFactory(Context context) {
                mContext = context;
            }

            @Override
            public View createTabContent(String tag) {
                View v = new View(mContext);
                v.setMinimumWidth(0);
                v.setMinimumHeight(0);
                return v;
            }
        }

        public TabsAdapter(Context context, TabHost tabHost, ViewPager pager) {
            mContext = context;
            mTabHost = tabHost;
            mViewPager = pager;
            mTabHost.setOnTabChangedListener(this);
            mViewPager.setAdapter(this);
            mViewPager.setOnPageChangeListener(this);
        }

        // 指定された TabSpec とページ内の文字からタブを追加
        public void addTab(TabHost.TabSpec tabSpec, String content) {
            tabSpec.setContent(new DummyTabFactory(mContext));
            mTabs.add(content);
            mTabHost.addTab(tabSpec);
            notifyDataSetChanged();
        }

        /**
         * ページ数を返す
         */
        @Override
        public int getCount() {
            return mTabs.size();
        }

        /**
         * 引数で渡されたページの view が instantiateitem() メソッドで
         * 返された object と関連しているかどうかを返す
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == (View) object;
        }

        /**
         * 引数で渡された ViewPager に指定された位置のページ要素を追加して返す
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ViewPager pager = (ViewPager) container;

            TextView tv = new TextView(mContext);
            tv.setText(mTabs.get(position));

            pager.addView(tv, 0);
            return tv;
        }

        /**
         * 引数で渡された ViewPager から指定されたページ要素を除く
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object view) {
            ((ViewPager) container).removeView((View) view);
        }

        /**
         * 選択されたタブと対応するページを ViewPager にセット
         */
        @Override
        public void onTabChanged(String tabId) {
            // 選択されているタブの位置を取得
            int position = mTabHost.getCurrentTab();
            
            // 選択中のタブの位置のページをセット
            mViewPager.setCurrentItem(position);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        /**
         * ページが切り替わったら、対応するタブを選択状態にセット
         */
        @Override
        public void onPageSelected(int position) {
            TabWidget widget = mTabHost.getTabWidget();
            HorizontalScrollView hsv = (HorizontalScrollView) widget.getParent();

            int oldFocusability = widget.getDescendantFocusability();
            widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

            // 現在のページの位置に対応するタブを選択状態にセット
            mTabHost.setCurrentTab(position);
            
            // HorizontalScroll内の現在のタブのつまみの View を取得
            View child = widget.getChildAt(position);

            // つまみが端でない場合は中心にくるようにスクロール
            float center = child.getLeft() + child.getWidth() / 2 - hsv.getWidth() / 2;
            hsv.smoothScrollTo((int) center, 0);

            widget.setDescendantFocusability(oldFocusability);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}
