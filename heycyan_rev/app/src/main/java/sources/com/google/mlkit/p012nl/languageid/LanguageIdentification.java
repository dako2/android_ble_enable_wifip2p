package com.google.mlkit.p012nl.languageid;

import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.p012nl.languageid.internal.LanguageIdentifierImpl;

/* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
/* loaded from: classes2.dex */
public class LanguageIdentification {
    private LanguageIdentification() {
    }

    public static LanguageIdentifier getClient() {
        return ((LanguageIdentifierImpl.Factory) MlKitContext.getInstance().get(LanguageIdentifierImpl.Factory.class)).create(LanguageIdentificationOptions.zza);
    }

    public static LanguageIdentifier getClient(LanguageIdentificationOptions languageIdentificationOptions) {
        Preconditions.checkNotNull(languageIdentificationOptions, "LanguageIdentificationOptions can not be null");
        return ((LanguageIdentifierImpl.Factory) MlKitContext.getInstance().get(LanguageIdentifierImpl.Factory.class)).create(languageIdentificationOptions);
    }
}
