package com.glasssutdio.wear.api;

import com.androidnetworking.common.ANConstants;
import com.glasssutdio.wear.all.pref.UserConfig;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: QcNoDataResponse.kt */
@Metadata(m606d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aH\u0010\u0000\u001a\u00020\u0001*\u00020\u000121\b\u0002\u0010\u0002\u001a+\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0003¢\u0006\u0002\b\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001aH\u0010\u000b\u001a\u00020\u0001*\u00020\u000121\b\u0002\u0010\f\u001a+\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0003¢\u0006\u0002\b\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, m607d2 = {"error", "Lcom/glasssutdio/wear/api/QcNoDataResponse;", "errorBlock", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lcom/glasssutdio/wear/api/QcNoDataResponse;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", ANConstants.SUCCESS, "successBlock", "app_release"}, m608k = 2, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class QcNoDataResponseKt {

    /* compiled from: QcNoDataResponse.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "Lcom/glasssutdio/wear/api/QcNoDataResponse;", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.api.QcNoDataResponseKt$success$2", m620f = "QcNoDataResponse.kt", m621i = {}, m622l = {11}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.api.QcNoDataResponseKt$success$2 */
    static final class C08472 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super QcNoDataResponse>, Object> {
        final /* synthetic */ Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> $successBlock;
        final /* synthetic */ QcNoDataResponse $this_success;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08472(QcNoDataResponse qcNoDataResponse, Function3<? super CoroutineScope, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super C08472> continuation) {
            super(2, continuation);
            this.$this_success = qcNoDataResponse;
            this.$successBlock = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C08472 c08472 = new C08472(this.$this_success, this.$successBlock, continuation);
            c08472.L$0 = obj;
            return c08472;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super QcNoDataResponse> continuation) {
            return ((C08472) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> function3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                if (this.$this_success.getRetCode() == 0 && (function3 = this.$successBlock) != null) {
                    Integer numBoxInt = Boxing.boxInt(this.$this_success.getRetCode());
                    this.label = 1;
                    if (function3.invoke(coroutineScope, numBoxInt, this) == coroutine_suspended) {
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

    public static /* synthetic */ Object success$default(QcNoDataResponse qcNoDataResponse, Function3 function3, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function3 = null;
        }
        return success(qcNoDataResponse, function3, continuation);
    }

    public static final Object success(QcNoDataResponse qcNoDataResponse, Function3<? super CoroutineScope, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super QcNoDataResponse> continuation) {
        return CoroutineScopeKt.coroutineScope(new C08472(qcNoDataResponse, function3, null), continuation);
    }

    /* compiled from: QcNoDataResponse.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "Lcom/glasssutdio/wear/api/QcNoDataResponse;", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.api.QcNoDataResponseKt$error$2", m620f = "QcNoDataResponse.kt", m621i = {}, m622l = {18}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.api.QcNoDataResponseKt$error$2 */
    static final class C08462 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super QcNoDataResponse>, Object> {
        final /* synthetic */ Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> $errorBlock;
        final /* synthetic */ QcNoDataResponse $this_error;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08462(QcNoDataResponse qcNoDataResponse, Function3<? super CoroutineScope, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super C08462> continuation) {
            super(2, continuation);
            this.$this_error = qcNoDataResponse;
            this.$errorBlock = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C08462 c08462 = new C08462(this.$this_error, this.$errorBlock, continuation);
            c08462.L$0 = obj;
            return c08462;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super QcNoDataResponse> continuation) {
            return ((C08462) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> function3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                if (this.$this_error.getRetCode() != 0 && (function3 = this.$errorBlock) != null) {
                    Integer numBoxInt = Boxing.boxInt(this.$this_error.getRetCode());
                    this.label = 1;
                    if (function3.invoke(coroutineScope, numBoxInt, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            if (this.$this_error.getRetCode() == 401) {
                UserConfig.INSTANCE.getInstance().setUserToken("15ef6eb5403406c1da0dc4a4defa2ea1");
            }
            return this.$this_error;
        }
    }

    public static /* synthetic */ Object error$default(QcNoDataResponse qcNoDataResponse, Function3 function3, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function3 = null;
        }
        return error(qcNoDataResponse, function3, continuation);
    }

    public static final Object error(QcNoDataResponse qcNoDataResponse, Function3<? super CoroutineScope, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super QcNoDataResponse> continuation) {
        return CoroutineScopeKt.coroutineScope(new C08462(qcNoDataResponse, function3, null), continuation);
    }
}
