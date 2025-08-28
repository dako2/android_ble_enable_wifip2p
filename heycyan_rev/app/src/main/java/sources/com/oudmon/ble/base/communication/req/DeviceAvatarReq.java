package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class DeviceAvatarReq extends BaseReqCmd {
    public DeviceAvatarReq() {
        super(Constants.CMD_DEVICE_AVATAR);
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return new byte[0];
    }
}
