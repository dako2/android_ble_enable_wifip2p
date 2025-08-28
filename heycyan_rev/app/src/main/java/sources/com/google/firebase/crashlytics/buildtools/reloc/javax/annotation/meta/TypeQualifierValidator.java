package com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.meta;

import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.Nonnull;
import java.lang.annotation.Annotation;

/* loaded from: classes2.dex */
public interface TypeQualifierValidator<A extends Annotation> {
    @Nonnull
    When forConstantValue(@Nonnull A a, Object obj);
}
