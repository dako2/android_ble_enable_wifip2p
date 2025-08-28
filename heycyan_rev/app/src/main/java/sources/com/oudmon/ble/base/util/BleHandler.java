package com.oudmon.ble.base.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/* loaded from: classes2.dex */
public class BleHandler extends Handler {
    private static final String TAG = "BleHandler";
    private static BleHandler sHandler;

    /* renamed from: of */
    public static BleHandler m591of() {
        BleHandler bleHandler;
        synchronized (BleHandler.class) {
            if (sHandler == null) {
                HandlerThread handlerThread = new HandlerThread("handler thread");
                handlerThread.start();
                sHandler = new BleHandler(handlerThread.getLooper());
            }
            bleHandler = sHandler;
        }
        return bleHandler;
    }

    private BleHandler(Looper looper) {
        super(Looper.myLooper());
    }
}
