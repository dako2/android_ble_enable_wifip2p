package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
final class JdkPattern extends CommonPattern implements Serializable {
    private static final long serialVersionUID = 0;
    private final Pattern pattern;

    JdkPattern(Pattern pattern) {
        this.pattern = (Pattern) Preconditions.checkNotNull(pattern);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.CommonPattern
    CommonMatcher matcher(CharSequence charSequence) {
        return new JdkMatcher(this.pattern.matcher(charSequence));
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.CommonPattern
    String pattern() {
        return this.pattern.pattern();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.CommonPattern
    int flags() {
        return this.pattern.flags();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.CommonPattern
    public String toString() {
        return this.pattern.toString();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.CommonPattern
    public int hashCode() {
        return this.pattern.hashCode();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.CommonPattern
    public boolean equals(Object obj) {
        if (obj instanceof JdkPattern) {
            return this.pattern.equals(((JdkPattern) obj).pattern);
        }
        return false;
    }

    private static final class JdkMatcher extends CommonMatcher {
        final Matcher matcher;

        JdkMatcher(Matcher matcher) {
            this.matcher = (Matcher) Preconditions.checkNotNull(matcher);
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.CommonMatcher
        boolean matches() {
            return this.matcher.matches();
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.CommonMatcher
        boolean find() {
            return this.matcher.find();
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.CommonMatcher
        boolean find(int i) {
            return this.matcher.find(i);
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.CommonMatcher
        String replaceAll(String str) {
            return this.matcher.replaceAll(str);
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.CommonMatcher
        int end() {
            return this.matcher.end();
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.CommonMatcher
        int start() {
            return this.matcher.start();
        }
    }
}
