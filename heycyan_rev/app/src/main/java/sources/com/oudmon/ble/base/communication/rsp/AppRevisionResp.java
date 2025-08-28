package com.oudmon.ble.base.communication.rsp;

import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.utils.ByteUtil;

/* loaded from: classes2.dex */
public class AppRevisionResp extends BaseRspCmd {
    private int dataType;
    private int result;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        XLog.m137i(ByteUtil.byteArrayToString(bArr));
        this.dataType = bArr[0];
        this.result = bArr[9];
        return false;
    }

    public int getDataType() {
        return this.dataType;
    }

    public int getResult() {
        return this.result;
    }
}
