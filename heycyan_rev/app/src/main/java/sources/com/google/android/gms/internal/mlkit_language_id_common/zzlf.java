package com.google.android.gms.internal.mlkit_language_id_common;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import com.iflytek.sparkchain.utils.DataUtil;
import java.io.UnsupportedEncodingException;

/* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
/* loaded from: classes.dex */
public final class zzlf implements zzku {
    private final zzhw zza;
    private zzjw zzb = new zzjw();
    private final int zzc;

    private zzlf(zzhw zzhwVar, int i) {
        this.zza = zzhwVar;
        zzlo.zza();
        this.zzc = i;
    }

    public static zzku zzf(zzhw zzhwVar) {
        return new zzlf(zzhwVar, 0);
    }

    public static zzku zzg(zzhw zzhwVar, int i) {
        return new zzlf(zzhwVar, 1);
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzku
    public final int zza() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzku
    public final zzku zzb(zzhv zzhvVar) {
        this.zza.zzd(zzhvVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzku
    public final zzku zzc(zzjw zzjwVar) {
        this.zzb = zzjwVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzku
    public final String zzd() {
        zzjy zzjyVarZzd = this.zza.zzg().zzd();
        return (zzjyVarZzd == null || zzl.zzb(zzjyVarZzd.zzk())) ? "NA" : (String) Preconditions.checkNotNull(zzjyVarZzd.zzk());
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzku
    public final byte[] zze(int i, boolean z) {
        this.zzb.zzf(Boolean.valueOf(1 == (i ^ 1)));
        this.zzb.zze(false);
        this.zza.zzf(this.zzb.zzm());
        try {
            zzlo.zza();
            if (i == 0) {
                return new JsonDataEncoderBuilder().configureWith(zzgi.zza).ignoreNullValues(true).build().encode(this.zza.zzg()).getBytes(DataUtil.UTF8);
            }
            zzhy zzhyVarZzg = this.zza.zzg();
            zzaq zzaqVar = new zzaq();
            zzgi.zza.configure(zzaqVar);
            return zzaqVar.zza().zza(zzhyVarZzg);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
        }
    }
}
