package com.glasssutdio.wear.stabilization;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaUtils.kt */
@Metadata(m606d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJ\u0010\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0006J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m607d2 = {"Lcom/glasssutdio/wear/stabilization/MediaUtils;", "", "contentResolver", "Landroid/content/ContentResolver;", "(Landroid/content/ContentResolver;)V", "insertMediaFile", "Landroid/net/Uri;", "name", "", "type", "openMediaFile", "Ljava/io/FileDescriptor;", "uri", "updateUnpending", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class MediaUtils {
    private final ContentResolver contentResolver;

    public MediaUtils(ContentResolver contentResolver) {
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        this.contentResolver = contentResolver;
    }

    public final Uri insertMediaFile(String name, String type) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(type, "type");
        if (Build.VERSION.SDK_INT < 29) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", name);
        contentValues.put("mime_type", type);
        contentValues.put("is_pending", (Integer) 1);
        return this.contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
    }

    public final FileDescriptor openMediaFile(Uri uri) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(uri, "uri");
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = this.contentResolver.openFileDescriptor(uri, "rwt");
        if (parcelFileDescriptorOpenFileDescriptor != null) {
            return parcelFileDescriptorOpenFileDescriptor.getFileDescriptor();
        }
        return null;
    }

    public final void updateUnpending(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_pending", (Integer) 0);
        this.contentResolver.update(uri, contentValues, null, null);
    }
}
