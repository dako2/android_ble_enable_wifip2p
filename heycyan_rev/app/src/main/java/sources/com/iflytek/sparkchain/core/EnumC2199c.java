package com.iflytek.sparkchain.core;

/* renamed from: com.iflytek.sparkchain.core.c */
/* loaded from: classes2.dex */
public enum EnumC2199c implements Const {
    TEXT(0),
    AUDIO(1),
    IMAGE(2),
    VIDEO(3),
    PER(4),
    OTHER(-1);


    /* renamed from: a */
    private final int f470a;

    EnumC2199c(int i) {
        this.f470a = i;
    }

    /* renamed from: a */
    public static EnumC2199c m532a(int i) {
        EnumC2199c enumC2199c = TEXT;
        if (i == enumC2199c.f470a) {
            return enumC2199c;
        }
        EnumC2199c enumC2199c2 = AUDIO;
        if (i == enumC2199c2.f470a) {
            return enumC2199c2;
        }
        EnumC2199c enumC2199c3 = IMAGE;
        if (i == enumC2199c3.f470a) {
            return enumC2199c3;
        }
        EnumC2199c enumC2199c4 = VIDEO;
        if (i == enumC2199c4.f470a) {
            return enumC2199c4;
        }
        EnumC2199c enumC2199c5 = PER;
        if (i == enumC2199c5.f470a) {
            return enumC2199c5;
        }
        EnumC2199c enumC2199c6 = OTHER;
        if (i == enumC2199c6.f470a) {
            return enumC2199c6;
        }
        throw new IllegalArgumentException("type not supported");
    }

    @Override // com.iflytek.sparkchain.core.Const
    public int getValue() {
        return this.f470a;
    }
}
