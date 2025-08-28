package com.iflytek.sparkchain.core.rtasr;

import android.util.Log;
import com.iflytek.sparkchain.core.AiHelper;
import com.iflytek.sparkchain.core.asr.RegionType;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class RTASR {
    private static final String TAG = "RTASR";
    private RTASRCallbacks cbs;
    private int sessionId;
    protected int sid;
    private AtomicInteger usrTagId = new AtomicInteger(0);
    private HashMap<Integer, Object> contextMap = new HashMap<>();
    private AtomicBoolean isWrite = new AtomicBoolean(false);

    public class RtAsrError {
        private String OstContextId;
        private int code;
        private String errMsg;
        private String sid;

        public RtAsrError() {
        }

        public int getCode() {
            return this.code;
        }

        public String getErrMsg() {
            return this.errMsg;
        }

        public String getOstContextId() {
            return this.OstContextId;
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

        public void setOstContextId(String str) {
            this.OstContextId = str;
        }

        public void setSid(String str) {
            this.sid = str;
        }
    }

    public class RtAsrResult {
        private String OstContextId;
        private String data;
        private String rawResult;
        private String sid;
        private int status;
        private RtTransResult transResult;

        public RtAsrResult() {
        }

        public String getData() {
            return this.data;
        }

        public String getOstContextId() {
            return this.OstContextId;
        }

        public String getRawResult() {
            return this.rawResult;
        }

        public String getSid() {
            return this.sid;
        }

        public int getStatus() {
            return this.status;
        }

        public RtTransResult getTransResult() {
            return this.transResult;
        }

        public void setData(String str) {
            this.data = str;
        }

        public void setOstContextId(String str) {
            this.OstContextId = str;
        }

        public void setRawResult(String str) {
            this.rawResult = str;
        }

        public void setSid(String str) {
            this.sid = str;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public void setTransResult(RtTransResult rtTransResult) {
            this.transResult = rtTransResult;
        }
    }

    public class RtTransResult {
        private String dst;
        private String src;
        private int status;

        public RtTransResult() {
        }

        public String getDst() {
            return this.dst;
        }

        public String getSrc() {
            return this.src;
        }

        public int getStatus() {
            return this.status;
        }

        public void setDst(String str) {
            this.dst = str;
        }

        public void setSrc(String str) {
            this.src = str;
        }

        public void setStatus(int i) {
            this.status = i;
        }
    }

    /* renamed from: com.iflytek.sparkchain.core.rtasr.RTASR$a */
    class RunnableC2208a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ int f489a;

        RunnableC2208a(int i) {
            this.f489a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.v(RTASR.TAG, "rtasrDestroy:index:" + this.f489a);
            RTASR.this.rtasrDestroy(this.f489a);
        }
    }

    public RTASR(String str) {
        this.sid = 0;
        this.sid = create(str);
    }

    public RTASR(String str, RegionType regionType) {
        this.sid = 0;
        this.sid = create(str == null ? "" : str, regionType);
    }

    private native int rtasrCreate(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native void rtasrDestroy(int i);

    private native int rtasrRegionCreate(String str, int i);

    private native int rtasrStart(int i, int i2);

    private native int rtasrStop(int i);

    private native int rtasrWrite(int i, byte[] bArr);

    private native void setRTASREngLangType(int i, int i2);

    private native void setRTASRLang(int i, String str);

    private native void setRTASROstAccent(int i, String str);

    private native void setRTASROstAppVer(int i, String str);

    private native void setRTASROstContextId(int i, String str);

    private native void setRTASROstDeviceId(int i, String str);

    private native void setRTASROstDeviceImei(int i, String str);

    private native void setRTASROstDeviceImsi(int i, String str);

    private native void setRTASROstDeviceMac(int i, String str);

    private native void setRTASROstDeviceOther(int i, String str);

    private native void setRTASROstDhw(int i, String str);

    private native void setRTASROstDhwMod(int i, int i2);

    private native void setRTASROstDid(int i, String str);

    private native void setRTASROstDomain(int i, String str);

    private native void setRTASROstDwa(int i, String str);

    private native void setRTASROstDyhotws(int i, int i2);

    private native void setRTASROstEncoding(int i, String str);

    private native void setRTASROstEos(int i, int i2);

    private native void setRTASROstFeatureList(int i, Vector<String> vector);

    private native void setRTASROstFormat(int i, String str);

    private native void setRTASROstFrameId(int i, int i2);

    private native void setRTASROstLanguageType(int i, int i2);

    private native void setRTASROstNetIsp(int i, String str);

    private native void setRTASROstNetType(int i, String str);

    private native void setRTASROstNunum(int i, int i2);

    private native void setRTASROstPersonalization(int i, String str, String str2);

    private native void setRTASROstPgsFlashFreq(int i, int i2);

    private native void setRTASROstPgsnum(int i, int i2);

    private native void setRTASROstPptaw(int i, int i2);

    private native void setRTASROstRequestId(int i, String str);

    private native void setRTASROstResId(int i, String str);

    private native void setRTASROstRf(int i, String str);

    private native void setRTASROstRlang(int i, int i2);

    private native void setRTASROstRsgid(int i, int i2);

    private native void setRTASROstSegMax(int i, int i2);

    private native void setRTASROstSegMin(int i, int i2);

    private native void setRTASROstSegWeight(int i, float f);

    private native void setRTASROstSpeexSize(int i, int i2);

    private native void setRTASROstSpkdia(int i, int i2);

    private native void setRTASROstUid(int i, String str);

    private native void setRTASROstVto(int i, int i2);

    private native void setRTASRPd(int i, String str);

    private native void setRTASRPunc(int i, String str);

    private native void setRTASRRoleType(int i, int i2);

    private native void setRTASRTargetLang(int i, String str);

    private native void setRTASRTransStrategy(int i, int i2);

    private native void setRTASRTransType(int i, String str);

    private native void setRTASRVadMdn(int i, int i2);

    public void OstAccent(String str) {
        setRTASROstAccent(this.sid, str);
    }

    public void OstAppVer(String str) {
        setRTASROstAppVer(this.sid, str);
    }

    public void OstContextId(String str) {
        setRTASROstContextId(this.sid, str);
    }

    public void OstDeviceId(String str) {
        setRTASROstDeviceId(this.sid, str);
    }

    public void OstDeviceImei(String str) {
        setRTASROstDeviceImei(this.sid, str);
    }

    public void OstDeviceImsi(String str) {
        setRTASROstDeviceImsi(this.sid, str);
    }

    public void OstDeviceMac(String str) {
        setRTASROstDeviceMac(this.sid, str);
    }

    public void OstDeviceOther(String str) {
        setRTASROstDeviceOther(this.sid, str);
    }

    public void OstDhw(String str) {
        setRTASROstDhw(this.sid, str);
    }

    public void OstDhwMod(int i) {
        setRTASROstDhwMod(this.sid, i);
    }

    public void OstDid(String str) {
        setRTASROstDid(this.sid, str);
    }

    public void OstDomain(String str) {
        setRTASROstDomain(this.sid, str);
    }

    public void OstDwa(String str) {
        setRTASROstDwa(this.sid, str);
    }

    public void OstDyhotws(int i) {
        setRTASROstDyhotws(this.sid, i);
    }

    public void OstEncoding(String str) {
        setRTASROstEncoding(this.sid, str);
    }

    public void OstEos(int i) {
        setRTASROstEos(this.sid, i);
    }

    public void OstFeatureList(Vector<String> vector) {
        setRTASROstFeatureList(this.sid, vector);
    }

    public void OstFormat(String str) {
        setRTASROstFormat(this.sid, str);
    }

    public void OstFrameId(int i) {
        setRTASROstFrameId(this.sid, i);
    }

    public void OstLanguageType(int i) {
        setRTASROstLanguageType(this.sid, i);
    }

    public void OstNetIsp(String str) {
        setRTASROstNetIsp(this.sid, str);
    }

    public void OstNetType(String str) {
        setRTASROstNetType(this.sid, str);
    }

    public void OstNunum(int i) {
        setRTASROstNunum(this.sid, i);
    }

    public void OstPersonalization(String str, String str2) {
        setRTASROstPersonalization(this.sid, str, str2);
    }

    public void OstPgsFlashFreq(int i) {
        setRTASROstPgsFlashFreq(this.sid, i);
    }

    public void OstPgsnum(int i) {
        setRTASROstPgsnum(this.sid, i);
    }

    public void OstPptaw(int i) {
        setRTASROstPptaw(this.sid, i);
    }

    public void OstRequestId(String str) {
        setRTASROstRequestId(this.sid, str);
    }

    public void OstResId(String str) {
        setRTASROstResId(this.sid, str);
    }

    public void OstRf(String str) {
        setRTASROstRf(this.sid, str);
    }

    public void OstRlang(int i) {
        setRTASROstRlang(this.sid, i);
    }

    public void OstRsgid(int i) {
        setRTASROstRsgid(this.sid, i);
    }

    public void OstSegMax(int i) {
        setRTASROstSegMax(this.sid, i);
    }

    public void OstSegMin(int i) {
        setRTASROstSegMin(this.sid, i);
    }

    public void OstSegWeight(float f) {
        setRTASROstSegWeight(this.sid, f);
    }

    public void OstSpeexSize(int i) {
        setRTASROstSpeexSize(this.sid, i);
    }

    public void OstSpkdia(int i) {
        setRTASROstSpkdia(this.sid, i);
    }

    public void OstUid(String str) {
        setRTASROstUid(this.sid, str);
    }

    public void OstVto(int i) {
        setRTASROstVto(this.sid, i);
    }

    protected int create(String str) {
        return rtasrCreate(str);
    }

    protected int create(String str, RegionType regionType) {
        return rtasrRegionCreate(str, regionType.getValue());
    }

    public void engLangType(int i) {
        setRTASREngLangType(this.sid, i);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        AiHelper.getInst().getHandler().postDelayed(new RunnableC2208a(this.sid), 1000L);
    }

    public void lang(String str) {
        setRTASRLang(this.sid, str);
    }

    /* renamed from: pd */
    public void m537pd(String str) {
        setRTASRPd(this.sid, str);
    }

    public void punc(String str) {
        setRTASRPunc(this.sid, str);
    }

    public void registerCallbacks(RTASRCallbacks rTASRCallbacks) {
        this.cbs = rTASRCallbacks;
    }

    public void roleType(int i) {
        setRTASRRoleType(this.sid, i);
    }

    public void rtasrErrorCallback(RtAsrError rtAsrError, int i) {
        Log.i(TAG, "Java rtasrErrorCallback");
        RTASRCallbacks rTASRCallbacks = this.cbs;
        if (rTASRCallbacks != null) {
            rTASRCallbacks.onError(rtAsrError, this.contextMap.get(Integer.valueOf(i)));
        }
        this.sessionId = i;
        this.contextMap.remove(Integer.valueOf(i));
    }

    public void rtasrResultCallback(RtAsrResult rtAsrResult, int i) {
        Log.i(TAG, "Java rtasrResultCallback");
        if (rtAsrResult == null) {
            return;
        }
        this.sessionId = i;
        RTASRCallbacks rTASRCallbacks = this.cbs;
        if (rTASRCallbacks != null) {
            rTASRCallbacks.onResult(rtAsrResult, this.contextMap.get(Integer.valueOf(i)));
        }
        if (rtAsrResult.getStatus() == 3) {
            this.contextMap.remove(Integer.valueOf(i));
        }
    }

    public int start(Object obj) {
        int iIncrementAndGet = this.usrTagId.incrementAndGet();
        this.contextMap.put(Integer.valueOf(iIncrementAndGet), obj);
        return rtasrStart(this.sid, iIncrementAndGet);
    }

    public int stop() {
        return rtasrStop(this.sid);
    }

    public void targetLang(String str) {
        setRTASRTargetLang(this.sid, str);
    }

    public void transStrategy(int i) {
        setRTASRTransStrategy(this.sid, i);
    }

    public void transType(String str) {
        setRTASRTransType(this.sid, str);
    }

    public void vadMdn(int i) {
        setRTASRVadMdn(this.sid, i);
    }

    public int write(byte[] bArr) {
        return rtasrWrite(this.sid, bArr);
    }
}
