package com.google.android.gms.libs.identity;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
/* loaded from: classes.dex */
final /* synthetic */ class zzcf implements Executor {
    static final /* synthetic */ zzcf zza = new zzcf();

    private /* synthetic */ zzcf() {
    }

    @Override // java.util.concurrent.Executor
    public final /* synthetic */ void execute(Runnable runnable) {
        runnable.run();
    }
}
