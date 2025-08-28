package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class MuslimReq extends MixtureReq {
    private byte index;

    public MuslimReq(byte b) {
        super(Constants.CMD_MUSLIM_DATA);
        this.subData = new byte[]{1, b};
    }

    public MuslimReq(boolean z) {
        super(Constants.CMD_MUSLIM_DATA);
        this.subData = new byte[]{2, 1};
    }

    public static MuslimReq getWriteInstance(boolean z) {
        return new MuslimReq(z);
    }
}
