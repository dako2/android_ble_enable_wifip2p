package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class DisplayStyleReq extends MixtureReq {
    private DisplayStyleReq() {
        super(Constants.CMD_DISPLAY_STYLE);
        this.subData = new byte[]{1};
    }

    private DisplayStyleReq(int i) {
        super(Constants.CMD_DISPLAY_STYLE);
        this.subData = new byte[]{2, (byte) i};
    }

    public static DisplayStyleReq getReadInstance() {
        return new DisplayStyleReq();
    }

    public static DisplayStyleReq getWriteInstance(int i) {
        return new DisplayStyleReq(i);
    }
}
