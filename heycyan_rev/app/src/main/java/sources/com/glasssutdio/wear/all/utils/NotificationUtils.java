package com.glasssutdio.wear.all.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.GlassApplication;
import com.glasssutdio.wear.MainActivity;
import com.oudmon.ble.base.bluetooth.BleOperateManager;

/* loaded from: classes.dex */
public class NotificationUtils extends ContextWrapper {
    private static final int BLUETOOTH_NOTIFICATION_ID = 12001;

    /* renamed from: id */
    public static final String f169id = "com.ai.glasses.bluetooth_channel";
    public static final String name = "com.ai.glasses.channel_name_2";
    private NotificationManager mManager;
    public Notification notification;

    public NotificationUtils(Context context) {
        super(context);
        initManager();
    }

    private void initManager() {
        if (this.mManager == null) {
            this.mManager = (NotificationManager) getSystemService("notification");
        }
    }

    public Notification initBleConnectNotification() {
        try {
            XLog.m137i("------initBleConnectNotification");
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            if (Build.VERSION.SDK_INT >= 26) {
                notificationManager.createNotificationChannel(new NotificationChannel(f169id, name, 2));
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(GlassApplication.INSTANCE.getCONTEXT(), f169id);
            builder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MainActivity.class), 201326592));
            builder.setSmallIcon(C0775R.mipmap.notification_icon).setSound(null).setVibrate(null).setContentTitle(getResources().getString(C0775R.string.app_name));
            if (BleOperateManager.getInstance().isConnected()) {
                builder.setContentText(getResources().getString(C0775R.string.h_glass_110));
            } else {
                builder.setContentText(getResources().getString(C0775R.string.h_glass_111));
            }
            Notification notificationBuild = builder.build();
            this.notification = notificationBuild;
            notificationManager.notify(BLUETOOTH_NOTIFICATION_ID, notificationBuild);
            return this.notification;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void cancelNotification() {
        try {
            this.mManager.cancel(BLUETOOTH_NOTIFICATION_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNotificationChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(f169id, name, 3);
        notificationChannel.enableLights(false);
        notificationChannel.enableVibration(false);
        notificationChannel.setSound(null, null);
        this.mManager.createNotificationChannel(notificationChannel);
    }
}
