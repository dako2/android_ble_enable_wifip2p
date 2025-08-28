package com.oudmon.ble.base.communication.req;

import java.nio.charset.StandardCharsets;

/* loaded from: classes2.dex */
public class MusicSwitchReq extends BaseReqCmd {
    protected byte[] data;

    private MusicSwitchReq() {
        super((byte) 28);
    }

    public static MusicSwitchReq getReadInstance() {
        return new MusicSwitchReq() { // from class: com.oudmon.ble.base.communication.req.MusicSwitchReq.1
            {
                this.data = new byte[]{1};
            }
        };
    }

    public static MusicSwitchReq getWriteInstance(boolean z) {
        return new MusicSwitchReq(z) { // from class: com.oudmon.ble.base.communication.req.MusicSwitchReq.2
            final /* synthetic */ boolean val$enable;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.val$enable = z;
                this.data = new byte[]{2, (byte) (z ? 1 : 2)};
            }
        };
    }

    public static MusicSwitchReq getNewWriteInstance(boolean z, int i, int i2, String str) {
        return new MusicSwitchReq(str, z, i, i2) { // from class: com.oudmon.ble.base.communication.req.MusicSwitchReq.3
            final /* synthetic */ String val$name;
            final /* synthetic */ boolean val$playing;
            final /* synthetic */ int val$progress;
            final /* synthetic */ int val$volume;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.val$name = str;
                this.val$playing = z;
                this.val$progress = i;
                this.val$volume = i2;
                byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
                byte[] bArr = new byte[bytes.length + 3];
                bArr[0] = (byte) (!z ? 1 : 0);
                bArr[1] = (byte) i;
                bArr[2] = (byte) i2;
                System.arraycopy(bytes, 0, bArr, 3, bytes.length);
                this.data = bArr;
            }
        };
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.data;
    }
}
