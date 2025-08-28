package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class HRVReq extends MixtureReq {
    private byte index;

    public HRVReq(byte b) {
        super(Constants.CMD_HRV);
        this.subData = new byte[]{b};
    }
}
