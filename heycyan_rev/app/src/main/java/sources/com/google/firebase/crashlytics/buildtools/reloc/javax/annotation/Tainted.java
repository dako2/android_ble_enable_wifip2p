package com.google.firebase.crashlytics.buildtools.reloc.javax.annotation;

import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.meta.When;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Untainted(when = When.MAYBE)
/* loaded from: classes2.dex */
public @interface Tainted {
}
