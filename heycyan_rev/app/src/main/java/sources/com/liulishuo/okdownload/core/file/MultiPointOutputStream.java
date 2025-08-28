package com.liulishuo.okdownload.core.file;

import android.net.Uri;
import android.os.StatFs;
import android.os.SystemClock;
import android.util.SparseArray;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.breakpoint.DownloadStore;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.exception.PreAllocateException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.LockSupport;

/* loaded from: classes2.dex */
public class MultiPointOutputStream {
    private static final ExecutorService FILE_IO_EXECUTOR = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkDownload file io", false));
    private static final String TAG = "MultiPointOutputStream";
    final AtomicLong allNoSyncLength;
    boolean canceled;
    final StreamsState doneState;
    private volatile boolean firstOutputStream;
    private final int flushBufferSize;
    private final BreakpointInfo info;
    private final boolean isPreAllocateLength;
    final AtomicLong lastSyncTimestamp;
    ArrayList<Integer> noMoreStreamList;
    final SparseArray<AtomicLong> noSyncLengthMap;
    final SparseArray<DownloadOutputStream> outputStreamMap;
    final SparseArray<Thread> parkedRunBlockThreadMap;
    private String path;
    List<Integer> requireStreamBlocks;
    volatile Thread runSyncThread;
    StreamsState state;
    private final DownloadStore store;
    private final boolean supportSeek;
    private final int syncBufferIntervalMills;
    private final int syncBufferSize;
    IOException syncException;
    volatile Future syncFuture;
    private final Runnable syncRunnable;
    private final DownloadTask task;

    MultiPointOutputStream(DownloadTask downloadTask, BreakpointInfo breakpointInfo, DownloadStore downloadStore, Runnable runnable) {
        this.outputStreamMap = new SparseArray<>();
        this.noSyncLengthMap = new SparseArray<>();
        this.allNoSyncLength = new AtomicLong();
        this.lastSyncTimestamp = new AtomicLong();
        this.canceled = false;
        this.parkedRunBlockThreadMap = new SparseArray<>();
        this.doneState = new StreamsState();
        this.state = new StreamsState();
        this.firstOutputStream = true;
        this.task = downloadTask;
        this.flushBufferSize = downloadTask.getFlushBufferSize();
        this.syncBufferSize = downloadTask.getSyncBufferSize();
        this.syncBufferIntervalMills = downloadTask.getSyncBufferIntervalMills();
        this.info = breakpointInfo;
        this.store = downloadStore;
        this.supportSeek = OkDownload.with().outputStreamFactory().supportSeek();
        this.isPreAllocateLength = OkDownload.with().processFileStrategy().isPreAllocateLength(downloadTask);
        this.noMoreStreamList = new ArrayList<>();
        if (runnable == null) {
            this.syncRunnable = new Runnable() { // from class: com.liulishuo.okdownload.core.file.MultiPointOutputStream.1
                @Override // java.lang.Runnable
                public void run() {
                    MultiPointOutputStream.this.runSyncDelayException();
                }
            };
        } else {
            this.syncRunnable = runnable;
        }
        File file = downloadTask.getFile();
        if (file != null) {
            this.path = file.getAbsolutePath();
        }
    }

    public MultiPointOutputStream(DownloadTask downloadTask, BreakpointInfo breakpointInfo, DownloadStore downloadStore) {
        this(downloadTask, breakpointInfo, downloadStore, null);
    }

    public synchronized void write(int i, byte[] bArr, int i2) throws IOException {
        if (this.canceled) {
            return;
        }
        outputStream(i).write(bArr, 0, i2);
        long j = i2;
        this.allNoSyncLength.addAndGet(j);
        this.noSyncLengthMap.get(i).addAndGet(j);
        inspectAndPersist();
    }

