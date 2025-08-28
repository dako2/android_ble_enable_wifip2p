package com.iflytek.sparkchain.core.its;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;
import com.iflytek.sparkchain.core.AiHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ITS {
    private static final String API_KEY = "AIzaSyD7C3AP2BpR9z79jkIqR3QdIZsYjsdwZfY";
    private static final String API_URL = "https://translation.googleapis.com/language/translate/v2?key=AIzaSyD7C3AP2BpR9z79jkIqR3QdIZsYjsdwZfY";
    private static final int MSG_EXECUTE_TRANSLATE = 0;
    private static final int MSG_TRANSLATION_ERROR = 2;
    private static final int MSG_TRANSLATION_SUCCESS = 1;
    private static final String TAG = "ITS";
    private ITSCallbacks cbs;
    private HashMap<Integer, Object> contextMap;
    private ITSError error;
    private ITSResult itsResult;
    private String mFormat;
    private String mSource;
    private String mTarget;
    private HandlerC2204b mTransHandle;
    private C2205c mTransThread;
    private TransType mType;
    protected int sid;
    private TransResult transResult;
    private AtomicInteger usrTagId;

    public static class ITSError {
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

    public static class ITSResult {
        private String from;
        private String sid;
        private int status;

        /* renamed from: to */
        private String f480to;
        private TransResult transResult;

        public String getFrom() {
            return this.from;
        }

        public String getSid() {
            return this.sid;
        }

        public int getStatus() {
            return this.status;
        }

        public String getTo() {
            return this.f480to;
        }

        public TransResult getTransResult() {
            return this.transResult;
        }

        public void setFrom(String str) {
            this.from = str;
        }

        public void setSid(String str) {
            this.sid = str;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public void setTo(String str) {
            this.f480to = str;
        }

        public void setTransResult(TransResult transResult) {
            this.transResult = transResult;
        }
    }

    public static class TransResult {
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

    /* renamed from: com.iflytek.sparkchain.core.its.ITS$a */
    class RunnableC2203a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ int f481a;

        RunnableC2203a(int i) {
            this.f481a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.v(ITS.TAG, "rtasrDestroy:index:" + this.f481a);
            ITS.this.itsDestroy(this.f481a);
        }
    }

    /* renamed from: com.iflytek.sparkchain.core.its.ITS$b */
    private class HandlerC2204b extends Handler {
        private HandlerC2204b() {
        }

        /* synthetic */ HandlerC2204b(ITS its, RunnableC2203a runnableC2203a) {
            this();
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) throws Throwable {
            if (message.what != 0) {
                return;
            }
            Log.d(ITS.TAG, "TransThread msg type:0");
            ITS.this.executeTrans(message.getData().getString("txt"), message.obj);
        }
    }

    /* renamed from: com.iflytek.sparkchain.core.its.ITS$c */
    public class C2205c extends Thread {
        public C2205c() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Log.d(ITS.TAG, "TransThread started.");
            Looper.prepare();
            ITS.this.mTransHandle = new HandlerC2204b(ITS.this, null);
            Looper.loop();
        }
    }

    public ITS() {
        this.sid = 0;
        this.usrTagId = new AtomicInteger(0);
        this.contextMap = new HashMap<>();
        TransType transType = TransType.ITRANS;
        this.mType = transType;
        this.mSource = "";
        this.mTarget = "";
        this.mFormat = "text";
        this.itsResult = new ITSResult();
        this.transResult = new TransResult();
        this.error = new ITSError();
        this.sid = create(transType);
    }

    public ITS(TransType transType) {
        this.sid = 0;
        this.usrTagId = new AtomicInteger(0);
        this.contextMap = new HashMap<>();
        this.mType = TransType.ITRANS;
        this.mSource = "";
        this.mTarget = "";
        this.mFormat = "text";
        this.itsResult = new ITSResult();
        this.transResult = new TransResult();
        this.error = new ITSError();
        this.sid = create(transType);
    }

    public ITS(String str, String str2, TransType transType) throws InterruptedException {
        this.sid = 0;
        this.usrTagId = new AtomicInteger(0);
        this.contextMap = new HashMap<>();
        this.mType = TransType.ITRANS;
        this.mSource = "";
        this.mTarget = "";
        this.mFormat = "text";
        this.itsResult = new ITSResult();
        this.transResult = new TransResult();
        this.error = new ITSError();
        C2205c c2205c = new C2205c();
        this.mTransThread = c2205c;
        c2205c.start();
        try {
            Thread.sleep(50L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mSource = str;
        this.mTarget = str2;
        this.sid = create(str, str2, transType);
    }

    private int createGoogleTrans(String str, Object obj) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.mSource.isEmpty() || this.mTarget.isEmpty()) {
            return 18501;
        }
        try {
            jSONObject.put("q", str);
            jSONObject.put("source", this.mSource);
            jSONObject.put("format", this.mFormat);
            jSONObject.put(TypedValues.AttributesType.S_TARGET, this.mTarget);
            if (this.mTransThread != null && this.mTransHandle != null) {
                Message messageObtain = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString("txt", jSONObject.toString());
                Log.d(TAG, "request txt:" + jSONObject.toString());
                messageObtain.obj = obj;
                messageObtain.setData(bundle);
                messageObtain.what = 0;
                this.mTransHandle.sendMessage(messageObtain);
            }
            return 0;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0101 A[Catch: IOException -> 0x0105, TRY_ENTER, TryCatch #1 {IOException -> 0x0105, blocks: (B:19:0x00ae, B:21:0x00b3, B:22:0x00b6, B:40:0x0101, B:44:0x0109, B:46:0x010e), top: B:65:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0109 A[Catch: IOException -> 0x0105, TryCatch #1 {IOException -> 0x0105, blocks: (B:19:0x00ae, B:21:0x00b3, B:22:0x00b6, B:40:0x0101, B:44:0x0109, B:46:0x010e), top: B:65:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x010e A[Catch: IOException -> 0x0105, TRY_LEAVE, TryCatch #1 {IOException -> 0x0105, blocks: (B:19:0x00ae, B:21:0x00b3, B:22:0x00b6, B:40:0x0101, B:44:0x0109, B:46:0x010e), top: B:65:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0124 A[Catch: IOException -> 0x0120, TryCatch #7 {IOException -> 0x0120, blocks: (B:55:0x011c, B:59:0x0124, B:61:0x0129), top: B:68:0x011c }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0129 A[Catch: IOException -> 0x0120, TRY_LEAVE, TryCatch #7 {IOException -> 0x0120, blocks: (B:55:0x011c, B:59:0x0124, B:61:0x0129), top: B:68:0x011c }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x011c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r5v8, types: [java.net.HttpURLConnection, java.net.URLConnection] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void executeTrans(String str, Object obj) throws Throwable {
        OutputStream outputStream;
        BufferedReader bufferedReader;
        ?? r5;
        BufferedReader bufferedReader2;
        OutputStream outputStream2;
        BufferedReader bufferedReader3 = null;
        try {
            try {
                r5 = (HttpURLConnection) new URL(API_URL).openConnection();
                try {
                    r5.setRequestMethod(HttpPost.METHOD_NAME);
                    r5.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                    r5.setDoOutput(true);
                    r5.setConnectTimeout(10000);
                    r5.setReadTimeout(10000);
                    outputStream = r5.getOutputStream();
                } catch (IOException e) {
                    e = e;
                    outputStream = null;
                } catch (Throwable th) {
                    th = th;
                    outputStream = null;
                    bufferedReader = null;
                    bufferedReader2 = r5;
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                return;
            }
        } catch (IOException e3) {
            e = e3;
            r5 = 0;
            outputStream = null;
        } catch (Throwable th2) {
            th = th2;
            outputStream = null;
            bufferedReader = null;
            r5 = bufferedReader3;
            if (outputStream != null) {
            }
            if (bufferedReader != null) {
            }
            if (r5 != 0) {
            }
            throw th;
        }
        try {
            outputStream.write(str.getBytes("UTF-8"));
            outputStream.flush();
            int responseCode = r5.getResponseCode();
            Log.d(TAG, "request code: " + responseCode);
            if (responseCode == 200) {
                bufferedReader = new BufferedReader(new InputStreamReader(r5.getInputStream(), "UTF-8"));
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        } else {
                            sb.append(line);
                        }
                    }
                    handleResult(sb.toString(), str, obj);
                    bufferedReader3 = bufferedReader;
                } catch (IOException e4) {
                    e = e4;
                    outputStream2 = outputStream;
                    try {
                        Log.d(TAG, "error msg: " + e.getMessage());
                        ITSError iTSError = new ITSError();
                        iTSError.setCode(-1);
                        iTSError.setErrMsg(e.getMessage());
                        this.cbs.onError(iTSError, obj);
                        if (outputStream2 != null) {
                            outputStream2.close();
                        }
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (r5 == 0) {
                            r5.disconnect();
                            return;
                        }
                        return;
                    } catch (Throwable th3) {
                        th = th3;
                        outputStream = outputStream2;
                        bufferedReader2 = r5;
                        bufferedReader3 = bufferedReader2;
                        r5 = bufferedReader3;
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                                throw th;
                            }
                        }
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (r5 != 0) {
                            r5.disconnect();
                        }
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    if (outputStream != null) {
                    }
                    if (bufferedReader != null) {
                    }
                    if (r5 != 0) {
                    }
                    throw th;
                }
            } else {
                Log.d(TAG, "request code: " + responseCode + "request msg: " + r5.getResponseMessage());
                ITSError iTSError2 = new ITSError();
                iTSError2.setCode(-1);
                iTSError2.setErrMsg("result is null.");
                this.cbs.onError(iTSError2, obj);
            }
            outputStream.close();
            if (bufferedReader3 != null) {
                bufferedReader3.close();
            }
            r5.disconnect();
        } catch (IOException e6) {
            e = e6;
            bufferedReader = null;
            outputStream2 = outputStream;
            Log.d(TAG, "error msg: " + e.getMessage());
            ITSError iTSError3 = new ITSError();
            iTSError3.setCode(-1);
            iTSError3.setErrMsg(e.getMessage());
            this.cbs.onError(iTSError3, obj);
            if (outputStream2 != null) {
            }
            if (bufferedReader != null) {
            }
            if (r5 == 0) {
            }
        } catch (Throwable th5) {
            th = th5;
            bufferedReader = null;
            bufferedReader2 = r5;
            bufferedReader3 = bufferedReader2;
            r5 = bufferedReader3;
            if (outputStream != null) {
            }
            if (bufferedReader != null) {
            }
            if (r5 != 0) {
            }
            throw th;
        }
    }

    private void handleResult(String str, String str2, Object obj) throws JSONException {
        ITSCallbacks iTSCallbacks;
        if (str != null) {
            System.out.println("Response: " + str);
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("data")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                    if (jSONObject2.has("translations")) {
                        JSONArray jSONArray = jSONObject2.getJSONArray("translations");
                        for (int i = 0; i < jSONArray.length(); i++) {
                            JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                            this.itsResult.setStatus(3);
                            this.transResult.setDst(jSONObject3.getString("translatedText"));
                            this.transResult.setSrc(str2);
                            this.itsResult.setTransResult(this.transResult);
                            this.cbs.onResult(this.itsResult, obj);
                        }
                        return;
                    }
                    this.error.setCode(-1);
                    this.error.setErrMsg("result translatedText is null.");
                    iTSCallbacks = this.cbs;
                } else {
                    this.error.setCode(-1);
                    this.error.setErrMsg("result data is null.");
                    iTSCallbacks = this.cbs;
                }
                iTSCallbacks.onError(this.error, obj);
                return;
            } catch (JSONException e) {
                this.error.setCode(-1);
                this.error.setErrMsg(e.getMessage());
            }
        } else {
            this.error.setCode(-1);
            this.error.setErrMsg("result is null.");
        }
        this.cbs.onError(this.error, obj);
    }

    private native int itsArun(int i, String str, int i2);

    private native int itsCreate(String str, String str2, int i);

    private native int itsCreateType(int i);

    /* JADX INFO: Access modifiers changed from: private */
    public native void itsDestroy(int i);

    private native void setITSFromlanguage(int i, String str);

    private native void setITSResId(int i, String str);

    private native void setITSTolanguage(int i, String str);

    public int arun(String str, Object obj) {
        if (this.mType == TransType.GOOGLE_TRANS) {
            return createGoogleTrans(str, obj);
        }
        int iIncrementAndGet = this.usrTagId.incrementAndGet();
        this.contextMap.put(Integer.valueOf(iIncrementAndGet), obj);
        return itsArun(this.sid, str, iIncrementAndGet);
    }

    protected int create(TransType transType) {
        if (transType != TransType.GOOGLE_TRANS) {
            return itsCreateType(transType.ordinal());
        }
        this.mType = transType;
        return 0;
    }

    protected int create(String str, String str2, TransType transType) {
        if (transType != TransType.GOOGLE_TRANS) {
            return itsCreate(str, str2, transType.ordinal());
        }
        this.mType = transType;
        return 0;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        AiHelper.getInst().getHandler().postDelayed(new RunnableC2203a(this.sid), 1000L);
    }

    public void fromlanguage(String str) {
        if (this.mType == TransType.GOOGLE_TRANS) {
            this.mSource = str;
        } else {
            setITSFromlanguage(this.sid, str);
        }
    }

    public void itsErrorCallback(ITSError iTSError, int i) {
        Log.i(TAG, "Java itsErrorCallback");
        ITSCallbacks iTSCallbacks = this.cbs;
        if (iTSCallbacks != null) {
            iTSCallbacks.onError(iTSError, this.contextMap.get(Integer.valueOf(i)));
        }
        this.contextMap.remove(Integer.valueOf(i));
    }

    public void itsResultCallback(ITSResult iTSResult, int i) {
        Log.i(TAG, "Java itsResultCallback");
        ITSCallbacks iTSCallbacks = this.cbs;
        if (iTSCallbacks != null) {
            iTSCallbacks.onResult(iTSResult, this.contextMap.get(Integer.valueOf(i)));
        }
        if (iTSResult.getStatus() == 3) {
            this.contextMap.remove(Integer.valueOf(i));
        }
    }

    public void registerCallbacks(ITSCallbacks iTSCallbacks) {
        this.cbs = iTSCallbacks;
    }

    public void resId(String str) {
        setITSResId(this.sid, str);
    }

    public void tolanguage(String str) {
        if (this.mType == TransType.GOOGLE_TRANS) {
            this.mTarget = str;
        } else {
            setITSTolanguage(this.sid, str);
        }
    }
}
