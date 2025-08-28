package com.google.android.gms.libs.identity;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
/* loaded from: classes.dex */
final /* synthetic */ class zzcd implements zzbg {
    static final /* synthetic */ zzcd zza = new zzcd();

    private /* synthetic */ zzcd() {
    }

    @Override // com.google.android.gms.libs.identity.zzbg
    public final /* synthetic */ void zza(zzdz zzdzVar, ListenerHolder.ListenerKey listenerKey, boolean z, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzdzVar.zzv(listenerKey, z, taskCompletionSource);
    }
}
