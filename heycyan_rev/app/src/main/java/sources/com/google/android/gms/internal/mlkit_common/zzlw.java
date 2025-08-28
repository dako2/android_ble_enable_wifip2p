package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.ModelType;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
final class zzlw extends zzmj {
    private zzit zza;
    private String zzb;
    private boolean zzc;
    private boolean zzd;
    private ModelType zze;
    private zziz zzf;
    private int zzg;
    private byte zzh;

    zzlw() {
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmj
    public final zzmj zza(zziz zzizVar) {
        if (zzizVar == null) {
            throw new NullPointerException("Null downloadStatus");
        }
        this.zzf = zzizVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmj
    public final zzmj zzb(zzit zzitVar) {
        if (zzitVar == null) {
            throw new NullPointerException("Null errorCode");
        }
        this.zza = zzitVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmj
    public final zzmj zzc(int i) {
        this.zzg = i;
        this.zzh = (byte) (this.zzh | 4);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmj
    public final zzmj zzd(ModelType modelType) {
        if (modelType == null) {
            throw new NullPointerException("Null modelType");
        }
        this.zze = modelType;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmj
    public final zzmj zze(boolean z) {
        this.zzd = z;
        this.zzh = (byte) (this.zzh | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmj
    public final zzmj zzf(boolean z) {
        this.zzc = z;
        this.zzh = (byte) (this.zzh | 1);
        return this;
    }

    public final zzmj zzg(String str) {
        this.zzb = "NA";
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmj
    public final zzmk zzh() {
        zzit zzitVar;
        String str;
        ModelType modelType;
        zziz zzizVar;
        if (this.zzh == 7 && (zzitVar = this.zza) != null && (str = this.zzb) != null && (modelType = this.zze) != null && (zzizVar = this.zzf) != null) {
            return new zzly(zzitVar, str, this.zzc, this.zzd, modelType, zzizVar, this.zzg, null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" errorCode");
        }
        if (this.zzb == null) {
            sb.append(" tfliteSchemaVersion");
        }
        if ((this.zzh & 1) == 0) {
            sb.append(" shouldLogRoughDownloadTime");
        }
        if ((this.zzh & 2) == 0) {
            sb.append(" shouldLogExactDownloadTime");
        }
        if (this.zze == null) {
            sb.append(" modelType");
        }
        if (this.zzf == null) {
            sb.append(" downloadStatus");
        }
        if ((this.zzh & 4) == 0) {
            sb.append(" failureStatusCode");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
