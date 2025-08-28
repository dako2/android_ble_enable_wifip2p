package com.glasssutdio.wear.setting;

import android.os.Bundle;
import android.view.View;
import com.glasssutdio.wear.databinding.ActivityWebStartBinding;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WebStartActivity.kt */
@Metadata(m606d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\n"}, m607d2 = {"Lcom/glasssutdio/wear/setting/WebStartActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityWebStartBinding;", "initViews", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class WebStartActivity extends BaseSettingActivity {
    private ActivityWebStartBinding binding;

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityWebStartBinding activityWebStartBindingInflate = ActivityWebStartBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityWebStartBindingInflate, "inflate(...)");
        this.binding = activityWebStartBindingInflate;
        if (activityWebStartBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWebStartBindingInflate = null;
        }
        setContentView(activityWebStartBindingInflate.getRoot());
        initViews();
    }

    private final void initViews() {
        ActivityWebStartBinding activityWebStartBinding = this.binding;
        if (activityWebStartBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWebStartBinding = null;
        }
        activityWebStartBinding.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.setting.WebStartActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebStartActivity.initViews$lambda$0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$0(WebStartActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }
}
