package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
public final class zzmw {
    private static zzmw zza;

    private zzmw() {
    }

    public static synchronized zzmw zza() {
        if (zza == null) {
            zza = new zzmw();
        }
        return zza;
    }

    public static void zzb() {
        zzmv.zza();
    }
}
