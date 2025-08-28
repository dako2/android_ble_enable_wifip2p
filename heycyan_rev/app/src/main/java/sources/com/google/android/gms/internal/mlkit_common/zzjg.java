package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
public final class zzjg {
    private final String zza;
    private final zzje zzc;
    private final String zze;
    private final zzjd zzf;
    private final String zzb = null;
    private final String zzd = null;
    private final Long zzg = null;
    private final Boolean zzh = null;
    private final Boolean zzi = null;

    /* synthetic */ zzjg(zzjc zzjcVar, zzjf zzjfVar) {
        this.zza = zzjcVar.zza;
        this.zzc = zzjcVar.zzb;
        this.zze = zzjcVar.zzc;
        this.zzf = zzjcVar.zzd;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzjg)) {
            return false;
        }
        zzjg zzjgVar = (zzjg) obj;
        if (Objects.equal(this.zza, zzjgVar.zza)) {
            String str = zzjgVar.zzb;
            if (Objects.equal(null, null) && Objects.equal(this.zzc, zzjgVar.zzc)) {
                String str2 = zzjgVar.zzd;
                if (Objects.equal(null, null) && Objects.equal(this.zze, zzjgVar.zze) && Objects.equal(this.zzf, zzjgVar.zzf)) {
                    Long l = zzjgVar.zzg;
                    if (Objects.equal(null, null)) {
                        Boolean bool = zzjgVar.zzh;
                        if (Objects.equal(null, null)) {
                            Boolean bool2 = zzjgVar.zzi;
                            if (Objects.equal(null, null)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, this.zzc, null, this.zze, this.zzf, null, null, null);
    }

    public final zzjd zza() {
        return this.zzf;
    }

    public final zzje zzb() {
        return this.zzc;
    }

    public final String zzc() {
        return this.zze;
    }

    public final String zzd() {
        return this.zza;
    }
}
