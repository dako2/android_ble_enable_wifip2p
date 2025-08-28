package com.google.android.gms.libs.identity;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
/* loaded from: classes.dex */
final /* synthetic */ class zzch implements Executor {
    static final /* synthetic */ zzch zza = new zzch();

    private /* synthetic */ zzch() {
    }

    @Override // java.util.concurrent.Executor
    public final /* synthetic */ void execute(Runnable runnable) {
        runnable.run();
    }
}
