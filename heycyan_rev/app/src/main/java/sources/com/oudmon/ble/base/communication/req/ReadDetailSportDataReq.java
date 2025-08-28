package com.oudmon.ble.base.communication.req;

/* loaded from: classes2.dex */
public class ReadDetailSportDataReq extends BaseReqCmd {
    private byte[] data;

    public ReadDetailSportDataReq(int i, int i2, int i3) {
        super((byte) 67);
        if (i > 29) {
            throw new IllegalArgumentException("dayOffset 最大只到29");
        }
        if (i2 > i3 || i3 > 95) {
            throw new IllegalArgumentException("数据段索引值异常");
        }
        this.data = new byte[]{(byte) i, 15, (byte) i2, (byte) i3, 1};
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.data;
    }
}
