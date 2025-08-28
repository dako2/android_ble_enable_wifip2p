package com.glasssutdio.wear.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.Constant;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.TextViewExtKt;
import com.glasssutdio.wear.all.ThreadExtKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.dialog.CommonDialog;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.GsonInstance;
import com.glasssutdio.wear.all.utils.PermissionUtilKt;
import com.glasssutdio.wear.api.NetState;
import com.glasssutdio.wear.api.response.FirmwareOtaResp;
import com.glasssutdio.wear.ble.connect.BindGuideActivity;
import com.glasssutdio.wear.ble.connect.DeviceBindViewModel;
import com.glasssutdio.wear.bus.AlbumRefreshEvent;
import com.glasssutdio.wear.bus.BluetoothEvent;
import com.glasssutdio.wear.bus.BusEvent;
import com.glasssutdio.wear.bus.EventType;
import com.glasssutdio.wear.bus.GlassesBatteryUpdateEvent;
import com.glasssutdio.wear.databinding.FragmentHomeBinding;
import com.glasssutdio.wear.depository.OTADepository;
import com.glasssutdio.wear.home.HomeFragment;
import com.glasssutdio.wear.home.activity.AIGuideActivity;
import com.glasssutdio.wear.home.activity.AIHelperActivity;
import com.glasssutdio.wear.home.activity.AiTranslateActivity;
import com.glasssutdio.wear.home.activity.UserGuideActivity;
import com.glasssutdio.wear.home.activity.UserSplashGuideActivity;
import com.glasssutdio.wear.home.activity.VolumeActivity;
import com.glasssutdio.wear.home.viewmodel.HomeFragmentViewModel;
import com.glasssutdio.wear.ota.OTAActivity;
import com.glasssutdio.wear.setting.YourGlassActivity;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.hjq.permissions.OnPermissionCallback;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.ILargeDataResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.resp.BaseResponse;
import com.oudmon.ble.base.communication.bigData.resp.BatteryResponse;
import com.oudmon.ble.base.scan.BleScannerHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
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

