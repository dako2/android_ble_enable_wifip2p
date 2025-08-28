package com.oudmon.ble.base.communication.bigData.bean;

import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
public class WifiInfoReq {
    private byte[] mData;

    public WifiInfoReq(String str) {
        this.mData = new byte[]{2, (byte) str.length()};
        this.mData = ByteUtil.concat(this.mData, str.getBytes(Charset.forName("UTF-8")));
    }

    public byte[] getSubData() {
        return this.mData;
    }
}
