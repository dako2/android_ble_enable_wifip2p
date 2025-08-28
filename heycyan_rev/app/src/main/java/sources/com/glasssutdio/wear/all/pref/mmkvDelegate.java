package com.glasssutdio.wear.all.pref;

import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.config.CookieSpecs;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

/* compiled from: MMKVConfig.kt */
@Metadata(m606d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ$\u0010\n\u001a\u00028\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rH\u0086\u0002¢\u0006\u0002\u0010\u000eJ,\u0010\u000f\u001a\u00020\u00102\b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\u0011\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0002\u0010\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m607d2 = {"Lcom/glasssutdio/wear/all/pref/mmkvDelegate;", ExifInterface.GPS_DIRECTION_TRUE, "", "config", "Lcom/glasssutdio/wear/all/pref/MMKVConfig;", CookieSpecs.DEFAULT, "key", "", "(Lcom/glasssutdio/wear/all/pref/MMKVConfig;Ljava/lang/Object;Ljava/lang/String;)V", "Ljava/lang/Object;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "value", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class mmkvDelegate<T> {
    private final MMKVConfig config;
    private final T default;
    private final String key;

    public mmkvDelegate(MMKVConfig config, T t, String str) {
        Intrinsics.checkNotNullParameter(config, "config");
        this.config = config;
        this.default = t;
        this.key = str;
    }

    public /* synthetic */ mmkvDelegate(MMKVConfig mMKVConfig, Object obj, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(mMKVConfig, obj, (i & 4) != 0 ? null : str);
    }

    public final T getValue(Object thisRef, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(property, "property");
        T t = this.default;
        if (t instanceof Boolean) {
            MMKVConfig mMKVConfig = this.config;
            String name = this.key;
            if (name == null) {
                name = property.getName();
            }
            T t2 = this.default;
            Intrinsics.checkNotNull(t2, "null cannot be cast to non-null type kotlin.Boolean");
            return (T) Boolean.valueOf(mMKVConfig.getBoolean(name, ((Boolean) t2).booleanValue()));
        }
        if (t instanceof Integer) {
            MMKVConfig mMKVConfig2 = this.config;
            String name2 = this.key;
            if (name2 == null) {
                name2 = property.getName();
            }
            T t3 = this.default;
            Intrinsics.checkNotNull(t3, "null cannot be cast to non-null type kotlin.Int");
            return (T) Integer.valueOf(mMKVConfig2.getInt(name2, ((Integer) t3).intValue()));
        }
        if (t instanceof Long) {
            MMKVConfig mMKVConfig3 = this.config;
            String name3 = this.key;
            if (name3 == null) {
                name3 = property.getName();
            }
            T t4 = this.default;
            Intrinsics.checkNotNull(t4, "null cannot be cast to non-null type kotlin.Long");
            return (T) Long.valueOf(mMKVConfig3.getLong(name3, ((Long) t4).longValue()));
        }
        if (t instanceof Float) {
            MMKVConfig mMKVConfig4 = this.config;
            String name4 = this.key;
            if (name4 == null) {
                name4 = property.getName();
            }
            T t5 = this.default;
            Intrinsics.checkNotNull(t5, "null cannot be cast to non-null type kotlin.Float");
            return (T) Float.valueOf(mMKVConfig4.getFloat(name4, ((Float) t5).floatValue()));
        }
        if (t instanceof Double) {
            MMKVConfig mMKVConfig5 = this.config;
            String name5 = this.key;
            if (name5 == null) {
                name5 = property.getName();
            }
            T t6 = this.default;
            Intrinsics.checkNotNull(t6, "null cannot be cast to non-null type kotlin.Double");
            return (T) Double.valueOf(mMKVConfig5.getDouble(name5, ((Double) t6).doubleValue()));
        }
        if (!(t instanceof String)) {
            throw new IllegalArgumentException("Unsupported type");
        }
        MMKVConfig mMKVConfig6 = this.config;
        String name6 = this.key;
        if (name6 == null) {
            name6 = property.getName();
        }
        T t7 = this.default;
        Intrinsics.checkNotNull(t7, "null cannot be cast to non-null type kotlin.String");
        return (T) mMKVConfig6.getString(name6, (String) t7);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setValue(Object thisRef, KProperty<?> property, T value) {
        Intrinsics.checkNotNullParameter(property, "property");
        if (value instanceof Boolean) {
            MMKVConfig mMKVConfig = this.config;
            String name = this.key;
            if (name == null) {
                name = property.getName();
            }
            mMKVConfig.putBoolean(name, ((Boolean) value).booleanValue());
            return;
        }
        if (value instanceof Integer) {
            MMKVConfig mMKVConfig2 = this.config;
            String name2 = this.key;
            if (name2 == null) {
                name2 = property.getName();
            }
            mMKVConfig2.putInt(name2, ((Number) value).intValue());
            return;
        }
        if (value instanceof Long) {
            MMKVConfig mMKVConfig3 = this.config;
            String name3 = this.key;
            if (name3 == null) {
                name3 = property.getName();
            }
            mMKVConfig3.putLong(name3, ((Number) value).longValue());
            return;
        }
        if (value instanceof Float) {
            MMKVConfig mMKVConfig4 = this.config;
            String name4 = this.key;
            if (name4 == null) {
                name4 = property.getName();
            }
            mMKVConfig4.putFloat(name4, ((Number) value).floatValue());
            return;
        }
        if (value instanceof Double) {
            MMKVConfig mMKVConfig5 = this.config;
            String name5 = this.key;
            if (name5 == null) {
                name5 = property.getName();
            }
            mMKVConfig5.putDouble(name5, ((Number) value).doubleValue());
            return;
        }
        if (!(value instanceof String)) {
            throw new IllegalArgumentException("Unsupported type");
        }
        MMKVConfig mMKVConfig6 = this.config;
        String name6 = this.key;
        if (name6 == null) {
            name6 = property.getName();
        }
        mMKVConfig6.putString(name6, (String) value);
    }
}
