package com.iflytek.sparkchain.core.imageTrans;

import android.util.Log;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class ImageTrans {
    private static final String TAG = "ImageTrans";
    private ImageTransCallbacks cbs;
    private HashMap<Integer, Object> contextMap;
    private String mFromLanguage;
    private String mOcrLanguage;
    private int mReturnType;
    private String mToLanguage;
    protected int sid;
    private AtomicInteger usrTagId;

    public static class BlockText {
        private String dst;
        private String src;

        public String getDst() {
            return this.dst;
        }

        public String getSrc() {
            return this.src;
        }

        public void setDst(String str) {
            this.dst = str;
        }

        public void setSrc(String str) {
            this.src = str;
        }
    }

    public static class ImageTransError {
        private int code;
        private String errMsg;
        private String sid;

        public int getCode() {
            return this.code;
        }

        public String getErrMsg() {
            return this.errMsg;
        }

        public String getSid() {
            return this.sid;
        }

        public void setCode(int i) {
            this.code = i;
        }

        public void setErrMsg(String str) {
            this.errMsg = str;
        }

        public void setSid(String str) {
            this.sid = str;
        }
    }

    public static class ImageTransResult {
        private BlockText[] blockText;
        private byte[] itsImage;
        private String itsOutput;
        private String sid;
        private int status;

        public BlockText[] getBlockText() {
            return this.blockText;
        }

        public byte[] getItsImage() {
            return this.itsImage;
        }

        public String getItsOutput() {
            return this.itsOutput;
        }

        public String getSid() {
            return this.sid;
        }

        public int getStatus() {
            return this.status;
        }

        public void setBlockText(BlockText[] blockTextArr) {
            this.blockText = blockTextArr;
        }

        public void setItsImage(byte[] bArr) {
            this.itsImage = bArr;
        }

        public void setItsOutput(String str) {
            this.itsOutput = str;
        }

        public void setSid(String str) {
            this.sid = str;
        }

        public void setStatus(int i) {
            this.status = i;
        }
    }

    public ImageTrans() {
        this.sid = 0;
        this.usrTagId = new AtomicInteger(0);
        this.contextMap = new HashMap<>();
        this.mOcrLanguage = "ch_en";
        this.mFromLanguage = "cn";
        this.mToLanguage = "en";
        this.mReturnType = 0;
        this.sid = create("", "", "");
    }

    public ImageTrans(String str, String str2, String str3) {
        this.sid = 0;
        this.usrTagId = new AtomicInteger(0);
        this.contextMap = new HashMap<>();
        this.mReturnType = 0;
        this.mOcrLanguage = str;
        this.mFromLanguage = str2;
        this.mToLanguage = str3;
        this.sid = create(str, str2, str3);
    }

    private native int imageTransArun(int i, byte[] bArr, String str, int i2);

    private native int imageTransCreate(String str, String str2, String str3);

    private native void imageTransDestroy(int i);

    private native void setImageTransFromLanguage(int i, String str);

    private native void setImageTransOcrLanguage(int i, String str);

    private native void setImageTransReturnType(int i, int i2);

    private native void setImageTransToLanguage(int i, String str);

    public int arun(byte[] bArr, String str, Object obj) {
        int iIncrementAndGet = this.usrTagId.incrementAndGet();
        this.contextMap.put(Integer.valueOf(iIncrementAndGet), obj);
        return imageTransArun(this.sid, bArr, str, iIncrementAndGet);
    }

    protected int create(String str, String str2, String str3) {
        return imageTransCreate(str, str2, str3);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        imageTransDestroy(this.sid);
    }

    public void fromLanguage(String str) {
        this.mFromLanguage = str;
        setImageTransFromLanguage(this.sid, str);
    }

    public void imageTransErrorCallback(ImageTransError imageTransError, int i) {
        Log.i(TAG, "Java imageTransErrorCallback");
        ImageTransCallbacks imageTransCallbacks = this.cbs;
        if (imageTransCallbacks != null) {
            imageTransCallbacks.onError(imageTransError, this.contextMap.get(Integer.valueOf(i)));
        }
        this.contextMap.remove(Integer.valueOf(i));
    }

    public void imageTransResultCallback(ImageTransResult imageTransResult, int i) {
        Log.i(TAG, "Java imageTransResultCallback");
        ImageTransCallbacks imageTransCallbacks = this.cbs;
        if (imageTransCallbacks != null) {
            imageTransCallbacks.onResult(imageTransResult, this.contextMap.get(Integer.valueOf(i)));
        }
        if (imageTransResult.getStatus() == 3) {
            this.contextMap.remove(Integer.valueOf(i));
        }
    }

    public void ocrLanguage(String str) {
        this.mOcrLanguage = str;
        setImageTransOcrLanguage(this.sid, str);
    }

    public void registerCallbacks(ImageTransCallbacks imageTransCallbacks) {
        this.cbs = imageTransCallbacks;
    }

    public void returnType(int i) {
        this.mReturnType = i;
        setImageTransReturnType(this.sid, i);
    }

    public void toLanguage(String str) {
        this.mToLanguage = str;
        setImageTransToLanguage(this.sid, str);
    }
}
