package com.glasssutdio.wear.home.activity;

import android.os.Bundle;
import android.view.View;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.databinding.ActivityVersionDownloadBinding;
import com.glasssutdio.wear.home.viewmodel.CheckVersionVM;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: VersionDownloadActivity.kt */
@Metadata(m606d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/VersionDownloadActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityVersionDownloadBinding;", "mViewMode", "Lcom/glasssutdio/wear/home/viewmodel/CheckVersionVM;", "getMViewMode", "()Lcom/glasssutdio/wear/home/viewmodel/CheckVersionVM;", "mViewMode$delegate", "Lkotlin/Lazy;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class VersionDownloadActivity extends BaseSettingActivity {
    private ActivityVersionDownloadBinding binding;

    /* renamed from: mViewMode$delegate, reason: from kotlin metadata */
    private final Lazy mViewMode;

    /* JADX WARN: Multi-variable type inference failed */
    public VersionDownloadActivity() {
        final VersionDownloadActivity versionDownloadActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewMode = LazyKt.lazy(new Function0<CheckVersionVM>() { // from class: com.glasssutdio.wear.home.activity.VersionDownloadActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.CheckVersionVM] */
            @Override // kotlin.jvm.functions.Function0
            public final CheckVersionVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(versionDownloadActivity, Reflection.getOrCreateKotlinClass(CheckVersionVM.class), qualifier, objArr);
            }
        });
    }

    private final CheckVersionVM getMViewMode() {
        return (CheckVersionVM) this.mViewMode.getValue();
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityVersionDownloadBinding activityVersionDownloadBindingInflate = ActivityVersionDownloadBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityVersionDownloadBindingInflate, "inflate(...)");
        this.binding = activityVersionDownloadBindingInflate;
        if (activityVersionDownloadBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVersionDownloadBindingInflate = null;
        }
        setContentView(activityVersionDownloadBindingInflate.getRoot());
        initView();
    }

    private final void initView() {
        ActivityVersionDownloadBinding activityVersionDownloadBinding = this.binding;
        if (activityVersionDownloadBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVersionDownloadBinding = null;
        }
        activityVersionDownloadBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.VersionDownloadActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VersionDownloadActivity.initView$lambda$1$lambda$0(this.f$0, view);
            }
        });
        activityVersionDownloadBinding.title.tvTitle.setText(getString(C0775R.string.version_glass_1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$1$lambda$0(VersionDownloadActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }
}
