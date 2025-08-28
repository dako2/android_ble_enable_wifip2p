package com.oudmon.ble.base.communication.bigData.resp;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class ClassBluetoothResponse extends BaseResponse {
    private String btAddress;
    private String btName;
    private byte[] subData;

    @Override // com.oudmon.ble.base.communication.bigData.resp.BaseResponse
    public boolean acceptData(byte[] bArr) {
        this.subData = bArr;
        this.btAddress = bytesToMac(Arrays.copyOfRange(bArr, 6, 12));
        this.btName = new String(Arrays.copyOfRange(bArr, 13, bArr[12] + 13), StandardCharsets.UTF_8);
        return false;
    }

    public byte[] getSubData() {
        return this.subData;
    }

    public String getBtAddress() {
        return this.btAddress;
    }

    public String getBtName() {
        return this.btName;
    }

    public String bytesToMac(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String upperCase = Integer.toHexString(b & 255).toUpperCase();
            if (upperCase.length() == 1) {
                upperCase = "0" + upperCase;
            }
            sb.append(upperCase).append(":");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
