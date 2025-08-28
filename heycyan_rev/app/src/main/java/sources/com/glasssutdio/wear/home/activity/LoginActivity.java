package com.glasssutdio.wear.home.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.MainActivity;
import com.glasssutdio.wear.all.Constant;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.StringExtKt;
import com.glasssutdio.wear.all.TextViewExtKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.dialog.PrivacyDialog;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.KeyboardUtils;
import com.glasssutdio.wear.all.utils.bar.NavigationBarUtil;
import com.glasssutdio.wear.all.utils.time.Interval;
import com.glasssutdio.wear.all.view.VerificationCodeEditText;
import com.glasssutdio.wear.api.RetCodeValue;
import com.glasssutdio.wear.bus.RefreshUserEvent;
import com.glasssutdio.wear.databinding.ActivityLoginBinding;
import com.glasssutdio.wear.home.bean.LoginResModel;
import com.glasssutdio.wear.home.bean.RequestFailModel;
import com.glasssutdio.wear.home.viewmodel.LoginActivityVM;
import com.glasssutdio.wear.manager.BaseFullActivity;
import com.glasssutdio.wear.setting.WebActivity;
import com.oudmon.qc_utils.date.LanguageUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: LoginActivity.kt */
@Metadata(m606d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\bH\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0002J\b\u0010\u0017\u001a\u00020\u0014H\u0002J\b\u0010\u0018\u001a\u00020\u0014H\u0002J\b\u0010\u0019\u001a\u00020\u0014H\u0016J\b\u0010\u001a\u001a\u00020\u0014H\u0016J\u0012\u0010\u001b\u001a\u00020\u00142\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\f\u0010\u001e\u001a\u00020\u0014*\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001f"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/LoginActivity;", "Lcom/glasssutdio/wear/manager/BaseFullActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityLoginBinding;", "interval", "Lcom/glasssutdio/wear/all/utils/time/Interval;", "isAgreement", "", "isEmailLogin", "isPasswordLogin", "isPasswordVisible", "isShowCodeView", "mViewModel", "Lcom/glasssutdio/wear/home/viewmodel/LoginActivityVM;", "getMViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/LoginActivityVM;", "mViewModel$delegate", "Lkotlin/Lazy;", "changeUiByLoginType", "", "isPassword", "checkConfirm", "customDialog", "initAgreementView", "initView", "observer", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "login", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class LoginActivity extends BaseFullActivity {
    private ActivityLoginBinding binding;
    private Interval interval;
    private boolean isAgreement;
    private boolean isEmailLogin;
    private boolean isPasswordLogin;
    private boolean isPasswordVisible;
    private boolean isShowCodeView;

    /* renamed from: mViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mViewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public LoginActivity() {
        final LoginActivity loginActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewModel = LazyKt.lazy(new Function0<LoginActivityVM>() { // from class: com.glasssutdio.wear.home.activity.LoginActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.LoginActivityVM] */
            @Override // kotlin.jvm.functions.Function0
            public final LoginActivityVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(loginActivity, Reflection.getOrCreateKotlinClass(LoginActivityVM.class), qualifier, objArr);
            }
        });
    }

    private final LoginActivityVM getMViewModel() {
        return (LoginActivityVM) this.mViewModel.getValue();
    }

    @Override // com.glasssutdio.wear.manager.BaseFullActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        ActivityLoginBinding activityLoginBindingInflate = ActivityLoginBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityLoginBindingInflate, "inflate(...)");
        this.binding = activityLoginBindingInflate;
        ActivityLoginBinding activityLoginBinding = null;
        if (activityLoginBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLoginBindingInflate = null;
        }
        setContentView(activityLoginBindingInflate.getRoot());
        super.onCreate(savedInstanceState);
        ActivityLoginBinding activityLoginBinding2 = this.binding;
        if (activityLoginBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityLoginBinding = activityLoginBinding2;
        }
        LinearLayout llBottom = activityLoginBinding.llBottom;
        Intrinsics.checkNotNullExpressionValue(llBottom, "llBottom");
        LoginActivity loginActivity = this;
        ViewKt.setMargin$default(llBottom, null, null, null, Integer.valueOf(NavigationBarUtil.INSTANCE.getNavigationBarHeight(loginActivity) + GlobalKt.getDp((Number) 24)), 7, null);
        NavigationBarUtil.INSTANCE.setNavigationBarColor(loginActivity, C0775R.color.transparent);
    }

    @Override // com.glasssutdio.wear.manager.BaseFullActivity
    public void observer() {
        LoginActivityVM mViewModel = getMViewModel();
        LoginActivity loginActivity = this;
        mViewModel.getLoginResLD().observe(loginActivity, new LoginActivity$sam$androidx_lifecycle_Observer$0(new Function1<LoginResModel, Unit>() { // from class: com.glasssutdio.wear.home.activity.LoginActivity$observer$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LoginResModel loginResModel) {
                invoke2(loginResModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LoginResModel loginResModel) {
                this.this$0.dismissLoadingDialog();
                UserConfig.INSTANCE.getInstance().setUserToken(loginResModel.getToken());
                UserConfig companion = UserConfig.INSTANCE.getInstance();
                Long longOrNull = StringsKt.toLongOrNull(loginResModel.getUid());
                companion.setUid(longOrNull != null ? longOrNull.longValue() : 51888L);
                LoginActivity loginActivity2 = this.this$0;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(loginActivity2, (Class<?>) MainActivity.class);
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
                loginActivity2.startActivity(intent);
                EventBus.getDefault().post(new RefreshUserEvent());
            }
        }));
        mViewModel.getFailLD().observe(loginActivity, new LoginActivity$sam$androidx_lifecycle_Observer$0(new Function1<RequestFailModel, Unit>() { // from class: com.glasssutdio.wear.home.activity.LoginActivity$observer$1$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(RequestFailModel requestFailModel) {
                invoke2(requestFailModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RequestFailModel requestFailModel) {
                String msg;
                this.this$0.dismissLoadingDialog();
                int code = requestFailModel.getCode();
                if (code == 10002) {
                    String string = this.this$0.getString(C0775R.string.login_glass_8);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    GlobalKt.showToast$default(string, 0, 1, null);
                    return;
                }
                if (code != 50012) {
                    switch (code) {
                        case RetCodeValue.ServerError_50003 /* 50003 */:
                            String string2 = this.this$0.getString(C0775R.string.login_glass_4);
                            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                            GlobalKt.showToast$default(string2, 0, 1, null);
                            break;
                        case RetCodeValue.ServerError_50004 /* 50004 */:
                            String string3 = this.this$0.getString(C0775R.string.login_glass_6);
                            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                            GlobalKt.showToast$default(string3, 0, 1, null);
                            break;
                        case RetCodeValue.ServerError_50005 /* 50005 */:
                            String string4 = this.this$0.getString(C0775R.string.login_glass_7);
                            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                            GlobalKt.showToast$default(string4, 0, 1, null);
                            break;
                        default:
                            if (requestFailModel.getCode() != -1 && (msg = requestFailModel.getMsg()) != null) {
                                GlobalKt.showToast$default(msg, 0, 1, null);
                                break;
                            }
                            break;
                    }
                    return;
                }
                String string5 = this.this$0.getString(C0775R.string.login_glass_5);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                GlobalKt.showToast$default(string5, 0, 1, null);
            }
        }));
    }

    @Override // com.glasssutdio.wear.manager.BaseFullActivity
    public void initView() throws Resources.NotFoundException {
        final ActivityLoginBinding activityLoginBinding = this.binding;
        if (activityLoginBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLoginBinding = null;
        }
        TextView tvSkip = activityLoginBinding.tvSkip;
        Intrinsics.checkNotNullExpressionValue(tvSkip, "tvSkip");
        ViewKt.statusMargin$default(tvSkip, false, 0, 3, null);
        activityLoginBinding.tvSkip.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.LoginActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginActivity.initView$lambda$8$lambda$1(this.f$0, view);
            }
        });
        TextView tvSwitchLogin = activityLoginBinding.tvSwitchLogin;
        Intrinsics.checkNotNullExpressionValue(tvSwitchLogin, "tvSwitchLogin");
        TextViewExtKt.setupMarquee(tvSwitchLogin);
        TextView tvForgetPwd = activityLoginBinding.tvForgetPwd;
        Intrinsics.checkNotNullExpressionValue(tvForgetPwd, "tvForgetPwd");
        TextViewExtKt.setupMarquee(tvForgetPwd);
        changeUiByLoginType(this.isPasswordLogin);
        activityLoginBinding.tvSwitchLogin.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.LoginActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginActivity.initView$lambda$8$lambda$2(this.f$0, view);
            }
        });
        activityLoginBinding.tvForgetPwd.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.LoginActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginActivity.initView$lambda$8$lambda$3(this.f$0, view);
            }
        });
        activityLoginBinding.tvLogin.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.LoginActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginActivity.initView$lambda$8$lambda$4(this.f$0, activityLoginBinding, view);
            }
        });
        EditText etAccount = activityLoginBinding.etAccount;
        Intrinsics.checkNotNullExpressionValue(etAccount, "etAccount");
        etAccount.addTextChangedListener(new TextWatcher() { // from class: com.glasssutdio.wear.home.activity.LoginActivity$initView$lambda$8$$inlined$doAfterTextChanged$1
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
        EditText etPwd = activityLoginBinding.etPwd;
        Intrinsics.checkNotNullExpressionValue(etPwd, "etPwd");
        etPwd.addTextChangedListener(new TextWatcher() { // from class: com.glasssutdio.wear.home.activity.LoginActivity$initView$lambda$8$$inlined$doAfterTextChanged$2
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
        activityLoginBinding.ivSeePwd.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.LoginActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginActivity.initView$lambda$8$lambda$7(this.f$0, activityLoginBinding, view);
            }
        });
        initAgreementView();
        if (LanguageUtil.isChinaReal()) {
            customDialog();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$8$lambda$1(LoginActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!this$0.isAgreement) {
            String string = this$0.getString(C0775R.string.h_glass_308);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        LoginActivity loginActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(loginActivity, (Class<?>) MainActivity.class);
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
        loginActivity.startActivity(intent);
        UserConfig.INSTANCE.getInstance().setNeedShowLogin(false);
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$8$lambda$2(LoginActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (LanguageUtil.isChina()) {
            this$0.changeUiByLoginType(!this$0.isPasswordLogin);
            return;
        }
        if (!this$0.isAgreement) {
            String string = this$0.getString(C0775R.string.h_glass_308);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        LoginActivity loginActivity = this$0;
        Pair pair = new Pair(Constant.PAGE_REGISTER_KEY, 3);
        ArrayList<Pair> arrayList = new ArrayList();
        arrayList.add(pair);
        Intent intent = new Intent(loginActivity, (Class<?>) ForgetPasswordActivity.class);
        for (Pair pair2 : arrayList) {
            if (pair2 != null) {
                String str = (String) pair2.getFirst();
                Object second = pair2.getSecond();
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
        loginActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$8$lambda$3(LoginActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (LanguageUtil.isChina()) {
            LoginActivity loginActivity = this$0;
            Pair pair = new Pair(Constant.PAGE_REGISTER_KEY, 1);
            ArrayList<Pair> arrayList = new ArrayList();
            arrayList.add(pair);
            Intent intent = new Intent(loginActivity, (Class<?>) ForgetPasswordActivity.class);
            for (Pair pair2 : arrayList) {
                if (pair2 != null) {
                    String str = (String) pair2.getFirst();
                    Object second = pair2.getSecond();
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
            loginActivity.startActivity(intent);
            return;
        }
        LoginActivity loginActivity2 = this$0;
        Pair pair3 = new Pair(Constant.PAGE_REGISTER_KEY, 2);
        ArrayList<Pair> arrayList2 = new ArrayList();
        arrayList2.add(pair3);
        Intent intent2 = new Intent(loginActivity2, (Class<?>) ForgetPasswordActivity.class);
        for (Pair pair4 : arrayList2) {
            if (pair4 != null) {
                String str2 = (String) pair4.getFirst();
                Object second2 = pair4.getSecond();
                if (second2 instanceof Integer) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).intValue()), "putExtra(...)");
                } else if (second2 instanceof Byte) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).byteValue()), "putExtra(...)");
                } else if (second2 instanceof Character) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Character) second2).charValue()), "putExtra(...)");
                } else if (second2 instanceof Short) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).shortValue()), "putExtra(...)");
                } else if (second2 instanceof Boolean) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Boolean) second2).booleanValue()), "putExtra(...)");
                } else if (second2 instanceof Long) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).longValue()), "putExtra(...)");
                } else if (second2 instanceof Float) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).floatValue()), "putExtra(...)");
                } else if (second2 instanceof Double) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).doubleValue()), "putExtra(...)");
                } else if (second2 instanceof String) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (String) second2), "putExtra(...)");
                } else if (second2 instanceof CharSequence) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (CharSequence) second2), "putExtra(...)");
                } else if (second2 instanceof Parcelable) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(...)");
                } else if (second2 instanceof Object[]) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                } else if (second2 instanceof ArrayList) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                } else if (second2 instanceof Serializable) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                } else if (second2 instanceof boolean[]) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (boolean[]) second2), "putExtra(...)");
                } else if (second2 instanceof byte[]) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (byte[]) second2), "putExtra(...)");
                } else if (second2 instanceof short[]) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (short[]) second2), "putExtra(...)");
                } else if (second2 instanceof char[]) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (char[]) second2), "putExtra(...)");
                } else if (second2 instanceof int[]) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (int[]) second2), "putExtra(...)");
                } else if (second2 instanceof long[]) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (long[]) second2), "putExtra(...)");
                } else if (second2 instanceof float[]) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (float[]) second2), "putExtra(...)");
                } else if (second2 instanceof double[]) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (double[]) second2), "putExtra(...)");
                } else if (second2 instanceof Bundle) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Bundle) second2), "putExtra(...)");
                } else if (second2 instanceof Intent) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(...)");
                } else {
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        }
        loginActivity2.startActivity(intent2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$8$lambda$4(LoginActivity this$0, ActivityLoginBinding this_run, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        this$0.login(this_run);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$8$lambda$7(LoginActivity this$0, ActivityLoginBinding this_run, View view) {
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

    private final void login(final ActivityLoginBinding activityLoginBinding) {
        if (!this.isAgreement) {
            String string = getString(C0775R.string.h_glass_308);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        if (this.isPasswordLogin) {
            String string2 = activityLoginBinding.etAccount.getText().toString();
            if (this.isEmailLogin) {
                if (string2.length() == 0) {
                    String string3 = getString(C0775R.string.h_glass_288_1);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                    GlobalKt.showToast$default(string3, 0, 1, null);
                    return;
                } else if (!StringExtKt.isEmail(string2)) {
                    String string4 = getString(C0775R.string.h_glass_298);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                    GlobalKt.showToast$default(string4, 0, 1, null);
                    return;
                }
            } else if (string2.length() == 0) {
                String string5 = getString(C0775R.string.h_glass_288_3);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                GlobalKt.showToast$default(string5, 0, 1, null);
                return;
            } else if (string2.length() != 11) {
                String string6 = getString(C0775R.string.h_glass_298_1);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                GlobalKt.showToast$default(string6, 0, 1, null);
                return;
            }
            String string7 = activityLoginBinding.etPwd.getText().toString();
            if (string7.length() == 0) {
                String string8 = getString(C0775R.string.h_glass_296);
                Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
                GlobalKt.showToast$default(string8, 0, 1, null);
                return;
            } else if (StringExtKt.verifyPwdNotValid(string7)) {
                String string9 = getString(C0775R.string.h_glass_257_1);
                Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
                GlobalKt.showToast$default(string9, 0, 1, null);
                return;
            } else {
                showLoadingDialog();
                getMViewModel().login(string2, string7, this.isEmailLogin);
                return;
            }
        }
        if (this.isShowCodeView) {
            if (String.valueOf(activityLoginBinding.codeView.etCode.getText()).length() == 0) {
                String string10 = getString(C0775R.string.h_glass_310);
                Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
                GlobalKt.showToast$default(string10, 0, 1, null);
                return;
            }
            activityLoginBinding.etAccount.getText().toString();
            return;
        }
        if (activityLoginBinding.etAccount.getText().toString().length() == 0) {
            String string11 = getString(C0775R.string.h_glass_288_3);
            Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
            GlobalKt.showToast$default(string11, 0, 1, null);
            return;
        }
        activityLoginBinding.tvLogin.setText(getString(C0775R.string.login_glass_2));
        this.isShowCodeView = true;
        ViewKt.visible(activityLoginBinding.flCodeView);
        ViewKt.invisible(activityLoginBinding.llAccount);
        activityLoginBinding.tvLogin.postDelayed(new Runnable() { // from class: com.glasssutdio.wear.home.activity.LoginActivity$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                LoginActivity.login$lambda$9(activityLoginBinding, this);
            }
        }, 500L);
        Interval intervalLife$default = Interval.life$default(new Interval(1L, 1L, TimeUnit.SECONDS, 60L, 0L, 16, null), this, (Lifecycle.Event) null, 2, (Object) null);
        this.interval = intervalLife$default;
        if (intervalLife$default != null) {
            intervalLife$default.subscribe(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.activity.LoginActivity$login$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    TextView textView = activityLoginBinding.codeView.tvCode;
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string12 = this.getString(C0775R.string.h_glass_251);
                    Intrinsics.checkNotNullExpressionValue(string12, "getString(...)");
                    String str = String.format(string12, Arrays.copyOf(new Object[]{String.valueOf(j)}, 1));
                    Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                    textView.setText(str);
                    activityLoginBinding.codeView.tvCode.setEnabled(false);
                }
            }).finish(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.activity.LoginActivity$login$2$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    activityLoginBinding.codeView.tvCode.setText(this.getString(C0775R.string.h_glass_252));
                    activityLoginBinding.codeView.tvCode.setEnabled(true);
                }
            });
        }
        Interval interval = this.interval;
        if (interval != null) {
            interval.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void login$lambda$9(ActivityLoginBinding this_login, LoginActivity this$0) {
        Intrinsics.checkNotNullParameter(this_login, "$this_login");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        KeyboardUtils keyboardUtils = KeyboardUtils.INSTANCE;
        VerificationCodeEditText etCode = this_login.codeView.etCode;
        Intrinsics.checkNotNullExpressionValue(etCode, "etCode");
        keyboardUtils.showKeyboard(etCode, this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkConfirm() {
        ActivityLoginBinding activityLoginBinding = this.binding;
        if (activityLoginBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLoginBinding = null;
        }
        String string = activityLoginBinding.etAccount.getText().toString();
        String string2 = activityLoginBinding.etPwd.getText().toString();
        TextView textView = activityLoginBinding.tvLogin;
        boolean z = true;
        if (!this.isPasswordLogin ? string.length() <= 0 : string.length() <= 0 || string2.length() <= 0) {
            z = false;
        }
        textView.setEnabled(z);
    }

    private final void changeUiByLoginType(boolean isPassword) {
        this.isEmailLogin = !LanguageUtil.isChina();
        ActivityLoginBinding activityLoginBinding = this.binding;
        ActivityLoginBinding activityLoginBinding2 = null;
        if (activityLoginBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLoginBinding = null;
        }
        ViewKt.visible(activityLoginBinding.llAccount);
        ActivityLoginBinding activityLoginBinding3 = this.binding;
        if (activityLoginBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLoginBinding3 = null;
        }
        if (LanguageUtil.isChina()) {
            this.isPasswordLogin = isPassword;
            this.isShowCodeView = false;
            Interval interval = this.interval;
            if (interval != null) {
                interval.cancel();
            }
            ActivityLoginBinding activityLoginBinding4 = this.binding;
            if (activityLoginBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityLoginBinding2 = activityLoginBinding4;
            }
            ViewKt.gone(activityLoginBinding2.flCodeView);
            activityLoginBinding3.etAccount.setHint(getString(C0775R.string.h_glass_288_3));
            activityLoginBinding3.etAccount.setInputType(3);
            if (this.isPasswordLogin) {
                LinearLayout llAccount = activityLoginBinding3.llAccount;
                Intrinsics.checkNotNullExpressionValue(llAccount, "llAccount");
                ViewKt.setMargin$default(llAccount, null, Integer.valueOf(GlobalKt.getDp((Number) 48)), null, null, 13, null);
                ViewKt.visible(activityLoginBinding3.llPwd);
                ViewKt.visible(activityLoginBinding3.ivDivider);
                ViewKt.visible(activityLoginBinding3.tvForgetPwd);
                activityLoginBinding3.tvLogin.setText(getString(C0775R.string.h_glass_307));
                activityLoginBinding3.tvSwitchLogin.setText(getString(C0775R.string.h_glass_289));
            } else {
                LinearLayout llAccount2 = activityLoginBinding3.llAccount;
                Intrinsics.checkNotNullExpressionValue(llAccount2, "llAccount");
                ViewKt.setMargin$default(llAccount2, null, Integer.valueOf(GlobalKt.getDp((Number) 90)), null, null, 13, null);
                ViewKt.gone(activityLoginBinding3.llPwd);
                ViewKt.gone(activityLoginBinding3.ivDivider);
                ViewKt.gone(activityLoginBinding3.tvForgetPwd);
                activityLoginBinding3.tvLogin.setText(getString(C0775R.string.h_glass_289));
                activityLoginBinding3.tvSwitchLogin.setText(getString(C0775R.string.h_glass_290));
            }
        } else {
            LinearLayout llAccount3 = activityLoginBinding3.llAccount;
            Intrinsics.checkNotNullExpressionValue(llAccount3, "llAccount");
            ViewKt.setMargin$default(llAccount3, null, Integer.valueOf(GlobalKt.getDp((Number) 48)), null, null, 13, null);
            activityLoginBinding3.etAccount.setHint(getString(C0775R.string.h_glass_288_1));
            activityLoginBinding3.etAccount.setInputType(32);
            this.isPasswordLogin = true;
            ViewKt.visible(activityLoginBinding3.llPwd);
            activityLoginBinding3.tvSwitchLogin.setText(getString(C0775R.string.login_glass_1));
            ViewKt.visible(activityLoginBinding3.ivDivider);
            ViewKt.visible(activityLoginBinding3.tvForgetPwd);
            activityLoginBinding3.tvLogin.setText(getString(C0775R.string.login_glass_2));
        }
        checkConfirm();
    }

    private final void initAgreementView() {
        final ActivityLoginBinding activityLoginBinding = this.binding;
        if (activityLoginBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLoginBinding = null;
        }
        String string = getString(C0775R.string.h_glass_291);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String str = string;
        SpannableString spannableString = new SpannableString(str);
        String string2 = getString(C0775R.string.h_glass_292);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        String string3 = getString(C0775R.string.h_glass_293);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ContextCompat.getColor(this, C0775R.color.color_click));
        spannableString.setSpan(foregroundColorSpan, StringsKt.indexOf$default((CharSequence) str, string2, 0, true, 2, (Object) null), StringsKt.indexOf$default((CharSequence) str, string2, 0, true, 2, (Object) null) + string2.length(), 33);
        spannableString.setSpan(foregroundColorSpan, StringsKt.indexOf$default((CharSequence) str, string3, 0, true, 2, (Object) null), StringsKt.indexOf$default((CharSequence) str, string3, 0, true, 2, (Object) null) + string3.length(), 33);
        spannableString.setSpan(new ClickableSpan() { // from class: com.glasssutdio.wear.home.activity.LoginActivity$initAgreementView$1$clickableSpan1$1
            @Override // android.text.style.ClickableSpan
            public void onClick(View widget) {
                Intrinsics.checkNotNullParameter(widget, "widget");
                String language = Locale.getDefault().getLanguage();
                Intrinsics.checkNotNullExpressionValue(language, "getLanguage(...)");
                if (StringsKt.startsWith$default(language, "zh", false, 2, (Object) null)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", "https://www.qlifesnap.com/ppm/heycyan_agreement.html");
                    LoginActivity loginActivity = this.this$0;
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intent intent = new Intent(loginActivity, (Class<?>) WebActivity.class);
                    intent.setFlags(1);
                    intent.putExtras(bundle);
                    for (Pair pair : arrayList) {
                        if (pair != null) {
                            String str2 = (String) pair.getFirst();
                            Object second = pair.getSecond();
                            if (second instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).intValue()), "putExtra(...)");
                            } else if (second instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).byteValue()), "putExtra(...)");
                            } else if (second instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Character) second).charValue()), "putExtra(...)");
                            } else if (second instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).shortValue()), "putExtra(...)");
                            } else if (second instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Boolean) second).booleanValue()), "putExtra(...)");
                            } else if (second instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).longValue()), "putExtra(...)");
                            } else if (second instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).floatValue()), "putExtra(...)");
                            } else if (second instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).doubleValue()), "putExtra(...)");
                            } else if (second instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (String) second), "putExtra(...)");
                            } else if (second instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (CharSequence) second), "putExtra(...)");
                            } else if (second instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Parcelable) second), "putExtra(...)");
                            } else if (second instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Serializable) second), "putExtra(...)");
                            } else if (second instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Serializable) second), "putExtra(...)");
                            } else if (second instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Serializable) second), "putExtra(...)");
                            } else if (second instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (boolean[]) second), "putExtra(...)");
                            } else if (second instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (byte[]) second), "putExtra(...)");
                            } else if (second instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (short[]) second), "putExtra(...)");
                            } else if (second instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (char[]) second), "putExtra(...)");
                            } else if (second instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (int[]) second), "putExtra(...)");
                            } else if (second instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (long[]) second), "putExtra(...)");
                            } else if (second instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (float[]) second), "putExtra(...)");
                            } else if (second instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (double[]) second), "putExtra(...)");
                            } else if (second instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Bundle) second), "putExtra(...)");
                            } else if (second instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Parcelable) second), "putExtra(...)");
                            } else {
                                Unit unit = Unit.INSTANCE;
                            }
                        }
                    }
                    loginActivity.startActivity(intent);
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString("url", "https://www.qlifesnap.com/ppm/heycyan_agreement.html");
                LoginActivity loginActivity2 = this.this$0;
                ArrayList<Pair> arrayList2 = new ArrayList();
                Intent intent2 = new Intent(loginActivity2, (Class<?>) WebActivity.class);
                intent2.setFlags(1);
                intent2.putExtras(bundle2);
                for (Pair pair2 : arrayList2) {
                    if (pair2 != null) {
                        String str3 = (String) pair2.getFirst();
                        Object second2 = pair2.getSecond();
                        if (second2 instanceof Integer) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, ((Number) second2).intValue()), "putExtra(...)");
                        } else if (second2 instanceof Byte) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, ((Number) second2).byteValue()), "putExtra(...)");
                        } else if (second2 instanceof Character) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, ((Character) second2).charValue()), "putExtra(...)");
                        } else if (second2 instanceof Short) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, ((Number) second2).shortValue()), "putExtra(...)");
                        } else if (second2 instanceof Boolean) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, ((Boolean) second2).booleanValue()), "putExtra(...)");
                        } else if (second2 instanceof Long) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, ((Number) second2).longValue()), "putExtra(...)");
                        } else if (second2 instanceof Float) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, ((Number) second2).floatValue()), "putExtra(...)");
                        } else if (second2 instanceof Double) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, ((Number) second2).doubleValue()), "putExtra(...)");
                        } else if (second2 instanceof String) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (String) second2), "putExtra(...)");
                        } else if (second2 instanceof CharSequence) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (CharSequence) second2), "putExtra(...)");
                        } else if (second2 instanceof Parcelable) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (Parcelable) second2), "putExtra(...)");
                        } else if (second2 instanceof Object[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (Serializable) second2), "putExtra(...)");
                        } else if (second2 instanceof ArrayList) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (Serializable) second2), "putExtra(...)");
                        } else if (second2 instanceof Serializable) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (Serializable) second2), "putExtra(...)");
                        } else if (second2 instanceof boolean[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (boolean[]) second2), "putExtra(...)");
                        } else if (second2 instanceof byte[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (byte[]) second2), "putExtra(...)");
                        } else if (second2 instanceof short[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (short[]) second2), "putExtra(...)");
                        } else if (second2 instanceof char[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (char[]) second2), "putExtra(...)");
                        } else if (second2 instanceof int[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (int[]) second2), "putExtra(...)");
                        } else if (second2 instanceof long[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (long[]) second2), "putExtra(...)");
                        } else if (second2 instanceof float[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (float[]) second2), "putExtra(...)");
                        } else if (second2 instanceof double[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (double[]) second2), "putExtra(...)");
                        } else if (second2 instanceof Bundle) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (Bundle) second2), "putExtra(...)");
                        } else if (second2 instanceof Intent) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (Parcelable) second2), "putExtra(...)");
                        } else {
                            Unit unit2 = Unit.INSTANCE;
                        }
                    }
                }
                loginActivity2.startActivity(intent2);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint ds) {
                Intrinsics.checkNotNullParameter(ds, "ds");
                super.updateDrawState(ds);
                ds.setColor(ContextCompat.getColor(this.this$0, C0775R.color.color_click));
                ds.setUnderlineText(false);
            }
        }, StringsKt.indexOf$default((CharSequence) str, string2, 0, true, 2, (Object) null), StringsKt.indexOf$default((CharSequence) str, string2, 0, true, 2, (Object) null) + string2.length(), 33);
        spannableString.setSpan(new ClickableSpan() { // from class: com.glasssutdio.wear.home.activity.LoginActivity$initAgreementView$1$clickableSpan2$1
            @Override // android.text.style.ClickableSpan
            public void onClick(View widget) {
                Intrinsics.checkNotNullParameter(widget, "widget");
                String language = Locale.getDefault().getLanguage();
                Intrinsics.checkNotNullExpressionValue(language, "getLanguage(...)");
                if (StringsKt.startsWith$default(language, "zh", false, 2, (Object) null)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", "https://www.qlifesnap.com/ppm/heycyan_cn.html");
                    LoginActivity loginActivity = this.this$0;
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intent intent = new Intent(loginActivity, (Class<?>) WebActivity.class);
                    intent.setFlags(1);
                    intent.putExtras(bundle);
                    for (Pair pair : arrayList) {
                        if (pair != null) {
                            String str2 = (String) pair.getFirst();
                            Object second = pair.getSecond();
                            if (second instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).intValue()), "putExtra(...)");
                            } else if (second instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).byteValue()), "putExtra(...)");
                            } else if (second instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Character) second).charValue()), "putExtra(...)");
                            } else if (second instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).shortValue()), "putExtra(...)");
                            } else if (second instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Boolean) second).booleanValue()), "putExtra(...)");
                            } else if (second instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).longValue()), "putExtra(...)");
                            } else if (second instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).floatValue()), "putExtra(...)");
                            } else if (second instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).doubleValue()), "putExtra(...)");
                            } else if (second instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (String) second), "putExtra(...)");
                            } else if (second instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (CharSequence) second), "putExtra(...)");
                            } else if (second instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Parcelable) second), "putExtra(...)");
                            } else if (second instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Serializable) second), "putExtra(...)");
                            } else if (second instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Serializable) second), "putExtra(...)");
                            } else if (second instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Serializable) second), "putExtra(...)");
                            } else if (second instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (boolean[]) second), "putExtra(...)");
                            } else if (second instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (byte[]) second), "putExtra(...)");
                            } else if (second instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (short[]) second), "putExtra(...)");
                            } else if (second instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (char[]) second), "putExtra(...)");
                            } else if (second instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (int[]) second), "putExtra(...)");
                            } else if (second instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (long[]) second), "putExtra(...)");
                            } else if (second instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (float[]) second), "putExtra(...)");
                            } else if (second instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (double[]) second), "putExtra(...)");
                            } else if (second instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Bundle) second), "putExtra(...)");
                            } else if (second instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Parcelable) second), "putExtra(...)");
                            } else {
                                Unit unit = Unit.INSTANCE;
                            }
                        }
                    }
                    loginActivity.startActivity(intent);
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString("url", "https://www.qlifesnap.com/heycyan.html");
                LoginActivity loginActivity2 = this.this$0;
                ArrayList<Pair> arrayList2 = new ArrayList();
                Intent intent2 = new Intent(loginActivity2, (Class<?>) WebActivity.class);
                intent2.setFlags(1);
                intent2.putExtras(bundle2);
                for (Pair pair2 : arrayList2) {
                    if (pair2 != null) {
                        String str3 = (String) pair2.getFirst();
                        Object second2 = pair2.getSecond();
                        if (second2 instanceof Integer) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, ((Number) second2).intValue()), "putExtra(...)");
                        } else if (second2 instanceof Byte) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, ((Number) second2).byteValue()), "putExtra(...)");
                        } else if (second2 instanceof Character) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, ((Character) second2).charValue()), "putExtra(...)");
                        } else if (second2 instanceof Short) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, ((Number) second2).shortValue()), "putExtra(...)");
                        } else if (second2 instanceof Boolean) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, ((Boolean) second2).booleanValue()), "putExtra(...)");
                        } else if (second2 instanceof Long) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, ((Number) second2).longValue()), "putExtra(...)");
                        } else if (second2 instanceof Float) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, ((Number) second2).floatValue()), "putExtra(...)");
                        } else if (second2 instanceof Double) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, ((Number) second2).doubleValue()), "putExtra(...)");
                        } else if (second2 instanceof String) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (String) second2), "putExtra(...)");
                        } else if (second2 instanceof CharSequence) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (CharSequence) second2), "putExtra(...)");
                        } else if (second2 instanceof Parcelable) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (Parcelable) second2), "putExtra(...)");
                        } else if (second2 instanceof Object[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (Serializable) second2), "putExtra(...)");
                        } else if (second2 instanceof ArrayList) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (Serializable) second2), "putExtra(...)");
                        } else if (second2 instanceof Serializable) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (Serializable) second2), "putExtra(...)");
                        } else if (second2 instanceof boolean[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (boolean[]) second2), "putExtra(...)");
                        } else if (second2 instanceof byte[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (byte[]) second2), "putExtra(...)");
                        } else if (second2 instanceof short[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (short[]) second2), "putExtra(...)");
                        } else if (second2 instanceof char[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (char[]) second2), "putExtra(...)");
                        } else if (second2 instanceof int[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (int[]) second2), "putExtra(...)");
                        } else if (second2 instanceof long[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (long[]) second2), "putExtra(...)");
                        } else if (second2 instanceof float[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (float[]) second2), "putExtra(...)");
                        } else if (second2 instanceof double[]) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (double[]) second2), "putExtra(...)");
                        } else if (second2 instanceof Bundle) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (Bundle) second2), "putExtra(...)");
                        } else if (second2 instanceof Intent) {
                            Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str3, (Parcelable) second2), "putExtra(...)");
                        } else {
                            Unit unit2 = Unit.INSTANCE;
                        }
                    }
                }
                loginActivity2.startActivity(intent2);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint ds) {
                Intrinsics.checkNotNullParameter(ds, "ds");
                super.updateDrawState(ds);
                ds.setColor(ContextCompat.getColor(this.this$0, C0775R.color.color_click));
                ds.setUnderlineText(false);
            }
        }, StringsKt.indexOf$default((CharSequence) str, string3, 0, true, 2, (Object) null), StringsKt.indexOf$default((CharSequence) str, string3, 0, true, 2, (Object) null) + string3.length(), 33);
        activityLoginBinding.tvAgreement.setText(spannableString);
        activityLoginBinding.tvAgreement.setMovementMethod(new LinkMovementMethod());
        activityLoginBinding.tvAgreement.setHighlightColor(0);
        activityLoginBinding.ivChecked.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.LoginActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginActivity.initAgreementView$lambda$14$lambda$13(activityLoginBinding, this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initAgreementView$lambda$14$lambda$13(ActivityLoginBinding this_run, LoginActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this_run.ivChecked.setImageResource(this$0.isAgreement ? C0775R.mipmap.ic_checked_off_gray : C0775R.mipmap.ic_checked_on);
        this$0.isAgreement = !this$0.isAgreement;
    }

    private final void customDialog() throws Resources.NotFoundException {
        final PrivacyDialog privacyDialog = new PrivacyDialog(this);
        View viewFindViewById = privacyDialog.findViewById(C0775R.id.tv_privacy_tips);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        TextView textView = (TextView) viewFindViewById;
        View viewFindViewById2 = privacyDialog.findViewById(C0775R.id.btn_exit);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        TextView textView2 = (TextView) viewFindViewById2;
        View viewFindViewById3 = privacyDialog.findViewById(C0775R.id.btn_enter);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        TextView textView3 = (TextView) viewFindViewById3;
        privacyDialog.show();
        String string = getResources().getString(C0775R.string.china_glasses_6);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = getResources().getString(C0775R.string.china_glasses_4);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        String string3 = getResources().getString(C0775R.string.china_glasses_5);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        String str = string;
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, string2, 0, false, 6, (Object) null);
        int iIndexOf$default2 = StringsKt.indexOf$default((CharSequence) str, string3, 0, false, 6, (Object) null);
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(C0775R.color.color_1A7CE7)), iIndexOf$default, string2.length() + iIndexOf$default, 34);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(C0775R.color.color_1A7CE7)), iIndexOf$default2, string3.length() + iIndexOf$default2, 34);
        spannableString.setSpan(new AbsoluteSizeSpan(18, true), iIndexOf$default, string2.length() + iIndexOf$default, 34);
        spannableString.setSpan(new AbsoluteSizeSpan(18, true), iIndexOf$default2, string3.length() + iIndexOf$default2, 34);
        spannableString.setSpan(new ClickableSpan() { // from class: com.glasssutdio.wear.home.activity.LoginActivity$customDialog$clickableSpan1$1
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                Bundle bundle = new Bundle();
                bundle.putString("url", "https://www.qlifesnap.com/ppm/heycyan_agreement.html");
                LoginActivity loginActivity = this.this$0;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(loginActivity, (Class<?>) WebActivity.class);
                intent.setFlags(1);
                intent.putExtras(bundle);
                for (Pair pair : arrayList) {
                    if (pair != null) {
                        String str2 = (String) pair.getFirst();
                        Object second = pair.getSecond();
                        if (second instanceof Integer) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).intValue()), "putExtra(...)");
                        } else if (second instanceof Byte) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).byteValue()), "putExtra(...)");
                        } else if (second instanceof Character) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Character) second).charValue()), "putExtra(...)");
                        } else if (second instanceof Short) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).shortValue()), "putExtra(...)");
                        } else if (second instanceof Boolean) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Boolean) second).booleanValue()), "putExtra(...)");
                        } else if (second instanceof Long) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).longValue()), "putExtra(...)");
                        } else if (second instanceof Float) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).floatValue()), "putExtra(...)");
                        } else if (second instanceof Double) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).doubleValue()), "putExtra(...)");
                        } else if (second instanceof String) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (String) second), "putExtra(...)");
                        } else if (second instanceof CharSequence) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (CharSequence) second), "putExtra(...)");
                        } else if (second instanceof Parcelable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Parcelable) second), "putExtra(...)");
                        } else if (second instanceof Object[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Serializable) second), "putExtra(...)");
                        } else if (second instanceof ArrayList) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Serializable) second), "putExtra(...)");
                        } else if (second instanceof Serializable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Serializable) second), "putExtra(...)");
                        } else if (second instanceof boolean[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (boolean[]) second), "putExtra(...)");
                        } else if (second instanceof byte[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (byte[]) second), "putExtra(...)");
                        } else if (second instanceof short[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (short[]) second), "putExtra(...)");
                        } else if (second instanceof char[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (char[]) second), "putExtra(...)");
                        } else if (second instanceof int[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (int[]) second), "putExtra(...)");
                        } else if (second instanceof long[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (long[]) second), "putExtra(...)");
                        } else if (second instanceof float[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (float[]) second), "putExtra(...)");
                        } else if (second instanceof double[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (double[]) second), "putExtra(...)");
                        } else if (second instanceof Bundle) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Bundle) second), "putExtra(...)");
                        } else if (second instanceof Intent) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Parcelable) second), "putExtra(...)");
                        } else {
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                }
                loginActivity.startActivity(intent);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint ds) {
                Intrinsics.checkNotNullParameter(ds, "ds");
                ds.setUnderlineText(false);
            }
        }, iIndexOf$default, string2.length() + iIndexOf$default, 34);
        spannableString.setSpan(new ClickableSpan() { // from class: com.glasssutdio.wear.home.activity.LoginActivity$customDialog$clickableSpan2$1
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                Bundle bundle = new Bundle();
                bundle.putString("url", "https://www.qlifesnap.com/ppm/heycyan_cn.html");
                LoginActivity loginActivity = this.this$0;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(loginActivity, (Class<?>) WebActivity.class);
                intent.setFlags(1);
                intent.putExtras(bundle);
                for (Pair pair : arrayList) {
                    if (pair != null) {
                        String str2 = (String) pair.getFirst();
                        Object second = pair.getSecond();
                        if (second instanceof Integer) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).intValue()), "putExtra(...)");
                        } else if (second instanceof Byte) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).byteValue()), "putExtra(...)");
                        } else if (second instanceof Character) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Character) second).charValue()), "putExtra(...)");
                        } else if (second instanceof Short) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).shortValue()), "putExtra(...)");
                        } else if (second instanceof Boolean) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Boolean) second).booleanValue()), "putExtra(...)");
                        } else if (second instanceof Long) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).longValue()), "putExtra(...)");
                        } else if (second instanceof Float) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).floatValue()), "putExtra(...)");
                        } else if (second instanceof Double) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).doubleValue()), "putExtra(...)");
                        } else if (second instanceof String) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (String) second), "putExtra(...)");
                        } else if (second instanceof CharSequence) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (CharSequence) second), "putExtra(...)");
                        } else if (second instanceof Parcelable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Parcelable) second), "putExtra(...)");
                        } else if (second instanceof Object[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Serializable) second), "putExtra(...)");
                        } else if (second instanceof ArrayList) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Serializable) second), "putExtra(...)");
                        } else if (second instanceof Serializable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Serializable) second), "putExtra(...)");
                        } else if (second instanceof boolean[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (boolean[]) second), "putExtra(...)");
                        } else if (second instanceof byte[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (byte[]) second), "putExtra(...)");
                        } else if (second instanceof short[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (short[]) second), "putExtra(...)");
                        } else if (second instanceof char[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (char[]) second), "putExtra(...)");
                        } else if (second instanceof int[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (int[]) second), "putExtra(...)");
                        } else if (second instanceof long[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (long[]) second), "putExtra(...)");
                        } else if (second instanceof float[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (float[]) second), "putExtra(...)");
                        } else if (second instanceof double[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (double[]) second), "putExtra(...)");
                        } else if (second instanceof Bundle) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Bundle) second), "putExtra(...)");
                        } else if (second instanceof Intent) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Parcelable) second), "putExtra(...)");
                        } else {
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                }
                loginActivity.startActivity(intent);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint ds) {
                Intrinsics.checkNotNullParameter(ds, "ds");
                ds.setUnderlineText(false);
            }
        }, iIndexOf$default2, string3.length() + iIndexOf$default2, 34);
        textView.setHighlightColor(0);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(spannableString);
        WindowManager windowManager = getWindowManager();
        Intrinsics.checkNotNullExpressionValue(windowManager, "getWindowManager(...)");
        Intrinsics.checkNotNullExpressionValue(windowManager.getDefaultDisplay(), "getDefaultDisplay(...)");
        Window window = privacyDialog.getWindow();
        Intrinsics.checkNotNull(window);
        WindowManager.LayoutParams attributes = window.getAttributes();
        Intrinsics.checkNotNullExpressionValue(attributes, "getAttributes(...)");
        attributes.width = (int) (r1.getWidth() * 0.8d);
        Window window2 = privacyDialog.getWindow();
        if (window2 != null) {
            window2.setAttributes(attributes);
        }
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.LoginActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginActivity.customDialog$lambda$15(privacyDialog, this, view);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.LoginActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginActivity.customDialog$lambda$16(privacyDialog, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void customDialog$lambda$15(PrivacyDialog dialog, LoginActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void customDialog$lambda$16(PrivacyDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }
}
