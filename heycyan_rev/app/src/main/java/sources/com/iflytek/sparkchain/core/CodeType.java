package com.iflytek.sparkchain.core;

/* loaded from: classes2.dex */
public enum CodeType implements Const {
    CODEC_ENCODE(0),
    CODEC_DECODE(1);


    /* renamed from: a */
    private final int f446a;

    CodeType(int i) {
        this.f446a = i;
    }

    public static CodeType valueOf(int i) {
        if (i == 0) {
            return CODEC_ENCODE;
        }
        if (i == 1) {
            return CODEC_DECODE;
        }
        return null;
    }

    @Override // com.iflytek.sparkchain.core.Const
    public int getValue() {
        return this.f446a;
    }
}
