package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes2.dex */
public class StartHeartRateRsp extends BaseRspCmd {
    private int dbp;
    private byte errCode;
    private int sbp;
    private byte type;
    private int value;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.type = bArr[0];
        this.errCode = bArr[1];
        this.value = bArr[2] & 255;
        if (bArr.length >= 5) {
            byte b = bArr[3];
            this.sbp = b;
            if (b < 0) {
                this.sbp = b & 255;
            }
            byte b2 = bArr[4];
            this.dbp = b2;
            if (b2 < 0) {
                this.dbp = b2 & 255;
            }
        }
        return false;
    }

    public byte getType() {
        return this.type;
    }

    public byte getErrCode() {
        return this.errCode;
    }

    public int getValue() {
        return this.value;
    }

    public int getSbp() {
        return Math.abs(this.sbp);
    }

    public int getDbp() {
        return Math.abs(this.dbp);
    }
}
