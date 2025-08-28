package com.google.android.gms.internal.mlkit_language_id_common;

/* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
/* loaded from: classes.dex */
final class zzkr extends zzkv {
    private String zza;
    private boolean zzb;
    private int zzc;
    private byte zzd;

    zzkr() {
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzkv
    public final zzkv zza(boolean z) {
        this.zzb = true;
        this.zzd = (byte) (1 | this.zzd);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzkv
    public final zzkv zzb(int i) {
        this.zzc = 1;
        this.zzd = (byte) (this.zzd | 2);
        return this;
    }

    public final zzkv zzc(String str) {
        this.zza = str;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzkv
    public final zzkw zzd() {
        String str;
        if (this.zzd == 3 && (str = this.zza) != null) {
            return new zzkt(str, this.zzb, this.zzc, null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" libraryName");
        }
        if ((this.zzd & 1) == 0) {
            sb.append(" enableFirelog");
        }
        if ((this.zzd & 2) == 0) {
            sb.append(" firelogEventType");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
