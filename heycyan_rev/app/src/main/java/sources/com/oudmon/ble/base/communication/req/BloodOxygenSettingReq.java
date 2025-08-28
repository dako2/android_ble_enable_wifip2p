package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class BloodOxygenSettingReq extends MixtureReq {
    private BloodOxygenSettingReq() {
        super(Constants.CMD_AUTO_BLOOD_OXYGEN);
        this.subData = new byte[]{1};
    }

    private BloodOxygenSettingReq(boolean z) {
        super(Constants.CMD_AUTO_BLOOD_OXYGEN);
        this.subData = new byte[]{2, z ? (byte) 1 : (byte) 0};
    }

    public static BloodOxygenSettingReq getReadInstance() {
        return new BloodOxygenSettingReq();
    }

    public static BloodOxygenSettingReq getWriteInstance(boolean z) {
        return new BloodOxygenSettingReq(z);
    }
}
