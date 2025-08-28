package org.tensorflow.lite.support.image;

import android.graphics.Bitmap;
import android.graphics.Color;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes3.dex */
class ImageConversions {
    static Bitmap convertRgbTensorBufferToBitmap(TensorBuffer tensorBuffer) {
        int[] shape = tensorBuffer.getShape();
        ColorSpaceType colorSpaceType = ColorSpaceType.RGB;
        colorSpaceType.assertShape(shape);
        int height = colorSpaceType.getHeight(shape);
        int width = colorSpaceType.getWidth(shape);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, colorSpaceType.toBitmapConfig());
        int i = width * height;
        int[] iArr = new int[i];
        int[] intArray = tensorBuffer.getIntArray();
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = intArray[i2];
            int i5 = i2 + 2;
            int i6 = intArray[i2 + 1];
            i2 += 3;
            iArr[i3] = Color.rgb(i4, i6, intArray[i5]);
        }
        bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmapCreateBitmap;
    }

    static Bitmap convertGrayscaleTensorBufferToBitmap(TensorBuffer tensorBuffer) {
        if (tensorBuffer.getDataType() != DataType.UINT8) {
            tensorBuffer = TensorBuffer.createFrom(tensorBuffer, DataType.UINT8);
        }
        int[] shape = tensorBuffer.getShape();
        ColorSpaceType colorSpaceType = ColorSpaceType.GRAYSCALE;
        colorSpaceType.assertShape(shape);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(colorSpaceType.getWidth(shape), colorSpaceType.getHeight(shape), colorSpaceType.toBitmapConfig());
        tensorBuffer.getBuffer().rewind();
        bitmapCreateBitmap.copyPixelsFromBuffer(tensorBuffer.getBuffer());
        return bitmapCreateBitmap;
    }

    static void convertBitmapToTensorBuffer(Bitmap bitmap, TensorBuffer tensorBuffer) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        int[] iArr2 = {height, width, 3};
        int i2 = C29981.$SwitchMap$org$tensorflow$lite$DataType[tensorBuffer.getDataType().ordinal()];
        int i3 = 0;
        if (i2 != 1) {
            if (i2 == 2) {
                float[] fArr = new float[i * 3];
                int i4 = 0;
                while (i3 < i) {
                    int i5 = iArr[i3];
                    fArr[i4] = (i5 >> 16) & 255;
                    int i6 = i4 + 2;
                    fArr[i4 + 1] = (i5 >> 8) & 255;
                    i4 += 3;
                    fArr[i6] = i5 & 255;
                    i3++;
                }
                tensorBuffer.loadArray(fArr, iArr2);
                return;
            }
            throw new IllegalStateException("The type of TensorBuffer, " + tensorBuffer.getBuffer() + ", is unsupported.");
        }
        byte[] bArr = new byte[i * 3];
        int i7 = 0;
        while (i3 < i) {
            int i8 = iArr[i3];
            bArr[i7] = (byte) ((i8 >> 16) & 255);
            int i9 = i7 + 2;
            bArr[i7 + 1] = (byte) ((i8 >> 8) & 255);
            i7 += 3;
            bArr[i9] = (byte) (i8 & 255);
            i3++;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        tensorBuffer.loadBuffer(byteBufferWrap, iArr2);
    }

    /* renamed from: org.tensorflow.lite.support.image.ImageConversions$1 */
    static /* synthetic */ class C29981 {
        static final /* synthetic */ int[] $SwitchMap$org$tensorflow$lite$DataType;

        static {
            int[] iArr = new int[DataType.values().length];
            $SwitchMap$org$tensorflow$lite$DataType = iArr;
            try {
                iArr[DataType.UINT8.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$tensorflow$lite$DataType[DataType.FLOAT32.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private ImageConversions() {
    }
}
