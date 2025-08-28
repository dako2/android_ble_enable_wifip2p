package com.glasssutdio.wear.home.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.utils.KeyboardUtils;
import com.glasssutdio.wear.all.utils.time.Interval;
import com.glasssutdio.wear.all.view.VerificationCodeEditText;
import com.glasssutdio.wear.databinding.ActivityLogoffByCodeBinding;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: LogoffByCodeActivity.kt */
@Metadata(m606d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/LogoffByCodeActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityLogoffByCodeBinding;", "code", "", "interval", "Lcom/glasssutdio/wear/all/utils/time/Interval;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class LogoffByCodeActivity extends BaseSettingActivity {
    private ActivityLogoffByCodeBinding binding;
    private String code;
    private Interval interval;

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityLogoffByCodeBinding activityLogoffByCodeBindingInflate = ActivityLogoffByCodeBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityLogoffByCodeBindingInflate, "inflate(...)");
        this.binding = activityLogoffByCodeBindingInflate;
        if (activityLogoffByCodeBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLogoffByCodeBindingInflate = null;
        }
        setContentView(activityLogoffByCodeBindingInflate.getRoot());
        initView();
        ActivityLogoffByCodeBinding activityLogoffByCodeBinding = this.binding;
        if (activityLogoffByCodeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLogoffByCodeBinding = null;
        }
        activityLogoffByCodeBinding.tvConfirm.postDelayed(new Runnable() { // from class: com.glasssutdio.wear.home.activity.LogoffByCodeActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                LogoffByCodeActivity.onCreate$lambda$0(this.f$0);
            }
        }, 500L);
        Interval intervalLife$default = Interval.life$default(new Interval(1L, 1L, TimeUnit.SECONDS, 60L, 0L, 16, null), this, (Lifecycle.Event) null, 2, (Object) null);
        this.interval = intervalLife$default;
        if (intervalLife$default != null) {
            intervalLife$default.subscribe(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.activity.LogoffByCodeActivity$onCreate$2$1
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Interval interval, Long l) {
                    invoke(interval, l.longValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Interval subscribe, long j) {
                    Intrinsics.checkNotNullParameter(subscribe, "$this$subscribe");
                    ActivityLogoffByCodeBinding activityLogoffByCodeBinding2 = this.this$0.binding;
                    ActivityLogoffByCodeBinding activityLogoffByCodeBinding3 = null;
                    if (activityLogoffByCodeBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityLogoffByCodeBinding2 = null;
                    }
                    TextView textView = activityLogoffByCodeBinding2.tvCode;
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string = this.this$0.getString(C0775R.string.h_glass_251);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    String str = String.format(string, Arrays.copyOf(new Object[]{String.valueOf(j)}, 1));
                    Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                    textView.setText(str);
                    ActivityLogoffByCodeBinding activityLogoffByCodeBinding4 = this.this$0.binding;
                    if (activityLogoffByCodeBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityLogoffByCodeBinding3 = activityLogoffByCodeBinding4;
                    }
                    activityLogoffByCodeBinding3.tvCode.setEnabled(false);
                }
            }).finish(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.activity.LogoffByCodeActivity$onCreate$2$2
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Interval interval, Long l) {
                    invoke(interval, l.longValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Interval finish, long j) {
                    Intrinsics.checkNotNullParameter(finish, "$this$finish");
                    ActivityLogoffByCodeBinding activityLogoffByCodeBinding2 = this.this$0.binding;
                    ActivityLogoffByCodeBinding activityLogoffByCodeBinding3 = null;
                    if (activityLogoffByCodeBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityLogoffByCodeBinding2 = null;
                    }
                    activityLogoffByCodeBinding2.tvCode.setText(this.this$0.getString(C0775R.string.h_glass_252));
                    ActivityLogoffByCodeBinding activityLogoffByCodeBinding4 = this.this$0.binding;
                    if (activityLogoffByCodeBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityLogoffByCodeBinding3 = activityLogoffByCodeBinding4;
                    }
                    activityLogoffByCodeBinding3.tvCode.setEnabled(true);
                }
            });
        }
        Interval interval = this.interval;
        if (interval != null) {
            interval.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(LogoffByCodeActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        KeyboardUtils keyboardUtils = KeyboardUtils.INSTANCE;
        ActivityLogoffByCodeBinding activityLogoffByCodeBinding = this$0.binding;
        if (activityLogoffByCodeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLogoffByCodeBinding = null;
        }
        VerificationCodeEditText etCode = activityLogoffByCodeBinding.etCode;
        Intrinsics.checkNotNullExpressionValue(etCode, "etCode");
        keyboardUtils.showKeyboard(etCode, this$0);
    }

    private final void initView() {
        final ActivityLogoffByCodeBinding activityLogoffByCodeBinding = this.binding;
        if (activityLogoffByCodeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLogoffByCodeBinding = null;
        }
        activityLogoffByCodeBinding.title.tvTitle.setText(getString(C0775R.string.h_glass_264));
        activityLogoffByCodeBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.LogoffByCodeActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LogoffByCodeActivity.initView$lambda$4$lambda$2(this.f$0, view);
            }
        });
        activityLogoffByCodeBinding.tvConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.LogoffByCodeActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LogoffByCodeActivity.initView$lambda$4$lambda$3(view);
            }
        });
        activityLogoffByCodeBinding.etCode.setOnInputTextListener(new VerificationCodeEditText.OnInputTextListener() { // from class: com.glasssutdio.wear.home.activity.LogoffByCodeActivity$initView$1$3
            @Override // com.glasssutdio.wear.all.view.VerificationCodeEditText.OnInputTextListener
            public void onInputTextComplete(CharSequence text) {
                Intrinsics.checkNotNullParameter(text, "text");
                this.this$0.code = text.toString();
                activityLogoffByCodeBinding.tvConfirm.setEnabled(true);
                activityLogoffByCodeBinding.tvConfirm.setBackgroundResource(C0775R.mipmap.btn_bg_screen);
                activityLogoffByCodeBinding.tvConfirm.setTextColor(ContextCompat.getColor(this.this$0, C0775R.color.g_black));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$4$lambda$2(LogoffByCodeActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$4$lambda$3(View view) {
        GlobalKt.showToast$default("账号注销", 0, 1, null);
    }
}
