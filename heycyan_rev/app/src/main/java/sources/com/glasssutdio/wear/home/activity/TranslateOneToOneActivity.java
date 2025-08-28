package com.glasssutdio.wear.home.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.GlassApplication;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.ThreadExtKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.bean.QLanguageType;
import com.glasssutdio.wear.all.bean.SelectLanguageModel;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.AudioRecorderManager;
import com.glasssutdio.wear.all.utils.MoshiUtils;
import com.glasssutdio.wear.all.utils.NetWorkUtils;
import com.glasssutdio.wear.all.utils.PermissionUtilKt;
import com.glasssutdio.wear.all.utils.TypeToken;
import com.glasssutdio.wear.all.utils.time.Interval;
import com.glasssutdio.wear.bus.BluetoothEvent;
import com.glasssutdio.wear.bus.BusEvent;
import com.glasssutdio.wear.databinding.ActivityTranslateOneToOneBinding;
import com.glasssutdio.wear.databinding.PopupSelectLanguageBinding;
import com.glasssutdio.wear.depository.AiChatDepository;
import com.glasssutdio.wear.home.activity.TranslateOneToOneActivity;
import com.glasssutdio.wear.home.bean.SelectLanguageBean;
import com.glasssutdio.wear.home.viewmodel.AiTranslateVM;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import com.glasssutdio.wear.p003ai.adapter.TranslateOneAdapter;
import com.glasssutdio.wear.p003ai.bean.AsrSrcBean;
import com.glasssutdio.wear.p003ai.bean.TranslateBean;
import com.glasssutdio.wear.p003ai.bean.TranslateOneModel;
import com.glasssutdio.wear.p003ai.spark.AudioTrackManager;
import com.glasssutdio.wear.p003ai.spark.SparkChainRecognizer;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.TokenParser;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.squareup.moshi.JsonAdapter;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: TranslateOneToOneActivity.kt */
@Metadata(m606d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 P2\u00020\u0001:\u0004PQRSB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"H\u0002J\b\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020%H\u0002J\b\u0010'\u001a\u00020%H\u0002J\b\u0010(\u001a\u00020%H\u0003J\u0012\u0010)\u001a\u00020%2\b\u0010*\u001a\u0004\u0018\u00010+H\u0014J\b\u0010,\u001a\u00020%H\u0014J\u0018\u0010-\u001a\u00020%2\b\u0010.\u001a\u0004\u0018\u00010/2\u0006\u00100\u001a\u00020\u0011J\u0010\u00101\u001a\u00020%2\u0006\u00102\u001a\u000203H\u0017J\b\u00104\u001a\u00020%H\u0014J\u0010\u00105\u001a\u00020%2\b\u0010.\u001a\u0004\u0018\u00010/J\b\u00106\u001a\u00020%H\u0002J\u001a\u00107\u001a\u00020%2\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020%09J\b\u0010:\u001a\u00020%H\u0003J2\u0010;\u001a\u00020%2\u0006\u0010<\u001a\u00020\b2\b\u0010=\u001a\u0004\u0018\u00010/2\u0006\u0010>\u001a\u00020/2\u0006\u0010?\u001a\u00020\u00112\u0006\u0010.\u001a\u00020/H\u0002J\u0016\u0010@\u001a\u00020%2\u0006\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020CJ\u0010\u0010D\u001a\u00020%2\u0006\u0010E\u001a\u00020\u0011H\u0002J\u000e\u0010F\u001a\u00020\u00112\u0006\u0010G\u001a\u00020HJ\u0006\u0010I\u001a\u00020%J\b\u0010J\u001a\u00020%H\u0002J\u0018\u0010K\u001a\u00020%2\u0006\u0010L\u001a\u00020/2\u0006\u0010M\u001a\u00020/H\u0002J\u000e\u0010N\u001a\u00020\u00112\u0006\u0010G\u001a\u00020OR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001d\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006T"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/TranslateOneToOneActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "audioRecorder", "Lcom/glasssutdio/wear/all/utils/AudioRecorderManager;", "binding", "Lcom/glasssutdio/wear/databinding/ActivityTranslateOneToOneBinding;", "clickType", "", "defaultLanguageBottom", "Lcom/glasssutdio/wear/all/bean/QLanguageType;", "defaultLanguageTop", "delayEnd", "", "interval", "Lcom/glasssutdio/wear/all/utils/time/Interval;", "isBottomRcyPlaying", "", "isRecording", "lastClickType", "mAdapterBottom", "Lcom/glasssutdio/wear/ai/adapter/TranslateOneAdapter;", "mAdapterTop", "mViewModel", "Lcom/glasssutdio/wear/home/viewmodel/AiTranslateVM;", "getMViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/AiTranslateVM;", "mViewModel$delegate", "Lkotlin/Lazy;", "playingIndex", "recognizer", "Lcom/glasssutdio/wear/ai/spark/SparkChainRecognizer;", "ttsStart", "getLanguageData", "", "Lcom/glasssutdio/wear/all/bean/SelectLanguageModel;", "initView", "", "micClick", "micLongClick", "observer", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onEndPlayAudio", "sid", "", "complete", "onMessageEvent", "messageEvent", "Lcom/glasssutdio/wear/bus/BusEvent;", "onResume", "onStartPlayAudio", "pauseRecord", "ping", "callback", "Lkotlin/Function1;", "preRecord", "realTranslate", NotificationCompat.CATEGORY_STATUS, "content", "dest", "isRealTime", "scrollToBottom", "adapter", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "showOrHideAnimation", "bool", "srcIsAdd", "model", "Lcom/glasssutdio/wear/ai/bean/AsrSrcBean;", "startDelayEnd", "stopRecord", "textToTTS", "text", "languageCode", "translateIsAdd", "Lcom/glasssutdio/wear/ai/bean/TranslateBean;", "Companion", "MPlayStateCallback", "MRecordArsCallback", "RecordPermissionCallback", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class TranslateOneToOneActivity extends BaseSettingActivity {
    public static final int BUTTON_TYPE_BOTTOM = 1;
    public static final int BUTTON_TYPE_TOP = 2;
    public static final int BUTTON_TYPE_TOP_2 = 3;
    private AudioRecorderManager audioRecorder;
    private ActivityTranslateOneToOneBinding binding;
    private QLanguageType defaultLanguageBottom;
    private QLanguageType defaultLanguageTop;
    private Interval interval;
    private boolean isBottomRcyPlaying;
    private boolean isRecording;
    private int lastClickType;
    private TranslateOneAdapter mAdapterBottom;
    private TranslateOneAdapter mAdapterTop;

    /* renamed from: mViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mViewModel;
    private SparkChainRecognizer recognizer;
    private volatile boolean ttsStart;
    private int clickType = 1;
    private long delayEnd = 3000;
    private int playingIndex = -1;

    /* JADX WARN: Multi-variable type inference failed */
    public TranslateOneToOneActivity() {
        final TranslateOneToOneActivity translateOneToOneActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewModel = LazyKt.lazy(new Function0<AiTranslateVM>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.AiTranslateVM] */
            @Override // kotlin.jvm.functions.Function0
            public final AiTranslateVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(translateOneToOneActivity, Reflection.getOrCreateKotlinClass(AiTranslateVM.class), qualifier, objArr);
            }
        });
        this.defaultLanguageTop = QLanguageType.English;
        this.defaultLanguageBottom = QLanguageType.Chinese;
    }

    private final AiTranslateVM getMViewModel() {
        return (AiTranslateVM) this.mViewModel.getValue();
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityTranslateOneToOneBinding activityTranslateOneToOneBindingInflate = ActivityTranslateOneToOneBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityTranslateOneToOneBindingInflate, "inflate(...)");
        this.binding = activityTranslateOneToOneBindingInflate;
        SparkChainRecognizer sparkChainRecognizer = null;
        if (activityTranslateOneToOneBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateOneToOneBindingInflate = null;
        }
        setContentView(activityTranslateOneToOneBindingInflate.getRoot());
        SparkChainRecognizer sparkChainRecognizer2 = SparkChainRecognizer.getInstance();
        Intrinsics.checkNotNullExpressionValue(sparkChainRecognizer2, "getInstance(...)");
        this.recognizer = sparkChainRecognizer2;
        if (sparkChainRecognizer2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recognizer");
            sparkChainRecognizer2 = null;
        }
        sparkChainRecognizer2.setVoiceType(SparkChainRecognizer.TRANSLATE_ONE_TO_ONE);
        AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT());
        SparkChainRecognizer sparkChainRecognizer3 = this.recognizer;
        if (sparkChainRecognizer3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recognizer");
        } else {
            sparkChainRecognizer = sparkChainRecognizer3;
        }
        this.audioRecorder = new AudioRecorderManager(sparkChainRecognizer);
        initView();
        observer();
        LargeDataHandler.getInstance().speakSoundSwitch(true);
    }

    public final boolean srcIsAdd(AsrSrcBean model) {
        Intrinsics.checkNotNullParameter(model, "model");
        TranslateOneAdapter translateOneAdapter = null;
        if (this.clickType == 1) {
            TranslateOneAdapter translateOneAdapter2 = this.mAdapterBottom;
            if (translateOneAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
                translateOneAdapter2 = null;
            }
            if (translateOneAdapter2.getData().isEmpty() || model.isNotRealTime()) {
                return true;
            }
            TranslateOneAdapter translateOneAdapter3 = this.mAdapterBottom;
            if (translateOneAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
                translateOneAdapter3 = null;
            }
            List<TranslateOneModel> data = translateOneAdapter3.getData();
            TranslateOneAdapter translateOneAdapter4 = this.mAdapterBottom;
            if (translateOneAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
            } else {
                translateOneAdapter = translateOneAdapter4;
            }
            return !Intrinsics.areEqual(data.get(translateOneAdapter.getData().size() - 1).getSid(), model.getSid());
        }
        TranslateOneAdapter translateOneAdapter5 = this.mAdapterTop;
        if (translateOneAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
            translateOneAdapter5 = null;
        }
        if (translateOneAdapter5.getData().isEmpty() || model.isNotRealTime()) {
            return true;
        }
        TranslateOneAdapter translateOneAdapter6 = this.mAdapterTop;
        if (translateOneAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
            translateOneAdapter6 = null;
        }
        List<TranslateOneModel> data2 = translateOneAdapter6.getData();
        TranslateOneAdapter translateOneAdapter7 = this.mAdapterTop;
        if (translateOneAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
        } else {
            translateOneAdapter = translateOneAdapter7;
        }
        return !Intrinsics.areEqual(data2.get(translateOneAdapter.getData().size() - 1).getSid(), model.getSid());
    }

    public final boolean translateIsAdd(TranslateBean model) {
        Intrinsics.checkNotNullParameter(model, "model");
        TranslateOneAdapter translateOneAdapter = null;
        if (this.clickType == 1) {
            TranslateOneAdapter translateOneAdapter2 = this.mAdapterTop;
            if (translateOneAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                translateOneAdapter2 = null;
            }
            if (translateOneAdapter2.getData().isEmpty() || model.isNotRealTime()) {
                return true;
            }
            TranslateOneAdapter translateOneAdapter3 = this.mAdapterTop;
            if (translateOneAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                translateOneAdapter3 = null;
            }
            List<TranslateOneModel> data = translateOneAdapter3.getData();
            TranslateOneAdapter translateOneAdapter4 = this.mAdapterTop;
            if (translateOneAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
            } else {
                translateOneAdapter = translateOneAdapter4;
            }
            TranslateOneModel translateOneModel = data.get(translateOneAdapter.getData().size() - 1);
            String sid = model.getSid();
            Intrinsics.checkNotNullExpressionValue(sid, "getSid(...)");
            if (sid.length() == 0 && model.getStatus() == 3) {
                XLog.m137i("交互sid特殊情况无需添加新的:，" + model.getTrans_result().getDst() + TokenParser.f390SP);
                return false;
            }
            XLog.m137i("交互sid是否添加=：" + (!Intrinsics.areEqual(translateOneModel.getSid(), model.getSid())) + " ，" + model.getTrans_result().getDst() + TokenParser.f390SP);
            XLog.m137i("交互sid是否添加：" + translateOneModel.getSid() + "\n " + model.getSid());
            return !Intrinsics.areEqual(translateOneModel.getSid(), model.getSid());
        }
        TranslateOneAdapter translateOneAdapter5 = this.mAdapterBottom;
        if (translateOneAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
            translateOneAdapter5 = null;
        }
        if (translateOneAdapter5.getData().isEmpty() || model.isNotRealTime()) {
            return true;
        }
        TranslateOneAdapter translateOneAdapter6 = this.mAdapterBottom;
        if (translateOneAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
            translateOneAdapter6 = null;
        }
        List<TranslateOneModel> data2 = translateOneAdapter6.getData();
        TranslateOneAdapter translateOneAdapter7 = this.mAdapterBottom;
        if (translateOneAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
        } else {
            translateOneAdapter = translateOneAdapter7;
        }
        TranslateOneModel translateOneModel2 = data2.get(translateOneAdapter.getData().size() - 1);
        String sid2 = model.getSid();
        Intrinsics.checkNotNullExpressionValue(sid2, "getSid(...)");
        if (sid2.length() == 0 && model.getStatus() == 3) {
            return false;
        }
        return !Intrinsics.areEqual(translateOneModel2.getSid(), model.getSid());
    }

    public final void scrollToBottom(TranslateOneAdapter adapter, RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        final Context context = recyclerView.getContext();
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(context) { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$scrollToBottom$smoothScroller$1
            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            protected int getVerticalSnapPreference() {
                return 1;
            }
        };
        linearSmoothScroller.setTargetPosition(adapter.getItemCount() - 1);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager != null) {
            layoutManager.startSmoothScroll(linearSmoothScroller);
        }
    }

    private final void observer() {
        AiTranslateVM mViewModel = getMViewModel();
        TranslateOneToOneActivity translateOneToOneActivity = this;
        mViewModel.getTranslateSrcLD().observe(translateOneToOneActivity, new TranslateOneToOneActivity$sam$androidx_lifecycle_Observer$0(new Function1<AsrSrcBean, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$observer$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AsrSrcBean asrSrcBean) {
                invoke2(asrSrcBean);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AsrSrcBean asrSrcBean) {
                this.this$0.startDelayEnd();
                ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding = null;
                if (this.this$0.clickType == 1) {
                    TranslateOneToOneActivity translateOneToOneActivity2 = this.this$0;
                    Intrinsics.checkNotNull(asrSrcBean);
                    if (translateOneToOneActivity2.srcIsAdd(asrSrcBean)) {
                        TranslateOneModel translateOneModel = new TranslateOneModel(1, asrSrcBean.getContent(), false, true, this.this$0.defaultLanguageBottom.getCode(), asrSrcBean.getSid(), asrSrcBean.getStatus(), 4, null);
                        TranslateOneAdapter translateOneAdapter = this.this$0.mAdapterBottom;
                        if (translateOneAdapter == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
                            translateOneAdapter = null;
                        }
                        translateOneAdapter.addTheLast(translateOneModel);
                        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding2 = this.this$0.binding;
                        if (activityTranslateOneToOneBinding2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityTranslateOneToOneBinding2 = null;
                        }
                        ViewKt.gone(activityTranslateOneToOneBinding2.etBottom);
                        XLog.m137i("srcBottom11111:" + translateOneModel.getContent());
                        TranslateOneAdapter translateOneAdapter2 = this.this$0.mAdapterBottom;
                        if (translateOneAdapter2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
                            translateOneAdapter2 = null;
                        }
                        if (translateOneAdapter2.getData().isEmpty()) {
                            return;
                        }
                        TranslateOneToOneActivity translateOneToOneActivity3 = this.this$0;
                        TranslateOneAdapter translateOneAdapter3 = translateOneToOneActivity3.mAdapterBottom;
                        if (translateOneAdapter3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
                            translateOneAdapter3 = null;
                        }
                        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding3 = this.this$0.binding;
                        if (activityTranslateOneToOneBinding3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityTranslateOneToOneBinding = activityTranslateOneToOneBinding3;
                        }
                        RecyclerView rcyBottom = activityTranslateOneToOneBinding.rcyBottom;
                        Intrinsics.checkNotNullExpressionValue(rcyBottom, "rcyBottom");
                        translateOneToOneActivity3.scrollToBottom(translateOneAdapter3, rcyBottom);
                        return;
                    }
                    XLog.m137i("srcBottom22222:" + asrSrcBean);
                    TranslateOneAdapter translateOneAdapter4 = this.this$0.mAdapterBottom;
                    if (translateOneAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
                        translateOneAdapter4 = null;
                    }
                    translateOneAdapter4.addTheLastTextStream(asrSrcBean.getContent());
                    ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding4 = this.this$0.binding;
                    if (activityTranslateOneToOneBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityTranslateOneToOneBinding4 = null;
                    }
                    ViewKt.gone(activityTranslateOneToOneBinding4.etBottom);
                    TranslateOneAdapter translateOneAdapter5 = this.this$0.mAdapterBottom;
                    if (translateOneAdapter5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
                        translateOneAdapter5 = null;
                    }
                    if (translateOneAdapter5.getData().isEmpty()) {
                        return;
                    }
                    TranslateOneToOneActivity translateOneToOneActivity4 = this.this$0;
                    TranslateOneAdapter translateOneAdapter6 = translateOneToOneActivity4.mAdapterBottom;
                    if (translateOneAdapter6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
                        translateOneAdapter6 = null;
                    }
                    ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding5 = this.this$0.binding;
                    if (activityTranslateOneToOneBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityTranslateOneToOneBinding = activityTranslateOneToOneBinding5;
                    }
                    RecyclerView rcyBottom2 = activityTranslateOneToOneBinding.rcyBottom;
                    Intrinsics.checkNotNullExpressionValue(rcyBottom2, "rcyBottom");
                    translateOneToOneActivity4.scrollToBottom(translateOneAdapter6, rcyBottom2);
                    return;
                }
                TranslateOneToOneActivity translateOneToOneActivity5 = this.this$0;
                Intrinsics.checkNotNull(asrSrcBean);
                if (!translateOneToOneActivity5.srcIsAdd(asrSrcBean)) {
                    TranslateOneAdapter translateOneAdapter7 = this.this$0.mAdapterTop;
                    if (translateOneAdapter7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                        translateOneAdapter7 = null;
                    }
                    translateOneAdapter7.addTheLastTextStream(asrSrcBean.getContent());
                    ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding6 = this.this$0.binding;
                    if (activityTranslateOneToOneBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityTranslateOneToOneBinding6 = null;
                    }
                    ViewKt.gone(activityTranslateOneToOneBinding6.etTop);
                    TranslateOneAdapter translateOneAdapter8 = this.this$0.mAdapterTop;
                    if (translateOneAdapter8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                        translateOneAdapter8 = null;
                    }
                    if (translateOneAdapter8.getData().isEmpty()) {
                        return;
                    }
                    TranslateOneToOneActivity translateOneToOneActivity6 = this.this$0;
                    TranslateOneAdapter translateOneAdapter9 = translateOneToOneActivity6.mAdapterTop;
                    if (translateOneAdapter9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                        translateOneAdapter9 = null;
                    }
                    ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding7 = this.this$0.binding;
                    if (activityTranslateOneToOneBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityTranslateOneToOneBinding = activityTranslateOneToOneBinding7;
                    }
                    RecyclerView rcyTop = activityTranslateOneToOneBinding.rcyTop;
                    Intrinsics.checkNotNullExpressionValue(rcyTop, "rcyTop");
                    translateOneToOneActivity6.scrollToBottom(translateOneAdapter9, rcyTop);
                    return;
                }
                TranslateOneModel translateOneModel2 = new TranslateOneModel(1, asrSrcBean.getContent(), false, false, this.this$0.defaultLanguageTop.getCode(), asrSrcBean.getSid(), asrSrcBean.getStatus(), 4, null);
                TranslateOneAdapter translateOneAdapter10 = this.this$0.mAdapterTop;
                if (translateOneAdapter10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                    translateOneAdapter10 = null;
                }
                translateOneAdapter10.addTheLast(translateOneModel2);
                ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding8 = this.this$0.binding;
                if (activityTranslateOneToOneBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityTranslateOneToOneBinding8 = null;
                }
                ViewKt.gone(activityTranslateOneToOneBinding8.etTop);
                TranslateOneAdapter translateOneAdapter11 = this.this$0.mAdapterTop;
                if (translateOneAdapter11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                    translateOneAdapter11 = null;
                }
                if (translateOneAdapter11.getData().isEmpty()) {
                    return;
                }
                TranslateOneToOneActivity translateOneToOneActivity7 = this.this$0;
                TranslateOneAdapter translateOneAdapter12 = translateOneToOneActivity7.mAdapterTop;
                if (translateOneAdapter12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                    translateOneAdapter12 = null;
                }
                ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding9 = this.this$0.binding;
                if (activityTranslateOneToOneBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityTranslateOneToOneBinding = activityTranslateOneToOneBinding9;
                }
                RecyclerView rcyTop2 = activityTranslateOneToOneBinding.rcyTop;
                Intrinsics.checkNotNullExpressionValue(rcyTop2, "rcyTop");
                translateOneToOneActivity7.scrollToBottom(translateOneAdapter12, rcyTop2);
            }
        }));
        mViewModel.getTranslateLD().observe(translateOneToOneActivity, new TranslateOneToOneActivity$sam$androidx_lifecycle_Observer$0(new Function1<TranslateBean, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$observer$1$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TranslateBean translateBean) {
                invoke2(translateBean);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TranslateBean translateBean) {
                String dst = translateBean.getTrans_result().getDst();
                if (dst == null) {
                    dst = HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR;
                }
                String str = dst;
                if (this.this$0.clickType == 1) {
                    TranslateOneToOneActivity translateOneToOneActivity2 = this.this$0;
                    Intrinsics.checkNotNull(translateBean);
                    if (translateOneToOneActivity2.translateIsAdd(translateBean)) {
                        String code = this.this$0.defaultLanguageTop.getCode();
                        String sid = translateBean.getSid();
                        Intrinsics.checkNotNullExpressionValue(sid, "getSid(...)");
                        TranslateOneModel translateOneModel = new TranslateOneModel(0, str, false, false, code, sid, translateBean.getStatus(), 4, null);
                        TranslateOneAdapter translateOneAdapter = this.this$0.mAdapterTop;
                        if (translateOneAdapter == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                            translateOneAdapter = null;
                        }
                        translateOneAdapter.addTheLast(translateOneModel);
                        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding = this.this$0.binding;
                        if (activityTranslateOneToOneBinding == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityTranslateOneToOneBinding = null;
                        }
                        ViewKt.gone(activityTranslateOneToOneBinding.etTop);
                        TranslateOneAdapter translateOneAdapter2 = this.this$0.mAdapterTop;
                        if (translateOneAdapter2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                            translateOneAdapter2 = null;
                        }
                        if (translateOneAdapter2.getData().isEmpty()) {
                            return;
                        }
                        TranslateOneToOneActivity translateOneToOneActivity3 = this.this$0;
                        TranslateOneAdapter translateOneAdapter3 = translateOneToOneActivity3.mAdapterTop;
                        if (translateOneAdapter3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                            translateOneAdapter3 = null;
                        }
                        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding2 = this.this$0.binding;
                        if (activityTranslateOneToOneBinding2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityTranslateOneToOneBinding2 = null;
                        }
                        RecyclerView rcyTop = activityTranslateOneToOneBinding2.rcyTop;
                        Intrinsics.checkNotNullExpressionValue(rcyTop, "rcyTop");
                        translateOneToOneActivity3.scrollToBottom(translateOneAdapter3, rcyTop);
                        return;
                    }
                    XLog.m137i("transTop22222:" + translateBean);
                    TranslateOneAdapter translateOneAdapter4 = this.this$0.mAdapterTop;
                    if (translateOneAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                        translateOneAdapter4 = null;
                    }
                    translateOneAdapter4.addTheLastTextStream(str);
                    ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding3 = this.this$0.binding;
                    if (activityTranslateOneToOneBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityTranslateOneToOneBinding3 = null;
                    }
                    ViewKt.gone(activityTranslateOneToOneBinding3.etTop);
                    TranslateOneAdapter translateOneAdapter5 = this.this$0.mAdapterTop;
                    if (translateOneAdapter5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                        translateOneAdapter5 = null;
                    }
                    if (translateOneAdapter5.getData().isEmpty()) {
                        return;
                    }
                    TranslateOneToOneActivity translateOneToOneActivity4 = this.this$0;
                    TranslateOneAdapter translateOneAdapter6 = translateOneToOneActivity4.mAdapterTop;
                    if (translateOneAdapter6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                        translateOneAdapter6 = null;
                    }
                    ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding4 = this.this$0.binding;
                    if (activityTranslateOneToOneBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityTranslateOneToOneBinding4 = null;
                    }
                    RecyclerView rcyTop2 = activityTranslateOneToOneBinding4.rcyTop;
                    Intrinsics.checkNotNullExpressionValue(rcyTop2, "rcyTop");
                    translateOneToOneActivity4.scrollToBottom(translateOneAdapter6, rcyTop2);
                    return;
                }
                TranslateOneToOneActivity translateOneToOneActivity5 = this.this$0;
                Intrinsics.checkNotNull(translateBean);
                if (translateOneToOneActivity5.translateIsAdd(translateBean)) {
                    String code2 = this.this$0.defaultLanguageBottom.getCode();
                    String sid2 = translateBean.getSid();
                    Intrinsics.checkNotNullExpressionValue(sid2, "getSid(...)");
                    TranslateOneModel translateOneModel2 = new TranslateOneModel(0, str, false, true, code2, sid2, translateBean.getStatus(), 4, null);
                    TranslateOneAdapter translateOneAdapter7 = this.this$0.mAdapterBottom;
                    if (translateOneAdapter7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
                        translateOneAdapter7 = null;
                    }
                    translateOneAdapter7.addTheLast(translateOneModel2);
                    ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding5 = this.this$0.binding;
                    if (activityTranslateOneToOneBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityTranslateOneToOneBinding5 = null;
                    }
                    ViewKt.gone(activityTranslateOneToOneBinding5.etBottom);
                    TranslateOneAdapter translateOneAdapter8 = this.this$0.mAdapterTop;
                    if (translateOneAdapter8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                        translateOneAdapter8 = null;
                    }
                    if (translateOneAdapter8.getData().isEmpty()) {
                        return;
                    }
                    TranslateOneToOneActivity translateOneToOneActivity6 = this.this$0;
                    TranslateOneAdapter translateOneAdapter9 = translateOneToOneActivity6.mAdapterBottom;
                    if (translateOneAdapter9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
                        translateOneAdapter9 = null;
                    }
                    ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding6 = this.this$0.binding;
                    if (activityTranslateOneToOneBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityTranslateOneToOneBinding6 = null;
                    }
                    RecyclerView rcyBottom = activityTranslateOneToOneBinding6.rcyBottom;
                    Intrinsics.checkNotNullExpressionValue(rcyBottom, "rcyBottom");
                    translateOneToOneActivity6.scrollToBottom(translateOneAdapter9, rcyBottom);
                    return;
                }
                XLog.m137i("transBottom22222:" + translateBean);
                TranslateOneAdapter translateOneAdapter10 = this.this$0.mAdapterBottom;
                if (translateOneAdapter10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
                    translateOneAdapter10 = null;
                }
                translateOneAdapter10.addTheLastTextStream(str);
                ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding7 = this.this$0.binding;
                if (activityTranslateOneToOneBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityTranslateOneToOneBinding7 = null;
                }
                ViewKt.gone(activityTranslateOneToOneBinding7.etBottom);
                TranslateOneAdapter translateOneAdapter11 = this.this$0.mAdapterTop;
                if (translateOneAdapter11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                    translateOneAdapter11 = null;
                }
                if (translateOneAdapter11.getData().isEmpty()) {
                    return;
                }
                TranslateOneToOneActivity translateOneToOneActivity7 = this.this$0;
                TranslateOneAdapter translateOneAdapter12 = translateOneToOneActivity7.mAdapterBottom;
                if (translateOneAdapter12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
                    translateOneAdapter12 = null;
                }
                ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding8 = this.this$0.binding;
                if (activityTranslateOneToOneBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityTranslateOneToOneBinding8 = null;
                }
                RecyclerView rcyBottom2 = activityTranslateOneToOneBinding8.rcyBottom;
                Intrinsics.checkNotNullExpressionValue(rcyBottom2, "rcyBottom");
                translateOneToOneActivity7.scrollToBottom(translateOneAdapter12, rcyBottom2);
            }
        }));
        mViewModel.getTranslateFailLD().observe(translateOneToOneActivity, new TranslateOneToOneActivity$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$observer$1$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean bool) {
                String string = this.this$0.getString(C0775R.string.g_translate_12);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                GlobalKt.showToast$default(string, 0, 1, null);
            }
        }));
    }

    public final void startDelayEnd() {
        XLog.m137i("开始倒计时");
        Interval interval = this.interval;
        if (interval != null) {
            interval.cancel();
        }
        Interval intervalLife$default = Interval.life$default(new Interval(0L, this.delayEnd, TimeUnit.MILLISECONDS, 1L, 0L, 16, null), this, (Lifecycle.Event) null, 2, (Object) null);
        this.interval = intervalLife$default;
        if (intervalLife$default != null) {
            intervalLife$default.subscribe(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$startDelayEnd$1$1
                public final void invoke(Interval subscribe, long j) {
                    Intrinsics.checkNotNullParameter(subscribe, "$this$subscribe");
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Interval interval2, Long l) {
                    invoke(interval2, l.longValue());
                    return Unit.INSTANCE;
                }
            }).finish(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$startDelayEnd$1$2
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Interval interval2, Long l) {
                    invoke(interval2, l.longValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Interval finish, long j) {
                    Intrinsics.checkNotNullParameter(finish, "$this$finish");
                    XLog.m137i("停止录制");
                    this.this$0.stopRecord();
                }
            }).start();
        }
    }

    private final void initView() {
        final ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding = this.binding;
        TranslateOneAdapter translateOneAdapter = null;
        if (activityTranslateOneToOneBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateOneToOneBinding = null;
        }
        activityTranslateOneToOneBinding.title.tvTitle.setText(getString(C0775R.string.g_translate_3));
        activityTranslateOneToOneBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TranslateOneToOneActivity.initView$lambda$6$lambda$2(this.f$0, view);
            }
        });
        SparkChainRecognizer.getInstance().initStartTTS();
        String languageJson = UserConfig.INSTANCE.getInstance().getLanguageJson();
        if (languageJson.length() > 0) {
            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<SelectLanguageBean>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$initView$lambda$6$$inlined$fromJson$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "adapter(...)");
            SelectLanguageBean selectLanguageBean = (SelectLanguageBean) jsonAdapterAdapter.fromJson(languageJson);
            if (selectLanguageBean != null) {
                this.defaultLanguageTop = selectLanguageBean.getOneTop();
                this.defaultLanguageBottom = selectLanguageBean.getOneBottom();
            }
        }
        this.mAdapterTop = new TranslateOneAdapter();
        TranslateOneToOneActivity translateOneToOneActivity = this;
        activityTranslateOneToOneBinding.rcyTop.setLayoutManager(new LinearLayoutManager(translateOneToOneActivity));
        activityTranslateOneToOneBinding.rcyTop.setItemAnimator(null);
        RecyclerView recyclerView = activityTranslateOneToOneBinding.rcyTop;
        TranslateOneAdapter translateOneAdapter2 = this.mAdapterTop;
        if (translateOneAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
            translateOneAdapter2 = null;
        }
        recyclerView.setAdapter(translateOneAdapter2);
        TranslateOneAdapter translateOneAdapter3 = this.mAdapterTop;
        if (translateOneAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
            translateOneAdapter3 = null;
        }
        translateOneAdapter3.setOnItemPlayClickListener(new Function3<View, TranslateOneModel, Integer, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$initView$1$2
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(View view, TranslateOneModel translateOneModel, Integer num) throws IllegalStateException {
                invoke(view, translateOneModel, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(View view, TranslateOneModel model, int i) throws IllegalStateException {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(model, "model");
                TranslateOneAdapter translateOneAdapter4 = null;
                if (!model.isPlaying()) {
                    if (!this.this$0.isRecording) {
                        this.this$0.isBottomRcyPlaying = false;
                        this.this$0.playingIndex = i;
                        TranslateOneAdapter translateOneAdapter5 = this.this$0.mAdapterTop;
                        if (translateOneAdapter5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                            translateOneAdapter5 = null;
                        }
                        List<TranslateOneModel> data = translateOneAdapter5.getData();
                        TranslateOneToOneActivity translateOneToOneActivity2 = this.this$0;
                        int i2 = 0;
                        for (Object obj : data) {
                            int i3 = i2 + 1;
                            if (i2 < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            TranslateOneModel translateOneModel = (TranslateOneModel) obj;
                            if (translateOneModel.isPlaying()) {
                                translateOneModel.setPlaying(false);
                                AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).restartPlay();
                                SparkChainRecognizer.getInstance().stopTTS();
                                TranslateOneAdapter translateOneAdapter6 = translateOneToOneActivity2.mAdapterTop;
                                if (translateOneAdapter6 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                                    translateOneAdapter6 = null;
                                }
                                translateOneAdapter6.refreshPlayingStatus(i2);
                            }
                            i2 = i3;
                        }
                        TranslateOneAdapter translateOneAdapter7 = this.this$0.mAdapterBottom;
                        if (translateOneAdapter7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
                            translateOneAdapter7 = null;
                        }
                        List<TranslateOneModel> data2 = translateOneAdapter7.getData();
                        TranslateOneToOneActivity translateOneToOneActivity3 = this.this$0;
                        int i4 = 0;
                        for (Object obj2 : data2) {
                            int i5 = i4 + 1;
                            if (i4 < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            TranslateOneModel translateOneModel2 = (TranslateOneModel) obj2;
                            if (translateOneModel2.isPlaying()) {
                                translateOneModel2.setPlaying(false);
                                AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).restartPlay();
                                SparkChainRecognizer.getInstance().stopTTS();
                                TranslateOneAdapter translateOneAdapter8 = translateOneToOneActivity3.mAdapterBottom;
                                if (translateOneAdapter8 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
                                    translateOneAdapter8 = null;
                                }
                                translateOneAdapter8.refreshPlayingStatus(i4);
                            }
                            i4 = i5;
                        }
                        model.setPlaying(true);
                        this.this$0.textToTTS(model.getContent(), model.getLanguageCode());
                    } else {
                        String string = this.this$0.getString(C0775R.string.g_translate_13);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                        GlobalKt.showToast$default(string, 0, 1, null);
                        return;
                    }
                } else {
                    model.setPlaying(false);
                    this.this$0.playingIndex = -1;
                    AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).restartPlay();
                }
                TranslateOneAdapter translateOneAdapter9 = this.this$0.mAdapterTop;
                if (translateOneAdapter9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                } else {
                    translateOneAdapter4 = translateOneAdapter9;
                }
                translateOneAdapter4.refreshPlayingStatus(i);
            }
        });
        this.mAdapterBottom = new TranslateOneAdapter();
        activityTranslateOneToOneBinding.rcyBottom.setLayoutManager(new LinearLayoutManager(translateOneToOneActivity));
        activityTranslateOneToOneBinding.rcyBottom.setItemAnimator(null);
        RecyclerView recyclerView2 = activityTranslateOneToOneBinding.rcyBottom;
        TranslateOneAdapter translateOneAdapter4 = this.mAdapterBottom;
        if (translateOneAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
            translateOneAdapter4 = null;
        }
        recyclerView2.setAdapter(translateOneAdapter4);
        TranslateOneAdapter translateOneAdapter5 = this.mAdapterBottom;
        if (translateOneAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
        } else {
            translateOneAdapter = translateOneAdapter5;
        }
        translateOneAdapter.setOnItemPlayClickListener(new Function3<View, TranslateOneModel, Integer, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$initView$1$3
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(View view, TranslateOneModel translateOneModel, Integer num) throws IllegalStateException {
                invoke(view, translateOneModel, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(View view, TranslateOneModel model, int i) throws IllegalStateException {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(model, "model");
                TranslateOneAdapter translateOneAdapter6 = null;
                if (!model.isPlaying()) {
                    if (!this.this$0.isRecording) {
                        this.this$0.isBottomRcyPlaying = true;
                        this.this$0.playingIndex = i;
                        TranslateOneAdapter translateOneAdapter7 = this.this$0.mAdapterTop;
                        if (translateOneAdapter7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                            translateOneAdapter7 = null;
                        }
                        List<TranslateOneModel> data = translateOneAdapter7.getData();
                        TranslateOneToOneActivity translateOneToOneActivity2 = this.this$0;
                        int i2 = 0;
                        for (Object obj : data) {
                            int i3 = i2 + 1;
                            if (i2 < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            TranslateOneModel translateOneModel = (TranslateOneModel) obj;
                            if (translateOneModel.isPlaying()) {
                                translateOneModel.setPlaying(false);
                                AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).restartPlay();
                                SparkChainRecognizer.getInstance().stopTTS();
                                TranslateOneAdapter translateOneAdapter8 = translateOneToOneActivity2.mAdapterTop;
                                if (translateOneAdapter8 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
                                    translateOneAdapter8 = null;
                                }
                                translateOneAdapter8.refreshPlayingStatus(i2);
                            }
                            i2 = i3;
                        }
                        TranslateOneAdapter translateOneAdapter9 = this.this$0.mAdapterBottom;
                        if (translateOneAdapter9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
                            translateOneAdapter9 = null;
                        }
                        List<TranslateOneModel> data2 = translateOneAdapter9.getData();
                        TranslateOneToOneActivity translateOneToOneActivity3 = this.this$0;
                        int i4 = 0;
                        for (Object obj2 : data2) {
                            int i5 = i4 + 1;
                            if (i4 < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            TranslateOneModel translateOneModel2 = (TranslateOneModel) obj2;
                            if (translateOneModel2.isPlaying()) {
                                translateOneModel2.setPlaying(false);
                                AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).restartPlay();
                                SparkChainRecognizer.getInstance().stopTTS();
                                TranslateOneAdapter translateOneAdapter10 = translateOneToOneActivity3.mAdapterBottom;
                                if (translateOneAdapter10 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
                                    translateOneAdapter10 = null;
                                }
                                translateOneAdapter10.refreshPlayingStatus(i4);
                            }
                            i4 = i5;
                        }
                        model.setPlaying(true);
                        this.this$0.textToTTS(model.getContent(), model.getLanguageCode());
                    } else {
                        String string = this.this$0.getString(C0775R.string.g_translate_13);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                        GlobalKt.showToast$default(string, 0, 1, null);
                        return;
                    }
                } else {
                    model.setPlaying(false);
                    this.this$0.playingIndex = -1;
                    AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).restartPlay();
                    SparkChainRecognizer.getInstance().stopTTS();
                }
                TranslateOneAdapter translateOneAdapter11 = this.this$0.mAdapterBottom;
                if (translateOneAdapter11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
                } else {
                    translateOneAdapter6 = translateOneAdapter11;
                }
                translateOneAdapter6.refreshPlayingStatus(i);
            }
        });
        activityTranslateOneToOneBinding.tvLanguageTop.setText(this.defaultLanguageTop.getLanguageName());
        activityTranslateOneToOneBinding.tvLanguageBottom.setText(this.defaultLanguageBottom.getLanguageName());
        activityTranslateOneToOneBinding.title.tvTitle.setText(getString(C0775R.string.g_translate_2));
        activityTranslateOneToOneBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TranslateOneToOneActivity.initView$lambda$6$lambda$3(this.f$0, view);
            }
        });
        micClick();
        activityTranslateOneToOneBinding.tvLanguageTop.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                TranslateOneToOneActivity.initView$lambda$6$lambda$4(this.f$0, activityTranslateOneToOneBinding, view);
            }
        });
        activityTranslateOneToOneBinding.tvLanguageBottom.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                TranslateOneToOneActivity.initView$lambda$6$lambda$5(this.f$0, activityTranslateOneToOneBinding, view);
            }
        });
        SparkChainRecognizer.getInstance().setPlayStateCallback(new MPlayStateCallback(new WeakReference(this)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$6$lambda$2(TranslateOneToOneActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$6$lambda$3(TranslateOneToOneActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$6$lambda$4(TranslateOneToOneActivity this$0, ActivityTranslateOneToOneBinding this_run, View view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        TranslateOneToOneActivity translateOneToOneActivity = this$0;
        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding = this$0.binding;
        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding2 = null;
        if (activityTranslateOneToOneBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateOneToOneBinding = null;
        }
        int width = activityTranslateOneToOneBinding.clsTop.getWidth();
        TranslateOneToOneActivity$initView$1$5$popup$1 translateOneToOneActivity$initView$1$5$popup$1 = new TranslateOneToOneActivity$initView$1$5$popup$1(this$0, this_run);
        Object objInvoke = PopupSelectLanguageBinding.class.getMethod("inflate", LayoutInflater.class).invoke(null, LayoutInflater.from(translateOneToOneActivity));
        if (objInvoke == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.glasssutdio.wear.databinding.PopupSelectLanguageBinding");
        }
        PopupSelectLanguageBinding popupSelectLanguageBinding = (PopupSelectLanguageBinding) objInvoke;
        PopupWindow popupWindow = new PopupWindow(popupSelectLanguageBinding.getRoot(), width, -2, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setSoftInputMode(20);
        popupWindow.setOutsideTouchable(true);
        translateOneToOneActivity$initView$1$5$popup$1.invoke((TranslateOneToOneActivity$initView$1$5$popup$1) popupSelectLanguageBinding, (PopupSelectLanguageBinding) popupWindow);
        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding3 = this$0.binding;
        if (activityTranslateOneToOneBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityTranslateOneToOneBinding2 = activityTranslateOneToOneBinding3;
        }
        popupWindow.showAsDropDown(activityTranslateOneToOneBinding2.clsMenuTop, 0, GlobalKt.getDp((Number) 4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$6$lambda$5(TranslateOneToOneActivity this$0, ActivityTranslateOneToOneBinding this_run, View view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        TranslateOneToOneActivity translateOneToOneActivity = this$0;
        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding = this$0.binding;
        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding2 = null;
        if (activityTranslateOneToOneBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateOneToOneBinding = null;
        }
        int width = activityTranslateOneToOneBinding.clsTop.getWidth();
        TranslateOneToOneActivity$initView$1$6$popup$1 translateOneToOneActivity$initView$1$6$popup$1 = new TranslateOneToOneActivity$initView$1$6$popup$1(this$0, this_run);
        Object objInvoke = PopupSelectLanguageBinding.class.getMethod("inflate", LayoutInflater.class).invoke(null, LayoutInflater.from(translateOneToOneActivity));
        if (objInvoke == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.glasssutdio.wear.databinding.PopupSelectLanguageBinding");
        }
        PopupSelectLanguageBinding popupSelectLanguageBinding = (PopupSelectLanguageBinding) objInvoke;
        PopupWindow popupWindow = new PopupWindow(popupSelectLanguageBinding.getRoot(), width, -2, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setSoftInputMode(20);
        popupWindow.setOutsideTouchable(true);
        translateOneToOneActivity$initView$1$6$popup$1.invoke((TranslateOneToOneActivity$initView$1$6$popup$1) popupSelectLanguageBinding, (PopupSelectLanguageBinding) popupWindow);
        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding3 = this$0.binding;
        if (activityTranslateOneToOneBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityTranslateOneToOneBinding2 = activityTranslateOneToOneBinding3;
        }
        popupWindow.showAsDropDown(activityTranslateOneToOneBinding2.clsMenuBottom, 0, -(GlobalKt.getDp((Number) 4) + GlobalKt.getDp((Number) 540) + GlobalKt.getDp((Number) 50)));
    }

    private final void micClick() {
        final ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding = this.binding;
        if (activityTranslateOneToOneBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateOneToOneBinding = null;
        }
        ImageView ivMicBottom = activityTranslateOneToOneBinding.ivMicBottom;
        Intrinsics.checkNotNullExpressionValue(ivMicBottom, "ivMicBottom");
        ViewKt.click$default(ivMicBottom, 0L, new Function1<View, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$micClick$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) throws Exception {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) throws Exception {
                Intrinsics.checkNotNullParameter(it, "it");
                SparkChainRecognizer sparkChainRecognizer = null;
                if (!NetWorkUtils.INSTANCE.isNetworkAvailable(this.this$0)) {
                    String string = this.this$0.getString(C0775R.string.ai_glass_1);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    GlobalKt.showToast$default(string, 0, 1, null);
                } else {
                    if (ContextCompat.checkSelfPermission(this.this$0, Permission.RECORD_AUDIO) == 0) {
                        SparkChainRecognizer sparkChainRecognizer2 = this.this$0.recognizer;
                        if (sparkChainRecognizer2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("recognizer");
                        } else {
                            sparkChainRecognizer = sparkChainRecognizer2;
                        }
                        sparkChainRecognizer.setVoiceType(SparkChainRecognizer.TRANSLATE_ONE_TO_ONE);
                        if (!this.this$0.isRecording) {
                            activityTranslateOneToOneBinding.ivMicTop.setEnabled(false);
                            activityTranslateOneToOneBinding.ivMicTop2.setEnabled(false);
                        }
                        this.this$0.clickType = 1;
                        this.this$0.preRecord();
                        return;
                    }
                    PermissionUtilKt.requestRecordPermission(this.this$0, new TranslateOneToOneActivity.RecordPermissionCallback(new WeakReference(this.this$0)));
                }
            }
        }, 1, null);
        ImageView ivMicTop = activityTranslateOneToOneBinding.ivMicTop;
        Intrinsics.checkNotNullExpressionValue(ivMicTop, "ivMicTop");
        ViewKt.click$default(ivMicTop, 0L, new Function1<View, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$micClick$1$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) throws Exception {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) throws Exception {
                Intrinsics.checkNotNullParameter(it, "it");
                SparkChainRecognizer sparkChainRecognizer = null;
                if (!NetWorkUtils.INSTANCE.isNetworkAvailable(this.this$0)) {
                    String string = this.this$0.getString(C0775R.string.ai_glass_1);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    GlobalKt.showToast$default(string, 0, 1, null);
                } else {
                    if (ContextCompat.checkSelfPermission(this.this$0, Permission.RECORD_AUDIO) == 0) {
                        this.this$0.ttsStart = false;
                        SparkChainRecognizer sparkChainRecognizer2 = this.this$0.recognizer;
                        if (sparkChainRecognizer2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("recognizer");
                        } else {
                            sparkChainRecognizer = sparkChainRecognizer2;
                        }
                        sparkChainRecognizer.setVoiceType(SparkChainRecognizer.TRANSLATE_ONE_TO_ONE);
                        if (!this.this$0.isRecording) {
                            activityTranslateOneToOneBinding.ivMicBottom.setEnabled(false);
                            activityTranslateOneToOneBinding.ivMicTop2.setEnabled(false);
                        }
                        this.this$0.clickType = 2;
                        this.this$0.preRecord();
                        return;
                    }
                    PermissionUtilKt.requestRecordPermission(this.this$0, new TranslateOneToOneActivity.RecordPermissionCallback(new WeakReference(this.this$0)));
                }
            }
        }, 1, null);
        ImageView ivMicTop2 = activityTranslateOneToOneBinding.ivMicTop2;
        Intrinsics.checkNotNullExpressionValue(ivMicTop2, "ivMicTop2");
        ViewKt.click$default(ivMicTop2, 0L, new Function1<View, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$micClick$1$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) throws Exception {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) throws Exception {
                Intrinsics.checkNotNullParameter(it, "it");
                SparkChainRecognizer sparkChainRecognizer = null;
                if (!NetWorkUtils.INSTANCE.isNetworkAvailable(this.this$0)) {
                    String string = this.this$0.getString(C0775R.string.ai_glass_1);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    GlobalKt.showToast$default(string, 0, 1, null);
                } else {
                    if (ContextCompat.checkSelfPermission(this.this$0, Permission.RECORD_AUDIO) == 0) {
                        this.this$0.ttsStart = false;
                        SparkChainRecognizer sparkChainRecognizer2 = this.this$0.recognizer;
                        if (sparkChainRecognizer2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("recognizer");
                        } else {
                            sparkChainRecognizer = sparkChainRecognizer2;
                        }
                        sparkChainRecognizer.setVoiceType(SparkChainRecognizer.TRANSLATE_ONE_TO_ONE);
                        if (!this.this$0.isRecording) {
                            activityTranslateOneToOneBinding.ivMicBottom.setEnabled(false);
                            activityTranslateOneToOneBinding.ivMicTop.setEnabled(false);
                        }
                        this.this$0.clickType = 3;
                        this.this$0.preRecord();
                        return;
                    }
                    PermissionUtilKt.requestRecordPermission(this.this$0, new TranslateOneToOneActivity.RecordPermissionCallback(new WeakReference(this.this$0)));
                }
            }
        }, 1, null);
    }

    private final void micLongClick() {
        final ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding = this.binding;
        if (activityTranslateOneToOneBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateOneToOneBinding = null;
        }
        ImageView ivMicBottom = activityTranslateOneToOneBinding.ivMicBottom;
        Intrinsics.checkNotNullExpressionValue(ivMicBottom, "ivMicBottom");
        ViewKt.addClickAnimation(ivMicBottom, activityTranslateOneToOneBinding.ivMicBottom, new Function0<Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$micLongClick$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() throws Exception {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() throws Exception {
                if (ContextCompat.checkSelfPermission(this.this$0, Permission.RECORD_AUDIO) == 0) {
                    SparkChainRecognizer sparkChainRecognizer = this.this$0.recognizer;
                    if (sparkChainRecognizer == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("recognizer");
                        sparkChainRecognizer = null;
                    }
                    sparkChainRecognizer.setVoiceType(SparkChainRecognizer.TRANSLATE_ONE_TO_ONE);
                    if (!this.this$0.isRecording) {
                        activityTranslateOneToOneBinding.ivMicTop.setEnabled(false);
                        activityTranslateOneToOneBinding.ivMicTop2.setEnabled(false);
                    }
                    this.this$0.clickType = 1;
                    this.this$0.preRecord();
                    return;
                }
                PermissionUtilKt.requestRecordPermission(this.this$0, new TranslateOneToOneActivity.RecordPermissionCallback(new WeakReference(this.this$0)));
            }
        }, new Function0<Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$micLongClick$1$2
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
                this.this$0.clickType = 1;
                this.this$0.pauseRecord();
            }
        });
        ImageView ivMicTop = activityTranslateOneToOneBinding.ivMicTop;
        Intrinsics.checkNotNullExpressionValue(ivMicTop, "ivMicTop");
        ViewKt.addClickAnimation(ivMicTop, activityTranslateOneToOneBinding.ivMicTop, new Function0<Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$micLongClick$1$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() throws Exception {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() throws Exception {
                if (ContextCompat.checkSelfPermission(this.this$0, Permission.RECORD_AUDIO) == 0) {
                    this.this$0.ttsStart = false;
                    SparkChainRecognizer sparkChainRecognizer = this.this$0.recognizer;
                    if (sparkChainRecognizer == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("recognizer");
                        sparkChainRecognizer = null;
                    }
                    sparkChainRecognizer.setVoiceType(SparkChainRecognizer.TRANSLATE_ONE_TO_ONE);
                    if (!this.this$0.isRecording) {
                        activityTranslateOneToOneBinding.ivMicBottom.setEnabled(false);
                        activityTranslateOneToOneBinding.ivMicTop2.setEnabled(false);
                    }
                    this.this$0.clickType = 2;
                    this.this$0.preRecord();
                    return;
                }
                PermissionUtilKt.requestRecordPermission(this.this$0, new TranslateOneToOneActivity.RecordPermissionCallback(new WeakReference(this.this$0)));
            }
        }, new Function0<Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$micLongClick$1$4
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
                this.this$0.clickType = 2;
                this.this$0.pauseRecord();
            }
        });
        ImageView ivMicTop2 = activityTranslateOneToOneBinding.ivMicTop2;
        Intrinsics.checkNotNullExpressionValue(ivMicTop2, "ivMicTop2");
        ViewKt.addClickAnimation(ivMicTop2, activityTranslateOneToOneBinding.ivMicTop2, new Function0<Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$micLongClick$1$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() throws Exception {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() throws Exception {
                if (ContextCompat.checkSelfPermission(this.this$0, Permission.RECORD_AUDIO) == 0) {
                    this.this$0.ttsStart = false;
                    SparkChainRecognizer sparkChainRecognizer = this.this$0.recognizer;
                    if (sparkChainRecognizer == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("recognizer");
                        sparkChainRecognizer = null;
                    }
                    sparkChainRecognizer.setVoiceType(SparkChainRecognizer.TRANSLATE_ONE_TO_ONE);
                    if (!this.this$0.isRecording) {
                        activityTranslateOneToOneBinding.ivMicBottom.setEnabled(false);
                        activityTranslateOneToOneBinding.ivMicTop.setEnabled(false);
                    }
                    this.this$0.clickType = 3;
                    this.this$0.preRecord();
                    return;
                }
                PermissionUtilKt.requestRecordPermission(this.this$0, new TranslateOneToOneActivity.RecordPermissionCallback(new WeakReference(this.this$0)));
            }
        }, new Function0<Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$micLongClick$1$6
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
                this.this$0.ttsStart = false;
                this.this$0.clickType = 3;
                this.this$0.pauseRecord();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void textToTTS(String text, String languageCode) {
        XLog.m137i("ttx文本：" + text + ",languageCode:" + languageCode);
        synchronized (this) {
            if (text.length() > 0) {
                SparkChainRecognizer.getInstance().setTranslateTo(languageCode);
                SparkChainRecognizer.getInstance().startTTS(text);
            } else {
                XLog.m137i("play fail:" + this.ttsStart);
            }
            Unit unit = Unit.INSTANCE;
        }
        if (text.length() == 0) {
            this.ttsStart = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void preRecord() throws Exception {
        if (this.clickType == 1) {
            SparkChainRecognizer.getInstance().setTranslateLn(this.defaultLanguageBottom.getCode());
            SparkChainRecognizer.getInstance().setTranslate(this.defaultLanguageBottom.getCode(), this.defaultLanguageTop.getCode());
            SparkChainRecognizer.getInstance().setAiLanguage(AiChatDepository.INSTANCE.getGetInstance().switchAsrLanguage(this.defaultLanguageBottom.getCode()).getAsrLanguage());
        } else {
            SparkChainRecognizer.getInstance().setTranslateLn(this.defaultLanguageTop.getCode());
            SparkChainRecognizer.getInstance().setTranslate(this.defaultLanguageTop.getCode(), this.defaultLanguageBottom.getCode());
            SparkChainRecognizer.getInstance().setAiLanguage(AiChatDepository.INSTANCE.getGetInstance().switchAsrLanguage(this.defaultLanguageTop.getCode()).getAsrLanguage());
        }
        if (this.isRecording) {
            Interval interval = this.interval;
            if (interval != null) {
                interval.cancel();
            }
            pauseRecord();
            return;
        }
        this.isRecording = true;
        SparkChainRecognizer sparkChainRecognizer = this.recognizer;
        AudioRecorderManager audioRecorderManager = null;
        if (sparkChainRecognizer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recognizer");
            sparkChainRecognizer = null;
        }
        sparkChainRecognizer.setOnRecordArsListener(new MRecordArsCallback(new WeakReference(this)));
        SparkChainRecognizer sparkChainRecognizer2 = this.recognizer;
        if (sparkChainRecognizer2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recognizer");
            sparkChainRecognizer2 = null;
        }
        sparkChainRecognizer2.initData();
        SparkChainRecognizer sparkChainRecognizer3 = this.recognizer;
        if (sparkChainRecognizer3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recognizer");
            sparkChainRecognizer3 = null;
        }
        sparkChainRecognizer3.start();
        Interval interval2 = this.interval;
        if (interval2 != null) {
            interval2.cancel();
        }
        XLog.m137i("开始录制音频====");
        AudioRecorderManager audioRecorderManager2 = this.audioRecorder;
        if (audioRecorderManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioRecorder");
        } else {
            audioRecorderManager = audioRecorderManager2;
        }
        audioRecorderManager.startRecording();
        showOrHideAnimation(true);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        ping(new Function1<Integer, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity.onResume.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                GlassApplication.INSTANCE.getGetInstance().setPingGoogle(i != 0);
            }
        });
    }

    public final void ping(final Function1<? super Integer, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Executors.newSingleThreadExecutor().execute(new Runnable() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() throws InterruptedException {
                TranslateOneToOneActivity.ping$lambda$10(callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ping$lambda$10(Function1 callback) throws InterruptedException {
        int iWaitFor;
        Intrinsics.checkNotNullParameter(callback, "$callback");
        try {
            iWaitFor = Runtime.getRuntime().exec("/system/bin/ping -c 1 -w 10 www.google.com").waitFor();
        } catch (IOException e) {
            e.printStackTrace();
            iWaitFor = -1;
            callback.invoke(Integer.valueOf(iWaitFor));
        } catch (InterruptedException e2) {
            e2.printStackTrace();
            iWaitFor = -1;
            callback.invoke(Integer.valueOf(iWaitFor));
        }
        callback.invoke(Integer.valueOf(iWaitFor));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopRecord() {
        runOnUiThread(new Runnable() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                TranslateOneToOneActivity.stopRecord$lambda$11(this.f$0);
            }
        });
        this.isRecording = false;
        AudioRecorderManager audioRecorderManager = this.audioRecorder;
        SparkChainRecognizer sparkChainRecognizer = null;
        if (audioRecorderManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioRecorder");
            audioRecorderManager = null;
        }
        audioRecorderManager.stopRecording();
        SparkChainRecognizer sparkChainRecognizer2 = this.recognizer;
        if (sparkChainRecognizer2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recognizer");
        } else {
            sparkChainRecognizer = sparkChainRecognizer2;
        }
        sparkChainRecognizer.stop();
        showOrHideAnimation(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void stopRecord$lambda$11(TranslateOneToOneActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding = this$0.binding;
        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding2 = null;
        if (activityTranslateOneToOneBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateOneToOneBinding = null;
        }
        activityTranslateOneToOneBinding.ivMicBottom.setEnabled(true);
        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding3 = this$0.binding;
        if (activityTranslateOneToOneBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateOneToOneBinding3 = null;
        }
        activityTranslateOneToOneBinding3.ivMicTop.setEnabled(true);
        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding4 = this$0.binding;
        if (activityTranslateOneToOneBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityTranslateOneToOneBinding2 = activityTranslateOneToOneBinding4;
        }
        activityTranslateOneToOneBinding2.ivMicTop2.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void pauseRecord() {
        runOnUiThread(new Runnable() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                TranslateOneToOneActivity.pauseRecord$lambda$12(this.f$0);
            }
        });
        XLog.m137i("停止录音");
        this.isRecording = false;
        AudioRecorderManager audioRecorderManager = this.audioRecorder;
        SparkChainRecognizer sparkChainRecognizer = null;
        if (audioRecorderManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioRecorder");
            audioRecorderManager = null;
        }
        audioRecorderManager.stopRecording();
        SparkChainRecognizer sparkChainRecognizer2 = this.recognizer;
        if (sparkChainRecognizer2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recognizer");
        } else {
            sparkChainRecognizer = sparkChainRecognizer2;
        }
        sparkChainRecognizer.stopHeartBeat();
        showOrHideAnimation(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void pauseRecord$lambda$12(TranslateOneToOneActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding = this$0.binding;
        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding2 = null;
        if (activityTranslateOneToOneBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateOneToOneBinding = null;
        }
        activityTranslateOneToOneBinding.ivMicBottom.setEnabled(true);
        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding3 = this$0.binding;
        if (activityTranslateOneToOneBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateOneToOneBinding3 = null;
        }
        activityTranslateOneToOneBinding3.ivMicTop.setEnabled(true);
        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding4 = this$0.binding;
        if (activityTranslateOneToOneBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityTranslateOneToOneBinding2 = activityTranslateOneToOneBinding4;
        }
        activityTranslateOneToOneBinding2.ivMicTop2.setEnabled(true);
    }

    private final void showOrHideAnimation(boolean bool) {
        int i = this.clickType;
        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding = null;
        if (i == 1) {
            if (bool) {
                ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding2 = this.binding;
                if (activityTranslateOneToOneBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityTranslateOneToOneBinding2 = null;
                }
                activityTranslateOneToOneBinding2.rippleBottom.onStart(this);
                ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding3 = this.binding;
                if (activityTranslateOneToOneBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityTranslateOneToOneBinding3 = null;
                }
                activityTranslateOneToOneBinding3.ivMicBottom.setImageResource(C0775R.mipmap.ic_mic_playing_bottom);
            } else {
                ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding4 = this.binding;
                if (activityTranslateOneToOneBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityTranslateOneToOneBinding4 = null;
                }
                activityTranslateOneToOneBinding4.ivMicBottom.setImageResource(C0775R.mipmap.ic_mic_2);
                ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding5 = this.binding;
                if (activityTranslateOneToOneBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityTranslateOneToOneBinding5 = null;
                }
                activityTranslateOneToOneBinding5.rippleBottom.onStop();
            }
            ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding6 = this.binding;
            if (activityTranslateOneToOneBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTranslateOneToOneBinding6 = null;
            }
            activityTranslateOneToOneBinding6.rippleTop.onStop();
            ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding7 = this.binding;
            if (activityTranslateOneToOneBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityTranslateOneToOneBinding = activityTranslateOneToOneBinding7;
            }
            activityTranslateOneToOneBinding.rippleTop2.onStop();
            return;
        }
        if (i == 2) {
            if (bool) {
                ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding8 = this.binding;
                if (activityTranslateOneToOneBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityTranslateOneToOneBinding8 = null;
                }
                activityTranslateOneToOneBinding8.ivMicTop.setImageResource(C0775R.mipmap.ic_mic_playing_top);
                ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding9 = this.binding;
                if (activityTranslateOneToOneBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityTranslateOneToOneBinding9 = null;
                }
                activityTranslateOneToOneBinding9.rippleTop.onStart(this);
            } else {
                ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding10 = this.binding;
                if (activityTranslateOneToOneBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityTranslateOneToOneBinding10 = null;
                }
                activityTranslateOneToOneBinding10.ivMicTop.setImageResource(C0775R.mipmap.ic_mic_1);
                ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding11 = this.binding;
                if (activityTranslateOneToOneBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityTranslateOneToOneBinding11 = null;
                }
                activityTranslateOneToOneBinding11.rippleTop.onStop();
            }
            ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding12 = this.binding;
            if (activityTranslateOneToOneBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTranslateOneToOneBinding12 = null;
            }
            activityTranslateOneToOneBinding12.rippleBottom.onStop();
            ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding13 = this.binding;
            if (activityTranslateOneToOneBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityTranslateOneToOneBinding = activityTranslateOneToOneBinding13;
            }
            activityTranslateOneToOneBinding.rippleTop2.onStop();
            return;
        }
        if (i != 3) {
            return;
        }
        if (bool) {
            ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding14 = this.binding;
            if (activityTranslateOneToOneBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTranslateOneToOneBinding14 = null;
            }
            activityTranslateOneToOneBinding14.ivMicTop2.setImageResource(C0775R.mipmap.ic_mic_playing_top);
            ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding15 = this.binding;
            if (activityTranslateOneToOneBinding15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTranslateOneToOneBinding15 = null;
            }
            activityTranslateOneToOneBinding15.rippleTop2.onStart(this);
        } else {
            ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding16 = this.binding;
            if (activityTranslateOneToOneBinding16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTranslateOneToOneBinding16 = null;
            }
            activityTranslateOneToOneBinding16.ivMicTop2.setImageResource(C0775R.mipmap.ic_mic_1);
            ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding17 = this.binding;
            if (activityTranslateOneToOneBinding17 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTranslateOneToOneBinding17 = null;
            }
            activityTranslateOneToOneBinding17.rippleTop2.onStop();
        }
        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding18 = this.binding;
        if (activityTranslateOneToOneBinding18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateOneToOneBinding18 = null;
        }
        activityTranslateOneToOneBinding18.rippleTop.onStop();
        ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding19 = this.binding;
        if (activityTranslateOneToOneBinding19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityTranslateOneToOneBinding = activityTranslateOneToOneBinding19;
        }
        activityTranslateOneToOneBinding.rippleBottom.onStop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void realTranslate(int status, String content, String dest, boolean isRealTime, String sid) {
        final ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding = this.binding;
        if (activityTranslateOneToOneBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateOneToOneBinding = null;
        }
        runOnUiThread(new Runnable() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                TranslateOneToOneActivity.realTranslate$lambda$14$lambda$13(this.f$0, activityTranslateOneToOneBinding);
            }
        });
        if (this.clickType == 1) {
            getMViewModel().textToTranslate(status, content, this.defaultLanguageBottom.getCode(), this.defaultLanguageTop.getCode(), dest, isRealTime, sid);
        } else {
            getMViewModel().textToTranslate(status, content, this.defaultLanguageTop.getCode(), this.defaultLanguageBottom.getCode(), dest, isRealTime, sid);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void realTranslate$lambda$14$lambda$13(TranslateOneToOneActivity this$0, ActivityTranslateOneToOneBinding this_run) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        int i = this$0.lastClickType;
        if ((i == 2 || i == 3) && i != this$0.clickType) {
            this_run.etTop.setText("");
            this_run.etBottom.setText("");
        } else if (i == 1 && i != this$0.clickType) {
            this_run.etTop.setText("");
            this_run.etBottom.setText("");
        }
        this$0.lastClickType = this$0.clickType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<SelectLanguageModel> getLanguageData() {
        ArrayList arrayList = new ArrayList();
        Iterator<QLanguageType> it = QLanguageType.getEntries().iterator();
        while (it.hasNext()) {
            arrayList.add(new SelectLanguageModel(it.next(), false, 2, null));
        }
        return arrayList;
    }

    /* compiled from: TranslateOneToOneActivity.kt */
    @Metadata(m606d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u001e\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001e\u0010\r\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000e\u001a\u00020\fH\u0017R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/TranslateOneToOneActivity$RecordPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "activity", "Ljava/lang/ref/WeakReference;", "Lcom/glasssutdio/wear/home/activity/TranslateOneToOneActivity;", "(Ljava/lang/ref/WeakReference;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class RecordPermissionCallback implements OnPermissionCallback {
        private final WeakReference<TranslateOneToOneActivity> activity;

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
        }

        public RecordPermissionCallback(WeakReference<TranslateOneToOneActivity> activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            this.activity = activity;
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            super.onDenied(permissions, never);
            TranslateOneToOneActivity translateOneToOneActivity = this.activity.get();
            if (translateOneToOneActivity != null) {
                String string = translateOneToOneActivity.getString(C0775R.string.ble_glass_20_1);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                GlobalKt.showToast$default(string, 0, 1, null);
                XLog.m136i(permissions);
                XLog.m136i(Boolean.valueOf(never));
            }
        }
    }

    /* compiled from: TranslateOneToOneActivity.kt */
    @Metadata(m606d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J2\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/TranslateOneToOneActivity$MRecordArsCallback;", "Lcom/glasssutdio/wear/ai/spark/SparkChainRecognizer$RecordArsCallback;", "activity", "Ljava/lang/ref/WeakReference;", "Lcom/glasssutdio/wear/home/activity/TranslateOneToOneActivity;", "(Ljava/lang/ref/WeakReference;)V", "recordArsResult", "", NotificationCompat.CATEGORY_STATUS, "", "content", "", "dest", "isRealTime", "", "sid", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class MRecordArsCallback implements SparkChainRecognizer.RecordArsCallback {
        private final WeakReference<TranslateOneToOneActivity> activity;

        public MRecordArsCallback(WeakReference<TranslateOneToOneActivity> activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            this.activity = activity;
        }

        @Override // com.glasssutdio.wear.ai.spark.SparkChainRecognizer.RecordArsCallback
        public void recordArsResult(final int status, final String content, final String dest, final boolean isRealTime, final String sid) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            Intrinsics.checkNotNullParameter(sid, "sid");
            final TranslateOneToOneActivity translateOneToOneActivity = this.activity.get();
            if (translateOneToOneActivity != null) {
                translateOneToOneActivity.runOnUiThread(new Runnable() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$MRecordArsCallback$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        TranslateOneToOneActivity.MRecordArsCallback.recordArsResult$lambda$1$lambda$0(isRealTime, translateOneToOneActivity, status, content, dest, sid);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void recordArsResult$lambda$1$lambda$0(boolean z, TranslateOneToOneActivity this_apply, int i, String str, String dest, String sid) {
            Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
            Intrinsics.checkNotNullParameter(dest, "$dest");
            Intrinsics.checkNotNullParameter(sid, "$sid");
            if (z) {
                this_apply.realTranslate(i, str, dest, true, sid);
            } else {
                this_apply.realTranslate(i, str, dest, false, sid);
            }
        }
    }

    /* compiled from: TranslateOneToOneActivity.kt */
    @Metadata(m606d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\u001a\u0010\u000b\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\rH\u0016J\u001a\u0010\u000e\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\tH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/TranslateOneToOneActivity$MPlayStateCallback;", "Lcom/glasssutdio/wear/ai/spark/SparkChainRecognizer$PlayStateCallback;", "activity", "Ljava/lang/ref/WeakReference;", "Lcom/glasssutdio/wear/home/activity/TranslateOneToOneActivity;", "(Ljava/lang/ref/WeakReference;)V", "audioPlaying", "", "sid", "", "text", "endAudio", "complete", "", "startAudio", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class MPlayStateCallback implements SparkChainRecognizer.PlayStateCallback {
        private final WeakReference<TranslateOneToOneActivity> activity;

        @Override // com.glasssutdio.wear.ai.spark.SparkChainRecognizer.PlayStateCallback
        public void audioPlaying(String sid, String text) {
            Intrinsics.checkNotNullParameter(text, "text");
        }

        public MPlayStateCallback(WeakReference<TranslateOneToOneActivity> activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            this.activity = activity;
        }

        @Override // com.glasssutdio.wear.ai.spark.SparkChainRecognizer.PlayStateCallback
        public void startAudio(final String sid, String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            ThreadExtKt.ktxRunOnUi(this, new Function1<MPlayStateCallback, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$MPlayStateCallback$startAudio$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TranslateOneToOneActivity.MPlayStateCallback mPlayStateCallback) {
                    invoke2(mPlayStateCallback);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TranslateOneToOneActivity.MPlayStateCallback ktxRunOnUi) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                    TranslateOneToOneActivity translateOneToOneActivity = (TranslateOneToOneActivity) ktxRunOnUi.activity.get();
                    if (translateOneToOneActivity != null) {
                        translateOneToOneActivity.onStartPlayAudio(sid);
                    }
                }
            });
        }

        @Override // com.glasssutdio.wear.ai.spark.SparkChainRecognizer.PlayStateCallback
        public void endAudio(final String sid, final boolean complete) {
            ThreadExtKt.ktxRunOnUi(this, new Function1<MPlayStateCallback, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$MPlayStateCallback$endAudio$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TranslateOneToOneActivity.MPlayStateCallback mPlayStateCallback) {
                    invoke2(mPlayStateCallback);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TranslateOneToOneActivity.MPlayStateCallback ktxRunOnUi) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                    TranslateOneToOneActivity translateOneToOneActivity = (TranslateOneToOneActivity) ktxRunOnUi.activity.get();
                    if (translateOneToOneActivity != null) {
                        translateOneToOneActivity.onEndPlayAudio(sid, complete);
                    }
                }
            });
        }
    }

    public final void onStartPlayAudio(String sid) {
        XLog.m137i("开始播报sid：" + sid);
    }

    public final void onEndPlayAudio(String sid, boolean complete) {
        XLog.m137i("结束播报sid：" + sid + ",complete:" + complete);
        if (this.binding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        this.ttsStart = false;
        if (!complete || this.playingIndex == -1) {
            return;
        }
        TranslateOneAdapter translateOneAdapter = null;
        if (this.isBottomRcyPlaying) {
            TranslateOneAdapter translateOneAdapter2 = this.mAdapterBottom;
            if (translateOneAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
                translateOneAdapter2 = null;
            }
            translateOneAdapter2.getData().get(this.playingIndex).setPlaying(false);
            TranslateOneAdapter translateOneAdapter3 = this.mAdapterBottom;
            if (translateOneAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdapterBottom");
            } else {
                translateOneAdapter = translateOneAdapter3;
            }
            translateOneAdapter.refreshPlayingStatus(this.playingIndex);
            return;
        }
        TranslateOneAdapter translateOneAdapter4 = this.mAdapterTop;
        if (translateOneAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
            translateOneAdapter4 = null;
        }
        translateOneAdapter4.getData().get(this.playingIndex).setPlaying(false);
        TranslateOneAdapter translateOneAdapter5 = this.mAdapterTop;
        if (translateOneAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapterTop");
        } else {
            translateOneAdapter = translateOneAdapter5;
        }
        translateOneAdapter.refreshPlayingStatus(this.playingIndex);
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BusEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if (messageEvent instanceof BluetoothEvent) {
            BluetoothEvent bluetoothEvent = (BluetoothEvent) messageEvent;
            XLog.m137i("蓝牙状态:->" + (!bluetoothEvent.getConnect()));
            if (bluetoothEvent.getConnect()) {
                return;
            }
            XLog.m137i("设备断开");
            String string = getString(C0775R.string.ble_glass_18);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
            finish();
        }
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        try {
            LargeDataHandler.getInstance().speakSoundSwitch(false);
            Interval interval = this.interval;
            if (interval != null) {
                interval.cancel();
            }
            stopRecord();
            SparkChainRecognizer.getInstance().stopTTS();
            AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).stopPlay();
            SparkChainRecognizer.getInstance().stop();
            ThreadExtKt.ktxRunOnUiDelay(this, 500L, new Function1<TranslateOneToOneActivity, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity.onDestroy.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TranslateOneToOneActivity translateOneToOneActivity) {
                    invoke2(translateOneToOneActivity);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TranslateOneToOneActivity ktxRunOnUiDelay) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUiDelay, "$this$ktxRunOnUiDelay");
                    AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).setAudioTrackNull();
                }
            });
            SparkChainRecognizer.getInstance().resetParams();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
