package com.elvishew.xlog;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.Logger;
import com.elvishew.xlog.formatter.border.BorderFormatter;
import com.elvishew.xlog.formatter.message.json.JsonFormatter;
import com.elvishew.xlog.formatter.message.object.ObjectFormatter;
import com.elvishew.xlog.formatter.message.throwable.ThrowableFormatter;
import com.elvishew.xlog.formatter.message.xml.XmlFormatter;
import com.elvishew.xlog.formatter.stacktrace.StackTraceFormatter;
import com.elvishew.xlog.formatter.thread.ThreadFormatter;
import com.elvishew.xlog.interceptor.Interceptor;
import com.elvishew.xlog.internal.DefaultsFactory;
import com.elvishew.xlog.internal.Platform;
import com.elvishew.xlog.internal.util.StackTraceUtil;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.PrinterSet;

/* loaded from: classes.dex */
public class XLog {
    static boolean sIsInitialized;
    static LogConfiguration sLogConfiguration;
    private static Logger sLogger;
    static Printer sPrinter;

    private XLog() {
    }

    public static void init() {
        init(new LogConfiguration.Builder().build(), DefaultsFactory.createPrinter());
    }

    public static void init(int i) {
        init(new LogConfiguration.Builder().logLevel(i).build(), DefaultsFactory.createPrinter());
    }

    @Deprecated
    public static void init(int i, LogConfiguration logConfiguration) {
        init(new LogConfiguration.Builder(logConfiguration).logLevel(i).build());
    }

    public static void init(LogConfiguration logConfiguration) {
        init(logConfiguration, DefaultsFactory.createPrinter());
    }

    public static void init(Printer... printerArr) {
        init(new LogConfiguration.Builder().build(), printerArr);
    }

    public static void init(int i, Printer... printerArr) {
        init(new LogConfiguration.Builder().logLevel(i).build(), printerArr);
    }

    @Deprecated
    public static void init(int i, LogConfiguration logConfiguration, Printer... printerArr) {
        init(new LogConfiguration.Builder(logConfiguration).logLevel(i).build(), printerArr);
    }

    public static void init(LogConfiguration logConfiguration, Printer... printerArr) {
        if (sIsInitialized) {
            Platform.get().warn("XLog is already initialized, do not initialize again");
        }
        sIsInitialized = true;
        if (logConfiguration == null) {
            throw new IllegalArgumentException("Please specify a LogConfiguration");
        }
        sLogConfiguration = logConfiguration;
        sPrinter = new PrinterSet(printerArr);
        sLogger = new Logger(sLogConfiguration, sPrinter);
    }

    static void assertInitialization() {
        if (!sIsInitialized) {
            throw new IllegalStateException("Do you forget to initialize XLog?");
        }
    }

    public static Logger.Builder logLevel(int i) {
        return new Logger.Builder().logLevel(i);
    }

    public static Logger.Builder tag(String str) {
        return new Logger.Builder().tag(str);
    }

    @Deprecated
    /* renamed from: t */
    public static Logger.Builder m145t() {
        return enableThreadInfo();
    }

    public static Logger.Builder enableThreadInfo() {
        return new Logger.Builder().enableThreadInfo();
    }

    @Deprecated
    /* renamed from: nt */
    public static Logger.Builder m142nt() {
        return disableThreadInfo();
    }

    public static Logger.Builder disableThreadInfo() {
        return new Logger.Builder().disableThreadInfo();
    }

    @Deprecated
    /* renamed from: st */
    public static Logger.Builder m143st(int i) {
        return enableStackTrace(i);
    }

    public static Logger.Builder enableStackTrace(int i) {
        return new Logger.Builder().enableStackTrace(i);
    }

    @Deprecated
    /* renamed from: st */
    public static Logger.Builder m144st(String str, int i) {
        return enableStackTrace(str, i);
    }

    public static Logger.Builder enableStackTrace(String str, int i) {
        return new Logger.Builder().enableStackTrace(str, i);
    }

    @Deprecated
    public static Logger.Builder nst() {
        return disableStackTrace();
    }

    public static Logger.Builder disableStackTrace() {
        return new Logger.Builder().disableStackTrace();
    }

