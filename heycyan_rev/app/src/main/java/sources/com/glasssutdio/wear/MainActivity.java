package com.glasssutdio.wear;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentTransaction;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.MainActivity;
import com.glasssutdio.wear.MainActivity.MyDeviceNotifyListener;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.ThreadExtKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.bean.Req.CheckVersionReq;
import com.glasssutdio.wear.all.bean.Req.UpdateUserLocationReq;
import com.glasssutdio.wear.all.dialog.CommonDialog;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.ActivityHelper;
import com.glasssutdio.wear.all.utils.AppstoreUtils;
import com.glasssutdio.wear.all.utils.DateUtil;
import com.glasssutdio.wear.all.utils.GFileUtilKt;
import com.glasssutdio.wear.all.utils.MoshiUtilsKt;
import com.glasssutdio.wear.all.utils.NetWorkUtils;
import com.glasssutdio.wear.all.utils.NotificationUtils;
import com.glasssutdio.wear.all.utils.bar.StatusBarUtil;
import com.glasssutdio.wear.api.NetState;
import com.glasssutdio.wear.api.SSEHandler;
import com.glasssutdio.wear.bus.AiHistoryEditEvent;
import com.glasssutdio.wear.bus.AlbumEditEvent;
import com.glasssutdio.wear.bus.BluetoothEvent;
import com.glasssutdio.wear.bus.BusEvent;
import com.glasssutdio.wear.bus.EventType;
import com.glasssutdio.wear.bus.GlassesBatteryUpdateEvent;
import com.glasssutdio.wear.bus.HomeTabSelectEvent;
import com.glasssutdio.wear.databinding.ActivityMainBinding;
import com.glasssutdio.wear.depository.AiChatDepository;
import com.glasssutdio.wear.depository.SettingDepository;
import com.glasssutdio.wear.home.AiFragment;
import com.glasssutdio.wear.home.HomeFragment;
import com.glasssutdio.wear.home.PictureFragment;
import com.glasssutdio.wear.home.SettingFragment;
import com.glasssutdio.wear.home.bean.AppUpgradeModel;
import com.glasssutdio.wear.home.viewmodel.MainActivityVM;
import com.glasssutdio.wear.manager.GlassBaseActivity;
import com.glasssutdio.wear.ota.OTAActivity;
import com.glasssutdio.wear.p003ai.base64.CustomBase64Encoder;
import com.glasssutdio.wear.p003ai.spark.AudioTrackManager;
import com.glasssutdio.wear.p003ai.spark.SparkChainRecognizer;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import com.hjq.permissions.Permission;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.DeviceManager;
import com.oudmon.ble.base.communication.ILargeDataImageResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.resp.GlassesDeviceNotifyListener;
import com.oudmon.ble.base.communication.bigData.resp.GlassesDeviceNotifyRsp;
import com.oudmon.ble.base.scan.BleScannerHelper;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
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
import kotlin.ranges.RangesKt;
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

