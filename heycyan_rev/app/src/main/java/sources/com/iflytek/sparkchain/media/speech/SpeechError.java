package com.iflytek.sparkchain.media.speech;

import com.iflytek.sparkchain.utils.constants.ErrorCode;
import com.iflytek.sparkchain.utils.log.LogUtil;
import iflb.C2438a;

/* loaded from: classes2.dex */
public class SpeechError extends Exception {
    protected static final int TIP_ENGINE_NOT_INSTALLED = 29;
    public static final int TIP_ERROR_ALREADY_EXIST = 66;
    protected static final int TIP_ERROR_AUDIO_RECORD = 9;
    public static final int TIP_ERROR_AUTHID_NOT_AVAILABLE = 71;
    protected static final int TIP_ERROR_BROWSER_NOT_INSTALLED = 26;
    protected static final int TIP_ERROR_CLIENT = 8;
    protected static final int TIP_ERROR_EMPTY_UTTERANCE = 13;
    protected static final int TIP_ERROR_ENGINE_INIT_FAIL = 28;
    protected static final int TIP_ERROR_EXIST_UNLISTED_WORD = 42;
    protected static final int TIP_ERROR_FACE_IMAGE_FULL_LEFT = 44;
    protected static final int TIP_ERROR_FACE_IMAGE_FULL_RIGHT = 45;
    protected static final int TIP_ERROR_FACE_INVALID_MODEL = 51;
    protected static final int TIP_ERROR_FACE_OCCULTATION = 50;
    protected static final int TIP_ERROR_FILE_ACCESS = 14;
    protected static final int TIP_ERROR_FUSION_ENOUGH_DATA = 54;
    protected static final int TIP_ERROR_FUSION_INVALID_INPUT_TYPE = 52;
    protected static final int TIP_ERROR_FUSION_NO_ENOUGH_DATA = 53;
    public static final int TIP_ERROR_GROUP_EMPTY = 68;
    protected static final int TIP_ERROR_ILLUMINATION = 49;
    protected static final int TIP_ERROR_IMAGE_CLOCKWISE_WHIRL = 46;
    protected static final int TIP_ERROR_IMAGE_COUNTET_CLOCKWISE_WHIRL = 47;
    protected static final int TIP_ERROR_INSUFFICIENT_PERMISSIONS = 4;
    protected static final int TIP_ERROR_INTERRUPT = 27;
    protected static final int TIP_ERROR_INVALID_DATA = 20;
    protected static final int TIP_ERROR_INVALID_ENCODING = 12;
    protected static final int TIP_ERROR_INVALID_GRAMMAR = 21;
    protected static final int TIP_ERROR_INVALID_LOCAL_RESOURCE = 22;
    protected static final int TIP_ERROR_INVALID_PARAM = 7;
    protected static final int TIP_ERROR_INVALID_RESULT = 5;
    protected static final int TIP_ERROR_IN_USE = 19;
    public static final int TIP_ERROR_IVP_EXTRA_RGN_SOPPORT = 56;
    public static final int TIP_ERROR_IVP_GENERAL = 55;
    public static final int TIP_ERROR_IVP_MUCH_NOISE = 58;
    public static final int TIP_ERROR_IVP_NO_ENOUGH_AUDIO = 63;
    public static final int TIP_ERROR_IVP_TEXT_NOT_MATCH = 62;
    public static final int TIP_ERROR_IVP_TOO_LOW = 59;
    public static final int TIP_ERROR_IVP_TRUNCATED = 57;
    public static final int TIP_ERROR_IVP_UTTER_TOO_SHORT = 61;
    public static final int TIP_ERROR_IVP_ZERO_AUDIO = 60;
    protected static final int TIP_ERROR_LOGIN = 18;
    protected static final int TIP_ERROR_LOGIN_INVALID_PWD = 24;
    protected static final int TIP_ERROR_LOGIN_INVALID_USER = 23;
    protected static final int TIP_ERROR_MEMORY_WRANING = 16;
    public static final int TIP_ERROR_MODEL_IS_CREATING = 65;
    public static final int TIP_ERROR_MODEL_NOT_FOUND = 64;
    protected static final int TIP_ERROR_NET_EXPECTION = 3;
    protected static final int TIP_ERROR_NOISY_OR_SHORT_AUDIO = 36;
    protected static final int TIP_ERROR_NOT_FACE_IMAGE = 43;
    protected static final int TIP_ERROR_NOT_PAPER_DATA = 37;
    protected static final int TIP_ERROR_NO_CONTENT = 34;
    public static final int TIP_ERROR_NO_GROUP = 67;
    protected static final int TIP_ERROR_NO_MATCH = 10;
    protected static final int TIP_ERROR_NO_PICTURE = 33;
    public static final int TIP_ERROR_NO_USER = 69;
    protected static final int TIP_ERROR_OTHER_DATA_EXCEPTION = 40;
    public static final int TIP_ERROR_OVERFLOW_IN_GROUP = 70;
    protected static final int TIP_ERROR_PERMISSION_DENIED = 25;
    protected static final int TIP_ERROR_PLAY_MEDIA = 15;
    protected static final int TIP_ERROR_SERVER_CONNECT = 6;
    protected static final int TIP_ERROR_SILENT_OR_LOW_VOLUME = 35;
    protected static final int TIP_ERROR_SPEECH_TIMEOUT = 11;
    protected static final int TIP_ERROR_TEXT_OVERFLOW = 17;
    protected static final int TIP_ERROR_VALID_IMAGE_SIZE = 48;
    protected static final int TIP_ERROR_WRONG_AUDIO_FORMAT = 39;
    protected static final int TIP_ERROR_WRONG_PAPER_CONTENT = 38;
    protected static final int TIP_ERROR_WRONG_PAPER_FORMAT = 41;
    protected static final int TIP_INVALID_AUTHORIZATION = 32;
    protected static final int TIP_LOCAL_ENGINE_ERROR = 30;
    protected static final int TIP_NO_NETWORK = 1;
    protected static final int TIP_RESULT_TIMEOUT = 2;
    protected static final int TIP_SCRIPT_ERROR = 31;
    private static final long serialVersionUID = 4434424251478985596L;
    private String mDescription;
    private int mErrorCode;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0117, code lost:
    
