package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/* loaded from: classes2.dex */
public class CommandLine implements Serializable {
    private static final long serialVersionUID = 1;
    private final List<String> args = new LinkedList();
    private final List<Option> options = new ArrayList();

    protected CommandLine() {
    }

    public boolean hasOption(String str) {
        return this.options.contains(resolveOption(str));
    }

    public boolean hasOption(char c) {
        return hasOption(String.valueOf(c));
    }

    @Deprecated
    public Object getOptionObject(String str) {
        try {
            return getParsedOptionValue(str);
        } catch (ParseException e) {
            System.err.println("Exception found converting " + str + " to desired type: " + e.getMessage());
            return null;
        }
    }

    public Object getParsedOptionValue(String str) throws ParseException {
        String optionValue = getOptionValue(str);
        Option optionResolveOption = resolveOption(str);
        if (optionResolveOption == null || optionValue == null) {
            return null;
        }
        return TypeHandler.createValue(optionValue, optionResolveOption.getType());
    }

    public Object getOptionObject(char c) {
        return getOptionObject(String.valueOf(c));
    }

    public String getOptionValue(String str) {
        String[] optionValues = getOptionValues(str);
        if (optionValues == null) {
            return null;
        }
        return optionValues[0];
    }

    public String getOptionValue(char c) {
        return getOptionValue(String.valueOf(c));
    }

    public String[] getOptionValues(String str) {
        ArrayList arrayList = new ArrayList();
        for (Option option : this.options) {
            if (str.equals(option.getOpt()) || str.equals(option.getLongOpt())) {
                arrayList.addAll(option.getValuesList());
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private Option resolveOption(String str) {
        String strStripLeadingHyphens = Util.stripLeadingHyphens(str);
        Iterator<Option> it = this.options.iterator();
        while (it.hasNext()) {
            Option next = it.next();
            if (strStripLeadingHyphens.equals(next.getOpt()) || strStripLeadingHyphens.equals(next.getLongOpt())) {
                return next;
            }
        }
        return null;
    }

    public String[] getOptionValues(char c) {
        return getOptionValues(String.valueOf(c));
    }

    public String getOptionValue(String str, String str2) {
        String optionValue = getOptionValue(str);
        return optionValue != null ? optionValue : str2;
    }

    public String getOptionValue(char c, String str) {
        return getOptionValue(String.valueOf(c), str);
    }

    public Properties getOptionProperties(String str) {
        Properties properties = new Properties();
        for (Option option : this.options) {
            if (str.equals(option.getOpt()) || str.equals(option.getLongOpt())) {
                List<String> valuesList = option.getValuesList();
                if (valuesList.size() >= 2) {
                    properties.put(valuesList.get(0), valuesList.get(1));
                } else if (valuesList.size() == 1) {
                    properties.put(valuesList.get(0), "true");
                }
            }
        }
        return properties;
    }

    public String[] getArgs() {
        String[] strArr = new String[this.args.size()];
        this.args.toArray(strArr);
        return strArr;
    }

    public List<String> getArgList() {
        return this.args;
    }

    protected void addArg(String str) {
        this.args.add(str);
    }

    protected void addOption(Option option) {
        this.options.add(option);
    }

    public Iterator<Option> iterator() {
        return this.options.iterator();
    }

    public Option[] getOptions() {
        List<Option> list = this.options;
        return (Option[]) list.toArray(new Option[list.size()]);
    }

    public static final class Builder {
        private final CommandLine commandLine = new CommandLine();

        public Builder addOption(Option option) {
            this.commandLine.addOption(option);
            return this;
        }

        public Builder addArg(String str) {
            this.commandLine.addArg(str);
            return this;
        }

        public CommandLine build() {
            return this.commandLine;
        }
    }
}
