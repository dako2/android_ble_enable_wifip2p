package com.oudmon.ble.base.communication.rsp;

import com.oudmon.qc_utils.bytes.DataTransferUtils;

/* loaded from: classes2.dex */
public class PpgDataRspCmd extends BaseRspCmd {
    public int mPpgValue;
    public int mRate;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.mRate = bArr[0];
        this.mPpgValue = DataTransferUtils.bytesToInt(bArr, 1);
        return false;
    }
}
