package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
final class zzlt extends zzma {
    private String zza;
    private boolean zzb;
    private int zzc;
    private byte zzd;

    zzlt() {
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzma
    public final zzma zza(boolean z) {
        this.zzb = true;
        this.zzd = (byte) (1 | this.zzd);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzma
    public final zzma zzb(int i) {
        this.zzc = 1;
        this.zzd = (byte) (this.zzd | 2);
        return this;
    }

    public final zzma zzc(String str) {
        this.zza = "common";
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzma
    public final zzmb zzd() {
        String str;
        if (this.zzd == 3 && (str = this.zza) != null) {
            return new zzlv(str, this.zzb, this.zzc, null);
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
