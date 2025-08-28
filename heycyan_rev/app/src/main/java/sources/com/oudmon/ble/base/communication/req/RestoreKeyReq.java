package com.oudmon.ble.base.communication.req;

/* loaded from: classes2.dex */
public class RestoreKeyReq extends SimpleKeyReq {
    public RestoreKeyReq(byte b) {
        super(b);
    }

    @Override // com.oudmon.ble.base.communication.req.SimpleKeyReq, com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return new byte[]{102, 102};
    }
}
