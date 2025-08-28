package com.google.firebase.crashlytics.buildtools.ndk.internal.csym;

import com.google.firebase.crashlytics.buildtools.ndk.internal.csym.CSym;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Charsets;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class CSymWriter {
    private static final int CSYM_VERSION = 1;
    private static final String HEADER_FORMAT = "code_mapping\t%d\t%s\t%s\t%s\t%d\t%d\t%d\n";
    private static final String NO_INDEX = "-1";

    public static void writeToOutputStream(CSym cSym, OutputStream outputStream) throws IOException {
        write(cSym, new OutputStreamWriter(outputStream, Charsets.UTF_8));
    }

    public static void writeToTextFile(CSym cSym, File file) throws Throwable {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (Throwable th) {
            th = th;
        }
        try {
            writeToOutputStream(cSym, fileOutputStream);
            fileOutputStream.close();
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }

    private static void write(CSym cSym, Writer writer) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        writeHeader(cSym, bufferedWriter);
        writeFiles(cSym, bufferedWriter);
        writeSymbols(cSym, bufferedWriter);
        writeRanges(cSym, bufferedWriter);
        bufferedWriter.flush();
    }

    private static void writeHeader(CSym cSym, BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.append((CharSequence) String.format(HEADER_FORMAT, 1, cSym.getType(), cSym.getUUID(), cSym.getArchitecture(), Integer.valueOf(cSym.getFiles().size()), Integer.valueOf(cSym.getSymbols().size()), Integer.valueOf(cSym.getRanges().size())));
    }

    private static void writeFiles(CSym cSym, BufferedWriter bufferedWriter) throws IOException {
        List<String> files = cSym.getFiles();
        bufferedWriter.append((CharSequence) ("files\t" + files.size() + IOUtils.LINE_SEPARATOR_UNIX));
        Iterator<String> it = files.iterator();
        while (it.hasNext()) {
            bufferedWriter.append((CharSequence) (it.next() + IOUtils.LINE_SEPARATOR_UNIX));
        }
    }

    private static void writeSymbols(CSym cSym, BufferedWriter bufferedWriter) throws IOException {
        List<String> symbols = cSym.getSymbols();
        bufferedWriter.append((CharSequence) ("symbols\t" + symbols.size() + IOUtils.LINE_SEPARATOR_UNIX));
        Iterator<String> it = symbols.iterator();
        while (it.hasNext()) {
            bufferedWriter.append((CharSequence) (it.next() + IOUtils.LINE_SEPARATOR_UNIX));
        }
    }

    private static void writeRanges(CSym cSym, BufferedWriter bufferedWriter) throws IOException {
        List<String> files = cSym.getFiles();
        List<String> symbols = cSym.getSymbols();
        List<CSym.Range> ranges = cSym.getRanges();
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        for (int i = 0; i < symbols.size(); i++) {
            map2.put(symbols.get(i), String.valueOf(i));
        }
        for (int i2 = 0; i2 < files.size(); i2++) {
            map.put(files.get(i2), String.valueOf(i2));
        }
        bufferedWriter.append((CharSequence) ("ranges\t" + ranges.size() + IOUtils.LINE_SEPARATOR_UNIX));
        for (CSym.Range range : ranges) {
            bufferedWriter.append((CharSequence) (range.offset + "\t" + range.size + "\t" + nullSafeIndex((String) map2.get(range.symbol)) + "\t" + nullSafeIndex((String) map.get(range.file)) + "\t" + range.lineNumber + IOUtils.LINE_SEPARATOR_UNIX));
        }
    }

    private static String nullSafeIndex(String str) {
        return str != null ? str : NO_INDEX;
    }
}
