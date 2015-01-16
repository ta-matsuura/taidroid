package icsbook.sample.section6.example1;

import icsbook.sample.R;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

public class GridWidgetProvider extends AppWidgetProvider {

    public static final String EXTRA_ITEM = "icsbook.sample.section6.example.TOAST_ACTION";
    public static final String TOAST_ACTION = "icsbook.sample.section6.example.EXTRA_ITEM";

    @Override
    public void onReceive(Context context, Intent intent) {
        // GridView の子 View がタップされたときに発行される
        // Broadcast を拾ってToast を表示する
        if (intent.getAction().equals(TOAST_ACTION)) {
            int viewIndex = intent.getIntExtra(EXTRA_ITEM, 0);
            Toast.makeText(context, "Touched view" + viewIndex, Toast.LENGTH_SHORT).show();
        }
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.gridwidget_layout);

        // 1. GridView にひもづける RemoteViewsService (ここでは GridWidgetService)
        // を指定するための intent
        Intent intent = new Intent(context, GridWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        // 2. 1で作った intent を GridView の ID を指定してセット
        views.setRemoteAdapter(R.id.grid_view, intent);

        // 3. GridView のデータが空のときに表示する View
        views.setEmptyView(R.id.grid_view, R.id.empty_view);

        // 3. GridView の子 View がタップされたときに発行する Broadcast の雛形をセット
        Intent toastIntent = new Intent(context, GridWidgetProvider.class);
        toastIntent.setAction(GridWidgetProvider.TOAST_ACTION);
        PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.grid_view, toastPendingIntent);

        // app widget を更新
        appWidgetManager.updateAppWidget(appWidgetIds, views);
    }
}
