package androidx.profileinstaller;

import com.oudmon.ble.base.communication.Constants;
import java.util.Arrays;

/* loaded from: classes.dex */
public class ProfileVersion {
    public static final int MIN_SUPPORTED_SDK = 24;
    static final byte[] V015_S = {Constants.CMD_AGPS_SWITCH, 49, 53, 0};
    static final byte[] V010_P = {Constants.CMD_AGPS_SWITCH, 49, Constants.CMD_AGPS_SWITCH, 0};
    static final byte[] V009_O_MR1 = {Constants.CMD_AGPS_SWITCH, Constants.CMD_AGPS_SWITCH, Constants.CMD_HRV, 0};
    static final byte[] V005_O = {Constants.CMD_AGPS_SWITCH, Constants.CMD_AGPS_SWITCH, 53, 0};
    static final byte[] V001_N = {Constants.CMD_AGPS_SWITCH, Constants.CMD_AGPS_SWITCH, 49, 0};
    static final byte[] METADATA_V001_N = {Constants.CMD_AGPS_SWITCH, Constants.CMD_AGPS_SWITCH, 49, 0};
    static final byte[] METADATA_V002 = {Constants.CMD_AGPS_SWITCH, Constants.CMD_AGPS_SWITCH, Constants.CMD_DEVICE_AVATAR, 0};

    private ProfileVersion() {
    }

    static String dexKeySeparator(byte[] bArr) {
        return (Arrays.equals(bArr, V001_N) || Arrays.equals(bArr, V005_O)) ? ":" : "!";
    }
}
