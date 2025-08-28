package com.glasssutdio.wear.home.bean;

import com.glasssutdio.wear.all.bean.QLanguageType;
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

/* compiled from: SelectLanguageBeanJsonAdapter.kt */
@Metadata(m606d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m607d2 = {"Lcom/glasssutdio/wear/home/bean/SelectLanguageBeanJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/glasssutdio/wear/home/bean/SelectLanguageBean;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "constructorRef", "Ljava/lang/reflect/Constructor;", "options", "Lcom/squareup/moshi/JsonReader$Options;", "qLanguageTypeAdapter", "Lcom/glasssutdio/wear/all/bean/QLanguageType;", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "toString", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* renamed from: com.glasssutdio.wear.home.bean.SelectLanguageBeanJsonAdapter, reason: from toString */
/* loaded from: classes.dex */
public final class GeneratedJsonAdapter extends JsonAdapter<SelectLanguageBean> {
    private volatile Constructor<SelectLanguageBean> constructorRef;
    private final JsonReader.Options options;
    private final JsonAdapter<QLanguageType> qLanguageTypeAdapter;

    public GeneratedJsonAdapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options optionsM593of = JsonReader.Options.m593of("listenerTop", "listenerBottom", "oneTop", "oneBottom");
        Intrinsics.checkNotNullExpressionValue(optionsM593of, "of(...)");
        this.options = optionsM593of;
        JsonAdapter<QLanguageType> jsonAdapterAdapter = moshi.adapter(QLanguageType.class, SetsKt.emptySet(), "listenerTop");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "adapter(...)");
        this.qLanguageTypeAdapter = jsonAdapterAdapter;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("GeneratedJsonAdapter(SelectLanguageBean)");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.JsonAdapter
    public SelectLanguageBean fromJson(JsonReader reader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, IOException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        reader.beginObject();
        QLanguageType qLanguageTypeFromJson = null;
        QLanguageType qLanguageTypeFromJson2 = null;
        QLanguageType qLanguageTypeFromJson3 = null;
        QLanguageType qLanguageTypeFromJson4 = null;
        int i = -1;
        while (reader.hasNext()) {
            int iSelectName = reader.selectName(this.options);
            if (iSelectName == -1) {
                reader.skipName();
                reader.skipValue();
            } else if (iSelectName == 0) {
                qLanguageTypeFromJson = this.qLanguageTypeAdapter.fromJson(reader);
                if (qLanguageTypeFromJson == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull = Util.unexpectedNull("listenerTop", "listenerTop", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull, "unexpectedNull(...)");
                    throw jsonDataExceptionUnexpectedNull;
                }
                i &= -2;
            } else if (iSelectName == 1) {
                qLanguageTypeFromJson2 = this.qLanguageTypeAdapter.fromJson(reader);
                if (qLanguageTypeFromJson2 == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull2 = Util.unexpectedNull("listenerBottom", "listenerBottom", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull2, "unexpectedNull(...)");
                    throw jsonDataExceptionUnexpectedNull2;
                }
                i &= -3;
            } else if (iSelectName == 2) {
                qLanguageTypeFromJson3 = this.qLanguageTypeAdapter.fromJson(reader);
                if (qLanguageTypeFromJson3 == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull3 = Util.unexpectedNull("oneTop", "oneTop", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull3, "unexpectedNull(...)");
                    throw jsonDataExceptionUnexpectedNull3;
                }
                i &= -5;
            } else if (iSelectName == 3) {
                qLanguageTypeFromJson4 = this.qLanguageTypeAdapter.fromJson(reader);
                if (qLanguageTypeFromJson4 == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull4 = Util.unexpectedNull("oneBottom", "oneBottom", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull4, "unexpectedNull(...)");
                    throw jsonDataExceptionUnexpectedNull4;
                }
                i &= -9;
            } else {
                continue;
            }
        }
        reader.endObject();
        if (i == -16) {
            Intrinsics.checkNotNull(qLanguageTypeFromJson, "null cannot be cast to non-null type com.glasssutdio.wear.all.bean.QLanguageType");
            Intrinsics.checkNotNull(qLanguageTypeFromJson2, "null cannot be cast to non-null type com.glasssutdio.wear.all.bean.QLanguageType");
            Intrinsics.checkNotNull(qLanguageTypeFromJson3, "null cannot be cast to non-null type com.glasssutdio.wear.all.bean.QLanguageType");
            Intrinsics.checkNotNull(qLanguageTypeFromJson4, "null cannot be cast to non-null type com.glasssutdio.wear.all.bean.QLanguageType");
            return new SelectLanguageBean(qLanguageTypeFromJson, qLanguageTypeFromJson2, qLanguageTypeFromJson3, qLanguageTypeFromJson4);
        }
        Constructor<SelectLanguageBean> declaredConstructor = this.constructorRef;
        if (declaredConstructor == null) {
            declaredConstructor = SelectLanguageBean.class.getDeclaredConstructor(QLanguageType.class, QLanguageType.class, QLanguageType.class, QLanguageType.class, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER);
            this.constructorRef = declaredConstructor;
            Intrinsics.checkNotNullExpressionValue(declaredConstructor, "also(...)");
        }
        SelectLanguageBean selectLanguageBeanNewInstance = declaredConstructor.newInstance(qLanguageTypeFromJson, qLanguageTypeFromJson2, qLanguageTypeFromJson3, qLanguageTypeFromJson4, Integer.valueOf(i), null);
        Intrinsics.checkNotNullExpressionValue(selectLanguageBeanNewInstance, "newInstance(...)");
        return selectLanguageBeanNewInstance;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, SelectLanguageBean value_) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        if (value_ == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        writer.beginObject();
        writer.name("listenerTop");
        this.qLanguageTypeAdapter.toJson(writer, (JsonWriter) value_.getListenerTop());
        writer.name("listenerBottom");
        this.qLanguageTypeAdapter.toJson(writer, (JsonWriter) value_.getListenerBottom());
        writer.name("oneTop");
        this.qLanguageTypeAdapter.toJson(writer, (JsonWriter) value_.getOneTop());
        writer.name("oneBottom");
        this.qLanguageTypeAdapter.toJson(writer, (JsonWriter) value_.getOneBottom());
        writer.endObject();
    }
}
