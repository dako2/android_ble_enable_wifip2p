package org.tensorflow.lite.support.image.ops;

import android.graphics.PointF;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.tensorflow.lite.support.common.TensorOperator;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.image.ColorSpaceType;
import org.tensorflow.lite.support.image.ImageOperator;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes3.dex */
public class TensorOperatorWrapper implements ImageOperator {
    private final TensorOperator tensorOp;

    @Override // org.tensorflow.lite.support.image.ImageOperator
    public int getOutputImageHeight(int i, int i2) {
        return i;
    }

    @Override // org.tensorflow.lite.support.image.ImageOperator
    public int getOutputImageWidth(int i, int i2) {
        return i2;
    }

    @Override // org.tensorflow.lite.support.image.ImageOperator
    public PointF inverseTransform(PointF pointF, int i, int i2) {
        return pointF;
    }

    public TensorOperatorWrapper(TensorOperator tensorOperator) {
        this.tensorOp = tensorOperator;
    }

    @Override // org.tensorflow.lite.support.common.Operator
    @NonNull
    public TensorImage apply(@NonNull TensorImage tensorImage) {
        SupportPreconditions.checkNotNull(tensorImage, "Op cannot apply on null image.");
        TensorBuffer tensorBufferApply = this.tensorOp.apply(tensorImage.getTensorBuffer());
        ColorSpaceType colorSpaceType = tensorImage.getColorSpaceType();
        TensorImage tensorImage2 = new TensorImage(tensorBufferApply.getDataType());
        tensorImage2.load(tensorBufferApply, colorSpaceType);
        return tensorImage2;
    }
}
