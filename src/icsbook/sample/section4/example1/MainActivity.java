package icsbook.sample.section4.example1;

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
        
        findViewById(R.id.notificationBtn)
            .setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v) {
                showNotification();
            }
        });
    }

    private void showNotification() {
        
        // 通知が発行されたときにステータスバーに表示されるアイコンと文字
        int icon = R.drawable.stat_notify_setting;
        CharSequence text = "Notification!";
        
        Notification notification = 
            new Notification(icon, text, System.currentTimeMillis());

        // 通知領域を引き出したときに表示される文字
        CharSequence title = "My notification";
        CharSequence message = "Hello World!";

        // 通知をクリックしたときに処理するインテント
        Intent notificationIntent = new Intent(this, MainActivity.class);        
        // 上記のインテントをアクティビティとして起動するためのPendingIntent
        PendingIntent intent = PendingIntent
            .getActivity(this, 0, notificationIntent, 0);
       
        notification.setLatestEventInfo(getApplicationContext(), 
                title, message, intent);
        
        // 通知を発行
        NotificationManager manager = (NotificationManager) 
            getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }
}
