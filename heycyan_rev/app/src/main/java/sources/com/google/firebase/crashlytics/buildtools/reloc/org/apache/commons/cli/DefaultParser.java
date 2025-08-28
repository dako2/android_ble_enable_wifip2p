package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/* loaded from: classes2.dex */
public class DefaultParser implements CommandLineParser {
    protected CommandLine cmd;
    protected Option currentOption;
    protected String currentToken;
    protected List expectedOpts;
    protected Options options;
    protected boolean skipParsing;
    protected boolean stopAtNonOption;

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.CommandLineParser
    public CommandLine parse(Options options, String[] strArr) throws ParseException {
        return parse(options, strArr, (Properties) null);
    }

    public CommandLine parse(Options options, String[] strArr, Properties properties) throws ParseException {
        return parse(options, strArr, properties, false);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.CommandLineParser
    public CommandLine parse(Options options, String[] strArr, boolean z) throws ParseException {
        return parse(options, strArr, null, z);
    }

    public CommandLine parse(Options options, String[] strArr, Properties properties, boolean z) throws ParseException {
        this.options = options;
        this.stopAtNonOption = z;
        this.skipParsing = false;
        this.currentOption = null;
        this.expectedOpts = new ArrayList(options.getRequiredOptions());
        Iterator<OptionGroup> it = options.getOptionGroups().iterator();
        while (it.hasNext()) {
            it.next().setSelected(null);
        }
        this.cmd = new CommandLine();
        if (strArr != null) {
            for (String str : strArr) {
                handleToken(str);
            }
        }
        checkRequiredArgs();
        handleProperties(properties);
        checkRequiredOptions();
        return this.cmd;
    }

    private void handleProperties(Properties properties) throws ParseException {
        if (properties == null) {
            return;
        }
        Enumeration<?> enumerationPropertyNames = properties.propertyNames();
        while (enumerationPropertyNames.hasMoreElements()) {
            String string = enumerationPropertyNames.nextElement().toString();
            Option option = this.options.getOption(string);
            if (option == null) {
                throw new UnrecognizedOptionException("Default option wasn't defined", string);
            }
            OptionGroup optionGroup = this.options.getOptionGroup(option);
            boolean z = (optionGroup == null || optionGroup.getSelected() == null) ? false : true;
            if (!this.cmd.hasOption(string) && !z) {
                String property = properties.getProperty(string);
                if (option.hasArg()) {
                    if (option.getValues() == null || option.getValues().length == 0) {
                        option.addValueForProcessing(property);
                    }
                } else if ("yes".equalsIgnoreCase(property) || "true".equalsIgnoreCase(property) || "1".equalsIgnoreCase(property)) {
                }
                handleOption(option);
                this.currentOption = null;
            }
        }
    }

    private void checkRequiredOptions() throws MissingOptionException {
        if (!this.expectedOpts.isEmpty()) {
            throw new MissingOptionException(this.expectedOpts);
        }
    }

    private void checkRequiredArgs() throws ParseException {
        Option option = this.currentOption;
        if (option != null && option.requiresArg()) {
            throw new MissingArgumentException(this.currentOption);
        }
    }

    private void handleToken(String str) throws ParseException {
        this.currentToken = str;
        if (this.skipParsing) {
            this.cmd.addArg(str);
        } else if (HelpFormatter.DEFAULT_LONG_OPT_PREFIX.equals(str)) {
            this.skipParsing = true;
        } else {
            Option option = this.currentOption;
            if (option != null && option.acceptsArg() && isArgument(str)) {
                this.currentOption.addValueForProcessing(Util.stripLeadingAndTrailingQuotes(str));
            } else if (str.startsWith(HelpFormatter.DEFAULT_LONG_OPT_PREFIX)) {
                handleLongOption(str);
            } else if (str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX) && !HelpFormatter.DEFAULT_OPT_PREFIX.equals(str)) {
                handleShortAndLongOption(str);
            } else {
                handleUnknownToken(str);
            }
        }
        Option option2 = this.currentOption;
        if (option2 == null || option2.acceptsArg()) {
            return;
        }
        this.currentOption = null;
    }

