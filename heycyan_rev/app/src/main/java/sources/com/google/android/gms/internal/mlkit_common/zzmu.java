package com.google.android.gms.internal.mlkit_common;

import android.os.SystemClock;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
public final class zzmu {
    private static final GmsLogger zza = new GmsLogger("RemoteModelUtils", "");

    public static zzjb zza(RemoteModel remoteModel, SharedPrefManager sharedPrefManager, zzmk zzmkVar) {
        ModelType modelTypeZzb = zzmkVar.zzb();
        String modelHash = remoteModel.getModelHash();
        zzjh zzjhVar = new zzjh();
        zzjc zzjcVar = new zzjc();
        zzjcVar.zzc(remoteModel.getModelNameForBackend());
        zzjcVar.zzd(zzje.CLOUD);
        zzjcVar.zza(zzad.zzb(modelHash));
        int iOrdinal = modelTypeZzb.ordinal();
        zzjcVar.zzb(iOrdinal != 2 ? iOrdinal != 4 ? iOrdinal != 5 ? zzjd.TYPE_UNKNOWN : zzjd.BASE_DIGITAL_INK : zzjd.CUSTOM : zzjd.BASE_TRANSLATE);
        zzjhVar.zzb(zzjcVar.zzg());
        zzjk zzjkVarZzc = zzjhVar.zzc();
        zziy zziyVar = new zziy();
        zziyVar.zzd(zzmkVar.zzc());
        zziyVar.zzc(zzmkVar.zzd());
        zziyVar.zzb(Long.valueOf(zzmkVar.zza()));
        zziyVar.zzf(zzjkVarZzc);
        if (zzmkVar.zzg()) {
            long modelDownloadBeginTimeMs = sharedPrefManager.getModelDownloadBeginTimeMs(remoteModel);
            if (modelDownloadBeginTimeMs == 0) {
                zza.m195w("RemoteModelUtils", "Model downloaded without its beginning time recorded.");
            } else {
                long modelFirstUseTimeMs = sharedPrefManager.getModelFirstUseTimeMs(remoteModel);
                if (modelFirstUseTimeMs == 0) {
                    modelFirstUseTimeMs = SystemClock.elapsedRealtime();
                    sharedPrefManager.setModelFirstUseTimeMs(remoteModel, modelFirstUseTimeMs);
                }
                zziyVar.zzg(Long.valueOf(modelFirstUseTimeMs - modelDownloadBeginTimeMs));
            }
        }
        if (zzmkVar.zzf()) {
            long modelDownloadBeginTimeMs2 = sharedPrefManager.getModelDownloadBeginTimeMs(remoteModel);
            if (modelDownloadBeginTimeMs2 == 0) {
                zza.m195w("RemoteModelUtils", "Model downloaded without its beginning time recorded.");
            } else {
                zziyVar.zze(Long.valueOf(SystemClock.elapsedRealtime() - modelDownloadBeginTimeMs2));
            }
        }
        return zziyVar.zzi();
    }
}
