package androidx.core.animation;

/* loaded from: classes.dex */
public abstract class TypeConverter<T, V> {
    private final Class<T> mFromClass;
    private final Class<V> mToClass;

    public abstract V convert(T t);

    public TypeConverter(Class<T> cls, Class<V> cls2) {
        this.mFromClass = cls;
        this.mToClass = cls2;
    }

    Class<V> getTargetType() {
        return this.mToClass;
    }

    Class<T> getSourceType() {
        return this.mFromClass;
    }
}
