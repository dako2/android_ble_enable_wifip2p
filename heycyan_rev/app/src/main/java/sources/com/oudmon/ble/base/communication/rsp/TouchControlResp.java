package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes2.dex */
public class TouchControlResp extends BaseRspCmd {
    private int appType;
    private int strength;
    private boolean touch;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.touch = bArr[1] == 0;
        this.appType = bArr[2];
        this.strength = bArr[3];
        return false;
    }

    public int getAppType() {
        return this.appType;
    }

    public int getStrength() {
        return this.strength;
    }

    public boolean isTouch() {
        return this.touch;
    }
}
