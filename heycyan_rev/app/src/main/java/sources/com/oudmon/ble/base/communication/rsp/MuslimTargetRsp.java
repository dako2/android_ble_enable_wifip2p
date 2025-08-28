package com.oudmon.ble.base.communication.rsp;

import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.utils.ByteUtil;

/* loaded from: classes2.dex */
public class MuslimTargetRsp extends BaseRspCmd {
    private int muslimTarget = 0;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        XLog.m137i(ByteUtil.byteArrayToString(bArr));
        try {
            this.muslimTarget = ByteUtil.bytesToInt(new byte[]{bArr[2], bArr[3], bArr[4], bArr[5]});
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getMuslimTarget() {
        return this.muslimTarget;
    }
}