    private boolean isArgument(String str) {
        return !isOption(str) || isNegativeNumber(str);
    }

    private boolean isNegativeNumber(String str) throws NumberFormatException {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private boolean isOption(String str) {
        return isLongOption(str) || isShortOption(str);
    }

    private boolean isShortOption(String str) {
        if (!str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX) || str.length() == 1) {
            return false;
        }
        int iIndexOf = str.indexOf("=");
        String strSubstring = iIndexOf == -1 ? str.substring(1) : str.substring(1, iIndexOf);
        if (this.options.hasShortOption(strSubstring)) {
            return true;
        }
        return strSubstring.length() > 0 && this.options.hasShortOption(String.valueOf(strSubstring.charAt(0)));
    }

    private boolean isLongOption(String str) {
        if (str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX) && str.length() != 1) {
            int iIndexOf = str.indexOf("=");
            if (!this.options.getMatchingOptions(iIndexOf == -1 ? str : str.substring(0, iIndexOf)).isEmpty()) {
                return true;
            }
            if (getLongPrefix(str) != null && !str.startsWith(HelpFormatter.DEFAULT_LONG_OPT_PREFIX)) {
                return true;
            }
        }
        return false;
    }

    private void handleUnknownToken(String str) throws ParseException {
        if (str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX) && str.length() > 1 && !this.stopAtNonOption) {
            throw new UnrecognizedOptionException("Unrecognized option: " + str, str);
        }
        this.cmd.addArg(str);
        if (this.stopAtNonOption) {
            this.skipParsing = true;
        }
    }

    private void handleLongOption(String str) throws ParseException {
        if (str.indexOf(61) == -1) {
            handleLongOptionWithoutEqual(str);
        } else {
            handleLongOptionWithEqual(str);
        }
    }

    private void handleLongOptionWithoutEqual(String str) throws ParseException {
        List<String> matchingOptions = this.options.getMatchingOptions(str);
        if (matchingOptions.isEmpty()) {
            handleUnknownToken(this.currentToken);
        } else {
            if (matchingOptions.size() > 1) {
                throw new AmbiguousOptionException(str, matchingOptions);
            }
            handleOption(this.options.getOption(matchingOptions.get(0)));
        }
    }

    private void handleLongOptionWithEqual(String str) throws ParseException {
        int iIndexOf = str.indexOf(61);
        String strSubstring = str.substring(iIndexOf + 1);
        String strSubstring2 = str.substring(0, iIndexOf);
        List<String> matchingOptions = this.options.getMatchingOptions(strSubstring2);
        if (matchingOptions.isEmpty()) {
            handleUnknownToken(this.currentToken);
            return;
        }
        if (matchingOptions.size() > 1) {
            throw new AmbiguousOptionException(strSubstring2, matchingOptions);
        }
        Option option = this.options.getOption(matchingOptions.get(0));
        if (option.acceptsArg()) {
            handleOption(option);
            this.currentOption.addValueForProcessing(strSubstring);
            this.currentOption = null;
            return;
        }
        handleUnknownToken(this.currentToken);
    }

    private void handleShortAndLongOption(String str) throws ParseException {
        String strStripLeadingHyphens = Util.stripLeadingHyphens(str);
        int iIndexOf = strStripLeadingHyphens.indexOf(61);
        if (strStripLeadingHyphens.length() == 1) {
            if (this.options.hasShortOption(strStripLeadingHyphens)) {
                handleOption(this.options.getOption(strStripLeadingHyphens));
                return;
            } else {
                handleUnknownToken(str);
                return;
            }
        }
        if (iIndexOf == -1) {
            if (this.options.hasShortOption(strStripLeadingHyphens)) {
                handleOption(this.options.getOption(strStripLeadingHyphens));
                return;
            }
            if (!this.options.getMatchingOptions(strStripLeadingHyphens).isEmpty()) {
                handleLongOptionWithoutEqual(str);
                return;
            }
            String longPrefix = getLongPrefix(strStripLeadingHyphens);
            if (longPrefix != null && this.options.getOption(longPrefix).acceptsArg()) {
                handleOption(this.options.getOption(longPrefix));
                this.currentOption.addValueForProcessing(strStripLeadingHyphens.substring(longPrefix.length()));
                this.currentOption = null;
                return;
            } else {
                if (isJavaProperty(strStripLeadingHyphens)) {
                    handleOption(this.options.getOption(strStripLeadingHyphens.substring(0, 1)));
                    this.currentOption.addValueForProcessing(strStripLeadingHyphens.substring(1));
                    this.currentOption = null;
                    return;
                }
                handleConcatenatedOptions(str);
                return;
            }
        }
        String strSubstring = strStripLeadingHyphens.substring(0, iIndexOf);
        String strSubstring2 = strStripLeadingHyphens.substring(iIndexOf + 1);
        if (strSubstring.length() == 1) {
            Option option = this.options.getOption(strSubstring);
            if (option != null && option.acceptsArg()) {
                handleOption(option);
                this.currentOption.addValueForProcessing(strSubstring2);
                this.currentOption = null;
                return;
            }
            handleUnknownToken(str);
            return;
        }
        if (isJavaProperty(strSubstring)) {
            handleOption(this.options.getOption(strSubstring.substring(0, 1)));
            this.currentOption.addValueForProcessing(strSubstring.substring(1));
            this.currentOption.addValueForProcessing(strSubstring2);
            this.currentOption = null;
            return;
        }
        handleLongOptionWithEqual(str);
    }

    private String getLongPrefix(String str) {
        String strStripLeadingHyphens = Util.stripLeadingHyphens(str);
        for (int length = strStripLeadingHyphens.length() - 2; length > 1; length--) {
            String strSubstring = strStripLeadingHyphens.substring(0, length);
            if (this.options.hasLongOption(strSubstring)) {
                return strSubstring;
            }
        }
        return null;
    }

    private boolean isJavaProperty(String str) {
        Option option = this.options.getOption(str.substring(0, 1));
        if (option != null) {
            return option.getArgs() >= 2 || option.getArgs() == -2;
        }
        return false;
    }

    private void handleOption(Option option) throws ParseException {
        checkRequiredArgs();
        Option option2 = (Option) option.clone();
        updateRequiredOptions(option2);
        this.cmd.addOption(option2);
        if (option2.hasArg()) {
            this.currentOption = option2;
        } else {
            this.currentOption = null;
        }
    }

    private void updateRequiredOptions(Option option) throws AlreadySelectedException {
        if (option.isRequired()) {
            this.expectedOpts.remove(option.getKey());
        }
        if (this.options.getOptionGroup(option) != null) {
            OptionGroup optionGroup = this.options.getOptionGroup(option);
            if (optionGroup.isRequired()) {
                this.expectedOpts.remove(optionGroup);
            }
            optionGroup.setSelected(option);
        }
    }

    protected void handleConcatenatedOptions(String str) throws ParseException {
        int i;
        for (int i2 = 1; i2 < str.length(); i2++) {
            String strValueOf = String.valueOf(str.charAt(i2));
            if (this.options.hasOption(strValueOf)) {
                handleOption(this.options.getOption(strValueOf));
                if (this.currentOption != null && str.length() != (i = i2 + 1)) {
                    this.currentOption.addValueForProcessing(str.substring(i));
                    return;
                }
            } else {
                if (this.stopAtNonOption && i2 > 1) {
                    str = str.substring(i2);
                }
                handleUnknownToken(str);
                return;
            }
        }
    }
}
