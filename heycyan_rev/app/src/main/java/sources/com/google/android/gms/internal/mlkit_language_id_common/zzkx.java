package com.google.android.gms.internal.mlkit_language_id_common;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
/* loaded from: classes.dex */
public final class zzkx implements zzlb {
    final List zza;

    public zzkx(Context context, zzkw zzkwVar) {
        ArrayList arrayList = new ArrayList();
        this.zza = arrayList;
        if (zzkwVar.zzc()) {
            arrayList.add(new zzlk(context, zzkwVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzlb
    public final void zza(zzku zzkuVar) {
        Iterator it = this.zza.iterator();
        while (it.hasNext()) {
            ((zzlb) it.next()).zza(zzkuVar);
        }
    }
}
