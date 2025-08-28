package com.jieli.jl_audio_decode.constant;

import java.util.Locale;

/* loaded from: classes2.dex */
public class ErrorCode {
    public static final int ERR_ALLOC_FAIL = -7;
    public static final int ERR_BAD_ARGS = -1;
    public static final int ERR_BUFFER_TOO_SMALL = -2;
    public static final int ERR_ENC_FAILURE = -12;
    public static final int ERR_FILE_NOT_EXIST = -1002;
    public static final int ERR_INTERNAL_ERROR = -3;
    public static final int ERR_INVALID_PACKET = -4;
    public static final int ERR_INVALID_STATE = -6;
    public static final int ERR_IN_PROCESS = -1003;
    public static final int ERR_IO_EXCEPTION = -2000;
    public static final int ERR_NONE = 0;
    public static final int ERR_NONE_INIT = -1000;
    public static final int ERR_NOT_SUPPORT_FUNCTION = -1001;
    public static final int ERR_OP_FAIL = -1004;
    public static final int ERR_OUTPUT_EXCEPTION = -11;
    public static final int ERR_UNIMPLEMENTED = -5;

    public static String getErrorMsg(int i) {
        if (i == -2000) {
            return "IO exception";
        }
        if (i == -12) {
            return "Encoding failed";
        }
        if (i == -11) {
            return "Abnormal output data";
        }
        switch (i) {
            case ERR_OP_FAIL /* -1004 */:
                return "Operation Failed";
            case ERR_IN_PROCESS /* -1003 */:
                return "Operation in progress";
            case ERR_FILE_NOT_EXIST /* -1002 */:
                return "File does not exist";
            case ERR_NOT_SUPPORT_FUNCTION /* -1001 */:
                return "Function not supported";
            case -1000:
                return "Not initialized";
            default:
                switch (i) {
                    case -7:
                        return "Memory allocation failed";
                    case -6:
                        return "Invalid state";
                    case -5:
                        return "Request not implemented";
                    case -4:
                        return "Corrupted stream";
                    case -3:
                        return "Internal error";
                    case -2:
                        return "Buffer too small";
                    case -1:
                        return "Invalid Argument";
                    case 0:
                        return "Success";
                    default:
                        return String.format(Locale.ENGLISH, "Unknown Error(%d)", Integer.valueOf(i));
                }
        }
    }
}
