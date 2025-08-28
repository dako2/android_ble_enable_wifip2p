package com.glasssutdio.wear.p003ai.bean;

import androidx.core.app.NotificationCompat;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TranslateOneModelJsonAdapter.kt */
@Metadata(m606d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0018\u001a\u00020\u000fH\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m607d2 = {"Lcom/glasssutdio/wear/ai/bean/TranslateOneModelJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/glasssutdio/wear/ai/bean/TranslateOneModel;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "booleanAdapter", "", "constructorRef", "Ljava/lang/reflect/Constructor;", "intAdapter", "", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* renamed from: com.glasssutdio.wear.ai.bean.TranslateOneModelJsonAdapter, reason: from toString */
/* loaded from: classes.dex */
public final class GeneratedJsonAdapter extends JsonAdapter<TranslateOneModel> {
    private final JsonAdapter<Boolean> booleanAdapter;
    private volatile Constructor<TranslateOneModel> constructorRef;
    private final JsonAdapter<Integer> intAdapter;
    private final JsonReader.Options options;
    private final JsonAdapter<String> stringAdapter;

    public GeneratedJsonAdapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options optionsM593of = JsonReader.Options.m593of("mItemType", "content", "isPlaying", "isBottom", "languageCode", "sid", NotificationCompat.CATEGORY_STATUS);
        Intrinsics.checkNotNullExpressionValue(optionsM593of, "of(...)");
        this.options = optionsM593of;
        JsonAdapter<Integer> jsonAdapterAdapter = moshi.adapter(Integer.TYPE, SetsKt.emptySet(), "mItemType");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "adapter(...)");
        this.intAdapter = jsonAdapterAdapter;
        JsonAdapter<String> jsonAdapterAdapter2 = moshi.adapter(String.class, SetsKt.emptySet(), "content");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "adapter(...)");
        this.stringAdapter = jsonAdapterAdapter2;
        JsonAdapter<Boolean> jsonAdapterAdapter3 = moshi.adapter(Boolean.TYPE, SetsKt.emptySet(), "isPlaying");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter3, "adapter(...)");
        this.booleanAdapter = jsonAdapterAdapter3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(39);
        sb.append("GeneratedJsonAdapter(TranslateOneModel)");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.JsonAdapter
    public TranslateOneModel fromJson(JsonReader reader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, IOException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        reader.beginObject();
        Integer numFromJson = null;
        int i = -1;
        Boolean boolFromJson = false;
        Boolean boolFromJson2 = null;
        Integer numFromJson2 = 0;
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        while (reader.hasNext()) {
            switch (reader.selectName(this.options)) {
                case -1:
                    reader.skipName();
                    reader.skipValue();
                    break;
                case 0:
                    numFromJson = this.intAdapter.fromJson(reader);
                    if (numFromJson == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull = Util.unexpectedNull("mItemType", "mItemType", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull, "unexpectedNull(...)");
                        throw jsonDataExceptionUnexpectedNull;
                    }
                    break;
                case 1:
                    strFromJson = this.stringAdapter.fromJson(reader);
                    if (strFromJson == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull2 = Util.unexpectedNull("content", "content", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull2, "unexpectedNull(...)");
                        throw jsonDataExceptionUnexpectedNull2;
                    }
                    break;
                case 2:
                    boolFromJson = this.booleanAdapter.fromJson(reader);
                    if (boolFromJson == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull3 = Util.unexpectedNull("isPlaying", "isPlaying", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull3, "unexpectedNull(...)");
                        throw jsonDataExceptionUnexpectedNull3;
                    }
                    i &= -5;
                    break;
                case 3:
                    boolFromJson2 = this.booleanAdapter.fromJson(reader);
                    if (boolFromJson2 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull4 = Util.unexpectedNull("isBottom", "isBottom", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull4, "unexpectedNull(...)");
                        throw jsonDataExceptionUnexpectedNull4;
                    }
                    i &= -9;
                    break;
                case 4:
                    strFromJson2 = this.stringAdapter.fromJson(reader);
                    if (strFromJson2 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull5 = Util.unexpectedNull("languageCode", "languageCode", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull5, "unexpectedNull(...)");
                        throw jsonDataExceptionUnexpectedNull5;
                    }
                    i &= -17;
                    break;
                case 5:
                    strFromJson3 = this.stringAdapter.fromJson(reader);
                    if (strFromJson3 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull6 = Util.unexpectedNull("sid", "sid", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull6, "unexpectedNull(...)");
                        throw jsonDataExceptionUnexpectedNull6;
                    }
                    break;
                case 6:
                    numFromJson2 = this.intAdapter.fromJson(reader);
                    if (numFromJson2 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull7 = Util.unexpectedNull(NotificationCompat.CATEGORY_STATUS, NotificationCompat.CATEGORY_STATUS, reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull7, "unexpectedNull(...)");
                        throw jsonDataExceptionUnexpectedNull7;
                    }
                    i &= -65;
                    break;
            }
        }
        reader.endObject();
        if (i == -93) {
            if (numFromJson != null) {
                int iIntValue = numFromJson.intValue();
                if (strFromJson == null) {
                    JsonDataException jsonDataExceptionMissingProperty = Util.missingProperty("content", "content", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty, "missingProperty(...)");
                    throw jsonDataExceptionMissingProperty;
                }
                boolean zBooleanValue = boolFromJson.booleanValue();
                boolean zBooleanValue2 = boolFromJson2.booleanValue();
                Intrinsics.checkNotNull(strFromJson2, "null cannot be cast to non-null type kotlin.String");
                if (strFromJson3 != null) {
                    return new TranslateOneModel(iIntValue, strFromJson, zBooleanValue, zBooleanValue2, strFromJson2, strFromJson3, numFromJson2.intValue());
                }
                JsonDataException jsonDataExceptionMissingProperty2 = Util.missingProperty("sid", "sid", reader);
                Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty2, "missingProperty(...)");
                throw jsonDataExceptionMissingProperty2;
            }
            JsonDataException jsonDataExceptionMissingProperty3 = Util.missingProperty("mItemType", "mItemType", reader);
            Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty3, "missingProperty(...)");
            throw jsonDataExceptionMissingProperty3;
        }
        Constructor<TranslateOneModel> declaredConstructor = this.constructorRef;
        if (declaredConstructor == null) {
            declaredConstructor = TranslateOneModel.class.getDeclaredConstructor(Integer.TYPE, String.class, Boolean.TYPE, Boolean.TYPE, String.class, String.class, Integer.TYPE, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER);
            this.constructorRef = declaredConstructor;
            Intrinsics.checkNotNullExpressionValue(declaredConstructor, "also(...)");
        }
        if (numFromJson != null) {
            Integer numValueOf = Integer.valueOf(numFromJson.intValue());
            if (strFromJson == null) {
                JsonDataException jsonDataExceptionMissingProperty4 = Util.missingProperty("content", "content", reader);
                Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty4, "missingProperty(...)");
                throw jsonDataExceptionMissingProperty4;
            }
            if (strFromJson3 == null) {
                JsonDataException jsonDataExceptionMissingProperty5 = Util.missingProperty("sid", "sid", reader);
                Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty5, "missingProperty(...)");
                throw jsonDataExceptionMissingProperty5;
            }
            TranslateOneModel translateOneModelNewInstance = declaredConstructor.newInstance(numValueOf, strFromJson, boolFromJson, boolFromJson2, strFromJson2, strFromJson3, numFromJson2, Integer.valueOf(i), null);
            Intrinsics.checkNotNullExpressionValue(translateOneModelNewInstance, "newInstance(...)");
            return translateOneModelNewInstance;
        }
        JsonDataException jsonDataExceptionMissingProperty6 = Util.missingProperty("mItemType", "mItemType", reader);
        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty6, "missingProperty(...)");
        throw jsonDataExceptionMissingProperty6;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, TranslateOneModel value_) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        if (value_ == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        writer.beginObject();
        writer.name("mItemType");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getMItemType()));
        writer.name("content");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getContent());
        writer.name("isPlaying");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.isPlaying()));
        writer.name("isBottom");
        this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(value_.isBottom()));
        writer.name("languageCode");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getLanguageCode());
        writer.name("sid");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getSid());
        writer.name(NotificationCompat.CATEGORY_STATUS);
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getStatus()));
        writer.endObject();
    }
}
