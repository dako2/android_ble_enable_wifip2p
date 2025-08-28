package com.google.firebase.crashlytics.buildtools.log;

/* loaded from: classes2.dex */
public interface CrashlyticsLogger {
    void logD(String str);

    void logE(String str, Throwable th);

    void logI(String str);

    void logV(String str);

    void logW(String str, Throwable th);

    void setLevel(Level level);

    public enum Level {
        ERROR(0),
        WARNING(1),
        INFO(2),
        DEBUG(3),
        VERBOSE(4);

        private final int value;

        Level(int i) {
            this.value = i;
        }

        public boolean logsFor(Level level) {
            return this.value >= level.value;
        }
    }
}
