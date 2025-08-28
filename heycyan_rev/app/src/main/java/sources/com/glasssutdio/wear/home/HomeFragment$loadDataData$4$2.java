package com.glasssutdio.wear.home;

import android.view.View;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.oudmon.ble.base.communication.ILargeDataResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.resp.BaseResponse;
import com.oudmon.ble.base.communication.bigData.resp.GlassModelControlResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

/* compiled from: HomeFragment.kt */
@Metadata(m606d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m607d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
final class HomeFragment$loadDataData$4$2 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ Ref.BooleanRef $videoStart;
    final /* synthetic */ HomeFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HomeFragment$loadDataData$4$2(Ref.BooleanRef booleanRef, HomeFragment homeFragment) {
        super(1);
        this.$videoStart = booleanRef;
        this.this$0 = homeFragment;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(View view) {
        invoke2(view);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(View it) {
        Intrinsics.checkNotNullParameter(it, "it");
        int i = this.$videoStart.element ? 2 : 3;
        LargeDataHandler largeDataHandler = LargeDataHandler.getInstance();
        byte[] bArr = {2, 1, (byte) i};
        final HomeFragment homeFragment = this.this$0;
        final Ref.BooleanRef booleanRef = this.$videoStart;
        largeDataHandler.glassesControl(bArr, new ILargeDataResponse() { // from class: com.glasssutdio.wear.home.HomeFragment$loadDataData$4$2$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i2, BaseResponse baseResponse) {
                HomeFragment$loadDataData$4$2.invoke$lambda$0(homeFragment, booleanRef, i2, (GlassModelControlResponse) baseResponse);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(HomeFragment this$0, Ref.BooleanRef videoStart, int i, GlassModelControlResponse glassModelControlResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(videoStart, "$videoStart");
        if (glassModelControlResponse.getDataType() == 1) {
            if (glassModelControlResponse.getErrorCode() == 0) {
                XLog.m136i(Integer.valueOf(glassModelControlResponse.getWorkTypeIng()));
                switch (glassModelControlResponse.getWorkTypeIng()) {
                    case 1:
                    case 6:
                        String string = this$0.getString(C0775R.string.album_glass_40);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                        GlobalKt.showToast$default(string, 0, 1, null);
                        break;
                    case 2:
                        String string2 = this$0.getString(C0775R.string.album_glass_36);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                        GlobalKt.showToast$default(string2, 0, 1, null);
                        break;
                    case 4:
                        String string3 = this$0.getString(C0775R.string.album_glass_45);
                        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                        GlobalKt.showToast$default(string3, 0, 1, null);
                        break;
                    case 5:
                        String string4 = this$0.getString(C0775R.string.album_glass_38);
                        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                        GlobalKt.showToast$default(string4, 0, 1, null);
                        break;
                    case 7:
                        String string5 = this$0.getString(C0775R.string.album_glass_37);
                        Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                        GlobalKt.showToast$default(string5, 0, 1, null);
                        break;
                    case 8:
                        String string6 = this$0.getString(C0775R.string.album_glass_39);
                        Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                        GlobalKt.showToast$default(string6, 0, 1, null);
                        break;
                }
            }
            if (videoStart.element) {
                String string7 = this$0.getString(C0775R.string.g_fire_28);
                Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                GlobalKt.showToast$default(string7, 0, 1, null);
            } else {
                String string8 = this$0.getString(C0775R.string.g_fire_29);
                Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
                GlobalKt.showToast$default(string8, 0, 1, null);
            }
            videoStart.element = !videoStart.element;
        }
    }
}
