package org.koin.android.ext.koin;

import android.app.Application;
import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.android.error.MissingAndroidContextException;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: ModuleExt.kt */
@Metadata(m605bv = {1, 0, 3}, m606d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0004\u001a\n\u0010\u0005\u001a\u00020\u0006*\u00020\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m607d2 = {"ERROR_MSG", "", "androidApplication", "Landroid/app/Application;", "Lorg/koin/core/scope/Scope;", "androidContext", "Landroid/content/Context;", "koin-android_release"}, m608k = 2, m609mv = {1, 1, 15})
/* loaded from: classes3.dex */
public final class ModuleExtKt {
    public static final String ERROR_MSG = "Please use androidContext() function in your KoinApplication configuration.";

    public static final Context androidContext(Scope androidContext) throws MissingAndroidContextException {
        Intrinsics.checkParameterIsNotNull(androidContext, "$this$androidContext");
        try {
            return (Context) androidContext.get(Reflection.getOrCreateKotlinClass(Context.class), (Qualifier) null, (Function0<DefinitionParameters>) null);
        } catch (Exception unused) {
            throw new MissingAndroidContextException("Can't resolve Context instance. Please use androidContext() function in your KoinApplication configuration.");
        }
    }

    public static final Application androidApplication(Scope androidApplication) throws MissingAndroidContextException {
        Intrinsics.checkParameterIsNotNull(androidApplication, "$this$androidApplication");
        try {
            return (Application) androidApplication.get(Reflection.getOrCreateKotlinClass(Application.class), (Qualifier) null, (Function0<DefinitionParameters>) null);
        } catch (Exception unused) {
            throw new MissingAndroidContextException("Can't resolve Application instance. Please use androidContext() function in your KoinApplication configuration.");
        }
    }
}
