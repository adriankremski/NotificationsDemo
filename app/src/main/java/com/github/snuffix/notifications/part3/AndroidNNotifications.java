package com.github.snuffix.notifications.part3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.widget.Toolbar;

import com.github.snuffix.notifications.NotificationActivity;
import com.github.snuffix.notifications.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AndroidNNotifications extends NotificationActivity {

    public static final String REPLY_KEY = "reply_input_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_n_notifications);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @OnClick(R.id.reply_notification)
    public void replyNotification() {
        RemoteInput remoteInput = new RemoteInput.Builder(REPLY_KEY)
                .setLabel("Reply me")
                .build();

        Intent intentWithDefinedAction = new Intent(this, ReplyToEmailService.class);
        intentWithDefinedAction.putExtra("ID", notificationId);

        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(android.R.drawable.ic_dialog_email,
                        "Reply", PendingIntent.getService(this, 0, intentWithDefinedAction, PendingIntent.FLAG_UPDATE_CURRENT))
                        .addRemoteInput(remoteInput)
                        .build();

        Notification newMessageNotification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.ic_dialog_alert)
                        .setContentTitle("New email")
                        .setContentText("Content of new email")
                        .addAction(action).build();

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, newMessageNotification);
    }

    @OnClick(R.id.bundled_notifications)
    public void bundledNotifications() {
        String GROUP_KEY = "group_key";

        Notification summaryNotification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.ic_dialog_alert)
                        .setContentTitle("5 New emails")
                        .setGroup(GROUP_KEY)
                        .setGroupSummary(true)
                        .setContentText("You have 5 new emails").build();

        notificationManager.notify(notificationId, summaryNotification);

        for (int id = 1; id < 5; ++id) {
            RemoteInput remoteInput = new RemoteInput.Builder(REPLY_KEY)
                    .setLabel("Reply me")
                    .build();

            Intent intentWithDefinedAction = new Intent(this, ReplyToEmailService.class);
            intentWithDefinedAction.putExtra("ID", id);

            NotificationCompat.Action action =
                    new NotificationCompat.Action.Builder(android.R.drawable.ic_dialog_email,
                            "Reply", PendingIntent.getService(this, id, intentWithDefinedAction, PendingIntent.FLAG_UPDATE_CURRENT))
                            .addRemoteInput(remoteInput)
                            .build();

            Notification newMessageNotification =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(android.R.drawable.ic_dialog_alert)
                            .setContentTitle(id + ". email")
                            .setGroup(GROUP_KEY)
                            .setContentText("Content of " + id + ". email")
                            .addAction(action).build();

            notificationManager.notify(id, newMessageNotification);
        }
    }

}