/* compiled from: HomeFragment.kt */
@Metadata(m606d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u0000 02\u00020\u0001:\u0003012B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0011H\u0002J \u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0011H\u0002J\b\u0010\u001a\u001a\u00020\u0013H\u0016J$\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010#\u001a\u00020\u0013H\u0016J\u0010\u0010$\u001a\u00020\u00132\u0006\u0010%\u001a\u00020&H\u0007J\b\u0010'\u001a\u00020\u0013H\u0016J\b\u0010(\u001a\u00020\u0013H\u0002J\b\u0010)\u001a\u00020\u0013H\u0002J\u0010\u0010*\u001a\u00020\u00132\u0006\u0010+\u001a\u00020,H\u0002J\u0010\u0010-\u001a\u00020\u00132\u0006\u0010.\u001a\u00020,H\u0002J\b\u0010/\u001a\u00020\u0013H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, m607d2 = {"Lcom/glasssutdio/wear/home/HomeFragment;", "Lcom/glasssutdio/wear/home/BaseFragment;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/FragmentHomeBinding;", "deviceViewModel", "Lcom/glasssutdio/wear/ble/connect/DeviceBindViewModel;", "getDeviceViewModel", "()Lcom/glasssutdio/wear/ble/connect/DeviceBindViewModel;", "deviceViewModel$delegate", "Lkotlin/Lazy;", "homeFragmentViewModel", "Lcom/glasssutdio/wear/home/viewmodel/HomeFragmentViewModel;", "getHomeFragmentViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/HomeFragmentViewModel;", "homeFragmentViewModel$delegate", "needRefreshVolume", "", "changeUiByConnectStatue", "", "connected", "checkOta", "hwName", "", "fmVersion", "wifiNotBle", "loadDataData", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/glasssutdio/wear/bus/BusEvent;", "onResume", "readDeviceBattery", "refreshVolume", "setVolume", "volumeValue", "", "showBatteryLowDialog", "battery", "showMemoryLowDialog", "Companion", "LocationPermissionCallback", "StoragePermissionCallback", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class HomeFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FragmentHomeBinding binding;

    /* renamed from: deviceViewModel$delegate, reason: from kotlin metadata */
    private final Lazy deviceViewModel;

    /* renamed from: homeFragmentViewModel$delegate, reason: from kotlin metadata */
    private final Lazy homeFragmentViewModel;
    private boolean needRefreshVolume;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadDataData$lambda$7$lambda$3(View view) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public HomeFragment() {
        final HomeFragment homeFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.homeFragmentViewModel = LazyKt.lazy(new Function0<HomeFragmentViewModel>() { // from class: com.glasssutdio.wear.home.HomeFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.HomeFragmentViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final HomeFragmentViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(homeFragment, Reflection.getOrCreateKotlinClass(HomeFragmentViewModel.class), qualifier, objArr);
            }
        });
        final Object[] objArr2 = 0 == true ? 1 : 0;
        final Object[] objArr3 = 0 == true ? 1 : 0;
        this.deviceViewModel = LazyKt.lazy(new Function0<DeviceBindViewModel>() { // from class: com.glasssutdio.wear.home.HomeFragment$special$$inlined$viewModel$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.ble.connect.DeviceBindViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final DeviceBindViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(homeFragment, Reflection.getOrCreateKotlinClass(DeviceBindViewModel.class), objArr2, objArr3);
            }
        });
        this.needRefreshVolume = true;
    }

    private final HomeFragmentViewModel getHomeFragmentViewModel() {
        return (HomeFragmentViewModel) this.homeFragmentViewModel.getValue();
    }

    private final DeviceBindViewModel getDeviceViewModel() {
        return (DeviceBindViewModel) this.deviceViewModel.getValue();
    }

    @Override // com.glasssutdio.wear.home.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws SecurityException {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentHomeBinding fragmentHomeBindingInflate = FragmentHomeBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentHomeBindingInflate, "inflate(...)");
        this.binding = fragmentHomeBindingInflate;
        EventBus.getDefault().register(this);
        FragmentHomeBinding fragmentHomeBinding = this.binding;
        if (fragmentHomeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHomeBinding = null;
        }
        ConstraintLayout root = fragmentHomeBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // com.glasssutdio.wear.home.BaseFragment
    public void loadDataData() throws NumberFormatException {
        super.loadDataData();
        FragmentHomeBinding fragmentHomeBinding = this.binding;
        FragmentHomeBinding fragmentHomeBinding2 = null;
        if (fragmentHomeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHomeBinding = null;
        }
        ConstraintLayout glassStatus1 = fragmentHomeBinding.glassStatus1;
        Intrinsics.checkNotNullExpressionValue(glassStatus1, "glassStatus1");
        ViewKt.statusMargin$default(glassStatus1, false, 0, 3, null);
        FragmentHomeBinding fragmentHomeBinding3 = this.binding;
        if (fragmentHomeBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHomeBinding3 = null;
        }
        ImageView imageGlasses1 = fragmentHomeBinding3.imageGlasses1;
        Intrinsics.checkNotNullExpressionValue(imageGlasses1, "imageGlasses1");
        ViewKt.statusMargin$default(imageGlasses1, false, GlobalKt.getDp((Number) 50), 1, null);
        FragmentHomeBinding fragmentHomeBinding4 = this.binding;
        if (fragmentHomeBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHomeBinding4 = null;
        }
        TextView tvToBt = fragmentHomeBinding4.tvToBt;
        Intrinsics.checkNotNullExpressionValue(tvToBt, "tvToBt");
        TextViewExtKt.setupMarqueeWithClick(tvToBt);
        getHomeFragmentViewModel().initData();
        new GridLayoutManager(getContext(), 2).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.glasssutdio.wear.home.HomeFragment.loadDataData.1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int position) {
                return position < 2 ? 1 : 2;
            }
        });
        final FragmentHomeBinding fragmentHomeBinding5 = this.binding;
        if (fragmentHomeBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHomeBinding5 = null;
        }
        fragmentHomeBinding5.clsVolume.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.HomeFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.loadDataData$lambda$7$lambda$0(this.f$0, view);
            }
        });
        fragmentHomeBinding5.tvDeviceName.setText(UserConfig.INSTANCE.getInstance().getDeviceName());
        fragmentHomeBinding5.tvToBind.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.HomeFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws NoSuchMethodException, SecurityException {
                HomeFragment.loadDataData$lambda$7$lambda$1(this.f$0, view);
            }
        });
        fragmentHomeBinding5.glassStatus1.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.HomeFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.loadDataData$lambda$7$lambda$2(this.f$0, view);
            }
        });
        fragmentHomeBinding5.noBindDevice.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.HomeFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.loadDataData$lambda$7$lambda$3(view);
            }
        });
        fragmentHomeBinding5.tvToBt.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.HomeFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.loadDataData$lambda$7$lambda$5(fragmentHomeBinding5, this, view);
            }
        });
        fragmentHomeBinding5.tvToOta.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.HomeFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.loadDataData$lambda$7$lambda$6(this.f$0, view);
            }
        });
        View[] viewArr = new View[4];
        FragmentHomeBinding fragmentHomeBinding6 = this.binding;
        if (fragmentHomeBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHomeBinding6 = null;
        }
        viewArr[0] = fragmentHomeBinding6.cslAiTranslate;
        FragmentHomeBinding fragmentHomeBinding7 = this.binding;
        if (fragmentHomeBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHomeBinding7 = null;
        }
        viewArr[1] = fragmentHomeBinding7.cslAiGesture;
        FragmentHomeBinding fragmentHomeBinding8 = this.binding;
        if (fragmentHomeBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHomeBinding8 = null;
        }
        viewArr[2] = fragmentHomeBinding8.cslAiVoice;
        FragmentHomeBinding fragmentHomeBinding9 = this.binding;
        if (fragmentHomeBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHomeBinding9 = null;
        }
        viewArr[3] = fragmentHomeBinding9.cslAiCamera;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.glasssutdio.wear.home.HomeFragment.loadDataData.3
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
                FragmentHomeBinding fragmentHomeBinding10 = HomeFragment.this.binding;
                FragmentHomeBinding fragmentHomeBinding11 = null;
                if (fragmentHomeBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding10 = null;
                }
                if (!Intrinsics.areEqual(setOnClickListener, fragmentHomeBinding10.cslAiTranslate)) {
                    FragmentHomeBinding fragmentHomeBinding12 = HomeFragment.this.binding;
                    if (fragmentHomeBinding12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentHomeBinding12 = null;
                    }
                    if (!Intrinsics.areEqual(setOnClickListener, fragmentHomeBinding12.cslAiCamera)) {
                        FragmentHomeBinding fragmentHomeBinding13 = HomeFragment.this.binding;
                        if (fragmentHomeBinding13 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            fragmentHomeBinding13 = null;
                        }
                        if (!Intrinsics.areEqual(setOnClickListener, fragmentHomeBinding13.cslAiGesture)) {
                            FragmentHomeBinding fragmentHomeBinding14 = HomeFragment.this.binding;
                            if (fragmentHomeBinding14 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                fragmentHomeBinding11 = fragmentHomeBinding14;
                            }
                            if (Intrinsics.areEqual(setOnClickListener, fragmentHomeBinding11.cslAiVoice)) {
                                if (UserConfig.INSTANCE.getInstance().isShowedAiGuided()) {
                                    HomeFragment homeFragment = HomeFragment.this;
                                    FragmentActivity activity = homeFragment.getActivity();
                                    if (activity != null) {
                                        ArrayList<Pair> arrayList = new ArrayList();
                                        Intrinsics.checkNotNull(activity);
                                        Intent intent = new Intent(activity, (Class<?>) AIHelperActivity.class);
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
                                        Unit unit2 = Unit.INSTANCE;
                                        Unit unit3 = Unit.INSTANCE;
                                        homeFragment.startActivity(intent);
                                        Unit unit4 = Unit.INSTANCE;
                                        Unit unit5 = Unit.INSTANCE;
                                        return;
                                    }
                                    return;
                                }
                                UserConfig.INSTANCE.getInstance().setShowedAiGuided(true);
                                HomeFragment homeFragment2 = HomeFragment.this;
                                FragmentActivity activity2 = homeFragment2.getActivity();
                                if (activity2 != null) {
                                    ArrayList<Pair> arrayList2 = new ArrayList();
                                    Intrinsics.checkNotNull(activity2);
                                    Intent intent2 = new Intent(activity2, (Class<?>) AIGuideActivity.class);
                                    for (Pair pair2 : arrayList2) {
                                        if (pair2 != null) {
                                            String str2 = (String) pair2.getFirst();
                                            Object second2 = pair2.getSecond();
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
                                                Unit unit6 = Unit.INSTANCE;
                                            }
                                        }
                                    }
                                    Unit unit7 = Unit.INSTANCE;
                                    Unit unit8 = Unit.INSTANCE;
                                    homeFragment2.startActivity(intent2);
                                    Unit unit9 = Unit.INSTANCE;
                                    Unit unit10 = Unit.INSTANCE;
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        HomeFragment homeFragment3 = HomeFragment.this;
                        Pair pair3 = new Pair(Constant.PAGE_GUIDE_KEY, 2);
                        FragmentActivity activity3 = homeFragment3.getActivity();
                        if (activity3 != null) {
                            ArrayList<Pair> arrayList3 = new ArrayList();
                            arrayList3.add(pair3);
                            Intrinsics.checkNotNull(activity3);
                            Intent intent3 = new Intent(activity3, (Class<?>) UserGuideActivity.class);
                            for (Pair pair4 : arrayList3) {
                                if (pair4 != null) {
                                    String str3 = (String) pair4.getFirst();
                                    Object second3 = pair4.getSecond();
                                    if (second3 instanceof Integer) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).intValue()), "putExtra(...)");
                                    } else if (second3 instanceof Byte) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).byteValue()), "putExtra(...)");
                                    } else if (second3 instanceof Character) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Character) second3).charValue()), "putExtra(...)");
                                    } else if (second3 instanceof Short) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).shortValue()), "putExtra(...)");
                                    } else if (second3 instanceof Boolean) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Boolean) second3).booleanValue()), "putExtra(...)");
                                    } else if (second3 instanceof Long) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).longValue()), "putExtra(...)");
                                    } else if (second3 instanceof Float) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).floatValue()), "putExtra(...)");
                                    } else if (second3 instanceof Double) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).doubleValue()), "putExtra(...)");
                                    } else if (second3 instanceof String) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (String) second3), "putExtra(...)");
                                    } else if (second3 instanceof CharSequence) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (CharSequence) second3), "putExtra(...)");
                                    } else if (second3 instanceof Parcelable) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Parcelable) second3), "putExtra(...)");
                                    } else if (second3 instanceof Object[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Serializable) second3), "putExtra(...)");
                                    } else if (second3 instanceof ArrayList) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Serializable) second3), "putExtra(...)");
                                    } else if (second3 instanceof Serializable) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Serializable) second3), "putExtra(...)");
                                    } else if (second3 instanceof boolean[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (boolean[]) second3), "putExtra(...)");
                                    } else if (second3 instanceof byte[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (byte[]) second3), "putExtra(...)");
                                    } else if (second3 instanceof short[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (short[]) second3), "putExtra(...)");
                                    } else if (second3 instanceof char[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (char[]) second3), "putExtra(...)");
                                    } else if (second3 instanceof int[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (int[]) second3), "putExtra(...)");
                                    } else if (second3 instanceof long[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (long[]) second3), "putExtra(...)");
                                    } else if (second3 instanceof float[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (float[]) second3), "putExtra(...)");
                                    } else if (second3 instanceof double[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (double[]) second3), "putExtra(...)");
                                    } else if (second3 instanceof Bundle) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Bundle) second3), "putExtra(...)");
                                    } else if (second3 instanceof Intent) {
                                        Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Parcelable) second3), "putExtra(...)");
                                    } else {
                                        Unit unit11 = Unit.INSTANCE;
                                    }
                                }
                            }
                            Unit unit12 = Unit.INSTANCE;
                            Unit unit13 = Unit.INSTANCE;
                            homeFragment3.startActivity(intent3);
                            Unit unit14 = Unit.INSTANCE;
                            Unit unit15 = Unit.INSTANCE;
                            return;
                        }
                        return;
                    }
                    HomeFragment homeFragment4 = HomeFragment.this;
                    Pair pair5 = new Pair(Constant.PAGE_GUIDE_KEY, 1);
                    FragmentActivity activity4 = homeFragment4.getActivity();
                    if (activity4 != null) {
                        ArrayList<Pair> arrayList4 = new ArrayList();
                        arrayList4.add(pair5);
                        Intrinsics.checkNotNull(activity4);
                        Intent intent4 = new Intent(activity4, (Class<?>) UserGuideActivity.class);
                        for (Pair pair6 : arrayList4) {
                            if (pair6 != null) {
                                String str4 = (String) pair6.getFirst();
                                Object second4 = pair6.getSecond();
                                if (second4 instanceof Integer) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Number) second4).intValue()), "putExtra(...)");
                                } else if (second4 instanceof Byte) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Number) second4).byteValue()), "putExtra(...)");
                                } else if (second4 instanceof Character) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Character) second4).charValue()), "putExtra(...)");
                                } else if (second4 instanceof Short) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Number) second4).shortValue()), "putExtra(...)");
                                } else if (second4 instanceof Boolean) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Boolean) second4).booleanValue()), "putExtra(...)");
                                } else if (second4 instanceof Long) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Number) second4).longValue()), "putExtra(...)");
                                } else if (second4 instanceof Float) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Number) second4).floatValue()), "putExtra(...)");
                                } else if (second4 instanceof Double) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Number) second4).doubleValue()), "putExtra(...)");
                                } else if (second4 instanceof String) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (String) second4), "putExtra(...)");
                                } else if (second4 instanceof CharSequence) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (CharSequence) second4), "putExtra(...)");
                                } else if (second4 instanceof Parcelable) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (Parcelable) second4), "putExtra(...)");
                                } else if (second4 instanceof Object[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (Serializable) second4), "putExtra(...)");
                                } else if (second4 instanceof ArrayList) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (Serializable) second4), "putExtra(...)");
                                } else if (second4 instanceof Serializable) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (Serializable) second4), "putExtra(...)");
                                } else if (second4 instanceof boolean[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (boolean[]) second4), "putExtra(...)");
                                } else if (second4 instanceof byte[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (byte[]) second4), "putExtra(...)");
                                } else if (second4 instanceof short[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (short[]) second4), "putExtra(...)");
                                } else if (second4 instanceof char[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (char[]) second4), "putExtra(...)");
                                } else if (second4 instanceof int[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (int[]) second4), "putExtra(...)");
                                } else if (second4 instanceof long[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (long[]) second4), "putExtra(...)");
                                } else if (second4 instanceof float[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (float[]) second4), "putExtra(...)");
                                } else if (second4 instanceof double[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (double[]) second4), "putExtra(...)");
                                } else if (second4 instanceof Bundle) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (Bundle) second4), "putExtra(...)");
                                } else if (second4 instanceof Intent) {
                                    Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (Parcelable) second4), "putExtra(...)");
                                } else {
                                    Unit unit16 = Unit.INSTANCE;
                                }
                            }
                        }
                        Unit unit17 = Unit.INSTANCE;
                        Unit unit18 = Unit.INSTANCE;
                        homeFragment4.startActivity(intent4);
                        Unit unit19 = Unit.INSTANCE;
                        Unit unit20 = Unit.INSTANCE;
                        return;
                    }
                    return;
                }
                HomeFragment homeFragment5 = HomeFragment.this;
                FragmentActivity activity5 = homeFragment5.getActivity();
                if (activity5 != null) {
                    ArrayList<Pair> arrayList5 = new ArrayList();
                    Intrinsics.checkNotNull(activity5);
                    Intent intent5 = new Intent(activity5, (Class<?>) AiTranslateActivity.class);
                    for (Pair pair7 : arrayList5) {
                        if (pair7 != null) {
                            String str5 = (String) pair7.getFirst();
                            Object second5 = pair7.getSecond();
                            if (second5 instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, ((Number) second5).intValue()), "putExtra(...)");
                            } else if (second5 instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, ((Number) second5).byteValue()), "putExtra(...)");
                            } else if (second5 instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, ((Character) second5).charValue()), "putExtra(...)");
                            } else if (second5 instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, ((Number) second5).shortValue()), "putExtra(...)");
                            } else if (second5 instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, ((Boolean) second5).booleanValue()), "putExtra(...)");
                            } else if (second5 instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, ((Number) second5).longValue()), "putExtra(...)");
                            } else if (second5 instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, ((Number) second5).floatValue()), "putExtra(...)");
                            } else if (second5 instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, ((Number) second5).doubleValue()), "putExtra(...)");
                            } else if (second5 instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (String) second5), "putExtra(...)");
                            } else if (second5 instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (CharSequence) second5), "putExtra(...)");
                            } else if (second5 instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (Parcelable) second5), "putExtra(...)");
                            } else if (second5 instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (Serializable) second5), "putExtra(...)");
                            } else if (second5 instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (Serializable) second5), "putExtra(...)");
                            } else if (second5 instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (Serializable) second5), "putExtra(...)");
                            } else if (second5 instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (boolean[]) second5), "putExtra(...)");
                            } else if (second5 instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (byte[]) second5), "putExtra(...)");
                            } else if (second5 instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (short[]) second5), "putExtra(...)");
                            } else if (second5 instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (char[]) second5), "putExtra(...)");
                            } else if (second5 instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (int[]) second5), "putExtra(...)");
                            } else if (second5 instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (long[]) second5), "putExtra(...)");
                            } else if (second5 instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (float[]) second5), "putExtra(...)");
                            } else if (second5 instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (double[]) second5), "putExtra(...)");
                            } else if (second5 instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (Bundle) second5), "putExtra(...)");
                            } else if (second5 instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent5.putExtra(str5, (Parcelable) second5), "putExtra(...)");
                            } else {
                                Unit unit21 = Unit.INSTANCE;
                            }
                        }
                    }
                    Unit unit22 = Unit.INSTANCE;
                    Unit unit23 = Unit.INSTANCE;
                    homeFragment5.startActivity(intent5);
                    Unit unit24 = Unit.INSTANCE;
                    Unit unit25 = Unit.INSTANCE;
                }
            }
        });
        FragmentHomeBinding fragmentHomeBinding10 = this.binding;
        if (fragmentHomeBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHomeBinding10 = null;
        }
        ConstraintLayout clsTakePhoto = fragmentHomeBinding10.clsTakePhoto;
        Intrinsics.checkNotNullExpressionValue(clsTakePhoto, "clsTakePhoto");
        ViewKt.click$default(clsTakePhoto, 0L, new HomeFragment$loadDataData$4$1(this), 1, null);
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        ConstraintLayout clsVideo = fragmentHomeBinding10.clsVideo;
        Intrinsics.checkNotNullExpressionValue(clsVideo, "clsVideo");
        ViewKt.click$default(clsVideo, 0L, new HomeFragment$loadDataData$4$2(booleanRef, this), 1, null);
        Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
        booleanRef2.element = true;
        ConstraintLayout clsRecording = fragmentHomeBinding10.clsRecording;
        Intrinsics.checkNotNullExpressionValue(clsRecording, "clsRecording");
        ViewKt.click$default(clsRecording, 0L, new HomeFragment$loadDataData$4$3(booleanRef2, this), 1, null);
        ConstraintLayout clsSmartPicture = fragmentHomeBinding10.clsSmartPicture;
        Intrinsics.checkNotNullExpressionValue(clsSmartPicture, "clsSmartPicture");
        ViewKt.click$default(clsSmartPicture, 0L, new HomeFragment$loadDataData$4$4(this), 1, null);
        ConstraintLayout clsTakePhoto2 = fragmentHomeBinding10.clsTakePhoto;
        Intrinsics.checkNotNullExpressionValue(clsTakePhoto2, "clsTakePhoto");
        ViewKt.addClickAnimation$default(clsTakePhoto2, fragmentHomeBinding10.ivTakePhoto, null, null, 6, null);
        ConstraintLayout clsVideo2 = fragmentHomeBinding10.clsVideo;
        Intrinsics.checkNotNullExpressionValue(clsVideo2, "clsVideo");
        ViewKt.addClickAnimation$default(clsVideo2, fragmentHomeBinding10.ivVideo, null, null, 6, null);
        ConstraintLayout clsRecording2 = fragmentHomeBinding10.clsRecording;
        Intrinsics.checkNotNullExpressionValue(clsRecording2, "clsRecording");
        ViewKt.addClickAnimation$default(clsRecording2, fragmentHomeBinding10.ivRecording, null, null, 6, null);
        ConstraintLayout clsSmartPicture2 = fragmentHomeBinding10.clsSmartPicture;
        Intrinsics.checkNotNullExpressionValue(clsSmartPicture2, "clsSmartPicture");
        ViewKt.addClickAnimation$default(clsSmartPicture2, fragmentHomeBinding10.ivSmartImg, null, null, 6, null);
        readDeviceBattery();
        getDeviceViewModel().getPicUiState().observe(this, new HomeFragment$sam$androidx_lifecycle_Observer$0(new Function1<DeviceBindViewModel.DevicePictureUI, Unit>() { // from class: com.glasssutdio.wear.home.HomeFragment.loadDataData.5
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
                    RequestBuilder<Bitmap> requestBuilderLoad = Glide.with(HomeFragment.this).asBitmap().load("file://" + devicePictureUI.getLocalUrl());
                    final HomeFragment homeFragment = HomeFragment.this;
                    requestBuilderLoad.into((RequestBuilder<Bitmap>) new CustomTarget<Bitmap>() { // from class: com.glasssutdio.wear.home.HomeFragment.loadDataData.5.1
                        @Override // com.bumptech.glide.request.target.Target
                        public void onLoadCleared(Drawable placeholder) {
                        }

                        @Override // com.bumptech.glide.request.target.Target
                        public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                            onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                        }

                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            Intrinsics.checkNotNullParameter(resource, "resource");
                            FragmentHomeBinding fragmentHomeBinding11 = homeFragment.binding;
                            if (fragmentHomeBinding11 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                fragmentHomeBinding11 = null;
                            }
                            fragmentHomeBinding11.imageNotification.setImageBitmap(resource);
                        }

                        @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                        public void onLoadFailed(Drawable errorDrawable) {
                            super.onLoadFailed(errorDrawable);
                            final DeviceBindViewModel.DevicePictureUI devicePictureUI2 = devicePictureUI;
                            final HomeFragment homeFragment2 = homeFragment;
                            ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass1, Unit>() { // from class: com.glasssutdio.wear.home.HomeFragment$loadDataData$5$1$onLoadFailed$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(HomeFragment.C09775.AnonymousClass1 anonymousClass1) {
                                    invoke2(anonymousClass1);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(HomeFragment.C09775.AnonymousClass1 ktxRunOnUi) {
                                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                    XLog.m137i(devicePictureUI2.getUrl());
                                    RequestBuilder<Drawable> requestBuilderLoad2 = Glide.with(homeFragment2.getActivity()).load(devicePictureUI2.getUrl());
                                    FragmentHomeBinding fragmentHomeBinding11 = homeFragment2.binding;
                                    if (fragmentHomeBinding11 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                                        fragmentHomeBinding11 = null;
                                    }
                                    requestBuilderLoad2.into(fragmentHomeBinding11.imageNotification);
                                }
                            });
                        }
                    });
                    return;
                }
                RequestBuilder<Drawable> requestBuilderLoad2 = Glide.with(HomeFragment.this).load(devicePictureUI.getUrl());
                FragmentHomeBinding fragmentHomeBinding11 = HomeFragment.this.binding;
                FragmentHomeBinding fragmentHomeBinding12 = null;
                if (fragmentHomeBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding11 = null;
                }
                RequestBuilder requestBuilderError = requestBuilderLoad2.placeholder(fragmentHomeBinding11.imageNotification.getDrawable()).dontAnimate().error(C0775R.mipmap.home_default_glasses);
                FragmentHomeBinding fragmentHomeBinding13 = HomeFragment.this.binding;
                if (fragmentHomeBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentHomeBinding12 = fragmentHomeBinding13;
                }
                requestBuilderError.into(fragmentHomeBinding12.imageNotification);
            }
        }));
        if (UserConfig.INSTANCE.getInstance().getHwVersion().length() > 0) {
            getDeviceViewModel().devicePicture(UserConfig.INSTANCE.getInstance().getHwVersion());
        }
        if (UserConfig.INSTANCE.getInstance().getSupportTranslate()) {
            FragmentHomeBinding fragmentHomeBinding11 = this.binding;
            if (fragmentHomeBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHomeBinding11 = null;
            }
            ViewKt.visible(fragmentHomeBinding11.cslAiTranslate);
        } else {
            FragmentHomeBinding fragmentHomeBinding12 = this.binding;
            if (fragmentHomeBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHomeBinding12 = null;
            }
            ViewKt.gone(fragmentHomeBinding12.cslAiTranslate);
        }
        if (UserConfig.INSTANCE.getInstance().getSupportVolumeControl()) {
            FragmentHomeBinding fragmentHomeBinding13 = this.binding;
            if (fragmentHomeBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHomeBinding13 = null;
            }
            ViewKt.visible(fragmentHomeBinding13.clsVolume);
            FragmentHomeBinding fragmentHomeBinding14 = this.binding;
            if (fragmentHomeBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHomeBinding14 = null;
            }
            fragmentHomeBinding14.tvTitleSubText2.setText(getString(C0775R.string.home_glass_2));
        } else {
            FragmentHomeBinding fragmentHomeBinding15 = this.binding;
            if (fragmentHomeBinding15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHomeBinding15 = null;
            }
            ViewKt.gone(fragmentHomeBinding15.clsVolume);
            FragmentHomeBinding fragmentHomeBinding16 = this.binding;
            if (fragmentHomeBinding16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHomeBinding16 = null;
            }
            fragmentHomeBinding16.tvTitleSubText2.setText(getString(C0775R.string.home_glass_2_1));
        }
        FragmentHomeBinding fragmentHomeBinding17 = this.binding;
        if (fragmentHomeBinding17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHomeBinding17 = null;
        }
        fragmentHomeBinding17.viewDisableVolume.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.HomeFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.loadDataData$lambda$9(this.f$0, view);
            }
        });
        refreshVolume();
        FragmentHomeBinding fragmentHomeBinding18 = this.binding;
        if (fragmentHomeBinding18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentHomeBinding2 = fragmentHomeBinding18;
        }
        fragmentHomeBinding2.pbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.glasssutdio.wear.home.HomeFragment.loadDataData.7
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar p0, int p1, boolean p2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                HomeFragment.this.setVolume(p0.getProgress());
                FragmentHomeBinding fragmentHomeBinding19 = HomeFragment.this.binding;
                if (fragmentHomeBinding19 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding19 = null;
                }
                fragmentHomeBinding19.pbVolumeDis.setProgress(p0.getProgress());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadDataData$lambda$7$lambda$0(HomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HomeFragment homeFragment = this$0;
        FragmentActivity activity = homeFragment.getActivity();
        if (activity != null) {
            ArrayList<Pair> arrayList = new ArrayList();
            Intrinsics.checkNotNull(activity);
            Intent intent = new Intent(activity, (Class<?>) VolumeActivity.class);
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
            homeFragment.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadDataData$lambda$7$lambda$1(HomeFragment this$0, View view) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Activity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        PermissionUtilKt.requestLocationPermission((FragmentActivity) activity, this$0.new LocationPermissionCallback());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadDataData$lambda$7$lambda$2(HomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HomeFragment homeFragment = this$0;
        FragmentActivity activity = homeFragment.getActivity();
        if (activity != null) {
            ArrayList<Pair> arrayList = new ArrayList();
            Intrinsics.checkNotNull(activity);
            Intent intent = new Intent(activity, (Class<?>) YourGlassActivity.class);
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
            homeFragment.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadDataData$lambda$7$lambda$5(final FragmentHomeBinding this_run, final HomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this_run.tvToBt.setText(this$0.getString(C0775R.string.ble_glass_39));
        BleOperateManager.getInstance().classicBluetoothStartScan();
        this_run.tvToBt.postDelayed(new Runnable() { // from class: com.glasssutdio.wear.home.HomeFragment$loadDataData$lambda$7$lambda$5$$inlined$Runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                this_run.tvToBt.setText(this$0.getString(C0775R.string.ble_glass_34));
            }
        }, DeviceOrientationRequest.OUTPUT_PERIOD_MEDIUM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadDataData$lambda$7$lambda$6(HomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!BleOperateManager.getInstance().isConnected()) {
            String string = this$0.getString(C0775R.string.ble_glass_18);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        if (UserConfig.INSTANCE.getInstance().getBattery() <= 30) {
            String string2 = this$0.getString(C0775R.string.ble_glass_21, "30");
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            GlobalKt.showToast$default(string2, 0, 1, null);
            return;
        }
        HomeFragment homeFragment = this$0;
        FragmentActivity activity = homeFragment.getActivity();
        if (activity != null) {
            ArrayList<Pair> arrayList = new ArrayList();
            Intrinsics.checkNotNull(activity);
            Intent intent = new Intent(activity, (Class<?>) OTAActivity.class);
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
            homeFragment.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadDataData$lambda$9(HomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (BleOperateManager.getInstance().isConnected()) {
            String string = this$0.getString(C0775R.string.ble_glass_32);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
        }
    }

    private final void changeUiByConnectStatue(boolean connected) {
        FragmentHomeBinding fragmentHomeBinding = this.binding;
        if (fragmentHomeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHomeBinding = null;
        }
        fragmentHomeBinding.tvVolumeTitle.setEnabled(connected);
        fragmentHomeBinding.tvTakePhoto.setEnabled(connected);
        fragmentHomeBinding.tvVideo.setEnabled(connected);
        fragmentHomeBinding.tvRecording.setEnabled(connected);
        fragmentHomeBinding.tvSmartImg.setEnabled(connected);
        fragmentHomeBinding.clsTakePhoto.setEnabled(connected);
        fragmentHomeBinding.clsVideo.setEnabled(connected);
        fragmentHomeBinding.clsRecording.setEnabled(connected);
        fragmentHomeBinding.clsSmartPicture.setEnabled(connected);
        fragmentHomeBinding.pbVolume.setEnabled(connected);
        fragmentHomeBinding.clsVolume.setEnabled(connected);
        fragmentHomeBinding.tvVolumeTitle.setEnabled(connected);
        if (connected) {
            fragmentHomeBinding.ivHomeVolume.setImageResource(C0775R.mipmap.ic_home_device_volume);
            fragmentHomeBinding.ivTakePhoto.setImageResource(C0775R.mipmap.iv_take_photo);
            fragmentHomeBinding.ivVideo.setImageResource(C0775R.mipmap.iv_record_video);
            fragmentHomeBinding.ivRecording.setImageResource(C0775R.mipmap.iv_recording_1);
            fragmentHomeBinding.ivSmartImg.setImageResource(C0775R.mipmap.ic_smart_img);
            fragmentHomeBinding.ivVolumeMore.setImageResource(C0775R.mipmap.ic_home_value_more);
            ViewKt.visible(fragmentHomeBinding.pbVolume);
            ViewKt.gone(fragmentHomeBinding.pbVolumeDis);
            fragmentHomeBinding.pbVolume.setEnabled(true);
            return;
        }
        fragmentHomeBinding.ivHomeVolume.setImageResource(C0775R.mipmap.ic_home_device_volume_dis);
        fragmentHomeBinding.ivTakePhoto.setImageResource(C0775R.mipmap.iv_take_photo_dis);
        fragmentHomeBinding.ivVideo.setImageResource(C0775R.mipmap.iv_record_video_dis);
        fragmentHomeBinding.ivRecording.setImageResource(C0775R.mipmap.iv_recording_1_dis);
        fragmentHomeBinding.ivSmartImg.setImageResource(C0775R.mipmap.ic_smart_img_dis);
        fragmentHomeBinding.ivVolumeMore.setImageResource(C0775R.mipmap.ic_home_value_more_dis);
        ViewKt.gone(fragmentHomeBinding.pbVolume);
        ViewKt.visible(fragmentHomeBinding.pbVolumeDis);
        fragmentHomeBinding.pbVolume.setEnabled(false);
    }

    private final void refreshVolume() throws NumberFormatException {
        try {
            String volumeControl = UserConfig.INSTANCE.getInstance().getVolumeControl();
            if (volumeControl.length() > 0) {
                List listSplit$default = StringsKt.split$default((CharSequence) volumeControl, new String[]{","}, false, 0, 6, (Object) null);
                int i = Integer.parseInt((String) listSplit$default.get(9));
                FragmentHomeBinding fragmentHomeBinding = null;
                if (i == 1) {
                    FragmentHomeBinding fragmentHomeBinding2 = this.binding;
                    if (fragmentHomeBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentHomeBinding2 = null;
                    }
                    fragmentHomeBinding2.tvVolumeTitle.setText(getString(C0775R.string.g_fire_10));
                    FragmentHomeBinding fragmentHomeBinding3 = this.binding;
                    if (fragmentHomeBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentHomeBinding3 = null;
                    }
                    fragmentHomeBinding3.pbVolume.setMax(Integer.parseInt((String) listSplit$default.get(1)));
                    FragmentHomeBinding fragmentHomeBinding4 = this.binding;
                    if (fragmentHomeBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentHomeBinding4 = null;
                    }
                    fragmentHomeBinding4.pbVolume.setProgress(Integer.parseInt((String) listSplit$default.get(2)));
                    FragmentHomeBinding fragmentHomeBinding5 = this.binding;
                    if (fragmentHomeBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentHomeBinding5 = null;
                    }
                    fragmentHomeBinding5.pbVolumeDis.setMax(Integer.parseInt((String) listSplit$default.get(1)));
                    FragmentHomeBinding fragmentHomeBinding6 = this.binding;
                    if (fragmentHomeBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentHomeBinding = fragmentHomeBinding6;
                    }
                    fragmentHomeBinding.pbVolumeDis.setProgress(Integer.parseInt((String) listSplit$default.get(2)));
                    return;
                }
                if (i == 2) {
                    FragmentHomeBinding fragmentHomeBinding7 = this.binding;
                    if (fragmentHomeBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentHomeBinding7 = null;
                    }
                    fragmentHomeBinding7.tvVolumeTitle.setText(getString(C0775R.string.g_fire_12));
                    FragmentHomeBinding fragmentHomeBinding8 = this.binding;
                    if (fragmentHomeBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentHomeBinding8 = null;
                    }
                    fragmentHomeBinding8.pbVolume.setMax(Integer.parseInt((String) listSplit$default.get(4)));
                    FragmentHomeBinding fragmentHomeBinding9 = this.binding;
                    if (fragmentHomeBinding9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentHomeBinding9 = null;
                    }
                    fragmentHomeBinding9.pbVolume.setProgress(Integer.parseInt((String) listSplit$default.get(5)));
                    FragmentHomeBinding fragmentHomeBinding10 = this.binding;
                    if (fragmentHomeBinding10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentHomeBinding10 = null;
                    }
                    fragmentHomeBinding10.pbVolumeDis.setMax(Integer.parseInt((String) listSplit$default.get(4)));
                    FragmentHomeBinding fragmentHomeBinding11 = this.binding;
                    if (fragmentHomeBinding11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentHomeBinding = fragmentHomeBinding11;
                    }
                    fragmentHomeBinding.pbVolumeDis.setProgress(Integer.parseInt((String) listSplit$default.get(5)));
                    return;
                }
                if (i != 3) {
                    return;
                }
                FragmentHomeBinding fragmentHomeBinding12 = this.binding;
                if (fragmentHomeBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding12 = null;
                }
                fragmentHomeBinding12.tvVolumeTitle.setText(getString(C0775R.string.g_fire_11));
                FragmentHomeBinding fragmentHomeBinding13 = this.binding;
                if (fragmentHomeBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding13 = null;
                }
                fragmentHomeBinding13.pbVolume.setMax(Integer.parseInt((String) listSplit$default.get(7)));
                FragmentHomeBinding fragmentHomeBinding14 = this.binding;
                if (fragmentHomeBinding14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding14 = null;
                }
                fragmentHomeBinding14.pbVolume.setProgress(Integer.parseInt((String) listSplit$default.get(8)));
                FragmentHomeBinding fragmentHomeBinding15 = this.binding;
                if (fragmentHomeBinding15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding15 = null;
                }
                fragmentHomeBinding15.pbVolumeDis.setMax(Integer.parseInt((String) listSplit$default.get(7)));
                FragmentHomeBinding fragmentHomeBinding16 = this.binding;
                if (fragmentHomeBinding16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentHomeBinding = fragmentHomeBinding16;
                }
                fragmentHomeBinding.pbVolumeDis.setProgress(Integer.parseInt((String) listSplit$default.get(8)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setVolume(int volumeValue) {
        try {
            String volumeControl = UserConfig.INSTANCE.getInstance().getVolumeControl();
            if (volumeControl.length() > 0) {
                List mutableList = CollectionsKt.toMutableList((Collection) StringsKt.split$default((CharSequence) volumeControl, new String[]{","}, false, 0, 6, (Object) null));
                XLog.m137i(volumeControl);
                if (Integer.parseInt((String) mutableList.get(9)) == 1) {
                    mutableList.set(2, String.valueOf(volumeValue));
                } else if (Integer.parseInt((String) mutableList.get(9)) == 2) {
                    mutableList.set(5, String.valueOf(volumeValue));
                } else if (Integer.parseInt((String) mutableList.get(9)) == 3) {
                    mutableList.set(8, String.valueOf(volumeValue));
                }
                LargeDataHandler.getInstance().setVolumeControl(Integer.parseInt((String) mutableList.get(0)), Integer.parseInt((String) mutableList.get(1)), Integer.parseInt((String) mutableList.get(2)), Integer.parseInt((String) mutableList.get(3)), Integer.parseInt((String) mutableList.get(4)), Integer.parseInt((String) mutableList.get(5)), Integer.parseInt((String) mutableList.get(6)), Integer.parseInt((String) mutableList.get(7)), Integer.parseInt((String) mutableList.get(8)), Integer.parseInt((String) mutableList.get(9)));
                try {
                    this.needRefreshVolume = false;
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    private final void showBatteryLowDialog(int battery) {
        boolean lowBattery = UserConfig.INSTANCE.getInstance().getLowBattery();
        FragmentHomeBinding fragmentHomeBinding = this.binding;
        if (fragmentHomeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHomeBinding = null;
        }
        fragmentHomeBinding.batteryView.isLowBattery(lowBattery);
        if (lowBattery && !UserConfig.INSTANCE.getInstance().isShowedBatteryLow()) {
            UserConfig.INSTANCE.getInstance().setShowedBatteryLow(true);
            CommonDialog.Builder builder = new CommonDialog.Builder();
            String string = getString(C0775R.string.h_glass_326);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            CommonDialog.Builder title = builder.setTitle(string);
            String string2 = getString(C0775R.string.h_glass_327);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            CommonDialog.Builder content = title.setContent(string2);
            String string3 = getString(C0775R.string.h_glass_330);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            CommonDialog.Builder confirmMessage = content.setConfirmMessage(string3);
            String string4 = getString(C0775R.string.h_glass_cancel);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
            CommonDialog commonDialogBuild = confirmMessage.setCancelMessage(string4).build();
            commonDialogBuild.show(getChildFragmentManager(), "showBatteryLowDialog");
            commonDialogBuild.setOnConfirmListener(new Function1<View, Unit>() { // from class: com.glasssutdio.wear.home.HomeFragment.showBatteryLowDialog.1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view) {
                    invoke2(view);
                    return Unit.INSTANCE;
                }
            });
        }
        UserConfig.INSTANCE.getInstance().setShowedBatteryLow(lowBattery);
    }

    private final void showMemoryLowDialog() {
        if (UserConfig.INSTANCE.getInstance().isShowedMemoryLow()) {
            return;
        }
        UserConfig.INSTANCE.getInstance().setShowedMemoryLow(true);
        CommonDialog.Builder builder = new CommonDialog.Builder();
        String string = getString(C0775R.string.h_glass_328);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        CommonDialog.Builder title = builder.setTitle(string);
        String string2 = getString(C0775R.string.h_glass_329);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        CommonDialog.Builder content = title.setContent(string2);
        String string3 = getString(C0775R.string.h_glass_330);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        CommonDialog.Builder confirmMessage = content.setConfirmMessage(string3);
        String string4 = getString(C0775R.string.h_glass_cancel);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        CommonDialog commonDialogBuild = confirmMessage.setCancelMessage(string4).build();
        commonDialogBuild.show(getChildFragmentManager(), "showMemoryLowDialog");
        commonDialogBuild.setOnConfirmListener(new Function1<View, Unit>() { // from class: com.glasssutdio.wear.home.HomeFragment.showMemoryLowDialog.1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }
        });
    }

    @Override // com.glasssutdio.wear.home.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentHomeBinding fragmentHomeBinding = null;
        if (!UserConfig.INSTANCE.getInstance().getDeviceBind()) {
            FragmentHomeBinding fragmentHomeBinding2 = this.binding;
            if (fragmentHomeBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHomeBinding2 = null;
            }
            ViewKt.visible(fragmentHomeBinding2.noBindDevice);
        } else {
            FragmentHomeBinding fragmentHomeBinding3 = this.binding;
            if (fragmentHomeBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHomeBinding3 = null;
            }
            ViewKt.gone(fragmentHomeBinding3.noBindDevice);
        }
        FragmentHomeBinding fragmentHomeBinding4 = this.binding;
        if (fragmentHomeBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHomeBinding4 = null;
        }
        fragmentHomeBinding4.tvDeviceName.setText(UserConfig.INSTANCE.getInstance().getDeviceName());
        if (BleOperateManager.getInstance().isConnected()) {
            changeUiByConnectStatue(true);
            FragmentHomeBinding fragmentHomeBinding5 = this.binding;
            if (fragmentHomeBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHomeBinding5 = null;
            }
            fragmentHomeBinding5.tvBleStatus.setText(getString(C0775R.string.h_glass_110));
            FragmentHomeBinding fragmentHomeBinding6 = this.binding;
            if (fragmentHomeBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHomeBinding6 = null;
            }
            TextView tvBleStatus = fragmentHomeBinding6.tvBleStatus;
            Intrinsics.checkNotNullExpressionValue(tvBleStatus, "tvBleStatus");
            TextViewExtKt.setDrawableWithPadding$default(tvBleStatus, C0775R.mipmap.ble_connected_status, GlobalKt.getDp((Number) 4), null, 4, null);
            FragmentHomeBinding fragmentHomeBinding7 = this.binding;
            if (fragmentHomeBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHomeBinding7 = null;
            }
            ViewKt.visible(fragmentHomeBinding7.textDeviceBattery);
            FragmentHomeBinding fragmentHomeBinding8 = this.binding;
            if (fragmentHomeBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHomeBinding8 = null;
            }
            ViewKt.visible(fragmentHomeBinding8.batteryView);
            FragmentHomeBinding fragmentHomeBinding9 = this.binding;
            if (fragmentHomeBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHomeBinding9 = null;
            }
            ViewKt.gone(fragmentHomeBinding9.viewDisableVolume);
            if (BleScannerHelper.getInstance().isMacSystemBond(UserConfig.INSTANCE.getInstance().getDeviceAddress())) {
                FragmentHomeBinding fragmentHomeBinding10 = this.binding;
                if (fragmentHomeBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding10 = null;
                }
                ViewKt.gone(fragmentHomeBinding10.ctlBt);
                FragmentHomeBinding fragmentHomeBinding11 = this.binding;
                if (fragmentHomeBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentHomeBinding = fragmentHomeBinding11;
                }
                ViewKt.gone(fragmentHomeBinding.viewDisableVolume);
                return;
            }
            FragmentHomeBinding fragmentHomeBinding12 = this.binding;
            if (fragmentHomeBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHomeBinding12 = null;
            }
            ViewKt.visible(fragmentHomeBinding12.ctlBt);
            FragmentHomeBinding fragmentHomeBinding13 = this.binding;
            if (fragmentHomeBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentHomeBinding = fragmentHomeBinding13;
            }
            ViewKt.visible(fragmentHomeBinding.viewDisableVolume);
            return;
        }
        changeUiByConnectStatue(false);
        FragmentHomeBinding fragmentHomeBinding14 = this.binding;
        if (fragmentHomeBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHomeBinding14 = null;
        }
        fragmentHomeBinding14.tvBleStatus.setText(getString(C0775R.string.h_glass_111));
        FragmentHomeBinding fragmentHomeBinding15 = this.binding;
        if (fragmentHomeBinding15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHomeBinding15 = null;
        }
        TextView tvBleStatus2 = fragmentHomeBinding15.tvBleStatus;
        Intrinsics.checkNotNullExpressionValue(tvBleStatus2, "tvBleStatus");
        TextViewExtKt.setDrawableWithPadding$default(tvBleStatus2, C0775R.mipmap.ble_disconnect_status, GlobalKt.getDp((Number) 4), null, 4, null);
        FragmentHomeBinding fragmentHomeBinding16 = this.binding;
        if (fragmentHomeBinding16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHomeBinding16 = null;
        }
        ViewKt.gone(fragmentHomeBinding16.textDeviceBattery);
        FragmentHomeBinding fragmentHomeBinding17 = this.binding;
        if (fragmentHomeBinding17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentHomeBinding = fragmentHomeBinding17;
        }
        ViewKt.visible(fragmentHomeBinding.viewDisableVolume);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(BusEvent messageEvent) throws NumberFormatException {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        FragmentHomeBinding fragmentHomeBinding = null;
        if (messageEvent instanceof BluetoothEvent) {
            BluetoothEvent bluetoothEvent = (BluetoothEvent) messageEvent;
            XLog.m137i("ble connect status：" + (!bluetoothEvent.getConnect()));
            if (!bluetoothEvent.getConnect()) {
                FragmentHomeBinding fragmentHomeBinding2 = this.binding;
                if (fragmentHomeBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding2 = null;
                }
                fragmentHomeBinding2.tvBleStatus.setText(getString(C0775R.string.h_glass_111));
                FragmentHomeBinding fragmentHomeBinding3 = this.binding;
                if (fragmentHomeBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding3 = null;
                }
                TextView tvBleStatus = fragmentHomeBinding3.tvBleStatus;
                Intrinsics.checkNotNullExpressionValue(tvBleStatus, "tvBleStatus");
                TextViewExtKt.setDrawableWithPadding$default(tvBleStatus, C0775R.mipmap.ble_disconnect_status, GlobalKt.getDp((Number) 4), null, 4, null);
                FragmentHomeBinding fragmentHomeBinding4 = this.binding;
                if (fragmentHomeBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding4 = null;
                }
                ViewKt.gone(fragmentHomeBinding4.textDeviceBattery);
                FragmentHomeBinding fragmentHomeBinding5 = this.binding;
                if (fragmentHomeBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding5 = null;
                }
                ViewKt.gone(fragmentHomeBinding5.batteryView);
                changeUiByConnectStatue(false);
                if (!UserConfig.INSTANCE.getInstance().getDeviceBind()) {
                    FragmentHomeBinding fragmentHomeBinding6 = this.binding;
                    if (fragmentHomeBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentHomeBinding6 = null;
                    }
                    ViewKt.visible(fragmentHomeBinding6.noBindDevice);
                    if (UserConfig.INSTANCE.getInstance().getDeviceAddress().length() == 0) {
                        FragmentHomeBinding fragmentHomeBinding7 = this.binding;
                        if (fragmentHomeBinding7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            fragmentHomeBinding = fragmentHomeBinding7;
                        }
                        ViewKt.visible(fragmentHomeBinding.noBindDevice);
                        return;
                    }
                    FragmentHomeBinding fragmentHomeBinding8 = this.binding;
                    if (fragmentHomeBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentHomeBinding = fragmentHomeBinding8;
                    }
                    ViewKt.gone(fragmentHomeBinding.noBindDevice);
                    return;
                }
                FragmentHomeBinding fragmentHomeBinding9 = this.binding;
                if (fragmentHomeBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding9 = null;
                }
                ViewKt.gone(fragmentHomeBinding9.noBindDevice);
                FragmentHomeBinding fragmentHomeBinding10 = this.binding;
                if (fragmentHomeBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentHomeBinding = fragmentHomeBinding10;
                }
                ViewKt.visible(fragmentHomeBinding.viewDisableVolume);
                return;
            }
            FragmentHomeBinding fragmentHomeBinding11 = this.binding;
            if (fragmentHomeBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHomeBinding11 = null;
            }
            ViewKt.gone(fragmentHomeBinding11.viewDisableVolume);
            if (!UserConfig.INSTANCE.getInstance().isShowedDeviceGuided()) {
                UserConfig.INSTANCE.getInstance().setShowedDeviceGuided(true);
                HomeFragment homeFragment = this;
                FragmentActivity activity = homeFragment.getActivity();
                if (activity != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNull(activity);
                    Intent intent = new Intent(activity, (Class<?>) UserSplashGuideActivity.class);
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
                    Unit unit2 = Unit.INSTANCE;
                    Unit unit3 = Unit.INSTANCE;
                    homeFragment.startActivity(intent);
                    Unit unit4 = Unit.INSTANCE;
                    Unit unit5 = Unit.INSTANCE;
                }
            }
            try {
                FragmentHomeBinding fragmentHomeBinding12 = this.binding;
                if (fragmentHomeBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding12 = null;
                }
                fragmentHomeBinding12.tvDeviceName.setText(UserConfig.INSTANCE.getInstance().getDeviceNameNoClear());
                FragmentHomeBinding fragmentHomeBinding13 = this.binding;
                if (fragmentHomeBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding13 = null;
                }
                ViewKt.gone(fragmentHomeBinding13.noBindDevice);
                FragmentHomeBinding fragmentHomeBinding14 = this.binding;
                if (fragmentHomeBinding14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding14 = null;
                }
                ViewKt.visible(fragmentHomeBinding14.batteryView);
                FragmentHomeBinding fragmentHomeBinding15 = this.binding;
                if (fragmentHomeBinding15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding15 = null;
                }
                fragmentHomeBinding15.tvDeviceName.setText(UserConfig.INSTANCE.getInstance().getDeviceName());
                FragmentHomeBinding fragmentHomeBinding16 = this.binding;
                if (fragmentHomeBinding16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding16 = null;
                }
                fragmentHomeBinding16.tvBleStatus.setText(getString(C0775R.string.h_glass_110));
                FragmentHomeBinding fragmentHomeBinding17 = this.binding;
                if (fragmentHomeBinding17 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding17 = null;
                }
                TextView tvBleStatus2 = fragmentHomeBinding17.tvBleStatus;
                Intrinsics.checkNotNullExpressionValue(tvBleStatus2, "tvBleStatus");
                TextViewExtKt.setDrawableWithPadding$default(tvBleStatus2, C0775R.mipmap.ble_connected_status, GlobalKt.getDp((Number) 4), null, 4, null);
                FragmentHomeBinding fragmentHomeBinding18 = this.binding;
                if (fragmentHomeBinding18 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding18 = null;
                }
                ViewKt.visible(fragmentHomeBinding18.textDeviceBattery);
                FragmentHomeBinding fragmentHomeBinding19 = this.binding;
                if (fragmentHomeBinding19 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding19 = null;
                }
                fragmentHomeBinding19.batteryView.setBatteryPercentage(UserConfig.INSTANCE.getInstance().getBattery());
                FragmentHomeBinding fragmentHomeBinding20 = this.binding;
                if (fragmentHomeBinding20 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding20 = null;
                }
                fragmentHomeBinding20.textDeviceBattery.setText(new StringBuilder().append(UserConfig.INSTANCE.getInstance().getBattery()).append('%').toString());
                showBatteryLowDialog(UserConfig.INSTANCE.getInstance().getBattery());
                if (UserConfig.INSTANCE.getInstance().getLowBattery()) {
                    FragmentHomeBinding fragmentHomeBinding21 = this.binding;
                    if (fragmentHomeBinding21 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentHomeBinding = fragmentHomeBinding21;
                    }
                    fragmentHomeBinding.textDeviceBattery.setTextColor(ContextCompat.getColor(getActivity(), C0775R.color.color_FF0303));
                } else {
                    FragmentHomeBinding fragmentHomeBinding22 = this.binding;
                    if (fragmentHomeBinding22 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentHomeBinding = fragmentHomeBinding22;
                    }
                    fragmentHomeBinding.textDeviceBattery.setTextColor(ContextCompat.getColor(getActivity(), C0775R.color.g_color_6));
                }
                changeUiByConnectStatue(true);
                EventBus.getDefault().post(new AlbumRefreshEvent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (UserConfig.INSTANCE.getInstance().getHwVersion().length() > 0) {
                getDeviceViewModel().devicePicture(UserConfig.INSTANCE.getInstance().getHwVersion());
            }
            ThreadExtKt.ktxRunOnUiDelay(this, 120L, new Function1<HomeFragment, Unit>() { // from class: com.glasssutdio.wear.home.HomeFragment.onMessageEvent.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HomeFragment homeFragment2) {
                    invoke2(homeFragment2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HomeFragment ktxRunOnUiDelay) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUiDelay, "$this$ktxRunOnUiDelay");
                    FragmentHomeBinding fragmentHomeBinding23 = null;
                    if (BleScannerHelper.getInstance().isMacSystemBond(UserConfig.INSTANCE.getInstance().getDeviceAddress())) {
                        FragmentHomeBinding fragmentHomeBinding24 = ktxRunOnUiDelay.binding;
                        if (fragmentHomeBinding24 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            fragmentHomeBinding23 = fragmentHomeBinding24;
                        }
                        ViewKt.gone(fragmentHomeBinding23.ctlBt);
                        return;
                    }
                    FragmentHomeBinding fragmentHomeBinding25 = ktxRunOnUiDelay.binding;
                    if (fragmentHomeBinding25 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentHomeBinding23 = fragmentHomeBinding25;
                    }
                    ViewKt.visible(fragmentHomeBinding23.ctlBt);
                }
            });
            ThreadExtKt.ktxRunOnUiDelay(this, 3000L, new Function1<HomeFragment, Unit>() { // from class: com.glasssutdio.wear.home.HomeFragment.onMessageEvent.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HomeFragment homeFragment2) {
                    invoke2(homeFragment2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HomeFragment ktxRunOnUiDelay) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUiDelay, "$this$ktxRunOnUiDelay");
                    if (BleScannerHelper.getInstance().isMacSystemBond(UserConfig.INSTANCE.getInstance().getDeviceAddress())) {
                        return;
                    }
                    FragmentHomeBinding fragmentHomeBinding23 = ktxRunOnUiDelay.binding;
                    if (fragmentHomeBinding23 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentHomeBinding23 = null;
                    }
                    fragmentHomeBinding23.tvToBt.performClick();
                }
            });
            return;
        }
        if (messageEvent instanceof GlassesBatteryUpdateEvent) {
            XLog.m137i("电量:" + UserConfig.INSTANCE.getInstance().getBattery());
            FragmentHomeBinding fragmentHomeBinding23 = this.binding;
            if (fragmentHomeBinding23 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHomeBinding23 = null;
            }
            fragmentHomeBinding23.batteryView.setBatteryPercentage(UserConfig.INSTANCE.getInstance().getBattery());
            FragmentHomeBinding fragmentHomeBinding24 = this.binding;
            if (fragmentHomeBinding24 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHomeBinding24 = null;
            }
            fragmentHomeBinding24.textDeviceBattery.setText(new StringBuilder().append(UserConfig.INSTANCE.getInstance().getBattery()).append('%').toString());
            if (UserConfig.INSTANCE.getInstance().getLowBattery()) {
                FragmentHomeBinding fragmentHomeBinding25 = this.binding;
                if (fragmentHomeBinding25 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding25 = null;
                }
                fragmentHomeBinding25.textDeviceBattery.setTextColor(ContextCompat.getColor(getActivity(), C0775R.color.color_FF0303));
            } else {
                FragmentHomeBinding fragmentHomeBinding26 = this.binding;
                if (fragmentHomeBinding26 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding26 = null;
                }
                fragmentHomeBinding26.textDeviceBattery.setTextColor(ContextCompat.getColor(getActivity(), C0775R.color.g_color_6));
            }
            showBatteryLowDialog(UserConfig.INSTANCE.getInstance().getBattery());
            FragmentHomeBinding fragmentHomeBinding27 = this.binding;
            if (fragmentHomeBinding27 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentHomeBinding27 = null;
            }
            fragmentHomeBinding27.batteryView.isLowBattery(UserConfig.INSTANCE.getInstance().getLowBattery());
            GlassesBatteryUpdateEvent glassesBatteryUpdateEvent = (GlassesBatteryUpdateEvent) messageEvent;
            UserConfig.INSTANCE.getInstance().setChanging(glassesBatteryUpdateEvent.getChanging());
            FragmentHomeBinding fragmentHomeBinding28 = this.binding;
            if (fragmentHomeBinding28 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentHomeBinding = fragmentHomeBinding28;
            }
            fragmentHomeBinding.batteryView.isCharging(glassesBatteryUpdateEvent.getChanging());
            return;
        }
        if (messageEvent instanceof EventType) {
            EventType eventType = (EventType) messageEvent;
            if (eventType.getType() == 3) {
                if (UserConfig.INSTANCE.getInstance().getBattery() == 0) {
                    LargeDataHandler.getInstance().syncBattery();
                    return;
                }
                return;
            }
            if (eventType.getType() == 11 || eventType.getType() == 12) {
                FragmentHomeBinding fragmentHomeBinding29 = this.binding;
                if (fragmentHomeBinding29 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentHomeBinding = fragmentHomeBinding29;
                }
                ViewKt.gone(fragmentHomeBinding.ctlOta);
                return;
            }
            if (eventType.getType() == 2) {
                checkOta(UserConfig.INSTANCE.getInstance().getHwVersionWifi(), UserConfig.INSTANCE.getInstance().getFmVersionWifi(), true);
                return;
            }
            if (eventType.getType() == 14) {
                FragmentHomeBinding fragmentHomeBinding30 = this.binding;
                if (fragmentHomeBinding30 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding30 = null;
                }
                ViewKt.gone(fragmentHomeBinding30.ctlBt);
                FragmentHomeBinding fragmentHomeBinding31 = this.binding;
                if (fragmentHomeBinding31 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentHomeBinding = fragmentHomeBinding31;
                }
                ViewKt.gone(fragmentHomeBinding.viewDisableVolume);
                return;
            }
            if (eventType.getType() == 15) {
                FragmentHomeBinding fragmentHomeBinding32 = this.binding;
                if (fragmentHomeBinding32 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding32 = null;
                }
                ViewKt.visible(fragmentHomeBinding32.ctlBt);
                FragmentHomeBinding fragmentHomeBinding33 = this.binding;
                if (fragmentHomeBinding33 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentHomeBinding = fragmentHomeBinding33;
                }
                ViewKt.visible(fragmentHomeBinding.viewDisableVolume);
                return;
            }
            if (eventType.getType() == 16) {
                if (UserConfig.INSTANCE.getInstance().getSupportTranslate()) {
                    FragmentHomeBinding fragmentHomeBinding34 = this.binding;
                    if (fragmentHomeBinding34 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentHomeBinding34 = null;
                    }
                    ViewKt.visible(fragmentHomeBinding34.cslAiTranslate);
                } else {
                    FragmentHomeBinding fragmentHomeBinding35 = this.binding;
                    if (fragmentHomeBinding35 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentHomeBinding35 = null;
                    }
                    ViewKt.gone(fragmentHomeBinding35.cslAiTranslate);
                }
                if (UserConfig.INSTANCE.getInstance().getSupportVolumeControl()) {
                    FragmentHomeBinding fragmentHomeBinding36 = this.binding;
                    if (fragmentHomeBinding36 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentHomeBinding36 = null;
                    }
                    ViewKt.visible(fragmentHomeBinding36.clsVolume);
                    FragmentHomeBinding fragmentHomeBinding37 = this.binding;
                    if (fragmentHomeBinding37 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentHomeBinding = fragmentHomeBinding37;
                    }
                    fragmentHomeBinding.tvTitleSubText2.setText(getString(C0775R.string.home_glass_2));
                    return;
                }
                FragmentHomeBinding fragmentHomeBinding38 = this.binding;
                if (fragmentHomeBinding38 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentHomeBinding38 = null;
                }
                ViewKt.gone(fragmentHomeBinding38.clsVolume);
                FragmentHomeBinding fragmentHomeBinding39 = this.binding;
                if (fragmentHomeBinding39 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentHomeBinding = fragmentHomeBinding39;
                }
                fragmentHomeBinding.tvTitleSubText2.setText(getString(C0775R.string.home_glass_2_1));
                return;
            }
            if (eventType.getType() == 19) {
                if (this.needRefreshVolume) {
                    refreshVolume();
                }
                this.needRefreshVolume = true;
            }
        }
    }

    private final void readDeviceBattery() {
        LargeDataHandler.getInstance().addBatteryCallBack("home", new ILargeDataResponse() { // from class: com.glasssutdio.wear.home.HomeFragment$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i, BaseResponse baseResponse) {
                HomeFragment.readDeviceBattery$lambda$11(this.f$0, i, (BatteryResponse) baseResponse);
            }
        });
        LargeDataHandler.getInstance().syncBattery();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void readDeviceBattery$lambda$11(HomeFragment this$0, int i, BatteryResponse batteryResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UserConfig.INSTANCE.getInstance().setBattery(batteryResponse.getBattery());
        UserConfig companion = UserConfig.INSTANCE.getInstance();
        int battery = batteryResponse.getBattery();
        boolean z = false;
        if (1 <= battery && battery < 16) {
            z = true;
        }
        companion.setLowBattery(z);
        FragmentHomeBinding fragmentHomeBinding = this$0.binding;
        FragmentHomeBinding fragmentHomeBinding2 = null;
        if (fragmentHomeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHomeBinding = null;
        }
        fragmentHomeBinding.batteryView.setBatteryPercentage(batteryResponse.getBattery());
        FragmentHomeBinding fragmentHomeBinding3 = this$0.binding;
        if (fragmentHomeBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentHomeBinding3 = null;
        }
        fragmentHomeBinding3.textDeviceBattery.setText(new StringBuilder().append(batteryResponse.getBattery()).append('%').toString());
        if (UserConfig.INSTANCE.getInstance().getLowBattery()) {
            FragmentHomeBinding fragmentHomeBinding4 = this$0.binding;
            if (fragmentHomeBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentHomeBinding2 = fragmentHomeBinding4;
            }
            fragmentHomeBinding2.textDeviceBattery.setTextColor(ContextCompat.getColor(this$0.getActivity(), C0775R.color.color_FF0303));
        } else {
            FragmentHomeBinding fragmentHomeBinding5 = this$0.binding;
            if (fragmentHomeBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentHomeBinding2 = fragmentHomeBinding5;
            }
            fragmentHomeBinding2.textDeviceBattery.setTextColor(ContextCompat.getColor(this$0.getActivity(), C0775R.color.g_color_6));
        }
        this$0.showBatteryLowDialog(batteryResponse.getBattery());
    }

    /* compiled from: HomeFragment.kt */
    @Metadata(m606d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, m607d2 = {"Lcom/glasssutdio/wear/home/HomeFragment$LocationPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/glasssutdio/wear/home/HomeFragment;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class LocationPermissionCallback implements OnPermissionCallback {
        public LocationPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            XLog.m136i(permissions);
            HomeFragment homeFragment = HomeFragment.this;
            FragmentActivity activity = homeFragment.getActivity();
            if (activity != null) {
                ArrayList<Pair> arrayList = new ArrayList();
                Intrinsics.checkNotNull(activity);
                Intent intent = new Intent(activity, (Class<?>) BindGuideActivity.class);
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
                homeFragment.startActivity(intent);
            }
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            super.onDenied(permissions, never);
            String string = HomeFragment.this.getString(C0775R.string.ble_glass_20);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
            XLog.m136i(permissions);
            XLog.m136i(Boolean.valueOf(never));
        }
    }

    /* compiled from: HomeFragment.kt */
    @Metadata(m606d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, m607d2 = {"Lcom/glasssutdio/wear/home/HomeFragment$StoragePermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/glasssutdio/wear/home/HomeFragment;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class StoragePermissionCallback implements OnPermissionCallback {
        public StoragePermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            if (all) {
                return;
            }
            XLog.m136i(permissions);
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            super.onDenied(permissions, never);
            XLog.m136i(permissions);
            XLog.m136i(Boolean.valueOf(never));
        }
    }

    @Override // com.glasssutdio.wear.home.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* compiled from: HomeFragment.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.HomeFragment$checkOta$1", m620f = "HomeFragment.kt", m621i = {0}, m622l = {815, 818}, m623m = "invokeSuspend", m624n = {"$this$launch"}, m625s = {"L$0"})
    /* renamed from: com.glasssutdio.wear.home.HomeFragment$checkOta$1 */
    static final class C09741 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $fmVersion;
        final /* synthetic */ String $hwName;
        final /* synthetic */ boolean $wifiNotBle;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ HomeFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09741(String str, String str2, boolean z, HomeFragment homeFragment, Continuation<? super C09741> continuation) {
            super(2, continuation);
            this.$hwName = str;
            this.$fmVersion = str2;
            this.$wifiNotBle = z;
            this.this$0 = homeFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09741 c09741 = new C09741(this.$hwName, this.$fmVersion, this.$wifiNotBle, this.this$0, continuation);
            c09741.L$0 = obj;
            return c09741;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C09741) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            final boolean z = this.$wifiNotBle;
            final HomeFragment homeFragment = this.this$0;
            this.L$0 = null;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.glasssutdio.wear.home.HomeFragment.checkOta.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<FirmwareOtaResp>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(final NetState<FirmwareOtaResp> netState, Continuation<? super Unit> continuation) {
                    XLog.m137i(GsonInstance.INSTANCE.getGson().toJson(netState));
                    if (netState.getRetCode() == 0) {
                        CoroutineScope coroutineScope2 = coroutineScope;
                        final HomeFragment homeFragment2 = homeFragment;
                        ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.glasssutdio.wear.home.HomeFragment.checkOta.1.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                HomeFragment homeFragment3;
                                FragmentActivity activity;
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                FragmentHomeBinding fragmentHomeBinding = null;
                                if (BleOperateManager.getInstance().isConnected()) {
                                    FragmentHomeBinding fragmentHomeBinding2 = homeFragment2.binding;
                                    if (fragmentHomeBinding2 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    } else {
                                        fragmentHomeBinding = fragmentHomeBinding2;
                                    }
                                    ViewKt.visible(fragmentHomeBinding.ctlOta);
                                    FirmwareOtaResp firmwareOtaRespIsSuccess = netState.isSuccess();
                                    Intrinsics.checkNotNull(firmwareOtaRespIsSuccess);
                                    String isEnforceUpdate = firmwareOtaRespIsSuccess.getIsEnforceUpdate();
                                    if (isEnforceUpdate.length() <= 0 || Integer.parseInt(isEnforceUpdate) != 2 || (activity = (homeFragment3 = homeFragment2).getActivity()) == null) {
                                        return;
                                    }
                                    ArrayList<Pair> arrayList = new ArrayList();
                                    Intrinsics.checkNotNull(activity);
                                    Intent intent = new Intent(activity, (Class<?>) OTAActivity.class);
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
                                    homeFragment3.startActivity(intent);
                                    return;
                                }
                                FragmentHomeBinding fragmentHomeBinding3 = homeFragment2.binding;
                                if (fragmentHomeBinding3 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                } else {
                                    fragmentHomeBinding = fragmentHomeBinding3;
                                }
                                ViewKt.gone(fragmentHomeBinding.ctlOta);
                            }
                        });
                    } else {
                        CoroutineScope coroutineScope3 = coroutineScope;
                        final HomeFragment homeFragment3 = homeFragment;
                        ThreadExtKt.ktxRunOnUi(coroutineScope3, new Function1<CoroutineScope, Unit>() { // from class: com.glasssutdio.wear.home.HomeFragment.checkOta.1.1.2
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope4) {
                                invoke2(coroutineScope4);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(CoroutineScope ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                FragmentHomeBinding fragmentHomeBinding = homeFragment3.binding;
                                if (fragmentHomeBinding == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    fragmentHomeBinding = null;
                                }
                                ViewKt.gone(fragmentHomeBinding.ctlOta);
                            }
                        });
                        if (z) {
                            homeFragment.checkOta(UserConfig.INSTANCE.getInstance().getHwVersion(), UserConfig.INSTANCE.getInstance().getFmVersion(), false);
                        }
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
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C09741(hwName, fmVersion, wifiNotBle, this, null), 3, null);
    }

    /* compiled from: HomeFragment.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, m607d2 = {"Lcom/glasssutdio/wear/home/HomeFragment$Companion;", "", "()V", "newInstance", "Lcom/glasssutdio/wear/home/HomeFragment;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HomeFragment newInstance() {
            return new HomeFragment();
        }
    }
}
