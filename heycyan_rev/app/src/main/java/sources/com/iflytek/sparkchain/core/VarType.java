package com.iflytek.sparkchain.core;

/* loaded from: classes2.dex */
public enum VarType implements Const {
    STRING(0),
    INT(1),
    DOUBLE(2),
    BOOL(3),
    UNKNOWN(-1);


    /* renamed from: a */
    private final int f456a;

    VarType(int i) {
        this.f456a = i;
    }

    public static VarType valueOf(int i) {
        if (i == 0) {
            return STRING;
        }
        if (i == 1) {
            return INT;
        }
        if (i == 2) {
            return DOUBLE;
        }
        if (i == 3) {
            return BOOL;
        }
        if (i == -1) {
            return UNKNOWN;
        }
        throw new IllegalArgumentException("type not supported");
    }

    @Override // com.iflytek.sparkchain.core.Const
    public int getValue() {
        return this.f456a;
    }
}
