package com.glasssutdio.wear.setting;

import android.os.Bundle;
import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.bean.CommonSelectModel;
import com.glasssutdio.wear.all.dialog.CommonSelectDialog;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.GsonInstance;
import com.glasssutdio.wear.bus.BluetoothEvent;
import com.glasssutdio.wear.bus.BusEvent;
import com.glasssutdio.wear.databinding.ActivityRecordSettingBinding;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.ILargeDataResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.resp.BaseResponse;
import com.oudmon.ble.base.communication.bigData.resp.GlassModelControlResponse;
import com.tencent.mmkv.MMKV;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: RecordSettingActivity.kt */
@Metadata(m606d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0017J\b\u0010\u0014\u001a\u00020\rH\u0014J\b\u0010\u0015\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m607d2 = {"Lcom/glasssutdio/wear/setting/RecordSettingActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityRecordSettingBinding;", "callback", "Lcom/oudmon/ble/base/communication/ILargeDataResponse;", "Lcom/oudmon/ble/base/communication/bigData/resp/GlassModelControlResponse;", "directionIndex", "", "durationAudioIndex", "durationIndex", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/glasssutdio/wear/bus/BusEvent;", "onResume", "updateIndex", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class RecordSettingActivity extends BaseSettingActivity {
    private ActivityRecordSettingBinding binding;
    private int directionIndex;
    private int durationIndex = 3;
    private int durationAudioIndex = 2;
    private final ILargeDataResponse<GlassModelControlResponse> callback = new ILargeDataResponse() { // from class: com.glasssutdio.wear.setting.RecordSettingActivity$$ExternalSyntheticLambda0
        @Override // com.oudmon.ble.base.communication.ILargeDataResponse
        public final void parseData(int i, BaseResponse baseResponse) {
            RecordSettingActivity.callback$lambda$0(this.f$0, i, (GlassModelControlResponse) baseResponse);
        }
    };

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityRecordSettingBinding activityRecordSettingBindingInflate = ActivityRecordSettingBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityRecordSettingBindingInflate, "inflate(...)");
        this.binding = activityRecordSettingBindingInflate;
        if (activityRecordSettingBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRecordSettingBindingInflate = null;
        }
        setContentView(activityRecordSettingBindingInflate.getRoot());
        initView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void callback$lambda$0(RecordSettingActivity this$0, int i, GlassModelControlResponse glassModelControlResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.m137i(GsonInstance.INSTANCE.getGson().toJson(glassModelControlResponse));
        ActivityRecordSettingBinding activityRecordSettingBinding = null;
        if (glassModelControlResponse.getVideoDuration() > 0) {
            UserConfig.INSTANCE.getInstance().setRecordVideoDuration(glassModelControlResponse.getVideoDuration());
            int videoDuration = glassModelControlResponse.getVideoDuration();
            if (videoDuration == 15) {
                ActivityRecordSettingBinding activityRecordSettingBinding2 = this$0.binding;
                if (activityRecordSettingBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityRecordSettingBinding2 = null;
                }
                activityRecordSettingBinding2.tv6.setText(this$0.getString(C0775R.string.ble_glass_22, new Object[]{"15"}));
            } else if (videoDuration == 30) {
                ActivityRecordSettingBinding activityRecordSettingBinding3 = this$0.binding;
                if (activityRecordSettingBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityRecordSettingBinding3 = null;
                }
                activityRecordSettingBinding3.tv6.setText(this$0.getString(C0775R.string.ble_glass_22, new Object[]{"30"}));
            } else if (videoDuration == 60) {
                ActivityRecordSettingBinding activityRecordSettingBinding4 = this$0.binding;
                if (activityRecordSettingBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityRecordSettingBinding4 = null;
                }
                activityRecordSettingBinding4.tv6.setText(this$0.getString(C0775R.string.ble_glass_22, new Object[]{"60"}));
            } else if (videoDuration == 180) {
                ActivityRecordSettingBinding activityRecordSettingBinding5 = this$0.binding;
                if (activityRecordSettingBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityRecordSettingBinding5 = null;
                }
                activityRecordSettingBinding5.tv6.setText(this$0.getString(C0775R.string.ble_glass_23, new Object[]{ExifInterface.GPS_MEASUREMENT_3D}));
            } else if (videoDuration == 540) {
                ActivityRecordSettingBinding activityRecordSettingBinding6 = this$0.binding;
                if (activityRecordSettingBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityRecordSettingBinding6 = null;
                }
                activityRecordSettingBinding6.tv6.setText(this$0.getString(C0775R.string.ble_glass_23, new Object[]{"9"}));
            } else if (videoDuration == 720) {
                ActivityRecordSettingBinding activityRecordSettingBinding7 = this$0.binding;
                if (activityRecordSettingBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityRecordSettingBinding7 = null;
                }
                activityRecordSettingBinding7.tv6.setText(this$0.getString(C0775R.string.ble_glass_23, new Object[]{"12"}));
            }
        }
        if (glassModelControlResponse.getRecordAudioDuration() > 0) {
            UserConfig.INSTANCE.getInstance().setRecordAudioDuration(glassModelControlResponse.getRecordAudioDuration());
            int recordAudioDuration = glassModelControlResponse.getRecordAudioDuration();
            if (recordAudioDuration == 1800) {
                ActivityRecordSettingBinding activityRecordSettingBinding8 = this$0.binding;
                if (activityRecordSettingBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityRecordSettingBinding = activityRecordSettingBinding8;
                }
                activityRecordSettingBinding.tvAudioDuration.setText(this$0.getString(C0775R.string.ble_glass_23, new Object[]{"30"}));
            } else if (recordAudioDuration == 3600) {
                ActivityRecordSettingBinding activityRecordSettingBinding9 = this$0.binding;
                if (activityRecordSettingBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityRecordSettingBinding = activityRecordSettingBinding9;
                }
                activityRecordSettingBinding.tvAudioDuration.setText(this$0.getString(C0775R.string.ble_glass_23, new Object[]{"60"}));
            } else if (recordAudioDuration == 7200) {
                ActivityRecordSettingBinding activityRecordSettingBinding10 = this$0.binding;
                if (activityRecordSettingBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityRecordSettingBinding = activityRecordSettingBinding10;
                }
                activityRecordSettingBinding.tvAudioDuration.setText(this$0.getString(C0775R.string.ble_glass_23, new Object[]{"120"}));
            }
        }
        this$0.updateIndex();
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void updateIndex() {
        int i;
        int i2 = 1;
        this.directionIndex = !UserConfig.INSTANCE.getInstance().getVideoIsLandscape() ? 1 : 0;
        int recordVideoDuration = UserConfig.INSTANCE.getInstance().getRecordVideoDuration();
        if (recordVideoDuration == 15) {
            i = 0;
        } else if (recordVideoDuration == 30) {
            i = 1;
        } else if (recordVideoDuration != 60) {
            i = 3;
            if (recordVideoDuration != 180) {
                if (recordVideoDuration == 540) {
                    i = 4;
                } else if (recordVideoDuration == 720) {
                    i = 5;
                }
            }
        } else {
            i = 2;
        }
        this.durationIndex = i;
        int recordAudioDuration = UserConfig.INSTANCE.getInstance().getRecordAudioDuration();
        if (recordAudioDuration == 1800) {
            i2 = 0;
        } else if (recordAudioDuration != 3600) {
            if (recordAudioDuration == 7200) {
                i2 = 2;
            }
        }
        this.durationAudioIndex = i2;
    }

    private final void initView() {
        final ActivityRecordSettingBinding activityRecordSettingBinding = this.binding;
        if (activityRecordSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRecordSettingBinding = null;
        }
        LargeDataHandler.getInstance().glassesControl(new byte[]{1, 2}, this.callback);
        LargeDataHandler.getInstance().glassesControl(new byte[]{1, 6}, this.callback);
        updateIndex();
        activityRecordSettingBinding.title.tvTitle.setText(getString(C0775R.string.h_glass_205));
        activityRecordSettingBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.setting.RecordSettingActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecordSettingActivity.initView$lambda$5$lambda$1(this.f$0, view);
            }
        });
        String string = getString(C0775R.string.h_glass_208);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = getString(C0775R.string.h_glass_209);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        final List listListOf = CollectionsKt.listOf((Object[]) new CommonSelectModel[]{new CommonSelectModel(string, false, 0, null, 14, null), new CommonSelectModel(string2, false, 0, null, 14, null)});
        activityRecordSettingBinding.cslDirection.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.setting.RecordSettingActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecordSettingActivity.initView$lambda$5$lambda$2(this.f$0, listListOf, activityRecordSettingBinding, view);
            }
        });
        final List listListOf2 = CollectionsKt.listOf((Object[]) new CommonSelectModel[]{new CommonSelectModel("15s", false, 15, null, 10, null), new CommonSelectModel("30s", false, 30, null, 10, null), new CommonSelectModel("60s", false, 60, null, 10, null), new CommonSelectModel("3min", false, EMachine.EM_L10M, null, 10, null), new CommonSelectModel("9min", false, 540, getString(C0775R.string.g_recording_1), 2, null), new CommonSelectModel("12min", false, 720, getString(C0775R.string.g_recording_1), 2, null)});
        activityRecordSettingBinding.cslDuration.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.setting.RecordSettingActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecordSettingActivity.initView$lambda$5$lambda$3(this.f$0, listListOf2, activityRecordSettingBinding, view);
            }
        });
        String string3 = getString(C0775R.string.ble_glass_23, new Object[]{"30"});
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        String string4 = getString(C0775R.string.ble_glass_23, new Object[]{"60"});
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        String string5 = getString(C0775R.string.ble_glass_23, new Object[]{"120"});
        Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
        final List listListOf3 = CollectionsKt.listOf((Object[]) new CommonSelectModel[]{new CommonSelectModel(string3, false, 1800, null, 10, null), new CommonSelectModel(string4, false, MMKV.ExpireInHour, null, 10, null), new CommonSelectModel(string5, false, 7200, null, 10, null)});
        activityRecordSettingBinding.cslDurationAudio.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.setting.RecordSettingActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecordSettingActivity.initView$lambda$5$lambda$4(this.f$0, listListOf3, activityRecordSettingBinding, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$5$lambda$1(RecordSettingActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$5$lambda$2(final RecordSettingActivity this$0, List directionData, final ActivityRecordSettingBinding this_run, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(directionData, "$directionData");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        CommonSelectDialog.Builder defaultChecked = new CommonSelectDialog.Builder().setDefaultChecked(this$0.directionIndex);
        String string = this$0.getString(C0775R.string.h_glass_206);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        CommonSelectDialog commonSelectDialogBuild = defaultChecked.setTitle(string).showSaveIcon(false).setData(directionData).build();
        commonSelectDialogBuild.setOnItemCheckedListener(new Function2<Integer, CommonSelectModel, Unit>() { // from class: com.glasssutdio.wear.setting.RecordSettingActivity$initView$1$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, CommonSelectModel commonSelectModel) {
                invoke(num.intValue(), commonSelectModel);
                return Unit.INSTANCE;
            }

            public final void invoke(int i, CommonSelectModel model) {
                Intrinsics.checkNotNullParameter(model, "model");
                this_run.tv4.setText(model.getName());
                this$0.directionIndex = i;
                UserConfig.INSTANCE.getInstance().setVideoIsLandscape(i == 0);
            }
        });
        commonSelectDialogBuild.show(this$0.getSupportFragmentManager(), "cslDirection");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$5$lambda$3(RecordSettingActivity this$0, List durationData, ActivityRecordSettingBinding this_run, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(durationData, "$durationData");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        CommonSelectDialog.Builder defaultChecked = new CommonSelectDialog.Builder().setDefaultChecked(this$0.durationIndex);
        String string = this$0.getString(C0775R.string.h_glass_207);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        CommonSelectDialog commonSelectDialogBuild = defaultChecked.setTitle(string).showSaveIcon(false).setData(durationData).build();
        commonSelectDialogBuild.setOnItemCheckedListener(new RecordSettingActivity$initView$1$3$1(this$0, durationData, this_run));
        commonSelectDialogBuild.show(this$0.getSupportFragmentManager(), "cslDuration");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$5$lambda$4(RecordSettingActivity this$0, List durationAudioData, ActivityRecordSettingBinding this_run, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(durationAudioData, "$durationAudioData");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        CommonSelectDialog.Builder defaultChecked = new CommonSelectDialog.Builder().setDefaultChecked(this$0.durationAudioIndex);
        String string = this$0.getString(C0775R.string.h_glass_207_1);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        CommonSelectDialog commonSelectDialogBuild = defaultChecked.setTitle(string).showSaveIcon(false).setData(durationAudioData).build();
        commonSelectDialogBuild.setOnItemCheckedListener(new RecordSettingActivity$initView$1$4$1(this$0, durationAudioData, this_run));
        commonSelectDialogBuild.show(this$0.getSupportFragmentManager(), "cslDurationAudio");
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (BleOperateManager.getInstance().isConnected()) {
            return;
        }
        String string = getString(C0775R.string.ble_glass_18);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        GlobalKt.showToast$default(string, 0, 1, null);
        finish();
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BusEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if (messageEvent instanceof BluetoothEvent) {
            BluetoothEvent bluetoothEvent = (BluetoothEvent) messageEvent;
            XLog.m136i(Boolean.valueOf(!bluetoothEvent.getConnect()));
            if (bluetoothEvent.getConnect()) {
                return;
            }
            String string = getString(C0775R.string.ble_glass_18);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
            finish();
        }
    }
}
