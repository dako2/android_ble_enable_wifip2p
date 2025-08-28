package com.google.mlkit.p012nl.languageid.bundled.internal;

import com.google.android.gms.internal.mlkit_language_id_bundled.zbj;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.mlkit.p012nl.languageid.internal.LanguageIdentifierCreatorDelegate;
import java.util.List;

/* compiled from: com.google.mlkit:language-id@@17.0.4 */
/* loaded from: classes2.dex */
public class ThickLanguageIdRegistrar implements ComponentRegistrar {
    public static final /* synthetic */ int zba = 0;

    @Override // com.google.firebase.components.ComponentRegistrar
    public final List getComponents() {
        return zbj.zbg(Component.intoSetBuilder(LanguageIdentifierCreatorDelegate.class).factory(new ComponentFactory() { // from class: com.google.mlkit.nl.languageid.bundled.internal.zba
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                int i = ThickLanguageIdRegistrar.zba;
                return new ThickLanguageIdentifierCreator();
            }
        }).build());
    }
}
