package com.google.firebase.crashlytics.buildtools.ndk.internal.breakpad;

import com.google.firebase.crashlytics.buildtools.Buildtools;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* loaded from: classes2.dex */
public class BreakpadRecords {
    private final String architecture;
    private final String codeId;
    private final String moduleId;
    private final String name;

    /* renamed from: os */
    private final String f247os;

    public static BreakpadRecords createFromBreakpadFile(File file) throws IOException {
        String str;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String[] strArrSplit = bufferedReader.readLine().split(HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR);
        if (strArrSplit.length < 5 || !strArrSplit[0].equals("MODULE")) {
            throw new IOException("Could not find valid module record for Breakpad file: " + file.getAbsolutePath() + " Clean your build directory and try again. Contact Firebase support if the problem persists.");
        }
        String[] strArrSplit2 = new String[0];
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            if (line.startsWith("INFO")) {
                strArrSplit2 = line.split(HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR);
                break;
            }
        }
        bufferedReader.close();
        if (strArrSplit2.length >= 3) {
            str = strArrSplit2[2];
        } else {
            Buildtools.logW("Invalid or missing INFO line, no CODE_ID found for " + file.getAbsolutePath());
            str = null;
        }
        return new BreakpadRecords(strArrSplit[1], strArrSplit[2], strArrSplit[3].toLowerCase(), strArrSplit[4], str.toLowerCase());
    }

    public BreakpadRecords(String str, String str2, String str3, String str4, String str5) {
        this.f247os = str;
        this.architecture = str2;
        this.moduleId = str3;
        this.name = str4;
        this.codeId = str5;
    }

    public String getOs() {
        return this.f247os;
    }

    public String getModuleId() {
        return this.moduleId;
    }

    public String getArchitecture() {
        return this.architecture;
    }

    public String getName() {
        return this.name;
    }

    public String getCodeId() {
        return this.codeId;
    }
}
