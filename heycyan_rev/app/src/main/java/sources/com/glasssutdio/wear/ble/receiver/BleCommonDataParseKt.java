package com.glasssutdio.wear.ble.receiver;

import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: BleCommonDataParse.kt */
@Metadata(m606d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0016\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0007"}, m607d2 = {"parseBleData", "", "data", "", "parseDeviceInfoData", "uuid", "", "app_release"}, m608k = 2, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class BleCommonDataParseKt {
    public static final void parseBleData(byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        byte b = data[0];
        int i = Constants.FLAG_MASK_ERROR;
    }

    public static final void parseDeviceInfoData(String uuid, byte[] data) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(data, "data");
        String str = new String(data, Charsets.UTF_8);
        if (Intrinsics.areEqual(uuid, Constants.CHAR_FIRMWARE_REVISION.toString())) {
            XLog.m137i(str);
            UserConfig.INSTANCE.getInstance().setFmVersion(str);
        } else if (Intrinsics.areEqual(uuid, Constants.CHAR_HW_REVISION.toString())) {
            UserConfig.INSTANCE.getInstance().setHwVersion(str);
            XLog.m137i(str);
            BleOperateManager.getInstance().setReady(true);
        }
    }
}
