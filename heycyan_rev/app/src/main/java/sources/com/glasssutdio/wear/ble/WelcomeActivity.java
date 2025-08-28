package com.glasssutdio.wear.ble;

import android.os.Bundle;
import android.view.View;
import com.glasssutdio.wear.databinding.ActivityWelcomeBinding;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WelcomeActivity.kt */
@Metadata(m606d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\n"}, m607d2 = {"Lcom/glasssutdio/wear/ble/WelcomeActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityWelcomeBinding;", "initViews", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class WelcomeActivity extends BaseSettingActivity {
    private ActivityWelcomeBinding binding;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$1$lambda$0(View view) {
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityWelcomeBinding activityWelcomeBindingInflate = ActivityWelcomeBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityWelcomeBindingInflate, "inflate(...)");
        this.binding = activityWelcomeBindingInflate;
        if (activityWelcomeBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWelcomeBindingInflate = null;
        }
        setContentView(activityWelcomeBindingInflate.getRoot());
        initViews();
    }

    private final void initViews() {
        ActivityWelcomeBinding activityWelcomeBinding = this.binding;
        if (activityWelcomeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWelcomeBinding = null;
        }
        activityWelcomeBinding.btnWelcome.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.ble.WelcomeActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WelcomeActivity.initViews$lambda$1$lambda$0(view);
            }
        });
    }
}
