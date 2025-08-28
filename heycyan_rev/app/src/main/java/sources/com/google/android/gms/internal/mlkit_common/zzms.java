package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
final class zzms extends LazyInstanceMap {
    private zzms() {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object create(Object obj) {
        zzmb zzmbVar = (zzmb) obj;
        MlKitContext mlKitContext = MlKitContext.getInstance();
        return new zzmi(mlKitContext.getApplicationContext(), (SharedPrefManager) mlKitContext.get(SharedPrefManager.class), new zzmc(MlKitContext.getInstance().getApplicationContext(), zzmbVar), zzmbVar.zzb());
    }

    /* synthetic */ zzms(zzmr zzmrVar) {
    }
}
