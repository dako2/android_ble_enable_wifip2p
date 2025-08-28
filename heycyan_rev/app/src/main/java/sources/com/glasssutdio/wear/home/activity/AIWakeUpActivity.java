package com.glasssutdio.wear.home.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.utils.AssetsAudioPlayer;
import com.glasssutdio.wear.all.utils.CommonUtils;
import com.glasssutdio.wear.databinding.ActivityAiWakeUpBinding;
import com.glasssutdio.wear.home.bean.GlassesType;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AIWakeUpActivity.kt */
@Metadata(m606d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\b\u0010\n\u001a\u00020\u0006H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/AIWakeUpActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityAiWakeUpBinding;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AIWakeUpActivity extends BaseSettingActivity {
    private ActivityAiWakeUpBinding binding;

    /* compiled from: AIWakeUpActivity.kt */
    @Metadata(m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[GlassesType.values().length];
            try {
                iArr[GlassesType.AO3.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[GlassesType.KEY40.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[GlassesType.KEY31.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[GlassesType.KEY21.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[GlassesType.AM01.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[GlassesType.KEY41.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[GlassesType.KEY42.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[GlassesType.KEY43.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[GlassesType.KEY22.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[GlassesType.KEY23.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$3$lambda$1(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$3$lambda$2(View view) {
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityAiWakeUpBinding activityAiWakeUpBindingInflate = ActivityAiWakeUpBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityAiWakeUpBindingInflate, "inflate(...)");
        this.binding = activityAiWakeUpBindingInflate;
        if (activityAiWakeUpBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAiWakeUpBindingInflate = null;
        }
        setContentView(activityAiWakeUpBindingInflate.getRoot());
        initView();
    }

    private final void initView() {
        ActivityAiWakeUpBinding activityAiWakeUpBinding = this.binding;
        if (activityAiWakeUpBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAiWakeUpBinding = null;
        }
        ImageView ivPlay = activityAiWakeUpBinding.ivPlay;
        Intrinsics.checkNotNullExpressionValue(ivPlay, "ivPlay");
        ViewKt.click$default(ivPlay, 0L, new Function1<View, Unit>() { // from class: com.glasssutdio.wear.home.activity.AIWakeUpActivity$initView$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) throws IllegalStateException, IOException, IllegalArgumentException {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) throws IllegalStateException, IOException, IllegalArgumentException {
                Intrinsics.checkNotNullParameter(it, "it");
                AssetsAudioPlayer.playAsset$default(AssetsAudioPlayer.INSTANCE.getInstance(), this.this$0, "heyCyan_voice.MP3", false, null, 12, null);
            }
        }, 1, null);
        ImageView ivPlay2 = activityAiWakeUpBinding.ivPlay;
        Intrinsics.checkNotNullExpressionValue(ivPlay2, "ivPlay");
        ViewKt.addClickAnimation$default(ivPlay2, activityAiWakeUpBinding.ivPlay, null, null, 6, null);
        activityAiWakeUpBinding.title.tvTitle.setText(getString(C0775R.string.h_glass_171));
        activityAiWakeUpBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.AIWakeUpActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AIWakeUpActivity.initView$lambda$3$lambda$0(this.f$0, view);
            }
        });
        switch (WhenMappings.$EnumSwitchMapping$0[CommonUtils.INSTANCE.getCurrentGlassTypeByHw().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                activityAiWakeUpBinding.tv4.setText(getString(C0775R.string.h_glass_163_a03));
                activityAiWakeUpBinding.tv5.setText(getString(C0775R.string.h_glass_164_a03));
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                activityAiWakeUpBinding.tv4.setText(getString(C0775R.string.h_glass_163_am01));
                activityAiWakeUpBinding.tv5.setText(getString(C0775R.string.h_glass_164_am01));
                break;
            case 9:
                activityAiWakeUpBinding.tv4.setText(getString(C0775R.string.g_guide_28));
                activityAiWakeUpBinding.tv5.setText(getString(C0775R.string.g_guide_29));
                break;
            case 10:
                activityAiWakeUpBinding.tv4.setText(getString(C0775R.string.h_glass_163_v23));
                activityAiWakeUpBinding.tv5.setText(getString(C0775R.string.h_glass_164_v23));
                break;
        }
        activityAiWakeUpBinding.clsVoice.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.AIWakeUpActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AIWakeUpActivity.initView$lambda$3$lambda$1(view);
            }
        });
        activityAiWakeUpBinding.clsTouch.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.AIWakeUpActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AIWakeUpActivity.initView$lambda$3$lambda$2(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$3$lambda$0(AIWakeUpActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        AssetsAudioPlayer.INSTANCE.getInstance().stop();
    }
}
