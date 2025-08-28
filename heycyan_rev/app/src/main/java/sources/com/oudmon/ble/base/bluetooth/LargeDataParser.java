package com.oudmon.ble.base.bluetooth;

import com.oudmon.ble.base.communication.ILargeDataResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.resp.BaseResponse;
import com.oudmon.ble.base.communication.bigData.resp.BigDataBeanFactory;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class LargeDataParser {
    private static LargeDataParser instance = null;
    public static String uuid_notify = "de5bf729-d711-4e47-af26-65e3012a5dc7";
    public int dataLength = 0;
    boolean end = true;
    public byte[] tempData;

    private LargeDataParser() {
    }

    public static LargeDataParser getInstance() {
        if (instance == null) {
            synchronized (LargeDataParser.class) {
                if (instance == null) {
                    instance = new LargeDataParser();
                }
            }
        }
        return instance;
    }

    public void parseBigLargeData(String str, byte[] bArr) {
        if (uuid_notify.equals(str)) {
            if (bArr.length >= 6 && (bArr[0] & 255) == 188 && this.end) {
                int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 2, 4));
                this.dataLength = iBytesToInt;
                if (bArr.length - 6 >= iBytesToInt) {
                    byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 0, bArr.length);
                    this.tempData = bArrCopyOfRange;
                    parseData(bArrCopyOfRange);
                    this.tempData = new byte[0];
                    this.end = true;
                    return;
                }
                this.end = false;
                this.tempData = Arrays.copyOfRange(bArr, 0, bArr.length);
                return;
            }
            byte[] bArrConcat = ByteUtil.concat(this.tempData, bArr);
            this.tempData = bArrConcat;
            if (bArrConcat.length - 6 == this.dataLength) {
                this.end = true;
                parseData(bArrConcat);
                this.tempData = new byte[0];
                return;
            }
            this.end = false;
        }
    }

    public void parseData(byte[] bArr) {
        BaseResponse baseResponseCreateBean;
        ILargeDataResponse iLargeDataResponse;
        byte b = bArr[1];
        if ((bArr[0] & 255) != 188 || (baseResponseCreateBean = BigDataBeanFactory.createBean(b)) == null) {
            return;
        }
        baseResponseCreateBean.acceptData(bArr);
        if (bArr[1] == 115) {
            iLargeDataResponse = LargeDataHandler.getInstance().getNoClearMap().get(Integer.valueOf(b));
        } else {
            iLargeDataResponse = LargeDataHandler.getInstance().getRespMap().get(Integer.valueOf(b));
        }
        if (iLargeDataResponse != null) {
            iLargeDataResponse.parseData(b, baseResponseCreateBean);
        }
    }
}
