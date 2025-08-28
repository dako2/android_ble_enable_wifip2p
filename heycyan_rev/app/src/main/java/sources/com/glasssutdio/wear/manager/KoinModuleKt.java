package com.glasssutdio.wear.manager;

import com.glasssutdio.wear.ble.connect.DeviceBindViewModel;
import com.glasssutdio.wear.depository.AiChatDepository;
import com.glasssutdio.wear.depository.AlbumDepository;
import com.glasssutdio.wear.depository.DeviceSettingDepository;
import com.glasssutdio.wear.depository.LoginDepository;
import com.glasssutdio.wear.depository.OTADepository;
import com.glasssutdio.wear.depository.SettingDepository;
import com.glasssutdio.wear.depository.TranslateDepository;
import com.glasssutdio.wear.home.album.p005vm.AlbumListViewModel;
import com.glasssutdio.wear.home.viewmodel.AIGuideActivityVM;
import com.glasssutdio.wear.home.viewmodel.AiTranslateVM;
import com.glasssutdio.wear.home.viewmodel.CheckVersionVM;
import com.glasssutdio.wear.home.viewmodel.HomeFragmentViewModel;
import com.glasssutdio.wear.home.viewmodel.LoginActivityVM;
import com.glasssutdio.wear.home.viewmodel.MainActivityVM;
import com.glasssutdio.wear.home.viewmodel.ModelAbilityActivityVM;
import com.glasssutdio.wear.home.viewmodel.RetrievePasswordActivityVM;
import com.glasssutdio.wear.home.viewmodel.SettingFragmentVM;
import com.glasssutdio.wear.home.viewmodel.SplashActivityVM;
import com.glasssutdio.wear.home.viewmodel.UserGuideFragmentVM;
import com.glasssutdio.wear.home.viewmodel.UserInfoEditActivityVM;
import com.glasssutdio.wear.ota.OTAViewModel;
import com.glasssutdio.wear.p003ai.p004vm.AiChatViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.viewmodel.dsl.ModuleExtKt;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.DefinitionFactory;
import org.koin.core.definition.Kind;
import org.koin.core.definition.Options;
import org.koin.core.module.Module;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;
import org.koin.dsl.ModuleKt;

