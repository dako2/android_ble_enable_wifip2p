package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.impl;

import androidx.core.os.EnvironmentCompat;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
public class Jdk13LumberjackLogger implements Log, Serializable {
    protected static final Level dummyLevel = Level.FINE;
    private static final long serialVersionUID = -8649807923527610591L;
    protected transient Logger logger;
    protected String name;
    private String sourceClassName = EnvironmentCompat.MEDIA_UNKNOWN;
    private String sourceMethodName = EnvironmentCompat.MEDIA_UNKNOWN;
    private boolean classAndMethodFound = false;

    public Jdk13LumberjackLogger(String str) {
        this.logger = null;
        this.name = str;
        this.logger = getLogger();
    }

    private void log(Level level, String str, Throwable th) {
        if (getLogger().isLoggable(level)) {
            LogRecord logRecord = new LogRecord(level, str);
            if (!this.classAndMethodFound) {
                getClassAndMethod();
            }
            logRecord.setSourceClassName(this.sourceClassName);
            logRecord.setSourceMethodName(this.sourceMethodName);
            if (th != null) {
                logRecord.setThrown(th);
            }
            getLogger().log(logRecord);
        }
    }

    private void getClassAndMethod() {
        try {
            Throwable th = new Throwable();
            th.fillInStackTrace();
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            StringTokenizer stringTokenizer = new StringTokenizer(stringWriter.getBuffer().toString(), IOUtils.LINE_SEPARATOR_UNIX);
            stringTokenizer.nextToken();
            String strNextToken = stringTokenizer.nextToken();
            while (strNextToken.indexOf(getClass().getName()) == -1) {
                strNextToken = stringTokenizer.nextToken();
            }
            while (strNextToken.indexOf(getClass().getName()) >= 0) {
                strNextToken = stringTokenizer.nextToken();
            }
            String strSubstring = strNextToken.substring(strNextToken.indexOf("at ") + 3, strNextToken.indexOf(40));
            int iLastIndexOf = strSubstring.lastIndexOf(46);
            this.sourceClassName = strSubstring.substring(0, iLastIndexOf);
            this.sourceMethodName = strSubstring.substring(iLastIndexOf + 1);
        } catch (Exception unused) {
        }
        this.classAndMethodFound = true;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log
    public void debug(Object obj) {
        log(Level.FINE, String.valueOf(obj), null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log
    public void debug(Object obj, Throwable th) {
        log(Level.FINE, String.valueOf(obj), th);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log
    public void error(Object obj) {
        log(Level.SEVERE, String.valueOf(obj), null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log
    public void error(Object obj, Throwable th) {
        log(Level.SEVERE, String.valueOf(obj), th);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log
    public void fatal(Object obj) {
        log(Level.SEVERE, String.valueOf(obj), null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log
    public void fatal(Object obj, Throwable th) {
        log(Level.SEVERE, String.valueOf(obj), th);
    }

    public Logger getLogger() {
        if (this.logger == null) {
            this.logger = Logger.getLogger(this.name);
        }
        return this.logger;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log
    public void info(Object obj) {
        log(Level.INFO, String.valueOf(obj), null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log
    public void info(Object obj, Throwable th) {
        log(Level.INFO, String.valueOf(obj), th);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log
    public boolean isDebugEnabled() {
        return getLogger().isLoggable(Level.FINE);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log
    public boolean isErrorEnabled() {
        return getLogger().isLoggable(Level.SEVERE);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log
    public boolean isFatalEnabled() {
        return getLogger().isLoggable(Level.SEVERE);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log
    public boolean isInfoEnabled() {
        return getLogger().isLoggable(Level.INFO);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log
    public boolean isTraceEnabled() {
        return getLogger().isLoggable(Level.FINEST);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log
    public boolean isWarnEnabled() {
        return getLogger().isLoggable(Level.WARNING);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log
    public void trace(Object obj) {
        log(Level.FINEST, String.valueOf(obj), null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log
    public void trace(Object obj, Throwable th) {
        log(Level.FINEST, String.valueOf(obj), th);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log
    public void warn(Object obj) {
        log(Level.WARNING, String.valueOf(obj), null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log
    public void warn(Object obj, Throwable th) {
        log(Level.WARNING, String.valueOf(obj), th);
    }
}
