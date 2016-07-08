package com.github.snuffix.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BasicNotifications extends AppCompatActivity {

    private NotificationManager notificationManager;
    private int notificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
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
        raiseNotification(Notification.DEFAULT_LIGHTS);
    }

    @OnClick(R.id.vibrate_notification)
    public void showVibrateNotification() {
        raiseNotification(Notification.DEFAULT_VIBRATE);
    }

    private void raiseNotification(int defaults) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        notificationBuilder.setContentTitle("Notice me!")
                .setContentText("I am your first notification :)")
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setDefaults(defaults)
                .setTicker("Ticker");

        Intent intentWithDefinedAction = new Intent();

        notificationBuilder.setContentIntent(PendingIntent.getActivity(this, 0,
                intentWithDefinedAction, PendingIntent.FLAG_UPDATE_CURRENT));

        notificationManager.notify(notificationId++, notificationBuilder.build());
    }
}
