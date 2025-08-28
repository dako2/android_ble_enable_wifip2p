package com.oudmon.ble.base.communication.bigData.resp;

/* loaded from: classes2.dex */
public class AiChatResponse extends BaseResponse {
    private byte[] subData;

    @Override // com.oudmon.ble.base.communication.bigData.resp.BaseResponse
    public boolean acceptData(byte[] bArr) {
        this.subData = bArr;
        return false;
    }

    public byte[] getSubData() {
        return this.subData;
    }
}
