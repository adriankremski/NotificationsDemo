package com.github.snuffix.notifications.part2;

import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;

import com.github.snuffix.notifications.NotificationActivity;
import com.github.snuffix.notifications.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LockScreenNotifications extends NotificationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lockscreen_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.lockscreen_public_notification)
    public void showLockScreenNotification() {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        notificationBuilder.setContentTitle("Alert!")
                .setContentText("This is heads-up notification")
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setTicker("Ticker");

        showNotification(notificationBuilder);
    }

    @OnClick(R.id.lockscreen_private_notification)
    public void showLockScreenPrivateNotification() {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        notificationBuilder.setContentTitle("Alert!")
                .setContentText("This is heads-up notification")
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setTicker("Ticker");

        showNotification(notificationBuilder);
    }

    @OnClick(R.id.lockscreen_secret_notification)
    public void showLockScreenSecretNotification() {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        notificationBuilder.setContentTitle("Alert!")
                .setContentText("This is heads-up notification")
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_SECRET)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setTicker("Ticker");

        showNotification(notificationBuilder);
    }

    @OnClick(R.id.lockscreen_public_private_notification)
    public void showLockScreenPublicPrivateNotification() {
        NotificationCompat.Builder publicNotificationBuilder = new NotificationCompat.Builder(this);

        publicNotificationBuilder.setContentTitle("Alert!")
                .setContentText("This is public part of notification")
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        notificationBuilder.setContentTitle("Alert!")
                .setContentText("This is private part of notification")
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPublicVersion(publicNotificationBuilder.build());

        showNotification(notificationBuilder);
    }
}
