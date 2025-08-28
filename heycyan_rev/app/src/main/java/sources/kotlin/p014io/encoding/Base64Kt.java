package kotlin.p014io.encoding;

import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.LargeDataHandler;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;

/* compiled from: Base64.kt */
@Metadata(m606d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0001\"\u0016\u0010\u0000\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0006\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0003\"\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, m607d2 = {"base64DecodeMap", "", "getBase64DecodeMap$annotations", "()V", "base64EncodeMap", "", "base64UrlDecodeMap", "getBase64UrlDecodeMap$annotations", "base64UrlEncodeMap", "isInMimeAlphabet", "", "symbol", "", "kotlin-stdlib"}, m608k = 2, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes3.dex */
public final class Base64Kt {
    private static final int[] base64DecodeMap;
    private static final byte[] base64EncodeMap;
    private static final int[] base64UrlDecodeMap;
    private static final byte[] base64UrlEncodeMap;

    private static /* synthetic */ void getBase64DecodeMap$annotations() {
    }

    private static /* synthetic */ void getBase64UrlDecodeMap$annotations() {
    }

    static {
        byte[] bArr = {LargeDataHandler.ACTION_GLASSES_CONTROL, LargeDataHandler.ACTION_GLASSES_BATTERY, 67, 68, LargeDataHandler.ACTION_DEVICE_HEART_BEAT, 70, LargeDataHandler.ACTION_DEVICE_WEAR_SUPPORT, 72, LargeDataHandler.ACTION_BT_CONNECT, 74, 75, 76, 77, 78, 79, Constants.CMD_ANTI_LOST_RATE, LargeDataHandler.ACTION_VOLUME_CONTROL, LargeDataHandler.ACTION_SPEAK_SOUND_SWITCH, 83, Constants.CMD_GPS_ONLINE, 85, 86, 87, 88, LargeDataHandler.ACTION_GPT_UPLOAD, 90, Constants.CMD_GET_ANCS_ON_OFF, 98, 99, 100, 101, 102, 103, 104, Constants.CMD_START_HEART_RATE, Constants.CMD_STOP_HEART_RATE, 107, Constants.CMD_HEALTH_ECG_START, Constants.CMD_HEALTH_ECG_DATA, Constants.CMD_HEALTH_PPG_DATA, Constants.CMD_ECG_STATUS_DATA, Constants.CMD_ECG_MEASURE_TIME, 113, 114, 115, 116, Constants.CMD_DEVICE_DIAL_INDEX, Constants.CMD_DEVICE_BATTERY_SAVING, Constants.CMD_PHONE_SPORT, Constants.CMD_PHONE_SPORT_N0TIFY, 121, Constants.CMD_MUSLIM_DATA, Constants.CMD_AGPS_SWITCH, 49, Constants.CMD_DEVICE_AVATAR, 51, 52, 53, Constants.CMD_PRESSURE_SETTING, Constants.CMD_PRESSURE, Constants.CMD_HRV_ENABLE, Constants.CMD_HRV, Constants.CMD_MENSTRUATION, Constants.CMD_PACKAGE_LENGTH};
        base64EncodeMap = bArr;
        int[] iArr = new int[256];
        ArraysKt.fill$default(iArr, -1, 0, 0, 6, (Object) null);
        iArr[61] = -2;
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            iArr[bArr[i2]] = i3;
            i2++;
            i3++;
        }
        base64DecodeMap = iArr;
        byte[] bArr2 = {LargeDataHandler.ACTION_GLASSES_CONTROL, LargeDataHandler.ACTION_GLASSES_BATTERY, 67, 68, LargeDataHandler.ACTION_DEVICE_HEART_BEAT, 70, LargeDataHandler.ACTION_DEVICE_WEAR_SUPPORT, 72, LargeDataHandler.ACTION_BT_CONNECT, 74, 75, 76, 77, 78, 79, Constants.CMD_ANTI_LOST_RATE, LargeDataHandler.ACTION_VOLUME_CONTROL, LargeDataHandler.ACTION_SPEAK_SOUND_SWITCH, 83, Constants.CMD_GPS_ONLINE, 85, 86, 87, 88, LargeDataHandler.ACTION_GPT_UPLOAD, 90, Constants.CMD_GET_ANCS_ON_OFF, 98, 99, 100, 101, 102, 103, 104, Constants.CMD_START_HEART_RATE, Constants.CMD_STOP_HEART_RATE, 107, Constants.CMD_HEALTH_ECG_START, Constants.CMD_HEALTH_ECG_DATA, Constants.CMD_HEALTH_PPG_DATA, Constants.CMD_ECG_STATUS_DATA, Constants.CMD_ECG_MEASURE_TIME, 113, 114, 115, 116, Constants.CMD_DEVICE_DIAL_INDEX, Constants.CMD_DEVICE_BATTERY_SAVING, Constants.CMD_PHONE_SPORT, Constants.CMD_PHONE_SPORT_N0TIFY, 121, Constants.CMD_MUSLIM_DATA, Constants.CMD_AGPS_SWITCH, 49, Constants.CMD_DEVICE_AVATAR, 51, 52, 53, Constants.CMD_PRESSURE_SETTING, Constants.CMD_PRESSURE, Constants.CMD_HRV_ENABLE, Constants.CMD_HRV, Constants.CMD_BlackList_LOCATION, 95};
        base64UrlEncodeMap = bArr2;
        int[] iArr2 = new int[256];
        ArraysKt.fill$default(iArr2, -1, 0, 0, 6, (Object) null);
        iArr2[61] = -2;
        int length2 = bArr2.length;
        int i4 = 0;
        while (i < length2) {
            iArr2[bArr2[i]] = i4;
            i++;
            i4++;
        }
        base64UrlDecodeMap = iArr2;
    }

    public static final boolean isInMimeAlphabet(int i) {
        if (i >= 0) {
            int[] iArr = base64DecodeMap;
            if (i < iArr.length && iArr[i] != -1) {
                return true;
            }
        }
        return false;
    }
}