    public void cancelAsync() {
        FILE_IO_EXECUTOR.execute(new Runnable() { // from class: com.liulishuo.okdownload.core.file.MultiPointOutputStream.2
            @Override // java.lang.Runnable
            public void run() {
                MultiPointOutputStream.this.cancel();
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00d4 A[Catch: all -> 0x0182, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0007, B:12:0x000e, B:17:0x0022, B:18:0x0028, B:20:0x002e, B:21:0x0034, B:24:0x003d, B:25:0x0070, B:39:0x00c8, B:40:0x00ce, B:42:0x00d4, B:43:0x00da, B:46:0x00e3, B:47:0x0116, B:51:0x0126, B:52:0x012c, B:54:0x0132, B:55:0x0138, B:58:0x0141, B:59:0x0174, B:60:0x0181, B:14:0x0016, B:28:0x007f, B:30:0x0083, B:32:0x008b, B:35:0x00a3, B:37:0x00b6, B:38:0x00c7), top: B:64:0x0001, inners: #2, #3, #4, #5 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized void cancel() {
        List<Integer> list = this.requireStreamBlocks;
        if (list == null) {
            return;
        }
        if (this.canceled) {
            return;
        }
        this.canceled = true;
        this.noMoreStreamList.addAll(list);
        try {
            if (this.allNoSyncLength.get() <= 0) {
                return;
            }
            if (this.syncFuture != null && !this.syncFuture.isDone()) {
                inspectValidPath();
                OkDownload.with().processFileStrategy().getFileLock().increaseLock(this.path);
                try {
                    ensureSync(true, -1);
                    OkDownload.with().processFileStrategy().getFileLock().decreaseLock(this.path);
                    while (r1.hasNext()) {
                    }
                    this.store.onTaskEnd(this.task.getId(), EndCause.CANCELED, null);
                    return;
                } catch (Throwable th) {
                    OkDownload.with().processFileStrategy().getFileLock().decreaseLock(this.path);
                    throw th;
                }
            }
            for (Integer num : this.requireStreamBlocks) {
                try {
                    close(num.intValue());
                } catch (IOException e) {
                    Util.m583d(TAG, "OutputStream close failed task[" + this.task.getId() + "] block[" + num + "]" + e);
                }
            }
            this.store.onTaskEnd(this.task.getId(), EndCause.CANCELED, null);
            return;
        } finally {
            for (Integer num2 : this.requireStreamBlocks) {
                try {
                    close(num2.intValue());
                } catch (IOException e2) {
                    Util.m583d(TAG, "OutputStream close failed task[" + this.task.getId() + "] block[" + num2 + "]" + e2);
                }
            }
            this.store.onTaskEnd(this.task.getId(), EndCause.CANCELED, null);
        }
    }

    public void done(int i) throws IOException {
        this.noMoreStreamList.add(Integer.valueOf(i));
        try {
            IOException iOException = this.syncException;
            if (iOException != null) {
                throw iOException;
            }
            if (this.syncFuture != null && !this.syncFuture.isDone()) {
                AtomicLong atomicLong = this.noSyncLengthMap.get(i);
                if (atomicLong != null && atomicLong.get() > 0) {
                    inspectStreamState(this.doneState);
                    ensureSync(this.doneState.isNoMoreStream, i);
                }
            } else if (this.syncFuture == null) {
                Util.m583d(TAG, "OutputStream done but no need to ensure sync, because the sync job not run yet. task[" + this.task.getId() + "] block[" + i + "]");
            } else {
                Util.m583d(TAG, "OutputStream done but no need to ensure sync, because the syncFuture.isDone[" + this.syncFuture.isDone() + "] task[" + this.task.getId() + "] block[" + i + "]");
            }
        } finally {
            close(i);
        }
    }

    void ensureSync(boolean z, int i) throws ExecutionException, InterruptedException {
        if (this.syncFuture == null || this.syncFuture.isDone()) {
            return;
        }
        if (!z) {
            this.parkedRunBlockThreadMap.put(i, Thread.currentThread());
        }
        if (this.runSyncThread != null) {
            unparkThread(this.runSyncThread);
        } else {
            while (!isRunSyncThreadValid()) {
                parkThread(25L);
            }
            unparkThread(this.runSyncThread);
        }
        if (z) {
            unparkThread(this.runSyncThread);
            try {
                this.syncFuture.get();
                return;
            } catch (InterruptedException | ExecutionException unused) {
                return;
            }
        }
        parkThread();
    }

    boolean isRunSyncThreadValid() {
        return this.runSyncThread != null;
    }

    public void inspectComplete(int i) throws IOException {
        BlockInfo block = this.info.getBlock(i);
        if (!Util.isCorrectFull(block.getCurrentOffset(), block.getContentLength())) {
            throw new IOException("The current offset on block-info isn't update correct, " + block.getCurrentOffset() + " != " + block.getContentLength() + " on " + i);
        }
    }

    void inspectAndPersist() throws IOException {
        IOException iOException = this.syncException;
        if (iOException != null) {
            throw iOException;
        }
        if (this.syncFuture == null) {
            synchronized (this.syncRunnable) {
                if (this.syncFuture == null) {
                    this.syncFuture = executeSyncRunnableAsync();
                }
            }
        }
    }

    synchronized void close(int i) throws IOException {
        DownloadOutputStream downloadOutputStream = this.outputStreamMap.get(i);
        if (downloadOutputStream != null) {
            downloadOutputStream.close();
            this.outputStreamMap.remove(i);
            Util.m583d(TAG, "OutputStream close task[" + this.task.getId() + "] block[" + i + "]");
        }
    }

    void parkThread(long j) {
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(j));
    }

    void parkThread() {
        LockSupport.park();
    }

    void unparkThread(Thread thread) {
        LockSupport.unpark(thread);
    }

    Future executeSyncRunnableAsync() {
        return FILE_IO_EXECUTOR.submit(this.syncRunnable);
    }

    void inspectStreamState(StreamsState streamsState) {
        streamsState.newNoMoreStreamBlockList.clear();
        int size = new HashSet((List) this.noMoreStreamList.clone()).size();
        if (size != this.requireStreamBlocks.size()) {
            Util.m583d(TAG, "task[" + this.task.getId() + "] current need fetching block count " + this.requireStreamBlocks.size() + " is not equal to no more stream block count " + size);
            streamsState.isNoMoreStream = false;
        } else {
            Util.m583d(TAG, "task[" + this.task.getId() + "] current need fetching block count " + this.requireStreamBlocks.size() + " is equal to no more stream block count " + size);
            streamsState.isNoMoreStream = true;
        }
        SparseArray<DownloadOutputStream> sparseArrayClone = this.outputStreamMap.clone();
        int size2 = sparseArrayClone.size();
        for (int i = 0; i < size2; i++) {
            int iKeyAt = sparseArrayClone.keyAt(i);
            if (this.noMoreStreamList.contains(Integer.valueOf(iKeyAt)) && !streamsState.noMoreStreamBlockList.contains(Integer.valueOf(iKeyAt))) {
                streamsState.noMoreStreamBlockList.add(Integer.valueOf(iKeyAt));
                streamsState.newNoMoreStreamBlockList.add(Integer.valueOf(iKeyAt));
            }
        }
    }

    public void setRequireStreamBlocks(List<Integer> list) {
        this.requireStreamBlocks = list;
    }

    public void catchBlockConnectException(int i) {
        this.noMoreStreamList.add(Integer.valueOf(i));
    }

    static class StreamsState {
        boolean isNoMoreStream;
        List<Integer> noMoreStreamBlockList = new ArrayList();
        List<Integer> newNoMoreStreamBlockList = new ArrayList();

        StreamsState() {
        }

        boolean isStreamsEndOrChanged() {
            return this.isNoMoreStream || this.newNoMoreStreamBlockList.size() > 0;
        }
    }

    void runSyncDelayException() {
        try {
            runSync();
        } catch (IOException e) {
            this.syncException = e;
            Util.m586w(TAG, "Sync to breakpoint-store for task[" + this.task.getId() + "] failed with cause: " + e);
        }
    }

    void runSync() throws IOException {
        int i;
        Util.m583d(TAG, "OutputStream start flush looper task[" + this.task.getId() + "] with syncBufferIntervalMills[" + this.syncBufferIntervalMills + "] syncBufferSize[" + this.syncBufferSize + "]");
        this.runSyncThread = Thread.currentThread();
        long nextParkMillisecond = this.syncBufferIntervalMills;
        flushProcess();
        while (true) {
            parkThread(nextParkMillisecond);
            inspectStreamState(this.state);
            if (this.state.isStreamsEndOrChanged()) {
                Util.m583d(TAG, "runSync state change isNoMoreStream[" + this.state.isNoMoreStream + "] newNoMoreStreamBlockList[" + this.state.newNoMoreStreamBlockList + "]");
                if (this.allNoSyncLength.get() > 0) {
                    flushProcess();
                }
                for (Integer num : this.state.newNoMoreStreamBlockList) {
                    Thread thread = this.parkedRunBlockThreadMap.get(num.intValue());
                    this.parkedRunBlockThreadMap.remove(num.intValue());
                    if (thread != null) {
                        unparkThread(thread);
                    }
                }
                if (this.state.isNoMoreStream) {
                    break;
                }
            } else {
                if (isNoNeedFlushForLength()) {
                    i = this.syncBufferIntervalMills;
                } else {
                    nextParkMillisecond = getNextParkMillisecond();
                    if (nextParkMillisecond <= 0) {
                        flushProcess();
                        i = this.syncBufferIntervalMills;
                    }
                }
                nextParkMillisecond = i;
            }
        }
        int size = this.parkedRunBlockThreadMap.size();
        for (int i2 = 0; i2 < size; i2++) {
            Thread threadValueAt = this.parkedRunBlockThreadMap.valueAt(i2);
            if (threadValueAt != null) {
                unparkThread(threadValueAt);
            }
        }
        this.parkedRunBlockThreadMap.clear();
        Util.m583d(TAG, "OutputStream stop flush looper task[" + this.task.getId() + "]");
    }

    boolean isNoNeedFlushForLength() {
        return this.allNoSyncLength.get() < ((long) this.syncBufferSize);
    }

    long getNextParkMillisecond() {
        return this.syncBufferIntervalMills - (now() - this.lastSyncTimestamp.get());
    }

    long now() {
        return SystemClock.uptimeMillis();
    }

    void flushProcess() throws IOException {
        int size;
        long j;
        synchronized (this.noSyncLengthMap) {
            size = this.noSyncLengthMap.size();
        }
        SparseArray sparseArray = new SparseArray(size);
        int i = 0;
        while (true) {
            j = 0;
            if (i >= size) {
                break;
            }
            try {
                int iKeyAt = this.outputStreamMap.keyAt(i);
                long j2 = this.noSyncLengthMap.get(iKeyAt).get();
                if (j2 > 0) {
                    sparseArray.put(iKeyAt, Long.valueOf(j2));
                    this.outputStreamMap.get(iKeyAt).flushAndSync();
                }
                i++;
            } catch (IOException e) {
                Util.m586w(TAG, "OutputStream flush and sync data to filesystem failed " + e);
                return;
            }
        }
        int size2 = sparseArray.size();
        for (int i2 = 0; i2 < size2; i2++) {
            int iKeyAt2 = sparseArray.keyAt(i2);
            long jLongValue = ((Long) sparseArray.valueAt(i2)).longValue();
            this.store.onSyncToFilesystemSuccess(this.info, iKeyAt2, jLongValue);
            j += jLongValue;
            this.noSyncLengthMap.get(iKeyAt2).addAndGet(-jLongValue);
            Util.m583d(TAG, "OutputStream sync success (" + this.task.getId() + ") block(" + iKeyAt2 + ")  syncLength(" + jLongValue + ") currentOffset(" + this.info.getBlock(iKeyAt2).getCurrentOffset() + ")");
        }
        this.allNoSyncLength.addAndGet(-j);
        this.lastSyncTimestamp.set(SystemClock.uptimeMillis());
    }

    synchronized DownloadOutputStream outputStream(int i) throws IOException {
        DownloadOutputStream downloadOutputStream;
        Uri uri;
        downloadOutputStream = this.outputStreamMap.get(i);
        if (downloadOutputStream == null) {
            boolean zIsUriFileScheme = Util.isUriFileScheme(this.task.getUri());
            if (zIsUriFileScheme) {
                File file = this.task.getFile();
                if (file == null) {
                    throw new FileNotFoundException("Filename is not ready!");
                }
                File parentFile = this.task.getParentFile();
                if (!parentFile.exists() && !parentFile.mkdirs()) {
                    throw new IOException("Create parent folder failed!");
                }
                if (file.createNewFile()) {
                    Util.m583d(TAG, "Create new file: " + file.getName());
                }
                uri = Uri.fromFile(file);
            } else {
                uri = this.task.getUri();
            }
            DownloadOutputStream downloadOutputStreamCreate = OkDownload.with().outputStreamFactory().create(OkDownload.with().context(), uri, this.flushBufferSize);
            if (this.supportSeek) {
                long rangeLeft = this.info.getBlock(i).getRangeLeft();
                if (rangeLeft > 0) {
                    downloadOutputStreamCreate.seek(rangeLeft);
                    Util.m583d(TAG, "Create output stream write from (" + this.task.getId() + ") block(" + i + ") " + rangeLeft);
                }
            }
            if (this.firstOutputStream) {
                this.store.markFileDirty(this.task.getId());
            }
            if (!this.info.isChunked() && this.firstOutputStream && this.isPreAllocateLength) {
                long totalLength = this.info.getTotalLength();
                if (zIsUriFileScheme) {
                    File file2 = this.task.getFile();
                    long length = totalLength - file2.length();
                    if (length > 0) {
                        inspectFreeSpace(new StatFs(file2.getAbsolutePath()), length);
                        downloadOutputStreamCreate.setLength(totalLength);
                    }
                } else {
                    downloadOutputStreamCreate.setLength(totalLength);
                }
            }
            synchronized (this.noSyncLengthMap) {
                this.outputStreamMap.put(i, downloadOutputStreamCreate);
                this.noSyncLengthMap.put(i, new AtomicLong());
            }
            this.firstOutputStream = false;
            downloadOutputStream = downloadOutputStreamCreate;
        }
        return downloadOutputStream;
    }

    void inspectFreeSpace(StatFs statFs, long j) throws PreAllocateException {
        long freeSpaceBytes = Util.getFreeSpaceBytes(statFs);
        if (freeSpaceBytes < j) {
            throw new PreAllocateException(j, freeSpaceBytes);
        }
    }

    private void inspectValidPath() {
        if (this.path != null || this.task.getFile() == null) {
            return;
        }
        this.path = this.task.getFile().getAbsolutePath();
    }
}
