package com.google.mlkit.p012nl.languageid.bundled.internal;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_language_id_bundled.zba;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.p012nl.languageid.IdentifiedLanguage;
import com.google.mlkit.p012nl.languageid.LanguageIdentificationOptions;
import com.google.mlkit.p012nl.languageid.internal.LanguageIdentifierDelegate;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.mlkit:language-id@@17.0.4 */
/* loaded from: classes2.dex */
public class ThickLanguageIdentifier implements LanguageIdentifierDelegate {
    private static boolean zba;
    private final Context zbb;
    private long zbc;

    ThickLanguageIdentifier(Context context, LanguageIdentificationOptions languageIdentificationOptions) {
        this.zbb = context;
    }

    private native void nativeDestroy(long j);

    private native IdentifiedLanguage[] nativeIdentifyPossibleLanguages(long j, byte[] bArr, float f);

    private native long nativeInitFromBuffer(MappedByteBuffer mappedByteBuffer, long j);

    public static synchronized void zba() throws MlKitException {
        if (zba) {
            return;
        }
        try {
            System.loadLibrary("language_id_l2c_jni");
            zba = true;
        } catch (UnsatisfiedLinkError e) {
            throw new MlKitException("Couldn't load language identification library.", 13, e);
        }
    }

    @Override // com.google.mlkit.p012nl.languageid.internal.LanguageIdentifierDelegate
    public final List identifyPossibleLanguages(String str, float f) {
        Preconditions.checkState(this.zbc != 0);
        IdentifiedLanguage[] identifiedLanguageArrNativeIdentifyPossibleLanguages = nativeIdentifyPossibleLanguages(this.zbc, str.getBytes(zba.zbc), f);
        ArrayList arrayList = new ArrayList();
        for (IdentifiedLanguage identifiedLanguage : identifiedLanguageArrNativeIdentifyPossibleLanguages) {
            arrayList.add(new IdentifiedLanguage(identifiedLanguage.getLanguageTag(), identifiedLanguage.getConfidence()));
        }
        return arrayList;
    }

    @Override // com.google.mlkit.p012nl.languageid.internal.LanguageIdentifierDelegate
    public final void init() throws MlKitException, IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        Preconditions.checkState(this.zbc == 0);
        zba();
        try {
            AssetFileDescriptor assetFileDescriptorOpenFd = this.zbb.getAssets().openFd("tflite_langid.tflite.jpg");
            try {
                FileChannel channel = new FileInputStream(assetFileDescriptorOpenFd.getFileDescriptor()).getChannel();
                try {
                    long jNativeInitFromBuffer = nativeInitFromBuffer(channel.map(FileChannel.MapMode.READ_ONLY, assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getDeclaredLength()), assetFileDescriptorOpenFd.getDeclaredLength());
                    this.zbc = jNativeInitFromBuffer;
                    if (jNativeInitFromBuffer == 0) {
                        throw new MlKitException("Couldn't load language identification model", 13);
                    }
                    if (channel != null) {
                        channel.close();
                    }
                    if (assetFileDescriptorOpenFd != null) {
                        assetFileDescriptorOpenFd.close();
                    }
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            throw new MlKitException("Couldn't open language identification model file", 13, e);
        }
    }

    @Override // com.google.mlkit.p012nl.languageid.internal.LanguageIdentifierDelegate
    public final void release() {
        long j = this.zbc;
        if (j == 0) {
            return;
        }
        nativeDestroy(j);
        this.zbc = 0L;
    }
}
