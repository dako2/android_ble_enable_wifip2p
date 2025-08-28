package kotlinx.coroutines.channels;

import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.AbstractTimeSource;
import kotlinx.coroutines.AbstractTimeSourceKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.EventLoop_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: TickerChannels.kt */
@Metadata(m606d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a/\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a/\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a4\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, m607d2 = {"fixedDelayTicker", "", "delayMillis", "", "initialDelayMillis", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "(JJLkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fixedPeriodTicker", "ticker", "Lkotlinx/coroutines/channels/ReceiveChannel;", "context", "Lkotlin/coroutines/CoroutineContext;", "mode", "Lkotlinx/coroutines/channels/TickerMode;", "kotlinx-coroutines-core"}, m608k = 2, m609mv = {1, 8, 0}, m611xi = 48)
/* loaded from: classes3.dex */
public final class TickerChannelsKt {

    /* compiled from: TickerChannels.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.TickerChannelsKt", m620f = "TickerChannels.kt", m621i = {0, 0, 1, 1, 2, 2}, m622l = {EMachine.EM_BLACKFIN, 108, 109}, m623m = "fixedDelayTicker", m624n = {"channel", "delayMillis", "channel", "delayMillis", "channel", "delayMillis"}, m625s = {"L$0", "J$0", "L$0", "J$0", "L$0", "J$0"})
    /* renamed from: kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1 */
    static final class C27641 extends ContinuationImpl {
        long J$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C27641(Continuation<? super C27641> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TickerChannelsKt.fixedDelayTicker(0L, 0L, null, this);
        }
    }

    /* compiled from: TickerChannels.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.TickerChannelsKt", m620f = "TickerChannels.kt", m621i = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3}, m622l = {EMachine.EM_FR30, EMachine.EM_M32R, EMachine.EM_XTENSA, EMachine.EM_TMM_GPP}, m623m = "fixedPeriodTicker", m624n = {"channel", "delayMillis", "deadline", "channel", "deadline", "delayNs", "channel", "deadline", "delayNs", "channel", "deadline", "delayNs"}, m625s = {"L$0", "J$0", "J$1", "L$0", "J$0", "J$1", "L$0", "J$0", "J$1", "L$0", "J$0", "J$1"})
    /* renamed from: kotlinx.coroutines.channels.TickerChannelsKt$fixedPeriodTicker$1 */
    static final class C27651 extends ContinuationImpl {
        long J$0;
        long J$1;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C27651(Continuation<? super C27651> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TickerChannelsKt.fixedPeriodTicker(0L, 0L, null, this);
        }
    }

