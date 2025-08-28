package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class BloodSugarLipidsSettingRsp extends BaseRspCmd {
    private boolean isEnable;
    private boolean read;
    private byte type;
    private int value;

    public boolean isEnable() {
        return this.isEnable;
    }

    public int getValue() {
        return this.value;
    }

    public byte getType() {
        return this.type;
    }

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        byte b = bArr[1];
        this.read = b == 1;
        if (b == 1) {
            this.type = bArr[0];
            this.isEnable = bArr[2] == 1;
            this.value = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 3, 5));
        }
        return false;
    }
}
