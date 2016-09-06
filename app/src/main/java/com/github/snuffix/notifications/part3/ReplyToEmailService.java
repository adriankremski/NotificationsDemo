package com.github.snuffix.notifications.part3;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;

public class ReplyToEmailService extends IntentService {

    public ReplyToEmailService() {
        super(ReplyToEmailService.class.toString());
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(workIntent);
        if (remoteInput != null) {
            String userInput = remoteInput.getCharSequence(AndroidNNotifications.REPLY_KEY).toString();

            try {
                Thread.sleep(3000); // Only for testing
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Notification messageDeliveredNotification = new NotificationCompat.Builder(this)
                    .setSmallIcon(android.R.drawable.ic_dialog_alert)
                    .setContentTitle("Message delivered")
                    .setContentText(userInput)
                    .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, AndroidNNotifications.class), PendingIntent.FLAG_UPDATE_CURRENT))
                    .build();

            ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).notify(workIntent.getIntExtra("ID", -1), messageDeliveredNotification);
        }
    }
}
