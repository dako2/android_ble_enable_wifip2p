package com.oudmon.ble.base.communication.rsp;

import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class GlassModelControlResp extends BaseRspCmd {
    private int dataType;
    private int glassWorkType;
    private int imageCount;
    private int videoCount;
    private String wifiMac;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        XLog.m137i(ByteUtil.byteArrayToString(bArr));
        byte b = bArr[1];
        this.dataType = b;
        if (b == 4) {
            this.imageCount = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 2, 4));
            this.videoCount = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 4, 6));
            return false;
        }
        if (b != 1) {
            return false;
        }
        this.glassWorkType = bArr[2];
        this.wifiMac = String.format("%02X", Byte.valueOf(bArr[4])) + ":" + String.format("%02X", Byte.valueOf(bArr[5])) + ":" + String.format("%02X", Byte.valueOf(bArr[6])) + ":" + String.format("%02X", Byte.valueOf(bArr[7])) + ":" + String.format("%02X", Byte.valueOf(bArr[8])) + ":" + String.format("%02X", Byte.valueOf(bArr[9]));
        return false;
    }

    public int getDataType() {
        return this.dataType;
    }

    public int getGlassWorkType() {
        return this.glassWorkType;
    }

    public int getImageCount() {
        return this.imageCount;
    }

    public int getVideoCount() {
        return this.videoCount;
    }

    public String getWifiMac() {
        return this.wifiMac;
    }
}
