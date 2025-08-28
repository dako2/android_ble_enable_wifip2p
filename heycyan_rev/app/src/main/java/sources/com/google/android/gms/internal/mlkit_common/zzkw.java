package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
public final class zzkw {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private zzan zze;
    private String zzf;
    private Boolean zzg;
    private Boolean zzh;
    private Boolean zzi;
    private Integer zzj;
    private Integer zzk;

    public final zzkw zzb(String str) {
        this.zza = str;
        return this;
    }

    public final zzkw zzc(String str) {
        this.zzb = str;
        return this;
    }

    public final zzkw zzd(Integer num) {
        this.zzj = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzkw zze(Boolean bool) {
        this.zzg = bool;
        return this;
    }

    public final zzkw zzf(Boolean bool) {
        this.zzi = bool;
        return this;
    }

    public final zzkw zzg(Boolean bool) {
        this.zzh = bool;
        return this;
    }

    public final zzkw zzh(zzan zzanVar) {
        this.zze = zzanVar;
        return this;
    }

    public final zzkw zzi(String str) {
        this.zzf = str;
        return this;
    }

    public final zzkw zzj(String str) {
        this.zzc = str;
        return this;
    }

    public final zzkw zzk(Integer num) {
        this.zzk = num;
        return this;
    }

    public final zzkw zzl(String str) {
        this.zzd = str;
        return this;
    }

    public final zzky zzm() {
        return new zzky(this, null);
    }
}
