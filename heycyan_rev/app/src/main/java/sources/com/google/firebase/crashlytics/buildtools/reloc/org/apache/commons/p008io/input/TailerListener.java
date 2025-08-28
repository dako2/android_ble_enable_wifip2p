package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.input;

/* loaded from: classes2.dex */
public interface TailerListener {
    void fileNotFound();

    void fileRotated();

    void handle(Exception exc);

    void handle(String str);

    void init(Tailer tailer);
}
