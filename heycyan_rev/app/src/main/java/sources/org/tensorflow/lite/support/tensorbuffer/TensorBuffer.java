package org.tensorflow.lite.support.tensorbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;

/* loaded from: classes3.dex */
public abstract class TensorBuffer {
    protected ByteBuffer buffer;
    protected int flatSize = -1;
    protected final boolean isDynamic = true;
    protected int[] shape;

    public abstract DataType getDataType();

    @NonNull
    public abstract float[] getFloatArray();

    public abstract float getFloatValue(int i);

    @NonNull
    public abstract int[] getIntArray();

    public abstract int getIntValue(int i);

    public abstract int getTypeSize();

    public abstract void loadArray(@NonNull float[] fArr, @NonNull int[] iArr);

    public abstract void loadArray(@NonNull int[] iArr, @NonNull int[] iArr2);

    /* renamed from: org.tensorflow.lite.support.tensorbuffer.TensorBuffer$1 */
    static /* synthetic */ class C30011 {
        static final /* synthetic */ int[] $SwitchMap$org$tensorflow$lite$DataType;

        static {
            int[] iArr = new int[DataType.values().length];
            $SwitchMap$org$tensorflow$lite$DataType = iArr;
            try {
                iArr[DataType.FLOAT32.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$tensorflow$lite$DataType[DataType.UINT8.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @NonNull
    public static TensorBuffer createFixedSize(@NonNull int[] iArr, DataType dataType) {
        int i = C30011.$SwitchMap$org$tensorflow$lite$DataType[dataType.ordinal()];
        if (i == 1) {
            return new TensorBufferFloat(iArr);
        }
        if (i == 2) {
            return new TensorBufferUint8(iArr);
        }
        throw new AssertionError("TensorBuffer does not support data type: " + dataType);
    }

    @NonNull
    public static TensorBuffer createDynamic(DataType dataType) {
        int i = C30011.$SwitchMap$org$tensorflow$lite$DataType[dataType.ordinal()];
        if (i == 1) {
            return new TensorBufferFloat();
        }
        if (i == 2) {
            return new TensorBufferUint8();
        }
        throw new AssertionError("TensorBuffer does not support data type: " + dataType);
    }

    @NonNull
    public static TensorBuffer createFrom(@NonNull TensorBuffer tensorBuffer, DataType dataType) {
        TensorBuffer tensorBufferCreateFixedSize;
        SupportPreconditions.checkNotNull(tensorBuffer, "Cannot create a buffer from null");
        if (tensorBuffer.isDynamic()) {
            tensorBufferCreateFixedSize = createDynamic(dataType);
        } else {
            tensorBufferCreateFixedSize = createFixedSize(tensorBuffer.shape, dataType);
        }
        if (tensorBuffer.getDataType() == DataType.FLOAT32 && dataType == DataType.FLOAT32) {
            tensorBufferCreateFixedSize.loadArray(tensorBuffer.getFloatArray(), tensorBuffer.shape);
        } else {
            tensorBufferCreateFixedSize.loadArray(tensorBuffer.getIntArray(), tensorBuffer.shape);
        }
        return tensorBufferCreateFixedSize;
    }

    @NonNull
    public ByteBuffer getBuffer() {
        return this.buffer;
    }

    public int getFlatSize() {
        assertShapeIsCorrect();
        return this.flatSize;
    }

    @NonNull
    public int[] getShape() {
        assertShapeIsCorrect();
        int[] iArr = this.shape;
        return Arrays.copyOf(iArr, iArr.length);
    }

    public boolean isDynamic() {
        return this.isDynamic;
    }

    public void loadArray(@NonNull int[] iArr) {
        loadArray(iArr, this.shape);
    }

    public void loadArray(@NonNull float[] fArr) {
        loadArray(fArr, this.shape);
    }

    public void loadBuffer(@NonNull ByteBuffer byteBuffer, @NonNull int[] iArr) {
        SupportPreconditions.checkNotNull(byteBuffer, "Byte buffer cannot be null.");
        SupportPreconditions.checkArgument(isShapeValid(iArr), "Values in TensorBuffer shape should be non-negative.");
        int iComputeFlatSize = computeFlatSize(iArr);
        SupportPreconditions.checkArgument(byteBuffer.limit() == getTypeSize() * iComputeFlatSize, "The size of byte buffer and the shape do not match. Expected: " + (getTypeSize() * iComputeFlatSize) + " Actual: " + byteBuffer.limit());
        if (!this.isDynamic) {
            SupportPreconditions.checkArgument(Arrays.equals(iArr, this.shape));
        }
        this.shape = (int[]) iArr.clone();
        this.flatSize = iComputeFlatSize;
        byteBuffer.rewind();
        this.buffer = byteBuffer;
    }

    public void loadBuffer(@NonNull ByteBuffer byteBuffer) {
        loadBuffer(byteBuffer, this.shape);
    }

    protected TensorBuffer(@NonNull int[] iArr) {
        allocateMemory(iArr);
    }

    protected TensorBuffer() {
        allocateMemory(new int[]{0});
    }

    protected static int computeFlatSize(@NonNull int[] iArr) {
        SupportPreconditions.checkNotNull(iArr, "Shape cannot be null.");
        int i = 1;
        for (int i2 : iArr) {
            i *= i2;
        }
        return i;
    }

    protected void resize(@NonNull int[] iArr) {
        if (this.isDynamic) {
            allocateMemory(iArr);
        } else {
            SupportPreconditions.checkArgument(Arrays.equals(iArr, this.shape));
            this.shape = (int[]) iArr.clone();
        }
    }

    protected synchronized void copyByteBufferIfReadOnly() {
        if (this.buffer.isReadOnly()) {
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(this.buffer.capacity());
            byteBufferAllocateDirect.order(this.buffer.order());
            byteBufferAllocateDirect.put(this.buffer);
            byteBufferAllocateDirect.rewind();
            this.buffer = byteBufferAllocateDirect;
        }
    }

    private void allocateMemory(@NonNull int[] iArr) {
        SupportPreconditions.checkNotNull(iArr, "TensorBuffer shape cannot be null.");
        SupportPreconditions.checkArgument(isShapeValid(iArr), "Values in TensorBuffer shape should be non-negative.");
        int iComputeFlatSize = computeFlatSize(iArr);
        this.shape = (int[]) iArr.clone();
        if (this.flatSize == iComputeFlatSize) {
            return;
        }
        this.flatSize = iComputeFlatSize;
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(iComputeFlatSize * getTypeSize());
        this.buffer = byteBufferAllocateDirect;
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
    }

    private void assertShapeIsCorrect() {
        SupportPreconditions.checkState(this.buffer.limit() == getTypeSize() * computeFlatSize(this.shape), String.format("The size of underlying ByteBuffer (%d) and the shape (%s) do not match. The ByteBuffer may have been changed.", Integer.valueOf(this.buffer.limit()), Arrays.toString(this.shape)));
    }

    private static boolean isShapeValid(@NonNull int[] iArr) {
        if (iArr.length == 0) {
            return true;
        }
        for (int i : iArr) {
            if (i < 0) {
                return false;
            }
        }
        return true;
    }
}
