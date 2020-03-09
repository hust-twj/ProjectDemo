package com.hust_twj.zademo.remote_view;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.hust_twj.zademo.R;

import java.util.Random;

/**
 * Created by Wenjing.Tang
 * on 2019/2/16
 * RemoteView 通知栏
 */
public class RemoteViewActivity extends Activity implements View.OnClickListener {

    private TextView mTvNotification;
    private TextView mTvRemoteView;

    private NotificationManager notificationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_view);

        mTvNotification = findViewById(R.id.tv_notification);
        mTvRemoteView = findViewById(R.id.tv_remote_view);

        mTvNotification.setOnClickListener(this);
        mTvRemoteView.setOnClickListener(this);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    }

    @Override
    public void onClick(View v) {
        if (v == mTvNotification) {
            /*Notification notification = new Notification();
            notification.icon = R.mipmap.ic_launcher;
            notification.tickerText = "通知来啦";
            notification.when = 10L;
            notification.defaults = Notification.DEFAULT_SOUND;
            notification.set*/

            /*Intent intent = new Intent(this, RemoteViewActivity1.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            Notification notification = new Notification.Builder(this)
                    .setAutoCancel(true)
                    .setContentTitle("title")
                    .setContentText("describe")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setWhen(System.currentTimeMillis())
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, notification);*/

            showNotification(this, getNotificationId(), "title", "content", null);
        } else if (v == mTvRemoteView) {
            Notification.Builder builder2 = new Notification.Builder(this);
            Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.jianshu.com/p/82e249713f1b"));
            PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, intent2, 0);
            builder2.setContentIntent(pendingIntent2);
            builder2.setSmallIcon(R.mipmap.ic_launcher);
            builder2.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
            builder2.setAutoCancel(true);
            builder2.setContentTitle("折叠通知");

            RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_remote_view);
            Notification notification = builder2.build();
            notification.bigContentView = remoteViews;
            notificationManager.notify(1, notification);

        }
    }

    private static final String PUSH_CHANNEL_ID = "PUSH_NOTIFY_ID";
    private static final String PUSH_CHANNEL_NAME = "PUSH_NOTIFY_NAME";
    private Bitmap launcherBitmap;
    private NotificationManager mNotificationManager;

    private void showNotification(final Context context, final int notificationId,
                                  final String title, final String content, Bitmap bitmap) {
        NotificationManager notificationManager = getNotificationManager(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(PUSH_CHANNEL_ID, PUSH_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        if (notificationManager == null) {
            return;
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        if (bitmap != null) {
            mBuilder.setLargeIcon(bitmap);
        } else {
            mBuilder.setLargeIcon(getLauncherBitmap(context));
        }

        Intent intent = new Intent(this, RemoteViewActivity1.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round)//设置通知小ICON
                .setContentIntent(pendingIntent) //设置通知栏点击意图
                .setTicker("hahaha") //通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
                .setContentTitle(title)
                .setContentText(content)
                .setChannelId(PUSH_CHANNEL_ID)
                .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setColor(context.getResources().getColor(R.color.colorPrimary))
                .setAutoCancel(true);

        // mBuilder.setDefaults(Notification.DEFAULT_ALL);
        notificationManager.notify(notificationId, mBuilder.build());
    }

    private NotificationManager getNotificationManager(Context context) {
        if (mNotificationManager == null) {
            mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mNotificationManager;
    }

    private Bitmap getLauncherBitmap(Context context) {
        if (launcherBitmap == null) {
            launcherBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        }
        return launcherBitmap;
    }

    private int getNotificationId() {
        return new Random().nextInt();
    }

}
