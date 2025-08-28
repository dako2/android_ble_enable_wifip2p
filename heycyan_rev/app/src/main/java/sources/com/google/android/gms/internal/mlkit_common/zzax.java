package com.google.android.gms.internal.mlkit_common;

import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
final class zzax extends zzap {
    static final zzap zza = new zzax(null, new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    private zzax(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    static zzax zzg(int i, Object[] objArr, zzao zzaoVar) {
        Object obj = objArr[0];
        obj.getClass();
        Object obj2 = objArr[1];
        obj2.getClass();
        zzaf.zza(obj, obj2);
        return new zzax(null, objArr, 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0007  */
    @Override // com.google.android.gms.internal.mlkit_common.zzap, java.util.Map
    @CheckForNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object get(@CheckForNull Object obj) {
        Object obj2;
        Object[] objArr = this.zzb;
        int i = this.zzc;
        if (obj != null && i == 1) {
            Object obj3 = objArr[0];
            obj3.getClass();
            if (obj3.equals(obj)) {
                obj2 = objArr[1];
                obj2.getClass();
            } else {
                obj2 = null;
            }
        }
        if (obj2 == null) {
            return null;
        }
        return obj2;
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzap
    final zzaj zza() {
        return new zzaw(this.zzb, 1, this.zzc);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzap
    final zzaq zzd() {
        return new zzau(this, this.zzb, 0, this.zzc);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzap
    final zzaq zze() {
        return new zzav(this, new zzaw(this.zzb, 0, this.zzc));
    }
}
