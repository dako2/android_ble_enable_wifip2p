package com.google.android.gms.internal.mlkit_common;

import android.content.Context;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
public final class zzh {
    public static final zzh zza;
    public static final zzh zzb;
    private final boolean zzc;
    private final boolean zzd = false;
    private final zzan zze;

    static {
        zze zzeVar = null;
        zzf zzfVar = new zzf(zzeVar);
        zzfVar.zza();
        zza = zzfVar.zzc();
        zzf zzfVar2 = new zzf(zzeVar);
        zzfVar2.zzb();
        zzb = zzfVar2.zzc();
    }

    /* synthetic */ zzh(boolean z, boolean z2, zzan zzanVar, zzg zzgVar) {
        this.zzc = z;
        this.zze = zzanVar;
    }

    static /* bridge */ /* synthetic */ boolean zza(zzh zzhVar) {
        boolean z = zzhVar.zzd;
        return false;
    }

    static /* bridge */ /* synthetic */ int zzc(zzh zzhVar, Context context, zzq zzqVar) {
        zzan zzanVar = zzhVar.zze;
        int size = zzanVar.size();
        int i = 0;
        while (i < size) {
            zzr zzrVar = (zzr) zzanVar.get(i);
            boolean z = zzhVar.zzc;
            int iZza = zzrVar.zza();
            int i2 = iZza - 1;
            if (iZza == 0) {
                throw null;
            }
            i++;
            if (i2 == 0) {
                return 1;
            }
            if (i2 == 1) {
                return 2;
            }
        }
        return 3;
    }
}
