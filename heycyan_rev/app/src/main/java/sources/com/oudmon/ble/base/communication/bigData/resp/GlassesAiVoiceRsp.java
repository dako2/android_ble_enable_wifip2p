package com.oudmon.ble.base.communication.bigData.resp;

import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class GlassesAiVoiceRsp extends BaseResponse {
    boolean open;

    @Override // com.oudmon.ble.base.communication.bigData.resp.BaseResponse
    public boolean acceptData(byte[] bArr) {
        this.open = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 7, 8)) == 1;
        return false;
    }

    public boolean isOpen() {
        return this.open;
    }
}
