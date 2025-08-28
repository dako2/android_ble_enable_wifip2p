package com.oudmon.ble.base.communication.bigData.bean;

import java.util.List;

/* loaded from: classes2.dex */
public class ManualHeartRate {
    private List<DetailBean> data;
    private int index;

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public List<DetailBean> getData() {
        return this.data;
    }

    public void setData(List<DetailBean> list) {
        this.data = list;
    }

    public static class DetailBean {

        /* renamed from: m */
        private int f531m;

        /* renamed from: v */
        private int f532v;

        public int getM() {
            return this.f531m;
        }

        public void setM(int i) {
            this.f531m = i;
        }

        public int getV() {
            return this.f532v;
        }

        public void setV(int i) {
            this.f532v = i;
        }
    }
}
