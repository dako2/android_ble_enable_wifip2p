package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes2.dex */
public class StopHeartRateRsp extends BaseRspCmd {
    private byte dbp;
    private byte errCode;
    private byte sbp;
    private byte type;
    private byte value;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.type = bArr[0];
        this.errCode = bArr[1];
        this.value = bArr[2];
        if (bArr.length >= 5) {
            this.sbp = bArr[3];
            this.dbp = bArr[4];
        }
        return false;
    }

    public byte getType() {
        return this.type;
    }

    public byte getErrCode() {
        return this.errCode;
    }

    public byte getValue() {
        return this.value;
    }

    public byte getSbp() {
        return this.sbp;
    }

    public byte getDbp() {
        return this.dbp;
    }
}
