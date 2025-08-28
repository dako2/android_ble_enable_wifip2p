package com.iflytek.sparkchain.core;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public interface EmbeddingOutput {
    int getErrCode();

    String getErrMsg();

    String getRaw();

    ArrayList<Float> getResultArray();

    String getSid();
}
