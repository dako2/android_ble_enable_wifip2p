package com.oudmon.ble.base.communication;

/* loaded from: classes2.dex */
public class JPackageManager {
    private static JPackageManager mInstance;
    private int mLength = 244;

    private JPackageManager() {
    }

    public static JPackageManager getInstance() {
        if (mInstance == null) {
            synchronized (JPackageManager.class) {
                if (mInstance == null) {
                    mInstance = new JPackageManager();
                }
            }
        }
        return mInstance;
    }

    public void setLength(int i) {
        this.mLength = i;
    }

    public int getLength() {
        return Math.max(this.mLength, 244);
    }
}
