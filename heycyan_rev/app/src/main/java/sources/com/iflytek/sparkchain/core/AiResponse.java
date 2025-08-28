package com.iflytek.sparkchain.core;

import java.util.Arrays;

/* loaded from: classes2.dex */
public class AiResponse {
    private String key;
    private int len;
    private int status;
    private EnumC2199c type;
    private byte[] value;
    private VarType varType;

    private byte[] cut(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    public static AiResponse gen(String str, String str2) {
        return gen(str, str2.getBytes(), EnumC2199c.TEXT, VarType.STRING);
    }

    public static AiResponse gen(String str, byte[] bArr, EnumC2199c enumC2199c, VarType varType) {
        AiResponse aiResponse = new AiResponse();
        aiResponse.setKey(str);
        aiResponse.setValue(bArr);
        aiResponse.setLen(bArr.length);
        aiResponse.setType(enumC2199c);
        aiResponse.setVarType(varType);
        return aiResponse;
    }

    public String getKey() {
        return this.key;
    }

    public int getLen() {
        return this.len;
    }

    public int getStatus() {
        return this.status;
    }

    public EnumC2199c getType() {
        return this.type;
    }

    public byte[] getValue() {
        return this.value;
    }

    public VarType getVarType() {
        return this.varType;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setKey(byte[] bArr, int i, int i2) {
        this.key = new String(cut(bArr, i, i2));
    }

    public void setLen(int i) {
        this.len = i;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setType(int i) {
        this.type = EnumC2199c.m532a(i);
    }

    public void setType(EnumC2199c enumC2199c) {
        this.type = enumC2199c;
    }

    public void setValue(byte[] bArr) {
        this.value = bArr;
    }

    public void setValue(byte[] bArr, int i, int i2) {
        this.value = cut(bArr, i, i2);
    }

    public void setVarType(VarType varType) {
        this.varType = varType;
    }

    public String toString() {
        return "AiOutput{key='" + this.key + "', value=" + Arrays.toString(this.value) + ", len=" + this.len + ", type=" + this.type + ", status=" + this.status + ", varType=" + this.varType + '}';
    }
}
