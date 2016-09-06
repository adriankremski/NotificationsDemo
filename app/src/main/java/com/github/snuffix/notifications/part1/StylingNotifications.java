package com.github.snuffix.notifications.part1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;

import com.github.snuffix.notifications.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StylingNotifications extends AppCompatActivity {

    private NotificationManager notificationManager;
    private int bigNotificationId = 0;
    private int inboxNotificationId = 1;
    private int bigPictureNotificationId = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_styling_notifications);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @OnClick(R.id.bigtext_notification)
    public void showBigTextNotification() {
        Intent intentWithDefinedAction = new Intent();

        PendingIntent pi = PendingIntent.getActivity(this, 0,
                intentWithDefinedAction, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = basicNotification()
                .setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.big_text)).setSummaryText("Summary"))
                .addAction(android.R.drawable.ic_dialog_email, getString(R.string.action), pi).build();

        notificationManager.notify(bigNotificationId, notification);
    }

    private NotificationCompat.Builder basicNotification() {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        notificationBuilder.setContentTitle("Notice me!")
                .setContentText("I am your first notification :)")
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setTicker("Ticker");

        return notificationBuilder;
    }


    @OnClick(R.id.inbox_notification)
    public void showInboxNotification() {
        Intent intentWithDefinedAction = new Intent();

        PendingIntent pi = PendingIntent.getActivity(this, 0,
                intentWithDefinedAction, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = basicNotification()
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine(getString(R.string.line_1))
                        .addLine(getString(R.string.line_2))
                        .addLine(getString(R.string.line_3))
                        .addLine(getString(R.string.line_4))
                        .setSummaryText("5 more lines"))
                .addAction(android.R.drawable.ic_dialog_email, getString(R.string.action), pi).build();

        notificationManager.notify(inboxNotificationId, notification);
    }

    @OnClick(R.id.bigpicture_notification)
    public void showBigPictureNotification() {
        Bitmap natureBitmap = decodeSampledBitmapFromResource(getResources(), R.drawable.nature, 100, 100);
        Intent intentWithDefinedAction = new Intent();

        PendingIntent pi = PendingIntent.getActivity(this, 0,
                intentWithDefinedAction, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = basicNotification()
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .setBigContentTitle(getString(R.string.big_picture_title))
                        .bigPicture(natureBitmap)
                )
                .addAction(android.R.drawable.ic_dialog_email, getString(R.string.action), pi).build();

        notificationManager.notify(bigPictureNotificationId, notification);
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
