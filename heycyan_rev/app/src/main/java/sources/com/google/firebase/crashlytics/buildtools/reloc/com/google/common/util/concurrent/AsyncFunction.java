package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.util.concurrent;

import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;

@FunctionalInterface
/* loaded from: classes2.dex */
public interface AsyncFunction<I, O> {
    ListenableFuture<O> apply(@NullableDecl I i) throws Exception;
}
