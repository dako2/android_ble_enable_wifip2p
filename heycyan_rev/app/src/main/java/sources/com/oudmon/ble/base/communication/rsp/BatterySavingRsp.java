package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes2.dex */
public class BatterySavingRsp extends BaseRspCmd {
    private boolean open;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.open = bArr[1] == 1;
        return false;
    }

    public boolean isOpen() {
        return this.open;
    }
}
