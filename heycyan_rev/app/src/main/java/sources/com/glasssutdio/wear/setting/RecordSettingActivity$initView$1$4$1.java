package com.glasssutdio.wear.setting;

import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.all.bean.CommonSelectModel;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.databinding.ActivityRecordSettingBinding;
import com.oudmon.ble.base.communication.ILargeDataResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.resp.BaseResponse;
import com.oudmon.ble.base.communication.bigData.resp.GlassModelControlResponse;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: RecordSettingActivity.kt */
@Metadata(m606d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m607d2 = {"<anonymous>", "", "position", "", "model", "Lcom/glasssutdio/wear/all/bean/CommonSelectModel;", "invoke"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
final class RecordSettingActivity$initView$1$4$1 extends Lambda implements Function2<Integer, CommonSelectModel, Unit> {
    final /* synthetic */ List<CommonSelectModel> $durationAudioData;
    final /* synthetic */ ActivityRecordSettingBinding $this_run;
    final /* synthetic */ RecordSettingActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RecordSettingActivity$initView$1$4$1(RecordSettingActivity recordSettingActivity, List<CommonSelectModel> list, ActivityRecordSettingBinding activityRecordSettingBinding) {
        super(2);
        this.this$0 = recordSettingActivity;
        this.$durationAudioData = list;
        this.$this_run = activityRecordSettingBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(int i, GlassModelControlResponse glassModelControlResponse) {
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, CommonSelectModel commonSelectModel) {
        invoke(num.intValue(), commonSelectModel);
        return Unit.INSTANCE;
    }

    public final void invoke(int i, CommonSelectModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        this.this$0.durationAudioIndex = i;
        UserConfig.INSTANCE.getInstance().setRecordAudioDuration(this.$durationAudioData.get(i).getValue());
        this.$this_run.tvAudioDuration.setText(model.getName());
        int value = this.$durationAudioData.get(i).getValue();
        XLog.m136i(Integer.valueOf(value));
        LargeDataHandler.getInstance().glassesControl(new byte[]{2, 6, 0, (byte) ByteUtil.loword(value), (byte) ByteUtil.hiword(value)}, new ILargeDataResponse() { // from class: com.glasssutdio.wear.setting.RecordSettingActivity$initView$1$4$1$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i2, BaseResponse baseResponse) {
                RecordSettingActivity$initView$1$4$1.invoke$lambda$0(i2, (GlassModelControlResponse) baseResponse);
            }
        });
    }
}
