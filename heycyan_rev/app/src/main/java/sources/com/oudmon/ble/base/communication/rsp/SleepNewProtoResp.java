package com.oudmon.ble.base.communication.rsp;

import java.util.List;

/* loaded from: classes2.dex */
public class SleepNewProtoResp {

    /* renamed from: et */
    private int f534et;
    private List<DetailBean> list;

    /* renamed from: st */
    private int f535st;

    public int getSt() {
        return this.f535st;
    }

    public void setSt(int i) {
        this.f535st = i;
    }

    public int getEt() {
        return this.f534et;
    }

    public void setEt(int i) {
        this.f534et = i;
    }

    public List<DetailBean> getList() {
        return this.list;
    }

    public void setList(List<DetailBean> list) {
        this.list = list;
    }

    public static class DetailBean {

        /* renamed from: d */
        private int f536d;

        /* renamed from: t */
        private int f537t;

        public int getD() {
            return this.f536d;
        }

        public void setD(int i) {
            this.f536d = i;
        }

        public int getT() {
            return this.f537t;
        }

        public void setT(int i) {
            this.f537t = i;
        }
    }
}
