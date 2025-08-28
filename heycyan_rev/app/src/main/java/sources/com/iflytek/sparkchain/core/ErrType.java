package com.iflytek.sparkchain.core;

/* loaded from: classes2.dex */
public enum ErrType implements Const {
    AUTH(0),
    HTTP(1),
    UNKNOWN(-1);


    /* renamed from: a */
    private final int f451a;

    ErrType(int i) {
        this.f451a = i;
    }

    public static ErrType valueOf(int i) {
        return i == 0 ? AUTH : i == 1 ? HTTP : UNKNOWN;
    }

    @Override // com.iflytek.sparkchain.core.Const
    public int getValue() {
        return this.f451a;
    }
}
