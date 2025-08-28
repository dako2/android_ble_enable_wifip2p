package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
public enum zzip implements zzbh {
    UNKNOWN(0),
    TRANSLATE(1);

    private final int zzd;

    zzip(int i) {
        this.zzd = i;
    }

    public static zzip zzb(int i) {
        for (zzip zzipVar : values()) {
            if (zzipVar.zzd == i) {
                return zzipVar;
            }
        }
        return UNKNOWN;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzbh
    public final int zza() {
        return this.zzd;
    }
}
