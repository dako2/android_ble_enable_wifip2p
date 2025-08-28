package com.glasssutdio.wear.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.utils.KeyboardUtils;
import com.glasssutdio.wear.all.utils.time.Interval;
import com.glasssutdio.wear.all.view.VerificationCodeEditText;
import com.glasssutdio.wear.databinding.ActivitySetPasswordBinding;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: SetPasswordActivity.kt */
@Metadata(m606d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/SetPasswordActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivitySetPasswordBinding;", "code", "", "interval", "Lcom/glasssutdio/wear/all/utils/time/Interval;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class SetPasswordActivity extends BaseSettingActivity {
    private ActivitySetPasswordBinding binding;
    private String code;
    private Interval interval;

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivitySetPasswordBinding activitySetPasswordBindingInflate = ActivitySetPasswordBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activitySetPasswordBindingInflate, "inflate(...)");
        this.binding = activitySetPasswordBindingInflate;
        if (activitySetPasswordBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySetPasswordBindingInflate = null;
        }
        setContentView(activitySetPasswordBindingInflate.getRoot());
        initView();
        ActivitySetPasswordBinding activitySetPasswordBinding = this.binding;
        if (activitySetPasswordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySetPasswordBinding = null;
        }
        activitySetPasswordBinding.tvHint.postDelayed(new Runnable() { // from class: com.glasssutdio.wear.home.activity.SetPasswordActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SetPasswordActivity.onCreate$lambda$0(this.f$0);
            }
        }, 500L);
        Interval intervalLife$default = Interval.life$default(new Interval(1L, 1L, TimeUnit.SECONDS, 60L, 0L, 16, null), this, (Lifecycle.Event) null, 2, (Object) null);
        this.interval = intervalLife$default;
        if (intervalLife$default != null) {
            intervalLife$default.subscribe(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.activity.SetPasswordActivity$onCreate$2$1
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
                    ActivitySetPasswordBinding activitySetPasswordBinding2 = this.this$0.binding;
                    ActivitySetPasswordBinding activitySetPasswordBinding3 = null;
                    if (activitySetPasswordBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySetPasswordBinding2 = null;
                    }
                    TextView textView = activitySetPasswordBinding2.tvCode;
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string = this.this$0.getString(C0775R.string.h_glass_251);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    String str = String.format(string, Arrays.copyOf(new Object[]{String.valueOf(j)}, 1));
                    Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                    textView.setText(str);
                    ActivitySetPasswordBinding activitySetPasswordBinding4 = this.this$0.binding;
                    if (activitySetPasswordBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activitySetPasswordBinding3 = activitySetPasswordBinding4;
                    }
                    activitySetPasswordBinding3.tvCode.setEnabled(false);
                }
            }).finish(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.activity.SetPasswordActivity$onCreate$2$2
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
                    ActivitySetPasswordBinding activitySetPasswordBinding2 = this.this$0.binding;
                    ActivitySetPasswordBinding activitySetPasswordBinding3 = null;
                    if (activitySetPasswordBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySetPasswordBinding2 = null;
                    }
                    activitySetPasswordBinding2.tvCode.setText(this.this$0.getString(C0775R.string.h_glass_252));
                    ActivitySetPasswordBinding activitySetPasswordBinding4 = this.this$0.binding;
                    if (activitySetPasswordBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activitySetPasswordBinding3 = activitySetPasswordBinding4;
                    }
                    activitySetPasswordBinding3.tvCode.setEnabled(true);
                }
            });
        }
        Interval interval = this.interval;
        if (interval != null) {
            interval.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(SetPasswordActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        KeyboardUtils keyboardUtils = KeyboardUtils.INSTANCE;
        ActivitySetPasswordBinding activitySetPasswordBinding = this$0.binding;
        if (activitySetPasswordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySetPasswordBinding = null;
        }
        VerificationCodeEditText etCode = activitySetPasswordBinding.etCode;
        Intrinsics.checkNotNullExpressionValue(etCode, "etCode");
        keyboardUtils.showKeyboard(etCode, this$0);
    }

    private final void initView() {
        final ActivitySetPasswordBinding activitySetPasswordBinding = this.binding;
        if (activitySetPasswordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySetPasswordBinding = null;
        }
        activitySetPasswordBinding.title.tvTitle.setText(getString(C0775R.string.h_glass_248));
        activitySetPasswordBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.SetPasswordActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetPasswordActivity.initView$lambda$4$lambda$2(this.f$0, view);
            }
        });
        activitySetPasswordBinding.tvConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.SetPasswordActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetPasswordActivity.initView$lambda$4$lambda$3(this.f$0, view);
            }
        });
        activitySetPasswordBinding.etCode.setOnInputTextListener(new VerificationCodeEditText.OnInputTextListener() { // from class: com.glasssutdio.wear.home.activity.SetPasswordActivity$initView$1$3
            @Override // com.glasssutdio.wear.all.view.VerificationCodeEditText.OnInputTextListener
            public void onInputTextComplete(CharSequence text) {
                Intrinsics.checkNotNullParameter(text, "text");
                this.this$0.code = text.toString();
                activitySetPasswordBinding.tvConfirm.setEnabled(true);
                activitySetPasswordBinding.tvConfirm.setBackgroundResource(C0775R.mipmap.btn_bg_screen);
                activitySetPasswordBinding.tvConfirm.setTextColor(ContextCompat.getColor(this.this$0, C0775R.color.g_black));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$4$lambda$2(SetPasswordActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$4$lambda$3(SetPasswordActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SetPasswordActivity setPasswordActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(setPasswordActivity, (Class<?>) SetPasswordConfirmActivity.class);
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
        setPasswordActivity.startActivity(intent);
    }
}
