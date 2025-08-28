package com.glasssutdio.wear.stabilization;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: AwEisGyroJni.kt */
@Metadata(m606d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m607d2 = {"Lcom/glasssutdio/wear/stabilization/AwEisGyroJni;", "Lcom/glasssutdio/wear/stabilization/AwEisCommonJni;", "()V", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AwEisGyroJni extends AwEisCommonJni {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final native long nativeInit(int i, int i2, int i3, int i4, String str, String str2, String str3);

    @JvmStatic
    public static final native int nativeRelease(long j);

    @JvmStatic
    public static final native int nativeRun(long j, byte[] bArr, int i, byte[] bArr2, int i2);

    /* compiled from: AwEisGyroJni.kt */
    @Metadata(m606d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JA\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0087 J\u0011\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0004H\u0087 J1\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0006H\u0087 ¨\u0006\u0016"}, m607d2 = {"Lcom/glasssutdio/wear/stabilization/AwEisGyroJni$Companion;", "", "()V", "nativeInit", "", "inputWidth", "", "inputHeight", "outputWidth", "outputHeight", "cfgPath", "", "gyroPath", "stampPath", "nativeRelease", "context", "nativeRun", "inputArray", "", "inputFormat", "outputArray", "outputFormat", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final long nativeInit(int inputWidth, int inputHeight, int outputWidth, int outputHeight, String cfgPath, String gyroPath, String stampPath) {
            return AwEisGyroJni.nativeInit(inputWidth, inputHeight, outputWidth, outputHeight, cfgPath, gyroPath, stampPath);
        }

        @JvmStatic
        public final int nativeRelease(long context) {
            return AwEisGyroJni.nativeRelease(context);
        }

        @JvmStatic
        public final int nativeRun(long context, byte[] inputArray, int inputFormat, byte[] outputArray, int outputFormat) {
            return AwEisGyroJni.nativeRun(context, inputArray, inputFormat, outputArray, outputFormat);
        }

        private Companion() {
        }
    }
}
