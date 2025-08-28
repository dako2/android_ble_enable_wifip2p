package com.google.mlkit.p012nl.languageid.internal;

import android.os.SystemClock;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_language_id_common.zzhj;
import com.google.android.gms.internal.mlkit_language_id_common.zzhp;
import com.google.android.gms.internal.mlkit_language_id_common.zzhr;
import com.google.android.gms.internal.mlkit_language_id_common.zzht;
import com.google.android.gms.internal.mlkit_language_id_common.zzhu;
import com.google.android.gms.internal.mlkit_language_id_common.zzhv;
import com.google.android.gms.internal.mlkit_language_id_common.zzhw;
import com.google.android.gms.internal.mlkit_language_id_common.zzir;
import com.google.android.gms.internal.mlkit_language_id_common.zzis;
import com.google.android.gms.internal.mlkit_language_id_common.zziv;
import com.google.android.gms.internal.mlkit_language_id_common.zzix;
import com.google.android.gms.internal.mlkit_language_id_common.zziy;
import com.google.android.gms.internal.mlkit_language_id_common.zzja;
import com.google.android.gms.internal.mlkit_language_id_common.zzku;
import com.google.android.gms.internal.mlkit_language_id_common.zzlc;
import com.google.android.gms.internal.mlkit_language_id_common.zzle;
import com.google.android.gms.internal.mlkit_language_id_common.zzlf;
import com.google.android.gms.internal.mlkit_language_id_common.zzln;
import com.google.android.gms.internal.mlkit_language_id_common.zzr;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.p012nl.languageid.IdentifiedLanguage;
import com.google.mlkit.p012nl.languageid.LanguageIdentificationOptions;
import com.google.mlkit.p012nl.languageid.LanguageIdentifier;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
/* loaded from: classes2.dex */
public class LanguageIdentifierImpl implements LanguageIdentifier {
    private final LanguageIdentificationOptions zza;
    private final zzlc zzb;
    private final zzle zzc;
    private final Executor zzd;
    private final AtomicReference zze;
    private final CancellationTokenSource zzf = new CancellationTokenSource();
    private final zzht zzg;

    /* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
    public static final class Factory {
        private final zzlc zza;
        private final zzg zzb;
        private final ExecutorSelector zzc;

        public Factory(zzg zzgVar, ExecutorSelector executorSelector) {
            this.zzb = zzgVar;
            this.zzc = executorSelector;
            this.zza = zzln.zzb(true != zzgVar.zzf() ? "play-services-mlkit-language-id" : "language-id");
        }

        public LanguageIdentifier create(LanguageIdentificationOptions languageIdentificationOptions) {
            this.zzb.zze(languageIdentificationOptions);
            return LanguageIdentifierImpl.zza(languageIdentificationOptions, this.zzb, this.zza, this.zzc);
        }
    }

    private LanguageIdentifierImpl(LanguageIdentificationOptions languageIdentificationOptions, zzg zzgVar, zzlc zzlcVar, Executor executor) {
        this.zza = languageIdentificationOptions;
        this.zzb = zzlcVar;
        this.zzd = executor;
        this.zze = new AtomicReference(zzgVar);
        this.zzg = zzgVar.zzf() ? zzht.TYPE_THICK : zzht.TYPE_THIN;
        this.zzc = zzle.zza(MlKitContext.getInstance().getApplicationContext());
    }

    public static LanguageIdentifier zza(LanguageIdentificationOptions languageIdentificationOptions, zzg zzgVar, zzlc zzlcVar, ExecutorSelector executorSelector) {
        LanguageIdentifierImpl languageIdentifierImpl = new LanguageIdentifierImpl(languageIdentificationOptions, zzgVar, zzlcVar, executorSelector.getExecutorToUse(languageIdentificationOptions.getExecutor()));
        zzlc zzlcVar2 = languageIdentifierImpl.zzb;
        zzhw zzhwVar = new zzhw();
        zzhwVar.zzc(languageIdentifierImpl.zzg);
        zzir zzirVar = new zzir();
        zzirVar.zzf(zzf(languageIdentifierImpl.zza.getConfidenceThreshold()));
        zzhwVar.zze(zzirVar.zzi());
        zzlcVar2.zzc(zzlf.zzg(zzhwVar, 1), zzhv.ON_DEVICE_LANGUAGE_IDENTIFICATION_CREATE);
        ((zzg) languageIdentifierImpl.zze.get()).pin();
        return languageIdentifierImpl;
    }

    private final void zze(long j, boolean z, zzja zzjaVar, zzix zzixVar, zzhu zzhuVar) {
        long jElapsedRealtime = SystemClock.elapsedRealtime() - j;
        this.zzb.zze(new zzf(this, jElapsedRealtime, z, zzhuVar, zzjaVar, zzixVar), zzhv.ON_DEVICE_LANGUAGE_IDENTIFICATION_DETECT);
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.zzc.zzc(this.zzg == zzht.TYPE_THICK ? 24603 : 24602, zzhuVar.zza(), jCurrentTimeMillis - jElapsedRealtime, jCurrentTimeMillis);
    }

    private static final zzhr zzf(Float f) {
        zzhp zzhpVar = new zzhp();
        zzhpVar.zza(Float.valueOf(f == null ? -1.0f : f.floatValue()));
        return zzhpVar.zzb();
    }

    @Override // com.google.mlkit.p012nl.languageid.LanguageIdentifier, java.io.Closeable, java.lang.AutoCloseable
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void close() {
        zzg zzgVar = (zzg) this.zze.getAndSet(null);
        if (zzgVar == null) {
            return;
        }
        this.zzf.cancel();
        zzgVar.unpin(this.zzd);
        zzlc zzlcVar = this.zzb;
        zzhw zzhwVar = new zzhw();
        zzhwVar.zzc(this.zzg);
        zzir zzirVar = new zzir();
        zzirVar.zzf(zzf(this.zza.getConfidenceThreshold()));
        zzhwVar.zze(zzirVar.zzi());
        zzlcVar.zzc(zzlf.zzg(zzhwVar, 1), zzhv.ON_DEVICE_LANGUAGE_IDENTIFICATION_CLOSE);
    }

    @Override // com.google.mlkit.p012nl.languageid.LanguageIdentifier
    public final Task<String> identifyLanguage(final String str) {
        Preconditions.checkNotNull(str, "Text can not be null");
        final zzg zzgVar = (zzg) this.zze.get();
        Preconditions.checkState(zzgVar != null, "LanguageIdentification has been closed");
        final boolean zIsLoaded = true ^ zzgVar.isLoaded();
        return zzgVar.callAfterLoad(this.zzd, new Callable() { // from class: com.google.mlkit.nl.languageid.internal.zze
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zza.zzc(zzgVar, str, zIsLoaded);
            }
        }, this.zzf.getToken());
    }

    @Override // com.google.mlkit.p012nl.languageid.LanguageIdentifier
    public final Task<List<IdentifiedLanguage>> identifyPossibleLanguages(final String str) {
        Preconditions.checkNotNull(str, "Text can not be null");
        final zzg zzgVar = (zzg) this.zze.get();
        Preconditions.checkState(zzgVar != null, "LanguageIdentification has been closed");
        final boolean zIsLoaded = true ^ zzgVar.isLoaded();
        return zzgVar.callAfterLoad(this.zzd, new Callable() { // from class: com.google.mlkit.nl.languageid.internal.zzd
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zza.zzd(zzgVar, str, zIsLoaded);
            }
        }, this.zzf.getToken());
    }

    final /* synthetic */ zzku zzb(long j, boolean z, zzhu zzhuVar, zzja zzjaVar, zzix zzixVar) {
        zzir zzirVar = new zzir();
        zzirVar.zzf(zzf(this.zza.getConfidenceThreshold()));
        zzhj zzhjVar = new zzhj();
        zzhjVar.zza(Long.valueOf(j));
        zzhjVar.zzc(Boolean.valueOf(z));
        zzhjVar.zzb(zzhuVar);
        zzirVar.zze(zzhjVar.zzd());
        if (zzjaVar != null) {
            zzirVar.zzd(zzjaVar);
        }
        if (zzixVar != null) {
            zzirVar.zzc(zzixVar);
        }
        zzhw zzhwVar = new zzhw();
        zzhwVar.zzc(this.zzg);
        zzhwVar.zze(zzirVar.zzi());
        return zzlf.zzf(zzhwVar);
    }

