package com.glasssutdio.wear.ble.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.glasssutdio.wear.GlassApplication;
import com.oudmon.ble.base.bluetooth.BleBaseControl;

/* loaded from: classes.dex */
public class RebootReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        BleBaseControl.getInstance(GlassApplication.INSTANCE.getCONTEXT()).setBluetoothTurnOff(true);
    }
}
