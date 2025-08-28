package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.language.p007bm.Languages;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.FilenameUtils;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpStatus;
import io.reactivex.annotations.SchedulerSupport;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import okhttp3.internal.p017ws.WebSocketProtocol;

/* compiled from: Deprecated.kt */
@Metadata(m606d1 = {"\u0000 \u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aJ\u0010\u0000\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u00072\u001a\u0010\b\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\n0\t\"\u0006\u0012\u0002\b\u00030\nH\u0001¢\u0006\u0002\u0010\u000b\u001a!\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a1\u0010\u0010\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007*\u0006\u0012\u0002\b\u00030\nH\u0001\u001a!\u0010\u0011\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0007\u001aZ\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u0010\u0015*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u0010\u0018\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00150\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a0\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007\u001aT\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a)\u0010!\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010\"\u001a\u00020\u0012H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010#\u001a+\u0010$\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010\"\u001a\u00020\u0012H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010#\u001aT\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001ai\u0010&\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u001727\u0010 \u001a3\b\u0001\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0'H\u0007ø\u0001\u0000¢\u0006\u0002\u0010(\u001aT\u0010)\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a$\u0010*\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\b\b\u0000\u0010\u000e*\u00020\u001b*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000e0\nH\u0001\u001aA\u0010+\u001a\u0002H,\"\b\b\u0000\u0010\u000e*\u00020\u001b\"\u0010\b\u0001\u0010,*\n\u0012\u0006\b\u0000\u0012\u0002H\u000e0-*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000e0\n2\u0006\u0010.\u001a\u0002H,H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010/\u001a?\u0010+\u001a\u0002H,\"\b\b\u0000\u0010\u000e*\u00020\u001b\"\u000e\b\u0001\u0010,*\b\u0012\u0004\u0012\u0002H\u000e00*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000e0\n2\u0006\u0010.\u001a\u0002H,H\u0087@ø\u0001\u0000¢\u0006\u0002\u00101\u001a!\u00102\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a#\u00103\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a`\u00104\u001a\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u00105*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172(\u00106\u001a$\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H50\n0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a)\u00107\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u00108\u001a\u0002H\u000eH\u0087@ø\u0001\u0000¢\u0006\u0002\u00109\u001a!\u0010:\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a)\u0010;\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u00108\u001a\u0002H\u000eH\u0087@ø\u0001\u0000¢\u0006\u0002\u00109\u001a#\u0010<\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001aZ\u0010=\u001a\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u00105*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u00106\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H50\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001ao\u0010>\u001a\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u00105*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u001727\u00106\u001a3\b\u0001\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H50\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0'H\u0001ø\u0001\u0000¢\u0006\u0002\u0010(\u001au\u0010?\u001a\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000e\"\b\b\u0001\u00105*\u00020\u001b*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u001729\u00106\u001a5\b\u0001\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u000e\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H50\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0'H\u0007ø\u0001\u0000¢\u0006\u0002\u0010(\u001a`\u0010@\u001a\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000e\"\b\b\u0001\u00105*\u00020\u001b*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172$\u00106\u001a \b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H50\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a?\u0010A\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u001a\u0010B\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000e0Cj\n\u0012\u0006\b\u0000\u0012\u0002H\u000e`DH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010E\u001a?\u0010F\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u001a\u0010B\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000e0Cj\n\u0012\u0006\b\u0000\u0012\u0002H\u000e`DH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010E\u001a!\u0010G\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a$\u0010H\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\b\b\u0000\u0010\u000e*\u00020\u001b*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000e0\nH\u0007\u001a!\u0010I\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a#\u0010J\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a0\u0010K\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007\u001aT\u0010L\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a9\u0010M\u001a\u0002H,\"\u0004\b\u0000\u0010\u000e\"\u000e\b\u0001\u0010,*\b\u0012\u0004\u0012\u0002H\u000e00*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010.\u001a\u0002H,H\u0081@ø\u0001\u0000¢\u0006\u0002\u00101\u001a;\u0010N\u001a\u0002H,\"\u0004\b\u0000\u0010\u000e\"\u0010\b\u0001\u0010,*\n\u0012\u0006\b\u0000\u0012\u0002H\u000e0-*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010.\u001a\u0002H,H\u0081@ø\u0001\u0000¢\u0006\u0002\u0010/\u001a?\u0010O\u001a\u000e\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002HQ0P\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010Q*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002HQ0R0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001aU\u0010O\u001a\u0002HS\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010Q\"\u0018\b\u0002\u0010S*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0015\u0012\u0006\b\u0000\u0012\u0002HQ0T*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002HQ0R0\n2\u0006\u0010.\u001a\u0002HSH\u0081@ø\u0001\u0000¢\u0006\u0002\u0010U\u001a'\u0010V\u001a\b\u0012\u0004\u0012\u0002H\u000e0W\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a'\u0010X\u001a\b\u0012\u0004\u0012\u0002H\u000e0Y\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0081@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a'\u0010Z\u001a\b\u0012\u0004\u0012\u0002H\u000e0[\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a.\u0010\\\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0]0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007\u001a?\u0010^\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H50R0\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u00105*\b\u0012\u0004\u0012\u0002H\u000e0\n2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002H50\nH\u0087\u0004\u001az\u0010^\u001a\b\u0012\u0004\u0012\u0002HQ0\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u00105\"\u0004\b\u0002\u0010Q*\b\u0012\u0004\u0012\u0002H\u000e0\n2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002H50\n2\b\b\u0002\u0010\u0016\u001a\u00020\u001726\u00106\u001a2\u0012\u0013\u0012\u0011H\u000e¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(`\u0012\u0013\u0012\u0011H5¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(a\u0012\u0004\u0012\u0002HQ0\u0019H\u0001\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006b"}, m607d2 = {"consumesAll", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "channels", "", "Lkotlinx/coroutines/channels/ReceiveChannel;", "([Lkotlinx/coroutines/channels/ReceiveChannel;)Lkotlin/jvm/functions/Function1;", Languages.ANY, "", ExifInterface.LONGITUDE_EAST, "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "consumes", "count", "", "distinct", "distinctBy", "K", "context", "Lkotlin/coroutines/CoroutineContext;", "selector", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/ReceiveChannel;", "drop", "n", "dropWhile", "predicate", "elementAt", "index", "(Lkotlinx/coroutines/channels/ReceiveChannel;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "elementAtOrNull", "filter", "filterIndexed", "Lkotlin/Function3;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/channels/ReceiveChannel;", "filterNot", "filterNotNull", "filterNotNullTo", "C", "", "destination", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/SendChannel;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "first", "firstOrNull", "flatMap", "R", "transform", "indexOf", "element", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "last", "lastIndexOf", "lastOrNull", "map", "mapIndexed", "mapIndexedNotNull", "mapNotNull", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Comparator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "minWith", SchedulerSupport.NONE, "requireNoNulls", "single", "singleOrNull", "take", "takeWhile", "toChannel", "toCollection", "toMap", "", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/Pair;", "M", "", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toMutableList", "", "toMutableSet", "", "toSet", "", "withIndex", "Lkotlin/collections/IndexedValue;", "zip", "other", "a", "b", "kotlinx-coroutines-core"}, m608k = 5, m609mv = {1, 8, 0}, m611xi = 48, m612xs = "kotlinx/coroutines/channels/ChannelsKt")
/* loaded from: classes3.dex */
final /* synthetic */ class ChannelsKt__DeprecatedKt {

    /* compiled from: Deprecated.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m620f = "Deprecated.kt", m621i = {0}, m622l = {HttpStatus.SC_NOT_FOUND}, m623m = Languages.ANY, m624n = {"$this$consume$iv"}, m625s = {"L$0"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$any$1 */
    static final class C27221<E> extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C27221(Continuation<? super C27221> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.any(null, this);
        }
    }

    /* compiled from: Deprecated.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m620f = "Deprecated.kt", m621i = {0, 0}, m622l = {487}, m623m = "count", m624n = {"count", "$this$consume$iv$iv"}, m625s = {"L$0", "L$1"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$count$1 */
    static final class C27251<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C27251(Continuation<? super C27251> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.count(null, this);
        }
    }

    /* compiled from: Deprecated.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m620f = "Deprecated.kt", m621i = {0, 0, 0}, m622l = {38}, m623m = "elementAt", m624n = {"$this$consume$iv", "index", "count"}, m625s = {"L$0", "I$0", "I$1"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAt$1 */
    static final class C27301<E> extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C27301(Continuation<? super C27301> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.elementAt(null, 0, this);
        }
    }

    /* compiled from: Deprecated.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m620f = "Deprecated.kt", m621i = {0, 0, 0}, m622l = {53}, m623m = "elementAtOrNull", m624n = {"$this$consume$iv", "index", "count"}, m625s = {"L$0", "I$0", "I$1"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAtOrNull$1 */
    static final class C27311<E> extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C27311(Continuation<? super C27311> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.elementAtOrNull(null, 0, this);
        }
    }

    /* compiled from: Deprecated.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m620f = "Deprecated.kt", m621i = {0, 0}, m622l = {487}, m623m = "filterNotNullTo", m624n = {"destination", "$this$consume$iv$iv"}, m625s = {"L$0", "L$1"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$1 */
    static final class C27361<E, C extends Collection<? super E>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C27361(Continuation<? super C27361> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.filterNotNullTo((ReceiveChannel) null, (Collection) null, this);
        }
    }

    /* compiled from: Deprecated.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m620f = "Deprecated.kt", m621i = {0, 0, 1, 1}, m622l = {487, 242}, m623m = "filterNotNullTo", m624n = {"destination", "$this$consume$iv$iv", "destination", "$this$consume$iv$iv"}, m625s = {"L$0", "L$1", "L$0", "L$1"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$3 */
    static final class C27373<E, C extends SendChannel<? super E>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C27373(Continuation<? super C27373> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.filterNotNullTo((ReceiveChannel) null, (SendChannel) null, this);
        }
    }

    /* compiled from: Deprecated.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m620f = "Deprecated.kt", m621i = {0, 0}, m622l = {65}, m623m = "first", m624n = {"$this$consume$iv", "iterator"}, m625s = {"L$0", "L$1"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$first$1 */
    static final class C27381<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C27381(Continuation<? super C27381> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.first(null, this);
        }
    }

    /* compiled from: Deprecated.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m620f = "Deprecated.kt", m621i = {0, 0}, m622l = {EMachine.EM_VAX}, m623m = "firstOrNull", m624n = {"$this$consume$iv", "iterator"}, m625s = {"L$0", "L$1"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$firstOrNull$1 */
    static final class C27391<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C27391(Continuation<? super C27391> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.firstOrNull(null, this);
        }
    }

    /* compiled from: Deprecated.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m620f = "Deprecated.kt", m621i = {0, 0, 0}, m622l = {487}, m623m = "indexOf", m624n = {"element", "index", "$this$consume$iv$iv"}, m625s = {"L$0", "L$1", "L$2"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$indexOf$1 */
    static final class C27411<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C27411(Continuation<? super C27411> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.indexOf(null, null, this);
        }
    }

    /* compiled from: Deprecated.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m620f = "Deprecated.kt", m621i = {0, 0, 1, 1, 1}, m622l = {EMachine.EM_NS32K, 100}, m623m = "last", m624n = {"$this$consume$iv", "iterator", "$this$consume$iv", "iterator", "last"}, m625s = {"L$0", "L$1", "L$0", "L$1", "L$2"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$last$1 */
    static final class C27421<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C27421(Continuation<? super C27421> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.last(null, this);
        }
    }

    /* compiled from: Deprecated.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m620f = "Deprecated.kt", m621i = {0, 0, 0, 0}, m622l = {487}, m623m = "lastIndexOf", m624n = {"element", "lastIndex", "index", "$this$consume$iv$iv"}, m625s = {"L$0", "L$1", "L$2", "L$3"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastIndexOf$1 */
    static final class C27431<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C27431(Continuation<? super C27431> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.lastIndexOf(null, null, this);
        }
    }

    /* compiled from: Deprecated.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m620f = "Deprecated.kt", m621i = {0, 0, 1, 1, 1}, m622l = {123, WebSocketProtocol.PAYLOAD_SHORT}, m623m = "lastOrNull", m624n = {"$this$consume$iv", "iterator", "$this$consume$iv", "iterator", "last"}, m625s = {"L$0", "L$1", "L$0", "L$1", "L$2"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastOrNull$1 */
    static final class C27441<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C27441(Continuation<? super C27441> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.lastOrNull(null, this);
        }
    }

    /* compiled from: Deprecated.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m620f = "Deprecated.kt", m621i = {0, 0, 0, 1, 1, 1, 1}, m622l = {420, 422}, m623m = "maxWith", m624n = {"comparator", "$this$consume$iv", "iterator", "comparator", "$this$consume$iv", "iterator", "max"}, m625s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$maxWith$1 */
    static final class C27471<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C27471(Continuation<? super C27471> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.maxWith(null, null, this);
        }
    }

    /* compiled from: Deprecated.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m620f = "Deprecated.kt", m621i = {0, 0, 0, 1, 1, 1, 1}, m622l = {434, 436}, m623m = "minWith", m624n = {"comparator", "$this$consume$iv", "iterator", "comparator", "$this$consume$iv", "iterator", "min"}, m625s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$minWith$1 */
    static final class C27481<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C27481(Continuation<? super C27481> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.minWith(null, null, this);
        }
    }

    /* compiled from: Deprecated.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m620f = "Deprecated.kt", m621i = {0}, m622l = {447}, m623m = SchedulerSupport.NONE, m624n = {"$this$consume$iv"}, m625s = {"L$0"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$none$1 */
    static final class C27491<E> extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C27491(Continuation<? super C27491> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.none(null, this);
        }
    }

    /* compiled from: Deprecated.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m620f = "Deprecated.kt", m621i = {0, 0, 1, 1}, m622l = {EMachine.EM_DSP24, EMachine.EM_SE_C17}, m623m = "single", m624n = {"$this$consume$iv", "iterator", "$this$consume$iv", "single"}, m625s = {"L$0", "L$1", "L$0", "L$1"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$single$1 */
    static final class C27511<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C27511(Continuation<? super C27511> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.single(null, this);
        }
    }

    /* compiled from: Deprecated.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m620f = "Deprecated.kt", m621i = {0, 0, 1, 1}, m622l = {149, 152}, m623m = "singleOrNull", m624n = {"$this$consume$iv", "iterator", "$this$consume$iv", "single"}, m625s = {"L$0", "L$1", "L$0", "L$1"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$singleOrNull$1 */
    static final class C27521<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C27521(Continuation<? super C27521> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.singleOrNull(null, this);
        }
    }

    /* compiled from: Deprecated.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m620f = "Deprecated.kt", m621i = {0, 0, 1, 1}, m622l = {487, 278}, m623m = "toChannel", m624n = {"destination", "$this$consume$iv$iv", "destination", "$this$consume$iv$iv"}, m625s = {"L$0", "L$1", "L$0", "L$1"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toChannel$1 */
    static final class C27551<E, C extends SendChannel<? super E>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C27551(Continuation<? super C27551> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt.toChannel(null, null, this);
        }
    }

    /* compiled from: Deprecated.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m620f = "Deprecated.kt", m621i = {0, 0}, m622l = {487}, m623m = "toCollection", m624n = {"destination", "$this$consume$iv$iv"}, m625s = {"L$0", "L$1"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toCollection$1 */
    static final class C27561<E, C extends Collection<? super E>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C27561(Continuation<? super C27561> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt.toCollection(null, null, this);
        }
    }

    /* compiled from: Deprecated.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m620f = "Deprecated.kt", m621i = {0, 0}, m622l = {487}, m623m = "toMap", m624n = {"destination", "$this$consume$iv$iv"}, m625s = {"L$0", "L$1"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toMap$2 */
    static final class C27572<K, V, M extends Map<? super K, ? super V>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C27572(Continuation<? super C27572> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt.toMap(null, null, this);
        }
    }

    public static final Function1<Throwable, Unit> consumesAll(final ReceiveChannel<?>... receiveChannelArr) {
        return new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.consumesAll.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) throws Throwable {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) throws Throwable {
                Throwable th2 = null;
                for (ReceiveChannel<?> receiveChannel : receiveChannelArr) {
                    try {
                        ChannelsKt.cancelConsumed(receiveChannel, th);
                    } catch (Throwable th3) {
                        if (th2 == null) {
                            th2 = th3;
                        } else {
                            ExceptionsKt.addSuppressed(th2, th3);
                        }
                    }
                }
                if (th2 != null) {
                    throw th2;
                }
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0060 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006e A[Catch: all -> 0x003b, TRY_LEAVE, TryCatch #2 {all -> 0x003b, blocks: (B:12:0x0037, B:25:0x0066, B:27:0x006e, B:33:0x007e, B:34:0x0098), top: B:46:0x0037 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007e A[Catch: all -> 0x003b, TRY_ENTER, TryCatch #2 {all -> 0x003b, blocks: (B:12:0x0037, B:25:0x0066, B:27:0x006e, B:33:0x007e, B:34:0x0098), top: B:46:0x0037 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0061 -> B:25:0x0066). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object elementAt(ReceiveChannel receiveChannel, int i, Continuation continuation) {
        C27301 c27301;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator it;
        int i2;
        Object objHasNext;
        if (continuation instanceof C27301) {
            c27301 = (C27301) continuation;
            if ((c27301.label & Integer.MIN_VALUE) != 0) {
                c27301.label -= Integer.MIN_VALUE;
            } else {
                c27301 = new C27301(continuation);
            }
        }
        Object obj = c27301.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c27301.label;
        try {
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                if (i < 0) {
                    throw new IndexOutOfBoundsException("ReceiveChannel doesn't contain element at index " + i + FilenameUtils.EXTENSION_SEPARATOR);
                }
                it = receiveChannel.iterator();
                i2 = 0;
                c27301.L$0 = receiveChannel;
                c27301.L$1 = it;
                c27301.I$0 = i;
                c27301.I$1 = i2;
                c27301.label = 1;
                objHasNext = it.hasNext(c27301);
                if (objHasNext != coroutine_suspended) {
                }
            } else {
                if (i3 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i4 = c27301.I$1;
                i = c27301.I$0;
                ChannelIterator channelIterator = (ChannelIterator) c27301.L$1;
                receiveChannel2 = (ReceiveChannel) c27301.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    if (!((Boolean) obj).booleanValue()) {
                        Object next = channelIterator.next();
                        int i5 = i4 + 1;
                        if (i == i4) {
                            ChannelsKt.cancelConsumed(receiveChannel2, null);
                            return next;
                        }
                        it = channelIterator;
                        receiveChannel = receiveChannel2;
                        i2 = i5;
                        c27301.L$0 = receiveChannel;
                        c27301.L$1 = it;
                        c27301.I$0 = i;
                        c27301.I$1 = i2;
                        c27301.label = 1;
                        objHasNext = it.hasNext(c27301);
                        if (objHasNext != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        receiveChannel2 = receiveChannel;
                        i4 = i2;
                        channelIterator = it;
                        obj = objHasNext;
                        if (!((Boolean) obj).booleanValue()) {
                            throw new IndexOutOfBoundsException("ReceiveChannel doesn't contain element at index " + i + FilenameUtils.EXTENSION_SEPARATOR);
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        throw th;
                    } catch (Throwable th3) {
                        ChannelsKt.cancelConsumed(receiveChannel2, th);
                        throw th3;
                    }
                }
            }
        } catch (Throwable th4) {
            receiveChannel2 = receiveChannel;
            th = th4;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0066 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0074 A[Catch: all -> 0x0089, TRY_LEAVE, TryCatch #0 {all -> 0x0089, blocks: (B:27:0x006c, B:29:0x0074, B:23:0x0056, B:22:0x0050), top: B:43:0x0050 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0085 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0067 -> B:27:0x006c). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object elementAtOrNull(ReceiveChannel receiveChannel, int i, Continuation continuation) {
        C27311 c27311;
        ChannelIterator it;
        int i2;
        Throwable th;
        Throwable th2;
        ReceiveChannel receiveChannel2;
        Object objHasNext;
        if (continuation instanceof C27311) {
            c27311 = (C27311) continuation;
            if ((c27311.label & Integer.MIN_VALUE) != 0) {
                c27311.label -= Integer.MIN_VALUE;
            } else {
                c27311 = new C27311(continuation);
            }
        }
        Object obj = c27311.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c27311.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            if (i >= 0) {
                try {
                    it = receiveChannel.iterator();
                    i2 = 0;
                    th = null;
                    c27311.L$0 = receiveChannel;
                    c27311.L$1 = it;
                    c27311.I$0 = i;
                    c27311.I$1 = i2;
                    c27311.label = 1;
                    objHasNext = it.hasNext(c27311);
                    if (objHasNext != coroutine_suspended) {
                    }
                } catch (Throwable th3) {
                    receiveChannel2 = receiveChannel;
                    th2 = th3;
                    throw th2;
                }
            } else {
                ChannelsKt.cancelConsumed(receiveChannel, null);
                return null;
            }
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i4 = c27311.I$1;
            i = c27311.I$0;
            ChannelIterator channelIterator = (ChannelIterator) c27311.L$1;
            receiveChannel2 = (ReceiveChannel) c27311.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                Throwable th4 = null;
                i2 = i4;
                receiveChannel = receiveChannel2;
                C27311 c273112 = c27311;
                ChannelIterator channelIterator2 = channelIterator;
                if (!((Boolean) obj).booleanValue()) {
                    Object next = channelIterator2.next();
                    int i5 = i2 + 1;
                    if (i == i2) {
                        return next;
                    }
                    it = channelIterator2;
                    c27311 = c273112;
                    th = th2;
                    i2 = i5;
                    c27311.L$0 = receiveChannel;
                    c27311.L$1 = it;
                    c27311.I$0 = i;
                    c27311.I$1 = i2;
                    c27311.label = 1;
                    objHasNext = it.hasNext(c27311);
                    if (objHasNext != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    C27311 c273113 = c27311;
                    channelIterator2 = it;
                    obj = objHasNext;
                    th4 = th;
                    c273112 = c273113;
                    if (!((Boolean) obj).booleanValue()) {
                        return null;
                    }
                }
            } catch (Throwable th5) {
                th2 = th5;
                try {
                    throw th2;
                } finally {
                    ChannelsKt.cancelConsumed(receiveChannel2, th2);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005c A[Catch: all -> 0x0032, TRY_LEAVE, TryCatch #1 {all -> 0x0032, blocks: (B:12:0x002e, B:23:0x0054, B:25:0x005c, B:28:0x0065, B:29:0x006c), top: B:38:0x002e }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0065 A[Catch: all -> 0x0032, TRY_ENTER, TryCatch #1 {all -> 0x0032, blocks: (B:12:0x002e, B:23:0x0054, B:25:0x005c, B:28:0x0065, B:29:0x006c), top: B:38:0x002e }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object first(ReceiveChannel receiveChannel, Continuation continuation) {
        C27381 c27381;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator channelIterator;
        if (continuation instanceof C27381) {
            c27381 = (C27381) continuation;
            if ((c27381.label & Integer.MIN_VALUE) != 0) {
                c27381.label -= Integer.MIN_VALUE;
            } else {
                c27381 = new C27381(continuation);
            }
        }
        Object obj = c27381.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c27381.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            channelIterator = (ChannelIterator) c27381.L$1;
            receiveChannel2 = (ReceiveChannel) c27381.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                if (((Boolean) obj).booleanValue()) {
                    throw new NoSuchElementException("ReceiveChannel is empty.");
                }
                Object next = channelIterator.next();
                ChannelsKt.cancelConsumed(receiveChannel2, null);
                return next;
            } catch (Throwable th2) {
                th = th2;
                try {
                    throw th;
                } catch (Throwable th3) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th3;
                }
            }
        }
        ResultKt.throwOnFailure(obj);
        try {
            ChannelIterator it = receiveChannel.iterator();
            c27381.L$0 = receiveChannel;
            c27381.L$1 = it;
            c27381.label = 1;
            Object objHasNext = it.hasNext(c27381);
            if (objHasNext == coroutine_suspended) {
                return coroutine_suspended;
            }
            receiveChannel2 = receiveChannel;
            channelIterator = it;
            obj = objHasNext;
            if (((Boolean) obj).booleanValue()) {
            }
        } catch (Throwable th4) {
            receiveChannel2 = receiveChannel;
            th = th4;
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object firstOrNull(ReceiveChannel receiveChannel, Continuation continuation) {
        C27391 c27391;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator channelIterator;
        if (continuation instanceof C27391) {
            c27391 = (C27391) continuation;
            if ((c27391.label & Integer.MIN_VALUE) != 0) {
                c27391.label -= Integer.MIN_VALUE;
            } else {
                c27391 = new C27391(continuation);
            }
        }
        Object obj = c27391.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c27391.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                ChannelIterator it = receiveChannel.iterator();
                c27391.L$0 = receiveChannel;
                c27391.L$1 = it;
                c27391.label = 1;
                Object objHasNext = it.hasNext(c27391);
                if (objHasNext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                receiveChannel2 = receiveChannel;
                channelIterator = it;
                obj = objHasNext;
            } catch (Throwable th2) {
                receiveChannel2 = receiveChannel;
                th = th2;
                throw th;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            channelIterator = (ChannelIterator) c27391.L$1;
            receiveChannel2 = (ReceiveChannel) c27391.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th3) {
                th = th3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th4;
                }
            }
        }
        if (((Boolean) obj).booleanValue()) {
            Object next = channelIterator.next();
            ChannelsKt.cancelConsumed(receiveChannel2, null);
            return next;
        }
        ChannelsKt.cancelConsumed(receiveChannel2, null);
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0064 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0070 A[Catch: all -> 0x0039, TryCatch #1 {all -> 0x0039, blocks: (B:12:0x0035, B:25:0x0068, B:27:0x0070, B:29:0x007a, B:32:0x0084, B:21:0x0054, B:33:0x008b), top: B:44:0x0035 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008b A[Catch: all -> 0x0039, TRY_LEAVE, TryCatch #1 {all -> 0x0039, blocks: (B:12:0x0035, B:25:0x0068, B:27:0x0070, B:29:0x007a, B:32:0x0084, B:21:0x0054, B:33:0x008b), top: B:44:0x0035 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0065 -> B:25:0x0068). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object indexOf(ReceiveChannel receiveChannel, Object obj, Continuation continuation) {
        C27411 c27411;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator it;
        Ref.IntRef intRef;
        Object obj2;
        Object objHasNext;
        if (continuation instanceof C27411) {
            c27411 = (C27411) continuation;
            if ((c27411.label & Integer.MIN_VALUE) != 0) {
                c27411.label -= Integer.MIN_VALUE;
            } else {
                c27411 = new C27411(continuation);
            }
        }
        Object obj3 = c27411.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c27411.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj3);
            Ref.IntRef intRef2 = new Ref.IntRef();
            try {
                receiveChannel2 = receiveChannel;
                it = receiveChannel.iterator();
                intRef = intRef2;
                obj2 = obj;
                c27411.L$0 = obj2;
                c27411.L$1 = intRef;
                c27411.L$2 = receiveChannel2;
                c27411.L$3 = it;
                c27411.label = 1;
                objHasNext = it.hasNext(c27411);
                if (objHasNext != coroutine_suspended) {
                }
            } catch (Throwable th2) {
                receiveChannel2 = receiveChannel;
                th = th2;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (ChannelIterator) c27411.L$3;
            receiveChannel2 = (ReceiveChannel) c27411.L$2;
            intRef = (Ref.IntRef) c27411.L$1;
            Object obj4 = c27411.L$0;
            try {
                ResultKt.throwOnFailure(obj3);
                if (((Boolean) obj3).booleanValue()) {
                    if (Intrinsics.areEqual(obj4, it.next())) {
                        Integer numBoxInt = Boxing.boxInt(intRef.element);
                        ChannelsKt.cancelConsumed(receiveChannel2, null);
                        return numBoxInt;
                    }
                    intRef.element++;
                    obj2 = obj4;
                    c27411.L$0 = obj2;
                    c27411.L$1 = intRef;
                    c27411.L$2 = receiveChannel2;
                    c27411.L$3 = it;
                    c27411.label = 1;
                    objHasNext = it.hasNext(c27411);
                    if (objHasNext != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj4 = obj2;
                    obj3 = objHasNext;
                    if (((Boolean) obj3).booleanValue()) {
                    }
                } else {
                    Unit unit = Unit.INSTANCE;
                    ChannelsKt.cancelConsumed(receiveChannel2, null);
                    return Boxing.boxInt(-1);
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        try {
            throw th;
        } catch (Throwable th4) {
            ChannelsKt.cancelConsumed(receiveChannel2, th);
            throw th4;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0087 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0094 A[Catch: all -> 0x0038, TRY_LEAVE, TryCatch #2 {all -> 0x0038, blocks: (B:13:0x0034, B:37:0x008c, B:39:0x0094), top: B:54:0x0034 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x0088 -> B:37:0x008c). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object last(ReceiveChannel receiveChannel, Continuation continuation) {
        C27421 c27421;
        ReceiveChannel receiveChannel2;
        ChannelIterator channelIterator;
        Object next;
        ChannelIterator channelIterator2;
        Object objHasNext;
        if (continuation instanceof C27421) {
            c27421 = (C27421) continuation;
            if ((c27421.label & Integer.MIN_VALUE) != 0) {
                c27421.label -= Integer.MIN_VALUE;
            } else {
                c27421 = new C27421(continuation);
            }
        }
        Object obj = c27421.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c27421.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                ChannelIterator it = receiveChannel.iterator();
                c27421.L$0 = receiveChannel;
                c27421.L$1 = it;
                c27421.label = 1;
                Object objHasNext2 = it.hasNext(c27421);
                if (objHasNext2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                receiveChannel2 = receiveChannel;
                channelIterator = it;
                obj = objHasNext2;
            } catch (Throwable th) {
                receiveChannel2 = receiveChannel;
                th = th;
                throw th;
            }
        } else if (i == 1) {
            channelIterator = (ChannelIterator) c27421.L$1;
            receiveChannel2 = (ReceiveChannel) c27421.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Object obj2 = c27421.L$2;
            channelIterator2 = (ChannelIterator) c27421.L$1;
            ReceiveChannel receiveChannel3 = (ReceiveChannel) c27421.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                if (!((Boolean) obj).booleanValue()) {
                    next = channelIterator2.next();
                    receiveChannel = receiveChannel3;
                    c27421.L$0 = receiveChannel;
                    c27421.L$1 = channelIterator2;
                    c27421.L$2 = next;
                    c27421.label = 2;
                    objHasNext = channelIterator2.hasNext(c27421);
                    if (objHasNext != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    receiveChannel3 = receiveChannel;
                    obj2 = next;
                    obj = objHasNext;
                    if (!((Boolean) obj).booleanValue()) {
                        ChannelsKt.cancelConsumed(receiveChannel3, null);
                        return obj2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                receiveChannel2 = receiveChannel3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th4;
                }
            }
        }
        if (!((Boolean) obj).booleanValue()) {
            throw new NoSuchElementException("ReceiveChannel is empty.");
        }
        next = channelIterator.next();
        ReceiveChannel receiveChannel4 = receiveChannel2;
        channelIterator2 = channelIterator;
        receiveChannel = receiveChannel4;
        c27421.L$0 = receiveChannel;
        c27421.L$1 = channelIterator2;
        c27421.L$2 = next;
        c27421.label = 2;
        objHasNext = channelIterator2.hasNext(c27421);
        if (objHasNext != coroutine_suspended) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0073 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007f A[Catch: all -> 0x003d, TryCatch #2 {all -> 0x003d, blocks: (B:12:0x0039, B:25:0x0077, B:27:0x007f, B:29:0x0089, B:30:0x008d, B:21:0x0061, B:31:0x0094), top: B:44:0x0039 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0094 A[Catch: all -> 0x003d, TRY_LEAVE, TryCatch #2 {all -> 0x003d, blocks: (B:12:0x0039, B:25:0x0077, B:27:0x007f, B:29:0x0089, B:30:0x008d, B:21:0x0061, B:31:0x0094), top: B:44:0x0039 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0074 -> B:25:0x0077). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object lastIndexOf(ReceiveChannel receiveChannel, Object obj, Continuation continuation) {
        C27431 c27431;
        Ref.IntRef intRef;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator it;
        Ref.IntRef intRef2;
        Object obj2;
        Object objHasNext;
        if (continuation instanceof C27431) {
            c27431 = (C27431) continuation;
            if ((c27431.label & Integer.MIN_VALUE) != 0) {
                c27431.label -= Integer.MIN_VALUE;
            } else {
                c27431 = new C27431(continuation);
            }
        }
        Object obj3 = c27431.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c27431.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj3);
            Ref.IntRef intRef3 = new Ref.IntRef();
            intRef3.element = -1;
            intRef = new Ref.IntRef();
            try {
                receiveChannel2 = receiveChannel;
                it = receiveChannel.iterator();
                intRef2 = intRef3;
                obj2 = obj;
                c27431.L$0 = obj2;
                c27431.L$1 = intRef2;
                c27431.L$2 = intRef;
                c27431.L$3 = receiveChannel2;
                c27431.L$4 = it;
                c27431.label = 1;
                objHasNext = it.hasNext(c27431);
                if (objHasNext != coroutine_suspended) {
                }
            } catch (Throwable th2) {
                receiveChannel2 = receiveChannel;
                th = th2;
                throw th;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (ChannelIterator) c27431.L$4;
            receiveChannel2 = (ReceiveChannel) c27431.L$3;
            intRef = (Ref.IntRef) c27431.L$2;
            intRef2 = (Ref.IntRef) c27431.L$1;
            Object obj4 = c27431.L$0;
            try {
                ResultKt.throwOnFailure(obj3);
                if (((Boolean) obj3).booleanValue()) {
                    if (Intrinsics.areEqual(obj4, it.next())) {
                        intRef2.element = intRef.element;
                    }
                    intRef.element++;
                    obj2 = obj4;
                    c27431.L$0 = obj2;
                    c27431.L$1 = intRef2;
                    c27431.L$2 = intRef;
                    c27431.L$3 = receiveChannel2;
                    c27431.L$4 = it;
                    c27431.label = 1;
                    objHasNext = it.hasNext(c27431);
                    if (objHasNext != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj4 = obj2;
                    obj3 = objHasNext;
                    if (((Boolean) obj3).booleanValue()) {
                    }
                } else {
                    Unit unit = Unit.INSTANCE;
                    ChannelsKt.cancelConsumed(receiveChannel2, null);
                    return Boxing.boxInt(intRef2.element);
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th4;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x008b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0098 A[Catch: all -> 0x0038, TRY_LEAVE, TryCatch #3 {all -> 0x0038, blocks: (B:13:0x0034, B:39:0x0090, B:41:0x0098), top: B:57:0x0034 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x008c -> B:39:0x0090). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object lastOrNull(ReceiveChannel receiveChannel, Continuation continuation) {
        C27441 c27441;
        ReceiveChannel receiveChannel2;
        ChannelIterator channelIterator;
        Object next;
        ChannelIterator channelIterator2;
        Object objHasNext;
        if (continuation instanceof C27441) {
            c27441 = (C27441) continuation;
            if ((c27441.label & Integer.MIN_VALUE) != 0) {
                c27441.label -= Integer.MIN_VALUE;
            } else {
                c27441 = new C27441(continuation);
            }
        }
        Object obj = c27441.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c27441.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                ChannelIterator it = receiveChannel.iterator();
                c27441.L$0 = receiveChannel;
                c27441.L$1 = it;
                c27441.label = 1;
                Object objHasNext2 = it.hasNext(c27441);
                if (objHasNext2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                receiveChannel2 = receiveChannel;
                channelIterator = it;
                obj = objHasNext2;
            } catch (Throwable th) {
                receiveChannel2 = receiveChannel;
                th = th;
                throw th;
            }
        } else if (i == 1) {
            channelIterator = (ChannelIterator) c27441.L$1;
            receiveChannel2 = (ReceiveChannel) c27441.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Object obj2 = c27441.L$2;
            channelIterator2 = (ChannelIterator) c27441.L$1;
            ReceiveChannel receiveChannel3 = (ReceiveChannel) c27441.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                if (!((Boolean) obj).booleanValue()) {
                    next = channelIterator2.next();
                    receiveChannel = receiveChannel3;
                    c27441.L$0 = receiveChannel;
                    c27441.L$1 = channelIterator2;
                    c27441.L$2 = next;
                    c27441.label = 2;
                    objHasNext = channelIterator2.hasNext(c27441);
                    if (objHasNext != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    receiveChannel3 = receiveChannel;
                    obj2 = next;
                    obj = objHasNext;
                    if (!((Boolean) obj).booleanValue()) {
                        ChannelsKt.cancelConsumed(receiveChannel3, null);
                        return obj2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                receiveChannel2 = receiveChannel3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th4;
                }
            }
        }
        if (((Boolean) obj).booleanValue()) {
            next = channelIterator.next();
            ReceiveChannel receiveChannel4 = receiveChannel2;
            channelIterator2 = channelIterator;
            receiveChannel = receiveChannel4;
            c27441.L$0 = receiveChannel;
            c27441.L$1 = channelIterator2;
            c27441.L$2 = next;
            c27441.label = 2;
            objHasNext = channelIterator2.hasNext(c27441);
            if (objHasNext != coroutine_suspended) {
            }
        } else {
            ChannelsKt.cancelConsumed(receiveChannel2, null);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x006d A[Catch: all -> 0x004b, TRY_LEAVE, TryCatch #2 {all -> 0x004b, blocks: (B:20:0x0047, B:29:0x0065, B:31:0x006d, B:41:0x0097, B:42:0x009e), top: B:53:0x0047 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0097 A[Catch: all -> 0x004b, TRY_ENTER, TryCatch #2 {all -> 0x004b, blocks: (B:20:0x0047, B:29:0x0065, B:31:0x006d, B:41:0x0097, B:42:0x009e), top: B:53:0x0047 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object single(ReceiveChannel receiveChannel, Continuation continuation) {
        C27511 c27511;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator channelIterator;
        ReceiveChannel receiveChannel3;
        Object obj;
        if (continuation instanceof C27511) {
            c27511 = (C27511) continuation;
            if ((c27511.label & Integer.MIN_VALUE) != 0) {
                c27511.label -= Integer.MIN_VALUE;
            } else {
                c27511 = new C27511(continuation);
            }
        }
        Object obj2 = c27511.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c27511.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj2);
            try {
                ChannelIterator it = receiveChannel.iterator();
                c27511.L$0 = receiveChannel;
                c27511.L$1 = it;
                c27511.label = 1;
                Object objHasNext = it.hasNext(c27511);
                if (objHasNext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                receiveChannel2 = receiveChannel;
                channelIterator = it;
                obj2 = objHasNext;
                if (((Boolean) obj2).booleanValue()) {
                }
            } catch (Throwable th2) {
                receiveChannel2 = receiveChannel;
                th = th2;
                throw th;
            }
        } else if (i == 1) {
            channelIterator = (ChannelIterator) c27511.L$1;
            receiveChannel2 = (ReceiveChannel) c27511.L$0;
            try {
                ResultKt.throwOnFailure(obj2);
                if (((Boolean) obj2).booleanValue()) {
                    throw new NoSuchElementException("ReceiveChannel is empty.");
                }
                Object next = channelIterator.next();
                c27511.L$0 = receiveChannel2;
                c27511.L$1 = next;
                c27511.label = 2;
                Object objHasNext2 = channelIterator.hasNext(c27511);
                if (objHasNext2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                receiveChannel3 = receiveChannel2;
                obj2 = objHasNext2;
                obj = next;
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            obj = c27511.L$1;
            receiveChannel3 = (ReceiveChannel) c27511.L$0;
            try {
                ResultKt.throwOnFailure(obj2);
            } catch (Throwable th4) {
                th = th4;
                receiveChannel2 = receiveChannel3;
                try {
                    throw th;
                } catch (Throwable th5) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th5;
                }
            }
        }
        if (((Boolean) obj2).booleanValue()) {
            throw new IllegalArgumentException("ReceiveChannel has more than one element.");
        }
        ChannelsKt.cancelConsumed(receiveChannel3, null);
        return obj;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object singleOrNull(ReceiveChannel receiveChannel, Continuation continuation) {
        C27521 c27521;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator channelIterator;
        ReceiveChannel receiveChannel3;
        Object obj;
        if (continuation instanceof C27521) {
            c27521 = (C27521) continuation;
            if ((c27521.label & Integer.MIN_VALUE) != 0) {
                c27521.label -= Integer.MIN_VALUE;
            } else {
                c27521 = new C27521(continuation);
            }
        }
        Object obj2 = c27521.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c27521.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj2);
            try {
                ChannelIterator it = receiveChannel.iterator();
                c27521.L$0 = receiveChannel;
                c27521.L$1 = it;
                c27521.label = 1;
                Object objHasNext = it.hasNext(c27521);
                if (objHasNext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                receiveChannel2 = receiveChannel;
                channelIterator = it;
                obj2 = objHasNext;
            } catch (Throwable th2) {
                receiveChannel2 = receiveChannel;
                th = th2;
                throw th;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                obj = c27521.L$1;
                receiveChannel3 = (ReceiveChannel) c27521.L$0;
                try {
                    ResultKt.throwOnFailure(obj2);
                    if (((Boolean) obj2).booleanValue()) {
                        ChannelsKt.cancelConsumed(receiveChannel3, null);
                        return obj;
                    }
                    ChannelsKt.cancelConsumed(receiveChannel3, null);
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    receiveChannel2 = receiveChannel3;
                    try {
                        throw th;
                    } catch (Throwable th4) {
                        ChannelsKt.cancelConsumed(receiveChannel2, th);
                        throw th4;
                    }
                }
            }
            channelIterator = (ChannelIterator) c27521.L$1;
            receiveChannel2 = (ReceiveChannel) c27521.L$0;
            try {
                ResultKt.throwOnFailure(obj2);
            } catch (Throwable th5) {
                th = th5;
                throw th;
            }
        }
        if (((Boolean) obj2).booleanValue()) {
            Object next = channelIterator.next();
            c27521.L$0 = receiveChannel2;
            c27521.L$1 = next;
            c27521.label = 2;
            Object objHasNext2 = channelIterator.hasNext(c27521);
            if (objHasNext2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            receiveChannel3 = receiveChannel2;
            obj2 = objHasNext2;
            obj = next;
            if (((Boolean) obj2).booleanValue()) {
            }
        } else {
            ChannelsKt.cancelConsumed(receiveChannel2, null);
            return null;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* compiled from: Deprecated.kt */
    @Metadata(m606d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, m607d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/ProducerScope;"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$drop$1", m620f = "Deprecated.kt", m621i = {0, 0, 1, 2}, m622l = {EMachine.EM_HEXAGON, EMachine.EM_MAXQ30, EMachine.EM_XIMO16}, m623m = "invokeSuspend", m624n = {"$this$produce", "remaining", "$this$produce", "$this$produce"}, m625s = {"L$0", "I$0", "L$0", "L$0"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$drop$1 */
    static final class C27281<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {

        /* renamed from: $n */
        final /* synthetic */ int f924$n;
        final /* synthetic */ ReceiveChannel<E> $this_drop;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C27281(int i, ReceiveChannel<? extends E> receiveChannel, Continuation<? super C27281> continuation) {
            super(2, continuation);
            this.f924$n = i;
            this.$this_drop = receiveChannel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C27281 c27281 = new C27281(this.f924$n, this.$this_drop, continuation);
            c27281.L$0 = obj;
            return c27281;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
            return ((C27281) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:26:0x0078, code lost:
        
            if (r1 == 0) goto L27;
         */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0073  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0090 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0091  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x009c  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00b0  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0068 -> B:23:0x006b). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x00ad -> B:8:0x001c). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            ProducerScope producerScope;
            int i;
            ChannelIterator<E> it;
            ProducerScope producerScope2;
            ChannelIterator<E> it2;
            ProducerScope producerScope3;
            Object objHasNext;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                producerScope = (ProducerScope) this.L$0;
                i = this.f924$n;
                if (!(i >= 0)) {
                    throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
                }
                if (i > 0) {
                    it = this.$this_drop.iterator();
                    producerScope2 = producerScope;
                    this.L$0 = producerScope2;
                    this.L$1 = it;
                    this.I$0 = i;
                    this.label = 1;
                    obj = it.hasNext(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    if (((Boolean) obj).booleanValue()) {
                    }
                    producerScope = producerScope2;
                }
                it2 = this.$this_drop.iterator();
                this.L$0 = producerScope;
                this.L$1 = it2;
                this.label = 2;
                objHasNext = it2.hasNext(this);
                if (objHasNext != coroutine_suspended) {
                }
            } else if (i2 == 1) {
                i = this.I$0;
                it = (ChannelIterator) this.L$1;
                producerScope2 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                if (((Boolean) obj).booleanValue()) {
                    it.next();
                    i--;
                }
                producerScope = producerScope2;
                it2 = this.$this_drop.iterator();
                this.L$0 = producerScope;
                this.L$1 = it2;
                this.label = 2;
                objHasNext = it2.hasNext(this);
                if (objHasNext != coroutine_suspended) {
                }
            } else if (i2 == 2) {
                it2 = (ChannelIterator) this.L$1;
                producerScope3 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                if (!((Boolean) obj).booleanValue()) {
                }
            } else {
                if (i2 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                it2 = (ChannelIterator) this.L$1;
                producerScope3 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope = producerScope3;
                this.L$0 = producerScope;
                this.L$1 = it2;
                this.label = 2;
                objHasNext = it2.hasNext(this);
                if (objHasNext != coroutine_suspended) {
                    return coroutine_suspended;
                }
                producerScope3 = producerScope;
                obj = objHasNext;
                if (!((Boolean) obj).booleanValue()) {
                    this.L$0 = producerScope3;
                    this.L$1 = it2;
                    this.label = 3;
                    if (producerScope3.send(it2.next(), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    producerScope = producerScope3;
                    this.L$0 = producerScope;
                    this.L$1 = it2;
                    this.label = 2;
                    objHasNext = it2.hasNext(this);
                    if (objHasNext != coroutine_suspended) {
                    }
                } else {
                    return Unit.INSTANCE;
                }
            }
        }
    }

    public static /* synthetic */ ReceiveChannel drop$default(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return drop(receiveChannel, i, coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel drop(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C27281(i, receiveChannel, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel dropWhile$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return dropWhile(receiveChannel, coroutineContext, function2);
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* compiled from: Deprecated.kt */
    @Metadata(m606d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, m607d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/ProducerScope;"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$dropWhile$1", m620f = "Deprecated.kt", m621i = {0, 1, 1, 2, 3, 4}, m622l = {EMachine.EM_K10M, 182, EMachine.EM_AARCH64, EMachine.EM_TILE64, EMachine.EM_TILEPRO}, m623m = "invokeSuspend", m624n = {"$this$produce", "$this$produce", "e", "$this$produce", "$this$produce", "$this$produce"}, m625s = {"L$0", "L$0", "L$2", "L$0", "L$0", "L$0"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$dropWhile$1 */
    static final class C27291<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<E, Continuation<? super Boolean>, Object> $predicate;
        final /* synthetic */ ReceiveChannel<E> $this_dropWhile;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C27291(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super C27291> continuation) {
            super(2, continuation);
            this.$this_dropWhile = receiveChannel;
            this.$predicate = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C27291 c27291 = new C27291(this.$this_dropWhile, this.$predicate, continuation);
            c27291.L$0 = obj;
            return c27291;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
            return ((C27291) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0084 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:25:0x008d  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00af  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00c3  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00da A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00db  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x00e7  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x00fb  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00a2 -> B:16:0x0054). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x00f8 -> B:10:0x0023). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            ChannelIterator<E> it;
            ProducerScope producerScope;
            ProducerScope producerScope2;
            ChannelIterator<E> it2;
            ProducerScope producerScope3;
            ChannelIterator<E> channelIterator;
            Object objHasNext;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope4 = (ProducerScope) this.L$0;
                it = this.$this_dropWhile.iterator();
                producerScope = producerScope4;
                this.L$0 = producerScope;
                this.L$1 = it;
                this.L$2 = null;
                this.label = 1;
                obj = it.hasNext(this);
                if (obj == coroutine_suspended) {
                }
                ProducerScope producerScope5 = producerScope;
                ChannelIterator<E> channelIterator2 = it;
                producerScope2 = producerScope5;
                if (((Boolean) obj).booleanValue()) {
                }
                it2 = this.$this_dropWhile.iterator();
                this.L$0 = producerScope2;
                this.L$1 = it2;
                this.label = 4;
                objHasNext = it2.hasNext(this);
                if (objHasNext == coroutine_suspended) {
                }
            } else if (i == 1) {
                it = (ChannelIterator) this.L$1;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope52 = producerScope;
                ChannelIterator<E> channelIterator22 = it;
                producerScope2 = producerScope52;
                if (((Boolean) obj).booleanValue()) {
                }
                it2 = this.$this_dropWhile.iterator();
                this.L$0 = producerScope2;
                this.L$1 = it2;
                this.label = 4;
                objHasNext = it2.hasNext(this);
                if (objHasNext == coroutine_suspended) {
                }
            } else if (i == 2) {
                Object obj2 = this.L$2;
                ChannelIterator<E> channelIterator3 = (ChannelIterator) this.L$1;
                ProducerScope producerScope6 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                ChannelIterator<E> channelIterator4 = channelIterator3;
                E e = obj2;
                it = channelIterator4;
                if (((Boolean) obj).booleanValue()) {
                    this.L$0 = producerScope6;
                    this.L$1 = null;
                    this.L$2 = null;
                    this.label = 3;
                    if (producerScope6.send(e, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    producerScope2 = producerScope6;
                    it2 = this.$this_dropWhile.iterator();
                    this.L$0 = producerScope2;
                    this.L$1 = it2;
                    this.label = 4;
                    objHasNext = it2.hasNext(this);
                    if (objHasNext == coroutine_suspended) {
                    }
                } else {
                    producerScope = producerScope6;
                    this.L$0 = producerScope;
                    this.L$1 = it;
                    this.L$2 = null;
                    this.label = 1;
                    obj = it.hasNext(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    ProducerScope producerScope522 = producerScope;
                    ChannelIterator<E> channelIterator222 = it;
                    producerScope2 = producerScope522;
                    if (((Boolean) obj).booleanValue()) {
                        E next = channelIterator222.next();
                        Function2<E, Continuation<? super Boolean>, Object> function2 = this.$predicate;
                        this.L$0 = producerScope2;
                        this.L$1 = channelIterator222;
                        this.L$2 = next;
                        this.label = 2;
                        Object objInvoke = function2.invoke(next, this);
                        if (objInvoke == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        channelIterator4 = channelIterator222;
                        e = next;
                        obj = objInvoke;
                        producerScope6 = producerScope2;
                        it = channelIterator4;
                        if (((Boolean) obj).booleanValue()) {
                        }
                    }
                    it2 = this.$this_dropWhile.iterator();
                    this.L$0 = producerScope2;
                    this.L$1 = it2;
                    this.label = 4;
                    objHasNext = it2.hasNext(this);
                    if (objHasNext == coroutine_suspended) {
                    }
                }
            } else if (i == 3) {
                producerScope2 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                it2 = this.$this_dropWhile.iterator();
                this.L$0 = producerScope2;
                this.L$1 = it2;
                this.label = 4;
                objHasNext = it2.hasNext(this);
                if (objHasNext == coroutine_suspended) {
                }
            } else if (i == 4) {
                channelIterator = (ChannelIterator) this.L$1;
                producerScope3 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                if (!((Boolean) obj).booleanValue()) {
                }
            } else {
                if (i != 5) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                channelIterator = (ChannelIterator) this.L$1;
                producerScope3 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                it2 = channelIterator;
                producerScope2 = producerScope3;
                this.L$0 = producerScope2;
                this.L$1 = it2;
                this.label = 4;
                objHasNext = it2.hasNext(this);
                if (objHasNext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                ProducerScope producerScope7 = producerScope2;
                channelIterator = it2;
                obj = objHasNext;
                producerScope3 = producerScope7;
                if (!((Boolean) obj).booleanValue()) {
                    this.L$0 = producerScope3;
                    this.L$1 = channelIterator;
                    this.label = 5;
                    if (producerScope3.send(channelIterator.next(), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    it2 = channelIterator;
                    producerScope2 = producerScope3;
                    this.L$0 = producerScope2;
                    this.L$1 = it2;
                    this.label = 4;
                    objHasNext = it2.hasNext(this);
                    if (objHasNext == coroutine_suspended) {
                    }
                } else {
                    return Unit.INSTANCE;
                }
            }
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel dropWhile(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C27291(receiveChannel, function2, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel filter$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.filter(receiveChannel, coroutineContext, function2);
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* compiled from: Deprecated.kt */
    @Metadata(m606d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, m607d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/ProducerScope;"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filter$1", m620f = "Deprecated.kt", m621i = {0, 1, 1, 2}, m622l = {EMachine.EM_VIDEOCORE5, EMachine.EM_78KOR, EMachine.EM_78KOR}, m623m = "invokeSuspend", m624n = {"$this$produce", "$this$produce", "e", "$this$produce"}, m625s = {"L$0", "L$0", "L$2", "L$0"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filter$1 */
    static final class C27321<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<E, Continuation<? super Boolean>, Object> $predicate;
        final /* synthetic */ ReceiveChannel<E> $this_filter;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C27321(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super C27321> continuation) {
            super(2, continuation);
            this.$this_filter = receiveChannel;
            this.$predicate = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C27321 c27321 = new C27321(this.$this_filter, this.$predicate, continuation);
            c27321.L$0 = obj;
            return c27321;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
            return ((C27321) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x0051, code lost:
        
            r6 = r7;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:18:0x006b  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x008c  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x009e  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00a0  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            ChannelIterator<E> it;
            ProducerScope producerScope;
            ProducerScope producerScope2;
            E e;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope3 = (ProducerScope) this.L$0;
                it = this.$this_filter.iterator();
                producerScope = producerScope3;
            } else if (i == 1) {
                it = (ChannelIterator) this.L$1;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                if (!((Boolean) obj).booleanValue()) {
                    E next = it.next();
                    Function2<E, Continuation<? super Boolean>, Object> function2 = this.$predicate;
                    this.L$0 = producerScope;
                    this.L$1 = it;
                    this.L$2 = next;
                    this.label = 2;
                    Object objInvoke = function2.invoke(next, this);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    ProducerScope producerScope4 = producerScope;
                    e = next;
                    obj = objInvoke;
                    producerScope2 = producerScope4;
                    if (!((Boolean) obj).booleanValue()) {
                    }
                } else {
                    return Unit.INSTANCE;
                }
            } else if (i == 2) {
                Object obj2 = this.L$2;
                ChannelIterator<E> channelIterator = (ChannelIterator) this.L$1;
                producerScope2 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                e = obj2;
                it = channelIterator;
                if (!((Boolean) obj).booleanValue()) {
                    this.L$0 = producerScope2;
                    this.L$1 = it;
                    this.L$2 = null;
                    this.label = 3;
                    if (producerScope2.send(e, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    producerScope = producerScope2;
                }
            } else {
                if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                it = (ChannelIterator) this.L$1;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            this.L$0 = producerScope;
            this.L$1 = it;
            this.L$2 = null;
            this.label = 1;
            obj = it.hasNext(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            if (!((Boolean) obj).booleanValue()) {
            }
        }
    }

    public static final <E> ReceiveChannel<E> filter(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C27321(receiveChannel, function2, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel filterIndexed$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return filterIndexed(receiveChannel, coroutineContext, function3);
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* compiled from: Deprecated.kt */
    @Metadata(m606d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, m607d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/ProducerScope;"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterIndexed$1", m620f = "Deprecated.kt", m621i = {0, 0, 1, 1, 1, 2, 2}, m622l = {211, 212, 212}, m623m = "invokeSuspend", m624n = {"$this$produce", "index", "$this$produce", "e", "index", "$this$produce", "index"}, m625s = {"L$0", "I$0", "L$0", "L$2", "I$0", "L$0", "I$0"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterIndexed$1 */
    static final class C27331<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function3<Integer, E, Continuation<? super Boolean>, Object> $predicate;
        final /* synthetic */ ReceiveChannel<E> $this_filterIndexed;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C27331(ReceiveChannel<? extends E> receiveChannel, Function3<? super Integer, ? super E, ? super Continuation<? super Boolean>, ? extends Object> function3, Continuation<? super C27331> continuation) {
            super(2, continuation);
            this.$this_filterIndexed = receiveChannel;
            this.$predicate = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C27331 c27331 = new C27331(this.$this_filterIndexed, this.$predicate, continuation);
            c27331.L$0 = obj;
            return c27331;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
            return ((C27331) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x005b, code lost:
        
            r7 = r8;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0077  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x00a0  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00b4  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00b6  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            ProducerScope producerScope;
            ChannelIterator<E> it;
            int i;
            ProducerScope producerScope2;
            E e;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                producerScope = (ProducerScope) this.L$0;
                it = this.$this_filterIndexed.iterator();
                i = 0;
            } else if (i2 == 1) {
                i = this.I$0;
                it = (ChannelIterator) this.L$1;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                if (!((Boolean) obj).booleanValue()) {
                    E next = it.next();
                    Function3<Integer, E, Continuation<? super Boolean>, Object> function3 = this.$predicate;
                    int i3 = i + 1;
                    Integer numBoxInt = Boxing.boxInt(i);
                    this.L$0 = producerScope;
                    this.L$1 = it;
                    this.L$2 = next;
                    this.I$0 = i3;
                    this.label = 2;
                    Object objInvoke = function3.invoke(numBoxInt, next, this);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    producerScope2 = producerScope;
                    e = next;
                    obj = objInvoke;
                    i = i3;
                    if (!((Boolean) obj).booleanValue()) {
                    }
                } else {
                    return Unit.INSTANCE;
                }
            } else if (i2 == 2) {
                i = this.I$0;
                Object obj2 = this.L$2;
                ChannelIterator<E> channelIterator = (ChannelIterator) this.L$1;
                producerScope2 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                e = obj2;
                it = channelIterator;
                if (!((Boolean) obj).booleanValue()) {
                    this.L$0 = producerScope2;
                    this.L$1 = it;
                    this.L$2 = null;
                    this.I$0 = i;
                    this.label = 3;
                    if (producerScope2.send(e, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    producerScope = producerScope2;
                }
            } else {
                if (i2 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i = this.I$0;
                it = (ChannelIterator) this.L$1;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            this.L$0 = producerScope;
            this.L$1 = it;
            this.L$2 = null;
            this.I$0 = i;
            this.label = 1;
            obj = it.hasNext(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            if (!((Boolean) obj).booleanValue()) {
            }
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel filterIndexed(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C27331(receiveChannel, function3, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel filterNot$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return filterNot(receiveChannel, coroutineContext, function2);
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* compiled from: Deprecated.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "it"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNot$1", m620f = "Deprecated.kt", m621i = {}, m622l = {222}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNot$1 */
    static final class C27341<E> extends SuspendLambda implements Function2<E, Continuation<? super Boolean>, Object> {
        final /* synthetic */ Function2<E, Continuation<? super Boolean>, Object> $predicate;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C27341(Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super C27341> continuation) {
            super(2, continuation);
            this.$predicate = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C27341 c27341 = new C27341(this.$predicate, continuation);
            c27341.L$0 = obj;
            return c27341;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Continuation<? super Boolean> continuation) {
            return invoke2((C27341<E>) obj, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(E e, Continuation<? super Boolean> continuation) {
            return ((C27341) create(e, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Object obj2 = this.L$0;
                Function2<E, Continuation<? super Boolean>, Object> function2 = this.$predicate;
                this.label = 1;
                obj = function2.invoke(obj2, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Boxing.boxBoolean(!((Boolean) obj).booleanValue());
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel filterNot(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ChannelsKt.filter(receiveChannel, coroutineContext, new C27341(function2, null));
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* compiled from: Deprecated.kt */
    @Metadata(m606d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u0001H\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "", "it"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNull$1", m620f = "Deprecated.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNull$1 */
    static final class C27351<E> extends SuspendLambda implements Function2<E, Continuation<? super Boolean>, Object> {
        /* synthetic */ Object L$0;
        int label;

        C27351(Continuation<? super C27351> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C27351 c27351 = new C27351(continuation);
            c27351.L$0 = obj;
            return c27351;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Continuation<? super Boolean> continuation) {
            return invoke2((C27351<E>) obj, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(E e, Continuation<? super Boolean> continuation) {
            return ((C27351) create(e, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(this.L$0 != null);
        }
    }

    public static final <E> ReceiveChannel<E> filterNotNull(ReceiveChannel<? extends E> receiveChannel) {
        ReceiveChannel<E> receiveChannelFilter$default = filter$default(receiveChannel, null, new C27351(null), 1, null);
        Intrinsics.checkNotNull(receiveChannelFilter$default, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveChannel<E of kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNotNull>");
        return receiveChannelFilter$default;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0066 A[Catch: all -> 0x0037, TryCatch #1 {all -> 0x0037, blocks: (B:12:0x0033, B:25:0x005e, B:27:0x0066, B:29:0x006c, B:21:0x004c, B:31:0x0071), top: B:42:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0071 A[Catch: all -> 0x0037, TRY_LEAVE, TryCatch #1 {all -> 0x0037, blocks: (B:12:0x0033, B:25:0x005e, B:27:0x0066, B:29:0x006c, B:21:0x004c, B:31:0x0071), top: B:42:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x005b -> B:25:0x005e). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object filterNotNullTo(ReceiveChannel receiveChannel, Collection collection, Continuation continuation) {
        C27361 c27361;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator it;
        Collection collection2;
        Object objHasNext;
        if (continuation instanceof C27361) {
            c27361 = (C27361) continuation;
            if ((c27361.label & Integer.MIN_VALUE) != 0) {
                c27361.label -= Integer.MIN_VALUE;
            } else {
                c27361 = new C27361(continuation);
            }
        }
        Object obj = c27361.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c27361.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                receiveChannel2 = receiveChannel;
                it = receiveChannel.iterator();
                collection2 = collection;
                c27361.L$0 = collection2;
                c27361.L$1 = receiveChannel2;
                c27361.L$2 = it;
                c27361.label = 1;
                objHasNext = it.hasNext(c27361);
                if (objHasNext != coroutine_suspended) {
                }
            } catch (Throwable th2) {
                receiveChannel2 = receiveChannel;
                th = th2;
                throw th;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (ChannelIterator) c27361.L$2;
            receiveChannel2 = (ReceiveChannel) c27361.L$1;
            Collection collection3 = (Collection) c27361.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                if (!((Boolean) obj).booleanValue()) {
                    Unit unit = Unit.INSTANCE;
                    ChannelsKt.cancelConsumed(receiveChannel2, null);
                    return collection3;
                }
                Object next = it.next();
                if (next != null) {
                    collection3.add(next);
                }
                collection2 = collection3;
                c27361.L$0 = collection2;
                c27361.L$1 = receiveChannel2;
                c27361.L$2 = it;
                c27361.label = 1;
                objHasNext = it.hasNext(c27361);
                if (objHasNext != coroutine_suspended) {
                    return coroutine_suspended;
                }
                collection3 = collection2;
                obj = objHasNext;
                if (!((Boolean) obj).booleanValue()) {
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th4;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0069 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0077 A[Catch: all -> 0x0052, TryCatch #0 {all -> 0x0052, blocks: (B:13:0x0036, B:28:0x006f, B:30:0x0077, B:32:0x007d, B:36:0x0090, B:18:0x004e), top: B:45:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0090 A[Catch: all -> 0x0052, TRY_LEAVE, TryCatch #0 {all -> 0x0052, blocks: (B:13:0x0036, B:28:0x006f, B:30:0x0077, B:32:0x007d, B:36:0x0090, B:18:0x004e), top: B:45:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Type inference failed for: r8v0, types: [kotlinx.coroutines.channels.SendChannel] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v2, types: [kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.lang.Object, kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x007b -> B:35:0x008c). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x0089 -> B:35:0x008c). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object filterNotNullTo(ReceiveChannel receiveChannel, SendChannel sendChannel, Continuation continuation) {
        C27373 c27373;
        ChannelIterator it;
        ChannelIterator channelIterator;
        SendChannel sendChannel2;
        SendChannel sendChannel3;
        Object objHasNext;
        if (continuation instanceof C27373) {
            c27373 = (C27373) continuation;
            if ((c27373.label & Integer.MIN_VALUE) != 0) {
                c27373.label -= Integer.MIN_VALUE;
            } else {
                c27373 = new C27373(continuation);
            }
        }
        Object obj = c27373.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c27373.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    it = receiveChannel.iterator();
                    sendChannel3 = sendChannel;
                    c27373.L$0 = sendChannel3;
                    c27373.L$1 = receiveChannel;
                    c27373.L$2 = it;
                    c27373.label = 1;
                    objHasNext = it.hasNext(c27373);
                    if (objHasNext == coroutine_suspended) {
                    }
                } catch (Throwable th) {
                    sendChannel = receiveChannel;
                    th = th;
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        ChannelsKt.cancelConsumed(sendChannel, th);
                        throw th2;
                    }
                }
            } else if (i == 1) {
                channelIterator = (ChannelIterator) c27373.L$2;
                ReceiveChannel receiveChannel2 = (ReceiveChannel) c27373.L$1;
                sendChannel2 = (SendChannel) c27373.L$0;
                ResultKt.throwOnFailure(obj);
                sendChannel = receiveChannel2;
                if (((Boolean) obj).booleanValue()) {
                }
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                channelIterator = (ChannelIterator) c27373.L$2;
                ReceiveChannel receiveChannel3 = (ReceiveChannel) c27373.L$1;
                sendChannel2 = (SendChannel) c27373.L$0;
                ResultKt.throwOnFailure(obj);
                ReceiveChannel receiveChannel4 = receiveChannel3;
                it = channelIterator;
                receiveChannel = receiveChannel4;
                sendChannel3 = sendChannel2;
                c27373.L$0 = sendChannel3;
                c27373.L$1 = receiveChannel;
                c27373.L$2 = it;
                c27373.label = 1;
                objHasNext = it.hasNext(c27373);
                if (objHasNext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                SendChannel sendChannel4 = sendChannel3;
                sendChannel = receiveChannel;
                channelIterator = it;
                obj = objHasNext;
                sendChannel2 = sendChannel4;
                if (((Boolean) obj).booleanValue()) {
                    Unit unit = Unit.INSTANCE;
                    ChannelsKt.cancelConsumed(sendChannel, null);
                    return sendChannel2;
                }
                Object next = channelIterator.next();
                receiveChannel4 = sendChannel;
                if (next != null) {
                    c27373.L$0 = sendChannel2;
                    c27373.L$1 = sendChannel;
                    c27373.L$2 = channelIterator;
                    c27373.label = 2;
                    receiveChannel4 = sendChannel;
                    if (sendChannel2.send(next, c27373) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                it = channelIterator;
                receiveChannel = receiveChannel4;
                sendChannel3 = sendChannel2;
                c27373.L$0 = sendChannel3;
                c27373.L$1 = receiveChannel;
                c27373.L$2 = it;
                c27373.label = 1;
                objHasNext = it.hasNext(c27373);
                if (objHasNext == coroutine_suspended) {
                }
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* compiled from: Deprecated.kt */
    @Metadata(m606d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, m607d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/ProducerScope;"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$take$1", m620f = "Deprecated.kt", m621i = {0, 0, 1, 1}, m622l = {254, 255}, m623m = "invokeSuspend", m624n = {"$this$produce", "remaining", "$this$produce", "remaining"}, m625s = {"L$0", "I$0", "L$0", "I$0"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$take$1 */
    static final class C27531<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {

        /* renamed from: $n */
        final /* synthetic */ int f925$n;
        final /* synthetic */ ReceiveChannel<E> $this_take;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C27531(int i, ReceiveChannel<? extends E> receiveChannel, Continuation<? super C27531> continuation) {
            super(2, continuation);
            this.f925$n = i;
            this.$this_take = receiveChannel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C27531 c27531 = new C27531(this.f925$n, this.$this_take, continuation);
            c27531.L$0 = obj;
            return c27531;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
            return ((C27531) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x005f A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0060  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x006b  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0085  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0088  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x007e -> B:7:0x001b). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            ProducerScope producerScope;
            int i;
            ChannelIterator<E> it;
            ProducerScope producerScope2;
            Object objHasNext;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                producerScope = (ProducerScope) this.L$0;
                i = this.f925$n;
                if (i == 0) {
                    return Unit.INSTANCE;
                }
                if (!(i >= 0)) {
                    throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
                }
                it = this.$this_take.iterator();
                this.L$0 = producerScope;
                this.L$1 = it;
                this.I$0 = i;
                this.label = 1;
                objHasNext = it.hasNext(this);
                if (objHasNext == coroutine_suspended) {
                }
            } else if (i2 == 1) {
                i = this.I$0;
                it = (ChannelIterator) this.L$1;
                producerScope2 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                if (!((Boolean) obj).booleanValue()) {
                }
            } else {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i = this.I$0;
                it = (ChannelIterator) this.L$1;
                producerScope2 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope = producerScope2;
                i--;
                if (i == 0) {
                    return Unit.INSTANCE;
                }
                this.L$0 = producerScope;
                this.L$1 = it;
                this.I$0 = i;
                this.label = 1;
                objHasNext = it.hasNext(this);
                if (objHasNext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                producerScope2 = producerScope;
                obj = objHasNext;
                if (!((Boolean) obj).booleanValue()) {
                    this.L$0 = producerScope2;
                    this.L$1 = it;
                    this.I$0 = i;
                    this.label = 2;
                    if (producerScope2.send(it.next(), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    producerScope = producerScope2;
                    i--;
                    if (i == 0) {
                    }
                    this.L$0 = producerScope;
                    this.L$1 = it;
                    this.I$0 = i;
                    this.label = 1;
                    objHasNext = it.hasNext(this);
                    if (objHasNext == coroutine_suspended) {
                    }
                } else {
                    return Unit.INSTANCE;
                }
            }
        }
    }

    public static /* synthetic */ ReceiveChannel take$default(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return take(receiveChannel, i, coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel take(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C27531(i, receiveChannel, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel takeWhile$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return takeWhile(receiveChannel, coroutineContext, function2);
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* compiled from: Deprecated.kt */
    @Metadata(m606d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, m607d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/ProducerScope;"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$takeWhile$1", m620f = "Deprecated.kt", m621i = {0, 1, 1, 2}, m622l = {269, 270, 271}, m623m = "invokeSuspend", m624n = {"$this$produce", "$this$produce", "e", "$this$produce"}, m625s = {"L$0", "L$0", "L$2", "L$0"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$takeWhile$1 */
    static final class C27541<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<E, Continuation<? super Boolean>, Object> $predicate;
        final /* synthetic */ ReceiveChannel<E> $this_takeWhile;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C27541(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super C27541> continuation) {
            super(2, continuation);
            this.$this_takeWhile = receiveChannel;
            this.$predicate = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C27541 c27541 = new C27541(this.$this_takeWhile, this.$predicate, continuation);
            c27541.L$0 = obj;
            return c27541;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
            return ((C27541) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0065  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0087  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x008a  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00a0  */
        /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.Object, kotlinx.coroutines.channels.ProducerScope] */
        /* JADX WARN: Type inference failed for: r6v3 */
        /* JADX WARN: Type inference failed for: r6v6 */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x009d -> B:13:0x004d). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            ChannelIterator<E> it;
            ProducerScope producerScope;
            ?? r6;
            ChannelIterator<E> channelIterator;
            Object obj2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope2 = (ProducerScope) this.L$0;
                it = this.$this_takeWhile.iterator();
                producerScope = producerScope2;
            } else if (i == 1) {
                it = (ChannelIterator) this.L$1;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                if (!((Boolean) obj).booleanValue()) {
                    E next = it.next();
                    Function2<E, Continuation<? super Boolean>, Object> function2 = this.$predicate;
                    this.L$0 = producerScope;
                    this.L$1 = it;
                    this.L$2 = next;
                    this.label = 2;
                    Object objInvoke = function2.invoke(next, this);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    ChannelIterator<E> channelIterator2 = it;
                    obj2 = next;
                    obj = objInvoke;
                    r6 = producerScope;
                    channelIterator = channelIterator2;
                    if (((Boolean) obj).booleanValue()) {
                    }
                } else {
                    return Unit.INSTANCE;
                }
            } else if (i == 2) {
                obj2 = this.L$2;
                channelIterator = (ChannelIterator) this.L$1;
                ProducerScope producerScope3 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                r6 = producerScope3;
                if (((Boolean) obj).booleanValue()) {
                    return Unit.INSTANCE;
                }
                this.L$0 = r6;
                this.L$1 = channelIterator;
                this.L$2 = null;
                this.label = 3;
                if (r6.send(obj2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                it = channelIterator;
                producerScope = r6;
            } else {
                if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                it = (ChannelIterator) this.L$1;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            this.L$0 = producerScope;
            this.L$1 = it;
            this.label = 1;
            obj = it.hasNext(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            if (!((Boolean) obj).booleanValue()) {
            }
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel takeWhile(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C27541(receiveChannel, function2, null), 6, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007a A[Catch: all -> 0x0055, TryCatch #1 {all -> 0x0055, blocks: (B:13:0x0036, B:28:0x0072, B:30:0x007a, B:33:0x008d, B:18:0x0051), top: B:44:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008d A[Catch: all -> 0x0055, TRY_LEAVE, TryCatch #1 {all -> 0x0055, blocks: (B:13:0x0036, B:28:0x0072, B:30:0x007a, B:33:0x008d, B:18:0x0051), top: B:44:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Type inference failed for: r8v0, types: [C extends kotlinx.coroutines.channels.SendChannel<? super E>] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Type inference failed for: r8v25 */
    /* JADX WARN: Type inference failed for: r8v26 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.lang.Object, kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x008a -> B:14:0x0039). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <E, C extends SendChannel<? super E>> Object toChannel(ReceiveChannel<? extends E> receiveChannel, C c, Continuation<? super C> continuation) {
        C27551 c27551;
        ChannelIterator<? extends E> it;
        ChannelIterator<? extends E> channelIterator;
        SendChannel sendChannel;
        SendChannel sendChannel2;
        Object objHasNext;
        if (continuation instanceof C27551) {
            c27551 = (C27551) continuation;
            if ((c27551.label & Integer.MIN_VALUE) != 0) {
                c27551.label -= Integer.MIN_VALUE;
            } else {
                c27551 = new C27551(continuation);
            }
        }
        Object obj = c27551.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c27551.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    it = receiveChannel.iterator();
                    sendChannel2 = c;
                    c27551.L$0 = sendChannel2;
                    c27551.L$1 = receiveChannel;
                    c27551.L$2 = it;
                    c27551.label = 1;
                    objHasNext = it.hasNext(c27551);
                    if (objHasNext == coroutine_suspended) {
                    }
                } catch (Throwable th) {
                    c = receiveChannel;
                    th = th;
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        ChannelsKt.cancelConsumed(c, th);
                        throw th2;
                    }
                }
            } else if (i == 1) {
                channelIterator = (ChannelIterator) c27551.L$2;
                boolean z = (C) ((ReceiveChannel) c27551.L$1);
                sendChannel = (SendChannel) c27551.L$0;
                ResultKt.throwOnFailure(obj);
                c = z;
                if (!((Boolean) obj).booleanValue()) {
                }
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                channelIterator = (ChannelIterator) c27551.L$2;
                ReceiveChannel<? extends E> receiveChannel2 = (C) ((ReceiveChannel) c27551.L$1);
                sendChannel = (SendChannel) c27551.L$0;
                ResultKt.throwOnFailure(obj);
                ReceiveChannel<? extends E> receiveChannel3 = receiveChannel2;
                it = channelIterator;
                receiveChannel = receiveChannel3;
                sendChannel2 = (C) sendChannel;
                c27551.L$0 = sendChannel2;
                c27551.L$1 = receiveChannel;
                c27551.L$2 = it;
                c27551.label = 1;
                objHasNext = it.hasNext(c27551);
                if (objHasNext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                SendChannel sendChannel3 = sendChannel2;
                boolean z2 = (C) receiveChannel;
                channelIterator = it;
                obj = objHasNext;
                sendChannel = sendChannel3;
                c = z2;
                if (!((Boolean) obj).booleanValue()) {
                    E next = channelIterator.next();
                    c27551.L$0 = sendChannel;
                    c27551.L$1 = (Object) c;
                    c27551.L$2 = channelIterator;
                    c27551.label = 2;
                    receiveChannel3 = c;
                    if (sendChannel.send(next, c27551) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    it = channelIterator;
                    receiveChannel = receiveChannel3;
                    sendChannel2 = (C) sendChannel;
                    c27551.L$0 = sendChannel2;
                    c27551.L$1 = receiveChannel;
                    c27551.L$2 = it;
                    c27551.label = 1;
                    objHasNext = it.hasNext(c27551);
                    if (objHasNext == coroutine_suspended) {
                    }
                } else {
                    Unit unit = Unit.INSTANCE;
                    ChannelsKt.cancelConsumed((ReceiveChannel) c, null);
                    return sendChannel;
                }
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0066 A[Catch: all -> 0x0037, TryCatch #1 {all -> 0x0037, blocks: (B:12:0x0033, B:25:0x005e, B:27:0x0066, B:21:0x004c, B:28:0x006f), top: B:39:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006f A[Catch: all -> 0x0037, TRY_LEAVE, TryCatch #1 {all -> 0x0037, blocks: (B:12:0x0033, B:25:0x005e, B:27:0x0066, B:21:0x004c, B:28:0x006f), top: B:39:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x005b -> B:25:0x005e). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <E, C extends Collection<? super E>> Object toCollection(ReceiveChannel<? extends E> receiveChannel, C c, Continuation<? super C> continuation) {
        C27561 c27561;
        ReceiveChannel<? extends E> receiveChannel2;
        Throwable th;
        ChannelIterator it;
        C c2;
        Object objHasNext;
        if (continuation instanceof C27561) {
            c27561 = (C27561) continuation;
            if ((c27561.label & Integer.MIN_VALUE) != 0) {
                c27561.label -= Integer.MIN_VALUE;
            } else {
                c27561 = new C27561(continuation);
            }
        }
        Object obj = c27561.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c27561.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                receiveChannel2 = receiveChannel;
                it = receiveChannel.iterator();
                c2 = c;
                c27561.L$0 = c2;
                c27561.L$1 = receiveChannel2;
                c27561.L$2 = it;
                c27561.label = 1;
                objHasNext = it.hasNext(c27561);
                if (objHasNext != coroutine_suspended) {
                }
            } catch (Throwable th2) {
                receiveChannel2 = receiveChannel;
                th = th2;
                throw th;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (ChannelIterator) c27561.L$2;
            receiveChannel2 = (ReceiveChannel) c27561.L$1;
            Collection collection = (Collection) c27561.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                ?? r2 = collection;
                if (!((Boolean) obj).booleanValue()) {
                    r2.add(it.next());
                    c2 = r2;
                    c27561.L$0 = c2;
                    c27561.L$1 = receiveChannel2;
                    c27561.L$2 = it;
                    c27561.label = 1;
                    objHasNext = it.hasNext(c27561);
                    if (objHasNext != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    r2 = c2;
                    obj = objHasNext;
                    if (!((Boolean) obj).booleanValue()) {
                        Unit unit = Unit.INSTANCE;
                        ChannelsKt.cancelConsumed(receiveChannel2, null);
                        return r2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th4;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0066 A[Catch: all -> 0x0037, TryCatch #1 {all -> 0x0037, blocks: (B:12:0x0033, B:25:0x005e, B:27:0x0066, B:21:0x004c, B:28:0x0079), top: B:39:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0079 A[Catch: all -> 0x0037, TRY_LEAVE, TryCatch #1 {all -> 0x0037, blocks: (B:12:0x0033, B:25:0x005e, B:27:0x0066, B:21:0x004c, B:28:0x0079), top: B:39:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object, java.util.Map] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x005b -> B:25:0x005e). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <K, V, M extends Map<? super K, ? super V>> Object toMap(ReceiveChannel<? extends Pair<? extends K, ? extends V>> receiveChannel, M m, Continuation<? super M> continuation) {
        C27572 c27572;
        ReceiveChannel<? extends Pair<? extends K, ? extends V>> receiveChannel2;
        Throwable th;
        ChannelIterator it;
        M m2;
        Object objHasNext;
        if (continuation instanceof C27572) {
            c27572 = (C27572) continuation;
            if ((c27572.label & Integer.MIN_VALUE) != 0) {
                c27572.label -= Integer.MIN_VALUE;
            } else {
                c27572 = new C27572(continuation);
            }
        }
        Object obj = c27572.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c27572.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                receiveChannel2 = receiveChannel;
                it = receiveChannel.iterator();
                m2 = m;
                c27572.L$0 = m2;
                c27572.L$1 = receiveChannel2;
                c27572.L$2 = it;
                c27572.label = 1;
                objHasNext = it.hasNext(c27572);
                if (objHasNext != coroutine_suspended) {
                }
            } catch (Throwable th2) {
                receiveChannel2 = receiveChannel;
                th = th2;
                throw th;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (ChannelIterator) c27572.L$2;
            receiveChannel2 = (ReceiveChannel) c27572.L$1;
            Map map = (Map) c27572.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                ?? r2 = map;
                if (!((Boolean) obj).booleanValue()) {
                    Pair pair = (Pair) it.next();
                    r2.put(pair.getFirst(), pair.getSecond());
                    m2 = r2;
                    c27572.L$0 = m2;
                    c27572.L$1 = receiveChannel2;
                    c27572.L$2 = it;
                    c27572.label = 1;
                    objHasNext = it.hasNext(c27572);
                    if (objHasNext != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    r2 = m2;
                    obj = objHasNext;
                    if (!((Boolean) obj).booleanValue()) {
                        Unit unit = Unit.INSTANCE;
                        ChannelsKt.cancelConsumed(receiveChannel2, null);
                        return r2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th4;
                }
            }
        }
    }

    public static /* synthetic */ ReceiveChannel flatMap$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return flatMap(receiveChannel, coroutineContext, function2);
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* compiled from: Deprecated.kt */
    @Metadata(m606d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "R", "Lkotlinx/coroutines/channels/ProducerScope;"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$flatMap$1", m620f = "Deprecated.kt", m621i = {0, 1, 2}, m622l = {321, 322, 322}, m623m = "invokeSuspend", m624n = {"$this$produce", "$this$produce", "$this$produce"}, m625s = {"L$0", "L$0", "L$0"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$flatMap$1 */
    static final class C27401<R> extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
        final /* synthetic */ ReceiveChannel<E> $this_flatMap;
        final /* synthetic */ Function2<E, Continuation<? super ReceiveChannel<? extends R>>, Object> $transform;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C27401(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super ReceiveChannel<? extends R>>, ? extends Object> function2, Continuation<? super C27401> continuation) {
            super(2, continuation);
            this.$this_flatMap = receiveChannel;
            this.$transform = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C27401 c27401 = new C27401(this.$this_flatMap, this.$transform, continuation);
            c27401.L$0 = obj;
            return c27401;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super R> producerScope, Continuation<? super Unit> continuation) {
            return ((C27401) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0063  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x008a A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:24:0x008b  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0088 -> B:13:0x004b). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            ChannelIterator it;
            ProducerScope producerScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope2 = (ProducerScope) this.L$0;
                it = this.$this_flatMap.iterator();
                producerScope = producerScope2;
            } else if (i == 1) {
                it = (ChannelIterator) this.L$1;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                if (!((Boolean) obj).booleanValue()) {
                    Object next = it.next();
                    Function2<E, Continuation<? super ReceiveChannel<? extends R>>, Object> function2 = this.$transform;
                    this.L$0 = producerScope;
                    this.L$1 = it;
                    this.label = 2;
                    obj = function2.invoke(next, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    this.L$0 = producerScope;
                    this.L$1 = it;
                    this.label = 3;
                    if (ChannelsKt.toChannel((ReceiveChannel) obj, producerScope, this) == coroutine_suspended) {
                    }
                } else {
                    return Unit.INSTANCE;
                }
            } else if (i == 2) {
                it = (ChannelIterator) this.L$1;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                this.L$0 = producerScope;
                this.L$1 = it;
                this.label = 3;
                if (ChannelsKt.toChannel((ReceiveChannel) obj, producerScope, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                it = (ChannelIterator) this.L$1;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            this.L$0 = producerScope;
            this.L$1 = it;
            this.label = 1;
            obj = it.hasNext(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            if (!((Boolean) obj).booleanValue()) {
            }
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel flatMap(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C27401(receiveChannel, function2, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel map$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.map(receiveChannel, coroutineContext, function2);
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* compiled from: Deprecated.kt */
    @Metadata(m606d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "R", "Lkotlinx/coroutines/channels/ProducerScope;"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$map$1", m620f = "Deprecated.kt", m621i = {0, 0, 1, 1, 2, 2}, m622l = {487, 333, 333}, m623m = "invokeSuspend", m624n = {"$this$produce", "$this$consume$iv$iv", "$this$produce", "$this$consume$iv$iv", "$this$produce", "$this$consume$iv$iv"}, m625s = {"L$0", "L$2", "L$0", "L$2", "L$0", "L$2"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$map$1 */
    static final class C27451<R> extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
        final /* synthetic */ ReceiveChannel<E> $this_map;
        final /* synthetic */ Function2<E, Continuation<? super R>, Object> $transform;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C27451(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super C27451> continuation) {
            super(2, continuation);
            this.$this_map = receiveChannel;
            this.$transform = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C27451 c27451 = new C27451(this.$this_map, this.$transform, continuation);
            c27451.L$0 = obj;
            return c27451;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super R> producerScope, Continuation<? super Unit> continuation) {
            return ((C27451) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x0093 A[Catch: all -> 0x00d1, TRY_LEAVE, TryCatch #0 {all -> 0x00d1, blocks: (B:8:0x0022, B:22:0x0076, B:26:0x008b, B:28:0x0093, B:36:0x00c9, B:18:0x005e, B:21:0x006e), top: B:44:0x000a }] */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00c2 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00c3  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00c9 A[Catch: all -> 0x00d1, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x00d1, blocks: (B:8:0x0022, B:22:0x0076, B:26:0x008b, B:28:0x0093, B:36:0x00c9, B:18:0x005e, B:21:0x006e), top: B:44:0x000a }] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x00c3 -> B:22:0x0076). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            ReceiveChannel receiveChannel;
            ProducerScope producerScope;
            Throwable th;
            Function2 function2;
            ChannelIterator it;
            ReceiveChannel receiveChannel2;
            Throwable th2;
            ProducerScope producerScope2;
            Function2 function22;
            ChannelIterator channelIterator;
            ProducerScope producerScope3;
            Throwable th3;
            ProducerScope producerScope4;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    producerScope = (ProducerScope) this.L$0;
                    receiveChannel = this.$this_map;
                    th = null;
                    function2 = this.$transform;
                    it = receiveChannel.iterator();
                } else if (i == 1) {
                    it = (ChannelIterator) this.L$3;
                    receiveChannel = (ReceiveChannel) this.L$2;
                    function2 = (Function2) this.L$1;
                    producerScope4 = (ProducerScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    th3 = null;
                    if (!((Boolean) obj).booleanValue()) {
                        Object next = it.next();
                        this.L$0 = producerScope4;
                        this.L$1 = function2;
                        this.L$2 = receiveChannel;
                        this.L$3 = it;
                        this.L$4 = producerScope4;
                        this.label = 2;
                        obj = function2.invoke(next, this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        th2 = th3;
                        producerScope2 = producerScope4;
                        function22 = function2;
                        receiveChannel2 = receiveChannel;
                        channelIterator = it;
                        producerScope3 = producerScope2;
                        this.L$0 = producerScope2;
                        this.L$1 = function22;
                        this.L$2 = receiveChannel2;
                        this.L$3 = channelIterator;
                        this.L$4 = null;
                        this.label = 3;
                        if (producerScope3.send(obj, this) != coroutine_suspended) {
                        }
                    } else {
                        Unit unit = Unit.INSTANCE;
                        ChannelsKt.cancelConsumed(receiveChannel, th3);
                        return Unit.INSTANCE;
                    }
                } else if (i == 2) {
                    producerScope3 = (ProducerScope) this.L$4;
                    channelIterator = (ChannelIterator) this.L$3;
                    receiveChannel2 = (ReceiveChannel) this.L$2;
                    function22 = (Function2) this.L$1;
                    producerScope2 = (ProducerScope) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        th2 = null;
                        this.L$0 = producerScope2;
                        this.L$1 = function22;
                        this.L$2 = receiveChannel2;
                        this.L$3 = channelIterator;
                        this.L$4 = null;
                        this.label = 3;
                        if (producerScope3.send(obj, this) != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        it = channelIterator;
                        receiveChannel = receiveChannel2;
                        function2 = function22;
                        producerScope = producerScope2;
                        th = th2;
                    } catch (Throwable th4) {
                        th = th4;
                        receiveChannel = receiveChannel2;
                        try {
                            throw th;
                        } catch (Throwable th5) {
                            ChannelsKt.cancelConsumed(receiveChannel, th);
                            throw th5;
                        }
                    }
                } else {
                    if (i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    it = (ChannelIterator) this.L$3;
                    receiveChannel = (ReceiveChannel) this.L$2;
                    function2 = (Function2) this.L$1;
                    ProducerScope producerScope5 = (ProducerScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    producerScope = producerScope5;
                    th = null;
                }
                this.L$0 = producerScope;
                this.L$1 = function2;
                this.L$2 = receiveChannel;
                this.L$3 = it;
                this.label = 1;
                Object objHasNext = it.hasNext(this);
                if (objHasNext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                Throwable th6 = th;
                producerScope4 = producerScope;
                obj = objHasNext;
                th3 = th6;
                if (!((Boolean) obj).booleanValue()) {
                }
            } catch (Throwable th7) {
                th = th7;
            }
        }
    }

    public static final <E, R> ReceiveChannel<R> map(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C27451(receiveChannel, function2, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel mapIndexed$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.mapIndexed(receiveChannel, coroutineContext, function3);
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* compiled from: Deprecated.kt */
    @Metadata(m606d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "R", "Lkotlinx/coroutines/channels/ProducerScope;"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$mapIndexed$1", m620f = "Deprecated.kt", m621i = {0, 0, 1, 1, 2, 2}, m622l = {344, 345, 345}, m623m = "invokeSuspend", m624n = {"$this$produce", "index", "$this$produce", "index", "$this$produce", "index"}, m625s = {"L$0", "I$0", "L$0", "I$0", "L$0", "I$0"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$mapIndexed$1 */
    static final class C27461<R> extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
        final /* synthetic */ ReceiveChannel<E> $this_mapIndexed;
        final /* synthetic */ Function3<Integer, E, Continuation<? super R>, Object> $transform;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C27461(ReceiveChannel<? extends E> receiveChannel, Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super C27461> continuation) {
            super(2, continuation);
            this.$this_mapIndexed = receiveChannel;
            this.$transform = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C27461 c27461 = new C27461(this.$this_mapIndexed, this.$transform, continuation);
            c27461.L$0 = obj;
            return c27461;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super R> producerScope, Continuation<? super Unit> continuation) {
            return ((C27461) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0076  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x00ab A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x00ac  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00af  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00ac -> B:13:0x0059). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            ProducerScope producerScope;
            ChannelIterator it;
            int i;
            ProducerScope producerScope2;
            ChannelIterator channelIterator;
            ProducerScope producerScope3;
            ProducerScope producerScope4;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                producerScope = (ProducerScope) this.L$0;
                it = this.$this_mapIndexed.iterator();
                i = 0;
            } else if (i2 == 1) {
                i = this.I$0;
                it = (ChannelIterator) this.L$1;
                producerScope4 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                if (!((Boolean) obj).booleanValue()) {
                    Object next = it.next();
                    Function3<Integer, E, Continuation<? super R>, Object> function3 = this.$transform;
                    int i3 = i + 1;
                    Integer numBoxInt = Boxing.boxInt(i);
                    this.L$0 = producerScope4;
                    this.L$1 = it;
                    this.L$2 = producerScope4;
                    this.I$0 = i3;
                    this.label = 2;
                    obj = function3.invoke(numBoxInt, next, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    producerScope2 = producerScope4;
                    i = i3;
                    channelIterator = it;
                    producerScope3 = producerScope2;
                    this.L$0 = producerScope2;
                    this.L$1 = channelIterator;
                    this.L$2 = null;
                    this.I$0 = i;
                    this.label = 3;
                    if (producerScope3.send(obj, this) != coroutine_suspended) {
                    }
                } else {
                    return Unit.INSTANCE;
                }
            } else if (i2 == 2) {
                i = this.I$0;
                producerScope3 = (ProducerScope) this.L$2;
                channelIterator = (ChannelIterator) this.L$1;
                producerScope2 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                this.L$0 = producerScope2;
                this.L$1 = channelIterator;
                this.L$2 = null;
                this.I$0 = i;
                this.label = 3;
                if (producerScope3.send(obj, this) != coroutine_suspended) {
                    return coroutine_suspended;
                }
                it = channelIterator;
                producerScope = producerScope2;
            } else {
                if (i2 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i = this.I$0;
                it = (ChannelIterator) this.L$1;
                ProducerScope producerScope5 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope = producerScope5;
            }
            this.L$0 = producerScope;
            this.L$1 = it;
            this.I$0 = i;
            this.label = 1;
            Object objHasNext = it.hasNext(this);
            if (objHasNext == coroutine_suspended) {
                return coroutine_suspended;
            }
            producerScope4 = producerScope;
            obj = objHasNext;
            if (!((Boolean) obj).booleanValue()) {
            }
        }
    }

    public static final <E, R> ReceiveChannel<R> mapIndexed(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C27461(receiveChannel, function3, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel mapIndexedNotNull$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return mapIndexedNotNull(receiveChannel, coroutineContext, function3);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel mapIndexedNotNull(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3) {
        return ChannelsKt.filterNotNull(ChannelsKt.mapIndexed(receiveChannel, coroutineContext, function3));
    }

    public static /* synthetic */ ReceiveChannel mapNotNull$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return mapNotNull(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel mapNotNull(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ChannelsKt.filterNotNull(ChannelsKt.map(receiveChannel, coroutineContext, function2));
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* compiled from: Deprecated.kt */
    @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H\u008a@"}, m607d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/collections/IndexedValue;"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$withIndex$1", m620f = "Deprecated.kt", m621i = {0, 0, 1, 1}, m622l = {370, 371}, m623m = "invokeSuspend", m624n = {"$this$produce", "index", "$this$produce", "index"}, m625s = {"L$0", "I$0", "L$0", "I$0"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$withIndex$1 */
    static final class C27581<E> extends SuspendLambda implements Function2<ProducerScope<? super IndexedValue<? extends E>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ ReceiveChannel<E> $this_withIndex;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C27581(ReceiveChannel<? extends E> receiveChannel, Continuation<? super C27581> continuation) {
            super(2, continuation);
            this.$this_withIndex = receiveChannel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C27581 c27581 = new C27581(this.$this_withIndex, continuation);
            c27581.L$0 = obj;
            return c27581;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super IndexedValue<? extends E>> producerScope, Continuation<? super Unit> continuation) {
            return ((C27581) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0064  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0085  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0081 -> B:11:0x0044). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            ProducerScope producerScope;
            ChannelIterator<E> it;
            int i;
            ProducerScope producerScope2;
            ChannelIterator<E> channelIterator;
            int i2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                producerScope = (ProducerScope) this.L$0;
                it = this.$this_withIndex.iterator();
                i = 0;
            } else if (i3 == 1) {
                i2 = this.I$0;
                channelIterator = (ChannelIterator) this.L$1;
                producerScope2 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                if (!((Boolean) obj).booleanValue()) {
                    int i4 = i2 + 1;
                    this.L$0 = producerScope2;
                    this.L$1 = channelIterator;
                    this.I$0 = i4;
                    this.label = 2;
                    if (producerScope2.send(new IndexedValue(i2, channelIterator.next()), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    it = channelIterator;
                    producerScope = producerScope2;
                    i = i4;
                } else {
                    return Unit.INSTANCE;
                }
            } else {
                if (i3 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i5 = this.I$0;
                ChannelIterator<E> channelIterator2 = (ChannelIterator) this.L$1;
                ProducerScope producerScope3 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope = producerScope3;
                i = i5;
                it = channelIterator2;
            }
            this.L$0 = producerScope;
            this.L$1 = it;
            this.I$0 = i;
            this.label = 1;
            Object objHasNext = it.hasNext(this);
            if (objHasNext == coroutine_suspended) {
                return coroutine_suspended;
            }
            producerScope2 = producerScope;
            obj = objHasNext;
            int i6 = i;
            channelIterator = it;
            i2 = i6;
            if (!((Boolean) obj).booleanValue()) {
            }
        }
    }

    public static /* synthetic */ ReceiveChannel withIndex$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return withIndex(receiveChannel, coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel withIndex(ReceiveChannel receiveChannel, CoroutineContext coroutineContext) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C27581(receiveChannel, null), 6, null);
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* compiled from: Deprecated.kt */
    @Metadata(m606d1 = {"\u0000\u0004\n\u0002\b\u0003\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u0001H\u008a@"}, m607d2 = {"<anonymous>", ExifInterface.LONGITUDE_EAST, "it"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$distinct$1", m620f = "Deprecated.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$distinct$1 */
    static final class C27261<E> extends SuspendLambda implements Function2<E, Continuation<? super E>, Object> {
        /* synthetic */ Object L$0;
        int label;

        C27261(Continuation<? super C27261> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C27261 c27261 = new C27261(continuation);
            c27261.L$0 = obj;
            return c27261;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke((C27261<E>) obj, (Continuation<? super C27261<E>>) obj2);
        }

        public final Object invoke(E e, Continuation<? super E> continuation) {
            return ((C27261) create(e, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return this.L$0;
        }
    }

    public static /* synthetic */ ReceiveChannel distinctBy$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.distinctBy(receiveChannel, coroutineContext, function2);
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* compiled from: Deprecated.kt */
    @Metadata(m606d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "K", "Lkotlinx/coroutines/channels/ProducerScope;"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$distinctBy$1", m620f = "Deprecated.kt", m621i = {0, 0, 1, 1, 1, 2, 2, 2}, m622l = {387, 388, 390}, m623m = "invokeSuspend", m624n = {"$this$produce", "keys", "$this$produce", "keys", "e", "$this$produce", "keys", "k"}, m625s = {"L$0", "L$1", "L$0", "L$1", "L$3", "L$0", "L$1", "L$3"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$distinctBy$1 */
    static final class C27271<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<E, Continuation<? super K>, Object> $selector;
        final /* synthetic */ ReceiveChannel<E> $this_distinctBy;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C27271(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super K>, ? extends Object> function2, Continuation<? super C27271> continuation) {
            super(2, continuation);
            this.$this_distinctBy = receiveChannel;
            this.$selector = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C27271 c27271 = new C27271(this.$this_distinctBy, this.$selector, continuation);
            c27271.L$0 = obj;
            return c27271;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
            return ((C27271) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:15:0x007b A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0084  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x00a6  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00c6  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x00a4 -> B:29:0x00c3). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00ba -> B:28:0x00bc). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            ProducerScope producerScope;
            HashSet hashSet;
            ChannelIterator<E> it;
            ProducerScope producerScope2;
            HashSet hashSet2;
            E e;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope3 = (ProducerScope) this.L$0;
                HashSet hashSet3 = new HashSet();
                producerScope = producerScope3;
                hashSet = hashSet3;
                it = this.$this_distinctBy.iterator();
                this.L$0 = producerScope;
                this.L$1 = hashSet;
                this.L$2 = it;
                this.L$3 = null;
                this.label = 1;
                obj = it.hasNext(this);
                if (obj == coroutine_suspended) {
                }
                if (((Boolean) obj).booleanValue()) {
                }
            } else if (i == 1) {
                it = (ChannelIterator) this.L$2;
                hashSet = (HashSet) this.L$1;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                if (((Boolean) obj).booleanValue()) {
                }
            } else if (i == 2) {
                Object obj2 = this.L$3;
                ChannelIterator<E> channelIterator = (ChannelIterator) this.L$2;
                hashSet2 = (HashSet) this.L$1;
                producerScope2 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                e = obj2;
                it = channelIterator;
                if (!hashSet2.contains(obj)) {
                }
                hashSet = hashSet2;
                producerScope = producerScope2;
                this.L$0 = producerScope;
                this.L$1 = hashSet;
                this.L$2 = it;
                this.L$3 = null;
                this.label = 1;
                obj = it.hasNext(this);
                if (obj == coroutine_suspended) {
                }
                if (((Boolean) obj).booleanValue()) {
                }
            } else {
                if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Object obj3 = this.L$3;
                ChannelIterator<E> channelIterator2 = (ChannelIterator) this.L$2;
                hashSet2 = (HashSet) this.L$1;
                producerScope2 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                hashSet2.add(obj3);
                it = channelIterator2;
                hashSet = hashSet2;
                producerScope = producerScope2;
                this.L$0 = producerScope;
                this.L$1 = hashSet;
                this.L$2 = it;
                this.L$3 = null;
                this.label = 1;
                obj = it.hasNext(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                if (((Boolean) obj).booleanValue()) {
                    E next = it.next();
                    Function2<E, Continuation<? super K>, Object> function2 = this.$selector;
                    this.L$0 = producerScope;
                    this.L$1 = hashSet;
                    this.L$2 = it;
                    this.L$3 = next;
                    this.label = 2;
                    Object objInvoke = function2.invoke(next, this);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    HashSet hashSet4 = hashSet;
                    e = next;
                    obj = objInvoke;
                    producerScope2 = producerScope;
                    hashSet2 = hashSet4;
                    if (!hashSet2.contains(obj)) {
                        this.L$0 = producerScope2;
                        this.L$1 = hashSet2;
                        this.L$2 = it;
                        this.L$3 = obj;
                        this.label = 3;
                        if (producerScope2.send(e, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        channelIterator2 = it;
                        obj3 = obj;
                        hashSet2.add(obj3);
                        it = channelIterator2;
                    }
                    hashSet = hashSet2;
                    producerScope = producerScope2;
                    this.L$0 = producerScope;
                    this.L$1 = hashSet;
                    this.L$2 = it;
                    this.L$3 = null;
                    this.label = 1;
                    obj = it.hasNext(this);
                    if (obj == coroutine_suspended) {
                    }
                    if (((Boolean) obj).booleanValue()) {
                        return Unit.INSTANCE;
                    }
                }
            }
        }
    }

    public static final <E, K> ReceiveChannel<E> distinctBy(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function2<? super E, ? super Continuation<? super K>, ? extends Object> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C27271(receiveChannel, function2, null), 6, null);
    }

    public static final <E> Object toMutableSet(ReceiveChannel<? extends E> receiveChannel, Continuation<? super Set<E>> continuation) {
        return ChannelsKt.toCollection(receiveChannel, new LinkedHashSet(), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object any(ReceiveChannel receiveChannel, Continuation continuation) {
        C27221 c27221;
        if (continuation instanceof C27221) {
            c27221 = (C27221) continuation;
            if ((c27221.label & Integer.MIN_VALUE) != 0) {
                c27221.label -= Integer.MIN_VALUE;
            } else {
                c27221 = new C27221(continuation);
            }
        }
        Object objHasNext = c27221.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c27221.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(objHasNext);
                ChannelIterator it = receiveChannel.iterator();
                c27221.L$0 = receiveChannel;
                c27221.label = 1;
                objHasNext = it.hasNext(c27221);
                if (objHasNext == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                receiveChannel = (ReceiveChannel) c27221.L$0;
                ResultKt.throwOnFailure(objHasNext);
            }
            ChannelsKt.cancelConsumed(receiveChannel, null);
            return objHasNext;
        } finally {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006a A[Catch: all -> 0x0037, TryCatch #3 {all -> 0x0037, blocks: (B:12:0x0033, B:25:0x0062, B:27:0x006a, B:28:0x0074), top: B:45:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0074 A[Catch: all -> 0x0037, TRY_LEAVE, TryCatch #3 {all -> 0x0037, blocks: (B:12:0x0033, B:25:0x0062, B:27:0x006a, B:28:0x0074), top: B:45:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x005f -> B:25:0x0062). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object count(ReceiveChannel receiveChannel, Continuation continuation) {
        C27251 c27251;
        ReceiveChannel receiveChannel2;
        Throwable th;
        Ref.IntRef intRef;
        ReceiveChannel receiveChannel3;
        ChannelIterator it;
        Object objHasNext;
        if (continuation instanceof C27251) {
            c27251 = (C27251) continuation;
            if ((c27251.label & Integer.MIN_VALUE) != 0) {
                c27251.label -= Integer.MIN_VALUE;
            } else {
                c27251 = new C27251(continuation);
            }
        }
        Object obj = c27251.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c27251.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                intRef = new Ref.IntRef();
                receiveChannel3 = receiveChannel;
                it = receiveChannel.iterator();
                c27251.L$0 = intRef;
                c27251.L$1 = receiveChannel3;
                c27251.L$2 = it;
                c27251.label = 1;
                objHasNext = it.hasNext(c27251);
                if (objHasNext != coroutine_suspended) {
                }
            } catch (Throwable th2) {
                receiveChannel2 = receiveChannel;
                th = th2;
                throw th;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (ChannelIterator) c27251.L$2;
            receiveChannel2 = (ReceiveChannel) c27251.L$1;
            intRef = (Ref.IntRef) c27251.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                if (!((Boolean) obj).booleanValue()) {
                    it.next();
                    intRef.element++;
                    receiveChannel3 = receiveChannel2;
                    try {
                        c27251.L$0 = intRef;
                        c27251.L$1 = receiveChannel3;
                        c27251.L$2 = it;
                        c27251.label = 1;
                        objHasNext = it.hasNext(c27251);
                        if (objHasNext != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        receiveChannel2 = receiveChannel3;
                        obj = objHasNext;
                        if (!((Boolean) obj).booleanValue()) {
                            Unit unit = Unit.INSTANCE;
                            ChannelsKt.cancelConsumed(receiveChannel2, null);
                            return Boxing.boxInt(intRef.element);
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        receiveChannel2 = receiveChannel3;
                        try {
                            throw th;
                        } catch (Throwable th4) {
                            ChannelsKt.cancelConsumed(receiveChannel2, th);
                            throw th4;
                        }
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x009f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ac A[Catch: all -> 0x00be, TRY_LEAVE, TryCatch #3 {all -> 0x00be, blocks: (B:40:0x00a4, B:42:0x00ac, B:36:0x008f, B:26:0x0063), top: B:61:0x0063 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x00a0 -> B:15:0x003e). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object maxWith(ReceiveChannel receiveChannel, Comparator comparator, Continuation continuation) {
        C27471 c27471;
        ReceiveChannel receiveChannel2;
        ChannelIterator channelIterator;
        Comparator comparator2;
        Object next;
        Comparator comparator3;
        ChannelIterator channelIterator2;
        Object objHasNext;
        if (continuation instanceof C27471) {
            c27471 = (C27471) continuation;
            if ((c27471.label & Integer.MIN_VALUE) != 0) {
                c27471.label -= Integer.MIN_VALUE;
            } else {
                c27471 = new C27471(continuation);
            }
        }
        Object obj = c27471.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c27471.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                ChannelIterator it = receiveChannel.iterator();
                c27471.L$0 = comparator;
                c27471.L$1 = receiveChannel;
                c27471.L$2 = it;
                c27471.label = 1;
                Object objHasNext2 = it.hasNext(c27471);
                if (objHasNext2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                receiveChannel2 = receiveChannel;
                channelIterator = it;
                obj = objHasNext2;
                comparator2 = comparator;
            } catch (Throwable th) {
                receiveChannel2 = receiveChannel;
                th = th;
                throw th;
            }
        } else if (i == 1) {
            channelIterator = (ChannelIterator) c27471.L$2;
            receiveChannel2 = (ReceiveChannel) c27471.L$1;
            comparator2 = (Comparator) c27471.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Object obj2 = c27471.L$3;
            channelIterator2 = (ChannelIterator) c27471.L$2;
            ReceiveChannel receiveChannel3 = (ReceiveChannel) c27471.L$1;
            comparator3 = (Comparator) c27471.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                C27471 c274712 = c27471;
                Object obj3 = obj2;
                receiveChannel = receiveChannel3;
                C27471 c274713 = c274712;
                if (!((Boolean) obj).booleanValue()) {
                    next = channelIterator2.next();
                    if (comparator3.compare(obj3, next) >= 0) {
                        next = obj3;
                    }
                    c27471 = c274713;
                    c27471.L$0 = comparator3;
                    c27471.L$1 = receiveChannel;
                    c27471.L$2 = channelIterator2;
                    c27471.L$3 = next;
                    c27471.label = 2;
                    objHasNext = channelIterator2.hasNext(c27471);
                    if (objHasNext != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    c274712 = c27471;
                    obj3 = next;
                    obj = objHasNext;
                    C27471 c2747132 = c274712;
                    if (!((Boolean) obj).booleanValue()) {
                        ChannelsKt.cancelConsumed(receiveChannel, null);
                        return obj3;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                receiveChannel2 = receiveChannel3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th4;
                }
            }
        }
        if (((Boolean) obj).booleanValue()) {
            next = channelIterator.next();
            comparator3 = comparator2;
            ReceiveChannel receiveChannel4 = receiveChannel2;
            channelIterator2 = channelIterator;
            receiveChannel = receiveChannel4;
            c27471.L$0 = comparator3;
            c27471.L$1 = receiveChannel;
            c27471.L$2 = channelIterator2;
            c27471.L$3 = next;
            c27471.label = 2;
            objHasNext = channelIterator2.hasNext(c27471);
            if (objHasNext != coroutine_suspended) {
            }
        } else {
            ChannelsKt.cancelConsumed(receiveChannel2, null);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x009f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ac A[Catch: all -> 0x00be, TRY_LEAVE, TryCatch #3 {all -> 0x00be, blocks: (B:40:0x00a4, B:42:0x00ac, B:36:0x008f, B:26:0x0063), top: B:61:0x0063 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x00a0 -> B:15:0x003e). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object minWith(ReceiveChannel receiveChannel, Comparator comparator, Continuation continuation) {
        C27481 c27481;
        ReceiveChannel receiveChannel2;
        ChannelIterator channelIterator;
        Comparator comparator2;
        Object next;
        Comparator comparator3;
        ChannelIterator channelIterator2;
        Object objHasNext;
        if (continuation instanceof C27481) {
            c27481 = (C27481) continuation;
            if ((c27481.label & Integer.MIN_VALUE) != 0) {
                c27481.label -= Integer.MIN_VALUE;
            } else {
                c27481 = new C27481(continuation);
            }
        }
        Object obj = c27481.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c27481.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                ChannelIterator it = receiveChannel.iterator();
                c27481.L$0 = comparator;
                c27481.L$1 = receiveChannel;
                c27481.L$2 = it;
                c27481.label = 1;
                Object objHasNext2 = it.hasNext(c27481);
                if (objHasNext2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                receiveChannel2 = receiveChannel;
                channelIterator = it;
                obj = objHasNext2;
                comparator2 = comparator;
            } catch (Throwable th) {
                receiveChannel2 = receiveChannel;
                th = th;
                throw th;
            }
        } else if (i == 1) {
            channelIterator = (ChannelIterator) c27481.L$2;
            receiveChannel2 = (ReceiveChannel) c27481.L$1;
            comparator2 = (Comparator) c27481.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Object obj2 = c27481.L$3;
            channelIterator2 = (ChannelIterator) c27481.L$2;
            ReceiveChannel receiveChannel3 = (ReceiveChannel) c27481.L$1;
            comparator3 = (Comparator) c27481.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                C27481 c274812 = c27481;
                Object obj3 = obj2;
                receiveChannel = receiveChannel3;
                C27481 c274813 = c274812;
                if (!((Boolean) obj).booleanValue()) {
                    next = channelIterator2.next();
                    if (comparator3.compare(obj3, next) <= 0) {
                        next = obj3;
                    }
                    c27481 = c274813;
                    c27481.L$0 = comparator3;
                    c27481.L$1 = receiveChannel;
                    c27481.L$2 = channelIterator2;
                    c27481.L$3 = next;
                    c27481.label = 2;
                    objHasNext = channelIterator2.hasNext(c27481);
                    if (objHasNext != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    c274812 = c27481;
                    obj3 = next;
                    obj = objHasNext;
                    C27481 c2748132 = c274812;
                    if (!((Boolean) obj).booleanValue()) {
                        ChannelsKt.cancelConsumed(receiveChannel, null);
                        return obj3;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                receiveChannel2 = receiveChannel3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th4;
                }
            }
        }
        if (((Boolean) obj).booleanValue()) {
            next = channelIterator.next();
            comparator3 = comparator2;
            ReceiveChannel receiveChannel4 = receiveChannel2;
            channelIterator2 = channelIterator;
            receiveChannel = receiveChannel4;
            c27481.L$0 = comparator3;
            c27481.L$1 = receiveChannel;
            c27481.L$2 = channelIterator2;
            c27481.L$3 = next;
            c27481.label = 2;
            objHasNext = channelIterator2.hasNext(c27481);
            if (objHasNext != coroutine_suspended) {
            }
        } else {
            ChannelsKt.cancelConsumed(receiveChannel2, null);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object none(ReceiveChannel receiveChannel, Continuation continuation) {
        C27491 c27491;
        if (continuation instanceof C27491) {
            c27491 = (C27491) continuation;
            if ((c27491.label & Integer.MIN_VALUE) != 0) {
                c27491.label -= Integer.MIN_VALUE;
            } else {
                c27491 = new C27491(continuation);
            }
        }
        Object objHasNext = c27491.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c27491.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(objHasNext);
                ChannelIterator it = receiveChannel.iterator();
                c27491.L$0 = receiveChannel;
                c27491.label = 1;
                objHasNext = it.hasNext(c27491);
                if (objHasNext == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                receiveChannel = (ReceiveChannel) c27491.L$0;
                ResultKt.throwOnFailure(objHasNext);
            }
            Boolean boolBoxBoolean = Boxing.boxBoolean(!((Boolean) objHasNext).booleanValue());
            ChannelsKt.cancelConsumed(receiveChannel, null);
            return boolBoxBoolean;
        } finally {
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* compiled from: Deprecated.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u0001H\u0001H\u008a@"}, m607d2 = {"<anonymous>", ExifInterface.LONGITUDE_EAST, "", "it"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$requireNoNulls$1", m620f = "Deprecated.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$requireNoNulls$1 */
    static final class C27501<E> extends SuspendLambda implements Function2<E, Continuation<? super E>, Object> {
        final /* synthetic */ ReceiveChannel<E> $this_requireNoNulls;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C27501(ReceiveChannel<? extends E> receiveChannel, Continuation<? super C27501> continuation) {
            super(2, continuation);
            this.$this_requireNoNulls = receiveChannel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C27501 c27501 = new C27501(this.$this_requireNoNulls, continuation);
            c27501.L$0 = obj;
            return c27501;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke((C27501<E>) obj, (Continuation<? super C27501<E>>) obj2);
        }

        public final Object invoke(E e, Continuation<? super E> continuation) {
            return ((C27501) create(e, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Object obj2 = this.L$0;
            if (obj2 != null) {
                return obj2;
            }
            throw new IllegalArgumentException("null element found in " + this.$this_requireNoNulls + FilenameUtils.EXTENSION_SEPARATOR);
        }
    }

    public static /* synthetic */ ReceiveChannel zip$default(ReceiveChannel receiveChannel, ReceiveChannel receiveChannel2, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.zip(receiveChannel, receiveChannel2, coroutineContext, function2);
    }

    /* JADX INFO: Add missing generic type declarations: [V] */
    /* compiled from: Deprecated.kt */
    @Metadata(m606d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005H\u008a@"}, m607d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "R", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlinx/coroutines/channels/ProducerScope;"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$zip$2", m620f = "Deprecated.kt", m621i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2}, m622l = {487, 469, 471}, m623m = "invokeSuspend", m624n = {"$this$produce", "otherIterator", "$this$consume$iv$iv", "$this$produce", "otherIterator", "$this$consume$iv$iv", "element1", "$this$produce", "otherIterator", "$this$consume$iv$iv"}, m625s = {"L$0", "L$1", "L$3", "L$0", "L$1", "L$3", "L$5", "L$0", "L$1", "L$3"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$zip$2 */
    static final class C27602<V> extends SuspendLambda implements Function2<ProducerScope<? super V>, Continuation<? super Unit>, Object> {
        final /* synthetic */ ReceiveChannel<R> $other;
        final /* synthetic */ ReceiveChannel<E> $this_zip;
        final /* synthetic */ Function2<E, R, V> $transform;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C27602(ReceiveChannel<? extends R> receiveChannel, ReceiveChannel<? extends E> receiveChannel2, Function2<? super E, ? super R, ? extends V> function2, Continuation<? super C27602> continuation) {
            super(2, continuation);
            this.$other = receiveChannel;
            this.$this_zip = receiveChannel2;
            this.$transform = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C27602 c27602 = new C27602(this.$other, this.$this_zip, this.$transform, continuation);
            c27602.L$0 = obj;
            return c27602;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super V> producerScope, Continuation<? super Unit> continuation) {
            return ((C27602) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:57:0x0093, code lost:
        
            r14 = r7;
            r6 = r8;
            r7 = r9;
            r8 = r10;
            r9 = r11;
         */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00b8 A[Catch: all -> 0x0057, TRY_LEAVE, TryCatch #3 {all -> 0x0057, blocks: (B:27:0x00b0, B:29:0x00b8, B:41:0x0109, B:13:0x004a), top: B:55:0x004a }] */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00e3 A[Catch: all -> 0x0106, TRY_LEAVE, TryCatch #2 {all -> 0x0106, blocks: (B:33:0x00db, B:35:0x00e3), top: B:53:0x00db }] */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0100  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x0109 A[Catch: all -> 0x0057, TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x0057, blocks: (B:27:0x00b0, B:29:0x00b8, B:41:0x0109, B:13:0x004a), top: B:55:0x004a }] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            ReceiveChannel receiveChannel;
            Function2 function2;
            ProducerScope producerScope;
            Throwable th;
            ChannelIterator channelIterator;
            ChannelIterator it;
            ReceiveChannel receiveChannel2;
            ChannelIterator channelIterator2;
            ProducerScope producerScope2;
            ChannelIterator channelIterator3;
            Function2 function22;
            ReceiveChannel receiveChannel3;
            Throwable th2;
            Object obj2;
            ProducerScope producerScope3;
            ChannelIterator channelIterator4;
            Function2 function23;
            ChannelIterator channelIterator5;
            Throwable th3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    ProducerScope producerScope4 = (ProducerScope) this.L$0;
                    ChannelIterator it2 = this.$other.iterator();
                    receiveChannel = this.$this_zip;
                    function2 = this.$transform;
                    producerScope = producerScope4;
                    th = null;
                    channelIterator = it2;
                    it = receiveChannel.iterator();
                } else if (i == 1) {
                    ChannelIterator channelIterator6 = (ChannelIterator) this.L$4;
                    ReceiveChannel receiveChannel4 = (ReceiveChannel) this.L$3;
                    Function2 function24 = (Function2) this.L$2;
                    ChannelIterator channelIterator7 = (ChannelIterator) this.L$1;
                    ProducerScope producerScope5 = (ProducerScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    producerScope3 = producerScope5;
                    channelIterator4 = channelIterator7;
                    function23 = function24;
                    receiveChannel2 = receiveChannel4;
                    channelIterator5 = channelIterator6;
                    th3 = null;
                    if (!((Boolean) obj).booleanValue()) {
                        Object next = channelIterator5.next();
                        this.L$0 = producerScope3;
                        this.L$1 = channelIterator4;
                        this.L$2 = function23;
                        this.L$3 = receiveChannel2;
                        this.L$4 = channelIterator5;
                        this.L$5 = next;
                        this.label = 2;
                        Object objHasNext = channelIterator4.hasNext(this);
                        if (objHasNext == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        channelIterator2 = channelIterator5;
                        obj2 = next;
                        obj = objHasNext;
                        producerScope2 = producerScope3;
                        channelIterator3 = channelIterator4;
                        function22 = function23;
                        receiveChannel3 = receiveChannel2;
                        th2 = th3;
                        it = channelIterator2;
                        if (!((Boolean) obj).booleanValue()) {
                        }
                    } else {
                        Unit unit = Unit.INSTANCE;
                        ChannelsKt.cancelConsumed(receiveChannel2, th3);
                        return Unit.INSTANCE;
                    }
                } else if (i == 2) {
                    Object obj3 = this.L$5;
                    ChannelIterator channelIterator8 = (ChannelIterator) this.L$4;
                    receiveChannel2 = (ReceiveChannel) this.L$3;
                    Function2 function25 = (Function2) this.L$2;
                    ChannelIterator channelIterator9 = (ChannelIterator) this.L$1;
                    ProducerScope producerScope6 = (ProducerScope) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        producerScope2 = producerScope6;
                        channelIterator3 = channelIterator9;
                        function22 = function25;
                        receiveChannel3 = receiveChannel2;
                        th2 = null;
                        channelIterator2 = channelIterator8;
                        obj2 = obj3;
                        it = channelIterator2;
                        try {
                            if (!((Boolean) obj).booleanValue()) {
                                Object objInvoke = function22.invoke(obj2, channelIterator3.next());
                                this.L$0 = producerScope2;
                                this.L$1 = channelIterator3;
                                this.L$2 = function22;
                                this.L$3 = receiveChannel3;
                                this.L$4 = it;
                                this.L$5 = null;
                                this.label = 3;
                                if (producerScope2.send(objInvoke, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                th = th2;
                                receiveChannel = receiveChannel3;
                                function2 = function22;
                                channelIterator = channelIterator3;
                                producerScope = producerScope2;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            receiveChannel = receiveChannel3;
                            try {
                                throw th;
                            } catch (Throwable th5) {
                                ChannelsKt.cancelConsumed(receiveChannel, th);
                                throw th5;
                            }
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        receiveChannel = receiveChannel2;
                        throw th;
                    }
                } else {
                    if (i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    it = (ChannelIterator) this.L$4;
                    receiveChannel = (ReceiveChannel) this.L$3;
                    function2 = (Function2) this.L$2;
                    channelIterator = (ChannelIterator) this.L$1;
                    producerScope = (ProducerScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    th = null;
                }
                this.L$0 = producerScope;
                this.L$1 = channelIterator;
                this.L$2 = function2;
                this.L$3 = receiveChannel;
                this.L$4 = it;
                this.L$5 = null;
                this.label = 1;
                Object objHasNext2 = it.hasNext(this);
                if (objHasNext2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                ChannelIterator channelIterator10 = it;
                th3 = th;
                obj = objHasNext2;
                producerScope3 = producerScope;
                channelIterator4 = channelIterator;
                function23 = function2;
                receiveChannel2 = receiveChannel;
                channelIterator5 = channelIterator10;
                if (!((Boolean) obj).booleanValue()) {
                }
            } catch (Throwable th7) {
                th = th7;
            }
        }
    }

    public static final <E, R, V> ReceiveChannel<V> zip(ReceiveChannel<? extends E> receiveChannel, ReceiveChannel<? extends R> receiveChannel2, CoroutineContext coroutineContext, Function2<? super E, ? super R, ? extends V> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumesAll(receiveChannel, receiveChannel2), new C27602(receiveChannel2, receiveChannel, function2, null), 6, null);
    }

    public static final Function1<Throwable, Unit> consumes(final ReceiveChannel<?> receiveChannel) {
        return new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.consumes.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                ChannelsKt.cancelConsumed(receiveChannel, th);
            }
        };
    }
}
