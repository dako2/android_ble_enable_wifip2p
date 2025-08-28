package com.glasssutdio.wear.p003ai.adapter;

import android.widget.TextView;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.p003ai.bean.ChatMessage;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: TextStreamer.kt */
@Metadata(m606d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\fJ\u000e\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000e\u001a\u00020\bR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m607d2 = {"Lcom/glasssutdio/wear/ai/adapter/TextStreamer;", "", "textView", "Landroid/widget/TextView;", "(Landroid/widget/TextView;)V", "job", "Lkotlinx/coroutines/Job;", "append", "", "message", "Lcom/glasssutdio/wear/ai/bean/ChatMessage;", "onComplete", "Lkotlin/Function0;", "display", "reset", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class TextStreamer {
    private Job job;
    private final TextView textView;

    public TextStreamer(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "textView");
        this.textView = textView;
    }

    public final void reset() {
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.textView.setText("");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void append$default(TextStreamer textStreamer, ChatMessage chatMessage, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = null;
        }
        textStreamer.append(chatMessage, function0);
    }

    public final void append(ChatMessage message, Function0<Unit> onComplete) {
        Intrinsics.checkNotNullParameter(message, "message");
        XLog.m137i(message.getMessage());
        if (message.getMessage().length() <= 300) {
            Job job = this.job;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.textView.setText(message.getMessage());
            message.setStreamIndex(message.getMessage().length());
            message.setStreamedContent(message.getMessage());
            message.setStreamComplete(true);
            if (onComplete != null) {
                onComplete.invoke();
                return;
            }
            return;
        }
        if (message.getStreamIndex() >= message.getMessage().length()) {
            this.textView.setText(message.getMessage());
            if (onComplete != null) {
                onComplete.invoke();
                return;
            }
            return;
        }
        Job job2 = this.job;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        this.job = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new C07791(message, this, onComplete, null), 3, null);
    }

    /* compiled from: TextStreamer.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.ai.adapter.TextStreamer$append$1", m620f = "TextStreamer.kt", m621i = {0, 0}, m622l = {50}, m623m = "invokeSuspend", m624n = {"charsPerFrame", "frameDelay"}, m625s = {"I$0", "J$0"})
    /* renamed from: com.glasssutdio.wear.ai.adapter.TextStreamer$append$1 */
    static final class C07791 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ChatMessage $message;
        final /* synthetic */ Function0<Unit> $onComplete;
        int I$0;
        long J$0;
        int label;
        final /* synthetic */ TextStreamer this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07791(ChatMessage chatMessage, TextStreamer textStreamer, Function0<Unit> function0, Continuation<? super C07791> continuation) {
            super(2, continuation);
            this.$message = chatMessage;
            this.this$0 = textStreamer;
            this.$onComplete = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C07791(this.$message, this.this$0, this.$onComplete, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07791) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            long j;
            int i;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                j = 60;
                i = 10;
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                j = this.J$0;
                i = this.I$0;
                ResultKt.throwOnFailure(obj);
            }
            while (this.$message.getStreamIndex() < this.$message.getMessage().length()) {
                int iCoerceAtMost = RangesKt.coerceAtMost(this.$message.getStreamIndex() + i, this.$message.getMessage().length());
                ChatMessage chatMessage = this.$message;
                String strSubstring = chatMessage.getMessage().substring(0, iCoerceAtMost);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                chatMessage.setStreamedContent(strSubstring);
                this.$message.setStreamIndex(iCoerceAtMost);
                this.this$0.textView.setText(this.$message.getStreamedContent());
                this.I$0 = i;
                this.J$0 = j;
                this.label = 1;
                if (DelayKt.delay(j, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            this.$message.setStreamComplete(true);
            Function0<Unit> function0 = this.$onComplete;
            if (function0 != null) {
                function0.invoke();
            }
            return Unit.INSTANCE;
        }
    }

    public final void display(ChatMessage message) {
        Intrinsics.checkNotNullParameter(message, "message");
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.textView.setText(message.getMessage());
    }
}
