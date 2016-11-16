package com.github.snuffix.notifications.part3;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.snuffix.notifications.NotificationActivity;
import com.github.snuffix.notifications.R;

import java.util.Date;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessagingStyle extends NotificationActivity {

    public static final String REPLY_KEY = "reply_input_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging_style);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @TargetApi(Build.VERSION_CODES.N)
    @OnClick(R.id.messaging_style)
    public void replyNotification() {
        Notification notification = new Notification.Builder(this)
                .setContentTitle("2 new messages wtih Adrian")
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setColor(getColor(R.color.colorAccent))
                .setStyle(new Notification.MessagingStyle("Me")
                        .setConversationTitle("Texting Dog")
                        .addMessage("Hello?", new Date().getTime(), "Dog")
                        .addMessage("Hello", new Date().getTime(), null)
                        .addMessage("Yes, this is dog", new Date().getTime(), "Dog"))
                .build();


        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notification);
    }
}
