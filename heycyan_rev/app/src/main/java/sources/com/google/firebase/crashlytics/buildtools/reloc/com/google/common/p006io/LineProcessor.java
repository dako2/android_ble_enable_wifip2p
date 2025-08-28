package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.p006io;

import java.io.IOException;

/* loaded from: classes2.dex */
public interface LineProcessor<T> {
    T getResult();

    boolean processLine(String str) throws IOException;
}
