package com.oudmon.ble.base.communication.req;

/* loaded from: classes2.dex */
public class SimpleKeyPowerOffReq extends MixtureReq {
    public SimpleKeyPowerOffReq() {
        super((byte) 8);
        this.subData = new byte[]{1};
    }
}
