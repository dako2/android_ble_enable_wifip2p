package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class AppRevisionReq extends MixtureReq {
    private AppRevisionReq(int i) {
        super(Constants.CMD_DEVICE_REVISION);
        this.subData = new byte[]{(byte) i};
    }

    public static AppRevisionReq getWriteInstance(int i) {
        return new AppRevisionReq(i);
    }
}
