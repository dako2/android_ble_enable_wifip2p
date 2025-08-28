package com.glasssutdio.wear.api;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.glasssutdio.wear.api.Result;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: BaseRepository.kt */
@Metadata(m606d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JE\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\b\b\u0000\u0010\u0005*\u00020\u00012\"\u0010\u0006\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u00040\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0089\u0001\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00050\u000b\"\b\b\u0000\u0010\u0005*\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00050\u00042+\b\u0002\u0010\r\u001a%\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\b\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u000e¢\u0006\u0002\b\u00112+\b\u0002\u0010\u0012\u001a%\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\b\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u000e¢\u0006\u0002\b\u0011H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0013JM\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00050\u000b\"\b\b\u0000\u0010\u0005*\u00020\u00012\"\u0010\u0006\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u000b0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00072\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, m607d2 = {"Lcom/glasssutdio/wear/api/BaseRepository;", "", "()V", "apiCall", "Lcom/glasssutdio/wear/api/QcResponse;", ExifInterface.GPS_DIRECTION_TRUE, NotificationCompat.CATEGORY_CALL, "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeResponse", "Lcom/glasssutdio/wear/api/Result;", "response", "successBlock", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "", "Lkotlin/ExtensionFunctionType;", "errorBlock", "(Lcom/glasssutdio/wear/api/QcResponse;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "safeApiCall", "errorMessage", "", "(Lkotlin/jvm/functions/Function1;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public class BaseRepository {

    /* compiled from: BaseRepository.kt */
    @Metadata(m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.api.BaseRepository", m620f = "BaseRepository.kt", m621i = {0}, m622l = {14}, m623m = "safeApiCall", m624n = {"errorMessage"}, m625s = {"L$0"})
    /* renamed from: com.glasssutdio.wear.api.BaseRepository$safeApiCall$1 */
    static final class C08441<T> extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C08441(Continuation<? super C08441> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BaseRepository.this.safeApiCall(null, null, this);
        }
    }

    public final <T> Object apiCall(Function1<? super Continuation<? super QcResponse<? extends T>>, ? extends Object> function1, Continuation<? super QcResponse<? extends T>> continuation) {
        return function1.invoke(continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final <T> Object safeApiCall(Function1<? super Continuation<? super Result<? extends T>>, ? extends Object> function1, String str, Continuation<? super Result<? extends T>> continuation) {
        C08441 c08441;
        if (continuation instanceof C08441) {
            c08441 = (C08441) continuation;
            if ((c08441.label & Integer.MIN_VALUE) != 0) {
                c08441.label -= Integer.MIN_VALUE;
            } else {
                c08441 = new C08441(continuation);
            }
        }
        Object objInvoke = c08441.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08441.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(objInvoke);
                c08441.L$0 = str;
                c08441.label = 1;
                objInvoke = function1.invoke(c08441);
                if (objInvoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                str = (String) c08441.L$0;
                ResultKt.throwOnFailure(objInvoke);
            }
            return (Result) objInvoke;
        } catch (Exception e) {
            return new Result.Error(new IOException(str, e));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object executeResponse$default(BaseRepository baseRepository, QcResponse qcResponse, Function2 function2, Function2 function22, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: executeResponse");
        }
        if ((i & 2) != 0) {
            function2 = null;
        }
        if ((i & 4) != 0) {
            function22 = null;
        }
        return baseRepository.executeResponse(qcResponse, function2, function22, continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: BaseRepository.kt */
    @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "Lcom/glasssutdio/wear/api/Result;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.api.BaseRepository$executeResponse$2", m620f = "BaseRepository.kt", m621i = {}, m622l = {24, 27}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.api.BaseRepository$executeResponse$2 */
    static final class C08432<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends T>>, Object> {
        final /* synthetic */ Function2<CoroutineScope, Continuation<? super Unit>, Object> $errorBlock;
        final /* synthetic */ QcResponse<T> $response;
        final /* synthetic */ Function2<CoroutineScope, Continuation<? super Unit>, Object> $successBlock;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08432(QcResponse<? extends T> qcResponse, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function22, Continuation<? super C08432> continuation) {
            super(2, continuation);
            this.$response = qcResponse;
            this.$errorBlock = function2;
            this.$successBlock = function22;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C08432 c08432 = new C08432(this.$response, this.$errorBlock, this.$successBlock, continuation);
            c08432.L$0 = obj;
            return c08432;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends T>> continuation) {
            return ((C08432) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i == 1) {
                    ResultKt.throwOnFailure(obj);
                    return new Result.Error(new IOException(this.$response.getMessage()));
                }
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return new Result.Success(this.$response.getData());
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            if (this.$response.getRetCode() != 0) {
                Function2<CoroutineScope, Continuation<? super Unit>, Object> function2 = this.$errorBlock;
                if (function2 != null) {
                    this.label = 1;
                    if (function2.invoke(coroutineScope, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return new Result.Error(new IOException(this.$response.getMessage()));
            }
            Function2<CoroutineScope, Continuation<? super Unit>, Object> function22 = this.$successBlock;
            if (function22 != null) {
                this.label = 2;
                if (function22.invoke(coroutineScope, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return new Result.Success(this.$response.getData());
        }
    }

    public final <T> Object executeResponse(QcResponse<? extends T> qcResponse, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function22, Continuation<? super Result<? extends T>> continuation) {
        return CoroutineScopeKt.coroutineScope(new C08432(qcResponse, function22, function2, null), continuation);
    }
}
