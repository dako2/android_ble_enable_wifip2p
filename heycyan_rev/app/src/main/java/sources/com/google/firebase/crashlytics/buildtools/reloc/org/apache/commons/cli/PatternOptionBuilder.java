package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Date;

/* loaded from: classes2.dex */
public class PatternOptionBuilder {
    public static final Class<String> STRING_VALUE = String.class;
    public static final Class<Object> OBJECT_VALUE = Object.class;
    public static final Class<Number> NUMBER_VALUE = Number.class;
    public static final Class<Date> DATE_VALUE = Date.class;
    public static final Class<?> CLASS_VALUE = Class.class;
    public static final Class<FileInputStream> EXISTING_FILE_VALUE = FileInputStream.class;
    public static final Class<File> FILE_VALUE = File.class;
    public static final Class<File[]> FILES_VALUE = File[].class;
    public static final Class<URL> URL_VALUE = URL.class;

    public static boolean isValueCode(char c) {
        return c == '@' || c == ':' || c == '%' || c == '+' || c == '#' || c == '<' || c == '>' || c == '*' || c == '/' || c == '!';
    }

    public static Object getValueClass(char c) {
        if (c == '#') {
            return DATE_VALUE;
        }
        if (c == '%') {
            return NUMBER_VALUE;
        }
        if (c == '/') {
            return URL_VALUE;
        }
        if (c == ':') {
            return STRING_VALUE;
        }
        if (c == '<') {
            return EXISTING_FILE_VALUE;
        }
        if (c == '>') {
            return FILE_VALUE;
        }
        if (c == '@') {
            return OBJECT_VALUE;
        }
        if (c == '*') {
            return FILES_VALUE;
        }
        if (c != '+') {
            return null;
        }
        return CLASS_VALUE;
    }

    public static Options parsePattern(String str) {
        Options options = new Options();
        char c = ' ';
        int i = 0;
        boolean z = false;
        Class<?> cls = null;
        while (true) {
            if (i >= str.length()) {
                break;
            }
            char cCharAt = str.charAt(i);
            if (!isValueCode(cCharAt)) {
                if (c != ' ') {
                    options.addOption(Option.builder(String.valueOf(c)).hasArg(cls != null).required(z).type(cls).build());
                    z = false;
                    cls = null;
                }
                c = cCharAt;
            } else if (cCharAt == '!') {
                z = true;
            } else {
                cls = (Class) getValueClass(cCharAt);
            }
            i++;
        }
        if (c != ' ') {
            options.addOption(Option.builder(String.valueOf(c)).hasArg(cls != null).required(z).type(cls).build());
        }
        return options;
    }
}
