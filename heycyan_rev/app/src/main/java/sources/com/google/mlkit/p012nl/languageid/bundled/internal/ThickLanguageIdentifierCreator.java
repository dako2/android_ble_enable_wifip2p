package com.google.mlkit.p012nl.languageid.bundled.internal;

import android.content.Context;
import com.google.mlkit.p012nl.languageid.LanguageIdentificationOptions;
import com.google.mlkit.p012nl.languageid.internal.LanguageIdentifierCreatorDelegate;
import com.google.mlkit.p012nl.languageid.internal.LanguageIdentifierDelegate;

/* compiled from: com.google.mlkit:language-id@@17.0.4 */
/* loaded from: classes2.dex */
public class ThickLanguageIdentifierCreator implements LanguageIdentifierCreatorDelegate {
    @Override // com.google.mlkit.p012nl.languageid.internal.LanguageIdentifierCreatorDelegate
    public final LanguageIdentifierDelegate create(Context context, LanguageIdentificationOptions languageIdentificationOptions) {
        return new ThickLanguageIdentifier(context, languageIdentificationOptions);
    }

    @Override // com.google.mlkit.p012nl.languageid.internal.LanguageIdentifierCreatorDelegate
    public final int getPriority() {
        return 100;
    }
}
