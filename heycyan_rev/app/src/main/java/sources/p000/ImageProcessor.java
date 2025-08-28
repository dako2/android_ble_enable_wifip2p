package p000;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.glasssutdio.wear.all.pref.UserConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p014io.CloseableKt;
import kotlin.ranges.RangesKt;

/* compiled from: ImageProcessor.kt */
@Metadata(m606d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\rH\u0002J\u0018\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0004H\u0002J\u0012\u0010\u0014\u001a\u00020\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\rH\u0002J\u0010\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\rH\u0002J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ \u0010\u001c\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u0004H\u0002J\u0018\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010\"\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u000bH\u0002J\u0018\u0010#\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006$"}, m607d2 = {"LImageProcessor;", "", "()V", "INIT_QUALITY", "", "MAX_LONG_SIDE", "MIN_QUALITY", "TARGET_MAX_SIZE_KB", "adaptiveQualityCompress", "", "bitmap", "Landroid/graphics/Bitmap;", "dest", "Ljava/io/File;", "atomicReplace", "temp", TypedValues.AttributesType.S_TARGET, "calculateInSampleSize", "width", "height", "createTempFile", "parentDir", "decodeWithMemoryControl", "file", "processAndReplace", "", "filePath", "", "saveBitmap", "quality", "scaleBitmap", "src", "scale", "", "scaleToMaxSize", "smartCompress", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class ImageProcessor {
    private static final int INIT_QUALITY = 95;
    public static final ImageProcessor INSTANCE = new ImageProcessor();
    private static final int MAX_LONG_SIDE = 7584;
    private static final int MIN_QUALITY = 40;
    private static final int TARGET_MAX_SIZE_KB = 2048;

    private ImageProcessor() {
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0072: MOVE (r8 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:31:0x0072 */
    public final boolean processAndReplace(String filePath) throws Throwable {
        Bitmap bitmap;
        File fileCreateTempFile;
        Bitmap bitmapScaleBitmap;
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        }
        Bitmap bitmap2 = null;
        try {
        } catch (Throwable th) {
            th = th;
            bitmap2 = bitmap;
        }
        try {
            try {
                fileCreateTempFile = createTempFile(file.getParentFile());
                fileCreateTempFile.deleteOnExit();
                try {
                    Bitmap bitmapDecodeWithMemoryControl = decodeWithMemoryControl(file);
                    if (bitmapDecodeWithMemoryControl.getWidth() > 1920 || bitmapDecodeWithMemoryControl.getHeight() > 1080) {
                        UserConfig.INSTANCE.getInstance().setCamera8Mp(true);
                    } else {
                        UserConfig.INSTANCE.getInstance().setCamera8Mp(false);
                    }
                    bitmapScaleBitmap = scaleBitmap(bitmapDecodeWithMemoryControl, 2.0f);
                    bitmapDecodeWithMemoryControl.recycle();
                } catch (Exception unused) {
                    bitmapScaleBitmap = null;
                }
            } catch (Exception unused2) {
                fileCreateTempFile = null;
                bitmapScaleBitmap = null;
            }
            try {
                smartCompress(bitmapScaleBitmap, fileCreateTempFile);
                atomicReplace(fileCreateTempFile, file);
                if (bitmapScaleBitmap != null) {
                    bitmapScaleBitmap.recycle();
                }
                return true;
            } catch (Exception unused3) {
                if (fileCreateTempFile != null) {
                    File file2 = fileCreateTempFile.exists() ? fileCreateTempFile : null;
                    if (file2 != null) {
                        file2.delete();
                    }
                }
                if (bitmapScaleBitmap != null) {
                    bitmapScaleBitmap.recycle();
                }
                return false;
            }
        } catch (Throwable th2) {
            th = th2;
            if (bitmap2 != null) {
                bitmap2.recycle();
            }
            throw th;
        }
    }

    private final void smartCompress(Bitmap bitmap, File dest) {
        Bitmap bitmapScaleToMaxSize = scaleToMaxSize(bitmap);
        adaptiveQualityCompress(bitmapScaleToMaxSize, dest);
        bitmapScaleToMaxSize.recycle();
    }

    private final Bitmap scaleToMaxSize(Bitmap src) {
        int width;
        if (src.getHeight() > src.getWidth()) {
            width = MAX_LONG_SIDE / src.getHeight();
        } else {
            width = MAX_LONG_SIDE / src.getWidth();
        }
        int iCoerceAtMost = RangesKt.coerceAtMost(width, 1);
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(src, src.getWidth() * iCoerceAtMost, src.getHeight() * iCoerceAtMost, true);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateScaledBitmap, "createScaledBitmap(...)");
        return bitmapCreateScaledBitmap;
    }

    private final void adaptiveQualityCompress(Bitmap bitmap, File dest) {
        long length = Long.MAX_VALUE;
        for (int i = 95; length > 2097152 && i >= 40; i -= 5) {
            saveBitmap(bitmap, dest, i);
            length = dest.length();
        }
        if (length > 2097152) {
            saveBitmap(bitmap, dest, 40);
        }
    }

    private final Bitmap decodeWithMemoryControl(File file) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        options.inSampleSize = INSTANCE.calculateInSampleSize(options.outWidth, options.outHeight);
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        if (bitmapDecodeFile != null) {
            return bitmapDecodeFile;
        }
        throw new IOException("Decode failed");
    }

    private final int calculateInSampleSize(int width, int height) {
        int i = 1;
        while ((width * height) / (i * i) > ((int) (Runtime.getRuntime().maxMemory() / 4))) {
            i *= 2;
        }
        return i;
    }

    private final Bitmap scaleBitmap(Bitmap src, float scale) {
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(src, (int) (src.getWidth() * scale), (int) (src.getHeight() * scale), true);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateScaledBitmap, "createScaledBitmap(...)");
        return bitmapCreateScaledBitmap;
    }

    private final void saveBitmap(Bitmap bitmap, File dest, int quality) {
        FileOutputStream fileOutputStream = new FileOutputStream(dest);
        try {
            FileOutputStream fileOutputStream2 = fileOutputStream;
            if (!bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream2)) {
                throw new IOException("Compress failed");
            }
            fileOutputStream2.getFD().sync();
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(fileOutputStream, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(fileOutputStream, th);
                throw th2;
            }
        }
    }

    private final void atomicReplace(File temp, File target) throws InterruptedException, IOException {
        for (int i = 0; i < 3; i++) {
            try {
            } catch (SecurityException e) {
                if (i == 2) {
                    throw e;
                }
                Thread.sleep(100L);
            }
            if (target.delete() && temp.renameTo(target)) {
                return;
            }
        }
        throw new IOException("File replace failed");
    }

    private final File createTempFile(File parentDir) throws IOException {
        File fileCreateTempFile = File.createTempFile("tmp_" + System.currentTimeMillis(), ".jpg", parentDir);
        Intrinsics.checkNotNullExpressionValue(fileCreateTempFile, "createTempFile(...)");
        return fileCreateTempFile;
    }
}
