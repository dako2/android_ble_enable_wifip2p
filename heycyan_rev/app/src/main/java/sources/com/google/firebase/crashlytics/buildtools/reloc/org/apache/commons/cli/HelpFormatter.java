package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.TokenParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class HelpFormatter {
    public static final String DEFAULT_ARG_NAME = "arg";
    public static final int DEFAULT_DESC_PAD = 3;
    public static final int DEFAULT_LEFT_PAD = 1;
    public static final String DEFAULT_LONG_OPT_PREFIX = "--";
    public static final String DEFAULT_LONG_OPT_SEPARATOR = " ";
    public static final String DEFAULT_OPT_PREFIX = "-";
    public static final String DEFAULT_SYNTAX_PREFIX = "usage: ";
    public static final int DEFAULT_WIDTH = 74;

    @Deprecated
    public int defaultWidth = 74;

    @Deprecated
    public int defaultLeftPad = 1;

    @Deprecated
    public int defaultDescPad = 3;

    @Deprecated
    public String defaultSyntaxPrefix = DEFAULT_SYNTAX_PREFIX;

    @Deprecated
    public String defaultNewLine = System.getProperty("line.separator");

    @Deprecated
    public String defaultOptPrefix = DEFAULT_OPT_PREFIX;

    @Deprecated
    public String defaultLongOptPrefix = DEFAULT_LONG_OPT_PREFIX;

    @Deprecated
    public String defaultArgName = DEFAULT_ARG_NAME;
    protected Comparator<Option> optionComparator = new OptionComparator();
    private String longOptSeparator = DEFAULT_LONG_OPT_SEPARATOR;

    public void setWidth(int i) {
        this.defaultWidth = i;
    }

    public int getWidth() {
        return this.defaultWidth;
    }

    public void setLeftPadding(int i) {
        this.defaultLeftPad = i;
    }

    public int getLeftPadding() {
        return this.defaultLeftPad;
    }

    public void setDescPadding(int i) {
        this.defaultDescPad = i;
    }

    public int getDescPadding() {
        return this.defaultDescPad;
    }

    public void setSyntaxPrefix(String str) {
        this.defaultSyntaxPrefix = str;
    }

    public String getSyntaxPrefix() {
        return this.defaultSyntaxPrefix;
    }

    public void setNewLine(String str) {
        this.defaultNewLine = str;
    }

    public String getNewLine() {
        return this.defaultNewLine;
    }

    public void setOptPrefix(String str) {
        this.defaultOptPrefix = str;
    }

    public String getOptPrefix() {
        return this.defaultOptPrefix;
    }

    public void setLongOptPrefix(String str) {
        this.defaultLongOptPrefix = str;
    }

    public String getLongOptPrefix() {
        return this.defaultLongOptPrefix;
    }

    public void setLongOptSeparator(String str) {
        this.longOptSeparator = str;
    }

    public String getLongOptSeparator() {
        return this.longOptSeparator;
    }

    public void setArgName(String str) {
        this.defaultArgName = str;
    }

    public String getArgName() {
        return this.defaultArgName;
    }

    public Comparator<Option> getOptionComparator() {
        return this.optionComparator;
    }

    public void setOptionComparator(Comparator<Option> comparator) {
        this.optionComparator = comparator;
    }

    public void printHelp(String str, Options options) throws IOException {
        printHelp(getWidth(), str, null, options, null, false);
    }

    public void printHelp(String str, Options options, boolean z) throws IOException {
        printHelp(getWidth(), str, null, options, null, z);
    }

    public void printHelp(String str, String str2, Options options, String str3) throws IOException {
        printHelp(str, str2, options, str3, false);
    }

    public void printHelp(String str, String str2, Options options, String str3, boolean z) throws IOException {
        printHelp(getWidth(), str, str2, options, str3, z);
    }

    public void printHelp(int i, String str, String str2, Options options, String str3) throws IOException {
        printHelp(i, str, str2, options, str3, false);
    }

    public void printHelp(int i, String str, String str2, Options options, String str3, boolean z) throws IOException {
        PrintWriter printWriter = new PrintWriter(System.out);
        printHelp(printWriter, i, str, str2, options, getLeftPadding(), getDescPadding(), str3, z);
        printWriter.flush();
    }

    public void printHelp(PrintWriter printWriter, int i, String str, String str2, Options options, int i2, int i3, String str3) throws IOException {
        printHelp(printWriter, i, str, str2, options, i2, i3, str3, false);
    }

    public void printHelp(PrintWriter printWriter, int i, String str, String str2, Options options, int i2, int i3, String str3, boolean z) throws IOException {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("cmdLineSyntax not provided");
        }
        if (z) {
            printUsage(printWriter, i, str, options);
        } else {
            printUsage(printWriter, i, str);
        }
        if (str2 != null && str2.trim().length() > 0) {
            printWrapped(printWriter, i, str2);
        }
        printOptions(printWriter, i, options, i2, i3);
        if (str3 == null || str3.trim().length() <= 0) {
            return;
        }
        printWrapped(printWriter, i, str3);
    }

    public void printUsage(PrintWriter printWriter, int i, String str, Options options) throws IOException {
        StringBuffer stringBufferAppend = new StringBuffer(getSyntaxPrefix()).append(str).append(DEFAULT_LONG_OPT_SEPARATOR);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(options.getOptions());
        if (getOptionComparator() != null) {
            Collections.sort(arrayList2, getOptionComparator());
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            Option option = (Option) it.next();
            OptionGroup optionGroup = options.getOptionGroup(option);
            if (optionGroup != null) {
                if (!arrayList.contains(optionGroup)) {
                    arrayList.add(optionGroup);
                    appendOptionGroup(stringBufferAppend, optionGroup);
                }
            } else {
                appendOption(stringBufferAppend, option, option.isRequired());
            }
            if (it.hasNext()) {
                stringBufferAppend.append(DEFAULT_LONG_OPT_SEPARATOR);
            }
        }
        printWrapped(printWriter, i, stringBufferAppend.toString().indexOf(32) + 1, stringBufferAppend.toString());
    }

    private void appendOptionGroup(StringBuffer stringBuffer, OptionGroup optionGroup) {
        if (!optionGroup.isRequired()) {
            stringBuffer.append("[");
        }
        ArrayList arrayList = new ArrayList(optionGroup.getOptions());
        if (getOptionComparator() != null) {
            Collections.sort(arrayList, getOptionComparator());
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            appendOption(stringBuffer, (Option) it.next(), true);
            if (it.hasNext()) {
                stringBuffer.append(" | ");
            }
        }
        if (optionGroup.isRequired()) {
            return;
        }
        stringBuffer.append("]");
    }

    private void appendOption(StringBuffer stringBuffer, Option option, boolean z) {
        if (!z) {
            stringBuffer.append("[");
        }
        if (option.getOpt() != null) {
            stringBuffer.append(DEFAULT_OPT_PREFIX).append(option.getOpt());
        } else {
            stringBuffer.append(DEFAULT_LONG_OPT_PREFIX).append(option.getLongOpt());
        }
        if (option.hasArg() && (option.getArgName() == null || option.getArgName().length() != 0)) {
            stringBuffer.append(option.getOpt() == null ? this.longOptSeparator : DEFAULT_LONG_OPT_SEPARATOR);
            stringBuffer.append("<").append(option.getArgName() != null ? option.getArgName() : getArgName()).append(">");
        }
        if (z) {
            return;
        }
        stringBuffer.append("]");
    }

    public void printUsage(PrintWriter printWriter, int i, String str) throws IOException {
        printWrapped(printWriter, i, getSyntaxPrefix().length() + str.indexOf(32) + 1, getSyntaxPrefix() + str);
    }

    public void printOptions(PrintWriter printWriter, int i, Options options, int i2, int i3) {
        StringBuffer stringBuffer = new StringBuffer();
        renderOptions(stringBuffer, i, options, i2, i3);
        printWriter.println(stringBuffer.toString());
    }

    public void printWrapped(PrintWriter printWriter, int i, String str) throws IOException {
        printWrapped(printWriter, i, 0, str);
    }

    public void printWrapped(PrintWriter printWriter, int i, int i2, String str) throws IOException {
        StringBuffer stringBuffer = new StringBuffer(str.length());
        renderWrappedTextBlock(stringBuffer, i, i2, str);
        printWriter.println(stringBuffer.toString());
    }

    protected StringBuffer renderOptions(StringBuffer stringBuffer, int i, Options options, int i2, int i3) {
        String strCreatePadding = createPadding(i2);
        String strCreatePadding2 = createPadding(i3);
        ArrayList arrayList = new ArrayList();
        List<Option> listHelpOptions = options.helpOptions();
        if (getOptionComparator() != null) {
            Collections.sort(listHelpOptions, getOptionComparator());
        }
        int i4 = 0;
        int length = 0;
        for (Option option : listHelpOptions) {
            StringBuffer stringBuffer2 = new StringBuffer();
            if (option.getOpt() == null) {
                stringBuffer2.append(strCreatePadding).append("   ").append(getLongOptPrefix()).append(option.getLongOpt());
            } else {
                stringBuffer2.append(strCreatePadding).append(getOptPrefix()).append(option.getOpt());
                if (option.hasLongOpt()) {
                    stringBuffer2.append(',').append(getLongOptPrefix()).append(option.getLongOpt());
                }
            }
            if (option.hasArg()) {
                String argName = option.getArgName();
                if (argName != null && argName.length() == 0) {
                    stringBuffer2.append(TokenParser.f390SP);
                } else {
                    stringBuffer2.append(option.hasLongOpt() ? this.longOptSeparator : DEFAULT_LONG_OPT_SEPARATOR);
                    stringBuffer2.append("<").append(argName != null ? option.getArgName() : getArgName()).append(">");
                }
            }
            arrayList.add(stringBuffer2);
            if (stringBuffer2.length() > length) {
                length = stringBuffer2.length();
            }
        }
        Iterator<Option> it = listHelpOptions.iterator();
        while (it.hasNext()) {
            Option next = it.next();
            int i5 = i4 + 1;
            StringBuilder sb = new StringBuilder(((StringBuffer) arrayList.get(i4)).toString());
            if (sb.length() < length) {
                sb.append(createPadding(length - sb.length()));
            }
            sb.append(strCreatePadding2);
            int i6 = length + i3;
            if (next.getDescription() != null) {
                sb.append(next.getDescription());
            }
            renderWrappedText(stringBuffer, i, i6, sb.toString());
            if (it.hasNext()) {
                stringBuffer.append(getNewLine());
            }
            i4 = i5;
        }
        return stringBuffer;
    }

    protected StringBuffer renderWrappedText(StringBuffer stringBuffer, int i, int i2, String str) {
        int iFindWrapPos = findWrapPos(str, i, 0);
        if (iFindWrapPos == -1) {
            stringBuffer.append(rtrim(str));
            return stringBuffer;
        }
        stringBuffer.append(rtrim(str.substring(0, iFindWrapPos))).append(getNewLine());
        if (i2 >= i) {
            i2 = 1;
        }
        String strCreatePadding = createPadding(i2);
        while (true) {
            str = strCreatePadding + str.substring(iFindWrapPos).trim();
            iFindWrapPos = findWrapPos(str, i, 0);
            if (iFindWrapPos == -1) {
                stringBuffer.append(str);
                return stringBuffer;
            }
            if (str.length() > i && iFindWrapPos == i2 - 1) {
                iFindWrapPos = i;
            }
            stringBuffer.append(rtrim(str.substring(0, iFindWrapPos))).append(getNewLine());
        }
    }

    private Appendable renderWrappedTextBlock(StringBuffer stringBuffer, int i, int i2, String str) throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new StringReader(str));
            boolean z = true;
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (z) {
                    z = false;
                } else {
                    stringBuffer.append(getNewLine());
                }
                renderWrappedText(stringBuffer, i, i2, line);
            }
        } catch (IOException unused) {
        }
        return stringBuffer;
    }

    protected int findWrapPos(String str, int i, int i2) {
        int iIndexOf = str.indexOf(10, i2);
        if (iIndexOf != -1 && iIndexOf <= i) {
            return iIndexOf + 1;
        }
        int iIndexOf2 = str.indexOf(9, i2);
        if (iIndexOf2 != -1 && iIndexOf2 <= i) {
            return iIndexOf2 + 1;
        }
        int i3 = i + i2;
        if (i3 >= str.length()) {
            return -1;
        }
        int i4 = i3;
        while (i4 >= i2) {
            char cCharAt = str.charAt(i4);
            if (cCharAt == ' ' || cCharAt == '\n' || cCharAt == '\r') {
                break;
            }
            i4--;
        }
        if (i4 > i2) {
            return i4;
        }
        if (i3 == str.length()) {
            return -1;
        }
        return i3;
    }

    protected String createPadding(int i) {
        char[] cArr = new char[i];
        Arrays.fill(cArr, TokenParser.f390SP);
        return new String(cArr);
    }

    protected String rtrim(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        int length = str.length();
        while (length > 0 && Character.isWhitespace(str.charAt(length - 1))) {
            length--;
        }
        return str.substring(0, length);
    }

    private static class OptionComparator implements Comparator<Option>, Serializable {
        private static final long serialVersionUID = 5305467873966684014L;

        private OptionComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Option option, Option option2) {
            return option.getKey().compareToIgnoreCase(option2.getKey());
        }
    }
}
