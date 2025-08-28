package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli;

import java.util.ArrayList;

@Deprecated
/* loaded from: classes2.dex */
public class GnuParser extends Parser {
    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.Parser
    protected String[] flatten(Options options, String[] strArr, boolean z) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        boolean z2 = false;
        while (i < strArr.length) {
            String str = strArr[i];
            if (HelpFormatter.DEFAULT_LONG_OPT_PREFIX.equals(str)) {
                arrayList.add(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
                z2 = true;
            } else if (HelpFormatter.DEFAULT_OPT_PREFIX.equals(str)) {
                arrayList.add(HelpFormatter.DEFAULT_OPT_PREFIX);
            } else if (str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
                String strStripLeadingHyphens = Util.stripLeadingHyphens(str);
                if (options.hasOption(strStripLeadingHyphens)) {
                    arrayList.add(str);
                } else if (strStripLeadingHyphens.indexOf(61) != -1 && options.hasOption(strStripLeadingHyphens.substring(0, strStripLeadingHyphens.indexOf(61)))) {
                    arrayList.add(str.substring(0, str.indexOf(61)));
                    arrayList.add(str.substring(str.indexOf(61) + 1));
                } else if (options.hasOption(str.substring(0, 2))) {
                    arrayList.add(str.substring(0, 2));
                    arrayList.add(str.substring(2));
                } else {
                    arrayList.add(str);
                    z2 = z;
                }
            } else {
                arrayList.add(str);
            }
            if (z2) {
                while (true) {
                    i++;
                    if (i < strArr.length) {
                        arrayList.add(strArr[i]);
                    }
                }
            }
            i++;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
