package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base;

import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.io.Serializable;
import java.util.Map;

/* loaded from: classes2.dex */
public final class Functions {
    private Functions() {
    }

    public static Function<Object, String> toStringFunction() {
        return ToStringFunction.INSTANCE;
    }

    private enum ToStringFunction implements Function<Object, String> {
        INSTANCE;

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function, java.util.function.Function
        public String apply(Object obj) {
            Preconditions.checkNotNull(obj);
            return obj.toString();
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Functions.toStringFunction()";
        }
    }

    public static <E> Function<E, E> identity() {
        return IdentityFunction.INSTANCE;
    }

    private enum IdentityFunction implements Function<Object, Object> {
        INSTANCE;

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function, java.util.function.Function
        @NullableDecl
        public Object apply(@NullableDecl Object obj) {
            return obj;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Functions.identity()";
        }
    }

    public static <K, V> Function<K, V> forMap(Map<K, V> map) {
        return new FunctionForMapNoDefault(map);
    }

    public static <K, V> Function<K, V> forMap(Map<K, ? extends V> map, @NullableDecl V v) {
        return new ForMapWithDefault(map, v);
    }

    private static class FunctionForMapNoDefault<K, V> implements Function<K, V>, Serializable {
        private static final long serialVersionUID = 0;
        final Map<K, V> map;

        FunctionForMapNoDefault(Map<K, V> map) {
            this.map = (Map) Preconditions.checkNotNull(map);
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function, java.util.function.Function
        public V apply(@NullableDecl K k) {
            V v = this.map.get(k);
            Preconditions.checkArgument(v != null || this.map.containsKey(k), "Key '%s' not present in map", k);
            return v;
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof FunctionForMapNoDefault) {
                return this.map.equals(((FunctionForMapNoDefault) obj).map);
            }
            return false;
        }

        public int hashCode() {
            return this.map.hashCode();
        }

        public String toString() {
            return "Functions.forMap(" + this.map + ")";
        }
    }

    private static class ForMapWithDefault<K, V> implements Function<K, V>, Serializable {
        private static final long serialVersionUID = 0;

        @NullableDecl
        final V defaultValue;
        final Map<K, ? extends V> map;

        ForMapWithDefault(Map<K, ? extends V> map, @NullableDecl V v) {
            this.map = (Map) Preconditions.checkNotNull(map);
            this.defaultValue = v;
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function, java.util.function.Function
        public V apply(@NullableDecl K k) {
            V v = this.map.get(k);
            return (v != null || this.map.containsKey(k)) ? v : this.defaultValue;
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function
        public boolean equals(@NullableDecl Object obj) {
            if (!(obj instanceof ForMapWithDefault)) {
                return false;
            }
            ForMapWithDefault forMapWithDefault = (ForMapWithDefault) obj;
            return this.map.equals(forMapWithDefault.map) && Objects.equal(this.defaultValue, forMapWithDefault.defaultValue);
        }

        public int hashCode() {
            return Objects.hashCode(this.map, this.defaultValue);
        }

        public String toString() {
            return "Functions.forMap(" + this.map + ", defaultValue=" + this.defaultValue + ")";
        }
    }

    public static <A, B, C> Function<A, C> compose(Function<B, C> function, Function<A, ? extends B> function2) {
        return new FunctionComposition(function, function2);
    }

    private static class FunctionComposition<A, B, C> implements Function<A, C>, Serializable {
        private static final long serialVersionUID = 0;

        /* renamed from: f */
        private final Function<A, ? extends B> f279f;

        /* renamed from: g */
        private final Function<B, C> f280g;

        public FunctionComposition(Function<B, C> function, Function<A, ? extends B> function2) {
            this.f280g = (Function) Preconditions.checkNotNull(function);
            this.f279f = (Function) Preconditions.checkNotNull(function2);
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function, java.util.function.Function
        public C apply(@NullableDecl A a) {
            return (C) this.f280g.apply(this.f279f.apply(a));
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function
        public boolean equals(@NullableDecl Object obj) {
            if (!(obj instanceof FunctionComposition)) {
                return false;
            }
            FunctionComposition functionComposition = (FunctionComposition) obj;
            return this.f279f.equals(functionComposition.f279f) && this.f280g.equals(functionComposition.f280g);
        }

        public int hashCode() {
            return this.f279f.hashCode() ^ this.f280g.hashCode();
        }

        public String toString() {
            return this.f280g + "(" + this.f279f + ")";
        }
    }

    public static <T> Function<T, Boolean> forPredicate(Predicate<T> predicate) {
        return new PredicateFunction(predicate);
    }

    private static class PredicateFunction<T> implements Function<T, Boolean>, Serializable {
        private static final long serialVersionUID = 0;
        private final Predicate<T> predicate;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function, java.util.function.Function
        public /* bridge */ /* synthetic */ Object apply(@NullableDecl Object obj) {
            return apply((PredicateFunction<T>) obj);
        }

        private PredicateFunction(Predicate<T> predicate) {
            this.predicate = (Predicate) Preconditions.checkNotNull(predicate);
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function, java.util.function.Function
        public Boolean apply(@NullableDecl T t) {
            return Boolean.valueOf(this.predicate.apply(t));
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof PredicateFunction) {
                return this.predicate.equals(((PredicateFunction) obj).predicate);
            }
            return false;
        }

        public int hashCode() {
            return this.predicate.hashCode();
        }

        public String toString() {
            return "Functions.forPredicate(" + this.predicate + ")";
        }
    }

    public static <E> Function<Object, E> constant(@NullableDecl E e) {
        return new ConstantFunction(e);
    }

    private static class ConstantFunction<E> implements Function<Object, E>, Serializable {
        private static final long serialVersionUID = 0;

        @NullableDecl
        private final E value;

        public ConstantFunction(@NullableDecl E e) {
            this.value = e;
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function, java.util.function.Function
        public E apply(@NullableDecl Object obj) {
            return this.value;
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof ConstantFunction) {
                return Objects.equal(this.value, ((ConstantFunction) obj).value);
            }
            return false;
        }

        public int hashCode() {
            E e = this.value;
            if (e == null) {
                return 0;
            }
            return e.hashCode();
        }

        public String toString() {
            return "Functions.constant(" + this.value + ")";
        }
    }

    public static <T> Function<Object, T> forSupplier(Supplier<T> supplier) {
        return new SupplierFunction(supplier);
    }

    private static class SupplierFunction<T> implements Function<Object, T>, Serializable {
        private static final long serialVersionUID = 0;
        private final Supplier<T> supplier;

        private SupplierFunction(Supplier<T> supplier) {
            this.supplier = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function, java.util.function.Function
        public T apply(@NullableDecl Object obj) {
            return this.supplier.get();
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof SupplierFunction) {
                return this.supplier.equals(((SupplierFunction) obj).supplier);
            }
            return false;
        }

        public int hashCode() {
            return this.supplier.hashCode();
        }

        public String toString() {
            return "Functions.forSupplier(" + this.supplier + ")";
        }
    }
}
