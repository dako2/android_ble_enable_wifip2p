package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class SetMessagePushReq extends BaseReqCmd {
    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return null;
    }

    public SetMessagePushReq() {
        super(Constants.CMD_GET_ANCS_ON_OFF);
    }
}
