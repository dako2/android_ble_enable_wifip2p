package com.oudmon.ble.base.communication.rsp;

import android.util.Log;
import com.oudmon.ble.base.communication.entity.BpDataEntity;
import com.oudmon.qc_utils.bytes.DataTransferUtils;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class BpDataRsp extends BaseRspCmd {
    private BpDataEntity bpDataEntity;
    private int valueIndex = 0;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        Log.i("Jxr35", "acceptData.. data: " + DataTransferUtils.getHexString(bArr));
        byte b = bArr[0];
        if (b == 0) {
            this.valueIndex = 0;
            this.bpDataEntity = new BpDataEntity(bArr[1] + 2000, bArr[2], bArr[3], bArr[4]);
            byte b2 = bArr[4];
            for (int i = 0; i < 6; i++) {
                byte b3 = bArr[i + 5];
                for (int i2 = 0; i2 < 8; i2++) {
                    if ((((byte) (b3 >>> i2)) & 1) == 1) {
                        this.bpDataEntity.addBpIndex(((i * 8) + i2) * b2);
                    }
                }
            }
        } else if (b == 1) {
            BpDataEntity bpDataEntity = this.bpDataEntity;
            if (bpDataEntity == null) {
                return true;
            }
            bpDataEntity.addRealValue(this.valueIndex * 13, Arrays.copyOfRange(bArr, 1, bArr.length));
            this.valueIndex++;
            Log.i("Jxr35", "acceptData: size=" + this.bpDataEntity.getBpValues().size() + " cur offset=" + (this.valueIndex * 13));
            if (this.valueIndex * 13 >= this.bpDataEntity.getBpValues().size()) {
                Log.i("Jxr35", "acceptData: 成功");
                return false;
            }
        } else if ((b & 255) == 255) {
            return false;
        }
        return true;
    }

    public BpDataEntity getBpDataEntity() {
        return this.bpDataEntity;
    }
}
