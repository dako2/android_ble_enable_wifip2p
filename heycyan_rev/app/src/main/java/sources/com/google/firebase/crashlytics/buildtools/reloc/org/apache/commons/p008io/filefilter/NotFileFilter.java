package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.filefilter;

import java.io.File;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class NotFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 6131563330944994230L;
    private final IOFileFilter filter;

    public NotFileFilter(IOFileFilter iOFileFilter) {
        if (iOFileFilter == null) {
            throw new IllegalArgumentException("The filter must not be null");
        }
        this.filter = iOFileFilter;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.filefilter.AbstractFileFilter, com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return !this.filter.accept(file);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.filefilter.AbstractFileFilter, com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return !this.filter.accept(file, str);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.filefilter.AbstractFileFilter
    public String toString() {
        return super.toString() + "(" + this.filter.toString() + ")";
    }
}
