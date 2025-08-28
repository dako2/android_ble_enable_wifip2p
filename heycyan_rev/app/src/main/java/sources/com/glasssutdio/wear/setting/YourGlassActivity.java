package com.glasssutdio.wear.setting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.ThreadExtKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.dialog.CommonDialog;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.api.NetState;
import com.glasssutdio.wear.api.response.FirmwareOtaResp;
import com.glasssutdio.wear.ble.connect.DeviceBindViewModel;
import com.glasssutdio.wear.bus.BluetoothEvent;
import com.glasssutdio.wear.bus.BusEvent;
import com.glasssutdio.wear.bus.EventType;
import com.glasssutdio.wear.bus.GlassesBatteryUpdateEvent;
import com.glasssutdio.wear.databinding.ActivityYourGlassBinding;
import com.glasssutdio.wear.depository.OTADepository;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import com.glasssutdio.wear.ota.OTAActivity;
import com.glasssutdio.wear.setting.YourGlassActivity;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpStatus;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.DeviceManager;
import com.oudmon.ble.base.communication.ILargeDataResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.resp.BaseResponse;
import com.oudmon.ble.base.communication.bigData.resp.GlassModelControlResponse;
import com.oudmon.ble.base.communication.bigData.resp.GlassesWearRsp;
import com.oudmon.ble.base.scan.BleScannerHelper;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: YourGlassActivity.kt */
@Metadata(m606d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0011H\u0002J\u000e\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0011J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\u0012\u0010\u001e\u001a\u00020\u00162\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\u0010\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020#H\u0017J\b\u0010$\u001a\u00020\u0016H\u0014J\b\u0010%\u001a\u00020\u0016H\u0002J\b\u0010&\u001a\u00020\u0016H\u0002J\b\u0010'\u001a\u00020\u0016H\u0002J\b\u0010(\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, m607d2 = {"Lcom/glasssutdio/wear/setting/YourGlassActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityYourGlassBinding;", "deviceViewModel", "Lcom/glasssutdio/wear/ble/connect/DeviceBindViewModel;", "getDeviceViewModel", "()Lcom/glasssutdio/wear/ble/connect/DeviceBindViewModel;", "deviceViewModel$delegate", "Lkotlin/Lazy;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/oudmon/ble/base/communication/ILargeDataResponse;", "Lcom/oudmon/ble/base/communication/bigData/resp/GlassesWearRsp;", "getListener", "()Lcom/oudmon/ble/base/communication/ILargeDataResponse;", "otaClick", "", "otaCount", "", "otaSuccess", "checkOta", "", "hwName", "", "fmVersion", "wifiNotBle", "connectedChangeRefreshUI", "connected", "loadViews", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/glasssutdio/wear/bus/BusEvent;", "onResume", "refreshOtaSuccess", "showRestFactoryDialog", "showRestartDialog", "showUnbindDialog", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class YourGlassActivity extends BaseSettingActivity {
    private ActivityYourGlassBinding binding;

    /* renamed from: deviceViewModel$delegate, reason: from kotlin metadata */
    private final Lazy deviceViewModel;
    private final ILargeDataResponse<GlassesWearRsp> listener;
    private boolean otaClick;
    private int otaCount;
    private int otaSuccess;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadViews$lambda$10$lambda$8(View view) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public YourGlassActivity() {
        final YourGlassActivity yourGlassActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.deviceViewModel = LazyKt.lazy(new Function0<DeviceBindViewModel>() { // from class: com.glasssutdio.wear.setting.YourGlassActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.ble.connect.DeviceBindViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final DeviceBindViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(yourGlassActivity, Reflection.getOrCreateKotlinClass(DeviceBindViewModel.class), qualifier, objArr);
            }
        });
        this.listener = new ILargeDataResponse() { // from class: com.glasssutdio.wear.setting.YourGlassActivity$$ExternalSyntheticLambda9
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i, BaseResponse baseResponse) {
                YourGlassActivity.listener$lambda$0(this.f$0, i, (GlassesWearRsp) baseResponse);
            }
        };
    }

    private final DeviceBindViewModel getDeviceViewModel() {
        return (DeviceBindViewModel) this.deviceViewModel.getValue();
    }

    public final ILargeDataResponse<GlassesWearRsp> getListener() {
        return this.listener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void listener$lambda$0(YourGlassActivity this$0, int i, GlassesWearRsp glassesWearRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityYourGlassBinding activityYourGlassBinding = this$0.binding;
        if (activityYourGlassBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityYourGlassBinding = null;
        }
        activityYourGlassBinding.gsc1.setChecked(glassesWearRsp.isOpen());
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityYourGlassBinding activityYourGlassBindingInflate = ActivityYourGlassBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityYourGlassBindingInflate, "inflate(...)");
        this.binding = activityYourGlassBindingInflate;
        if (activityYourGlassBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityYourGlassBindingInflate = null;
        }
        setContentView(activityYourGlassBindingInflate.getRoot());
        loadViews();
    }

    private final void loadViews() {
        ActivityYourGlassBinding activityYourGlassBinding = this.binding;
        ActivityYourGlassBinding activityYourGlassBinding2 = null;
        if (activityYourGlassBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityYourGlassBinding = null;
        }
        activityYourGlassBinding.tvDeviceName.setText(UserConfig.INSTANCE.getInstance().getDeviceName());
        activityYourGlassBinding.tvDeviceAddress.setText(UserConfig.INSTANCE.getInstance().getDeviceAddress());
        String fmVersionWifi = UserConfig.INSTANCE.getInstance().getFmVersionWifi();
        List listSplit$default = StringsKt.split$default((CharSequence) fmVersionWifi, new String[]{"_"}, false, 0, 6, (Object) null);
        if (listSplit$default.size() >= 2) {
            fmVersionWifi = (String) listSplit$default.get(2);
        }
        activityYourGlassBinding.tvDeviceFirmwareWifi.setText(fmVersionWifi);
        String fmVersion = UserConfig.INSTANCE.getInstance().getFmVersion();
        List listSplit$default2 = StringsKt.split$default((CharSequence) fmVersion, new String[]{"_"}, false, 0, 6, (Object) null);
        if (listSplit$default2.size() >= 2) {
            fmVersion = (String) listSplit$default2.get(1);
        }
        activityYourGlassBinding.tvDeviceFirmware.setText(fmVersion);
        activityYourGlassBinding.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.setting.YourGlassActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                YourGlassActivity.loadViews$lambda$10$lambda$1(this.f$0, view);
            }
        });
        activityYourGlassBinding.tvToUnbind.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.setting.YourGlassActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                YourGlassActivity.loadViews$lambda$10$lambda$2(this.f$0, view);
            }
        });
        activityYourGlassBinding.ctlOta.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.setting.YourGlassActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                YourGlassActivity.loadViews$lambda$10$lambda$3(this.f$0, view);
            }
        });
        activityYourGlassBinding.ctlRestart.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.setting.YourGlassActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                YourGlassActivity.loadViews$lambda$10$lambda$4(this.f$0, view);
            }
        });
        activityYourGlassBinding.ctlReset.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.setting.YourGlassActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                YourGlassActivity.loadViews$lambda$10$lambda$5(this.f$0, view);
            }
        });
        activityYourGlassBinding.ctlVideoSetting.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.setting.YourGlassActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                YourGlassActivity.loadViews$lambda$10$lambda$6(this.f$0, view);
            }
        });
        activityYourGlassBinding.ctlDeiceAbout.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.setting.YourGlassActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                YourGlassActivity.loadViews$lambda$10$lambda$7(this.f$0, view);
            }
        });
        activityYourGlassBinding.glassStatus1.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.setting.YourGlassActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                YourGlassActivity.loadViews$lambda$10$lambda$8(view);
            }
        });
        activityYourGlassBinding.gsc1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.glasssutdio.wear.setting.YourGlassActivity$$ExternalSyntheticLambda8
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                YourGlassActivity.loadViews$lambda$10$lambda$9(this.f$0, compoundButton, z);
            }
        });
        boolean lowBattery = UserConfig.INSTANCE.getInstance().getLowBattery();
        ActivityYourGlassBinding activityYourGlassBinding3 = this.binding;
        if (activityYourGlassBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityYourGlassBinding3 = null;
        }
        activityYourGlassBinding3.batteryView.setBatteryPercentage(UserConfig.INSTANCE.getInstance().getBattery());
        ActivityYourGlassBinding activityYourGlassBinding4 = this.binding;
        if (activityYourGlassBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityYourGlassBinding4 = null;
        }
        activityYourGlassBinding4.textDeviceBattery.setText(new StringBuilder().append(UserConfig.INSTANCE.getInstance().getBattery()).append('%').toString());
        ActivityYourGlassBinding activityYourGlassBinding5 = this.binding;
        if (activityYourGlassBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityYourGlassBinding5 = null;
        }
        activityYourGlassBinding5.batteryView.isLowBattery(lowBattery);
        ActivityYourGlassBinding activityYourGlassBinding6 = this.binding;
        if (activityYourGlassBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityYourGlassBinding6 = null;
        }
        activityYourGlassBinding6.batteryView.isCharging(UserConfig.INSTANCE.getInstance().getChanging());
        if (lowBattery) {
            ActivityYourGlassBinding activityYourGlassBinding7 = this.binding;
            if (activityYourGlassBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityYourGlassBinding7 = null;
            }
            activityYourGlassBinding7.textDeviceBattery.setTextColor(ContextCompat.getColor(this, C0775R.color.color_FF0303));
        } else {
            ActivityYourGlassBinding activityYourGlassBinding8 = this.binding;
            if (activityYourGlassBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityYourGlassBinding8 = null;
            }
            activityYourGlassBinding8.textDeviceBattery.setTextColor(ContextCompat.getColor(this, C0775R.color.g_color_6));
        }
        LargeDataHandler.getInstance().wearCheck(false, false, this.listener);
        checkOta(UserConfig.INSTANCE.getInstance().getHwVersionWifi(), UserConfig.INSTANCE.getInstance().getFmVersionWifi(), true);
        getDeviceViewModel().getPicUiState().observe(this, new YourGlassActivity$sam$androidx_lifecycle_Observer$0(new Function1<DeviceBindViewModel.DevicePictureUI, Unit>() { // from class: com.glasssutdio.wear.setting.YourGlassActivity.loadViews.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DeviceBindViewModel.DevicePictureUI devicePictureUI) {
                invoke2(devicePictureUI);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final DeviceBindViewModel.DevicePictureUI devicePictureUI) {
                if (devicePictureUI.getLocalUrl().length() > 0) {
                    RequestBuilder<Bitmap> requestBuilderLoad = Glide.with((FragmentActivity) YourGlassActivity.this).asBitmap().load("file://" + devicePictureUI.getLocalUrl());
                    final YourGlassActivity yourGlassActivity = YourGlassActivity.this;
                    requestBuilderLoad.into((RequestBuilder<Bitmap>) new CustomTarget<Bitmap>() { // from class: com.glasssutdio.wear.setting.YourGlassActivity.loadViews.2.1
                        @Override // com.bumptech.glide.request.target.Target
                        public void onLoadCleared(Drawable placeholder) {
                        }

                        @Override // com.bumptech.glide.request.target.Target
                        public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                            onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                        }

                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            Intrinsics.checkNotNullParameter(resource, "resource");
                            ActivityYourGlassBinding activityYourGlassBinding9 = yourGlassActivity.binding;
                            if (activityYourGlassBinding9 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityYourGlassBinding9 = null;
                            }
                            activityYourGlassBinding9.imageNotification.setImageBitmap(resource);
                        }

                        @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                        public void onLoadFailed(Drawable errorDrawable) {
                            super.onLoadFailed(errorDrawable);
                            final DeviceBindViewModel.DevicePictureUI devicePictureUI2 = devicePictureUI;
                            final YourGlassActivity yourGlassActivity2 = yourGlassActivity;
                            ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass1, Unit>() { // from class: com.glasssutdio.wear.setting.YourGlassActivity$loadViews$2$1$onLoadFailed$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(YourGlassActivity.C11342.AnonymousClass1 anonymousClass1) {
                                    invoke2(anonymousClass1);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(YourGlassActivity.C11342.AnonymousClass1 ktxRunOnUi) {
                                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                    XLog.m137i(devicePictureUI2.getUrl());
                                    RequestBuilder<Drawable> requestBuilderLoad2 = Glide.with((FragmentActivity) yourGlassActivity2).load(devicePictureUI2.getUrl());
                                    ActivityYourGlassBinding activityYourGlassBinding9 = yourGlassActivity2.binding;
                                    if (activityYourGlassBinding9 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                                        activityYourGlassBinding9 = null;
                                    }
                                    requestBuilderLoad2.into(activityYourGlassBinding9.imageNotification);
                                }
                            });
                        }
                    });
                    return;
                }
                RequestBuilder<Drawable> requestBuilderLoad2 = Glide.with((FragmentActivity) YourGlassActivity.this).load(devicePictureUI.getUrl());
                ActivityYourGlassBinding activityYourGlassBinding9 = YourGlassActivity.this.binding;
                ActivityYourGlassBinding activityYourGlassBinding10 = null;
                if (activityYourGlassBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityYourGlassBinding9 = null;
                }
                RequestBuilder requestBuilderError = requestBuilderLoad2.placeholder(activityYourGlassBinding9.imageNotification.getDrawable()).dontAnimate().error(C0775R.mipmap.home_default_glasses);
                ActivityYourGlassBinding activityYourGlassBinding11 = YourGlassActivity.this.binding;
                if (activityYourGlassBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityYourGlassBinding10 = activityYourGlassBinding11;
                }
                requestBuilderError.into(activityYourGlassBinding10.imageNotification);
            }
        }));
        if (UserConfig.INSTANCE.getInstance().getHwVersion().length() > 0) {
            getDeviceViewModel().devicePicture(UserConfig.INSTANCE.getInstance().getHwVersion());
        }
        if (UserConfig.INSTANCE.getInstance().getSupportWear()) {
            ActivityYourGlassBinding activityYourGlassBinding9 = this.binding;
            if (activityYourGlassBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityYourGlassBinding2 = activityYourGlassBinding9;
            }
            ViewKt.visible(activityYourGlassBinding2.ctlWearCheck);
            return;
        }
        ActivityYourGlassBinding activityYourGlassBinding10 = this.binding;
        if (activityYourGlassBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityYourGlassBinding2 = activityYourGlassBinding10;
        }
        ViewKt.gone(activityYourGlassBinding2.ctlWearCheck);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadViews$lambda$10$lambda$1(YourGlassActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadViews$lambda$10$lambda$2(YourGlassActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showUnbindDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadViews$lambda$10$lambda$3(YourGlassActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!BleOperateManager.getInstance().isConnected()) {
            String string = this$0.getString(C0775R.string.ble_glass_18);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        if (UserConfig.INSTANCE.getInstance().getBattery() <= 30) {
            String string2 = this$0.getString(C0775R.string.ble_glass_21, new Object[]{"30"});
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            GlobalKt.showToast$default(string2, 0, 1, null);
            return;
        }
        if (this$0.otaClick || UserConfig.INSTANCE.getInstance().getOtaDown()) {
            YourGlassActivity yourGlassActivity = this$0;
            ArrayList<Pair> arrayList = new ArrayList();
            Intent intent = new Intent(yourGlassActivity, (Class<?>) OTAActivity.class);
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
            yourGlassActivity.startActivity(intent);
            return;
        }
        String string3 = this$0.getString(C0775R.string.ble_glass_19);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        GlobalKt.showToast$default(string3, 0, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadViews$lambda$10$lambda$4(YourGlassActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!BleOperateManager.getInstance().isConnected()) {
            String string = this$0.getString(C0775R.string.ble_glass_18);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        this$0.showRestartDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadViews$lambda$10$lambda$5(YourGlassActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!BleOperateManager.getInstance().isConnected()) {
            String string = this$0.getString(C0775R.string.ble_glass_18);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        this$0.showRestFactoryDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadViews$lambda$10$lambda$6(YourGlassActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!BleOperateManager.getInstance().isConnected()) {
            String string = this$0.getString(C0775R.string.ble_glass_18);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        YourGlassActivity yourGlassActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(yourGlassActivity, (Class<?>) RecordSettingActivity.class);
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
        yourGlassActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadViews$lambda$10$lambda$7(YourGlassActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!BleOperateManager.getInstance().isConnected()) {
            String string = this$0.getString(C0775R.string.ble_glass_18);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        YourGlassActivity yourGlassActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(yourGlassActivity, (Class<?>) DeviceAboutActivity.class);
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
        yourGlassActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadViews$lambda$10$lambda$9(YourGlassActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!BleOperateManager.getInstance().isConnected()) {
            String string = this$0.getString(C0775R.string.ble_glass_18);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
        } else if (compoundButton.isPressed()) {
            LargeDataHandler.getInstance().wearCheck(true, z, this$0.listener);
        }
    }

    private final void showRestartDialog() {
        CommonDialog.Builder builder = new CommonDialog.Builder();
        String string = getString(C0775R.string.ble_glass_10);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        CommonDialog.Builder builderTitleMarginTop = builder.setTitle(string).setContent("").titleMarginTop(GlobalKt.getDp((Number) 65));
        String string2 = getString(C0775R.string.h_glass_confirm);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        CommonDialog.Builder confirmMessage = builderTitleMarginTop.setConfirmMessage(string2);
        String string3 = getString(C0775R.string.h_glass_cancel);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        CommonDialog commonDialogBuild = confirmMessage.setCancelMessage(string3).build();
        commonDialogBuild.show(getSupportFragmentManager(), "showRestartDialog");
        commonDialogBuild.setOnConfirmListener(C11361.INSTANCE);
    }

    /* compiled from: YourGlassActivity.kt */
    @Metadata(m606d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m607d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    /* renamed from: com.glasssutdio.wear.setting.YourGlassActivity$showRestartDialog$1 */
    static final class C11361 extends Lambda implements Function1<View, Unit> {
        public static final C11361 INSTANCE = new C11361();

        C11361() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(int i, GlassModelControlResponse glassModelControlResponse) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(View it) {
            Intrinsics.checkNotNullParameter(it, "it");
            LargeDataHandler.getInstance().glassesControl(new byte[]{2, 1, 14}, new ILargeDataResponse() { // from class: com.glasssutdio.wear.setting.YourGlassActivity$showRestartDialog$1$$ExternalSyntheticLambda0
                @Override // com.oudmon.ble.base.communication.ILargeDataResponse
                public final void parseData(int i, BaseResponse baseResponse) {
                    YourGlassActivity.C11361.invoke$lambda$0(i, (GlassModelControlResponse) baseResponse);
                }
            });
        }
    }

    private final void showRestFactoryDialog() {
        CommonDialog.Builder builder = new CommonDialog.Builder();
        String string = getString(C0775R.string.ble_glass_8);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        CommonDialog.Builder title = builder.setTitle(string);
        String string2 = getString(C0775R.string.ble_glass_9);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        CommonDialog.Builder content = title.setContent(string2);
        String string3 = getString(C0775R.string.h_glass_confirm);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        CommonDialog.Builder confirmMessage = content.setConfirmMessage(string3);
        String string4 = getString(C0775R.string.h_glass_cancel);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        CommonDialog commonDialogBuild = confirmMessage.setCancelMessage(string4).build();
        commonDialogBuild.show(getSupportFragmentManager(), "showRestFactoryDialog");
        commonDialogBuild.setOnConfirmListener(C11351.INSTANCE);
    }

    /* compiled from: YourGlassActivity.kt */
    @Metadata(m606d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m607d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    /* renamed from: com.glasssutdio.wear.setting.YourGlassActivity$showRestFactoryDialog$1 */
    static final class C11351 extends Lambda implements Function1<View, Unit> {
        public static final C11351 INSTANCE = new C11351();

        C11351() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(int i, GlassModelControlResponse glassModelControlResponse) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(View it) {
            Intrinsics.checkNotNullParameter(it, "it");
            LargeDataHandler.getInstance().glassesControl(new byte[]{2, 1, 10}, new ILargeDataResponse() { // from class: com.glasssutdio.wear.setting.YourGlassActivity$showRestFactoryDialog$1$$ExternalSyntheticLambda0
                @Override // com.oudmon.ble.base.communication.ILargeDataResponse
                public final void parseData(int i, BaseResponse baseResponse) {
                    YourGlassActivity.C11351.invoke$lambda$0(i, (GlassModelControlResponse) baseResponse);
                }
            });
        }
    }

    private final void showUnbindDialog() {
        CommonDialog.Builder builder = new CommonDialog.Builder();
        String string = getString(C0775R.string.ble_glass_5);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        CommonDialog.Builder title = builder.setTitle(string);
        String string2 = getString(C0775R.string.ble_glass_6);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        CommonDialog.Builder content = title.setContent(string2);
        String string3 = getString(C0775R.string.ble_glass_7);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        CommonDialog.Builder confirmMessage = content.setConfirmMessage(string3);
        String string4 = getString(C0775R.string.h_glass_cancel);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        CommonDialog commonDialogBuild = confirmMessage.setCancelMessage(string4).build();
        commonDialogBuild.show(getSupportFragmentManager(), "showUnbindDialog");
        commonDialogBuild.setOnConfirmListener(new Function1<View, Unit>() { // from class: com.glasssutdio.wear.setting.YourGlassActivity.showUnbindDialog.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
                DeviceManager.getInstance().reSet();
                UserConfig.INSTANCE.getInstance().setDeviceName("");
                UserConfig.INSTANCE.getInstance().setDeviceAddress("");
                UserConfig.INSTANCE.getInstance().setDeviceBind(false);
                UserConfig.INSTANCE.getInstance().setUniqueIdHw("");
                UserConfig.INSTANCE.getInstance().setShowedDeviceGuided(false);
                BleOperateManager.getInstance().unBindDevice();
                ThreadExtKt.ktxRunOnUiDelay(YourGlassActivity.this, 2000L, new Function1<YourGlassActivity, Unit>() { // from class: com.glasssutdio.wear.setting.YourGlassActivity.showUnbindDialog.1.1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(YourGlassActivity yourGlassActivity) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
                        invoke2(yourGlassActivity);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(YourGlassActivity ktxRunOnUiDelay) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
                        Intrinsics.checkNotNullParameter(ktxRunOnUiDelay, "$this$ktxRunOnUiDelay");
                        BleScannerHelper.getInstance().removeSystemBle();
                        BleScannerHelper.getInstance().removeSystemBle(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
                    }
                });
                EventBus.getDefault().post(new EventType(8));
                YourGlassActivity.this.finish();
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        ActivityYourGlassBinding activityYourGlassBinding = null;
        if (BleOperateManager.getInstance().isConnected()) {
            ActivityYourGlassBinding activityYourGlassBinding2 = this.binding;
            if (activityYourGlassBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityYourGlassBinding2 = null;
            }
            activityYourGlassBinding2.tvBleStatus.setText(getString(C0775R.string.h_glass_110));
            ActivityYourGlassBinding activityYourGlassBinding3 = this.binding;
            if (activityYourGlassBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityYourGlassBinding = activityYourGlassBinding3;
            }
            activityYourGlassBinding.bleImageConnected.setImageResource(C0775R.mipmap.ble_connected_status);
        } else {
            ActivityYourGlassBinding activityYourGlassBinding4 = this.binding;
            if (activityYourGlassBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityYourGlassBinding4 = null;
            }
            activityYourGlassBinding4.bleImageConnected.setImageResource(C0775R.mipmap.ble_disconnect_status);
            ActivityYourGlassBinding activityYourGlassBinding5 = this.binding;
            if (activityYourGlassBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityYourGlassBinding = activityYourGlassBinding5;
            }
            activityYourGlassBinding.tvBleStatus.setText(getString(C0775R.string.h_glass_111));
        }
        connectedChangeRefreshUI(BleOperateManager.getInstance().isConnected());
    }

    public final void connectedChangeRefreshUI(boolean connected) {
        ActivityYourGlassBinding activityYourGlassBinding = this.binding;
        if (activityYourGlassBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityYourGlassBinding = null;
        }
        ViewKt.goneOrVisible(activityYourGlassBinding.tvDeviceFirmware, connected);
        ViewKt.goneOrVisible(activityYourGlassBinding.tvDeviceFirmwareWifi, connected);
        ViewKt.goneOrVisible(activityYourGlassBinding.batteryView, connected);
        ViewKt.goneOrVisible(activityYourGlassBinding.textDeviceBattery, connected);
        YourGlassActivity yourGlassActivity = this;
        int color = ContextCompat.getColor(yourGlassActivity, C0775R.color.g_line_color);
        activityYourGlassBinding.gsc1.setEnabled(connected);
        if (!connected) {
            activityYourGlassBinding.gsc1.setChecked(false);
        }
        activityYourGlassBinding.tv1.setTextColor(connected ? ContextCompat.getColor(yourGlassActivity, C0775R.color.g_color_common_1) : color);
        activityYourGlassBinding.tv1Tip.setTextColor(connected ? ContextCompat.getColor(yourGlassActivity, C0775R.color.g_color_7) : color);
        activityYourGlassBinding.tvVideoSetting.setTextColor(connected ? ContextCompat.getColor(yourGlassActivity, C0775R.color.g_color_6) : color);
        activityYourGlassBinding.tvOta.setTextColor(connected ? ContextCompat.getColor(yourGlassActivity, C0775R.color.g_color_6) : color);
        activityYourGlassBinding.tvRestart.setTextColor(connected ? ContextCompat.getColor(yourGlassActivity, C0775R.color.g_color_6) : color);
        activityYourGlassBinding.tvReset.setTextColor(connected ? ContextCompat.getColor(yourGlassActivity, C0775R.color.g_color_6) : color);
        TextView textView = activityYourGlassBinding.tvAbout;
        if (connected) {
            color = ContextCompat.getColor(yourGlassActivity, C0775R.color.g_color_6);
        }
        textView.setTextColor(color);
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BusEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        ActivityYourGlassBinding activityYourGlassBinding = null;
        if (messageEvent instanceof BluetoothEvent) {
            BluetoothEvent bluetoothEvent = (BluetoothEvent) messageEvent;
            XLog.m136i(Boolean.valueOf(!bluetoothEvent.getConnect()));
            if (!bluetoothEvent.getConnect()) {
                ActivityYourGlassBinding activityYourGlassBinding2 = this.binding;
                if (activityYourGlassBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityYourGlassBinding2 = null;
                }
                activityYourGlassBinding2.tvBleStatus.setText(getString(C0775R.string.h_glass_111));
                ActivityYourGlassBinding activityYourGlassBinding3 = this.binding;
                if (activityYourGlassBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityYourGlassBinding = activityYourGlassBinding3;
                }
                activityYourGlassBinding.bleImageConnected.setImageResource(C0775R.mipmap.ble_disconnect_status);
            } else {
                loadViews();
                ActivityYourGlassBinding activityYourGlassBinding4 = this.binding;
                if (activityYourGlassBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityYourGlassBinding4 = null;
                }
                activityYourGlassBinding4.tvBleStatus.setText(getString(C0775R.string.h_glass_110));
                ActivityYourGlassBinding activityYourGlassBinding5 = this.binding;
                if (activityYourGlassBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityYourGlassBinding = activityYourGlassBinding5;
                }
                activityYourGlassBinding.bleImageConnected.setImageResource(C0775R.mipmap.ble_connected_status);
            }
            connectedChangeRefreshUI(bluetoothEvent.getConnect());
            return;
        }
        if (messageEvent instanceof GlassesBatteryUpdateEvent) {
            ActivityYourGlassBinding activityYourGlassBinding6 = this.binding;
            if (activityYourGlassBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityYourGlassBinding6 = null;
            }
            activityYourGlassBinding6.batteryView.setBatteryPercentage(UserConfig.INSTANCE.getInstance().getBattery());
            ActivityYourGlassBinding activityYourGlassBinding7 = this.binding;
            if (activityYourGlassBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityYourGlassBinding7 = null;
            }
            activityYourGlassBinding7.textDeviceBattery.setText(new StringBuilder().append(UserConfig.INSTANCE.getInstance().getBattery()).append('%').toString());
            ActivityYourGlassBinding activityYourGlassBinding8 = this.binding;
            if (activityYourGlassBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityYourGlassBinding8 = null;
            }
            activityYourGlassBinding8.batteryView.isLowBattery(UserConfig.INSTANCE.getInstance().getLowBattery());
            ActivityYourGlassBinding activityYourGlassBinding9 = this.binding;
            if (activityYourGlassBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityYourGlassBinding9 = null;
            }
            activityYourGlassBinding9.batteryView.isCharging(((GlassesBatteryUpdateEvent) messageEvent).getChanging());
            if (UserConfig.INSTANCE.getInstance().getLowBattery()) {
                ActivityYourGlassBinding activityYourGlassBinding10 = this.binding;
                if (activityYourGlassBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityYourGlassBinding = activityYourGlassBinding10;
                }
                activityYourGlassBinding.textDeviceBattery.setTextColor(ContextCompat.getColor(this, C0775R.color.color_FF0303));
                return;
            }
            ActivityYourGlassBinding activityYourGlassBinding11 = this.binding;
            if (activityYourGlassBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityYourGlassBinding = activityYourGlassBinding11;
            }
            activityYourGlassBinding.textDeviceBattery.setTextColor(ContextCompat.getColor(this, C0775R.color.g_color_6));
            return;
        }
        if (messageEvent instanceof EventType) {
            EventType eventType = (EventType) messageEvent;
            if (eventType.getType() == 11 || eventType.getType() == 12) {
                this.otaSuccess++;
                refreshOtaSuccess();
            }
        }
    }

    private final void refreshOtaSuccess() {
        ActivityYourGlassBinding activityYourGlassBinding = this.binding;
        if (activityYourGlassBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityYourGlassBinding = null;
        }
        String fmVersionWifi = UserConfig.INSTANCE.getInstance().getFmVersionWifi();
        List listSplit$default = StringsKt.split$default((CharSequence) fmVersionWifi, new String[]{"_"}, false, 0, 6, (Object) null);
        if (listSplit$default.size() >= 2) {
            fmVersionWifi = (String) listSplit$default.get(2);
        }
        activityYourGlassBinding.tvDeviceFirmwareWifi.setText(fmVersionWifi);
        String fmVersion = UserConfig.INSTANCE.getInstance().getFmVersion();
        List listSplit$default2 = StringsKt.split$default((CharSequence) fmVersion, new String[]{"_"}, false, 0, 6, (Object) null);
        if (listSplit$default2.size() >= 2) {
            fmVersion = (String) listSplit$default2.get(1);
        }
        activityYourGlassBinding.tvDeviceFirmware.setText(fmVersion);
        if (this.otaSuccess >= this.otaCount) {
            ViewKt.gone(activityYourGlassBinding.imageOta1);
        }
    }

    /* compiled from: YourGlassActivity.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.setting.YourGlassActivity$checkOta$1", m620f = "YourGlassActivity.kt", m621i = {0}, m622l = {HttpStatus.SC_REQUEST_TOO_LONG, 416}, m623m = "invokeSuspend", m624n = {"$this$launch"}, m625s = {"L$0"})
    /* renamed from: com.glasssutdio.wear.setting.YourGlassActivity$checkOta$1 */
    static final class C11331 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $fmVersion;
        final /* synthetic */ String $hwName;
        final /* synthetic */ boolean $wifiNotBle;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ YourGlassActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C11331(String str, String str2, YourGlassActivity yourGlassActivity, boolean z, Continuation<? super C11331> continuation) {
            super(2, continuation);
            this.$hwName = str;
            this.$fmVersion = str2;
            this.this$0 = yourGlassActivity;
            this.$wifiNotBle = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C11331 c11331 = new C11331(this.$hwName, this.$fmVersion, this.this$0, this.$wifiNotBle, continuation);
            c11331.L$0 = obj;
            return c11331;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C11331) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = (CoroutineScope) this.L$0;
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = OTADepository.INSTANCE.getGetInstance().checkOtaFromServer(this.$hwName, this.$fmVersion, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            final YourGlassActivity yourGlassActivity = this.this$0;
            final boolean z = this.$wifiNotBle;
            this.L$0 = null;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.glasssutdio.wear.setting.YourGlassActivity.checkOta.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<FirmwareOtaResp>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<FirmwareOtaResp> netState, Continuation<? super Unit> continuation) {
                    if (netState.getRetCode() == 0) {
                        yourGlassActivity.otaCount++;
                        yourGlassActivity.otaClick = true;
                        CoroutineScope coroutineScope2 = coroutineScope;
                        final YourGlassActivity yourGlassActivity2 = yourGlassActivity;
                        ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.glasssutdio.wear.setting.YourGlassActivity.checkOta.1.1.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                                invoke2(coroutineScope3);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(CoroutineScope ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                ActivityYourGlassBinding activityYourGlassBinding = yourGlassActivity2.binding;
                                if (activityYourGlassBinding == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityYourGlassBinding = null;
                                }
                                ViewKt.visible(activityYourGlassBinding.imageOta1);
                            }
                        });
                    } else if (z) {
                        yourGlassActivity.checkOta(UserConfig.INSTANCE.getInstance().getHwVersion(), UserConfig.INSTANCE.getInstance().getFmVersion(), false);
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkOta(String hwName, String fmVersion, boolean wifiNotBle) {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C11331(hwName, fmVersion, this, wifiNotBle, null), 3, null);
    }
}
