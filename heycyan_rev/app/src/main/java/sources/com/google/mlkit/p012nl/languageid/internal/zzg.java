package com.google.mlkit.p012nl.languageid.internal;

import android.content.Context;
import androidx.core.os.EnvironmentCompat;
import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.ModelResource;
import com.google.mlkit.p012nl.languageid.IdentifiedLanguage;
import com.google.mlkit.p012nl.languageid.LanguageIdentificationOptions;
import com.google.mlkit.p012nl.languageid.LanguageIdentifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
/* loaded from: classes2.dex */
public final class zzg extends ModelResource {
    private LanguageIdentifierDelegate zza;
    private LanguageIdentificationOptions zzb;
    private final Context zzc;
    private final LanguageIdentifierCreatorDelegate zzd;
    private final boolean zze;

    public zzg(Context context, LanguageIdentifierCreatorDelegate languageIdentifierCreatorDelegate) {
        this.zzc = context;
        this.zzd = languageIdentifierCreatorDelegate;
        this.zze = languageIdentifierCreatorDelegate.getPriority() == 100;
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final void load() throws MlKitException {
        this.taskQueue.checkIsRunningOnCurrentThread();
        if (this.zza == null) {
            LanguageIdentifierDelegate languageIdentifierDelegateCreate = this.zzd.create(this.zzc, this.zzb);
            this.zza = languageIdentifierDelegateCreate;
            languageIdentifierDelegateCreate.init();
        }
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final void release() {
        this.taskQueue.checkIsRunningOnCurrentThread();
        LanguageIdentifierDelegate languageIdentifierDelegate = this.zza;
        if (languageIdentifierDelegate != null) {
            languageIdentifierDelegate.release();
            this.zza = null;
        }
    }

    public final String zzc(String str, float f) throws MlKitException {
        String languageTag;
        if (this.zza == null) {
            load();
        }
        if (str.isEmpty()) {
            return LanguageIdentifier.UNDETERMINED_LANGUAGE_TAG;
        }
        Iterator<IdentifiedLanguage> it = ((LanguageIdentifierDelegate) Preconditions.checkNotNull(this.zza)).identifyPossibleLanguages(str, f).iterator();
        while (true) {
            if (!it.hasNext()) {
                languageTag = "";
                break;
            }
            IdentifiedLanguage next = it.next();
            if (!EnvironmentCompat.MEDIA_UNKNOWN.equals(next.getLanguageTag())) {
                languageTag = next.getLanguageTag();
                break;
            }
        }
        return languageTag.isEmpty() ? LanguageIdentifier.UNDETERMINED_LANGUAGE_TAG : "iw".equals(languageTag) ? "he" : languageTag;
    }

    public final List zzd(String str, float f) throws MlKitException {
        if (this.zza == null) {
            load();
        }
        ArrayList arrayList = new ArrayList();
        if (str.isEmpty()) {
            arrayList.add(new IdentifiedLanguage(LanguageIdentifier.UNDETERMINED_LANGUAGE_TAG, 1.0f));
            return arrayList;
        }
        for (IdentifiedLanguage identifiedLanguage : ((LanguageIdentifierDelegate) Preconditions.checkNotNull(this.zza)).identifyPossibleLanguages(str, f)) {
            if (!EnvironmentCompat.MEDIA_UNKNOWN.equals(identifiedLanguage.getLanguageTag())) {
                arrayList.add(new IdentifiedLanguage("iw".equals(identifiedLanguage.getLanguageTag()) ? "he" : identifiedLanguage.getLanguageTag(), identifiedLanguage.getConfidence()));
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new IdentifiedLanguage(LanguageIdentifier.UNDETERMINED_LANGUAGE_TAG, 1.0f));
        }
        return arrayList;
    }

    final void zze(LanguageIdentificationOptions languageIdentificationOptions) {
        this.zzb = languageIdentificationOptions;
    }

    public final boolean zzf() {
        return this.zze;
    }
}
