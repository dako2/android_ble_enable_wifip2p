package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class DialIndexReq extends MixtureReq {
    public DialIndexReq() {
        super(Constants.CMD_DEVICE_DIAL_INDEX);
        this.subData = new byte[]{0};
    }

    private DialIndexReq(int i) {
        super(Constants.CMD_DEVICE_DIAL_INDEX);
        this.subData = new byte[]{1, (byte) i};
    }

    public static DialIndexReq getReadInstance() {
        return new DialIndexReq();
    }

    public static DialIndexReq getWriteInstance(int i) {
        return new DialIndexReq(i);
    }
}
