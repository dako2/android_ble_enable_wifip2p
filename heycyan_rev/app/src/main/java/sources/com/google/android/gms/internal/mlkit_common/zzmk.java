package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.ModelType;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
public abstract class zzmk {
    public static zzmj zzh() {
        zzlw zzlwVar = new zzlw();
        zzlwVar.zzg("NA");
        zzlwVar.zzf(false);
        zzlwVar.zze(false);
        zzlwVar.zzd(ModelType.UNKNOWN);
        zzlwVar.zzb(zzit.NO_ERROR);
        zzlwVar.zza(zziz.UNKNOWN_STATUS);
        zzlwVar.zzc(0);
        return zzlwVar;
    }

    public abstract int zza();

    public abstract ModelType zzb();

    public abstract zzit zzc();

    public abstract zziz zzd();

    public abstract String zze();

    public abstract boolean zzf();

    public abstract boolean zzg();
}
