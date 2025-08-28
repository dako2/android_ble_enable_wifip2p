package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.communication.utils.ByteUtil;

/* loaded from: classes2.dex */
public class HeartRateSettingRsp extends MixtureRsp {
    private int heartInterval;
    private boolean isEnable;
    private int startInterval;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] bArr) {
        this.isEnable = bArr[1] == 1;
        this.heartInterval = ByteUtil.byteToInt(bArr[2]);
        int iByteToInt = ByteUtil.byteToInt(bArr[3]);
        if (iByteToInt == 0) {
            this.startInterval = 5;
        } else {
            this.startInterval = iByteToInt;
        }
    }

    public boolean isEnable() {
        return this.isEnable;
    }

    public int getHeartInterval() {
        return this.heartInterval;
    }

    public int getStartInterval() {
        return this.startInterval;
    }

    public void setStartInterval(int i) {
        this.startInterval = i;
    }
}
