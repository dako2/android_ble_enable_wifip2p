package com.glasssutdio.wear.home.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.database.entity.TranslateEntity;
import com.glasssutdio.wear.databinding.ActivityTranslateDetailBinding;
import com.glasssutdio.wear.home.viewmodel.AiTranslateVM;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: TranslateHistoryDetailActivity.kt */
@Metadata(m606d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/TranslateHistoryDetailActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityTranslateDetailBinding;", "createTime", "", "mViewModel", "Lcom/glasssutdio/wear/home/viewmodel/AiTranslateVM;", "getMViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/AiTranslateVM;", "mViewModel$delegate", "Lkotlin/Lazy;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class TranslateHistoryDetailActivity extends BaseSettingActivity {
    public static final String HISTORY_CREATE_TIME = "history_create_time";
    private ActivityTranslateDetailBinding binding;
    private long createTime;

    /* renamed from: mViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mViewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public TranslateHistoryDetailActivity() {
        final TranslateHistoryDetailActivity translateHistoryDetailActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewModel = LazyKt.lazy(new Function0<AiTranslateVM>() { // from class: com.glasssutdio.wear.home.activity.TranslateHistoryDetailActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.AiTranslateVM] */
            @Override // kotlin.jvm.functions.Function0
            public final AiTranslateVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(translateHistoryDetailActivity, Reflection.getOrCreateKotlinClass(AiTranslateVM.class), qualifier, objArr);
            }
        });
    }

    private final AiTranslateVM getMViewModel() {
        return (AiTranslateVM) this.mViewModel.getValue();
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityTranslateDetailBinding activityTranslateDetailBindingInflate = ActivityTranslateDetailBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityTranslateDetailBindingInflate, "inflate(...)");
        this.binding = activityTranslateDetailBindingInflate;
        if (activityTranslateDetailBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateDetailBindingInflate = null;
        }
        setContentView(activityTranslateDetailBindingInflate.getRoot());
        this.createTime = getIntent().getLongExtra(HISTORY_CREATE_TIME, 0L);
        initView();
    }

    private final void initView() {
        final ActivityTranslateDetailBinding activityTranslateDetailBinding = this.binding;
        if (activityTranslateDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateDetailBinding = null;
        }
        activityTranslateDetailBinding.title.tvTitle.setText(getString(C0775R.string.g_translate_14));
        activityTranslateDetailBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.TranslateHistoryDetailActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TranslateHistoryDetailActivity.initView$lambda$3$lambda$0(this.f$0, view);
            }
        });
        getMViewModel().queryByCreateTime(this.createTime);
        getMViewModel().getTranslateDetailLD().observe(this, new TranslateHistoryDetailActivity$sam$androidx_lifecycle_Observer$0(new Function1<TranslateEntity, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateHistoryDetailActivity$initView$1$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TranslateEntity translateEntity) {
                invoke2(translateEntity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TranslateEntity translateEntity) {
                activityTranslateDetailBinding.etTop.setText(translateEntity.getSrcContent());
                activityTranslateDetailBinding.etBottom.setText(translateEntity.getDstContent());
                if (translateEntity.getTranslateTitle().length() > 0) {
                    activityTranslateDetailBinding.title.tvTitle.setText(translateEntity.getTranslateTitle());
                }
            }
        }));
        ActivityTranslateDetailBinding activityTranslateDetailBinding2 = this.binding;
        if (activityTranslateDetailBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateDetailBinding2 = null;
        }
        EditText editText = activityTranslateDetailBinding2.etTop;
        editText.setFocusable(false);
        editText.setClickable(true);
        editText.setLongClickable(true);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        ActivityTranslateDetailBinding activityTranslateDetailBinding3 = this.binding;
        if (activityTranslateDetailBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateDetailBinding3 = null;
        }
        EditText editText2 = activityTranslateDetailBinding3.etBottom;
        editText2.setFocusable(false);
        editText2.setClickable(true);
        editText2.setLongClickable(true);
        editText2.setCursorVisible(false);
        editText2.setKeyListener(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$3$lambda$0(TranslateHistoryDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }
}
