package com.google.android.gms.internal.mlkit_language_id_common;

/* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
/* loaded from: classes.dex */
final class zzac extends zzu {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc;

    zzac(Object[] objArr, int i, int i2) {
        this.zza = objArr;
        this.zzb = i;
        this.zzc = i2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzk.zza(i, this.zzc, "index");
        Object obj = this.zza[i + i + this.zzb];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }
}
