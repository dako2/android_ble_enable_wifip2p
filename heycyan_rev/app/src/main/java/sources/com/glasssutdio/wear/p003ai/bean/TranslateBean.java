package com.glasssutdio.wear.p003ai.bean;

/* loaded from: classes.dex */
public class TranslateBean {
    String from;
    boolean isNew;
    boolean isNotRealTime;
    String sid;
    int status;

    /* renamed from: to */
    String f165to;
    TranslateResult trans_result;

    public TranslateBean(String from, String to, TranslateResult trans_result) {
        this.from = from;
        this.f165to = to;
        this.trans_result = trans_result;
    }

    public TranslateBean() {
    }

    public String getSid() {
        return this.sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public TranslateResult getTrans_result() {
        return this.trans_result;
    }

    public void setTrans_result(TranslateResult trans_result) {
        this.trans_result = trans_result;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return this.f165to;
    }

    public void setTo(String to) {
        this.f165to = to;
    }

    public boolean isNotRealTime() {
        return this.isNotRealTime;
    }

    public void setNotRealTime(boolean notRealTime) {
        this.isNotRealTime = notRealTime;
    }

    public boolean isNew() {
        return this.isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }
}
