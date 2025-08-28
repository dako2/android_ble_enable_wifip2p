package org.koin.core.definition;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.koin.core.instance.DefinitionInstance;
import org.koin.core.instance.FactoryDefinitionInstance;
import org.koin.core.instance.InstanceContext;
import org.koin.core.instance.ScopeDefinitionInstance;
import org.koin.core.instance.SingleDefinitionInstance;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;
import org.koin.ext.KClassExtKt;

/* compiled from: BeanDefinition.kt */
@Metadata(m605bv = {1, 0, 3}, m606d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B)\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010C\u001a\u00020!J\u0006\u0010D\u001a\u00020!J\u0013\u0010E\u001a\u00020F2\b\u0010G\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\u0006\u0010H\u001a\u00020FJ\b\u0010I\u001a\u00020JH\u0016J\u0019\u0010K\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\u0006\u0010L\u001a\u00020M¢\u0006\u0002\u0010NJ\b\u0010O\u001a\u00020PH\u0016R;\u0010\t\u001a#\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00028\u00000\nj\b\u0012\u0004\u0012\u00028\u0000`\r¢\u0006\u0002\b\u000eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR6\u0010\u001f\u001a\u001e\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00020!\u0018\u00010 j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R6\u0010'\u001a\u001e\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00020!\u0018\u00010 j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010$\"\u0004\b*\u0010&R\u001a\u0010+\u001a\u00020,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0015\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u001a\u00103\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010:R2\u0010<\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070=j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007`>X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010B¨\u0006Q"}, m607d2 = {"Lorg/koin/core/definition/BeanDefinition;", ExifInterface.GPS_DIRECTION_TRUE, "", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "scopeName", "primaryType", "Lkotlin/reflect/KClass;", "(Lorg/koin/core/qualifier/Qualifier;Lorg/koin/core/qualifier/Qualifier;Lkotlin/reflect/KClass;)V", "definition", "Lkotlin/Function2;", "Lorg/koin/core/scope/Scope;", "Lorg/koin/core/parameter/DefinitionParameters;", "Lorg/koin/core/definition/Definition;", "Lkotlin/ExtensionFunctionType;", "getDefinition", "()Lkotlin/jvm/functions/Function2;", "setDefinition", "(Lkotlin/jvm/functions/Function2;)V", "instance", "Lorg/koin/core/instance/DefinitionInstance;", "getInstance", "()Lorg/koin/core/instance/DefinitionInstance;", "setInstance", "(Lorg/koin/core/instance/DefinitionInstance;)V", "kind", "Lorg/koin/core/definition/Kind;", "getKind", "()Lorg/koin/core/definition/Kind;", "setKind", "(Lorg/koin/core/definition/Kind;)V", "onClose", "Lkotlin/Function1;", "", "Lorg/koin/core/definition/OnCloseCallback;", "getOnClose", "()Lkotlin/jvm/functions/Function1;", "setOnClose", "(Lkotlin/jvm/functions/Function1;)V", "onRelease", "Lorg/koin/core/definition/OnReleaseCallback;", "getOnRelease", "setOnRelease", "options", "Lorg/koin/core/definition/Options;", "getOptions", "()Lorg/koin/core/definition/Options;", "setOptions", "(Lorg/koin/core/definition/Options;)V", "getPrimaryType", "()Lkotlin/reflect/KClass;", "properties", "Lorg/koin/core/definition/Properties;", "getProperties", "()Lorg/koin/core/definition/Properties;", "setProperties", "(Lorg/koin/core/definition/Properties;)V", "getQualifier", "()Lorg/koin/core/qualifier/Qualifier;", "getScopeName", "secondaryTypes", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getSecondaryTypes", "()Ljava/util/ArrayList;", "setSecondaryTypes", "(Ljava/util/ArrayList;)V", "close", "createInstanceHolder", "equals", "", "other", "hasScopeSet", "hashCode", "", "resolveInstance", "context", "Lorg/koin/core/instance/InstanceContext;", "(Lorg/koin/core/instance/InstanceContext;)Ljava/lang/Object;", "toString", "", "koin-core"}, m608k = 1, m609mv = {1, 1, 15})
/* loaded from: classes3.dex */
public final class BeanDefinition<T> {
    public Function2<? super Scope, ? super DefinitionParameters, ? extends T> definition;
    private DefinitionInstance<T> instance;
    public Kind kind;
    private Function1<? super T, Unit> onClose;
    private Function1<? super T, Unit> onRelease;
    private Options options;
    private final KClass<?> primaryType;
    private Properties properties;
    private final Qualifier qualifier;
    private final Qualifier scopeName;
    private ArrayList<KClass<?>> secondaryTypes;

