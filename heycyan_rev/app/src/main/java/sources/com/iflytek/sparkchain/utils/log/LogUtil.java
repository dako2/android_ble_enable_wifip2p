package com.iflytek.sparkchain.utils.log;

import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class LogUtil {

    /* renamed from: A */
    private static final int f511A = 6;

    /* renamed from: D */
    private static final int f512D = 2;
    private static final String DEFAULT_MESSAGE = "A.I.Kit";

    /* renamed from: E */
    private static final int f513E = 5;

    /* renamed from: I */
    private static final int f514I = 3;
    private static boolean IS_SHOW_LOG = true;
    private static final int JSON = 7;
    private static final int JSON_INDENT = 4;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /* renamed from: V */
    private static final int f515V = 1;

    /* renamed from: W */
    private static final int f516W = 4;

    /* renamed from: a */
    public static void m557a() {
        printLog(6, null, DEFAULT_MESSAGE);
    }

    /* renamed from: a */
    public static void m558a(Object obj) {
        printLog(6, null, obj);
    }

    /* renamed from: a */
    public static void m559a(String str, Object obj) {
        printLog(6, str, obj);
    }

    /* renamed from: d */
    public static void m560d() {
        printLog(2, null, DEFAULT_MESSAGE);
    }

    /* renamed from: d */
    public static void m561d(Object obj) {
        printLog(2, null, obj);
    }

    /* renamed from: d */
    public static void m562d(String str, Object obj) {
        printLog(2, str, obj);
    }

    /* renamed from: e */
    public static void m563e() {
        printLog(5, null, DEFAULT_MESSAGE);
    }

    /* renamed from: e */
    public static void m564e(Object obj) {
        printLog(5, null, obj);
    }

    /* renamed from: e */
    public static void m565e(String str, Object obj) {
        printLog(5, str, obj);
    }

    /* renamed from: i */
    public static void m566i() {
        printLog(3, null, DEFAULT_MESSAGE);
    }

    /* renamed from: i */
    public static void m567i(Object obj) {
        printLog(3, null, obj);
    }

    /* renamed from: i */
    public static void m568i(String str, Object obj) {
        printLog(3, str, obj);
    }

    public static void init(boolean z) {
        IS_SHOW_LOG = z;
    }

    public static void json(String str) {
        printLog(7, null, str);
    }

    public static void json(String str, String str2) {
        printLog(7, str, str2);
    }

    private static void printLine(String str, boolean z) {
        Log.w(str, z ? "╔═══════════════════════════════════════════════════════════════════════════════════════" : "╚═══════════════════════════════════════════════════════════════════════════════════════");
    }

    private static void printLog(int i, String str, Object obj) {
        if (IS_SHOW_LOG) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String fileName = stackTrace[4].getFileName();
            String methodName = stackTrace[4].getMethodName();
            int lineNumber = stackTrace[4].getLineNumber();
            if (str == null) {
                str = fileName;
            }
            String str2 = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
            StringBuilder sb = new StringBuilder("[ (");
            sb.append(fileName).append(":").append(lineNumber).append(")#").append(str2).append(" ] ");
            String string = obj == null ? "Log with null Object" : obj.toString();
            if (string != null && i != 7) {
                sb.append(string);
            }
            String string2 = sb.toString();
            switch (i) {
                case 1:
                    Log.v(str, string2);
                    break;
                case 2:
                    Log.d(str, string2);
                    break;
                case 3:
                    Log.i(str, string2);
                    break;
                case 4:
                    Log.w(str, string2);
                    break;
                case 5:
                    Log.e(str, string2);
                    break;
                case 6:
                    Log.wtf(str, string2);
                    break;
                case 7:
                    if (TextUtils.isEmpty(string)) {
                        Log.d(str, "Empty or Null json content");
                        break;
                    } else {
                        try {
                            String string3 = string.startsWith("{") ? new JSONObject(string).toString(4) : string.startsWith("[") ? new JSONArray(string).toString(4) : null;
                            printLine(str, true);
                            StringBuilder sbAppend = new StringBuilder().append(string2);
                            String str3 = LINE_SEPARATOR;
                            String[] strArrSplit = sbAppend.append(str3).append(string3).toString().split(str3);
                            StringBuilder sb2 = new StringBuilder();
                            for (String str4 : strArrSplit) {
                                sb2.append("║ ").append(str4).append(LINE_SEPARATOR);
                            }
                            if (sb2.toString().length() > 3200) {
                                Log.w(str, "jsonContent.length = " + sb2.toString().length());
                                int length = sb2.toString().length() / 3200;
                                int i2 = 0;
                                while (i2 <= length) {
                                    int i3 = i2 + 1;
                                    int i4 = i3 * 3200;
                                    Log.w(str, i4 >= sb2.toString().length() ? sb2.toString().substring(i2 * 3200) : sb2.toString().substring(i2 * 3200, i4));
                                    i2 = i3;
                                }
                            } else {
                                Log.w(str, sb2.toString());
                            }
                            printLine(str, false);
                            break;
                        } catch (JSONException e) {
                            m565e(str, e.getCause().getMessage() + IOUtils.LINE_SEPARATOR_UNIX + string);
                            return;
                        }
                    }
            }
        }
    }

    /* renamed from: v */
    public static void m569v() {
        printLog(1, null, DEFAULT_MESSAGE);
    }

    /* renamed from: v */
    public static void m570v(Object obj) {
        printLog(1, null, obj);
    }

    /* renamed from: v */
    public static void m571v(String str, String str2) {
        printLog(1, str, str2);
    }

    /* renamed from: w */
    public static void m572w() {
        printLog(4, null, DEFAULT_MESSAGE);
    }

    /* renamed from: w */
    public static void m573w(Object obj) {
        printLog(4, null, obj);
    }

    /* renamed from: w */
    public static void m574w(String str, Object obj) {
        printLog(4, str, obj);
    }
}
