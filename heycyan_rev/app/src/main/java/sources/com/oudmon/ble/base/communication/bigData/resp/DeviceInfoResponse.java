package com.oudmon.ble.base.communication.bigData.resp;

import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class DeviceInfoResponse extends BaseResponse {
    private byte[] subData;
    private String firmwareVersion = "";
    private String hardwareVersion = "";
    private String wifiFirmwareVersion = "";
    private String wifiHardwareVersion = "";

    @Override // com.oudmon.ble.base.communication.bigData.resp.BaseResponse
    public boolean acceptData(byte[] bArr) {
        this.subData = bArr;
        try {
            int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 7, 9));
            int iBytesToInt2 = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 9, 11));
            int iBytesToInt3 = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 11, 13));
            int iBytesToInt4 = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 13, 15));
            int i = iBytesToInt + 15;
            this.firmwareVersion = new String(Arrays.copyOfRange(bArr, 15, i), StandardCharsets.UTF_8);
            int i2 = iBytesToInt2 + i;
            this.hardwareVersion = new String(Arrays.copyOfRange(bArr, i, i2), StandardCharsets.UTF_8);
            int i3 = iBytesToInt3 + i2;
            this.wifiFirmwareVersion = new String(Arrays.copyOfRange(bArr, i2, i3), StandardCharsets.UTF_8);
            this.wifiHardwareVersion = new String(Arrays.copyOfRange(bArr, i3, iBytesToInt4 + i3), StandardCharsets.UTF_8);
            XLog.m137i("firmwareVersion=" + this.firmwareVersion + ",hardwareVersion=" + this.hardwareVersion + ",wifiFirmwareVersion=" + this.wifiFirmwareVersion + ",wifiHardwareVersion=" + this.wifiHardwareVersion);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public byte[] getSubData() {
        return this.subData;
    }

    public String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public String getWifiFirmwareVersion() {
        return this.wifiFirmwareVersion;
    }

    public String getWifiHardwareVersion() {
        return this.wifiHardwareVersion;
    }
}
