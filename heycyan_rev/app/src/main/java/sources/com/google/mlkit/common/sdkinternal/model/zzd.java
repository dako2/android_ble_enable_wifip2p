package com.google.mlkit.common.sdkinternal.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.internal.mlkit_common.zzit;
import com.google.android.gms.internal.mlkit_common.zziz;
import com.google.android.gms.internal.mlkit_common.zzlz;
import com.google.android.gms.internal.mlkit_common.zzmi;
import com.google.android.gms.internal.mlkit_common.zzmj;
import com.google.android.gms.internal.mlkit_common.zzmk;
import com.google.android.gms.internal.mlkit_common.zzml;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.RemoteModel;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes2.dex */
final class zzd extends BroadcastReceiver {
    final /* synthetic */ RemoteModelDownloadManager zza;
    private final long zzb;
    private final TaskCompletionSource zzc;

    /* synthetic */ zzd(RemoteModelDownloadManager remoteModelDownloadManager, long j, TaskCompletionSource taskCompletionSource, zzc zzcVar) {
        this.zza = remoteModelDownloadManager;
        this.zzb = j;
        this.zzc = taskCompletionSource;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        long longExtra = intent.getLongExtra("extra_download_id", -1L);
        if (longExtra != this.zzb) {
            return;
        }
        Integer downloadingModelStatusCode = this.zza.getDownloadingModelStatusCode();
        synchronized (this.zza) {
            try {
                this.zza.zze.getApplicationContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                RemoteModelDownloadManager.zza.m196w("ModelDownloadManager", "Exception thrown while trying to unregister the broadcast receiver for the download", e);
            }
            this.zza.zzc.remove(this.zzb);
            this.zza.zzd.remove(this.zzb);
        }
        if (downloadingModelStatusCode != null) {
            if (downloadingModelStatusCode.intValue() == 16) {
                zzmi zzmiVar = this.zza.zzi;
                zzlz zzlzVarZzg = zzml.zzg();
                RemoteModelDownloadManager remoteModelDownloadManager = this.zza;
                RemoteModel remoteModel = remoteModelDownloadManager.zzg;
                Long lValueOf = Long.valueOf(longExtra);
                zzmiVar.zze(zzlzVarZzg, remoteModel, false, remoteModelDownloadManager.getFailureReason(lValueOf));
                this.zzc.setException(this.zza.zzl(lValueOf));
                return;
            }
            if (downloadingModelStatusCode.intValue() == 8) {
                zzmi zzmiVar2 = this.zza.zzi;
                zzlz zzlzVarZzg2 = zzml.zzg();
                RemoteModel remoteModel2 = this.zza.zzg;
                zzmj zzmjVarZzh = zzmk.zzh();
                zzmjVarZzh.zzb(zzit.NO_ERROR);
                zzmjVarZzh.zze(true);
                zzmjVarZzh.zzd(this.zza.zzg.getModelType());
                zzmjVarZzh.zza(zziz.SUCCEEDED);
                zzmiVar2.zzg(zzlzVarZzg2, remoteModel2, zzmjVarZzh.zzh());
                this.zzc.setResult(null);
                return;
            }
        }
        this.zza.zzi.zze(zzml.zzg(), this.zza.zzg, false, 0);
        this.zzc.setException(new MlKitException("Model downloading failed", 13));
    }
}
