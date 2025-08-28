package com.glasssutdio.wear.ble.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import com.glasssutdio.wear.ble.glass.thread.ThreadManager;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NetWorkStateReceiver.kt */
@Metadata(m606d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/ble/receiver/NetWorkStateReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class NetWorkStateReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork != null) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
            if (networkCapabilities != null) {
                if (networkCapabilities.hasTransport(1) || !networkCapabilities.hasTransport(0) || BleOperateManager.getInstance().isConnected()) {
                    return;
                }
                ThreadManager.getInstance().wakeUp();
                return;
            }
            if (BleOperateManager.getInstance().isConnected()) {
                return;
            }
            ThreadManager.getInstance().wakeUp();
            return;
        }
        if (BleOperateManager.getInstance().isConnected()) {
            return;
        }
        ThreadManager.getInstance().wakeUp();
    }
}
