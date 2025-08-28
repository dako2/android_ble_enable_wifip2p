package org.tensorflow.lite.support.common;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;

/* loaded from: classes3.dex */
public class FileUtil {
    private FileUtil() {
    }

    @NonNull
    public static List<String> loadLabels(@NonNull Context context, @NonNull String str) throws IOException {
        return loadLabels(context, str, Charset.defaultCharset());
    }

    @NonNull
    public static List<String> loadLabels(@NonNull Context context, @NonNull String str, Charset charset) throws IOException {
        SupportPreconditions.checkNotNull(context, "Context cannot be null.");
        SupportPreconditions.checkNotNull(str, "File path cannot be null.");
        InputStream inputStreamOpen = context.getAssets().open(str);
        try {
            List<String> listLoadLabels = loadLabels(inputStreamOpen, charset);
            if (inputStreamOpen != null) {
                inputStreamOpen.close();
            }
            return listLoadLabels;
        } catch (Throwable th) {
            if (inputStreamOpen != null) {
                try {
                    inputStreamOpen.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @NonNull
    public static List<String> loadLabels(@NonNull InputStream inputStream) throws IOException {
        return loadLabels(inputStream, Charset.defaultCharset());
    }

    @NonNull
    public static List<String> loadLabels(@NonNull InputStream inputStream, Charset charset) throws IOException {
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset));
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line != null) {
                    if (line.trim().length() > 0) {
                        arrayList.add(line);
                    }
                } else {
                    bufferedReader.close();
                    return arrayList;
                }
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    @NonNull
    public static List<String> loadSingleColumnTextFile(@NonNull Context context, @NonNull String str, Charset charset) throws IOException {
        return loadLabels(context, str, charset);
    }

    @NonNull
    public static List<String> loadSingleColumnTextFile(@NonNull InputStream inputStream, Charset charset) throws IOException {
        return loadLabels(inputStream, charset);
    }

    @NonNull
    public static MappedByteBuffer loadMappedFile(@NonNull Context context, @NonNull String str) throws IOException {
        SupportPreconditions.checkNotNull(context, "Context should not be null.");
        SupportPreconditions.checkNotNull(str, "File path cannot be null.");
        AssetFileDescriptor assetFileDescriptorOpenFd = context.getAssets().openFd(str);
        try {
            FileInputStream fileInputStream = new FileInputStream(assetFileDescriptorOpenFd.getFileDescriptor());
            try {
                MappedByteBuffer map = fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getDeclaredLength());
                fileInputStream.close();
                if (assetFileDescriptorOpenFd != null) {
                    assetFileDescriptorOpenFd.close();
                }
                return map;
            } finally {
            }
        } catch (Throwable th) {
            if (assetFileDescriptorOpenFd != null) {
                try {
                    assetFileDescriptorOpenFd.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @NonNull
    public static byte[] loadByteFromFile(@NonNull Context context, @NonNull String str) throws IOException {
        MappedByteBuffer mappedByteBufferLoadMappedFile = loadMappedFile(context, str);
        byte[] bArr = new byte[mappedByteBufferLoadMappedFile.remaining()];
        mappedByteBufferLoadMappedFile.get(bArr);
        return bArr;
    }
}
