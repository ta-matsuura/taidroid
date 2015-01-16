package icsbook.sample.section6.example4;

import icsbook.sample.R;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

public class AdapterViewFlipperWidgetProvider extends AppWidgetProvider {

    public static final String EXTRA_ITEM = "icsbook.sample.section6.example4.TOAST_ACTION";
    public static final String TOAST_ACTION = "icsbook.sample.section6.example4.EXTRA_ITEM";

    @Override
    public void onReceive(Context context, Intent intent) {
        // ViewFlipper の子 View がタップされたときに発行される
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

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.adapterviewflipper_layout);

        // 1. AdapterViewFlipper にひもづける RemoteViewsService (ここでは StackWidgetService)
        // を指定するための intent
        Intent intent = new Intent(context, AdapterViewFlipperWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        // 2. 1で作った intent を AdapterViewFlipper の ID を指定してセット
        views.setRemoteAdapter(R.id.adapterviewflipper, intent);

        // 3. AdapterViewFlipper のデータが空のときに表示する View
        views.setEmptyView(R.id.adapterviewflipper, R.id.empty_view);

        // 3. AdapterViewFlipper の子 View がタップされたときに発行する Broadcast の雛形をセット
        Intent toastIntent = new Intent(context, AdapterViewFlipperWidgetProvider.class);
        toastIntent.setAction(AdapterViewFlipperWidgetProvider.TOAST_ACTION);
        PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.adapterviewflipper, toastPendingIntent);

        // app widget を更新
        appWidgetManager.updateAppWidget(appWidgetIds, views);
    }
}
