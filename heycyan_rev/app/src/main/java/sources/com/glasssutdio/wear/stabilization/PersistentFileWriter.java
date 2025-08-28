package com.glasssutdio.wear.stabilization;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PersistentFileWriter.kt */
@Metadata(m606d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\bJ\u0006\u0010\f\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, m607d2 = {"Lcom/glasssutdio/wear/stabilization/PersistentFileWriter;", "", "file", "Ljava/io/File;", "(Ljava/io/File;)V", "outputStream", "Ljava/io/FileOutputStream;", "append", "", "byteArray", "", "close", "start", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class PersistentFileWriter {
    private final File file;
    private FileOutputStream outputStream;

    public PersistentFileWriter(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        this.file = file;
    }

    public final void start() {
        this.outputStream = new FileOutputStream(this.file, true);
    }

    public final void append(byte[] byteArray) throws IOException {
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        FileOutputStream fileOutputStream = this.outputStream;
        if (fileOutputStream != null) {
            fileOutputStream.write(byteArray);
        }
    }

    public final void close() throws IOException {
        FileOutputStream fileOutputStream = this.outputStream;
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
        this.outputStream = null;
    }
}
