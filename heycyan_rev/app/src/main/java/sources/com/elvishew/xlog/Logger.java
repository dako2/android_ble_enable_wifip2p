package com.elvishew.xlog;

import com.elvishew.xlog.LogConfiguration;
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
import com.elvishew.xlog.internal.SystemCompat;
import com.elvishew.xlog.internal.util.StackTraceUtil;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.PrinterSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class Logger {
    private LogConfiguration logConfiguration;
    private Printer printer;

    Logger(LogConfiguration logConfiguration, Printer printer) {
        this.logConfiguration = logConfiguration;
        this.printer = printer;
    }

    Logger(Builder builder) {
        LogConfiguration.Builder builder2 = new LogConfiguration.Builder(XLog.sLogConfiguration);
        if (builder.logLevel != 0) {
            builder2.logLevel(builder.logLevel);
        }
        if (builder.tag != null) {
            builder2.tag(builder.tag);
        }
        if (builder.threadSet) {
            if (builder.withThread) {
                builder2.enableThreadInfo();
            } else {
                builder2.disableThreadInfo();
            }
        }
        if (builder.stackTraceSet) {
            if (!builder.withStackTrace) {
                builder2.disableStackTrace();
            } else {
                builder2.enableStackTrace(builder.stackTraceOrigin, builder.stackTraceDepth);
            }
        }
        if (builder.borderSet) {
            if (builder.withBorder) {
                builder2.enableBorder();
            } else {
                builder2.disableBorder();
            }
        }
        if (builder.jsonFormatter != null) {
            builder2.jsonFormatter(builder.jsonFormatter);
        }
        if (builder.xmlFormatter != null) {
            builder2.xmlFormatter(builder.xmlFormatter);
        }
        if (builder.throwableFormatter != null) {
            builder2.throwableFormatter(builder.throwableFormatter);
        }
        if (builder.threadFormatter != null) {
            builder2.threadFormatter(builder.threadFormatter);
        }
        if (builder.stackTraceFormatter != null) {
            builder2.stackTraceFormatter(builder.stackTraceFormatter);
        }
        if (builder.borderFormatter != null) {
            builder2.borderFormatter(builder.borderFormatter);
        }
        if (builder.objectFormatters != null) {
            builder2.objectFormatters(builder.objectFormatters);
        }
        if (builder.interceptors != null) {
            builder2.interceptors(builder.interceptors);
        }
        this.logConfiguration = builder2.build();
        if (builder.printer == null) {
            this.printer = XLog.sPrinter;
        } else {
            this.printer = builder.printer;
        }
    }

    /* renamed from: v */
    public void m84v(Object obj) {
        println(2, (int) obj);
    }

    /* renamed from: v */
    public void m88v(Object[] objArr) {
        println(2, objArr);
    }

    /* renamed from: v */
    public void m87v(String str, Object... objArr) {
        println(2, str, objArr);
    }

    /* renamed from: v */
    public void m85v(String str) {
        println(2, str);
    }

    /* renamed from: v */
    public void m86v(String str, Throwable th) {
        println(2, str, th);
    }

    /* renamed from: d */
    public void m69d(Object obj) {
        println(3, (int) obj);
    }

    /* renamed from: d */
    public void m73d(Object[] objArr) {
        println(3, objArr);
    }

    /* renamed from: d */
    public void m72d(String str, Object... objArr) {
        println(3, str, objArr);
    }

    /* renamed from: d */
    public void m70d(String str) {
        println(3, str);
    }

    /* renamed from: d */
    public void m71d(String str, Throwable th) {
        println(3, str, th);
    }

    /* renamed from: i */
    public void m79i(Object obj) {
        println(4, (int) obj);
    }

    /* renamed from: i */
    public void m83i(Object[] objArr) {
        println(4, objArr);
    }

    /* renamed from: i */
    public void m82i(String str, Object... objArr) {
        println(4, str, objArr);
    }

    /* renamed from: i */
    public void m80i(String str) {
        println(4, str);
    }

    /* renamed from: i */
    public void m81i(String str, Throwable th) {
        println(4, str, th);
    }

    /* renamed from: w */
    public void m89w(Object obj) {
        println(5, (int) obj);
    }

    /* renamed from: w */
    public void m93w(Object[] objArr) {
        println(5, objArr);
    }

    /* renamed from: w */
    public void m92w(String str, Object... objArr) {
        println(5, str, objArr);
    }

    /* renamed from: w */
    public void m90w(String str) {
        println(5, str);
    }

    /* renamed from: w */
    public void m91w(String str, Throwable th) {
        println(5, str, th);
    }

    /* renamed from: e */
    public void m74e(Object obj) {
        println(6, (int) obj);
    }

    /* renamed from: e */
    public void m78e(Object[] objArr) {
        println(6, objArr);
    }

    /* renamed from: e */
    public void m77e(String str, Object... objArr) {
        println(6, str, objArr);
    }

    /* renamed from: e */
    public void m75e(String str) {
        println(6, str);
    }

    /* renamed from: e */
    public void m76e(String str, Throwable th) {
        println(6, str, th);
    }

    public void log(int i, Object obj) {
        println(i, (int) obj);
    }

    public void log(int i, Object[] objArr) {
        println(i, objArr);
    }

    public void log(int i, String str, Object... objArr) {
        println(i, str, objArr);
    }

    public void log(int i, String str) {
        println(i, str);
    }

    public void log(int i, String str, Throwable th) {
        println(i, str, th);
    }

    public void json(String str) {
        if (3 < this.logConfiguration.logLevel) {
            return;
        }
        printlnInternal(3, this.logConfiguration.jsonFormatter.format(str));
    }

    public void xml(String str) {
        if (3 < this.logConfiguration.logLevel) {
            return;
        }
        printlnInternal(3, this.logConfiguration.xmlFormatter.format(str));
    }

    private <T> void println(int i, T t) {
        String string;
        if (i < this.logConfiguration.logLevel) {
            return;
        }
        if (t != null) {
            ObjectFormatter<? super T> objectFormatter = this.logConfiguration.getObjectFormatter(t);
            if (objectFormatter != null) {
                string = objectFormatter.format(t);
            } else {
                string = t.toString();
            }
        } else {
            string = "null";
        }
        printlnInternal(i, string);
    }

    private void println(int i, Object[] objArr) {
        if (i < this.logConfiguration.logLevel) {
            return;
        }
        printlnInternal(i, Arrays.deepToString(objArr));
    }

    private void println(int i, String str, Object... objArr) {
        if (i < this.logConfiguration.logLevel) {
            return;
        }
        printlnInternal(i, formatArgs(str, objArr));
    }

    void println(int i, String str) {
        if (i < this.logConfiguration.logLevel) {
            return;
        }
        if (str == null) {
            str = "";
        }
        printlnInternal(i, str);
    }

    private void println(int i, String str, Throwable th) {
        if (i < this.logConfiguration.logLevel) {
            return;
        }
        printlnInternal(i, ((str == null || str.length() == 0) ? "" : str + SystemCompat.lineSeparator) + this.logConfiguration.throwableFormatter.format(th));
    }

    private void printlnInternal(int i, String str) {
        String str2;
        String str3 = this.logConfiguration.tag;
        String str4 = this.logConfiguration.withThread ? this.logConfiguration.threadFormatter.format(Thread.currentThread()) : null;
        String str5 = this.logConfiguration.withStackTrace ? this.logConfiguration.stackTraceFormatter.format(StackTraceUtil.getCroppedRealStackTrack(new Throwable().getStackTrace(), this.logConfiguration.stackTraceOrigin, this.logConfiguration.stackTraceDepth)) : null;
        if (this.logConfiguration.interceptors != null) {
            LogItem logItem = new LogItem(i, str3, str4, str5, str);
            for (Interceptor interceptor : this.logConfiguration.interceptors) {
                logItem = interceptor.intercept(logItem);
                if (logItem == null) {
                    return;
                }
                if (logItem.tag == null || logItem.msg == null) {
                    Platform.get().error("Interceptor " + interceptor + " should not remove the tag or message of a log, if you don't want to print this log, just return a null when intercept.");
                    return;
                }
            }
            i = logItem.level;
            str3 = logItem.tag;
            str4 = logItem.threadInfo;
            str5 = logItem.stackTraceInfo;
            str = logItem.msg;
        }
        Printer printer = this.printer;
        if (this.logConfiguration.withBorder) {
            str2 = this.logConfiguration.borderFormatter.format(new String[]{str4, str5, str});
        } else {
            str2 = (str4 != null ? str4 + SystemCompat.lineSeparator : "") + (str5 != null ? str5 + SystemCompat.lineSeparator : "") + str;
        }
        printer.println(i, str3, str2);
    }

    private String formatArgs(String str, Object... objArr) {
        if (str != null) {
            return String.format(str, objArr);
        }
        StringBuilder sb = new StringBuilder();
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(objArr[i]);
        }
        return sb.toString();
    }

    public static class Builder {
        private BorderFormatter borderFormatter;
        private boolean borderSet;
        private List<Interceptor> interceptors;
        private JsonFormatter jsonFormatter;
        private int logLevel;
        private Map<Class<?>, ObjectFormatter<?>> objectFormatters;
        private Printer printer;
        private int stackTraceDepth;
        private StackTraceFormatter stackTraceFormatter;
        private String stackTraceOrigin;
        private boolean stackTraceSet;
        private String tag;
        private ThreadFormatter threadFormatter;
        private boolean threadSet;
        private ThrowableFormatter throwableFormatter;
        private boolean withBorder;
        private boolean withStackTrace;
        private boolean withThread;
        private XmlFormatter xmlFormatter;

        public Builder() {
            XLog.assertInitialization();
        }

        public Builder logLevel(int i) {
            this.logLevel = i;
            return this;
        }

        public Builder tag(String str) {
            this.tag = str;
            return this;
        }

        @Deprecated
        /* renamed from: t */
        public Builder m114t() {
            return enableThreadInfo();
        }

        public Builder enableThreadInfo() {
            this.withThread = true;
            this.threadSet = true;
            return this;
        }

        @Deprecated
        /* renamed from: nt */
        public Builder m111nt() {
            return disableThreadInfo();
        }

        public Builder disableThreadInfo() {
            this.withThread = false;
            this.threadSet = true;
            return this;
        }

        @Deprecated
        /* renamed from: st */
        public Builder m112st(int i) {
            return enableStackTrace(i);
        }

        public Builder enableStackTrace(int i) {
            this.withStackTrace = true;
            this.stackTraceDepth = i;
            this.stackTraceSet = true;
            return this;
        }

        @Deprecated
        /* renamed from: st */
        public Builder m113st(String str, int i) {
            return enableStackTrace(str, i);
        }

        public Builder enableStackTrace(String str, int i) {
            this.withStackTrace = true;
            this.stackTraceOrigin = str;
            this.stackTraceDepth = i;
            this.stackTraceSet = true;
            return this;
        }

        @Deprecated
        public Builder nst() {
            return disableStackTrace();
        }

        public Builder disableStackTrace() {
            this.withStackTrace = false;
            this.stackTraceOrigin = null;
            this.stackTraceDepth = 0;
            this.stackTraceSet = true;
            return this;
        }

        @Deprecated
        /* renamed from: b */
        public Builder m94b() {
            return enableBorder();
        }

        public Builder enableBorder() {
            this.withBorder = true;
            this.borderSet = true;
            return this;
        }

        @Deprecated
        /* renamed from: nb */
        public Builder m110nb() {
            return disableBorder();
        }

        public Builder disableBorder() {
            this.withBorder = false;
            this.borderSet = true;
            return this;
        }

        public Builder jsonFormatter(JsonFormatter jsonFormatter) {
            this.jsonFormatter = jsonFormatter;
            return this;
        }

        public Builder xmlFormatter(XmlFormatter xmlFormatter) {
            this.xmlFormatter = xmlFormatter;
            return this;
        }

        public Builder throwableFormatter(ThrowableFormatter throwableFormatter) {
            this.throwableFormatter = throwableFormatter;
            return this;
        }

        public Builder threadFormatter(ThreadFormatter threadFormatter) {
            this.threadFormatter = threadFormatter;
            return this;
        }

        public Builder stackTraceFormatter(StackTraceFormatter stackTraceFormatter) {
            this.stackTraceFormatter = stackTraceFormatter;
            return this;
        }

        public Builder borderFormatter(BorderFormatter borderFormatter) {
            this.borderFormatter = borderFormatter;
            return this;
        }

        public <T> Builder addObjectFormatter(Class<T> cls, ObjectFormatter<? super T> objectFormatter) {
            if (this.objectFormatters == null) {
                this.objectFormatters = new HashMap(DefaultsFactory.builtinObjectFormatters());
            }
            this.objectFormatters.put(cls, objectFormatter);
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            if (this.interceptors == null) {
                this.interceptors = new ArrayList();
            }
            this.interceptors.add(interceptor);
            return this;
        }

        public Builder printers(Printer... printerArr) {
            if (printerArr.length == 0) {
                this.printer = null;
            } else if (printerArr.length == 1) {
                this.printer = printerArr[0];
            } else {
                this.printer = new PrinterSet(printerArr);
            }
            return this;
        }

        /* renamed from: v */
        public void m115v(Object obj) {
            build().m84v(obj);
        }

        /* renamed from: v */
        public void m119v(Object[] objArr) {
            build().m88v(objArr);
        }

        /* renamed from: v */
        public void m118v(String str, Object... objArr) {
            build().m87v(str, objArr);
        }

        /* renamed from: v */
        public void m116v(String str) {
            build().m85v(str);
        }

        /* renamed from: v */
        public void m117v(String str, Throwable th) {
            build().m86v(str, th);
        }

        /* renamed from: d */
        public void m95d(Object obj) {
            build().m69d(obj);
        }

        /* renamed from: d */
        public void m99d(Object[] objArr) {
            build().m73d(objArr);
        }

        /* renamed from: d */
        public void m98d(String str, Object... objArr) {
            build().m72d(str, objArr);
        }

        /* renamed from: d */
        public void m96d(String str) {
            build().m70d(str);
        }

        /* renamed from: d */
        public void m97d(String str, Throwable th) {
            build().m71d(str, th);
        }

        /* renamed from: i */
        public void m105i(Object obj) {
            build().m79i(obj);
        }

        /* renamed from: i */
        public void m109i(Object[] objArr) {
            build().m83i(objArr);
        }

        /* renamed from: i */
        public void m108i(String str, Object... objArr) {
            build().m82i(str, objArr);
        }

        /* renamed from: i */
        public void m106i(String str) {
            build().m80i(str);
        }

        /* renamed from: i */
        public void m107i(String str, Throwable th) {
            build().m81i(str, th);
        }

        /* renamed from: w */
        public void m120w(Object obj) {
            build().m89w(obj);
        }

        /* renamed from: w */
        public void m124w(Object[] objArr) {
            build().m93w(objArr);
        }

        /* renamed from: w */
        public void m123w(String str, Object... objArr) {
            build().m92w(str, objArr);
        }

        /* renamed from: w */
        public void m121w(String str) {
            build().m90w(str);
        }

        /* renamed from: w */
        public void m122w(String str, Throwable th) {
            build().m91w(str, th);
        }

        /* renamed from: e */
        public void m100e(Object obj) {
            build().m74e(obj);
        }

        /* renamed from: e */
        public void m104e(Object[] objArr) {
            build().m78e(objArr);
        }

        /* renamed from: e */
        public void m103e(String str, Object... objArr) {
            build().m77e(str, objArr);
        }

        /* renamed from: e */
        public void m101e(String str) {
            build().m75e(str);
        }

        /* renamed from: e */
        public void m102e(String str, Throwable th) {
            build().m76e(str, th);
        }

        public void log(int i, Object obj) {
            build().log(i, obj);
        }

        public void log(int i, Object[] objArr) {
            build().log(i, objArr);
        }

        public void log(int i, String str, Object... objArr) {
            build().log(i, str, objArr);
        }

        public void log(int i, String str) {
            build().log(i, str);
        }

        public void log(int i, String str, Throwable th) {
            build().log(i, str, th);
        }

        public void json(String str) {
            build().json(str);
        }

        public void xml(String str) {
            build().xml(str);
        }

        public Logger build() {
            return new Logger(this);
        }
    }
}
