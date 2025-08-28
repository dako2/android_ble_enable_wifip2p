package com.iflytek.sparkchain.core.raasr;

import android.util.Log;
import com.iflytek.sparkchain.core.AiHelper;
import com.iflytek.sparkchain.core.asr.RegionType;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class RAASR {
    private static final String TAG = "RAASR";
    private RAASRCallbacks cbs;
    protected int sid;
    private AtomicInteger usrTagId = new AtomicInteger(0);
    private HashMap<Integer, Object> contextMap = new HashMap<>();

    public class RaAsrError {
        private String OstSid;
        private String OstTaskId;
        private String OstTaskStatus;
        private String OstTaskType;
        private int code;
        private String errMsg;
        private int failType;
        private String orderId;

        public RaAsrError() {
        }

        public int getCode() {
            return this.code;
        }

        public String getErrMsg() {
            return this.errMsg;
        }

        public int getFailType() {
            return this.failType;
        }

        public String getOrderId() {
            return this.orderId;
        }

        public String getOstSid() {
            return this.OstSid;
        }

        public String getOstTaskId() {
            return this.OstTaskId;
        }

        public String getOstTaskStatus() {
            return this.OstTaskStatus;
        }

        public String getOstTaskType() {
            return this.OstTaskType;
        }

        public void setCode(int i) {
            this.code = i;
        }

        public void setErrMsg(String str) {
            this.errMsg = str;
        }

        public void setFailType(int i) {
            this.failType = i;
        }

        public void setOrderId(String str) {
            this.orderId = str;
        }

        public void setOstSid(String str) {
            this.OstSid = str;
        }

        public void setOstTaskId(String str) {
            this.OstTaskId = str;
        }

        public void setOstTaskStatus(String str) {
            this.OstTaskStatus = str;
        }

        public void setOstTaskType(String str) {
            this.OstTaskType = str;
        }
    }

    public class RaAsrResult {
        private String OstSid;
        private String OstTaskId;
        private String OstTaskStatus;
        private String OstTaskType;
        private int code;
        private long expireTime;
        private String orderId;
        private String orderResult;
        private long originalDuration;
        private long realDuration;
        private int status;
        private int taskEstimateTime;
        private RaAsrTransResult[] transResult;

        public RaAsrResult() {
        }

        public int getCode() {
            return this.code;
        }

        public long getExpireTime() {
            return this.expireTime;
        }

        public String getOrderId() {
            return this.orderId;
        }

        public String getOrderResult() {
            return this.orderResult;
        }

        public long getOriginalDuration() {
            return this.originalDuration;
        }

        public String getOstSid() {
            return this.OstSid;
        }

        public String getOstTaskId() {
            return this.OstTaskId;
        }

        public String getOstTaskStatus() {
            return this.OstTaskStatus;
        }

        public String getOstTaskType() {
            return this.OstTaskType;
        }

        public long getRealDuration() {
            return this.realDuration;
        }

        public int getStatus() {
            return this.status;
        }

        public int getTaskEstimateTime() {
            return this.taskEstimateTime;
        }

        public RaAsrTransResult[] getTransResult() {
            return this.transResult;
        }

        public void setCode(int i) {
            this.code = i;
        }

        public void setExpireTime(long j) {
            this.expireTime = j;
        }

        public void setOrderId(String str) {
            this.orderId = str;
        }

        public void setOrderResult(String str) {
            this.orderResult = str;
        }

        public void setOriginalDuration(long j) {
            this.originalDuration = j;
        }

        public void setOstSid(String str) {
            this.OstSid = str;
        }

        public void setOstTaskId(String str) {
            this.OstTaskId = str;
        }

        public void setOstTaskStatus(String str) {
            this.OstTaskStatus = str;
        }

        public void setOstTaskType(String str) {
            this.OstTaskType = str;
        }

        public void setRealDuration(long j) {
            this.realDuration = j;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public void setTaskEstimateTime(int i) {
            this.taskEstimateTime = i;
        }

        public void setTransResult(RaAsrTransResult[] raAsrTransResultArr) {
            this.transResult = raAsrTransResultArr;
        }
    }

    public class RaAsrTransResult {

        /* renamed from: bg */
        private int f485bg;
        private String dst;

        /* renamed from: ed */
        private int f486ed;
        private String[] roles;
        private String segId;
        private String[] tags;

        public RaAsrTransResult() {
        }

        public int getBg() {
            return this.f485bg;
        }

        public String getDst() {
            return this.dst;
        }

        public int getEd() {
            return this.f486ed;
        }

        public String[] getRoles() {
            return this.roles;
        }

        public String getSegId() {
            return this.segId;
        }

        public String[] getTags() {
            return this.tags;
        }

        public void setBg(int i) {
            this.f485bg = i;
        }

        public void setDst(String str) {
            this.dst = str;
        }

        public void setEd(int i) {
            this.f486ed = i;
        }

        public void setRoles(String[] strArr) {
            this.roles = strArr;
        }

        public void setSegId(String str) {
            this.segId = str;
        }

        public void setTags(String[] strArr) {
            this.tags = strArr;
        }
    }

    /* renamed from: com.iflytek.sparkchain.core.raasr.RAASR$a */
    class RunnableC2207a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ int f487a;

        RunnableC2207a(int i) {
            this.f487a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.v(RAASR.TAG, "raasrDestroy:index:" + this.f487a);
            RAASR.this.raasrDestroy(this.f487a);
        }
    }

    public RAASR(String str) {
        this.sid = 0;
        this.sid = create(str);
    }

    public RAASR(String str, RegionType regionType) {
        this.sid = 0;
        this.sid = create(str == null ? "" : str, regionType);
    }

    private native int raasrCreate(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native void raasrDestroy(int i);

    private native int raasrGetResultOnceAsync(int i, String str, int i2);

    private native int raasrOstCancelTask(int i, String str, String str2, int i2);

    private native int raasrOstDeleteTask(int i, String str, String str2, int i2);

    private native int raasrOstRetryTask(int i, String str, int i2);

    private native int raasrRegionCreate(String str, int i);

    private native int raasrStop(int i);

    private native int raasrUploadAsync(int i, String str, String str2, int i2);

    private native void setRAASRAudioMode(int i, String str);

    private native void setRAASRAudioUrl(int i, String str);

    private native void setRAASRCallbackUrl(int i, String str);

    private native void setRAASRCandidate(int i, int i2);

    private native void setRAASREngColloqproc(int i, boolean z);

    private native void setRAASREngRlang(int i, int i2);

    private native void setRAASREngSegMax(int i, int i2);

    private native void setRAASREngSegMin(int i, int i2);

    private native void setRAASREngSegWeight(int i, float f);

    private native void setRAASREngSmoothproc(int i, boolean z);

    private native void setRAASREngVadMargin(int i, int i2);

    private native void setRAASREngVadMdn(int i, int i2);

    private native void setRAASRHotWord(int i, String str);

    private native void setRAASRLanguage(int i, String str);

    private native void setRAASRLanguageType(int i, int i2);

    private native void setRAASROstAppVer(int i, String str);

    private native void setRAASROstAudioSize(int i, int i2);

    private native void setRAASROstAudioSrc(int i, String str);

    private native void setRAASROstCallbackKey(int i, String str);

    private native void setRAASROstCallbackSecret(int i, String str);

    private native void setRAASROstCombineMax(int i, int i2);

    private native void setRAASROstDeviceId(int i, String str);

    private native void setRAASROstDeviceImei(int i, String str);

    private native void setRAASROstDeviceImsi(int i, String str);

    private native void setRAASROstDeviceMac(int i, String str);

    private native void setRAASROstDeviceOther(int i, String str);

    private native void setRAASROstDhw(int i, String str);

    private native void setRAASROstDuration(int i, int i2);

    private native void setRAASROstEnableSubtitle(int i, int i2);

    private native void setRAASROstEncoding(int i, String str);

    private native void setRAASROstFeatureList(int i, Vector<String> vector);

    private native void setRAASROstFormat(int i, String str);

    private native void setRAASROstNbest(int i, int i2);

    private native void setRAASROstNetIsp(int i, String str);

    private native void setRAASROstNetType(int i, String str);

    private native void setRAASROstNunum(int i, int i2);

    private native void setRAASROstOutputType(int i, int i2);

    private native void setRAASROstPersonalization(int i, String str, String str2);

    private native void setRAASROstPostprocOn(int i, int i2);

    private native void setRAASROstProcinfoOn(int i, int i2);

    private native void setRAASROstResId(int i, String str);

    private native void setRAASROstResUrl(int i, String str);

    private native void setRAASROstResegment(int i, int i2);

    private native void setRAASROstSpeakerNum(int i, int i2);

    private native void setRAASROstTaskType(int i, String str);

    private native void setRAASROstUid(int i, String str);

    private native void setRAASROstVsppOn(int i, int i2);

    private native void setRAASROstVto(int i, int i2);

    private native void setRAASRPd(int i, String str);

    private native void setRAASRResultType(int i, String str);

    private native void setRAASRRoleNum(int i, int i2);

    private native void setRAASRRoleType(int i, int i2);

    private native void setRAASRStandardWav(int i, int i2);

    private native void setRAASRTrackMode(int i, int i2);

    private native void setRAASRTransLanguage(int i, String str);

    private native void setRAASRTransMode(int i, int i2);

    public void OstAppVer(String str) {
        setRAASROstAppVer(this.sid, str);
    }

    public void OstAudioSize(int i) {
        setRAASROstAudioSize(this.sid, i);
    }

    public void OstAudioSrc(String str) {
        setRAASROstAudioSrc(this.sid, str);
    }

    public void OstCallbackKey(String str) {
        setRAASROstCallbackKey(this.sid, str);
    }

    public void OstCallbackSecret(String str) {
        setRAASROstCallbackSecret(this.sid, str);
    }

    public int OstCancelTask(String str, String str2, Object obj) {
        int iIncrementAndGet = this.usrTagId.incrementAndGet();
        this.contextMap.put(Integer.valueOf(iIncrementAndGet), obj);
        return raasrOstCancelTask(this.sid, str, str2, iIncrementAndGet);
    }

    public void OstCombineMax(int i) {
        setRAASROstCombineMax(this.sid, i);
    }

    public int OstDeleteTask(String str, String str2, Object obj) {
        int iIncrementAndGet = this.usrTagId.incrementAndGet();
        this.contextMap.put(Integer.valueOf(iIncrementAndGet), obj);
        return raasrOstDeleteTask(this.sid, str, str2, iIncrementAndGet);
    }

    public void OstDeviceId(String str) {
        setRAASROstDeviceId(this.sid, str);
    }

    public void OstDeviceImei(String str) {
        setRAASROstDeviceImei(this.sid, str);
    }

    public void OstDeviceImsi(String str) {
        setRAASROstDeviceImsi(this.sid, str);
    }

    public void OstDeviceMac(String str) {
        setRAASROstDeviceMac(this.sid, str);
    }

    public void OstDeviceOther(String str) {
        setRAASROstDeviceOther(this.sid, str);
    }

    public void OstDhw(String str) {
        setRAASROstDhw(this.sid, str);
    }

    public void OstDuration(int i) {
        setRAASROstDuration(this.sid, i);
    }

    public void OstEnableSubtitle(int i) {
        setRAASROstEnableSubtitle(this.sid, i);
    }

    public void OstEncoding(String str) {
        setRAASROstEncoding(this.sid, str);
    }

    public void OstFeatureList(Vector<String> vector) {
        setRAASROstFeatureList(this.sid, vector);
    }

    public void OstFormat(String str) {
        setRAASROstFormat(this.sid, str);
    }

    public void OstNbest(int i) {
        setRAASROstNbest(this.sid, i);
    }

    public void OstNetIsp(String str) {
        setRAASROstNetIsp(this.sid, str);
    }

    public void OstNetType(String str) {
        setRAASROstNetType(this.sid, str);
    }

    public void OstNunum(int i) {
        setRAASROstNunum(this.sid, i);
    }

    public void OstOutputType(int i) {
        setRAASROstOutputType(this.sid, i);
    }

    public void OstPersonalization(String str, String str2) {
        setRAASROstPersonalization(this.sid, str, str2);
    }

    public void OstPostprocOn(int i) {
        setRAASROstPostprocOn(this.sid, i);
    }

    public void OstProcinfoOn(int i) {
        setRAASROstProcinfoOn(this.sid, i);
    }

    public void OstResId(String str) {
        setRAASROstResId(this.sid, str);
    }

    public void OstResUrl(String str) {
        setRAASROstResUrl(this.sid, str);
    }

    public void OstResegment(int i) {
        setRAASROstResegment(this.sid, i);
    }

    public int OstRetryTask(String str, Object obj) {
        int iIncrementAndGet = this.usrTagId.incrementAndGet();
        this.contextMap.put(Integer.valueOf(iIncrementAndGet), obj);
        return raasrOstRetryTask(this.sid, str, iIncrementAndGet);
    }

    public void OstSpeakerNum(int i) {
        setRAASROstSpeakerNum(this.sid, i);
    }

    public void OstTaskType(String str) {
        setRAASROstTaskType(this.sid, str);
    }

    public void OstUid(String str) {
        setRAASROstUid(this.sid, str);
    }

    public void OstVsppOn(int i) {
        setRAASROstVsppOn(this.sid, i);
    }

    public void OstVto(int i) {
        setRAASROstVto(this.sid, i);
    }

    public void audioMode(String str) {
        setRAASRAudioMode(this.sid, str);
    }

    public void audioUrl(String str) {
        setRAASRAudioUrl(this.sid, str);
    }

    public void callbackUrl(String str) {
        setRAASRCallbackUrl(this.sid, str);
    }

    public void candidate(int i) {
        setRAASRCandidate(this.sid, i);
    }

    protected int create(String str) {
        return raasrCreate(str);
    }

    protected int create(String str, RegionType regionType) {
        return raasrRegionCreate(str, regionType.getValue());
    }

    public void engColloqproc(boolean z) {
        setRAASREngColloqproc(this.sid, z);
    }

    public void engRlang(int i) {
        setRAASREngRlang(this.sid, i);
    }

    public void engSegMax(int i) {
        setRAASREngSegMax(this.sid, i);
    }

    public void engSegMin(int i) {
        setRAASREngSegMin(this.sid, i);
    }

    public void engSegWeight(float f) {
        setRAASREngSegWeight(this.sid, f);
    }

    public void engSmoothproc(boolean z) {
        setRAASREngSmoothproc(this.sid, z);
    }

    public void engVadMargin(int i) {
        setRAASREngVadMargin(this.sid, i);
    }

    public void engVadMdn(int i) {
        setRAASREngVadMdn(this.sid, i);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        AiHelper.getInst().getHandler().postDelayed(new RunnableC2207a(this.sid), 1000L);
    }

    public int getResultOnceAsync(String str, Object obj) {
        int iIncrementAndGet = this.usrTagId.incrementAndGet();
        this.contextMap.put(Integer.valueOf(iIncrementAndGet), obj);
        return raasrGetResultOnceAsync(this.sid, str, iIncrementAndGet);
    }

    public void hotWord(String str) {
        setRAASRHotWord(this.sid, str);
    }

    public void language(String str) {
        setRAASRLanguage(this.sid, str);
    }

    public void languageType(int i) {
        setRAASRLanguageType(this.sid, i);
    }

    /* renamed from: pd */
    public void m536pd(String str) {
        setRAASRPd(this.sid, str);
    }

    public void raasrErrorCallback(RaAsrError raAsrError, int i) {
        Log.i(TAG, "Java raasrErrorCallback");
        RAASRCallbacks rAASRCallbacks = this.cbs;
        if (rAASRCallbacks != null) {
            rAASRCallbacks.onError(raAsrError, this.contextMap.get(Integer.valueOf(i)));
        }
        this.contextMap.remove(Integer.valueOf(i));
    }

    public void raasrResultCallback(RaAsrResult raAsrResult, int i) {
        Log.i(TAG, "Java raasrResultCallback");
        RAASRCallbacks rAASRCallbacks = this.cbs;
        if (rAASRCallbacks != null) {
            rAASRCallbacks.onResult(raAsrResult, this.contextMap.get(Integer.valueOf(i)));
        }
        this.contextMap.remove(Integer.valueOf(i));
    }

    public void registerCallbacks(RAASRCallbacks rAASRCallbacks) {
        this.cbs = rAASRCallbacks;
    }

    public void resultType(String str) {
        setRAASRResultType(this.sid, str);
    }

    public void roleNum(int i) {
        setRAASRRoleNum(this.sid, i);
    }

    public void roleType(int i) {
        setRAASRRoleType(this.sid, i);
    }

    public void standardWav(int i) {
        setRAASRStandardWav(this.sid, i);
    }

    public int stop() {
        return raasrStop(this.sid);
    }

    public void trackMode(int i) {
        setRAASRTrackMode(this.sid, i);
    }

    public void transLanguage(String str) {
        setRAASRTransLanguage(this.sid, str);
    }

    public void transMode(int i) {
        setRAASRTransMode(this.sid, i);
    }

    public int uploadAsync(String str, String str2, Object obj) {
        int iIncrementAndGet = this.usrTagId.incrementAndGet();
        this.contextMap.put(Integer.valueOf(iIncrementAndGet), obj);
        return raasrUploadAsync(this.sid, str, str2, iIncrementAndGet);
    }
}
