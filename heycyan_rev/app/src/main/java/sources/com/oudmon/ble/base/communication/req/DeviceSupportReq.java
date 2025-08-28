package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class DeviceSupportReq extends MixtureReq {
    private DeviceSupportReq() {
        super(Constants.CMD_DEVICE_FUNCTION_SUPPORT);
    }

    public static DeviceSupportReq getReadInstance() {
        return new DeviceSupportReq();
    }
}
