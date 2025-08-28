package com.glasssutdio.wear.setting;

import android.os.Bundle;
import android.view.View;
import com.glasssutdio.wear.databinding.ActivityScorpionBinding;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScorpionActivity.kt */
@Metadata(m606d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\n"}, m607d2 = {"Lcom/glasssutdio/wear/setting/ScorpionActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityScorpionBinding;", "initViews", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class ScorpionActivity extends BaseSettingActivity {
    private ActivityScorpionBinding binding;

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityScorpionBinding activityScorpionBindingInflate = ActivityScorpionBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityScorpionBindingInflate, "inflate(...)");
        this.binding = activityScorpionBindingInflate;
        if (activityScorpionBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityScorpionBindingInflate = null;
        }
        setContentView(activityScorpionBindingInflate.getRoot());
        initViews();
    }

    private final void initViews() {
        ActivityScorpionBinding activityScorpionBinding = this.binding;
        if (activityScorpionBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityScorpionBinding = null;
        }
        activityScorpionBinding.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.setting.ScorpionActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScorpionActivity.initViews$lambda$1$lambda$0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$1$lambda$0(ScorpionActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }
}
