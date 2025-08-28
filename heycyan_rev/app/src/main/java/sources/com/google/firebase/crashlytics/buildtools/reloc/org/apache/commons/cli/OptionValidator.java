package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli;

/* loaded from: classes2.dex */
final class OptionValidator {
    OptionValidator() {
    }

    static void validateOption(String str) throws IllegalArgumentException {
        if (str == null) {
            return;
        }
        if (str.length() == 1) {
            char cCharAt = str.charAt(0);
            if (!isValidOpt(cCharAt)) {
                throw new IllegalArgumentException("Illegal option name '" + cCharAt + "'");
            }
            return;
        }
        for (char c : str.toCharArray()) {
            if (!isValidChar(c)) {
                throw new IllegalArgumentException("The option '" + str + "' contains an illegal character : '" + c + "'");
            }
        }
    }

    private static boolean isValidOpt(char c) {
        return isValidChar(c) || c == '?' || c == '@';
    }

    private static boolean isValidChar(char c) {
        return Character.isJavaIdentifierPart(c);
    }
}