    public static /* synthetic */ ReceiveChannel ticker$default(long j, long j2, CoroutineContext coroutineContext, TickerMode tickerMode, int i, Object obj) {
        if ((i & 2) != 0) {
            j2 = j;
        }
        if ((i & 4) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 8) != 0) {
            tickerMode = TickerMode.FIXED_PERIOD;
        }
        return ticker(j, j2, coroutineContext, tickerMode);
    }

    public static final ReceiveChannel<Unit> ticker(long j, long j2, CoroutineContext coroutineContext, TickerMode tickerMode) {
        if (j < 0) {
            throw new IllegalArgumentException(("Expected non-negative delay, but has " + j + " ms").toString());
        }
        if (j2 < 0) {
            throw new IllegalArgumentException(("Expected non-negative initial delay, but has " + j2 + " ms").toString());
        }
        return ProduceKt.produce(GlobalScope.INSTANCE, Dispatchers.getUnconfined().plus(coroutineContext), 0, new C27663(tickerMode, j, j2, null));
    }

    /* compiled from: TickerChannels.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.TickerChannelsKt$ticker$3", m620f = "TickerChannels.kt", m621i = {}, m622l = {EMachine.EM_68HC05, EMachine.EM_SVX}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: kotlinx.coroutines.channels.TickerChannelsKt$ticker$3 */
    static final class C27663 extends SuspendLambda implements Function2<ProducerScope<? super Unit>, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $delayMillis;
        final /* synthetic */ long $initialDelayMillis;
        final /* synthetic */ TickerMode $mode;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: TickerChannels.kt */
        @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
        /* renamed from: kotlinx.coroutines.channels.TickerChannelsKt$ticker$3$WhenMappings */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[TickerMode.values().length];
                try {
                    iArr[TickerMode.FIXED_PERIOD.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[TickerMode.FIXED_DELAY.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C27663(TickerMode tickerMode, long j, long j2, Continuation<? super C27663> continuation) {
            super(2, continuation);
            this.$mode = tickerMode;
            this.$delayMillis = j;
            this.$initialDelayMillis = j2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C27663 c27663 = new C27663(this.$mode, this.$delayMillis, this.$initialDelayMillis, continuation);
            c27663.L$0 = obj;
            return c27663;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super Unit> producerScope, Continuation<? super Unit> continuation) {
            return ((C27663) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope = (ProducerScope) this.L$0;
                int i2 = WhenMappings.$EnumSwitchMapping$0[this.$mode.ordinal()];
                if (i2 == 1) {
                    this.label = 1;
                    if (TickerChannelsKt.fixedPeriodTicker(this.$delayMillis, this.$initialDelayMillis, producerScope.getChannel(), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i2 == 2) {
                    this.label = 2;
                    if (TickerChannelsKt.fixedDelayTicker(this.$delayMillis, this.$initialDelayMillis, producerScope.getChannel(), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1 && i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00bc A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0115 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:46:0x00fd -> B:31:0x00ab). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:48:0x0113 -> B:15:0x003e). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object fixedPeriodTicker(long j, long j2, SendChannel<? super Unit> sendChannel, Continuation<? super Unit> continuation) {
        C27651 c27651;
        SendChannel sendChannel2;
        long j3;
        long j4;
        long jDelayToNanos;
        long j5;
        long j6;
        SendChannel sendChannel3;
        char c;
        long j7;
        long jCoerceAtLeast;
        char c2;
        long jDelayNanosToMillis;
        char c3;
        Unit unit;
        if (continuation instanceof C27651) {
            c27651 = (C27651) continuation;
            if ((c27651.label & Integer.MIN_VALUE) != 0) {
                c27651.label -= Integer.MIN_VALUE;
            } else {
                c27651 = new C27651(continuation);
            }
        }
        Object obj = c27651.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c27651.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
            long jNanoTime = (timeSource != null ? timeSource.nanoTime() : System.nanoTime()) + EventLoop_commonKt.delayToNanos(j2);
            sendChannel2 = sendChannel;
            c27651.L$0 = sendChannel2;
            j3 = j;
            c27651.J$0 = j3;
            c27651.J$1 = jNanoTime;
            c27651.label = 1;
            if (DelayKt.delay(j2, c27651) == coroutine_suspended) {
                return coroutine_suspended;
            }
            j4 = jNanoTime;
        } else if (i == 1) {
            j4 = c27651.J$1;
            long j8 = c27651.J$0;
            SendChannel sendChannel4 = (SendChannel) c27651.L$0;
            ResultKt.throwOnFailure(obj);
            sendChannel2 = sendChannel4;
            j3 = j8;
        } else if (i == 2) {
            j6 = c27651.J$1;
            j7 = c27651.J$0;
            sendChannel3 = (SendChannel) c27651.L$0;
            ResultKt.throwOnFailure(obj);
            AbstractTimeSource timeSource2 = AbstractTimeSourceKt.getTimeSource();
            if (timeSource2 == null) {
            }
            jCoerceAtLeast = RangesKt.coerceAtLeast(j7 - jNanoTime, 0L);
            if (jCoerceAtLeast != 0) {
            }
            c2 = 3;
            jDelayNanosToMillis = EventLoop_commonKt.delayNanosToMillis(jCoerceAtLeast);
            c27651.L$0 = sendChannel3;
            c27651.J$0 = j7;
            c27651.J$1 = j6;
            c3 = 4;
            c27651.label = 4;
            if (DelayKt.delay(jDelayNanosToMillis, c27651) == coroutine_suspended) {
            }
            long j9 = j6;
            j4 = j7;
            jDelayToNanos = j9;
            sendChannel2 = sendChannel3;
            long j10 = j4 + jDelayToNanos;
            unit = Unit.INSTANCE;
            c27651.L$0 = sendChannel2;
            c27651.J$0 = j10;
            c27651.J$1 = jDelayToNanos;
            c27651.label = 2;
            if (sendChannel2.send(unit, c27651) != coroutine_suspended) {
            }
        } else if (i == 3) {
            j6 = c27651.J$1;
            j5 = c27651.J$0;
            sendChannel3 = (SendChannel) c27651.L$0;
            ResultKt.throwOnFailure(obj);
            c = 3;
            long j11 = j6;
            j4 = j5;
            jDelayToNanos = j11;
            sendChannel2 = sendChannel3;
            long j102 = j4 + jDelayToNanos;
            unit = Unit.INSTANCE;
            c27651.L$0 = sendChannel2;
            c27651.J$0 = j102;
            c27651.J$1 = jDelayToNanos;
            c27651.label = 2;
            if (sendChannel2.send(unit, c27651) != coroutine_suspended) {
            }
        } else {
            if (i != 4) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            j6 = c27651.J$1;
            j7 = c27651.J$0;
            sendChannel3 = (SendChannel) c27651.L$0;
            ResultKt.throwOnFailure(obj);
            c3 = 4;
            c2 = 3;
            long j92 = j6;
            j4 = j7;
            jDelayToNanos = j92;
            sendChannel2 = sendChannel3;
            long j1022 = j4 + jDelayToNanos;
            unit = Unit.INSTANCE;
            c27651.L$0 = sendChannel2;
            c27651.J$0 = j1022;
            c27651.J$1 = jDelayToNanos;
            c27651.label = 2;
            if (sendChannel2.send(unit, c27651) != coroutine_suspended) {
                return coroutine_suspended;
            }
            sendChannel3 = sendChannel2;
            j6 = jDelayToNanos;
            j7 = j1022;
            AbstractTimeSource timeSource22 = AbstractTimeSourceKt.getTimeSource();
            long jNanoTime2 = timeSource22 == null ? timeSource22.nanoTime() : System.nanoTime();
            jCoerceAtLeast = RangesKt.coerceAtLeast(j7 - jNanoTime2, 0L);
            if (jCoerceAtLeast != 0 && j6 != 0) {
                long j12 = j6 - ((jNanoTime2 - j7) % j6);
                j5 = jNanoTime2 + j12;
                long jDelayNanosToMillis2 = EventLoop_commonKt.delayNanosToMillis(j12);
                c27651.L$0 = sendChannel3;
                c27651.J$0 = j5;
                c27651.J$1 = j6;
                c = 3;
                c27651.label = 3;
                if (DelayKt.delay(jDelayNanosToMillis2, c27651) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                long j112 = j6;
                j4 = j5;
                jDelayToNanos = j112;
                sendChannel2 = sendChannel3;
                long j10222 = j4 + jDelayToNanos;
                unit = Unit.INSTANCE;
                c27651.L$0 = sendChannel2;
                c27651.J$0 = j10222;
                c27651.J$1 = jDelayToNanos;
                c27651.label = 2;
                if (sendChannel2.send(unit, c27651) != coroutine_suspended) {
                }
            } else {
                c2 = 3;
                jDelayNanosToMillis = EventLoop_commonKt.delayNanosToMillis(jCoerceAtLeast);
                c27651.L$0 = sendChannel3;
                c27651.J$0 = j7;
                c27651.J$1 = j6;
                c3 = 4;
                c27651.label = 4;
                if (DelayKt.delay(jDelayNanosToMillis, c27651) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                long j922 = j6;
                j4 = j7;
                jDelayToNanos = j922;
                sendChannel2 = sendChannel3;
                long j102222 = j4 + jDelayToNanos;
                unit = Unit.INSTANCE;
                c27651.L$0 = sendChannel2;
                c27651.J$0 = j102222;
                c27651.J$1 = jDelayToNanos;
                c27651.label = 2;
                if (sendChannel2.send(unit, c27651) != coroutine_suspended) {
                }
            }
        }
        jDelayToNanos = EventLoop_commonKt.delayToNanos(j3);
        long j1022222 = j4 + jDelayToNanos;
        unit = Unit.INSTANCE;
        c27651.L$0 = sendChannel2;
        c27651.J$0 = j1022222;
        c27651.J$1 = jDelayToNanos;
        c27651.label = 2;
        if (sendChannel2.send(unit, c27651) != coroutine_suspended) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0072 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0080 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x007e -> B:14:0x0035). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object fixedDelayTicker(long j, long j2, SendChannel<? super Unit> sendChannel, Continuation<? super Unit> continuation) {
        C27641 c27641;
        SendChannel<? super Unit> sendChannel2;
        if (continuation instanceof C27641) {
            c27641 = (C27641) continuation;
            if ((c27641.label & Integer.MIN_VALUE) != 0) {
                c27641.label -= Integer.MIN_VALUE;
            } else {
                c27641 = new C27641(continuation);
            }
        }
        Object obj = c27641.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c27641.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            c27641.L$0 = sendChannel;
            c27641.J$0 = j;
            c27641.label = 1;
            if (DelayKt.delay(j2, c27641) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            j = c27641.J$0;
            sendChannel = (SendChannel) c27641.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            j = c27641.J$0;
            sendChannel2 = (SendChannel) c27641.L$0;
            ResultKt.throwOnFailure(obj);
            c27641.L$0 = sendChannel2;
            c27641.J$0 = j;
            c27641.label = 3;
            if (DelayKt.delay(j, c27641) == coroutine_suspended) {
                return coroutine_suspended;
            }
            sendChannel = sendChannel2;
        } else {
            if (i != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            j = c27641.J$0;
            sendChannel2 = (SendChannel) c27641.L$0;
            ResultKt.throwOnFailure(obj);
            sendChannel = sendChannel2;
        }
        Unit unit = Unit.INSTANCE;
        c27641.L$0 = sendChannel;
        c27641.J$0 = j;
        c27641.label = 2;
        if (sendChannel.send(unit, c27641) != coroutine_suspended) {
            return coroutine_suspended;
        }
        sendChannel2 = sendChannel;
        c27641.L$0 = sendChannel2;
        c27641.J$0 = j;
        c27641.label = 3;
        if (DelayKt.delay(j, c27641) == coroutine_suspended) {
        }
        sendChannel = sendChannel2;
        Unit unit2 = Unit.INSTANCE;
        c27641.L$0 = sendChannel;
        c27641.J$0 = j;
        c27641.label = 2;
        if (sendChannel.send(unit2, c27641) != coroutine_suspended) {
        }
    }
}
