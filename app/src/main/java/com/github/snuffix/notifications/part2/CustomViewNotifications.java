package com.github.snuffix.notifications.part2;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RemoteViews;

import com.github.snuffix.notifications.NotificationActivity;
import com.github.snuffix.notifications.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomViewNotifications extends NotificationActivity {

    @Bind(R.id.custom_view_notifcation_input)
    EditText textInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        textInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sendNotification(s.toString());
            }
        });
    }

    @OnClick(R.id.custom_view_notifcation)
    public void showCustomViewNotification() {
        sendNotification(null);
    }

    private void sendNotification(String title) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        RemoteViews content = new RemoteViews(getPackageName(), R.layout.custom_notification);

        if (title != null) {
            content.setTextViewText(R.id.title, title);
        }

        builder
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setContent(content)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        Intent intentWithDefinedAction = new Intent();

        builder.setContentIntent(PendingIntent.getActivity(this, 0,
                intentWithDefinedAction, PendingIntent.FLAG_UPDATE_CURRENT));

        notificationManager.notify(notificationId, builder.build());
    }
}
