package com.google.android.gms.internal.mlkit_language_id_common;

/* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
/* loaded from: classes.dex */
final class zzkt extends zzkw {
    private final String zza;
    private final boolean zzb;
    private final int zzc;

    /* synthetic */ zzkt(String str, boolean z, int i, zzks zzksVar) {
        this.zza = str;
        this.zzb = z;
        this.zzc = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzkw) {
            zzkw zzkwVar = (zzkw) obj;
            if (this.zza.equals(zzkwVar.zzb()) && this.zzb == zzkwVar.zzc() && this.zzc == zzkwVar.zza()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.zza.hashCode() ^ 1000003) * 1000003) ^ (true != this.zzb ? 1237 : 1231)) * 1000003) ^ this.zzc;
    }

    public final String toString() {
        return "MLKitLoggingOptions{libraryName=" + this.zza + ", enableFirelog=" + this.zzb + ", firelogEventType=" + this.zzc + "}";
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzkw
    public final int zza() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzkw
    public final String zzb() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzkw
    public final boolean zzc() {
        return this.zzb;
    }
}
