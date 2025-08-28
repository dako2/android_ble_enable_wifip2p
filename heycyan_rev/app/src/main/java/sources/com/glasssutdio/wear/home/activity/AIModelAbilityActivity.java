package com.glasssutdio.wear.home.activity;

import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.Constant;
import com.glasssutdio.wear.databinding.ActivityModelAbilityBinding;
import com.glasssutdio.wear.home.adapter.AIQuestionAdapter;
import com.glasssutdio.wear.home.bean.QuestionModel;
import com.glasssutdio.wear.home.viewmodel.ModelAbilityActivityVM;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import java.util.List;
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

/* compiled from: AIModelAbilityActivity.kt */
@Metadata(m606d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\u0012\u0010\u0010\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/AIModelAbilityActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityModelAbilityBinding;", "mAdapter", "Lcom/glasssutdio/wear/home/adapter/AIQuestionAdapter;", "mViewModel", "Lcom/glasssutdio/wear/home/viewmodel/ModelAbilityActivityVM;", "getMViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/ModelAbilityActivityVM;", "mViewModel$delegate", "Lkotlin/Lazy;", "initView", "", "observer", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AIModelAbilityActivity extends BaseSettingActivity {
    private ActivityModelAbilityBinding binding;
    private AIQuestionAdapter mAdapter;

    /* renamed from: mViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mViewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public AIModelAbilityActivity() {
        final AIModelAbilityActivity aIModelAbilityActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewModel = LazyKt.lazy(new Function0<ModelAbilityActivityVM>() { // from class: com.glasssutdio.wear.home.activity.AIModelAbilityActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.ModelAbilityActivityVM] */
            @Override // kotlin.jvm.functions.Function0
            public final ModelAbilityActivityVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(aIModelAbilityActivity, Reflection.getOrCreateKotlinClass(ModelAbilityActivityVM.class), qualifier, objArr);
            }
        });
    }

    private final ModelAbilityActivityVM getMViewModel() {
        return (ModelAbilityActivityVM) this.mViewModel.getValue();
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityModelAbilityBinding activityModelAbilityBindingInflate = ActivityModelAbilityBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityModelAbilityBindingInflate, "inflate(...)");
        this.binding = activityModelAbilityBindingInflate;
        getMViewModel().setPageType(getIntent().getIntExtra(Constant.PAGE_TYPE, 1));
        ActivityModelAbilityBinding activityModelAbilityBinding = this.binding;
        if (activityModelAbilityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityModelAbilityBinding = null;
        }
        setContentView(activityModelAbilityBinding.getRoot());
        initView();
        observer();
    }

    private final void observer() {
        ModelAbilityActivityVM mViewModel = getMViewModel();
        AIModelAbilityActivity aIModelAbilityActivity = this;
        mViewModel.getQuestionDataLD().observe(aIModelAbilityActivity, new AIModelAbilityActivity$sam$androidx_lifecycle_Observer$0(new Function1<List<QuestionModel>, Unit>() { // from class: com.glasssutdio.wear.home.activity.AIModelAbilityActivity$observer$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<QuestionModel> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<QuestionModel> list) {
                AIQuestionAdapter aIQuestionAdapter = this.this$0.mAdapter;
                if (aIQuestionAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
                    aIQuestionAdapter = null;
                }
                aIQuestionAdapter.setList(list);
            }
        }));
        mViewModel.getVisionDataLD().observe(aIModelAbilityActivity, new AIModelAbilityActivity$sam$androidx_lifecycle_Observer$0(new Function1<List<QuestionModel>, Unit>() { // from class: com.glasssutdio.wear.home.activity.AIModelAbilityActivity$observer$1$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<QuestionModel> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<QuestionModel> list) {
                AIQuestionAdapter aIQuestionAdapter = this.this$0.mAdapter;
                if (aIQuestionAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
                    aIQuestionAdapter = null;
                }
                aIQuestionAdapter.setList(list);
            }
        }));
    }

    private final void initView() {
        ActivityModelAbilityBinding activityModelAbilityBinding = this.binding;
        AIQuestionAdapter aIQuestionAdapter = null;
        if (activityModelAbilityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityModelAbilityBinding = null;
        }
        activityModelAbilityBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.AIModelAbilityActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AIModelAbilityActivity.initView$lambda$2$lambda$1(this.f$0, view);
            }
        });
        int pageType = getMViewModel().getPageType();
        if (pageType == 1) {
            activityModelAbilityBinding.title.tvTitle.setText(getString(C0775R.string.h_glass_192));
            activityModelAbilityBinding.tvDesc1.setText(getString(C0775R.string.h_glass_193));
            activityModelAbilityBinding.tvDesc2.setText(getString(C0775R.string.h_glass_194));
            getMViewModel().getQuestionData();
        } else if (pageType == 2) {
            activityModelAbilityBinding.title.tvTitle.setText(getString(C0775R.string.h_glass_195));
            activityModelAbilityBinding.tvDesc1.setText(getString(C0775R.string.h_glass_196));
            activityModelAbilityBinding.tvDesc2.setText(getString(C0775R.string.h_glass_197));
            getMViewModel().getVisionData();
        }
        this.mAdapter = new AIQuestionAdapter();
        activityModelAbilityBinding.rcyQuestion.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView = activityModelAbilityBinding.rcyQuestion;
        AIQuestionAdapter aIQuestionAdapter2 = this.mAdapter;
        if (aIQuestionAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        } else {
            aIQuestionAdapter = aIQuestionAdapter2;
        }
        recyclerView.setAdapter(aIQuestionAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$2$lambda$1(AIModelAbilityActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }
}
