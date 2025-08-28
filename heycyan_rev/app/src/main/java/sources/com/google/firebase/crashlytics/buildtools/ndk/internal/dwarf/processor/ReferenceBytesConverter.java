package com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes2.dex */
public class ReferenceBytesConverter {
    private final ByteOrder byteOrder;

    public ReferenceBytesConverter(ByteOrder byteOrder) {
        this.byteOrder = byteOrder;
    }

    public long asLongValue(byte[] bArr) {
        return referenceBytesAsLong(bArr, this.byteOrder);
    }

    private static long referenceBytesAsLong(byte[] bArr, ByteOrder byteOrder) {
        int length = bArr.length;
        byte[] bArr2 = new byte[8];
        System.arraycopy(bArr, 0, bArr2, byteOrder == ByteOrder.BIG_ENDIAN ? 8 - length : 0, length);
        return ByteBuffer.wrap(bArr2).order(byteOrder).getLong();
    }
}
