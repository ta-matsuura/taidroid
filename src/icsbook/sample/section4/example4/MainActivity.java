package icsbook.sample.section4.example4;

import icsbook.sample.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

        Thread workerThread = new Thread() {

            @Override
            public void run() {

                int max = 100;
                for (int progress = 0; progress <= max; progress++) {
                    showProgress(max, progress);
                    
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        
        workerThread.start();
    }

    private void showProgress(int max, int progress) {
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

        // ステータスバーを引き出したときに右下に出る文字
        builder.setContentInfo((progress * 100 / max) + "%");

        // 通知をタップしたときに処理するインテント
        Intent notificationIntent = new Intent(this, MainActivity.class);
        // 上記のインテントをアクティビティとして起動するためのPendingIntent
        PendingIntent intent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        // タップで起動するインテントをセット
        builder.setContentIntent(intent);

        // プログレスバーを表示
        builder.setProgress(max, progress, false);

        Notification notification = builder.getNotification();

        // 通知を発行
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }
}
