package com.oudmon.ble.base.communication.req;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import com.oudmon.ble.base.communication.rsp.MixtureRsp;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class TestResp extends MixtureRsp {
    String data;

    /* renamed from: sb */
    StringBuilder f533sb = new StringBuilder();

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] bArr) {
        StringBuilder sb = this.f533sb;
        sb.delete(0, sb.length());
        int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 0, 2));
        int iBytesToInt2 = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 2, 4));
        int iBytesToInt3 = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 4, 6));
        int iBytesToInt4 = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 6, 8));
        int iBytesToInt5 = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 8, 10));
        int iBytesToInt6 = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 10, 12));
        this.f533sb.append(iBytesToInt).append(",");
        this.f533sb.append(iBytesToInt2).append(",");
        this.f533sb.append(iBytesToInt3).append(",");
        this.f533sb.append(iBytesToInt4).append(",");
        this.f533sb.append(iBytesToInt5).append(",");
        this.f533sb.append(iBytesToInt6).append(IOUtils.LINE_SEPARATOR_UNIX);
    }

    public String getData() {
        return this.f533sb.toString();
    }
}
