package com.oudmon.ble.base.communication.bigData.resp;

import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class GlassesAiVoicePlayStatusRsp extends BaseResponse {
    int status;

    @Override // com.oudmon.ble.base.communication.bigData.resp.BaseResponse
    public boolean acceptData(byte[] bArr) {
        this.status = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 7, 8));
        return false;
    }

    public int getStatus() {
        return this.status;
    }
}
