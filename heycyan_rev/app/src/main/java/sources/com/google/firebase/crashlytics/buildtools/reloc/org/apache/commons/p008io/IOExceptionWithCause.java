package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io;

import java.io.IOException;

@Deprecated
/* loaded from: classes2.dex */
public class IOExceptionWithCause extends IOException {
    private static final long serialVersionUID = 1;

    public IOExceptionWithCause(String str, Throwable th) {
        super(str, th);
    }

    public IOExceptionWithCause(Throwable th) {
        super(th);
    }
}
