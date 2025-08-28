package com.glasssutdio.wear.depository;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.GlassApplication;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.Localization;
import com.glasssutdio.wear.all.ThreadExtKt;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.GsonInstance;
import com.glasssutdio.wear.all.utils.MoshiUtils;
import com.glasssutdio.wear.all.utils.TypeToken;
import com.glasssutdio.wear.api.SSEHandler;
import com.glasssutdio.wear.api.request.AiChatBean;
import com.glasssutdio.wear.api.request.VisionChatRequest;
import com.glasssutdio.wear.database.GlassDatabase;
import com.glasssutdio.wear.database.dao.GlassAiChatDao;
import com.glasssutdio.wear.database.entity.AiChatEntity;
import com.glasssutdio.wear.depository.AiChatDepository;
import com.glasssutdio.wear.depository.bean.AiJsonBean;
import com.glasssutdio.wear.depository.bean.AsrParamsBean;
import com.glasssutdio.wear.depository.bean.ChatEnumAction;
import com.glasssutdio.wear.depository.bean.ChatHistoryCollectionManagerKotlin;
import com.glasssutdio.wear.p003ai.bean.TranslateBean;
import com.glasssutdio.wear.p003ai.callback.MyChatGptCallback;
import com.glasssutdio.wear.p003ai.spark.MachineTranslationMain;
import com.glasssutdio.wear.p003ai.spark.SparkChainRecognizer;
import com.google.android.material.internal.ViewUtils;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.FilenameUtils;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import com.squareup.moshi.JsonAdapter;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AiChatDepository.kt */
@Metadata(m606d1 = {"\u0000\u0091\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\n*\u0001\u0010\u0018\u0000 f2\u00020\u0001:\u0003efgB\u0005¢\u0006\u0002\u0010\u0002J \u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u0006H\u0007J\u0006\u00100\u001a\u00020+J\u0006\u00101\u001a\u00020+J\u0010\u00102\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u0006H\u0007J\u0018\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u0006H\u0002J\u0014\u00107\u001a\u00020+2\f\u00108\u001a\b\u0012\u0004\u0012\u00020509J!\u0010:\u001a\u00020+2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010>J\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00060\u001aJ\b\u0010@\u001a\u00020+H\u0002J\u0010\u0010A\u001a\u00020+2\u0006\u0010B\u001a\u00020\u0006H\u0003J\b\u0010C\u001a\u00020+H\u0002J\u0010\u0010D\u001a\u00020+2\u0006\u0010E\u001a\u00020\u0006H\u0002J#\u0010F\u001a\u0004\u0018\u0001052\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010>J$\u0010G\u001a\b\u0012\u0004\u0012\u000205092\u0006\u0010;\u001a\u00020<2\u0006\u0010H\u001a\u00020<2\u0006\u0010I\u001a\u00020-J.\u0010J\u001a\u00020+2\u0006\u0010K\u001a\u00020-2\u0006\u0010L\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u00062\u0006\u0010M\u001a\u00020\u00062\u0006\u0010N\u001a\u00020\u001cJ\u000e\u0010O\u001a\u00020\u00062\u0006\u0010P\u001a\u00020\u0006J\u000e\u0010Q\u001a\u00020\u00062\u0006\u0010P\u001a\u00020\u0006J\u0018\u0010R\u001a\u0002052\u0006\u00106\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u0006H\u0002J\u000e\u0010S\u001a\u00020+2\u0006\u0010.\u001a\u00020\u0006J\u0016\u0010T\u001a\u00020+2\u0006\u0010U\u001a\u00020\u00062\u0006\u0010V\u001a\u00020\u0006J\u000e\u0010W\u001a\u00020+2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010X\u001a\u00020+2\u0006\u0010 \u001a\u00020!J\u0016\u0010Y\u001a\u00020+2\u0006\u0010Z\u001a\u00020\u00062\u0006\u0010[\u001a\u00020\u0006J\u0006\u0010\\\u001a\u00020+J\u000e\u0010]\u001a\u00020^2\u0006\u0010_\u001a\u00020\u0006J\u0016\u0010`\u001a\u00020+2\u0006\u0010.\u001a\u00020\u00062\u0006\u0010K\u001a\u00020-J1\u0010a\u001a\u00020+2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<2\u0006\u0010b\u001a\u00020\u001c2\u0006\u0010c\u001a\u00020\u001cH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010dR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\b\"\u0004\b\u001f\u0010\nR\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\"\u001a\u00060#j\u0002`$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010'\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\b\"\u0004\b)\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006h"}, m607d2 = {"Lcom/glasssutdio/wear/depository/AiChatDepository;", "", "()V", "aiChatDao", "Lcom/glasssutdio/wear/database/dao/GlassAiChatDao;", "cacheString", "", "getCacheString", "()Ljava/lang/String;", "setCacheString", "(Ljava/lang/String;)V", "chatGptCallback", "Lcom/glasssutdio/wear/depository/AiChatDepository$ChatGptCallback;", "chatHistoryList", "Lcom/glasssutdio/wear/depository/bean/ChatHistoryCollectionManagerKotlin;", "itsCallback", "com/glasssutdio/wear/depository/AiChatDepository$itsCallback$1", "Lcom/glasssutdio/wear/depository/AiChatDepository$itsCallback$1;", "machineTranslationMain", "Lcom/glasssutdio/wear/ai/spark/MachineTranslationMain;", "myCallback", "Lcom/glasssutdio/wear/ai/callback/MyChatGptCallback;", "punctuationSet", "", "", "realTimeTTSQueue", "Ljava/util/concurrent/BlockingDeque;", "startRTTTS", "", "temp", "getTemp", "setTemp", "translateCallback", "Lcom/glasssutdio/wear/depository/AiChatDepository$TranslateCallback;", "translateContent", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "translateFrom", "translateTo", "userVisionText", "getUserVisionText", "setUserVisionText", "chatGpt", "", "chatType", "", "content", "imageBase64", "cleanRealTimeTTSQueue", "cleanTranslate", "convertToLocalDateTime", "jsonInput", "createChatFromAi", "Lcom/glasssutdio/wear/database/entity/AiChatEntity;", "role", "deleteAiChatHistoryList", "elements", "", "deleteByUidAndTimestamp", "uid", "", "chatTimestamp", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRealTimeTTSQueue", "handleEnd", "handleIncomingText", "text", "initTTSResultCallback", "outputChunk", "chunk", "queryByUidAndTimestamp", "queryChatHistory", "lastTimestamp", "limit", "realTimeTranslate", NotificationCompat.CATEGORY_STATUS, "asrContent", "dest", "finish", "restoreContent", "encoded", "restoreTTSContent", "saveChatFromAi", "saveChatFromSparkChain", "saveChatImageFromGlasses", "fileName", "filePath", "setChatGptCallback", "setTranslateCallback", "setUserTranslateFromAndTo", TypedValues.TransitionType.S_FROM, TypedValues.TransitionType.S_TO, "startRealTimeTTS", "switchAsrLanguage", "Lcom/glasssutdio/wear/depository/bean/AsrParamsBean;", "appLanguage", "translate", "updateLikeUnlikeStatus", "isLike", "isUnlike", "(JJZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ChatGptCallback", "Companion", "TranslateCallback", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AiChatDepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<AiChatDepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<AiChatDepository>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final AiChatDepository invoke() {
            return new AiChatDepository();
        }
    });
    private String cacheString;
    private ChatGptCallback chatGptCallback;
    private final AiChatDepository$itsCallback$1 itsCallback;
    private final MachineTranslationMain machineTranslationMain;
    private final Set<Character> punctuationSet;
    private final BlockingDeque<String> realTimeTTSQueue;
    private boolean startRTTTS;
    private String temp;
    private TranslateCallback translateCallback;
    private StringBuilder translateContent;
    private String translateFrom;
    private String translateTo;
    private final GlassAiChatDao aiChatDao = GlassDatabase.INSTANCE.getDatabase(GlassApplication.INSTANCE.getCONTEXT()).glassAiChatDao();
    private final ChatHistoryCollectionManagerKotlin chatHistoryList = new ChatHistoryCollectionManagerKotlin();
    private final MyChatGptCallback myCallback = new MyChatGptCallback();
    private String userVisionText = "";

    /* compiled from: AiChatDepository.kt */
    @Metadata(m606d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\"\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\"\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\tH&¨\u0006\u0011"}, m607d2 = {"Lcom/glasssutdio/wear/depository/AiChatDepository$ChatGptCallback;", "", "chatGptFail", "", "reason", "", "chatGptSuccess", "type", "content", "", "aiChatEntity", "Lcom/glasssutdio/wear/database/entity/AiChatEntity;", "chatGptTextStream", "isComplete", "", "entity", "text", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public interface ChatGptCallback {
        void chatGptFail(int reason);

        void chatGptSuccess(int type, String content, AiChatEntity aiChatEntity);

        void chatGptTextStream(boolean isComplete, AiChatEntity entity, String text);
    }

    /* compiled from: AiChatDepository.kt */
    @Metadata(m606d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0005H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u000e"}, m607d2 = {"Lcom/glasssutdio/wear/depository/AiChatDepository$TranslateCallback;", "", "translateDst", "", "src", "", "same", "", "translateFail", "reason", "", "translateRTDst", "rtDst", "translateSrc", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public interface TranslateCallback {
        void translateDst(String src, boolean same);

        void translateFail(int reason);

        void translateRTDst(String rtDst);

        void translateSrc(String src);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.glasssutdio.wear.depository.AiChatDepository$itsCallback$1] */
    public AiChatDepository() {
        ?? r1 = new MachineTranslationMain.OnTranslateListener() { // from class: com.glasssutdio.wear.depository.AiChatDepository$itsCallback$1
            @Override // com.glasssutdio.wear.ai.spark.MachineTranslationMain.OnTranslateListener
            public void onTranslate(TranslateBean translateBean) {
                Intrinsics.checkNotNullParameter(translateBean, "translateBean");
                XLog.m137i(GsonInstance.INSTANCE.getGson().toJson(translateBean));
                AiChatDepository.TranslateCallback translateCallback = this.this$0.translateCallback;
                Intrinsics.checkNotNull(translateCallback);
                String dst = translateBean.getTrans_result().getDst();
                Intrinsics.checkNotNullExpressionValue(dst, "getDst(...)");
                translateCallback.translateDst(dst, false);
            }

            @Override // com.glasssutdio.wear.ai.spark.MachineTranslationMain.OnTranslateListener
            public void onTranslateError(int errorCode) {
                XLog.m136i(Integer.valueOf(errorCode));
                AiChatDepository.TranslateCallback translateCallback = this.this$0.translateCallback;
                Intrinsics.checkNotNull(translateCallback);
                translateCallback.translateFail(-1);
            }
        };
        this.itsCallback = r1;
        this.machineTranslationMain = new MachineTranslationMain((MachineTranslationMain.OnTranslateListener) r1);
        this.translateFrom = "cn";
        this.translateTo = "en";
        this.translateContent = new StringBuilder();
        this.realTimeTTSQueue = new LinkedBlockingDeque(100);
        this.startRTTTS = true;
        this.temp = "";
        this.punctuationSet = SetsKt.setOf((Object[]) new Character[]{(char) 65292, (char) 12290, Character.valueOf(FilenameUtils.EXTENSION_SEPARATOR), ',', ':', (char) 65306, '?'});
        this.cacheString = "";
    }

    public final String getUserVisionText() {
        return this.userVisionText;
    }

    public final void setUserVisionText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userVisionText = str;
    }

    public final BlockingDeque<String> getRealTimeTTSQueue() {
        return this.realTimeTTSQueue;
    }

    public final void cleanRealTimeTTSQueue() {
        this.realTimeTTSQueue.clear();
        this.cacheString = "";
    }

    public final void setUserTranslateFromAndTo(String from, String to) {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        this.translateFrom = from;
        this.translateTo = to;
    }

    public final void cleanTranslate() {
        StringsKt.clear(this.translateContent);
    }

    public final void setChatGptCallback(ChatGptCallback chatGptCallback) {
        Intrinsics.checkNotNullParameter(chatGptCallback, "chatGptCallback");
        this.chatGptCallback = chatGptCallback;
    }

    public final void setTranslateCallback(TranslateCallback translateCallback) {
        Intrinsics.checkNotNullParameter(translateCallback, "translateCallback");
        this.translateCallback = translateCallback;
    }

    public final void saveChatFromSparkChain(final String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        ThreadExtKt.ktxRunOnBgSingleDao(this, new Function1<AiChatDepository, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository.saveChatFromSparkChain.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AiChatDepository aiChatDepository) {
                invoke2(aiChatDepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AiChatDepository ktxRunOnBgSingleDao) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingleDao, "$this$ktxRunOnBgSingleDao");
                AiChatEntity aiChatEntity = new AiChatEntity(UserConfig.INSTANCE.getInstance().getUid(), ChatEnumAction.USER, content, 1, "", "", System.currentTimeMillis() / 1000, 0, false, false, ViewUtils.EDGE_TO_EDGE_FLAGS, null);
                ktxRunOnBgSingleDao.aiChatDao.insert(aiChatEntity);
                if (ktxRunOnBgSingleDao.chatGptCallback == null) {
                    ktxRunOnBgSingleDao.myCallback.chatGptSuccess(666, content, aiChatEntity);
                    return;
                }
                ChatGptCallback chatGptCallback = ktxRunOnBgSingleDao.chatGptCallback;
                if (chatGptCallback != null) {
                    chatGptCallback.chatGptSuccess(666, content, aiChatEntity);
                }
            }
        });
    }

    public final void deleteAiChatHistoryList(final List<AiChatEntity> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        ThreadExtKt.ktxRunOnBgSingleDao(this, new Function1<AiChatDepository, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository.deleteAiChatHistoryList.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AiChatDepository aiChatDepository) {
                invoke2(aiChatDepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AiChatDepository ktxRunOnBgSingleDao) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingleDao, "$this$ktxRunOnBgSingleDao");
                ktxRunOnBgSingleDao.aiChatDao.deleteList(CollectionsKt.toMutableList((Collection) elements));
            }
        });
    }

    public final Object deleteByUidAndTimestamp(long j, long j2, Continuation<? super Unit> continuation) {
        Object objDeleteByUidAndTimestamp = this.aiChatDao.deleteByUidAndTimestamp(j, j2, continuation);
        return objDeleteByUidAndTimestamp == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDeleteByUidAndTimestamp : Unit.INSTANCE;
    }

    public final Object queryByUidAndTimestamp(long j, long j2, Continuation<? super AiChatEntity> continuation) {
        return this.aiChatDao.queryByUidAndTimestamp(j, j2, continuation);
    }

    public final Object updateLikeUnlikeStatus(long j, long j2, boolean z, boolean z2, Continuation<? super Unit> continuation) {
        Object objUpdateLikeUnlikeStatus = this.aiChatDao.updateLikeUnlikeStatus(j, j2, z, z2, continuation);
        return objUpdateLikeUnlikeStatus == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objUpdateLikeUnlikeStatus : Unit.INSTANCE;
    }

    public final void saveChatImageFromGlasses(final String fileName, final String filePath) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        ThreadExtKt.ktxRunOnBgSingleDao(this, new Function1<AiChatDepository, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository.saveChatImageFromGlasses.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AiChatDepository aiChatDepository) {
                invoke2(aiChatDepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AiChatDepository ktxRunOnBgSingleDao) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingleDao, "$this$ktxRunOnBgSingleDao");
                long uid = UserConfig.INSTANCE.getInstance().getUid();
                String str = fileName;
                String str2 = filePath;
                AiChatEntity aiChatEntity = new AiChatEntity(uid, ChatEnumAction.USER, str, 2, str2, str2, System.currentTimeMillis() / 1000, 0, false, false, ViewUtils.EDGE_TO_EDGE_FLAGS, null);
                ktxRunOnBgSingleDao.aiChatDao.insert(aiChatEntity);
                if (ktxRunOnBgSingleDao.chatGptCallback == null) {
                    ktxRunOnBgSingleDao.myCallback.chatGptSuccess(666, fileName, aiChatEntity);
                    return;
                }
                ChatGptCallback chatGptCallback = ktxRunOnBgSingleDao.chatGptCallback;
                if (chatGptCallback != null) {
                    chatGptCallback.chatGptSuccess(666, fileName, aiChatEntity);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AiChatEntity createChatFromAi(String role, String content) {
        return new AiChatEntity(UserConfig.INSTANCE.getInstance().getUid(), role, content, 1, "", "", System.currentTimeMillis() / 1000, 0, false, false, ViewUtils.EDGE_TO_EDGE_FLAGS, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AiChatEntity saveChatFromAi(String role, String content) {
        AiChatEntity aiChatEntity = new AiChatEntity(UserConfig.INSTANCE.getInstance().getUid(), role, content, 1, "", "", System.currentTimeMillis() / 1000, 0, false, false, ViewUtils.EDGE_TO_EDGE_FLAGS, null);
        this.aiChatDao.insert(aiChatEntity);
        return aiChatEntity;
    }

    public final List<AiChatEntity> queryChatHistory(long uid, long lastTimestamp, int limit) {
        return this.aiChatDao.queryHistoryChatList(uid, lastTimestamp, limit);
    }

    public final String getTemp() {
        return this.temp;
    }

    public final void setTemp(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.temp = str;
    }

    public final void realTimeTranslate(int status, String asrContent, String content, String dest, boolean finish) {
        Intrinsics.checkNotNullParameter(asrContent, "asrContent");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(dest, "dest");
        this.translateFrom = UserConfig.INSTANCE.getInstance().getTranslateFromDefault();
        this.translateTo = UserConfig.INSTANCE.getInstance().getTranslateToDefault();
        String language = Locale.getDefault().getLanguage();
        Intrinsics.checkNotNullExpressionValue(language, "getLanguage(...)");
        if (StringsKt.startsWith$default(language, "zh", false, 2, (Object) null) || GlassApplication.INSTANCE.getGetInstance().getPingGoogle()) {
            if (this.translateCallback == null) {
                return;
            }
            if (finish) {
                this.translateContent.append(IOUtils.LINE_SEPARATOR_UNIX);
            }
            if (status == 1) {
                this.temp = this.translateContent.toString() + asrContent;
                TranslateCallback translateCallback = this.translateCallback;
                Intrinsics.checkNotNull(translateCallback);
                translateCallback.translateSrc(this.temp);
                return;
            }
            if (status == 2 && this.temp.length() > 0 && content.length() > 0) {
                if (Intrinsics.areEqual(this.translateFrom, this.translateTo)) {
                    TranslateCallback translateCallback2 = this.translateCallback;
                    Intrinsics.checkNotNull(translateCallback2);
                    translateCallback2.translateDst(content, true);
                    return;
                }
                this.translateContent.append(content);
                TranslateCallback translateCallback3 = this.translateCallback;
                Intrinsics.checkNotNull(translateCallback3);
                String string = this.translateContent.toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                translateCallback3.translateSrc(string);
                this.temp = "";
                if (Intrinsics.areEqual(this.translateFrom, "cn")) {
                    String string2 = this.translateContent.toString();
                    Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                    if (string2.length() == 0) {
                        return;
                    }
                    TranslateBean translateBeanTranslateOld = this.machineTranslationMain.translateOld("cn", this.translateTo, content);
                    TranslateCallback translateCallback4 = this.translateCallback;
                    Intrinsics.checkNotNull(translateCallback4);
                    String dst = translateBeanTranslateOld.getTrans_result().getDst();
                    Intrinsics.checkNotNullExpressionValue(dst, "getDst(...)");
                    translateCallback4.translateDst(dst, false);
                    return;
                }
                XLog.m137i(this.translateContent.toString());
                String string3 = this.translateContent.toString();
                Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                if (string3.length() == 0) {
                    return;
                }
                TranslateBean translateBeanTranslateOld2 = this.machineTranslationMain.translateOld(this.translateFrom, "cn", content);
                if (translateBeanTranslateOld2 == null) {
                    TranslateCallback translateCallback5 = this.translateCallback;
                    Intrinsics.checkNotNull(translateCallback5);
                    translateCallback5.translateFail(-1);
                    return;
                }
                if (!StringsKt.equals(this.translateTo, "cn", false)) {
                    translateBeanTranslateOld2 = this.machineTranslationMain.translateOld("cn", this.translateTo, translateBeanTranslateOld2.getTrans_result().getDst());
                }
                TranslateCallback translateCallback6 = this.translateCallback;
                if (translateCallback6 != null) {
                    if (translateBeanTranslateOld2 == null) {
                        Intrinsics.checkNotNull(translateCallback6);
                        translateCallback6.translateFail(-1);
                        return;
                    } else if (Intrinsics.areEqual(this.translateFrom, this.translateTo)) {
                        TranslateCallback translateCallback7 = this.translateCallback;
                        Intrinsics.checkNotNull(translateCallback7);
                        translateCallback7.translateDst(content, true);
                        return;
                    } else {
                        TranslateCallback translateCallback8 = this.translateCallback;
                        Intrinsics.checkNotNull(translateCallback8);
                        String dst2 = translateBeanTranslateOld2.getTrans_result().getDst();
                        Intrinsics.checkNotNullExpressionValue(dst2, "getDst(...)");
                        translateCallback8.translateDst(dst2, false);
                        return;
                    }
                }
                return;
            }
            return;
        }
        if (this.translateCallback == null) {
            return;
        }
        if (status == 1) {
            this.temp = this.translateContent.toString() + asrContent;
            TranslateCallback translateCallback9 = this.translateCallback;
            Intrinsics.checkNotNull(translateCallback9);
            translateCallback9.translateSrc(this.temp);
            XLog.m137i(this.temp);
            return;
        }
        if (status != 2) {
            return;
        }
        this.translateContent.append(asrContent);
        TranslateCallback translateCallback10 = this.translateCallback;
        Intrinsics.checkNotNull(translateCallback10);
        String string4 = this.translateContent.toString();
        Intrinsics.checkNotNullExpressionValue(string4, "toString(...)");
        translateCallback10.translateSrc(string4);
        XLog.m137i(content);
        if (Intrinsics.areEqual(this.translateFrom, this.translateTo)) {
            TranslateCallback translateCallback11 = this.translateCallback;
            Intrinsics.checkNotNull(translateCallback11);
            translateCallback11.translateDst(content, true);
            return;
        }
        this.machineTranslationMain.translate(this.translateFrom, this.translateTo, content);
    }

    public final void translate(String content, int status) {
        TranslateBean translateBeanTranslateOld;
        Intrinsics.checkNotNullParameter(content, "content");
        String language = Locale.getDefault().getLanguage();
        Intrinsics.checkNotNullExpressionValue(language, "getLanguage(...)");
        if (StringsKt.startsWith$default(language, "zh", false, 2, (Object) null) || GlassApplication.INSTANCE.getGetInstance().getPingGoogle()) {
            TranslateCallback translateCallback = this.translateCallback;
            if (translateCallback == null) {
                return;
            }
            Intrinsics.checkNotNull(translateCallback);
            translateCallback.translateSrc(content);
            if (status == 2) {
                if (Intrinsics.areEqual(this.translateFrom, this.translateTo)) {
                    TranslateCallback translateCallback2 = this.translateCallback;
                    Intrinsics.checkNotNull(translateCallback2);
                    translateCallback2.translateDst(content, false);
                    return;
                }
                if (Intrinsics.areEqual(this.translateFrom, "cn") || Intrinsics.areEqual(this.translateFrom, "en")) {
                    translateBeanTranslateOld = this.machineTranslationMain.translateOld(this.translateFrom, this.translateTo, content);
                } else {
                    translateBeanTranslateOld = this.machineTranslationMain.translateOld(this.translateFrom, "cn", content);
                    if (translateBeanTranslateOld == null) {
                        TranslateCallback translateCallback3 = this.translateCallback;
                        Intrinsics.checkNotNull(translateCallback3);
                        translateCallback3.translateFail(-1);
                        return;
                    } else if (!StringsKt.equals(this.translateTo, "cn", false)) {
                        translateBeanTranslateOld = this.machineTranslationMain.translateOld("cn", this.translateTo, translateBeanTranslateOld.getTrans_result().getDst());
                    }
                }
                TranslateCallback translateCallback4 = this.translateCallback;
                if (translateCallback4 != null) {
                    if (translateBeanTranslateOld == null) {
                        Intrinsics.checkNotNull(translateCallback4);
                        translateCallback4.translateFail(-1);
                        return;
                    } else if (Intrinsics.areEqual(this.translateFrom, this.translateTo)) {
                        TranslateCallback translateCallback5 = this.translateCallback;
                        Intrinsics.checkNotNull(translateCallback5);
                        translateCallback5.translateDst(content, true);
                        return;
                    } else {
                        TranslateCallback translateCallback6 = this.translateCallback;
                        Intrinsics.checkNotNull(translateCallback6);
                        translateCallback6.translateDst(translateBeanTranslateOld.getTrans_result().getDst().toString(), false);
                        return;
                    }
                }
                return;
            }
            return;
        }
        TranslateCallback translateCallback7 = this.translateCallback;
        if (translateCallback7 == null) {
            return;
        }
        if (status == 1) {
            Intrinsics.checkNotNull(translateCallback7);
            translateCallback7.translateSrc(content);
            return;
        }
        Intrinsics.checkNotNull(translateCallback7);
        translateCallback7.translateSrc(content);
        if (Intrinsics.areEqual(this.translateFrom, this.translateTo)) {
            TranslateCallback translateCallback8 = this.translateCallback;
            Intrinsics.checkNotNull(translateCallback8);
            translateCallback8.translateDst(content, true);
            return;
        }
        this.machineTranslationMain.translate(this.translateFrom, this.translateTo, content);
    }

    /* compiled from: AiChatDepository.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1", m620f = "AiChatDepository.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1 */
    static final class C08891 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $chatType;
        final /* synthetic */ String $content;
        final /* synthetic */ String $imageBase64;
        int label;
        final /* synthetic */ AiChatDepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08891(int i, String str, AiChatDepository aiChatDepository, String str2, Continuation<? super C08891> continuation) {
            super(2, continuation);
            this.$chatType = i;
            this.$content = str;
            this.this$0 = aiChatDepository;
            this.$imageBase64 = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C08891(this.$chatType, this.$content, this.this$0, this.$imageBase64, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08891) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            int i = this.$chatType;
            if (i == 1) {
                String strPredict$default = IntentClassifier.predict$default(new IntentClassifier(GlassApplication.INSTANCE.getCONTEXT(), UserConfig.INSTANCE.getInstance().getAiLanguageCode()), this.$content, 0.0f, 2, null);
                XLog.m137i("IntentClassifier-预测意图是：" + strPredict$default);
                switch (strPredict$default.hashCode()) {
                    case -1521030562:
                        if (strPredict$default.equals("start_video")) {
                            invokeSuspend$invokeCallback(this.this$0, 11, "start_video");
                            return Unit.INSTANCE;
                        }
                        final StringBuilder sb = new StringBuilder();
                        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                        booleanRef.element = true;
                        this.this$0.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.USER, this.$content, 0));
                        final SSEHandler sSEHandlerStartStream = SSEHandler.INSTANCE.getInstance().startStream(UserConfig.INSTANCE.getInstance().getUniqueIdHw(), UserConfig.INSTANCE.getInstance().getAiLanguageCode(), GlobalKt.getAppName(GlassApplication.INSTANCE.getCONTEXT()), this.this$0.chatHistoryList.getMessages());
                        final AiChatDepository aiChatDepository = this.this$0;
                        final int i2 = this.$chatType;
                        final String str = this.$content;
                        sSEHandlerStartStream.onThinking(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                                invoke2(str2);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                XLog.m137i("onThinking:" + it);
                            }
                        });
                        sSEHandlerStartStream.onJsonContent(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                                invoke2(str2);
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Type inference failed for: r4v3, types: [T, com.glasssutdio.wear.database.entity.AiChatEntity] */
                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<AiJsonBean>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$2$invoke$$inlined$fromJson$1
                                }.getType());
                                Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "adapter(...)");
                                AiJsonBean aiJsonBean = (AiJsonBean) jsonAdapterAdapter.fromJson(it);
                                if (aiJsonBean != null) {
                                    int type = aiJsonBean.getType();
                                    if (type == 7) {
                                        String strConvertToLocalDateTime = aiChatDepository.convertToLocalDateTime(aiChatDepository.restoreContent(aiJsonBean.getResult()));
                                        XLog.m137i(strConvertToLocalDateTime);
                                        aiChatDepository.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, strConvertToLocalDateTime, i2));
                                        if (aiChatDepository.chatGptCallback != null) {
                                            AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository.chatGptCallback;
                                            if (chatGptCallback != null) {
                                                chatGptCallback.chatGptSuccess(aiJsonBean.getType(), it, objectRef.element);
                                            }
                                        } else {
                                            aiChatDepository.myCallback.chatGptSuccess(aiJsonBean.getType(), it, objectRef.element);
                                        }
                                    } else if (type == 8) {
                                        aiChatDepository.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, aiJsonBean.getResult(), i2));
                                        if (aiChatDepository.chatGptCallback != null) {
                                            AiChatDepository.ChatGptCallback chatGptCallback2 = aiChatDepository.chatGptCallback;
                                            if (chatGptCallback2 != null) {
                                                chatGptCallback2.chatGptSuccess(aiJsonBean.getType(), it, objectRef.element);
                                            }
                                        } else {
                                            aiChatDepository.myCallback.chatGptSuccess(aiJsonBean.getType(), it, objectRef.element);
                                        }
                                    } else if (type == 9) {
                                        String strConvertToLocalDateTime2 = aiChatDepository.convertToLocalDateTime(aiJsonBean.getResult());
                                        XLog.m137i(strConvertToLocalDateTime2);
                                        objectRef.element = aiChatDepository.saveChatFromAi(ChatEnumAction.ASSISTANT, strConvertToLocalDateTime2);
                                        aiChatDepository.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, strConvertToLocalDateTime2, i2));
                                        if (aiChatDepository.chatGptCallback != null) {
                                            AiChatDepository.ChatGptCallback chatGptCallback3 = aiChatDepository.chatGptCallback;
                                            if (chatGptCallback3 != null) {
                                                chatGptCallback3.chatGptSuccess(aiJsonBean.getType(), it, objectRef.element);
                                            }
                                        } else {
                                            aiChatDepository.myCallback.chatGptSuccess(aiJsonBean.getType(), it, objectRef.element);
                                        }
                                    }
                                }
                                XLog.m137i("onJsonContent:" + it);
                            }
                        });
                        sSEHandlerStartStream.onCustom(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str2) throws NumberFormatException {
                                invoke2(str2);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) throws NumberFormatException {
                                Intrinsics.checkNotNullParameter(it, "it");
                                int i3 = Integer.parseInt(it);
                                if (i3 == 2) {
                                    aiChatDepository.setUserVisionText(str);
                                    aiChatDepository.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, str, i2));
                                } else if (i3 == 3) {
                                    aiChatDepository.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, "camera", i2));
                                } else if (i3 == 5) {
                                    aiChatDepository.chatHistoryList.clear();
                                    aiChatDepository.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, "exit ai", i2));
                                }
                                if (aiChatDepository.chatGptCallback != null) {
                                    AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository.chatGptCallback;
                                    if (chatGptCallback != null) {
                                        chatGptCallback.chatGptSuccess(i3, it, objectRef.element);
                                        return;
                                    }
                                    return;
                                }
                                aiChatDepository.myCallback.chatGptSuccess(i3, it, objectRef.element);
                            }
                        });
                        sSEHandlerStartStream.onContent(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str2) throws InterruptedException {
                                invoke2(str2);
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Type inference failed for: r3v1, types: [T, com.glasssutdio.wear.database.entity.AiChatEntity] */
                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) throws InterruptedException {
                                Intrinsics.checkNotNullParameter(it, "it");
                                if (booleanRef.element) {
                                    XLog.m137i("content:" + booleanRef.element);
                                    aiChatDepository.initTTSResultCallback();
                                    aiChatDepository.startRTTTS = true;
                                    aiChatDepository.setCacheString("");
                                    aiChatDepository.realTimeTTSQueue.clear();
                                    booleanRef.element = false;
                                    objectRef.element = aiChatDepository.createChatFromAi(ChatEnumAction.ASSISTANT, "");
                                    if (aiChatDepository.chatGptCallback != null) {
                                        AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository.chatGptCallback;
                                        if (chatGptCallback != null) {
                                            chatGptCallback.chatGptSuccess(666, "", objectRef.element);
                                        }
                                    } else {
                                        aiChatDepository.myCallback.chatGptSuccess(666, "", objectRef.element);
                                    }
                                }
                                String strRestoreContent = aiChatDepository.restoreContent(it);
                                sb.append(strRestoreContent);
                                aiChatDepository.handleIncomingText(strRestoreContent);
                                if (aiChatDepository.chatGptCallback == null) {
                                    aiChatDepository.myCallback.chatGptTextStream(false, objectRef.element, strRestoreContent);
                                    return;
                                }
                                AiChatDepository.ChatGptCallback chatGptCallback2 = aiChatDepository.chatGptCallback;
                                if (chatGptCallback2 != null) {
                                    chatGptCallback2.chatGptTextStream(false, objectRef.element, strRestoreContent);
                                }
                            }
                        });
                        sSEHandlerStartStream.onEnd(new Function0<Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$5
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                SSEHandler sSEHandler = sSEHandlerStartStream;
                                final StringBuilder sb2 = sb;
                                final Ref.ObjectRef<AiChatEntity> objectRef2 = objectRef;
                                final AiChatDepository aiChatDepository2 = aiChatDepository;
                                final int i3 = i2;
                                ThreadExtKt.ktxRunOnBgSingle(sSEHandler, new Function1<SSEHandler, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$5.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SSEHandler sSEHandler2) throws InterruptedException {
                                        invoke2(sSEHandler2);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX WARN: Type inference failed for: r2v1, types: [T, com.glasssutdio.wear.database.entity.AiChatEntity] */
                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SSEHandler ktxRunOnBgSingle) throws InterruptedException {
                                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                                        XLog.m137i("end");
                                        String string = sb2.toString();
                                        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                                        if (string.length() == 0) {
                                            return;
                                        }
                                        objectRef2.element = aiChatDepository2.saveChatFromAi(ChatEnumAction.ASSISTANT, string);
                                        ChatHistoryCollectionManagerKotlin chatHistoryCollectionManagerKotlin = aiChatDepository2.chatHistoryList;
                                        String string2 = sb2.toString();
                                        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                                        chatHistoryCollectionManagerKotlin.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, string2, i3));
                                        aiChatDepository2.handleEnd();
                                        if (aiChatDepository2.chatGptCallback == null) {
                                            aiChatDepository2.myCallback.chatGptTextStream(true, objectRef2.element, string);
                                            return;
                                        }
                                        AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository2.chatGptCallback;
                                        if (chatGptCallback != null) {
                                            chatGptCallback.chatGptTextStream(true, objectRef2.element, string);
                                        }
                                    }
                                });
                            }
                        });
                        sSEHandlerStartStream.onError(new Function1<Throwable, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$6
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                invoke2(th);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(final Throwable it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                SSEHandler sSEHandler = sSEHandlerStartStream;
                                final StringBuilder sb2 = sb;
                                final AiChatDepository aiChatDepository2 = aiChatDepository;
                                ThreadExtKt.ktxRunOnUi(sSEHandler, new Function1<SSEHandler, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$6.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SSEHandler sSEHandler2) {
                                        invoke2(sSEHandler2);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SSEHandler ktxRunOnUi) {
                                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                        StringsKt.clear(sb2);
                                        XLog.m137i(it.toString());
                                        if (aiChatDepository2.chatGptCallback == null) {
                                            aiChatDepository2.myCallback.chatGptFail(-1);
                                            return;
                                        }
                                        AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository2.chatGptCallback;
                                        if (chatGptCallback != null) {
                                            chatGptCallback.chatGptFail(-1);
                                        }
                                    }
                                });
                            }
                        });
                        break;
                    case -778038150:
                        if (strPredict$default.equals("take_photo")) {
                            invokeSuspend$invokeCallback(this.this$0, 3, "camera");
                            return Unit.INSTANCE;
                        }
                        final StringBuilder sb2 = new StringBuilder();
                        final Ref.ObjectRef<AiChatEntity> objectRef2 = new Ref.ObjectRef();
                        final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
                        booleanRef2.element = true;
                        this.this$0.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.USER, this.$content, 0));
                        final SSEHandler sSEHandlerStartStream2 = SSEHandler.INSTANCE.getInstance().startStream(UserConfig.INSTANCE.getInstance().getUniqueIdHw(), UserConfig.INSTANCE.getInstance().getAiLanguageCode(), GlobalKt.getAppName(GlassApplication.INSTANCE.getCONTEXT()), this.this$0.chatHistoryList.getMessages());
                        final AiChatDepository aiChatDepository2 = this.this$0;
                        final int i22 = this.$chatType;
                        final String str2 = this.$content;
                        sSEHandlerStartStream2.onThinking(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str22) {
                                invoke2(str22);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                XLog.m137i("onThinking:" + it);
                            }
                        });
                        sSEHandlerStartStream2.onJsonContent(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str22) {
                                invoke2(str22);
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Type inference failed for: r4v3, types: [T, com.glasssutdio.wear.database.entity.AiChatEntity] */
                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<AiJsonBean>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$2$invoke$$inlined$fromJson$1
                                }.getType());
                                Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "adapter(...)");
                                AiJsonBean aiJsonBean = (AiJsonBean) jsonAdapterAdapter.fromJson(it);
                                if (aiJsonBean != null) {
                                    int type = aiJsonBean.getType();
                                    if (type == 7) {
                                        String strConvertToLocalDateTime = aiChatDepository2.convertToLocalDateTime(aiChatDepository2.restoreContent(aiJsonBean.getResult()));
                                        XLog.m137i(strConvertToLocalDateTime);
                                        aiChatDepository2.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, strConvertToLocalDateTime, i22));
                                        if (aiChatDepository2.chatGptCallback != null) {
                                            AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository2.chatGptCallback;
                                            if (chatGptCallback != null) {
                                                chatGptCallback.chatGptSuccess(aiJsonBean.getType(), it, objectRef2.element);
                                            }
                                        } else {
                                            aiChatDepository2.myCallback.chatGptSuccess(aiJsonBean.getType(), it, objectRef2.element);
                                        }
                                    } else if (type == 8) {
                                        aiChatDepository2.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, aiJsonBean.getResult(), i22));
                                        if (aiChatDepository2.chatGptCallback != null) {
                                            AiChatDepository.ChatGptCallback chatGptCallback2 = aiChatDepository2.chatGptCallback;
                                            if (chatGptCallback2 != null) {
                                                chatGptCallback2.chatGptSuccess(aiJsonBean.getType(), it, objectRef2.element);
                                            }
                                        } else {
                                            aiChatDepository2.myCallback.chatGptSuccess(aiJsonBean.getType(), it, objectRef2.element);
                                        }
                                    } else if (type == 9) {
                                        String strConvertToLocalDateTime2 = aiChatDepository2.convertToLocalDateTime(aiJsonBean.getResult());
                                        XLog.m137i(strConvertToLocalDateTime2);
                                        objectRef2.element = aiChatDepository2.saveChatFromAi(ChatEnumAction.ASSISTANT, strConvertToLocalDateTime2);
                                        aiChatDepository2.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, strConvertToLocalDateTime2, i22));
                                        if (aiChatDepository2.chatGptCallback != null) {
                                            AiChatDepository.ChatGptCallback chatGptCallback3 = aiChatDepository2.chatGptCallback;
                                            if (chatGptCallback3 != null) {
                                                chatGptCallback3.chatGptSuccess(aiJsonBean.getType(), it, objectRef2.element);
                                            }
                                        } else {
                                            aiChatDepository2.myCallback.chatGptSuccess(aiJsonBean.getType(), it, objectRef2.element);
                                        }
                                    }
                                }
                                XLog.m137i("onJsonContent:" + it);
                            }
                        });
                        sSEHandlerStartStream2.onCustom(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str22) throws NumberFormatException {
                                invoke2(str22);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) throws NumberFormatException {
                                Intrinsics.checkNotNullParameter(it, "it");
                                int i3 = Integer.parseInt(it);
                                if (i3 == 2) {
                                    aiChatDepository2.setUserVisionText(str2);
                                    aiChatDepository2.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, str2, i22));
                                } else if (i3 == 3) {
                                    aiChatDepository2.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, "camera", i22));
                                } else if (i3 == 5) {
                                    aiChatDepository2.chatHistoryList.clear();
                                    aiChatDepository2.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, "exit ai", i22));
                                }
                                if (aiChatDepository2.chatGptCallback != null) {
                                    AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository2.chatGptCallback;
                                    if (chatGptCallback != null) {
                                        chatGptCallback.chatGptSuccess(i3, it, objectRef2.element);
                                        return;
                                    }
                                    return;
                                }
                                aiChatDepository2.myCallback.chatGptSuccess(i3, it, objectRef2.element);
                            }
                        });
                        sSEHandlerStartStream2.onContent(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str22) throws InterruptedException {
                                invoke2(str22);
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Type inference failed for: r3v1, types: [T, com.glasssutdio.wear.database.entity.AiChatEntity] */
                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) throws InterruptedException {
                                Intrinsics.checkNotNullParameter(it, "it");
                                if (booleanRef2.element) {
                                    XLog.m137i("content:" + booleanRef2.element);
                                    aiChatDepository2.initTTSResultCallback();
                                    aiChatDepository2.startRTTTS = true;
                                    aiChatDepository2.setCacheString("");
                                    aiChatDepository2.realTimeTTSQueue.clear();
                                    booleanRef2.element = false;
                                    objectRef2.element = aiChatDepository2.createChatFromAi(ChatEnumAction.ASSISTANT, "");
                                    if (aiChatDepository2.chatGptCallback != null) {
                                        AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository2.chatGptCallback;
                                        if (chatGptCallback != null) {
                                            chatGptCallback.chatGptSuccess(666, "", objectRef2.element);
                                        }
                                    } else {
                                        aiChatDepository2.myCallback.chatGptSuccess(666, "", objectRef2.element);
                                    }
                                }
                                String strRestoreContent = aiChatDepository2.restoreContent(it);
                                sb2.append(strRestoreContent);
                                aiChatDepository2.handleIncomingText(strRestoreContent);
                                if (aiChatDepository2.chatGptCallback == null) {
                                    aiChatDepository2.myCallback.chatGptTextStream(false, objectRef2.element, strRestoreContent);
                                    return;
                                }
                                AiChatDepository.ChatGptCallback chatGptCallback2 = aiChatDepository2.chatGptCallback;
                                if (chatGptCallback2 != null) {
                                    chatGptCallback2.chatGptTextStream(false, objectRef2.element, strRestoreContent);
                                }
                            }
                        });
                        sSEHandlerStartStream2.onEnd(new Function0<Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$5
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                SSEHandler sSEHandler = sSEHandlerStartStream2;
                                final StringBuilder sb22 = sb2;
                                final Ref.ObjectRef<AiChatEntity> objectRef22 = objectRef2;
                                final AiChatDepository aiChatDepository22 = aiChatDepository2;
                                final int i3 = i22;
                                ThreadExtKt.ktxRunOnBgSingle(sSEHandler, new Function1<SSEHandler, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$5.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SSEHandler sSEHandler2) throws InterruptedException {
                                        invoke2(sSEHandler2);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX WARN: Type inference failed for: r2v1, types: [T, com.glasssutdio.wear.database.entity.AiChatEntity] */
                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SSEHandler ktxRunOnBgSingle) throws InterruptedException {
                                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                                        XLog.m137i("end");
                                        String string = sb22.toString();
                                        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                                        if (string.length() == 0) {
                                            return;
                                        }
                                        objectRef22.element = aiChatDepository22.saveChatFromAi(ChatEnumAction.ASSISTANT, string);
                                        ChatHistoryCollectionManagerKotlin chatHistoryCollectionManagerKotlin = aiChatDepository22.chatHistoryList;
                                        String string2 = sb22.toString();
                                        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                                        chatHistoryCollectionManagerKotlin.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, string2, i3));
                                        aiChatDepository22.handleEnd();
                                        if (aiChatDepository22.chatGptCallback == null) {
                                            aiChatDepository22.myCallback.chatGptTextStream(true, objectRef22.element, string);
                                            return;
                                        }
                                        AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository22.chatGptCallback;
                                        if (chatGptCallback != null) {
                                            chatGptCallback.chatGptTextStream(true, objectRef22.element, string);
                                        }
                                    }
                                });
                            }
                        });
                        sSEHandlerStartStream2.onError(new Function1<Throwable, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$6
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                invoke2(th);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(final Throwable it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                SSEHandler sSEHandler = sSEHandlerStartStream2;
                                final StringBuilder sb22 = sb2;
                                final AiChatDepository aiChatDepository22 = aiChatDepository2;
                                ThreadExtKt.ktxRunOnUi(sSEHandler, new Function1<SSEHandler, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$6.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SSEHandler sSEHandler2) {
                                        invoke2(sSEHandler2);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SSEHandler ktxRunOnUi) {
                                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                        StringsKt.clear(sb22);
                                        XLog.m137i(it.toString());
                                        if (aiChatDepository22.chatGptCallback == null) {
                                            aiChatDepository22.myCallback.chatGptFail(-1);
                                            return;
                                        }
                                        AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository22.chatGptCallback;
                                        if (chatGptCallback != null) {
                                            chatGptCallback.chatGptFail(-1);
                                        }
                                    }
                                });
                            }
                        });
                        break;
                    case -588535212:
                        if (strPredict$default.equals("start_recording")) {
                            invokeSuspend$invokeCallback(this.this$0, 12, "start_recording");
                            return Unit.INSTANCE;
                        }
                        final StringBuilder sb22 = new StringBuilder();
                        final Ref.ObjectRef<AiChatEntity> objectRef22 = new Ref.ObjectRef();
                        final Ref.BooleanRef booleanRef22 = new Ref.BooleanRef();
                        booleanRef22.element = true;
                        this.this$0.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.USER, this.$content, 0));
                        final SSEHandler sSEHandlerStartStream22 = SSEHandler.INSTANCE.getInstance().startStream(UserConfig.INSTANCE.getInstance().getUniqueIdHw(), UserConfig.INSTANCE.getInstance().getAiLanguageCode(), GlobalKt.getAppName(GlassApplication.INSTANCE.getCONTEXT()), this.this$0.chatHistoryList.getMessages());
                        final AiChatDepository aiChatDepository22 = this.this$0;
                        final int i222 = this.$chatType;
                        final String str22 = this.$content;
                        sSEHandlerStartStream22.onThinking(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str222) {
                                invoke2(str222);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                XLog.m137i("onThinking:" + it);
                            }
                        });
                        sSEHandlerStartStream22.onJsonContent(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str222) {
                                invoke2(str222);
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Type inference failed for: r4v3, types: [T, com.glasssutdio.wear.database.entity.AiChatEntity] */
                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<AiJsonBean>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$2$invoke$$inlined$fromJson$1
                                }.getType());
                                Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "adapter(...)");
                                AiJsonBean aiJsonBean = (AiJsonBean) jsonAdapterAdapter.fromJson(it);
                                if (aiJsonBean != null) {
                                    int type = aiJsonBean.getType();
                                    if (type == 7) {
                                        String strConvertToLocalDateTime = aiChatDepository22.convertToLocalDateTime(aiChatDepository22.restoreContent(aiJsonBean.getResult()));
                                        XLog.m137i(strConvertToLocalDateTime);
                                        aiChatDepository22.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, strConvertToLocalDateTime, i222));
                                        if (aiChatDepository22.chatGptCallback != null) {
                                            AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository22.chatGptCallback;
                                            if (chatGptCallback != null) {
                                                chatGptCallback.chatGptSuccess(aiJsonBean.getType(), it, objectRef22.element);
                                            }
                                        } else {
                                            aiChatDepository22.myCallback.chatGptSuccess(aiJsonBean.getType(), it, objectRef22.element);
                                        }
                                    } else if (type == 8) {
                                        aiChatDepository22.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, aiJsonBean.getResult(), i222));
                                        if (aiChatDepository22.chatGptCallback != null) {
                                            AiChatDepository.ChatGptCallback chatGptCallback2 = aiChatDepository22.chatGptCallback;
                                            if (chatGptCallback2 != null) {
                                                chatGptCallback2.chatGptSuccess(aiJsonBean.getType(), it, objectRef22.element);
                                            }
                                        } else {
                                            aiChatDepository22.myCallback.chatGptSuccess(aiJsonBean.getType(), it, objectRef22.element);
                                        }
                                    } else if (type == 9) {
                                        String strConvertToLocalDateTime2 = aiChatDepository22.convertToLocalDateTime(aiJsonBean.getResult());
                                        XLog.m137i(strConvertToLocalDateTime2);
                                        objectRef22.element = aiChatDepository22.saveChatFromAi(ChatEnumAction.ASSISTANT, strConvertToLocalDateTime2);
                                        aiChatDepository22.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, strConvertToLocalDateTime2, i222));
                                        if (aiChatDepository22.chatGptCallback != null) {
                                            AiChatDepository.ChatGptCallback chatGptCallback3 = aiChatDepository22.chatGptCallback;
                                            if (chatGptCallback3 != null) {
                                                chatGptCallback3.chatGptSuccess(aiJsonBean.getType(), it, objectRef22.element);
                                            }
                                        } else {
                                            aiChatDepository22.myCallback.chatGptSuccess(aiJsonBean.getType(), it, objectRef22.element);
                                        }
                                    }
                                }
                                XLog.m137i("onJsonContent:" + it);
                            }
                        });
                        sSEHandlerStartStream22.onCustom(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str222) throws NumberFormatException {
                                invoke2(str222);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) throws NumberFormatException {
                                Intrinsics.checkNotNullParameter(it, "it");
                                int i3 = Integer.parseInt(it);
                                if (i3 == 2) {
                                    aiChatDepository22.setUserVisionText(str22);
                                    aiChatDepository22.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, str22, i222));
                                } else if (i3 == 3) {
                                    aiChatDepository22.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, "camera", i222));
                                } else if (i3 == 5) {
                                    aiChatDepository22.chatHistoryList.clear();
                                    aiChatDepository22.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, "exit ai", i222));
                                }
                                if (aiChatDepository22.chatGptCallback != null) {
                                    AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository22.chatGptCallback;
                                    if (chatGptCallback != null) {
                                        chatGptCallback.chatGptSuccess(i3, it, objectRef22.element);
                                        return;
                                    }
                                    return;
                                }
                                aiChatDepository22.myCallback.chatGptSuccess(i3, it, objectRef22.element);
                            }
                        });
                        sSEHandlerStartStream22.onContent(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str222) throws InterruptedException {
                                invoke2(str222);
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Type inference failed for: r3v1, types: [T, com.glasssutdio.wear.database.entity.AiChatEntity] */
                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) throws InterruptedException {
                                Intrinsics.checkNotNullParameter(it, "it");
                                if (booleanRef22.element) {
                                    XLog.m137i("content:" + booleanRef22.element);
                                    aiChatDepository22.initTTSResultCallback();
                                    aiChatDepository22.startRTTTS = true;
                                    aiChatDepository22.setCacheString("");
                                    aiChatDepository22.realTimeTTSQueue.clear();
                                    booleanRef22.element = false;
                                    objectRef22.element = aiChatDepository22.createChatFromAi(ChatEnumAction.ASSISTANT, "");
                                    if (aiChatDepository22.chatGptCallback != null) {
                                        AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository22.chatGptCallback;
                                        if (chatGptCallback != null) {
                                            chatGptCallback.chatGptSuccess(666, "", objectRef22.element);
                                        }
                                    } else {
                                        aiChatDepository22.myCallback.chatGptSuccess(666, "", objectRef22.element);
                                    }
                                }
                                String strRestoreContent = aiChatDepository22.restoreContent(it);
                                sb22.append(strRestoreContent);
                                aiChatDepository22.handleIncomingText(strRestoreContent);
                                if (aiChatDepository22.chatGptCallback == null) {
                                    aiChatDepository22.myCallback.chatGptTextStream(false, objectRef22.element, strRestoreContent);
                                    return;
                                }
                                AiChatDepository.ChatGptCallback chatGptCallback2 = aiChatDepository22.chatGptCallback;
                                if (chatGptCallback2 != null) {
                                    chatGptCallback2.chatGptTextStream(false, objectRef22.element, strRestoreContent);
                                }
                            }
                        });
                        sSEHandlerStartStream22.onEnd(new Function0<Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$5
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                SSEHandler sSEHandler = sSEHandlerStartStream22;
                                final StringBuilder sb222 = sb22;
                                final Ref.ObjectRef<AiChatEntity> objectRef222 = objectRef22;
                                final AiChatDepository aiChatDepository222 = aiChatDepository22;
                                final int i3 = i222;
                                ThreadExtKt.ktxRunOnBgSingle(sSEHandler, new Function1<SSEHandler, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$5.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SSEHandler sSEHandler2) throws InterruptedException {
                                        invoke2(sSEHandler2);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX WARN: Type inference failed for: r2v1, types: [T, com.glasssutdio.wear.database.entity.AiChatEntity] */
                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SSEHandler ktxRunOnBgSingle) throws InterruptedException {
                                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                                        XLog.m137i("end");
                                        String string = sb222.toString();
                                        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                                        if (string.length() == 0) {
                                            return;
                                        }
                                        objectRef222.element = aiChatDepository222.saveChatFromAi(ChatEnumAction.ASSISTANT, string);
                                        ChatHistoryCollectionManagerKotlin chatHistoryCollectionManagerKotlin = aiChatDepository222.chatHistoryList;
                                        String string2 = sb222.toString();
                                        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                                        chatHistoryCollectionManagerKotlin.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, string2, i3));
                                        aiChatDepository222.handleEnd();
                                        if (aiChatDepository222.chatGptCallback == null) {
                                            aiChatDepository222.myCallback.chatGptTextStream(true, objectRef222.element, string);
                                            return;
                                        }
                                        AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository222.chatGptCallback;
                                        if (chatGptCallback != null) {
                                            chatGptCallback.chatGptTextStream(true, objectRef222.element, string);
                                        }
                                    }
                                });
                            }
                        });
                        sSEHandlerStartStream22.onError(new Function1<Throwable, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$6
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                invoke2(th);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(final Throwable it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                SSEHandler sSEHandler = sSEHandlerStartStream22;
                                final StringBuilder sb222 = sb22;
                                final AiChatDepository aiChatDepository222 = aiChatDepository22;
                                ThreadExtKt.ktxRunOnUi(sSEHandler, new Function1<SSEHandler, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$6.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SSEHandler sSEHandler2) {
                                        invoke2(sSEHandler2);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SSEHandler ktxRunOnUi) {
                                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                        StringsKt.clear(sb222);
                                        XLog.m137i(it.toString());
                                        if (aiChatDepository222.chatGptCallback == null) {
                                            aiChatDepository222.myCallback.chatGptFail(-1);
                                            return;
                                        }
                                        AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository222.chatGptCallback;
                                        if (chatGptCallback != null) {
                                            chatGptCallback.chatGptFail(-1);
                                        }
                                    }
                                });
                            }
                        });
                        break;
                    case 1491209953:
                        if (strPredict$default.equals("identify_objects")) {
                            this.this$0.setUserVisionText(this.$content);
                            invokeSuspend$invokeCallback(this.this$0, 2, "识别物体");
                            return Unit.INSTANCE;
                        }
                        final StringBuilder sb222 = new StringBuilder();
                        final Ref.ObjectRef<AiChatEntity> objectRef222 = new Ref.ObjectRef();
                        final Ref.BooleanRef booleanRef222 = new Ref.BooleanRef();
                        booleanRef222.element = true;
                        this.this$0.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.USER, this.$content, 0));
                        final SSEHandler sSEHandlerStartStream222 = SSEHandler.INSTANCE.getInstance().startStream(UserConfig.INSTANCE.getInstance().getUniqueIdHw(), UserConfig.INSTANCE.getInstance().getAiLanguageCode(), GlobalKt.getAppName(GlassApplication.INSTANCE.getCONTEXT()), this.this$0.chatHistoryList.getMessages());
                        final AiChatDepository aiChatDepository222 = this.this$0;
                        final int i2222 = this.$chatType;
                        final String str222 = this.$content;
                        sSEHandlerStartStream222.onThinking(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str2222) {
                                invoke2(str2222);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                XLog.m137i("onThinking:" + it);
                            }
                        });
                        sSEHandlerStartStream222.onJsonContent(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str2222) {
                                invoke2(str2222);
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Type inference failed for: r4v3, types: [T, com.glasssutdio.wear.database.entity.AiChatEntity] */
                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<AiJsonBean>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$2$invoke$$inlined$fromJson$1
                                }.getType());
                                Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "adapter(...)");
                                AiJsonBean aiJsonBean = (AiJsonBean) jsonAdapterAdapter.fromJson(it);
                                if (aiJsonBean != null) {
                                    int type = aiJsonBean.getType();
                                    if (type == 7) {
                                        String strConvertToLocalDateTime = aiChatDepository222.convertToLocalDateTime(aiChatDepository222.restoreContent(aiJsonBean.getResult()));
                                        XLog.m137i(strConvertToLocalDateTime);
                                        aiChatDepository222.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, strConvertToLocalDateTime, i2222));
                                        if (aiChatDepository222.chatGptCallback != null) {
                                            AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository222.chatGptCallback;
                                            if (chatGptCallback != null) {
                                                chatGptCallback.chatGptSuccess(aiJsonBean.getType(), it, objectRef222.element);
                                            }
                                        } else {
                                            aiChatDepository222.myCallback.chatGptSuccess(aiJsonBean.getType(), it, objectRef222.element);
                                        }
                                    } else if (type == 8) {
                                        aiChatDepository222.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, aiJsonBean.getResult(), i2222));
                                        if (aiChatDepository222.chatGptCallback != null) {
                                            AiChatDepository.ChatGptCallback chatGptCallback2 = aiChatDepository222.chatGptCallback;
                                            if (chatGptCallback2 != null) {
                                                chatGptCallback2.chatGptSuccess(aiJsonBean.getType(), it, objectRef222.element);
                                            }
                                        } else {
                                            aiChatDepository222.myCallback.chatGptSuccess(aiJsonBean.getType(), it, objectRef222.element);
                                        }
                                    } else if (type == 9) {
                                        String strConvertToLocalDateTime2 = aiChatDepository222.convertToLocalDateTime(aiJsonBean.getResult());
                                        XLog.m137i(strConvertToLocalDateTime2);
                                        objectRef222.element = aiChatDepository222.saveChatFromAi(ChatEnumAction.ASSISTANT, strConvertToLocalDateTime2);
                                        aiChatDepository222.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, strConvertToLocalDateTime2, i2222));
                                        if (aiChatDepository222.chatGptCallback != null) {
                                            AiChatDepository.ChatGptCallback chatGptCallback3 = aiChatDepository222.chatGptCallback;
                                            if (chatGptCallback3 != null) {
                                                chatGptCallback3.chatGptSuccess(aiJsonBean.getType(), it, objectRef222.element);
                                            }
                                        } else {
                                            aiChatDepository222.myCallback.chatGptSuccess(aiJsonBean.getType(), it, objectRef222.element);
                                        }
                                    }
                                }
                                XLog.m137i("onJsonContent:" + it);
                            }
                        });
                        sSEHandlerStartStream222.onCustom(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str2222) throws NumberFormatException {
                                invoke2(str2222);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) throws NumberFormatException {
                                Intrinsics.checkNotNullParameter(it, "it");
                                int i3 = Integer.parseInt(it);
                                if (i3 == 2) {
                                    aiChatDepository222.setUserVisionText(str222);
                                    aiChatDepository222.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, str222, i2222));
                                } else if (i3 == 3) {
                                    aiChatDepository222.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, "camera", i2222));
                                } else if (i3 == 5) {
                                    aiChatDepository222.chatHistoryList.clear();
                                    aiChatDepository222.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, "exit ai", i2222));
                                }
                                if (aiChatDepository222.chatGptCallback != null) {
                                    AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository222.chatGptCallback;
                                    if (chatGptCallback != null) {
                                        chatGptCallback.chatGptSuccess(i3, it, objectRef222.element);
                                        return;
                                    }
                                    return;
                                }
                                aiChatDepository222.myCallback.chatGptSuccess(i3, it, objectRef222.element);
                            }
                        });
                        sSEHandlerStartStream222.onContent(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str2222) throws InterruptedException {
                                invoke2(str2222);
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Type inference failed for: r3v1, types: [T, com.glasssutdio.wear.database.entity.AiChatEntity] */
                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) throws InterruptedException {
                                Intrinsics.checkNotNullParameter(it, "it");
                                if (booleanRef222.element) {
                                    XLog.m137i("content:" + booleanRef222.element);
                                    aiChatDepository222.initTTSResultCallback();
                                    aiChatDepository222.startRTTTS = true;
                                    aiChatDepository222.setCacheString("");
                                    aiChatDepository222.realTimeTTSQueue.clear();
                                    booleanRef222.element = false;
                                    objectRef222.element = aiChatDepository222.createChatFromAi(ChatEnumAction.ASSISTANT, "");
                                    if (aiChatDepository222.chatGptCallback != null) {
                                        AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository222.chatGptCallback;
                                        if (chatGptCallback != null) {
                                            chatGptCallback.chatGptSuccess(666, "", objectRef222.element);
                                        }
                                    } else {
                                        aiChatDepository222.myCallback.chatGptSuccess(666, "", objectRef222.element);
                                    }
                                }
                                String strRestoreContent = aiChatDepository222.restoreContent(it);
                                sb222.append(strRestoreContent);
                                aiChatDepository222.handleIncomingText(strRestoreContent);
                                if (aiChatDepository222.chatGptCallback == null) {
                                    aiChatDepository222.myCallback.chatGptTextStream(false, objectRef222.element, strRestoreContent);
                                    return;
                                }
                                AiChatDepository.ChatGptCallback chatGptCallback2 = aiChatDepository222.chatGptCallback;
                                if (chatGptCallback2 != null) {
                                    chatGptCallback2.chatGptTextStream(false, objectRef222.element, strRestoreContent);
                                }
                            }
                        });
                        sSEHandlerStartStream222.onEnd(new Function0<Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$5
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                SSEHandler sSEHandler = sSEHandlerStartStream222;
                                final StringBuilder sb2222 = sb222;
                                final Ref.ObjectRef<AiChatEntity> objectRef2222 = objectRef222;
                                final AiChatDepository aiChatDepository2222 = aiChatDepository222;
                                final int i3 = i2222;
                                ThreadExtKt.ktxRunOnBgSingle(sSEHandler, new Function1<SSEHandler, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$5.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SSEHandler sSEHandler2) throws InterruptedException {
                                        invoke2(sSEHandler2);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX WARN: Type inference failed for: r2v1, types: [T, com.glasssutdio.wear.database.entity.AiChatEntity] */
                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SSEHandler ktxRunOnBgSingle) throws InterruptedException {
                                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                                        XLog.m137i("end");
                                        String string = sb2222.toString();
                                        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                                        if (string.length() == 0) {
                                            return;
                                        }
                                        objectRef2222.element = aiChatDepository2222.saveChatFromAi(ChatEnumAction.ASSISTANT, string);
                                        ChatHistoryCollectionManagerKotlin chatHistoryCollectionManagerKotlin = aiChatDepository2222.chatHistoryList;
                                        String string2 = sb2222.toString();
                                        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                                        chatHistoryCollectionManagerKotlin.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, string2, i3));
                                        aiChatDepository2222.handleEnd();
                                        if (aiChatDepository2222.chatGptCallback == null) {
                                            aiChatDepository2222.myCallback.chatGptTextStream(true, objectRef2222.element, string);
                                            return;
                                        }
                                        AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository2222.chatGptCallback;
                                        if (chatGptCallback != null) {
                                            chatGptCallback.chatGptTextStream(true, objectRef2222.element, string);
                                        }
                                    }
                                });
                            }
                        });
                        sSEHandlerStartStream222.onError(new Function1<Throwable, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$6
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                invoke2(th);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(final Throwable it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                SSEHandler sSEHandler = sSEHandlerStartStream222;
                                final StringBuilder sb2222 = sb222;
                                final AiChatDepository aiChatDepository2222 = aiChatDepository222;
                                ThreadExtKt.ktxRunOnUi(sSEHandler, new Function1<SSEHandler, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$6.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SSEHandler sSEHandler2) {
                                        invoke2(sSEHandler2);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SSEHandler ktxRunOnUi) {
                                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                        StringsKt.clear(sb2222);
                                        XLog.m137i(it.toString());
                                        if (aiChatDepository2222.chatGptCallback == null) {
                                            aiChatDepository2222.myCallback.chatGptFail(-1);
                                            return;
                                        }
                                        AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository2222.chatGptCallback;
                                        if (chatGptCallback != null) {
                                            chatGptCallback.chatGptFail(-1);
                                        }
                                    }
                                });
                            }
                        });
                        break;
                    default:
                        final StringBuilder sb2222 = new StringBuilder();
                        final Ref.ObjectRef<AiChatEntity> objectRef2222 = new Ref.ObjectRef();
                        final Ref.BooleanRef booleanRef2222 = new Ref.BooleanRef();
                        booleanRef2222.element = true;
                        this.this$0.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.USER, this.$content, 0));
                        final SSEHandler sSEHandlerStartStream2222 = SSEHandler.INSTANCE.getInstance().startStream(UserConfig.INSTANCE.getInstance().getUniqueIdHw(), UserConfig.INSTANCE.getInstance().getAiLanguageCode(), GlobalKt.getAppName(GlassApplication.INSTANCE.getCONTEXT()), this.this$0.chatHistoryList.getMessages());
                        final AiChatDepository aiChatDepository2222 = this.this$0;
                        final int i22222 = this.$chatType;
                        final String str2222 = this.$content;
                        sSEHandlerStartStream2222.onThinking(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str22222) {
                                invoke2(str22222);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                XLog.m137i("onThinking:" + it);
                            }
                        });
                        sSEHandlerStartStream2222.onJsonContent(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str22222) {
                                invoke2(str22222);
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Type inference failed for: r4v3, types: [T, com.glasssutdio.wear.database.entity.AiChatEntity] */
                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<AiJsonBean>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$2$invoke$$inlined$fromJson$1
                                }.getType());
                                Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "adapter(...)");
                                AiJsonBean aiJsonBean = (AiJsonBean) jsonAdapterAdapter.fromJson(it);
                                if (aiJsonBean != null) {
                                    int type = aiJsonBean.getType();
                                    if (type == 7) {
                                        String strConvertToLocalDateTime = aiChatDepository2222.convertToLocalDateTime(aiChatDepository2222.restoreContent(aiJsonBean.getResult()));
                                        XLog.m137i(strConvertToLocalDateTime);
                                        aiChatDepository2222.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, strConvertToLocalDateTime, i22222));
                                        if (aiChatDepository2222.chatGptCallback != null) {
                                            AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository2222.chatGptCallback;
                                            if (chatGptCallback != null) {
                                                chatGptCallback.chatGptSuccess(aiJsonBean.getType(), it, objectRef2222.element);
                                            }
                                        } else {
                                            aiChatDepository2222.myCallback.chatGptSuccess(aiJsonBean.getType(), it, objectRef2222.element);
                                        }
                                    } else if (type == 8) {
                                        aiChatDepository2222.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, aiJsonBean.getResult(), i22222));
                                        if (aiChatDepository2222.chatGptCallback != null) {
                                            AiChatDepository.ChatGptCallback chatGptCallback2 = aiChatDepository2222.chatGptCallback;
                                            if (chatGptCallback2 != null) {
                                                chatGptCallback2.chatGptSuccess(aiJsonBean.getType(), it, objectRef2222.element);
                                            }
                                        } else {
                                            aiChatDepository2222.myCallback.chatGptSuccess(aiJsonBean.getType(), it, objectRef2222.element);
                                        }
                                    } else if (type == 9) {
                                        String strConvertToLocalDateTime2 = aiChatDepository2222.convertToLocalDateTime(aiJsonBean.getResult());
                                        XLog.m137i(strConvertToLocalDateTime2);
                                        objectRef2222.element = aiChatDepository2222.saveChatFromAi(ChatEnumAction.ASSISTANT, strConvertToLocalDateTime2);
                                        aiChatDepository2222.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, strConvertToLocalDateTime2, i22222));
                                        if (aiChatDepository2222.chatGptCallback != null) {
                                            AiChatDepository.ChatGptCallback chatGptCallback3 = aiChatDepository2222.chatGptCallback;
                                            if (chatGptCallback3 != null) {
                                                chatGptCallback3.chatGptSuccess(aiJsonBean.getType(), it, objectRef2222.element);
                                            }
                                        } else {
                                            aiChatDepository2222.myCallback.chatGptSuccess(aiJsonBean.getType(), it, objectRef2222.element);
                                        }
                                    }
                                }
                                XLog.m137i("onJsonContent:" + it);
                            }
                        });
                        sSEHandlerStartStream2222.onCustom(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str22222) throws NumberFormatException {
                                invoke2(str22222);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) throws NumberFormatException {
                                Intrinsics.checkNotNullParameter(it, "it");
                                int i3 = Integer.parseInt(it);
                                if (i3 == 2) {
                                    aiChatDepository2222.setUserVisionText(str2222);
                                    aiChatDepository2222.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, str2222, i22222));
                                } else if (i3 == 3) {
                                    aiChatDepository2222.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, "camera", i22222));
                                } else if (i3 == 5) {
                                    aiChatDepository2222.chatHistoryList.clear();
                                    aiChatDepository2222.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, "exit ai", i22222));
                                }
                                if (aiChatDepository2222.chatGptCallback != null) {
                                    AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository2222.chatGptCallback;
                                    if (chatGptCallback != null) {
                                        chatGptCallback.chatGptSuccess(i3, it, objectRef2222.element);
                                        return;
                                    }
                                    return;
                                }
                                aiChatDepository2222.myCallback.chatGptSuccess(i3, it, objectRef2222.element);
                            }
                        });
                        sSEHandlerStartStream2222.onContent(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str22222) throws InterruptedException {
                                invoke2(str22222);
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Type inference failed for: r3v1, types: [T, com.glasssutdio.wear.database.entity.AiChatEntity] */
                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String it) throws InterruptedException {
                                Intrinsics.checkNotNullParameter(it, "it");
                                if (booleanRef2222.element) {
                                    XLog.m137i("content:" + booleanRef2222.element);
                                    aiChatDepository2222.initTTSResultCallback();
                                    aiChatDepository2222.startRTTTS = true;
                                    aiChatDepository2222.setCacheString("");
                                    aiChatDepository2222.realTimeTTSQueue.clear();
                                    booleanRef2222.element = false;
                                    objectRef2222.element = aiChatDepository2222.createChatFromAi(ChatEnumAction.ASSISTANT, "");
                                    if (aiChatDepository2222.chatGptCallback != null) {
                                        AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository2222.chatGptCallback;
                                        if (chatGptCallback != null) {
                                            chatGptCallback.chatGptSuccess(666, "", objectRef2222.element);
                                        }
                                    } else {
                                        aiChatDepository2222.myCallback.chatGptSuccess(666, "", objectRef2222.element);
                                    }
                                }
                                String strRestoreContent = aiChatDepository2222.restoreContent(it);
                                sb2222.append(strRestoreContent);
                                aiChatDepository2222.handleIncomingText(strRestoreContent);
                                if (aiChatDepository2222.chatGptCallback == null) {
                                    aiChatDepository2222.myCallback.chatGptTextStream(false, objectRef2222.element, strRestoreContent);
                                    return;
                                }
                                AiChatDepository.ChatGptCallback chatGptCallback2 = aiChatDepository2222.chatGptCallback;
                                if (chatGptCallback2 != null) {
                                    chatGptCallback2.chatGptTextStream(false, objectRef2222.element, strRestoreContent);
                                }
                            }
                        });
                        sSEHandlerStartStream2222.onEnd(new Function0<Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$5
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                SSEHandler sSEHandler = sSEHandlerStartStream2222;
                                final StringBuilder sb22222 = sb2222;
                                final Ref.ObjectRef<AiChatEntity> objectRef22222 = objectRef2222;
                                final AiChatDepository aiChatDepository22222 = aiChatDepository2222;
                                final int i3 = i22222;
                                ThreadExtKt.ktxRunOnBgSingle(sSEHandler, new Function1<SSEHandler, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$5.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SSEHandler sSEHandler2) throws InterruptedException {
                                        invoke2(sSEHandler2);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX WARN: Type inference failed for: r2v1, types: [T, com.glasssutdio.wear.database.entity.AiChatEntity] */
                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SSEHandler ktxRunOnBgSingle) throws InterruptedException {
                                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                                        XLog.m137i("end");
                                        String string = sb22222.toString();
                                        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                                        if (string.length() == 0) {
                                            return;
                                        }
                                        objectRef22222.element = aiChatDepository22222.saveChatFromAi(ChatEnumAction.ASSISTANT, string);
                                        ChatHistoryCollectionManagerKotlin chatHistoryCollectionManagerKotlin = aiChatDepository22222.chatHistoryList;
                                        String string2 = sb22222.toString();
                                        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                                        chatHistoryCollectionManagerKotlin.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, string2, i3));
                                        aiChatDepository22222.handleEnd();
                                        if (aiChatDepository22222.chatGptCallback == null) {
                                            aiChatDepository22222.myCallback.chatGptTextStream(true, objectRef22222.element, string);
                                            return;
                                        }
                                        AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository22222.chatGptCallback;
                                        if (chatGptCallback != null) {
                                            chatGptCallback.chatGptTextStream(true, objectRef22222.element, string);
                                        }
                                    }
                                });
                            }
                        });
                        sSEHandlerStartStream2222.onError(new Function1<Throwable, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$6
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                invoke2(th);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(final Throwable it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                SSEHandler sSEHandler = sSEHandlerStartStream2222;
                                final StringBuilder sb22222 = sb2222;
                                final AiChatDepository aiChatDepository22222 = aiChatDepository2222;
                                ThreadExtKt.ktxRunOnUi(sSEHandler, new Function1<SSEHandler, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$1$6.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SSEHandler sSEHandler2) {
                                        invoke2(sSEHandler2);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SSEHandler ktxRunOnUi) {
                                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                        StringsKt.clear(sb22222);
                                        XLog.m137i(it.toString());
                                        if (aiChatDepository22222.chatGptCallback == null) {
                                            aiChatDepository22222.myCallback.chatGptFail(-1);
                                            return;
                                        }
                                        AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository22222.chatGptCallback;
                                        if (chatGptCallback != null) {
                                            chatGptCallback.chatGptFail(-1);
                                        }
                                    }
                                });
                            }
                        });
                        break;
                }
            } else if (i == 2) {
                final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                final Ref.BooleanRef booleanRef3 = new Ref.BooleanRef();
                booleanRef3.element = true;
                final StringBuilder sb3 = new StringBuilder();
                this.this$0.chatHistoryList.addMessage(new AiChatBean(ChatEnumAction.USER, this.$content, 0));
                final SSEHandler sSEHandlerStartImageStream = SSEHandler.INSTANCE.getInstance().startImageStream(UserConfig.INSTANCE.getInstance().getUniqueIdHw(), new VisionChatRequest(this.this$0.getUserVisionText(), this.$imageBase64));
                final AiChatDepository aiChatDepository3 = this.this$0;
                final int i3 = this.$chatType;
                sSEHandlerStartImageStream.onContent(new Function1<String, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(String str3) throws InterruptedException {
                        invoke2(str3);
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Type inference failed for: r3v1, types: [T, com.glasssutdio.wear.database.entity.AiChatEntity] */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String it) throws InterruptedException {
                        Intrinsics.checkNotNullParameter(it, "it");
                        if (booleanRef3.element) {
                            aiChatDepository3.initTTSResultCallback();
                            aiChatDepository3.startRTTTS = true;
                            aiChatDepository3.setCacheString("");
                            aiChatDepository3.realTimeTTSQueue.clear();
                            booleanRef3.element = false;
                            objectRef3.element = aiChatDepository3.createChatFromAi(ChatEnumAction.ASSISTANT, "");
                            if (aiChatDepository3.chatGptCallback != null) {
                                AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository3.chatGptCallback;
                                if (chatGptCallback != null) {
                                    chatGptCallback.chatGptSuccess(666, "", objectRef3.element);
                                }
                            } else {
                                aiChatDepository3.myCallback.chatGptSuccess(666, "", objectRef3.element);
                            }
                        }
                        String strRestoreContent = aiChatDepository3.restoreContent(it);
                        sb3.append(strRestoreContent);
                        aiChatDepository3.handleIncomingText(strRestoreContent);
                        if (aiChatDepository3.chatGptCallback == null) {
                            aiChatDepository3.myCallback.chatGptTextStream(false, objectRef3.element, strRestoreContent);
                            return;
                        }
                        AiChatDepository.ChatGptCallback chatGptCallback2 = aiChatDepository3.chatGptCallback;
                        if (chatGptCallback2 != null) {
                            chatGptCallback2.chatGptTextStream(false, objectRef3.element, strRestoreContent);
                        }
                    }
                });
                sSEHandlerStartImageStream.onEnd(new Function0<Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$2$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        SSEHandler sSEHandler = sSEHandlerStartImageStream;
                        final StringBuilder sb4 = sb3;
                        final Ref.ObjectRef<AiChatEntity> objectRef4 = objectRef3;
                        final AiChatDepository aiChatDepository4 = aiChatDepository3;
                        final int i4 = i3;
                        ThreadExtKt.ktxRunOnBgSingle(sSEHandler, new Function1<SSEHandler, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$chatGpt$1$2$2.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SSEHandler sSEHandler2) throws InterruptedException {
                                invoke2(sSEHandler2);
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Type inference failed for: r2v1, types: [T, com.glasssutdio.wear.database.entity.AiChatEntity] */
                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SSEHandler ktxRunOnBgSingle) throws InterruptedException {
                                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                                XLog.m137i("end");
                                String string = sb4.toString();
                                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                                if (string.length() == 0) {
                                    return;
                                }
                                objectRef4.element = aiChatDepository4.saveChatFromAi(ChatEnumAction.ASSISTANT, string);
                                ChatHistoryCollectionManagerKotlin chatHistoryCollectionManagerKotlin = aiChatDepository4.chatHistoryList;
                                String string2 = sb4.toString();
                                Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                                chatHistoryCollectionManagerKotlin.addMessage(new AiChatBean(ChatEnumAction.ASSISTANT, string2, i4));
                                aiChatDepository4.handleEnd();
                                if (aiChatDepository4.chatGptCallback == null) {
                                    aiChatDepository4.myCallback.chatGptTextStream(true, objectRef4.element, string);
                                    return;
                                }
                                AiChatDepository.ChatGptCallback chatGptCallback = aiChatDepository4.chatGptCallback;
                                if (chatGptCallback != null) {
                                    chatGptCallback.chatGptTextStream(true, objectRef4.element, string);
                                }
                            }
                        });
                    }
                });
            }
            return Unit.INSTANCE;
        }

        private static final void invokeSuspend$invokeCallback(AiChatDepository aiChatDepository, int i, String str) {
            Unit unit;
            ChatGptCallback chatGptCallback = aiChatDepository.chatGptCallback;
            if (chatGptCallback != null) {
                chatGptCallback.chatGptSuccess(i, str, null);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                aiChatDepository.myCallback.chatGptSuccess(i, str, null);
            }
        }
    }

    public final void chatGpt(int chatType, String content, String imageBase64) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(imageBase64, "imageBase64");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C08891(chatType, content, this, imageBase64, null), 3, null);
    }

    public final String convertToLocalDateTime(String jsonInput) {
        Intrinsics.checkNotNullParameter(jsonInput, "jsonInput");
        try {
            String str = DateTimeFormatter.ofPattern("yyyy-MM-dd EEEE HH:mm", Locale.US).format(LocalDate.parse(StringsKt.trim((CharSequence) StringsKt.substringBefore$default(StringsKt.substringAfter$default(jsonInput, "\"result\":\"", (String) null, 2, (Object) null), "[", (String) null, 2, (Object) null)).toString(), DateTimeFormatter.ISO_DATE).atTime(LocalTime.now(Clock.systemUTC())).atOffset(ZoneOffset.UTC).atZoneSameInstant(ZoneId.systemDefault()));
            Intrinsics.checkNotNull(str);
            return str;
        } catch (Exception unused) {
            return "格式错误！输入必须为 {\"result\":\"YYYY-MM-DD[&]Weekday\",...}";
        }
    }

    public final String restoreContent(String encoded) {
        Intrinsics.checkNotNullParameter(encoded, "encoded");
        return StringsKt.replace$default(StringsKt.replace$default(encoded, "[&]", HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR, false, 4, (Object) null), "[*]", IOUtils.LINE_SEPARATOR_UNIX, false, 4, (Object) null);
    }

    public final String restoreTTSContent(String encoded) {
        Intrinsics.checkNotNullParameter(encoded, "encoded");
        return StringsKt.replace$default(encoded, "*", "", false, 4, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initTTSResultCallback() {
        SparkChainRecognizer.getInstance().setTTSResultCallback(new SparkChainRecognizer.TTSResultCallback() { // from class: com.glasssutdio.wear.depository.AiChatDepository$$ExternalSyntheticLambda0
            @Override // com.glasssutdio.wear.ai.spark.SparkChainRecognizer.TTSResultCallback
            public final void ttsResult(int i) {
                AiChatDepository.initTTSResultCallback$lambda$0(this.f$0, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initTTSResultCallback$lambda$0(AiChatDepository this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 2) {
            ThreadExtKt.ktxRunOnUiDelay(this$0, 500L, new Function1<AiChatDepository, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository$initTTSResultCallback$1$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AiChatDepository aiChatDepository) {
                    invoke2(aiChatDepository);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AiChatDepository ktxRunOnUiDelay) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUiDelay, "$this$ktxRunOnUiDelay");
                    ktxRunOnUiDelay.startRealTimeTTS();
                }
            });
        }
    }

    public final String getCacheString() {
        return this.cacheString;
    }

    public final void setCacheString(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cacheString = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleIncomingText(String text) throws InterruptedException {
        String strSubstring;
        String str;
        this.cacheString += text;
        int i = StringsKt.startsWith$default(UserConfig.INSTANCE.getInstance().getAiLanguageCode(), "zh", false, 2, (Object) null) ? 100 : 200;
        ArrayList arrayList = new ArrayList();
        int length = this.cacheString.length();
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (this.punctuationSet.contains(Character.valueOf(this.cacheString.charAt(i3)))) {
                int i4 = i3 + 1;
                String strSubstring2 = this.cacheString.substring(i2, i4);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                arrayList.add(strSubstring2);
                i2 = i4;
            }
        }
        if (i2 < this.cacheString.length()) {
            strSubstring = this.cacheString.substring(i2);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        } else {
            strSubstring = "";
        }
        this.cacheString = strSubstring;
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        loop1: while (true) {
            str = "";
            while (it.hasNext()) {
                str = str + ((String) it.next());
                if (str.length() >= i) {
                    break;
                }
            }
            arrayList2.add(StringsKt.trim((CharSequence) str).toString());
        }
        if (str.length() > 0) {
            this.cacheString = str + this.cacheString;
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            outputChunk((String) it2.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleEnd() throws InterruptedException {
        String string = StringsKt.trim((CharSequence) this.cacheString).toString();
        if (string.length() > 0) {
            this.realTimeTTSQueue.putLast(string);
        }
        if (this.startRTTTS) {
            startRealTimeTTS();
        }
        this.cacheString = "";
    }

    private final void outputChunk(String chunk) throws InterruptedException {
        XLog.m137i(chunk);
        if (chunk.length() > 0) {
            this.realTimeTTSQueue.putLast(chunk);
            if (this.startRTTTS) {
                this.startRTTTS = false;
                startRealTimeTTS();
            }
        }
    }

    public final void startRealTimeTTS() {
        XLog.m137i("realTimeTTSQueue" + this.realTimeTTSQueue.size());
        if (this.realTimeTTSQueue.isEmpty()) {
            return;
        }
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<AiChatDepository, Unit>() { // from class: com.glasssutdio.wear.depository.AiChatDepository.startRealTimeTTS.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AiChatDepository aiChatDepository) {
                invoke2(aiChatDepository);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AiChatDepository ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                String str = (String) ktxRunOnBgSingle.realTimeTTSQueue.take();
                Intrinsics.checkNotNull(str);
                String strRestoreTTSContent = ktxRunOnBgSingle.restoreTTSContent(str);
                XLog.m137i(strRestoreTTSContent);
                SparkChainRecognizer.getInstance().startTTS(strRestoreTTSContent);
            }
        });
    }

    public final AsrParamsBean switchAsrLanguage(String appLanguage) {
        Intrinsics.checkNotNullParameter(appLanguage, "appLanguage");
        if (StringsKt.startsWith$default(appLanguage, "zh", false, 2, (Object) null) || StringsKt.startsWith$default(appLanguage, "cn", false, 2, (Object) null) || StringsKt.startsWith$default(appLanguage, "hk", false, 2, (Object) null)) {
            return new AsrParamsBean("cn", "zh_cn", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "en", false, 2, (Object) null)) {
            return new AsrParamsBean("en", "en_us", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, Localization.language, false, 2, (Object) null)) {
            return new AsrParamsBean(Localization.language, "fr_fr", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "de", false, 2, (Object) null)) {
            return new AsrParamsBean("de", "de_de", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "ja", false, 2, (Object) null)) {
            return new AsrParamsBean("ja", "ja_jp", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "ko", false, 2, (Object) null)) {
            return new AsrParamsBean("ko", "ko_kr", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "es", false, 2, (Object) null)) {
            return new AsrParamsBean("es", "es_es", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "pt", false, 2, (Object) null)) {
            return new AsrParamsBean("pt", "pt_pt", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "ru", false, 2, (Object) null)) {
            return new AsrParamsBean("ru", "ru-ru", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "ar", false, 2, (Object) null)) {
            return new AsrParamsBean("ar", "ar_il", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "hi", false, 2, (Object) null)) {
            return new AsrParamsBean("hi", "hi_in", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "th", false, 2, (Object) null)) {
            return new AsrParamsBean("th", "th_th", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, BreakpointSQLiteKey.f521ID, false, 2, (Object) null)) {
            return new AsrParamsBean(BreakpointSQLiteKey.f521ID, "id_id", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "it", false, 2, (Object) null)) {
            return new AsrParamsBean("it", "it_it", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "vi", false, 2, (Object) null)) {
            return new AsrParamsBean("vi", "vi_vn", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "ms", false, 2, (Object) null)) {
            return new AsrParamsBean("ms", "ms_my", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "tr", false, 2, (Object) null)) {
            return new AsrParamsBean("tr", "tr_tr", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "fa", false, 2, (Object) null)) {
            return new AsrParamsBean("fa", "fa_ir", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "pl", false, 2, (Object) null)) {
            return new AsrParamsBean("pl", "pl_pl", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "pt", false, 2, (Object) null)) {
            return new AsrParamsBean("pt", "pt_pt", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "nl", false, 2, (Object) null)) {
            return new AsrParamsBean("nl", "nl_nl", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "sv", false, 2, (Object) null)) {
            return new AsrParamsBean("sv", "sv_se", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "cs", false, 2, (Object) null)) {
            return new AsrParamsBean("cs", "cs_cz", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "ro", false, 2, (Object) null)) {
            return new AsrParamsBean("ro", "ro_ro", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "ur", false, 2, (Object) null)) {
            return new AsrParamsBean("ur", "ur_in", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "hu", false, 2, (Object) null)) {
            return new AsrParamsBean("hu", "hu_hu", "mandarin");
        }
        if (StringsKt.startsWith$default(appLanguage, "el", false, 2, (Object) null)) {
            return new AsrParamsBean("el", "el_gr", "mandarin");
        }
        return new AsrParamsBean("en", "en_us", "mandarin");
    }

    /* compiled from: AiChatDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/depository/AiChatDepository$Companion;", "", "()V", "getInstance", "Lcom/glasssutdio/wear/depository/AiChatDepository;", "getGetInstance", "()Lcom/glasssutdio/wear/depository/AiChatDepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AiChatDepository getGetInstance() {
            return (AiChatDepository) AiChatDepository.getInstance$delegate.getValue();
        }
    }
}