    final /* synthetic */ String zzc(zzg zzgVar, String str, boolean z) throws Exception {
        zzix zzixVarZzc;
        Float confidenceThreshold = this.zza.getConfidenceThreshold();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        try {
            String strZzc = zzgVar.zzc(str.substring(0, Math.min(str.length(), 200)), confidenceThreshold != null ? confidenceThreshold.floatValue() : 0.5f);
            if (strZzc == null) {
                zzixVarZzc = null;
            } else {
                zziv zzivVar = new zziv();
                zzis zzisVar = new zzis();
                zzisVar.zzb(strZzc);
                zzivVar.zzb(zzisVar.zzc());
                zzixVarZzc = zzivVar.zzc();
            }
            zze(jElapsedRealtime, z, null, zzixVarZzc, zzhu.NO_ERROR);
            return strZzc;
        } catch (RuntimeException e) {
            zze(jElapsedRealtime, z, null, null, zzhu.UNKNOWN_ERROR);
            throw e;
        }
    }

    final /* synthetic */ List zzd(zzg zzgVar, String str, boolean z) throws Exception {
        Float confidenceThreshold = this.zza.getConfidenceThreshold();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        try {
            List<IdentifiedLanguage> listZzd = zzgVar.zzd(str.substring(0, Math.min(str.length(), 200)), confidenceThreshold != null ? confidenceThreshold.floatValue() : 0.01f);
            zzr zzrVar = new zzr();
            for (IdentifiedLanguage identifiedLanguage : listZzd) {
                zzis zzisVar = new zzis();
                zzisVar.zzb(identifiedLanguage.getLanguageTag());
                zzisVar.zza(Float.valueOf(identifiedLanguage.getConfidence()));
                zzrVar.zzb(zzisVar.zzc());
            }
            zziy zziyVar = new zziy();
            zziyVar.zzb(zzrVar.zzc());
            zze(jElapsedRealtime, z, zziyVar.zzc(), null, zzhu.NO_ERROR);
            return listZzd;
        } catch (RuntimeException e) {
            zze(jElapsedRealtime, z, null, null, zzhu.UNKNOWN_ERROR);
            throw e;
        }
    }
}
