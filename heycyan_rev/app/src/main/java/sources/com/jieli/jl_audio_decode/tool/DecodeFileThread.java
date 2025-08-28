package com.jieli.jl_audio_decode.tool;

import android.text.TextUtils;
import com.jieli.jl_audio_decode.callback.OnStateCallback;
import com.jieli.jl_audio_decode.callback.OnThreadFinishListener;
import com.jieli.jl_audio_decode.constant.ErrorCode;
import com.jieli.jl_audio_decode.opus.model.OpusOption;
import java.io.File;

/* loaded from: classes2.dex */
public class DecodeFileThread extends OperationThread {
    private final String inFile;
    private final OpusOption opusOption;
    private final String outFile;

    @Override // com.jieli.jl_audio_decode.tool.OperationThread
    public int operation() {
        return 1;
    }

    public DecodeFileThread(BaseManager baseManager, String str, String str2, OpusOption opusOption, OnStateCallback onStateCallback, OnThreadFinishListener onThreadFinishListener) {
        super("DecodeFileThread", baseManager, onStateCallback, onThreadFinishListener);
        this.inFile = str;
        this.outFile = str2;
        this.opusOption = opusOption;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        if (this.manager != null) {
            File file = new File(this.inFile);
            if (!TextUtils.isEmpty(this.inFile) && !TextUtils.isEmpty(this.outFile) && file.exists() && file.isFile()) {
                int iNativeDecodeFile = this.manager.nativeDecodeFile(this.inFile, this.outFile, this.opusOption);
                if (iNativeDecodeFile != 0) {
                    callbackError(iNativeDecodeFile);
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
