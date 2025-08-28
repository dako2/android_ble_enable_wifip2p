package com.oudmon.ble.base.bluetooth;

/* loaded from: classes2.dex */
public class SDKInit {
    public static int SDK_TYPE_MY = 2;
    public static int SDK_TYPE_QC = 1;
    private static SDKInit sdkInit;
    private int currSDK = 1;

    public static SDKInit getInstance() {
        if (sdkInit == null) {
            synchronized (SDKInit.class) {
                if (sdkInit == null) {
                    sdkInit = new SDKInit();
                }
            }
        }
        return sdkInit;
    }

    public int getSdkType() {
        return this.currSDK;
    }

    public void setSDKType(int i) {
        this.currSDK = i;
    }
}
