package com.google.firebase.crashlytics.buildtools.api;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.FilenameUtils;
import com.google.firebase.crashlytics.buildtools.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/* loaded from: classes2.dex */
public abstract class FirebaseSymbolFileService implements SymbolFileService {
    private final String uploadRequestFormat;

    protected abstract String extractUuid(File file) throws IOException;

    protected FirebaseSymbolFileService(String str) {
        this.uploadRequestFormat = str;
    }

    @Override // com.google.firebase.crashlytics.buildtools.api.SymbolFileService
    public void uploadNativeSymbolFile(WebApi webApi, File file, String str) throws IOException {
        File file2;
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            file2 = new File(FilenameUtils.removeExtension(file.getName()) + FileUtils.GZIPPED_FILE_SUFFIX);
        } else {
            file2 = new File(parentFile, FilenameUtils.removeExtension(file.getName()) + FileUtils.GZIPPED_FILE_SUFFIX);
        }
        FileUtils.gZipFile(file, file2);
        webApi.uploadFile(new URL(String.format(this.uploadRequestFormat, webApi.getCodeMappingApiUrl(), str, extractUuid(file))), file2);
        file2.delete();
    }
}
