package com.glasssutdio.wear.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.lifecycle.Lifecycle;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.Constant;
import com.glasssutdio.wear.all.EditTextViewExtKt;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.StringExtKt;
import com.glasssutdio.wear.all.TextViewExtKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.bean.Req.ResetPwdReq;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.MD5Utils;
import com.glasssutdio.wear.all.utils.time.Interval;
import com.glasssutdio.wear.all.view.PasswordEditText;
import com.glasssutdio.wear.api.RetCodeValue;
import com.glasssutdio.wear.bus.RefreshUserEvent;
import com.glasssutdio.wear.databinding.ActivityRetrieveBinding;
import com.glasssutdio.wear.home.bean.LoginResModel;
import com.glasssutdio.wear.home.bean.RequestFailModel;
import com.glasssutdio.wear.home.viewmodel.RetrievePasswordActivityVM;
import com.glasssutdio.wear.manager.BaseFullActivity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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

/* compiled from: ForgetPasswordActivity.kt */
@Metadata(m606d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\b\u0010\u0010\u001a\u00020\u000eH\u0016J\b\u0010\u0011\u001a\u00020\u000eH\u0016J\u0012\u0010\u0012\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/ForgetPasswordActivity;", "Lcom/glasssutdio/wear/manager/BaseFullActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityRetrieveBinding;", "interval", "Lcom/glasssutdio/wear/all/utils/time/Interval;", "mViewModel", "Lcom/glasssutdio/wear/home/viewmodel/RetrievePasswordActivityVM;", "getMViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/RetrievePasswordActivityVM;", "mViewModel$delegate", "Lkotlin/Lazy;", "checkEmpty", "", "confirm", "initView", "observer", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class ForgetPasswordActivity extends BaseFullActivity {
    private ActivityRetrieveBinding binding;
    private Interval interval;

    /* renamed from: mViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mViewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public ForgetPasswordActivity() {
        final ForgetPasswordActivity forgetPasswordActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewModel = LazyKt.lazy(new Function0<RetrievePasswordActivityVM>() { // from class: com.glasssutdio.wear.home.activity.ForgetPasswordActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.RetrievePasswordActivityVM] */
            @Override // kotlin.jvm.functions.Function0
            public final RetrievePasswordActivityVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(forgetPasswordActivity, Reflection.getOrCreateKotlinClass(RetrievePasswordActivityVM.class), qualifier, objArr);
            }
        });
    }

    private final RetrievePasswordActivityVM getMViewModel() {
        return (RetrievePasswordActivityVM) this.mViewModel.getValue();
    }

    @Override // com.glasssutdio.wear.manager.BaseFullActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        ActivityRetrieveBinding activityRetrieveBindingInflate = ActivityRetrieveBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityRetrieveBindingInflate, "inflate(...)");
        this.binding = activityRetrieveBindingInflate;
        if (activityRetrieveBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRetrieveBindingInflate = null;
        }
        setContentView(activityRetrieveBindingInflate.getRoot());
        super.onCreate(savedInstanceState);
    }

    @Override // com.glasssutdio.wear.manager.BaseFullActivity
    public void observer() {
        RetrievePasswordActivityVM mViewModel = getMViewModel();
        ForgetPasswordActivity forgetPasswordActivity = this;
        mViewModel.getSendEmailLD().observe(forgetPasswordActivity, new ForgetPasswordActivity$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: com.glasssutdio.wear.home.activity.ForgetPasswordActivity$observer$1$1
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
                Interval interval = this.this$0.interval;
                if (interval != null) {
                    interval.start();
                }
            }
        }));
        mViewModel.getResetPwdLD().observe(forgetPasswordActivity, new ForgetPasswordActivity$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: com.glasssutdio.wear.home.activity.ForgetPasswordActivity$observer$1$2
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
                String string = this.this$0.getString(C0775R.string.h_glass_321);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                GlobalKt.showToast$default(string, 0, 1, null);
                this.this$0.finish();
            }
        }));
        mViewModel.getRegisterLD().observe(forgetPasswordActivity, new ForgetPasswordActivity$sam$androidx_lifecycle_Observer$0(new Function1<LoginResModel, Unit>() { // from class: com.glasssutdio.wear.home.activity.ForgetPasswordActivity$observer$1$3
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
                String string = this.this$0.getString(C0775R.string.h_glass_321_1);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                GlobalKt.showToast$default(string, 0, 1, null);
                UserConfig.INSTANCE.getInstance().setUserToken(loginResModel.getToken());
                UserConfig companion = UserConfig.INSTANCE.getInstance();
                Long longOrNull = StringsKt.toLongOrNull(loginResModel.getUid());
                companion.setUid(longOrNull != null ? longOrNull.longValue() : 51888L);
                EventBus.getDefault().post(new RefreshUserEvent());
                ForgetPasswordActivity forgetPasswordActivity2 = this.this$0;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(forgetPasswordActivity2, (Class<?>) CompleteProfileActivity.class);
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
                forgetPasswordActivity2.startActivity(intent);
                this.this$0.finish();
            }
        }));
        mViewModel.getFailLD().observe(forgetPasswordActivity, new ForgetPasswordActivity$sam$androidx_lifecycle_Observer$0(new Function1<RequestFailModel, Unit>() { // from class: com.glasssutdio.wear.home.activity.ForgetPasswordActivity$observer$1$4
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
                            String msg = requestFailModel.getMsg();
                            if (msg != null) {
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
    public void initView() {
        getMViewModel().setPageType(getIntent().getIntExtra(Constant.PAGE_REGISTER_KEY, 1));
        Interval intervalLife$default = Interval.life$default(new Interval(1L, 1L, TimeUnit.SECONDS, 60L, 0L, 16, null), this, (Lifecycle.Event) null, 2, (Object) null);
        this.interval = intervalLife$default;
        if (intervalLife$default != null) {
            intervalLife$default.subscribe(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.activity.ForgetPasswordActivity$initView$1$1
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
                    ActivityRetrieveBinding activityRetrieveBinding = this.this$0.binding;
                    ActivityRetrieveBinding activityRetrieveBinding2 = null;
                    if (activityRetrieveBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRetrieveBinding = null;
                    }
                    TextView textView = activityRetrieveBinding.tvGetCode;
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string = this.this$0.getString(C0775R.string.h_glass_316);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    String str = String.format(string, Arrays.copyOf(new Object[]{Long.valueOf(j)}, 1));
                    Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                    textView.setText(str);
                    ActivityRetrieveBinding activityRetrieveBinding3 = this.this$0.binding;
                    if (activityRetrieveBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityRetrieveBinding2 = activityRetrieveBinding3;
                    }
                    activityRetrieveBinding2.tvGetCode.setEnabled(false);
                }
            }).finish(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.activity.ForgetPasswordActivity$initView$1$2
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
                    ActivityRetrieveBinding activityRetrieveBinding = this.this$0.binding;
                    ActivityRetrieveBinding activityRetrieveBinding2 = null;
                    if (activityRetrieveBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityRetrieveBinding = null;
                    }
                    activityRetrieveBinding.tvGetCode.setText(this.this$0.getString(C0775R.string.h_glass_311));
                    ActivityRetrieveBinding activityRetrieveBinding3 = this.this$0.binding;
                    if (activityRetrieveBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityRetrieveBinding2 = activityRetrieveBinding3;
                    }
                    activityRetrieveBinding2.tvGetCode.setEnabled(true);
                }
            });
        }
        final ActivityRetrieveBinding activityRetrieveBinding = this.binding;
        if (activityRetrieveBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRetrieveBinding = null;
        }
        activityRetrieveBinding.ivBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.ForgetPasswordActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ForgetPasswordActivity.initView$lambda$8$lambda$2(this.f$0, view);
            }
        });
        TextView tvTitle = activityRetrieveBinding.tvTitle;
        Intrinsics.checkNotNullExpressionValue(tvTitle, "tvTitle");
        ViewKt.statusMargin$default(tvTitle, false, 0, 3, null);
        TextView tvTitle2 = activityRetrieveBinding.tvTitle;
        Intrinsics.checkNotNullExpressionValue(tvTitle2, "tvTitle");
        TextViewExtKt.setupMarquee(tvTitle2);
        activityRetrieveBinding.tvConfirm.setEnabled(false);
        EditText etEmail = activityRetrieveBinding.etEmail;
        Intrinsics.checkNotNullExpressionValue(etEmail, "etEmail");
        etEmail.addTextChangedListener(new TextWatcher() { // from class: com.glasssutdio.wear.home.activity.ForgetPasswordActivity$initView$lambda$8$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                this.this$0.checkEmpty();
            }
        });
        EditText etCode = activityRetrieveBinding.etCode;
        Intrinsics.checkNotNullExpressionValue(etCode, "etCode");
        etCode.addTextChangedListener(new TextWatcher() { // from class: com.glasssutdio.wear.home.activity.ForgetPasswordActivity$initView$lambda$8$$inlined$doAfterTextChanged$2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                this.this$0.checkEmpty();
            }
        });
        activityRetrieveBinding.petPassword.doAfterTextChanged(new Function1<Editable, Unit>() { // from class: com.glasssutdio.wear.home.activity.ForgetPasswordActivity$initView$2$4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Editable editable) {
                invoke2(editable);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Editable editable) {
                this.this$0.checkEmpty();
            }
        });
        activityRetrieveBinding.petPasswordConfirm.doAfterTextChanged(new Function1<Editable, Unit>() { // from class: com.glasssutdio.wear.home.activity.ForgetPasswordActivity$initView$2$5
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Editable editable) {
                invoke2(editable);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Editable editable) {
                this.this$0.checkEmpty();
            }
        });
        int pageType = getMViewModel().getPageType();
        if (pageType == 1) {
            activityRetrieveBinding.tvTitle.setText(getString(C0775R.string.h_glass_300));
            activityRetrieveBinding.tvAccount.setText(getString(C0775R.string.h_glass_287));
            activityRetrieveBinding.etEmail.setHint(getString(C0775R.string.h_glass_288_3));
            PasswordEditText passwordEditText = activityRetrieveBinding.petPassword;
            String string = getString(C0775R.string.h_glass_313);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            passwordEditText.setHint(string);
            activityRetrieveBinding.etEmail.setInputType(3);
        } else if (pageType == 2) {
            activityRetrieveBinding.tvTitle.setText(getString(C0775R.string.h_glass_300));
            activityRetrieveBinding.tvAccount.setText(getString(C0775R.string.h_glass_287));
            activityRetrieveBinding.etEmail.setHint(getString(C0775R.string.h_glass_288_1));
            PasswordEditText passwordEditText2 = activityRetrieveBinding.petPassword;
            String string2 = getString(C0775R.string.h_glass_313);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            passwordEditText2.setHint(string2);
            activityRetrieveBinding.etEmail.setInputType(32);
        } else if (pageType == 3) {
            activityRetrieveBinding.tvTitle.setText(getString(C0775R.string.login_glass_1));
            activityRetrieveBinding.tvAccount.setText(getString(C0775R.string.h_glass_287));
            activityRetrieveBinding.tvPwdHint.setText(getString(C0775R.string.h_glass_248));
            activityRetrieveBinding.etEmail.setHint(getString(C0775R.string.h_glass_288_1));
            PasswordEditText passwordEditText3 = activityRetrieveBinding.petPassword;
            String string3 = getString(C0775R.string.h_glass_296);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            passwordEditText3.setHint(string3);
            activityRetrieveBinding.etEmail.setInputType(32);
        } else if (pageType == 4) {
            activityRetrieveBinding.tvTitle.setText(getString(C0775R.string.h_glass_300_1));
            activityRetrieveBinding.tvAccount.setText(getString(C0775R.string.h_glass_287));
            activityRetrieveBinding.etEmail.setHint(getString(C0775R.string.h_glass_288_1));
            activityRetrieveBinding.etEmail.setEnabled(UserConfig.INSTANCE.getInstance().getLastLoginAccount().length() == 0);
            activityRetrieveBinding.etEmail.setText(UserConfig.INSTANCE.getInstance().getLastLoginAccount());
            PasswordEditText passwordEditText4 = activityRetrieveBinding.petPassword;
            String string4 = getString(C0775R.string.h_glass_313);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
            passwordEditText4.setHint(string4);
            activityRetrieveBinding.etEmail.setInputType(32);
        }
        activityRetrieveBinding.ivBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.ForgetPasswordActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ForgetPasswordActivity.initView$lambda$8$lambda$5(this.f$0, view);
            }
        });
        activityRetrieveBinding.tvGetCode.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.ForgetPasswordActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ForgetPasswordActivity.initView$lambda$8$lambda$6(activityRetrieveBinding, this, view);
            }
        });
        activityRetrieveBinding.tvConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.ForgetPasswordActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ForgetPasswordActivity.initView$lambda$8$lambda$7(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$8$lambda$2(ForgetPasswordActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$8$lambda$5(ForgetPasswordActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$8$lambda$6(ActivityRetrieveBinding this_run, ForgetPasswordActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this_run.etEmail.getText().toString();
        if (this$0.getMViewModel().getPageType() == 1) {
            EditText etEmail = this_run.etEmail;
            Intrinsics.checkNotNullExpressionValue(etEmail, "etEmail");
            if (EditTextViewExtKt.isEmpty(etEmail)) {
                String string2 = this$0.getString(C0775R.string.h_glass_288_3);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                GlobalKt.showToast$default(string2, 0, 1, null);
                return;
            } else if (string.length() != 11) {
                String string3 = this$0.getString(C0775R.string.h_glass_298_1);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                GlobalKt.showToast$default(string3, 0, 1, null);
                return;
            }
        } else {
            EditText etEmail2 = this_run.etEmail;
            Intrinsics.checkNotNullExpressionValue(etEmail2, "etEmail");
            if (EditTextViewExtKt.isEmpty(etEmail2)) {
                String string4 = this$0.getString(C0775R.string.h_glass_288_1);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                GlobalKt.showToast$default(string4, 0, 1, null);
                return;
            } else if (!StringExtKt.isEmail(string)) {
                String string5 = this$0.getString(C0775R.string.h_glass_298);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                GlobalKt.showToast$default(string5, 0, 1, null);
                return;
            }
        }
        if (this$0.getMViewModel().getPageType() == 2 || this$0.getMViewModel().getPageType() == 4) {
            this$0.getMViewModel().emailSendCode(string);
        } else if (this$0.getMViewModel().getPageType() == 3) {
            this$0.getMViewModel().registerSendCode(string, GlobalKt.getAppName(this$0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$8$lambda$7(ForgetPasswordActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.confirm();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void checkEmpty() {
        boolean z;
        ActivityRetrieveBinding activityRetrieveBinding = this.binding;
        if (activityRetrieveBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRetrieveBinding = null;
        }
        TextView textView = activityRetrieveBinding.tvConfirm;
        EditText etEmail = activityRetrieveBinding.etEmail;
        Intrinsics.checkNotNullExpressionValue(etEmail, "etEmail");
        if (!EditTextViewExtKt.isEmpty(etEmail)) {
            EditText etCode = activityRetrieveBinding.etCode;
            Intrinsics.checkNotNullExpressionValue(etCode, "etCode");
            z = !EditTextViewExtKt.isEmpty(etCode) && activityRetrieveBinding.petPassword.getText().length() > 0 && activityRetrieveBinding.petPasswordConfirm.getText().length() > 0;
        }
        textView.setEnabled(z);
    }

    private final void confirm() {
        ActivityRetrieveBinding activityRetrieveBinding = this.binding;
        if (activityRetrieveBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRetrieveBinding = null;
        }
        String string = activityRetrieveBinding.etEmail.getText().toString();
        String string2 = activityRetrieveBinding.etCode.getText().toString();
        String text = activityRetrieveBinding.petPassword.getText();
        String text2 = activityRetrieveBinding.petPasswordConfirm.getText();
        if (getMViewModel().getPageType() == 1) {
            EditText etEmail = activityRetrieveBinding.etEmail;
            Intrinsics.checkNotNullExpressionValue(etEmail, "etEmail");
            if (EditTextViewExtKt.isEmpty(etEmail)) {
                String string3 = getString(C0775R.string.h_glass_288_3);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                GlobalKt.showToast$default(string3, 0, 1, null);
                return;
            }
            if (string.length() != 11) {
                String string4 = getString(C0775R.string.h_glass_298_1);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                GlobalKt.showToast$default(string4, 0, 1, null);
                return;
            }
            if (string2.length() == 0) {
                String string5 = getString(C0775R.string.h_glass_310);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                GlobalKt.showToast$default(string5, 0, 1, null);
                return;
            }
            if (text.length() == 0) {
                String string6 = getString(C0775R.string.h_glass_313);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                GlobalKt.showToast$default(string6, 0, 1, null);
                return;
            } else if (StringExtKt.verifyPwdNotValid(text2)) {
                String string7 = getString(C0775R.string.h_glass_257_1);
                Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                GlobalKt.showToast$default(string7, 0, 1, null);
                return;
            } else if (text2.length() == 0) {
                String string8 = getString(C0775R.string.h_glass_315);
                Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
                GlobalKt.showToast$default(string8, 0, 1, null);
                return;
            } else if (StringExtKt.verifyPwdNotValid(text2)) {
                String string9 = getString(C0775R.string.h_glass_257_1);
                Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
                GlobalKt.showToast$default(string9, 0, 1, null);
                return;
            }
        } else {
            EditText etEmail2 = activityRetrieveBinding.etEmail;
            Intrinsics.checkNotNullExpressionValue(etEmail2, "etEmail");
            if (EditTextViewExtKt.isEmpty(etEmail2)) {
                String string10 = getString(C0775R.string.h_glass_288_1);
                Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
                GlobalKt.showToast$default(string10, 0, 1, null);
                return;
            }
            if (!StringExtKt.isEmail(string)) {
                String string11 = getString(C0775R.string.h_glass_298);
                Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
                GlobalKt.showToast$default(string11, 0, 1, null);
                return;
            }
            if (string2.length() == 0) {
                String string12 = getString(C0775R.string.h_glass_310);
                Intrinsics.checkNotNullExpressionValue(string12, "getString(...)");
                GlobalKt.showToast$default(string12, 0, 1, null);
                return;
            }
            if (text.length() == 0) {
                if (getMViewModel().getPageType() == 3) {
                    String string13 = getString(C0775R.string.h_glass_296);
                    Intrinsics.checkNotNullExpressionValue(string13, "getString(...)");
                    GlobalKt.showToast$default(string13, 0, 1, null);
                    return;
                } else {
                    String string14 = getString(C0775R.string.h_glass_313);
                    Intrinsics.checkNotNullExpressionValue(string14, "getString(...)");
                    GlobalKt.showToast$default(string14, 0, 1, null);
                    return;
                }
            }
            if (StringExtKt.verifyPwdNotValid(text)) {
                String string15 = getString(C0775R.string.h_glass_257_1);
                Intrinsics.checkNotNullExpressionValue(string15, "getString(...)");
                GlobalKt.showToast$default(string15, 0, 1, null);
                return;
            } else if (text2.length() == 0) {
                String string16 = getString(C0775R.string.h_glass_315);
                Intrinsics.checkNotNullExpressionValue(string16, "getString(...)");
                GlobalKt.showToast$default(string16, 0, 1, null);
                return;
            } else if (StringExtKt.verifyPwdNotValid(text2)) {
                String string17 = getString(C0775R.string.h_glass_257_1);
                Intrinsics.checkNotNullExpressionValue(string17, "getString(...)");
                GlobalKt.showToast$default(string17, 0, 1, null);
                return;
            }
        }
        if (!Intrinsics.areEqual(text2, text)) {
            String string18 = getString(C0775R.string.h_glass_258);
            Intrinsics.checkNotNullExpressionValue(string18, "getString(...)");
            GlobalKt.showToast$default(string18, 0, 1, null);
            return;
        }
        String md5 = MD5Utils.getMD5(text);
        showLoadingDialog();
        int pageType = getMViewModel().getPageType();
        if (pageType != 2) {
            if (pageType == 3) {
                RetrievePasswordActivityVM mViewModel = getMViewModel();
                Intrinsics.checkNotNull(md5);
                mViewModel.registerByEmail(string, md5, string2);
                return;
            } else if (pageType != 4) {
                return;
            }
        }
        getMViewModel().resetPassword(new ResetPwdReq(string2, string, md5));
    }
}
