package com.oudmon.ble.base.communication.req;

/* loaded from: classes2.dex */
public class RealTimeHeartRate extends BaseReqCmd {
    private byte[] mData;
    private int type;

    public RealTimeHeartRate(int i) {
        super((byte) 30);
        this.mData = new byte[]{(byte) i};
        this.type = i;
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.mData;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}
