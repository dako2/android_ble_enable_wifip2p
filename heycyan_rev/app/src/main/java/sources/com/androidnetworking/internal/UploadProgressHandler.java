package com.androidnetworking.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.androidnetworking.model.Progress;

/* loaded from: classes.dex */
public class UploadProgressHandler extends Handler {
    private final UploadProgressListener mUploadProgressListener;

    public UploadProgressHandler(UploadProgressListener uploadProgressListener) {
        super(Looper.getMainLooper());
        this.mUploadProgressListener = uploadProgressListener;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what == 1) {
            if (this.mUploadProgressListener != null) {
                Progress progress = (Progress) message.obj;
                this.mUploadProgressListener.onProgress(progress.currentBytes, progress.totalBytes);
                return;
            }
            return;
        }
        super.handleMessage(message);
    }
}
