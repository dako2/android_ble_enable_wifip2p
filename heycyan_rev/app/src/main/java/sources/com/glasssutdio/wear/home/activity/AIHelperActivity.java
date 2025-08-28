package com.glasssutdio.wear.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.CompoundButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.Constant;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.bean.TimbreModel;
import com.glasssutdio.wear.all.dialog.TimerSelectDialog;
import com.glasssutdio.wear.all.utils.CommonUtils;
import com.glasssutdio.wear.databinding.ActivityAiHelperBinding;
import com.glasssutdio.wear.home.adapter.AiHelperAdapter;
import com.glasssutdio.wear.home.adapter.SpacingItemDecoration;
import com.glasssutdio.wear.home.bean.AiHelperModel;
import com.glasssutdio.wear.home.bean.GlassesType;
import com.glasssutdio.wear.home.viewmodel.AIGuideActivityVM;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.ILargeDataResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.resp.BaseResponse;
import com.oudmon.ble.base.communication.bigData.resp.GlassesAiVoiceRsp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: AIHelperActivity.kt */
@Metadata(m606d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0012\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u0013H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0018"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/AIHelperActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityAiHelperBinding;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/oudmon/ble/base/communication/ILargeDataResponse;", "Lcom/oudmon/ble/base/communication/bigData/resp/GlassesAiVoiceRsp;", "getListener", "()Lcom/oudmon/ble/base/communication/ILargeDataResponse;", "mAdapter", "Lcom/glasssutdio/wear/home/adapter/AiHelperAdapter;", "mViewModel", "Lcom/glasssutdio/wear/home/viewmodel/AIGuideActivityVM;", "getMViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/AIGuideActivityVM;", "mViewModel$delegate", "Lkotlin/Lazy;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AIHelperActivity extends BaseSettingActivity {
    private ActivityAiHelperBinding binding;
    private final ILargeDataResponse<GlassesAiVoiceRsp> listener;
    private AiHelperAdapter mAdapter;

    /* renamed from: mViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mViewModel;

    /* compiled from: AIHelperActivity.kt */
    @Metadata(m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[GlassesType.values().length];
            try {
                iArr[GlassesType.AO3.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[GlassesType.KEY40.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[GlassesType.KEY31.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[GlassesType.KEY21.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[GlassesType.AM01.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[GlassesType.KEY41.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[GlassesType.KEY42.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[GlassesType.KEY43.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[GlassesType.KEY22.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[GlassesType.KEY23.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AIHelperActivity() {
        final AIHelperActivity aIHelperActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewModel = LazyKt.lazy(new Function0<AIGuideActivityVM>() { // from class: com.glasssutdio.wear.home.activity.AIHelperActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.AIGuideActivityVM] */
            @Override // kotlin.jvm.functions.Function0
            public final AIGuideActivityVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(aIHelperActivity, Reflection.getOrCreateKotlinClass(AIGuideActivityVM.class), qualifier, objArr);
            }
        });
        this.listener = new ILargeDataResponse() { // from class: com.glasssutdio.wear.home.activity.AIHelperActivity$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i, BaseResponse baseResponse) {
                AIHelperActivity.listener$lambda$0(this.f$0, i, (GlassesAiVoiceRsp) baseResponse);
            }
        };
    }

    private final AIGuideActivityVM getMViewModel() {
        return (AIGuideActivityVM) this.mViewModel.getValue();
    }

    public final ILargeDataResponse<GlassesAiVoiceRsp> getListener() {
        return this.listener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void listener$lambda$0(AIHelperActivity this$0, int i, GlassesAiVoiceRsp glassesAiVoiceRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityAiHelperBinding activityAiHelperBinding = this$0.binding;
        if (activityAiHelperBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAiHelperBinding = null;
        }
        activityAiHelperBinding.gsc1.setChecked(glassesAiVoiceRsp.isOpen());
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityAiHelperBinding activityAiHelperBindingInflate = ActivityAiHelperBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityAiHelperBindingInflate, "inflate(...)");
        this.binding = activityAiHelperBindingInflate;
        if (activityAiHelperBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAiHelperBindingInflate = null;
        }
        setContentView(activityAiHelperBindingInflate.getRoot());
        initView();
        AIGuideActivityVM mViewModel = getMViewModel();
        mViewModel.getAiHelperData();
        mViewModel.getAiHelperDataLD().observe(this, new AIHelperActivity$sam$androidx_lifecycle_Observer$0(new Function1<List<AiHelperModel>, Unit>() { // from class: com.glasssutdio.wear.home.activity.AIHelperActivity$onCreate$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<AiHelperModel> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<AiHelperModel> list) {
                AiHelperAdapter aiHelperAdapter = this.this$0.mAdapter;
                if (aiHelperAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
                    aiHelperAdapter = null;
                }
                aiHelperAdapter.setList(list);
            }
        }));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (BleOperateManager.getInstance().isConnected()) {
            return;
        }
        String string = getString(C0775R.string.ble_glass_18);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        GlobalKt.showToast$default(string, 0, 1, null);
        finish();
    }

    /* JADX WARN: Type inference failed for: r3v7, types: [T, java.lang.Integer] */
    private final void initView() {
        final ActivityAiHelperBinding activityAiHelperBinding = this.binding;
        AiHelperAdapter aiHelperAdapter = null;
        if (activityAiHelperBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAiHelperBinding = null;
        }
        this.mAdapter = new AiHelperAdapter();
        LargeDataHandler.getInstance().aiVoiceWake(false, false, this.listener);
        activityAiHelperBinding.title.tvTitle.setText(getString(C0775R.string.h_glass_179));
        activityAiHelperBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.AIHelperActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AIHelperActivity.initView$lambda$6$lambda$2(this.f$0, view);
            }
        });
        switch (WhenMappings.$EnumSwitchMapping$0[CommonUtils.INSTANCE.getCurrentGlassTypeByHw().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                activityAiHelperBinding.tv2.setText(getString(C0775R.string.h_glass_168_a03));
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                activityAiHelperBinding.tv2.setText(getString(C0775R.string.h_glass_168_am01));
                break;
            case 9:
                activityAiHelperBinding.tv2.setText(getString(C0775R.string.g_guide_30));
                break;
            case 10:
                activityAiHelperBinding.tv2.setText(getString(C0775R.string.h_glass_168_v23));
                break;
        }
        activityAiHelperBinding.gsc1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.glasssutdio.wear.home.activity.AIHelperActivity$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                AIHelperActivity.initView$lambda$6$lambda$3(this.f$0, compoundButton, z);
            }
        });
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = 0;
        activityAiHelperBinding.cslTimbre.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.AIHelperActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AIHelperActivity.initView$lambda$6$lambda$4(objectRef, this, activityAiHelperBinding, view);
            }
        });
        AIHelperActivity aIHelperActivity = this;
        activityAiHelperBinding.recyclerView.setLayoutManager(new GridLayoutManager(aIHelperActivity, 2));
        activityAiHelperBinding.recyclerView.addItemDecoration(new SpacingItemDecoration(2, (int) GlobalKt.dp2px(aIHelperActivity, 10.0f), false, 4, null));
        RecyclerView recyclerView = activityAiHelperBinding.recyclerView;
        AiHelperAdapter aiHelperAdapter2 = this.mAdapter;
        if (aiHelperAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            aiHelperAdapter2 = null;
        }
        recyclerView.setAdapter(aiHelperAdapter2);
        AiHelperAdapter aiHelperAdapter3 = this.mAdapter;
        if (aiHelperAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        } else {
            aiHelperAdapter = aiHelperAdapter3;
        }
        aiHelperAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.glasssutdio.wear.home.activity.AIHelperActivity$$ExternalSyntheticLambda4
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                AIHelperActivity.initView$lambda$6$lambda$5(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$6$lambda$2(AIHelperActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$6$lambda$3(AIHelperActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (compoundButton.isPressed()) {
            LargeDataHandler.getInstance().aiVoiceWake(true, z, this$0.listener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void initView$lambda$6$lambda$4(final Ref.ObjectRef checkPosition, AIHelperActivity this$0, final ActivityAiHelperBinding this_run, View view) {
        Intrinsics.checkNotNullParameter(checkPosition, "$checkPosition");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        TimerSelectDialog timerSelectDialogBuild = new TimerSelectDialog.Builder().setDefaultCheck((Integer) checkPosition.element).build();
        timerSelectDialogBuild.setOnItemCheckedListener(new Function2<Integer, TimbreModel, Unit>() { // from class: com.glasssutdio.wear.home.activity.AIHelperActivity$initView$1$3$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, TimbreModel timbreModel) {
                invoke(num.intValue(), timbreModel);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.Integer] */
            public final void invoke(int i, TimbreModel model) {
                Intrinsics.checkNotNullParameter(model, "model");
                checkPosition.element = Integer.valueOf(i);
                this_run.tv4.setText(model.getTitle());
            }
        });
        timerSelectDialogBuild.show(this$0.getSupportFragmentManager(), "CustomBottomSheetDialogFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$6$lambda$5(AIHelperActivity this$0, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        if (i == 0) {
            AIHelperActivity aIHelperActivity = this$0;
            ArrayList<Pair> arrayList = new ArrayList();
            Intent intent = new Intent(aIHelperActivity, (Class<?>) AIWakeUpActivity.class);
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
            aIHelperActivity.startActivity(intent);
            return;
        }
        if (i == 1) {
            AIHelperActivity aIHelperActivity2 = this$0;
            Pair pair2 = new Pair(Constant.PAGE_TYPE, 1);
            ArrayList<Pair> arrayList2 = new ArrayList();
            arrayList2.add(pair2);
            Intent intent2 = new Intent(aIHelperActivity2, (Class<?>) AIModelAbilityActivity.class);
            for (Pair pair3 : arrayList2) {
                if (pair3 != null) {
                    String str2 = (String) pair3.getFirst();
                    Object second2 = pair3.getSecond();
                    if (second2 instanceof Integer) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).intValue()), "putExtra(...)");
                    } else if (second2 instanceof Byte) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).byteValue()), "putExtra(...)");
                    } else if (second2 instanceof Character) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Character) second2).charValue()), "putExtra(...)");
                    } else if (second2 instanceof Short) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).shortValue()), "putExtra(...)");
                    } else if (second2 instanceof Boolean) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Boolean) second2).booleanValue()), "putExtra(...)");
                    } else if (second2 instanceof Long) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).longValue()), "putExtra(...)");
                    } else if (second2 instanceof Float) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).floatValue()), "putExtra(...)");
                    } else if (second2 instanceof Double) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).doubleValue()), "putExtra(...)");
                    } else if (second2 instanceof String) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (String) second2), "putExtra(...)");
                    } else if (second2 instanceof CharSequence) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (CharSequence) second2), "putExtra(...)");
                    } else if (second2 instanceof Parcelable) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(...)");
                    } else if (second2 instanceof Object[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                    } else if (second2 instanceof ArrayList) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                    } else if (second2 instanceof Serializable) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                    } else if (second2 instanceof boolean[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (boolean[]) second2), "putExtra(...)");
                    } else if (second2 instanceof byte[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (byte[]) second2), "putExtra(...)");
                    } else if (second2 instanceof short[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (short[]) second2), "putExtra(...)");
                    } else if (second2 instanceof char[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (char[]) second2), "putExtra(...)");
                    } else if (second2 instanceof int[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (int[]) second2), "putExtra(...)");
                    } else if (second2 instanceof long[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (long[]) second2), "putExtra(...)");
                    } else if (second2 instanceof float[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (float[]) second2), "putExtra(...)");
                    } else if (second2 instanceof double[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (double[]) second2), "putExtra(...)");
                    } else if (second2 instanceof Bundle) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Bundle) second2), "putExtra(...)");
                    } else if (second2 instanceof Intent) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(...)");
                    } else {
                        Unit unit2 = Unit.INSTANCE;
                    }
                }
            }
            aIHelperActivity2.startActivity(intent2);
            return;
        }
        if (i != 2) {
            return;
        }
        AIHelperActivity aIHelperActivity3 = this$0;
        Pair pair4 = new Pair(Constant.PAGE_TYPE, 2);
        ArrayList<Pair> arrayList3 = new ArrayList();
        arrayList3.add(pair4);
        Intent intent3 = new Intent(aIHelperActivity3, (Class<?>) AIModelAbilityActivity.class);
        for (Pair pair5 : arrayList3) {
            if (pair5 != null) {
                String str3 = (String) pair5.getFirst();
                Object second3 = pair5.getSecond();
                if (second3 instanceof Integer) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).intValue()), "putExtra(...)");
                } else if (second3 instanceof Byte) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).byteValue()), "putExtra(...)");
                } else if (second3 instanceof Character) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Character) second3).charValue()), "putExtra(...)");
                } else if (second3 instanceof Short) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).shortValue()), "putExtra(...)");
                } else if (second3 instanceof Boolean) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Boolean) second3).booleanValue()), "putExtra(...)");
                } else if (second3 instanceof Long) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).longValue()), "putExtra(...)");
                } else if (second3 instanceof Float) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).floatValue()), "putExtra(...)");
                } else if (second3 instanceof Double) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).doubleValue()), "putExtra(...)");
                } else if (second3 instanceof String) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (String) second3), "putExtra(...)");
                } else if (second3 instanceof CharSequence) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (CharSequence) second3), "putExtra(...)");
                } else if (second3 instanceof Parcelable) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Parcelable) second3), "putExtra(...)");
                } else if (second3 instanceof Object[]) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Serializable) second3), "putExtra(...)");
                } else if (second3 instanceof ArrayList) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Serializable) second3), "putExtra(...)");
                } else if (second3 instanceof Serializable) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Serializable) second3), "putExtra(...)");
                } else if (second3 instanceof boolean[]) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (boolean[]) second3), "putExtra(...)");
                } else if (second3 instanceof byte[]) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (byte[]) second3), "putExtra(...)");
                } else if (second3 instanceof short[]) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (short[]) second3), "putExtra(...)");
                } else if (second3 instanceof char[]) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (char[]) second3), "putExtra(...)");
                } else if (second3 instanceof int[]) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (int[]) second3), "putExtra(...)");
                } else if (second3 instanceof long[]) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (long[]) second3), "putExtra(...)");
                } else if (second3 instanceof float[]) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (float[]) second3), "putExtra(...)");
                } else if (second3 instanceof double[]) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (double[]) second3), "putExtra(...)");
                } else if (second3 instanceof Bundle) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Bundle) second3), "putExtra(...)");
                } else if (second3 instanceof Intent) {
                    Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Parcelable) second3), "putExtra(...)");
                } else {
                    Unit unit3 = Unit.INSTANCE;
                }
            }
        }
        aIHelperActivity3.startActivity(intent3);
    }
}
