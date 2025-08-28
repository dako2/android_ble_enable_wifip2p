package org.tensorflow.lite.support.common.internal;

import org.checkerframework.checker.nullness.qual.Nullable;

/* loaded from: classes3.dex */
public final class SupportPreconditions {
    public static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException("The object reference is null.");
    }

    public static <T> T checkNotNull(T t, @Nullable Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static String checkNotEmpty(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Given String is empty or null.");
        }
        return str;
    }

    public static String checkNotEmpty(String str, Object obj) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
        return str;
    }

    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean z, @Nullable Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static int checkElementIndex(int i, int i2) {
        return checkElementIndex(i, i2, "index");
    }

    public static int checkElementIndex(int i, int i2, @Nullable String str) {
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(badElementIndex(i, i2, str));
        }
        return i;
    }

    public static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean z, @Nullable Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    private static String badElementIndex(int i, int i2, @Nullable String str) {
        if (i < 0) {
            return String.format("%s (%s) must not be negative", str, Integer.valueOf(i));
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("negative size: " + i2);
        }
        return String.format("%s (%s) must be less than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
    }

    private SupportPreconditions() {
        throw new AssertionError("SupportPreconditions is Uninstantiable.");
    }
}
