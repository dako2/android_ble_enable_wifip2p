package com.glasssutdio.wear.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager2.widget.ViewPager2;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.MainActivity;
import com.glasssutdio.wear.all.Constant;
import com.glasssutdio.wear.all.TextViewExtKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.dialog.CommonDialog;
import com.glasssutdio.wear.databinding.ActivityUserGuideBinding;
import com.glasssutdio.wear.home.adapter.UserGuideAdapter;
import com.glasssutdio.wear.home.bean.UserGuideModel;
import com.glasssutdio.wear.home.fragment.UserGuideFragment;
import com.glasssutdio.wear.home.viewmodel.UserGuideFragmentVM;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import com.glasssutdio.wear.setting.RecordSettingActivity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: UserGuideActivity.kt */
@Metadata(m606d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0003J\u0012\u0010\u0010\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0003J\u0010\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u0017"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/UserGuideActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityUserGuideBinding;", "mPageAdapter", "Lcom/glasssutdio/wear/home/adapter/UserGuideAdapter;", "mViewModel", "Lcom/glasssutdio/wear/home/viewmodel/UserGuideFragmentVM;", "getMViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/UserGuideFragmentVM;", "mViewModel$delegate", "Lkotlin/Lazy;", "finish", "", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSelectPage", "position", "", "setIndicatorSelected", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class UserGuideActivity extends BaseSettingActivity {
    private ActivityUserGuideBinding binding;
    private UserGuideAdapter mPageAdapter;

    /* renamed from: mViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mViewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public UserGuideActivity() {
        final UserGuideActivity userGuideActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewModel = LazyKt.lazy(new Function0<UserGuideFragmentVM>() { // from class: com.glasssutdio.wear.home.activity.UserGuideActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.UserGuideFragmentVM] */
            @Override // kotlin.jvm.functions.Function0
            public final UserGuideFragmentVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(userGuideActivity, Reflection.getOrCreateKotlinClass(UserGuideFragmentVM.class), qualifier, objArr);
            }
        });
    }

    private final UserGuideFragmentVM getMViewModel() {
        return (UserGuideFragmentVM) this.mViewModel.getValue();
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityUserGuideBinding activityUserGuideBindingInflate = ActivityUserGuideBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityUserGuideBindingInflate, "inflate(...)");
        this.binding = activityUserGuideBindingInflate;
        getMViewModel().setPageGuideType(getIntent().getIntExtra(Constant.PAGE_GUIDE_KEY, 1));
        ActivityUserGuideBinding activityUserGuideBinding = this.binding;
        if (activityUserGuideBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityUserGuideBinding = null;
        }
        setContentView(activityUserGuideBinding.getRoot());
        getMViewModel().initData();
        initView();
    }

    private final void initView() {
        ArrayList arrayList;
        ActivityUserGuideBinding activityUserGuideBinding = this.binding;
        if (activityUserGuideBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityUserGuideBinding = null;
        }
        activityUserGuideBinding.title.ivMenu.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.UserGuideActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserGuideActivity.initView$lambda$0(this.f$0, view);
            }
        });
        ActivityUserGuideBinding activityUserGuideBinding2 = this.binding;
        if (activityUserGuideBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityUserGuideBinding2 = null;
        }
        TextView ivSkip = activityUserGuideBinding2.ivSkip;
        Intrinsics.checkNotNullExpressionValue(ivSkip, "ivSkip");
        TextViewExtKt.setupMarquee(ivSkip);
        ActivityUserGuideBinding activityUserGuideBinding3 = this.binding;
        if (activityUserGuideBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityUserGuideBinding3 = null;
        }
        TextView textView = activityUserGuideBinding3.title.tvTitle;
        UserGuideModel userGuideModel = (UserGuideModel) CollectionsKt.getOrNull(getMViewModel().getGuideDataList(), 0);
        textView.setText(userGuideModel != null ? userGuideModel.getPageTitle() : null);
        ActivityUserGuideBinding activityUserGuideBinding4 = this.binding;
        if (activityUserGuideBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityUserGuideBinding4 = null;
        }
        activityUserGuideBinding4.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.UserGuideActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserGuideActivity.initView$lambda$1(this.f$0, view);
            }
        });
        int pageGuideType = getMViewModel().getPageGuideType();
        if (pageGuideType == 2) {
            arrayList = new ArrayList();
            int fragmentTotalsByType = getMViewModel().getFragmentTotalsByType();
            for (int i = 0; i < fragmentTotalsByType; i++) {
                arrayList.add(UserGuideFragment.INSTANCE.newInstance(i, 2));
            }
        } else if (pageGuideType == 3) {
            ActivityUserGuideBinding activityUserGuideBinding5 = this.binding;
            if (activityUserGuideBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityUserGuideBinding5 = null;
            }
            activityUserGuideBinding5.tvIndex2.setText("1/" + getMViewModel().getFragmentTotalsByType());
            ActivityUserGuideBinding activityUserGuideBinding6 = this.binding;
            if (activityUserGuideBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityUserGuideBinding6 = null;
            }
            ViewKt.invisible(activityUserGuideBinding6.flTitle);
            ActivityUserGuideBinding activityUserGuideBinding7 = this.binding;
            if (activityUserGuideBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityUserGuideBinding7 = null;
            }
            ViewKt.visible(activityUserGuideBinding7.ivSkip);
            ActivityUserGuideBinding activityUserGuideBinding8 = this.binding;
            if (activityUserGuideBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityUserGuideBinding8 = null;
            }
            ViewKt.invisible(activityUserGuideBinding8.ivClose);
            ActivityUserGuideBinding activityUserGuideBinding9 = this.binding;
            if (activityUserGuideBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityUserGuideBinding9 = null;
            }
            ViewKt.visible(activityUserGuideBinding9.tvIndex2);
            arrayList = new ArrayList();
            int fragmentTotalsByType2 = getMViewModel().getFragmentTotalsByType();
            for (int i2 = 0; i2 < fragmentTotalsByType2; i2++) {
                arrayList.add(UserGuideFragment.INSTANCE.newInstance(i2, 3));
            }
        } else {
            arrayList = new ArrayList();
            int fragmentTotalsByType3 = getMViewModel().getFragmentTotalsByType();
            for (int i3 = 0; i3 < fragmentTotalsByType3; i3++) {
                arrayList.add(UserGuideFragment.INSTANCE.newInstance(i3, 1));
            }
        }
        this.mPageAdapter = new UserGuideAdapter(this, arrayList);
        final ActivityUserGuideBinding activityUserGuideBinding10 = this.binding;
        if (activityUserGuideBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityUserGuideBinding10 = null;
        }
        activityUserGuideBinding10.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.UserGuideActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserGuideActivity.initView$lambda$9$lambda$5(this.f$0, view);
            }
        });
        activityUserGuideBinding10.ivSkip.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.UserGuideActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserGuideActivity.initView$lambda$9$lambda$6(this.f$0, view);
            }
        });
        ViewPager2 viewPager2 = activityUserGuideBinding10.viewPager2;
        UserGuideAdapter userGuideAdapter = this.mPageAdapter;
        if (userGuideAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPageAdapter");
            userGuideAdapter = null;
        }
        viewPager2.setAdapter(userGuideAdapter);
        ViewPager2 viewPager22 = activityUserGuideBinding10.viewPager2;
        UserGuideAdapter userGuideAdapter2 = this.mPageAdapter;
        if (userGuideAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPageAdapter");
            userGuideAdapter2 = null;
        }
        viewPager22.setOffscreenPageLimit(userGuideAdapter2.getFragments().size());
        activityUserGuideBinding10.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.glasssutdio.wear.home.activity.UserGuideActivity$initView$3$3
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                this.this$0.onSelectPage(position);
            }
        });
        UserGuideAdapter userGuideAdapter3 = this.mPageAdapter;
        if (userGuideAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPageAdapter");
            userGuideAdapter3 = null;
        }
        int size = userGuideAdapter3.getFragments().size();
        int i4 = 0;
        while (i4 < size) {
            View viewInflate = View.inflate(this, C0775R.layout.user_guide_indicator_view, null);
            ((ImageView) viewInflate.findViewById(C0775R.id.iv_indicator)).setSelected(i4 == 0);
            activityUserGuideBinding10.llIndicator.addView(viewInflate);
            i4++;
        }
        activityUserGuideBinding10.ivNext.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.UserGuideActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserGuideActivity.initView$lambda$9$lambda$7(activityUserGuideBinding10, view);
            }
        });
        activityUserGuideBinding10.ivLast.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.UserGuideActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserGuideActivity.initView$lambda$9$lambda$8(activityUserGuideBinding10, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$0(UserGuideActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UserGuideActivity userGuideActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(userGuideActivity, (Class<?>) RecordSettingActivity.class);
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
        userGuideActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$1(UserGuideActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$9$lambda$5(UserGuideActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$9$lambda$6(final UserGuideActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CommonDialog.Builder builder = new CommonDialog.Builder();
        String string = this$0.getString(C0775R.string.h_glass_218);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        CommonDialog.Builder title = builder.setTitle(string);
        String string2 = this$0.getString(C0775R.string.h_glass_219);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        CommonDialog.Builder content = title.setContent(string2);
        String string3 = this$0.getString(C0775R.string.h_glass_220);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        CommonDialog.Builder confirmMessage = content.setConfirmMessage(string3);
        String string4 = this$0.getString(C0775R.string.h_glass_221);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        CommonDialog commonDialogBuild = confirmMessage.setCancelMessage(string4).build();
        commonDialogBuild.setOnConfirmListener(new Function1<View, Unit>() { // from class: com.glasssutdio.wear.home.activity.UserGuideActivity$initView$3$2$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
                UserGuideActivity userGuideActivity = this.this$0;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(userGuideActivity, (Class<?>) MainActivity.class);
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
                userGuideActivity.startActivity(intent);
            }
        });
        commonDialogBuild.show(this$0.getSupportFragmentManager(), "skip");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$9$lambda$7(ActivityUserGuideBinding this_run, View view) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        ViewPager2 viewPager2 = this_run.viewPager2;
        viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$9$lambda$8(ActivityUserGuideBinding this_run, View view) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        this_run.viewPager2.setCurrentItem(r0.getCurrentItem() - 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onSelectPage(int position) {
        ActivityUserGuideBinding activityUserGuideBinding = this.binding;
        UserGuideAdapter userGuideAdapter = null;
        if (activityUserGuideBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityUserGuideBinding = null;
        }
        TextView textView = activityUserGuideBinding.title.tvTitle;
        UserGuideModel userGuideModel = (UserGuideModel) CollectionsKt.getOrNull(getMViewModel().getGuideDataList(), position);
        textView.setText(userGuideModel != null ? userGuideModel.getPageTitle() : null);
        ActivityUserGuideBinding activityUserGuideBinding2 = this.binding;
        if (activityUserGuideBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityUserGuideBinding2 = null;
        }
        int pageGuideType = getMViewModel().getPageGuideType();
        if (pageGuideType == 1) {
            ActivityUserGuideBinding activityUserGuideBinding3 = this.binding;
            if (activityUserGuideBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityUserGuideBinding3 = null;
            }
            ViewKt.goneOrVisible(activityUserGuideBinding3.title.ivMenu, position == 1 || position == 2);
        } else if (pageGuideType != 2) {
            ActivityUserGuideBinding activityUserGuideBinding4 = this.binding;
            if (activityUserGuideBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityUserGuideBinding4 = null;
            }
            activityUserGuideBinding4.tvIndex2.setText(new StringBuilder().append(position + 1).append(IOUtils.DIR_SEPARATOR_UNIX).append(getMViewModel().getFragmentTotalsByType()).toString());
        }
        ViewKt.goneOrVisible(activityUserGuideBinding2.ivLast, position != 0);
        ImageView imageView = activityUserGuideBinding2.ivNext;
        UserGuideAdapter userGuideAdapter2 = this.mPageAdapter;
        if (userGuideAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPageAdapter");
        } else {
            userGuideAdapter = userGuideAdapter2;
        }
        ViewKt.goneOrVisible(imageView, position != userGuideAdapter.getFragments().size() - 1);
        setIndicatorSelected(position);
    }

    private final void setIndicatorSelected(int position) {
        ActivityUserGuideBinding activityUserGuideBinding = this.binding;
        if (activityUserGuideBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityUserGuideBinding = null;
        }
        int childCount = activityUserGuideBinding.llIndicator.getChildCount();
        int i = 0;
        while (i < childCount) {
            ActivityUserGuideBinding activityUserGuideBinding2 = this.binding;
            if (activityUserGuideBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityUserGuideBinding2 = null;
            }
            View childAt = activityUserGuideBinding2.llIndicator.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                ((ViewGroup) childAt).getChildAt(0).setSelected(i == position);
            }
            i++;
        }
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
    }
}
