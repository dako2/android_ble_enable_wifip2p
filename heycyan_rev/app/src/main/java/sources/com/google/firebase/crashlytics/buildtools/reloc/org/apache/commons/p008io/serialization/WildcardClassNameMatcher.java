package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.serialization;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.FilenameUtils;

/* loaded from: classes2.dex */
final class WildcardClassNameMatcher implements ClassNameMatcher {
    private final String pattern;

    public WildcardClassNameMatcher(String str) {
        this.pattern = str;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.serialization.ClassNameMatcher
    public boolean matches(String str) {
        return FilenameUtils.wildcardMatch(str, this.pattern);
    }
}
