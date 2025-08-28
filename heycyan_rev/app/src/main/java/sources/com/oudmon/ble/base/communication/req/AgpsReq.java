package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class AgpsReq extends MixtureReq {
    private AgpsReq() {
        super(Constants.CMD_AGPS_SWITCH);
    }

    public static AgpsReq getReadInstance() {
        return new AgpsReq() { // from class: com.oudmon.ble.base.communication.req.AgpsReq.1
            {
                this.subData = new byte[]{1};
            }
        };
    }

    public static AgpsReq getWriteInstance(boolean z) {
        return new AgpsReq(z) { // from class: com.oudmon.ble.base.communication.req.AgpsReq.2
            final /* synthetic */ boolean val$enable;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.val$enable = z;
                this.subData = new byte[]{2, z ? (byte) 1 : (byte) 0};
            }
        };
    }
}
