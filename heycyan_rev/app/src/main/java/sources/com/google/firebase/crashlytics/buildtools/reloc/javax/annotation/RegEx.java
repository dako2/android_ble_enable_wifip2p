package com.google.firebase.crashlytics.buildtools.reloc.javax.annotation;

import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.meta.TypeQualifierValidator;
import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.meta.When;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Syntax("RegEx")
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface RegEx {
    When when() default When.ALWAYS;

    public static class Checker implements TypeQualifierValidator<RegEx> {
        @Override // com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.meta.TypeQualifierValidator
        public When forConstantValue(RegEx regEx, Object obj) {
            if (!(obj instanceof String)) {
                return When.NEVER;
            }
            try {
                Pattern.compile((String) obj);
                return When.ALWAYS;
            } catch (PatternSyntaxException unused) {
                return When.NEVER;
            }
        }
    }
}
