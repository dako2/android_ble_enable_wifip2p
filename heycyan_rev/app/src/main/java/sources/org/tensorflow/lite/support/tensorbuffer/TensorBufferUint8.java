package org.tensorflow.lite.support.tensorbuffer;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;

/* loaded from: classes3.dex */
public final class TensorBufferUint8 extends TensorBuffer {
    private static final DataType DATA_TYPE = DataType.UINT8;

    TensorBufferUint8(@NonNull int[] iArr) {
        super(iArr);
    }

    TensorBufferUint8() {
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public DataType getDataType() {
        return DATA_TYPE;
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    @NonNull
    public float[] getFloatArray() {
        this.buffer.rewind();
        this.buffer.get(new byte[this.flatSize]);
        float[] fArr = new float[this.flatSize];
        for (int i = 0; i < this.flatSize; i++) {
            fArr[i] = r0[i] & 255;
        }
        return fArr;
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public float getFloatValue(int i) {
        return this.buffer.get(i) & 255;
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    @NonNull
    public int[] getIntArray() {
        this.buffer.rewind();
        byte[] bArr = new byte[this.flatSize];
        this.buffer.get(bArr);
        int[] iArr = new int[this.flatSize];
        for (int i = 0; i < this.flatSize; i++) {
            iArr[i] = bArr[i] & 255;
        }
        return iArr;
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public int getIntValue(int i) {
        return this.buffer.get(i) & 255;
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public int getTypeSize() {
        return DATA_TYPE.byteSize();
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public void loadArray(@NonNull float[] fArr, @NonNull int[] iArr) {
        SupportPreconditions.checkNotNull(fArr, "The array to be loaded cannot be null.");
        int i = 0;
        SupportPreconditions.checkArgument(fArr.length == computeFlatSize(iArr), "The size of the array to be loaded does not match the specified shape.");
        copyByteBufferIfReadOnly();
        resize(iArr);
        this.buffer.rewind();
        byte[] bArr = new byte[fArr.length];
        int length = fArr.length;
        int i2 = 0;
        while (i < length) {
            bArr[i2] = (byte) Math.max(Math.min(fArr[i], 255.0d), 0.0d);
            i++;
            i2++;
        }
        this.buffer.put(bArr);
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public void loadArray(@NonNull int[] iArr, @NonNull int[] iArr2) {
        SupportPreconditions.checkNotNull(iArr, "The array to be loaded cannot be null.");
        int i = 0;
        SupportPreconditions.checkArgument(iArr.length == computeFlatSize(iArr2), "The size of the array to be loaded does not match the specified shape.");
        copyByteBufferIfReadOnly();
        resize(iArr2);
        this.buffer.rewind();
        byte[] bArr = new byte[iArr.length];
        int length = iArr.length;
        int i2 = 0;
        while (i < length) {
            bArr[i2] = (byte) Math.max(Math.min(iArr[i], 255.0f), 0.0f);
            i++;
            i2++;
        }
        this.buffer.put(bArr);
    }
}
