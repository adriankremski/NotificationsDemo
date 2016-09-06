package com.github.snuffix.notifications.part1;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;

import com.github.snuffix.notifications.NotificationActivity;
import com.github.snuffix.notifications.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BasicNotifications extends NotificationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_notifications);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.default_notification)
    public void showDefaultNotification() {
        raiseNotification(Notification.DEFAULT_ALL);
    }

    @OnClick(R.id.sound_notification)
    public void showSoundNotification() {
        raiseNotification(Notification.DEFAULT_SOUND);
    }

    @OnClick(R.id.flash_notification)
    public void showFlashNotification() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        raiseNotification(Notification.DEFAULT_LIGHTS);
                    }
                });
            }
        }).start();
    }

    @OnClick(R.id.vibrate_notification)
    public void showVibrateNotification() {
        raiseNotification(Notification.DEFAULT_VIBRATE);
    }

    @OnClick(R.id.open_url_notification)
    public void showOpenUrlNotification() {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        String url = "https://www.google.pl";

        Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        PendingIntent pendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        openUrlIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );


        notificationBuilder.setContentTitle("Notice me!")
                .setContentText("Click here to open " + url)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        notificationManager.notify(notificationId++, notificationBuilder.build());
    }

    private void raiseNotification(int defaults) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        notificationBuilder.setContentTitle("Notice me!")
                .setContentText("I am your first notification :)")
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setDefaults(defaults)
                .setTicker("Ticker");

        showNotification(notificationBuilder);
    }
}
