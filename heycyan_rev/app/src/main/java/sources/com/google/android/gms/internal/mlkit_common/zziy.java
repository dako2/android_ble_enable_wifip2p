package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
public final class zziy {
    private zzjk zza;
    private Long zzb;
    private zzit zzc;
    private Long zzd;
    private zziz zze;
    private Long zzf;

    public final zziy zzb(Long l) {
        this.zzf = l;
        return this;
    }

    public final zziy zzc(zziz zzizVar) {
        this.zze = zzizVar;
        return this;
    }

    public final zziy zzd(zzit zzitVar) {
        this.zzc = zzitVar;
        return this;
    }

    public final zziy zze(Long l) {
        this.zzd = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zziy zzf(zzjk zzjkVar) {
        this.zza = zzjkVar;
        return this;
    }

    public final zziy zzg(Long l) {
        this.zzb = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzjb zzi() {
        return new zzjb(this, null);
    }
}
