package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base;

import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.util.Collections;
import java.util.Set;

/* loaded from: classes2.dex */
final class Present<T> extends Optional<T> {
    private static final long serialVersionUID = 0;
    private final T reference;

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    public boolean isPresent() {
        return true;
    }

    Present(T t) {
        this.reference = t;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    public T get() {
        return this.reference;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    /* renamed from: or */
    public T mo305or(T t) {
        Preconditions.checkNotNull(t, "use Optional.orNull() instead of Optional.or(null)");
        return this.reference;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    /* renamed from: or */
    public Optional<T> mo303or(Optional<? extends T> optional) {
        Preconditions.checkNotNull(optional);
        return this;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    /* renamed from: or */
    public T mo304or(Supplier<? extends T> supplier) {
        Preconditions.checkNotNull(supplier);
        return this.reference;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    public T orNull() {
        return this.reference;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    public Set<T> asSet() {
        return Collections.singleton(this.reference);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    public <V> Optional<V> transform(Function<? super T, V> function) {
        return new Present(Preconditions.checkNotNull(function.apply(this.reference), "the Function passed to Optional.transform() must not return null."));
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    public boolean equals(@NullableDecl Object obj) {
        if (obj instanceof Present) {
            return this.reference.equals(((Present) obj).reference);
        }
        return false;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    public int hashCode() {
        return this.reference.hashCode() + 1502476572;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional
    public String toString() {
        return "Optional.of(" + this.reference + ")";
    }
}
