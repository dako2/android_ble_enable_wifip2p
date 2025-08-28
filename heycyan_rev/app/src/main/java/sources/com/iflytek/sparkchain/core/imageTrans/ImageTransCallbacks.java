package com.iflytek.sparkchain.core.imageTrans;

import com.iflytek.sparkchain.core.imageTrans.ImageTrans;

/* loaded from: classes2.dex */
public interface ImageTransCallbacks {
    void onError(ImageTrans.ImageTransError imageTransError, Object obj);

    void onResult(ImageTrans.ImageTransResult imageTransResult, Object obj);
}
