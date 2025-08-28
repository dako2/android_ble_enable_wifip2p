package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class PressureSettingReq extends MixtureReq {
    private PressureSettingReq(boolean z) {
        super(Constants.CMD_PRESSURE_SETTING);
        this.subData = new byte[]{2, z ? (byte) 1 : (byte) 0};
    }

    private PressureSettingReq() {
        super(Constants.CMD_PRESSURE_SETTING);
        this.subData = new byte[]{1};
    }

    public static PressureSettingReq getWriteInstance(boolean z) {
        return new PressureSettingReq(z);
    }

    public static PressureSettingReq getReadInstance() {
        return new PressureSettingReq();
    }
}
