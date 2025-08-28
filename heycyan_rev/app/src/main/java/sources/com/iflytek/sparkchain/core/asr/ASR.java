package com.iflytek.sparkchain.core.asr;

import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.iflytek.sparkchain.core.AiHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ASR {
    private static final String TAG = "AEE_ASR";
    private int sessionId;
    private AtomicInteger usrTagId = new AtomicInteger(0);
    private HashMap<Integer, Object> contextMap = new HashMap<>();
    private AsrCallbacks cbs = null;
    private AtomicBoolean isWrite = new AtomicBoolean(false);
    private final int index = asrCreate();

    public class ASRError {
        private int code;
        private String errMsg;
        private String sid;

        public ASRError() {
        }

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

    public class ASRResult {
        private int begin;
        private String bestMatchText;
        private int end;
        private String sid;
        private int status;
        private List<Transcription> transcriptions;
        private List<Vad> vads;

        public ASRResult() {
        }

        public int getBegin() {
            return this.begin;
        }

        public String getBestMatchText() {
            return this.bestMatchText;
        }

        public int getEnd() {
            return this.end;
        }

        public String getSid() {
            return this.sid;
        }

        public int getStatus() {
            return this.status;
        }

        public List<Transcription> getTranscriptions() {
            return this.transcriptions;
        }

        public List<Vad> getVads() {
            return this.vads;
        }

        public void setBegin(int i) {
            this.begin = i;
        }

        public void setBestMatchText(String str) {
            this.bestMatchText = str;
        }

        public void setEnd(int i) {
            this.end = i;
        }

        public void setSid(String str) {
            this.sid = str;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public void setTranscriptions(List<Transcription> list) {
            this.transcriptions = list;
        }

        public void setVads(List<Vad> list) {
            this.vads = list;
        }
    }

    public ASR() {
    }

    public ASR(String str, String str2, String str3) {
        language(str);
        domain(str2);
        accent(str3);
    }

    public ASR(String str, String str2, String str3, RegionType regionType) {
        language(str);
        domain(str2);
        accent(str3);
        region(regionType.getValue());
    }

    private native int asrCreate();

    /* JADX INFO: Access modifiers changed from: private */
    public native void asrDestroy(int i);

    private native int asrStart(int i, int i2);

    private native int asrStartWithAudioAttributes(int i, int i2, int i3, String str, int i4, int i5, int i6);

    private native int asrStop(int i, boolean z);

    private native int asrWrite(int i, byte[] bArr);

    private ASRResult parseJson(String str) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            ASRResult aSRResult = new ASRResult();
            aSRResult.setBegin(jSONObject.optInt("begin"));
            aSRResult.setEnd(jSONObject.optInt("end"));
            aSRResult.setSid(jSONObject.optString("sid"));
            aSRResult.setBestMatchText(jSONObject.optString("bestMatchText"));
            aSRResult.setStatus(jSONObject.optInt(NotificationCompat.CATEGORY_STATUS));
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("transcriptions");
            ArrayList arrayList = new ArrayList();
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
                    Transcription transcription = new Transcription();
                    transcription.setIndex(jSONObject2.optInt("index"));
                    JSONArray jSONArrayOptJSONArray2 = jSONObject2.optJSONArray("segments");
                    ArrayList arrayList2 = new ArrayList();
                    if (jSONArrayOptJSONArray2 != null) {
                        for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                            JSONObject jSONObject3 = jSONArrayOptJSONArray2.getJSONObject(i2);
                            Segment segment = new Segment();
                            segment.setScore(jSONObject3.optInt("score"));
                            segment.setText(jSONObject3.optString("text"));
                            segment.setLg(jSONObject3.optString("lg"));
                            arrayList2.add(segment);
                        }
                    }
                    transcription.setSegments(arrayList2);
                    arrayList.add(transcription);
                }
            }
            aSRResult.setTranscriptions(arrayList);
            JSONArray jSONArrayOptJSONArray3 = jSONObject.optJSONArray("vads");
            ArrayList arrayList3 = new ArrayList();
            if (jSONArrayOptJSONArray3 != null) {
                for (int i3 = 0; i3 < jSONArrayOptJSONArray3.length(); i3++) {
                    JSONObject jSONObject4 = jSONArrayOptJSONArray3.getJSONObject(i3);
                    Vad vad = new Vad();
                    vad.setBegin(jSONObject4.optInt("begin"));
                    vad.setEnd(jSONObject4.optInt("end"));
                    arrayList3.add(vad);
                }
            }
            aSRResult.setVads(arrayList3);
            return aSRResult;
        } catch (JSONException e) {
            Log.w(TAG, "Java tts parseJson:" + e.toString());
            return null;
        }
    }

    private native void setAsrAccent(int i, String str);

    private native void setAsrDomain(int i, String str);

    private native void setAsrDwa(int i, String str);

    private native void setAsrLanguage(int i, String str);

    private native void setAsrNbest(int i, int i2);

    private native void setAsrNunum(int i, boolean z);

    private native void setAsrPd(int i, String str);

    private native void setAsrPtt(int i, boolean z);

    private native void setAsrRlang(int i, String str);

    private native void setAsrSpeexSize(int i, int i2);

    private native void setAsrVadEos(int i, int i2);

    private native void setAsrVinfo(int i, boolean z);

    private native void setAsrWbest(int i, int i2);

    private native void setDhw(int i, String str);

    private native void setLn(int i, String str);

    private native void setLtc(int i, int i2);

    private native void setOpt(int i, int i2);

    private native void setProc(int i, boolean z);

    private native void setRegion(int i, int i2);

    private native void setSmth(int i, boolean z);

    private native void setSvad(int i, int i2);

    private native void setTargetLanguages(int i, String str);

    private native void setVadEnable(int i, boolean z);

    private native void setVgap(int i, int i2);

    public void accent(String str) {
        setAsrAccent(this.index, str);
    }

    protected void asrErrorCallback(ASRError aSRError, int i) {
        Log.i(TAG, "Java ttsErrorCallback");
        this.sessionId = i;
        AsrCallbacks asrCallbacks = this.cbs;
        if (asrCallbacks != null) {
            asrCallbacks.onError(aSRError, this.contextMap.get(Integer.valueOf(i)));
        }
        this.contextMap.remove(Integer.valueOf(i));
    }

    protected void asrResultCallback(String str, int i) {
        ASRResult json;
        Log.i(TAG, "Java ttsResultCallback");
        if (TextUtils.isEmpty(str) || (json = parseJson(str)) == null) {
            return;
        }
        this.sessionId = i;
        AsrCallbacks asrCallbacks = this.cbs;
        if (asrCallbacks != null) {
            asrCallbacks.onResult(json, this.contextMap.get(Integer.valueOf(i)));
        }
        if (json.getStatus() == 2) {
            this.contextMap.remove(Integer.valueOf(i));
        }
    }

    public void dhw(String str) {
        setDhw(this.index, str);
    }

    public void domain(String str) {
        setAsrDomain(this.index, str);
    }

    public void dwa(String str) {
        setAsrDwa(this.index, str);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        final int i = this.index;
        AiHelper.getInst().getHandler().postDelayed(new Runnable() { // from class: com.iflytek.sparkchain.core.asr.ASR.1
            @Override // java.lang.Runnable
            public void run() {
                Log.v(ASR.TAG, "asrDestroy:index:" + ASR.this.index);
                ASR.this.asrDestroy(i);
            }
        }, 1000L);
    }

    public void language(String str) {
        setAsrLanguage(this.index, str);
    }

    /* renamed from: ln */
    public void m529ln(String str) {
        setLn(this.index, str);
    }

    public void ltc(int i) {
        setLtc(this.index, i);
    }

    public void nbest(int i) {
        setAsrNbest(this.index, i);
    }

    public void nunum(boolean z) {
        setAsrNunum(this.index, z);
    }

    public void opt(int i) {
        setOpt(this.index, i);
    }

    /* renamed from: pd */
    public void m530pd(String str) {
        setAsrPd(this.index, str);
    }

    public void proc(boolean z) {
        setProc(this.index, z);
    }

    public void ptt(boolean z) {
        setAsrPtt(this.index, z);
    }

    public void region(int i) {
        setRegion(this.index, i);
    }

    public void registerCallbacks(AsrCallbacks asrCallbacks) {
        this.cbs = asrCallbacks;
    }

    public void rlang(String str) {
        setAsrRlang(this.index, str);
    }

    public void smth(boolean z) {
        setSmth(this.index, z);
    }

    public void speexSize(int i) {
        setAsrSpeexSize(this.index, i);
    }

    public int start(AudioAttributes audioAttributes, Object obj) {
        if (audioAttributes == null) {
            return start(obj);
        }
        int iIncrementAndGet = this.usrTagId.incrementAndGet();
        this.contextMap.put(Integer.valueOf(iIncrementAndGet), obj);
        return asrStartWithAudioAttributes(this.index, iIncrementAndGet, audioAttributes.getSampleRate(), audioAttributes.getEncoding(), audioAttributes.getChannels(), audioAttributes.getBitdepth(), audioAttributes.getFrameSize());
    }

    public int start(Object obj) {
        int iIncrementAndGet = this.usrTagId.incrementAndGet();
        this.contextMap.put(Integer.valueOf(iIncrementAndGet), obj);
        return asrStart(this.index, iIncrementAndGet);
    }

    public int stop(boolean z) {
        return asrStop(this.index, z);
    }

    public void svad(int i) {
        setSvad(this.index, i);
    }

    public void targetLanguages(String str) {
        setTargetLanguages(this.index, str);
    }

    public void vadEnable(boolean z) {
        setVadEnable(this.index, z);
    }

    public void vadEos(int i) {
        setAsrVadEos(this.index, i);
    }

    public void vgap(int i) {
        setVgap(this.index, i);
    }

    public void vinfo(boolean z) {
        setAsrVinfo(this.index, z);
    }

    public void wbest(int i) {
        setAsrWbest(this.index, i);
    }

    public int write(byte[] bArr) {
        return asrWrite(this.index, bArr);
    }
}
