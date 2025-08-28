package com.google.firebase.crashlytics.buildtools.ndk;

import com.google.firebase.crashlytics.buildtools.ndk.internal.CodeMappingException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.FilenameUtils;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOCase;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.filefilter.IOFileFilter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.filefilter.SuffixFileFilter;
import java.io.File;
import java.io.IOException;

/* loaded from: classes2.dex */
public interface NativeSymbolGenerator {
    public static final String LIB_PREFIX = "lib";
    public static final IOFileFilter SO_FILE_FILTER;
    public static final String[] SYMBOL_FILE_SUFFIXES;

    File generateSymbols(File file, File file2) throws CodeMappingException, IOException;

    static {
        String[] strArr = {".so", ".symbols"};
        SYMBOL_FILE_SUFFIXES = strArr;
        SO_FILE_FILTER = new SuffixFileFilter(strArr, IOCase.INSENSITIVE);
    }

    static String createSymbolFileBasename(String str, String str2, String str3) {
        String strRemoveExtension = FilenameUtils.removeExtension(str);
        if (strRemoveExtension.startsWith(LIB_PREFIX)) {
            strRemoveExtension = strRemoveExtension.substring(LIB_PREFIX.length());
        }
        return String.format("%s-%s-%s", strRemoveExtension, str2, str3);
    }
}
