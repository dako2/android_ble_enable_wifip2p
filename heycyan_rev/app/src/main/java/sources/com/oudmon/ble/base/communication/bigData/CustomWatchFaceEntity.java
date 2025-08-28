package com.oudmon.ble.base.communication.bigData;

import java.util.List;

/* loaded from: classes2.dex */
public class CustomWatchFaceEntity {
    List<CustomElement> data;

    public List<CustomElement> getData() {
        return this.data;
    }

    public void setData(List<CustomElement> list) {
        this.data = list;
    }

    public static class CustomElement {

        /* renamed from: b */
        int f524b;

        /* renamed from: g */
        int f525g;

        /* renamed from: r */
        int f526r;
        int type;

        /* renamed from: x */
        int f527x;

        /* renamed from: y */
        int f528y;

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }

        public int getX() {
            return this.f527x;
        }

        public void setX(int i) {
            this.f527x = i;
        }

        public int getY() {
            return this.f528y;
        }

        public void setY(int i) {
            this.f528y = i;
        }

        public int getR() {
            return this.f526r;
        }

        public void setR(int i) {
            this.f526r = i;
        }

        public int getG() {
            return this.f525g;
        }

        public void setG(int i) {
            this.f525g = i;
        }

        public int getB() {
            return this.f524b;
        }

        public void setB(int i) {
            this.f524b = i;
        }
    }
}
