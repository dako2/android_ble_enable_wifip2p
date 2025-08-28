package com.google.android.gms.internal.mlkit_language_id_common;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
/* loaded from: classes.dex */
public final class zzlc {
    private static zzu zza;
    private static final zzw zzb = zzw.zzc("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
    private final String zzc;
    private final String zzd;
    private final zzlb zze;
    private final SharedPrefManager zzf;
    private final Task zzg;
    private final Task zzh;
    private final String zzi;
    private final int zzj;
    private final Map zzk = new HashMap();
    private final Map zzl = new HashMap();

    public zzlc(Context context, final SharedPrefManager sharedPrefManager, zzlb zzlbVar, String str) {
        this.zzc = context.getPackageName();
        this.zzd = CommonUtils.getAppVersion(context);
        this.zzf = sharedPrefManager;
        this.zze = zzlbVar;
        zzlo.zza();
        this.zzi = str;
        this.zzg = MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_language_id_common.zzky
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zza.zza();
            }
        });
        MLTaskExecutor mLTaskExecutor = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.zzh = mLTaskExecutor.scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_language_id_common.zzkz
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return sharedPrefManager.getMlSdkInstanceId();
            }
        });
        zzw zzwVar = zzb;
        this.zzj = zzwVar.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzwVar.get(str)) : -1;
    }

    private static synchronized zzu zzf() {
        zzu zzuVar = zza;
        if (zzuVar != null) {
            return zzuVar;
        }
        LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
        zzr zzrVar = new zzr();
        for (int i = 0; i < locales.size(); i++) {
            zzrVar.zzb(CommonUtils.languageTagFromLocale(locales.get(i)));
        }
        zzu zzuVarZzc = zzrVar.zzc();
        zza = zzuVarZzc;
        return zzuVarZzc;
    }

    private final String zzg() {
        return this.zzg.isSuccessful() ? (String) this.zzg.getResult() : LibraryVersion.getInstance().getVersion(this.zzi);
    }

    final /* synthetic */ String zza() throws Exception {
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    final /* synthetic */ void zzb(zzku zzkuVar, zzhv zzhvVar, String str) {
        zzkuVar.zzb(zzhvVar);
        String strZzd = zzkuVar.zzd();
        zzjw zzjwVar = new zzjw();
        zzjwVar.zzb(this.zzc);
        zzjwVar.zzc(this.zzd);
        zzjwVar.zzh(zzf());
        zzjwVar.zzg(true);
        zzjwVar.zzl(strZzd);
        zzjwVar.zzj(str);
        zzjwVar.zzi(this.zzh.isSuccessful() ? (String) this.zzh.getResult() : this.zzf.getMlSdkInstanceId());
        zzjwVar.zzd(10);
        zzjwVar.zzk(Integer.valueOf(this.zzj));
        zzkuVar.zzc(zzjwVar);
        this.zze.zza(zzkuVar);
    }

    public final void zzc(zzku zzkuVar, zzhv zzhvVar) {
        zzd(zzkuVar, zzhvVar, zzg());
    }

    public final void zzd(final zzku zzkuVar, final zzhv zzhvVar, final String str) {
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_language_id_common.zzla
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzb(zzkuVar, zzhvVar, str);
            }
        });
    }

    public final void zze(com.google.mlkit.p012nl.languageid.internal.zzf zzfVar, zzhv zzhvVar) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (this.zzk.get(zzhvVar) != null && jElapsedRealtime - ((Long) this.zzk.get(zzhvVar)).longValue() <= TimeUnit.SECONDS.toMillis(30L)) {
            return;
        }
        this.zzk.put(zzhvVar, Long.valueOf(jElapsedRealtime));
        zzd(zzfVar.zza(), zzhvVar, zzg());
    }
}
