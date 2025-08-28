package com.glasssutdio.wear.all;

import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;

/* compiled from: ThreadExt.kt */
@Metadata(m606d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, m607d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
@DebugMetadata(m619c = "com.glasssutdio.wear.all.ThreadExtKt$ktxRunOnBgSingleDao$job$1", m620f = "ThreadExt.kt", m621i = {}, m622l = {EMachine.EM_M32R}, m623m = "invokeSuspend", m624n = {}, m625s = {})
/* loaded from: classes.dex */
final class ThreadExtKt$ktxRunOnBgSingleDao$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<T, Unit> $block;
    final /* synthetic */ T $this_ktxRunOnBgSingleDao;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    ThreadExtKt$ktxRunOnBgSingleDao$job$1(Function1<? super T, Unit> function1, T t, Continuation<? super ThreadExtKt$ktxRunOnBgSingleDao$job$1> continuation) {
        super(2, continuation);
        this.$block = function1;
        this.$this_ktxRunOnBgSingleDao = t;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ThreadExtKt$ktxRunOnBgSingleDao$job$1(this.$block, this.$this_ktxRunOnBgSingleDao, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ThreadExtKt$ktxRunOnBgSingleDao$job$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: ThreadExt.kt */
    @Metadata(m606d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, m607d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.all.ThreadExtKt$ktxRunOnBgSingleDao$job$1$1", m620f = "ThreadExt.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.all.ThreadExtKt$ktxRunOnBgSingleDao$job$1$1 */
    static final class C08041 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<T, Unit> $block;
        final /* synthetic */ T $this_ktxRunOnBgSingleDao;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08041(Function1<? super T, Unit> function1, T t, Continuation<? super C08041> continuation) {
            super(2, continuation);
            this.$block = function1;
            this.$this_ktxRunOnBgSingleDao = t;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C08041(this.$block, this.$this_ktxRunOnBgSingleDao, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08041) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.$block.invoke(this.$this_ktxRunOnBgSingleDao);
            return Unit.INSTANCE;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (TimeoutKt.withTimeout(2000L, new C08041(this.$block, this.$this_ktxRunOnBgSingleDao, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
