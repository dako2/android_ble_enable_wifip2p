package com.google.firebase.crashlytics.buildtools.reloc.javax.annotation;

import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.meta.TypeQualifier;
import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.meta.TypeQualifierValidator;
import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.meta.When;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@TypeQualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface Nonnull {
    When when() default When.ALWAYS;

    public static class Checker implements TypeQualifierValidator<Nonnull> {
        @Override // com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.meta.TypeQualifierValidator
        public When forConstantValue(Nonnull nonnull, Object obj) {
            if (obj == null) {
                return When.NEVER;
            }
            return When.ALWAYS;
        }
    }
}
