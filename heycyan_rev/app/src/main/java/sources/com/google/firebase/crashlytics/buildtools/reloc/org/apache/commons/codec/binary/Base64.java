package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.binary;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Ascii;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.binary.BaseNCodec;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.LargeDataHandler;
import java.math.BigInteger;
import okio.Utf8;

/* loaded from: classes2.dex */
public class Base64 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 6;
    private static final int BYTES_PER_ENCODED_BLOCK = 4;
    private static final int BYTES_PER_UNENCODED_BLOCK = 3;
    private static final int MASK_6BITS = 63;
    private final int decodeSize;
    private final byte[] decodeTable;
    private final int encodeSize;
    private final byte[] encodeTable;
    private final byte[] lineSeparator;
    static final byte[] CHUNK_SEPARATOR = {13, 10};
    private static final byte[] STANDARD_ENCODE_TABLE = {LargeDataHandler.ACTION_GLASSES_CONTROL, LargeDataHandler.ACTION_GLASSES_BATTERY, 67, 68, LargeDataHandler.ACTION_DEVICE_HEART_BEAT, 70, LargeDataHandler.ACTION_DEVICE_WEAR_SUPPORT, 72, LargeDataHandler.ACTION_BT_CONNECT, 74, 75, 76, 77, 78, 79, Constants.CMD_ANTI_LOST_RATE, LargeDataHandler.ACTION_VOLUME_CONTROL, LargeDataHandler.ACTION_SPEAK_SOUND_SWITCH, 83, Constants.CMD_GPS_ONLINE, 85, 86, 87, 88, LargeDataHandler.ACTION_GPT_UPLOAD, 90, Constants.CMD_GET_ANCS_ON_OFF, 98, 99, 100, 101, 102, 103, 104, Constants.CMD_START_HEART_RATE, Constants.CMD_STOP_HEART_RATE, 107, Constants.CMD_HEALTH_ECG_START, Constants.CMD_HEALTH_ECG_DATA, Constants.CMD_HEALTH_PPG_DATA, Constants.CMD_ECG_STATUS_DATA, Constants.CMD_ECG_MEASURE_TIME, 113, 114, 115, 116, Constants.CMD_DEVICE_DIAL_INDEX, Constants.CMD_DEVICE_BATTERY_SAVING, Constants.CMD_PHONE_SPORT, Constants.CMD_PHONE_SPORT_N0TIFY, 121, Constants.CMD_MUSLIM_DATA, Constants.CMD_AGPS_SWITCH, 49, Constants.CMD_DEVICE_AVATAR, 51, 52, 53, Constants.CMD_PRESSURE_SETTING, Constants.CMD_PRESSURE, Constants.CMD_HRV_ENABLE, Constants.CMD_HRV, Constants.CMD_MENSTRUATION, Constants.CMD_PACKAGE_LENGTH};
    private static final byte[] URL_SAFE_ENCODE_TABLE = {LargeDataHandler.ACTION_GLASSES_CONTROL, LargeDataHandler.ACTION_GLASSES_BATTERY, 67, 68, LargeDataHandler.ACTION_DEVICE_HEART_BEAT, 70, LargeDataHandler.ACTION_DEVICE_WEAR_SUPPORT, 72, LargeDataHandler.ACTION_BT_CONNECT, 74, 75, 76, 77, 78, 79, Constants.CMD_ANTI_LOST_RATE, LargeDataHandler.ACTION_VOLUME_CONTROL, LargeDataHandler.ACTION_SPEAK_SOUND_SWITCH, 83, Constants.CMD_GPS_ONLINE, 85, 86, 87, 88, LargeDataHandler.ACTION_GPT_UPLOAD, 90, Constants.CMD_GET_ANCS_ON_OFF, 98, 99, 100, 101, 102, 103, 104, Constants.CMD_START_HEART_RATE, Constants.CMD_STOP_HEART_RATE, 107, Constants.CMD_HEALTH_ECG_START, Constants.CMD_HEALTH_ECG_DATA, Constants.CMD_HEALTH_PPG_DATA, Constants.CMD_ECG_STATUS_DATA, Constants.CMD_ECG_MEASURE_TIME, 113, 114, 115, 116, Constants.CMD_DEVICE_DIAL_INDEX, Constants.CMD_DEVICE_BATTERY_SAVING, Constants.CMD_PHONE_SPORT, Constants.CMD_PHONE_SPORT_N0TIFY, 121, Constants.CMD_MUSLIM_DATA, Constants.CMD_AGPS_SWITCH, 49, Constants.CMD_DEVICE_AVATAR, 51, 52, 53, Constants.CMD_PRESSURE_SETTING, Constants.CMD_PRESSURE, Constants.CMD_HRV_ENABLE, Constants.CMD_HRV, Constants.CMD_BlackList_LOCATION, 95};
    private static final byte[] DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Constants.CMD_DEVICE_GLASS_MODEL_CONTROL, -1, Constants.CMD_DEVICE_GLASS_MODEL_CONTROL, -1, Utf8.REPLACEMENT_BYTE, 52, 53, Constants.CMD_PRESSURE_SETTING, Constants.CMD_PRESSURE, Constants.CMD_HRV_ENABLE, Constants.CMD_HRV, Constants.CMD_DEVICE_SUGAR_LIPIDS, Constants.CMD_DEVICE_TOUCH, Constants.CMD_DEVICE_FUNCTION_SUPPORT, kotlin.p014io.encoding.Base64.padSymbol, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, Ascii.CAN, 25, -1, -1, -1, -1, Utf8.REPLACEMENT_BYTE, -1, 26, 27, 28, 29, 30, 31, 32, Constants.CMD_TARGET_SETTING, Constants.CMD_FIND_THE_PHONE, Constants.CMD_SET_ALARM_CLOCK, Constants.CMD_GET_ALARM_CLOCK, Constants.CMD_SET_SIT_LONG, Constants.CMD_GET_SIT_LONG, Constants.CMD_SET_DRINK_TIME, Constants.CMD_GET_DRINK_TIME, Constants.CMD_ORIENTATION, Constants.CMD_DISPLAY_STYLE, Constants.CMD_MENSTRUATION, Constants.CMD_AUTO_BLOOD_OXYGEN, Constants.CMD_BlackList_LOCATION, LargeDataHandler.ACTION_BT_MAC_Protocol, Constants.CMD_PACKAGE_LENGTH, Constants.CMD_AGPS_SWITCH, 49, Constants.CMD_DEVICE_AVATAR, 51};

    public Base64() {
        this(0);
    }

    public Base64(boolean z) {
        this(76, CHUNK_SEPARATOR, z);
    }

    public Base64(int i) {
        this(i, CHUNK_SEPARATOR);
    }

    public Base64(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    public Base64(int i, byte[] bArr, boolean z) {
        super(3, 4, i, bArr == null ? 0 : bArr.length);
        this.decodeTable = DECODE_TABLE;
        if (bArr != null) {
            if (containsAlphabetOrPad(bArr)) {
                throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + StringUtils.newStringUtf8(bArr) + "]");
            }
            if (i > 0) {
                this.encodeSize = bArr.length + 4;
                byte[] bArr2 = new byte[bArr.length];
                this.lineSeparator = bArr2;
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            } else {
                this.encodeSize = 4;
                this.lineSeparator = null;
            }
        } else {
            this.encodeSize = 4;
            this.lineSeparator = null;
        }
        this.decodeSize = this.encodeSize - 1;
        this.encodeTable = z ? URL_SAFE_ENCODE_TABLE : STANDARD_ENCODE_TABLE;
    }

    public boolean isUrlSafe() {
        return this.encodeTable == URL_SAFE_ENCODE_TABLE;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.binary.BaseNCodec
    void encode(byte[] bArr, int i, int i2, BaseNCodec.Context context) {
        if (context.eof) {
            return;
        }
        if (i2 >= 0) {
            int i3 = 0;
            while (i3 < i2) {
                byte[] bArrEnsureBufferSize = ensureBufferSize(this.encodeSize, context);
                context.modulus = (context.modulus + 1) % 3;
                int i4 = i + 1;
                int i5 = bArr[i];
                if (i5 < 0) {
                    i5 += 256;
                }
                context.ibitWorkArea = (context.ibitWorkArea << 8) + i5;
                if (context.modulus == 0) {
                    int i6 = context.pos;
                    context.pos = i6 + 1;
                    bArrEnsureBufferSize[i6] = this.encodeTable[(context.ibitWorkArea >> 18) & 63];
                    int i7 = context.pos;
                    context.pos = i7 + 1;
                    bArrEnsureBufferSize[i7] = this.encodeTable[(context.ibitWorkArea >> 12) & 63];
                    int i8 = context.pos;
                    context.pos = i8 + 1;
                    bArrEnsureBufferSize[i8] = this.encodeTable[(context.ibitWorkArea >> 6) & 63];
                    int i9 = context.pos;
                    context.pos = i9 + 1;
                    bArrEnsureBufferSize[i9] = this.encodeTable[context.ibitWorkArea & 63];
                    context.currentLinePos += 4;
                    if (this.lineLength > 0 && this.lineLength <= context.currentLinePos) {
                        System.arraycopy(this.lineSeparator, 0, bArrEnsureBufferSize, context.pos, this.lineSeparator.length);
                        context.pos += this.lineSeparator.length;
                        context.currentLinePos = 0;
                    }
                }
                i3++;
                i = i4;
            }
            return;
        }
        context.eof = true;
        if (context.modulus == 0 && this.lineLength == 0) {
            return;
        }
        byte[] bArrEnsureBufferSize2 = ensureBufferSize(this.encodeSize, context);
        int i10 = context.pos;
        int i11 = context.modulus;
        if (i11 != 0) {
            if (i11 == 1) {
                int i12 = context.pos;
                context.pos = i12 + 1;
                bArrEnsureBufferSize2[i12] = this.encodeTable[(context.ibitWorkArea >> 2) & 63];
                int i13 = context.pos;
                context.pos = i13 + 1;
                bArrEnsureBufferSize2[i13] = this.encodeTable[(context.ibitWorkArea << 4) & 63];
                if (this.encodeTable == STANDARD_ENCODE_TABLE) {
                    int i14 = context.pos;
                    context.pos = i14 + 1;
                    bArrEnsureBufferSize2[i14] = this.pad;
                    int i15 = context.pos;
                    context.pos = i15 + 1;
                    bArrEnsureBufferSize2[i15] = this.pad;
                }
            } else if (i11 == 2) {
                int i16 = context.pos;
                context.pos = i16 + 1;
                bArrEnsureBufferSize2[i16] = this.encodeTable[(context.ibitWorkArea >> 10) & 63];
                int i17 = context.pos;
                context.pos = i17 + 1;
                bArrEnsureBufferSize2[i17] = this.encodeTable[(context.ibitWorkArea >> 4) & 63];
                int i18 = context.pos;
                context.pos = i18 + 1;
                bArrEnsureBufferSize2[i18] = this.encodeTable[(context.ibitWorkArea << 2) & 63];
                if (this.encodeTable == STANDARD_ENCODE_TABLE) {
                    int i19 = context.pos;
                    context.pos = i19 + 1;
                    bArrEnsureBufferSize2[i19] = this.pad;
                }
            } else {
                throw new IllegalStateException("Impossible modulus " + context.modulus);
            }
        }
        context.currentLinePos += context.pos - i10;
        if (this.lineLength <= 0 || context.currentLinePos <= 0) {
            return;
        }
        System.arraycopy(this.lineSeparator, 0, bArrEnsureBufferSize2, context.pos, this.lineSeparator.length);
        context.pos += this.lineSeparator.length;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.binary.BaseNCodec
    void decode(byte[] bArr, int i, int i2, BaseNCodec.Context context) {
        byte b;
        if (context.eof) {
            return;
        }
        if (i2 < 0) {
            context.eof = true;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= i2) {
                break;
            }
            byte[] bArrEnsureBufferSize = ensureBufferSize(this.decodeSize, context);
            int i4 = i + 1;
            byte b2 = bArr[i];
            if (b2 == this.pad) {
                context.eof = true;
                break;
            }
            if (b2 >= 0) {
                byte[] bArr2 = DECODE_TABLE;
                if (b2 < bArr2.length && (b = bArr2[b2]) >= 0) {
                    context.modulus = (context.modulus + 1) % 4;
                    context.ibitWorkArea = (context.ibitWorkArea << 6) + b;
                    if (context.modulus == 0) {
                        int i5 = context.pos;
                        context.pos = i5 + 1;
                        bArrEnsureBufferSize[i5] = (byte) ((context.ibitWorkArea >> 16) & 255);
                        int i6 = context.pos;
                        context.pos = i6 + 1;
                        bArrEnsureBufferSize[i6] = (byte) ((context.ibitWorkArea >> 8) & 255);
                        int i7 = context.pos;
                        context.pos = i7 + 1;
                        bArrEnsureBufferSize[i7] = (byte) (context.ibitWorkArea & 255);
                    }
                }
            }
            i3++;
            i = i4;
        }
        if (!context.eof || context.modulus == 0) {
            return;
        }
        byte[] bArrEnsureBufferSize2 = ensureBufferSize(this.decodeSize, context);
        int i8 = context.modulus;
        if (i8 != 1) {
            if (i8 == 2) {
                context.ibitWorkArea >>= 4;
                int i9 = context.pos;
                context.pos = i9 + 1;
                bArrEnsureBufferSize2[i9] = (byte) (context.ibitWorkArea & 255);
                return;
            }
            if (i8 == 3) {
                context.ibitWorkArea >>= 2;
                int i10 = context.pos;
                context.pos = i10 + 1;
                bArrEnsureBufferSize2[i10] = (byte) ((context.ibitWorkArea >> 8) & 255);
                int i11 = context.pos;
                context.pos = i11 + 1;
                bArrEnsureBufferSize2[i11] = (byte) (context.ibitWorkArea & 255);
                return;
            }
            throw new IllegalStateException("Impossible modulus " + context.modulus);
        }
    }

    @Deprecated
    public static boolean isArrayByteBase64(byte[] bArr) {
        return isBase64(bArr);
    }

    public static boolean isBase64(byte b) {
        if (b != 61) {
            if (b >= 0) {
                byte[] bArr = DECODE_TABLE;
                if (b >= bArr.length || bArr[b] == -1) {
                }
            }
            return false;
        }
        return true;
    }

    public static boolean isBase64(String str) {
        return isBase64(StringUtils.getBytesUtf8(str));
    }

    public static boolean isBase64(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            if (!isBase64(bArr[i]) && !isWhiteSpace(bArr[i])) {
                return false;
            }
        }
        return true;
    }

    public static byte[] encodeBase64(byte[] bArr) {
        return encodeBase64(bArr, false);
    }

    public static String encodeBase64String(byte[] bArr) {
        return StringUtils.newStringUtf8(encodeBase64(bArr, false));
    }

    public static byte[] encodeBase64URLSafe(byte[] bArr) {
        return encodeBase64(bArr, false, true);
    }

    public static String encodeBase64URLSafeString(byte[] bArr) {
        return StringUtils.newStringUtf8(encodeBase64(bArr, false, true));
    }

    public static byte[] encodeBase64Chunked(byte[] bArr) {
        return encodeBase64(bArr, true);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z) {
        return encodeBase64(bArr, z, false);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2) {
        return encodeBase64(bArr, z, z2, Integer.MAX_VALUE);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2, int i) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        Base64 base64 = z ? new Base64(z2) : new Base64(0, CHUNK_SEPARATOR, z2);
        long encodedLength = base64.getEncodedLength(bArr);
        if (encodedLength > i) {
            throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + encodedLength + ") than the specified maximum size of " + i);
        }
        return base64.encode(bArr);
    }

    public static byte[] decodeBase64(String str) {
        return new Base64().decode(str);
    }

    public static byte[] decodeBase64(byte[] bArr) {
        return new Base64().decode(bArr);
    }

    public static BigInteger decodeInteger(byte[] bArr) {
        return new BigInteger(1, decodeBase64(bArr));
    }

    public static byte[] encodeInteger(BigInteger bigInteger) {
        if (bigInteger == null) {
            throw new NullPointerException("encodeInteger called with null parameter");
        }
        return encodeBase64(toIntegerBytes(bigInteger), false);
    }

    static byte[] toIntegerBytes(BigInteger bigInteger) {
        int iBitLength = ((bigInteger.bitLength() + 7) >> 3) << 3;
        byte[] byteArray = bigInteger.toByteArray();
        int i = 1;
        if (bigInteger.bitLength() % 8 != 0 && (bigInteger.bitLength() / 8) + 1 == iBitLength / 8) {
            return byteArray;
        }
        int length = byteArray.length;
        if (bigInteger.bitLength() % 8 == 0) {
            length--;
        } else {
            i = 0;
        }
        int i2 = iBitLength / 8;
        int i3 = i2 - length;
        byte[] bArr = new byte[i2];
        System.arraycopy(byteArray, i, bArr, i3, length);
        return bArr;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.binary.BaseNCodec
    protected boolean isInAlphabet(byte b) {
        if (b >= 0) {
            byte[] bArr = this.decodeTable;
            if (b < bArr.length && bArr[b] != -1) {
                return true;
            }
        }
        return false;
    }
}
