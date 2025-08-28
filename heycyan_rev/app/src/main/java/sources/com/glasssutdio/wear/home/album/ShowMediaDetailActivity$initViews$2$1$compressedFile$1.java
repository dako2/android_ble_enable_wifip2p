package com.glasssutdio.wear.home.album;

import android.graphics.Bitmap;
import com.glasssutdio.wear.home.album.water.ImageCompressor;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: ShowMediaDetailActivity.kt */
@Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "Ljava/io/File;", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
@DebugMetadata(m619c = "com.glasssutdio.wear.home.album.ShowMediaDetailActivity$initViews$2$1$compressedFile$1", m620f = "ShowMediaDetailActivity.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
/* loaded from: classes.dex */
final class ShowMediaDetailActivity$initViews$2$1$compressedFile$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super File>, Object> {
    final /* synthetic */ String $path;
    final /* synthetic */ Bitmap $watermarkedBitmap;
    int label;
    final /* synthetic */ ShowMediaDetailActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ShowMediaDetailActivity$initViews$2$1$compressedFile$1(ShowMediaDetailActivity showMediaDetailActivity, String str, Bitmap bitmap, Continuation<? super ShowMediaDetailActivity$initViews$2$1$compressedFile$1> continuation) {
        super(2, continuation);
        this.this$0 = showMediaDetailActivity;
        this.$path = str;
        this.$watermarkedBitmap = bitmap;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ShowMediaDetailActivity$initViews$2$1$compressedFile$1(this.this$0, this.$path, this.$watermarkedBitmap, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super File> continuation) {
        return ((ShowMediaDetailActivity$initViews$2$1$compressedFile$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ImageCompressor.Builder maxSize = ImageCompressor.INSTANCE.with(this.this$0).setMaxSize(6560, 5058);
        String path = this.$path;
        Intrinsics.checkNotNullExpressionValue(path, "$path");
        return maxSize.setOutputDir(path).build().compressBitmap(this.$watermarkedBitmap, "IMG_" + System.currentTimeMillis() + ".jpg");
    }
}