    @Deprecated
    /* renamed from: b */
    public static Logger.Builder m125b() {
        return enableBorder();
    }

    public static Logger.Builder enableBorder() {
        return new Logger.Builder().enableBorder();
    }

    @Deprecated
    /* renamed from: nb */
    public static Logger.Builder m141nb() {
        return disableBorder();
    }

    public static Logger.Builder disableBorder() {
        return new Logger.Builder().disableBorder();
    }

    public static Logger.Builder jsonFormatter(JsonFormatter jsonFormatter) {
        return new Logger.Builder().jsonFormatter(jsonFormatter);
    }

    public static Logger.Builder xmlFormatter(XmlFormatter xmlFormatter) {
        return new Logger.Builder().xmlFormatter(xmlFormatter);
    }

    public static Logger.Builder throwableFormatter(ThrowableFormatter throwableFormatter) {
        return new Logger.Builder().throwableFormatter(throwableFormatter);
    }

    public static Logger.Builder threadFormatter(ThreadFormatter threadFormatter) {
        return new Logger.Builder().threadFormatter(threadFormatter);
    }

    public static Logger.Builder stackTraceFormatter(StackTraceFormatter stackTraceFormatter) {
        return new Logger.Builder().stackTraceFormatter(stackTraceFormatter);
    }

    public static Logger.Builder borderFormatter(BorderFormatter borderFormatter) {
        return new Logger.Builder().borderFormatter(borderFormatter);
    }

    public static <T> Logger.Builder addObjectFormatter(Class<T> cls, ObjectFormatter<? super T> objectFormatter) {
        return new Logger.Builder().addObjectFormatter(cls, objectFormatter);
    }

    public static Logger.Builder addInterceptor(Interceptor interceptor) {
        return new Logger.Builder().addInterceptor(interceptor);
    }

    public static Logger.Builder printers(Printer... printerArr) {
        return new Logger.Builder().printers(printerArr);
    }

    /* renamed from: v */
    public static void m146v(Object obj) {
        assertInitialization();
        sLogger.m84v(obj);
    }

    /* renamed from: v */
    public static void m150v(Object[] objArr) {
        assertInitialization();
        sLogger.m88v(objArr);
    }

    /* renamed from: v */
    public static void m149v(String str, Object... objArr) {
        assertInitialization();
        sLogger.m87v(str, objArr);
    }

    /* renamed from: v */
    public static void m147v(String str) {
        assertInitialization();
        sLogger.m85v(str);
    }

    /* renamed from: v */
    public static void m148v(String str, Throwable th) {
        assertInitialization();
        sLogger.m86v(str, th);
    }

    /* renamed from: d */
    public static void m126d(Object obj) {
        assertInitialization();
        sLogger.m69d(obj);
    }

    /* renamed from: d */
    public static void m130d(Object[] objArr) {
        assertInitialization();
        sLogger.m73d(objArr);
    }

    /* renamed from: d */
    public static void m129d(String str, Object... objArr) {
        assertInitialization();
        sLogger.m72d(str, objArr);
    }

    /* renamed from: d */
    public static void m127d(String str) {
        assertInitialization();
        sLogger.m70d(str);
    }

    /* renamed from: d */
    public static void m128d(String str, Throwable th) {
        assertInitialization();
        sLogger.m71d(str, th);
    }

    /* renamed from: i */
    public static void m136i(Object obj) {
        assertInitialization();
        sLogger.m79i(obj);
    }

    /* renamed from: i */
    public static void m140i(Object[] objArr) {
        assertInitialization();
        sLogger.m83i(objArr);
    }

    /* renamed from: i */
    public static void m139i(String str, Object... objArr) {
        assertInitialization();
        sLogger.m82i(str, objArr);
    }

    /* renamed from: i */
    public static void m137i(String str) {
        assertInitialization();
        sLogger.m80i(str);
    }

    /* renamed from: i */
    public static void m138i(String str, Throwable th) {
        assertInitialization();
        sLogger.m81i(str, th);
    }

    /* renamed from: w */
    public static void m151w(Object obj) {
        assertInitialization();
        sLogger.m89w(obj);
    }

