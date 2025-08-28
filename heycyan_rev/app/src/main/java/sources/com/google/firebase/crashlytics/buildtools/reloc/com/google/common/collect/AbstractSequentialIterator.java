package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect;

import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.util.NoSuchElementException;

/* loaded from: classes2.dex */
public abstract class AbstractSequentialIterator<T> extends UnmodifiableIterator<T> {

    @NullableDecl
    private T nextOrNull;

    protected abstract T computeNext(T t);

    protected AbstractSequentialIterator(@NullableDecl T t) {
        this.nextOrNull = t;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.nextOrNull != null;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        try {
            T t = this.nextOrNull;
            this.nextOrNull = computeNext(t);
            return t;
        } catch (Throwable th) {
            this.nextOrNull = computeNext(this.nextOrNull);
            throw th;
        }
    }
}
