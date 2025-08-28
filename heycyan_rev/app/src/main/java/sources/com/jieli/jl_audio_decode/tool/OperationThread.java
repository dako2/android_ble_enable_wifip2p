package com.jieli.jl_audio_decode.tool;

import android.os.Handler;
import android.os.Looper;
import com.jieli.jl_audio_decode.callback.OnStateCallback;
import com.jieli.jl_audio_decode.callback.OnThreadFinishListener;
import com.jieli.jl_audio_decode.constant.ErrorCode;

/* loaded from: classes2.dex */
abstract class OperationThread extends Thread {
    public static final int OP_ADD_STREAM_DATA = 3;
    public static final int OP_DECODE_FILE = 1;
    public static final int OP_ENCODE_FILE = 2;
    protected final OnStateCallback callback;
    protected final OnThreadFinishListener listener;
    protected final BaseManager manager;
    protected final Handler uiHandler;

    public abstract int operation();

    public OperationThread(BaseManager baseManager, OnStateCallback onStateCallback, OnThreadFinishListener onThreadFinishListener) {
        this(null, baseManager, onStateCallback, onThreadFinishListener);
    }

    public OperationThread(String str, BaseManager baseManager, OnStateCallback onStateCallback, OnThreadFinishListener onThreadFinishListener) {
        super(str);
        this.uiHandler = new Handler(Looper.getMainLooper());
        this.manager = baseManager;
        this.callback = onStateCallback;
        this.listener = onThreadFinishListener;
    }

    protected void onThreadFinish() {
        final OnThreadFinishListener onThreadFinishListener = this.listener;
        if (onThreadFinishListener == null) {
            return;
        }
        this.uiHandler.post(new Runnable() { // from class: com.jieli.jl_audio_decode.tool.OperationThread$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m582x3af4292a(onThreadFinishListener);
            }
        });
    }

    /* renamed from: lambda$onThreadFinish$0$com-jieli-jl_audio_decode-tool-OperationThread */
    /* synthetic */ void m582x3af4292a(OnThreadFinishListener onThreadFinishListener) {
        onThreadFinishListener.onThreadFinish(getId());
    }

    protected void callbackError(int i) {
        callbackError(i, ErrorCode.getErrorMsg(i));
    }

    protected void callbackError(final int i, final String str) {
        final OnStateCallback onStateCallback = this.callback;
        if (onStateCallback != null) {
            this.uiHandler.post(new Runnable() { // from class: com.jieli.jl_audio_decode.tool.OperationThread$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    onStateCallback.onError(i, str);
                }
            });
        }
    }
}
