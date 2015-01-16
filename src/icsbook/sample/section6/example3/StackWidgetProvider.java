package icsbook.sample.section6.example3;

import icsbook.sample.R;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

public class StackWidgetProvider extends AppWidgetProvider {

    public static final String EXTRA_ITEM = "icsbook.sample.section6.example3.TOAST_ACTION";
    public static final String TOAST_ACTION = "icsbook.sample.section6.example3.EXTRA_ITEM";

    @Override
    public void onReceive(Context context, Intent intent) {
        // StackView の子 View がタップされたときに発行される
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

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.stackwidget_layout);

        // 1. StackView にひもづける RemoteViewsService (ここでは StackWidgetService)
        // を指定するための intent
        Intent intent = new Intent(context, StackWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        // 2. 1で作った intent を StackView の ID を指定してセット
        views.setRemoteAdapter(R.id.stack_view, intent);

        // 3. StackView のデータが空のときに表示する View
        views.setEmptyView(R.id.stack_view, R.id.empty_view);

        // 3. StackView の子 View がタップされたときに発行する Broadcast の雛形をセット
        Intent toastIntent = new Intent(context, StackWidgetProvider.class);
        toastIntent.setAction(StackWidgetProvider.TOAST_ACTION);
        PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.stack_view, toastPendingIntent);

        // app widget を更新
        appWidgetManager.updateAppWidget(appWidgetIds, views);
    }
}
