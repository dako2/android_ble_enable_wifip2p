package com.glasssutdio.wear.p003ai.callback;

import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.ThreadExtKt;
import com.glasssutdio.wear.bus.EventType;
import com.glasssutdio.wear.database.entity.AiChatEntity;
import com.glasssutdio.wear.depository.AiChatDepository;
import com.glasssutdio.wear.p003ai.spark.SparkChainRecognizer;
import com.oudmon.ble.base.communication.ILargeDataResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.resp.BaseResponse;
import com.oudmon.ble.base.communication.bigData.resp.GlassModelControlResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* compiled from: MyChatGptCallback.kt */
@Metadata(m606d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\"\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\"\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0010\u001a\u00020\nH\u0016J\b\u0010\u0011\u001a\u00020\u0004H\u0002J\b\u0010\u0012\u001a\u00020\u0004H\u0002J\b\u0010\u0013\u001a\u00020\u0004H\u0002J\b\u0010\u0014\u001a\u00020\u0004H\u0002¨\u0006\u0015"}, m607d2 = {"Lcom/glasssutdio/wear/ai/callback/MyChatGptCallback;", "Lcom/glasssutdio/wear/depository/AiChatDepository$ChatGptCallback;", "()V", "chatGptFail", "", "reason", "", "chatGptSuccess", "type", "content", "", "entity", "Lcom/glasssutdio/wear/database/entity/AiChatEntity;", "chatGptTextStream", "isComplete", "", "text", "startRecord", "startVideo", "takePicture", "visionAiStart", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class MyChatGptCallback implements AiChatDepository.ChatGptCallback {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void takePicture$lambda$1(int i, GlassModelControlResponse glassModelControlResponse) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void visionAiStart$lambda$0(int i, GlassModelControlResponse glassModelControlResponse) {
    }

    @Override // com.glasssutdio.wear.depository.AiChatDepository.ChatGptCallback
    public void chatGptTextStream(boolean isComplete, AiChatEntity entity, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
    }

    @Override // com.glasssutdio.wear.depository.AiChatDepository.ChatGptCallback
    public void chatGptSuccess(int type, String content, AiChatEntity entity) {
        Intrinsics.checkNotNullParameter(content, "content");
        XLog.m137i("没有初始化回调的时候，正常返回-加载更多" + type + "---" + content);
        switch (type) {
            case 0:
            case 1:
            case 9:
                if (content.length() > 0) {
                    SparkChainRecognizer.getInstance().startTTS(content);
                    break;
                }
                break;
            case 2:
                visionAiStart();
                break;
            case 3:
                takePicture();
                break;
            case 5:
                SparkChainRecognizer.getInstance().exitAi();
                ThreadExtKt.ktxRunOnUi(this, new Function1<MyChatGptCallback, Unit>() { // from class: com.glasssutdio.wear.ai.callback.MyChatGptCallback.chatGptSuccess.1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(MyChatGptCallback myChatGptCallback) {
                        invoke2(myChatGptCallback);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(MyChatGptCallback ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.ble_glass_42), 0, 1, null);
                    }
                });
                break;
            case 7:
                ThreadExtKt.ktxRunOnUi(this, new Function1<MyChatGptCallback, Unit>() { // from class: com.glasssutdio.wear.ai.callback.MyChatGptCallback.chatGptSuccess.2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(MyChatGptCallback myChatGptCallback) {
                        invoke2(myChatGptCallback);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(MyChatGptCallback ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.ble_glass_43), 0, 1, null);
                    }
                });
                break;
            case 8:
                EventBus.getDefault().post(new EventType(18));
                break;
            case 11:
                startVideo();
                break;
            case 12:
                startRecord();
                break;
        }
    }

    @Override // com.glasssutdio.wear.depository.AiChatDepository.ChatGptCallback
    public void chatGptFail(int reason) {
        XLog.m137i("出现了异常:" + reason);
    }

    private final void visionAiStart() {
        LargeDataHandler.getInstance().glassesControl(new byte[]{2, 1, 6}, new ILargeDataResponse() { // from class: com.glasssutdio.wear.ai.callback.MyChatGptCallback$$ExternalSyntheticLambda2
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i, BaseResponse baseResponse) {
                MyChatGptCallback.visionAiStart$lambda$0(i, (GlassModelControlResponse) baseResponse);
            }
        });
    }

    private final void takePicture() {
        LargeDataHandler.getInstance().glassesControl(new byte[]{2, 1, 1}, new ILargeDataResponse() { // from class: com.glasssutdio.wear.ai.callback.MyChatGptCallback$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i, BaseResponse baseResponse) {
                MyChatGptCallback.takePicture$lambda$1(i, (GlassModelControlResponse) baseResponse);
            }
        });
    }

    private final void startVideo() {
        LargeDataHandler.getInstance().glassesControl(new byte[]{2, 1, (byte) 2}, new ILargeDataResponse() { // from class: com.glasssutdio.wear.ai.callback.MyChatGptCallback$$ExternalSyntheticLambda3
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i, BaseResponse baseResponse) {
                MyChatGptCallback.startVideo$lambda$2(this.f$0, i, (GlassModelControlResponse) baseResponse);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startVideo$lambda$2(MyChatGptCallback this$0, int i, final GlassModelControlResponse glassModelControlResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (glassModelControlResponse.getDataType() == 1) {
            if (glassModelControlResponse.getErrorCode() == 0) {
                XLog.m136i(Integer.valueOf(glassModelControlResponse.getWorkTypeIng()));
                ThreadExtKt.ktxRunOnUi(this$0, new Function1<MyChatGptCallback, Unit>() { // from class: com.glasssutdio.wear.ai.callback.MyChatGptCallback$startVideo$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(MyChatGptCallback myChatGptCallback) {
                        invoke2(myChatGptCallback);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(MyChatGptCallback ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        switch (glassModelControlResponse.getWorkTypeIng()) {
                            case 1:
                            case 6:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_40), 0, 1, null);
                                break;
                            case 2:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_36), 0, 1, null);
                                break;
                            case 4:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_45), 0, 1, null);
                                break;
                            case 5:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_38), 0, 1, null);
                                break;
                            case 7:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_37), 0, 1, null);
                                break;
                            case 8:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_39), 0, 1, null);
                                break;
                        }
                    }
                });
            } else {
                XLog.m137i("----2222");
            }
        }
    }

    private final void startRecord() {
        LargeDataHandler.getInstance().glassesControl(new byte[]{2, 1, (byte) 8}, new ILargeDataResponse() { // from class: com.glasssutdio.wear.ai.callback.MyChatGptCallback$$ExternalSyntheticLambda1
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i, BaseResponse baseResponse) {
                MyChatGptCallback.startRecord$lambda$3(this.f$0, i, (GlassModelControlResponse) baseResponse);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startRecord$lambda$3(MyChatGptCallback this$0, int i, final GlassModelControlResponse glassModelControlResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (glassModelControlResponse.getDataType() == 1) {
            if (glassModelControlResponse.getErrorCode() == 0) {
                XLog.m136i(Integer.valueOf(glassModelControlResponse.getWorkTypeIng()));
                ThreadExtKt.ktxRunOnUi(this$0, new Function1<MyChatGptCallback, Unit>() { // from class: com.glasssutdio.wear.ai.callback.MyChatGptCallback$startRecord$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(MyChatGptCallback myChatGptCallback) {
                        invoke2(myChatGptCallback);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(MyChatGptCallback ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        switch (glassModelControlResponse.getWorkTypeIng()) {
                            case 1:
                            case 6:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_40), 0, 1, null);
                                break;
                            case 2:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_36), 0, 1, null);
                                break;
                            case 4:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_45), 0, 1, null);
                                break;
                            case 5:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_38), 0, 1, null);
                                break;
                            case 7:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_37), 0, 1, null);
                                break;
                            case 8:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_39), 0, 1, null);
                                break;
                        }
                    }
                });
            } else {
                XLog.m137i("----1111");
            }
        }
    }
}
