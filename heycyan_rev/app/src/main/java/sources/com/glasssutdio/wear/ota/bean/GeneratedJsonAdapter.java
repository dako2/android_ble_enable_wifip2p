package com.glasssutdio.wear.ota.bean;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DFUInformationBeanJsonAdapter.kt */
@Metadata(m606d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m607d2 = {"Lcom/glasssutdio/wear/ota/bean/DFUInformationBeanJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/glasssutdio/wear/ota/bean/DFUInformationBean;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "intAdapter", "", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* renamed from: com.glasssutdio.wear.ota.bean.DFUInformationBeanJsonAdapter, reason: from toString */
/* loaded from: classes.dex */
public final class GeneratedJsonAdapter extends JsonAdapter<DFUInformationBean> {
    private final JsonAdapter<Integer> intAdapter;
    private final JsonReader.Options options;
    private final JsonAdapter<String> stringAdapter;

    public GeneratedJsonAdapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options optionsM593of = JsonReader.Options.m593of("hardwareVersion", "lastVersion", "isEnforceUpdate", "downloadUrl", "openOrNot");
        Intrinsics.checkNotNullExpressionValue(optionsM593of, "of(...)");
        this.options = optionsM593of;
        JsonAdapter<String> jsonAdapterAdapter = moshi.adapter(String.class, SetsKt.emptySet(), "hardwareVersion");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "adapter(...)");
        this.stringAdapter = jsonAdapterAdapter;
        JsonAdapter<Integer> jsonAdapterAdapter2 = moshi.adapter(Integer.TYPE, SetsKt.emptySet(), "isEnforceUpdate");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "adapter(...)");
        this.intAdapter = jsonAdapterAdapter2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("GeneratedJsonAdapter(DFUInformationBean)");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.JsonAdapter
    public DFUInformationBean fromJson(JsonReader reader) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        reader.beginObject();
        String hardwareVersion = null;
        String lastVersion = null;
        Integer numFromJson = null;
        String downloadUrl = null;
        Integer numFromJson2 = null;
        while (reader.hasNext()) {
            int iSelectName = reader.selectName(this.options);
            if (iSelectName == -1) {
                reader.skipName();
                reader.skipValue();
            } else if (iSelectName == 0) {
                hardwareVersion = this.stringAdapter.fromJson(reader);
                if (hardwareVersion == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull = Util.unexpectedNull("hardwareVersion", "hardwareVersion", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull, "unexpectedNull(...)");
                    throw jsonDataExceptionUnexpectedNull;
                }
            } else if (iSelectName == 1) {
                lastVersion = this.stringAdapter.fromJson(reader);
                if (lastVersion == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull2 = Util.unexpectedNull("lastVersion", "lastVersion", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull2, "unexpectedNull(...)");
                    throw jsonDataExceptionUnexpectedNull2;
                }
            } else if (iSelectName == 2) {
                numFromJson = this.intAdapter.fromJson(reader);
                if (numFromJson == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull3 = Util.unexpectedNull("isEnforceUpdate", "isEnforceUpdate", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull3, "unexpectedNull(...)");
                    throw jsonDataExceptionUnexpectedNull3;
                }
            } else if (iSelectName == 3) {
                downloadUrl = this.stringAdapter.fromJson(reader);
                if (downloadUrl == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull4 = Util.unexpectedNull("downloadUrl", "downloadUrl", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull4, "unexpectedNull(...)");
                    throw jsonDataExceptionUnexpectedNull4;
                }
            } else if (iSelectName == 4 && (numFromJson2 = this.intAdapter.fromJson(reader)) == null) {
                JsonDataException jsonDataExceptionUnexpectedNull5 = Util.unexpectedNull("openOrNot", "openOrNot", reader);
                Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull5, "unexpectedNull(...)");
                throw jsonDataExceptionUnexpectedNull5;
            }
        }
        reader.endObject();
        DFUInformationBean dFUInformationBean = new DFUInformationBean();
        if (hardwareVersion == null) {
            hardwareVersion = dFUInformationBean.getHardwareVersion();
        }
        dFUInformationBean.setHardwareVersion(hardwareVersion);
        if (lastVersion == null) {
            lastVersion = dFUInformationBean.getLastVersion();
        }
        dFUInformationBean.setLastVersion(lastVersion);
        dFUInformationBean.setEnforceUpdate(numFromJson != null ? numFromJson.intValue() : dFUInformationBean.getIsEnforceUpdate());
        if (downloadUrl == null) {
            downloadUrl = dFUInformationBean.getDownloadUrl();
        }
        dFUInformationBean.setDownloadUrl(downloadUrl);
        dFUInformationBean.setOpenOrNot(numFromJson2 != null ? numFromJson2.intValue() : dFUInformationBean.getOpenOrNot());
        return dFUInformationBean;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, DFUInformationBean value_) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        if (value_ == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        writer.beginObject();
        writer.name("hardwareVersion");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getHardwareVersion());
        writer.name("lastVersion");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getLastVersion());
        writer.name("isEnforceUpdate");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getIsEnforceUpdate()));
        writer.name("downloadUrl");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getDownloadUrl());
        writer.name("openOrNot");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getOpenOrNot()));
        writer.endObject();
    }
}
