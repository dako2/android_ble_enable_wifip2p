package com.google.mlkit.common.sdkinternal;

import android.util.Log;
import com.google.android.gms.internal.mlkit_common.zzip;
import com.google.android.gms.internal.mlkit_common.zziq;
import com.google.android.gms.internal.mlkit_common.zziu;
import com.google.android.gms.internal.mlkit_common.zziv;
import com.google.android.gms.internal.mlkit_common.zzmi;
import com.google.android.gms.internal.mlkit_common.zzml;
import com.google.android.gms.internal.mlkit_common.zzmt;
import com.google.mlkit.common.sdkinternal.Cleaner;
import java.io.Closeable;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes2.dex */
public class CloseGuard implements Closeable {
    public static final int API_TRANSLATE = 1;
    private final AtomicBoolean zza = new AtomicBoolean();
    private final String zzb;
    private final Cleaner.Cleanable zzc;

    /* compiled from: com.google.mlkit:common@@18.4.0 */
    public static class Factory {
        private final Cleaner zza;

        public Factory(Cleaner cleaner) {
            this.zza = cleaner;
        }

        public CloseGuard create(Object obj, int i, Runnable runnable) {
            return new CloseGuard(obj, i, this.zza, runnable, zzmt.zzb("common"));
        }
    }

    CloseGuard(Object obj, final int i, Cleaner cleaner, final Runnable runnable, final zzmi zzmiVar) {
        this.zzb = obj.toString();
        this.zzc = cleaner.register(obj, new Runnable() { // from class: com.google.mlkit.common.sdkinternal.zze
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zza(i, zzmiVar, runnable);
            }
        });
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.zza.set(true);
        this.zzc.clean();
    }

    final /* synthetic */ void zza(int i, zzmi zzmiVar, Runnable runnable) {
        if (!this.zza.get()) {
            Log.e("MlKitCloseGuard", String.format(Locale.ENGLISH, "%s has not been closed", this.zzb));
            zziv zzivVar = new zziv();
            zziq zziqVar = new zziq();
            zziqVar.zzb(zzip.zzb(i));
            zzivVar.zzh(zziqVar.zzc());
            zzmiVar.zzd(zzml.zzf(zzivVar), zziu.HANDLE_LEAKED);
        }
        runnable.run();
    }
}
