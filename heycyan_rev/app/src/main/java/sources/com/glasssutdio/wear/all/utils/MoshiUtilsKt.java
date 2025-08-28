package com.glasssutdio.wear.all.utils;

import androidx.exifinterface.media.ExifInterface;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MoshiUtils.kt */
@Metadata(m606d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a \u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u0003H\u0086\b¢\u0006\u0002\u0010\u0004\u001a\n\u0010\u0005\u001a\u00020\u0003*\u00020\u0002¨\u0006\u0006"}, m607d2 = {"fromJson", ExifInterface.GPS_DIRECTION_TRUE, "", "", "(Ljava/lang/String;)Ljava/lang/Object;", "toJson", "app_release"}, m608k = 2, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class MoshiUtilsKt {
    public static final /* synthetic */ <T> T fromJson(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Moshi moshiBuild = MoshiUtils.INSTANCE.getMoshiBuild();
        Intrinsics.needClassReification();
        JsonAdapter<T> jsonAdapterAdapter = moshiBuild.adapter(new TypeToken<T>() { // from class: com.glasssutdio.wear.all.utils.MoshiUtilsKt$fromJson$$inlined$fromJson$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "adapter(...)");
        return jsonAdapterAdapter.fromJson(str);
    }

    public static final String toJson(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<Object>() { // from class: com.glasssutdio.wear.all.utils.MoshiUtilsKt$toJson$$inlined$toJson$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "adapter(...)");
        String json = jsonAdapterAdapter.toJson(obj);
        if (json == null) {
            return "";
        }
        Intrinsics.checkNotNull(json);
        return json;
    }
}
