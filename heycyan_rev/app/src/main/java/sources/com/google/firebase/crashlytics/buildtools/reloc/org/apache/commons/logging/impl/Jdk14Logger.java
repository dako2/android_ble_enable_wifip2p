package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.impl;

import androidx.core.os.EnvironmentCompat;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
public class Jdk14Logger implements Log, Serializable {
    protected static final Level dummyLevel = Level.FINE;
    private static final long serialVersionUID = 4784713551416303804L;
    protected transient Logger logger;
    protected String name;

    public Jdk14Logger(String str) {
        this.logger = null;
        this.name = str;
        this.logger = getLogger();
    }

    protected void log(Level level, String str, Throwable th) {
        String methodName;
        Logger logger = getLogger();
        if (logger.isLoggable(level)) {
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            String str2 = this.name;
            if (stackTrace != null && stackTrace.length > 2) {
                methodName = stackTrace[2].getMethodName();
            } else {
                methodName = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            String str3 = methodName;
            if (th == null) {
                logger.logp(level, str2, str3, str);
            } else {
                logger.logp(level, str2, str3, str, th);
            }
        }
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
