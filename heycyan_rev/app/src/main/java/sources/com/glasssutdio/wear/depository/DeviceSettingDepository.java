package com.glasssutdio.wear.depository;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.glasssutdio.wear.GlassApplication;
import com.glasssutdio.wear.all.ThreadExtKt;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.MoshiUtils;
import com.glasssutdio.wear.all.utils.MoshiUtilsKt;
import com.glasssutdio.wear.api.NetState;
import com.glasssutdio.wear.api.QcResponse;
import com.glasssutdio.wear.api.QcResponseKt;
import com.glasssutdio.wear.api.QcRetrofitClient;
import com.glasssutdio.wear.api.RetCodeValue;
import com.glasssutdio.wear.api.response.DevicePictureResp;
import com.glasssutdio.wear.database.GlassDatabase;
import com.glasssutdio.wear.database.dao.GlassDeviceSettingDao;
import com.glasssutdio.wear.database.entity.DeviceSettingEntity;
import com.glasssutdio.wear.depository.bean.DevicePictureBean;
import com.glasssutdio.wear.depository.bean.DeviceSetting;
import com.glasssutdio.wear.depository.bean.DeviceSettingAction;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import com.squareup.moshi.JsonAdapter;
import java.io.IOException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: DeviceSettingDepository.kt */
@Metadata(m606d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ!\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n2\u0006\u0010\f\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\rJ%\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\n2\u0006\u0010\u0011\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\rJ!\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\n2\u0006\u0010\f\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u000e\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ%\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u000f0\n2\u0006\u0010\f\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000bJ\u000e\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001dR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, m607d2 = {"Lcom/glasssutdio/wear/depository/DeviceSettingDepository;", "", "()V", "glassDeviceSettingDao", "Lcom/glasssutdio/wear/database/dao/GlassDeviceSettingDao;", "getAppName", "", "context", "Landroid/content/Context;", "getDevicePictureFromLocal", "Lkotlinx/coroutines/flow/Flow;", "Lcom/glasssutdio/wear/depository/bean/DevicePictureBean;", "mac", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDevicePictureFromServer", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/api/response/DevicePictureResp;", "hardwareVersion", "getDeviceSetting", "Lcom/glasssutdio/wear/depository/bean/DeviceSetting;", "getPackageName", "getUniqueIdFromServer", "getVersionCode", "", "saveDevicePicture", "", "bean", "saveDeviceSetting", "deviceSettingEntity", "Lcom/glasssutdio/wear/database/entity/DeviceSettingEntity;", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class DeviceSettingDepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<DeviceSettingDepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<DeviceSettingDepository>() { // from class: com.glasssutdio.wear.depository.DeviceSettingDepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DeviceSettingDepository invoke() {
            return new DeviceSettingDepository();
        }
    });
    private final GlassDeviceSettingDao glassDeviceSettingDao = GlassDatabase.INSTANCE.getDatabase(GlassApplication.INSTANCE.getCONTEXT()).glassDeviceSettingDao();

    /* compiled from: DeviceSettingDepository.kt */
    @Metadata(m606d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/database/entity/DeviceSettingEntity;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$2", m620f = "DeviceSettingDepository.kt", m621i = {}, m622l = {ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$2 */
    static final class C09132 extends SuspendLambda implements Function2<FlowCollector<? super DeviceSettingEntity>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09132(String str, Continuation<? super C09132> continuation) {
            super(2, continuation);
            this.$mac = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09132 c09132 = DeviceSettingDepository.this.new C09132(this.$mac, continuation);
            c09132.L$0 = obj;
            return c09132;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super DeviceSettingEntity> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09132) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(DeviceSettingDepository.this.glassDeviceSettingDao.queryByMacAndAction(this.$mac, DeviceSettingAction.GlassSetting), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final Object getDeviceSetting(String str, Continuation<? super Flow<DeviceSetting>> continuation) {
        final Flow flow = FlowKt.flow(new C09132(str, null));
        return FlowKt.m2434catch(FlowKt.flowOn(FlowKt.onStart(new Flow<DeviceSetting>() { // from class: com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$$inlined$map$1

            /* compiled from: Emitters.kt */
            @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, m607d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
            /* renamed from: com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$$inlined$map$1$2 */
            public static final class C09052<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: Emitters.kt */
                @Metadata(m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
                @DebugMetadata(m619c = "com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$$inlined$map$1$2", m620f = "DeviceSettingDepository.kt", m621i = {}, m622l = {223}, m623m = "emit", m624n = {}, m625s = {})
                /* renamed from: com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$$inlined$map$1$2$1, reason: invalid class name */
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return C09052.this.emit(null, this);
                    }
                }

                public C09052(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final Object emit(Object obj, Continuation continuation) throws IOException {
                    AnonymousClass1 anonymousClass1;
                    if (continuation instanceof AnonymousClass1) {
                        anonymousClass1 = (AnonymousClass1) continuation;
                        if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                            anonymousClass1.label -= Integer.MIN_VALUE;
                        } else {
                            anonymousClass1 = new AnonymousClass1(continuation);
                        }
                    }
                    Object obj2 = anonymousClass1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = anonymousClass1.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj2);
                        FlowCollector flowCollector = this.$this_unsafeFlow;
                        String content = ((DeviceSettingEntity) obj).getContent();
                        JsonAdapter<T> jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x004f: INVOKE (r2v5 'jsonAdapterAdapter' com.squareup.moshi.JsonAdapter<T>) = 
                              (wrap:com.squareup.moshi.Moshi:0x0042: INVOKE 
                              (wrap:com.glasssutdio.wear.all.utils.MoshiUtils:0x0040: SGET  A[WRAPPED] (LINE:225) com.glasssutdio.wear.all.utils.MoshiUtils.INSTANCE com.glasssutdio.wear.all.utils.MoshiUtils)
                             VIRTUAL call: com.glasssutdio.wear.all.utils.MoshiUtils.getMoshiBuild():com.squareup.moshi.Moshi A[MD:():com.squareup.moshi.Moshi (m), WRAPPED] (LINE:227))
                              (wrap:java.lang.reflect.Type:0x004b: INVOKE 
                              (wrap:com.glasssutdio.wear.all.utils.TypeToken<com.glasssutdio.wear.depository.bean.DeviceSetting>:0x0048: CONSTRUCTOR  A[MD:():void (m), WRAPPED] call: com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$lambda$0$$inlined$fromJson$1.<init>():void type: CONSTRUCTOR)
                             VIRTUAL call: com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$lambda$0$$inlined$fromJson$1.getType():java.lang.reflect.Type A[MD:():java.lang.reflect.Type (m), WRAPPED])
                             VIRTUAL call: com.squareup.moshi.Moshi.adapter(java.lang.reflect.Type):com.squareup.moshi.JsonAdapter A[DECLARE_VAR, MD:<T>:(java.lang.reflect.Type):com.squareup.moshi.JsonAdapter<T> (m)] (LINE:227) in method: com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$$inlined$map$1.2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object, file: classes.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:298)
                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:277)
                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$lambda$0$$inlined$fromJson$1, state: NOT_LOADED
                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:304)
                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                            	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:97)
                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:878)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                            	... 21 more
                            */
                        /*
                            this = this;
                            boolean r0 = r7 instanceof com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$$inlined$map$1.C09052.AnonymousClass1
                            if (r0 == 0) goto L14
                            r0 = r7
                            com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$$inlined$map$1$2$1 r0 = (com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$$inlined$map$1.C09052.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r1 = r1 & r2
                            if (r1 == 0) goto L14
                            int r7 = r0.label
                            int r7 = r7 - r2
                            r0.label = r7
                            goto L19
                        L14:
                            com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$$inlined$map$1$2$1 r0 = new com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$$inlined$map$1$2$1
                            r0.<init>(r7)
                        L19:
                            java.lang.Object r7 = r0.result
                            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L32
                            if (r2 != r3) goto L2a
                            kotlin.ResultKt.throwOnFailure(r7)
                            goto L65
                        L2a:
                            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                            r6.<init>(r7)
                            throw r6
                        L32:
                            kotlin.ResultKt.throwOnFailure(r7)
                            kotlinx.coroutines.flow.FlowCollector r7 = r5.$this_unsafeFlow
                            r2 = r0
                            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                            com.glasssutdio.wear.database.entity.DeviceSettingEntity r6 = (com.glasssutdio.wear.database.entity.DeviceSettingEntity) r6
                            java.lang.String r6 = r6.getContent()
                            com.glasssutdio.wear.all.utils.MoshiUtils r2 = com.glasssutdio.wear.all.utils.MoshiUtils.INSTANCE
                            com.squareup.moshi.Moshi r2 = r2.getMoshiBuild()
                            com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$lambda$0$$inlined$fromJson$1 r4 = new com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$lambda$0$$inlined$fromJson$1
                            r4.<init>()
                            java.lang.reflect.Type r4 = r4.getType()
                            com.squareup.moshi.JsonAdapter r2 = r2.adapter(r4)
                            java.lang.String r4 = "adapter(...)"
                            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
                            java.lang.Object r6 = r2.fromJson(r6)
                            r0.label = r3
                            java.lang.Object r6 = r7.emit(r6, r0)
                            if (r6 != r1) goto L65
                            return r1
                        L65:
                            kotlin.Unit r6 = kotlin.Unit.INSTANCE
                            return r6
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$$inlined$map$1.C09052.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                public Object collect(FlowCollector<? super DeviceSetting> flowCollector, Continuation continuation2) {
                    Object objCollect = flow.collect(new C09052(flowCollector), continuation2);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            }, new C09144(null)), Dispatchers.getIO()), new C09155(str, null));
        }

        /* compiled from: DeviceSettingDepository.kt */
        @Metadata(m606d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/depository/bean/DeviceSetting;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$4", m620f = "DeviceSettingDepository.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$4 */
        static final class C09144 extends SuspendLambda implements Function2<FlowCollector<? super DeviceSetting>, Continuation<? super Unit>, Object> {
            int label;

            C09144(Continuation<? super C09144> continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C09144(continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(FlowCollector<? super DeviceSetting> flowCollector, Continuation<? super Unit> continuation) {
                return ((C09144) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
        }

        /* compiled from: DeviceSettingDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/depository/bean/DeviceSetting;", "t", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$5", m620f = "DeviceSettingDepository.kt", m621i = {}, m622l = {46}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.DeviceSettingDepository$getDeviceSetting$5 */
        static final class C09155 extends SuspendLambda implements Function3<FlowCollector<? super DeviceSetting>, Throwable, Continuation<? super Unit>, Object> {
            final /* synthetic */ String $mac;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C09155(String str, Continuation<? super C09155> continuation) {
                super(3, continuation);
                this.$mac = str;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(FlowCollector<? super DeviceSetting> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
                C09155 c09155 = DeviceSettingDepository.this.new C09155(this.$mac, continuation);
                c09155.L$0 = flowCollector;
                return c09155.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    FlowCollector flowCollector = (FlowCollector) this.L$0;
                    final DeviceSettingDepository deviceSettingDepository = DeviceSettingDepository.this;
                    final String str = this.$mac;
                    ThreadExtKt.ktxRunOnBgSingle(flowCollector, new Function1<FlowCollector<? super DeviceSetting>, Unit>() { // from class: com.glasssutdio.wear.depository.DeviceSettingDepository.getDeviceSetting.5.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(FlowCollector<? super DeviceSetting> flowCollector2) {
                            invoke2(flowCollector2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(FlowCollector<? super DeviceSetting> ktxRunOnBgSingle) {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            deviceSettingDepository.glassDeviceSettingDao.insert(new DeviceSettingEntity(str, DeviceSettingAction.GlassSetting, MoshiUtilsKt.toJson(new DeviceSetting(false, 1, null))));
                        }
                    });
                    this.label = 1;
                    if (flowCollector.emit(new DeviceSetting(false, 1, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* compiled from: DeviceSettingDepository.kt */
        @Metadata(m606d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/database/entity/DeviceSettingEntity;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$2", m620f = "DeviceSettingDepository.kt", m621i = {}, m622l = {52}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$2 */
        static final class C09072 extends SuspendLambda implements Function2<FlowCollector<? super DeviceSettingEntity>, Continuation<? super Unit>, Object> {
            final /* synthetic */ String $mac;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C09072(String str, Continuation<? super C09072> continuation) {
                super(2, continuation);
                this.$mac = str;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C09072 c09072 = DeviceSettingDepository.this.new C09072(this.$mac, continuation);
                c09072.L$0 = obj;
                return c09072;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(FlowCollector<? super DeviceSettingEntity> flowCollector, Continuation<? super Unit> continuation) {
                return ((C09072) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (((FlowCollector) this.L$0).emit(DeviceSettingDepository.this.glassDeviceSettingDao.queryByMacAndAction(this.$mac, DeviceSettingAction.DevicePicture), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        public final Object getDevicePictureFromLocal(String str, Continuation<? super Flow<DevicePictureBean>> continuation) {
            final Flow flow = FlowKt.flow(new C09072(str, null));
            return FlowKt.m2434catch(FlowKt.flowOn(FlowKt.onStart(new Flow<DevicePictureBean>() { // from class: com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$$inlined$map$1

                /* compiled from: Emitters.kt */
                @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, m607d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
                /* renamed from: com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$$inlined$map$1$2 */
                public static final class C09032<T> implements FlowCollector {
                    final /* synthetic */ FlowCollector $this_unsafeFlow;

                    /* compiled from: Emitters.kt */
                    @Metadata(m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
                    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$$inlined$map$1$2", m620f = "DeviceSettingDepository.kt", m621i = {}, m622l = {223}, m623m = "emit", m624n = {}, m625s = {})
                    /* renamed from: com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$$inlined$map$1$2$1, reason: invalid class name */
                    public static final class AnonymousClass1 extends ContinuationImpl {
                        Object L$0;
                        int label;
                        /* synthetic */ Object result;

                        public AnonymousClass1(Continuation continuation) {
                            super(continuation);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            this.result = obj;
                            this.label |= Integer.MIN_VALUE;
                            return C09032.this.emit(null, this);
                        }
                    }

                    public C09032(FlowCollector flowCollector) {
                        this.$this_unsafeFlow = flowCollector;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                    */
                    public final Object emit(Object obj, Continuation continuation) throws IOException {
                        AnonymousClass1 anonymousClass1;
                        if (continuation instanceof AnonymousClass1) {
                            anonymousClass1 = (AnonymousClass1) continuation;
                            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                                anonymousClass1.label -= Integer.MIN_VALUE;
                            } else {
                                anonymousClass1 = new AnonymousClass1(continuation);
                            }
                        }
                        Object obj2 = anonymousClass1.result;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = anonymousClass1.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj2);
                            FlowCollector flowCollector = this.$this_unsafeFlow;
                            String content = ((DeviceSettingEntity) obj).getContent();
                            JsonAdapter<T> jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(
                            /*  JADX ERROR: Method code generation error
                                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x004f: INVOKE (r2v5 'jsonAdapterAdapter' com.squareup.moshi.JsonAdapter<T>) = 
                                  (wrap:com.squareup.moshi.Moshi:0x0042: INVOKE 
                                  (wrap:com.glasssutdio.wear.all.utils.MoshiUtils:0x0040: SGET  A[WRAPPED] (LINE:225) com.glasssutdio.wear.all.utils.MoshiUtils.INSTANCE com.glasssutdio.wear.all.utils.MoshiUtils)
                                 VIRTUAL call: com.glasssutdio.wear.all.utils.MoshiUtils.getMoshiBuild():com.squareup.moshi.Moshi A[MD:():com.squareup.moshi.Moshi (m), WRAPPED] (LINE:227))
                                  (wrap:java.lang.reflect.Type:0x004b: INVOKE 
                                  (wrap:com.glasssutdio.wear.all.utils.TypeToken<com.glasssutdio.wear.depository.bean.DevicePictureBean>:0x0048: CONSTRUCTOR  A[MD:():void (m), WRAPPED] call: com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$lambda$1$$inlined$fromJson$1.<init>():void type: CONSTRUCTOR)
                                 VIRTUAL call: com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$lambda$1$$inlined$fromJson$1.getType():java.lang.reflect.Type A[MD:():java.lang.reflect.Type (m), WRAPPED])
                                 VIRTUAL call: com.squareup.moshi.Moshi.adapter(java.lang.reflect.Type):com.squareup.moshi.JsonAdapter A[DECLARE_VAR, MD:<T>:(java.lang.reflect.Type):com.squareup.moshi.JsonAdapter<T> (m)] (LINE:227) in method: com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$$inlined$map$1.2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object, file: classes.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:298)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:277)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$lambda$1$$inlined$fromJson$1, state: NOT_LOADED
                                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:304)
                                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:97)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:878)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                	... 21 more
                                */
                            /*
                                this = this;
                                boolean r0 = r7 instanceof com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$$inlined$map$1.C09032.AnonymousClass1
                                if (r0 == 0) goto L14
                                r0 = r7
                                com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$$inlined$map$1$2$1 r0 = (com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$$inlined$map$1.C09032.AnonymousClass1) r0
                                int r1 = r0.label
                                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                                r1 = r1 & r2
                                if (r1 == 0) goto L14
                                int r7 = r0.label
                                int r7 = r7 - r2
                                r0.label = r7
                                goto L19
                            L14:
                                com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$$inlined$map$1$2$1 r0 = new com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$$inlined$map$1$2$1
                                r0.<init>(r7)
                            L19:
                                java.lang.Object r7 = r0.result
                                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                                int r2 = r0.label
                                r3 = 1
                                if (r2 == 0) goto L32
                                if (r2 != r3) goto L2a
                                kotlin.ResultKt.throwOnFailure(r7)
                                goto L65
                            L2a:
                                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                                r6.<init>(r7)
                                throw r6
                            L32:
                                kotlin.ResultKt.throwOnFailure(r7)
                                kotlinx.coroutines.flow.FlowCollector r7 = r5.$this_unsafeFlow
                                r2 = r0
                                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                                com.glasssutdio.wear.database.entity.DeviceSettingEntity r6 = (com.glasssutdio.wear.database.entity.DeviceSettingEntity) r6
                                java.lang.String r6 = r6.getContent()
                                com.glasssutdio.wear.all.utils.MoshiUtils r2 = com.glasssutdio.wear.all.utils.MoshiUtils.INSTANCE
                                com.squareup.moshi.Moshi r2 = r2.getMoshiBuild()
                                com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$lambda$1$$inlined$fromJson$1 r4 = new com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$lambda$1$$inlined$fromJson$1
                                r4.<init>()
                                java.lang.reflect.Type r4 = r4.getType()
                                com.squareup.moshi.JsonAdapter r2 = r2.adapter(r4)
                                java.lang.String r4 = "adapter(...)"
                                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
                                java.lang.Object r6 = r2.fromJson(r6)
                                r0.label = r3
                                java.lang.Object r6 = r7.emit(r6, r0)
                                if (r6 != r1) goto L65
                                return r1
                            L65:
                                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                                return r6
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$$inlined$map$1.C09032.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                        }
                    }

                    @Override // kotlinx.coroutines.flow.Flow
                    public Object collect(FlowCollector<? super DevicePictureBean> flowCollector, Continuation continuation2) {
                        Object objCollect = flow.collect(new C09032(flowCollector), continuation2);
                        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                    }
                }, new C09084(null)), Dispatchers.getIO()), new C09095(null));
            }

            /* compiled from: DeviceSettingDepository.kt */
            @Metadata(m606d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/depository/bean/DevicePictureBean;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
            @DebugMetadata(m619c = "com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$4", m620f = "DeviceSettingDepository.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
            /* renamed from: com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$4 */
            static final class C09084 extends SuspendLambda implements Function2<FlowCollector<? super DevicePictureBean>, Continuation<? super Unit>, Object> {
                int label;

                C09084(Continuation<? super C09084> continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C09084(continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(FlowCollector<? super DevicePictureBean> flowCollector, Continuation<? super Unit> continuation) {
                    return ((C09084) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
            }

            /* compiled from: DeviceSettingDepository.kt */
            @Metadata(m606d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/depository/bean/DevicePictureBean;", "t", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
            @DebugMetadata(m619c = "com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$5", m620f = "DeviceSettingDepository.kt", m621i = {}, m622l = {58}, m623m = "invokeSuspend", m624n = {}, m625s = {})
            /* renamed from: com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromLocal$5 */
            static final class C09095 extends SuspendLambda implements Function3<FlowCollector<? super DevicePictureBean>, Throwable, Continuation<? super Unit>, Object> {
                private /* synthetic */ Object L$0;
                int label;

                C09095(Continuation<? super C09095> continuation) {
                    super(3, continuation);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(FlowCollector<? super DevicePictureBean> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
                    C09095 c09095 = new C09095(continuation);
                    c09095.L$0 = flowCollector;
                    return c09095.invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        if (((FlowCollector) this.L$0).emit(new DevicePictureBean("", "", ""), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            public final void saveDevicePicture(final DevicePictureBean bean) {
                Intrinsics.checkNotNullParameter(bean, "bean");
                ThreadExtKt.ktxRunOnBgSingle(this, new Function1<DeviceSettingDepository, Unit>() { // from class: com.glasssutdio.wear.depository.DeviceSettingDepository.saveDevicePicture.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DeviceSettingDepository deviceSettingDepository) {
                        invoke2(deviceSettingDepository);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DeviceSettingDepository ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        ktxRunOnBgSingle.glassDeviceSettingDao.insert(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.DevicePicture, MoshiUtilsKt.toJson(bean)));
                    }
                });
            }

            public final void saveDeviceSetting(final DeviceSettingEntity deviceSettingEntity) {
                Intrinsics.checkNotNullParameter(deviceSettingEntity, "deviceSettingEntity");
                ThreadExtKt.ktxRunOnBgSingle(this, new Function1<DeviceSettingDepository, Unit>() { // from class: com.glasssutdio.wear.depository.DeviceSettingDepository.saveDeviceSetting.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DeviceSettingDepository deviceSettingDepository) {
                        invoke2(deviceSettingDepository);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DeviceSettingDepository ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        ktxRunOnBgSingle.glassDeviceSettingDao.insert(deviceSettingEntity);
                    }
                });
            }

            /* compiled from: DeviceSettingDepository.kt */
            @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/api/response/DevicePictureResp;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
            @DebugMetadata(m619c = "com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromServer$2", m620f = "DeviceSettingDepository.kt", m621i = {1, 2}, m622l = {EMachine.EM_MMIX, EMachine.EM_AVR, EMachine.EM_AVR, EMachine.EM_D10V}, m623m = "invokeSuspend", m624n = {"$this$flow", "$this$flow"}, m625s = {"L$0", "L$0"})
            /* renamed from: com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromServer$2 */
            static final class C09102 extends SuspendLambda implements Function2<FlowCollector<? super NetState<DevicePictureResp>>, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $hardwareVersion;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C09102(String str, Continuation<? super C09102> continuation) {
                    super(2, continuation);
                    this.$hardwareVersion = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C09102 c09102 = new C09102(this.$hardwareVersion, continuation);
                    c09102.L$0 = obj;
                    return c09102;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(FlowCollector<? super NetState<DevicePictureResp>> flowCollector, Continuation<? super Unit> continuation) {
                    return ((C09102) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:26:0x0096 A[RETURN] */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final Object invokeSuspend(Object obj) {
                    FlowCollector flowCollector;
                    Object devicePicture;
                    Object objSuccess;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        flowCollector = (FlowCollector) this.L$0;
                        if (this.$hardwareVersion.length() == 0) {
                            this.label = 1;
                            if (flowCollector.emit(new NetState(false, null, RetCodeValue.ErrorCode_1, null, 11, null), this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            return Unit.INSTANCE;
                        }
                        this.L$0 = flowCollector;
                        this.label = 2;
                        devicePicture = QcRetrofitClient.INSTANCE.service().getDevicePicture(this.$hardwareVersion, this);
                        if (devicePicture == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        this.L$0 = flowCollector;
                        this.label = 3;
                        objSuccess = QcResponseKt.success((QcResponse) devicePicture, new AnonymousClass1(flowCollector, null), this);
                        if (objSuccess == coroutine_suspended) {
                        }
                    } else {
                        if (i == 1) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        if (i == 2) {
                            flowCollector = (FlowCollector) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            devicePicture = obj;
                            this.L$0 = flowCollector;
                            this.label = 3;
                            objSuccess = QcResponseKt.success((QcResponse) devicePicture, new AnonymousClass1(flowCollector, null), this);
                            if (objSuccess == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 3) {
                                if (i != 4) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                                return Unit.INSTANCE;
                            }
                            flowCollector = (FlowCollector) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            objSuccess = obj;
                        }
                    }
                    this.L$0 = null;
                    this.label = 4;
                    if (QcResponseKt.error((QcResponse) objSuccess, new AnonymousClass2(flowCollector, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }

                /* compiled from: DeviceSettingDepository.kt */
                @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", "Lcom/glasssutdio/wear/api/response/DevicePictureResp;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
                @DebugMetadata(m619c = "com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromServer$2$1", m620f = "DeviceSettingDepository.kt", m621i = {}, m622l = {EMachine.EM_FR30}, m623m = "invokeSuspend", m624n = {}, m625s = {})
                /* renamed from: com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromServer$2$1, reason: invalid class name */
                static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, DevicePictureResp, Continuation<? super Unit>, Object> {
                    final /* synthetic */ FlowCollector<NetState<DevicePictureResp>> $$this$flow;
                    /* synthetic */ Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    AnonymousClass1(FlowCollector<? super NetState<DevicePictureResp>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                        super(3, continuation);
                        this.$$this$flow = flowCollector;
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(CoroutineScope coroutineScope, DevicePictureResp devicePictureResp, Continuation<? super Unit> continuation) {
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                        anonymousClass1.L$0 = devicePictureResp;
                        return anonymousClass1.invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            DevicePictureResp devicePictureResp = (DevicePictureResp) this.L$0;
                            this.label = 1;
                            if (this.$$this$flow.emit(new NetState<>(false, devicePictureResp, 0, null, 9, null), this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* compiled from: DeviceSettingDepository.kt */
                @Metadata(m606d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", "", NotificationCompat.CATEGORY_MESSAGE, ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
                @DebugMetadata(m619c = "com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromServer$2$2", m620f = "DeviceSettingDepository.kt", m621i = {}, m622l = {EMachine.EM_D30V}, m623m = "invokeSuspend", m624n = {}, m625s = {})
                /* renamed from: com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromServer$2$2, reason: invalid class name */
                static final class AnonymousClass2 extends SuspendLambda implements Function4<CoroutineScope, Integer, String, Continuation<? super Unit>, Object> {
                    final /* synthetic */ FlowCollector<NetState<DevicePictureResp>> $$this$flow;
                    /* synthetic */ int I$0;
                    /* synthetic */ Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    AnonymousClass2(FlowCollector<? super NetState<DevicePictureResp>> flowCollector, Continuation<? super AnonymousClass2> continuation) {
                        super(4, continuation);
                        this.$$this$flow = flowCollector;
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, String str, Continuation<? super Unit> continuation) {
                        return invoke(coroutineScope, num.intValue(), str, continuation);
                    }

                    public final Object invoke(CoroutineScope coroutineScope, int i, String str, Continuation<? super Unit> continuation) {
                        AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$$this$flow, continuation);
                        anonymousClass2.I$0 = i;
                        anonymousClass2.L$0 = str;
                        return anonymousClass2.invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            int i2 = this.I$0;
                            String str = (String) this.L$0;
                            this.label = 1;
                            if (this.$$this$flow.emit(new NetState<>(false, null, i2, str, 3, null), this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }
            }

            public final Object getDevicePictureFromServer(String str, Continuation<? super Flow<NetState<DevicePictureResp>>> continuation) {
                return FlowKt.m2434catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C09102(str, null)), new C09113(null)), Dispatchers.getIO()), new C09124(null));
            }

            /* compiled from: DeviceSettingDepository.kt */
            @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/api/response/DevicePictureResp;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
            @DebugMetadata(m619c = "com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromServer$3", m620f = "DeviceSettingDepository.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
            /* renamed from: com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromServer$3 */
            static final class C09113 extends SuspendLambda implements Function2<FlowCollector<? super NetState<DevicePictureResp>>, Continuation<? super Unit>, Object> {
                int label;

                C09113(Continuation<? super C09113> continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C09113(continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(FlowCollector<? super NetState<DevicePictureResp>> flowCollector, Continuation<? super Unit> continuation) {
                    return ((C09113) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
            }

            /* compiled from: DeviceSettingDepository.kt */
            @Metadata(m606d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/api/response/DevicePictureResp;", "it", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
            @DebugMetadata(m619c = "com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromServer$4", m620f = "DeviceSettingDepository.kt", m621i = {}, m622l = {EMachine.EM_PJ}, m623m = "invokeSuspend", m624n = {}, m625s = {})
            /* renamed from: com.glasssutdio.wear.depository.DeviceSettingDepository$getDevicePictureFromServer$4 */
            static final class C09124 extends SuspendLambda implements Function3<FlowCollector<? super NetState<DevicePictureResp>>, Throwable, Continuation<? super Unit>, Object> {
                private /* synthetic */ Object L$0;
                int label;

                C09124(Continuation<? super C09124> continuation) {
                    super(3, continuation);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(FlowCollector<? super NetState<DevicePictureResp>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
                    C09124 c09124 = new C09124(continuation);
                    c09124.L$0 = flowCollector;
                    return c09124.invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        if (((FlowCollector) this.L$0).emit(new NetState(false, null, RetCodeValue.ErrorCode_0, null, 11, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* compiled from: DeviceSettingDepository.kt */
            @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
            @DebugMetadata(m619c = "com.glasssutdio.wear.depository.DeviceSettingDepository$getUniqueIdFromServer$2", m620f = "DeviceSettingDepository.kt", m621i = {1, 2}, m622l = {EMachine.EM_NS32K, 100, 100, 102}, m623m = "invokeSuspend", m624n = {"$this$flow", "$this$flow"}, m625s = {"L$0", "L$0"})
            /* renamed from: com.glasssutdio.wear.depository.DeviceSettingDepository$getUniqueIdFromServer$2 */
            static final class C09162 extends SuspendLambda implements Function2<FlowCollector<? super NetState<String>>, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $mac;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C09162(String str, Continuation<? super C09162> continuation) {
                    super(2, continuation);
                    this.$mac = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C09162 c09162 = new C09162(this.$mac, continuation);
                    c09162.L$0 = obj;
                    return c09162;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super Unit> continuation) {
                    return ((C09162) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:26:0x0096 A[RETURN] */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final Object invokeSuspend(Object obj) {
                    FlowCollector flowCollector;
                    Object objUniqueMac;
                    Object objSuccess;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        flowCollector = (FlowCollector) this.L$0;
                        if (this.$mac.length() == 0) {
                            this.label = 1;
                            if (flowCollector.emit(new NetState(false, null, RetCodeValue.ErrorCode_1, null, 11, null), this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            return Unit.INSTANCE;
                        }
                        this.L$0 = flowCollector;
                        this.label = 2;
                        objUniqueMac = QcRetrofitClient.INSTANCE.service().uniqueMac(this.$mac, 2, this);
                        if (objUniqueMac == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        this.L$0 = flowCollector;
                        this.label = 3;
                        objSuccess = QcResponseKt.success((QcResponse) objUniqueMac, new AnonymousClass1(flowCollector, null), this);
                        if (objSuccess == coroutine_suspended) {
                        }
                    } else {
                        if (i == 1) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        if (i == 2) {
                            flowCollector = (FlowCollector) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            objUniqueMac = obj;
                            this.L$0 = flowCollector;
                            this.label = 3;
                            objSuccess = QcResponseKt.success((QcResponse) objUniqueMac, new AnonymousClass1(flowCollector, null), this);
                            if (objSuccess == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 3) {
                                if (i != 4) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                                return Unit.INSTANCE;
                            }
                            flowCollector = (FlowCollector) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            objSuccess = obj;
                        }
                    }
                    this.L$0 = null;
                    this.label = 4;
                    if (QcResponseKt.error((QcResponse) objSuccess, new AnonymousClass2(flowCollector, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }

                /* compiled from: DeviceSettingDepository.kt */
                @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
                @DebugMetadata(m619c = "com.glasssutdio.wear.depository.DeviceSettingDepository$getUniqueIdFromServer$2$1", m620f = "DeviceSettingDepository.kt", m621i = {}, m622l = {101}, m623m = "invokeSuspend", m624n = {}, m625s = {})
                /* renamed from: com.glasssutdio.wear.depository.DeviceSettingDepository$getUniqueIdFromServer$2$1, reason: invalid class name */
                static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, String, Continuation<? super Unit>, Object> {
                    final /* synthetic */ FlowCollector<NetState<String>> $$this$flow;
                    /* synthetic */ Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    AnonymousClass1(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                        super(3, continuation);
                        this.$$this$flow = flowCollector;
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(CoroutineScope coroutineScope, String str, Continuation<? super Unit> continuation) {
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                        anonymousClass1.L$0 = str;
                        return anonymousClass1.invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            String str = (String) this.L$0;
                            this.label = 1;
                            if (this.$$this$flow.emit(new NetState<>(false, str, 0, null, 9, null), this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* compiled from: DeviceSettingDepository.kt */
                @Metadata(m606d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", "", NotificationCompat.CATEGORY_MESSAGE, ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
                @DebugMetadata(m619c = "com.glasssutdio.wear.depository.DeviceSettingDepository$getUniqueIdFromServer$2$2", m620f = "DeviceSettingDepository.kt", m621i = {}, m622l = {EMachine.EM_CR}, m623m = "invokeSuspend", m624n = {}, m625s = {})
                /* renamed from: com.glasssutdio.wear.depository.DeviceSettingDepository$getUniqueIdFromServer$2$2, reason: invalid class name */
                static final class AnonymousClass2 extends SuspendLambda implements Function4<CoroutineScope, Integer, String, Continuation<? super Unit>, Object> {
                    final /* synthetic */ FlowCollector<NetState<String>> $$this$flow;
                    /* synthetic */ int I$0;
                    /* synthetic */ Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    AnonymousClass2(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super AnonymousClass2> continuation) {
                        super(4, continuation);
                        this.$$this$flow = flowCollector;
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, String str, Continuation<? super Unit> continuation) {
                        return invoke(coroutineScope, num.intValue(), str, continuation);
                    }

                    public final Object invoke(CoroutineScope coroutineScope, int i, String str, Continuation<? super Unit> continuation) {
                        AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$$this$flow, continuation);
                        anonymousClass2.I$0 = i;
                        anonymousClass2.L$0 = str;
                        return anonymousClass2.invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            int i2 = this.I$0;
                            String str = (String) this.L$0;
                            this.label = 1;
                            if (this.$$this$flow.emit(new NetState<>(false, null, i2, str, 3, null), this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }
            }

            public final Object getUniqueIdFromServer(String str, Continuation<? super Flow<NetState<String>>> continuation) {
                return FlowKt.m2434catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C09162(str, null)), new C09173(null)), Dispatchers.getIO()), new C09184(null));
            }

            /* compiled from: DeviceSettingDepository.kt */
            @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
            @DebugMetadata(m619c = "com.glasssutdio.wear.depository.DeviceSettingDepository$getUniqueIdFromServer$3", m620f = "DeviceSettingDepository.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
            /* renamed from: com.glasssutdio.wear.depository.DeviceSettingDepository$getUniqueIdFromServer$3 */
            static final class C09173 extends SuspendLambda implements Function2<FlowCollector<? super NetState<String>>, Continuation<? super Unit>, Object> {
                int label;

                C09173(Continuation<? super C09173> continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C09173(continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super Unit> continuation) {
                    return ((C09173) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
            }

            /* compiled from: DeviceSettingDepository.kt */
            @Metadata(m606d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "", "it", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
            @DebugMetadata(m619c = "com.glasssutdio.wear.depository.DeviceSettingDepository$getUniqueIdFromServer$4", m620f = "DeviceSettingDepository.kt", m621i = {}, m622l = {108}, m623m = "invokeSuspend", m624n = {}, m625s = {})
            /* renamed from: com.glasssutdio.wear.depository.DeviceSettingDepository$getUniqueIdFromServer$4 */
            static final class C09184 extends SuspendLambda implements Function3<FlowCollector<? super NetState<String>>, Throwable, Continuation<? super Unit>, Object> {
                private /* synthetic */ Object L$0;
                int label;

                C09184(Continuation<? super C09184> continuation) {
                    super(3, continuation);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
                    C09184 c09184 = new C09184(continuation);
                    c09184.L$0 = flowCollector;
                    return c09184.invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        if (((FlowCollector) this.L$0).emit(new NetState(false, null, RetCodeValue.ErrorCode_0, null, 11, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            public final String getPackageName(Context context) throws PackageManager.NameNotFoundException {
                Intrinsics.checkNotNullParameter(context, "context");
                PackageManager packageManager = context.getPackageManager();
                Intrinsics.checkNotNullExpressionValue(packageManager, "getPackageManager(...)");
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                    Intrinsics.checkNotNullExpressionValue(packageInfo, "getPackageInfo(...)");
                    String packageName = packageInfo.packageName;
                    Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                    return packageName;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                    return "com.qc";
                }
            }

            public final String getAppName(Context context) throws Resources.NotFoundException, PackageManager.NameNotFoundException {
                Intrinsics.checkNotNullParameter(context, "context");
                PackageManager packageManager = context.getPackageManager();
                Intrinsics.checkNotNullExpressionValue(packageManager, "getPackageManager(...)");
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                    Intrinsics.checkNotNullExpressionValue(packageInfo, "getPackageInfo(...)");
                    ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                    Intrinsics.checkNotNull(applicationInfo);
                    String string = context.getResources().getString(applicationInfo.labelRes);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    return string;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                    return "others";
                }
            }

            public final int getVersionCode(Context context) throws PackageManager.NameNotFoundException {
                Intrinsics.checkNotNullParameter(context, "context");
                PackageManager packageManager = context.getPackageManager();
                Intrinsics.checkNotNullExpressionValue(packageManager, "getPackageManager(...)");
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                    Intrinsics.checkNotNullExpressionValue(packageInfo, "getPackageInfo(...)");
                    return packageInfo.versionCode;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                    return 0;
                }
            }

            /* compiled from: DeviceSettingDepository.kt */
            @Metadata(m606d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/depository/DeviceSettingDepository$Companion;", "", "()V", "getInstance", "Lcom/glasssutdio/wear/depository/DeviceSettingDepository;", "getGetInstance", "()Lcom/glasssutdio/wear/depository/DeviceSettingDepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final DeviceSettingDepository getGetInstance() {
                    return (DeviceSettingDepository) DeviceSettingDepository.getInstance$delegate.getValue();
                }
            }
        }
