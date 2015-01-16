package icsbook.sample.section1.example7;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // タップされた位置を取得
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        // ViewPager を生成
        ViewPager pager = new ViewPager(this);
        
        // ViewPager 用の Adapter をセット
        pager.setAdapter(new ViewPagerAdapter(this));
        
        // 初期位置を渡されたページ番号にセット
        pager.setCurrentItem(position);
        
        // ViewPager を UI に追加
        setContentView(pager);
    }

    public class ViewPagerAdapter extends PagerAdapter {

        private Context mContext;
        
        /**
         * コンストラクタ
         * @param context
         */
        public ViewPagerAdapter(final Context context) {
            mContext = context;
        }

        /**
         * ページ数を返す
         */
        @Override
        public int getCount() {
            return ListData.contentList.length;
        }

        /**
         * 引数で渡された ViewPager に指定された位置のページ要素を追加して返す
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ViewPager pager = (ViewPager) container;

            TextView tv = new TextView(mContext);
            tv.setText(ListData.contentList[position]);

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
         * 引数で渡されたページの view が instantiateitem() メソッドで
         * 返された object と関連しているかどうかを返す
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == (View) object;
        }

        /**
         * 状態を保存したい Parcelable を返す
         */
        @Override
        public Parcelable saveState() {
            return null;
        }

        /**
         * 引数で渡された Parcelable から状態を復元する
         */
        @Override
        public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        }

        /**
         * 表示されているページの切り替えが始まったときに呼ばれる
         */
        @Override
        public void startUpdate(ViewGroup container) {
        }

        /**
         * 表示されているページの切り替えが終わったときに呼ばれる
         */
        @Override
        public void finishUpdate(ViewGroup container) {
        }
    }
}
