package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class SetANCSReq extends BaseReqCmd {
    public SetANCSReq() {
        super(Constants.CMD_SET_ANCS_ON_OFF);
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return new byte[]{-1, -97};
    }
}
