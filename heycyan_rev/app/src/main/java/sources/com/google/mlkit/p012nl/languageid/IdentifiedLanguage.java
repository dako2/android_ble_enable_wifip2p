package com.google.mlkit.p012nl.languageid;

import com.google.android.gms.internal.mlkit_language_id_common.zze;
import com.google.android.gms.internal.mlkit_language_id_common.zzf;
import com.google.android.gms.internal.mlkit_language_id_common.zzg;
import java.util.Arrays;

/* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
/* loaded from: classes2.dex */
public final class IdentifiedLanguage {
    private final String zza;
    private final float zzb;

    public IdentifiedLanguage(String str, float f) {
        this.zza = str;
        this.zzb = f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IdentifiedLanguage)) {
            return false;
        }
        IdentifiedLanguage identifiedLanguage = (IdentifiedLanguage) obj;
        return Float.compare(identifiedLanguage.zzb, this.zzb) == 0 && zzg.zza(this.zza, identifiedLanguage.zza);
    }

    public float getConfidence() {
        return this.zzb;
    }

    public String getLanguageTag() {
        return this.zza;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, Float.valueOf(this.zzb)});
    }

    public String toString() {
        zze zzeVarZza = zzf.zza(this);
        zzeVarZza.zzb("languageTag", this.zza);
        zzeVarZza.zza("confidence", this.zzb);
        return zzeVarZza.toString();
    }
}
