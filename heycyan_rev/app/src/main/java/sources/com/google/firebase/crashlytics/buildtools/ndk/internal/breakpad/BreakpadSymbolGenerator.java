package com.google.firebase.crashlytics.buildtools.ndk.internal.breakpad;

import com.google.firebase.crashlytics.buildtools.Buildtools;
import com.google.firebase.crashlytics.buildtools.ndk.NativeSymbolGenerator;
import com.google.firebase.crashlytics.buildtools.ndk.internal.CodeMappingException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public class BreakpadSymbolGenerator implements NativeSymbolGenerator {
    private static final Set<PosixFilePermission> DUMP_SYMS_PERMISSIONS = Collections.unmodifiableSet(new HashSet(Arrays.asList(PosixFilePermission.OWNER_READ, PosixFilePermission.GROUP_READ, PosixFilePermission.OTHERS_READ, PosixFilePermission.OWNER_WRITE, PosixFilePermission.GROUP_WRITE, PosixFilePermission.OTHERS_WRITE, PosixFilePermission.OWNER_EXECUTE, PosixFilePermission.GROUP_EXECUTE, PosixFilePermission.OTHERS_EXECUTE)));
    private static final String OBJ_FILE = "dump_syms.obj";
    private static final String OS_LINUX = "linux";
    private static final String OS_MAC = "macos";
    private static final String OS_WINDOWS = "windows";
    private final File dumpSymsBin;

    public static File extractDefaultDumpSymsBinary(File file) throws IOException {
        String osForDumpSyms = getOsForDumpSyms();
        File file2 = new File(file, OS_WINDOWS.equals(osForDumpSyms) ? "dump_syms.exe" : "dump_syms.bin");
        if (file2.exists()) {
            Buildtools.logD("Skipping dumpsyms extraction, file exists: " + file2.getAbsolutePath());
            return file2;
        }
        String str = "dump_syms/" + osForDumpSyms + "/dump_syms.bin";
        Buildtools.logD("Extracting dump_syms from " + str + " to " + file2.getAbsolutePath());
        extractResource(str, file2);
        if (!OS_WINDOWS.equals(osForDumpSyms)) {
            Files.setPosixFilePermissions(file2.toPath(), DUMP_SYMS_PERMISSIONS);
        } else {
            if (!file2.setExecutable(true)) {
                Buildtools.logW("File#setExecutable() failed for " + file2.getAbsolutePath() + "; library extracted without setting permissions.");
            }
            Buildtools.logD("Extracting object file to " + file);
            extractResource("dump_syms/windows/dump_syms.obj", new File(file, OBJ_FILE));
        }
        return file2;
    }

    private static void extractResource(String str, File file) throws IOException {
        InputStream resourceAsStream = BreakpadSymbolGenerator.class.getClassLoader().getResourceAsStream(str);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        IOUtils.copy(resourceAsStream, fileOutputStream);
        resourceAsStream.close();
        fileOutputStream.close();
    }

    private static String getOsForDumpSyms() throws IOException {
        String lowerCase = System.getProperty("os.name").toLowerCase();
        if (lowerCase.startsWith(OS_WINDOWS)) {
            return OS_WINDOWS;
        }
        if (lowerCase.startsWith("mac")) {
            return OS_MAC;
        }
        if (lowerCase.startsWith(OS_LINUX)) {
            return OS_LINUX;
        }
        throw new IOException("Cannot extract dump_syms, unexpected os.name: " + lowerCase);
    }

    public BreakpadSymbolGenerator(File file) {
        this.dumpSymsBin = file;
        Buildtools.logD("Breakpad symbol generator initialized: " + file.getAbsolutePath());
    }

    @Override // com.google.firebase.crashlytics.buildtools.ndk.NativeSymbolGenerator
    public File generateSymbols(File file, File file2) throws InterruptedException, CodeMappingException, IOException {
        Buildtools.logD("Crashlytics generating Breakpad Symbol file for: " + file.getAbsolutePath());
        File fileCreateTempFile = File.createTempFile(file.getName(), ".tmp", file2);
        Buildtools.logD("Extracting Breakpad symbols to temp file: " + fileCreateTempFile.getAbsolutePath());
        Process processStart = new ProcessBuilder(this.dumpSymsBin.getAbsolutePath(), file.getAbsolutePath()).redirectOutput(fileCreateTempFile).start();
        try {
            processStart.waitFor();
            if (processStart.exitValue() != 0) {
                throw new IOException("Breakpad symbol generation failed (exit=" + processStart.exitValue() + "), see STDERR");
            }
            BreakpadRecords breakpadRecordsCreateFromBreakpadFile = BreakpadRecords.createFromBreakpadFile(fileCreateTempFile);
            String codeId = breakpadRecordsCreateFromBreakpadFile.getCodeId() != null ? breakpadRecordsCreateFromBreakpadFile.getCodeId() : breakpadRecordsCreateFromBreakpadFile.getModuleId();
            Buildtools.logD("GNU Build Id for " + file.getAbsolutePath() + ": " + codeId);
            File file3 = new File(file2, NativeSymbolGenerator.createSymbolFileBasename(file.getName(), breakpadRecordsCreateFromBreakpadFile.getArchitecture(), codeId) + ".sym");
            Buildtools.logD("Renaming Breakpad symbol file to: " + file3.getAbsolutePath());
            Files.move(fileCreateTempFile.toPath(), file3.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return file3;
        } catch (InterruptedException e) {
            throw new IOException("Dump symbols was unexpectedly interrupted.", e);
        }
    }
}
