package com.iflytek.sparkchain.core.asr;

import com.iflytek.sparkchain.core.Const;

/* loaded from: classes2.dex */
public enum RegionType implements Const {
    EU_TYPE(2);

    private final int value;

    RegionType(int i) {
        this.value = i;
    }

    public static RegionType valueOf(int i) {
        if (i == 2) {
            return EU_TYPE;
        }
        return null;
    }

    @Override // com.iflytek.sparkchain.core.Const
    public int getValue() {
        return this.value;
    }
}
