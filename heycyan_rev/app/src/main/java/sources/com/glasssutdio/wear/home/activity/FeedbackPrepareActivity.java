package com.glasssutdio.wear.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.TextViewExtKt;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.databinding.ActivityFeedbackPreBinding;
import com.glasssutdio.wear.home.viewmodel.MainActivityVM;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: FeedbackPrepareActivity.kt */
@Metadata(m606d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010$\u001a\u00020%H\u0003J\b\u0010&\u001a\u00020%H\u0002J\u0012\u0010'\u001a\u00020%2\b\u0010(\u001a\u0004\u0018\u00010)H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006*"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/FeedbackPrepareActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityFeedbackPreBinding;", "index", "", "getIndex", "()I", "setIndex", "(I)V", "indexSub", "getIndexSub", "setIndexSub", "isOther", "", "()Z", "setOther", "(Z)V", "launcher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "getLauncher", "()Landroidx/activity/result/ActivityResultLauncher;", "mViewModel", "Lcom/glasssutdio/wear/home/viewmodel/MainActivityVM;", "getMViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/MainActivityVM;", "mViewModel$delegate", "Lkotlin/Lazy;", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "initView", "", "observer", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class FeedbackPrepareActivity extends BaseSettingActivity {
    private ActivityFeedbackPreBinding binding;
    private int index;
    private int indexSub;
    private boolean isOther;
    private final ActivityResultLauncher<Intent> launcher;

    /* renamed from: mViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mViewModel;
    private String name;

    /* JADX WARN: Multi-variable type inference failed */
    public FeedbackPrepareActivity() {
        final FeedbackPrepareActivity feedbackPrepareActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewModel = LazyKt.lazy(new Function0<MainActivityVM>() { // from class: com.glasssutdio.wear.home.activity.FeedbackPrepareActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.MainActivityVM] */
            @Override // kotlin.jvm.functions.Function0
            public final MainActivityVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(feedbackPrepareActivity, Reflection.getOrCreateKotlinClass(MainActivityVM.class), qualifier, objArr);
            }
        });
        this.index = -1;
        this.indexSub = -1;
        this.name = "";
        this.launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.glasssutdio.wear.home.activity.FeedbackPrepareActivity$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                FeedbackPrepareActivity.launcher$lambda$0(this.f$0, (ActivityResult) obj);
            }
        });
    }

    private final MainActivityVM getMViewModel() {
        return (MainActivityVM) this.mViewModel.getValue();
    }

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int i) {
        this.index = i;
    }

    public final int getIndexSub() {
        return this.indexSub;
    }

    public final void setIndexSub(int i) {
        this.indexSub = i;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    /* renamed from: isOther, reason: from getter */
    public final boolean getIsOther() {
        return this.isOther;
    }

    public final void setOther(boolean z) {
        this.isOther = z;
    }

    public final ActivityResultLauncher<Intent> getLauncher() {
        return this.launcher;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void launcher$lambda$0(FeedbackPrepareActivity this$0, ActivityResult it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.getResultCode() == -1) {
            Intent data = it.getData();
            this$0.index = data != null ? data.getIntExtra("feed_type_index", -1) : -1;
            Intent data2 = it.getData();
            this$0.indexSub = data2 != null ? data2.getIntExtra("feed_type_index_sub", -1) : -1;
            Intent data3 = it.getData();
            ActivityFeedbackPreBinding activityFeedbackPreBinding = null;
            String stringExtra = data3 != null ? data3.getStringExtra("feed_type_name") : null;
            if (stringExtra == null) {
                stringExtra = "";
            }
            this$0.name = stringExtra;
            Intent data4 = it.getData();
            boolean z = false;
            if (data4 != null && data4.getBooleanExtra("feed_type_is_other", false)) {
                z = true;
            }
            this$0.isOther = z;
            ActivityFeedbackPreBinding activityFeedbackPreBinding2 = this$0.binding;
            if (activityFeedbackPreBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityFeedbackPreBinding = activityFeedbackPreBinding2;
            }
            activityFeedbackPreBinding.tvTypeName.setText(this$0.name);
        }
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityFeedbackPreBinding activityFeedbackPreBindingInflate = ActivityFeedbackPreBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityFeedbackPreBindingInflate, "inflate(...)");
        this.binding = activityFeedbackPreBindingInflate;
        if (activityFeedbackPreBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFeedbackPreBindingInflate = null;
        }
        setContentView(activityFeedbackPreBindingInflate.getRoot());
        initView();
        observer();
    }

    private final void observer() {
        getMViewModel();
    }

    private final void initView() {
        ActivityFeedbackPreBinding activityFeedbackPreBinding = this.binding;
        if (activityFeedbackPreBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFeedbackPreBinding = null;
        }
        activityFeedbackPreBinding.title.tvTitle.setText(getString(C0775R.string.h_glass_267));
        activityFeedbackPreBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.FeedbackPrepareActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedbackPrepareActivity.initView$lambda$5$lambda$2(this.f$0, view);
            }
        });
        TextView tvTypeName = activityFeedbackPreBinding.tvTypeName;
        Intrinsics.checkNotNullExpressionValue(tvTypeName, "tvTypeName");
        TextViewExtKt.setupMarquee(tvTypeName);
        TextView tvTypeHint = activityFeedbackPreBinding.tvTypeHint;
        Intrinsics.checkNotNullExpressionValue(tvTypeHint, "tvTypeHint");
        TextViewExtKt.setupMarquee(tvTypeHint);
        activityFeedbackPreBinding.tvDeviceName.setText(UserConfig.INSTANCE.getInstance().getDeviceName());
        activityFeedbackPreBinding.clsFeedbackType.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.FeedbackPrepareActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedbackPrepareActivity.initView$lambda$5$lambda$3(this.f$0, view);
            }
        });
        activityFeedbackPreBinding.tvConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.FeedbackPrepareActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedbackPrepareActivity.initView$lambda$5$lambda$4(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$5$lambda$2(FeedbackPrepareActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$5$lambda$3(FeedbackPrepareActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0, (Class<?>) FeedbackSelectTypeActivity.class);
        intent.putExtra("feed_type_index", this$0.index);
        intent.putExtra("feed_type_index_sub", this$0.indexSub);
        this$0.launcher.launch(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$5$lambda$4(FeedbackPrepareActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Bundle bundle = new Bundle();
        bundle.putString("feed_type_name", this$0.name);
        bundle.putInt("feed_type_index", this$0.index);
        bundle.putInt("feed_type_index_sub", this$0.indexSub);
        bundle.putBoolean("feed_type_is_other", this$0.isOther);
        FeedbackPrepareActivity feedbackPrepareActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(feedbackPrepareActivity, (Class<?>) FeedbackActivity.class);
        intent.putExtras(bundle);
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
        feedbackPrepareActivity.startActivity(intent);
    }
}
