package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
public final class zzjk {
    private final zzjg zza;
    private final zzji zzb = null;
    private final zzji zzc = null;
    private final Boolean zzd = null;

    /* synthetic */ zzjk(zzjh zzjhVar, zzjj zzjjVar) {
        this.zza = zzjhVar.zza;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzjk)) {
            return false;
        }
        zzjk zzjkVar = (zzjk) obj;
        if (Objects.equal(this.zza, zzjkVar.zza)) {
            zzji zzjiVar = zzjkVar.zzb;
            if (Objects.equal(null, null)) {
                zzji zzjiVar2 = zzjkVar.zzc;
                if (Objects.equal(null, null)) {
                    Boolean bool = zzjkVar.zzd;
                    if (Objects.equal(null, null)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, null, null);
    }

    public final zzjg zza() {
        return this.zza;
    }
}
