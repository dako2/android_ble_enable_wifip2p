package com.elvishew.xlog.printer.file;

import com.elvishew.xlog.flattener.Flattener;
import com.elvishew.xlog.flattener.Flattener2;
import com.elvishew.xlog.internal.DefaultsFactory;
import com.elvishew.xlog.internal.Platform;
import com.elvishew.xlog.internal.printer.file.backup.BackupStrategyWrapper;
import com.elvishew.xlog.internal.printer.file.backup.BackupUtil;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.file.backup.BackupStrategy;
import com.elvishew.xlog.printer.file.backup.BackupStrategy2;
import com.elvishew.xlog.printer.file.clean.CleanStrategy;
import com.elvishew.xlog.printer.file.naming.FileNameGenerator;
import com.elvishew.xlog.printer.file.writer.Writer;
import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes.dex */
public class FilePrinter implements Printer {
    private static final boolean USE_WORKER = true;
    private final BackupStrategy2 backupStrategy;
    private final boolean cleanInRealTime;
    private final CleanStrategy cleanStrategy;
    private final FileNameGenerator fileNameGenerator;
    private Flattener2 flattener;
    private final String folderPath;
    private volatile Worker worker = new Worker();
    private Writer writer;

    FilePrinter(Builder builder) {
        this.folderPath = builder.folderPath;
        this.fileNameGenerator = builder.fileNameGenerator;
        this.backupStrategy = builder.backupStrategy;
        this.cleanStrategy = builder.cleanStrategy;
        this.cleanInRealTime = builder.cleanInRealTime;
        this.flattener = builder.flattener;
        this.writer = builder.writer;
        checkLogFolder();
    }

    private void checkLogFolder() {
        File file = new File(this.folderPath);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    @Override // com.elvishew.xlog.printer.Printer
    public void println(int i, String str, String str2) throws InterruptedException {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (!this.worker.isStarted()) {
            this.worker.start();
        }
        this.worker.enqueue(new LogItem(jCurrentTimeMillis, i, str, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doPrintln(long j, int i, String str, String str2) {
        String openedFileName = this.writer.getOpenedFileName();
        boolean zIsOpened = this.writer.isOpened();
        if (openedFileName == null || !zIsOpened || this.fileNameGenerator.isFileNameChangeable()) {
            String strGenerateFileName = this.fileNameGenerator.generateFileName(i, System.currentTimeMillis());
            if (strGenerateFileName == null || strGenerateFileName.trim().length() == 0) {
                Platform.get().error("File name should not be empty, ignore log: " + str2);
                return;
            } else if (!strGenerateFileName.equals(openedFileName) || !zIsOpened) {
                this.writer.close();
                cleanLogFilesIfNecessary(null);
                if (!this.writer.open(new File(this.folderPath, strGenerateFileName))) {
                    return;
                } else {
                    openedFileName = strGenerateFileName;
                }
            }
        }
        File openedFile = this.writer.getOpenedFile();
        if (this.backupStrategy.shouldBackup(openedFile)) {
            this.writer.close();
            BackupUtil.backup(openedFile, this.backupStrategy);
            if (!this.writer.open(new File(this.folderPath, openedFileName))) {
                return;
            }
        }
        this.writer.appendLog(this.flattener.flatten(j, i, str, str2).toString());
    }

    private void cleanLogFilesIfNecessary(String str) {
        File[] fileArrListFiles = new File(this.folderPath).listFiles();
        if (fileArrListFiles == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            if ((str == null || !str.equals(file.getName())) && this.cleanStrategy.shouldClean(file)) {
                file.delete();
            }
        }
    }

    public static class Builder {
        BackupStrategy2 backupStrategy;
        boolean cleanInRealTime;
        CleanStrategy cleanStrategy;
        FileNameGenerator fileNameGenerator;
        Flattener2 flattener;
        String folderPath;
        Writer writer;

        public Builder(String str) {
            this.folderPath = str;
        }

        public Builder fileNameGenerator(FileNameGenerator fileNameGenerator) {
            this.fileNameGenerator = fileNameGenerator;
            return this;
        }

        public Builder backupStrategy(BackupStrategy backupStrategy) {
            if (!(backupStrategy instanceof BackupStrategy2)) {
                backupStrategy = new BackupStrategyWrapper(backupStrategy);
            }
            BackupStrategy2 backupStrategy2 = (BackupStrategy2) backupStrategy;
            this.backupStrategy = backupStrategy2;
            BackupUtil.verifyBackupStrategy(backupStrategy2);
            return this;
        }

        public Builder cleanStrategy(CleanStrategy cleanStrategy) {
            this.cleanStrategy = cleanStrategy;
            return this;
        }

        public Builder cleanStrategy(CleanStrategy cleanStrategy, boolean z) {
            this.cleanStrategy = cleanStrategy;
            this.cleanInRealTime = z;
            return this;
        }

        @Deprecated
        public Builder logFlattener(final Flattener flattener) {
            return flattener(new Flattener2() { // from class: com.elvishew.xlog.printer.file.FilePrinter.Builder.1
                @Override // com.elvishew.xlog.flattener.Flattener2
                public CharSequence flatten(long j, int i, String str, String str2) {
                    return flattener.flatten(i, str, str2);
                }
            });
        }

        public Builder flattener(Flattener2 flattener2) {
            this.flattener = flattener2;
            return this;
        }

        public Builder writer(Writer writer) {
            this.writer = writer;
            return this;
        }

        public FilePrinter build() {
            fillEmptyFields();
            return new FilePrinter(this);
        }

        private void fillEmptyFields() {
            if (this.fileNameGenerator == null) {
                this.fileNameGenerator = DefaultsFactory.createFileNameGenerator();
            }
            if (this.backupStrategy == null) {
                this.backupStrategy = DefaultsFactory.createBackupStrategy();
            }
            if (this.cleanStrategy == null) {
                this.cleanStrategy = DefaultsFactory.createCleanStrategy();
            }
            if (this.flattener == null) {
                this.flattener = DefaultsFactory.createFlattener2();
            }
            if (this.writer == null) {
                this.writer = DefaultsFactory.createWriter();
            }
        }
    }

    private static class LogItem {
        int level;
        String msg;
        String tag;
        long timeMillis;

        LogItem(long j, int i, String str, String str2) {
            this.timeMillis = j;
            this.level = i;
            this.tag = str;
            this.msg = str2;
        }
    }

    private class Worker implements Runnable {
        private BlockingQueue<LogItem> logs;
        private volatile boolean started;

        private Worker() {
            this.logs = new LinkedBlockingQueue();
        }

        void enqueue(LogItem logItem) throws InterruptedException {
            try {
                this.logs.put(logItem);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        boolean isStarted() {
            boolean z;
            synchronized (this) {
                z = this.started;
            }
            return z;
        }

        void start() {
            synchronized (this) {
                if (this.started) {
                    return;
                }
                new Thread(this).start();
                this.started = true;
            }
        }

        @Override // java.lang.Runnable
        public void run() throws InterruptedException {
            while (true) {
                try {
                    LogItem logItemTake = this.logs.take();
                    if (logItemTake == null) {
                        return;
                    } else {
                        FilePrinter.this.doPrintln(logItemTake.timeMillis, logItemTake.level, logItemTake.tag, logItemTake.msg);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    synchronized (this) {
                        this.started = false;
                        return;
                    }
                }
            }
        }
    }
}
