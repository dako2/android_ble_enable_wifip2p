package com.glasssutdio.wear.home.activity;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;
import androidx.core.splashscreen.SplashScreen;
import androidx.lifecycle.Lifecycle;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.MainActivity;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.DateUtil;
import com.glasssutdio.wear.all.utils.image.ImageExtKt;
import com.glasssutdio.wear.all.utils.time.Interval;
import com.glasssutdio.wear.databinding.ActivityQcSplashBinding;
import com.glasssutdio.wear.home.bean.LoginResModel;
import com.glasssutdio.wear.home.bean.RequestFailModel;
import com.glasssutdio.wear.home.viewmodel.SplashActivityVM;
import com.glasssutdio.wear.manager.BaseFullActivity;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: SplashQcActivity.kt */
@Metadata(m606d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\b\u0010\u0015\u001a\u00020\u0013H\u0002J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\u0013H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0013H\u0016J\u0012\u0010\u001e\u001a\u00020\u00132\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/SplashQcActivity;", "Lcom/glasssutdio/wear/manager/BaseFullActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityQcSplashBinding;", "delayTime", "", "interval", "Lcom/glasssutdio/wear/all/utils/time/Interval;", "mViewModel", "Lcom/glasssutdio/wear/home/viewmodel/SplashActivityVM;", "getMViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/SplashActivityVM;", "mViewModel$delegate", "Lkotlin/Lazy;", "splashScreen", "Landroidx/core/splashscreen/SplashScreen;", "startTime", "checkLoginAndEnterApp", "", "enterApp", "enterLogin", "getGifDuration", "", "inputStream", "Ljava/io/InputStream;", "initView", "isBarIconDark", "", "observer", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class SplashQcActivity extends BaseFullActivity {
    private ActivityQcSplashBinding binding;
    private final long delayTime;
    private Interval interval;

    /* renamed from: mViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mViewModel;
    private SplashScreen splashScreen;
    private long startTime;

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$0() {
        return false;
    }

    @Override // com.glasssutdio.wear.manager.BaseFullActivity
    public boolean isBarIconDark() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SplashQcActivity() {
        final SplashQcActivity splashQcActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewModel = LazyKt.lazy(new Function0<SplashActivityVM>() { // from class: com.glasssutdio.wear.home.activity.SplashQcActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.SplashActivityVM] */
            @Override // kotlin.jvm.functions.Function0
            public final SplashActivityVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(splashQcActivity, Reflection.getOrCreateKotlinClass(SplashActivityVM.class), qualifier, objArr);
            }
        });
        this.delayTime = 2800L;
    }

    private final SplashActivityVM getMViewModel() {
        return (SplashActivityVM) this.mViewModel.getValue();
    }

    @Override // com.glasssutdio.wear.manager.BaseFullActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        this.splashScreen = SplashScreen.INSTANCE.installSplashScreen(this);
        ActivityQcSplashBinding activityQcSplashBindingInflate = ActivityQcSplashBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityQcSplashBindingInflate, "inflate(...)");
        this.binding = activityQcSplashBindingInflate;
        if (activityQcSplashBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQcSplashBindingInflate = null;
        }
        setContentView(activityQcSplashBindingInflate.getRoot());
        super.onCreate(savedInstanceState);
        SplashScreen splashScreen = this.splashScreen;
        if (splashScreen != null) {
            splashScreen.setKeepOnScreenCondition(new SplashScreen.KeepOnScreenCondition() { // from class: com.glasssutdio.wear.home.activity.SplashQcActivity$$ExternalSyntheticLambda0
                @Override // androidx.core.splashscreen.SplashScreen.KeepOnScreenCondition
                public final boolean shouldKeepOnScreen() {
                    return SplashQcActivity.onCreate$lambda$0();
                }
            });
        }
    }

    @Override // com.glasssutdio.wear.manager.BaseFullActivity
    public void initView() {
        checkLoginAndEnterApp();
        ActivityQcSplashBinding activityQcSplashBinding = this.binding;
        if (activityQcSplashBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQcSplashBinding = null;
        }
        ImageView ivLogo = activityQcSplashBinding.ivLogo;
        Intrinsics.checkNotNullExpressionValue(ivLogo, "ivLogo");
        ImageExtKt.loadGifFromAssets(ivLogo, "guide/splash_logo.gif", 1);
    }

    public final int getGifDuration(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        return Movie.decodeStream(inputStream).duration();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void enterApp() {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.startTime;
        if (jCurrentTimeMillis >= this.delayTime) {
            SplashQcActivity splashQcActivity = this;
            ArrayList<Pair> arrayList = new ArrayList();
            Intent intent = new Intent(splashQcActivity, (Class<?>) MainActivity.class);
            for (Pair pair : arrayList) {
                if (pair != null) {
                    String str = (String) pair.getFirst();
                    Object second = pair.getSecond();
                    if (second instanceof Integer) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(...)");
                    } else if (second instanceof Byte) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(...)");
                    } else if (second instanceof Character) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(...)");
                    } else if (second instanceof Short) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(...)");
                    } else if (second instanceof Boolean) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(...)");
                    } else if (second instanceof Long) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(...)");
                    } else if (second instanceof Float) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(...)");
                    } else if (second instanceof Double) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(...)");
                    } else if (second instanceof String) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(...)");
                    } else if (second instanceof CharSequence) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(...)");
                    } else if (second instanceof Parcelable) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                    } else if (second instanceof Object[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof ArrayList) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof Serializable) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof boolean[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(...)");
                    } else if (second instanceof byte[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(...)");
                    } else if (second instanceof short[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(...)");
                    } else if (second instanceof char[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(...)");
                    } else if (second instanceof int[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(...)");
                    } else if (second instanceof long[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(...)");
                    } else if (second instanceof float[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(...)");
                    } else if (second instanceof double[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(...)");
                    } else if (second instanceof Bundle) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(...)");
                    } else if (second instanceof Intent) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                    } else {
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            splashQcActivity.startActivity(intent);
            return;
        }
        Interval intervalLife$default = Interval.life$default(new Interval(0L, Math.abs(this.delayTime - jCurrentTimeMillis), TimeUnit.MILLISECONDS, 1L, 0L, 16, null), this, (Lifecycle.Event) null, 2, (Object) null);
        this.interval = intervalLife$default;
        if (intervalLife$default != null) {
            intervalLife$default.subscribe(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.activity.SplashQcActivity$enterApp$1$1
                public final void invoke(Interval subscribe, long j) {
                    Intrinsics.checkNotNullParameter(subscribe, "$this$subscribe");
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Interval interval, Long l) {
                    invoke(interval, l.longValue());
                    return Unit.INSTANCE;
                }
            }).finish(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.activity.SplashQcActivity$enterApp$1$2
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Interval interval, Long l) {
                    invoke(interval, l.longValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Interval finish, long j) {
                    Intrinsics.checkNotNullParameter(finish, "$this$finish");
                    SplashQcActivity splashQcActivity2 = this.this$0;
                    ArrayList<Pair> arrayList2 = new ArrayList();
                    Intent intent2 = new Intent(splashQcActivity2, (Class<?>) MainActivity.class);
                    for (Pair pair2 : arrayList2) {
                        if (pair2 != null) {
                            String str2 = (String) pair2.getFirst();
                            Object second2 = pair2.getSecond();
                            if (second2 instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).intValue()), "putExtra(...)");
                            } else if (second2 instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).byteValue()), "putExtra(...)");
                            } else if (second2 instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Character) second2).charValue()), "putExtra(...)");
                            } else if (second2 instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).shortValue()), "putExtra(...)");
                            } else if (second2 instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Boolean) second2).booleanValue()), "putExtra(...)");
                            } else if (second2 instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).longValue()), "putExtra(...)");
                            } else if (second2 instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).floatValue()), "putExtra(...)");
                            } else if (second2 instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).doubleValue()), "putExtra(...)");
                            } else if (second2 instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (String) second2), "putExtra(...)");
                            } else if (second2 instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (CharSequence) second2), "putExtra(...)");
                            } else if (second2 instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(...)");
                            } else if (second2 instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                            } else if (second2 instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                            } else if (second2 instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                            } else if (second2 instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (boolean[]) second2), "putExtra(...)");
                            } else if (second2 instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (byte[]) second2), "putExtra(...)");
                            } else if (second2 instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (short[]) second2), "putExtra(...)");
                            } else if (second2 instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (char[]) second2), "putExtra(...)");
                            } else if (second2 instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (int[]) second2), "putExtra(...)");
                            } else if (second2 instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (long[]) second2), "putExtra(...)");
                            } else if (second2 instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (float[]) second2), "putExtra(...)");
                            } else if (second2 instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (double[]) second2), "putExtra(...)");
                            } else if (second2 instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Bundle) second2), "putExtra(...)");
                            } else if (second2 instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(...)");
                            } else {
                                Unit unit2 = Unit.INSTANCE;
                            }
                        }
                    }
                    splashQcActivity2.startActivity(intent2);
                }
            }).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void enterLogin() {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.startTime;
        if (jCurrentTimeMillis >= this.delayTime) {
            SplashQcActivity splashQcActivity = this;
            ArrayList<Pair> arrayList = new ArrayList();
            Intent intent = new Intent(splashQcActivity, (Class<?>) LoginActivity.class);
            for (Pair pair : arrayList) {
                if (pair != null) {
                    String str = (String) pair.getFirst();
                    Object second = pair.getSecond();
                    if (second instanceof Integer) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(...)");
                    } else if (second instanceof Byte) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(...)");
                    } else if (second instanceof Character) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(...)");
                    } else if (second instanceof Short) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(...)");
                    } else if (second instanceof Boolean) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(...)");
                    } else if (second instanceof Long) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(...)");
                    } else if (second instanceof Float) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(...)");
                    } else if (second instanceof Double) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(...)");
                    } else if (second instanceof String) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(...)");
                    } else if (second instanceof CharSequence) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(...)");
                    } else if (second instanceof Parcelable) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                    } else if (second instanceof Object[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof ArrayList) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof Serializable) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof boolean[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(...)");
                    } else if (second instanceof byte[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(...)");
                    } else if (second instanceof short[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(...)");
                    } else if (second instanceof char[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(...)");
                    } else if (second instanceof int[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(...)");
                    } else if (second instanceof long[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(...)");
                    } else if (second instanceof float[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(...)");
                    } else if (second instanceof double[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(...)");
                    } else if (second instanceof Bundle) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(...)");
                    } else if (second instanceof Intent) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                    } else {
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            splashQcActivity.startActivity(intent);
            finish();
            return;
        }
        Interval intervalLife$default = Interval.life$default(new Interval(0L, Math.abs(this.delayTime - jCurrentTimeMillis), TimeUnit.MILLISECONDS, 1L, 0L, 16, null), this, (Lifecycle.Event) null, 2, (Object) null);
        this.interval = intervalLife$default;
        if (intervalLife$default != null) {
            intervalLife$default.subscribe(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.activity.SplashQcActivity$enterLogin$1$1
                public final void invoke(Interval subscribe, long j) {
                    Intrinsics.checkNotNullParameter(subscribe, "$this$subscribe");
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Interval interval, Long l) {
                    invoke(interval, l.longValue());
                    return Unit.INSTANCE;
                }
            }).finish(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.activity.SplashQcActivity$enterLogin$1$2
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Interval interval, Long l) {
                    invoke(interval, l.longValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Interval finish, long j) {
                    Intrinsics.checkNotNullParameter(finish, "$this$finish");
                    SplashQcActivity splashQcActivity2 = this.this$0;
                    ArrayList<Pair> arrayList2 = new ArrayList();
                    Intent intent2 = new Intent(splashQcActivity2, (Class<?>) LoginActivity.class);
                    for (Pair pair2 : arrayList2) {
                        if (pair2 != null) {
                            String str2 = (String) pair2.getFirst();
                            Object second2 = pair2.getSecond();
                            if (second2 instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).intValue()), "putExtra(...)");
                            } else if (second2 instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).byteValue()), "putExtra(...)");
                            } else if (second2 instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Character) second2).charValue()), "putExtra(...)");
                            } else if (second2 instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).shortValue()), "putExtra(...)");
                            } else if (second2 instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Boolean) second2).booleanValue()), "putExtra(...)");
                            } else if (second2 instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).longValue()), "putExtra(...)");
                            } else if (second2 instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).floatValue()), "putExtra(...)");
                            } else if (second2 instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).doubleValue()), "putExtra(...)");
                            } else if (second2 instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (String) second2), "putExtra(...)");
                            } else if (second2 instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (CharSequence) second2), "putExtra(...)");
                            } else if (second2 instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(...)");
                            } else if (second2 instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                            } else if (second2 instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                            } else if (second2 instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                            } else if (second2 instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (boolean[]) second2), "putExtra(...)");
                            } else if (second2 instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (byte[]) second2), "putExtra(...)");
                            } else if (second2 instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (short[]) second2), "putExtra(...)");
                            } else if (second2 instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (char[]) second2), "putExtra(...)");
                            } else if (second2 instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (int[]) second2), "putExtra(...)");
                            } else if (second2 instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (long[]) second2), "putExtra(...)");
                            } else if (second2 instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (float[]) second2), "putExtra(...)");
                            } else if (second2 instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (double[]) second2), "putExtra(...)");
                            } else if (second2 instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Bundle) second2), "putExtra(...)");
                            } else if (second2 instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(...)");
                            } else {
                                Unit unit2 = Unit.INSTANCE;
                            }
                        }
                    }
                    splashQcActivity2.startActivity(intent2);
                    this.this$0.finish();
                }
            }).start();
        }
    }

    private final void checkLoginAndEnterApp() {
        this.startTime = System.currentTimeMillis();
        long lastLoginTimeStamp = UserConfig.INSTANCE.getInstance().getLastLoginTimeStamp();
        if (lastLoginTimeStamp == 0) {
            if (UserConfig.INSTANCE.getInstance().getNeedShowLogin()) {
                enterLogin();
                return;
            } else {
                enterApp();
                return;
            }
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (DateUtil.isSameDay(lastLoginTimeStamp, jCurrentTimeMillis)) {
            XLog.m137i("同一天内已登录过无需登录,进入app:" + UserConfig.INSTANCE.getInstance().getLastLoginType());
            enterApp();
            return;
        }
        if (DateUtil.isWithin7Days(lastLoginTimeStamp, jCurrentTimeMillis)) {
            int lastLoginType = UserConfig.INSTANCE.getInstance().getLastLoginType();
            String lastLoginAccount = UserConfig.INSTANCE.getInstance().getLastLoginAccount();
            String lastLoginPwd = UserConfig.INSTANCE.getInstance().getLastLoginPwd();
            if (lastLoginType == 0 || lastLoginAccount.length() == 0 || lastLoginPwd.length() == 0) {
                enterLogin();
                return;
            }
            XLog.m137i("自动登录：" + lastLoginType);
            if (lastLoginType == 1) {
                getMViewModel().login(lastLoginAccount, lastLoginPwd, false);
                return;
            } else {
                if (lastLoginType != 3) {
                    return;
                }
                getMViewModel().login(lastLoginAccount, lastLoginPwd, true);
                return;
            }
        }
        enterLogin();
    }

    @Override // com.glasssutdio.wear.manager.BaseFullActivity
    public void observer() {
        super.observer();
        SplashActivityVM mViewModel = getMViewModel();
        SplashQcActivity splashQcActivity = this;
        mViewModel.getLoginResLD().observe(splashQcActivity, new SplashQcActivity$sam$androidx_lifecycle_Observer$0(new Function1<LoginResModel, Unit>() { // from class: com.glasssutdio.wear.home.activity.SplashQcActivity$observer$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LoginResModel loginResModel) {
                invoke2(loginResModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LoginResModel loginResModel) {
                XLog.m137i("登录成功：进入app");
                UserConfig.INSTANCE.getInstance().setUserToken(loginResModel.getToken());
                UserConfig companion = UserConfig.INSTANCE.getInstance();
                Long longOrNull = StringsKt.toLongOrNull(loginResModel.getUid());
                companion.setUid(longOrNull != null ? longOrNull.longValue() : 0L);
                this.this$0.enterApp();
            }
        }));
        mViewModel.getLoginFailLD().observe(splashQcActivity, new SplashQcActivity$sam$androidx_lifecycle_Observer$0(new Function1<RequestFailModel, Unit>() { // from class: com.glasssutdio.wear.home.activity.SplashQcActivity$observer$1$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(RequestFailModel requestFailModel) {
                invoke2(requestFailModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RequestFailModel requestFailModel) {
                XLog.m137i("自动登录失败：进入登录界面，" + requestFailModel.getCode() + ',' + requestFailModel.getMsg());
                this.this$0.enterLogin();
            }
        }));
    }
}
