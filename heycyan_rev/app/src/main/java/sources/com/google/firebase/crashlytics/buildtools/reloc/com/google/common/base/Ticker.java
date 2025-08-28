package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base;

/* loaded from: classes2.dex */
public abstract class Ticker {
    private static final Ticker SYSTEM_TICKER = new Ticker() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Ticker.1
        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Ticker
        public long read() {
            return Platform.systemNanoTime();
        }
    };

    public abstract long read();

    protected Ticker() {
    }

    public static Ticker systemTicker() {
        return SYSTEM_TICKER;
    }
}
