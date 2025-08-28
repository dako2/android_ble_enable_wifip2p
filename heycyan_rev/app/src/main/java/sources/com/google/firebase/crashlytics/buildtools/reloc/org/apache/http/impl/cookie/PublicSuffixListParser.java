package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.util.PublicSuffixList;
import java.io.IOException;
import java.io.Reader;

@Deprecated
/* loaded from: classes2.dex */
public class PublicSuffixListParser {
    private final PublicSuffixFilter filter;
    private final com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.util.PublicSuffixListParser parser = new com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.util.PublicSuffixListParser();

    PublicSuffixListParser(PublicSuffixFilter publicSuffixFilter) {
        this.filter = publicSuffixFilter;
    }

    public void parse(Reader reader) throws IOException {
        PublicSuffixList publicSuffixList = this.parser.parse(reader);
        this.filter.setPublicSuffixes(publicSuffixList.getRules());
        this.filter.setExceptions(publicSuffixList.getExceptions());
    }
}
