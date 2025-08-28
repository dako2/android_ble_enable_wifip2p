package com.glasssutdio.wear.ota;

import android.os.Handler;
import android.widget.TextView;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.ThreadExtKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.databinding.ActivityOtaactivityBinding;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.oudmon.ble.base.communication.DfuHandle;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: OTAActivity1.kt */
@Metadata(m606d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0005H\u0016¨\u0006\t"}, m607d2 = {"com/glasssutdio/wear/ota/OTAActivity1$dfuOpResult$1", "Lcom/oudmon/ble/base/communication/DfuHandle$IOpResult;", "onActionResult", "", "type", "", "errCode", "onProgress", "percent", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class OTAActivity1$dfuOpResult$1 implements DfuHandle.IOpResult {
    final /* synthetic */ OTAActivity1 this$0;

    OTAActivity1$dfuOpResult$1(OTAActivity1 oTAActivity1) {
        this.this$0 = oTAActivity1;
    }

    @Override // com.oudmon.ble.base.communication.DfuHandle.IOpResult
    public void onActionResult(int type, int errCode) {
        if (errCode != 0) {
            final OTAActivity1 oTAActivity1 = this.this$0;
            ThreadExtKt.ktxRunOnUi(this, new Function1<OTAActivity1$dfuOpResult$1, Unit>() { // from class: com.glasssutdio.wear.ota.OTAActivity1$dfuOpResult$1$onActionResult$3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(OTAActivity1$dfuOpResult$1 oTAActivity1$dfuOpResult$1) {
                    invoke2(oTAActivity1$dfuOpResult$1);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(OTAActivity1$dfuOpResult$1 ktxRunOnUi) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                    String string = oTAActivity1.getString(C0775R.string.ble_glass_13);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    GlobalKt.showToast$default(string, 0, 1, null);
                    oTAActivity1.finish();
                }
            });
            return;
        }
        DfuHandle dfuHandle = null;
        if (type == 1) {
            DfuHandle dfuHandle2 = this.this$0.dfuHandle;
            if (dfuHandle2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dfuHandle");
            } else {
                dfuHandle = dfuHandle2;
            }
            dfuHandle.init();
            return;
        }
        if (type == 2) {
            DfuHandle dfuHandle3 = this.this$0.dfuHandle;
            if (dfuHandle3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dfuHandle");
            } else {
                dfuHandle = dfuHandle3;
            }
            dfuHandle.sendPacket();
            return;
        }
        if (type == 3) {
            DfuHandle dfuHandle4 = this.this$0.dfuHandle;
            if (dfuHandle4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dfuHandle");
            } else {
                dfuHandle = dfuHandle4;
            }
            dfuHandle.check();
            return;
        }
        if (type != 4) {
            return;
        }
        DfuHandle dfuHandle5 = this.this$0.dfuHandle;
        if (dfuHandle5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dfuHandle");
        } else {
            dfuHandle = dfuHandle5;
        }
        dfuHandle.endAndRelease();
        this.this$0.handler.removeCallbacks(this.this$0.runnable);
        final OTAActivity1 oTAActivity12 = this.this$0;
        oTAActivity12.runOnUiThread(new Runnable() { // from class: com.glasssutdio.wear.ota.OTAActivity1$dfuOpResult$1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                OTAActivity1$dfuOpResult$1.onActionResult$lambda$0(oTAActivity12);
            }
        });
        UserConfig.INSTANCE.getInstance().setFmVersion("");
        Handler handler = this.this$0.handler;
        final OTAActivity1 oTAActivity13 = this.this$0;
        handler.postDelayed(new Runnable() { // from class: com.glasssutdio.wear.ota.OTAActivity1$dfuOpResult$1$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                OTAActivity1$dfuOpResult$1.onActionResult$lambda$1(oTAActivity13);
            }
        }, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onActionResult$lambda$0(OTAActivity1 this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityOtaactivityBinding activityOtaactivityBinding = this$0.binding;
        ActivityOtaactivityBinding activityOtaactivityBinding2 = null;
        if (activityOtaactivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOtaactivityBinding = null;
        }
        TextView textView = activityOtaactivityBinding.tvOta2;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this$0.getString(C0775R.string.ble_glass_31);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String str = String.format(string, Arrays.copyOf(new Object[]{"100"}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        textView.setText(str);
        ActivityOtaactivityBinding activityOtaactivityBinding3 = this$0.binding;
        if (activityOtaactivityBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityOtaactivityBinding2 = activityOtaactivityBinding3;
        }
        activityOtaactivityBinding2.progressValue.setProgress(100);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onActionResult$lambda$1(OTAActivity1 this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.appDisconnect();
    }

    @Override // com.oudmon.ble.base.communication.DfuHandle.IOpResult
    public void onProgress(final int percent) {
        final OTAActivity1 oTAActivity1 = this.this$0;
        oTAActivity1.runOnUiThread(new Runnable() { // from class: com.glasssutdio.wear.ota.OTAActivity1$dfuOpResult$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                OTAActivity1$dfuOpResult$1.onProgress$lambda$2(percent, oTAActivity1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onProgress$lambda$2(int i, OTAActivity1 this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityOtaactivityBinding activityOtaactivityBinding = null;
        if (i > 0) {
            ActivityOtaactivityBinding activityOtaactivityBinding2 = this$0.binding;
            if (activityOtaactivityBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityOtaactivityBinding2 = null;
            }
            ViewKt.gone(activityOtaactivityBinding2.appBack);
        } else {
            ActivityOtaactivityBinding activityOtaactivityBinding3 = this$0.binding;
            if (activityOtaactivityBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityOtaactivityBinding3 = null;
            }
            ViewKt.visible(activityOtaactivityBinding3.appBack);
        }
        ActivityOtaactivityBinding activityOtaactivityBinding4 = this$0.binding;
        if (activityOtaactivityBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOtaactivityBinding4 = null;
        }
        activityOtaactivityBinding4.otaImage1.setImageResource(C0775R.mipmap.ota_file_update_to_device);
        ActivityOtaactivityBinding activityOtaactivityBinding5 = this$0.binding;
        if (activityOtaactivityBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOtaactivityBinding5 = null;
        }
        activityOtaactivityBinding5.tvTitle.setText(this$0.getString(C0775R.string.ble_glass_30));
        int iCombineProgressBle = this$0.combineProgressBle(100, i, 0.0d, 1.0d);
        ActivityOtaactivityBinding activityOtaactivityBinding6 = this$0.binding;
        if (activityOtaactivityBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOtaactivityBinding6 = null;
        }
        activityOtaactivityBinding6.progressValue.setProgress(iCombineProgressBle);
        ActivityOtaactivityBinding activityOtaactivityBinding7 = this$0.binding;
        if (activityOtaactivityBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityOtaactivityBinding = activityOtaactivityBinding7;
        }
        TextView textView = activityOtaactivityBinding.tvOta2;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this$0.getString(C0775R.string.ble_glass_31);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String str = String.format(string, Arrays.copyOf(new Object[]{String.valueOf(iCombineProgressBle)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        textView.setText(str);
        this$0.handler.removeCallbacks(this$0.runnable);
        this$0.handler.postDelayed(this$0.runnable, DeviceOrientationRequest.OUTPUT_PERIOD_MEDIUM);
    }
}
