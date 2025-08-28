package com.google.mlkit.p012nl.languageid.internal;

import android.content.Context;
import com.google.mlkit.p012nl.languageid.LanguageIdentificationOptions;

/* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
/* loaded from: classes2.dex */
public interface LanguageIdentifierCreatorDelegate {
    LanguageIdentifierDelegate create(Context context, LanguageIdentificationOptions languageIdentificationOptions);

    int getPriority();
}
