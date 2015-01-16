package icsbook.sample.section4.example4;

import icsbook.sample.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

public class MainActivity3 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main4_1);
        
        findViewById(R.id.notificationBtn)
            .setOnClickListener(new View.OnClickListener() {
            
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
        
        // ステータスバーを引き出したときに表示されるタイトル（1 行目）
        builder.setContentTitle("My notification");
        // ステータスバーを引き出したときに表示される内容（2 行目）
        builder.setContentText("Hello World!");
        
        // 大きいアイコンを設定
        builder.setLargeIcon(BitmapFactory.decodeResource(
                getResources(), R.drawable.contact_pic));

        // 通知をタップしたときに処理するインテント
        Intent notificationIntent = new Intent(this, MainActivity3.class);
        // 上記のインテントをアクティビティとして起動するためのPendingIntent
        PendingIntent intent = PendingIntent
            .getActivity(this, 0, notificationIntent, 0);
        
        // タップで起動するインテントをセット
        builder.setContentIntent(intent);
        
        // 不定状態のプログレスバーを表示
        builder.setProgress(100, 50, true);
        
        Notification notification = builder.getNotification();
        
        // 通知を発行
        NotificationManager manager = (NotificationManager)
            getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }
}
