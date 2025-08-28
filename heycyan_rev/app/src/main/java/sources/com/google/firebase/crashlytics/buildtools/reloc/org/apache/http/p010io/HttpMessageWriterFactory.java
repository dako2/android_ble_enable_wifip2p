package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpMessage;

/* loaded from: classes2.dex */
public interface HttpMessageWriterFactory<T extends HttpMessage> {
    HttpMessageWriter<T> create(SessionOutputBuffer sessionOutputBuffer);
}
