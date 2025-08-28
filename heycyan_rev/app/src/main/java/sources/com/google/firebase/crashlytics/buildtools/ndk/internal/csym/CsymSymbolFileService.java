package com.google.firebase.crashlytics.buildtools.ndk.internal.csym;

import com.google.firebase.crashlytics.buildtools.api.FirebaseSymbolFileService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* loaded from: classes2.dex */
public class CsymSymbolFileService extends FirebaseSymbolFileService {
    private static final String CSYM_UPLOAD_REQUEST_FORMAT = "%s/v1/project/-/app/%s/upload/native/%s";

    public CsymSymbolFileService() {
        super(CSYM_UPLOAD_REQUEST_FORMAT);
    }

    @Override // com.google.firebase.crashlytics.buildtools.api.FirebaseSymbolFileService
    protected String extractUuid(File file) throws IOException {
        String[] strArrSplit = readCSymHeader(file).split("\t");
        if (strArrSplit.length != 8 || !strArrSplit[0].equals("code_mapping")) {
            throw new IOException("Invalid cSYM header for " + file.getAbsolutePath());
        }
        return strArrSplit[3];
    }

    private static String readCSymHeader(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        try {
            String line = bufferedReader.readLine();
            bufferedReader.close();
            if (line == null || line.length() == 0) {
                throw new IOException("Could not read cSYM header for " + file.getPath());
            }
            return line;
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
