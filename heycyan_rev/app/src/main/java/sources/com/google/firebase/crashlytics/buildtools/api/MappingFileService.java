package com.google.firebase.crashlytics.buildtools.api;

import com.google.firebase.crashlytics.buildtools.AppBuildInfo;
import com.google.firebase.crashlytics.buildtools.Obfuscator;
import java.io.File;
import java.io.IOException;

/* loaded from: classes2.dex */
public interface MappingFileService {
    void uploadMappingFile(File file, String str, AppBuildInfo appBuildInfo, Obfuscator obfuscator) throws IOException;
}
