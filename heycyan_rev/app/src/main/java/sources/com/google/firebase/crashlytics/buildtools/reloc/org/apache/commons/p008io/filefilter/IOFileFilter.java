package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.filefilter;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/* loaded from: classes2.dex */
public interface IOFileFilter extends FileFilter, FilenameFilter {
    @Override // java.io.FileFilter
    boolean accept(File file);

    boolean accept(File file, String str);
}
