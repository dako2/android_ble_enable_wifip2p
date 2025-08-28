package com.oudmon.ble.base.communication.req;

/* loaded from: classes2.dex */
public class HeartRateSettingReq extends MixtureReq {
    private HeartRateSettingReq() {
        super((byte) 22);
        this.subData = new byte[]{1};
    }

    private HeartRateSettingReq(boolean z, int i) {
        super((byte) 22);
        this.subData = new byte[]{2, (byte) (z ? 1 : 2), (byte) i};
    }

    public static HeartRateSettingReq getReadInstance() {
        return new HeartRateSettingReq();
    }

    public static HeartRateSettingReq getWriteInstance(boolean z, int i) {
        return new HeartRateSettingReq(z, i);
    }
}
