package com.glasssutdio.wear.p003ai.spark;

/* loaded from: classes.dex */
public class AudioByte {
    private byte[] rawData;
    private String sid;
    private String text;

    public AudioByte(byte[] rawData, String sid) {
        this.rawData = rawData;
        this.sid = sid;
    }

    public AudioByte(byte[] rawData, String sid, String text) {
        this.rawData = rawData;
        this.sid = sid;
        this.text = text;
    }

    public AudioByte(byte[] rawData) {
        this.rawData = rawData;
    }

    public byte[] getRawData() {
        return this.rawData;
    }

    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }

    public String getSid() {
        return this.sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
