package com.glasssutdio.wear.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.bean.CommonSelectModel;
import com.glasssutdio.wear.all.dialog.CommonSelectDialog;
import com.glasssutdio.wear.databinding.ActivityFeedbackSelectTypeBinding;
import com.glasssutdio.wear.home.adapter.FeedbackTypeAdapter;
import com.glasssutdio.wear.home.bean.QuestionModel;
import com.glasssutdio.wear.home.viewmodel.ModelAbilityActivityVM;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: FeedbackSelectTypeActivity.kt */
@Metadata(m606d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\u0012\u0010\u0013\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/FeedbackSelectTypeActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityFeedbackSelectTypeBinding;", "index", "", "mAdapter", "Lcom/glasssutdio/wear/home/adapter/FeedbackTypeAdapter;", "mViewModel", "Lcom/glasssutdio/wear/home/viewmodel/ModelAbilityActivityVM;", "getMViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/ModelAbilityActivityVM;", "mViewModel$delegate", "Lkotlin/Lazy;", "subIndex", "initView", "", "observer", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class FeedbackSelectTypeActivity extends BaseSettingActivity {
    public static final String FEED_TYPE_INDEX = "feed_type_index";
    public static final String FEED_TYPE_INDEX_SUB = "feed_type_index_sub";
    public static final String FEED_TYPE_IS_OTHER = "feed_type_is_other";
    public static final String FEED_TYPE_NAME = "feed_type_name";
    private ActivityFeedbackSelectTypeBinding binding;
    private int index;
    private FeedbackTypeAdapter mAdapter;

    /* renamed from: mViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mViewModel;
    private int subIndex;

    /* JADX WARN: Multi-variable type inference failed */
    public FeedbackSelectTypeActivity() {
        final FeedbackSelectTypeActivity feedbackSelectTypeActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewModel = LazyKt.lazy(new Function0<ModelAbilityActivityVM>() { // from class: com.glasssutdio.wear.home.activity.FeedbackSelectTypeActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.ModelAbilityActivityVM] */
            @Override // kotlin.jvm.functions.Function0
            public final ModelAbilityActivityVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(feedbackSelectTypeActivity, Reflection.getOrCreateKotlinClass(ModelAbilityActivityVM.class), qualifier, objArr);
            }
        });
        this.index = -1;
        this.subIndex = -1;
    }

    private final ModelAbilityActivityVM getMViewModel() {
        return (ModelAbilityActivityVM) this.mViewModel.getValue();
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityFeedbackSelectTypeBinding activityFeedbackSelectTypeBindingInflate = ActivityFeedbackSelectTypeBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityFeedbackSelectTypeBindingInflate, "inflate(...)");
        this.binding = activityFeedbackSelectTypeBindingInflate;
        if (activityFeedbackSelectTypeBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFeedbackSelectTypeBindingInflate = null;
        }
        setContentView(activityFeedbackSelectTypeBindingInflate.getRoot());
        this.index = getIntent().getIntExtra("feed_type_index", -1);
        this.subIndex = getIntent().getIntExtra("feed_type_index_sub", -1);
        initView();
        observer();
    }

    private final void observer() {
        getMViewModel();
    }

    private final void initView() {
        ActivityFeedbackSelectTypeBinding activityFeedbackSelectTypeBinding = this.binding;
        FeedbackTypeAdapter feedbackTypeAdapter = null;
        if (activityFeedbackSelectTypeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFeedbackSelectTypeBinding = null;
        }
        activityFeedbackSelectTypeBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.FeedbackSelectTypeActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedbackSelectTypeActivity.initView$lambda$5$lambda$1(this.f$0, view);
            }
        });
        activityFeedbackSelectTypeBinding.title.tvTitle.setText(getString(C0775R.string.g_feedback_2));
        String string = getString(C0775R.string.g_feedback_question_type_1);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        int i = 0;
        String string2 = getString(C0775R.string.g_feedback_question_type_2);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        String string3 = getString(C0775R.string.g_feedback_question_type_3);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        String string4 = getString(C0775R.string.g_feedback_question_type_4);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        String string5 = getString(C0775R.string.g_feedback_question_type_5);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
        String string6 = getString(C0775R.string.g_feedback_question_type_6);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
        List listListOf = CollectionsKt.listOf((Object[]) new String[]{string, string2, string3, string4, string5, string6});
        final ArrayList arrayList = new ArrayList();
        for (Object obj : listListOf) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(new QuestionModel((String) obj, i));
            i = i2;
        }
        this.mAdapter = new FeedbackTypeAdapter();
        activityFeedbackSelectTypeBinding.rcyQuestion.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView = activityFeedbackSelectTypeBinding.rcyQuestion;
        FeedbackTypeAdapter feedbackTypeAdapter2 = this.mAdapter;
        if (feedbackTypeAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            feedbackTypeAdapter2 = null;
        }
        recyclerView.setAdapter(feedbackTypeAdapter2);
        FeedbackTypeAdapter feedbackTypeAdapter3 = this.mAdapter;
        if (feedbackTypeAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            feedbackTypeAdapter3 = null;
        }
        feedbackTypeAdapter3.setList(arrayList);
        FeedbackTypeAdapter feedbackTypeAdapter4 = this.mAdapter;
        if (feedbackTypeAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        } else {
            feedbackTypeAdapter = feedbackTypeAdapter4;
        }
        feedbackTypeAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.glasssutdio.wear.home.activity.FeedbackSelectTypeActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i3) {
                FeedbackSelectTypeActivity.initView$lambda$5$lambda$4(arrayList, this, baseQuickAdapter, view, i3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$5$lambda$1(FeedbackSelectTypeActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$5$lambda$4(List list, final FeedbackSelectTypeActivity this$0, BaseQuickAdapter adapter, View view, final int i) {
        int i2;
        Intrinsics.checkNotNullParameter(list, "$list");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        final QuestionModel questionModel = (QuestionModel) list.get(i);
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        if (i == 0) {
            String string = this$0.getString(C0775R.string.g_feedback_type_1_1);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String string2 = this$0.getString(C0775R.string.g_feedback_type_1_2);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            String string3 = this$0.getString(C0775R.string.g_feedback_type_1_3);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            String string4 = this$0.getString(C0775R.string.g_feedback_type_1_4);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
            String string5 = this$0.getString(C0775R.string.g_feedback_type_1_5);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
            String string6 = this$0.getString(C0775R.string.g_feedback_type_1_6);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
            String string7 = this$0.getString(C0775R.string.g_feedback_type_1_7);
            Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
            arrayList.addAll(CollectionsKt.listOf((Object[]) new String[]{string, string2, string3, string4, string5, string6, string7}));
        } else if (i == 1) {
            String string8 = this$0.getString(C0775R.string.g_feedback_type_2_1);
            Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
            String string9 = this$0.getString(C0775R.string.g_feedback_type_2_2);
            Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
            String string10 = this$0.getString(C0775R.string.g_feedback_type_2_3);
            Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
            String string11 = this$0.getString(C0775R.string.g_feedback_type_2_4);
            Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
            String string12 = this$0.getString(C0775R.string.g_feedback_type_2_5);
            Intrinsics.checkNotNullExpressionValue(string12, "getString(...)");
            String string13 = this$0.getString(C0775R.string.g_feedback_type_2_6);
            Intrinsics.checkNotNullExpressionValue(string13, "getString(...)");
            String string14 = this$0.getString(C0775R.string.g_feedback_type_2_7);
            Intrinsics.checkNotNullExpressionValue(string14, "getString(...)");
            String string15 = this$0.getString(C0775R.string.g_feedback_type_2_8);
            Intrinsics.checkNotNullExpressionValue(string15, "getString(...)");
            arrayList.addAll(CollectionsKt.listOf((Object[]) new String[]{string8, string9, string10, string11, string12, string13, string14, string15}));
        } else if (i == 2) {
            String string16 = this$0.getString(C0775R.string.g_feedback_type_3_1);
            Intrinsics.checkNotNullExpressionValue(string16, "getString(...)");
            String string17 = this$0.getString(C0775R.string.g_feedback_type_3_2);
            Intrinsics.checkNotNullExpressionValue(string17, "getString(...)");
            String string18 = this$0.getString(C0775R.string.g_feedback_type_3_3);
            Intrinsics.checkNotNullExpressionValue(string18, "getString(...)");
            String string19 = this$0.getString(C0775R.string.g_feedback_type_3_4);
            Intrinsics.checkNotNullExpressionValue(string19, "getString(...)");
            String string20 = this$0.getString(C0775R.string.g_feedback_type_3_5);
            Intrinsics.checkNotNullExpressionValue(string20, "getString(...)");
            String string21 = this$0.getString(C0775R.string.g_feedback_type_3_6);
            Intrinsics.checkNotNullExpressionValue(string21, "getString(...)");
            String string22 = this$0.getString(C0775R.string.g_feedback_type_3_7);
            Intrinsics.checkNotNullExpressionValue(string22, "getString(...)");
            arrayList.addAll(CollectionsKt.listOf((Object[]) new String[]{string16, string17, string18, string19, string20, string21, string22}));
        } else if (i == 3) {
            String string23 = this$0.getString(C0775R.string.g_feedback_type_4_1);
            Intrinsics.checkNotNullExpressionValue(string23, "getString(...)");
            String string24 = this$0.getString(C0775R.string.g_feedback_type_4_2);
            Intrinsics.checkNotNullExpressionValue(string24, "getString(...)");
            String string25 = this$0.getString(C0775R.string.g_feedback_type_4_3);
            Intrinsics.checkNotNullExpressionValue(string25, "getString(...)");
            String string26 = this$0.getString(C0775R.string.g_feedback_type_4_4);
            Intrinsics.checkNotNullExpressionValue(string26, "getString(...)");
            String string27 = this$0.getString(C0775R.string.g_feedback_type_4_5);
            Intrinsics.checkNotNullExpressionValue(string27, "getString(...)");
            String string28 = this$0.getString(C0775R.string.g_feedback_type_4_6);
            Intrinsics.checkNotNullExpressionValue(string28, "getString(...)");
            String string29 = this$0.getString(C0775R.string.g_feedback_type_4_7);
            Intrinsics.checkNotNullExpressionValue(string29, "getString(...)");
            arrayList.addAll(CollectionsKt.listOf((Object[]) new String[]{string23, string24, string25, string26, string27, string28, string29}));
        } else if (i == 4) {
            String string30 = this$0.getString(C0775R.string.g_feedback_type_5_1);
            Intrinsics.checkNotNullExpressionValue(string30, "getString(...)");
            String string31 = this$0.getString(C0775R.string.g_feedback_type_5_2);
            Intrinsics.checkNotNullExpressionValue(string31, "getString(...)");
            String string32 = this$0.getString(C0775R.string.g_feedback_type_5_3);
            Intrinsics.checkNotNullExpressionValue(string32, "getString(...)");
            arrayList.addAll(CollectionsKt.listOf((Object[]) new String[]{string30, string31, string32}));
        } else if (i == 5) {
            Intent intent = new Intent();
            intent.putExtra("feed_type_is_other", true);
            intent.putExtra("feed_type_name", this$0.getString(C0775R.string.h_glass_190));
            this$0.setResult(-1, intent);
            this$0.finish();
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new CommonSelectModel((String) it.next(), false, 0, null, 14, null));
        }
        int i4 = this$0.index;
        if (i4 != -1 && i4 == i && (i2 = this$0.subIndex) != -1) {
            i3 = i2;
        }
        CommonSelectDialog commonSelectDialogBuild = new CommonSelectDialog.Builder().setDefaultChecked(i3).setTitle(questionModel.getTitle()).setData(arrayList2).build();
        commonSelectDialogBuild.setOnItemCheckedListener(new Function2<Integer, CommonSelectModel, Unit>() { // from class: com.glasssutdio.wear.home.activity.FeedbackSelectTypeActivity$initView$1$3$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, CommonSelectModel commonSelectModel) {
                invoke(num.intValue(), commonSelectModel);
                return Unit.INSTANCE;
            }

            public final void invoke(int i5, CommonSelectModel subModel) {
                Intrinsics.checkNotNullParameter(subModel, "subModel");
                this.this$0.subIndex = i5;
                this.this$0.index = i;
                Intent intent2 = new Intent();
                intent2.putExtra("feed_type_is_other", false);
                intent2.putExtra("feed_type_index", this.this$0.index);
                intent2.putExtra("feed_type_index_sub", this.this$0.subIndex);
                intent2.putExtra("feed_type_name", questionModel.getTitle() + '\\' + subModel.getName());
                this.this$0.setResult(-1, intent2);
                this.this$0.finish();
            }
        });
        commonSelectDialogBuild.show(this$0.getSupportFragmentManager(), "typeDialog" + i);
    }
}
