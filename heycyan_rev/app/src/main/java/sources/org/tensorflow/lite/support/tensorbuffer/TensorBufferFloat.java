package org.tensorflow.lite.support.tensorbuffer;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;

/* loaded from: classes3.dex */
public final class TensorBufferFloat extends TensorBuffer {
    private static final DataType DATA_TYPE = DataType.FLOAT32;

    TensorBufferFloat(@NonNull int[] iArr) {
        super(iArr);
    }

    TensorBufferFloat() {
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public DataType getDataType() {
        return DATA_TYPE;
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    @NonNull
    public float[] getFloatArray() {
        this.buffer.rewind();
        float[] fArr = new float[this.flatSize];
        this.buffer.asFloatBuffer().get(fArr);
        return fArr;
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public float getFloatValue(int i) {
        return this.buffer.getFloat(i << 2);
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    @NonNull
    public int[] getIntArray() {
        this.buffer.rewind();
        float[] fArr = new float[this.flatSize];
        this.buffer.asFloatBuffer().get(fArr);
        int[] iArr = new int[this.flatSize];
        for (int i = 0; i < this.flatSize; i++) {
            iArr[i] = (int) fArr[i];
        }
        return iArr;
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public int getIntValue(int i) {
        return (int) this.buffer.getFloat(i << 2);
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public int getTypeSize() {
        return DATA_TYPE.byteSize();
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public void loadArray(@NonNull float[] fArr, @NonNull int[] iArr) {
        SupportPreconditions.checkNotNull(fArr, "The array to be loaded cannot be null.");
        SupportPreconditions.checkArgument(fArr.length == computeFlatSize(iArr), "The size of the array to be loaded does not match the specified shape.");
        copyByteBufferIfReadOnly();
        resize(iArr);
        this.buffer.rewind();
        this.buffer.asFloatBuffer().put(fArr);
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public void loadArray(@NonNull int[] iArr, @NonNull int[] iArr2) {
        SupportPreconditions.checkNotNull(iArr, "The array to be loaded cannot be null.");
        int i = 0;
        SupportPreconditions.checkArgument(iArr.length == computeFlatSize(iArr2), "The size of the array to be loaded does not match the specified shape.");
        copyByteBufferIfReadOnly();
        resize(iArr2);
        this.buffer.rewind();
        float[] fArr = new float[iArr.length];
        int length = iArr.length;
        int i2 = 0;
        while (i < length) {
            fArr[i2] = iArr[i];
            i++;
            i2++;
        }
        this.buffer.asFloatBuffer().put(fArr);
    }
}
