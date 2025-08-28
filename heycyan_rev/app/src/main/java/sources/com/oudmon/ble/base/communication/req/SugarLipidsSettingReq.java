package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.utils.ByteUtil;

/* loaded from: classes2.dex */
public class SugarLipidsSettingReq extends MixtureReq {
    private SugarLipidsSettingReq(byte b) {
        super(Constants.CMD_DEVICE_SUGAR_LIPIDS);
        this.subData = new byte[]{b, 1};
    }

    private SugarLipidsSettingReq(byte b, boolean z, int i) {
        super(Constants.CMD_DEVICE_SUGAR_LIPIDS);
        this.subData = new byte[]{b, 2, z ? (byte) 1 : (byte) 0, (byte) ByteUtil.loword(i), (byte) ByteUtil.hiword(i)};
    }

    public static SugarLipidsSettingReq getReadInstance(byte b) {
        return new SugarLipidsSettingReq(b);
    }

    public static SugarLipidsSettingReq getWriteInstance(byte b, boolean z, int i) {
        return new SugarLipidsSettingReq(b, z, i);
    }
}
