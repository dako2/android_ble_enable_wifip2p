package org.tensorflow.lite.support.common.ops;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.common.TensorOperator;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes3.dex */
public class CastOp implements TensorOperator {
    private final DataType destinationType;

    public CastOp(DataType dataType) {
        SupportPreconditions.checkArgument(dataType == DataType.UINT8 || dataType == DataType.FLOAT32, "Destination type " + dataType + " is not supported.");
        this.destinationType = dataType;
    }

    @Override // org.tensorflow.lite.support.common.Operator
    public TensorBuffer apply(TensorBuffer tensorBuffer) {
        DataType dataType = tensorBuffer.getDataType();
        DataType dataType2 = this.destinationType;
        return dataType == dataType2 ? tensorBuffer : TensorBuffer.createFrom(tensorBuffer, dataType2);
    }
}
