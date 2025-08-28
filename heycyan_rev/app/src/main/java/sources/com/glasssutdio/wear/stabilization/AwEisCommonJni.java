package com.glasssutdio.wear.stabilization;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: AwEisCommonJni.kt */
@Metadata(m606d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m607d2 = {"Lcom/glasssutdio/wear/stabilization/AwEisCommonJni;", "", "()V", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public class AwEisCommonJni {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final native void nativeInit(String str);

    /* compiled from: AwEisCommonJni.kt */
    @Metadata(m606d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0087 ¨\u0006\u0007"}, m607d2 = {"Lcom/glasssutdio/wear/stabilization/AwEisCommonJni$Companion;", "", "()V", "nativeInit", "", "cachePath", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final void nativeInit(String cachePath) {
            AwEisCommonJni.nativeInit(cachePath);
        }

        private Companion() {
        }
    }

    static {
        System.loadLibrary("aweis_jni");
    }
}
