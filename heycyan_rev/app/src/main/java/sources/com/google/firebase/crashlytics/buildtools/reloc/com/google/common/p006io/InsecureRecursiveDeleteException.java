package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.p006io;

import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.nio.file.FileSystemException;

/* loaded from: classes2.dex */
public final class InsecureRecursiveDeleteException extends FileSystemException {
    public InsecureRecursiveDeleteException(@NullableDecl String str) {
        super(str, null, "unable to guarantee security of recursive delete");
    }
}
