package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.impl;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.LogConfigurationException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.LogFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.FilenameUtils;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Hashtable;

/* loaded from: classes2.dex */
public class LogFactoryImpl extends LogFactory {
    public static final String ALLOW_FLAWED_CONTEXT_PROPERTY = "com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log.allowFlawedContext";
    public static final String ALLOW_FLAWED_DISCOVERY_PROPERTY = "com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log.allowFlawedDiscovery";
    public static final String ALLOW_FLAWED_HIERARCHY_PROPERTY = "com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log.allowFlawedHierarchy";
    public static final String LOG_PROPERTY = "com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log";
    protected static final String LOG_PROPERTY_OLD = "com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.log";
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$org$apache$commons$logging$Log;
    static /* synthetic */ Class class$org$apache$commons$logging$LogFactory;
    static /* synthetic */ Class class$org$apache$commons$logging$impl$LogFactoryImpl;
    private boolean allowFlawedContext;
    private boolean allowFlawedDiscovery;
    private boolean allowFlawedHierarchy;
    private String diagnosticPrefix;
    private String logClassName;
    protected Class[] logConstructorSignature;
    protected Method logMethod;
    protected Class[] logMethodSignature;
    private static final String PKG_IMPL = "com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.impl.";
    private static final int PKG_LEN = PKG_IMPL.length();
    private static final String LOGGING_IMPL_LOG4J_LOGGER = "com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.impl.Log4JLogger";
    private static final String LOGGING_IMPL_JDK14_LOGGER = "com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.impl.Jdk14Logger";
    private static final String LOGGING_IMPL_LUMBERJACK_LOGGER = "com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.impl.Jdk13LumberjackLogger";
    private static final String LOGGING_IMPL_SIMPLE_LOGGER = "com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.impl.SimpleLog";
    private static final String[] classesToDiscover = {LOGGING_IMPL_LOG4J_LOGGER, LOGGING_IMPL_JDK14_LOGGER, LOGGING_IMPL_LUMBERJACK_LOGGER, LOGGING_IMPL_SIMPLE_LOGGER};
    private boolean useTCCL = true;
    protected Hashtable attributes = new Hashtable();
    protected Hashtable instances = new Hashtable();
    protected Constructor logConstructor = null;

