package com.oudmon.ble.base.communication.bigData.resp;

import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class GlassesTouchSupportRsp extends BaseResponse {
    int glassesModel;
    boolean translationSupport;
    boolean volumeControl;
    boolean wearCheckSupport;

    @Override // com.oudmon.ble.base.communication.bigData.resp.BaseResponse
    public boolean acceptData(byte[] bArr) {
        try {
            boolean z = true;
            if (bArr.length <= 9) {
                this.glassesModel = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 7, 8));
                this.translationSupport = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 8, 9)) == 1;
                this.wearCheckSupport = true;
            } else if (bArr.length == 10) {
                this.glassesModel = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 7, 8));
                this.translationSupport = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 8, 9)) == 1;
                if (ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 9, 10)) != 0) {
                    z = false;
                }
                this.wearCheckSupport = z;
            } else {
                this.glassesModel = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 7, 8));
                this.translationSupport = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 8, 9)) == 1;
                this.wearCheckSupport = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 9, 10)) == 0;
                if ((bArr[10] & 1) == 0) {
                    z = false;
                }
                this.volumeControl = z;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getGlassesModel() {
        return this.glassesModel;
    }

    public boolean isTranslationSupport() {
        return this.translationSupport;
    }

    public boolean isWearCheckSupport() {
        return this.wearCheckSupport;
    }

    public boolean isVolumeControl() {
        return this.volumeControl;
    }
}
