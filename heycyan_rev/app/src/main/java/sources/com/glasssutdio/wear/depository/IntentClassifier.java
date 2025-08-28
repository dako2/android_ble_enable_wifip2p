package com.glasssutdio.wear.depository;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import androidx.core.os.EnvironmentCompat;
import com.elvishew.xlog.XLog;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.p014io.CloseableKt;
import kotlin.p014io.TextStreamsKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.tensorflow.lite.Interpreter;

/* compiled from: IntentClassifier.kt */
@Metadata(m606d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0015\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0010\u001a\u00020\u0005H\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0005H\u0002J\u001c\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0005H\u0002J\u001a\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u0005H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\u000eX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m607d2 = {"Lcom/glasssutdio/wear/depository/IntentClassifier;", "", "context", "Landroid/content/Context;", "language", "", "(Landroid/content/Context;Ljava/lang/String;)V", "interpreter", "Lorg/tensorflow/lite/Interpreter;", "labels", "", "sequenceLength", "", "vocab", "", "loadLabels", "fileName", "loadModelFile", "Ljava/nio/MappedByteBuffer;", "modelFileName", "loadVocab", "predict", "text", "threshold", "", "preprocess", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class IntentClassifier {
    private final Context context;
    private Interpreter interpreter;
    private List<String> labels;
    private int sequenceLength;
    private Map<String, Integer> vocab;

    public IntentClassifier(Context context, String language) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(language, "language");
        this.context = context;
        this.sequenceLength = 20;
        XLog.m137i("language:" + language);
        if (StringsKt.startsWith$default(language, "zh", false, 2, (Object) null) || StringsKt.startsWith$default(language, "cn", false, 2, (Object) null)) {
            this.sequenceLength = 20;
            this.interpreter = new Interpreter(loadModelFile("intent_model.tflite"));
            this.vocab = loadVocab("vocab.txt");
            this.labels = loadLabels("labels.json");
            return;
        }
        if (StringsKt.startsWith$default(language, "en", false, 2, (Object) null)) {
            this.sequenceLength = 64;
            this.interpreter = new Interpreter(loadModelFile("intent_model_en.tflite"));
            this.vocab = loadVocab("vocab_en.txt");
            this.labels = loadLabels("labels_en.json");
            return;
        }
        this.sequenceLength = 20;
        this.interpreter = new Interpreter(loadModelFile("intent_model.tflite"));
        this.vocab = loadVocab("vocab.txt");
        this.labels = loadLabels("labels.json");
    }

    private final MappedByteBuffer loadModelFile(String modelFileName) throws IOException {
        AssetFileDescriptor assetFileDescriptorOpenFd = this.context.getAssets().openFd(modelFileName);
        Intrinsics.checkNotNullExpressionValue(assetFileDescriptorOpenFd, "openFd(...)");
        MappedByteBuffer map = new FileInputStream(assetFileDescriptorOpenFd.getFileDescriptor()).getChannel().map(FileChannel.MapMode.READ_ONLY, assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getDeclaredLength());
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        return map;
    }

    private final Map<String, Integer> loadVocab(String fileName) throws IOException {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        InputStream inputStreamOpen = this.context.getAssets().open(fileName);
        Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "open(...)");
        Reader inputStreamReader = new InputStreamReader(inputStreamOpen, Charsets.UTF_8);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        BufferedReader bufferedReader2 = bufferedReader instanceof BufferedReader ? bufferedReader : new BufferedReader(bufferedReader, 8192);
        try {
            int i = 0;
            for (String str : TextStreamsKt.lineSequence(bufferedReader2)) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                linkedHashMap.put(StringsKt.trim((CharSequence) str).toString(), Integer.valueOf(i));
                i = i2;
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(bufferedReader2, null);
            return linkedHashMap;
        } finally {
        }
    }

    private final List<String> loadLabels(String fileName) throws IOException {
        InputStream inputStreamOpen = this.context.getAssets().open(fileName);
        Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "open(...)");
        Reader inputStreamReader = new InputStreamReader(inputStreamOpen, Charsets.UTF_8);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        try {
            String text = TextStreamsKt.readText(bufferedReader);
            CloseableKt.closeFinally(bufferedReader, null);
            JSONArray jSONArray = new JSONArray(text);
            int length = jSONArray.length();
            ArrayList arrayList = new ArrayList(length);
            for (int i = 0; i < length; i++) {
                arrayList.add(jSONArray.getString(i));
            }
            return arrayList;
        } finally {
        }
    }

    public static /* synthetic */ String predict$default(IntentClassifier intentClassifier, String str, float f, int i, Object obj) {
        if ((i & 2) != 0) {
            f = 0.98f;
        }
        return intentClassifier.predict(str, f);
    }

    public final String predict(String text, float threshold) {
        Integer next;
        Intrinsics.checkNotNullParameter(text, "text");
        int[] iArrPreprocess = preprocess(text);
        XLog.m137i("预处理后的 Token IDs: " + ArraysKt.joinToString$default(iArrPreprocess, (CharSequence) ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(this.sequenceLength * 4);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        for (int i : iArrPreprocess) {
            byteBufferAllocateDirect.putInt(i);
        }
        byteBufferAllocateDirect.rewind();
        List<String> list = this.labels;
        List<String> list2 = null;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("labels");
            list = null;
        }
        ByteBuffer byteBufferAllocateDirect2 = ByteBuffer.allocateDirect(4 * list.size());
        byteBufferAllocateDirect2.order(ByteOrder.nativeOrder());
        Interpreter interpreter = this.interpreter;
        if (interpreter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("interpreter");
            interpreter = null;
        }
        interpreter.run(byteBufferAllocateDirect, byteBufferAllocateDirect2);
        byteBufferAllocateDirect2.rewind();
        List<String> list3 = this.labels;
        if (list3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("labels");
            list3 = null;
        }
        int size = list3.size();
        float[] fArr = new float[size];
        for (int i2 = 0; i2 < size; i2++) {
            fArr[i2] = byteBufferAllocateDirect2.getFloat();
        }
        XLog.m137i("模型输出概率: " + ArraysKt.joinToString$default(fArr, (CharSequence) ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new Function1<Float, CharSequence>() { // from class: com.glasssutdio.wear.depository.IntentClassifier.predict.1
            public final CharSequence invoke(float f) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format("%.4f", Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                return str;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Float f) {
                return invoke(f.floatValue());
            }
        }, 30, (Object) null));
        Iterator<Integer> it = ArraysKt.getIndices(fArr).iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                float f = fArr[next.intValue()];
                do {
                    Integer next2 = it.next();
                    float f2 = fArr[next2.intValue()];
                    if (Float.compare(f, f2) < 0) {
                        next = next2;
                        f = f2;
                    }
                } while (it.hasNext());
            }
        } else {
            next = null;
        }
        Integer num = next;
        int iIntValue = num != null ? num.intValue() : -1;
        float f3 = iIntValue >= 0 ? fArr[iIntValue] : 0.0f;
        if (f3 < threshold) {
            String str = String.format("置信度不足（%.4f），预测为 Unknown", Arrays.copyOf(new Object[]{Float.valueOf(f3)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            XLog.m152w(str);
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        List<String> list4 = this.labels;
        if (list4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("labels");
        } else {
            list2 = list4;
        }
        String str2 = list2.get(iIntValue);
        String str3 = String.format("预测意图: " + str2 + "（置信度: %.4f）", Arrays.copyOf(new Object[]{Float.valueOf(f3)}, 1));
        Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
        XLog.m137i(str3);
        return str2;
    }

    private final int[] preprocess(String text) {
        char[] charArray = text.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        ArrayList arrayList = new ArrayList(charArray.length);
        for (char c : charArray) {
            arrayList.add(String.valueOf(c));
        }
        ArrayList<String> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        for (String str : arrayList2) {
            Map<String, Integer> map = this.vocab;
            if (map == null) {
                Intrinsics.throwUninitializedPropertyAccessException("vocab");
                map = null;
            }
            Integer num = map.get(str);
            arrayList3.add(Integer.valueOf(num != null ? num.intValue() : 1));
        }
        ArrayList arrayList4 = arrayList3;
        int size = arrayList4.size();
        int i = this.sequenceLength;
        if (size >= i) {
            return CollectionsKt.toIntArray(arrayList4.subList(0, i));
        }
        ArrayList arrayList5 = arrayList4;
        int size2 = i - arrayList4.size();
        ArrayList arrayList6 = new ArrayList(size2);
        for (int i2 = 0; i2 < size2; i2++) {
            arrayList6.add(0);
        }
        return CollectionsKt.toIntArray(CollectionsKt.plus((Collection) arrayList5, (Iterable) arrayList6));
    }
}
