package androidx.room;

import androidx.room.InvalidationTracker;
import com.glasssutdio.wear.home.album.water.WatermarkGenerator;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: Add missing generic type declarations: [R] */
/* compiled from: CoroutinesRoom.kt */
@Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\r\u0012\t\u0012\u0007H\u0002¢\u0006\u0002\b\u00040\u0003H\u008a@"}, m607d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/jvm/JvmSuppressWildcards;"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
@DebugMetadata(m619c = "androidx.room.CoroutinesRoom$Companion$createFlow$1", m620f = "CoroutinesRoom.kt", m621i = {}, m622l = {EMachine.EM_EXCESS}, m623m = "invokeSuspend", m624n = {}, m625s = {})
/* loaded from: classes.dex */
final class CoroutinesRoom$Companion$createFlow$1<R> extends SuspendLambda implements Function2<FlowCollector<R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Callable<R> $callable;
    final /* synthetic */ RoomDatabase $db;
    final /* synthetic */ boolean $inTransaction;
    final /* synthetic */ String[] $tableNames;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CoroutinesRoom$Companion$createFlow$1(boolean z, RoomDatabase roomDatabase, String[] strArr, Callable<R> callable, Continuation<? super CoroutinesRoom$Companion$createFlow$1> continuation) {
        super(2, continuation);
        this.$inTransaction = z;
        this.$db = roomDatabase;
        this.$tableNames = strArr;
        this.$callable = callable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CoroutinesRoom$Companion$createFlow$1 coroutinesRoom$Companion$createFlow$1 = new CoroutinesRoom$Companion$createFlow$1(this.$inTransaction, this.$db, this.$tableNames, this.$callable, continuation);
        coroutinesRoom$Companion$createFlow$1.L$0 = obj;
        return coroutinesRoom$Companion$createFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(FlowCollector<R> flowCollector, Continuation<? super Unit> continuation) {
        return ((CoroutinesRoom$Companion$createFlow$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: CoroutinesRoom.kt */
    @Metadata(m606d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, m607d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "androidx.room.CoroutinesRoom$Companion$createFlow$1$1", m620f = "CoroutinesRoom.kt", m621i = {}, m622l = {EMachine.EM_VIDEOCORE3}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: androidx.room.CoroutinesRoom$Companion$createFlow$1$1 */
    static final class C05611 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ FlowCollector<R> $$this$flow;
        final /* synthetic */ Callable<R> $callable;
        final /* synthetic */ RoomDatabase $db;
        final /* synthetic */ boolean $inTransaction;
        final /* synthetic */ String[] $tableNames;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05611(boolean z, RoomDatabase roomDatabase, FlowCollector<R> flowCollector, String[] strArr, Callable<R> callable, Continuation<? super C05611> continuation) {
            super(2, continuation);
            this.$inTransaction = z;
            this.$db = roomDatabase;
            this.$$this$flow = flowCollector;
            this.$tableNames = strArr;
            this.$callable = callable;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05611 c05611 = new C05611(this.$inTransaction, this.$db, this.$$this$flow, this.$tableNames, this.$callable, continuation);
            c05611.L$0 = obj;
            return c05611;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05611) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r7v0, types: [androidx.room.CoroutinesRoom$Companion$createFlow$1$1$observer$1] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineDispatcher transactionDispatcher;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                final Channel channelChannel$default = ChannelKt.Channel$default(-1, null, null, 6, null);
                final String[] strArr = this.$tableNames;
                ?? r7 = new InvalidationTracker.Observer(strArr) { // from class: androidx.room.CoroutinesRoom$Companion$createFlow$1$1$observer$1
                    @Override // androidx.room.InvalidationTracker.Observer
                    public void onInvalidated(Set<String> tables) {
                        channelChannel$default.mo2405trySendJP2dKIU(Unit.INSTANCE);
                    }
                };
                channelChannel$default.mo2405trySendJP2dKIU(Unit.INSTANCE);
                TransactionElement transactionElement = (TransactionElement) coroutineScope.getCoroutineContext().get(TransactionElement.INSTANCE);
                if (transactionElement == null || (transactionDispatcher = transactionElement.getTransactionDispatcher()) == null) {
                    transactionDispatcher = this.$inTransaction ? CoroutinesRoomKt.getTransactionDispatcher(this.$db) : CoroutinesRoomKt.getQueryDispatcher(this.$db);
                }
                Channel channelChannel$default2 = ChannelKt.Channel$default(0, null, null, 7, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, transactionDispatcher, null, new AnonymousClass1(this.$db, r7, channelChannel$default, this.$callable, channelChannel$default2, null), 2, null);
                this.label = 1;
                if (FlowKt.emitAll(this.$$this$flow, channelChannel$default2, this) == coroutine_suspended) {
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

        /* compiled from: CoroutinesRoom.kt */
        @Metadata(m606d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, m607d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
        @DebugMetadata(m619c = "androidx.room.CoroutinesRoom$Companion$createFlow$1$1$1", m620f = "CoroutinesRoom.kt", m621i = {}, m622l = {128, WatermarkGenerator.WATERMARK_HEIGHT_DP}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: androidx.room.CoroutinesRoom$Companion$createFlow$1$1$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Callable<R> $callable;
            final /* synthetic */ RoomDatabase $db;
            final /* synthetic */ CoroutinesRoom$Companion$createFlow$1$1$observer$1<R> $observer;
            final /* synthetic */ Channel<Unit> $observerChannel;
            final /* synthetic */ Channel<R> $resultChannel;
            Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(RoomDatabase roomDatabase, CoroutinesRoom$Companion$createFlow$1$1$observer$1<R> coroutinesRoom$Companion$createFlow$1$1$observer$1, Channel<Unit> channel, Callable<R> callable, Channel<R> channel2, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$db = roomDatabase;
                this.$observer = coroutinesRoom$Companion$createFlow$1$1$observer$1;
                this.$observerChannel = channel;
                this.$callable = callable;
                this.$resultChannel = channel2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.$db, this.$observer, this.$observerChannel, this.$callable, this.$resultChannel, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x004a A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:19:0x004b  */
            /* JADX WARN: Removed duplicated region for block: B:22:0x0056 A[Catch: all -> 0x007f, TRY_LEAVE, TryCatch #0 {all -> 0x007f, blocks: (B:7:0x0012, B:16:0x003d, B:20:0x004e, B:22:0x0056, B:12:0x0023, B:15:0x0037), top: B:30:0x0008 }] */
            /* JADX WARN: Removed duplicated region for block: B:25:0x006f  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x006c -> B:8:0x0015). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object invokeSuspend(Object obj) {
                ChannelIterator<Unit> it;
                ChannelIterator<Unit> channelIterator;
                Object objHasNext;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                try {
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.$db.getInvalidationTracker().addObserver(this.$observer);
                        it = this.$observerChannel.iterator();
                        this.L$0 = it;
                        this.label = 1;
                        objHasNext = it.hasNext(this);
                        if (objHasNext == coroutine_suspended) {
                        }
                    } else if (i == 1) {
                        channelIterator = (ChannelIterator) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        if (!((Boolean) obj).booleanValue()) {
                        }
                    } else {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        channelIterator = (ChannelIterator) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        it = channelIterator;
                        this.L$0 = it;
                        this.label = 1;
                        objHasNext = it.hasNext(this);
                        if (objHasNext == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        channelIterator = it;
                        obj = objHasNext;
                        if (!((Boolean) obj).booleanValue()) {
                            channelIterator.next();
                            R rCall = this.$callable.call();
                            this.L$0 = channelIterator;
                            this.label = 2;
                            if (this.$resultChannel.send(rCall, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            it = channelIterator;
                            this.L$0 = it;
                            this.label = 1;
                            objHasNext = it.hasNext(this);
                            if (objHasNext == coroutine_suspended) {
                            }
                        } else {
                            this.$db.getInvalidationTracker().removeObserver(this.$observer);
                            return Unit.INSTANCE;
                        }
                    }
                } catch (Throwable th) {
                    this.$db.getInvalidationTracker().removeObserver(this.$observer);
                    throw th;
                }
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            this.label = 1;
            if (CoroutineScopeKt.coroutineScope(new C05611(this.$inTransaction, this.$db, flowCollector, this.$tableNames, this.$callable, null), this) == coroutine_suspended) {
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
