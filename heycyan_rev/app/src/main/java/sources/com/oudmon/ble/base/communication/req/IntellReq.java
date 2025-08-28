package com.oudmon.ble.base.communication.req;

/* loaded from: classes2.dex */
public class IntellReq extends MixtureReq {
    private byte delaySecond;
    private boolean isEnable;

    private IntellReq() {
        super((byte) 9);
        this.subData = new byte[]{1};
    }

    private IntellReq(boolean z, byte b) {
        super((byte) 9);
        this.subData = new byte[]{2, (byte) (z ? 1 : 2), b};
    }

    public static IntellReq getReadInstance() {
        return new IntellReq();
    }

    public static IntellReq getWriteInstance(boolean z, byte b) {
        return new IntellReq(z, b);
    }
}
