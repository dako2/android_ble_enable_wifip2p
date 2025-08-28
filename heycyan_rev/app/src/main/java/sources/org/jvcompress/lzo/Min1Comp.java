package org.jvcompress.lzo;

import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.FileUtils;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
import org.jvcompress.util.MInt;

/* loaded from: classes3.dex */
public class Min1Comp {
    private static final int IN_LEN = 1048576;
    private static final int OUT_LEN = 2097152;

    private static void clearDict(int[] iArr) {
        Arrays.fill(iArr, 0);
    }

    /* renamed from: R */
    private static String m651R(long j, long j2, long j3) {
        long j4 = j + 1;
        return ", millis:" + j4 + ", MB/sec:" + (((1000 * j2) / j4) / 1000000) + ", ratio:" + (((j3 + 1) * 100) / j2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x036f, code lost:
    
        throw new java.lang.AssertionError(r10 + r5 + r0 + ")  Decompreesed values not matching to Zero @:" + r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x039b, code lost:
    
        if (r1 == null) goto L90;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void main(String[] strArr) throws Throwable {
        BufferedInputStream bufferedInputStream;
        String str = ". Dict-File Data(";
        System.out.println("Usage: java [-DDICT=c:/words.txt] org.jvcompress.lzo.Min1Comp");
        int[] iArr = new int[131072];
        byte[] bArr = new byte[1048576];
        byte[] bArr2 = new byte[2097152];
        MInt mInt = new MInt();
        int i = 0;
        while (true) {
            int i2 = 10;
            if (i < 10) {
                clearDict(iArr);
                long jCurrentTimeMillis = System.currentTimeMillis();
                int iLzo1x_1_compress = MiniLZO.lzo1x_1_compress(bArr, 1048576, bArr2, mInt, iArr);
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                System.out.println(i + ". Zero Data Compress ret=" + iLzo1x_1_compress + ", out_lenth:" + mInt.f949v + ",comp-millis:" + m651R(jCurrentTimeMillis2 - jCurrentTimeMillis, FileUtils.ONE_MB, mInt.f949v) + ", Zero Data Decompressing ...");
                MInt mInt2 = new MInt();
                MiniLZO.lzo1x_decompress(bArr2, mInt.f949v, bArr, mInt2);
                System.out.println(i + ". Zero Data Got decompressed length:" + mInt2.f949v + ", Zero Data checking ... millis:" + m651R(System.currentTimeMillis() - jCurrentTimeMillis2, mInt2.f949v, mInt.f949v));
                for (int i3 = 0; i3 < mInt2.f949v; i3++) {
                    if (bArr[i3] != 0) {
                        throw new AssertionError(i + ". Zero Data Decompreesed values not matching to Zero @:" + i3);
                    }
                }
                i++;
            } else {
                Random random = new Random();
                byte[] bArr3 = new byte[1048576];
                int i4 = 7;
                int[] iArr2 = {16, 32, 64, 128, EMachine.EM_CLOUDSHIELD, 224, 256};
                int i5 = 0;
                while (true) {
                    String str2 = ")  Decompreesed values not matching to Zero @:";
                    String str3 = ")Got decompressed length:";
                    String str4 = ", outL:";
                    String str5 = ") Compress ret=";
                    String str6 = ", millis:";
                    if (i5 < i4) {
                        int i6 = iArr2[i5];
                        int i7 = 0;
                        while (i7 < i2) {
                            clearDict(iArr);
                            boolean z = i7 >= 5;
                            fillPartillyRandom(i6, bArr3, random, z);
                            Random random2 = random;
                            int[] iArr3 = iArr2;
                            System.arraycopy(bArr3, 0, bArr, 0, 1048576);
                            long jCurrentTimeMillis3 = System.currentTimeMillis();
                            MInt mInt3 = new MInt();
                            String str7 = str;
                            int iLzo1x_1_compress2 = MiniLZO.lzo1x_1_compress(bArr, 1048576, bArr2, mInt3, iArr);
                            long jCurrentTimeMillis4 = System.currentTimeMillis();
                            int[] iArr4 = iArr;
                            int i8 = i5;
                            String str8 = str2;
                            long j = jCurrentTimeMillis4 - jCurrentTimeMillis3;
                            String str9 = str6;
                            System.out.println(i7 + ". Random Data(" + i6 + "/repeatPattern:" + z + str5 + iLzo1x_1_compress2 + str4 + mInt3.f949v + str6 + m651R(j, FileUtils.ONE_MB, mInt3.f949v));
                            MInt mInt4 = new MInt();
                            MiniLZO.lzo1x_decompress(bArr2, mInt3.f949v, bArr, mInt4);
                            String str10 = str4;
                            String str11 = str3;
                            String str12 = str5;
                            System.out.println(i7 + ". Random Data(" + i6 + "/repeatPattern:" + z + str3 + mInt4.f949v + ",millis:" + m651R(System.currentTimeMillis() - jCurrentTimeMillis4, mInt4.f949v, mInt3.f949v));
                            for (int i9 = 0; i9 < mInt4.f949v; i9++) {
                                if (bArr[i9] != bArr3[i9]) {
                                    throw new AssertionError(i7 + ". Random Data(" + i6 + "/repeatPattern:" + z + str8 + i9);
                                }
                            }
                            i7++;
                            i2 = 10;
                            str = str7;
                            str2 = str8;
                            random = random2;
                            iArr2 = iArr3;
                            str6 = str9;
                            str4 = str10;
                            str5 = str12;
                            iArr = iArr4;
                            i5 = i8;
                            str3 = str11;
                        }
                        i5++;
                        i2 = 10;
                        i4 = 7;
                    } else {
                        String str13 = str;
                        int[] iArr5 = iArr;
                        String str14 = ", millis:";
                        String str15 = ")Got decompressed length:";
                        String str16 = ", outL:";
                        String str17 = ") Compress ret=";
                        BufferedInputStream bufferedInputStream2 = null;
                        BufferedInputStream bufferedInputStream3 = null;
                        bufferedInputStream2 = null;
                        bufferedInputStream2 = null;
                        try {
                            try {
                                try {
                                    BufferedInputStream bufferedInputStream4 = new BufferedInputStream(new FileInputStream(System.getProperty("DICT", "c:/words.txt")));
                                    loop5: while (true) {
                                        try {
                                            int i10 = bufferedInputStream4.read(bArr3);
                                            if (i10 < 0) {
                                                bufferedInputStream4.close();
                                                bufferedInputStream2 = bufferedInputStream3;
                                                break;
                                            }
                                            ?? r1 = 10;
                                            int i11 = 0;
                                            while (i11 < r1) {
                                                clearDict(iArr5);
                                                System.arraycopy(bArr3, 0, bArr, 0, i10);
                                                MInt mInt5 = new MInt();
                                                long jCurrentTimeMillis5 = System.currentTimeMillis();
                                                int[] iArr6 = iArr5;
                                                int iLzo1x_1_compress3 = MiniLZO.lzo1x_1_compress(bArr, i10, bArr2, mInt5, iArr6);
                                                long jCurrentTimeMillis6 = System.currentTimeMillis();
                                                PrintStream printStream = System.out;
                                                bufferedInputStream = bufferedInputStream4;
                                                String str18 = str13;
                                                try {
                                                    iArr5 = iArr6;
                                                    String str19 = str17;
                                                    StringBuilder sbAppend = new StringBuilder().append(i11).append(str18).append(i10).append(str19).append(iLzo1x_1_compress3);
                                                    String str20 = str16;
                                                    str17 = str19;
                                                    String str21 = str14;
                                                    str16 = str20;
                                                    printStream.println(sbAppend.append(str20).append(mInt5.f949v).append(str21).append(m651R(jCurrentTimeMillis6 - jCurrentTimeMillis5, i10, mInt5.f949v)).toString());
                                                    MInt mInt6 = new MInt();
                                                    MiniLZO.lzo1x_decompress(bArr2, mInt5.f949v, bArr, mInt6);
                                                    String str22 = str15;
                                                    str14 = str21;
                                                    System.out.println(i11 + str18 + i10 + str22 + mInt6.f949v + str21 + m651R(System.currentTimeMillis() - jCurrentTimeMillis6, mInt6.f949v, mInt5.f949v));
                                                    if (mInt6.f949v != i10) {
                                                        System.err.println("Dict-File Decompressed length does not match");
                                                    }
                                                    int i12 = 0;
                                                    while (i12 < mInt6.f949v) {
                                                        if (bArr[i12] != bArr3[i12]) {
                                                            break loop5;
                                                        } else {
                                                            i12++;
                                                        }
                                                    }
                                                    i11++;
                                                    str13 = str18;
                                                    str15 = str22;
                                                    bufferedInputStream4 = bufferedInputStream;
                                                    r1 = 10;
                                                } catch (FileNotFoundException unused) {
                                                    bufferedInputStream2 = bufferedInputStream;
                                                    if (bufferedInputStream2 == null) {
                                                        return;
                                                    }
                                                    bufferedInputStream2.close();
                                                    bufferedInputStream2 = bufferedInputStream2;
                                                    return;
                                                } catch (Exception e) {
                                                    e = e;
                                                    bufferedInputStream2 = bufferedInputStream;
                                                    e.printStackTrace();
                                                } catch (Throwable th) {
                                                    th = th;
                                                    bufferedInputStream2 = bufferedInputStream;
                                                    if (bufferedInputStream2 != null) {
                                                        try {
                                                            bufferedInputStream2.close();
                                                        } catch (IOException unused2) {
                                                        }
                                                    }
                                                    throw th;
                                                }
                                            }
                                            bufferedInputStream3 = r1;
                                        } catch (FileNotFoundException unused3) {
                                            bufferedInputStream = bufferedInputStream4;
                                        } catch (Exception e2) {
                                            e = e2;
                                            bufferedInputStream = bufferedInputStream4;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            bufferedInputStream = bufferedInputStream4;
                                        }
                                    }
                                } catch (IOException unused4) {
                                    return;
                                }
                            } catch (FileNotFoundException unused5) {
                            } catch (Exception e3) {
                                e = e3;
                            }
                            return;
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    }
                }
            }
        }
    }

    private static void fillPartillyRandom(int i, byte[] bArr, Random random, boolean z) {
        int i2 = 0;
        do {
            int iNextInt = z ? random.nextInt(10) : 1;
            byte bNextInt = (byte) random.nextInt(i);
            int i3 = 0;
            while (i3 < iNextInt && i2 < bArr.length) {
                bArr[i2] = bNextInt;
                i3++;
                i2++;
            }
        } while (i2 < bArr.length);
    }
}
