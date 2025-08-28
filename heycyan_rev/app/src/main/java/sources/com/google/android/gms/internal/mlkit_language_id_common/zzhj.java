package com.google.android.gms.internal.mlkit_language_id_common;

/* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
/* loaded from: classes.dex */
public final class zzhj {
    private Long zza;
    private zzhu zzb;
    private Boolean zzc;

    public final zzhj zza(Long l) {
        this.zza = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzhj zzb(zzhu zzhuVar) {
        this.zzb = zzhuVar;
        return this;
    }

    public final zzhj zzc(Boolean bool) {
        this.zzc = bool;
        return this;
    }

    public final zzhl zzd() {
        return new zzhl(this, null);
    }
}
