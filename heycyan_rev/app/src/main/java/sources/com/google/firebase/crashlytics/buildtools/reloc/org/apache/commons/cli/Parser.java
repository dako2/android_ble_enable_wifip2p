package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

@Deprecated
/* loaded from: classes2.dex */
public abstract class Parser implements CommandLineParser {
    protected CommandLine cmd;
    private Options options;
    private List requiredOptions;

    protected abstract String[] flatten(Options options, String[] strArr, boolean z) throws ParseException;

    protected void setOptions(Options options) {
        this.options = options;
        this.requiredOptions = new ArrayList(options.getRequiredOptions());
    }

    protected Options getOptions() {
        return this.options;
    }

    protected List getRequiredOptions() {
        return this.requiredOptions;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.CommandLineParser
    public CommandLine parse(Options options, String[] strArr) throws ParseException {
        return parse(options, strArr, null, false);
    }

    public CommandLine parse(Options options, String[] strArr, Properties properties) throws ParseException {
        return parse(options, strArr, properties, false);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.CommandLineParser
    public CommandLine parse(Options options, String[] strArr, boolean z) throws ParseException {
        return parse(options, strArr, null, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public CommandLine parse(Options options, String[] strArr, Properties properties, boolean z) throws ParseException {
        Iterator<Option> it = options.helpOptions().iterator();
        while (it.hasNext()) {
            it.next().clearValues();
        }
        Iterator<OptionGroup> it2 = options.getOptionGroups().iterator();
        while (it2.hasNext()) {
            it2.next().setSelected(null);
        }
        setOptions(options);
        this.cmd = new CommandLine();
        boolean z2 = false;
        if (strArr == null) {
            strArr = new String[0];
        }
        ListIterator<String> listIterator = Arrays.asList(flatten(getOptions(), strArr, z)).listIterator();
        while (listIterator.hasNext()) {
            String next = listIterator.next();
            if (!HelpFormatter.DEFAULT_LONG_OPT_PREFIX.equals(next)) {
                if (HelpFormatter.DEFAULT_OPT_PREFIX.equals(next)) {
                    if (z) {
                        z2 = true;
                    } else {
                        this.cmd.addArg(next);
                    }
                } else if (next.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
                    if (z && !getOptions().hasOption(next)) {
                        this.cmd.addArg(next);
                        z2 = true;
                    } else {
                        processOption(next, listIterator);
                    }
                } else {
                    this.cmd.addArg(next);
                    if (z) {
                    }
                }
            }
            if (z2) {
                while (listIterator.hasNext()) {
                    String next2 = listIterator.next();
                    if (!HelpFormatter.DEFAULT_LONG_OPT_PREFIX.equals(next2)) {
                        this.cmd.addArg(next2);
                    }
                }
            }
        }
        processProperties(properties);
        checkRequiredOptions();
        return this.cmd;
    }

    protected void processProperties(Properties properties) throws ParseException {
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
                        try {
                            option.addValueForProcessing(property);
                        } catch (RuntimeException unused) {
                        }
                    }
                } else if ("yes".equalsIgnoreCase(property) || "true".equalsIgnoreCase(property) || "1".equalsIgnoreCase(property)) {
                }
                this.cmd.addOption(option);
                updateRequiredOptions(option);
            }
        }
    }

    protected void checkRequiredOptions() throws MissingOptionException {
        if (!getRequiredOptions().isEmpty()) {
            throw new MissingOptionException(getRequiredOptions());
        }
    }

    public void processArgs(Option option, ListIterator<String> listIterator) throws ParseException {
        while (true) {
            if (!listIterator.hasNext()) {
                break;
            }
            String next = listIterator.next();
            if (getOptions().hasOption(next) && next.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
                listIterator.previous();
                break;
            } else {
                try {
                    option.addValueForProcessing(Util.stripLeadingAndTrailingQuotes(next));
                } catch (RuntimeException unused) {
                    listIterator.previous();
                }
            }
        }
        if (option.getValues() == null && !option.hasOptionalArg()) {
            throw new MissingArgumentException(option);
        }
    }

    protected void processOption(String str, ListIterator<String> listIterator) throws ParseException {
        if (!getOptions().hasOption(str)) {
            throw new UnrecognizedOptionException("Unrecognized option: " + str, str);
        }
        Option option = (Option) getOptions().getOption(str).clone();
        updateRequiredOptions(option);
        if (option.hasArg()) {
            processArgs(option, listIterator);
        }
        this.cmd.addOption(option);
    }

    private void updateRequiredOptions(Option option) throws ParseException {
        if (option.isRequired()) {
            getRequiredOptions().remove(option.getKey());
        }
        if (getOptions().getOptionGroup(option) != null) {
            OptionGroup optionGroup = getOptions().getOptionGroup(option);
            if (optionGroup.isRequired()) {
                getRequiredOptions().remove(optionGroup);
            }
            optionGroup.setSelected(option);
        }
    }
}