/* compiled from: MainActivity.kt */
@Metadata(m606d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001NB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020\"H\u0002J\b\u0010&\u001a\u00020\"H\u0002J\b\u0010'\u001a\u00020\"H\u0002J\u001a\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+H\u0002J\u0010\u0010-\u001a\u00020\"2\u0006\u0010.\u001a\u00020\u0010H\u0002J\u0010\u0010/\u001a\u00020\"2\u0006\u00100\u001a\u000201H\u0002J\b\u00102\u001a\u00020\"H\u0002J\b\u00103\u001a\u00020\u0010H\u0016J\b\u00104\u001a\u00020\"H\u0002J\u0012\u00105\u001a\u00020\"2\b\u00106\u001a\u0004\u0018\u000107H\u0014J\u001a\u00108\u001a\u00020\u00102\u0006\u00109\u001a\u00020\u001e2\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\u0010\u0010<\u001a\u00020\"2\u0006\u0010=\u001a\u00020>H\u0017J\b\u0010?\u001a\u00020\"H\u0014J\u0010\u0010@\u001a\u00020\"2\u0006\u0010A\u001a\u00020BH\u0003J\b\u0010C\u001a\u00020\"H\u0002J\u0010\u0010D\u001a\u00020\"2\u0006\u0010E\u001a\u00020FH\u0002J\u0012\u0010G\u001a\u00020\"2\b\b\u0002\u0010H\u001a\u00020\u0010H\u0002J\b\u0010I\u001a\u00020\"H\u0002J\u0010\u0010J\u001a\u00020\"2\u0006\u0010E\u001a\u00020\u001eH\u0002J.\u0010K\u001a\u00020\"*\u00020L2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+2\b\u0010M\u001a\u0004\u0018\u00010)2\u0006\u0010.\u001a\u00020\u0010H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001f\u0010\u0007\u001a\u00060\bR\u00020\u00008BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\f\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006O"}, m607d2 = {"Lcom/glasssutdio/wear/MainActivity;", "Lcom/glasssutdio/wear/manager/GlassBaseActivity;", "()V", "ai", "Lcom/glasssutdio/wear/home/AiFragment;", "binding", "Lcom/glasssutdio/wear/databinding/ActivityMainBinding;", "deviceNotifyListener", "Lcom/glasssutdio/wear/MainActivity$MyDeviceNotifyListener;", "getDeviceNotifyListener", "()Lcom/glasssutdio/wear/MainActivity$MyDeviceNotifyListener;", "deviceNotifyListener$delegate", "Lkotlin/Lazy;", "home", "Lcom/glasssutdio/wear/home/HomeFragment;", "isSelectAll", "", "()Z", "setSelectAll", "(Z)V", "mViewModel", "Lcom/glasssutdio/wear/home/viewmodel/MainActivityVM;", "getMViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/MainActivityVM;", "mViewModel$delegate", "picture", "Lcom/glasssutdio/wear/home/PictureFragment;", "setting", "Lcom/glasssutdio/wear/home/SettingFragment;", "tabIndex", "", "touchTime", "", "appCheckUpgrade", "", "model", "Lcom/glasssutdio/wear/home/bean/AppUpgradeModel;", "clearAllSelected", "deviceScanConfig", "deviceUnbindEvent", "getCityName", "Landroid/location/Address;", "lat", "", "lng", "getLocation", "requestAi", "hideFragments", "transaction", "Landroidx/fragment/app/FragmentTransaction;", "initData", "needStatusBarPadding", "observer", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onKeyDown", "keyCode", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "onMessageEvent", "messageEvent", "Lcom/glasssutdio/wear/bus/BusEvent;", "onResume", "openNotificationSettings", "context", "Landroid/content/Context;", "registerOutDeviceListener", "setTabSelection", "index", "Landroid/view/View;", "showAppUpgradeDialog", "isForce", "startViews", "updateTabSelection", "updateLocationUI", "Landroid/app/Activity;", "address", "MyDeviceNotifyListener", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class MainActivity extends GlassBaseActivity {
    private AiFragment ai;
    private ActivityMainBinding binding;

    /* renamed from: deviceNotifyListener$delegate, reason: from kotlin metadata */
    private final Lazy deviceNotifyListener;
    private HomeFragment home;
    private boolean isSelectAll;

    /* renamed from: mViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mViewModel;
    private PictureFragment picture;
    private SettingFragment setting;
    private int tabIndex;
    private long touchTime;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startViews$lambda$1(View view) {
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity
    public boolean needStatusBarPadding() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MainActivity() {
        final MainActivity mainActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewModel = LazyKt.lazy(new Function0<MainActivityVM>() { // from class: com.glasssutdio.wear.MainActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.MainActivityVM] */
            @Override // kotlin.jvm.functions.Function0
            public final MainActivityVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(mainActivity, Reflection.getOrCreateKotlinClass(MainActivityVM.class), qualifier, objArr);
            }
        });
        this.deviceNotifyListener = LazyKt.lazy(new Function0<MyDeviceNotifyListener>() { // from class: com.glasssutdio.wear.MainActivity$deviceNotifyListener$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MainActivity.MyDeviceNotifyListener invoke() {
                return this.this$0.new MyDeviceNotifyListener();
            }
        });
    }

    private final MainActivityVM getMViewModel() {
        return (MainActivityVM) this.mViewModel.getValue();
    }

    /* renamed from: isSelectAll, reason: from getter */
    public final boolean getIsSelectAll() {
        return this.isSelectAll;
    }

    public final void setSelectAll(boolean z) {
        this.isSelectAll = z;
    }

    private final MyDeviceNotifyListener getDeviceNotifyListener() {
        return (MyDeviceNotifyListener) this.deviceNotifyListener.getValue();
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBindingInflate = ActivityMainBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityMainBindingInflate, "inflate(...)");
        this.binding = activityMainBindingInflate;
        if (activityMainBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBindingInflate = null;
        }
        setContentView(activityMainBindingInflate.getRoot());
        ActivityHelper.INSTANCE.finishAllActivitiesExceptNewest();
        startViews();
        observer();
        initData();
        MainActivity mainActivity = this;
        StatusBarUtil.setTranslucentStatus(mainActivity);
        StatusBarUtil.setRootViewFitsSystemWindows(mainActivity, false);
        StatusBarUtil.setStatusBarDarkTheme(mainActivity, false);
    }

    private final void observer() {
        getMViewModel().getCheckVersionLD().observe(this, new MainActivity$sam$androidx_lifecycle_Observer$0(new Function1<AppUpgradeModel, Unit>() { // from class: com.glasssutdio.wear.MainActivity$observer$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AppUpgradeModel appUpgradeModel) {
                invoke2(appUpgradeModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AppUpgradeModel appUpgradeModel) {
                MainActivity mainActivity = this.this$0;
                Intrinsics.checkNotNull(appUpgradeModel);
                mainActivity.appCheckUpgrade(appUpgradeModel);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void appCheckUpgrade(AppUpgradeModel model) {
        UserConfig.INSTANCE.getInstance().setHasNewVersion(model.getUpgrade() != 0);
        if (model.getUpgrade() == 1) {
            showAppUpgradeDialog(false);
            return;
        }
        if (model.getUpgrade() == 2) {
            long lastShowAppUpgradeTimeStamp = UserConfig.INSTANCE.getInstance().getLastShowAppUpgradeTimeStamp();
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (DateUtil.isSameDay(jCurrentTimeMillis, lastShowAppUpgradeTimeStamp)) {
                return;
            }
            UserConfig.INSTANCE.getInstance().setLastShowAppUpgradeTimeStamp(jCurrentTimeMillis);
            showAppUpgradeDialog(false);
            return;
        }
        if (model.getUpgrade() == 3) {
            showAppUpgradeDialog(true);
        }
    }

    static /* synthetic */ void showAppUpgradeDialog$default(MainActivity mainActivity, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        mainActivity.showAppUpgradeDialog(z);
    }

    private final void showAppUpgradeDialog(boolean isForce) {
        CommonDialog.Builder builder = new CommonDialog.Builder();
        String string = getString(C0775R.string.version_glass_7);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        CommonDialog.Builder title = builder.setTitle(string);
        String string2 = getString(C0775R.string.version_glass_8);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        CommonDialog.Builder content = title.setContent(string2);
        String string3 = getString(C0775R.string.version_glass_9);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        CommonDialog.Builder confirmMessage = content.setConfirmMessage(string3);
        String string4 = getString(C0775R.string.version_glass_10);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        CommonDialog commonDialogBuild = confirmMessage.setCancelMessage(string4).hideCancelButton(isForce).isCancelable(!isForce).isCanceledOnTouchOutside(!isForce).build();
        commonDialogBuild.setOnConfirmListener(new Function1<View, Unit>() { // from class: com.glasssutdio.wear.MainActivity.showAppUpgradeDialog.1
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
                AppstoreUtils appstoreUtils = AppstoreUtils.INSTANCE;
                MainActivity mainActivity = MainActivity.this;
                appstoreUtils.openAppStore(mainActivity, GlobalKt.getPackageName(mainActivity));
            }
        });
        commonDialogBuild.show(getSupportFragmentManager(), "showAppUpgradeDialog");
    }

    private final void initData() {
        MainActivityVM mViewModel = getMViewModel();
        String string = getString(C0775R.string.app_name);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        MainActivity mainActivity = this;
        mViewModel.appLastVersion(new CheckVersionReq(string, GlobalKt.getVersionCode(mainActivity), GlobalKt.getVersionName(mainActivity), 0, 8, null));
    }

    private final void startViews() {
        if (UserConfig.INSTANCE.getInstance().getAiIsSystemLanguage()) {
            UserConfig companion = UserConfig.INSTANCE.getInstance();
            String language = Locale.getDefault().getLanguage();
            Intrinsics.checkNotNullExpressionValue(language, "getLanguage(...)");
            companion.setAiLanguageCode(language);
            XLog.m137i(UserConfig.INSTANCE.getInstance().getAiLanguageCode());
        }
        View[] viewArr = new View[5];
        ActivityMainBinding activityMainBinding = this.binding;
        if (activityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding = null;
        }
        viewArr[0] = activityMainBinding.imageTab0;
        ActivityMainBinding activityMainBinding2 = this.binding;
        if (activityMainBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding2 = null;
        }
        viewArr[1] = activityMainBinding2.imageTab1;
        ActivityMainBinding activityMainBinding3 = this.binding;
        if (activityMainBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding3 = null;
        }
        viewArr[2] = activityMainBinding3.imageTab2;
        ActivityMainBinding activityMainBinding4 = this.binding;
        if (activityMainBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding4 = null;
        }
        viewArr[3] = activityMainBinding4.imageTab3;
        ActivityMainBinding activityMainBinding5 = this.binding;
        if (activityMainBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding5 = null;
        }
        viewArr[4] = activityMainBinding5.tabView;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.glasssutdio.wear.MainActivity.startViews.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View setOnClickListener) {
                Intrinsics.checkNotNullParameter(setOnClickListener, "$this$setOnClickListener");
                ActivityMainBinding activityMainBinding6 = MainActivity.this.binding;
                ActivityMainBinding activityMainBinding7 = null;
                if (activityMainBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMainBinding6 = null;
                }
                if (!Intrinsics.areEqual(setOnClickListener, activityMainBinding6.imageTab0)) {
                    ActivityMainBinding activityMainBinding8 = MainActivity.this.binding;
                    if (activityMainBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMainBinding8 = null;
                    }
                    if (!Intrinsics.areEqual(setOnClickListener, activityMainBinding8.imageTab1)) {
                        ActivityMainBinding activityMainBinding9 = MainActivity.this.binding;
                        if (activityMainBinding9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMainBinding9 = null;
                        }
                        if (!Intrinsics.areEqual(setOnClickListener, activityMainBinding9.imageTab2)) {
                            ActivityMainBinding activityMainBinding10 = MainActivity.this.binding;
                            if (activityMainBinding10 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMainBinding10 = null;
                            }
                            if (!Intrinsics.areEqual(setOnClickListener, activityMainBinding10.imageTab3)) {
                                ActivityMainBinding activityMainBinding11 = MainActivity.this.binding;
                                if (activityMainBinding11 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                } else {
                                    activityMainBinding7 = activityMainBinding11;
                                }
                                Intrinsics.areEqual(setOnClickListener, activityMainBinding7.tabView);
                                return;
                            }
                            MainActivity mainActivity = MainActivity.this;
                            ActivityMainBinding activityMainBinding12 = mainActivity.binding;
                            if (activityMainBinding12 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activityMainBinding7 = activityMainBinding12;
                            }
                            ImageView imageTab3 = activityMainBinding7.imageTab3;
                            Intrinsics.checkNotNullExpressionValue(imageTab3, "imageTab3");
                            mainActivity.setTabSelection(imageTab3);
                            EventBus.getDefault().post(new EventType(6));
                            return;
                        }
                        MainActivity mainActivity2 = MainActivity.this;
                        ActivityMainBinding activityMainBinding13 = mainActivity2.binding;
                        if (activityMainBinding13 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityMainBinding7 = activityMainBinding13;
                        }
                        ImageView imageTab2 = activityMainBinding7.imageTab2;
                        Intrinsics.checkNotNullExpressionValue(imageTab2, "imageTab2");
                        mainActivity2.setTabSelection(imageTab2);
                        return;
                    }
                    MainActivity mainActivity3 = MainActivity.this;
                    ActivityMainBinding activityMainBinding14 = mainActivity3.binding;
                    if (activityMainBinding14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityMainBinding7 = activityMainBinding14;
                    }
                    ImageView imageTab1 = activityMainBinding7.imageTab1;
                    Intrinsics.checkNotNullExpressionValue(imageTab1, "imageTab1");
                    mainActivity3.setTabSelection(imageTab1);
                    EventBus.getDefault().post(new EventType(4));
                    return;
                }
                MainActivity mainActivity4 = MainActivity.this;
                ActivityMainBinding activityMainBinding15 = mainActivity4.binding;
                if (activityMainBinding15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityMainBinding7 = activityMainBinding15;
                }
                ImageView imageTab0 = activityMainBinding7.imageTab0;
                Intrinsics.checkNotNullExpressionValue(imageTab0, "imageTab0");
                mainActivity4.setTabSelection(imageTab0);
                EventBus.getDefault().post(new EventType(3));
            }
        });
        ActivityMainBinding activityMainBinding6 = this.binding;
        if (activityMainBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding6 = null;
        }
        activityMainBinding6.clsControl.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.MainActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.startViews$lambda$1(view);
            }
        });
        ActivityMainBinding activityMainBinding7 = this.binding;
        if (activityMainBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding7 = null;
        }
        ImageView imageTab0 = activityMainBinding7.imageTab0;
        Intrinsics.checkNotNullExpressionValue(imageTab0, "imageTab0");
        setTabSelection(imageTab0);
        ActivityMainBinding activityMainBinding8 = this.binding;
        if (activityMainBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding8 = null;
        }
        activityMainBinding8.tvCheckAll.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.MainActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.startViews$lambda$2(this.f$0, view);
            }
        });
        ActivityMainBinding activityMainBinding9 = this.binding;
        if (activityMainBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding9 = null;
        }
        activityMainBinding9.tvDelete.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.MainActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.startViews$lambda$3(view);
            }
        });
        if (this.binding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        deviceScanConfig();
        String language2 = Locale.getDefault().getLanguage();
        Intrinsics.checkNotNullExpressionValue(language2, "getLanguage(...)");
        if (StringsKt.startsWith$default(language2, "zh", false, 2, (Object) null)) {
            return;
        }
        getLocation(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startViews$lambda$2(MainActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.m137i("isSelectAll:" + this$0.isSelectAll);
        ActivityMainBinding activityMainBinding = null;
        if (this$0.isSelectAll) {
            ActivityMainBinding activityMainBinding2 = this$0.binding;
            if (activityMainBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMainBinding = activityMainBinding2;
            }
            activityMainBinding.tvCheckAll.setText(this$0.getString(C0775R.string.album_glass_16));
        } else {
            ActivityMainBinding activityMainBinding3 = this$0.binding;
            if (activityMainBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMainBinding = activityMainBinding3;
            }
            activityMainBinding.tvCheckAll.setText(this$0.getString(C0775R.string.album_glass_19));
        }
        this$0.isSelectAll = !this$0.isSelectAll;
        EventBus.getDefault().post(new AiHistoryEditEvent(1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startViews$lambda$3(View view) {
        EventBus.getDefault().post(new AiHistoryEditEvent(2));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        registerOutDeviceListener();
    }

    private final void registerOutDeviceListener() {
        XLog.m137i("----registerOutDeviceListener");
        LargeDataHandler.getInstance().addOutDeviceListener(100, getDeviceNotifyListener());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTabSelection(View index) {
        clearAllSelected();
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNull(fragmentTransactionBeginTransaction);
        hideFragments(fragmentTransactionBeginTransaction);
        ActivityMainBinding activityMainBinding = this.binding;
        ActivityMainBinding activityMainBinding2 = null;
        if (activityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding = null;
        }
        if (Intrinsics.areEqual(index, activityMainBinding.imageTab0)) {
            this.tabIndex = 0;
            ActivityMainBinding activityMainBinding3 = this.binding;
            if (activityMainBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding3 = null;
            }
            activityMainBinding3.imageTab0.setSelected(true);
            ActivityMainBinding activityMainBinding4 = this.binding;
            if (activityMainBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding4 = null;
            }
            activityMainBinding4.tvTab0.setSelected(true);
            ActivityMainBinding activityMainBinding5 = this.binding;
            if (activityMainBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMainBinding2 = activityMainBinding5;
            }
            activityMainBinding2.tvTab0.setEnabled(true);
            HomeFragment homeFragment = this.home;
            if (homeFragment == null) {
                this.home = HomeFragment.INSTANCE.newInstance();
                int i = C0775R.id.homeContainer;
                HomeFragment homeFragment2 = this.home;
                Intrinsics.checkNotNull(homeFragment2);
                fragmentTransactionBeginTransaction.add(i, homeFragment2);
            } else {
                Intrinsics.checkNotNull(homeFragment);
                fragmentTransactionBeginTransaction.show(homeFragment);
            }
        } else {
            ActivityMainBinding activityMainBinding6 = this.binding;
            if (activityMainBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding6 = null;
            }
            if (Intrinsics.areEqual(index, activityMainBinding6.imageTab1)) {
                this.tabIndex = 1;
                ActivityMainBinding activityMainBinding7 = this.binding;
                if (activityMainBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMainBinding7 = null;
                }
                activityMainBinding7.imageTab1.setSelected(true);
                ActivityMainBinding activityMainBinding8 = this.binding;
                if (activityMainBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMainBinding8 = null;
                }
                activityMainBinding8.tvTab1.setSelected(true);
                ActivityMainBinding activityMainBinding9 = this.binding;
                if (activityMainBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityMainBinding2 = activityMainBinding9;
                }
                activityMainBinding2.tvTab1.setEnabled(true);
                AiFragment aiFragment = this.ai;
                if (aiFragment == null) {
                    this.ai = AiFragment.INSTANCE.newInstance();
                    int i2 = C0775R.id.homeContainer;
                    AiFragment aiFragment2 = this.ai;
                    Intrinsics.checkNotNull(aiFragment2);
                    fragmentTransactionBeginTransaction.add(i2, aiFragment2);
                    AiFragment aiFragment3 = this.ai;
                    if (aiFragment3 != null) {
                        aiFragment3.setOnHistoryEditChangeListener(new Function1<Boolean, Unit>() { // from class: com.glasssutdio.wear.MainActivity$setTabSelection$1$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                invoke(bool.booleanValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(boolean z) {
                                ActivityMainBinding activityMainBinding10 = this.this$0.binding;
                                ActivityMainBinding activityMainBinding11 = null;
                                if (activityMainBinding10 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityMainBinding10 = null;
                                }
                                ViewKt.goneOrVisible(activityMainBinding10.clsControl, z);
                                this.this$0.setSelectAll(false);
                                if (z) {
                                    ActivityMainBinding activityMainBinding12 = this.this$0.binding;
                                    if (activityMainBinding12 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    } else {
                                        activityMainBinding11 = activityMainBinding12;
                                    }
                                    activityMainBinding11.tvCheckAll.setText(this.this$0.getString(C0775R.string.album_glass_16));
                                }
                            }
                        });
                    }
                } else {
                    Intrinsics.checkNotNull(aiFragment);
                    fragmentTransactionBeginTransaction.show(aiFragment);
                }
            } else {
                ActivityMainBinding activityMainBinding10 = this.binding;
                if (activityMainBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMainBinding10 = null;
                }
                if (Intrinsics.areEqual(index, activityMainBinding10.imageTab2)) {
                    this.tabIndex = 2;
                    ActivityMainBinding activityMainBinding11 = this.binding;
                    if (activityMainBinding11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMainBinding11 = null;
                    }
                    activityMainBinding11.imageTab2.setSelected(true);
                    ActivityMainBinding activityMainBinding12 = this.binding;
                    if (activityMainBinding12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMainBinding12 = null;
                    }
                    activityMainBinding12.tvTab2.setSelected(true);
                    ActivityMainBinding activityMainBinding13 = this.binding;
                    if (activityMainBinding13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityMainBinding2 = activityMainBinding13;
                    }
                    activityMainBinding2.tvTab2.setEnabled(true);
                    PictureFragment pictureFragment = this.picture;
                    if (pictureFragment == null) {
                        this.picture = PictureFragment.INSTANCE.newInstance();
                        int i3 = C0775R.id.homeContainer;
                        PictureFragment pictureFragment2 = this.picture;
                        Intrinsics.checkNotNull(pictureFragment2);
                        fragmentTransactionBeginTransaction.add(i3, pictureFragment2);
                    } else {
                        Intrinsics.checkNotNull(pictureFragment);
                        fragmentTransactionBeginTransaction.show(pictureFragment);
                    }
                    PictureFragment pictureFragment3 = this.picture;
                    Intrinsics.checkNotNull(pictureFragment3);
                    pictureFragment3.readAlbumCounts();
                } else {
                    ActivityMainBinding activityMainBinding14 = this.binding;
                    if (activityMainBinding14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMainBinding14 = null;
                    }
                    if (Intrinsics.areEqual(index, activityMainBinding14.imageTab3)) {
                        this.tabIndex = 3;
                        ActivityMainBinding activityMainBinding15 = this.binding;
                        if (activityMainBinding15 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMainBinding15 = null;
                        }
                        activityMainBinding15.imageTab3.setSelected(true);
                        ActivityMainBinding activityMainBinding16 = this.binding;
                        if (activityMainBinding16 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMainBinding16 = null;
                        }
                        activityMainBinding16.tvTab3.setSelected(true);
                        ActivityMainBinding activityMainBinding17 = this.binding;
                        if (activityMainBinding17 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityMainBinding2 = activityMainBinding17;
                        }
                        activityMainBinding2.tvTab3.setEnabled(true);
                        SettingFragment settingFragment = this.setting;
                        if (settingFragment == null) {
                            this.setting = SettingFragment.INSTANCE.newInstance();
                            int i4 = C0775R.id.homeContainer;
                            SettingFragment settingFragment2 = this.setting;
                            Intrinsics.checkNotNull(settingFragment2);
                            fragmentTransactionBeginTransaction.add(i4, settingFragment2);
                        } else {
                            Intrinsics.checkNotNull(settingFragment);
                            fragmentTransactionBeginTransaction.show(settingFragment);
                        }
                    } else {
                        this.tabIndex = 0;
                        ActivityMainBinding activityMainBinding18 = this.binding;
                        if (activityMainBinding18 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMainBinding18 = null;
                        }
                        activityMainBinding18.imageTab0.setSelected(true);
                        ActivityMainBinding activityMainBinding19 = this.binding;
                        if (activityMainBinding19 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMainBinding19 = null;
                        }
                        activityMainBinding19.tvTab0.setSelected(true);
                        ActivityMainBinding activityMainBinding20 = this.binding;
                        if (activityMainBinding20 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityMainBinding2 = activityMainBinding20;
                        }
                        activityMainBinding2.tvTab0.setEnabled(true);
                        HomeFragment homeFragment3 = this.home;
                        if (homeFragment3 == null) {
                            this.home = HomeFragment.INSTANCE.newInstance();
                            int i5 = C0775R.id.homeContainer;
                            HomeFragment homeFragment4 = this.home;
                            Intrinsics.checkNotNull(homeFragment4);
                            fragmentTransactionBeginTransaction.add(i5, homeFragment4);
                        } else {
                            Intrinsics.checkNotNull(homeFragment3);
                            fragmentTransactionBeginTransaction.show(homeFragment3);
                        }
                    }
                }
            }
        }
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    private final void clearAllSelected() {
        ActivityMainBinding activityMainBinding = this.binding;
        ActivityMainBinding activityMainBinding2 = null;
        if (activityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding = null;
        }
        activityMainBinding.imageTab0.setSelected(false);
        ActivityMainBinding activityMainBinding3 = this.binding;
        if (activityMainBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding3 = null;
        }
        activityMainBinding3.tvTab0.setSelected(false);
        ActivityMainBinding activityMainBinding4 = this.binding;
        if (activityMainBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding4 = null;
        }
        activityMainBinding4.imageTab1.setSelected(false);
        ActivityMainBinding activityMainBinding5 = this.binding;
        if (activityMainBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding5 = null;
        }
        activityMainBinding5.tvTab1.setSelected(false);
        ActivityMainBinding activityMainBinding6 = this.binding;
        if (activityMainBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding6 = null;
        }
        activityMainBinding6.imageTab2.setSelected(false);
        ActivityMainBinding activityMainBinding7 = this.binding;
        if (activityMainBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding7 = null;
        }
        activityMainBinding7.tvTab2.setSelected(false);
        ActivityMainBinding activityMainBinding8 = this.binding;
        if (activityMainBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding8 = null;
        }
        activityMainBinding8.imageTab3.setSelected(false);
        ActivityMainBinding activityMainBinding9 = this.binding;
        if (activityMainBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMainBinding2 = activityMainBinding9;
        }
        activityMainBinding2.tvTab3.setSelected(false);
    }

    private final void hideFragments(FragmentTransaction transaction) {
        HomeFragment homeFragment = this.home;
        if (homeFragment != null) {
            Intrinsics.checkNotNull(homeFragment);
            transaction.hide(homeFragment);
        }
        AiFragment aiFragment = this.ai;
        if (aiFragment != null) {
            Intrinsics.checkNotNull(aiFragment);
            transaction.hide(aiFragment);
        }
        PictureFragment pictureFragment = this.picture;
        if (pictureFragment != null) {
            Intrinsics.checkNotNull(pictureFragment);
            transaction.hide(pictureFragment);
        }
        SettingFragment settingFragment = this.setting;
        if (settingFragment != null) {
            Intrinsics.checkNotNull(settingFragment);
            transaction.hide(settingFragment);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (4 == keyCode) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - this.touchTime > 2000) {
                String string = getString(C0775R.string.h_glass_325);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                GlobalKt.showToast$default(string, 0, 1, null);
                this.touchTime = jCurrentTimeMillis;
            } else {
                ActivityHelper.INSTANCE.exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BusEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if (messageEvent instanceof BluetoothEvent) {
            BluetoothEvent bluetoothEvent = (BluetoothEvent) messageEvent;
            XLog.m137i("蓝牙状态:->" + (true ^ bluetoothEvent.getConnect()));
            try {
                Object systemService = getSystemService("notification");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
                if (!((NotificationManager) systemService).areNotificationsEnabled()) {
                    openNotificationSettings(this);
                } else {
                    new NotificationUtils(getActivity()).initBleConnectNotification();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!bluetoothEvent.getConnect()) {
                XLog.m137i("设备断开");
                return;
            } else {
                XLog.m137i("设备连接");
                registerOutDeviceListener();
                return;
            }
        }
        if (messageEvent instanceof AlbumEditEvent) {
            try {
                PictureFragment pictureFragment = this.picture;
                if (pictureFragment != null) {
                    pictureFragment.imageEdit(((AlbumEditEvent) messageEvent).getEnable());
                    return;
                }
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        if (messageEvent instanceof EventType) {
            if (((EventType) messageEvent).getType() == 18) {
                getLocation(true);
                return;
            }
            return;
        }
        if (messageEvent instanceof AiHistoryEditEvent) {
            AiHistoryEditEvent aiHistoryEditEvent = (AiHistoryEditEvent) messageEvent;
            ActivityMainBinding activityMainBinding = null;
            if (aiHistoryEditEvent.getType() == 5) {
                XLog.m137i("messageEvent.type == AiHistoryEditEvent.EDIT_TYPE_SELECT_ALL_ADD");
                ActivityMainBinding activityMainBinding2 = this.binding;
                if (activityMainBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityMainBinding = activityMainBinding2;
                }
                activityMainBinding.tvCheckAll.setText(getString(C0775R.string.album_glass_19));
                return;
            }
            if (aiHistoryEditEvent.getType() == 4) {
                ActivityMainBinding activityMainBinding3 = this.binding;
                if (activityMainBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityMainBinding = activityMainBinding3;
                }
                activityMainBinding.tvCheckAll.setText(getString(C0775R.string.album_glass_16));
                return;
            }
            if (aiHistoryEditEvent.getType() == 6) {
                ActivityMainBinding activityMainBinding4 = this.binding;
                if (activityMainBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityMainBinding = activityMainBinding4;
                }
                activityMainBinding.tvCheckAll.setText(getString(C0775R.string.album_glass_16));
                return;
            }
            return;
        }
        if (messageEvent instanceof HomeTabSelectEvent) {
            updateTabSelection(RangesKt.coerceIn(((HomeTabSelectEvent) messageEvent).getIndex(), 0, 3));
        }
    }

    private final void updateTabSelection(int index) {
        ActivityMainBinding activityMainBinding = null;
        if (index == 0) {
            ActivityMainBinding activityMainBinding2 = this.binding;
            if (activityMainBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMainBinding = activityMainBinding2;
            }
            ImageView imageTab0 = activityMainBinding.imageTab0;
            Intrinsics.checkNotNullExpressionValue(imageTab0, "imageTab0");
            setTabSelection(imageTab0);
            return;
        }
        if (index == 1) {
            ActivityMainBinding activityMainBinding3 = this.binding;
            if (activityMainBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMainBinding = activityMainBinding3;
            }
            ImageView imageTab1 = activityMainBinding.imageTab1;
            Intrinsics.checkNotNullExpressionValue(imageTab1, "imageTab1");
            setTabSelection(imageTab1);
            return;
        }
        if (index == 2) {
            ActivityMainBinding activityMainBinding4 = this.binding;
            if (activityMainBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMainBinding = activityMainBinding4;
            }
            ImageView imageTab2 = activityMainBinding.imageTab2;
            Intrinsics.checkNotNullExpressionValue(imageTab2, "imageTab2");
            setTabSelection(imageTab2);
            return;
        }
        if (index != 3) {
            return;
        }
        ActivityMainBinding activityMainBinding5 = this.binding;
        if (activityMainBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMainBinding = activityMainBinding5;
        }
        ImageView imageTab3 = activityMainBinding.imageTab3;
        Intrinsics.checkNotNullExpressionValue(imageTab3, "imageTab3");
        setTabSelection(imageTab3);
    }

    /* compiled from: MainActivity.kt */
    @Metadata(m606d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0017¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/MainActivity$MyDeviceNotifyListener;", "Lcom/oudmon/ble/base/communication/bigData/resp/GlassesDeviceNotifyListener;", "(Lcom/glasssutdio/wear/MainActivity;)V", "parseData", "", "cmdType", "", "response", "Lcom/oudmon/ble/base/communication/bigData/resp/GlassesDeviceNotifyRsp;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class MyDeviceNotifyListener extends GlassesDeviceNotifyListener {
        public MyDeviceNotifyListener() {
        }

        @Override // com.oudmon.ble.base.communication.bigData.resp.GlassesDeviceNotifyListener, com.oudmon.ble.base.communication.ILargeDataResponse
        public void parseData(int cmdType, GlassesDeviceNotifyRsp response) throws Exception {
            Intrinsics.checkNotNullParameter(response, "response");
            if (GlassesWearJavaApplication.getInstance().isOtaUpgrading()) {
                XLog.m137i("升级中");
                return;
            }
            byte b = response.getLoadData()[6];
            if (b == 2) {
                if (response.getLoadData().length > 9 && response.getLoadData()[9] == 2) {
                    AiChatDepository.INSTANCE.getGetInstance().setUserVisionText(GlassApplication.INSTANCE.getGetInstance().getAiLanguageValueByKey(UserConfig.INSTANCE.getInstance().getAiLanguageCode()));
                }
                final String absolutePath = GFileUtilKt.getAlbumDirFile().getAbsolutePath();
                final String str = "Thumb_" + System.currentTimeMillis() + ".jpg";
                LargeDataHandler.getInstance().getPictureThumbnails(new ILargeDataImageResponse() { // from class: com.glasssutdio.wear.MainActivity$MyDeviceNotifyListener$$ExternalSyntheticLambda0
                    @Override // com.oudmon.ble.base.communication.ILargeDataImageResponse
                    public final void parseData(int i, boolean z, byte[] bArr) {
                        MainActivity.MyDeviceNotifyListener.parseData$lambda$0(this.f$0, absolutePath, str, i, z, bArr);
                    }
                });
                return;
            }
            if (b == 3) {
                XLog.m137i("网络状态:" + NetWorkUtils.INSTANCE.isNetworkAvailable(MainActivity.this));
                if (!NetWorkUtils.INSTANCE.isNetworkAvailable(MainActivity.this)) {
                    String string = MainActivity.this.getString(C0775R.string.ai_glass_1);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    GlobalKt.showToast$default(string, 0, 1, null);
                    LargeDataHandler.getInstance().aiVoicePlay(241, null);
                    return;
                }
                if (GlassesWearJavaApplication.getInstance().isTranslateDoing()) {
                    return;
                }
                SparkChainRecognizer.getInstance().setAiLanguage(AiChatDepository.INSTANCE.getGetInstance().switchAsrLanguage(UserConfig.INSTANCE.getInstance().getAiLanguageCode()).getAsrLanguage());
                SSEHandler.INSTANCE.getInstance().stop();
                SparkChainRecognizer.getInstance().voiceType = 0;
                SparkChainRecognizer.getInstance().initData();
                SparkChainRecognizer.getInstance().initStartTTS();
                SparkChainRecognizer.getInstance().setTranslateTo(AiChatDepository.INSTANCE.getGetInstance().switchAsrLanguage(UserConfig.INSTANCE.getInstance().getAiLanguageCode()).getAppLanguage());
                if (response.getLoadData()[7] == 1) {
                    XLog.m137i("ai测试开始说话");
                    SparkChainRecognizer.getInstance().start();
                    return;
                }
                return;
            }
            if (b != 4) {
                if (b == 5) {
                    UserConfig.INSTANCE.getInstance().setBattery(response.getLoadData()[7]);
                    UserConfig companion = UserConfig.INSTANCE.getInstance();
                    byte b2 = response.getLoadData()[7];
                    companion.setLowBattery(1 <= b2 && b2 < 16);
                    EventBus.getDefault().post(new GlassesBatteryUpdateEvent(response.getLoadData()[7], response.getLoadData()[8] == 1));
                    return;
                }
                if (b == 16) {
                    if (response.getLoadData()[7] == 1) {
                        EventBus.getDefault().post(new EventType(17));
                        return;
                    }
                    return;
                }
                if (b == 18) {
                    UserConfig.INSTANCE.getInstance().setVolumeControl("" + ((int) response.getLoadData()[8]) + ',' + ((int) response.getLoadData()[9]) + ',' + ((int) response.getLoadData()[10]) + ',' + ((int) response.getLoadData()[12]) + ',' + ((int) response.getLoadData()[13]) + ',' + ((int) response.getLoadData()[14]) + ',' + ((int) response.getLoadData()[16]) + ',' + ((int) response.getLoadData()[17]) + ',' + ((int) response.getLoadData()[18]) + ',' + ((int) response.getLoadData()[19]));
                    EventBus.getDefault().post(new EventType(19));
                    return;
                }
                switch (b) {
                    case 12:
                        if (response.getLoadData()[7] == 1) {
                            SparkChainRecognizer.getInstance().stopTTS();
                            AiChatDepository.INSTANCE.getGetInstance().cleanRealTimeTTSQueue();
                            AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).restartPlay();
                            SSEHandler.INSTANCE.getInstance().stop();
                            LargeDataHandler.getInstance().aiVoicePlay(3, null);
                            break;
                        }
                        break;
                    case 13:
                        if (response.getLoadData()[7] == 1) {
                            MainActivity.this.deviceUnbindEvent();
                            break;
                        }
                        break;
                    case 14:
                        final MainActivity mainActivity = MainActivity.this;
                        ThreadExtKt.ktxRunOnUi(this, new Function1<MyDeviceNotifyListener, Unit>() { // from class: com.glasssutdio.wear.MainActivity$MyDeviceNotifyListener$parseData$2
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(MainActivity.MyDeviceNotifyListener myDeviceNotifyListener) {
                                invoke2(myDeviceNotifyListener);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(MainActivity.MyDeviceNotifyListener ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                String string2 = mainActivity.getString(C0775R.string.ble_glass_41);
                                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                                GlobalKt.showToast$default(string2, 0, 1, null);
                            }
                        });
                        break;
                }
                return;
            }
            try {
                XLog.m137i(((int) response.getLoadData()[7]) + HelpFormatter.DEFAULT_LONG_OPT_PREFIX + ((int) response.getLoadData()[8]) + "---" + ((int) response.getLoadData()[9]));
                if (MainActivity.this.getActivity() instanceof MainActivity) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("background", 1);
                    if (UserConfig.INSTANCE.getInstance().getBattery() <= 30) {
                        String string2 = MainActivity.this.getString(C0775R.string.ble_glass_21, new Object[]{"30"});
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                        GlobalKt.showToast$default(string2, 0, 1, null);
                        return;
                    }
                    MainActivity mainActivity2 = MainActivity.this;
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intent intent = new Intent(mainActivity2, (Class<?>) OTAActivity.class);
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
                    mainActivity2.startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void parseData$lambda$0(MyDeviceNotifyListener this$0, final String str, final String fileName, int i, final boolean z, final byte[] bArr) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(fileName, "$fileName");
            ThreadExtKt.ktxRunOnBgSingle(this$0, new Function1<MyDeviceNotifyListener, Unit>() { // from class: com.glasssutdio.wear.MainActivity$MyDeviceNotifyListener$parseData$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(MainActivity.MyDeviceNotifyListener myDeviceNotifyListener) throws Throwable {
                    invoke2(myDeviceNotifyListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(MainActivity.MyDeviceNotifyListener ktxRunOnBgSingle) throws Throwable {
                    Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                    byte[] data = bArr;
                    Intrinsics.checkNotNullExpressionValue(data, "$data");
                    String path = str;
                    Intrinsics.checkNotNullExpressionValue(path, "$path");
                    GFileUtilKt.writeToFile1(data, path, fileName);
                    if (z) {
                        SparkChainRecognizer.getInstance().setTranslateTo(AiChatDepository.INSTANCE.getGetInstance().switchAsrLanguage(UserConfig.INSTANCE.getInstance().getAiLanguageCode()).getAppLanguage());
                        AiChatDepository.INSTANCE.getGetInstance().saveChatImageFromGlasses(fileName, str + IOUtils.DIR_SEPARATOR_UNIX + fileName);
                        String strImageToBase64 = CustomBase64Encoder.imageToBase64(str + IOUtils.DIR_SEPARATOR_UNIX + fileName);
                        Intrinsics.checkNotNullExpressionValue(strImageToBase64, "imageToBase64(...)");
                        AiChatDepository.INSTANCE.getGetInstance().chatGpt(2, "", strImageToBase64);
                    }
                }
            });
        }
    }

    /* compiled from: MainActivity.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.MainActivity$deviceScanConfig$1", m620f = "MainActivity.kt", m621i = {}, m622l = {TypedValues.MotionType.TYPE_EASING, TypedValues.MotionType.TYPE_EASING}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.MainActivity$deviceScanConfig$1 */
    static final class C07691 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C07691(Continuation<? super C07691> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C07691(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07691) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = SettingDepository.INSTANCE.getGetInstance().getDeviceScanConfigFromServer(GlobalKt.getAppName(), this);
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
                ResultKt.throwOnFailure(obj);
            }
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.glasssutdio.wear.MainActivity.deviceScanConfig.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<String>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<String> netState, Continuation<? super Unit> continuation) {
                    try {
                        String strValueOf = String.valueOf(netState.isSuccess());
                        XLog.m137i(strValueOf);
                        if (strValueOf.length() > 0 && netState.getRetCode() == 0 && !Intrinsics.areEqual(strValueOf, "null")) {
                            UserConfig.INSTANCE.getInstance().setScanKeyFilter(strValueOf);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    private final void deviceScanConfig() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C07691(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deviceUnbindEvent() {
        DeviceManager.getInstance().reSet();
        UserConfig.INSTANCE.getInstance().setDeviceName("");
        UserConfig.INSTANCE.getInstance().setDeviceAddress("");
        UserConfig.INSTANCE.getInstance().setUniqueIdHw("");
        UserConfig.INSTANCE.getInstance().setDeviceBind(false);
        UserConfig.INSTANCE.getInstance().setShowedDeviceGuided(false);
        BleOperateManager.getInstance().unBindDevice();
        ThreadExtKt.ktxRunOnUiDelay(this, 2000L, new Function1<MainActivity, Unit>() { // from class: com.glasssutdio.wear.MainActivity.deviceUnbindEvent.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MainActivity mainActivity) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
                invoke2(mainActivity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MainActivity ktxRunOnUiDelay) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
                Intrinsics.checkNotNullParameter(ktxRunOnUiDelay, "$this$ktxRunOnUiDelay");
                BleScannerHelper.getInstance().removeSystemBle();
                BleScannerHelper.getInstance().removeSystemBle(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
            }
        });
        EventBus.getDefault().post(new EventType(8));
    }

    private final void openNotificationSettings(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
        intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
        context.startActivity(intent);
    }

    private final void getLocation(boolean requestAi) {
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient((Activity) this);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(...)");
        if (ActivityCompat.checkSelfPermission(this, Permission.ACCESS_COARSE_LOCATION) != 0) {
            XLog.m137i("没有权限");
            return;
        }
        try {
            Task<Location> lastLocation = fusedLocationProviderClient.getLastLocation();
            final C07711 c07711 = new C07711(requestAi);
            lastLocation.addOnSuccessListener(new OnSuccessListener() { // from class: com.glasssutdio.wear.MainActivity$$ExternalSyntheticLambda3
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    MainActivity.getLocation$lambda$8(c07711, obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* compiled from: MainActivity.kt */
    @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m607d2 = {"<anonymous>", "", "location", "Landroid/location/Location;", "kotlin.jvm.PlatformType", "invoke"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    /* renamed from: com.glasssutdio.wear.MainActivity$getLocation$1 */
    static final class C07711 extends Lambda implements Function1<Location, Unit> {
        final /* synthetic */ boolean $requestAi;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07711(boolean z) {
            super(1);
            this.$requestAi = z;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Location location) {
            invoke2(location);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Location location) {
            if (location != null) {
                if (BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new MainActivity$getLocation$1$1$1(MainActivity.this, location, this.$requestAi, null), 3, null) != null) {
                    return;
                }
            }
            final MainActivity mainActivity = MainActivity.this;
            final boolean z = this.$requestAi;
            XLog.m137i("无法获取位置信息");
            Object systemService = mainActivity.getSystemService("location");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
            LocationManager locationManager = (LocationManager) systemService;
            if (locationManager.isProviderEnabled("gps")) {
                locationManager.requestLocationUpdates("gps", 5000L, 10.0f, new LocationListener() { // from class: com.glasssutdio.wear.MainActivity$getLocation$1$$ExternalSyntheticLambda0
                    @Override // android.location.LocationListener
                    public final void onLocationChanged(Location location2) {
                        MainActivity.C07711.invoke$lambda$3$lambda$1(mainActivity, z, location2);
                    }
                });
            } else {
                locationManager.requestLocationUpdates("network", 5000L, 10.0f, new LocationListener() { // from class: com.glasssutdio.wear.MainActivity$getLocation$1$$ExternalSyntheticLambda1
                    @Override // android.location.LocationListener
                    public final void onLocationChanged(Location location2) {
                        MainActivity.C07711.invoke$lambda$3$lambda$2(mainActivity, z, location2);
                    }
                });
            }
            Unit unit = Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$3$lambda$1(MainActivity this_run, boolean z, Location location) {
            Intrinsics.checkNotNullParameter(this_run, "$this_run");
            Intrinsics.checkNotNullParameter(location, "location");
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new MainActivity$getLocation$1$2$1$1(this_run, location, z, null), 3, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$3$lambda$2(MainActivity this_run, boolean z, Location location) {
            Intrinsics.checkNotNullParameter(this_run, "$this_run");
            Intrinsics.checkNotNullParameter(location, "location");
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new MainActivity$getLocation$1$2$2$1(this_run, location, z, null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getLocation$lambda$8(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Address getCityName(double lat, double lng) throws IOException {
        try {
            List<Address> fromLocation = new Geocoder(this, Locale.getDefault()).getFromLocation(lat, lng, 1);
            if (fromLocation == null) {
                return null;
            }
            Intrinsics.checkNotNull(fromLocation);
            return (Address) CollectionsKt.firstOrNull((List) fromLocation);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateLocationUI(Activity activity, double d, double d2, Address address, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("纬度: " + d + "\n经度: " + d2 + '\n');
        if (address == null || sb.append(StringsKt.trimIndent("\n                国家: " + address.getCountryName() + "\n                城市: " + address.getLocality() + "\n                区县: " + address.getSubLocality() + "\n                街道: " + address.getThoroughfare() + "\n            ")) == null) {
            sb.append("无法解析地址信息");
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C07741(address, d, d2, z, null), 3, null);
        XLog.m137i(string);
    }

    /* compiled from: MainActivity.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.MainActivity$updateLocationUI$1", m620f = "MainActivity.kt", m621i = {}, m622l = {741, 741}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.MainActivity$updateLocationUI$1 */
    static final class C07741 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Address $address;
        final /* synthetic */ double $lat;
        final /* synthetic */ double $lng;
        final /* synthetic */ boolean $requestAi;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07741(Address address, double d, double d2, boolean z, Continuation<? super C07741> continuation) {
            super(2, continuation);
            this.$address = address;
            this.$lat = d;
            this.$lng = d2;
            this.$requestAi = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C07741(this.$address, this.$lat, this.$lng, this.$requestAi, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07741) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x007e A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$address != null) {
                    String locality = this.$address.getLocality();
                    Intrinsics.checkNotNullExpressionValue(locality, "getLocality(...)");
                    UpdateUserLocationReq updateUserLocationReq = new UpdateUserLocationReq(locality, String.valueOf(UserConfig.INSTANCE.getInstance().getUid()), this.$lat, this.$lng);
                    if (this.$requestAi) {
                        AiChatDepository.INSTANCE.getGetInstance().chatGpt(1, MoshiUtilsKt.toJson(updateUserLocationReq), "");
                    }
                    this.label = 1;
                    obj = SettingDepository.INSTANCE.getGetInstance().updateUserLocation(updateUserLocationReq, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    this.label = 2;
                    if (((Flow) obj).collect(new FlowCollector() { // from class: com.glasssutdio.wear.MainActivity.updateLocationUI.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                            return emit((NetState<Integer>) obj2, (Continuation<? super Unit>) continuation);
                        }

                        public final Object emit(NetState<Integer> netState, Continuation<? super Unit> continuation) {
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                    }
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
                this.label = 2;
                if (((Flow) obj).collect(new FlowCollector() { // from class: com.glasssutdio.wear.MainActivity.updateLocationUI.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((NetState<Integer>) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(NetState<Integer> netState, Continuation<? super Unit> continuation) {
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }
}
