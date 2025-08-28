package com.google.android.gms.internal.mlkit_language_id_common;

/* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
/* loaded from: classes.dex */
public final class zzln {
    private static zzlm zza;

    public static synchronized zzlc zza(zzkw zzkwVar) {
        if (zza == null) {
            zza = new zzlm(null);
        }
        return (zzlc) zza.get(zzkwVar);
    }

    public static synchronized zzlc zzb(String str) {
        return zza(zzkw.zzd(str).zzd());
    }
}
