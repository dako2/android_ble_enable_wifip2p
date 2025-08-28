package org.tensorflow.lite.support.label;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes3.dex */
public class TensorLabel {
    private final Map<Integer, List<String>> axisLabels;
    private final int[] shape;
    private final TensorBuffer tensorBuffer;

    public TensorLabel(@NonNull Map<Integer, List<String>> map, @NonNull TensorBuffer tensorBuffer) {
        SupportPreconditions.checkNotNull(map, "Axis labels cannot be null.");
        SupportPreconditions.checkNotNull(tensorBuffer, "Tensor Buffer cannot be null.");
        this.axisLabels = map;
        this.tensorBuffer = tensorBuffer;
        this.shape = tensorBuffer.getShape();
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            int iIntValue = entry.getKey().intValue();
            boolean z = true;
            SupportPreconditions.checkArgument(iIntValue >= 0 && iIntValue < this.shape.length, "Invalid axis id: " + iIntValue);
            SupportPreconditions.checkNotNull(entry.getValue(), "Label list is null on axis " + iIntValue);
            if (this.shape[iIntValue] != entry.getValue().size()) {
                z = false;
            }
            SupportPreconditions.checkArgument(z, "Label number " + entry.getValue().size() + " mismatch the shape on axis " + iIntValue);
        }
    }

    public TensorLabel(@NonNull List<String> list, @NonNull TensorBuffer tensorBuffer) {
        this(makeMap(getFirstAxisWithSizeGreaterThanOne(tensorBuffer), list), tensorBuffer);
    }

    @NonNull
    public Map<String, TensorBuffer> getMapWithTensorBuffer() {
        int firstAxisWithSizeGreaterThanOne = getFirstAxisWithSizeGreaterThanOne(this.tensorBuffer);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        SupportPreconditions.checkArgument(this.axisLabels.containsKey(Integer.valueOf(firstAxisWithSizeGreaterThanOne)), "get a <String, TensorBuffer> map requires the labels are set on the first non-1 axis.");
        List<String> list = this.axisLabels.get(Integer.valueOf(firstAxisWithSizeGreaterThanOne));
        DataType dataType = this.tensorBuffer.getDataType();
        int typeSize = this.tensorBuffer.getTypeSize();
        int flatSize = this.tensorBuffer.getFlatSize();
        ByteBuffer buffer = this.tensorBuffer.getBuffer();
        buffer.rewind();
        int i = (flatSize / this.shape[firstAxisWithSizeGreaterThanOne]) * typeSize;
        SupportPreconditions.checkNotNull(list, "Label list should never be null");
        int i2 = 0;
        for (String str : list) {
            buffer.position(i2 * i);
            ByteBuffer byteBufferSlice = buffer.slice();
            byteBufferSlice.order(buffer.order()).limit(i);
            TensorBuffer tensorBufferCreateDynamic = TensorBuffer.createDynamic(dataType);
            int[] iArr = this.shape;
            tensorBufferCreateDynamic.loadBuffer(byteBufferSlice, Arrays.copyOfRange(iArr, firstAxisWithSizeGreaterThanOne + 1, iArr.length));
            linkedHashMap.put(str, tensorBufferCreateDynamic);
            i2++;
        }
        return linkedHashMap;
    }

    @NonNull
    public Map<String, Float> getMapWithFloatValue() {
        int firstAxisWithSizeGreaterThanOne = getFirstAxisWithSizeGreaterThanOne(this.tensorBuffer);
        int i = 0;
        SupportPreconditions.checkState(firstAxisWithSizeGreaterThanOne == this.shape.length - 1, "get a <String, Scalar> map is only valid when the only labeled axis is the last one.");
        List<String> list = this.axisLabels.get(Integer.valueOf(firstAxisWithSizeGreaterThanOne));
        float[] floatArray = this.tensorBuffer.getFloatArray();
        SupportPreconditions.checkState(list.size() == floatArray.length);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            linkedHashMap.put(it.next(), Float.valueOf(floatArray[i]));
            i++;
        }
        return linkedHashMap;
    }

    @NonNull
    public List<Category> getCategoryList() {
        int firstAxisWithSizeGreaterThanOne = getFirstAxisWithSizeGreaterThanOne(this.tensorBuffer);
        int i = 0;
        SupportPreconditions.checkState(firstAxisWithSizeGreaterThanOne == this.shape.length - 1, "get a Category list is only valid when the only labeled axis is the last one.");
        List<String> list = this.axisLabels.get(Integer.valueOf(firstAxisWithSizeGreaterThanOne));
        float[] floatArray = this.tensorBuffer.getFloatArray();
        SupportPreconditions.checkState(list.size() == floatArray.length);
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new Category(it.next(), floatArray[i]));
            i++;
        }
        return arrayList;
    }

    private static int getFirstAxisWithSizeGreaterThanOne(@NonNull TensorBuffer tensorBuffer) {
        int[] shape = tensorBuffer.getShape();
        for (int i = 0; i < shape.length; i++) {
            if (shape[i] > 1) {
                return i;
            }
        }
        throw new IllegalArgumentException("Cannot find an axis to label. A valid axis to label should have size larger than 1.");
    }

    private static Map<Integer, List<String>> makeMap(int i, List<String> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(Integer.valueOf(i), list);
        return linkedHashMap;
    }
}
