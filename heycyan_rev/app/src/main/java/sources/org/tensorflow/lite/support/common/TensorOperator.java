package org.tensorflow.lite.support.common;

import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes3.dex */
public interface TensorOperator extends Operator<TensorBuffer> {
    TensorBuffer apply(TensorBuffer tensorBuffer);
}
