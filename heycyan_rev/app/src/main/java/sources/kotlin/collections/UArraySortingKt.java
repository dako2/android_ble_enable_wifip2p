package kotlin.collections;

import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UArraySorting.kt */
@Metadata(m606d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0010\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\t\u0010\n\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\f\u0010\r\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u000f\u0010\u0010\u001a'\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0013\u0010\u0014\u001a'\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0015\u0010\u0016\u001a'\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0017\u0010\u0018\u001a'\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0019\u0010\u001a\u001a'\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001¢\u0006\u0004\b\u001e\u0010\u0014\u001a'\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001¢\u0006\u0004\b\u001f\u0010\u0016\u001a'\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001¢\u0006\u0004\b \u0010\u0018\u001a'\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001¢\u0006\u0004\b!\u0010\u001a¨\u0006\""}, m607d2 = {"partition", "", "array", "Lkotlin/UByteArray;", "left", "right", "partition-4UcCI2c", "([BII)I", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "quickSort-oBK06Vg", "([III)V", "quickSort--nroSd4", "([JII)V", "quickSort-Aa5vz7o", "([SII)V", "sortArray", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray-oBK06Vg", "sortArray--nroSd4", "sortArray-Aa5vz7o", "kotlin-stdlib"}, m608k = 2, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes3.dex */
public final class UArraySortingKt {
    /* renamed from: partition-4UcCI2c, reason: not valid java name */
    private static final int m1359partition4UcCI2c(byte[] bArr, int i, int i2) {
        int i3;
        byte bM979getw2LRezQ = UByteArray.m979getw2LRezQ(bArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                i3 = bM979getw2LRezQ & 255;
                if (Intrinsics.compare(UByteArray.m979getw2LRezQ(bArr, i) & 255, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UByteArray.m979getw2LRezQ(bArr, i2) & 255, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                byte bM979getw2LRezQ2 = UByteArray.m979getw2LRezQ(bArr, i);
                UByteArray.m984setVurrAj0(bArr, i, UByteArray.m979getw2LRezQ(bArr, i2));
                UByteArray.m984setVurrAj0(bArr, i2, bM979getw2LRezQ2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-4UcCI2c, reason: not valid java name */
    private static final void m1363quickSort4UcCI2c(byte[] bArr, int i, int i2) {
        int iM1359partition4UcCI2c = m1359partition4UcCI2c(bArr, i, i2);
        int i3 = iM1359partition4UcCI2c - 1;
        if (i < i3) {
            m1363quickSort4UcCI2c(bArr, i, i3);
        }
        if (iM1359partition4UcCI2c < i2) {
            m1363quickSort4UcCI2c(bArr, iM1359partition4UcCI2c, i2);
        }
    }

    /* renamed from: partition-Aa5vz7o, reason: not valid java name */
    private static final int m1360partitionAa5vz7o(short[] sArr, int i, int i2) {
        int i3;
        short sM1242getMh2AYeg = UShortArray.m1242getMh2AYeg(sArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                int iM1242getMh2AYeg = UShortArray.m1242getMh2AYeg(sArr, i) & UShort.MAX_VALUE;
                i3 = sM1242getMh2AYeg & UShort.MAX_VALUE;
                if (Intrinsics.compare(iM1242getMh2AYeg, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UShortArray.m1242getMh2AYeg(sArr, i2) & UShort.MAX_VALUE, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                short sM1242getMh2AYeg2 = UShortArray.m1242getMh2AYeg(sArr, i);
                UShortArray.m1247set01HTLdE(sArr, i, UShortArray.m1242getMh2AYeg(sArr, i2));
                UShortArray.m1247set01HTLdE(sArr, i2, sM1242getMh2AYeg2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-Aa5vz7o, reason: not valid java name */
    private static final void m1364quickSortAa5vz7o(short[] sArr, int i, int i2) {
        int iM1360partitionAa5vz7o = m1360partitionAa5vz7o(sArr, i, i2);
        int i3 = iM1360partitionAa5vz7o - 1;
        if (i < i3) {
            m1364quickSortAa5vz7o(sArr, i, i3);
        }
        if (iM1360partitionAa5vz7o < i2) {
            m1364quickSortAa5vz7o(sArr, iM1360partitionAa5vz7o, i2);
        }
    }

    /* renamed from: partition-oBK06Vg, reason: not valid java name */
    private static final int m1361partitionoBK06Vg(int[] iArr, int i, int i2) {
        int iM1058getpVg5ArA = UIntArray.m1058getpVg5ArA(iArr, (i + i2) / 2);
        while (i <= i2) {
            while (Integer.compare(UIntArray.m1058getpVg5ArA(iArr, i) ^ Integer.MIN_VALUE, iM1058getpVg5ArA ^ Integer.MIN_VALUE) < 0) {
                i++;
            }
            while (Integer.compare(UIntArray.m1058getpVg5ArA(iArr, i2) ^ Integer.MIN_VALUE, iM1058getpVg5ArA ^ Integer.MIN_VALUE) > 0) {
                i2--;
            }
            if (i <= i2) {
                int iM1058getpVg5ArA2 = UIntArray.m1058getpVg5ArA(iArr, i);
                UIntArray.m1063setVXSXFK8(iArr, i, UIntArray.m1058getpVg5ArA(iArr, i2));
                UIntArray.m1063setVXSXFK8(iArr, i2, iM1058getpVg5ArA2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-oBK06Vg, reason: not valid java name */
    private static final void m1365quickSortoBK06Vg(int[] iArr, int i, int i2) {
        int iM1361partitionoBK06Vg = m1361partitionoBK06Vg(iArr, i, i2);
        int i3 = iM1361partitionoBK06Vg - 1;
        if (i < i3) {
            m1365quickSortoBK06Vg(iArr, i, i3);
        }
        if (iM1361partitionoBK06Vg < i2) {
            m1365quickSortoBK06Vg(iArr, iM1361partitionoBK06Vg, i2);
        }
    }

    /* renamed from: partition--nroSd4, reason: not valid java name */
    private static final int m1358partitionnroSd4(long[] jArr, int i, int i2) {
        long jM1137getsVKNKU = ULongArray.m1137getsVKNKU(jArr, (i + i2) / 2);
        while (i <= i2) {
            while (Long.compare(ULongArray.m1137getsVKNKU(jArr, i) ^ Long.MIN_VALUE, jM1137getsVKNKU ^ Long.MIN_VALUE) < 0) {
                i++;
            }
            while (Long.compare(ULongArray.m1137getsVKNKU(jArr, i2) ^ Long.MIN_VALUE, jM1137getsVKNKU ^ Long.MIN_VALUE) > 0) {
                i2--;
            }
            if (i <= i2) {
                long jM1137getsVKNKU2 = ULongArray.m1137getsVKNKU(jArr, i);
                ULongArray.m1142setk8EXiF4(jArr, i, ULongArray.m1137getsVKNKU(jArr, i2));
                ULongArray.m1142setk8EXiF4(jArr, i2, jM1137getsVKNKU2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort--nroSd4, reason: not valid java name */
    private static final void m1362quickSortnroSd4(long[] jArr, int i, int i2) {
        int iM1358partitionnroSd4 = m1358partitionnroSd4(jArr, i, i2);
        int i3 = iM1358partitionnroSd4 - 1;
        if (i < i3) {
            m1362quickSortnroSd4(jArr, i, i3);
        }
        if (iM1358partitionnroSd4 < i2) {
            m1362quickSortnroSd4(jArr, iM1358partitionnroSd4, i2);
        }
    }

    /* renamed from: sortArray-4UcCI2c, reason: not valid java name */
    public static final void m1367sortArray4UcCI2c(byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m1363quickSort4UcCI2c(array, i, i2 - 1);
    }

    /* renamed from: sortArray-Aa5vz7o, reason: not valid java name */
    public static final void m1368sortArrayAa5vz7o(short[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m1364quickSortAa5vz7o(array, i, i2 - 1);
    }

    /* renamed from: sortArray-oBK06Vg, reason: not valid java name */
    public static final void m1369sortArrayoBK06Vg(int[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m1365quickSortoBK06Vg(array, i, i2 - 1);
    }

    /* renamed from: sortArray--nroSd4, reason: not valid java name */
    public static final void m1366sortArraynroSd4(long[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m1362quickSortnroSd4(array, i, i2 - 1);
    }
}
