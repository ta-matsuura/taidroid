package icsbook.sample.section4.example5;

import icsbook.sample.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main4_1);

        findViewById(R.id.notificationBtn).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                showNotification();
            }
        });
    }

    private void showNotification() {

        Notification.Builder builder = new Notification.Builder(this);
        
        // 通知が発行されたときにステータスバーに出るアイコン
        builder.setSmallIcon(R.drawable.stat_notify_setting);
        // 通知が発行されたときにステータスバーに出る文字
        builder.setTicker("Notification!");
        // 通知を発行するタイミング
        builder.setWhen(System.currentTimeMillis());
        
        // 通知をタップしたときに実行されるインテント
        Intent notificationIntent1 = new Intent(this, MainActivity.class);
        // 上記のインテントをアクティビティとして起動するためのPendingIntent
        PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 0, notificationIntent1, 0);
        // タップされたときのインテントをセット
        builder.setContentIntent(pendingIntent1);
        
        // カスタムレイアウト用の RemoteViews を作成
        RemoteViews customViews = new RemoteViews(getPackageName(), R.layout.custom_notification);

        // カスタムレイアウトのボタンをタップしたときに実行されるインテント
        Intent notificationIntent2 = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.google.com"));
        // インテントをブロードキャストとして発行するためのPendingIntent
        PendingIntent pendingIntent2 = 
                    PendingIntent.getActivity(this, 0, notificationIntent2, 0);
        // カスタムレイアウト上のボタンにインテントをセット
        customViews.setOnClickPendingIntent(R.id.button, pendingIntent2);

        // ステータスバーを引き出したときに表示されるタイトル（1 行目）
        customViews.setTextViewText(R.id.title, "My notification");
        // ステータスバーを引き出したときに表示される内容（2 行目）
        customViews.setTextViewText(R.id.text, "Hello World!");
        // アイコン
        customViews.setImageViewResource(R.id.icon, R.drawable.stat_notify_setting);
        
        builder.setContent(customViews);
        
        Notification notification = builder.getNotification();

        // 通知を発行
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }
}
