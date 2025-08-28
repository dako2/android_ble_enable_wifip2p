package com.glasssutdio.wear.home.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import androidx.core.content.ContextCompat;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.EditTextViewExtKt;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.databinding.ActivitySetPwdConfirmBinding;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SetPasswordConfirmActivity.kt */
@Metadata(m606d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\f\u001a\u00020\nH\u0002J\u0012\u0010\r\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/SetPasswordConfirmActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivitySetPwdConfirmBinding;", "code", "", "isPasswordVisible", "", "checkConfirm", "", "confirm", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class SetPasswordConfirmActivity extends BaseSettingActivity {
    private ActivitySetPwdConfirmBinding binding;
    private String code;
    private boolean isPasswordVisible;

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivitySetPwdConfirmBinding activitySetPwdConfirmBindingInflate = ActivitySetPwdConfirmBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activitySetPwdConfirmBindingInflate, "inflate(...)");
        this.binding = activitySetPwdConfirmBindingInflate;
        if (activitySetPwdConfirmBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySetPwdConfirmBindingInflate = null;
        }
        setContentView(activitySetPwdConfirmBindingInflate.getRoot());
        initView();
    }

    private final void initView() {
        final ActivitySetPwdConfirmBinding activitySetPwdConfirmBinding = this.binding;
        if (activitySetPwdConfirmBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySetPwdConfirmBinding = null;
        }
        activitySetPwdConfirmBinding.title.tvTitle.setText(getString(C0775R.string.h_glass_248));
        activitySetPwdConfirmBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.SetPasswordConfirmActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetPasswordConfirmActivity.initView$lambda$5$lambda$0(this.f$0, view);
            }
        });
        activitySetPwdConfirmBinding.tvConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.SetPasswordConfirmActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetPasswordConfirmActivity.initView$lambda$5$lambda$1(this.f$0, view);
            }
        });
        activitySetPwdConfirmBinding.ivSeePwd.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.SetPasswordConfirmActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetPasswordConfirmActivity.initView$lambda$5$lambda$2(this.f$0, activitySetPwdConfirmBinding, view);
            }
        });
        EditText etPwd = activitySetPwdConfirmBinding.etPwd;
        Intrinsics.checkNotNullExpressionValue(etPwd, "etPwd");
        etPwd.addTextChangedListener(new TextWatcher() { // from class: com.glasssutdio.wear.home.activity.SetPasswordConfirmActivity$initView$lambda$5$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                this.this$0.checkConfirm();
            }
        });
        EditText etConfirmPwd = activitySetPwdConfirmBinding.etConfirmPwd;
        Intrinsics.checkNotNullExpressionValue(etConfirmPwd, "etConfirmPwd");
        etConfirmPwd.addTextChangedListener(new TextWatcher() { // from class: com.glasssutdio.wear.home.activity.SetPasswordConfirmActivity$initView$lambda$5$$inlined$doAfterTextChanged$2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                this.this$0.checkConfirm();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$5$lambda$0(SetPasswordConfirmActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$5$lambda$1(SetPasswordConfirmActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.confirm();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$5$lambda$2(SetPasswordConfirmActivity this$0, ActivitySetPwdConfirmBinding this_run, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        if (this$0.isPasswordVisible) {
            this_run.etPwd.setInputType(129);
            this_run.ivSeePwd.setImageResource(C0775R.mipmap.ic_pwd_close);
            this$0.isPasswordVisible = false;
        } else {
            this_run.etPwd.setInputType(145);
            this_run.ivSeePwd.setImageResource(C0775R.mipmap.ic_pwd_open);
            this$0.isPasswordVisible = true;
        }
        this_run.etPwd.setSelection(this_run.etPwd.getText().length());
    }

    private final void confirm() {
        ActivitySetPwdConfirmBinding activitySetPwdConfirmBinding = this.binding;
        if (activitySetPwdConfirmBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySetPwdConfirmBinding = null;
        }
        String string = activitySetPwdConfirmBinding.etPwd.getText().toString();
        if (string.length() == 0) {
            String string2 = getString(C0775R.string.h_glass_254);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            GlobalKt.showToast$default(string2, 0, 1, null);
            return;
        }
        ActivitySetPwdConfirmBinding activitySetPwdConfirmBinding2 = this.binding;
        if (activitySetPwdConfirmBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySetPwdConfirmBinding2 = null;
        }
        String string3 = activitySetPwdConfirmBinding2.etConfirmPwd.getText().toString();
        if (string3.length() == 0) {
            String string4 = getString(C0775R.string.h_glass_256);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
            GlobalKt.showToast$default(string4, 0, 1, null);
        } else {
            if (Intrinsics.areEqual(string, string3)) {
                return;
            }
            String string5 = getString(C0775R.string.h_glass_258);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
            GlobalKt.showToast$default(string5, 0, 1, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkConfirm() {
        ActivitySetPwdConfirmBinding activitySetPwdConfirmBinding = this.binding;
        ActivitySetPwdConfirmBinding activitySetPwdConfirmBinding2 = null;
        if (activitySetPwdConfirmBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySetPwdConfirmBinding = null;
        }
        EditText etPwd = activitySetPwdConfirmBinding.etPwd;
        Intrinsics.checkNotNullExpressionValue(etPwd, "etPwd");
        if (!EditTextViewExtKt.isEmpty(etPwd)) {
            ActivitySetPwdConfirmBinding activitySetPwdConfirmBinding3 = this.binding;
            if (activitySetPwdConfirmBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySetPwdConfirmBinding3 = null;
            }
            EditText etConfirmPwd = activitySetPwdConfirmBinding3.etConfirmPwd;
            Intrinsics.checkNotNullExpressionValue(etConfirmPwd, "etConfirmPwd");
            if (!EditTextViewExtKt.isEmpty(etConfirmPwd)) {
                ActivitySetPwdConfirmBinding activitySetPwdConfirmBinding4 = this.binding;
                if (activitySetPwdConfirmBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySetPwdConfirmBinding4 = null;
                }
                activitySetPwdConfirmBinding4.tvConfirm.setEnabled(true);
                ActivitySetPwdConfirmBinding activitySetPwdConfirmBinding5 = this.binding;
                if (activitySetPwdConfirmBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySetPwdConfirmBinding5 = null;
                }
                activitySetPwdConfirmBinding5.tvConfirm.setBackgroundResource(C0775R.mipmap.btn_bg_screen);
                ActivitySetPwdConfirmBinding activitySetPwdConfirmBinding6 = this.binding;
                if (activitySetPwdConfirmBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activitySetPwdConfirmBinding2 = activitySetPwdConfirmBinding6;
                }
                activitySetPwdConfirmBinding2.tvConfirm.setTextColor(ContextCompat.getColor(this, C0775R.color.g_black));
                return;
            }
        }
        ActivitySetPwdConfirmBinding activitySetPwdConfirmBinding7 = this.binding;
        if (activitySetPwdConfirmBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySetPwdConfirmBinding7 = null;
        }
        activitySetPwdConfirmBinding7.tvConfirm.setEnabled(false);
        ActivitySetPwdConfirmBinding activitySetPwdConfirmBinding8 = this.binding;
        if (activitySetPwdConfirmBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySetPwdConfirmBinding8 = null;
        }
        activitySetPwdConfirmBinding8.tvConfirm.setBackgroundResource(C0775R.drawable.bg_gray_6b_44_shape);
        ActivitySetPwdConfirmBinding activitySetPwdConfirmBinding9 = this.binding;
        if (activitySetPwdConfirmBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySetPwdConfirmBinding2 = activitySetPwdConfirmBinding9;
        }
        activitySetPwdConfirmBinding2.tvConfirm.setTextColor(ContextCompat.getColor(this, C0775R.color.g_white));
    }
}
