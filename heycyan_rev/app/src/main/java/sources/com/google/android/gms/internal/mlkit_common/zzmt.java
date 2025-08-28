package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
public final class zzmt {
    private static zzms zza;

    public static synchronized zzmi zza(zzmb zzmbVar) {
        if (zza == null) {
            zza = new zzms(null);
        }
        return (zzmi) zza.get(zzmbVar);
    }

    public static synchronized zzmi zzb(String str) {
        return zza(zzmb.zzd("common").zzd());
    }
}
