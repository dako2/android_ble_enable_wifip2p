package com.google.firebase.crashlytics.buildtools.utils;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes2.dex */
public class FileUtils {
    public static final String[] FILE_EXTENSIONS = {"so"};
    public static final String GZIPPED_FILE_SUFFIX = ".gz";

    public static void verifyDirectory(File file) throws IOException {
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file.exists() || !file.isDirectory()) {
            throw new IOException("Could not create directory: " + file);
        }
    }

    public static void redirect(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                outputStream.write(bArr, 0, i);
            } else {
                outputStream.flush();
                return;
            }
        }
    }

    public static void gZipFile(File file, File file2) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(new FileOutputStream(file2));
            try {
                IOUtils.copy(bufferedInputStream, gZIPOutputStream);
                gZIPOutputStream.close();
                bufferedInputStream.close();
            } finally {
            }
        } catch (Throwable th) {
            try {
                bufferedInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static boolean isZipFile(File file) {
        return file.exists() && file.isFile() && file.getName().toLowerCase().endsWith(".zip");
    }

    public static void unzipArchive(File file, File file2) throws IOException {
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        while (enumerationEntries.hasMoreElements()) {
            unzipEntry(zipFile, enumerationEntries.nextElement(), file2);
        }
    }

    private static void unzipEntry(ZipFile zipFile, ZipEntry zipEntry, File file) throws IOException {
        if (zipEntry.isDirectory()) {
            verifyDirectory(new File(file, zipEntry.getName()));
            return;
        }
        File file2 = new File(file, zipEntry.getName());
        if (!file2.getParentFile().exists()) {
            verifyDirectory(file2.getParentFile());
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
        try {
            try {
                IOUtils.copy(bufferedInputStream, bufferedOutputStream);
            } catch (IOException e) {
                throw e;
            }
        } finally {
            bufferedOutputStream.close();
            bufferedInputStream.close();
        }
    }

    public static void writeInputStreamToFile(InputStream inputStream, File file) throws Throwable {
        BufferedReader bufferedReader;
        if (!file.exists()) {
            file.createNewFile();
        }
        PrintWriter printWriter = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                PrintWriter printWriter2 = new PrintWriter(file, "UTF-8");
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line != null) {
                            printWriter2.println(line);
                        } else {
                            printWriter2.close();
                            bufferedReader.close();
                            return;
                        }
                    } catch (Throwable th) {
                        th = th;
                        printWriter = printWriter2;
                        if (printWriter != null) {
                            printWriter.close();
                        }
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
        }
    }

    public static Collection<File> listFiles(File file) {
        return com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.FileUtils.listFiles(file, FILE_EXTENSIONS, true);
    }
}
