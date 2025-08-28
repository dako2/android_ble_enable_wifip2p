package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client;

/* loaded from: classes2.dex */
class SystemClock implements Clock {
    SystemClock() {
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.Clock
    public long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
