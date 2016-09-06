package com.github.snuffix.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;

public abstract class NotificationActivity extends AppCompatActivity {

    protected NotificationManager notificationManager;
    protected int notificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    protected void showNotification(NotificationCompat.Builder builder) {
        Intent intentWithDefinedAction = new Intent();

        builder.setContentIntent(PendingIntent.getActivity(this, 0,
                intentWithDefinedAction, PendingIntent.FLAG_UPDATE_CURRENT));

        notificationManager.notify(notificationId++, builder.build());
    }
}
