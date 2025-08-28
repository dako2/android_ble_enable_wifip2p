package kotlin.time;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.FilenameUtils;
import kotlin.Metadata;

/* compiled from: TimeSources.kt */
@Metadata(m606d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086\u0002¢\u0006\u0004\b\f\u0010\nJ\b\u0010\r\u001a\u00020\u0004H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m607d2 = {"Lkotlin/time/TestTimeSource;", "Lkotlin/time/AbstractLongTimeSource;", "()V", "reading", "", "overflow", "", TypedValues.TransitionType.S_DURATION, "Lkotlin/time/Duration;", "overflow-LRDsOJo", "(J)V", "plusAssign", "plusAssign-LRDsOJo", "read", "kotlin-stdlib"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes3.dex */
public final class TestTimeSource extends AbstractLongTimeSource {
    private long reading;

    public TestTimeSource() {
        super(DurationUnit.NANOSECONDS);
        markNow();
    }

    @Override // kotlin.time.AbstractLongTimeSource
    /* renamed from: read, reason: from getter */
    protected long getReading() {
        return this.reading;
    }

    /* renamed from: plusAssign-LRDsOJo, reason: not valid java name */
    public final void m2365plusAssignLRDsOJo(long duration) {
        long jM2275toLongimpl = Duration.m2275toLongimpl(duration, getUnit());
        if (((jM2275toLongimpl - 1) | 1) != Long.MAX_VALUE) {
            long j = this.reading;
            long j2 = j + jM2275toLongimpl;
            if ((jM2275toLongimpl ^ j) >= 0 && (j ^ j2) < 0) {
                m2364overflowLRDsOJo(duration);
            }
            this.reading = j2;
            return;
        }
        long jM2232divUwyO8pc = Duration.m2232divUwyO8pc(duration, 2);
        if ((1 | (Duration.m2275toLongimpl(jM2232divUwyO8pc, getUnit()) - 1)) != Long.MAX_VALUE) {
            long j3 = this.reading;
            try {
                m2365plusAssignLRDsOJo(jM2232divUwyO8pc);
                m2365plusAssignLRDsOJo(Duration.m2264minusLRDsOJo(duration, jM2232divUwyO8pc));
                return;
            } catch (IllegalStateException e) {
                this.reading = j3;
                throw e;
            }
        }
        m2364overflowLRDsOJo(duration);
    }

    /* renamed from: overflow-LRDsOJo, reason: not valid java name */
    private final void m2364overflowLRDsOJo(long duration) {
        throw new IllegalStateException("TestTimeSource will overflow if its reading " + this.reading + DurationUnitKt.shortName(getUnit()) + " is advanced by " + ((Object) Duration.m2278toStringimpl(duration)) + FilenameUtils.EXTENSION_SEPARATOR);
    }
}
