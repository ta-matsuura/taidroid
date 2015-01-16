package icsbook.sample.section6.example2;

import icsbook.sample.R;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class ListWidgetService extends RemoteViewsService {

    public class WidgetItem {
        String mText;
        
        public WidgetItem(String text) {
            mText = text;
        }
    }
    
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        List<WidgetItem> data = new ArrayList<WidgetItem>();
        for (int i = 0; i < 10; i++) {
            data.add(new WidgetItem(i + "!"));
        }
        
        return new ListRemoteViewFactory(this.getApplicationContext(), intent, data);
    }

    class ListRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {
        private List<WidgetItem> mData = new ArrayList<WidgetItem>();
        private Context mContext;

        public ListRemoteViewFactory(Context context, Intent intent, List<WidgetItem> data) {
            mContext = context;
            mData = data;
        }

        public void onCreate() {
        }

        public void onDestroy() {
            mData.clear();
        }

        public void onDataSetChanged() {
        }

        public int getCount() {
            return mData.size();
        }

        public long getItemId(int position) {
            return position;
        }

        public RemoteViews getLoadingView() {
            // 読み込み中用の View を返す
            return null;
        }

        public RemoteViews getViewAt(int position) {
            // ListView の子 View に表示する RemoteViews を取得
            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.listwidget_item);

            // RemoteViews 内の TextView に表示する文字を指定
            views.setTextViewText(R.id.widget_item, mData.get(position).mText);

            // タップされたアイテムを識別するための情報を持ったIntentを作成
            Intent fillInIntent = new Intent();
            fillInIntent.putExtra(ListWidgetProvider.EXTRA_ITEM, position);

            // RemoteViews がタップされたときに、
            // PendingItentTemplate + 指定された Intent の情報
            // を持った Intent を発行する
            views.setOnClickFillInIntent(R.id.widget_item, fillInIntent);

            return views;
        }

        public int getViewTypeCount() {
            return 1;
        }

        public boolean hasStableIds() {
            return true;
        }
    }
}