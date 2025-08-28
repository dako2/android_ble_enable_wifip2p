package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base;

import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.util.Collections;
import java.util.Set;

/* loaded from: classes2.dex */
final class Absent<T> extends Optional<T> {
    static final Absent<Object> INSTANCE = new Absent<>();
    private static final long serialVersionUID = 0;

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    public boolean equals(@NullableDecl Object obj) {
        return obj == this;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    public int hashCode() {
        return 2040732332;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    public boolean isPresent() {
        return false;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    @NullableDecl
    public T orNull() {
        return null;
    }

    static <T> Optional<T> withType() {
        return INSTANCE;
    }

    private Absent() {
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    public T get() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    /* renamed from: or */
    public T mo305or(T t) {
        return (T) Preconditions.checkNotNull(t, "use Optional.orNull() instead of Optional.or(null)");
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    /* renamed from: or */
    public Optional<T> mo303or(Optional<? extends T> optional) {
        return (Optional) Preconditions.checkNotNull(optional);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    /* renamed from: or */
    public T mo304or(Supplier<? extends T> supplier) {
        return (T) Preconditions.checkNotNull(supplier.get(), "use Optional.orNull() instead of a Supplier that returns null");
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    public Set<T> asSet() {
        return Collections.emptySet();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    public <V> Optional<V> transform(Function<? super T, V> function) {
        Preconditions.checkNotNull(function);
        return Optional.absent();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    public String toString() {
        return "Optional.absent()";
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
