package com.google.android.gms.internal.mlkit_language_id_common;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
/* loaded from: classes.dex */
final class zzlm extends LazyInstanceMap {
    private zzlm() {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object create(Object obj) {
        zzkw zzkwVar = (zzkw) obj;
        MlKitContext mlKitContext = MlKitContext.getInstance();
        return new zzlc(mlKitContext.getApplicationContext(), (SharedPrefManager) mlKitContext.get(SharedPrefManager.class), new zzkx(MlKitContext.getInstance().getApplicationContext(), zzkwVar), zzkwVar.zzb());
    }

    /* synthetic */ zzlm(zzll zzllVar) {
    }
}
