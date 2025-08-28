package com.iflytek.sparkchain.core;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.app.NotificationCompat;
import com.iflytek.sparkchain.core.AiRequest;
import com.iflytek.sparkchain.core.AiUrl;
import com.iflytek.sparkchain.core.JniCallback;
import com.iflytek.sparkchain.core.media.player.PcmPlayer;
import com.iflytek.sparkchain.core.media.record.IFlyAudioManager;
import com.iflytek.sparkchain.core.media.record.PcmRecorder;
import com.iflytek.sparkchain.media.PcmPlayerHelper;
import com.iflytek.sparkchain.media.RecorderHelper;
import com.iflytek.sparkchain.media.record.InterfaceC2219b;
import com.iflytek.sparkchain.media.record.PcmRecorder;
import com.iflytek.sparkchain.media.speech.SpeechError;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public class AiHelper extends BaseLibrary implements AiAbilityApi {
    private static final String AIKit_VERSION = "2.0";
    private static final int HANDLE_EMPTY = 8305;
    private static final String TAG = "AiHelper";
    protected static Handler handler;
    private final ChatListener chatListener;
    private int contextId;
    private AtomicBoolean isRecord;
    private final AiResponseListener listener;
    private final HashMap<String, AiListener> listeners;
    private AiResponseListener userListener;
    private ChatListener usrChatListener;
    private SparseArray usrContextMap;

    private static class Holder {
        private static final AiHelper instance = new AiHelper();

        private Holder() {
        }
    }

    static {
        try {
            System.loadLibrary("SparkChain");
            System.loadLibrary("spark");
        } catch (Exception e) {
            Log.e(TAG, "loadLibrary:" + e.toString());
        }
        handler = new Handler(Looper.getMainLooper());
    }

    private AiHelper() {
        this.usrContextMap = new SparseArray();
        this.contextId = 0;
        this.isRecord = new AtomicBoolean(false);
        this.listeners = new HashMap<>();
        this.usrChatListener = null;
        this.chatListener = new ChatListener() { // from class: com.iflytek.sparkchain.core.AiHelper.3
            @Override // com.iflytek.sparkchain.core.ChatListener
            public void onChatError(AIChatHandle aIChatHandle, int i, String str) {
                if (AiHelper.this.usrChatListener != null) {
                    AiHelper.this.usrChatListener.onChatError(aIChatHandle, i, str);
                }
                AiHelper.this.usrContextMap.remove(aIChatHandle.getUsrContextIndex());
            }

            @Override // com.iflytek.sparkchain.core.ChatListener
            public void onChatOutput(AIChatHandle aIChatHandle, String str, String str2, String str3, int i) {
                if (AiHelper.this.usrChatListener != null) {
                    AiHelper.this.usrChatListener.onChatOutput(aIChatHandle, str, str2, str3, i);
                }
            }

            @Override // com.iflytek.sparkchain.core.ChatListener
            public void onChatToken(AIChatHandle aIChatHandle, int i, int i2, int i3) {
                if (AiHelper.this.usrChatListener != null) {
                    AiHelper.this.usrChatListener.onChatToken(aIChatHandle, i, i2, i3);
                }
                AiHelper.this.usrContextMap.remove(aIChatHandle.getUsrContextIndex());
            }
        };
        this.userListener = null;
        this.listener = new AiResponseListener() { // from class: com.iflytek.sparkchain.core.AiHelper.4
            @Override // com.iflytek.sparkchain.core.AiResponseListener
            public void onError(String str, int i, int i2, String str2, Object obj) {
                AiListener aiListener = (AiListener) AiHelper.this.listeners.get(str);
                if (aiListener != null) {
                    aiListener.onError(i, i2, str2, obj);
                } else if (AiHelper.this.userListener != null) {
                    AiHelper.this.userListener.onError(str, i, i2, str2, obj);
                }
            }

            @Override // com.iflytek.sparkchain.core.AiResponseListener
            public void onEvent(String str, int i, int i2, List<AiResponse> list, Object obj) {
                AiListener aiListener = (AiListener) AiHelper.this.listeners.get(str);
                if (aiListener != null) {
                    aiListener.onEvent(i, i2, list, obj);
                } else if (AiHelper.this.userListener != null) {
                    AiHelper.this.userListener.onEvent(str, i, i2, list, obj);
                }
            }

            @Override // com.iflytek.sparkchain.core.AiResponseListener
            public void onResult(String str, int i, List<AiResponse> list, Object obj) {
                AiListener aiListener = (AiListener) AiHelper.this.listeners.get(str);
                if (aiListener != null) {
                    aiListener.onResult(i, list, obj);
                } else if (AiHelper.this.userListener != null) {
                    AiHelper.this.userListener.onResult(str, i, list, obj);
                }
            }
        };
    }

    private int genUsrContextId(Object obj) {
        int i;
        if (obj == null) {
            return 0;
        }
        synchronized (Holder.instance) {
            i = this.contextId + 1;
            this.contextId = i;
            this.usrContextMap.put(i, obj);
        }
        return i;
    }

    public static AiHelper getInst() {
        return Holder.instance;
    }

    native int aiKitCodec(String str, String str2, int i, String str3, int i2);

    native int aikitAsyncChat(Object obj, String str, int i, long j);

    native int aikitEnd(int i, int i2);

    native int aikitEndChat(int i, int i2);

    native int aikitEngineInit(String str, long j);

    native int aikitEngineInitNoParams(String str);

    native int aikitEngineUnInit(String str);

    native int aikitFreeAbility(String str);

    native JniCallback.JniAuthTimer aikitGetAuthLeftTime(String str);

    native int aikitLoadData(String str, long j);

    native int aikitLoadDataSpeakable(String str, long j);

    public int aikitLoadDataSpeakable(String str, AiRequest aiRequest) {
        return aikitLoadDataSpeakable(str, aiRequest.getHandle());
    }

    native int aikitOneShot(String str, long j, long j2, int i);

    native int aikitOneShotAsync(String str, long j);

    native JniCallback.JniOutput aikitOneShotSync(String str, long j, int i);

    native int aikitPreProcess(String str, long j);

    native int aikitRead(String str, int i);

    native JniCallback.JniOutput aikitReadSync(String str, int i);

    native int aikitSetBoolConfig(String str, boolean z);

    native int aikitSetConfig(String str, String str2);

    native int aikitSetDoubleConfig(String str, double d);

    native int aikitSetIntConfig(String str, int i);

    native int aikitSetLogInfo(int i, int i2, String str);

    native int aikitSetLogLevel(int i);

    native int aikitSetLogMode(int i);

    native int aikitSetLogPath(String str);

    native int aikitSetMemoryMode(String str, int i);

    native int aikitSpecifyDataSet(String str, String str2, int[] iArr);

    native int[] aikitStart(String str, long j, int i);

    native int[] aikitStartChat(Object obj, int i, long j);

    native int[] aikitStartNoParam(String str, int i);

    native int aikitUnLoadData(String str, String str2, int i);

    native int aikitWrite(long j, int i);

    native int aikitWriteChat(String str, int i);

    public int asyncChat(ChatParam chatParam, String str, Object obj) {
        if (chatParam == null) {
            return -1;
        }
        return aikitAsyncChat(chatParam, str, genUsrContextId(obj), chatParam.getParamListHandle());
    }

    public int codeC(String str, String str2, CodeType codeType, AudioType audioType, int i) {
        return aiKitCodec(str, str2, codeType.getValue(), audioType.getValue(), i);
    }

    native void delBuilder(long j);

    public int end(AIChatHandle aIChatHandle) {
        if (aIChatHandle == null) {
            return HANDLE_EMPTY;
        }
        this.usrContextMap.remove(aIChatHandle.getUsrContextIndex());
        return aikitEndChat(aIChatHandle.getHandleId(), aIChatHandle.getUsrContextIndex());
    }

    @Override // com.iflytek.sparkchain.core.AiAbilityApi
    public int end(AiHandle aiHandle) {
        if (this.isRecord.get()) {
            this.isRecord.set(false);
            RecorderHelper.getInst().stop();
            return 0;
        }
        if (aiHandle == null) {
            return HANDLE_EMPTY;
        }
        this.usrContextMap.remove(aiHandle.getI());
        return aikitEnd(aiHandle.getId(), aiHandle.getI());
    }

    @Override // com.iflytek.sparkchain.core.AiAbilityApi
    public int engineInit(String str) {
        return aikitEngineInitNoParams(str);
    }

    @Override // com.iflytek.sparkchain.core.AiAbilityApi
    public int engineInit(String str, AiRequest aiRequest) {
        return aikitEngineInit(str, aiRequest.getHandle());
    }

    @Deprecated
    public int engineInitNoParams(String str) {
        return engineInit(str);
    }

    @Override // com.iflytek.sparkchain.core.AiAbilityApi
    public int engineUnInit(String str) {
        return aikitEngineUnInit(str);
    }

    public int freeAbility(String str) {
        return aikitFreeAbility(str);
    }

    public JniCallback.JniAuthTimer getAuthLeftTime(String str) {
        return aikitGetAuthLeftTime(str);
    }

    public ChatListener getChatListener() {
        return this.chatListener;
    }

    native int getContextId(int i);

    public String getDeviceID() {
        return Auth.m510c().m517b();
    }

    public Handler getHandler() {
        return handler;
    }

    public String getLibraryPath(String str) {
        return JniCallback.m525gp(str);
    }

    public AiResponseListener getListener() {
        return this.listener;
    }

    protected Object getUsrContext(int i) {
        return this.usrContextMap.get(i);
    }

    native void header(long j);

    @Deprecated
    public void initPlayer(Context context) {
        PcmPlayerHelper.getInst().initPlayer(context);
    }

    native void inputClear(long j);

    @Override // com.iflytek.sparkchain.core.AiAbilityApi
    public int loadData(String str, AiRequest aiRequest) {
        return aikitLoadData(str, aiRequest.getHandle());
    }

    native void newBoolean(long j, String str, boolean z);

    native void newBuffer(long j, String str, byte[] bArr, int i, int i2, int i3);

    native void newBuilder(long j, String str, long j2);

    native void newCustom(long j, String str, byte[] bArr, int i, int i2, int i3);

    native void newDesc(long j, String str, long j2);

    native void newDirectBuffer(long j, String str, ByteBuffer byteBuffer, int i, int i2, int i3, int i4);

    native void newDouble(long j, String str, double d);

    native void newInteger(long j, String str, int i);

    @Override // com.iflytek.sparkchain.core.AiAbilityApi
    public int oneShot(String str, AiRequest aiRequest, AiRequest aiRequest2, Object obj) {
        return aikitOneShot(str, aiRequest.getHandle(), aiRequest2.getHandle(), genUsrContextId(obj));
    }

    @Override // com.iflytek.sparkchain.core.AiAbilityApi
    public int oneShot(String str, AiRequest aiRequest, Object obj) {
        return aikitOneShot(str, aiRequest.getHandle(), aiRequest.getHandle(), genUsrContextId(obj));
    }

    @Override // com.iflytek.sparkchain.core.AiAbilityApi
    public int oneShotAsync(String str, AiRequest aiRequest) {
        return aikitOneShotAsync(str, aiRequest.getHandle());
    }

    @Override // com.iflytek.sparkchain.core.AiAbilityApi
    public AiOutput oneShotSync(String str, AiRequest aiRequest, Object obj) {
        JniCallback.JniOutput jniOutputAikitOneShotSync = aikitOneShotSync(str, aiRequest.getHandle(), genUsrContextId(obj));
        if (jniOutputAikitOneShotSync == null) {
            return null;
        }
        AiOutput aiOutput = new AiOutput();
        int i = jniOutputAikitOneShotSync.code;
        aiOutput.code = i;
        if (i == 0) {
            aiOutput.data = jniOutputAikitOneShotSync.formatData();
        }
        return aiOutput;
    }

    native void paramClear(long j);

    @Deprecated
    public void pausePlay() {
        PcmPlayerHelper.getInst().pause();
    }

    @Override // com.iflytek.sparkchain.core.AiAbilityApi
    public int preProcess(String str, AiRequest aiRequest) {
        return aikitPreProcess(str, aiRequest.getHandle());
    }

    @Deprecated
    public void rePercent(int i) {
        PcmPlayerHelper.getInst().resetPercent(i);
    }

    @Override // com.iflytek.sparkchain.core.AiAbilityApi
    public int read(String str, AiHandle aiHandle) {
        return aiHandle == null ? HANDLE_EMPTY : aikitRead(str, aiHandle.getId());
    }

    @Override // com.iflytek.sparkchain.core.AiAbilityApi
    public AiOutput readSync(String str, AiHandle aiHandle) {
        AiOutput aiOutput = new AiOutput();
        if (aiHandle == null) {
            aiOutput.code = HANDLE_EMPTY;
            return aiOutput;
        }
        JniCallback.JniOutput jniOutputAikitReadSync = aikitReadSync(str, aiHandle.getId());
        if (jniOutputAikitReadSync.code == 0) {
            aiOutput.data = jniOutputAikitReadSync.formatData();
        }
        return aiOutput;
    }

    public AiHandle record(final String str, final AiHandle aiHandle, final RecorderHelper.Params params, final String str2, Object obj) {
        this.isRecord.set(true);
        RecorderHelper.getInst().init(params, new InterfaceC2219b() { // from class: com.iflytek.sparkchain.core.AiHelper.2
            @Override // com.iflytek.sparkchain.media.record.InterfaceC2219b
            public void onBuffer(byte[] bArr, int i, int i2) {
                AiRequest.Builder builder = AiRequest.builder();
                builder.dataStatus(DataStatus.BEGIN);
                builder.audio(str2, bArr);
                int iWrite = AiHelper.this.write(builder.build(), aiHandle);
                if (iWrite != 0) {
                    Log.d("AIKIT", "write failure " + iWrite);
                    if (params.getListener() != null) {
                        params.getListener().onError(AiHelper.HANDLE_EMPTY, "write failure " + iWrite);
                        return;
                    }
                    return;
                }
                int i3 = AiHelper.this.read(str, aiHandle);
                if (i3 != 0) {
                    Log.d("AIKIT", "read failure " + i3);
                    if (params.getListener() != null) {
                        params.getListener().onError(i3, "read failure " + i3);
                    }
                }
            }

            @Override // com.iflytek.sparkchain.media.record.InterfaceC2219b
            public void onDecibel(int i) {
                if (params.getListener() != null) {
                    params.getListener().onDecibel(i);
                }
            }

            @Override // com.iflytek.sparkchain.media.record.InterfaceC2219b
            public void onError(SpeechError speechError) {
                AiHelper.this.isRecord.set(false);
                Log.d("AIKIT", "record failure " + speechError.toString());
                if (params.getListener() != null) {
                    params.getListener().onError(speechError.getErrorCode(), "record failure " + speechError.toString());
                }
            }

            @Override // com.iflytek.sparkchain.media.record.InterfaceC2219b
            public void onRelease() {
                if (aiHandle == null) {
                    Log.d("AIKIT", "end failure 8305");
                    if (params.getListener() != null) {
                        params.getListener().onError(AiHelper.HANDLE_EMPTY, "end failure 8305");
                        return;
                    }
                    return;
                }
                AiHelper.this.usrContextMap.remove(aiHandle.getI());
                int iEnd = AiHelper.this.end(aiHandle);
                if (iEnd != 0) {
                    Log.d("AIKIT", "end failure " + iEnd);
                    if (params.getListener() != null) {
                        params.getListener().onError(iEnd, "end failure " + iEnd);
                    }
                }
            }

            @Override // com.iflytek.sparkchain.media.record.InterfaceC2219b
            public void onStart(boolean z) {
            }
        });
        return aiHandle;
    }

    public AiHandle record(String str, final AiHandle aiHandle, final PcmRecorder.Builder builder, final AiRequest.Builder builder2, final String str2, Object obj) {
        this.isRecord.set(true);
        IFlyAudioManager.getInst().initPcmRecorder(builder, new PcmRecorder.PcmRecordListener() { // from class: com.iflytek.sparkchain.core.AiHelper.1
            private int index = 0;
            private byte[] endBuffer = new byte[0];

            @Override // com.iflytek.sparkchain.core.media.record.PcmRecorder.PcmRecordListener
            public void onDecibel(int i) {
                if (builder.getDecibelListener() != null) {
                    builder.getDecibelListener().onDecibel(i);
                }
            }

            @Override // com.iflytek.sparkchain.core.media.record.PcmRecorder.PcmRecordListener
            public void onError(com.iflytek.sparkchain.core.media.speech.SpeechError speechError) {
                AiHelper.this.isRecord.set(false);
                Log.d("AEE", "record failure " + speechError.toString());
                if (builder.getDecibelListener() != null) {
                    builder.getDecibelListener().onError(speechError.getErrorCode(), "record failure " + speechError.toString());
                }
            }

            @Override // com.iflytek.sparkchain.core.media.record.PcmRecorder.PcmRecordListener
            public void onRecordBuffer(byte[] bArr, int i, int i2) {
                int i3;
                if (this.index > 0) {
                    builder2.dataStatus(DataStatus.CONTINUE);
                    i3 = 1;
                } else {
                    builder2.dataStatus(DataStatus.BEGIN);
                    i3 = 0;
                }
                builder2.audio(str2, bArr);
                builder2.param(NotificationCompat.CATEGORY_STATUS, i3);
                AiRequest.Builder builder3 = builder2;
                builder3.desc("audio", builder3);
                int iWrite = AiHelper.this.write(builder2.build(), aiHandle);
                if (iWrite == 0) {
                    this.endBuffer = bArr;
                    this.index++;
                } else {
                    Log.d("AEE", "write failure " + iWrite + "," + this.index);
                    if (builder.getDecibelListener() != null) {
                        builder.getDecibelListener().onError(iWrite, "write failure " + iWrite);
                    }
                }
            }

            @Override // com.iflytek.sparkchain.core.media.record.PcmRecorder.PcmRecordListener
            public void onRecordReleased() {
                if (aiHandle == null) {
                    Log.d("AEE", "end failure 8305");
                    if (builder.getDecibelListener() != null) {
                        builder.getDecibelListener().onError(AiHelper.HANDLE_EMPTY, "end failure 8305");
                        return;
                    }
                    return;
                }
                Log.d("AEE", "record onRecordReleased:" + this);
                builder2.dataStatus(DataStatus.END);
                builder2.audio(str2, this.endBuffer);
                builder2.param(NotificationCompat.CATEGORY_STATUS, 2);
                AiRequest.Builder builder3 = builder2;
                builder3.desc("audio", builder3);
                int iWrite = AiHelper.this.write(builder2.build(), aiHandle);
                if (iWrite == 0) {
                    Log.v("AEE", "record " + this.index + " write成功:" + this);
                    return;
                }
                Log.d("AEE", "最后一帧 failure " + iWrite);
                if (builder.getDecibelListener() != null) {
                    builder.getDecibelListener().onError(iWrite, "最后一帧 failure " + iWrite);
                }
            }

            @Override // com.iflytek.sparkchain.core.media.record.PcmRecorder.PcmRecordListener
            public void onRecordStarted(boolean z) {
                Log.d("AEE", "record onRecordStarted:" + this);
            }
        });
        return aiHandle;
    }

    @Override // com.iflytek.sparkchain.core.AiAbilityApi
    @Deprecated
    public AiHandle record(String str, AiHandle aiHandle, PcmRecorder.Builder builder, String str2, Object obj) {
        return record(str, aiHandle, RecorderHelper.genFrom(builder), str2, obj);
    }

    public void registerChatListener(ChatListener chatListener) {
        this.usrChatListener = chatListener;
    }

    @Override // com.iflytek.sparkchain.core.BaseLibrary
    public void registerListener(AiResponseListener aiResponseListener) {
        Log.i("aikitLog", "AiHelper.registerListener：" + aiResponseListener);
        this.userListener = aiResponseListener;
    }

    public void registerListener(AuthListener authListener) {
        Auth.m510c().m515a(authListener);
    }

    public void registerListener(AuthListener authListener, AiResponseListener aiResponseListener) {
        registerListener(authListener);
        registerListener(aiResponseListener);
    }

    public void registerListener(String str, AiListener aiListener) {
        this.listeners.put(str, aiListener);
    }

    @Deprecated
    public void resetBuffer(int i) {
        PcmPlayerHelper.getInst().resetBuffer(i);
    }

    @Deprecated
    public void resumePlay() {
        PcmPlayerHelper.getInst().resume();
    }

    native void service(long j, String str);

    native void serviceParam(long j, String str, long j2);

    public int setAbilityUrl(String str, String str2) {
        try {
            return setConfig("AseUrl", new AiUrl.Builder().url(str2).build().toJson(str));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return 0;
        }
    }

    public int setConfig(String str, double d) {
        return aikitSetDoubleConfig(str, d);
    }

    public int setConfig(String str, int i) {
        return aikitSetIntConfig(str, i);
    }

    public int setConfig(String str, String str2) {
        return aikitSetConfig(str, str2);
    }

    public int setConfig(String str, boolean z) {
        return aikitSetBoolConfig(str, z);
    }

    public int setLogInfo(LogLvl logLvl, int i, String str) {
        return aikitSetLogInfo(logLvl.getValue(), i, str);
    }

    public int setLogLevel(LogLvl logLvl) {
        return aikitSetLogLevel(logLvl.getValue());
    }

    public int setLogMode(int i) {
        return aikitSetLogMode(i);
    }

    public int setLogPath(String str) {
        return aikitSetLogPath(str);
    }

    public int setMemoryMode(String str, int i) {
        return aikitSetMemoryMode(str, i);
    }

    @Override // com.iflytek.sparkchain.core.AiAbilityApi
    public int specifyDataSet(String str, String str2, int[] iArr) {
        return aikitSpecifyDataSet(str, str2, iArr);
    }

    public AIChatHandle start(ChatParam chatParam, Object obj) {
        if (chatParam == null) {
            return null;
        }
        long paramListHandle = chatParam.getParamListHandle();
        int iGenUsrContextId = genUsrContextId(obj);
        int[] iArrAikitStartChat = aikitStartChat(chatParam, iGenUsrContextId, paramListHandle);
        if (iArrAikitStartChat[0] != 0) {
            return null;
        }
        AIChatHandle aIChatHandle = new AIChatHandle(obj, "AIKit-Spark", iArrAikitStartChat[1]);
        aIChatHandle.setUsrContextIndex(iGenUsrContextId);
        return aIChatHandle;
    }

    @Override // com.iflytek.sparkchain.core.AiAbilityApi
    public AiHandle start(String str, AiRequest aiRequest, Object obj) {
        int iGenUsrContextId = genUsrContextId(obj);
        int[] iArrAikitStart = aikitStart(str, aiRequest == null ? -1L : aiRequest.getHandle(), iGenUsrContextId);
        return new AiHandle(iArrAikitStart[0], iArrAikitStart[1], iGenUsrContextId);
    }

    @Override // com.iflytek.sparkchain.core.AiAbilityApi
    public AiHandle start(String str, Object obj) {
        int iGenUsrContextId = genUsrContextId(obj);
        int[] iArrAikitStartNoParam = aikitStartNoParam(str, iGenUsrContextId);
        return new AiHandle(iArrAikitStartNoParam[0], iArrAikitStartNoParam[1], iGenUsrContextId);
    }

    @Deprecated
    public int startPlay(PcmPlayer.PcmPlayerListener pcmPlayerListener) {
        return PcmPlayerHelper.getInst().startPlay(pcmPlayerListener);
    }

    @Deprecated
    public void stopPlay() {
        PcmPlayerHelper.getInst().stop();
    }

    public void stopRecord() {
        if (this.isRecord.get()) {
            this.isRecord.set(false);
            RecorderHelper.getInst().stop();
        }
    }

    native void ttsDestroy(int i);

    public void ttsObjDestroy(final int i) {
        handler.postDelayed(new Runnable() { // from class: com.iflytek.sparkchain.core.AiHelper.5
            @Override // java.lang.Runnable
            public void run() {
                Log.v("AEE", "ttsDestroy:sid:" + i);
                AiHelper.this.ttsDestroy(i);
            }
        }, 1000L);
    }

    @Override // com.iflytek.sparkchain.core.AiAbilityApi
    public int unLoadData(String str, String str2, int i) {
        return aikitUnLoadData(str, str2, i);
    }

    public int write(AIChatHandle aIChatHandle, String str) {
        return aIChatHandle == null ? HANDLE_EMPTY : aikitWriteChat(str, aIChatHandle.getHandleId());
    }

    @Override // com.iflytek.sparkchain.core.AiAbilityApi
    public int write(AiRequest aiRequest, AiHandle aiHandle) {
        return aiHandle == null ? HANDLE_EMPTY : aikitWrite(aiRequest.getHandle(), aiHandle.getId());
    }

    @Deprecated
    public void writeDateToPlayer(ArrayList<byte[]> arrayList, int i, int i2) {
        PcmPlayerHelper.getInst().write(arrayList, i, i2);
    }
}
