package com.google.mlkit.p012nl.languageid;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executor;

/* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
/* loaded from: classes2.dex */
public class LanguageIdentificationOptions {
    public static final LanguageIdentificationOptions zza = new Builder().build();
    private final Float zzb;
    private final Executor zzc;

    /* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
    public static class Builder {
        private Float zza;
        private Executor zzb;

        public LanguageIdentificationOptions build() {
            return new LanguageIdentificationOptions(this.zza, this.zzb, null);
        }

        public Builder setConfidenceThreshold(float f) {
            boolean z = false;
            if (f >= 0.0f && f <= 1.0f) {
                z = true;
            }
            Float fValueOf = Float.valueOf(f);
            Preconditions.checkArgument(z, "Threshold value %f should be between 0 and 1", fValueOf);
            this.zza = fValueOf;
            return this;
        }

        public Builder setExecutor(Executor executor) {
            Preconditions.checkArgument(executor != null, "Custom executor should not be null");
            this.zzb = executor;
            return this;
        }
    }

    /* synthetic */ LanguageIdentificationOptions(Float f, Executor executor, zza zzaVar) {
        this.zzb = f;
        this.zzc = executor;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LanguageIdentificationOptions)) {
            return false;
        }
        LanguageIdentificationOptions languageIdentificationOptions = (LanguageIdentificationOptions) obj;
        return Objects.equal(languageIdentificationOptions.zzb, this.zzb) && Objects.equal(languageIdentificationOptions.zzc, this.zzc);
    }

    public Float getConfidenceThreshold() {
        return this.zzb;
    }

    public Executor getExecutor() {
        return this.zzc;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzb, this.zzc);
    }
}
