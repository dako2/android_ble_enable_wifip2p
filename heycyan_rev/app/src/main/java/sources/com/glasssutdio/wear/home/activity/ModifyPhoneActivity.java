package com.glasssutdio.wear.home.activity;

import android.os.Bundle;
import android.view.View;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.databinding.ActivityModifyPhoneBinding;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ModifyPhoneActivity.kt */
@Metadata(m606d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\n"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/ModifyPhoneActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityModifyPhoneBinding;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class ModifyPhoneActivity extends BaseSettingActivity {
    private ActivityModifyPhoneBinding binding;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$2$lambda$1(View view) {
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityModifyPhoneBinding activityModifyPhoneBindingInflate = ActivityModifyPhoneBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityModifyPhoneBindingInflate, "inflate(...)");
        this.binding = activityModifyPhoneBindingInflate;
        if (activityModifyPhoneBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityModifyPhoneBindingInflate = null;
        }
        setContentView(activityModifyPhoneBindingInflate.getRoot());
        initView();
    }

    private final void initView() {
        ActivityModifyPhoneBinding activityModifyPhoneBinding = this.binding;
        if (activityModifyPhoneBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityModifyPhoneBinding = null;
        }
        activityModifyPhoneBinding.title.tvTitle.setText(getString(C0775R.string.h_glass_247));
        activityModifyPhoneBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.ModifyPhoneActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ModifyPhoneActivity.initView$lambda$2$lambda$0(this.f$0, view);
            }
        });
        activityModifyPhoneBinding.tvConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.ModifyPhoneActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ModifyPhoneActivity.initView$lambda$2$lambda$1(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$2$lambda$0(ModifyPhoneActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }
}
