package com.glasssutdio.wear.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import com.glasssutdio.wear.all.utils.GFileUtilKt;
import com.glasssutdio.wear.databinding.ActivityVoiceBinding;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import com.iflytek.sparkchain.core.asr.ASR;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoiceActivity.kt */
@Metadata(m606d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0012\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m607d2 = {"Lcom/glasssutdio/wear/setting/VoiceActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "TAG", "", "asr", "Lcom/iflytek/sparkchain/core/asr/ASR;", "binding", "Lcom/glasssutdio/wear/databinding/ActivityVoiceBinding;", "filePath", "mTypeSpinner", "Landroid/widget/Spinner;", "tv_result", "Landroid/widget/TextView;", "initViews", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class VoiceActivity extends BaseSettingActivity {
    private final String TAG = "AEELog";
    private ASR asr;
    private ActivityVoiceBinding binding;
    private String filePath;
    private Spinner mTypeSpinner;
    private TextView tv_result;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$2$lambda$1(View view) {
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityVoiceBinding activityVoiceBindingInflate = ActivityVoiceBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityVoiceBindingInflate, "inflate(...)");
        this.binding = activityVoiceBindingInflate;
        if (activityVoiceBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVoiceBindingInflate = null;
        }
        setContentView(activityVoiceBindingInflate.getRoot());
        initViews();
    }

    private final void initViews() {
        this.filePath = GFileUtilKt.getGPTDirFile().getAbsolutePath() + "/cn.pcm";
        ActivityVoiceBinding activityVoiceBinding = this.binding;
        if (activityVoiceBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVoiceBinding = null;
        }
        activityVoiceBinding.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.setting.VoiceActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VoiceActivity.initViews$lambda$2$lambda$0(this.f$0, view);
            }
        });
        activityVoiceBinding.ctlVoice1.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.setting.VoiceActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VoiceActivity.initViews$lambda$2$lambda$1(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$2$lambda$0(VoiceActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }
}
