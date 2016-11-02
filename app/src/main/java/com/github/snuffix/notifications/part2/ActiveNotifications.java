package com.github.snuffix.notifications.part2;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.github.snuffix.notifications.NotificationActivity;
import com.github.snuffix.notifications.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActiveNotifications extends NotificationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_notifications);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.active_notifications)
    public void showActiveNotifications() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            StatusBarNotification[] activeNotifications = notificationManager.getActiveNotifications();

            String notifications = "";
            for (StatusBarNotification notification : activeNotifications) {
                notifications += String.valueOf(notification.getId()) + ". " + notification.getNotification().extras.getString(Notification.EXTRA_TEXT) + "\n";
                Log.i("Active notifications", notifications);
            }

            Toast.makeText(this, notifications, Toast.LENGTH_LONG).show();

        } else {
            Snackbar.make(findViewById(android.R.id.content), "This operation requires device with Android M or higher", Snackbar.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.start_notification_service)
    public void startNotificationServices() {
        Intent intent = new Intent(this, NotificationService.class);
        startService(intent);

        intent = new Intent(this, NotificationAccessibilityService.class);
        startService(intent);
    }

    @OnClick(R.id.stop_notification_service)
    public void stopNotificationServices() {
        Intent intent = new Intent(this, NotificationService.class);
        startService(intent);
    }

    @OnClick(R.id.access_settings)
    public void accessibilitySettings() {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);
    }

}
