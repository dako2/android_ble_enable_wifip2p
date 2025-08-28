package com.glasssutdio.wear.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.glasssutdio.wear.all.utils.ActivityHelper;
import com.glasssutdio.wear.databinding.ActivityAiGuideBinding;
import com.glasssutdio.wear.home.adapter.VoiceQuestionAdapter;
import com.glasssutdio.wear.home.bean.QuestionModel;
import com.glasssutdio.wear.home.viewmodel.AIGuideActivityVM;
import com.glasssutdio.wear.manager.BaseSettingActivity;
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
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AIGuideActivity.kt */
@Metadata(m606d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0002J\u0012\u0010\u0011\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/AIGuideActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityAiGuideBinding;", "mPhotoAdapter", "Lcom/glasssutdio/wear/home/adapter/VoiceQuestionAdapter;", "mViewModel", "Lcom/glasssutdio/wear/home/viewmodel/AIGuideActivityVM;", "getMViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/AIGuideActivityVM;", "mViewModel$delegate", "Lkotlin/Lazy;", "mVoiceAdapter", "initView", "", "observer", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AIGuideActivity extends BaseSettingActivity {
    private ActivityAiGuideBinding binding;
    private VoiceQuestionAdapter mPhotoAdapter;

    /* renamed from: mViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mViewModel = LazyKt.lazy(new Function0<AIGuideActivityVM>() { // from class: com.glasssutdio.wear.home.activity.AIGuideActivity$mViewModel$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final AIGuideActivityVM invoke() {
            return new AIGuideActivityVM();
        }
    });
    private VoiceQuestionAdapter mVoiceAdapter;

    private final AIGuideActivityVM getMViewModel() {
        return (AIGuideActivityVM) this.mViewModel.getValue();
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityAiGuideBinding activityAiGuideBindingInflate = ActivityAiGuideBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityAiGuideBindingInflate, "inflate(...)");
        this.binding = activityAiGuideBindingInflate;
        if (activityAiGuideBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAiGuideBindingInflate = null;
        }
        setContentView(activityAiGuideBindingInflate.getRoot());
        initView();
        observer();
    }

    private final void observer() {
        AIGuideActivityVM mViewModel = getMViewModel();
        AIGuideActivity aIGuideActivity = this;
        mViewModel.getVoiceDataLD().observe(aIGuideActivity, new AIGuideActivity$sam$androidx_lifecycle_Observer$0(new Function1<List<QuestionModel>, Unit>() { // from class: com.glasssutdio.wear.home.activity.AIGuideActivity$observer$1$1
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
                VoiceQuestionAdapter voiceQuestionAdapter = this.this$0.mVoiceAdapter;
                if (voiceQuestionAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mVoiceAdapter");
                    voiceQuestionAdapter = null;
                }
                voiceQuestionAdapter.setList(list);
            }
        }));
        mViewModel.getPhotoDataLD().observe(aIGuideActivity, new AIGuideActivity$sam$androidx_lifecycle_Observer$0(new Function1<List<QuestionModel>, Unit>() { // from class: com.glasssutdio.wear.home.activity.AIGuideActivity$observer$1$2
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
                VoiceQuestionAdapter voiceQuestionAdapter = this.this$0.mPhotoAdapter;
                if (voiceQuestionAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mPhotoAdapter");
                    voiceQuestionAdapter = null;
                }
                voiceQuestionAdapter.setList(list);
            }
        }));
    }

    private final void initView() {
        ActivityAiGuideBinding activityAiGuideBinding = this.binding;
        VoiceQuestionAdapter voiceQuestionAdapter = null;
        if (activityAiGuideBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAiGuideBinding = null;
        }
        activityAiGuideBinding.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.AIGuideActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AIGuideActivity.initView$lambda$3$lambda$1(this.f$0, view);
            }
        });
        AIGuideActivity aIGuideActivity = this;
        this.mVoiceAdapter = new VoiceQuestionAdapter(aIGuideActivity);
        activityAiGuideBinding.rcyVoice.setLayoutManager(new LinearLayoutManager(aIGuideActivity));
        RecyclerView recyclerView = activityAiGuideBinding.rcyVoice;
        VoiceQuestionAdapter voiceQuestionAdapter2 = this.mVoiceAdapter;
        if (voiceQuestionAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVoiceAdapter");
            voiceQuestionAdapter2 = null;
        }
        recyclerView.setAdapter(voiceQuestionAdapter2);
        this.mPhotoAdapter = new VoiceQuestionAdapter(aIGuideActivity);
        activityAiGuideBinding.rcyPhoto.setLayoutManager(new LinearLayoutManager(aIGuideActivity));
        RecyclerView recyclerView2 = activityAiGuideBinding.rcyPhoto;
        VoiceQuestionAdapter voiceQuestionAdapter3 = this.mPhotoAdapter;
        if (voiceQuestionAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPhotoAdapter");
        } else {
            voiceQuestionAdapter = voiceQuestionAdapter3;
        }
        recyclerView2.setAdapter(voiceQuestionAdapter);
        getMViewModel().getPageData();
        activityAiGuideBinding.tvContinue.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.AIGuideActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AIGuideActivity.initView$lambda$3$lambda$2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$3$lambda$1(AIGuideActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityHelper.INSTANCE.finish(AIGuideActivity.class);
        AIGuideActivity aIGuideActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(aIGuideActivity, (Class<?>) AIHelperActivity.class);
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
        aIGuideActivity.startActivity(intent);
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$3$lambda$2(AIGuideActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AIGuideActivity aIGuideActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(aIGuideActivity, (Class<?>) AIGuideNextActivity.class);
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
        aIGuideActivity.startActivity(intent);
    }
}
