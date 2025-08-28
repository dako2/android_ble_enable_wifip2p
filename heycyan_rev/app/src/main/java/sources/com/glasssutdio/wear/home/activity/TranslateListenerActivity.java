package com.glasssutdio.wear.home.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.ActivityCompat;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.GlassApplication;
import com.glasssutdio.wear.GlassesWearJavaApplication;
import com.glasssutdio.wear.all.EditTextViewExtKt;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.ThreadExtKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.bean.QLanguageType;
import com.glasssutdio.wear.all.bean.SelectLanguageModel;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.AudioRecorderManager;
import com.glasssutdio.wear.all.utils.NetWorkUtils;
import com.glasssutdio.wear.all.utils.PermissionUtilKt;
import com.glasssutdio.wear.bus.BusEvent;
import com.glasssutdio.wear.bus.EventType;
import com.glasssutdio.wear.database.entity.TranslateEntity;
import com.glasssutdio.wear.databinding.ActivityTranslateListenerBinding;
import com.glasssutdio.wear.databinding.PopupSelectLanguageBinding;
import com.glasssutdio.wear.depository.AiChatDepository;
import com.glasssutdio.wear.home.activity.TranslateListenerActivity;
import com.glasssutdio.wear.home.viewmodel.AiTranslateVM;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import com.glasssutdio.wear.p003ai.spark.AudioTrackManager;
import com.glasssutdio.wear.p003ai.spark.SparkChainRecognizer;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.scan.BleScannerHelper;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: TranslateListenerActivity.kt */
@Metadata(m606d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001EB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010,\u001a\u00020-H\u0002J\u0016\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\u0006\u00101\u001a\u00020\u001dH\u0002J\u0010\u00102\u001a\u00020-2\u0006\u00103\u001a\u00020'H\u0002J\b\u00104\u001a\u00020-H\u0002J\u0010\u00105\u001a\u00020\u001d2\u0006\u00106\u001a\u00020'H\u0002J\u0012\u00107\u001a\u00020-2\b\u00108\u001a\u0004\u0018\u000109H\u0014J\b\u0010:\u001a\u00020-H\u0014J\u0010\u0010;\u001a\u00020-2\u0006\u0010<\u001a\u00020=H\u0017J\b\u0010>\u001a\u00020-H\u0014J\u001a\u0010?\u001a\u00020-2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020-0AJ\b\u0010B\u001a\u00020-H\u0002J\b\u0010C\u001a\u00020-H\u0002J\u0010\u0010D\u001a\u00020-2\u0006\u00106\u001a\u00020'H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00060\bj\u0002`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010)\u001a\u00060\bj\u0002`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/TranslateListenerActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "audioRecorder", "Lcom/glasssutdio/wear/all/utils/AudioRecorderManager;", "binding", "Lcom/glasssutdio/wear/databinding/ActivityTranslateListenerBinding;", "cacheSB", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "createTime", "", "defaultLanguageBottom", "Lcom/glasssutdio/wear/all/bean/QLanguageType;", "defaultLanguageTop", "handler", "Landroid/os/Handler;", "lastCreateTime", "lastInputJob", "Lkotlinx/coroutines/Job;", "mViewModel", "Lcom/glasssutdio/wear/home/viewmodel/AiTranslateVM;", "getMViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/AiTranslateVM;", "mViewModel$delegate", "Lkotlin/Lazy;", "minBlockSize", "", "playVoice", "", "startTime", "getStartTime", "()J", "setStartTime", "(J)V", "timeoutRunnable", "Ljava/lang/Runnable;", "transLateQueue", "Ljava/util/concurrent/BlockingDeque;", "", "translateContent", "translateDst", "translateIng", "translateStatus", "flushSentence", "", "getLanguageData", "", "Lcom/glasssutdio/wear/all/bean/SelectLanguageModel;", TypedValues.TransitionType.S_FROM, "highlightText", "targetText", "initView", "isSentenceComplete", "text", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/glasssutdio/wear/bus/BusEvent;", "onResume", "ping", "callback", "Lkotlin/Function1;", "saveTranslate", "startTranslate", "textToTTS", "RecordPermissionCallback", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class TranslateListenerActivity extends BaseSettingActivity {
    private AudioRecorderManager audioRecorder;
    private ActivityTranslateListenerBinding binding;
    private long createTime;
    private QLanguageType defaultLanguageBottom;
    private QLanguageType defaultLanguageTop;
    private long lastCreateTime;
    private Job lastInputJob;

    /* renamed from: mViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mViewModel;
    private final int minBlockSize;
    private boolean playVoice;
    private long startTime;
    private final Runnable timeoutRunnable;
    private boolean translateIng;
    private boolean translateStatus;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private String translateContent = "";
    private final StringBuilder translateDst = new StringBuilder();
    private final StringBuilder cacheSB = new StringBuilder();
    private final BlockingDeque<String> transLateQueue = new LinkedBlockingDeque(100);

    /* JADX WARN: Multi-variable type inference failed */
    public TranslateListenerActivity() {
        final TranslateListenerActivity translateListenerActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewModel = LazyKt.lazy(new Function0<AiTranslateVM>() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.AiTranslateVM] */
            @Override // kotlin.jvm.functions.Function0
            public final AiTranslateVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(translateListenerActivity, Reflection.getOrCreateKotlinClass(AiTranslateVM.class), qualifier, objArr);
            }
        });
        this.defaultLanguageTop = QLanguageType.English;
        this.defaultLanguageBottom = QLanguageType.English;
        this.minBlockSize = 6;
        this.timeoutRunnable = new Runnable() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                TranslateListenerActivity.timeoutRunnable$lambda$12(this.f$0);
            }
        };
    }

    private final AiTranslateVM getMViewModel() {
        return (AiTranslateVM) this.mViewModel.getValue();
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityTranslateListenerBinding activityTranslateListenerBindingInflate = ActivityTranslateListenerBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityTranslateListenerBindingInflate, "inflate(...)");
        this.binding = activityTranslateListenerBindingInflate;
        if (activityTranslateListenerBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateListenerBindingInflate = null;
        }
        setContentView(activityTranslateListenerBindingInflate.getRoot());
        initView();
    }

    private final void initView() {
        final ActivityTranslateListenerBinding activityTranslateListenerBinding = this.binding;
        ActivityTranslateListenerBinding activityTranslateListenerBinding2 = null;
        if (activityTranslateListenerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateListenerBinding = null;
        }
        GlassesWearJavaApplication.getInstance().setTranslateDoing(true);
        SparkChainRecognizer.getInstance().initStartTTS();
        AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT());
        SparkChainRecognizer sparkChainRecognizer = SparkChainRecognizer.getInstance();
        Intrinsics.checkNotNullExpressionValue(sparkChainRecognizer, "getInstance(...)");
        this.audioRecorder = new AudioRecorderManager(sparkChainRecognizer);
        activityTranslateListenerBinding.title.ivMenu.setImageResource(C0775R.mipmap.ic_translate_menu);
        ViewKt.visible(activityTranslateListenerBinding.title.ivMenu);
        activityTranslateListenerBinding.title.ivMenu.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TranslateListenerActivity.initView$lambda$8$lambda$0(this.f$0, view);
            }
        });
        activityTranslateListenerBinding.title.ivMenu2.setImageResource(C0775R.mipmap.ic_translate_save);
        activityTranslateListenerBinding.title.ivMenu2.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TranslateListenerActivity.initView$lambda$8$lambda$1(this.f$0, view);
            }
        });
        ActivityTranslateListenerBinding activityTranslateListenerBinding3 = this.binding;
        if (activityTranslateListenerBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateListenerBinding3 = null;
        }
        activityTranslateListenerBinding3.tvSave.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TranslateListenerActivity.initView$lambda$8$lambda$2(this.f$0, view);
            }
        });
        for (QLanguageType qLanguageType : QLanguageType.getEntries()) {
            if (Intrinsics.areEqual(UserConfig.INSTANCE.getInstance().getTranslateFromDefault(), qLanguageType.getCode())) {
                activityTranslateListenerBinding.tvLanguageTop.setText(qLanguageType.getLanguageName());
            }
            if (Intrinsics.areEqual(UserConfig.INSTANCE.getInstance().getTranslateToDefault(), qLanguageType.getCode())) {
                activityTranslateListenerBinding.tvLanguageBottom.setText(qLanguageType.getLanguageName());
            }
        }
        SparkChainRecognizer.getInstance().setTranslate(UserConfig.INSTANCE.getInstance().getTranslateFromDefault(), UserConfig.INSTANCE.getInstance().getTranslateToDefault());
        activityTranslateListenerBinding.title.tvTitle.setText(getString(C0775R.string.g_translate_3));
        activityTranslateListenerBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TranslateListenerActivity.initView$lambda$8$lambda$4(this.f$0, view);
            }
        });
        activityTranslateListenerBinding.tvLanguageTop.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                TranslateListenerActivity.initView$lambda$8$lambda$5(this.f$0, activityTranslateListenerBinding, view);
            }
        });
        activityTranslateListenerBinding.tvLanguageBottom.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                TranslateListenerActivity.initView$lambda$8$lambda$6(this.f$0, activityTranslateListenerBinding, view);
            }
        });
        activityTranslateListenerBinding.cclStart.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IllegalStateException, InterruptedException, NoSuchMethodException, SecurityException {
                TranslateListenerActivity.initView$lambda$8$lambda$7(this.f$0, view);
            }
        });
        AiChatDepository.INSTANCE.getGetInstance().setTranslateCallback(new AiChatDepository.TranslateCallback() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity$initView$1$9
            @Override // com.glasssutdio.wear.depository.AiChatDepository.TranslateCallback
            public void translateRTDst(String rtDst) {
                Intrinsics.checkNotNullParameter(rtDst, "rtDst");
            }

            @Override // com.glasssutdio.wear.depository.AiChatDepository.TranslateCallback
            public void translateSrc(final String src) {
                Intrinsics.checkNotNullParameter(src, "src");
                final TranslateListenerActivity translateListenerActivity = this.this$0;
                ThreadExtKt.ktxRunOnUi(this, new Function1<TranslateListenerActivity$initView$1$9, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity$initView$1$9$translateSrc$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TranslateListenerActivity$initView$1$9 translateListenerActivity$initView$1$9) {
                        invoke2(translateListenerActivity$initView$1$9);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TranslateListenerActivity$initView$1$9 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        translateListenerActivity.translateContent = src;
                        ActivityTranslateListenerBinding activityTranslateListenerBinding4 = translateListenerActivity.binding;
                        if (activityTranslateListenerBinding4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityTranslateListenerBinding4 = null;
                        }
                        activityTranslateListenerBinding4.etTop.setText(src);
                        TranslateListenerActivity translateListenerActivity2 = translateListenerActivity;
                        translateListenerActivity2.lastCreateTime = translateListenerActivity2.createTime;
                    }
                });
            }

            @Override // com.glasssutdio.wear.depository.AiChatDepository.TranslateCallback
            public void translateDst(String src, boolean same) throws InterruptedException {
                Intrinsics.checkNotNullParameter(src, "src");
                this.this$0.translateDst.append(src);
                this.this$0.cacheSB.append(src);
                final TranslateListenerActivity translateListenerActivity = this.this$0;
                ThreadExtKt.ktxRunOnUi(this, new Function1<TranslateListenerActivity$initView$1$9, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity$initView$1$9$translateDst$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TranslateListenerActivity$initView$1$9 translateListenerActivity$initView$1$9) {
                        invoke2(translateListenerActivity$initView$1$9);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TranslateListenerActivity$initView$1$9 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        TranslateListenerActivity translateListenerActivity2 = translateListenerActivity;
                        translateListenerActivity2.lastCreateTime = translateListenerActivity2.createTime;
                        ActivityTranslateListenerBinding activityTranslateListenerBinding4 = translateListenerActivity.binding;
                        if (activityTranslateListenerBinding4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityTranslateListenerBinding4 = null;
                        }
                        activityTranslateListenerBinding4.etBottom.setText(translateListenerActivity.translateDst.toString());
                    }
                });
                Job job = this.this$0.lastInputJob;
                if (job != null) {
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
                TranslateListenerActivity translateListenerActivity2 = this.this$0;
                String string = translateListenerActivity2.cacheSB.toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                if (translateListenerActivity2.isSentenceComplete(string)) {
                    this.this$0.flushSentence();
                } else {
                    this.this$0.lastInputJob = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new TranslateListenerActivity$initView$1$9$translateDst$2(this.this$0, null), 3, null);
                }
            }

            @Override // com.glasssutdio.wear.depository.AiChatDepository.TranslateCallback
            public void translateFail(int reason) {
                final TranslateListenerActivity translateListenerActivity = this.this$0;
                ThreadExtKt.ktxRunOnUi(this, new Function1<TranslateListenerActivity$initView$1$9, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity$initView$1$9$translateFail$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TranslateListenerActivity$initView$1$9 translateListenerActivity$initView$1$9) {
                        invoke2(translateListenerActivity$initView$1$9);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TranslateListenerActivity$initView$1$9 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        translateListenerActivity.getString(C0775R.string.g_translate_12);
                    }
                });
            }
        });
        SparkChainRecognizer.getInstance().setPlayStateCallback(new SparkChainRecognizer.PlayStateCallback() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity.initView.2
            @Override // com.glasssutdio.wear.ai.spark.SparkChainRecognizer.PlayStateCallback
            public void startAudio(String sid, String text) {
                Intrinsics.checkNotNullParameter(text, "text");
                final TranslateListenerActivity translateListenerActivity = TranslateListenerActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<C10172, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity$initView$2$startAudio$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TranslateListenerActivity.C10172 c10172) {
                        invoke2(c10172);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TranslateListenerActivity.C10172 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        ActivityTranslateListenerBinding activityTranslateListenerBinding4 = translateListenerActivity.binding;
                        ActivityTranslateListenerBinding activityTranslateListenerBinding5 = null;
                        if (activityTranslateListenerBinding4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityTranslateListenerBinding4 = null;
                        }
                        ViewKt.visible(activityTranslateListenerBinding4.tvPlayTranslate);
                        if (translateListenerActivity.playVoice) {
                            ActivityTranslateListenerBinding activityTranslateListenerBinding6 = translateListenerActivity.binding;
                            if (activityTranslateListenerBinding6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activityTranslateListenerBinding5 = activityTranslateListenerBinding6;
                            }
                            activityTranslateListenerBinding5.tvPlayTranslate.setImageResource(C0775R.mipmap.translate_not_sound);
                            return;
                        }
                        ActivityTranslateListenerBinding activityTranslateListenerBinding7 = translateListenerActivity.binding;
                        if (activityTranslateListenerBinding7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityTranslateListenerBinding5 = activityTranslateListenerBinding7;
                        }
                        activityTranslateListenerBinding5.tvPlayTranslate.setImageResource(C0775R.mipmap.translate_sound);
                    }
                });
            }

            @Override // com.glasssutdio.wear.ai.spark.SparkChainRecognizer.PlayStateCallback
            public void audioPlaying(String sid, final String text) {
                Intrinsics.checkNotNullParameter(sid, "sid");
                Intrinsics.checkNotNullParameter(text, "text");
                final TranslateListenerActivity translateListenerActivity = TranslateListenerActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<C10172, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity$initView$2$audioPlaying$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TranslateListenerActivity.C10172 c10172) {
                        invoke2(c10172);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TranslateListenerActivity.C10172 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        translateListenerActivity.highlightText(text);
                    }
                });
            }

            @Override // com.glasssutdio.wear.ai.spark.SparkChainRecognizer.PlayStateCallback
            public void endAudio(String sid, boolean complete) {
                final TranslateListenerActivity translateListenerActivity = TranslateListenerActivity.this;
                ThreadExtKt.ktxRunOnUiDelay(this, 3000L, new Function1<C10172, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity$initView$2$endAudio$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TranslateListenerActivity.C10172 c10172) {
                        invoke2(c10172);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TranslateListenerActivity.C10172 ktxRunOnUiDelay) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUiDelay, "$this$ktxRunOnUiDelay");
                        translateListenerActivity.highlightText("||");
                    }
                });
            }
        });
        ActivityTranslateListenerBinding activityTranslateListenerBinding4 = this.binding;
        if (activityTranslateListenerBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityTranslateListenerBinding2 = activityTranslateListenerBinding4;
        }
        activityTranslateListenerBinding2.tvPlayTranslate.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IllegalStateException {
                TranslateListenerActivity.initView$lambda$9(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$8$lambda$0(TranslateListenerActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TranslateListenerActivity translateListenerActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(translateListenerActivity, (Class<?>) TranslateHistoryActivity.class);
        for (Pair pair : arrayList) {
            if (pair != null) {
                String str = (String) pair.getFirst();
                Object second = pair.getSecond();
                if (second instanceof Integer) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(...)");
                } else if (second instanceof Byte) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(...)");
                } else if (second instanceof Character) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(...)");
                } else if (second instanceof Short) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(...)");
                } else if (second instanceof Boolean) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(...)");
                } else if (second instanceof Long) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(...)");
                } else if (second instanceof Float) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(...)");
                } else if (second instanceof Double) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(...)");
                } else if (second instanceof String) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(...)");
                } else if (second instanceof CharSequence) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(...)");
                } else if (second instanceof Parcelable) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                } else if (second instanceof Object[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                } else if (second instanceof ArrayList) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                } else if (second instanceof Serializable) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                } else if (second instanceof boolean[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(...)");
                } else if (second instanceof byte[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(...)");
                } else if (second instanceof short[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(...)");
                } else if (second instanceof char[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(...)");
                } else if (second instanceof int[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(...)");
                } else if (second instanceof long[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(...)");
                } else if (second instanceof float[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(...)");
                } else if (second instanceof double[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(...)");
                } else if (second instanceof Bundle) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(...)");
                } else if (second instanceof Intent) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                } else {
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
        translateListenerActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$8$lambda$1(TranslateListenerActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.saveTranslate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$8$lambda$2(TranslateListenerActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.saveTranslate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$8$lambda$4(TranslateListenerActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$8$lambda$5(TranslateListenerActivity this$0, ActivityTranslateListenerBinding this_run, View view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        TranslateListenerActivity translateListenerActivity = this$0;
        ActivityTranslateListenerBinding activityTranslateListenerBinding = this$0.binding;
        ActivityTranslateListenerBinding activityTranslateListenerBinding2 = null;
        if (activityTranslateListenerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateListenerBinding = null;
        }
        int width = activityTranslateListenerBinding.clsTop.getWidth();
        TranslateListenerActivity$initView$1$6$popup$1 translateListenerActivity$initView$1$6$popup$1 = new TranslateListenerActivity$initView$1$6$popup$1(this$0, this_run);
        Object objInvoke = PopupSelectLanguageBinding.class.getMethod("inflate", LayoutInflater.class).invoke(null, LayoutInflater.from(translateListenerActivity));
        if (objInvoke == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.glasssutdio.wear.databinding.PopupSelectLanguageBinding");
        }
        PopupSelectLanguageBinding popupSelectLanguageBinding = (PopupSelectLanguageBinding) objInvoke;
        PopupWindow popupWindow = new PopupWindow(popupSelectLanguageBinding.getRoot(), width, -2, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setSoftInputMode(20);
        popupWindow.setOutsideTouchable(true);
        translateListenerActivity$initView$1$6$popup$1.invoke((TranslateListenerActivity$initView$1$6$popup$1) popupSelectLanguageBinding, (PopupSelectLanguageBinding) popupWindow);
        ActivityTranslateListenerBinding activityTranslateListenerBinding3 = this$0.binding;
        if (activityTranslateListenerBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityTranslateListenerBinding2 = activityTranslateListenerBinding3;
        }
        popupWindow.showAsDropDown(activityTranslateListenerBinding2.tvLanguageTop, -GlobalKt.getDp((Number) 14), GlobalKt.getDp((Number) 4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$8$lambda$6(TranslateListenerActivity this$0, ActivityTranslateListenerBinding this_run, View view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        TranslateListenerActivity translateListenerActivity = this$0;
        ActivityTranslateListenerBinding activityTranslateListenerBinding = this$0.binding;
        ActivityTranslateListenerBinding activityTranslateListenerBinding2 = null;
        if (activityTranslateListenerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateListenerBinding = null;
        }
        int width = activityTranslateListenerBinding.clsTop.getWidth();
        TranslateListenerActivity$initView$1$7$popup$1 translateListenerActivity$initView$1$7$popup$1 = new TranslateListenerActivity$initView$1$7$popup$1(this$0, this_run);
        Object objInvoke = PopupSelectLanguageBinding.class.getMethod("inflate", LayoutInflater.class).invoke(null, LayoutInflater.from(translateListenerActivity));
        if (objInvoke == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.glasssutdio.wear.databinding.PopupSelectLanguageBinding");
        }
        PopupSelectLanguageBinding popupSelectLanguageBinding = (PopupSelectLanguageBinding) objInvoke;
        PopupWindow popupWindow = new PopupWindow(popupSelectLanguageBinding.getRoot(), width, -2, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setSoftInputMode(20);
        popupWindow.setOutsideTouchable(true);
        translateListenerActivity$initView$1$7$popup$1.invoke((TranslateListenerActivity$initView$1$7$popup$1) popupSelectLanguageBinding, (PopupSelectLanguageBinding) popupWindow);
        ActivityTranslateListenerBinding activityTranslateListenerBinding3 = this$0.binding;
        if (activityTranslateListenerBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityTranslateListenerBinding2 = activityTranslateListenerBinding3;
        }
        popupWindow.showAsDropDown(activityTranslateListenerBinding2.viewBottom, 0, -GlobalKt.getDp((Number) 540));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$8$lambda$7(TranslateListenerActivity this$0, View view) throws IllegalStateException, InterruptedException, NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!NetWorkUtils.INSTANCE.isNetworkAvailable(this$0)) {
            String string = this$0.getString(C0775R.string.ai_glass_1);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
        } else {
            TranslateListenerActivity translateListenerActivity = this$0;
            if (PermissionUtilKt.hasRecord(translateListenerActivity)) {
                this$0.startTime = System.currentTimeMillis();
                this$0.startTranslate();
            } else {
                PermissionUtilKt.requestRecordPermission(translateListenerActivity, this$0.new RecordPermissionCallback());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$9(TranslateListenerActivity this$0, View view) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean z = this$0.playVoice;
        this$0.playVoice = !z;
        ActivityTranslateListenerBinding activityTranslateListenerBinding = null;
        if (!z) {
            ActivityTranslateListenerBinding activityTranslateListenerBinding2 = this$0.binding;
            if (activityTranslateListenerBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityTranslateListenerBinding = activityTranslateListenerBinding2;
            }
            activityTranslateListenerBinding.tvPlayTranslate.setImageResource(C0775R.mipmap.translate_not_sound);
        } else {
            ActivityTranslateListenerBinding activityTranslateListenerBinding3 = this$0.binding;
            if (activityTranslateListenerBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityTranslateListenerBinding = activityTranslateListenerBinding3;
            }
            activityTranslateListenerBinding.tvPlayTranslate.setImageResource(C0775R.mipmap.translate_sound);
        }
        AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).restartPlay();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isSentenceComplete(String text) {
        boolean z = StringsKt.endsWith$default(text, "。", false, 2, (Object) null) || StringsKt.endsWith$default(text, ".", false, 2, (Object) null) || StringsKt.endsWith$default(text, "?", false, 2, (Object) null) || StringsKt.endsWith$default(text, "？", false, 2, (Object) null) || StringsKt.endsWith$default(text, "!", false, 2, (Object) null) || StringsKt.endsWith$default(text, "！", false, 2, (Object) null) || StringsKt.endsWith$default(text, ",", false, 2, (Object) null) || StringsKt.endsWith$default(text, "，", false, 2, (Object) null);
        if (text.length() < this.minBlockSize) {
            return false;
        }
        return z || text.length() >= 15;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void flushSentence() throws InterruptedException {
        if (this.cacheSB.length() > 0) {
            String string = this.cacheSB.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            this.transLateQueue.putLast(string);
            StringsKt.clear(this.cacheSB);
            ThreadExtKt.ktxRunOnBgSingle(this, new Function1<TranslateListenerActivity, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity.flushSentence.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TranslateListenerActivity translateListenerActivity) {
                    invoke2(translateListenerActivity);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TranslateListenerActivity ktxRunOnBgSingle) {
                    Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                    String str = (String) ktxRunOnBgSingle.transLateQueue.take();
                    XLog.m137i("----start tts" + str);
                    Intrinsics.checkNotNull(str);
                    ktxRunOnBgSingle.textToTTS(str);
                }
            });
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        ping(new Function1<Integer, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity.onResume.1
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
        Executors.newSingleThreadExecutor().execute(new Runnable() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() throws InterruptedException {
                TranslateListenerActivity.ping$lambda$10(callback);
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

    private final void saveTranslate() {
        ActivityTranslateListenerBinding activityTranslateListenerBinding = this.binding;
        if (activityTranslateListenerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateListenerBinding = null;
        }
        TextView etTop = activityTranslateListenerBinding.etTop;
        Intrinsics.checkNotNullExpressionValue(etTop, "etTop");
        if (EditTextViewExtKt.textString(etTop).length() != 0) {
            TextView etBottom = activityTranslateListenerBinding.etBottom;
            Intrinsics.checkNotNullExpressionValue(etBottom, "etBottom");
            if (EditTextViewExtKt.textString(etBottom).length() != 0 && this.lastCreateTime != 0) {
                getMViewModel().saveTranslate(new TranslateEntity(UserConfig.INSTANCE.getInstance().getUid(), this.lastCreateTime, activityTranslateListenerBinding.etTop.getText().toString(), activityTranslateListenerBinding.etBottom.getText().toString(), 1, null, 32, null));
                String string = getString(C0775R.string.g_guide_32);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                GlobalKt.showToast$default(string, 0, 1, null);
                return;
            }
        }
        String string2 = getString(C0775R.string.g_translate_21);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        GlobalKt.showToast$default(string2, 0, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void timeoutRunnable$lambda$12(TranslateListenerActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityTranslateListenerBinding activityTranslateListenerBinding = this$0.binding;
        if (activityTranslateListenerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateListenerBinding = null;
        }
        activityTranslateListenerBinding.cclStart.performClick();
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BusEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if (messageEvent instanceof EventType) {
            EventType eventType = (EventType) messageEvent;
            if (eventType.getType() == 17) {
                XLog.m137i("messageEvent.type" + eventType.getType());
                ActivityTranslateListenerBinding activityTranslateListenerBinding = this.binding;
                if (activityTranslateListenerBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityTranslateListenerBinding = null;
                }
                activityTranslateListenerBinding.tvVoiceStart.setText(getString(C0775R.string.g_translate_10));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void textToTTS(final String text) {
        SparkChainRecognizer.getInstance().setTranslate(UserConfig.INSTANCE.getInstance().getTranslateFromDefault(), UserConfig.INSTANCE.getInstance().getTranslateToDefault());
        if (this.playVoice) {
            return;
        }
        if (!BleScannerHelper.getInstance().isMacSystemBond(UserConfig.INSTANCE.getInstance().getDeviceAddress())) {
            ThreadExtKt.ktxRunOnUi(this, new Function1<TranslateListenerActivity, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity.textToTTS.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TranslateListenerActivity translateListenerActivity) {
                    invoke2(translateListenerActivity);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TranslateListenerActivity ktxRunOnUi) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                    String string = ktxRunOnUi.getString(C0775R.string.home_glass_2_2);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    GlobalKt.showToast$default(string, 0, 1, null);
                }
            });
            BleOperateManager.getInstance().classicBluetoothStartScan();
        } else {
            SparkChainRecognizer.getInstance().setTranslateTo(UserConfig.INSTANCE.getInstance().getTranslateToDefault());
            ThreadExtKt.ktxRunOnUiDelay(this, 300L, new Function1<TranslateListenerActivity, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity.textToTTS.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TranslateListenerActivity translateListenerActivity) {
                    invoke2(translateListenerActivity);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TranslateListenerActivity ktxRunOnUiDelay) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUiDelay, "$this$ktxRunOnUiDelay");
                    SparkChainRecognizer.getInstance().startTTS(text);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<SelectLanguageModel> getLanguageData(boolean from) {
        ArrayList arrayList = new ArrayList();
        for (QLanguageType qLanguageType : QLanguageType.getEntries()) {
            if (from) {
                if (Intrinsics.areEqual(UserConfig.INSTANCE.getInstance().getTranslateFromDefault(), qLanguageType.getCode())) {
                    arrayList.add(new SelectLanguageModel(qLanguageType, true));
                } else {
                    arrayList.add(new SelectLanguageModel(qLanguageType, false, 2, null));
                }
            } else if (Intrinsics.areEqual(UserConfig.INSTANCE.getInstance().getTranslateToDefault(), qLanguageType.getCode())) {
                arrayList.add(new SelectLanguageModel(qLanguageType, true));
            } else {
                arrayList.add(new SelectLanguageModel(qLanguageType, false, 2, null));
            }
        }
        return arrayList;
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        try {
            SparkChainRecognizer.getInstance().stopTTS();
            AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).stopPlay();
            GlassesWearJavaApplication.getInstance().setTranslateDoing(false);
            AudioRecorderManager audioRecorderManager = this.audioRecorder;
            if (audioRecorderManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("audioRecorder");
                audioRecorderManager = null;
            }
            audioRecorderManager.stopRecording();
            SparkChainRecognizer.getInstance().stop();
            SparkChainRecognizer.getInstance().resetParams();
            ThreadExtKt.ktxRunOnUiDelay(this, 500L, new Function1<TranslateListenerActivity, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity.onDestroy.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TranslateListenerActivity translateListenerActivity) {
                    invoke2(translateListenerActivity);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TranslateListenerActivity ktxRunOnUiDelay) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUiDelay, "$this$ktxRunOnUiDelay");
                    AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).setAudioTrackNull();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* compiled from: TranslateListenerActivity.kt */
    @Metadata(m606d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/TranslateListenerActivity$RecordPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/glasssutdio/wear/home/activity/TranslateListenerActivity;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class RecordPermissionCallback implements OnPermissionCallback {
        public RecordPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) throws IllegalStateException, InterruptedException {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            XLog.m136i(permissions);
            TranslateListenerActivity.this.setStartTime(System.currentTimeMillis());
            TranslateListenerActivity.this.startTranslate();
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            super.onDenied(permissions, never);
            XLog.m136i(permissions);
            XLog.m136i(Boolean.valueOf(never));
            String string = TranslateListenerActivity.this.getString(C0775R.string.ble_glass_20_1);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
        }
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final void setStartTime(long j) {
        this.startTime = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startTranslate() throws IllegalStateException, InterruptedException {
        AudioRecorderManager audioRecorderManager = null;
        if (Intrinsics.areEqual(UserConfig.INSTANCE.getInstance().getTranslateFromDefault(), UserConfig.INSTANCE.getInstance().getTranslateToDefault())) {
            String string = getString(C0775R.string.translate_same_language);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        this.handler.removeCallbacks(this.timeoutRunnable);
        AiChatDepository.INSTANCE.getGetInstance().setUserTranslateFromAndTo(UserConfig.INSTANCE.getInstance().getTranslateFromDefault(), UserConfig.INSTANCE.getInstance().getTranslateToDefault());
        SparkChainRecognizer.getInstance().setTranslateTo(UserConfig.INSTANCE.getInstance().getTranslateToDefault());
        boolean z = this.translateStatus;
        this.translateStatus = !z;
        if (!z) {
            this.createTime = System.currentTimeMillis();
            ActivityTranslateListenerBinding activityTranslateListenerBinding = this.binding;
            if (activityTranslateListenerBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTranslateListenerBinding = null;
            }
            ViewKt.gone(activityTranslateListenerBinding.tvPlayTranslate);
            StringsKt.clear(this.translateDst);
            AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).restartPlay();
            LargeDataHandler.getInstance().aiVoicePlay(3, null);
            SparkChainRecognizer.getInstance().setTranslateLn(UserConfig.INSTANCE.getInstance().getTranslateFromDefault());
            SparkChainRecognizer.getInstance().setTranslate(UserConfig.INSTANCE.getInstance().getTranslateFromDefault(), UserConfig.INSTANCE.getInstance().getTranslateToDefault());
            if (Intrinsics.areEqual(this.defaultLanguageTop.getCode(), "cn")) {
                SparkChainRecognizer.getInstance().setTranslateLn("zh");
            }
            this.playVoice = false;
            ThreadExtKt.ktxRunOnUi(this, new Function1<TranslateListenerActivity, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity.startTranslate.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TranslateListenerActivity translateListenerActivity) {
                    invoke2(translateListenerActivity);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TranslateListenerActivity ktxRunOnUi) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                    ActivityTranslateListenerBinding activityTranslateListenerBinding2 = ktxRunOnUi.binding;
                    if (activityTranslateListenerBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityTranslateListenerBinding2 = null;
                    }
                    activityTranslateListenerBinding2.tvVoiceStart.setText(ktxRunOnUi.getString(C0775R.string.g_translate_11));
                }
            });
            this.translateIng = true;
            AiChatDepository.INSTANCE.getGetInstance().cleanTranslate();
            this.lastCreateTime = 0L;
            ActivityTranslateListenerBinding activityTranslateListenerBinding2 = this.binding;
            if (activityTranslateListenerBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTranslateListenerBinding2 = null;
            }
            activityTranslateListenerBinding2.etTop.setText("");
            ActivityTranslateListenerBinding activityTranslateListenerBinding3 = this.binding;
            if (activityTranslateListenerBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTranslateListenerBinding3 = null;
            }
            activityTranslateListenerBinding3.etBottom.setText("");
            if (ActivityCompat.checkSelfPermission(this, Permission.RECORD_AUDIO) != 0) {
                String string2 = getString(C0775R.string.ble_glass_20_1);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                GlobalKt.showToast$default(string2, 0, 1, null);
                return;
            }
            SparkChainRecognizer.getInstance().voiceType = 1;
            SparkChainRecognizer.getInstance().setTraditional(StringsKt.startsWith$default(UserConfig.INSTANCE.getInstance().getTranslateFromDefault(), "hk", false, 2, (Object) null));
            SparkChainRecognizer.getInstance().setAiLanguage(AiChatDepository.INSTANCE.getGetInstance().switchAsrLanguage(UserConfig.INSTANCE.getInstance().getTranslateFromDefault()).getAsrLanguage());
            ThreadExtKt.ktxRunOnSparkSingle(this, new Function1<TranslateListenerActivity, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity.startTranslate.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TranslateListenerActivity translateListenerActivity) throws Exception {
                    invoke2(translateListenerActivity);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TranslateListenerActivity ktxRunOnSparkSingle) throws Exception {
                    Intrinsics.checkNotNullParameter(ktxRunOnSparkSingle, "$this$ktxRunOnSparkSingle");
                    SparkChainRecognizer.getInstance().initData();
                    SparkChainRecognizer.getInstance().start();
                }
            });
            AudioRecorderManager audioRecorderManager2 = this.audioRecorder;
            if (audioRecorderManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("audioRecorder");
            } else {
                audioRecorderManager = audioRecorderManager2;
            }
            audioRecorderManager.startRecording();
            XLog.m137i("yy点击耗时：" + (System.currentTimeMillis() - this.startTime));
            return;
        }
        XLog.m137i("yy222点击耗时");
        this.translateIng = false;
        ActivityTranslateListenerBinding activityTranslateListenerBinding4 = this.binding;
        if (activityTranslateListenerBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateListenerBinding4 = null;
        }
        activityTranslateListenerBinding4.tvVoiceStart.setText(getString(C0775R.string.g_translate_10));
        ThreadExtKt.ktxRunOnSparkSingle(this, new Function1<TranslateListenerActivity, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity.startTranslate.3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TranslateListenerActivity translateListenerActivity) {
                invoke2(translateListenerActivity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TranslateListenerActivity ktxRunOnSparkSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnSparkSingle, "$this$ktxRunOnSparkSingle");
                SparkChainRecognizer.getInstance().stopHeartBeat();
            }
        });
        AudioRecorderManager audioRecorderManager3 = this.audioRecorder;
        if (audioRecorderManager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioRecorder");
        } else {
            audioRecorderManager = audioRecorderManager3;
        }
        audioRecorderManager.stopRecording();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void highlightText(String targetText) {
        try {
            String string = this.translateDst.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
            ForegroundColorSpan[] foregroundColorSpanArr = (ForegroundColorSpan[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), ForegroundColorSpan.class);
            Intrinsics.checkNotNull(foregroundColorSpanArr);
            for (ForegroundColorSpan foregroundColorSpan : foregroundColorSpanArr) {
                spannableStringBuilder.removeSpan(foregroundColorSpan);
            }
            int iLastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) string, targetText, 0, false, 6, (Object) null);
            if (iLastIndexOf$default != -1) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(getColor(C0775R.color.color_click)), iLastIndexOf$default, targetText.length() + iLastIndexOf$default, 33);
            } else {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(getColor(C0775R.color.white)), 0, spannableStringBuilder.length(), 33);
            }
            ActivityTranslateListenerBinding activityTranslateListenerBinding = this.binding;
            if (activityTranslateListenerBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTranslateListenerBinding = null;
            }
            activityTranslateListenerBinding.etBottom.setText(spannableStringBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
