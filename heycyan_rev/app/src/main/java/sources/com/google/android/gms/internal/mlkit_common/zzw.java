package com.google.android.gms.internal.mlkit_common;

import java.util.Arrays;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
public final class zzw {
    private final String zza;
    private final zzv zzb;
    private zzv zzc;

    /* synthetic */ zzw(String str, zzt zztVar) {
        zzv zzvVar = new zzv(null);
        this.zzb = zzvVar;
        this.zzc = zzvVar;
        str.getClass();
        this.zza = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.zza);
        sb.append('{');
        zzv zzvVar = this.zzb.zzc;
        String str = "";
        while (zzvVar != null) {
            Object obj = zzvVar.zzb;
            sb.append(str);
            String str2 = zzvVar.zza;
            if (str2 != null) {
                sb.append(str2);
                sb.append('=');
            }
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                sb.append((CharSequence) Arrays.deepToString(new Object[]{obj}), 1, r2.length() - 1);
            }
            zzvVar = zzvVar.zzc;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }

    public final zzw zza(String str, @CheckForNull Object obj) {
        zzv zzvVar = new zzv(null);
        this.zzc.zzc = zzvVar;
        this.zzc = zzvVar;
        zzvVar.zzb = obj;
        zzvVar.zza = str;
        return this;
    }

    public final zzw zzb(String str, boolean z) {
        String strValueOf = String.valueOf(z);
        zzu zzuVar = new zzu(null);
        this.zzc.zzc = zzuVar;
        this.zzc = zzuVar;
        zzuVar.zzb = strValueOf;
        zzuVar.zza = "isManifestFile";
        return this;
    }
}
