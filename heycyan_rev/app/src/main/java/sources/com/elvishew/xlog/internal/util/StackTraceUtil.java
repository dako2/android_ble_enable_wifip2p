package com.elvishew.xlog.internal.util;

import com.elvishew.xlog.XLog;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

/* loaded from: classes.dex */
public class StackTraceUtil {
    private static final String XLOG_STACK_TRACE_ORIGIN;

    static {
        String name = XLog.class.getName();
        XLOG_STACK_TRACE_ORIGIN = name.substring(0, name.lastIndexOf(46) + 1);
    }

    public static String getStackTraceString(Throwable th) {
        if (th == null) {
            return "";
        }
        for (Throwable cause = th; cause != null; cause = cause.getCause()) {
            if (cause instanceof UnknownHostException) {
                return "";
            }
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    public static StackTraceElement[] getCroppedRealStackTrack(StackTraceElement[] stackTraceElementArr, String str, int i) {
        return cropStackTrace(getRealStackTrack(stackTraceElementArr, str), i);
    }

    private static StackTraceElement[] getRealStackTrack(StackTraceElement[] stackTraceElementArr, String str) {
        int i;
        int length = stackTraceElementArr.length;
        for (int i2 = length - 1; i2 >= 0; i2--) {
            String className = stackTraceElementArr[i2].getClassName();
            if (className.startsWith(XLOG_STACK_TRACE_ORIGIN) || (str != null && className.startsWith(str))) {
                i = i2 + 1;
                break;
            }
        }
        i = 0;
        int i3 = length - i;
        StackTraceElement[] stackTraceElementArr2 = new StackTraceElement[i3];
        System.arraycopy(stackTraceElementArr, i, stackTraceElementArr2, 0, i3);
        return stackTraceElementArr2;
    }

    private static StackTraceElement[] cropStackTrace(StackTraceElement[] stackTraceElementArr, int i) {
        int length = stackTraceElementArr.length;
        if (i > 0) {
            length = Math.min(i, length);
        }
        StackTraceElement[] stackTraceElementArr2 = new StackTraceElement[length];
        System.arraycopy(stackTraceElementArr, 0, stackTraceElementArr2, 0, length);
        return stackTraceElementArr2;
    }
}
