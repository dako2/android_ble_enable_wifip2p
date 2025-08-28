package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base;

import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;

@FunctionalInterface
/* loaded from: classes2.dex */
public interface Predicate<T> extends java.util.function.Predicate<T> {
    boolean apply(@NullableDecl T t);

    boolean equals(@NullableDecl Object obj);

    @Override // java.util.function.Predicate
    default boolean test(@NullableDecl T t) {
        return apply(t);
    }
}
