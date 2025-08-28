package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes2.dex */
public class DialIndexRsp extends BaseRspCmd {
    private int index = 0;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.index = bArr[1];
        return false;
    }

    public int getIndex() {
        return this.index;
    }
}