    /* renamed from: w */
    public static void m155w(Object[] objArr) {
        assertInitialization();
        sLogger.m93w(objArr);
    }

    /* renamed from: w */
    public static void m154w(String str, Object... objArr) {
        assertInitialization();
        sLogger.m92w(str, objArr);
    }

    /* renamed from: w */
    public static void m152w(String str) {
        assertInitialization();
        sLogger.m90w(str);
    }

    /* renamed from: w */
    public static void m153w(String str, Throwable th) {
        assertInitialization();
        sLogger.m91w(str, th);
    }

    /* renamed from: e */
    public static void m131e(Object obj) {
        assertInitialization();
        sLogger.m74e(obj);
    }

    /* renamed from: e */
    public static void m135e(Object[] objArr) {
        assertInitialization();
        sLogger.m78e(objArr);
    }

    /* renamed from: e */
    public static void m134e(String str, Object... objArr) {
        assertInitialization();
        sLogger.m77e(str, objArr);
    }

    /* renamed from: e */
    public static void m132e(String str) {
        assertInitialization();
        sLogger.m75e(str);
    }

    /* renamed from: e */
    public static void m133e(String str, Throwable th) {
        assertInitialization();
        sLogger.m76e(str, th);
    }

    public static void log(int i, Object obj) {
        assertInitialization();
        sLogger.log(i, obj);
    }

    public static void log(int i, Object[] objArr) {
        assertInitialization();
        sLogger.log(i, objArr);
    }

    public static void log(int i, String str, Object... objArr) {
        assertInitialization();
        sLogger.log(i, str, objArr);
    }

    public static void log(int i, String str) {
        assertInitialization();
        sLogger.log(i, str);
    }

    public static void log(int i, String str, Throwable th) {
        assertInitialization();
        sLogger.log(i, str, th);
    }

    public static void json(String str) {
        assertInitialization();
        sLogger.json(str);
    }

    public static void xml(String str) {
        assertInitialization();
        sLogger.xml(str);
    }

    public static class Log {
        /* renamed from: v */
        public static void m162v(String str, String str2) {
            XLog.tag(str).build().m85v(str2);
        }

        /* renamed from: v */
        public static void m163v(String str, String str2, Throwable th) {
            XLog.tag(str).build().m86v(str2, th);
        }

        /* renamed from: d */
        public static void m156d(String str, String str2) {
            XLog.tag(str).build().m70d(str2);
        }

        /* renamed from: d */
        public static void m157d(String str, String str2, Throwable th) {
            XLog.tag(str).build().m71d(str2, th);
        }

        /* renamed from: i */
        public static void m160i(String str, String str2) {
            XLog.tag(str).build().m80i(str2);
        }

        /* renamed from: i */
        public static void m161i(String str, String str2, Throwable th) {
            XLog.tag(str).build().m81i(str2, th);
        }

        /* renamed from: w */
        public static void m164w(String str, String str2) {
            XLog.tag(str).build().m90w(str2);
        }

        /* renamed from: w */
        public static void m165w(String str, String str2, Throwable th) {
            XLog.tag(str).build().m91w(str2, th);
        }

        /* renamed from: w */
        public static void m166w(String str, Throwable th) {
            XLog.tag(str).build().m91w("", th);
        }

        /* renamed from: e */
        public static void m158e(String str, String str2) {
            XLog.tag(str).build().m75e(str2);
        }

        /* renamed from: e */
        public static void m159e(String str, String str2, Throwable th) {
            XLog.tag(str).build().m76e(str2, th);
        }

        public static void wtf(String str, String str2) {
            m158e(str, str2);
        }

        public static void wtf(String str, Throwable th) {
            wtf(str, "", th);
        }

        public static void wtf(String str, String str2, Throwable th) {
            m159e(str, str2, th);
        }

        public static void println(int i, String str, String str2) {
            XLog.tag(str).build().println(i, str2);
        }

        public static boolean isLoggable(String str, int i) {
            return XLog.sLogConfiguration.isLoggable(i);
        }

        public static String getStackTraceString(Throwable th) {
            return StackTraceUtil.getStackTraceString(th);
        }
    }
}
