package okio.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.ClientCookie;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import okio.BufferedSink;
import okio.FileMetadata;
import okio.Okio;
import okio.Path;
import okio.Source;

/* compiled from: FileSystem.kt */
@Metadata(m606d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aF\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0080@¢\u0006\u0002\u0010\f\u001a\u001c\u0010\r\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0000\u001a\u001c\u0010\u0010\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\nH\u0000\u001a\u001c\u0010\u0013\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\nH\u0000\u001a\u0014\u0010\u0016\u001a\u00020\n*\u00020\u00052\u0006\u0010\b\u001a\u00020\u0003H\u0000\u001a\"\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018*\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0000\u001a\u0014\u0010\u0019\u001a\u00020\u001a*\u00020\u00052\u0006\u0010\b\u001a\u00020\u0003H\u0000\u001a\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u0003*\u00020\u00052\u0006\u0010\b\u001a\u00020\u0003H\u0000¨\u0006\u001c"}, m607d2 = {"collectRecursively", "", "Lkotlin/sequences/SequenceScope;", "Lokio/Path;", "fileSystem", "Lokio/FileSystem;", "stack", "Lkotlin/collections/ArrayDeque;", ClientCookie.PATH_ATTR, "followSymlinks", "", "postorder", "(Lkotlin/sequences/SequenceScope;Lokio/FileSystem;Lkotlin/collections/ArrayDeque;Lokio/Path;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "commonCopy", "source", TypedValues.AttributesType.S_TARGET, "commonCreateDirectories", "dir", "mustCreate", "commonDeleteRecursively", "fileOrDirectory", "mustExist", "commonExists", "commonListRecursively", "Lkotlin/sequences/Sequence;", "commonMetadata", "Lokio/FileMetadata;", "symlinkTarget", "okio"}, m608k = 2, m609mv = {1, 9, 0}, m611xi = 48)
/* renamed from: okio.internal.-FileSystem, reason: invalid class name */
/* loaded from: classes3.dex */
public final class FileSystem {

    /* compiled from: FileSystem.kt */
    @Metadata(m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "okio.internal.-FileSystem", m620f = "FileSystem.kt", m621i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, m622l = {EMachine.EM_C166, EMachine.EM_SCORE7, 145}, m623m = "collectRecursively", m624n = {"$this$collectRecursively", "fileSystem", "stack", ClientCookie.PATH_ATTR, "followSymlinks", "postorder", "$this$collectRecursively", "fileSystem", "stack", ClientCookie.PATH_ATTR, "followSymlinks", "postorder"}, m625s = {"L$0", "L$1", "L$2", "L$3", "Z$0", "Z$1", "L$0", "L$1", "L$2", "L$3", "Z$0", "Z$1"})
    /* renamed from: okio.internal.-FileSystem$collectRecursively$1 */
    static final class C29391 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        boolean Z$0;
        boolean Z$1;
        int label;
        /* synthetic */ Object result;

        C29391(Continuation<? super C29391> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FileSystem.collectRecursively(null, null, null, null, false, false, this);
        }
    }

    public static final FileMetadata commonMetadata(okio.FileSystem fileSystem, Path path) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        FileMetadata fileMetadataMetadataOrNull = fileSystem.metadataOrNull(path);
        if (fileMetadataMetadataOrNull != null) {
            return fileMetadataMetadataOrNull;
        }
        throw new FileNotFoundException("no such file: " + path);
    }

    public static final boolean commonExists(okio.FileSystem fileSystem, Path path) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        return fileSystem.metadataOrNull(path) != null;
    }

    public static final void commonCreateDirectories(okio.FileSystem fileSystem, Path dir, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(dir, "dir");
        ArrayDeque arrayDeque = new ArrayDeque();
        for (Path pathParent = dir; pathParent != null && !fileSystem.exists(pathParent); pathParent = pathParent.parent()) {
            arrayDeque.addFirst(pathParent);
        }
        if (z && arrayDeque.isEmpty()) {
            throw new IOException(dir + " already exists.");
        }
        Iterator it = arrayDeque.iterator();
        while (it.hasNext()) {
            fileSystem.createDirectory((Path) it.next());
        }
    }

    public static final void commonDeleteRecursively(okio.FileSystem fileSystem, Path fileOrDirectory, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(fileOrDirectory, "fileOrDirectory");
        Iterator it = SequencesKt.sequence(new FileSystem$commonDeleteRecursively$sequence$1(fileSystem, fileOrDirectory, null)).iterator();
        while (it.hasNext()) {
            fileSystem.delete((Path) it.next(), z && !it.hasNext());
        }
    }

    /* compiled from: FileSystem.kt */
    @Metadata(m606d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Lokio/Path;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "okio.internal.-FileSystem$commonListRecursively$1", m620f = "FileSystem.kt", m621i = {0, 0}, m622l = {EMachine.EM_TMM_GPP}, m623m = "invokeSuspend", m624n = {"$this$sequence", "stack"}, m625s = {"L$0", "L$1"})
    /* renamed from: okio.internal.-FileSystem$commonListRecursively$1 */
    static final class C29401 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Path $dir;
        final /* synthetic */ boolean $followSymlinks;
        final /* synthetic */ okio.FileSystem $this_commonListRecursively;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C29401(Path path, okio.FileSystem fileSystem, boolean z, Continuation<? super C29401> continuation) {
            super(2, continuation);
            this.$dir = path;
            this.$this_commonListRecursively = fileSystem;
            this.$followSymlinks = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C29401 c29401 = new C29401(this.$dir, this.$this_commonListRecursively, this.$followSymlinks, continuation);
            c29401.L$0 = obj;
            return c29401;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SequenceScope<? super Path> sequenceScope, Continuation<? super Unit> continuation) {
            return ((C29401) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            SequenceScope sequenceScope;
            ArrayDeque arrayDeque;
            Iterator<Path> it;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SequenceScope sequenceScope2 = (SequenceScope) this.L$0;
                ArrayDeque arrayDeque2 = new ArrayDeque();
                arrayDeque2.addLast(this.$dir);
                sequenceScope = sequenceScope2;
                arrayDeque = arrayDeque2;
                it = this.$this_commonListRecursively.list(this.$dir).iterator();
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                it = (Iterator) this.L$2;
                ArrayDeque arrayDeque3 = (ArrayDeque) this.L$1;
                SequenceScope sequenceScope3 = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                arrayDeque = arrayDeque3;
                sequenceScope = sequenceScope3;
            }
            while (it.hasNext()) {
                Path next = it.next();
                this.L$0 = sequenceScope;
                this.L$1 = arrayDeque;
                this.L$2 = it;
                this.label = 1;
                if (FileSystem.collectRecursively(sequenceScope, this.$this_commonListRecursively, arrayDeque, next, this.$followSymlinks, false, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    public static final Sequence<Path> commonListRecursively(okio.FileSystem fileSystem, Path dir, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(dir, "dir");
        return SequencesKt.sequence(new C29401(dir, fileSystem, z, null));
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00df, code lost:
    
        if (r0 != false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00e1, code lost:
    
        if (r7 != 0) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00e3, code lost:
    
        r6.addLast(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00ea, code lost:
    
        r7 = r6;
        r6 = r1;
        r1 = r0;
        r0 = r2;
        r2 = r3.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0125, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0126, code lost:
    
        r7 = r6;
     */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object collectRecursively(SequenceScope<? super Path> sequenceScope, okio.FileSystem fileSystem, ArrayDeque<Path> arrayDeque, Path path, boolean z, boolean z2, Continuation<? super Unit> continuation) throws Throwable {
        C29391 c29391;
        okio.FileSystem fileSystem2;
        ArrayDeque<Path> arrayDeque2;
        boolean z3;
        okio.FileSystem fileSystem3;
        SequenceScope<? super Path> sequenceScope2;
        boolean z4;
        Path path2 = path;
        boolean z5 = z2;
        if (continuation instanceof C29391) {
            c29391 = (C29391) continuation;
            if ((c29391.label & Integer.MIN_VALUE) != 0) {
                c29391.label -= Integer.MIN_VALUE;
            } else {
                c29391 = new C29391(continuation);
            }
        }
        Object obj = c29391.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c29391.label;
        int i2 = 0;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (z5) {
                fileSystem2 = fileSystem;
                arrayDeque2 = arrayDeque;
                z3 = z;
            } else {
                c29391.L$0 = sequenceScope;
                fileSystem2 = fileSystem;
                c29391.L$1 = fileSystem2;
                arrayDeque2 = arrayDeque;
                c29391.L$2 = arrayDeque2;
                c29391.L$3 = path2;
                z3 = z;
                c29391.Z$0 = z3;
                c29391.Z$1 = z5;
                c29391.label = 1;
                if (sequenceScope.yield(path2, c29391) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            fileSystem3 = fileSystem2;
            boolean z6 = z3;
            sequenceScope2 = sequenceScope;
            z4 = z6;
        } else {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                boolean z7 = c29391.Z$1;
                boolean z8 = c29391.Z$0;
                Iterator<Path> it = (Iterator) c29391.L$4;
                Path path3 = (Path) c29391.L$3;
                ArrayDeque<Path> arrayDeque3 = (ArrayDeque) c29391.L$2;
                fileSystem3 = (okio.FileSystem) c29391.L$1;
                sequenceScope2 = (SequenceScope) c29391.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    while (it.hasNext()) {
                        Path next = it.next();
                        c29391.L$0 = sequenceScope2;
                        c29391.L$1 = fileSystem3;
                        c29391.L$2 = arrayDeque3;
                        c29391.L$3 = path3;
                        c29391.L$4 = it;
                        c29391.Z$0 = z8;
                        c29391.Z$1 = z7;
                        c29391.label = 2;
                        if (collectRecursively(sequenceScope2, fileSystem3, arrayDeque3, next, z8, z7, c29391) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    arrayDeque3.removeLast();
                    z5 = z7;
                    path2 = path3;
                    if (z5) {
                        return Unit.INSTANCE;
                    }
                    c29391.L$0 = null;
                    c29391.L$1 = null;
                    c29391.L$2 = null;
                    c29391.L$3 = null;
                    c29391.L$4 = null;
                    c29391.label = 3;
                    if (sequenceScope2.yield(path2, c29391) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    th = th;
                    arrayDeque3.removeLast();
                    throw th;
                }
            }
            boolean z9 = c29391.Z$1;
            boolean z10 = c29391.Z$0;
            Path path4 = (Path) c29391.L$3;
            arrayDeque2 = (ArrayDeque) c29391.L$2;
            fileSystem3 = (okio.FileSystem) c29391.L$1;
            sequenceScope2 = (SequenceScope) c29391.L$0;
            ResultKt.throwOnFailure(obj);
            z5 = z9;
            z4 = z10;
            path2 = path4;
        }
        List<Path> listListOrNull = fileSystem3.listOrNull(path2);
        if (listListOrNull == null) {
            listListOrNull = CollectionsKt.emptyList();
        }
        if (!listListOrNull.isEmpty()) {
            Path path5 = path2;
            while (true) {
                if (z4 && arrayDeque2.contains(path5)) {
                    throw new IOException("symlink cycle at " + path2);
                }
                Path pathSymlinkTarget = symlinkTarget(fileSystem3, path5);
                if (pathSymlinkTarget == null) {
                    break;
                }
                i2++;
                path5 = pathSymlinkTarget;
            }
        }
        if (z5) {
        }
    }

    public static final Path symlinkTarget(okio.FileSystem fileSystem, Path path) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        Path symlinkTarget = fileSystem.metadata(path).getSymlinkTarget();
        if (symlinkTarget == null) {
            return null;
        }
        Path pathParent = path.parent();
        Intrinsics.checkNotNull(pathParent);
        return pathParent.resolve(symlinkTarget);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0047 A[Catch: all -> 0x005d, TRY_LEAVE, TryCatch #4 {all -> 0x005d, blocks: (B:3:0x0016, B:19:0x0047, B:25:0x005c, B:16:0x0040, B:4:0x0023, B:13:0x003b), top: B:45:0x0016, inners: #2, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005c A[Catch: all -> 0x005d, TRY_ENTER, TRY_LEAVE, TryCatch #4 {all -> 0x005d, blocks: (B:3:0x0016, B:19:0x0047, B:25:0x005c, B:16:0x0040, B:4:0x0023, B:13:0x003b), top: B:45:0x0016, inners: #2, #5 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void commonCopy(okio.FileSystem fileSystem, Path source, Path target) throws IOException {
        Long lValueOf;
        Throwable th;
        Long lValueOf2;
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(target, "target");
        Source source2 = fileSystem.source(source);
        Throwable th2 = null;
        try {
            Source source3 = source2;
            BufferedSink bufferedSinkBuffer = Okio.buffer(fileSystem.sink(target));
            try {
                lValueOf2 = Long.valueOf(bufferedSinkBuffer.writeAll(source3));
            } catch (Throwable th3) {
                if (bufferedSinkBuffer != null) {
                    try {
                        bufferedSinkBuffer.close();
                    } catch (Throwable th4) {
                        ExceptionsKt.addSuppressed(th3, th4);
                    }
                }
                th = th3;
                lValueOf2 = null;
            }
            if (bufferedSinkBuffer != null) {
                try {
                    bufferedSinkBuffer.close();
                    th = null;
                } catch (Throwable th5) {
                    th = th5;
                }
                if (th == null) {
                    throw th;
                }
                Intrinsics.checkNotNull(lValueOf2);
                lValueOf = Long.valueOf(lValueOf2.longValue());
                if (source2 != null) {
                    try {
                        source2.close();
                    } catch (Throwable th6) {
                        th2 = th6;
                    }
                }
            } else {
                th = null;
                if (th == null) {
                }
            }
        } catch (Throwable th7) {
            if (source2 != null) {
                try {
                    source2.close();
                } catch (Throwable th8) {
                    ExceptionsKt.addSuppressed(th7, th8);
                }
            }
            th2 = th7;
            lValueOf = null;
        }
        if (th2 != null) {
            throw th2;
        }
        Intrinsics.checkNotNull(lValueOf);
    }
}
