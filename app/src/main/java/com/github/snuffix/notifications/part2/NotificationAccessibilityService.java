package com.github.snuffix.notifications.part2;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Notification;
import android.os.Parcelable;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class NotificationAccessibilityService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.i(getClass().toString(), "onAccessibilityEvent");

        if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
            Parcelable data = event.getParcelableData();
            if (data instanceof Notification) {
                Log.i(getClass().toString(), "receivedNotification");
                Notification notification = (Notification) data;
                String notificationDescription = notification.getGroup() + " " + notification.getGroup();
                Log.i(getClass().toString(), notificationDescription + " onNotificationPosted");
            }
        }
    }

    @Override
    public void onInterrupt() {
    }

    @Override
    public void onServiceConnected() {
        // Set the type of events that this service wants to listen to.  Others
        // won't be passed to this service.
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
        info.feedbackType = AccessibilityServiceInfo.DEFAULT;
        info.notificationTimeout = 100;
        this.setServiceInfo(info);
    }
}
