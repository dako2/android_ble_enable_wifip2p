package com.glasssutdio.wear.api;

import androidx.exifinterface.media.ExifInterface;
import com.androidnetworking.common.ANConstants;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.api.Result;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: QcResponse.kt */
@Metadata(m606d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001ad\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u000127\b\u0002\u0010\u0004\u001a1\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0005¢\u0006\u0002\b\u000bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a\u0085\u0001\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000e\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012+\b\u0002\u0010\u000f\u001a%\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0010¢\u0006\u0002\b\u000b2+\b\u0002\u0010\u0004\u001a%\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0010¢\u0006\u0002\b\u000bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a^\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u000121\b\u0002\u0010\u000f\u001a+\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0013¢\u0006\u0002\b\u000bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, m607d2 = {"error", "Lcom/glasssutdio/wear/api/QcResponse;", ExifInterface.GPS_DIRECTION_TRUE, "", "errorBlock", "Lkotlin/Function4;", "Lkotlinx/coroutines/CoroutineScope;", "", "", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lcom/glasssutdio/wear/api/QcResponse;Lkotlin/jvm/functions/Function4;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeResponse", "Lcom/glasssutdio/wear/api/Result;", "successBlock", "Lkotlin/Function2;", "(Lcom/glasssutdio/wear/api/QcResponse;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", ANConstants.SUCCESS, "Lkotlin/Function3;", "(Lcom/glasssutdio/wear/api/QcResponse;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"}, m608k = 2, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class QcResponseKt {
    public static /* synthetic */ Object executeResponse$default(QcResponse qcResponse, Function2 function2, Function2 function22, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function2 = null;
        }
        if ((i & 2) != 0) {
            function22 = null;
        }
        return executeResponse(qcResponse, function2, function22, continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: QcResponse.kt */
    @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "Lcom/glasssutdio/wear/api/Result;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.api.QcResponseKt$executeResponse$2", m620f = "QcResponse.kt", m621i = {}, m622l = {14, 20}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.api.QcResponseKt$executeResponse$2 */
    static final class C08492<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends T>>, Object> {
        final /* synthetic */ Function2<CoroutineScope, Continuation<? super Unit>, Object> $errorBlock;
        final /* synthetic */ Function2<CoroutineScope, Continuation<? super Unit>, Object> $successBlock;
        final /* synthetic */ QcResponse<T> $this_executeResponse;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08492(QcResponse<? extends T> qcResponse, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function22, Continuation<? super C08492> continuation) {
            super(2, continuation);
            this.$this_executeResponse = qcResponse;
            this.$errorBlock = function2;
            this.$successBlock = function22;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C08492 c08492 = new C08492(this.$this_executeResponse, this.$errorBlock, this.$successBlock, continuation);
            c08492.L$0 = obj;
            return c08492;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends T>> continuation) {
            return ((C08492) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0044  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return new Result.Success(this.$this_executeResponse.getData());
                }
                ResultKt.throwOnFailure(obj);
                if (this.$this_executeResponse.getRetCode() == 401) {
                    UserConfig.INSTANCE.getInstance().setUserToken("15ef6eb5403406c1da0dc4a4defa2ea1");
                }
                return new Result.Error(new IOException(this.$this_executeResponse.getMessage()));
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            if (this.$this_executeResponse.getRetCode() != 0) {
                Function2<CoroutineScope, Continuation<? super Unit>, Object> function2 = this.$errorBlock;
                if (function2 != null) {
                    this.label = 1;
                    if (function2.invoke(coroutineScope, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                if (this.$this_executeResponse.getRetCode() == 401) {
                }
                return new Result.Error(new IOException(this.$this_executeResponse.getMessage()));
            }
            Function2<CoroutineScope, Continuation<? super Unit>, Object> function22 = this.$successBlock;
            if (function22 != null) {
                this.label = 2;
                if (function22.invoke(coroutineScope, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return new Result.Success(this.$this_executeResponse.getData());
        }
    }

    public static final <T> Object executeResponse(QcResponse<? extends T> qcResponse, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function22, Continuation<? super Result<? extends T>> continuation) {
        return CoroutineScopeKt.coroutineScope(new C08492(qcResponse, function22, function2, null), continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: QcResponse.kt */
    @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "Lcom/glasssutdio/wear/api/QcResponse;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.api.QcResponseKt$success$2", m620f = "QcResponse.kt", m621i = {}, m622l = {28}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.api.QcResponseKt$success$2 */
    static final class C08502<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super QcResponse<? extends T>>, Object> {
        final /* synthetic */ Function3<CoroutineScope, T, Continuation<? super Unit>, Object> $successBlock;
        final /* synthetic */ QcResponse<T> $this_success;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08502(QcResponse<? extends T> qcResponse, Function3<? super CoroutineScope, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super C08502> continuation) {
            super(2, continuation);
            this.$this_success = qcResponse;
            this.$successBlock = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C08502 c08502 = new C08502(this.$this_success, this.$successBlock, continuation);
            c08502.L$0 = obj;
            return c08502;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super QcResponse<? extends T>> continuation) {
            return ((C08502) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Function3<CoroutineScope, T, Continuation<? super Unit>, Object> function3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                if (this.$this_success.getRetCode() == 0 && (function3 = this.$successBlock) != null) {
                    T data = this.$this_success.getData();
                    this.label = 1;
                    if (function3.invoke(coroutineScope, data, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return this.$this_success;
        }
    }

    public static /* synthetic */ Object success$default(QcResponse qcResponse, Function3 function3, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function3 = null;
        }
        return success(qcResponse, function3, continuation);
    }

    public static final <T> Object success(QcResponse<? extends T> qcResponse, Function3<? super CoroutineScope, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super QcResponse<? extends T>> continuation) {
        return CoroutineScopeKt.coroutineScope(new C08502(qcResponse, function3, null), continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: QcResponse.kt */
    @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "Lcom/glasssutdio/wear/api/QcResponse;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.api.QcResponseKt$error$2", m620f = "QcResponse.kt", m621i = {}, m622l = {39}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.api.QcResponseKt$error$2 */
    static final class C08482<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super QcResponse<? extends T>>, Object> {
        final /* synthetic */ Function4<CoroutineScope, Integer, String, Continuation<? super Unit>, Object> $errorBlock;
        final /* synthetic */ QcResponse<T> $this_error;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08482(QcResponse<? extends T> qcResponse, Function4<? super CoroutineScope, ? super Integer, ? super String, ? super Continuation<? super Unit>, ? extends Object> function4, Continuation<? super C08482> continuation) {
            super(2, continuation);
            this.$this_error = qcResponse;
            this.$errorBlock = function4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C08482 c08482 = new C08482(this.$this_error, this.$errorBlock, continuation);
            c08482.L$0 = obj;
            return c08482;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super QcResponse<? extends T>> continuation) {
            return ((C08482) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Function4<CoroutineScope, Integer, String, Continuation<? super Unit>, Object> function4;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                if (this.$this_error.getRetCode() == 401) {
                    UserConfig.INSTANCE.getInstance().setUserToken("15ef6eb5403406c1da0dc4a4defa2ea1");
                }
                if (this.$this_error.getRetCode() != 0 && (function4 = this.$errorBlock) != null) {
                    Integer numBoxInt = Boxing.boxInt(this.$this_error.getRetCode());
                    String message = this.$this_error.getMessage();
                    this.label = 1;
                    if (function4.invoke(coroutineScope, numBoxInt, message, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return this.$this_error;
        }
    }

    public static /* synthetic */ Object error$default(QcResponse qcResponse, Function4 function4, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function4 = null;
        }
        return error(qcResponse, function4, continuation);
    }

    public static final <T> Object error(QcResponse<? extends T> qcResponse, Function4<? super CoroutineScope, ? super Integer, ? super String, ? super Continuation<? super Unit>, ? extends Object> function4, Continuation<? super QcResponse<? extends T>> continuation) {
        return CoroutineScopeKt.coroutineScope(new C08482(qcResponse, function4, null), continuation);
    }
}
