package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class HrvSettingReq extends MixtureReq {
    private HrvSettingReq() {
        super(Constants.CMD_HRV_ENABLE);
        this.subData = new byte[]{1};
    }

    public HrvSettingReq(boolean z) {
        super(Constants.CMD_HRV_ENABLE);
        this.subData = new byte[]{2, z ? (byte) 1 : (byte) 0};
    }

    public static HrvSettingReq getReadInstance() {
        return new HrvSettingReq();
    }
}
