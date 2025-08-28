package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
public final class zzmc implements zzmh {
    final List zza;

    public zzmc(Context context, zzmb zzmbVar) {
        ArrayList arrayList = new ArrayList();
        this.zza = arrayList;
        if (zzmbVar.zzc()) {
            arrayList.add(new zzmq(context, zzmbVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmh
    public final void zza(zzlz zzlzVar) {
        Iterator it = this.zza.iterator();
        while (it.hasNext()) {
            ((zzmh) it.next()).zza(zzlzVar);
        }
    }
}
