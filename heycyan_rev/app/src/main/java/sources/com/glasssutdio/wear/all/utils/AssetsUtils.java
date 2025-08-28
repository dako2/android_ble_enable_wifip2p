package com.glasssutdio.wear.all.utils;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p014io.ByteStreamsKt;
import kotlin.p014io.CloseableKt;

/* compiled from: AssetsUtils.kt */
@Metadata(m606d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m607d2 = {"Lcom/glasssutdio/wear/all/utils/AssetsUtils;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "cacheDir", "Ljava/io/File;", "getTmpFile", "fileName", "", "useAssets", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AssetsUtils {
    private final File cacheDir;
    private final Context context;

    public AssetsUtils(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.cacheDir = new File(context.getCacheDir(), "tmp");
    }

    private final File getTmpFile(String fileName) {
        if (!this.cacheDir.exists()) {
            this.cacheDir.mkdirs();
        }
        File file = new File(this.cacheDir, fileName);
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        return file;
    }

    public final File useAssets(String fileName) throws IOException {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        File tmpFile = getTmpFile(fileName);
        if (tmpFile.exists() && tmpFile.length() > 0) {
            return tmpFile;
        }
        if (!tmpFile.exists()) {
            tmpFile.createNewFile();
        }
        FileOutputStream fileOutputStreamOpen = this.context.getAssets().open(fileName);
        try {
            InputStream inputStream = fileOutputStreamOpen;
            fileOutputStreamOpen = new FileOutputStream(tmpFile);
            try {
                Intrinsics.checkNotNull(inputStream);
                ByteStreamsKt.copyTo$default(inputStream, fileOutputStreamOpen, 0, 2, null);
                CloseableKt.closeFinally(fileOutputStreamOpen, null);
                CloseableKt.closeFinally(fileOutputStreamOpen, null);
                return tmpFile;
            } finally {
            }
        } finally {
        }
    }
}
