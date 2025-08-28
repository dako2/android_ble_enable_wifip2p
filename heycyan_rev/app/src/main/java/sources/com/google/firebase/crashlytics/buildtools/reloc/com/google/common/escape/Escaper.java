package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.escape;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function;

/* loaded from: classes2.dex */
public abstract class Escaper {
    private final Function<String, String> asFunction = new Function<String, String>() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.escape.Escaper.1
        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function, java.util.function.Function
        public String apply(String str) {
            return Escaper.this.escape(str);
        }
    };

    public abstract String escape(String str);

    protected Escaper() {
    }

    public final Function<String, String> asFunction() {
        return this.asFunction;
    }
}
