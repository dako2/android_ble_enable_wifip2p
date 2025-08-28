package com.glasssutdio.wear.home.album;

import android.graphics.Bitmap;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: EditAlbumActivity.kt */
@Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
@DebugMetadata(m619c = "com.glasssutdio.wear.home.album.EditAlbumActivity$saveToSystemAlbum$1$1", m620f = "EditAlbumActivity.kt", m621i = {}, m622l = {315}, m623m = "invokeSuspend", m624n = {}, m625s = {})
/* loaded from: classes.dex */
final class EditAlbumActivity$saveToSystemAlbum$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $path;
    final /* synthetic */ Bitmap $watermarkedBitmap;
    int label;
    final /* synthetic */ EditAlbumActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    EditAlbumActivity$saveToSystemAlbum$1$1(EditAlbumActivity editAlbumActivity, String str, Bitmap bitmap, Continuation<? super EditAlbumActivity$saveToSystemAlbum$1$1> continuation) {
        super(2, continuation);
        this.this$0 = editAlbumActivity;
        this.$path = str;
        this.$watermarkedBitmap = bitmap;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new EditAlbumActivity$saveToSystemAlbum$1$1(this.this$0, this.$path, this.$watermarkedBitmap, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EditAlbumActivity$saveToSystemAlbum$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = BuildersKt.withContext(Dispatchers.getIO(), new EditAlbumActivity$saveToSystemAlbum$1$1$compressedFile$1(this.this$0, this.$path, this.$watermarkedBitmap, null), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            EditAlbumActivity editAlbumActivity = this.this$0;
            editAlbumActivity.saveFileToAppGalleryFolder(editAlbumActivity, (File) obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Unit.INSTANCE;
    }
}
