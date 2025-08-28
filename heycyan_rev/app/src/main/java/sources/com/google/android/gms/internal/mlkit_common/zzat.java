package com.google.android.gms.internal.mlkit_common;

import java.util.AbstractMap;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
final class zzat extends zzan {
    final /* synthetic */ zzau zza;

    zzat(zzau zzauVar) {
        this.zza = zzauVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzac.zza(i, this.zza.zzc, "index");
        zzau zzauVar = this.zza;
        int i2 = i + i;
        Object obj = zzauVar.zzb[i2];
        obj.getClass();
        Object obj2 = zzauVar.zzb[i2 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzc;
    }
}
