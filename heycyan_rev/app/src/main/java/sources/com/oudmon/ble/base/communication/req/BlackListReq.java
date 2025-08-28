package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class BlackListReq extends BaseReqCmd {
    public BlackListReq() {
        super(Constants.CMD_BlackList_LOCATION);
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return new byte[]{1};
    }
}
