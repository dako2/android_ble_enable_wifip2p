package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.hash;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Preconditions;

/* loaded from: classes2.dex */
final class FarmHashFingerprint64 extends AbstractNonStreamingHashFunction {
    static final HashFunction FARMHASH_FINGERPRINT_64 = new FarmHashFingerprint64();

    /* renamed from: K0 */
    private static final long f310K0 = -4348849565147123417L;

    /* renamed from: K1 */
    private static final long f311K1 = -5435081209227447693L;

    /* renamed from: K2 */
    private static final long f312K2 = -7286425919675154353L;

    private static long hashLength16(long j, long j2, long j3) {
        long j4 = (j ^ j2) * j3;
        long j5 = ((j4 ^ (j4 >>> 47)) ^ j2) * j3;
        return (j5 ^ (j5 >>> 47)) * j3;
    }

    private static long shiftMix(long j) {
        return j ^ (j >>> 47);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.hash.HashFunction
    public int bits() {
        return 64;
    }

    FarmHashFingerprint64() {
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.hash.AbstractNonStreamingHashFunction, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.hash.AbstractHashFunction, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.hash.HashFunction
    public HashCode hashBytes(byte[] bArr, int i, int i2) {
        Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
        return HashCode.fromLong(fingerprint(bArr, i, i2));
    }

    public String toString() {
        return "Hashing.farmHashFingerprint64()";
    }

    static long fingerprint(byte[] bArr, int i, int i2) {
        if (i2 <= 32) {
            if (i2 <= 16) {
                return hashLength0to16(bArr, i, i2);
            }
            return hashLength17to32(bArr, i, i2);
        }
        if (i2 <= 64) {
            return hashLength33To64(bArr, i, i2);
        }
        return hashLength65Plus(bArr, i, i2);
    }

    private static void weakHashLength32WithSeeds(byte[] bArr, int i, long j, long j2, long[] jArr) {
        long jLoad64 = LittleEndianByteArray.load64(bArr, i);
        long jLoad642 = LittleEndianByteArray.load64(bArr, i + 8);
        long jLoad643 = LittleEndianByteArray.load64(bArr, i + 16);
        long jLoad644 = LittleEndianByteArray.load64(bArr, i + 24);
        long j3 = j + jLoad64;
        long j4 = jLoad642 + j3 + jLoad643;
        long jRotateRight = Long.rotateRight(j2 + j3 + jLoad644, 21) + Long.rotateRight(j4, 44);
        jArr[0] = j4 + jLoad644;
        jArr[1] = jRotateRight + j3;
    }

    private static long hashLength0to16(byte[] bArr, int i, int i2) {
        if (i2 >= 8) {
            long j = (i2 * 2) + f312K2;
            long jLoad64 = LittleEndianByteArray.load64(bArr, i) + f312K2;
            long jLoad642 = LittleEndianByteArray.load64(bArr, (i + i2) - 8);
            return hashLength16((Long.rotateRight(jLoad642, 37) * j) + jLoad64, (Long.rotateRight(jLoad64, 25) + jLoad642) * j, j);
        }
        if (i2 >= 4) {
            return hashLength16(i2 + ((LittleEndianByteArray.load32(bArr, i) & 4294967295L) << 3), LittleEndianByteArray.load32(bArr, (i + i2) - 4) & 4294967295L, (i2 * 2) + f312K2);
        }
        if (i2 <= 0) {
            return f312K2;
        }
        return shiftMix((((bArr[i] & 255) + ((bArr[(i2 >> 1) + i] & 255) << 8)) * f312K2) ^ ((i2 + ((bArr[i + (i2 - 1)] & 255) << 2)) * f310K0)) * f312K2;
    }

    private static long hashLength17to32(byte[] bArr, int i, int i2) {
        long j = (i2 * 2) + f312K2;
        long jLoad64 = LittleEndianByteArray.load64(bArr, i) * f311K1;
        long jLoad642 = LittleEndianByteArray.load64(bArr, i + 8);
        int i3 = i + i2;
        long jLoad643 = LittleEndianByteArray.load64(bArr, i3 - 8) * j;
        return hashLength16((LittleEndianByteArray.load64(bArr, i3 - 16) * f312K2) + Long.rotateRight(jLoad64 + jLoad642, 43) + Long.rotateRight(jLoad643, 30), jLoad64 + Long.rotateRight(jLoad642 + f312K2, 18) + jLoad643, j);
    }

    private static long hashLength33To64(byte[] bArr, int i, int i2) {
        long j = (i2 * 2) + f312K2;
        long jLoad64 = LittleEndianByteArray.load64(bArr, i) * f312K2;
        long jLoad642 = LittleEndianByteArray.load64(bArr, i + 8);
        int i3 = i + i2;
        long jLoad643 = LittleEndianByteArray.load64(bArr, i3 - 8) * j;
        long jRotateRight = Long.rotateRight(jLoad64 + jLoad642, 43) + Long.rotateRight(jLoad643, 30) + (LittleEndianByteArray.load64(bArr, i3 - 16) * f312K2);
        long jHashLength16 = hashLength16(jRotateRight, jLoad643 + Long.rotateRight(jLoad642 + f312K2, 18) + jLoad64, j);
        long jLoad644 = LittleEndianByteArray.load64(bArr, i + 16) * j;
        long jLoad645 = LittleEndianByteArray.load64(bArr, i + 24);
        long jLoad646 = (jRotateRight + LittleEndianByteArray.load64(bArr, i3 - 32)) * j;
        return hashLength16(((jHashLength16 + LittleEndianByteArray.load64(bArr, i3 - 24)) * j) + Long.rotateRight(jLoad644 + jLoad645, 43) + Long.rotateRight(jLoad646, 30), jLoad644 + Long.rotateRight(jLoad645 + jLoad64, 18) + jLoad646, j);
    }

    private static long hashLength65Plus(byte[] bArr, int i, int i2) {
        long jShiftMix = shiftMix(-7956866745689871395L) * f312K2;
        long[] jArr = new long[2];
        long[] jArr2 = new long[2];
        long jLoad64 = 95310865018149119L + LittleEndianByteArray.load64(bArr, i);
        int i3 = i2 - 1;
        int i4 = i + ((i3 / 64) * 64);
        int i5 = i3 & 63;
        int i6 = i4 + i5;
        int i7 = i6 - 63;
        long j = 2480279821605975764L;
        int i8 = i;
        while (true) {
            long jRotateRight = Long.rotateRight(jLoad64 + j + jArr[0] + LittleEndianByteArray.load64(bArr, i8 + 8), 37) * f311K1;
            long jRotateRight2 = Long.rotateRight(j + jArr[1] + LittleEndianByteArray.load64(bArr, i8 + 48), 42) * f311K1;
            long j2 = jRotateRight ^ jArr2[1];
            long jLoad642 = jRotateRight2 + jArr[0] + LittleEndianByteArray.load64(bArr, i8 + 40);
            long jRotateRight3 = Long.rotateRight(jShiftMix + jArr2[0], 33) * f311K1;
            weakHashLength32WithSeeds(bArr, i8, jArr[1] * f311K1, j2 + jArr2[0], jArr);
            weakHashLength32WithSeeds(bArr, i8 + 32, jRotateRight3 + jArr2[1], jLoad642 + LittleEndianByteArray.load64(bArr, i8 + 16), jArr2);
            i8 += 64;
            if (i8 == i4) {
                long j3 = ((j2 & 255) << 1) + f311K1;
                long j4 = jArr2[0] + i5;
                jArr2[0] = j4;
                long j5 = jArr[0] + j4;
                jArr[0] = j5;
                jArr2[0] = jArr2[0] + j5;
                long jRotateRight4 = Long.rotateRight(jRotateRight3 + jLoad642 + jArr[0] + LittleEndianByteArray.load64(bArr, i6 - 55), 37) * j3;
                long jRotateRight5 = Long.rotateRight(jLoad642 + jArr[1] + LittleEndianByteArray.load64(bArr, i6 - 15), 42) * j3;
                long j6 = jRotateRight4 ^ (jArr2[1] * 9);
                long jLoad643 = jRotateRight5 + (jArr[0] * 9) + LittleEndianByteArray.load64(bArr, i6 - 23);
                long jRotateRight6 = Long.rotateRight(j2 + jArr2[0], 33) * j3;
                weakHashLength32WithSeeds(bArr, i7, jArr[1] * j3, j6 + jArr2[0], jArr);
                weakHashLength32WithSeeds(bArr, i6 - 31, jRotateRight6 + jArr2[1], LittleEndianByteArray.load64(bArr, i6 - 47) + jLoad643, jArr2);
                return hashLength16(hashLength16(jArr[0], jArr2[0], j3) + (shiftMix(jLoad643) * f310K0) + j6, hashLength16(jArr[1], jArr2[1], j3) + jRotateRight6, j3);
            }
            jShiftMix = j2;
            j = jLoad642;
            jLoad64 = jRotateRight3;
        }
    }
}
