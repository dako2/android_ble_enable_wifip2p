package com.oudmon.ble.base.communication.rsp;

import android.util.Log;
import com.oudmon.qc_utils.bytes.DataTransferUtils;

/* loaded from: classes2.dex */
public class MenstruationDataRsp extends BaseRspCmd {
    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        Log.i("Jxr35", "acceptData.. data: " + DataTransferUtils.getHexString(bArr));
        return true;
    }
}
