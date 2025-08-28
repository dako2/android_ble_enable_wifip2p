package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.filefilter;

import java.io.File;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class FileFileFilter extends AbstractFileFilter implements Serializable {
    public static final IOFileFilter FILE = new FileFileFilter();
    private static final long serialVersionUID = 5345244090827540862L;

    protected FileFileFilter() {
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.filefilter.AbstractFileFilter, com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return file.isFile();
    }
}
