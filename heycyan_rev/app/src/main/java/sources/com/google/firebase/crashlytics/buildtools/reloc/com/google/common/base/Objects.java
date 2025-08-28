package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base;

import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class Objects extends ExtraObjectsMethodsForWeb {
    private Objects() {
    }

    public static boolean equal(@NullableDecl Object obj, @NullableDecl Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int hashCode(@NullableDecl Object... objArr) {
        return Arrays.hashCode(objArr);
    }
}
