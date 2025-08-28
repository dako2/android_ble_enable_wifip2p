package com.jieli.jl_audio_decode.tool;

import android.text.TextUtils;
import com.jieli.jl_audio_decode.callback.OnStateCallback;
import com.jieli.jl_audio_decode.callback.OnThreadFinishListener;
import com.jieli.jl_audio_decode.constant.ErrorCode;
import java.io.File;

/* loaded from: classes2.dex */
public class EncodeFileThread extends OperationThread {
    private final String inFile;
    private final String outFile;

    @Override // com.jieli.jl_audio_decode.tool.OperationThread
    public int operation() {
        return 2;
    }

    public EncodeFileThread(BaseManager baseManager, String str, String str2, OnStateCallback onStateCallback, OnThreadFinishListener onThreadFinishListener) {
        super("EncodeFileThread", baseManager, onStateCallback, onThreadFinishListener);
        this.inFile = str;
        this.outFile = str2;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        if (this.manager != null) {
            File file = new File(this.inFile);
            if (!TextUtils.isEmpty(this.inFile) && !TextUtils.isEmpty(this.outFile) && file.exists() && file.isFile()) {
                int iNativeEncodeFile = this.manager.nativeEncodeFile(this.inFile, this.outFile);
                if (iNativeEncodeFile != 0) {
                    callbackError(iNativeEncodeFile);
                }
            } else {
                callbackError(ErrorCode.ERR_FILE_NOT_EXIST);
            }
        } else {
            callbackError(-1);
        }
        onThreadFinish();
    }
}
