package com.google.android.gms.internal.mlkit_language_id_common;

/* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
/* loaded from: classes.dex */
public enum zzht implements zzak {
    TYPE_UNKNOWN(0),
    TYPE_THIN(1),
    TYPE_THICK(2),
    TYPE_GMV(3);

    private final int zzf;

    zzht(int i) {
        this.zzf = i;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzak
    public final int zza() {
        return this.zzf;
    }
}
