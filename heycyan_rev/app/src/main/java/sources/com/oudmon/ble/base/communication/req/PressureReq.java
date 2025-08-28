package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class PressureReq extends MixtureReq {
    private byte index;

    public PressureReq(byte b) {
        super(Constants.CMD_PRESSURE);
        this.subData = new byte[]{b};
    }
}
