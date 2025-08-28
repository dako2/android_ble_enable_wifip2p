package com.glasssutdio.wear.all.utils.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import com.elvishew.xlog.XLog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p014io.ByteStreamsKt;
import kotlin.p014io.CloseableKt;
import kotlin.ranges.RangesKt;

/* compiled from: ImageUtil.kt */
@Metadata(m606d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0019¨\u0006\u001a"}, m607d2 = {"Lcom/glasssutdio/wear/all/utils/image/ImageUtils;", "", "()V", "combineBitmaps", "Landroid/graphics/Bitmap;", "topBitmap", "bottomBitmap", "getBitmapFromImageView", "imageView", "Landroid/widget/ImageView;", "getBitmapFromPath", "filePath", "", "saveBitmapToFile", "", "bitmap", "file", "Ljava/io/File;", "saveUriToExternalPrivateStorage", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "viewToBitmap", "view", "Landroid/view/View;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class ImageUtils {
    public static final ImageUtils INSTANCE = new ImageUtils();

    private ImageUtils() {
    }

    public final Bitmap getBitmapFromPath(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        File file = new File(filePath);
        if (file.exists()) {
            return BitmapFactory.decodeFile(file.getAbsolutePath());
        }
        return null;
    }

    public final Bitmap getBitmapFromImageView(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        Drawable drawable = imageView.getDrawable();
        if (drawable == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
        XLog.m137i("原图宽度：" + drawable.getIntrinsicWidth());
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    public final Bitmap viewToBitmap(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (view.getWidth() <= 0 || view.getHeight() <= 0) {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
        XLog.m137i("底图宽度：" + view.getWidth());
        view.draw(new Canvas(bitmapCreateBitmap));
        return bitmapCreateBitmap;
    }

    public final Bitmap combineBitmaps(Bitmap topBitmap, Bitmap bottomBitmap) {
        Intrinsics.checkNotNullParameter(topBitmap, "topBitmap");
        Intrinsics.checkNotNullParameter(bottomBitmap, "bottomBitmap");
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(RangesKt.coerceAtLeast(topBitmap.getWidth(), bottomBitmap.getWidth()), topBitmap.getHeight() + bottomBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(topBitmap, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(bottomBitmap, 0.0f, topBitmap.getHeight(), (Paint) null);
        return bitmapCreateBitmap;
    }

    public final boolean saveBitmapToFile(Bitmap bitmap, File file) throws IOException {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(file, "file");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final String saveUriToExternalPrivateStorage(Context context, Uri uri) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        try {
            InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
            if (inputStreamOpenInputStream == null) {
                return null;
            }
            File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "img_" + UUID.randomUUID() + ".jpg");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                ByteStreamsKt.copyTo$default(inputStreamOpenInputStream, fileOutputStream, 0, 2, null);
                CloseableKt.closeFinally(fileOutputStream, null);
                return file.getAbsolutePath();
            } finally {
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
