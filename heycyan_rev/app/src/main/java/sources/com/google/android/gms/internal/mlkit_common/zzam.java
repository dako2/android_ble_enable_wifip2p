package com.google.android.gms.internal.mlkit_common;

import java.util.List;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
final class zzam extends zzan {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzan zzc;

    zzam(zzan zzanVar, int i, int i2) {
        this.zzc = zzanVar;
        this.zza = i;
        this.zzb = i2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzac.zza(i, this.zzb, "index");
        return this.zzc.get(i + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzan, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzaj
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzaj
    final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzaj
    @CheckForNull
    final Object[] zze() {
        return this.zzc.zze();
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzan
    /* renamed from: zzf */
    public final zzan subList(int i, int i2) {
        zzac.zzc(i, i2, this.zzb);
        zzan zzanVar = this.zzc;
        int i3 = this.zza;
        return zzanVar.subList(i + i3, i2 + i3);
    }
}
