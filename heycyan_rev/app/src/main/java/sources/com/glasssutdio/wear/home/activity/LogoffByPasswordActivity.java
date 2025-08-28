package com.glasssutdio.wear.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.MainActivity;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.MD5Utils;
import com.glasssutdio.wear.bus.RefreshUserEvent;
import com.glasssutdio.wear.databinding.ActivityLogoffByPasswordBinding;
import com.glasssutdio.wear.home.viewmodel.LoginActivityVM;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.EventBus;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: LogoffByPasswordActivity.kt */
@Metadata(m606d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\u0012\u0010\u0012\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/LogoffByPasswordActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityLogoffByPasswordBinding;", "code", "", "isPasswordVisible", "", "mViewModel", "Lcom/glasssutdio/wear/home/viewmodel/LoginActivityVM;", "getMViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/LoginActivityVM;", "mViewModel$delegate", "Lkotlin/Lazy;", "confirm", "", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class LogoffByPasswordActivity extends BaseSettingActivity {
    private ActivityLogoffByPasswordBinding binding;
    private String code;
    private boolean isPasswordVisible;

    /* renamed from: mViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mViewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public LogoffByPasswordActivity() {
        final LogoffByPasswordActivity logoffByPasswordActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewModel = LazyKt.lazy(new Function0<LoginActivityVM>() { // from class: com.glasssutdio.wear.home.activity.LogoffByPasswordActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.LoginActivityVM] */
            @Override // kotlin.jvm.functions.Function0
            public final LoginActivityVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(logoffByPasswordActivity, Reflection.getOrCreateKotlinClass(LoginActivityVM.class), qualifier, objArr);
            }
        });
    }

    private final LoginActivityVM getMViewModel() {
        return (LoginActivityVM) this.mViewModel.getValue();
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityLogoffByPasswordBinding activityLogoffByPasswordBindingInflate = ActivityLogoffByPasswordBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityLogoffByPasswordBindingInflate, "inflate(...)");
        this.binding = activityLogoffByPasswordBindingInflate;
        if (activityLogoffByPasswordBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLogoffByPasswordBindingInflate = null;
        }
        setContentView(activityLogoffByPasswordBindingInflate.getRoot());
        initView();
    }

    private final void initView() {
        final ActivityLogoffByPasswordBinding activityLogoffByPasswordBinding = this.binding;
        if (activityLogoffByPasswordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLogoffByPasswordBinding = null;
        }
        activityLogoffByPasswordBinding.title.tvTitle.setText(getString(C0775R.string.h_glass_266));
        activityLogoffByPasswordBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.LogoffByPasswordActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LogoffByPasswordActivity.initView$lambda$3$lambda$0(this.f$0, view);
            }
        });
        getMViewModel().getLogoffLD().observe(this, new LogoffByPasswordActivity$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: com.glasssutdio.wear.home.activity.LogoffByPasswordActivity$initView$1$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean bool) {
                this.this$0.dismissLoadingDialog();
                UserConfig.INSTANCE.getInstance().setUserToken("");
                UserConfig.INSTANCE.getInstance().setUid(0L);
                UserConfig.INSTANCE.getInstance().setLastLoginTimeStamp(0L);
                UserConfig.INSTANCE.getInstance().setLastLoginAccount("");
                UserConfig.INSTANCE.getInstance().setLastLoginPwd("");
                UserConfig.INSTANCE.getInstance().setLastLoginType(0);
                EventBus eventBus = EventBus.getDefault();
                RefreshUserEvent refreshUserEvent = new RefreshUserEvent();
                refreshUserEvent.setRefreshType(2);
                eventBus.post(refreshUserEvent);
                this.this$0.finish();
                LogoffByPasswordActivity logoffByPasswordActivity = this.this$0;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(logoffByPasswordActivity, (Class<?>) MainActivity.class);
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
                logoffByPasswordActivity.startActivity(intent);
                this.this$0.finish();
            }
        }));
        activityLogoffByPasswordBinding.tvConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.LogoffByPasswordActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LogoffByPasswordActivity.initView$lambda$3$lambda$1(this.f$0, view);
            }
        });
        activityLogoffByPasswordBinding.ivSeePwd.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.LogoffByPasswordActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LogoffByPasswordActivity.initView$lambda$3$lambda$2(this.f$0, activityLogoffByPasswordBinding, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$3$lambda$0(LogoffByPasswordActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$3$lambda$1(LogoffByPasswordActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.confirm();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$3$lambda$2(LogoffByPasswordActivity this$0, ActivityLogoffByPasswordBinding this_run, View view) {
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
        ActivityLogoffByPasswordBinding activityLogoffByPasswordBinding = this.binding;
        if (activityLogoffByPasswordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLogoffByPasswordBinding = null;
        }
        String string = activityLogoffByPasswordBinding.etPwd.getText().toString();
        if (string.length() == 0) {
            String string2 = getString(C0775R.string.h_glass_254);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            GlobalKt.showToast$default(string2, 0, 1, null);
        } else if (!Intrinsics.areEqual(MD5Utils.getMD5(string), UserConfig.INSTANCE.getInstance().getLastLoginPwd())) {
            String string3 = getString(C0775R.string.login_glass_4);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            GlobalKt.showToast$default(string3, 0, 1, null);
        } else {
            showLoadingDialog();
            getMViewModel().logoff(String.valueOf(UserConfig.INSTANCE.getInstance().getUid()));
        }
    }
}
