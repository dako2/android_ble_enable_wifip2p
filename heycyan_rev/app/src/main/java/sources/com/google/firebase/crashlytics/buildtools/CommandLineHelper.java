package com.google.firebase.crashlytics.buildtools;

import com.google.firebase.crashlytics.buildtools.Obfuscator;
import com.google.firebase.crashlytics.buildtools.api.SymbolFileService;
import com.google.firebase.crashlytics.buildtools.log.CrashlyticsLogger;
import com.google.firebase.crashlytics.buildtools.ndk.NativeSymbolGenerator;
import com.google.firebase.crashlytics.buildtools.ndk.internal.breakpad.BreakpadSymbolFileService;
import com.google.firebase.crashlytics.buildtools.ndk.internal.breakpad.BreakpadSymbolGenerator;
import com.google.firebase.crashlytics.buildtools.ndk.internal.csym.CsymSymbolFileService;
import com.google.firebase.crashlytics.buildtools.ndk.internal.csym.NdkCSymGenerator;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Preconditions;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.CommandLine;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.DefaultParser;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.Options;
import com.google.firebase.crashlytics.buildtools.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class CommandLineHelper {
    private final CommandLine cmd;
    private static final String[] VALID_COMMANDS = {CrashlyticsOptions.OPT_HELP, CrashlyticsOptions.OPT_INJECT_MAPPING_FILE_ID, CrashlyticsOptions.OPT_UPLOAD_MAPPING_FILE, CrashlyticsOptions.OPT_GENERATE_NATIVE_SYMBOLS, CrashlyticsOptions.OPT_UPLOAD_NATIVE_SYMBOLS};
    private static final Pattern GOOGLE_APP_ID_PATTERN = Pattern.compile("(\\d+):(\\d+):(\\w+):(\\p{XDigit}+)");

    public static void main(String[] strArr) {
        boolean z = false;
        try {
            Buildtools.getLogger();
            int length = strArr.length;
            int i = 0;
            boolean z2 = false;
            while (true) {
                if (i >= length) {
                    break;
                }
                String str = strArr[i];
                if (str.equals("-verbose")) {
                    z = true;
                    break;
                } else {
                    if (str.equals("-quiet")) {
                        z2 = true;
                    }
                    i++;
                }
            }
            if (z) {
                Buildtools.getLogger().setLevel(CrashlyticsLogger.Level.VERBOSE);
            } else if (z2) {
                Buildtools.getLogger().setLevel(CrashlyticsLogger.Level.ERROR);
            }
            Options optionsCreateOptions = CrashlyticsOptions.createOptions();
            CommandLine commandLine = new DefaultParser().parse(optionsCreateOptions, strArr);
            if (commandLine.hasOption(CrashlyticsOptions.OPT_HELP)) {
                new HelpFormatter().printHelp(Buildtools.class.getName(), optionsCreateOptions);
            } else {
                new CommandLineHelper(commandLine).executeCommand();
            }
        } catch (Exception e) {
            Buildtools.logE("Crashlytics execution failed.".concat(0 == 0 ? " Run with -verbose for additional output." : ""), e);
            System.exit(-1);
        }
    }

    private void configureWebApi() {
        Buildtools.setWebApi(Buildtools.createWebApi());
        Package r0 = CommandLineHelper.class.getPackage();
        String implementationTitle = r0.getImplementationTitle();
        String implementationVersion = r0.getImplementationVersion();
        if (this.cmd.hasOption(CrashlyticsOptions.OPT_CLIENT_NAME)) {
            implementationTitle = this.cmd.getOptionValue(CrashlyticsOptions.OPT_CLIENT_NAME);
        }
        if (this.cmd.hasOption(CrashlyticsOptions.OPT_CLIENT_VERSION)) {
            implementationVersion = this.cmd.getOptionValue(CrashlyticsOptions.OPT_CLIENT_VERSION);
        }
        Buildtools.getInstance().setBuildtoolsClientInfo(implementationTitle, implementationVersion);
    }

    public CommandLineHelper(CommandLine commandLine) {
        this.cmd = commandLine;
    }

    public void executeCommand() throws Throwable {
        configureWebApi();
        int i = 0;
        for (String str : VALID_COMMANDS) {
            if (this.cmd.hasOption(str)) {
                i++;
            }
        }
        if (i != 1) {
            throw new IllegalArgumentException("Exactly ONE valid command required. Use '-help' valid arguments.");
        }
        if (this.cmd.hasOption(CrashlyticsOptions.OPT_INJECT_MAPPING_FILE_ID)) {
            executeInjectMappingFileId();
            return;
        }
        if (this.cmd.hasOption(CrashlyticsOptions.OPT_UPLOAD_MAPPING_FILE)) {
            executeUploadMappingFile();
        } else if (this.cmd.hasOption(CrashlyticsOptions.OPT_GENERATE_NATIVE_SYMBOLS)) {
            executeGenerateSymbols();
        } else if (this.cmd.hasOption(CrashlyticsOptions.OPT_UPLOAD_NATIVE_SYMBOLS)) {
            executeUploadSymbols();
        }
    }

    private void executeInjectMappingFileId() throws Throwable {
        String optionValueOrThrow = getOptionValueOrThrow(this.cmd, CrashlyticsOptions.OPT_INJECT_MAPPING_FILE_ID);
        if (this.cmd.hasOption(CrashlyticsOptions.OPT_MAPPING_FILE_ID)) {
            Buildtools.getInstance().injectMappingFileIdIntoResource(new File(optionValueOrThrow), getOptionValueOrThrow(this.cmd, CrashlyticsOptions.OPT_MAPPING_FILE_ID));
        } else {
            Buildtools.getInstance().injectMappingFileIdIntoResource(new File(optionValueOrThrow));
        }
    }

    private void executeUploadMappingFile() throws IOException, IllegalArgumentException {
        File file = new File(getOptionValueOrThrow(this.cmd, CrashlyticsOptions.OPT_UPLOAD_MAPPING_FILE));
        String optionValueOrThrow = getOptionValueOrThrow(this.cmd, CrashlyticsOptions.OPT_GOOGLE_APP_ID);
        validateGoogleAppId(optionValueOrThrow);
        AppBuildInfo appBuildInfo = new AppBuildInfo(this.cmd.getOptionValue(CrashlyticsOptions.OPT_ANDROID_APPLICATION_ID, (String) null), optionValueOrThrow, null);
        Obfuscator obfuscator = new Obfuscator(Obfuscator.Vendor.PROGUARD, "0.0.0");
        if (this.cmd.hasOption(CrashlyticsOptions.OPT_MAPPING_FILE_ID) && !this.cmd.hasOption(CrashlyticsOptions.OPT_RESOURCE_FILE)) {
            Buildtools.getInstance().uploadMappingFile(file, getOptionValueOrThrow(this.cmd, CrashlyticsOptions.OPT_MAPPING_FILE_ID), appBuildInfo, obfuscator);
        } else {
            if (this.cmd.hasOption(CrashlyticsOptions.OPT_RESOURCE_FILE) && !this.cmd.hasOption(CrashlyticsOptions.OPT_MAPPING_FILE_ID)) {
                Buildtools.getInstance().uploadMappingFile(file, new File(getOptionValueOrThrow(this.cmd, CrashlyticsOptions.OPT_RESOURCE_FILE)), appBuildInfo, obfuscator);
                return;
            }
            throw new IllegalArgumentException("When executing uploadMappingFile, use either mappingFileId or resourceFile (but not both).");
        }
    }

    private void executeGenerateSymbols() throws IOException {
        String optionValue = this.cmd.getOptionValue(CrashlyticsOptions.OPT_NATIVE_UNSTRIPPED_LIB);
        String optionValue2 = this.cmd.getOptionValue(CrashlyticsOptions.OPT_NATIVE_UNSTRIPPED_LIBS_DIR);
        boolean z = optionValue != null;
        if (!Boolean.logicalXor(z, optionValue2 != null)) {
            throw new IllegalArgumentException("generateNativeSymbols requires either 1) unstrippedLibrary or 2) unstrippedLibrariesDir");
        }
        File file = new File(getOptionValueOrThrow(this.cmd, CrashlyticsOptions.OPT_CSYM_CACHE_DIR));
        FileUtils.verifyDirectory(file);
        Buildtools.getInstance().generateNativeSymbolFiles(z ? new File(optionValue) : new File(optionValue2), file, createSymbolGenerator(this.cmd));
    }

    private void executeUploadSymbols() throws IOException, IllegalArgumentException {
        File file = new File(getOptionValueOrThrow(this.cmd, CrashlyticsOptions.OPT_CSYM_CACHE_DIR));
        FileUtils.verifyDirectory(file);
        String optionValueOrThrow = getOptionValueOrThrow(this.cmd, CrashlyticsOptions.OPT_GOOGLE_APP_ID);
        validateGoogleAppId(optionValueOrThrow);
        Buildtools.getInstance().uploadNativeSymbolFiles(file, optionValueOrThrow, createSymbolFileService(this.cmd));
    }

    private static String getOptionValueOrThrow(CommandLine commandLine, String str) throws IllegalArgumentException {
        String optionValue = commandLine.getOptionValue(str);
        if (optionValue != null) {
            return optionValue;
        }
        throw new IllegalArgumentException("Required argument missing: " + str);
    }

    private static NativeSymbolGenerator createSymbolGenerator(CommandLine commandLine) throws IOException, IllegalArgumentException {
        String optionValue = commandLine.getOptionValue(CrashlyticsOptions.OPT_SYMBOL_GENERATOR_TYPE, "breakpad");
        if ("breakpad".equals(optionValue)) {
            return new BreakpadSymbolGenerator(resolveDumpSymsBinary(commandLine));
        }
        if (CrashlyticsOptions.SYMBOL_GENERATOR_CSYM.equals(optionValue)) {
            return new NdkCSymGenerator();
        }
        throwInvalidSymbolGeneratorMode(optionValue);
        return null;
    }

    private static SymbolFileService createSymbolFileService(CommandLine commandLine) throws IllegalArgumentException {
        String optionValue = commandLine.getOptionValue(CrashlyticsOptions.OPT_SYMBOL_GENERATOR_TYPE, "breakpad");
        if ("breakpad".equals(optionValue)) {
            return new BreakpadSymbolFileService();
        }
        if (CrashlyticsOptions.SYMBOL_GENERATOR_CSYM.equals(optionValue)) {
            return new CsymSymbolFileService();
        }
        throwInvalidSymbolGeneratorMode(optionValue);
        return null;
    }

    private static File resolveDumpSymsBinary(CommandLine commandLine) throws IOException, IllegalArgumentException {
        if (commandLine.hasOption(CrashlyticsOptions.OPT_DUMP_SYMS_BINARY)) {
            return new File(commandLine.getOptionValue(CrashlyticsOptions.OPT_DUMP_SYMS_BINARY));
        }
        File file = new File(".crashlytics");
        if (!file.isDirectory()) {
            if (file.isFile()) {
                throw new IOException("Could not create Crashlytics directory, a file already exists at that location: " + file.getAbsolutePath());
            }
            file.mkdir();
        }
        return BreakpadSymbolGenerator.extractDefaultDumpSymsBinary(file);
    }

    private static void throwInvalidSymbolGeneratorMode(String str) throws IllegalArgumentException {
        throw new IllegalArgumentException("Invalid argument for symbolGenerator (" + str + "), must be one of [breakpad, csym]");
    }

    static void validateGoogleAppId(String str) {
        Preconditions.checkArgument(GOOGLE_APP_ID_PATTERN.matcher(str).matches(), "Google App ID parameter doesn't match the expected format. Check that the parameter has been passed in correctly.");
    }
}
