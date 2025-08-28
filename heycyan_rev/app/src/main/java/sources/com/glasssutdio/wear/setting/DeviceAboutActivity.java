package com.glasssutdio.wear.setting;

import android.os.Bundle;
import android.view.View;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.databinding.ActivityDeviceAboutBinding;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: DeviceAboutActivity.kt */
@Metadata(m606d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\n"}, m607d2 = {"Lcom/glasssutdio/wear/setting/DeviceAboutActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityDeviceAboutBinding;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class DeviceAboutActivity extends BaseSettingActivity {
    private ActivityDeviceAboutBinding binding;

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityDeviceAboutBinding activityDeviceAboutBindingInflate = ActivityDeviceAboutBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityDeviceAboutBindingInflate, "inflate(...)");
        this.binding = activityDeviceAboutBindingInflate;
        if (activityDeviceAboutBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDeviceAboutBindingInflate = null;
        }
        setContentView(activityDeviceAboutBindingInflate.getRoot());
        initView();
    }

    private final void initView() {
        ActivityDeviceAboutBinding activityDeviceAboutBinding = this.binding;
        if (activityDeviceAboutBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDeviceAboutBinding = null;
        }
        activityDeviceAboutBinding.title.tvTitle.setText(getString(C0775R.string.h_glass_45));
        activityDeviceAboutBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.setting.DeviceAboutActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceAboutActivity.initView$lambda$1$lambda$0(this.f$0, view);
            }
        });
        activityDeviceAboutBinding.tvBleName.setText(UserConfig.INSTANCE.getInstance().getDeviceName());
        activityDeviceAboutBinding.tvHwVersion.setText(UserConfig.INSTANCE.getInstance().getHwVersion());
        activityDeviceAboutBinding.tvSwVersion.setText(StringsKt.contains$default((CharSequence) UserConfig.INSTANCE.getInstance().getFmVersion(), (CharSequence) "_", false, 2, (Object) null) ? (String) StringsKt.split$default((CharSequence) UserConfig.INSTANCE.getInstance().getFmVersion(), new String[]{"_"}, false, 2, 2, (Object) null).get(1) : UserConfig.INSTANCE.getInstance().getFmVersion());
        activityDeviceAboutBinding.tvWifiHwVersion.setText(UserConfig.INSTANCE.getInstance().getHwVersionWifi());
        activityDeviceAboutBinding.tvWifiSwVersion.setText(StringsKt.contains$default((CharSequence) UserConfig.INSTANCE.getInstance().getFmVersionWifi(), (CharSequence) "_", false, 2, (Object) null) ? (String) StringsKt.split$default((CharSequence) UserConfig.INSTANCE.getInstance().getFmVersionWifi(), new String[]{"_"}, false, 2, 2, (Object) null).get(1) : UserConfig.INSTANCE.getInstance().getFmVersionWifi());
        activityDeviceAboutBinding.tvAppVersion.setText(GlobalKt.getVersionName(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$1$lambda$0(DeviceAboutActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }
}
