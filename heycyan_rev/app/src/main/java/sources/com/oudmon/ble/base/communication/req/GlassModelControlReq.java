package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class GlassModelControlReq extends MixtureReq {
    public GlassModelControlReq(int i, int i2) {
        super(Constants.CMD_DEVICE_GLASS_MODEL_CONTROL);
        this.subData = new byte[]{2, (byte) i, (byte) i2};
    }
}
