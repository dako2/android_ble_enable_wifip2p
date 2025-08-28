package org.tensorflow.lite.support.image.ops;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.image.ColorSpaceType;
import org.tensorflow.lite.support.image.ImageOperator;
import org.tensorflow.lite.support.image.TensorImage;

/* loaded from: classes3.dex */
public class ResizeWithCropOrPadOp implements ImageOperator {
    private final Bitmap output;
    private final int targetHeight;
    private final int targetWidth;

    public ResizeWithCropOrPadOp(int i, int i2) {
        this.targetHeight = i;
        this.targetWidth = i2;
        this.output = Bitmap.createBitmap(i2, i, Bitmap.Config.ARGB_8888);
    }

    @Override // org.tensorflow.lite.support.common.Operator
    @NonNull
    public TensorImage apply(@NonNull TensorImage tensorImage) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = 0;
        SupportPreconditions.checkArgument(tensorImage.getColorSpaceType() == ColorSpaceType.RGB, "Only RGB images are supported in ResizeWithCropOrPadOp, but not " + tensorImage.getColorSpaceType().name());
        Bitmap bitmap = tensorImage.getBitmap();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i8 = this.targetWidth;
        if (i8 > width) {
            i4 = (i8 - width) / 2;
            i3 = i4 + width;
            i2 = width;
            i = 0;
        } else {
            i = (width - i8) / 2;
            i2 = i + i8;
            i3 = i8;
            i4 = 0;
        }
        int i9 = this.targetHeight;
        if (i9 > height) {
            i5 = (i9 - height) / 2;
            i6 = i5 + height;
        } else {
            int i10 = (height - i9) / 2;
            int i11 = i10 + i9;
            i5 = 0;
            i7 = i10;
            height = i11;
            i6 = i9;
        }
        new Canvas(this.output).drawBitmap(bitmap, new Rect(i, i7, i2, height), new Rect(i4, i5, i3, i6), (Paint) null);
        tensorImage.load(this.output);
        return tensorImage;
    }

    @Override // org.tensorflow.lite.support.image.ImageOperator
    public int getOutputImageHeight(int i, int i2) {
        return this.targetHeight;
    }

    @Override // org.tensorflow.lite.support.image.ImageOperator
    public int getOutputImageWidth(int i, int i2) {
        return this.targetWidth;
    }

    @Override // org.tensorflow.lite.support.image.ImageOperator
    public PointF inverseTransform(PointF pointF, int i, int i2) {
        return transformImpl(pointF, this.targetHeight, this.targetWidth, i, i2);
    }

    private static PointF transformImpl(PointF pointF, int i, int i2, int i3, int i4) {
        return new PointF(pointF.x + ((i4 - i2) / 2), pointF.y + ((i3 - i) / 2));
    }
}
