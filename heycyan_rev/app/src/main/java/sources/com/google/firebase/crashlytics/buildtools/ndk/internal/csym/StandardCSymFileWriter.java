package com.google.firebase.crashlytics.buildtools.ndk.internal.csym;

import java.io.File;

/* loaded from: classes2.dex */
public class StandardCSymFileWriter implements CSymFileWriter {
    @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.csym.CSymFileWriter
    public void writeCSymFile(CSym cSym, File file) throws Throwable {
        CSymWriter.writeToTextFile(cSym, file);
    }
}
