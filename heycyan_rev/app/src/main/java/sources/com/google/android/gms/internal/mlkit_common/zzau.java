package com.google.android.gms.internal.mlkit_common;

import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
final class zzau extends zzaq {
    private final transient zzap zza;
    private final transient Object[] zzb;
    private final transient int zzc;

    zzau(zzap zzapVar, Object[] objArr, int i, int i2) {
        this.zza = zzapVar;
        this.zzb = objArr;
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzaj, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.zza.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzaq, com.google.android.gms.internal.mlkit_common.zzaj, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return zzf().listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzaj
    final int zza(Object[] objArr, int i) {
        return zzf().zza(objArr, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzaq, com.google.android.gms.internal.mlkit_common.zzaj
    /* renamed from: zzd */
    public final zzaz iterator() {
        return zzf().listIterator(0);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzaq
    final zzan zzg() {
        return new zzat(this);
    }
}
