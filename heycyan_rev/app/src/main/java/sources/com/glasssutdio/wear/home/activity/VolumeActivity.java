package com.glasssutdio.wear.home.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.GsonInstance;
import com.glasssutdio.wear.bus.BusEvent;
import com.glasssutdio.wear.bus.EventType;
import com.glasssutdio.wear.databinding.ActivityVolumeBinding;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import com.oudmon.ble.base.communication.LargeDataHandler;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: VolumeActivity.kt */
@Metadata(m606d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\u0012\u0010\f\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0017J\b\u0010\u0012\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\b¨\u0006\u0013"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/VolumeActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityVolumeBinding;", "volumeArray", "", "", "[Ljava/lang/Integer;", "changeVolume", "", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/glasssutdio/wear/bus/BusEvent;", "refreshVolume", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class VolumeActivity extends BaseSettingActivity {
    private ActivityVolumeBinding binding;
    private Integer[] volumeArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityVolumeBinding activityVolumeBindingInflate = ActivityVolumeBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityVolumeBindingInflate, "inflate(...)");
        this.binding = activityVolumeBindingInflate;
        if (activityVolumeBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVolumeBindingInflate = null;
        }
        setContentView(activityVolumeBindingInflate.getRoot());
        initView();
    }

    private final void initView() {
        try {
            String volumeControl = UserConfig.INSTANCE.getInstance().getVolumeControl();
            if (volumeControl.length() > 0) {
                List listSplit$default = StringsKt.split$default((CharSequence) volumeControl, new String[]{","}, false, 0, 6, (Object) null);
                XLog.m137i(volumeControl);
                this.volumeArray = new Integer[]{Integer.valueOf(Integer.parseInt((String) listSplit$default.get(0))), Integer.valueOf(Integer.parseInt((String) listSplit$default.get(1))), Integer.valueOf(Integer.parseInt((String) listSplit$default.get(2))), Integer.valueOf(Integer.parseInt((String) listSplit$default.get(3))), Integer.valueOf(Integer.parseInt((String) listSplit$default.get(4))), Integer.valueOf(Integer.parseInt((String) listSplit$default.get(5))), Integer.valueOf(Integer.parseInt((String) listSplit$default.get(6))), Integer.valueOf(Integer.parseInt((String) listSplit$default.get(7))), Integer.valueOf(Integer.parseInt((String) listSplit$default.get(8))), Integer.valueOf(Integer.parseInt((String) listSplit$default.get(9)))};
                XLog.m137i(GsonInstance.INSTANCE.getGson().toJson(this.volumeArray));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ActivityVolumeBinding activityVolumeBinding = this.binding;
        if (activityVolumeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVolumeBinding = null;
        }
        activityVolumeBinding.title.tvTitle.setText(getString(C0775R.string.g_fire_4));
        activityVolumeBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.VolumeActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VolumeActivity.initView$lambda$1$lambda$0(this.f$0, view);
            }
        });
        if (UserConfig.INSTANCE.getInstance().getVolumeControl().length() > 0) {
            activityVolumeBinding.pbMusic.setMax(this.volumeArray[1].intValue());
            activityVolumeBinding.pbMusic.setProgress(this.volumeArray[2].intValue());
            activityVolumeBinding.pbCall.setMax(this.volumeArray[4].intValue());
            activityVolumeBinding.pbCall.setProgress(this.volumeArray[5].intValue());
            activityVolumeBinding.pbSystem.setMax(this.volumeArray[7].intValue());
            activityVolumeBinding.pbSystem.setProgress(this.volumeArray[8].intValue());
        }
        activityVolumeBinding.pbMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.glasssutdio.wear.home.activity.VolumeActivity$initView$1$2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar p0, int p1, boolean p2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar p0) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                XLog.m136i(Integer.valueOf(p0.getProgress()));
                this.this$0.volumeArray[2] = Integer.valueOf(p0.getProgress());
                this.this$0.changeVolume();
            }
        });
        activityVolumeBinding.pbCall.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.glasssutdio.wear.home.activity.VolumeActivity$initView$1$3
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar p0, int p1, boolean p2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar p0) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                XLog.m136i(Integer.valueOf(p0.getProgress()));
                this.this$0.volumeArray[5] = Integer.valueOf(p0.getProgress());
                this.this$0.changeVolume();
            }
        });
        activityVolumeBinding.pbSystem.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.glasssutdio.wear.home.activity.VolumeActivity$initView$1$4
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar p0, int p1, boolean p2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar p0) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                XLog.m136i(p0);
                this.this$0.volumeArray[8] = Integer.valueOf(p0.getProgress());
                this.this$0.changeVolume();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$1$lambda$0(VolumeActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeVolume() {
        XLog.m137i(GsonInstance.INSTANCE.getGson().toJson(this.volumeArray));
        LargeDataHandler.getInstance().setVolumeControl(this.volumeArray[0].intValue(), this.volumeArray[1].intValue(), this.volumeArray[2].intValue(), this.volumeArray[3].intValue(), this.volumeArray[4].intValue(), this.volumeArray[5].intValue(), this.volumeArray[6].intValue(), this.volumeArray[7].intValue(), this.volumeArray[8].intValue(), this.volumeArray[9].intValue());
    }

    private final void refreshVolume() {
        try {
            String volumeControl = UserConfig.INSTANCE.getInstance().getVolumeControl();
            if (volumeControl.length() > 0) {
                List listSplit$default = StringsKt.split$default((CharSequence) volumeControl, new String[]{","}, false, 0, 6, (Object) null);
                XLog.m137i(volumeControl);
                this.volumeArray = new Integer[]{Integer.valueOf(Integer.parseInt((String) listSplit$default.get(0))), Integer.valueOf(Integer.parseInt((String) listSplit$default.get(1))), Integer.valueOf(Integer.parseInt((String) listSplit$default.get(2))), Integer.valueOf(Integer.parseInt((String) listSplit$default.get(3))), Integer.valueOf(Integer.parseInt((String) listSplit$default.get(4))), Integer.valueOf(Integer.parseInt((String) listSplit$default.get(5))), Integer.valueOf(Integer.parseInt((String) listSplit$default.get(6))), Integer.valueOf(Integer.parseInt((String) listSplit$default.get(7))), Integer.valueOf(Integer.parseInt((String) listSplit$default.get(8))), Integer.valueOf(Integer.parseInt((String) listSplit$default.get(9)))};
                XLog.m137i(GsonInstance.INSTANCE.getGson().toJson(this.volumeArray));
                ActivityVolumeBinding activityVolumeBinding = this.binding;
                if (activityVolumeBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityVolumeBinding = null;
                }
                activityVolumeBinding.pbMusic.setMax(this.volumeArray[1].intValue());
                activityVolumeBinding.pbMusic.setProgress(this.volumeArray[2].intValue());
                activityVolumeBinding.pbCall.setMax(this.volumeArray[4].intValue());
                activityVolumeBinding.pbCall.setProgress(this.volumeArray[5].intValue());
                activityVolumeBinding.pbSystem.setMax(this.volumeArray[7].intValue());
                activityVolumeBinding.pbSystem.setProgress(this.volumeArray[8].intValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BusEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if ((messageEvent instanceof EventType) && ((EventType) messageEvent).getType() == 19) {
            refreshVolume();
        }
    }
}
