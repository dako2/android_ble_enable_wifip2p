package com.glasssutdio.wear.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.Constant;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.dialog.CommonDialog;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.bus.RefreshUserEvent;
import com.glasssutdio.wear.databinding.ActivityAccountSafetyBinding;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* compiled from: AccountSafetyActivity.kt */
@Metadata(m606d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0002J\u0012\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/AccountSafetyActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityAccountSafetyBinding;", "initView", "", "logout", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AccountSafetyActivity extends BaseSettingActivity {
    private ActivityAccountSafetyBinding binding;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$5$lambda$2(View view) {
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityAccountSafetyBinding activityAccountSafetyBindingInflate = ActivityAccountSafetyBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityAccountSafetyBindingInflate, "inflate(...)");
        this.binding = activityAccountSafetyBindingInflate;
        if (activityAccountSafetyBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAccountSafetyBindingInflate = null;
        }
        setContentView(activityAccountSafetyBindingInflate.getRoot());
        initView();
    }

    private final void initView() {
        ActivityAccountSafetyBinding activityAccountSafetyBinding = this.binding;
        if (activityAccountSafetyBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAccountSafetyBinding = null;
        }
        activityAccountSafetyBinding.title.tvTitle.setText(getString(C0775R.string.h_glass_236));
        activityAccountSafetyBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.AccountSafetyActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountSafetyActivity.initView$lambda$5$lambda$0(this.f$0, view);
            }
        });
        activityAccountSafetyBinding.tvLogout.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.AccountSafetyActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountSafetyActivity.initView$lambda$5$lambda$1(this.f$0, view);
            }
        });
        String lastLoginAccount = UserConfig.INSTANCE.getInstance().getLastLoginAccount();
        activityAccountSafetyBinding.tvAccountTitle.setText(getString(C0775R.string.h_glass_288_2));
        activityAccountSafetyBinding.tvPhone.setText(lastLoginAccount);
        ViewKt.gone(activityAccountSafetyBinding.ivArrow);
        activityAccountSafetyBinding.tvPwd.setText(getString(C0775R.string.album_glass_31_1));
        activityAccountSafetyBinding.clsPhone.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.AccountSafetyActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountSafetyActivity.initView$lambda$5$lambda$2(view);
            }
        });
        activityAccountSafetyBinding.clsPassword.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.AccountSafetyActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountSafetyActivity.initView$lambda$5$lambda$3(this.f$0, view);
            }
        });
        activityAccountSafetyBinding.clsSafetySetting.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.AccountSafetyActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountSafetyActivity.initView$lambda$5$lambda$4(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$5$lambda$0(AccountSafetyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$5$lambda$1(final AccountSafetyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CommonDialog.Builder builder = new CommonDialog.Builder();
        String string = this$0.getString(C0775R.string.h_glass_242);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        CommonDialog.Builder builderTitleMarginTop = builder.setTitle(string).titleMarginTop(GlobalKt.getDp((Number) 32));
        String string2 = this$0.getString(C0775R.string.h_glass_241);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        CommonDialog.Builder confirmMessage = builderTitleMarginTop.setConfirmMessage(string2);
        String string3 = this$0.getString(C0775R.string.h_glass_cancel);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        CommonDialog commonDialogBuild = confirmMessage.setCancelMessage(string3).build();
        commonDialogBuild.show(this$0.getSupportFragmentManager(), "logout");
        commonDialogBuild.setOnConfirmListener(new Function1<View, Unit>() { // from class: com.glasssutdio.wear.home.activity.AccountSafetyActivity$initView$1$2$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
                this.this$0.logout();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$5$lambda$3(AccountSafetyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AccountSafetyActivity accountSafetyActivity = this$0;
        Pair pair = new Pair(Constant.PAGE_REGISTER_KEY, 4);
        ArrayList<Pair> arrayList = new ArrayList();
        arrayList.add(pair);
        Intent intent = new Intent(accountSafetyActivity, (Class<?>) ForgetPasswordActivity.class);
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
        accountSafetyActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$5$lambda$4(AccountSafetyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AccountSafetyActivity accountSafetyActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(accountSafetyActivity, (Class<?>) SafetySettingActivity.class);
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
        accountSafetyActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void logout() {
        UserConfig.INSTANCE.getInstance().setUserToken("");
        UserConfig.INSTANCE.getInstance().setUid(0L);
        UserConfig.INSTANCE.getInstance().setLastLoginTimeStamp(0L);
        UserConfig.INSTANCE.getInstance().setLastLoginAccount("");
        UserConfig.INSTANCE.getInstance().setLastLoginPwd("");
        UserConfig.INSTANCE.getInstance().setLastLoginType(0);
        UserConfig.INSTANCE.getInstance().setNeedShowLogin(false);
        EventBus eventBus = EventBus.getDefault();
        RefreshUserEvent refreshUserEvent = new RefreshUserEvent();
        refreshUserEvent.setRefreshType(2);
        eventBus.post(refreshUserEvent);
        finish();
    }
}
