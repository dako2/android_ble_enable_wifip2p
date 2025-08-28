package org.checkerframework.checker.index.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.JavaExpression;

@Target({ElementType.FIELD})
/* loaded from: classes3.dex */
public @interface HasSubsequence {
    @JavaExpression
    String from();

    @JavaExpression
    /* renamed from: to */
    String m647to();

    @JavaExpression
    String value();
}
