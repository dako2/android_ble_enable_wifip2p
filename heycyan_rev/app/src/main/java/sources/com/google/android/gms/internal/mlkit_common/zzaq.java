package com.google.android.gms.internal.mlkit_common;

import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
public abstract class zzaq extends zzaj implements Set {

    @CheckForNull
    private transient zzan zza;

    zzaq() {
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this || obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    if (containsAll(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        return zzay.zza(this);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzaj, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: zzd */
    public abstract zzaz iterator();

    public final zzan zzf() {
        zzan zzanVar = this.zza;
        if (zzanVar != null) {
            return zzanVar;
        }
        zzan zzanVarZzg = zzg();
        this.zza = zzanVarZzg;
        return zzanVarZzg;
    }

    zzan zzg() {
        return zzan.zzh(toArray());
    }
}
