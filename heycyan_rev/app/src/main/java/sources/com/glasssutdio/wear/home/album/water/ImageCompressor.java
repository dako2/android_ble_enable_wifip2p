package com.glasssutdio.wear.home.album.water;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.exifinterface.media.ExifInterface;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.all.pref.UserConfig;
import java.io.File;
import java.io.FileOutputStream;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p014io.CloseableKt;
import kotlin.ranges.RangesKt;

/* compiled from: ImageCompressor.kt */
@Metadata(m606d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0003\u001c\u001d\u001eB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\f2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006H\u0002J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\tJ\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\tH\u0002J\b\u0010\u0016\u001a\u00020\u0010H\u0002J\f\u0010\u0017\u001a\u00020\u0018*\u00020\u0010H\u0002J\u0014\u0010\u0019\u001a\u00020\u0010*\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u0010H\u0002J\f\u0010\u001b\u001a\u00020\u0012*\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m607d2 = {"Lcom/glasssutdio/wear/home/album/water/ImageCompressor;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "maxHeight", "", "maxWidth", "outputDirName", "", "quality", "calculateTargetSize", "Lkotlin/Pair;", "originalWidth", "originalHeight", "compressBitmap", "Ljava/io/File;", "sourceBitmap", "Landroid/graphics/Bitmap;", "distName", "createOutputFile", "name", "getOutputDir", "clearExifData", "", "compressToFile", "outputFile", "scaleToMaxSize", "Builder", "Companion", "CompressCallback", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class ImageCompressor {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String DEFAULT_DIR_NAME = "Compressed";
    private static final int DEFAULT_MAX_SIZE = 1080;
    private static final int DEFAULT_QUALITY = 85;
    private final Context context;
    private int maxHeight;
    private int maxWidth;
    private String outputDirName;
    private int quality;

    /* compiled from: ImageCompressor.kt */
    @Metadata(m606d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, m607d2 = {"Lcom/glasssutdio/wear/home/album/water/ImageCompressor$CompressCallback;", "", "onError", "", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "compressedFile", "Ljava/io/File;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public interface CompressCallback {
        void onError(Exception exception);

        void onSuccess(File compressedFile);
    }

    public /* synthetic */ ImageCompressor(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    private ImageCompressor(Context context) {
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        this.context = applicationContext;
        this.maxWidth = DEFAULT_MAX_SIZE;
        this.maxHeight = DEFAULT_MAX_SIZE;
        this.quality = 85;
        this.outputDirName = DEFAULT_DIR_NAME;
    }

    /* compiled from: ImageCompressor.kt */
    @Metadata(m606d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, m607d2 = {"Lcom/glasssutdio/wear/home/album/water/ImageCompressor$Companion;", "", "()V", "DEFAULT_DIR_NAME", "", "DEFAULT_MAX_SIZE", "", "DEFAULT_QUALITY", "with", "Lcom/glasssutdio/wear/home/album/water/ImageCompressor$Builder;", "context", "Landroid/content/Context;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Builder with(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new Builder(context);
        }
    }

    /* compiled from: ImageCompressor.kt */
    @Metadata(m606d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006J\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\tJ\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m607d2 = {"Lcom/glasssutdio/wear/home/album/water/ImageCompressor$Builder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "maxHeight", "", "maxWidth", "outputDirName", "", "quality", "build", "Lcom/glasssutdio/wear/home/album/water/ImageCompressor;", "setMaxSize", "width", "height", "setOutputDir", "name", "setQuality", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Builder {
        private final Context context;
        private int maxHeight;
        private int maxWidth;
        private String outputDirName;
        private int quality;

        public Builder(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
            this.maxWidth = ImageCompressor.DEFAULT_MAX_SIZE;
            this.maxHeight = ImageCompressor.DEFAULT_MAX_SIZE;
            this.quality = 85;
            this.outputDirName = ImageCompressor.DEFAULT_DIR_NAME;
        }

        public final Builder setMaxSize(int width, int height) {
            if (!UserConfig.INSTANCE.getInstance().getCamera8Mp()) {
                this.maxWidth = 5184;
                this.maxHeight = 4018;
            } else {
                this.maxWidth = 6560;
                this.maxHeight = 5058;
            }
            return this;
        }

        public final Builder setQuality(int quality) {
            this.quality = RangesKt.coerceIn(quality, 1, 100);
            return this;
        }

        public final Builder setOutputDir(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.outputDirName = name;
            return this;
        }

        public final ImageCompressor build() {
            ImageCompressor imageCompressor = new ImageCompressor(this.context, null);
            imageCompressor.maxWidth = this.maxWidth;
            imageCompressor.maxHeight = this.maxHeight;
            imageCompressor.quality = this.quality;
            imageCompressor.outputDirName = this.outputDirName;
            return imageCompressor;
        }
    }

    public final File compressBitmap(Bitmap sourceBitmap, String distName) throws Exception {
        Intrinsics.checkNotNullParameter(sourceBitmap, "sourceBitmap");
        Intrinsics.checkNotNullParameter(distName, "distName");
        File fileCompressToFile = compressToFile(scaleToMaxSize(sourceBitmap), createOutputFile(distName));
        clearExifData(fileCompressToFile);
        return fileCompressToFile;
    }

    private final Bitmap scaleToMaxSize(Bitmap bitmap) {
        Pair<Integer, Integer> pairCalculateTargetSize = calculateTargetSize(bitmap.getWidth(), bitmap.getHeight());
        int iIntValue = pairCalculateTargetSize.component1().intValue();
        int iIntValue2 = pairCalculateTargetSize.component2().intValue();
        if (bitmap.getWidth() == iIntValue && bitmap.getHeight() == iIntValue2) {
            return bitmap;
        }
        float fMin = Math.min(iIntValue / bitmap.getWidth(), iIntValue2 / bitmap.getHeight());
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * fMin), (int) (bitmap.getHeight() * fMin), true);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateScaledBitmap, "createScaledBitmap(...)");
        if (!Intrinsics.areEqual(bitmapCreateScaledBitmap, bitmap)) {
            bitmap.recycle();
        }
        return bitmapCreateScaledBitmap;
    }

    private final File createOutputFile(String name) {
        XLog.m137i(getOutputDir().getAbsolutePath());
        XLog.m137i(name);
        File file = new File(getOutputDir(), name);
        if (file.createNewFile()) {
            return file;
        }
        throw new IllegalStateException("Failed to create output file");
    }

    private final File compressToFile(Bitmap bitmap, File file) {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            FileOutputStream fileOutputStream2 = fileOutputStream;
            if (!bitmap.compress(Bitmap.CompressFormat.JPEG, this.quality, fileOutputStream2)) {
                throw new IllegalStateException("Bitmap compression failed");
            }
            fileOutputStream2.flush();
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(fileOutputStream, null);
            bitmap.recycle();
            return file;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(fileOutputStream, th);
                throw th2;
            }
        }
    }

    private final void clearExifData(File file) throws Throwable {
        ExifInterface exifInterface = new ExifInterface(file);
        exifInterface.setAttribute(ExifInterface.TAG_GPS_LATITUDE, null);
        exifInterface.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, null);
        exifInterface.saveAttributes();
    }

    private final Pair<Integer, Integer> calculateTargetSize(int originalWidth, int originalHeight) {
        float f = originalWidth / originalHeight;
        if (originalWidth > originalHeight) {
            int i = this.maxWidth;
            return TuplesKt.m614to(Integer.valueOf(i), Integer.valueOf(RangesKt.coerceAtMost((int) (i / f), this.maxHeight)));
        }
        return TuplesKt.m614to(Integer.valueOf(RangesKt.coerceAtMost((int) (this.maxHeight * f), this.maxWidth)), Integer.valueOf(this.maxHeight));
    }

    private final File getOutputDir() {
        File file = new File(this.outputDirName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
}
