package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class BatterySavingReq extends MixtureReq {
    public BatterySavingReq() {
        super(Constants.CMD_DEVICE_BATTERY_SAVING);
        this.subData = new byte[]{0};
    }

    private BatterySavingReq(boolean z) {
        super(Constants.CMD_DEVICE_BATTERY_SAVING);
        this.subData = new byte[]{1, z ? (byte) 1 : (byte) 0};
    }

    public static BatterySavingReq getReadInstance() {
        return new BatterySavingReq();
    }

    public static BatterySavingReq getWriteInstance(boolean z) {
        return new BatterySavingReq(z);
    }
}
