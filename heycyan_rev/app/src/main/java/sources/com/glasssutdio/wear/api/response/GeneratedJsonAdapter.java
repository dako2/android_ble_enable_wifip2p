package com.glasssutdio.wear.api.response;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.ClientCookie;
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

/* compiled from: FirmwareOtaRespJsonAdapter.kt */
@Metadata(m606d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m607d2 = {"Lcom/glasssutdio/wear/api/response/FirmwareOtaRespJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/glasssutdio/wear/api/response/FirmwareOtaResp;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "intAdapter", "", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* renamed from: com.glasssutdio.wear.api.response.FirmwareOtaRespJsonAdapter, reason: from toString */
/* loaded from: classes.dex */
public final class GeneratedJsonAdapter extends JsonAdapter<FirmwareOtaResp> {
    private final JsonAdapter<Integer> intAdapter;
    private final JsonReader.Options options;
    private final JsonAdapter<String> stringAdapter;

    public GeneratedJsonAdapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options optionsM593of = JsonReader.Options.m593of("hardwareVersion", ClientCookie.VERSION_ATTR, "enforceUpdateFrom", "enforceUpdateTo", "isEnforceUpdate", "downloadUrl", "openOrNot", "uploadDate", "os", "updateDesc");
        Intrinsics.checkNotNullExpressionValue(optionsM593of, "of(...)");
        this.options = optionsM593of;
        JsonAdapter<String> jsonAdapterAdapter = moshi.adapter(String.class, SetsKt.emptySet(), "hardwareVersion");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "adapter(...)");
        this.stringAdapter = jsonAdapterAdapter;
        JsonAdapter<Integer> jsonAdapterAdapter2 = moshi.adapter(Integer.TYPE, SetsKt.emptySet(), "openOrNot");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "adapter(...)");
        this.intAdapter = jsonAdapterAdapter2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(37);
        sb.append("GeneratedJsonAdapter(FirmwareOtaResp)");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.JsonAdapter
    public FirmwareOtaResp fromJson(JsonReader reader) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        reader.beginObject();
        String hardwareVersion = null;
        String version = null;
        String enforceUpdateFrom = null;
        String enforceUpdateTo = null;
        String isEnforceUpdate = null;
        String downloadUrl = null;
        Integer numFromJson = null;
        String uploadDate = null;
        Integer numFromJson2 = null;
        String updateDesc = null;
        while (reader.hasNext()) {
            switch (reader.selectName(this.options)) {
                case -1:
                    reader.skipName();
                    reader.skipValue();
                    break;
                case 0:
                    hardwareVersion = this.stringAdapter.fromJson(reader);
                    if (hardwareVersion == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull = Util.unexpectedNull("hardwareVersion", "hardwareVersion", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull, "unexpectedNull(...)");
                        throw jsonDataExceptionUnexpectedNull;
                    }
                    break;
                case 1:
                    version = this.stringAdapter.fromJson(reader);
                    if (version == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull2 = Util.unexpectedNull(ClientCookie.VERSION_ATTR, ClientCookie.VERSION_ATTR, reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull2, "unexpectedNull(...)");
                        throw jsonDataExceptionUnexpectedNull2;
                    }
                    break;
                case 2:
                    enforceUpdateFrom = this.stringAdapter.fromJson(reader);
                    if (enforceUpdateFrom == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull3 = Util.unexpectedNull("enforceUpdateFrom", "enforceUpdateFrom", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull3, "unexpectedNull(...)");
                        throw jsonDataExceptionUnexpectedNull3;
                    }
                    break;
                case 3:
                    enforceUpdateTo = this.stringAdapter.fromJson(reader);
                    if (enforceUpdateTo == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull4 = Util.unexpectedNull("enforceUpdateTo", "enforceUpdateTo", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull4, "unexpectedNull(...)");
                        throw jsonDataExceptionUnexpectedNull4;
                    }
                    break;
                case 4:
                    isEnforceUpdate = this.stringAdapter.fromJson(reader);
                    if (isEnforceUpdate == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull5 = Util.unexpectedNull("isEnforceUpdate", "isEnforceUpdate", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull5, "unexpectedNull(...)");
                        throw jsonDataExceptionUnexpectedNull5;
                    }
                    break;
                case 5:
                    downloadUrl = this.stringAdapter.fromJson(reader);
                    if (downloadUrl == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull6 = Util.unexpectedNull("downloadUrl", "downloadUrl", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull6, "unexpectedNull(...)");
                        throw jsonDataExceptionUnexpectedNull6;
                    }
                    break;
                case 6:
                    numFromJson = this.intAdapter.fromJson(reader);
                    if (numFromJson == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull7 = Util.unexpectedNull("openOrNot", "openOrNot", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull7, "unexpectedNull(...)");
                        throw jsonDataExceptionUnexpectedNull7;
                    }
                    break;
                case 7:
                    uploadDate = this.stringAdapter.fromJson(reader);
                    if (uploadDate == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull8 = Util.unexpectedNull("uploadDate", "uploadDate", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull8, "unexpectedNull(...)");
                        throw jsonDataExceptionUnexpectedNull8;
                    }
                    break;
                case 8:
                    numFromJson2 = this.intAdapter.fromJson(reader);
                    if (numFromJson2 == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull9 = Util.unexpectedNull("os", "os", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull9, "unexpectedNull(...)");
                        throw jsonDataExceptionUnexpectedNull9;
                    }
                    break;
                case 9:
                    updateDesc = this.stringAdapter.fromJson(reader);
                    if (updateDesc == null) {
                        JsonDataException jsonDataExceptionUnexpectedNull10 = Util.unexpectedNull("updateDesc", "updateDesc", reader);
                        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull10, "unexpectedNull(...)");
                        throw jsonDataExceptionUnexpectedNull10;
                    }
                    break;
            }
        }
        reader.endObject();
        FirmwareOtaResp firmwareOtaResp = new FirmwareOtaResp();
        if (hardwareVersion == null) {
            hardwareVersion = firmwareOtaResp.getHardwareVersion();
        }
        firmwareOtaResp.setHardwareVersion(hardwareVersion);
        if (version == null) {
            version = firmwareOtaResp.getVersion();
        }
        firmwareOtaResp.setVersion(version);
        if (enforceUpdateFrom == null) {
            enforceUpdateFrom = firmwareOtaResp.getEnforceUpdateFrom();
        }
        firmwareOtaResp.setEnforceUpdateFrom(enforceUpdateFrom);
        if (enforceUpdateTo == null) {
            enforceUpdateTo = firmwareOtaResp.getEnforceUpdateTo();
        }
        firmwareOtaResp.setEnforceUpdateTo(enforceUpdateTo);
        if (isEnforceUpdate == null) {
            isEnforceUpdate = firmwareOtaResp.getIsEnforceUpdate();
        }
        firmwareOtaResp.setEnforceUpdate(isEnforceUpdate);
        if (downloadUrl == null) {
            downloadUrl = firmwareOtaResp.getDownloadUrl();
        }
        firmwareOtaResp.setDownloadUrl(downloadUrl);
        firmwareOtaResp.setOpenOrNot(numFromJson != null ? numFromJson.intValue() : firmwareOtaResp.getOpenOrNot());
        if (uploadDate == null) {
            uploadDate = firmwareOtaResp.getUploadDate();
        }
        firmwareOtaResp.setUploadDate(uploadDate);
        firmwareOtaResp.setOs(numFromJson2 != null ? numFromJson2.intValue() : firmwareOtaResp.getOs());
        if (updateDesc == null) {
            updateDesc = firmwareOtaResp.getUpdateDesc();
        }
        firmwareOtaResp.setUpdateDesc(updateDesc);
        return firmwareOtaResp;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, FirmwareOtaResp value_) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        if (value_ == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        writer.beginObject();
        writer.name("hardwareVersion");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getHardwareVersion());
        writer.name(ClientCookie.VERSION_ATTR);
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getVersion());
        writer.name("enforceUpdateFrom");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getEnforceUpdateFrom());
        writer.name("enforceUpdateTo");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getEnforceUpdateTo());
        writer.name("isEnforceUpdate");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getIsEnforceUpdate());
        writer.name("downloadUrl");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getDownloadUrl());
        writer.name("openOrNot");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getOpenOrNot()));
        writer.name("uploadDate");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getUploadDate());
        writer.name("os");
        this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(value_.getOs()));
        writer.name("updateDesc");
        this.stringAdapter.toJson(writer, (JsonWriter) value_.getUpdateDesc());
        writer.endObject();
    }
}
