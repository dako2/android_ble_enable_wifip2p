package com.glasssutdio.wear.all.pref;

import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.config.CookieSpecs;
import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MMKVConfig.kt */
@Metadata(m606d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0011\u0018\u0000 &2\u00020\u0001:\u0001&B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\nJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u0010J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u0012J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u0014J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u0016J \u0010\u0017\u001a\u0004\u0018\u0001H\u0018\"\u0006\b\u0000\u0010\u0018\u0018\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0086\b¢\u0006\u0002\u0010\u0019J\u0018\u0010\u001a\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\fJ\u0016\u0010\u001b\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\nJ\u0016\u0010\u001d\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u0010J\u0016\u0010\u001e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u0012J\u0016\u0010\u001f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u0014J\u0016\u0010 \u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u0016J&\u0010!\u001a\u00020\b\"\u0006\b\u0000\u0010\u0018\u0018\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\"\u001a\u0002H\u0018H\u0086\b¢\u0006\u0002\u0010#J\u0016\u0010$\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\fJ\u000e\u0010%\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006'"}, m607d2 = {"Lcom/glasssutdio/wear/all/pref/MMKVConfig;", "", "()V", "mmkv", "Lcom/tencent/mmkv/MMKV;", "getMmkv", "()Lcom/tencent/mmkv/MMKV;", "clearAll", "", "contains", "", "key", "", "getBoolean", CookieSpecs.DEFAULT, "getDouble", "", "getFloat", "", "getInt", "", "getLong", "", "getObject", ExifInterface.GPS_DIRECTION_TRUE, "(Ljava/lang/String;)Ljava/lang/Object;", "getString", "putBoolean", "value", "putDouble", "putFloat", "putInt", "putLong", "putObject", "obj", "(Ljava/lang/String;Ljava/lang/Object;)V", "putString", "remove", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class MMKVConfig {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<MMKVConfig> instance$delegate = LazyKt.lazy(new Function0<MMKVConfig>() { // from class: com.glasssutdio.wear.all.pref.MMKVConfig$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MMKVConfig invoke() {
            return new MMKVConfig(null);
        }
    });
    private final MMKV mmkv;

    public /* synthetic */ MMKVConfig(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private MMKVConfig() throws RuntimeException {
        MMKV mmkvMmkvWithID = MMKV.mmkvWithID("app_config", 2, "glass_51888");
        Intrinsics.checkNotNullExpressionValue(mmkvMmkvWithID, "mmkvWithID(...)");
        this.mmkv = mmkvMmkvWithID;
    }

    public final MMKV getMmkv() {
        return this.mmkv;
    }

    /* compiled from: MMKVConfig.kt */
    @Metadata(m606d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/all/pref/MMKVConfig$Companion;", "", "()V", "instance", "Lcom/glasssutdio/wear/all/pref/MMKVConfig;", "getInstance", "()Lcom/glasssutdio/wear/all/pref/MMKVConfig;", "instance$delegate", "Lkotlin/Lazy;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MMKVConfig getInstance() {
            return (MMKVConfig) MMKVConfig.instance$delegate.getValue();
        }
    }

    public final boolean putBoolean(String key, boolean value) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.mmkv.encode(key, value);
    }

    public static /* synthetic */ boolean getBoolean$default(MMKVConfig mMKVConfig, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return mMKVConfig.getBoolean(str, z);
    }

    public final boolean getBoolean(String key, boolean z) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.mmkv.decodeBool(key, z);
    }

    public final boolean putInt(String key, int value) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.mmkv.encode(key, value);
    }

    public static /* synthetic */ int getInt$default(MMKVConfig mMKVConfig, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return mMKVConfig.getInt(str, i);
    }

    public final int getInt(String key, int i) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.mmkv.decodeInt(key, i);
    }

    public final boolean putLong(String key, long value) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.mmkv.encode(key, value);
    }

    public static /* synthetic */ long getLong$default(MMKVConfig mMKVConfig, String str, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        return mMKVConfig.getLong(str, j);
    }

    public final long getLong(String key, long j) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.mmkv.decodeLong(key, j);
    }

    public final boolean putFloat(String key, float value) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.mmkv.encode(key, value);
    }

    public static /* synthetic */ float getFloat$default(MMKVConfig mMKVConfig, String str, float f, int i, Object obj) {
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        return mMKVConfig.getFloat(str, f);
    }

    public final float getFloat(String key, float f) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.mmkv.decodeFloat(key, f);
    }

    public final boolean putDouble(String key, double value) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.mmkv.encode(key, value);
    }

    public static /* synthetic */ double getDouble$default(MMKVConfig mMKVConfig, String str, double d, int i, Object obj) {
        if ((i & 2) != 0) {
            d = 0.0d;
        }
        return mMKVConfig.getDouble(str, d);
    }

    public final double getDouble(String key, double d) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.mmkv.decodeDouble(key, d);
    }

    public final boolean putString(String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        return this.mmkv.encode(key, value);
    }

    public static /* synthetic */ String getString$default(MMKVConfig mMKVConfig, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        return mMKVConfig.getString(str, str2);
    }

    public final String getString(String key, String str) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(str, "default");
        String strDecodeString = this.mmkv.decodeString(key, str);
        return strDecodeString == null ? str : strDecodeString;
    }

    public final /* synthetic */ <T> void putObject(String key, T obj) {
        Intrinsics.checkNotNullParameter(key, "key");
        getMmkv().encode(key, new Gson().toJson(obj));
    }

    public final /* synthetic */ <T> T getObject(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            Gson gson = new Gson();
            String strDecodeString = getMmkv().decodeString(key);
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return (T) gson.fromJson(strDecodeString, (Class) Object.class);
        } catch (Exception unused) {
            return null;
        }
    }

    public final boolean contains(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.mmkv.containsKey(key);
    }

    public final void remove(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.mmkv.removeValueForKey(key);
    }

    public final void clearAll() {
        this.mmkv.clearAll();
    }
}
