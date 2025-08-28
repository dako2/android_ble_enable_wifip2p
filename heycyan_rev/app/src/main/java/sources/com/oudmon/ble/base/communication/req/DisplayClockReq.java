package com.oudmon.ble.base.communication.req;

/* loaded from: classes2.dex */
public class DisplayClockReq extends MixtureReq {
    private DisplayClockReq() {
        super((byte) 18);
        this.subData = new byte[]{1};
    }

    private DisplayClockReq(boolean z) {
        super((byte) 18);
        this.subData = new byte[]{2, (byte) (z ? 1 : 2)};
    }

    public static DisplayClockReq getReadInstance() {
        return new DisplayClockReq();
    }

    public static DisplayClockReq getWriteInstance(boolean z) {
        return new DisplayClockReq(z);
    }
}
