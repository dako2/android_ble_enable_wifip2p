package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.utils.ByteUtil;

/* loaded from: classes2.dex */
public class MuslimTargetReq extends MixtureReq {
    private byte index;

    public MuslimTargetReq() {
        super(Constants.CMD_MUSLIM_GOAL_DATA);
        this.subData = new byte[]{1, 1};
    }

    public MuslimTargetReq(int i) {
        super(Constants.CMD_MUSLIM_GOAL_DATA);
        this.subData = ByteUtil.concat(new byte[]{2, 1}, ByteUtil.intToByte(i, 4));
    }
}
