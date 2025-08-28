package com.glasssutdio.wear.home.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.TextViewExtKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.bean.Req.CheckVersionReq;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.AppstoreUtils;
import com.glasssutdio.wear.databinding.ActivityAboutBinding;
import com.glasssutdio.wear.home.viewmodel.CheckVersionVM;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import com.glasssutdio.wear.setting.WebActivity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: AboutActivity.kt */
@Metadata(m606d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/AboutActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityAboutBinding;", "mViewMode", "Lcom/glasssutdio/wear/home/viewmodel/CheckVersionVM;", "getMViewMode", "()Lcom/glasssutdio/wear/home/viewmodel/CheckVersionVM;", "mViewMode$delegate", "Lkotlin/Lazy;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AboutActivity extends BaseSettingActivity {
    private ActivityAboutBinding binding;

    /* renamed from: mViewMode$delegate, reason: from kotlin metadata */
    private final Lazy mViewMode;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$9$lambda$8(View view) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AboutActivity() {
        final AboutActivity aboutActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewMode = LazyKt.lazy(new Function0<CheckVersionVM>() { // from class: com.glasssutdio.wear.home.activity.AboutActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.CheckVersionVM] */
            @Override // kotlin.jvm.functions.Function0
            public final CheckVersionVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(aboutActivity, Reflection.getOrCreateKotlinClass(CheckVersionVM.class), qualifier, objArr);
            }
        });
    }

    private final CheckVersionVM getMViewMode() {
        return (CheckVersionVM) this.mViewMode.getValue();
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws PackageManager.NameNotFoundException, SecurityException {
        super.onCreate(savedInstanceState);
        ActivityAboutBinding activityAboutBindingInflate = ActivityAboutBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityAboutBindingInflate, "inflate(...)");
        this.binding = activityAboutBindingInflate;
        if (activityAboutBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAboutBindingInflate = null;
        }
        setContentView(activityAboutBindingInflate.getRoot());
        initView();
    }

    private final void initView() throws PackageManager.NameNotFoundException {
        ActivityAboutBinding activityAboutBinding = this.binding;
        if (activityAboutBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAboutBinding = null;
        }
        activityAboutBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.AboutActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AboutActivity.initView$lambda$9$lambda$0(this.f$0, view);
            }
        });
        activityAboutBinding.title.tvTitle.setText(getString(C0775R.string.h_glass_45));
        TextView tvAgreement = activityAboutBinding.tvAgreement;
        Intrinsics.checkNotNullExpressionValue(tvAgreement, "tvAgreement");
        TextViewExtKt.setupMarquee(tvAgreement);
        TextView tvPolicy = activityAboutBinding.tvPolicy;
        Intrinsics.checkNotNullExpressionValue(tvPolicy, "tvPolicy");
        TextViewExtKt.setupMarquee(tvPolicy);
        activityAboutBinding.tvAgreement.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.AboutActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AboutActivity.initView$lambda$9$lambda$3(this.f$0, view);
            }
        });
        activityAboutBinding.tvPolicy.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.AboutActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AboutActivity.initView$lambda$9$lambda$6(this.f$0, view);
            }
        });
        ViewKt.goneOrVisible(activityAboutBinding.ivNew, UserConfig.INSTANCE.getInstance().getHasNewVersion());
        TextView textView = activityAboutBinding.tvVersion;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(C0775R.string.h_glass_275);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        AboutActivity aboutActivity = this;
        String str = String.format(string, Arrays.copyOf(new Object[]{GlobalKt.getVersionName(aboutActivity)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        textView.setText(str);
        activityAboutBinding.clsCheckUpdate.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.AboutActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AboutActivity.initView$lambda$9$lambda$7(this.f$0, view);
            }
        });
        activityAboutBinding.ivLogo.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.AboutActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AboutActivity.initView$lambda$9$lambda$8(view);
            }
        });
        CheckVersionVM mViewMode = getMViewMode();
        String string2 = getString(C0775R.string.app_name);
        String versionName = GlobalKt.getVersionName(aboutActivity);
        int versionCode = GlobalKt.getVersionCode(aboutActivity);
        Intrinsics.checkNotNull(string2);
        mViewMode.appLastVersion(new CheckVersionReq(string2, versionCode, versionName, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$9$lambda$0(AboutActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$9$lambda$3(AboutActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.m137i(Locale.getDefault().getLanguage());
        String language = Locale.getDefault().getLanguage();
        Intrinsics.checkNotNullExpressionValue(language, "getLanguage(...)");
        if (StringsKt.startsWith$default(language, "zh", false, 2, (Object) null)) {
            Bundle bundle = new Bundle();
            bundle.putString("url", "https://www.qlifesnap.com/ppm/heycyan_agreement.html");
            AboutActivity aboutActivity = this$0;
            ArrayList<Pair> arrayList = new ArrayList();
            Intent intent = new Intent(aboutActivity, (Class<?>) WebActivity.class);
            intent.setFlags(1);
            intent.putExtras(bundle);
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
            aboutActivity.startActivity(intent);
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("url", "https://www.qlifesnap.com/ppm/heycyan_agreement.html");
        AboutActivity aboutActivity2 = this$0;
        ArrayList<Pair> arrayList2 = new ArrayList();
        Intent intent2 = new Intent(aboutActivity2, (Class<?>) WebActivity.class);
        intent2.setFlags(1);
        intent2.putExtras(bundle2);
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
        aboutActivity2.startActivity(intent2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$9$lambda$6(AboutActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String language = Locale.getDefault().getLanguage();
        Intrinsics.checkNotNullExpressionValue(language, "getLanguage(...)");
        if (StringsKt.startsWith$default(language, "zh", false, 2, (Object) null)) {
            Bundle bundle = new Bundle();
            bundle.putString("url", "https://www.qlifesnap.com/ppm/heycyan_cn.html");
            AboutActivity aboutActivity = this$0;
            ArrayList<Pair> arrayList = new ArrayList();
            Intent intent = new Intent(aboutActivity, (Class<?>) WebActivity.class);
            intent.setFlags(1);
            intent.putExtras(bundle);
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
            aboutActivity.startActivity(intent);
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("url", "https://www.qlifesnap.com/heycyan.html");
        AboutActivity aboutActivity2 = this$0;
        ArrayList<Pair> arrayList2 = new ArrayList();
        Intent intent2 = new Intent(aboutActivity2, (Class<?>) WebActivity.class);
        intent2.setFlags(1);
        intent2.putExtras(bundle2);
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
        aboutActivity2.startActivity(intent2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$9$lambda$7(AboutActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!UserConfig.INSTANCE.getInstance().getHasNewVersion()) {
            String string = this$0.getString(C0775R.string.h_glass_280);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
        } else {
            AboutActivity aboutActivity = this$0;
            AppstoreUtils.INSTANCE.openAppStore(aboutActivity, GlobalKt.getPackageName(aboutActivity));
        }
    }
}