        if (r7 == 11503) goto L116;
     */
    /* JADX WARN: Removed duplicated region for block: B:128:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0080  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public SpeechError(int i) {
        this.mDescription = "";
        this.mErrorCode = i;
        int i2 = 64;
        int i3 = 7;
        int i4 = 3;
        if (i < 20001) {
            if (i == 10118) {
                i3 = 11;
            } else if (10106 == i || 10107 == i || 10124 == i) {
                LogUtil.m562d("sdk errorcode", this.mErrorCode + "");
            } else if (i == 10110) {
                i3 = 32;
            } else if (i == 10111) {
                i3 = 28;
            } else if (i >= 10200 && i < 10300) {
                i3 = 3;
            } else if (i == 10117 || i == 10101) {
                i3 = 16;
            } else if (i == 10113) {
                i3 = 17;
            } else if (i == 10116) {
                i3 = 64;
            } else if (i == 10121) {
                i3 = 66;
            } else if (i < 10400 || i > 10407) {
                if (i >= 11000 && i < 11099) {
                    i3 = i == 11005 ? 23 : i == 11006 ? 24 : 18;
                } else if (i == 10129) {
                    i3 = 19;
                } else if (i == 10109) {
                    i3 = 20;
                } else if (i == 10702) {
                    i3 = 21;
                } else if (i >= 10500 && i < 10600) {
                    i3 = 22;
                } else if (i >= 11200 && i <= 11250) {
                    i3 = 25;
                } else if ((i >= 14000 && i <= 14006) || (i >= 16000 && i <= 16006)) {
                    i3 = 31;
                } else if (11401 == i) {
                    i3 = 35;
                } else if (11402 == i) {
                    i3 = 36;
                } else if (11403 == i) {
                    i3 = 37;
                } else if (11404 == i) {
                    i3 = 38;
                } else if (11405 == i) {
                    i3 = 39;
                } else if (11406 == i) {
                    i3 = 40;
                } else if (11407 == i) {
                    i3 = 41;
                } else if (11408 == i) {
                    i3 = 42;
                } else if (i == 11501) {
                    i3 = 65;
                } else {
                    if (i != 11502) {
                    }
                    i3 = 64;
                }
            }
            i4 = i3;
        } else if (i < 30000) {
            if (i == 20001) {
                i3 = 1;
            } else if (i == 20002) {
                i3 = 2;
            } else if (i != 20003) {
                if (i == 20004) {
                    i3 = 5;
                } else if (i == 20005) {
                    i3 = 10;
                } else if (i == 20006) {
                    i3 = 9;
                } else if (i == 20007) {
                    i3 = 12;
                } else if (i != 20008) {
                    if (i == 20009) {
                        i3 = 13;
                    } else if (i == 20010) {
                        i3 = 14;
                    } else if (i != 20012) {
                        if (i != 21003) {
                            i3 = (i == 21002 || i == 21001) ? 29 : i == 26001 ? 71 : 30;
                        }
                    }
                }
            }
            i4 = i3;
        }
        int i5 = this.mErrorCode;
        if (i5 != 10031) {
            if (i5 != 11610) {
                switch (i5) {
                    case ErrorCode.MSP_ERROR_GROUP_EMPTY /* 10141 */:
                        i2 = 68;
                        break;
                    case ErrorCode.MSP_ERROR_NO_USER /* 10142 */:
                        i2 = 69;
                        break;
                    case ErrorCode.MSP_ERROR_NO_GROUP /* 10143 */:
                        i2 = 67;
                        break;
                    case ErrorCode.MSP_ERROR_OVERFLOW_IN_GROUP /* 10144 */:
                        i2 = 70;
                        break;
                    default:
                        switch (i5) {
                            case ErrorCode.MSP_ERROR_IVP_GENERAL /* 11600 */:
                                i2 = 55;
                                break;
                            case ErrorCode.MSP_ERROR_IVP_EXTRA_RGN_SOPPORT /* 11601 */:
                                i2 = 56;
                                break;
                            case ErrorCode.MSP_ERROR_IVP_TRUNCATED /* 11602 */:
                                i2 = 57;
                                break;
                            case ErrorCode.MSP_ERROR_IVP_MUCH_NOISE /* 11603 */:
                                i2 = 58;
                                break;
                            case ErrorCode.MSP_ERROR_IVP_TOO_LOW /* 11604 */:
                                i2 = 59;
                                break;
                            case ErrorCode.MSP_ERROR_IVP_ZERO_AUDIO /* 11605 */:
                                i2 = 60;
                                break;
                            case ErrorCode.MSP_ERROR_IVP_UTTER_TOO_SHORT /* 11606 */:
                                i2 = 61;
                                break;
                            case ErrorCode.MSP_ERROR_IVP_TEXT_NOT_MATCH /* 11607 */:
                                i2 = 62;
                                break;
                            case ErrorCode.MSP_ERROR_IVP_NO_ENOUGH_AUDIO /* 11608 */:
                                i2 = 63;
                                break;
                            default:
                                switch (i5) {
                                    case ErrorCode.MSP_ERROR_IFR_NOT_FACE_IMAGE /* 11700 */:
                                        i2 = 43;
                                        break;
                                    case ErrorCode.MSP_ERROR_FACE_IMAGE_FULL_LEFT /* 11701 */:
                                        i2 = 44;
                                        break;
                                    case ErrorCode.MSP_ERROR_FACE_IMAGE_FULL_RIGHT /* 11702 */:
                                        i2 = 45;
                                        break;
                                    case ErrorCode.MSP_ERROR_IMAGE_CLOCKWISE_WHIRL /* 11703 */:
                                        i2 = 46;
                                        break;
                                    case ErrorCode.MSP_ERROR_IMAGE_COUNTET_CLOCKWISE_WHIRL /* 11704 */:
                                        i2 = 47;
                                        break;
                                    case ErrorCode.MSP_ERROR_VALID_IMAGE_SIZE /* 11705 */:
                                        i2 = 48;
                                        break;
                                    case ErrorCode.MSP_ERROR_ILLUMINATION /* 11706 */:
                                        i2 = 49;
                                        break;
                                    case ErrorCode.MSP_ERROR_FACE_OCCULTATION /* 11707 */:
                                        i2 = 50;
                                        break;
                                    case ErrorCode.MSP_ERROR_FACE_INVALID_MODEL /* 11708 */:
                                        i2 = 51;
                                        break;
                                    case ErrorCode.MSP_ERROR_FUSION_INVALID_INPUT_TYPE /* 11709 */:
                                        i2 = 52;
                                        break;
                                    case ErrorCode.MSP_ERROR_FUSION_NO_ENOUGH_DATA /* 11710 */:
                                        i2 = 53;
                                        break;
                                    case ErrorCode.MSP_ERROR_FUSION_ENOUGH_DATA /* 11711 */:
                                        i2 = 54;
                                        break;
                                }
                        }
                }
            }
            this.mDescription = C2438a.m595a(i4);
        }
        i2 = 65;
        i4 = i2;
        this.mDescription = C2438a.m595a(i4);
    }

    public SpeechError(int i, String str) {
        this(i);
    }

    public SpeechError(Exception exc) {
        this.mDescription = "";
        this.mErrorCode = ErrorCode.ERROR_UNKNOWN;
        this.mDescription = exc.toString();
    }

    public SpeechError(Throwable th, int i) {
        this(i);
        initCause(th);
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public String getErrorDescription() {
        return this.mDescription;
    }

    public String getHtmlDescription(boolean z) {
        String str = this.mDescription + "...";
        if (!z) {
            return str;
        }
        return ((str + "<br>(") + C2438a.m596b(0) + ":") + this.mErrorCode + ")";
    }

    public String getPlainDescription(boolean z) {
        String str = this.mDescription;
        if (!z) {
            return str;
        }
        return ((str + ".") + "(" + C2438a.m596b(0) + ":") + this.mErrorCode + ")";
    }

    @Override // java.lang.Throwable
    public String toString() {
        return getPlainDescription(true);
    }
}
