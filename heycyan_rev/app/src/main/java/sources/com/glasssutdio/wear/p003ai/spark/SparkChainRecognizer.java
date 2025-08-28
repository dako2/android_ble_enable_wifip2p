package com.glasssutdio.wear.p003ai.spark;

import android.os.Handler;
import android.os.Looper;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.GlassApplication;
import com.glasssutdio.wear.all.Localization;
import com.glasssutdio.wear.all.utils.DateUtil;
import com.glasssutdio.wear.all.utils.GFileUtilKt;
import com.glasssutdio.wear.depository.AiChatDepository;
import com.glasssutdio.wear.p003ai.spark.AudioTrackManager;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import com.iflytek.sparkchain.core.asr.ASR;
import com.iflytek.sparkchain.core.asr.AsrCallbacks;
import com.iflytek.sparkchain.core.asr.AudioAttributes;
import com.iflytek.sparkchain.core.asr.RegionType;
import com.iflytek.sparkchain.core.asr.Segment;
import com.iflytek.sparkchain.core.asr.Transcription;
import com.iflytek.sparkchain.core.asr.Vad;
import com.iflytek.sparkchain.core.rtasr.RTASR;
import com.iflytek.sparkchain.core.rtasr.RTASRCallbacks;
import com.iflytek.sparkchain.core.tts.OnlineTTS;
import com.iflytek.sparkchain.core.tts.TTS;
import com.iflytek.sparkchain.core.tts.TTSCallbacks;
import com.jieli.jl_audio_decode.callback.OnDecodeStreamCallback;
import com.jieli.jl_audio_decode.opus.OpusManager;
import com.jieli.jl_audio_decode.opus.model.OpusOption;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.ILargeDataResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.resp.AiChatResponse;
import com.oudmon.ble.base.communication.bigData.resp.GlassModelControlResponse;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class SparkChainRecognizer implements AudioTrackManager.MyAudioCallback {
    public static int GPT = 0;
    public static int TRANSLATE = 1;
    public static int TRANSLATE_ONE_TO_ONE = 2;
    private static SparkChainRecognizer instance;
    private ASR asr;
    private OnlineTTS mOnlineTTS;
    private OpusManager mOpusManager;
    public RTASR mRTASR;
    private PlayStateCallback playStateCallback;
    private RecordArsCallback recordArsCallback;
    private ScheduledFuture<?> timeoutFuture;
    private String translateLn;
    private TTSResultCallback ttsResultCallback;
    private Runnable ttsTimeoutRunnable;
    private String currTime = new DateUtil().getTimestamp() + "";
    String fileName = this.currTime + ".pcm";
    String fileNameOpus = this.currTime + ".opus";
    String parent = GFileUtilKt.getGPTDirFile().getAbsolutePath();
    OpusOption opusOption = new OpusOption();
    private SimpleTimer heartbeatTimer = new SimpleTimer();
    private SimpleTimer timeOutTimer = new SimpleTimer();
    private final Handler handler = new Handler(Looper.getMainLooper());
    public int voiceType = 0;
    private String translateFrom = "cn";
    private String translateTo = "en";
    private String aiLanguage = "en_us";
    boolean traditional = false;
    RTASRCallbacks rtasrCallbacks = new RTASRCallbacks() { // from class: com.glasssutdio.wear.ai.spark.SparkChainRecognizer.3
        @Override // com.iflytek.sparkchain.core.rtasr.RTASRCallbacks
        public void onBeginOfSpeech() {
        }

        @Override // com.iflytek.sparkchain.core.rtasr.RTASRCallbacks
        public void onEndOfSpeech() {
        }

        @Override // com.iflytek.sparkchain.core.rtasr.RTASRCallbacks
        public void onResult(RTASR.RtAsrResult result, Object o) {
            String data = result.getData();
            result.getRawResult();
            int status = result.getStatus();
            String sid = result.getSid();
            result.getTransResult().getSrc();
            String dst = result.getTransResult().getDst();
            result.getTransResult().getStatus();
            if (data.isEmpty()) {
                return;
            }
            if (SparkChainRecognizer.this.voiceType == SparkChainRecognizer.TRANSLATE) {
                AiChatDepository.INSTANCE.getGetInstance().realTimeTranslate(status, data, data, dst, status == 3);
            } else {
                if (SparkChainRecognizer.this.voiceType != SparkChainRecognizer.TRANSLATE_ONE_TO_ONE || SparkChainRecognizer.this.recordArsCallback == null) {
                    return;
                }
                XLog.m137i("RTAS回调：" + status + " =" + data + ",译文：" + dst);
                SparkChainRecognizer.this.recordArsCallback.recordArsResult(status, data, dst, true, sid);
            }
        }

        @Override // com.iflytek.sparkchain.core.rtasr.RTASRCallbacks
        public void onError(RTASR.RtAsrError rtAsrError, Object o) {
            XLog.m137i("RTASR onError:" + rtAsrError.getErrMsg());
        }
    };
    private String euLast = "";
    AsrCallbacks mAsrCallbacks = new AsrCallbacks() { // from class: com.glasssutdio.wear.ai.spark.SparkChainRecognizer.4
        @Override // com.iflytek.sparkchain.core.asr.AsrCallbacks
        public void onBeginOfSpeech() {
        }

        @Override // com.iflytek.sparkchain.core.asr.AsrCallbacks
        public void onEndOfSpeech() {
        }

        @Override // com.iflytek.sparkchain.core.asr.AsrCallbacks
        public void onResult(ASR.ASRResult asrResult, Object o) {
            String bestMatchText = asrResult.getBestMatchText();
            int status = asrResult.getStatus();
            asrResult.getSid();
            List<Vad> vads = asrResult.getVads();
            List<Transcription> transcriptions = asrResult.getTranscriptions();
            int i = -1;
            int end = -1;
            for (Vad vad : vads) {
                int begin = vad.getBegin();
                end = vad.getEnd();
                i = begin;
            }
            Iterator<Transcription> it = transcriptions.iterator();
            String text = null;
            while (it.hasNext()) {
                Iterator<Segment> it2 = it.next().getSegments().iterator();
                while (it2.hasNext()) {
                    text = it2.next().getText();
                }
            }
            XLog.m137i("onAsrResult:{result:" + bestMatchText + ",status:" + status + ",begin:" + i + ",end:" + end + ",word:" + text + "}");
            int i2 = 2;
            if (SparkChainRecognizer.this.voiceType == SparkChainRecognizer.GPT) {
                SparkChainRecognizer sparkChainRecognizer = SparkChainRecognizer.this;
                if (!sparkChainRecognizer.m167eu(sparkChainRecognizer.aiLanguage)) {
                    i2 = 1;
                } else if (bestMatchText.equals(SparkChainRecognizer.this.euLast) || status == 2) {
                    SparkChainRecognizer.this.euLast = "";
                    i2 = 1;
                } else {
                    SparkChainRecognizer.this.euLast = bestMatchText;
                }
                if (bestMatchText.isEmpty() || status < i2) {
                    return;
                }
                LargeDataHandler.getInstance().glassesControl(new byte[]{2, 1, 11}, new ILargeDataResponse<GlassModelControlResponse>() { // from class: com.glasssutdio.wear.ai.spark.SparkChainRecognizer.4.1
                    @Override // com.oudmon.ble.base.communication.ILargeDataResponse
                    public void parseData(int cmdType, GlassModelControlResponse response) {
                    }
                });
                SparkChainRecognizer.this.timeOutTimer.stop();
                SparkChainRecognizer.this.stop();
                AiChatDepository.INSTANCE.getGetInstance().saveChatFromSparkChain(bestMatchText);
                AiChatDepository.INSTANCE.getGetInstance().chatGpt(1, bestMatchText, "");
                return;
            }
            if (SparkChainRecognizer.this.voiceType == SparkChainRecognizer.TRANSLATE && status >= 1) {
                if (bestMatchText.isEmpty()) {
                    return;
                }
                AiChatDepository.INSTANCE.getGetInstance().translate(bestMatchText, status);
            } else {
                if (SparkChainRecognizer.this.voiceType != SparkChainRecognizer.TRANSLATE_ONE_TO_ONE || status != 2 || bestMatchText.isEmpty() || SparkChainRecognizer.this.recordArsCallback == null) {
                    return;
                }
                XLog.m137i("语音转文字:" + bestMatchText);
                SparkChainRecognizer.this.recordArsCallback.recordArsResult(status, bestMatchText, "", false, "666");
            }
        }

        @Override // com.iflytek.sparkchain.core.asr.AsrCallbacks
        public void onError(ASR.ASRError asrError, Object o) {
            XLog.m137i(asrError.getCode() + "---" + asrError.getErrMsg() + HelpFormatter.DEFAULT_LONG_OPT_PREFIX + asrError.getSid());
        }
    };
    private byte[] buffer = new byte[1280];
    private int currentIndex = 0;
    private boolean errorRetry = false;
    private byte[] opusBuffer = new byte[EMachine.EM_M32C];
    private int opusIndex = 0;
    private final Queue<String> ttsTextQueue = new ConcurrentLinkedQueue();
    private Handler ttsHandler = new Handler(Looper.getMainLooper());
    private final long TTS_TIMEOUT_MS = 4000;
    private boolean isRunning = false;
    TTSCallbacks mTTSCallback = new TTSCallbacks() { // from class: com.glasssutdio.wear.ai.spark.SparkChainRecognizer.6
        @Override // com.iflytek.sparkchain.core.tts.TTSCallbacks
        public void onResult(TTS.TTSResult result, Object o) throws IllegalStateException, InterruptedException {
            byte[] data = result.getData();
            result.getLen();
            int status = result.getStatus();
            result.getCed();
            String sid = result.getSid();
            String str = (String) SparkChainRecognizer.this.ttsTextQueue.peek();
            XLog.m137i("------回调 sid=" + sid + " 对应 text=" + str + " status=" + status);
            AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).startPlay(result.getSid(), str);
            AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).feedPCMDataWithSid(data, result.getSid(), str);
            if (SparkChainRecognizer.this.ttsResultCallback != null) {
                SparkChainRecognizer.this.ttsResultCallback.ttsResult(result.getStatus());
            }
            if (result.getStatus() == 2) {
                SparkChainRecognizer.this.cancelTTSTimeout();
                SparkChainRecognizer.this.ttsTextQueue.poll();
                SparkChainRecognizer.this.runNextTTS();
            }
        }

        @Override // com.iflytek.sparkchain.core.tts.TTSCallbacks
        public void onError(TTS.TTSError ttsError, Object o) {
            AiChatDepository.INSTANCE.getGetInstance().startRealTimeTTS();
            XLog.m137i("onError:errCode:" + ttsError.getCode() + ",errMsg:" + ttsError.getErrMsg());
        }
    };
    private final ILargeDataResponse<AiChatResponse> aiChatListener = new ILargeDataResponse<AiChatResponse>() { // from class: com.glasssutdio.wear.ai.spark.SparkChainRecognizer.7
        @Override // com.oudmon.ble.base.communication.ILargeDataResponse
        public void parseData(int cmdType, AiChatResponse response) throws InterruptedException {
            if (cmdType == 89) {
                SparkChainRecognizer.this.receiveOpusData(Arrays.copyOfRange(response.getSubData(), 6, response.getSubData().length));
            }
        }
    };
    Runnable playHeart = new Runnable() { // from class: com.glasssutdio.wear.ai.spark.SparkChainRecognizer.8
        @Override // java.lang.Runnable
        public void run() {
            LargeDataHandler.getInstance().aiVoicePlay(2, null);
            SparkChainRecognizer.this.handler.postDelayed(SparkChainRecognizer.this.playHeart, 2000L);
        }
    };

    public interface PlayStateCallback {
        void audioPlaying(String sid, String text);

        void endAudio(String sid, boolean complete);

        void startAudio(String sid, String text);
    }

    public interface RecordArsCallback {
        void recordArsResult(int status, String content, String dest, boolean isRealTime, String sid);
    }

    public interface TTSResultCallback {
        void ttsResult(int status);
    }

    public boolean supportRealTimeASR(String language) {
        return true;
    }

    public void setTraditional(boolean traditional) {
        this.traditional = traditional;
    }

    public void setTTSResultCallback(TTSResultCallback ttsResultCallback) {
        this.ttsResultCallback = ttsResultCallback;
    }

    public void setAiLanguage(String aiLanguage) {
        this.aiLanguage = aiLanguage;
    }

    public void setTranslate(String translateFrom, String translateTo) {
        this.translateFrom = translateFrom;
        this.translateTo = translateTo;
        XLog.m137i("translateFrom=" + translateFrom + ",translateTo=" + translateTo);
        setAiLanguage(translateFrom);
    }

    public void setTranslateTo(String translateTo) {
        XLog.m137i("translateTo=" + translateTo);
        this.translateTo = translateTo;
        if (this.mOnlineTTS == null) {
            initStartTTS();
            return;
        }
        if (translateTo.equalsIgnoreCase("cn") || translateTo.startsWith("zh")) {
            this.mOnlineTTS.vcn("x_xiaoyan");
            return;
        }
        if (translateTo.equalsIgnoreCase("en")) {
            this.mOnlineTTS.vcn("x_Catherine");
            return;
        }
        if (translateTo.equalsIgnoreCase(Localization.language)) {
            this.mOnlineTTS.vcn("mariane");
            return;
        }
        if (translateTo.equalsIgnoreCase("de")) {
            this.mOnlineTTS.vcn("leonie");
            return;
        }
        if (translateTo.equalsIgnoreCase("es")) {
            this.mOnlineTTS.vcn("x2_spes_aurora");
            return;
        }
        if (translateTo.equalsIgnoreCase("ja")) {
            this.mOnlineTTS.vcn("x2_zhongcun");
            return;
        }
        if (translateTo.equalsIgnoreCase("it")) {
            this.mOnlineTTS.vcn("x2_ItIt_Anna");
            return;
        }
        if (translateTo.equalsIgnoreCase("hi")) {
            this.mOnlineTTS.vcn("x2_hiin_mohita");
            return;
        }
        if (translateTo.equalsIgnoreCase("ko")) {
            this.mOnlineTTS.vcn("x2_KoKr_Miya");
            return;
        }
        if (translateTo.equalsIgnoreCase("th")) {
            this.mOnlineTTS.vcn("x2_thth_suparut");
            return;
        }
        if (translateTo.equalsIgnoreCase("ru")) {
            this.mOnlineTTS.vcn("x2_ruru_keshu");
            return;
        }
        if (translateTo.equalsIgnoreCase("vi")) {
            this.mOnlineTTS.vcn("x2_vivn_thuhien");
            return;
        }
        if (translateTo.equalsIgnoreCase("ms")) {
            this.mOnlineTTS.vcn("x2_msmy_hashim");
            return;
        }
        if (translateTo.equalsIgnoreCase(BreakpointSQLiteKey.f521ID)) {
            this.mOnlineTTS.vcn("x2_idid_kris");
            return;
        }
        if (translateTo.equalsIgnoreCase("el")) {
            this.mOnlineTTS.vcn("x2_ElGr_Dimitra");
            return;
        }
        if (translateTo.equalsIgnoreCase("cs")) {
            this.mOnlineTTS.vcn("x2_CsCz_Petra");
            return;
        }
        if (translateTo.equalsIgnoreCase("ro")) {
            this.mOnlineTTS.vcn("x2_RoRo_Miruna");
            return;
        }
        if (translateTo.equalsIgnoreCase("sv")) {
            this.mOnlineTTS.vcn("x2_SvSe_Michaela");
            return;
        }
        if (translateTo.equalsIgnoreCase("nl")) {
            this.mOnlineTTS.vcn("x2_NlNl_Robin");
            return;
        }
        if (translateTo.equalsIgnoreCase("pl")) {
            this.mOnlineTTS.vcn("x2_PlPl_Malgorzata");
            return;
        }
        if (translateTo.equalsIgnoreCase("pt")) {
            this.mOnlineTTS.vcn("x2_PtPt_Pedro");
            return;
        }
        if (translateTo.equalsIgnoreCase("ar")) {
            this.mOnlineTTS.vcn("x2_aren_rania");
            return;
        }
        if (translateTo.equalsIgnoreCase("fa")) {
            this.mOnlineTTS.vcn("x2_FaIr_Saheli");
            return;
        }
        if (translateTo.equalsIgnoreCase("ur")) {
            this.mOnlineTTS.vcn("x2_urpk_noreen");
        } else if (translateTo.equalsIgnoreCase("tr")) {
            this.mOnlineTTS.vcn("x2_trtr_ersoy");
        } else {
            this.mOnlineTTS.vcn("x_xiaoyan");
        }
    }

    public String switchRTAsrLanguage(String appLanguage) {
        if (appLanguage.startsWith("zh") || appLanguage.startsWith("cn")) {
            return "zh_cn";
        }
        if (appLanguage.startsWith("en")) {
            return "en_us";
        }
        if (appLanguage.startsWith(Localization.language)) {
            return "fr_fr";
        }
        if (appLanguage.startsWith("de")) {
            return "de_DE";
        }
        if (appLanguage.startsWith("ja")) {
            return "ja_jp";
        }
        if (appLanguage.startsWith("ko")) {
            return "ko_kr";
        }
        if (appLanguage.startsWith("es")) {
            return "es_es";
        }
        if (appLanguage.startsWith("pt")) {
            return "pt_PT";
        }
        if (appLanguage.startsWith("ru")) {
            return "ru_ru";
        }
        if (appLanguage.startsWith("ar")) {
            return "ar_il";
        }
        if (appLanguage.startsWith("hi")) {
            return "hi_IN";
        }
        if (appLanguage.startsWith("th")) {
            return "th_TH";
        }
        if (appLanguage.startsWith(BreakpointSQLiteKey.f521ID)) {
            return "id_ID";
        }
        if (appLanguage.startsWith("it")) {
            return "it_IT";
        }
        if (appLanguage.startsWith("vi")) {
            return "vi_VN";
        }
        if (appLanguage.startsWith("ms")) {
            return "ms_MY";
        }
        if (appLanguage.startsWith("tr")) {
            return "tr_TR";
        }
        if (appLanguage.startsWith("fa")) {
            return "fa_IR";
        }
        if (appLanguage.startsWith("pl")) {
            return "pl_PL";
        }
        if (appLanguage.startsWith("nl")) {
            return "nl_NL";
        }
        if (appLanguage.startsWith("sv")) {
            return "sv_SE";
        }
        if (appLanguage.startsWith("cs")) {
            return "cs_CZ";
        }
        if (appLanguage.startsWith("ro")) {
            return "ro_RO";
        }
        if (appLanguage.startsWith("ur")) {
            return "ur_IN";
        }
        if (appLanguage.startsWith("hu")) {
            return "hu_hu";
        }
        return appLanguage.startsWith("el") ? "el_GR" : "en_us";
    }

    public int getVoiceType() {
        return this.voiceType;
    }

    public void setVoiceType(int voiceType) {
        this.voiceType = voiceType;
    }

    public PlayStateCallback getPlayStateCallback() {
        return this.playStateCallback;
    }

    public void setTranslateLn(String translateLn) {
        this.translateLn = translateLn;
    }

    public void setPlayStateCallback(PlayStateCallback playStateCallback) {
        this.playStateCallback = playStateCallback;
    }

    private SparkChainRecognizer() {
        setPlayAudioCallback();
    }

    public static SparkChainRecognizer getInstance() {
        SparkChainRecognizer sparkChainRecognizer;
        SparkChainRecognizer sparkChainRecognizer2 = instance;
        if (sparkChainRecognizer2 != null) {
            return sparkChainRecognizer2;
        }
        synchronized (SparkChainRecognizer.class) {
            if (instance == null) {
                instance = new SparkChainRecognizer();
            }
            sparkChainRecognizer = instance;
        }
        return sparkChainRecognizer;
    }

    public void setPlayAudioCallback() {
        AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).setAudioCallback(this);
    }

    public void initData() {
        try {
            this.timeOutTimer.stop();
            AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).setAudioCallback(this);
            if (this.voiceType == GPT || !supportRealTimeASR(this.translateFrom)) {
                ASR asr = this.asr;
                if (asr != null) {
                    asr.stop(true);
                }
                XLog.m137i("asr aiLanguage：" + this.aiLanguage + "-----" + m167eu(this.aiLanguage));
                if (m167eu(this.aiLanguage)) {
                    ASR asr2 = this.asr;
                    if (asr2 == null) {
                        this.asr = new ASR(this.aiLanguage, "iat", "mandarin", RegionType.EU_TYPE);
                    } else {
                        asr2.language(this.aiLanguage);
                    }
                } else {
                    ASR asr3 = this.asr;
                    if (asr3 == null) {
                        this.asr = new ASR(this.aiLanguage, "iat", "mandarin");
                    } else {
                        asr3.language(this.aiLanguage);
                    }
                }
                if (this.traditional) {
                    this.asr.rlang("zh_HK");
                }
                XLog.m137i("asr rlang zh_HK---" + this.traditional);
                this.asr.registerCallbacks(this.mAsrCallbacks);
                AudioAttributes audioAttributes = new AudioAttributes();
                audioAttributes.setSampleRate(16000);
                audioAttributes.setEncoding("raw");
                audioAttributes.setChannels(1);
                int i = this.voiceType;
                if (i == TRANSLATE || i == TRANSLATE_ONE_TO_ONE || i == GPT) {
                    this.asr.vadEos(DownloadTask.Builder.DEFAULT_SYNC_BUFFER_INTERVAL_MILLIS);
                    this.asr.svad(1);
                }
                int iStart = this.asr.start(audioAttributes, null);
                if (iStart != 0) {
                    XLog.m137i("asr start failed" + iStart);
                }
            } else {
                int i2 = this.voiceType;
                if (i2 == TRANSLATE || i2 == TRANSLATE_ONE_TO_ONE) {
                    if (this.mRTASR == null) {
                        this.mRTASR = new RTASR(null, RegionType.EU_TYPE);
                    }
                    this.mRTASR.registerCallbacks(this.rtasrCallbacks);
                    XLog.m137i("----translateFrom" + this.translateFrom);
                    this.mRTASR.lang(switchRTAsrLanguage(this.translateFrom));
                    this.mRTASR.OstAccent("mandarin");
                    this.mRTASR.OstDomain("ist_mul_sp");
                    this.mRTASR.OstDwa("wpgs");
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    int iStart2 = this.mRTASR.start("glasses");
                    if (iStart2 != 0) {
                        XLog.m137i("mRTASR start failed" + iStart2);
                    }
                    XLog.m137i("YY initData 耗时：" + (System.currentTimeMillis() - jCurrentTimeMillis));
                }
            }
            if (this.mOpusManager == null) {
                this.mOpusManager = new OpusManager();
            }
            this.opusOption.setHasHead(false);
            this.opusOption.setSampleRate(16000);
            this.opusOption.setPacketSize(40);
            this.opusOption.setChannel(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() throws Exception {
        AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).restartPlay();
        this.currTime = new DateUtil().getTimestamp() + "";
        this.fileNameOpus = this.currTime + ".opus";
        this.opusIndex = 0;
        GFileUtilKt.createDirs(GFileUtilKt.getGPTDirFile().getAbsolutePath() + "/");
        decodeOpusStream(this.mOpusManager);
        LargeDataHandler.getInstance().initPackageNotify(this.aiChatListener);
        try {
            this.heartbeatTimer.stop();
            this.heartbeatTimer.start(new Runnable() { // from class: com.glasssutdio.wear.ai.spark.SparkChainRecognizer.1
                @Override // java.lang.Runnable
                public void run() {
                    if (BleOperateManager.getInstance().isConnected()) {
                        LargeDataHandler.getInstance().syncHeartBeat(7);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = this.voiceType;
        if (i == GPT) {
            timeoutTask(15);
        } else if (i == TRANSLATE) {
            timeoutTask(60);
        }
    }

    private void decodeOpusStream(OpusManager manager) {
        manager.startDecodeStream(this.opusOption, new OnDecodeStreamCallback() { // from class: com.glasssutdio.wear.ai.spark.SparkChainRecognizer.2
            @Override // com.jieli.jl_audio_decode.callback.OnStateCallback
            public void onStart() {
                XLog.m137i("开始解码");
            }

            @Override // com.jieli.jl_audio_decode.callback.OnStateCallback
            public void onComplete(String outFilePath) {
                XLog.m137i("解码结束 >> " + outFilePath);
            }

            @Override // com.jieli.jl_audio_decode.callback.OnStateCallback
            public void onError(int code, String message) {
                try {
                    XLog.m137i(code + ", " + message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.jieli.jl_audio_decode.callback.OnDecodeStreamCallback
            public void onDecodeStream(byte[] data) {
                SparkChainRecognizer.this.receiveData(data);
            }
        });
    }

    public void cleanBuffer() {
        Arrays.fill(this.buffer, (byte) 0);
        this.currentIndex = 0;
        Arrays.fill(this.opusBuffer, (byte) 0);
        this.opusIndex = 0;
    }

    public void receiveData(byte[] newData) {
        try {
            for (byte b : newData) {
                if (this.currentIndex >= this.buffer.length) {
                    if (this.voiceType == GPT || !supportRealTimeASR(this.translateFrom)) {
                        int iWrite = this.asr.write(this.buffer);
                        if (iWrite == 0) {
                            this.errorRetry = false;
                        } else if (iWrite == 18305 && !this.errorRetry) {
                            resetParams();
                            initData();
                            this.errorRetry = true;
                        }
                    } else {
                        int i = this.voiceType;
                        if (i == TRANSLATE || i == TRANSLATE_ONE_TO_ONE) {
                            this.mRTASR.write(this.buffer);
                        }
                    }
                    this.currentIndex = 0;
                    Arrays.fill(this.buffer, (byte) 0);
                }
                byte[] bArr = this.buffer;
                int i2 = this.currentIndex;
                this.currentIndex = i2 + 1;
                bArr[i2] = b;
            }
        } catch (Exception e) {
            this.buffer = new byte[1280];
            this.currentIndex = 0;
            e.printStackTrace();
        }
    }

    public void receiveOpusData(byte[] newData) throws InterruptedException {
        try {
            for (byte b : newData) {
                byte[] bArr = this.opusBuffer;
                int i = this.opusIndex;
                int i2 = i + 1;
                this.opusIndex = i2;
                bArr[i] = b;
                if (i2 == 120) {
                    this.mOpusManager.writeAudioStream(bArr);
                    Thread.sleep(1L);
                    this.opusIndex = 0;
                    Arrays.fill(this.opusBuffer, (byte) 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        RTASR rtasr;
        int i = this.voiceType;
        if (i == GPT) {
            ASR asr = this.asr;
            if (asr != null) {
                asr.stop(true);
            }
        } else if ((i == TRANSLATE || i == TRANSLATE_ONE_TO_ONE) && (rtasr = this.mRTASR) != null) {
            rtasr.stop();
        }
        this.mOpusManager = null;
        LargeDataHandler.getInstance().removeGptNotify();
        this.currTime = new DateUtil().getTimestamp() + "";
        this.fileNameOpus = this.currTime + ".opus";
    }

    public void stopHeartBeat() {
        try {
            int i = this.voiceType;
            if (i == GPT) {
                ASR asr = this.asr;
                if (asr != null) {
                    asr.stop(false);
                }
            } else if (i == TRANSLATE || i == TRANSLATE_ONE_TO_ONE) {
                if (supportRealTimeASR(this.translateFrom)) {
                    RTASR rtasr = this.mRTASR;
                    if (rtasr != null) {
                        rtasr.stop();
                    }
                } else {
                    ASR asr2 = this.asr;
                    if (asr2 != null) {
                        asr2.stop(false);
                    }
                }
            }
            this.heartbeatTimer.stop();
            OpusManager opusManager = this.mOpusManager;
            if (opusManager != null) {
                opusManager.stopDecodeStream();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initStartTTS() {
        XLog.m137i("translateTo=" + this.translateTo);
        if (this.translateTo.equalsIgnoreCase("cn") || this.translateTo.startsWith("zh")) {
            this.mOnlineTTS = new OnlineTTS("x_xiaoyan");
        } else if (this.translateTo.equalsIgnoreCase("en")) {
            this.mOnlineTTS = new OnlineTTS("x_Catherine");
        } else if (this.translateTo.equalsIgnoreCase(Localization.language)) {
            this.mOnlineTTS = new OnlineTTS("mariane");
        } else if (this.translateTo.equalsIgnoreCase("de")) {
            this.mOnlineTTS = new OnlineTTS("leonie");
        } else if (this.translateTo.equalsIgnoreCase("es")) {
            this.mOnlineTTS = new OnlineTTS("x2_spes_aurora");
        } else if (this.translateTo.equalsIgnoreCase("ja")) {
            this.mOnlineTTS = new OnlineTTS("x2_zhongcun");
        } else if (this.translateTo.equalsIgnoreCase("it")) {
            this.mOnlineTTS = new OnlineTTS("x2_ItIt_Anna");
        } else if (this.translateTo.equalsIgnoreCase("hi")) {
            this.mOnlineTTS = new OnlineTTS("x2_hiin_mohita");
        } else if (this.translateTo.equalsIgnoreCase("ko")) {
            this.mOnlineTTS = new OnlineTTS("x2_KoKr_Miya");
        } else if (this.translateTo.equalsIgnoreCase("th")) {
            this.mOnlineTTS = new OnlineTTS("x2_thth_suparut");
        } else if (this.translateTo.equalsIgnoreCase("ru")) {
            this.mOnlineTTS = new OnlineTTS("x2_ruru_keshu");
        } else if (this.translateTo.equalsIgnoreCase("vi")) {
            this.mOnlineTTS = new OnlineTTS("x2_vivn_thuhien");
        } else if (this.translateTo.equalsIgnoreCase("ms")) {
            this.mOnlineTTS = new OnlineTTS("x2_msmy_hashim");
        } else if (this.translateTo.equalsIgnoreCase(BreakpointSQLiteKey.f521ID)) {
            this.mOnlineTTS = new OnlineTTS("x2_idid_kris");
        } else if (this.translateTo.equalsIgnoreCase("el")) {
            this.mOnlineTTS = new OnlineTTS("x2_ElGr_Dimitra");
        } else if (this.translateTo.equalsIgnoreCase("cs")) {
            this.mOnlineTTS = new OnlineTTS("x2_CsCz_Petra");
        } else if (this.translateTo.equalsIgnoreCase("ro")) {
            this.mOnlineTTS = new OnlineTTS("x2_RoRo_Miruna");
        } else if (this.translateTo.equalsIgnoreCase("sv")) {
            this.mOnlineTTS = new OnlineTTS("x2_SvSe_Michaela");
        } else if (this.translateTo.equalsIgnoreCase("nl")) {
            this.mOnlineTTS = new OnlineTTS("x2_NlNl_Robin");
        } else if (this.translateTo.equalsIgnoreCase("pl")) {
            this.mOnlineTTS = new OnlineTTS("x2_PlPl_Malgorzata");
        } else if (this.translateTo.equalsIgnoreCase("pt")) {
            this.mOnlineTTS = new OnlineTTS("x2_PtPt_Pedro");
        } else if (this.translateTo.equalsIgnoreCase("ar")) {
            this.mOnlineTTS = new OnlineTTS("x2_aren_rania");
        } else if (this.translateTo.equalsIgnoreCase("fa")) {
            this.mOnlineTTS = new OnlineTTS("x2_FaIr_Saheli");
        } else if (this.translateTo.equalsIgnoreCase("ur")) {
            this.mOnlineTTS = new OnlineTTS("x2_urpk_noreen");
        } else if (this.translateTo.equalsIgnoreCase("tr")) {
            this.mOnlineTTS = new OnlineTTS("x2 trtr ersoy");
        } else {
            this.mOnlineTTS = new OnlineTTS("x4_lingxiaolu_en");
        }
        this.mOnlineTTS.speed(50);
        this.mOnlineTTS.pitch(50);
        this.mOnlineTTS.volume(50);
        this.mOnlineTTS.registerCallbacks(this.mTTSCallback);
    }

    public void startTTS(String text) {
        setTranslateTo(this.translateTo);
        this.ttsTextQueue.add(text);
        XLog.m137i("----ttsTextQueue=" + (!this.isRunning));
        if (this.isRunning) {
            return;
        }
        runNextTTS();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runNextTTS() {
        String strPeek = this.ttsTextQueue.peek();
        XLog.m137i("-------------" + strPeek);
        if (strPeek == null || strPeek.equals("null")) {
            this.isRunning = false;
            return;
        }
        this.isRunning = true;
        int iARun = this.mOnlineTTS.aRun(strPeek);
        if (iARun != 0) {
            XLog.m137i("合成出错!ret=" + iARun);
            this.ttsTextQueue.poll();
            runNextTTS();
        } else {
            XLog.m137i("开始合成！ret=" + iARun);
            cancelTTSTimeout();
            Runnable runnable = new Runnable() { // from class: com.glasssutdio.wear.ai.spark.SparkChainRecognizer.5
                @Override // java.lang.Runnable
                public void run() {
                    XLog.m137i("TTS 超时未回调，移除当前 text 并执行下一个");
                    SparkChainRecognizer.this.ttsTextQueue.poll();
                    SparkChainRecognizer.this.runNextTTS();
                }
            };
            this.ttsTimeoutRunnable = runnable;
            this.ttsHandler.postDelayed(runnable, 4000L);
        }
    }

    public void stopTTS() {
        OnlineTTS onlineTTS = this.mOnlineTTS;
        if (onlineTTS != null) {
            onlineTTS.stop();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelTTSTimeout() {
        Runnable runnable = this.ttsTimeoutRunnable;
        if (runnable != null) {
            this.ttsHandler.removeCallbacks(runnable);
            this.ttsTimeoutRunnable = null;
        }
    }

    @Override // com.glasssutdio.wear.ai.spark.AudioTrackManager.MyAudioCallback
    public void startAudio(String sid, String text) {
        XLog.m137i("----------startAudio");
        PlayStateCallback playStateCallback = this.playStateCallback;
        if (playStateCallback != null) {
            playStateCallback.startAudio(sid, text);
        }
        LargeDataHandler.getInstance().aiVoicePlay(1, null);
        this.handler.removeCallbacks(this.playHeart);
        this.handler.postDelayed(this.playHeart, 2000L);
    }

    @Override // com.glasssutdio.wear.ai.spark.AudioTrackManager.MyAudioCallback
    public void onPlaying(String sid, String text) {
        PlayStateCallback playStateCallback = this.playStateCallback;
        if (playStateCallback != null) {
            playStateCallback.audioPlaying(sid, text);
        }
    }

    @Override // com.glasssutdio.wear.ai.spark.AudioTrackManager.MyAudioCallback
    public void endAudio(String sid, boolean complete) {
        XLog.m137i("----------endAudio" + complete);
        PlayStateCallback playStateCallback = this.playStateCallback;
        if (playStateCallback != null) {
            playStateCallback.endAudio(sid, complete);
        }
        this.handler.removeCallbacks(this.playHeart);
        if (complete) {
            cancelTimeoutTask();
            LargeDataHandler.getInstance().aiVoicePlay(3, null);
        }
    }

    public void timeoutTask(int timeoutSecond) {
        cancelTimeoutTask();
        this.timeoutFuture = TimeoutTaskUtils.schedule(new Runnable() { // from class: com.glasssutdio.wear.ai.spark.SparkChainRecognizer$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m168xb21a8303();
            }
        }, timeoutSecond, TimeUnit.SECONDS);
    }

    /* renamed from: lambda$timeoutTask$0$com-glasssutdio-wear-ai-spark-SparkChainRecognizer */
    /* synthetic */ void m168xb21a8303() {
        XLog.m137i("-------------------time out");
        LargeDataHandler.getInstance().glassesControl(new byte[]{2, 1, 11}, new ILargeDataResponse<GlassModelControlResponse>() { // from class: com.glasssutdio.wear.ai.spark.SparkChainRecognizer.9
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int cmdType, GlassModelControlResponse response) {
            }
        });
        stopHeartBeat();
    }

    public void cancelTimeoutTask() {
        ScheduledFuture<?> scheduledFuture = this.timeoutFuture;
        if (scheduledFuture == null || scheduledFuture.isCancelled()) {
            return;
        }
        try {
            XLog.m137i("---------取消超时任务");
            this.timeoutFuture.cancel(true);
        } catch (Exception e) {
            XLog.m132e("-----取消超时任务时出错: " + e.getMessage());
        }
    }

    public void exitAi() {
        try {
            LargeDataHandler.getInstance().glassesControl(new byte[]{2, 1, 11}, new ILargeDataResponse<GlassModelControlResponse>() { // from class: com.glasssutdio.wear.ai.spark.SparkChainRecognizer.10
                @Override // com.oudmon.ble.base.communication.ILargeDataResponse
                public void parseData(int cmdType, GlassModelControlResponse response) {
                }
            });
            this.heartbeatTimer.stop();
            XLog.m137i("-----cancelTimeoutTask");
            cancelTimeoutTask();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startNextChat() {
        XLog.m137i("-------startNextChat");
        initData();
        LargeDataHandler.getInstance().initPackageNotify(this.aiChatListener);
        LargeDataHandler.getInstance().glassesControl(new byte[]{2, 1, 7}, new ILargeDataResponse<GlassModelControlResponse>() { // from class: com.glasssutdio.wear.ai.spark.SparkChainRecognizer.11
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int cmdType, GlassModelControlResponse response) {
            }
        });
        timeoutTask(10);
    }

    public void aiChatFailRestart() {
        LargeDataHandler.getInstance().initPackageNotify(this.aiChatListener);
        initData();
        timeoutTask(10);
    }

    public class SimpleTimer {
        private final Handler handler = new Handler(Looper.getMainLooper());
        private Runnable task;

        public SimpleTimer() {
        }

        public void start(final Runnable action) {
            Runnable runnable = new Runnable() { // from class: com.glasssutdio.wear.ai.spark.SparkChainRecognizer.SimpleTimer.1
                @Override // java.lang.Runnable
                public void run() {
                    action.run();
                    SimpleTimer.this.handler.postDelayed(this, 3000L);
                }
            };
            this.task = runnable;
            this.handler.post(runnable);
        }

        public void stop() {
            Runnable runnable = this.task;
            if (runnable != null) {
                this.handler.removeCallbacks(runnable);
            }
        }
    }

    public void setOnRecordArsListener(RecordArsCallback callback) {
        this.recordArsCallback = callback;
    }

    /* renamed from: eu */
    public boolean m167eu(String language) {
        return language.startsWith("it") || language.startsWith("cs") || language.startsWith("ro") || language.startsWith("sv") || language.startsWith("nl") || language.startsWith("pl") || language.startsWith("tr") || language.startsWith("hu") || language.startsWith("el") || language.startsWith("pt");
    }

    public void resetParams() {
        this.asr = null;
        this.mRTASR = null;
        this.mOnlineTTS = null;
    }
}
