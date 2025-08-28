package com.iflytek.sparkchain.core;

/* loaded from: classes2.dex */
public enum LogLvl implements Const {
    VERBOSE(0),
    DEBUG(1),
    INFO(2),
    WARN(3),
    ERROR(4),
    FATAL(5),
    OFF(100);


    /* renamed from: a */
    private final int f453a;

    LogLvl(int i) {
        this.f453a = i;
    }

    public static LogLvl valueOf(int i) {
        if (i == 100) {
            return OFF;
        }
        if (i == 0) {
            return VERBOSE;
        }
        if (i == 1) {
            return DEBUG;
        }
        if (i == 2) {
            return INFO;
        }
        if (i == 3) {
            return WARN;
        }
        if (i == 4) {
            return ERROR;
        }
        if (i == 5) {
            return FATAL;
        }
        return null;
    }

    @Override // com.iflytek.sparkchain.core.Const
    public int getValue() {
        return this.f453a;
    }
}
