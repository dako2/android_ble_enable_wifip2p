package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class TouchControlReq extends MixtureReq {
    boolean touch;

    public static TouchControlReq getReadInstance(boolean z) {
        return new TouchControlReq(z);
    }

    public TouchControlReq() {
        super(Constants.CMD_DEVICE_TOUCH);
        this.touch = false;
        this.subData = new byte[]{1};
    }

    public TouchControlReq(boolean z) {
        super(Constants.CMD_DEVICE_TOUCH);
        this.touch = false;
        if (z) {
            this.subData = new byte[]{1, 0};
        } else {
            this.subData = new byte[]{1, 1};
        }
    }

    private TouchControlReq(int i, boolean z, int i2) {
        super(Constants.CMD_DEVICE_TOUCH);
        this.touch = false;
        if (z) {
            this.subData = new byte[]{2, 0, (byte) i};
        } else {
            this.subData = new byte[]{2, 1, (byte) i, (byte) i2};
        }
    }

    public static TouchControlReq getWriteInstance(int i, boolean z, int i2) {
        return new TouchControlReq(i, z, i2);
    }
}