/* compiled from: KoinModule.kt */
@Metadata(m606d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0011\u0010\u0005\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0011\u0010\b\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, m607d2 = {"appModule", "", "Lorg/koin/core/module/Module;", "getAppModule", "()Ljava/util/List;", "repositoryModule", "getRepositoryModule", "()Lorg/koin/core/module/Module;", "viewModelModule", "getViewModelModule", "app_release"}, m608k = 2, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class KoinModuleKt {
    private static final List<Module> appModule;
    private static final Module repositoryModule;
    private static final Module viewModelModule;

    static {
        Module moduleModule$default = ModuleKt.module$default(false, false, new Function1<Module, Unit>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$viewModelModule$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Module module) {
                invoke2(module);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Module module) {
                Intrinsics.checkNotNullParameter(module, "$this$module");
                C10981 c10981 = new Function2<Scope, DefinitionParameters, HomeFragmentViewModel>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$viewModelModule$1.1
                    @Override // kotlin.jvm.functions.Function2
                    public final HomeFragmentViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new HomeFragmentViewModel();
                    }
                };
                DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
                Kind kind = Kind.Factory;
                BeanDefinition beanDefinition = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(HomeFragmentViewModel.class));
                beanDefinition.setDefinition(c10981);
                beanDefinition.setKind(kind);
                module.declareDefinition(beanDefinition, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition);
                C11062 c11062 = new Function2<Scope, DefinitionParameters, AlbumListViewModel>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$viewModelModule$1.2
                    @Override // kotlin.jvm.functions.Function2
                    public final AlbumListViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new AlbumListViewModel((AlbumDepository) viewModel.get(Reflection.getOrCreateKotlinClass(AlbumDepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory2 = DefinitionFactory.INSTANCE;
                Kind kind2 = Kind.Factory;
                BeanDefinition beanDefinition2 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(AlbumListViewModel.class));
                beanDefinition2.setDefinition(c11062);
                beanDefinition2.setKind(kind2);
                module.declareDefinition(beanDefinition2, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition2);
                C11073 c11073 = new Function2<Scope, DefinitionParameters, DeviceBindViewModel>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$viewModelModule$1.3
                    @Override // kotlin.jvm.functions.Function2
                    public final DeviceBindViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new DeviceBindViewModel((DeviceSettingDepository) viewModel.get(Reflection.getOrCreateKotlinClass(DeviceSettingDepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory3 = DefinitionFactory.INSTANCE;
                Kind kind3 = Kind.Factory;
                BeanDefinition beanDefinition3 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(DeviceBindViewModel.class));
                beanDefinition3.setDefinition(c11073);
                beanDefinition3.setKind(kind3);
                module.declareDefinition(beanDefinition3, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition3);
                C11084 c11084 = new Function2<Scope, DefinitionParameters, LoginActivityVM>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$viewModelModule$1.4
                    @Override // kotlin.jvm.functions.Function2
                    public final LoginActivityVM invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new LoginActivityVM();
                    }
                };
                DefinitionFactory definitionFactory4 = DefinitionFactory.INSTANCE;
                Kind kind4 = Kind.Factory;
                BeanDefinition beanDefinition4 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(LoginActivityVM.class));
                beanDefinition4.setDefinition(c11084);
                beanDefinition4.setKind(kind4);
                module.declareDefinition(beanDefinition4, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition4);
                C11095 c11095 = new Function2<Scope, DefinitionParameters, AIGuideActivityVM>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$viewModelModule$1.5
                    @Override // kotlin.jvm.functions.Function2
                    public final AIGuideActivityVM invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new AIGuideActivityVM();
                    }
                };
                DefinitionFactory definitionFactory5 = DefinitionFactory.INSTANCE;
                Kind kind5 = Kind.Factory;
                BeanDefinition beanDefinition5 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(AIGuideActivityVM.class));
                beanDefinition5.setDefinition(c11095);
                beanDefinition5.setKind(kind5);
                module.declareDefinition(beanDefinition5, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition5);
                C11106 c11106 = new Function2<Scope, DefinitionParameters, AiTranslateVM>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$viewModelModule$1.6
                    @Override // kotlin.jvm.functions.Function2
                    public final AiTranslateVM invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new AiTranslateVM((TranslateDepository) viewModel.get(Reflection.getOrCreateKotlinClass(TranslateDepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory6 = DefinitionFactory.INSTANCE;
                Kind kind6 = Kind.Factory;
                BeanDefinition beanDefinition6 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(AiTranslateVM.class));
                beanDefinition6.setDefinition(c11106);
                beanDefinition6.setKind(kind6);
                module.declareDefinition(beanDefinition6, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition6);
                C11117 c11117 = new Function2<Scope, DefinitionParameters, ModelAbilityActivityVM>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$viewModelModule$1.7
                    @Override // kotlin.jvm.functions.Function2
                    public final ModelAbilityActivityVM invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new ModelAbilityActivityVM();
                    }
                };
                DefinitionFactory definitionFactory7 = DefinitionFactory.INSTANCE;
                Kind kind7 = Kind.Factory;
                BeanDefinition beanDefinition7 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(ModelAbilityActivityVM.class));
                beanDefinition7.setDefinition(c11117);
                beanDefinition7.setKind(kind7);
                module.declareDefinition(beanDefinition7, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition7);
                C11128 c11128 = new Function2<Scope, DefinitionParameters, UserGuideFragmentVM>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$viewModelModule$1.8
                    @Override // kotlin.jvm.functions.Function2
                    public final UserGuideFragmentVM invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new UserGuideFragmentVM();
                    }
                };
                DefinitionFactory definitionFactory8 = DefinitionFactory.INSTANCE;
                Kind kind8 = Kind.Factory;
                BeanDefinition beanDefinition8 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(UserGuideFragmentVM.class));
                beanDefinition8.setDefinition(c11128);
                beanDefinition8.setKind(kind8);
                module.declareDefinition(beanDefinition8, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition8);
                C11139 c11139 = new Function2<Scope, DefinitionParameters, AiChatViewModel>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$viewModelModule$1.9
                    @Override // kotlin.jvm.functions.Function2
                    public final AiChatViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new AiChatViewModel((AiChatDepository) viewModel.get(Reflection.getOrCreateKotlinClass(AiChatDepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory9 = DefinitionFactory.INSTANCE;
                Kind kind9 = Kind.Factory;
                BeanDefinition beanDefinition9 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(AiChatViewModel.class));
                beanDefinition9.setDefinition(c11139);
                beanDefinition9.setKind(kind9);
                module.declareDefinition(beanDefinition9, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition9);
                C109910 c109910 = new Function2<Scope, DefinitionParameters, SettingFragmentVM>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$viewModelModule$1.10
                    @Override // kotlin.jvm.functions.Function2
                    public final SettingFragmentVM invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new SettingFragmentVM();
                    }
                };
                DefinitionFactory definitionFactory10 = DefinitionFactory.INSTANCE;
                Kind kind10 = Kind.Factory;
                BeanDefinition beanDefinition10 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(SettingFragmentVM.class));
                beanDefinition10.setDefinition(c109910);
                beanDefinition10.setKind(kind10);
                module.declareDefinition(beanDefinition10, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition10);
                C110011 c110011 = new Function2<Scope, DefinitionParameters, UserInfoEditActivityVM>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$viewModelModule$1.11
                    @Override // kotlin.jvm.functions.Function2
                    public final UserInfoEditActivityVM invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new UserInfoEditActivityVM();
                    }
                };
                DefinitionFactory definitionFactory11 = DefinitionFactory.INSTANCE;
                Kind kind11 = Kind.Factory;
                BeanDefinition beanDefinition11 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(UserInfoEditActivityVM.class));
                beanDefinition11.setDefinition(c110011);
                beanDefinition11.setKind(kind11);
                module.declareDefinition(beanDefinition11, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition11);
                C110112 c110112 = new Function2<Scope, DefinitionParameters, RetrievePasswordActivityVM>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$viewModelModule$1.12
                    @Override // kotlin.jvm.functions.Function2
                    public final RetrievePasswordActivityVM invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new RetrievePasswordActivityVM();
                    }
                };
                DefinitionFactory definitionFactory12 = DefinitionFactory.INSTANCE;
                Kind kind12 = Kind.Factory;
                BeanDefinition beanDefinition12 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(RetrievePasswordActivityVM.class));
                beanDefinition12.setDefinition(c110112);
                beanDefinition12.setKind(kind12);
                module.declareDefinition(beanDefinition12, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition12);
                C110213 c110213 = new Function2<Scope, DefinitionParameters, CheckVersionVM>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$viewModelModule$1.13
                    @Override // kotlin.jvm.functions.Function2
                    public final CheckVersionVM invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new CheckVersionVM();
                    }
                };
                DefinitionFactory definitionFactory13 = DefinitionFactory.INSTANCE;
                Kind kind13 = Kind.Factory;
                BeanDefinition beanDefinition13 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(CheckVersionVM.class));
                beanDefinition13.setDefinition(c110213);
                beanDefinition13.setKind(kind13);
                module.declareDefinition(beanDefinition13, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition13);
                C110314 c110314 = new Function2<Scope, DefinitionParameters, OTAViewModel>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$viewModelModule$1.14
                    @Override // kotlin.jvm.functions.Function2
                    public final OTAViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new OTAViewModel((OTADepository) viewModel.get(Reflection.getOrCreateKotlinClass(OTADepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory14 = DefinitionFactory.INSTANCE;
                Kind kind14 = Kind.Factory;
                BeanDefinition beanDefinition14 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(OTAViewModel.class));
                beanDefinition14.setDefinition(c110314);
                beanDefinition14.setKind(kind14);
                module.declareDefinition(beanDefinition14, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition14);
                C110415 c110415 = new Function2<Scope, DefinitionParameters, SplashActivityVM>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$viewModelModule$1.15
                    @Override // kotlin.jvm.functions.Function2
                    public final SplashActivityVM invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new SplashActivityVM();
                    }
                };
                DefinitionFactory definitionFactory15 = DefinitionFactory.INSTANCE;
                Kind kind15 = Kind.Factory;
                BeanDefinition beanDefinition15 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(SplashActivityVM.class));
                beanDefinition15.setDefinition(c110415);
                beanDefinition15.setKind(kind15);
                module.declareDefinition(beanDefinition15, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition15);
                C110516 c110516 = new Function2<Scope, DefinitionParameters, MainActivityVM>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$viewModelModule$1.16
                    @Override // kotlin.jvm.functions.Function2
                    public final MainActivityVM invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new MainActivityVM();
                    }
                };
                DefinitionFactory definitionFactory16 = DefinitionFactory.INSTANCE;
                Kind kind16 = Kind.Factory;
                BeanDefinition beanDefinition16 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(MainActivityVM.class));
                beanDefinition16.setDefinition(c110516);
                beanDefinition16.setKind(kind16);
                module.declareDefinition(beanDefinition16, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition16);
            }
        }, 3, null);
        viewModelModule = moduleModule$default;
        Module moduleModule$default2 = ModuleKt.module$default(false, false, new Function1<Module, Unit>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$repositoryModule$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Module module) {
                invoke2(module);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Module module) {
                Intrinsics.checkNotNullParameter(module, "$this$module");
                C10911 c10911 = new Function2<Scope, DefinitionParameters, AlbumDepository>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$repositoryModule$1.1
                    @Override // kotlin.jvm.functions.Function2
                    public final AlbumDepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new AlbumDepository();
                    }
                };
                DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
                Kind kind = Kind.Single;
                BeanDefinition beanDefinition = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(AlbumDepository.class));
                beanDefinition.setDefinition(c10911);
                beanDefinition.setKind(kind);
                module.declareDefinition(beanDefinition, new Options(false, false));
                C10922 c10922 = new Function2<Scope, DefinitionParameters, DeviceSettingDepository>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$repositoryModule$1.2
                    @Override // kotlin.jvm.functions.Function2
                    public final DeviceSettingDepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new DeviceSettingDepository();
                    }
                };
                DefinitionFactory definitionFactory2 = DefinitionFactory.INSTANCE;
                Kind kind2 = Kind.Single;
                BeanDefinition beanDefinition2 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(DeviceSettingDepository.class));
                beanDefinition2.setDefinition(c10922);
                beanDefinition2.setKind(kind2);
                module.declareDefinition(beanDefinition2, new Options(false, false));
                C10933 c10933 = new Function2<Scope, DefinitionParameters, AiChatDepository>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$repositoryModule$1.3
                    @Override // kotlin.jvm.functions.Function2
                    public final AiChatDepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new AiChatDepository();
                    }
                };
                DefinitionFactory definitionFactory3 = DefinitionFactory.INSTANCE;
                Kind kind3 = Kind.Single;
                BeanDefinition beanDefinition3 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(AiChatDepository.class));
                beanDefinition3.setDefinition(c10933);
                beanDefinition3.setKind(kind3);
                module.declareDefinition(beanDefinition3, new Options(false, false));
                C10944 c10944 = new Function2<Scope, DefinitionParameters, OTADepository>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$repositoryModule$1.4
                    @Override // kotlin.jvm.functions.Function2
                    public final OTADepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new OTADepository();
                    }
                };
                DefinitionFactory definitionFactory4 = DefinitionFactory.INSTANCE;
                Kind kind4 = Kind.Single;
                BeanDefinition beanDefinition4 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(OTADepository.class));
                beanDefinition4.setDefinition(c10944);
                beanDefinition4.setKind(kind4);
                module.declareDefinition(beanDefinition4, new Options(false, false));
                C10955 c10955 = new Function2<Scope, DefinitionParameters, LoginDepository>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$repositoryModule$1.5
                    @Override // kotlin.jvm.functions.Function2
                    public final LoginDepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new LoginDepository();
                    }
                };
                DefinitionFactory definitionFactory5 = DefinitionFactory.INSTANCE;
                Kind kind5 = Kind.Single;
                BeanDefinition beanDefinition5 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(LoginDepository.class));
                beanDefinition5.setDefinition(c10955);
                beanDefinition5.setKind(kind5);
                module.declareDefinition(beanDefinition5, new Options(false, false));
                C10966 c10966 = new Function2<Scope, DefinitionParameters, SettingDepository>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$repositoryModule$1.6
                    @Override // kotlin.jvm.functions.Function2
                    public final SettingDepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new SettingDepository();
                    }
                };
                DefinitionFactory definitionFactory6 = DefinitionFactory.INSTANCE;
                Kind kind6 = Kind.Single;
                BeanDefinition beanDefinition6 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(SettingDepository.class));
                beanDefinition6.setDefinition(c10966);
                beanDefinition6.setKind(kind6);
                module.declareDefinition(beanDefinition6, new Options(false, false));
                C10977 c10977 = new Function2<Scope, DefinitionParameters, TranslateDepository>() { // from class: com.glasssutdio.wear.manager.KoinModuleKt$repositoryModule$1.7
                    @Override // kotlin.jvm.functions.Function2
                    public final TranslateDepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new TranslateDepository();
                    }
                };
                DefinitionFactory definitionFactory7 = DefinitionFactory.INSTANCE;
                Kind kind7 = Kind.Single;
                BeanDefinition beanDefinition7 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(TranslateDepository.class));
                beanDefinition7.setDefinition(c10977);
                beanDefinition7.setKind(kind7);
                module.declareDefinition(beanDefinition7, new Options(false, false));
            }
        }, 3, null);
        repositoryModule = moduleModule$default2;
        appModule = CollectionsKt.listOf((Object[]) new Module[]{moduleModule$default, moduleModule$default2});
    }

    public static final Module getViewModelModule() {
        return viewModelModule;
    }

    public static final Module getRepositoryModule() {
        return repositoryModule;
    }

    public static final List<Module> getAppModule() {
        return appModule;
    }
}
