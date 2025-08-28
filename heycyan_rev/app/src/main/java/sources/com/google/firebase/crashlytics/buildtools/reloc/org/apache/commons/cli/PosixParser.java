package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Deprecated
/* loaded from: classes2.dex */
public class PosixParser extends Parser {
    private Option currentOption;
    private boolean eatTheRest;
    private Options options;
    private final List<String> tokens = new ArrayList();

    private void init() {
        this.eatTheRest = false;
        this.tokens.clear();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.Parser
    protected String[] flatten(Options options, String[] strArr, boolean z) throws ParseException {
        init();
        this.options = options;
        Iterator<String> it = Arrays.asList(strArr).iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (HelpFormatter.DEFAULT_OPT_PREFIX.equals(next) || HelpFormatter.DEFAULT_LONG_OPT_PREFIX.equals(next)) {
                this.tokens.add(next);
            } else if (next.startsWith(HelpFormatter.DEFAULT_LONG_OPT_PREFIX)) {
                int iIndexOf = next.indexOf(61);
                String strSubstring = iIndexOf == -1 ? next : next.substring(0, iIndexOf);
                List<String> matchingOptions = options.getMatchingOptions(strSubstring);
                if (matchingOptions.isEmpty()) {
                    processNonOptionToken(next, z);
                } else {
                    if (matchingOptions.size() > 1) {
                        throw new AmbiguousOptionException(strSubstring, matchingOptions);
                    }
                    this.currentOption = options.getOption(matchingOptions.get(0));
                    this.tokens.add(HelpFormatter.DEFAULT_LONG_OPT_PREFIX + this.currentOption.getLongOpt());
                    if (iIndexOf != -1) {
                        this.tokens.add(next.substring(iIndexOf + 1));
                    }
                }
            } else if (next.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
                if (next.length() == 2 || options.hasOption(next)) {
                    processOptionToken(next, z);
                } else if (!options.getMatchingOptions(next).isEmpty()) {
                    List<String> matchingOptions2 = options.getMatchingOptions(next);
                    if (matchingOptions2.size() > 1) {
                        throw new AmbiguousOptionException(next, matchingOptions2);
                    }
                    processOptionToken(HelpFormatter.DEFAULT_OPT_PREFIX + options.getOption(matchingOptions2.get(0)).getLongOpt(), z);
                } else {
                    burstToken(next, z);
                }
            } else {
                processNonOptionToken(next, z);
            }
            gobble(it);
        }
        List<String> list = this.tokens;
        return (String[]) list.toArray(new String[list.size()]);
    }

    private void gobble(Iterator<String> it) {
        if (this.eatTheRest) {
            while (it.hasNext()) {
                this.tokens.add(it.next());
            }
        }
    }

    private void processNonOptionToken(String str, boolean z) {
        Option option;
        if (z && ((option = this.currentOption) == null || !option.hasArg())) {
            this.eatTheRest = true;
            this.tokens.add(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
        }
        this.tokens.add(str);
    }

    private void processOptionToken(String str, boolean z) {
        if (z && !this.options.hasOption(str)) {
            this.eatTheRest = true;
        }
        if (this.options.hasOption(str)) {
            this.currentOption = this.options.getOption(str);
        }
        this.tokens.add(str);
    }

    protected void burstToken(String str, boolean z) {
        int i;
        for (int i2 = 1; i2 < str.length(); i2++) {
            String strValueOf = String.valueOf(str.charAt(i2));
            if (!this.options.hasOption(strValueOf)) {
                if (z) {
                    processNonOptionToken(str.substring(i2), true);
                    return;
                } else {
                    this.tokens.add(str);
                    return;
                }
            }
            this.tokens.add(HelpFormatter.DEFAULT_OPT_PREFIX + strValueOf);
            Option option = this.options.getOption(strValueOf);
            this.currentOption = option;
            if (option.hasArg() && str.length() != (i = i2 + 1)) {
                this.tokens.add(str.substring(i));
                return;
            }
        }
    }
}
