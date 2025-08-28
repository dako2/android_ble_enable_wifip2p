package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class FindDeviceReq extends BaseReqCmd {
    public FindDeviceReq() {
        super(Constants.CMD_ANTI_LOST_RATE);
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return new byte[]{85, -86};
    }
}
