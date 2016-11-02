package com.github.snuffix.notifications.part2;

import android.app.Notification;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;


public class NotificationService extends NotificationListenerService {

    @Override
    public void onNotificationPosted(StatusBarNotification notification) {
        String notificationDescription = String.valueOf(notification.getId()) + ". " + notification.getNotification().extras.getString(Notification.EXTRA_TEXT);
        Log.i(getClass().toString(), notificationDescription + " onNotificationPosted");
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification notification) {
        String notificationDescription = String.valueOf(notification.getId()) + ". " + notification.getNotification().extras.getString(Notification.EXTRA_TEXT);
        Log.i(getClass().toString(), notificationDescription + " onNotificationRemoved");
    }
}
