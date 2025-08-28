package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.ModelType;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
final class zzly extends zzmk {
    private final zzit zza;
    private final String zzb;
    private final boolean zzc;
    private final boolean zzd;
    private final ModelType zze;
    private final zziz zzf;
    private final int zzg;

    /* synthetic */ zzly(zzit zzitVar, String str, boolean z, boolean z2, ModelType modelType, zziz zzizVar, int i, zzlx zzlxVar) {
        this.zza = zzitVar;
        this.zzb = str;
        this.zzc = z;
        this.zzd = z2;
        this.zze = modelType;
        this.zzf = zzizVar;
        this.zzg = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzmk) {
            zzmk zzmkVar = (zzmk) obj;
            if (this.zza.equals(zzmkVar.zzc()) && this.zzb.equals(zzmkVar.zze()) && this.zzc == zzmkVar.zzg() && this.zzd == zzmkVar.zzf() && this.zze.equals(zzmkVar.zzb()) && this.zzf.equals(zzmkVar.zzd()) && this.zzg == zzmkVar.zza()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003) ^ (true != this.zzc ? 1237 : 1231)) * 1000003) ^ (true == this.zzd ? 1231 : 1237)) * 1000003) ^ this.zze.hashCode()) * 1000003) ^ this.zzf.hashCode()) * 1000003) ^ this.zzg;
    }

    public final String toString() {
        return "RemoteModelLoggingOptions{errorCode=" + this.zza.toString() + ", tfliteSchemaVersion=" + this.zzb + ", shouldLogRoughDownloadTime=" + this.zzc + ", shouldLogExactDownloadTime=" + this.zzd + ", modelType=" + this.zze.toString() + ", downloadStatus=" + this.zzf.toString() + ", failureStatusCode=" + this.zzg + "}";
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmk
    public final int zza() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmk
    public final ModelType zzb() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmk
    public final zzit zzc() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmk
    public final zziz zzd() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmk
    public final String zze() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmk
    public final boolean zzf() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmk
    public final boolean zzg() {
        return this.zzc;
    }
}
