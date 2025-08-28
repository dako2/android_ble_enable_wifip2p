package com.glasssutdio.wear.api;

import android.util.Log;
import androidx.core.os.EnvironmentCompat;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.p012nl.languageid.LanguageIdentification;
import com.google.mlkit.p012nl.languageid.LanguageIdentifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LanguageDetectionUtil.kt */
@Metadata(m606d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00060\tJ\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\r"}, m607d2 = {"Lcom/glasssutdio/wear/api/LanguageTTSMappingUtil;", "", "()V", "TAG", "", "detectLanguageAndMapTTS", "", "text", "callback", "Lkotlin/Function1;", "Lcom/glasssutdio/wear/api/LanguageTTSMappingUtil$LanguageTTSResult;", "detectLanguageByUnicode", "LanguageTTSResult", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class LanguageTTSMappingUtil {
    public static final LanguageTTSMappingUtil INSTANCE = new LanguageTTSMappingUtil();
    private static final String TAG = "LanguageTTSMappingUtil";

    private LanguageTTSMappingUtil() {
    }

    /* compiled from: LanguageDetectionUtil.kt */
    @Metadata(m606d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, m607d2 = {"Lcom/glasssutdio/wear/api/LanguageTTSMappingUtil$LanguageTTSResult;", "", "detectedLang", "", "(Ljava/lang/String;)V", "getDetectedLang", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final /* data */ class LanguageTTSResult {
        private final String detectedLang;

        public static /* synthetic */ LanguageTTSResult copy$default(LanguageTTSResult languageTTSResult, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = languageTTSResult.detectedLang;
            }
            return languageTTSResult.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getDetectedLang() {
            return this.detectedLang;
        }

        public final LanguageTTSResult copy(String detectedLang) {
            Intrinsics.checkNotNullParameter(detectedLang, "detectedLang");
            return new LanguageTTSResult(detectedLang);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof LanguageTTSResult) && Intrinsics.areEqual(this.detectedLang, ((LanguageTTSResult) other).detectedLang);
        }

        public int hashCode() {
            return this.detectedLang.hashCode();
        }

        public String toString() {
            return "LanguageTTSResult(detectedLang=" + this.detectedLang + ')';
        }

        public LanguageTTSResult(String detectedLang) {
            Intrinsics.checkNotNullParameter(detectedLang, "detectedLang");
            this.detectedLang = detectedLang;
        }

        public final String getDetectedLang() {
            return this.detectedLang;
        }
    }

    public final void detectLanguageAndMapTTS(String text, final Function1<? super LanguageTTSResult, Unit> callback) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(callback, "callback");
        LanguageIdentifier client = LanguageIdentification.getClient();
        Intrinsics.checkNotNullExpressionValue(client, "getClient(...)");
        Task<String> taskIdentifyLanguage = client.identifyLanguage(text);
        final Function1<String, Unit> function1 = new Function1<String, Unit>() { // from class: com.glasssutdio.wear.api.LanguageTTSMappingUtil.detectLanguageAndMapTTS.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String str) {
                if (Intrinsics.areEqual(str, LanguageIdentifier.UNDETERMINED_LANGUAGE_TAG)) {
                    str = EnvironmentCompat.MEDIA_UNKNOWN;
                }
                Function1<LanguageTTSResult, Unit> function12 = callback;
                Intrinsics.checkNotNull(str);
                function12.invoke(new LanguageTTSResult(str));
            }
        };
        taskIdentifyLanguage.addOnSuccessListener(new OnSuccessListener() { // from class: com.glasssutdio.wear.api.LanguageTTSMappingUtil$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                LanguageTTSMappingUtil.detectLanguageAndMapTTS$lambda$0(function1, obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.glasssutdio.wear.api.LanguageTTSMappingUtil$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                LanguageTTSMappingUtil.detectLanguageAndMapTTS$lambda$1(callback, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detectLanguageAndMapTTS$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detectLanguageAndMapTTS$lambda$1(Function1 callback, Exception e) {
        Intrinsics.checkNotNullParameter(callback, "$callback");
        Intrinsics.checkNotNullParameter(e, "e");
        Log.e(TAG, "语言检测失败", e);
        callback.invoke(new LanguageTTSResult(EnvironmentCompat.MEDIA_UNKNOWN));
    }

    public final String detectLanguageByUnicode(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (text.length() <= 0) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        char cCharAt = text.charAt(0);
        if (19968 <= cCharAt && cCharAt < 40960) {
            return "zh";
        }
        if (12352 <= cCharAt && cCharAt < 12544) {
            return "ja";
        }
        if (44032 <= cCharAt && cCharAt < 55216) {
            return "ko";
        }
        if (2304 <= cCharAt && cCharAt < 2432) {
            return "hi";
        }
        if (1536 > cCharAt || cCharAt >= 1792) {
            return (1024 > cCharAt || cCharAt >= 1280) ? EnvironmentCompat.MEDIA_UNKNOWN : "ru";
        }
        return "ar";
    }
}
