package com.glasssutdio.wear.ble.connect;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.GlassApplication;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.utils.GsonInstance;
import com.glasssutdio.wear.all.utils.PermissionUtilKt;
import com.glasssutdio.wear.ble.connect.adapter.DeviceListAdapter;
import com.glasssutdio.wear.ble.connect.bean.SmartWatch;
import com.glasssutdio.wear.bus.EventType;
import com.glasssutdio.wear.databinding.ActivityDeviceBindBinding;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.oudmon.ble.base.bluetooth.BleAction;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.QCBluetoothCallbackCloneReceiver;
import com.oudmon.ble.base.scan.BleScannerHelper;
import com.oudmon.ble.base.scan.ScanRecord;
import com.oudmon.ble.base.scan.ScanWrapperCallback;
import com.oudmon.qc_utils.bluetooth.BluetoothUtils;
import java.util.Comparator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.EventBus;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: DeviceBindActivity.kt */
@Metadata(m606d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001:\u000656789:B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\u000e2\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J\u0012\u0010-\u001a\u00020(2\b\u0010.\u001a\u0004\u0018\u00010/H\u0014J\b\u00100\u001a\u00020(H\u0014J\b\u00101\u001a\u00020(H\u0014J\b\u00102\u001a\u00020(H\u0014J\b\u00103\u001a\u00020(H\u0002J\b\u00104\u001a\u00020(H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0015\u0010\u0007\u001a\u00060\bR\u00020\u0000¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00060\u0010R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001bR\u0012\u0010\u001e\u001a\u00060\u001fR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010$\u001a\u00060%R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006;"}, m607d2 = {"Lcom/glasssutdio/wear/ble/connect/DeviceBindActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "adapter", "Lcom/glasssutdio/wear/ble/connect/adapter/DeviceListAdapter;", "binding", "Lcom/glasssutdio/wear/databinding/ActivityDeviceBindBinding;", "bleScanCallback", "Lcom/glasssutdio/wear/ble/connect/DeviceBindActivity$BleCallback;", "getBleScanCallback", "()Lcom/glasssutdio/wear/ble/connect/DeviceBindActivity$BleCallback;", "bluetoothAdapter", "Landroid/bluetooth/BluetoothAdapter;", "connectFailNumber", "", "connectRunnable", "Lcom/glasssutdio/wear/ble/connect/DeviceBindActivity$ConnectTimeoutRunnable;", "deviceLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/glasssutdio/wear/ble/connect/bean/SmartWatch;", "getDeviceLiveData", "()Landroidx/lifecycle/MutableLiveData;", "setDeviceLiveData", "(Landroidx/lifecycle/MutableLiveData;)V", "deviceViewModel", "Lcom/glasssutdio/wear/ble/connect/DeviceBindViewModel;", "getDeviceViewModel", "()Lcom/glasssutdio/wear/ble/connect/DeviceBindViewModel;", "deviceViewModel$delegate", "Lkotlin/Lazy;", "myBleReceiver", "Lcom/glasssutdio/wear/ble/connect/DeviceBindActivity$MyReceiver;", "myHandler", "Landroid/os/Handler;", "popupWindow", "Landroid/widget/PopupWindow;", "runnable", "Lcom/glasssutdio/wear/ble/connect/DeviceBindActivity$MyRunnable;", "scanSize", "onActivityResult", "", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "setupViews", "showConnectingAnim", "BleCallback", "BluetoothPermissionCallback", "ConnectTimeoutRunnable", "MyReceiver", "MyRunnable", "PermissionCallback", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class DeviceBindActivity extends BaseSettingActivity {
    private DeviceListAdapter adapter;
    private ActivityDeviceBindBinding binding;
    private final BleCallback bleScanCallback;
    private BluetoothAdapter bluetoothAdapter;
    private int connectFailNumber;
    private final ConnectTimeoutRunnable connectRunnable;
    private MutableLiveData<SmartWatch> deviceLiveData;

    /* renamed from: deviceViewModel$delegate, reason: from kotlin metadata */
    private final Lazy deviceViewModel;
    private final MyReceiver myBleReceiver;
    private final Handler myHandler;
    private PopupWindow popupWindow;
    private final MyRunnable runnable;
    private int scanSize;

    /* JADX WARN: Multi-variable type inference failed */
    public DeviceBindActivity() {
        final DeviceBindActivity deviceBindActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.deviceViewModel = LazyKt.lazy(new Function0<DeviceBindViewModel>() { // from class: com.glasssutdio.wear.ble.connect.DeviceBindActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.ble.connect.DeviceBindViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final DeviceBindViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(deviceBindActivity, Reflection.getOrCreateKotlinClass(DeviceBindViewModel.class), qualifier, objArr);
            }
        });
        this.runnable = new MyRunnable();
        this.connectRunnable = new ConnectTimeoutRunnable();
        this.myBleReceiver = new MyReceiver();
        this.bleScanCallback = new BleCallback();
        this.deviceLiveData = new MutableLiveData<>();
        final Looper mainLooper = Looper.getMainLooper();
        this.myHandler = new Handler(mainLooper) { // from class: com.glasssutdio.wear.ble.connect.DeviceBindActivity$myHandler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DeviceBindViewModel getDeviceViewModel() {
        return (DeviceBindViewModel) this.deviceViewModel.getValue();
    }

    public final BleCallback getBleScanCallback() {
        return this.bleScanCallback;
    }

    public final MutableLiveData<SmartWatch> getDeviceLiveData() {
        return this.deviceLiveData;
    }

    public final void setDeviceLiveData(MutableLiveData<SmartWatch> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.deviceLiveData = mutableLiveData;
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityDeviceBindBinding activityDeviceBindBindingInflate = ActivityDeviceBindBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityDeviceBindBindingInflate, "inflate(...)");
        this.binding = activityDeviceBindBindingInflate;
        if (activityDeviceBindBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDeviceBindBindingInflate = null;
        }
        setContentView(activityDeviceBindBindingInflate.getRoot());
        setupViews();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() throws NoSuchMethodException, SecurityException {
        super.onResume();
        try {
            if (!BluetoothUtils.isEnabledBluetooth(this)) {
                Intent intent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
                if (Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(this, Permission.BLUETOOTH_CONNECT) != 0) {
                    XLog.m137i("---------");
                    startActivityForResult(intent, 1001);
                    return;
                }
                startActivityForResult(intent, 1001);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DeviceBindActivity deviceBindActivity = this;
        if (!PermissionUtilKt.hasBluetooth(deviceBindActivity)) {
            PermissionUtilKt.requestBluetoothPermission(deviceBindActivity, new BluetoothPermissionCallback());
        } else {
            PermissionUtilKt.requestLocationPermission(deviceBindActivity, new PermissionCallback());
        }
    }

    private final void setupViews() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        Intrinsics.checkNotNullExpressionValue(defaultAdapter, "getDefaultAdapter(...)");
        this.bluetoothAdapter = defaultAdapter;
        IntentFilter intentFilter = BleAction.getIntentFilter();
        Intrinsics.checkNotNullExpressionValue(intentFilter, "getIntentFilter(...)");
        LocalBroadcastManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).registerReceiver(this.myBleReceiver, intentFilter);
        DeviceBindActivity deviceBindActivity = this;
        this.adapter = new DeviceListAdapter(deviceBindActivity, getDeviceViewModel().getDeviceList());
        ActivityDeviceBindBinding activityDeviceBindBinding = this.binding;
        ActivityDeviceBindBinding activityDeviceBindBinding2 = null;
        if (activityDeviceBindBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDeviceBindBinding = null;
        }
        activityDeviceBindBinding.deviceRcv.setLayoutManager(new LinearLayoutManager(deviceBindActivity));
        RecyclerView recyclerView = activityDeviceBindBinding.deviceRcv;
        DeviceListAdapter deviceListAdapter = this.adapter;
        if (deviceListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            deviceListAdapter = null;
        }
        recyclerView.setAdapter(deviceListAdapter);
        activityDeviceBindBinding.titleBar.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.ble.connect.DeviceBindActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceBindActivity.setupViews$lambda$1$lambda$0(this.f$0, view);
            }
        });
        DeviceListAdapter deviceListAdapter2 = this.adapter;
        if (deviceListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            deviceListAdapter2 = null;
        }
        deviceListAdapter2.notifyDataSetChanged();
        final DeviceBindViewModel deviceViewModel = getDeviceViewModel();
        this.deviceLiveData.observe(this, new DeviceBindActivity$sam$androidx_lifecycle_Observer$0(new Function1<SmartWatch, Unit>() { // from class: com.glasssutdio.wear.ble.connect.DeviceBindActivity$setupViews$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SmartWatch smartWatch) {
                invoke2(smartWatch);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SmartWatch smartWatch) {
                ActivityDeviceBindBinding activityDeviceBindBinding3 = this.this$0.binding;
                DeviceListAdapter deviceListAdapter3 = null;
                if (activityDeviceBindBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDeviceBindBinding3 = null;
                }
                activityDeviceBindBinding3.titleBar.tvTitle.setText(this.this$0.getString(C0775R.string.ble_glass_1));
                ActivityDeviceBindBinding activityDeviceBindBinding4 = this.this$0.binding;
                if (activityDeviceBindBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDeviceBindBinding4 = null;
                }
                ViewKt.gone(activityDeviceBindBinding4.ctlSearchIng);
                if (deviceViewModel.getDeviceList().contains(smartWatch)) {
                    return;
                }
                this.this$0.scanSize++;
                List<SmartWatch> deviceList = deviceViewModel.getDeviceList();
                Intrinsics.checkNotNull(smartWatch);
                deviceList.add(0, smartWatch);
                List<SmartWatch> deviceList2 = deviceViewModel.getDeviceList();
                if (deviceList2.size() > 1) {
                    CollectionsKt.sortWith(deviceList2, new Comparator() { // from class: com.glasssutdio.wear.ble.connect.DeviceBindActivity$setupViews$2$1$invoke$$inlined$sortByDescending$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return ComparisonsKt.compareValues(Integer.valueOf(((SmartWatch) t2).getRssi()), Integer.valueOf(((SmartWatch) t).getRssi()));
                        }
                    });
                }
                DeviceListAdapter deviceListAdapter4 = this.this$0.adapter;
                if (deviceListAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    deviceListAdapter3 = deviceListAdapter4;
                }
                deviceListAdapter3.notifyDataSetChanged();
                if (this.this$0.scanSize > 30) {
                    BleScannerHelper.getInstance().stopScan(GlassApplication.INSTANCE.getCONTEXT());
                }
            }
        }));
        DeviceListAdapter deviceListAdapter3 = this.adapter;
        if (deviceListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            deviceListAdapter3 = null;
        }
        deviceListAdapter3.setOnItemClickListener(new OnItemClickListener() { // from class: com.glasssutdio.wear.ble.connect.DeviceBindActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DeviceBindActivity.setupViews$lambda$4$lambda$3(this.f$0, baseQuickAdapter, view, i);
            }
        });
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        View[] viewArr = new View[1];
        ActivityDeviceBindBinding activityDeviceBindBinding3 = this.binding;
        if (activityDeviceBindBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDeviceBindBinding2 = activityDeviceBindBinding3;
        }
        viewArr[0] = activityDeviceBindBinding2.tvRestartSearch;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.glasssutdio.wear.ble.connect.DeviceBindActivity.setupViews.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Type inference failed for: r4v5, types: [T, android.animation.ObjectAnimator] */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View setOnClickListener) {
                Intrinsics.checkNotNullParameter(setOnClickListener, "$this$setOnClickListener");
                DeviceBindActivity.this.getDeviceViewModel().getDeviceList().clear();
                ActivityDeviceBindBinding activityDeviceBindBinding4 = DeviceBindActivity.this.binding;
                if (activityDeviceBindBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDeviceBindBinding4 = null;
                }
                ViewKt.visible(activityDeviceBindBinding4.ctlSearchIng);
                ObjectAnimator objectAnimator = objectRef.element;
                if (objectAnimator != null) {
                    objectAnimator.cancel();
                }
                Ref.ObjectRef<ObjectAnimator> objectRef2 = objectRef;
                ActivityDeviceBindBinding activityDeviceBindBinding5 = DeviceBindActivity.this.binding;
                if (activityDeviceBindBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDeviceBindBinding5 = null;
                }
                ImageView ivSearching = activityDeviceBindBinding5.ivSearching;
                Intrinsics.checkNotNullExpressionValue(ivSearching, "ivSearching");
                objectRef2.element = ViewKt.rotate(ivSearching, (54 & 1) != 0 ? 1400L : 2500L, (54 & 2) != 0 ? -1 : 0, (54 & 4) != 0, (54 & 8) != 0 ? null : DeviceBindActivity.this.getLifecycle(), (54 & 16) != 0 ? 0L : 0L, (54 & 32) == 0 ? null : null);
                ActivityDeviceBindBinding activityDeviceBindBinding6 = DeviceBindActivity.this.binding;
                if (activityDeviceBindBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDeviceBindBinding6 = null;
                }
                ViewKt.gone(activityDeviceBindBinding6.ctlSearchAgain);
                DeviceListAdapter deviceListAdapter4 = DeviceBindActivity.this.adapter;
                if (deviceListAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    deviceListAdapter4 = null;
                }
                deviceListAdapter4.notifyDataSetChanged();
                BleScannerHelper.getInstance().reSetCallback();
                List<SmartWatch> listAddSystemBondedDevice = DeviceBindActivity.this.getDeviceViewModel().addSystemBondedDevice();
                if (listAddSystemBondedDevice.size() > 0) {
                    DeviceBindActivity.this.getDeviceViewModel().getDeviceList().addAll(listAddSystemBondedDevice);
                }
                if (!BluetoothUtils.isEnabledBluetooth(DeviceBindActivity.this)) {
                    if (Build.VERSION.SDK_INT < 31 || ActivityCompat.checkSelfPermission(DeviceBindActivity.this, Permission.BLUETOOTH_CONNECT) == 0) {
                        Intent intent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
                        Activity activity = DeviceBindActivity.this.getActivity();
                        Intrinsics.checkNotNull(activity);
                        activity.startActivityForResult(intent, 1001);
                        return;
                    }
                    return;
                }
                XLog.m137i("start scan:" + GsonInstance.INSTANCE.getGson().toJson(GlassApplication.INSTANCE.getGetInstance().getDeviceKeys()));
                DeviceBindActivity.this.scanSize = 0;
                ActivityDeviceBindBinding activityDeviceBindBinding7 = DeviceBindActivity.this.binding;
                if (activityDeviceBindBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDeviceBindBinding7 = null;
                }
                activityDeviceBindBinding7.tvScanStatus.setText(DeviceBindActivity.this.getString(C0775R.string.h_glass_96));
                BleScannerHelper.getInstance().scanDevice(GlassApplication.INSTANCE.getCONTEXT(), null, DeviceBindActivity.this.getBleScanCallback());
                DeviceBindActivity.this.myHandler.removeCallbacks(DeviceBindActivity.this.runnable);
                DeviceBindActivity.this.myHandler.postDelayed(DeviceBindActivity.this.runnable, 12000L);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupViews$lambda$1$lambda$0(DeviceBindActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupViews$lambda$4$lambda$3(DeviceBindActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        ActivityDeviceBindBinding activityDeviceBindBinding = this$0.binding;
        if (activityDeviceBindBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDeviceBindBinding = null;
        }
        ViewKt.visible(activityDeviceBindBinding.ctlConnecting);
        this$0.myHandler.removeCallbacks(this$0.runnable);
        SmartWatch smartWatch = this$0.getDeviceViewModel().getDeviceList().get(i);
        XLog.m136i(smartWatch);
        this$0.showConnectingAnim();
        BleOperateManager.getInstance().connectDirectly(smartWatch.getDeviceAddress());
        this$0.myHandler.postDelayed(this$0.connectRunnable, 15000L);
    }

    /* compiled from: DeviceBindActivity.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m607d2 = {"Lcom/glasssutdio/wear/ble/connect/DeviceBindActivity$MyRunnable;", "Ljava/lang/Runnable;", "(Lcom/glasssutdio/wear/ble/connect/DeviceBindActivity;)V", "run", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class MyRunnable implements Runnable {
        public MyRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActivityDeviceBindBinding activityDeviceBindBinding = DeviceBindActivity.this.binding;
            ActivityDeviceBindBinding activityDeviceBindBinding2 = null;
            if (activityDeviceBindBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDeviceBindBinding = null;
            }
            ViewKt.gone(activityDeviceBindBinding.ctlSearchIng);
            if (DeviceBindActivity.this.getDeviceViewModel().getDeviceList().isEmpty()) {
                ActivityDeviceBindBinding activityDeviceBindBinding3 = DeviceBindActivity.this.binding;
                if (activityDeviceBindBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityDeviceBindBinding2 = activityDeviceBindBinding3;
                }
                ViewKt.visible(activityDeviceBindBinding2.ctlSearchAgain);
            }
        }
    }

    /* compiled from: DeviceBindActivity.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m607d2 = {"Lcom/glasssutdio/wear/ble/connect/DeviceBindActivity$ConnectTimeoutRunnable;", "Ljava/lang/Runnable;", "(Lcom/glasssutdio/wear/ble/connect/DeviceBindActivity;)V", "run", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class ConnectTimeoutRunnable implements Runnable {
        public ConnectTimeoutRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActivityDeviceBindBinding activityDeviceBindBinding = DeviceBindActivity.this.binding;
            ActivityDeviceBindBinding activityDeviceBindBinding2 = null;
            if (activityDeviceBindBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDeviceBindBinding = null;
            }
            ViewKt.gone(activityDeviceBindBinding.ctlSearchIng);
            ActivityDeviceBindBinding activityDeviceBindBinding3 = DeviceBindActivity.this.binding;
            if (activityDeviceBindBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityDeviceBindBinding2 = activityDeviceBindBinding3;
            }
            ViewKt.gone(activityDeviceBindBinding2.ctlConnecting);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        PopupWindow popupWindow = this.popupWindow;
        if (popupWindow != null) {
            PopupWindow popupWindow2 = null;
            if (popupWindow == null) {
                Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
                popupWindow = null;
            }
            if (popupWindow.isShowing()) {
                PopupWindow popupWindow3 = this.popupWindow;
                if (popupWindow3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
                } else {
                    popupWindow2 = popupWindow3;
                }
                popupWindow2.dismiss();
            }
        }
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.myHandler.postDelayed(new Runnable() { // from class: com.glasssutdio.wear.ble.connect.DeviceBindActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                DeviceBindActivity.onDestroy$lambda$5();
            }
        }, 100L);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.myBleReceiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onDestroy$lambda$5() {
        BleScannerHelper.getInstance().stopScan(GlassApplication.INSTANCE.getCONTEXT());
    }

    /* compiled from: DeviceBindActivity.kt */
    @Metadata(m606d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u001a\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, m607d2 = {"Lcom/glasssutdio/wear/ble/connect/DeviceBindActivity$MyReceiver;", "Lcom/oudmon/ble/base/bluetooth/QCBluetoothCallbackCloneReceiver;", "(Lcom/glasssutdio/wear/ble/connect/DeviceBindActivity;)V", "bleStatus", "", NotificationCompat.CATEGORY_STATUS, "", "newState", "connectStatue", "device", "Landroid/bluetooth/BluetoothDevice;", "connected", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class MyReceiver extends QCBluetoothCallbackCloneReceiver {
        public MyReceiver() {
        }

        @Override // com.oudmon.ble.base.bluetooth.QCBluetoothCallbackCloneReceiver
        public void connectStatue(BluetoothDevice device, boolean connected) {
            if (connected) {
                DeviceBindActivity.this.myHandler.removeCallbacks(DeviceBindActivity.this.connectRunnable);
                DeviceBindActivity.this.connectFailNumber = 0;
                EventBus.getDefault().post(new EventType(7));
                DeviceBindActivity.this.finish();
            }
        }

        @Override // com.oudmon.ble.base.bluetooth.QCBluetoothCallbackCloneReceiver
        public void bleStatus(int status, int newState) {
            super.bleStatus(status, newState);
            if (status > 0) {
                DeviceBindActivity.this.connectFailNumber++;
                int unused = DeviceBindActivity.this.connectFailNumber;
            }
        }
    }

    /* compiled from: DeviceBindActivity.kt */
    @Metadata(m606d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, m607d2 = {"Lcom/glasssutdio/wear/ble/connect/DeviceBindActivity$PermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/glasssutdio/wear/ble/connect/DeviceBindActivity;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class PermissionCallback implements OnPermissionCallback {
        public PermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            ActivityDeviceBindBinding activityDeviceBindBinding = null;
            if (all) {
                ActivityDeviceBindBinding activityDeviceBindBinding2 = DeviceBindActivity.this.binding;
                if (activityDeviceBindBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityDeviceBindBinding = activityDeviceBindBinding2;
                }
                activityDeviceBindBinding.tvRestartSearch.performClick();
                return;
            }
            String string = DeviceBindActivity.this.getString(C0775R.string.h_glass_101);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            super.onDenied(permissions, never);
            if (never) {
                String string = DeviceBindActivity.this.getString(C0775R.string.h_glass_103);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                GlobalKt.showToast$default(string, 0, 1, null);
                XXPermissions.startPermissionActivity((Activity) DeviceBindActivity.this, permissions);
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            if (resultCode == -1) {
                XLog.m137i("打开蓝牙");
            } else {
                XLog.m137i("用户拒绝");
                finish();
            }
        }
    }

    /* compiled from: DeviceBindActivity.kt */
    @Metadata(m606d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, m607d2 = {"Lcom/glasssutdio/wear/ble/connect/DeviceBindActivity$BluetoothPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/glasssutdio/wear/ble/connect/DeviceBindActivity;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class BluetoothPermissionCallback implements OnPermissionCallback {
        public BluetoothPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            ActivityDeviceBindBinding activityDeviceBindBinding = null;
            if (all) {
                ActivityDeviceBindBinding activityDeviceBindBinding2 = DeviceBindActivity.this.binding;
                if (activityDeviceBindBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityDeviceBindBinding = activityDeviceBindBinding2;
                }
                activityDeviceBindBinding.tvRestartSearch.performClick();
                return;
            }
            String string = DeviceBindActivity.this.getString(C0775R.string.h_glass_101);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            super.onDenied(permissions, never);
            if (never) {
                DeviceBindActivity.this.finish();
                XXPermissions.startPermissionActivity((Activity) DeviceBindActivity.this, permissions);
                String string = DeviceBindActivity.this.getString(C0775R.string.h_glass_102);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                GlobalKt.showToast$default(string, 0, 1, null);
            }
        }
    }

    private final void showConnectingAnim() {
        RequestBuilder<GifDrawable> requestBuilderLoad = Glide.with((FragmentActivity) this).asGif().load(Integer.valueOf(C0775R.drawable.ble_connecting));
        ActivityDeviceBindBinding activityDeviceBindBinding = this.binding;
        if (activityDeviceBindBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDeviceBindBinding = null;
        }
        requestBuilderLoad.into(activityDeviceBindBinding.imageConnecting);
    }

    /* compiled from: DeviceBindActivity.kt */
    @Metadata(m606d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0016J$\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u001c\u0010\u000f\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\fH\u0016J\b\u0010\u0013\u001a\u00020\u0004H\u0016J\b\u0010\u0014\u001a\u00020\u0004H\u0016¨\u0006\u0015"}, m607d2 = {"Lcom/glasssutdio/wear/ble/connect/DeviceBindActivity$BleCallback;", "Lcom/oudmon/ble/base/scan/ScanWrapperCallback;", "(Lcom/glasssutdio/wear/ble/connect/DeviceBindActivity;)V", "onBatchScanResults", "", "results", "", "Landroid/bluetooth/le/ScanResult;", "onLeScan", "device", "Landroid/bluetooth/BluetoothDevice;", "rssi", "", "scanRecord", "", "onParsedData", "Lcom/oudmon/ble/base/scan/ScanRecord;", "onScanFailed", "errorCode", "onStart", "onStop", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class BleCallback implements ScanWrapperCallback {
        @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
        public void onBatchScanResults(List<ScanResult> results) {
        }

        @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
        public void onParsedData(BluetoothDevice device, ScanRecord scanRecord) {
        }

        @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
        public void onScanFailed(int errorCode) {
        }

        @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
        public void onStart() {
        }

        @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
        public void onStop() {
        }

        public BleCallback() {
        }

        @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            if (device != null) {
                try {
                    String name = device.getName();
                    if (name != null && name.length() != 0) {
                        StringBuilder sb = new StringBuilder();
                        String name2 = device.getName();
                        int length = name2.length();
                        for (int i = 0; i < length; i++) {
                            sb.append(name2.charAt(i));
                            if (GlassApplication.INSTANCE.getGetInstance().getKeysMap().get(sb.toString()) != null) {
                                MutableLiveData<SmartWatch> deviceLiveData = DeviceBindActivity.this.getDeviceLiveData();
                                String name3 = device.getName();
                                Intrinsics.checkNotNullExpressionValue(name3, "getName(...)");
                                deviceLiveData.setValue(new SmartWatch(name3, device.getAddress(), rssi));
                                XLog.m137i(device.getName() + "---" + device.getAddress());
                                return;
                            }
                        }
                    }
                } catch (Exception unused) {
                }
            }
        }
    }
}
