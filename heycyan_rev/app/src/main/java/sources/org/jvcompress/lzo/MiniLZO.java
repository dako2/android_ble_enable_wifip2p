package org.jvcompress.lzo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.jvcompress.util.MInt;

/* loaded from: classes3.dex */
public final class MiniLZO implements LZOConstants {
    public static final int c0_last = 7;
    public static final int c0_literal = 3;
    public static final int c0_m3_m4_len = 5;
    public static final int c0_m3_m4_offset = 6;
    public static final int c0_match = 4;
    public static final int c0_top = 1;
    public static final int c0_try_match = 2;
    public static final int c_copy_match = 4;
    public static final int c_eof_found = 7;
    public static final int c_first_literal_run = 2;
    public static final int c_input_overrun = 8;
    public static final int c_lookbehind_overrun = 10;
    public static final int c_match = 3;
    public static final int c_match_done = 5;
    public static final int c_match_next = 6;
    public static final int c_output_overrun = 9;
    public static final int c_top_loop = 1;
    private static final boolean debug = false;

    /* renamed from: U */
    private static final int m652U(byte b) {
        return b & 255;
    }

    public static void main(String[] strArr) throws IOException {
        int i = 0;
        int iIntValue = Integer.getInteger("ZERO_FILL", 0).intValue();
        String property = System.getProperty("IFILE", "IFILE");
        try {
            File file = new File(System.getProperty("OFILE", "IFILE"));
            long length = file.length();
            int i2 = (int) length;
            byte[] bArr = new byte[i2];
            File file2 = new File(property);
            byte[] bArr2 = new byte[(int) file2.length()];
            byte[] bArr3 = new byte[(int) file2.length()];
            FileInputStream fileInputStream = new FileInputStream(file);
            FileInputStream fileInputStream2 = new FileInputStream(property);
            if (fileInputStream.read(bArr) > 0) {
                MInt mInt = new MInt();
                System.out.println("Decompressing byte.length=" + length);
                lzo1x_decompress(bArr, i2, bArr2, mInt);
                System.out.println("Got decompressed length:" + mInt.f949v);
                if (iIntValue > 0) {
                    System.out.println("Doing zero fill check");
                    while (i < mInt.f949v) {
                        if (bArr2[i] != 0) {
                            throw new AssertionError("Decompreesed values not matching to Zero @:" + i);
                        }
                        i++;
                    }
                    return;
                }
                fileInputStream2.read(bArr3);
                while (i < mInt.f949v) {
                    if (bArr2[i] != bArr3[i]) {
                        throw new AssertionError("Decompreesed values not matching to Zero @:" + i);
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0016. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:116:0x003e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01a8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final int _lzo1x_1_do_compress(byte[] bArr, int i, byte[] bArr2, MInt mInt, int[] iArr) {
        MInt mInt2;
        int i2;
        int i3;
        int i4 = i - 13;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        char c = 1;
        int i11 = 4;
        while (true) {
            switch (c) {
                case 1:
                    int i12 = (((255 & bArr[i11]) ^ (((bArr[i11 + 1] & 255) << 5) ^ (((bArr[i11 + 4] & 255) << 6) ^ ((bArr[i11 + 3] & 255) << 5)))) >> 5) * 33;
                    i6 = i12 & 16383;
                    i5 = i11 - (i11 - iArr[i6]);
                    if (i5 > 0 && (i9 = i11 - i5) > 0 && i9 <= 49151) {
                        if (i9 > 2048) {
                            byte b = bArr[i5 + 3];
                            byte b2 = bArr[i11 + 3];
                            if (b != b2 && ((i5 = iArr[(i6 = (i12 & 2047) ^ 8223)]) < 0 || (i9 = i11 - i5) <= 0 || i9 > 49151 || (i9 > 2048 && bArr[i5 + 3] != b2))) {
                            }
                        }
                        c = 2;
                    }
                    c = 3;
                    break;
                case 2:
                    if (bArr[i5] == bArr[i11] && bArr[i5 + 1] == bArr[i11 + 1] && bArr[i5 + 2] == bArr[i11 + 2]) {
                        c = 4;
                    } else {
                        iArr[i6] = i11;
                        i11++;
                        if (i11 < i4) {
                            mInt2 = mInt;
                            i11 = i7;
                            break;
                        } else {
                            c = 1;
                        }
                    }
                    break;
                case 3:
                    iArr[i6] = i11;
                    i11++;
                    if (i11 < i4) {
                    }
                    break;
                case 4:
                    iArr[i6] = i11;
                    int i13 = i11 - i7;
                    if (i13 > 0) {
                        if (i13 <= 3) {
                            int i14 = i8 - 2;
                            bArr2[i14] = (byte) (bArr2[i14] | ((byte) i13));
                        } else if (i13 <= 18) {
                            bArr2[i8] = (byte) (i13 - 3);
                            i8++;
                        } else {
                            int i15 = i13 - 18;
                            int i16 = i8 + 1;
                            bArr2[i8] = 0;
                            while (i15 > 255) {
                                i15 -= 255;
                                bArr2[i16] = 0;
                                i16++;
                            }
                            i8 = i16 + 1;
                            bArr2[i16] = (byte) i15;
                        }
                        do {
                            bArr2[i8] = bArr[i7];
                            i13--;
                            i8++;
                            i7++;
                        } while (i13 > 0);
                    }
                    int i17 = i11 + 4;
                    if (bArr[i5 + 3] == bArr[i11 + 3]) {
                        int i18 = i11 + 5;
                        if (bArr[i5 + 4] == bArr[i17]) {
                            i17 = i11 + 6;
                            if (bArr[i5 + 5] == bArr[i18]) {
                                i18 = i11 + 7;
                                if (bArr[i5 + 6] == bArr[i17]) {
                                    i17 = i11 + 8;
                                    if (bArr[i5 + 7] == bArr[i18]) {
                                        i18 = i11 + 9;
                                        if (bArr[i5 + 8] == bArr[i17]) {
                                            int i19 = i5 + 9;
                                            i11 = i18;
                                            while (i11 < i && bArr[i19] == bArr[i11]) {
                                                i19++;
                                                i11++;
                                            }
                                            int i20 = i11 - i7;
                                            if (i9 <= 16384) {
                                                i9--;
                                                if (i20 <= 33) {
                                                    i3 = i8 + 1;
                                                    bArr2[i8] = (byte) ((i20 - 2) | 32);
                                                } else {
                                                    i10 = i20 - 33;
                                                    bArr2[i8] = 32;
                                                    i8++;
                                                    c = 5;
                                                }
                                            } else {
                                                i9 -= 16384;
                                                if (i20 <= 9) {
                                                    i3 = i8 + 1;
                                                    bArr2[i8] = (byte) (((i9 & 16384) >> 11) | 16 | (i20 - 2));
                                                } else {
                                                    i20 -= 9;
                                                    int i21 = i8 + 1;
                                                    bArr2[i8] = (byte) (((i9 & 16384) >> 11) | 16);
                                                    while (i20 > 255) {
                                                        i20 -= 255;
                                                        bArr2[i21] = 0;
                                                        i21++;
                                                    }
                                                    i3 = i21 + 1;
                                                    bArr2[i21] = (byte) i20;
                                                }
                                            }
                                            i10 = i20;
                                            bArr2[i3] = (byte) ((i9 & 63) << 2);
                                            i8 = i3 + 2;
                                            bArr2[i3 + 1] = (byte) (i9 >> 6);
                                            c = 7;
                                        }
                                    }
                                }
                            }
                        }
                        i17 = i18;
                    }
                    i11 = i17 - 1;
                    i10 = i11 - i7;
                    if (i9 <= 2048) {
                        i9--;
                        int i22 = i8 + 1;
                        bArr2[i8] = (byte) (((i10 - 1) << 5) | ((i9 & 7) << 2));
                        i8 += 2;
                        bArr2[i22] = (byte) (i9 >> 3);
                        c = 7;
                    } else {
                        if (i9 <= 16384) {
                            i9--;
                            i2 = i8 + 1;
                            bArr2[i8] = (byte) ((i10 - 2) | 32);
                        } else {
                            i9 -= 16384;
                            i2 = i8 + 1;
                            bArr2[i8] = (byte) (((i9 & 16384) >> 11) | 16 | (i10 - 2));
                        }
                        i8 = i2;
                        c = 6;
                    }
                case 5:
                    while (i10 > 255) {
                        i10 -= 255;
                        bArr2[i8] = 0;
                        i8++;
                    }
                    bArr2[i8] = (byte) i10;
                    i8++;
                    int i23 = i8 + 1;
                    bArr2[i8] = (byte) ((i9 & 63) << 2);
                    i8 += 2;
                    bArr2[i23] = (byte) (i9 >> 6);
                    if (i11 >= i4) {
                        mInt2 = mInt;
                        break;
                    } else {
                        i7 = i11;
                        c = 1;
                    }
                case 6:
                    int i232 = i8 + 1;
                    bArr2[i8] = (byte) ((i9 & 63) << 2);
                    i8 += 2;
                    bArr2[i232] = (byte) (i9 >> 6);
                    if (i11 >= i4) {
                    }
                    break;
                case 7:
                    if (i11 >= i4) {
                    }
                    break;
            }
        }
        mInt2.f949v = i8;
        return i - i11;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x005e A[LOOP:0: B:24:0x0050->B:27:0x005e, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0060 A[EDGE_INSN: B:30:0x0060->B:28:0x0060 BREAK  A[LOOP:0: B:24:0x0050->B:27:0x005e], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final int lzo1x_1_compress(byte[] bArr, int i, byte[] bArr2, MInt mInt, int[] iArr) {
        int i_lzo1x_1_do_compress;
        int i2;
        int i3;
        if (i <= 13) {
            i_lzo1x_1_do_compress = i;
            i2 = 0;
        } else {
            i_lzo1x_1_do_compress = _lzo1x_1_do_compress(bArr, i, bArr2, mInt, iArr);
            i2 = mInt.f949v;
        }
        if (i_lzo1x_1_do_compress > 0) {
            int i4 = i - i_lzo1x_1_do_compress;
            if (i2 != 0 || i_lzo1x_1_do_compress > 238) {
                if (i_lzo1x_1_do_compress <= 3) {
                    int i5 = i2 - 2;
                    bArr2[i5] = (byte) (bArr2[i5] | ((byte) i_lzo1x_1_do_compress));
                } else if (i_lzo1x_1_do_compress <= 18) {
                    i3 = i2 + 1;
                    bArr2[i2] = (byte) (i_lzo1x_1_do_compress - 3);
                } else {
                    int i6 = i_lzo1x_1_do_compress - 18;
                    int i7 = i2 + 1;
                    bArr2[i2] = 0;
                    while (i6 > 255) {
                        i6 -= 255;
                        bArr2[i7] = 0;
                        i7++;
                    }
                    i2 = i7 + 1;
                    bArr2[i7] = (byte) i6;
                }
                while (true) {
                    int i8 = i4 + 1;
                    bArr2[i2] = bArr[i4];
                    i_lzo1x_1_do_compress--;
                    i2++;
                    if (i_lzo1x_1_do_compress > 0) {
                        break;
                    }
                    i4 = i8;
                }
            } else {
                i3 = i2 + 1;
                bArr2[i2] = (byte) (i_lzo1x_1_do_compress + 17);
            }
            i2 = i3;
            while (true) {
                int i82 = i4 + 1;
                bArr2[i2] = bArr[i4];
                i_lzo1x_1_do_compress--;
                i2++;
                if (i_lzo1x_1_do_compress > 0) {
                }
                i4 = i82;
            }
        }
        bArr2[i2] = 17;
        bArr2[i2 + 1] = 0;
        bArr2[i2 + 2] = 0;
        mInt.f949v = i2 + 3;
        return 0;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:14:0x0040. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01b0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final int lzo1x_decompress(byte[] bArr, int i, byte[] bArr2, MInt mInt) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        boolean z;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        boolean z2;
        mInt.f949v = 0;
        int i12 = bArr[0] & 255;
        if (i12 > 17) {
            i12 -= 17;
            if (i12 < 4) {
                i2 = 0;
                i3 = 0;
                i5 = 0;
                i6 = 0;
                z = 6;
                i4 = 1;
            } else {
                int i13 = 0;
                int i14 = 1;
                while (true) {
                    i3 = i13 + 1;
                    i4 = i14 + 1;
                    bArr2[i13] = bArr[i14];
                    i12--;
                    if (i12 <= 0) {
                        break;
                    }
                    i13 = i3;
                    i14 = i4;
                }
                i2 = 0;
                i5 = 0;
                i6 = 0;
                z = 2;
            }
        } else {
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
            z = true;
        }
        while (true) {
            switch (z) {
                case true:
                    i7 = i4 + 1;
                    i8 = bArr[i4] & 255;
                    if (i8 < 16) {
                        if (i8 == 0) {
                            while (true) {
                                byte b = bArr[i7];
                                if (b == 0) {
                                    i8 += 255;
                                    i7++;
                                } else {
                                    i7++;
                                    i8 += (b & 255) + 15;
                                }
                            }
                        }
                        bArr2[i3] = bArr[i7];
                        bArr2[i3 + 1] = bArr[i7 + 1];
                        bArr2[i3 + 2] = bArr[i7 + 2];
                        bArr2[i3 + 3] = bArr[i7 + 3];
                        i3 += 4;
                        i4 = i7 + 4;
                        int i15 = i8 - 1;
                        if (i15 > 0) {
                            if (i15 >= 4) {
                                do {
                                    bArr2[i3] = bArr[i4];
                                    bArr2[i3 + 1] = bArr[i4 + 1];
                                    bArr2[i3 + 2] = bArr[i4 + 2];
                                    bArr2[i3 + 3] = bArr[i4 + 3];
                                    i3 += 4;
                                    i4 += 4;
                                    i15 -= 4;
                                } while (i15 >= 4);
                                if (i15 > 0) {
                                    while (true) {
                                        i9 = i3 + 1;
                                        i10 = i4 + 1;
                                        bArr2[i3] = bArr[i4];
                                        i15--;
                                        if (i15 > 0) {
                                            i3 = i9;
                                            i4 = i10;
                                        }
                                    }
                                }
                            } else {
                                while (true) {
                                    i9 = i3 + 1;
                                    i10 = i4 + 1;
                                    bArr2[i3] = bArr[i4];
                                    i15--;
                                    if (i15 > 0) {
                                        i3 = i9;
                                        i4 = i10;
                                    }
                                }
                            }
                            i3 = i9;
                            i4 = i10;
                        }
                    }
                    i4 = i7;
                    i12 = i8;
                    z = 3;
                case true:
                    i7 = i4 + 1;
                    i8 = bArr[i4] & 255;
                    if (i8 < 16) {
                        i4 += 2;
                        int i16 = ((i3 - 2049) - (i8 >> 2)) - ((bArr[i7] & 255) << 2);
                        int i17 = i16 - i3;
                        int iAbs = Math.abs(i17);
                        if (iAbs > i2) {
                            i2 = iAbs;
                        }
                        if (i17 < i5) {
                            i5 = i17;
                        }
                        int i18 = i16 + 1;
                        bArr2[i3] = bArr2[i16];
                        int i19 = i3 + 2;
                        int i20 = i16 + 2;
                        bArr2[i3 + 1] = bArr2[i18];
                        i3 += 3;
                        bArr2[i19] = bArr2[i20];
                        i6 = i20;
                        i12 = i8;
                        z = 5;
                    }
                    i4 = i7;
                    i12 = i8;
                    z = 3;
                case true:
                    z2 = false;
                    if (!z2) {
                        int i21 = i3 + 1;
                        int i22 = i6 + 1;
                        bArr2[i3] = bArr2[i6];
                        i3 += 2;
                        i6 += 2;
                        bArr2[i21] = bArr2[i22];
                        do {
                            bArr2[i3] = bArr2[i6];
                            i12--;
                            i3++;
                            i6++;
                        } while (i12 > 0);
                    }
                    i12 = bArr[i4 - 2] & 3;
                    if (i12 == 0) {
                        z = true;
                    }
                    int i23 = i3 + 1;
                    int i24 = i4 + 1;
                    bArr2[i3] = bArr[i4];
                    if (i12 <= 1) {
                        int i25 = i3 + 2;
                        int i26 = i4 + 2;
                        bArr2[i23] = bArr[i24];
                        if (i12 > 2) {
                            i3 += 3;
                            i24 = i4 + 3;
                            bArr2[i25] = bArr[i26];
                        } else {
                            i3 = i25;
                            i24 = i26;
                        }
                    } else {
                        i3 = i23;
                    }
                    i4 = i24 + 1;
                    i12 = bArr[i24] & 255;
                    z = 3;
                case true:
                    i12 = bArr[i4 - 2] & 3;
                    if (i12 == 0) {
                    }
                    break;
                case true:
                    int i232 = i3 + 1;
                    int i242 = i4 + 1;
                    bArr2[i3] = bArr[i4];
                    if (i12 <= 1) {
                    }
                    i4 = i242 + 1;
                    i12 = bArr[i242] & 255;
                    z = 3;
                    break;
            }
            if (i12 >= 64) {
                int i27 = i4 + 1;
                i6 = ((i3 - 1) - ((i12 >> 2) & 7)) - ((bArr[i4] & 255) << 3);
                int i28 = i6 - i3;
                int iAbs2 = Math.abs(i28);
                if (iAbs2 > i2) {
                    i2 = iAbs2;
                }
                if (i28 < i5) {
                    i5 = i28;
                }
                i12 = (i12 >> 5) - 1;
                i4 = i27;
                z = 4;
            } else {
                if (i12 >= 32) {
                    int i29 = i12 & 31;
                    if (i29 == 0) {
                        while (true) {
                            byte b2 = bArr[i4];
                            if (b2 == 0) {
                                i29 += 255;
                                i4++;
                            } else {
                                i4++;
                                i29 += (b2 & 255) + 31;
                            }
                        }
                    }
                    int i30 = (i3 - 1) - (((bArr[i4] & 255) + ((bArr[i4 + 1] & 255) << 8)) >> 2);
                    int i31 = i30 - i3;
                    int iAbs3 = Math.abs(i31);
                    if (iAbs3 > i2) {
                        i2 = iAbs3;
                    }
                    if (i31 < i5) {
                        i5 = i31;
                    }
                    i4 += 2;
                    i12 = i29;
                    i11 = i30;
                } else if (i12 >= 16) {
                    int i32 = i3 - ((i12 & 8) << 11);
                    int i33 = i32 - i3;
                    int iAbs4 = Math.abs(i33);
                    if (iAbs4 > i2) {
                        i2 = iAbs4;
                    }
                    if (i33 < i5) {
                        i5 = i33;
                    }
                    i12 &= 7;
                    if (i12 == 0) {
                        while (true) {
                            byte b3 = bArr[i4];
                            if (b3 == 0) {
                                i12 += 255;
                                i4++;
                            } else {
                                i4++;
                                i12 += (b3 & 255) + 7;
                            }
                        }
                    }
                    int i34 = i32 - (((bArr[i4] & 255) + ((bArr[i4 + 1] & 255) << 8)) >> 2);
                    int i35 = i34 - i3;
                    int iAbs5 = Math.abs(i35);
                    if (iAbs5 > i2) {
                        i2 = iAbs5;
                    }
                    if (i35 < i5) {
                        i5 = i35;
                    }
                    i4 += 2;
                    if (i34 == i3) {
                        mInt.f949v = i3;
                        if (i4 == bArr.length) {
                            return 0;
                        }
                        return i4 < bArr.length ? -8 : -4;
                    }
                    i11 = i34 - 16384;
                } else {
                    int i36 = i4 + 1;
                    int i37 = ((i3 - 1) - (i12 >> 2)) - ((bArr[i4] & 255) << 2);
                    int i38 = i37 - i3;
                    int iAbs6 = Math.abs(i38);
                    if (iAbs6 > i2) {
                        i2 = iAbs6;
                    }
                    if (i38 < i5) {
                        i5 = i38;
                    }
                    int i39 = i3 + 1;
                    i6 = i37 + 1;
                    bArr2[i3] = bArr2[i37];
                    i3 += 2;
                    bArr2[i39] = bArr2[i6];
                    i4 = i36;
                    z = 5;
                }
                if (i12 >= 6 && i3 - i11 >= 4) {
                    bArr2[i3] = bArr2[i11];
                    bArr2[i3 + 1] = bArr2[i11 + 1];
                    bArr2[i3 + 2] = bArr2[i11 + 2];
                    bArr2[i3 + 3] = bArr2[i11 + 3];
                    i3 += 4;
                    int i40 = i11 + 4;
                    i12 -= 2;
                    do {
                        bArr2[i3] = bArr2[i40];
                        bArr2[i3 + 1] = bArr2[i40 + 1];
                        bArr2[i3 + 2] = bArr2[i40 + 2];
                        bArr2[i3 + 3] = bArr2[i40 + 3];
                        i3 += 4;
                        i40 += 4;
                        i12 -= 4;
                    } while (i12 >= 4);
                    if (i12 > 0) {
                        while (true) {
                            i6 = i40 + 1;
                            bArr2[i3] = bArr2[i40];
                            i12--;
                            i3++;
                            if (i12 > 0) {
                                i40 = i6;
                            }
                        }
                    } else {
                        i6 = i40;
                    }
                    z2 = true;
                    if (!z2) {
                    }
                    i12 = bArr[i4 - 2] & 3;
                    if (i12 == 0) {
                    }
                    int i2322 = i3 + 1;
                    int i2422 = i4 + 1;
                    bArr2[i3] = bArr[i4];
                    if (i12 <= 1) {
                    }
                    i4 = i2422 + 1;
                    i12 = bArr[i2422] & 255;
                    z = 3;
                } else {
                    i6 = i11;
                    z2 = false;
                    if (!z2) {
                    }
                    i12 = bArr[i4 - 2] & 3;
                    if (i12 == 0) {
                    }
                    int i23222 = i3 + 1;
                    int i24222 = i4 + 1;
                    bArr2[i3] = bArr[i4];
                    if (i12 <= 1) {
                    }
                    i4 = i24222 + 1;
                    i12 = bArr[i24222] & 255;
                    z = 3;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:138:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x01ab A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:215:0x01b3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01af A[FALL_THROUGH, PHI: r4 r13 r14 r16
      0x01af: PHI (r4v30 int) = (r4v3 int), (r4v34 int) binds: [B:24:0x0058, B:93:0x01a9] A[DONT_GENERATE, DONT_INLINE]
      0x01af: PHI (r13v15 int) = (r13v2 int), (r13v21 int) binds: [B:24:0x0058, B:93:0x01a9] A[DONT_GENERATE, DONT_INLINE]
      0x01af: PHI (r14v20 int) = (r14v2 int), (r14v23 int) binds: [B:24:0x0058, B:93:0x01a9] A[DONT_GENERATE, DONT_INLINE]
      0x01af: PHI (r16v8 int) = (r16v1 int), (r16v10 int) binds: [B:24:0x0058, B:93:0x01a9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01af A[PHI: r4 r13 r14 r16
      0x01af: PHI (r4v30 int) = (r4v3 int), (r4v34 int) binds: [B:24:0x0058, B:93:0x01a9] A[DONT_GENERATE, DONT_INLINE]
      0x01af: PHI (r13v15 int) = (r13v2 int), (r13v21 int) binds: [B:24:0x0058, B:93:0x01a9] A[DONT_GENERATE, DONT_INLINE]
      0x01af: PHI (r14v20 int) = (r14v2 int), (r14v23 int) binds: [B:24:0x0058, B:93:0x01a9] A[DONT_GENERATE, DONT_INLINE]
      0x01af: PHI (r16v8 int) = (r16v1 int), (r16v10 int) binds: [B:24:0x0058, B:93:0x01a9] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final int lzo1x_decompress_safe(byte[] bArr, int i, byte[] bArr2, MInt mInt) {
        int i2;
        int i3;
        char c;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        boolean z;
        mInt.f949v = 0;
        int i10 = bArr[0] & 255;
        if (i10 > 17) {
            int i11 = i10 - 17;
            if (i11 < 4) {
                i2 = 0;
                i10 = i11;
                c = 6;
            } else if (bArr2.length < i11) {
                i2 = 0;
                i10 = i11;
                i3 = 1;
                c = '\t';
            } else if (bArr.length < i10 - 16) {
                i2 = 0;
                i10 = i11;
                c = '\b';
            } else {
                int i12 = 0;
                int i13 = 1;
                while (true) {
                    i2 = i12 + 1;
                    i3 = i13 + 1;
                    bArr2[i12] = bArr[i13];
                    i11--;
                    if (i11 <= 0) {
                        break;
                    }
                    i12 = i2;
                    i13 = i3;
                }
                i10 = i11;
                c = 2;
            }
            i3 = 1;
        } else {
            i2 = 0;
            i3 = 0;
            c = 1;
        }
        int length = bArr.length;
        int length2 = bArr2.length;
        int i14 = 0;
        while (true) {
            if (i3 < length) {
                switch (c) {
                    case 1:
                        i4 = i3 + 1;
                        i5 = bArr[i3] & 255;
                        if (i5 >= 16) {
                            i3 = i4;
                            i10 = i5;
                            c = 3;
                        } else if (i5 != 0) {
                            i3 = i4;
                            i10 = i5;
                            if (length2 - i2 < i10 + 3) {
                                c = '\t';
                            } else if (length - i3 < i10 + 4) {
                                c = '\b';
                            } else {
                                bArr2[i2] = bArr[i3];
                                bArr2[i2 + 1] = bArr[i3 + 1];
                                bArr2[i2 + 2] = bArr[i3 + 2];
                                bArr2[i2 + 3] = bArr[i3 + 3];
                                i2 += 4;
                                i3 += 4;
                                int i15 = i10 - 1;
                                if (i15 > 0) {
                                    if (i15 >= 4) {
                                        do {
                                            bArr2[i2] = bArr[i3];
                                            bArr2[i2 + 1] = bArr[i3 + 1];
                                            bArr2[i2 + 2] = bArr[i3 + 2];
                                            bArr2[i2 + 3] = bArr[i3 + 3];
                                            i2 += 4;
                                            i3 += 4;
                                        } while (i15 >= 4);
                                        if (i15 > 0) {
                                            while (true) {
                                                i6 = i2 + 1;
                                                i7 = i3 + 1;
                                                bArr2[i2] = bArr[i3];
                                                i15--;
                                                if (i15 > 0) {
                                                    i2 = i6;
                                                    i3 = i7;
                                                }
                                            }
                                        }
                                    } else {
                                        while (true) {
                                            i6 = i2 + 1;
                                            i7 = i3 + 1;
                                            bArr2[i2] = bArr[i3];
                                            i15--;
                                            if (i15 > 0) {
                                                i2 = i6;
                                                i3 = i7;
                                            }
                                        }
                                    }
                                    i2 = i6;
                                    i3 = i7;
                                }
                            }
                        } else {
                            if (length - i4 >= 1) {
                                do {
                                    byte b = bArr[i4];
                                    if (b == 0) {
                                        i5 += 255;
                                        i4++;
                                    } else {
                                        i4++;
                                        i5 += (b & 255) + 15;
                                        i3 = i4;
                                        i10 = i5;
                                        if (length2 - i2 < i10 + 3) {
                                        }
                                    }
                                } while (length - i4 >= 1);
                            }
                            i3 = i4;
                            i10 = i5;
                            c = '\b';
                        }
                    case 2:
                        i4 = i3 + 1;
                        i5 = bArr[i3] & 255;
                        if (i5 >= 16) {
                            i3 = i4;
                            i10 = i5;
                            c = 3;
                        } else {
                            i3 += 2;
                            int i16 = ((i2 - 2049) - (i5 >> 2)) - ((bArr[i4] & 255) << 2);
                            if (i16 < 0 || i16 >= i2) {
                                i14 = i16;
                                i10 = i5;
                                c = '\n';
                            } else if (length2 - i2 < 3) {
                                i14 = i16;
                                i10 = i5;
                                c = '\t';
                            } else {
                                int i17 = i16 + 1;
                                bArr2[i2] = bArr2[i16];
                                int i18 = i2 + 2;
                                int i19 = i16 + 2;
                                bArr2[i2 + 1] = bArr2[i17];
                                i2 += 3;
                                bArr2[i18] = bArr2[i19];
                                i14 = i19;
                                i10 = i5;
                                c = 5;
                            }
                        }
                        break;
                    case 3:
                        if (i10 >= 64) {
                            int i20 = i3 + 1;
                            int i21 = ((i2 - 1) - ((i10 >> 2) & 7)) - ((bArr[i3] & 255) << 3);
                            int i22 = i10 >> 5;
                            int i23 = i22 - 1;
                            if (i21 < 0 || i21 >= i2) {
                                i14 = i21;
                                i3 = i20;
                                i10 = i23;
                                c = '\n';
                            } else if (length2 - i2 < i22 + 1) {
                                i14 = i21;
                                i3 = i20;
                                i10 = i23;
                                c = '\t';
                            } else {
                                i14 = i21;
                                i3 = i20;
                                i10 = i23;
                                c = 5;
                            }
                        } else if (i10 >= 32) {
                            i10 &= 31;
                            if (i10 == 0) {
                                if (length - i3 >= 1) {
                                    do {
                                        byte b2 = bArr[i3];
                                        if (b2 == 0) {
                                            i10 += 255;
                                            i3++;
                                        } else {
                                            i3++;
                                            i10 += (b2 & 255) + 31;
                                        }
                                    } while (length - i3 >= 1);
                                }
                                c = '\b';
                            }
                            i9 = (i2 - 1) - (((bArr[i3] & 255) + ((bArr[i3 + 1] & 255) << 8)) >> 2);
                            i3 += 2;
                            if (i9 >= 0 || i9 >= i2) {
                                i14 = i9;
                                c = '\n';
                            } else if (length2 - i2 < i10 + 2) {
                                i14 = i9;
                                c = '\t';
                            } else if (i10 < 6 || i2 - i9 < 4) {
                                i14 = i9;
                            } else {
                                bArr2[i2] = bArr2[i9];
                                bArr2[i2 + 1] = bArr2[i9 + 1];
                                bArr2[i2 + 2] = bArr2[i9 + 2];
                                bArr2[i2 + 3] = bArr2[i9 + 3];
                                i2 += 4;
                                int i24 = i9 + 4;
                                i10 -= 2;
                                do {
                                    bArr2[i2] = bArr2[i24];
                                    bArr2[i2 + 1] = bArr2[i24 + 1];
                                    bArr2[i2 + 2] = bArr2[i24 + 2];
                                    bArr2[i2 + 3] = bArr2[i24 + 3];
                                    i2 += 4;
                                    i24 += 4;
                                    i10 -= 4;
                                } while (i10 >= 4);
                                if (i10 > 0) {
                                    while (true) {
                                        int i25 = i24 + 1;
                                        bArr2[i2] = bArr2[i24];
                                        i10--;
                                        i2++;
                                        if (i10 <= 0) {
                                            i14 = i25;
                                        } else {
                                            i24 = i25;
                                        }
                                    }
                                } else {
                                    i14 = i24;
                                }
                                z = true;
                                if (!z) {
                                    int i26 = i2 + 1;
                                    int i27 = i14 + 1;
                                    bArr2[i2] = bArr2[i14];
                                    i2 += 2;
                                    i14 += 2;
                                    bArr2[i26] = bArr2[i27];
                                    do {
                                        bArr2[i2] = bArr2[i14];
                                        i10--;
                                        i2++;
                                        i14++;
                                    } while (i10 > 0);
                                }
                                i8 = bArr[i3 - 2] & 3;
                                i10 = i8;
                                if (i8 != 0) {
                                    if (length2 - i2 < i10) {
                                        c = '\t';
                                    } else if (length - i3 < i10 + 1) {
                                        c = '\b';
                                    } else {
                                        int i28 = i2 + 1;
                                        int i29 = i3 + 1;
                                        bArr2[i2] = bArr[i3];
                                        if (i10 > 1) {
                                            int i30 = i2 + 2;
                                            int i31 = i3 + 2;
                                            bArr2[i28] = bArr[i29];
                                            if (i10 > 2) {
                                                i2 += 3;
                                                i29 = i3 + 3;
                                                bArr2[i30] = bArr[i31];
                                            } else {
                                                i2 = i30;
                                                i29 = i31;
                                            }
                                        } else {
                                            i2 = i28;
                                        }
                                        i3 = i29 + 1;
                                        i10 = bArr[i29] & 255;
                                        if (i3 < length) {
                                            c = 3;
                                        }
                                    }
                                }
                                c = 1;
                            }
                        } else if (i10 >= 16) {
                            i14 = i2 - ((i10 & 8) << 11);
                            i10 &= 7;
                            if (i10 == 0) {
                                if (length - i3 >= 1) {
                                    do {
                                        byte b3 = bArr[i3];
                                        if (b3 == 0) {
                                            i10 += 255;
                                            i3++;
                                        } else {
                                            i3++;
                                            i10 += (b3 & 255) + 7;
                                        }
                                    } while (length - i3 >= 1);
                                }
                                c = '\b';
                            }
                            int i32 = i14 - (((bArr[i3] & 255) + ((bArr[i3 + 1] & 255) << 8)) >> 2);
                            i3 += 2;
                            if (i32 == i2) {
                                c = 7;
                                break;
                            } else {
                                i9 = i32 - 16384;
                                if (i9 >= 0) {
                                }
                                i14 = i9;
                                c = '\n';
                            }
                        } else {
                            int i33 = i3 + 1;
                            int i34 = ((i2 - 1) - (i10 >> 2)) - ((bArr[i3] & 255) << 2);
                            if (i34 < 0 || i34 >= i2) {
                                i14 = i34;
                                i3 = i33;
                                c = '\n';
                            } else if (length2 - i2 < 2) {
                                i14 = i34;
                                i3 = i33;
                                c = '\t';
                            } else {
                                int i35 = i2 + 1;
                                i14 = i34 + 1;
                                bArr2[i2] = bArr2[i34];
                                i2 += 2;
                                bArr2[i35] = bArr2[i14];
                                i3 = i33;
                                c = 5;
                            }
                        }
                        break;
                    case 4:
                        z = false;
                        if (!z) {
                        }
                        i8 = bArr[i3 - 2] & 3;
                        i10 = i8;
                        if (i8 != 0) {
                        }
                        c = 1;
                        break;
                    case 5:
                        i8 = bArr[i3 - 2] & 3;
                        i10 = i8;
                        if (i8 != 0) {
                        }
                        c = 1;
                        break;
                }
            }
        }
        mInt.f949v = i2;
        switch (c) {
            case 7:
                if (i3 == length) {
                    return 0;
                }
                return i3 < length ? -8 : -4;
            case '\b':
                return -4;
            case '\t':
                return -5;
            case '\n':
                return -6;
            default:
                return -7;
        }
    }
}