    @Metadata(m605bv = {1, 0, 3}, m608k = 3, m609mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Kind.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Kind.Single.ordinal()] = 1;
            iArr[Kind.Factory.ordinal()] = 2;
            iArr[Kind.Scoped.ordinal()] = 3;
        }
    }

    public BeanDefinition(Qualifier qualifier, Qualifier qualifier2, KClass<?> primaryType) {
        Intrinsics.checkParameterIsNotNull(primaryType, "primaryType");
        this.qualifier = qualifier;
        this.scopeName = qualifier2;
        this.primaryType = primaryType;
        this.secondaryTypes = new ArrayList<>();
        this.options = new Options(false, false, 3, null);
        this.properties = new Properties(null, 1, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ BeanDefinition(Qualifier qualifier, Qualifier qualifier2, KClass kClass, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            qualifier2 = null;
        }
        this(qualifier, qualifier2, kClass);
    }

    public final Qualifier getQualifier() {
        return this.qualifier;
    }

    public final Qualifier getScopeName() {
        return this.scopeName;
    }

    public final KClass<?> getPrimaryType() {
        return this.primaryType;
    }

    public final ArrayList<KClass<?>> getSecondaryTypes() {
        return this.secondaryTypes;
    }

    public final void setSecondaryTypes(ArrayList<KClass<?>> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.secondaryTypes = arrayList;
    }

    public final DefinitionInstance<T> getInstance() {
        return this.instance;
    }

    public final void setInstance(DefinitionInstance<T> definitionInstance) {
        this.instance = definitionInstance;
    }

    public final Function2<Scope, DefinitionParameters, T> getDefinition() {
        Function2<? super Scope, ? super DefinitionParameters, ? extends T> function2 = this.definition;
        if (function2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("definition");
        }
        return function2;
    }

    public final void setDefinition(Function2<? super Scope, ? super DefinitionParameters, ? extends T> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "<set-?>");
        this.definition = function2;
    }

    public final Options getOptions() {
        return this.options;
    }

    public final void setOptions(Options options) {
        Intrinsics.checkParameterIsNotNull(options, "<set-?>");
        this.options = options;
    }

    public final Properties getProperties() {
        return this.properties;
    }

    public final void setProperties(Properties properties) {
        Intrinsics.checkParameterIsNotNull(properties, "<set-?>");
        this.properties = properties;
    }

    public final Kind getKind() {
        Kind kind = this.kind;
        if (kind == null) {
            Intrinsics.throwUninitializedPropertyAccessException("kind");
        }
        return kind;
    }

    public final void setKind(Kind kind) {
        Intrinsics.checkParameterIsNotNull(kind, "<set-?>");
        this.kind = kind;
    }

    public final Function1<T, Unit> getOnRelease() {
        return this.onRelease;
    }

    public final void setOnRelease(Function1<? super T, Unit> function1) {
        this.onRelease = function1;
    }

    public final Function1<T, Unit> getOnClose() {
        return this.onClose;
    }

    public final void setOnClose(Function1<? super T, Unit> function1) {
        this.onClose = function1;
    }

    public final boolean hasScopeSet() {
        return this.scopeName != null;
    }

    public final void createInstanceHolder() {
        SingleDefinitionInstance singleDefinitionInstance;
        Kind kind = this.kind;
        if (kind == null) {
            Intrinsics.throwUninitializedPropertyAccessException("kind");
        }
        int i = WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
        if (i == 1) {
            singleDefinitionInstance = new SingleDefinitionInstance(this);
        } else if (i == 2) {
            singleDefinitionInstance = new FactoryDefinitionInstance(this);
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            singleDefinitionInstance = new ScopeDefinitionInstance(this);
        }
        this.instance = singleDefinitionInstance;
    }

    public final <T> T resolveInstance(InstanceContext context) {
        T t;
        Intrinsics.checkParameterIsNotNull(context, "context");
        DefinitionInstance<T> definitionInstance = this.instance;
        if (definitionInstance == null || (t = definitionInstance.get(context)) == null) {
            throw new IllegalStateException(("Definition without any InstanceContext - " + this).toString());
        }
        return t;
    }

    public String toString() {
        String str;
        String str2;
        Kind kind = this.kind;
        if (kind == null) {
            Intrinsics.throwUninitializedPropertyAccessException("kind");
        }
        String string = kind.toString();
        if (this.qualifier == null || (str = "name:'" + this.qualifier + "', ") == null) {
            str = "";
        }
        if (this.scopeName == null || (str2 = "scope:'" + this.scopeName + "', ") == null) {
            str2 = "";
        }
        return "[type:" + string + ',' + str2 + str + ("primary_type:'" + KClassExtKt.getFullName(this.primaryType) + '\'') + (this.secondaryTypes.isEmpty() ? "" : ", secondary_type:" + CollectionsKt.joinToString$default(this.secondaryTypes, ",", null, null, 0, null, new Function1<KClass<?>, String>() { // from class: org.koin.core.definition.BeanDefinition$toString$defOtherTypes$typesAsString$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(KClass<?> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return KClassExtKt.getFullName(it);
            }
        }, 30, null)) + ']';
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            BeanDefinition beanDefinition = (BeanDefinition) other;
            return Intrinsics.areEqual(this.qualifier, beanDefinition.qualifier) && Intrinsics.areEqual(this.primaryType, beanDefinition.primaryType);
        }
        throw new TypeCastException("null cannot be cast to non-null type org.koin.core.definition.BeanDefinition<*>");
    }

    public int hashCode() {
        Qualifier qualifier = this.qualifier;
        return ((qualifier != null ? qualifier.hashCode() : 0) * 31) + this.primaryType.hashCode();
    }

    public final void close() {
        DefinitionInstance<T> definitionInstance = this.instance;
        if (definitionInstance != null) {
            definitionInstance.close();
        }
        this.instance = null;
    }
}
