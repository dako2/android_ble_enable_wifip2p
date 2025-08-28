package com.google.firebase.crashlytics.buildtools.ndk.internal.csym;

import com.google.firebase.crashlytics.buildtools.Buildtools;
import com.google.firebase.crashlytics.buildtools.ndk.NativeSymbolGenerator;
import com.google.firebase.crashlytics.buildtools.ndk.internal.CodeMappingException;
import com.google.firebase.crashlytics.buildtools.utils.FileUtils;
import java.io.File;
import java.io.IOException;

/* loaded from: classes2.dex */
public class NdkCSymGenerator implements NativeSymbolGenerator {
    public static final String CSYM_SUFFIX = ".cSYM";

    @Override // com.google.firebase.crashlytics.buildtools.ndk.NativeSymbolGenerator
    public File generateSymbols(File file, File file2) throws CodeMappingException, IOException {
        return generateSymbolFileFromFile(file, file2, new ElfCSymFactory(true), new StandardCSymFileWriter());
    }

    File generateSymbolFileFromFile(File file, File file2, CSymFactory cSymFactory, CSymFileWriter cSymFileWriter) throws CodeMappingException, IOException {
        Buildtools.logD("Generating native symbol file from: " + file);
        if (file == null || !file.isFile()) {
            throw new CodeMappingException("Specified path is not a file: " + file);
        }
        FileUtils.verifyDirectory(file2);
        try {
            CSym cSymCreateCSymFromFile = cSymFactory.createCSymFromFile(file);
            if (cSymCreateCSymFromFile.getUUID() == null || cSymCreateCSymFromFile.getUUID().equals("")) {
                Buildtools.logD("Crashlytics could not generate a UUID for " + file + ", skipping.");
            }
            if (cSymCreateCSymFromFile.getSymbols().isEmpty()) {
                Buildtools.logD("Crashlytics found no symbols for " + file + ", skipping.");
                return null;
            }
            File file3 = new File(file2, NativeSymbolGenerator.createSymbolFileBasename(file.getName(), cSymCreateCSymFromFile.getArchitecture(), cSymCreateCSymFromFile.getUUID()) + CSYM_SUFFIX);
            cSymFileWriter.writeCSymFile(cSymCreateCSymFromFile, file3);
            return file3;
        } catch (CSymException e) {
            throw new CodeMappingException(e);
        }
    }
}
