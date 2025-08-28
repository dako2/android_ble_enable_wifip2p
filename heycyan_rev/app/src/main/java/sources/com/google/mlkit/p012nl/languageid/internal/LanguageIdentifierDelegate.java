package com.google.mlkit.p012nl.languageid.internal;

import com.google.mlkit.common.MlKitException;
import com.google.mlkit.p012nl.languageid.IdentifiedLanguage;
import java.util.List;

/* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
/* loaded from: classes2.dex */
public interface LanguageIdentifierDelegate {
    List<IdentifiedLanguage> identifyPossibleLanguages(String str, float f) throws MlKitException;

    void init() throws MlKitException;

    void release();
}
