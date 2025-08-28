package com.glasssutdio.wear.stabilization;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: AwEisImgJni.kt */
@Metadata(m606d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m607d2 = {"Lcom/glasssutdio/wear/stabilization/AwEisImgJni;", "Lcom/glasssutdio/wear/stabilization/AwEisCommonJni;", "()V", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AwEisImgJni extends AwEisCommonJni {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final native long nativeInit(int i, double d, int i2, int i3, String str);

    @JvmStatic
    public static final native int nativeRelease(long j);

    @JvmStatic
    public static final native int nativeRun(long j, byte[] bArr, int i, byte[] bArr2, int i2);

    /* compiled from: AwEisImgJni.kt */
    @Metadata(m606d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J1\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0087 J\u0011\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0004H\u0087 J1\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0006H\u0087 ¨\u0006\u0015"}, m607d2 = {"Lcom/glasssutdio/wear/stabilization/AwEisImgJni$Companion;", "", "()V", "nativeInit", "", "radius", "", "trimRatio", "", "width", "height", "licensePath", "", "nativeRelease", "context", "nativeRun", "inputArray", "", "inputFormat", "outputArray", "outputFormat", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final long nativeInit(int radius, double trimRatio, int width, int height, String licensePath) {
            return AwEisImgJni.nativeInit(radius, trimRatio, width, height, licensePath);
        }

        @JvmStatic
        public final int nativeRelease(long context) {
            return AwEisImgJni.nativeRelease(context);
        }

        @JvmStatic
        public final int nativeRun(long context, byte[] inputArray, int inputFormat, byte[] outputArray, int outputFormat) {
            return AwEisImgJni.nativeRun(context, inputArray, inputFormat, outputArray, outputFormat);
        }

        private Companion() {
        }
    }
}