    public LogFactoryImpl() {
        Class[] clsArr = new Class[1];
        Class clsClass$ = class$java$lang$String;
        if (clsClass$ == null) {
            clsClass$ = class$("java.lang.String");
            class$java$lang$String = clsClass$;
        }
        clsArr[0] = clsClass$;
        this.logConstructorSignature = clsArr;
        this.logMethod = null;
        Class[] clsArr2 = new Class[1];
        Class clsClass$2 = class$org$apache$commons$logging$LogFactory;
        if (clsClass$2 == null) {
            clsClass$2 = class$(LogFactory.FACTORY_PROPERTY);
            class$org$apache$commons$logging$LogFactory = clsClass$2;
        }
        clsArr2[0] = clsClass$2;
        this.logMethodSignature = clsArr2;
        initDiagnostics();
        if (isDiagnosticsEnabled()) {
            logDiagnostic("Instance created.");
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.LogFactory
    public Object getAttribute(String str) {
        return this.attributes.get(str);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.LogFactory
    public String[] getAttributeNames() {
        return (String[]) this.attributes.keySet().toArray(new String[this.attributes.size()]);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.LogFactory
    public Log getInstance(Class cls) throws LogConfigurationException {
        return getInstance(cls.getName());
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.LogFactory
    public Log getInstance(String str) throws LogConfigurationException {
        Log log = (Log) this.instances.get(str);
        if (log != null) {
            return log;
        }
        Log logNewInstance = newInstance(str);
        this.instances.put(str, logNewInstance);
        return logNewInstance;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.LogFactory
    public void release() {
        logDiagnostic("Releasing all known loggers");
        this.instances.clear();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.LogFactory
    public void removeAttribute(String str) {
        this.attributes.remove(str);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.LogFactory
    public void setAttribute(String str, Object obj) {
        if (this.logConstructor != null) {
            logDiagnostic("setAttribute: call too late; configuration already performed.");
        }
        if (obj == null) {
            this.attributes.remove(str);
        } else {
            this.attributes.put(str, obj);
        }
        if (str.equals(LogFactory.TCCL_KEY)) {
            this.useTCCL = obj != null && Boolean.valueOf(obj.toString()).booleanValue();
        }
    }

    protected static ClassLoader getContextClassLoader() throws LogConfigurationException {
        return LogFactory.getContextClassLoader();
    }

    protected static boolean isDiagnosticsEnabled() {
        return LogFactory.isDiagnosticsEnabled();
    }

    protected static ClassLoader getClassLoader(Class cls) {
        return LogFactory.getClassLoader(cls);
    }

    private void initDiagnostics() {
        String strObjectId;
        ClassLoader classLoader = getClassLoader(getClass());
        if (classLoader == null) {
            strObjectId = "BOOTLOADER";
        } else {
            try {
                strObjectId = objectId(classLoader);
            } catch (SecurityException unused) {
                strObjectId = "UNKNOWN";
            }
        }
        this.diagnosticPrefix = new StringBuffer("[LogFactoryImpl@").append(System.identityHashCode(this)).append(" from ").append(strObjectId).append("] ").toString();
    }

    protected void logDiagnostic(String str) {
        if (isDiagnosticsEnabled()) {
            logRawDiagnostic(new StringBuffer().append(this.diagnosticPrefix).append(str).toString());
        }
    }

    protected String getLogClassName() throws LogConfigurationException {
        if (this.logClassName == null) {
            discoverLogImplementation(getClass().getName());
        }
        return this.logClassName;
    }

    protected Constructor getLogConstructor() throws LogConfigurationException {
        if (this.logConstructor == null) {
            discoverLogImplementation(getClass().getName());
        }
        return this.logConstructor;
    }

    protected boolean isJdk13LumberjackAvailable() {
        return isLogLibraryAvailable("Jdk13Lumberjack", LOGGING_IMPL_LUMBERJACK_LOGGER);
    }

    protected boolean isJdk14Available() {
        return isLogLibraryAvailable("Jdk14", LOGGING_IMPL_JDK14_LOGGER);
    }

    protected boolean isLog4JAvailable() {
        return isLogLibraryAvailable("Log4J", LOGGING_IMPL_LOG4J_LOGGER);
    }

    protected Log newInstance(String str) throws LogConfigurationException {
        Log logDiscoverLogImplementation;
        try {
            Constructor constructor = this.logConstructor;
            if (constructor == null) {
                logDiscoverLogImplementation = discoverLogImplementation(str);
            } else {
                logDiscoverLogImplementation = (Log) constructor.newInstance(str);
            }
            Method method = this.logMethod;
            if (method != null) {
                method.invoke(logDiscoverLogImplementation, this);
            }
            return logDiscoverLogImplementation;
        } catch (LogConfigurationException e) {
            throw e;
        } catch (InvocationTargetException e2) {
            e = e2;
            Throwable targetException = e.getTargetException();
            if (targetException != null) {
                e = targetException;
            }
            throw new LogConfigurationException(e);
        } catch (Throwable th) {
            handleThrowable(th);
            throw new LogConfigurationException(th);
        }
    }

    private static ClassLoader getContextClassLoaderInternal() throws LogConfigurationException {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.impl.LogFactoryImpl.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                return LogFactoryImpl.directGetContextClassLoader();
            }
        });
    }

    private static String getSystemProperty(final String str, final String str2) throws SecurityException {
        return (String) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.impl.LogFactoryImpl.2
            @Override // java.security.PrivilegedAction
            public Object run() {
                return System.getProperty(str, str2);
            }
        });
    }

    private ClassLoader getParentClassLoader(final ClassLoader classLoader) {
        try {
            return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.impl.LogFactoryImpl.3
                @Override // java.security.PrivilegedAction
                public Object run() {
                    return classLoader.getParent();
                }
            });
        } catch (SecurityException unused) {
            logDiagnostic("[SECURITY] Unable to obtain parent classloader");
            return null;
        }
    }

    private boolean isLogLibraryAvailable(String str, String str2) {
        if (isDiagnosticsEnabled()) {
            logDiagnostic(new StringBuffer("Checking for '").append(str).append("'.").toString());
        }
        try {
            if (createLogFromClass(str2, getClass().getName(), false) == null) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic(new StringBuffer("Did not find '").append(str).append("'.").toString());
                }
                return false;
            }
            if (!isDiagnosticsEnabled()) {
                return true;
            }
            logDiagnostic(new StringBuffer("Found '").append(str).append("'.").toString());
            return true;
        } catch (LogConfigurationException unused) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic(new StringBuffer("Logging system '").append(str).append("' is available but not useable.").toString());
            }
            return false;
        }
    }

    private String getConfigurationValue(String str) {
        String systemProperty;
        if (isDiagnosticsEnabled()) {
            logDiagnostic(new StringBuffer("[ENV] Trying to get configuration for item ").append(str).toString());
        }
        Object attribute = getAttribute(str);
        if (attribute != null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic(new StringBuffer("[ENV] Found LogFactory attribute [").append(attribute).append("] for ").append(str).toString());
            }
            return attribute.toString();
        }
        if (isDiagnosticsEnabled()) {
            logDiagnostic(new StringBuffer("[ENV] No LogFactory attribute found for ").append(str).toString());
        }
        try {
            systemProperty = getSystemProperty(str, null);
        } catch (SecurityException unused) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic(new StringBuffer("[ENV] Security prevented reading system property ").append(str).toString());
            }
        }
        if (systemProperty != null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic(new StringBuffer("[ENV] Found system property [").append(systemProperty).append("] for ").append(str).toString());
            }
            return systemProperty;
        }
        if (isDiagnosticsEnabled()) {
            logDiagnostic(new StringBuffer("[ENV] No system property found for property ").append(str).toString());
        }
        if (isDiagnosticsEnabled()) {
            logDiagnostic(new StringBuffer("[ENV] No configuration defined for item ").append(str).toString());
        }
        return null;
    }

    private boolean getBooleanConfiguration(String str, boolean z) {
        String configurationValue = getConfigurationValue(str);
        return configurationValue == null ? z : Boolean.valueOf(configurationValue).booleanValue();
    }

    private void initConfiguration() {
        this.allowFlawedContext = getBooleanConfiguration(ALLOW_FLAWED_CONTEXT_PROPERTY, true);
        this.allowFlawedDiscovery = getBooleanConfiguration(ALLOW_FLAWED_DISCOVERY_PROPERTY, true);
        this.allowFlawedHierarchy = getBooleanConfiguration(ALLOW_FLAWED_HIERARCHY_PROPERTY, true);
    }

    private Log discoverLogImplementation(String str) throws LogConfigurationException {
        if (isDiagnosticsEnabled()) {
            logDiagnostic("Discovering a Log implementation...");
        }
        initConfiguration();
        String strFindUserSpecifiedLogClassName = findUserSpecifiedLogClassName();
        if (strFindUserSpecifiedLogClassName != null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic(new StringBuffer("Attempting to load user-specified log class '").append(strFindUserSpecifiedLogClassName).append("'...").toString());
            }
            Log logCreateLogFromClass = createLogFromClass(strFindUserSpecifiedLogClassName, str, true);
            if (logCreateLogFromClass != null) {
                return logCreateLogFromClass;
            }
            StringBuffer stringBuffer = new StringBuffer("User-specified log class '");
            stringBuffer.append(strFindUserSpecifiedLogClassName);
            stringBuffer.append("' cannot be found or is not useable.");
            informUponSimilarName(stringBuffer, strFindUserSpecifiedLogClassName, LOGGING_IMPL_LOG4J_LOGGER);
            informUponSimilarName(stringBuffer, strFindUserSpecifiedLogClassName, LOGGING_IMPL_JDK14_LOGGER);
            informUponSimilarName(stringBuffer, strFindUserSpecifiedLogClassName, LOGGING_IMPL_LUMBERJACK_LOGGER);
            informUponSimilarName(stringBuffer, strFindUserSpecifiedLogClassName, LOGGING_IMPL_SIMPLE_LOGGER);
            throw new LogConfigurationException(stringBuffer.toString());
        }
        if (isDiagnosticsEnabled()) {
            logDiagnostic("No user-specified Log implementation; performing discovery using the standard supported logging implementations...");
        }
        Log logCreateLogFromClass2 = null;
        int i = 0;
        while (true) {
            String[] strArr = classesToDiscover;
            if (i >= strArr.length || logCreateLogFromClass2 != null) {
                break;
            }
            logCreateLogFromClass2 = createLogFromClass(strArr[i], str, true);
            i++;
        }
        if (logCreateLogFromClass2 != null) {
            return logCreateLogFromClass2;
        }
        throw new LogConfigurationException("No suitable Log implementation");
    }

    private void informUponSimilarName(StringBuffer stringBuffer, String str, String str2) {
        if (!str.equals(str2) && str.regionMatches(true, 0, str2, 0, PKG_LEN + 5)) {
            stringBuffer.append(" Did you mean '");
            stringBuffer.append(str2);
            stringBuffer.append("'?");
        }
    }

    private String findUserSpecifiedLogClassName() {
        if (isDiagnosticsEnabled()) {
            logDiagnostic("Trying to get log class from attribute 'org.apache.commons.logging.Log'");
        }
        String systemProperty = (String) getAttribute(LOG_PROPERTY);
        if (systemProperty == null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("Trying to get log class from attribute 'org.apache.commons.logging.log'");
            }
            systemProperty = (String) getAttribute(LOG_PROPERTY_OLD);
        }
        if (systemProperty == null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("Trying to get log class from system property 'org.apache.commons.logging.Log'");
            }
            try {
                systemProperty = getSystemProperty(LOG_PROPERTY, null);
            } catch (SecurityException e) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic(new StringBuffer("No access allowed to system property 'org.apache.commons.logging.Log' - ").append(e.getMessage()).toString());
                }
            }
        }
        if (systemProperty == null) {
            if (isDiagnosticsEnabled()) {
                logDiagnostic("Trying to get log class from system property 'org.apache.commons.logging.log'");
            }
            try {
                systemProperty = getSystemProperty(LOG_PROPERTY_OLD, null);
            } catch (SecurityException e2) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic(new StringBuffer("No access allowed to system property 'org.apache.commons.logging.log' - ").append(e2.getMessage()).toString());
                }
            }
        }
        return systemProperty != null ? systemProperty.trim() : systemProperty;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x016d A[LOOP:0: B:6:0x0033->B:43:0x016d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01d7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01d4 A[EDGE_INSN: B:72:0x01d4->B:50:0x01d4 BREAK  A[LOOP:0: B:6:0x0033->B:43:0x016d], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Log createLogFromClass(String str, String str2, boolean z) throws LogConfigurationException {
        Log log;
        Class<?> cls;
        Class<?> cls2;
        Object objNewInstance;
        URL systemResource;
        if (isDiagnosticsEnabled()) {
            logDiagnostic(new StringBuffer("Attempting to instantiate '").append(str).append("'").toString());
        }
        Object[] objArr = {str2};
        ClassLoader baseClassLoader = getBaseClassLoader();
        Constructor<?> constructor = null;
        Class<?> cls3 = null;
        while (true) {
            logDiagnostic(new StringBuffer("Trying to load '").append(str).append("' from classloader ").append(objectId(baseClassLoader)).toString());
            try {
                try {
                    if (isDiagnosticsEnabled()) {
                        String string = new StringBuffer().append(str.replace(FilenameUtils.EXTENSION_SEPARATOR, IOUtils.DIR_SEPARATOR_UNIX)).append(".class").toString();
                        if (baseClassLoader == null) {
                            systemResource = ClassLoader.getSystemResource(new StringBuffer().append(string).append(".class").toString());
                        } else {
                            systemResource = baseClassLoader.getResource(string);
                        }
                        if (systemResource == null) {
                            logDiagnostic(new StringBuffer().append("Class '").append(str).append("' [").append(string).append("] cannot be found.").toString());
                        } else {
                            logDiagnostic(new StringBuffer().append("Class '").append(str).append("' was found at '").append(systemResource).append("'").toString());
                        }
                    }
                    try {
                        cls = Class.forName(str, true, baseClassLoader);
                    } catch (ClassNotFoundException e) {
                        logDiagnostic(new StringBuffer().append("The log adapter '").append(str).append("' is not available via classloader ").append(objectId(baseClassLoader)).append(": ").append(e.getMessage().trim()).toString());
                        try {
                            cls = Class.forName(str);
                        } catch (ClassNotFoundException e2) {
                            logDiagnostic(new StringBuffer().append("The log adapter '").append(str).append("' is not available via the LogFactoryImpl class classloader: ").append(e2.getMessage().trim()).toString());
                            break;
                        }
                    }
                    cls2 = cls;
                    constructor = cls2.getConstructor(this.logConstructorSignature);
                    objNewInstance = constructor.newInstance(objArr);
                } catch (LogConfigurationException e3) {
                    throw e3;
                }
            } catch (ExceptionInInitializerError e4) {
                e = e4;
            } catch (NoClassDefFoundError e5) {
                e = e5;
            } catch (Throwable th) {
                th = th;
            }
            if (objNewInstance instanceof Log) {
                try {
                    log = (Log) objNewInstance;
                    cls3 = cls2;
                    break;
                } catch (ExceptionInInitializerError e6) {
                    e = e6;
                    cls3 = cls2;
                    logDiagnostic(new StringBuffer("The log adapter '").append(str).append("' is unable to initialize itself when loaded via classloader ").append(objectId(baseClassLoader)).append(": ").append(e.getMessage().trim()).toString());
                    log = null;
                    if (cls3 != null) {
                        this.logClassName = str;
                        this.logConstructor = constructor;
                        try {
                            this.logMethod = cls3.getMethod("setLogFactory", this.logMethodSignature);
                            logDiagnostic(new StringBuffer().append("Found method setLogFactory(LogFactory) in '").append(str).append("'").toString());
                        } catch (Throwable th2) {
                            handleThrowable(th2);
                            this.logMethod = null;
                            logDiagnostic(new StringBuffer("[INFO] '").append(str).append("' from classloader ").append(objectId(baseClassLoader)).append(" does not declare optional method setLogFactory(LogFactory)").toString());
                        }
                        logDiagnostic(new StringBuffer("Log adapter '").append(str).append("' from classloader ").append(objectId(cls3.getClassLoader())).append(" has been selected for use.").toString());
                    }
                    return log;
                } catch (NoClassDefFoundError e7) {
                    e = e7;
                    cls3 = cls2;
                    logDiagnostic(new StringBuffer("The log adapter '").append(str).append("' is missing dependencies when loaded via classloader ").append(objectId(baseClassLoader)).append(": ").append(e.getMessage().trim()).toString());
                    log = null;
                    if (cls3 != null) {
                    }
                    return log;
                } catch (Throwable th3) {
                    th = th3;
                    cls3 = cls2;
                    handleThrowable(th);
                    handleFlawedDiscovery(str, baseClassLoader, th);
                    if (baseClassLoader != null) {
                    }
                }
            } else {
                handleFlawedHierarchy(baseClassLoader, cls2);
                if (baseClassLoader != null) {
                    break;
                }
                baseClassLoader = getParentClassLoader(baseClassLoader);
            }
        }
        log = null;
        if (cls3 != null && z) {
            this.logClassName = str;
            this.logConstructor = constructor;
            this.logMethod = cls3.getMethod("setLogFactory", this.logMethodSignature);
            logDiagnostic(new StringBuffer().append("Found method setLogFactory(LogFactory) in '").append(str).append("'").toString());
            logDiagnostic(new StringBuffer("Log adapter '").append(str).append("' from classloader ").append(objectId(cls3.getClassLoader())).append(" has been selected for use.").toString());
        }
        return log;
    }

    private ClassLoader getBaseClassLoader() throws LogConfigurationException {
        Class clsClass$ = class$org$apache$commons$logging$impl$LogFactoryImpl;
        if (clsClass$ == null) {
            clsClass$ = class$(LogFactory.FACTORY_DEFAULT);
            class$org$apache$commons$logging$impl$LogFactoryImpl = clsClass$;
        }
        ClassLoader classLoader = getClassLoader(clsClass$);
        if (!this.useTCCL) {
            return classLoader;
        }
        ClassLoader contextClassLoaderInternal = getContextClassLoaderInternal();
        ClassLoader lowestClassLoader = getLowestClassLoader(contextClassLoaderInternal, classLoader);
        if (lowestClassLoader == null) {
            if (this.allowFlawedContext) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic("[WARNING] the context classloader is not part of a parent-child relationship with the classloader that loaded LogFactoryImpl.");
                }
                return contextClassLoaderInternal;
            }
            throw new LogConfigurationException("Bad classloader hierarchy; LogFactoryImpl was loaded via a classloader that is not related to the current context classloader.");
        }
        if (lowestClassLoader != contextClassLoaderInternal) {
            if (this.allowFlawedContext) {
                if (isDiagnosticsEnabled()) {
                    logDiagnostic("Warning: the context classloader is an ancestor of the classloader that loaded LogFactoryImpl; it should be the same or a descendant. The application using commons-logging should ensure the context classloader is used correctly.");
                }
            } else {
                throw new LogConfigurationException("Bad classloader hierarchy; LogFactoryImpl was loaded via a classloader that is not related to the current context classloader.");
            }
        }
        return lowestClassLoader;
    }

    private ClassLoader getLowestClassLoader(ClassLoader classLoader, ClassLoader classLoader2) {
        if (classLoader == null) {
            return classLoader2;
        }
        if (classLoader2 == null) {
            return classLoader;
        }
        ClassLoader parentClassLoader = classLoader;
        while (parentClassLoader != null) {
            if (parentClassLoader == classLoader2) {
                return classLoader;
            }
            parentClassLoader = getParentClassLoader(parentClassLoader);
        }
        ClassLoader parentClassLoader2 = classLoader2;
        while (parentClassLoader2 != null) {
            if (parentClassLoader2 == classLoader) {
                return classLoader2;
            }
            parentClassLoader2 = getParentClassLoader(parentClassLoader2);
        }
        return null;
    }

    private void handleFlawedDiscovery(String str, ClassLoader classLoader, Throwable th) {
        Throwable targetException;
        Throwable exception;
        if (isDiagnosticsEnabled()) {
            logDiagnostic(new StringBuffer("Could not instantiate Log '").append(str).append("' -- ").append(th.getClass().getName()).append(": ").append(th.getLocalizedMessage()).toString());
            if ((th instanceof InvocationTargetException) && (targetException = ((InvocationTargetException) th).getTargetException()) != null) {
                logDiagnostic(new StringBuffer("... InvocationTargetException: ").append(targetException.getClass().getName()).append(": ").append(targetException.getLocalizedMessage()).toString());
                if ((targetException instanceof ExceptionInInitializerError) && (exception = ((ExceptionInInitializerError) targetException).getException()) != null) {
                    StringWriter stringWriter = new StringWriter();
                    exception.printStackTrace(new PrintWriter((Writer) stringWriter, true));
                    logDiagnostic(new StringBuffer("... ExceptionInInitializerError: ").append(stringWriter.toString()).toString());
                }
            }
        }
        if (!this.allowFlawedDiscovery) {
            throw new LogConfigurationException(th);
        }
    }

    private void handleFlawedHierarchy(ClassLoader classLoader, Class cls) throws LogConfigurationException {
        Class clsClass$ = class$org$apache$commons$logging$Log;
        if (clsClass$ == null) {
            clsClass$ = class$(LOG_PROPERTY);
            class$org$apache$commons$logging$Log = clsClass$;
        }
        String name = clsClass$.getName();
        for (Class<?> cls2 : cls.getInterfaces()) {
            if (name.equals(cls2.getName())) {
                if (isDiagnosticsEnabled()) {
                    try {
                        Class clsClass$2 = class$org$apache$commons$logging$Log;
                        if (clsClass$2 == null) {
                            clsClass$2 = class$(LOG_PROPERTY);
                            class$org$apache$commons$logging$Log = clsClass$2;
                        }
                        logDiagnostic(new StringBuffer().append("Class '").append(cls.getName()).append("' was found in classloader ").append(objectId(classLoader)).append(". It is bound to a Log interface which is not").append(" the one loaded from classloader ").append(objectId(getClassLoader(clsClass$2))).toString());
                    } catch (Throwable th) {
                        handleThrowable(th);
                        logDiagnostic(new StringBuffer("Error while trying to output diagnostics about bad class '").append(cls).append("'").toString());
                    }
                }
                if (!this.allowFlawedHierarchy) {
                    StringBuffer stringBuffer = new StringBuffer("Terminating logging for this context due to bad log hierarchy. You have more than one version of '");
                    Class clsClass$3 = class$org$apache$commons$logging$Log;
                    if (clsClass$3 == null) {
                        clsClass$3 = class$(LOG_PROPERTY);
                        class$org$apache$commons$logging$Log = clsClass$3;
                    }
                    stringBuffer.append(clsClass$3.getName());
                    stringBuffer.append("' visible.");
                    if (isDiagnosticsEnabled()) {
                        logDiagnostic(stringBuffer.toString());
                    }
                    throw new LogConfigurationException(stringBuffer.toString());
                }
                if (isDiagnosticsEnabled()) {
                    StringBuffer stringBuffer2 = new StringBuffer("Warning: bad log hierarchy. You have more than one version of '");
                    Class clsClass$4 = class$org$apache$commons$logging$Log;
                    if (clsClass$4 == null) {
                        clsClass$4 = class$(LOG_PROPERTY);
                        class$org$apache$commons$logging$Log = clsClass$4;
                    }
                    stringBuffer2.append(clsClass$4.getName());
                    stringBuffer2.append("' visible.");
                    logDiagnostic(stringBuffer2.toString());
                    return;
                }
                return;
            }
        }
        if (!this.allowFlawedDiscovery) {
            StringBuffer stringBuffer3 = new StringBuffer("Terminating logging for this context. Log class '");
            stringBuffer3.append(cls.getName());
            stringBuffer3.append("' does not implement the Log interface.");
            if (isDiagnosticsEnabled()) {
                logDiagnostic(stringBuffer3.toString());
            }
            throw new LogConfigurationException(stringBuffer3.toString());
        }
        if (isDiagnosticsEnabled()) {
            StringBuffer stringBuffer4 = new StringBuffer("[WARNING] Log class '");
            stringBuffer4.append(cls.getName());
            stringBuffer4.append("' does not implement the Log interface.");
            logDiagnostic(stringBuffer4.toString());
        }
    }
}
