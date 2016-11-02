package com.github.snuffix.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.snuffix.notifications.part1.BasicNotifications;
import com.github.snuffix.notifications.part1.StylingNotifications;
import com.github.snuffix.notifications.part2.ActiveNotifications;
import com.github.snuffix.notifications.part2.CustomViewNotifications;
import com.github.snuffix.notifications.part2.HeadsUpNotifications;
import com.github.snuffix.notifications.part2.LockScreenNotifications;
import com.github.snuffix.notifications.part3.AndroidNNotifications;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.basic_notifications)
    public void basicNotifications() {
        startActivity(new Intent(this, BasicNotifications.class));
    }

    @OnClick(R.id.styling_notifications)
    public void stylingNotifications() {
        startActivity(new Intent(this, StylingNotifications.class));
    }

    @OnClick(R.id.headsup_notifications)
    public void headsUpNotifications() {
        startActivity(new Intent(this, HeadsUpNotifications.class));
    }

    @OnClick(R.id.lockscreen_notifications)
    public void lockScreenNotifications() {
        startActivity(new Intent(this, LockScreenNotifications.class));
    }

    @OnClick(R.id.custom_view_notifications)
    public void customViewNotifications() {
        startActivity(new Intent(this, CustomViewNotifications.class));
    }

    @OnClick(R.id.active_notifications)
    public void activeNotifications() {
        startActivity(new Intent(this, ActiveNotifications.class));
    }

    @OnClick(R.id.android_n_notifications)
    public void androidNNotifications() {
        startActivity(new Intent(this, AndroidNNotifications.class));
    }
}
