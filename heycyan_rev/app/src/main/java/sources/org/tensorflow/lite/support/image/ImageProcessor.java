package org.tensorflow.lite.support.image;

import android.graphics.PointF;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.tensorflow.lite.support.common.Operator;
import org.tensorflow.lite.support.common.SequentialProcessor;
import org.tensorflow.lite.support.common.TensorOperator;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.image.ops.Rot90Op;
import org.tensorflow.lite.support.image.ops.TensorOperatorWrapper;

/* loaded from: classes3.dex */
public class ImageProcessor extends SequentialProcessor<TensorImage> {
    private ImageProcessor(Builder builder) {
        super(builder);
    }

    public PointF inverseTransform(PointF pointF, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = this.operatorList.iterator();
        while (it.hasNext()) {
            Operator operator = (Operator) it.next();
            arrayList.add(Integer.valueOf(i2));
            arrayList2.add(Integer.valueOf(i));
            ImageOperator imageOperator = (ImageOperator) operator;
            int outputImageHeight = imageOperator.getOutputImageHeight(i, i2);
            i2 = imageOperator.getOutputImageWidth(i, i2);
            i = outputImageHeight;
        }
        ListIterator listIterator = this.operatorList.listIterator(this.operatorList.size());
        ListIterator listIterator2 = arrayList.listIterator(arrayList.size());
        ListIterator listIterator3 = arrayList2.listIterator(arrayList2.size());
        while (listIterator.hasPrevious()) {
            pointF = ((ImageOperator) listIterator.previous()).inverseTransform(pointF, ((Integer) listIterator3.previous()).intValue(), ((Integer) listIterator2.previous()).intValue());
        }
        return pointF;
    }

    public RectF inverseTransform(RectF rectF, int i, int i2) {
        PointF pointFInverseTransform = inverseTransform(new PointF(rectF.left, rectF.top), i, i2);
        PointF pointFInverseTransform2 = inverseTransform(new PointF(rectF.right, rectF.bottom), i, i2);
        return new RectF(Math.min(pointFInverseTransform.x, pointFInverseTransform2.x), Math.min(pointFInverseTransform.y, pointFInverseTransform2.y), Math.max(pointFInverseTransform.x, pointFInverseTransform2.x), Math.max(pointFInverseTransform.y, pointFInverseTransform2.y));
    }

    @Override // org.tensorflow.lite.support.common.SequentialProcessor, org.tensorflow.lite.support.common.Processor
    public TensorImage process(TensorImage tensorImage) {
        return (TensorImage) super.process((ImageProcessor) tensorImage);
    }

    public static class Builder extends SequentialProcessor.Builder<TensorImage> {
        @Override // org.tensorflow.lite.support.common.SequentialProcessor.Builder
        public /* bridge */ /* synthetic */ SequentialProcessor.Builder<TensorImage> add(@NonNull Operator<TensorImage> operator) {
            return super.add(operator);
        }

        public Builder add(ImageOperator imageOperator) {
            super.add((Operator) imageOperator);
            return this;
        }

        public Builder add(TensorOperator tensorOperator) {
            return add((ImageOperator) new TensorOperatorWrapper(tensorOperator));
        }

        @Override // org.tensorflow.lite.support.common.SequentialProcessor.Builder
        public SequentialProcessor<TensorImage> build() {
            return new ImageProcessor(this);
        }
    }

    public void updateNumberOfRotations(int i) {
        updateNumberOfRotations(i, 0);
    }

    public synchronized void updateNumberOfRotations(int i, int i2) {
        SupportPreconditions.checkState(this.operatorIndex.containsKey(Rot90Op.class.getName()), "The Rot90Op has not been added to the ImageProcessor.");
        List<Integer> list = this.operatorIndex.get(Rot90Op.class.getName());
        SupportPreconditions.checkElementIndex(i2, list.size(), "occurrence");
        this.operatorList.set(list.get(i2).intValue(), new Rot90Op(i));
    }
}
