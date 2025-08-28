package com.google.android.gms.internal.mlkit_language_id_common;

import android.content.Context;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.firebase.components.Lazy;
import com.google.firebase.inject.Provider;

/* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
/* loaded from: classes.dex */
public final class zzlk implements zzlb {
    private Provider zza;
    private final Provider zzb;
    private final zzkw zzc;

    public zzlk(Context context, zzkw zzkwVar) {
        this.zzc = zzkwVar;
        CCTDestination cCTDestination = CCTDestination.INSTANCE;
        TransportRuntime.initialize(context);
        final TransportFactory transportFactoryNewFactory = TransportRuntime.getInstance().newFactory(cCTDestination);
        if (cCTDestination.getSupportedEncodings().contains(Encoding.m177of("json"))) {
            this.zza = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_language_id_common.zzlh
                @Override // com.google.firebase.inject.Provider
                public final Object get() {
                    return transportFactoryNewFactory.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.m177of("json"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_language_id_common.zzlj
                        @Override // com.google.android.datatransport.Transformer
                        public final Object apply(Object obj) {
                            return (byte[]) obj;
                        }
                    });
                }
            });
        }
        this.zzb = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_language_id_common.zzli
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return transportFactoryNewFactory.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.m177of("proto"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_language_id_common.zzlg
                    @Override // com.google.android.datatransport.Transformer
                    public final Object apply(Object obj) {
                        return (byte[]) obj;
                    }
                });
            }
        });
    }

    static Event zzb(zzkw zzkwVar, zzku zzkuVar) {
        int iZza = zzkwVar.zza();
        return zzkuVar.zza() != 0 ? Event.ofData(zzkuVar.zze(iZza, false)) : Event.ofTelemetry(zzkuVar.zze(iZza, false));
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzlb
    public final void zza(zzku zzkuVar) {
        if (this.zzc.zza() != 0) {
            ((Transport) this.zzb.get()).send(zzb(this.zzc, zzkuVar));
            return;
        }
        Provider provider = this.zza;
        if (provider != null) {
            ((Transport) provider.get()).send(zzb(this.zzc, zzkuVar));
        }
    }
}
