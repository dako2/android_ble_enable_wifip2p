package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class DisplayOrientationReq extends MixtureReq {
    private DisplayOrientationReq() {
        super(Constants.CMD_ORIENTATION);
        this.subData = new byte[]{1};
    }

    private DisplayOrientationReq(boolean z, boolean z2) {
        super(Constants.CMD_ORIENTATION);
        this.subData = new byte[]{2, (byte) (z ? 1 : 2), (byte) (z ? 0 : z2 ? 1 : 2)};
    }

    public static DisplayOrientationReq getReadInstance() {
        return new DisplayOrientationReq();
    }

    public static DisplayOrientationReq getWriteInstance(boolean z, boolean z2) {
        return new DisplayOrientationReq(z, z2);
    }
}
