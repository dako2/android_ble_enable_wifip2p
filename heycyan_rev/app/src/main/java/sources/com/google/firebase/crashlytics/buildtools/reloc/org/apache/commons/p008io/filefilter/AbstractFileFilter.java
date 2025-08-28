package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.filefilter;

import java.io.File;

/* loaded from: classes2.dex */
public abstract class AbstractFileFilter implements IOFileFilter {
    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return accept(file.getParentFile(), file.getName());
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return accept(new File(file, str));
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
