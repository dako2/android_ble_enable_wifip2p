package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import com.iflytek.sparkchain.utils.DataUtil;
import java.io.UnsupportedEncodingException;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
public final class zzml implements zzlz {
    private final zziv zza;
    private zzkw zzb = new zzkw();

    private zzml(zziv zzivVar, int i) {
        this.zza = zzivVar;
        zzmw.zza();
    }

    public static zzlz zzf(zziv zzivVar) {
        return new zzml(zzivVar, 0);
    }

    public static zzlz zzg() {
        return new zzml(new zziv(), 0);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzlz
    public final zzlz zza(zziu zziuVar) {
        this.zza.zzf(zziuVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzlz
    public final zzlz zzb(zzjb zzjbVar) {
        this.zza.zzi(zzjbVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzlz
    public final zzlz zzc(zzkw zzkwVar) {
        this.zzb = zzkwVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzlz
    public final String zzd() {
        zzky zzkyVarZzf = this.zza.zzk().zzf();
        return (zzkyVarZzf == null || zzad.zzc(zzkyVarZzf.zzk())) ? "NA" : (String) Preconditions.checkNotNull(zzkyVarZzf.zzk());
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzlz
    public final byte[] zze(int i, boolean z) {
        this.zzb.zzf(Boolean.valueOf(1 == (i ^ 1)));
        this.zzb.zze(false);
        this.zza.zzj(this.zzb.zzm());
        try {
            zzmw.zza();
            if (i == 0) {
                return new JsonDataEncoderBuilder().configureWith(zzhf.zza).ignoreNullValues(true).build().encode(this.zza.zzk()).getBytes(DataUtil.UTF8);
            }
            zzix zzixVarZzk = this.zza.zzk();
            zzbn zzbnVar = new zzbn();
            zzhf.zza.configure(zzbnVar);
            return zzbnVar.zza().zza(zzixVarZzk);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
        }
    }
}
