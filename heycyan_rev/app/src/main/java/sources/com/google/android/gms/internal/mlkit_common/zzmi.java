package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import android.content.res.Resources;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
public final class zzmi {
    private static zzan zza;
    private static final zzap zzb = zzap.zzc("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
    private final String zzc;
    private final String zzd;
    private final zzmh zze;
    private final SharedPrefManager zzf;
    private final Task zzg;
    private final Task zzh;
    private final String zzi;
    private final int zzj;
    private final Map zzk = new HashMap();
    private final Map zzl = new HashMap();

    public zzmi(Context context, final SharedPrefManager sharedPrefManager, zzmh zzmhVar, String str) {
        this.zzc = context.getPackageName();
        this.zzd = CommonUtils.getAppVersion(context);
        this.zzf = sharedPrefManager;
        this.zze = zzmhVar;
        zzmw.zza();
        this.zzi = str;
        this.zzg = MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzmd
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zza.zza();
            }
        });
        MLTaskExecutor mLTaskExecutor = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.zzh = mLTaskExecutor.scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzme
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return sharedPrefManager.getMlSdkInstanceId();
            }
        });
        zzap zzapVar = zzb;
        this.zzj = zzapVar.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzapVar.get(str)) : -1;
    }

    private static synchronized zzan zzh() {
        zzan zzanVar = zza;
        if (zzanVar != null) {
            return zzanVar;
        }
        LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
        zzak zzakVar = new zzak();
        for (int i = 0; i < locales.size(); i++) {
            zzakVar.zzb(CommonUtils.languageTagFromLocale(locales.get(i)));
        }
        zzan zzanVarZzc = zzakVar.zzc();
        zza = zzanVarZzc;
        return zzanVarZzc;
    }

    private final zzkw zzi(String str, String str2) {
        zzkw zzkwVar = new zzkw();
        zzkwVar.zzb(this.zzc);
        zzkwVar.zzc(this.zzd);
        zzkwVar.zzh(zzh());
        zzkwVar.zzg(true);
        zzkwVar.zzl(str);
        zzkwVar.zzj(str2);
        zzkwVar.zzi(this.zzh.isSuccessful() ? (String) this.zzh.getResult() : this.zzf.getMlSdkInstanceId());
        zzkwVar.zzd(10);
        zzkwVar.zzk(Integer.valueOf(this.zzj));
        return zzkwVar;
    }

    private final String zzj() {
        return this.zzg.isSuccessful() ? (String) this.zzg.getResult() : LibraryVersion.getInstance().getVersion(this.zzi);
    }

    final /* synthetic */ String zza() throws Exception {
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    final /* synthetic */ void zzb(zzlz zzlzVar, zziu zziuVar, String str) {
        zzlzVar.zza(zziuVar);
        zzlzVar.zzc(zzi(zzlzVar.zzd(), str));
        this.zze.zza(zzlzVar);
    }

    final /* synthetic */ void zzc(zzlz zzlzVar, zzmk zzmkVar, RemoteModel remoteModel) {
        zzlzVar.zza(zziu.MODEL_DOWNLOAD);
        zzlzVar.zzc(zzi(zzmkVar.zze(), zzj()));
        zzlzVar.zzb(zzmu.zza(remoteModel, this.zzf, zzmkVar));
        this.zze.zza(zzlzVar);
    }

    public final void zzd(final zzlz zzlzVar, final zziu zziuVar) {
        final String strZzj = zzj();
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_common.zzmf
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzb(zzlzVar, zziuVar, strZzj);
            }
        });
    }

    public final void zze(zzlz zzlzVar, RemoteModel remoteModel, boolean z, int i) {
        zzmj zzmjVarZzh = zzmk.zzh();
        zzmjVarZzh.zzf(false);
        zzmjVarZzh.zzd(remoteModel.getModelType());
        zzmjVarZzh.zza(zziz.FAILED);
        zzmjVarZzh.zzb(zzit.DOWNLOAD_FAILED);
        zzmjVarZzh.zzc(i);
        zzg(zzlzVar, remoteModel, zzmjVarZzh.zzh());
    }

    public final void zzf(zzlz zzlzVar, RemoteModel remoteModel, zzit zzitVar, boolean z, ModelType modelType, zziz zzizVar) {
        zzmj zzmjVarZzh = zzmk.zzh();
        zzmjVarZzh.zzf(z);
        zzmjVarZzh.zzd(modelType);
        zzmjVarZzh.zzb(zzitVar);
        zzmjVarZzh.zza(zzizVar);
        zzg(zzlzVar, remoteModel, zzmjVarZzh.zzh());
    }

    public final void zzg(final zzlz zzlzVar, final RemoteModel remoteModel, final zzmk zzmkVar) {
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_common.zzmg
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzc(zzlzVar, zzmkVar, remoteModel);
            }
        });
    }
}
