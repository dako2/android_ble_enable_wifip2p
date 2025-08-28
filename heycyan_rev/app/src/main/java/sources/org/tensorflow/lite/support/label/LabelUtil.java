package org.tensorflow.lite.support.label;

import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes3.dex */
public class LabelUtil {
    public static List<String> mapValueToLabels(@NonNull TensorBuffer tensorBuffer, @NonNull List<String> list, int i) {
        SupportPreconditions.checkNotNull(tensorBuffer, "Given tensor should not be null");
        SupportPreconditions.checkNotNull(list, "Given labels should not be null");
        int[] intArray = tensorBuffer.getIntArray();
        Log.d("values", Arrays.toString(intArray));
        ArrayList arrayList = new ArrayList();
        for (int i2 : intArray) {
            int i3 = i2 + i;
            if (i3 < 0 || i3 >= list.size()) {
                arrayList.add("");
            } else {
                arrayList.add(list.get(i3));
            }
        }
        return arrayList;
    }

    private LabelUtil() {
    }
}
