package com.iflytek.sparkchain.core;

/* loaded from: classes2.dex */
public class AiHandle {
    private int code;

    /* renamed from: i */
    private int f431i;

    /* renamed from: id */
    private int f432id;

    public AiHandle(int i, int i2, int i3) {
        this.code = i;
        this.f432id = i2;
        this.f431i = i3;
    }

    public int getCode() {
        return this.code;
    }

    public int getI() {
        return this.f431i;
    }

    public int getId() {
        return this.f432id;
    }

    public boolean isFail() {
        return !isSuccess();
    }

    public boolean isSuccess() {
        return this.code == 0;
    }
}
