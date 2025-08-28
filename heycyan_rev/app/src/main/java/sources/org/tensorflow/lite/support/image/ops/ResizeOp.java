package org.tensorflow.lite.support.image.ops;

import android.graphics.Bitmap;
import android.graphics.PointF;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.image.ColorSpaceType;
import org.tensorflow.lite.support.image.ImageOperator;
import org.tensorflow.lite.support.image.TensorImage;

/* loaded from: classes3.dex */
public class ResizeOp implements ImageOperator {
    private final int targetHeight;
    private final int targetWidth;
    private final boolean useBilinear;

    public enum ResizeMethod {
        BILINEAR,
        NEAREST_NEIGHBOR
    }

    public ResizeOp(int i, int i2, ResizeMethod resizeMethod) {
        this.targetHeight = i;
        this.targetWidth = i2;
        this.useBilinear = resizeMethod == ResizeMethod.BILINEAR;
    }

    @Override // org.tensorflow.lite.support.common.Operator
    @NonNull
    public TensorImage apply(@NonNull TensorImage tensorImage) {
        SupportPreconditions.checkArgument(tensorImage.getColorSpaceType() == ColorSpaceType.RGB, "Only RGB images are supported in ResizeOp, but not " + tensorImage.getColorSpaceType().name());
        tensorImage.load(Bitmap.createScaledBitmap(tensorImage.getBitmap(), this.targetWidth, this.targetHeight, this.useBilinear));
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
        return new PointF((pointF.x * i2) / this.targetWidth, (pointF.y * i) / this.targetHeight);
    }
}
