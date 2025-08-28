package com.iflytek.sparkchain.core;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public class Embedding {

    public class EmbeddingOutputImpl implements EmbeddingOutput {
        int errCode;
        String errMsg;
        String raw;
        ArrayList<Float> resultArray;
        String sid;

        public EmbeddingOutputImpl() {
        }

        @Override // com.iflytek.sparkchain.core.EmbeddingOutput
        public int getErrCode() {
            return this.errCode;
        }

        @Override // com.iflytek.sparkchain.core.EmbeddingOutput
        public String getErrMsg() {
            return this.errMsg;
        }

        @Override // com.iflytek.sparkchain.core.EmbeddingOutput
        public String getRaw() {
            return this.raw;
        }

        @Override // com.iflytek.sparkchain.core.EmbeddingOutput
        public ArrayList<Float> getResultArray() {
            return this.resultArray;
        }

        @Override // com.iflytek.sparkchain.core.EmbeddingOutput
        public String getSid() {
            return this.sid;
        }

        public void setErrCode(int i) {
            this.errCode = i;
        }

        public void setErrMsg(String str) {
            this.errMsg = str;
        }

        public void setRaw(String str) {
            this.raw = str;
        }

        public void setResultArray(ArrayList<Float> arrayList) {
            this.resultArray = arrayList;
        }

        public void setSid(String str) {
            this.sid = str;
        }
    }

    /* renamed from: com.iflytek.sparkchain.core.Embedding$b */
    private static class C2190b {

        /* renamed from: a */
        private static final Embedding f449a = new Embedding();
    }

    private Embedding() {
    }

    public static Embedding getInst() {
        return C2190b.f449a;
    }

    public EmbeddingOutput embedding(String str, String str2) {
        return embedding(str, str2, null);
    }

    public EmbeddingOutput embedding(String str, String str2, String str3) {
        if (str == null) {
            EmbeddingOutputImpl embeddingOutputImpl = new EmbeddingOutputImpl();
            embeddingOutputImpl.setErrCode(18501);
            embeddingOutputImpl.setErrMsg("input is null object");
        }
        return runEmbedding(str, str2, str3);
    }

    native EmbeddingOutput runEmbedding(String str, String str2, String str3);
}
