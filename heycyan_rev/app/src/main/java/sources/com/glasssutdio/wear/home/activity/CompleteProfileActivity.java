package com.glasssutdio.wear.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.MainActivity;
import com.glasssutdio.wear.all.EditTextViewExtKt;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.TextViewExtKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.bean.CommonSelectModel;
import com.glasssutdio.wear.all.bean.Req.UpdateUserReq;
import com.glasssutdio.wear.all.dialog.CommonSelectDialog;
import com.glasssutdio.wear.all.dialog.DateSelectDialog;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.bar.StatusBarUtil;
import com.glasssutdio.wear.databinding.ActivityCompleteProfileBinding;
import com.glasssutdio.wear.home.bean.RequestFailModel;
import com.glasssutdio.wear.home.viewmodel.UserInfoEditActivityVM;
import com.glasssutdio.wear.manager.BaseFullActivity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: CompleteProfileActivity.kt */
@Metadata(m606d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\b\u0010\u0015\u001a\u00020\u0013H\u0016J\u0012\u0010\u0016\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\u001a\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/CompleteProfileActivity;", "Lcom/glasssutdio/wear/manager/BaseFullActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityCompleteProfileBinding;", "day", "", "defaultIndex", "mViewModel", "Lcom/glasssutdio/wear/home/viewmodel/UserInfoEditActivityVM;", "getMViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/UserInfoEditActivityVM;", "mViewModel$delegate", "Lkotlin/Lazy;", "month", "sexType", "Ljava/lang/Integer;", "year", "confirm", "", "initView", "observer", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onKeyDown", "", "keyCode", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class CompleteProfileActivity extends BaseFullActivity {
    private ActivityCompleteProfileBinding binding;
    private int day;
    private int defaultIndex;

    /* renamed from: mViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mViewModel;
    private int month;
    private Integer sexType;
    private int year;

    /* JADX WARN: Multi-variable type inference failed */
    public CompleteProfileActivity() {
        final CompleteProfileActivity completeProfileActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewModel = LazyKt.lazy(new Function0<UserInfoEditActivityVM>() { // from class: com.glasssutdio.wear.home.activity.CompleteProfileActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.UserInfoEditActivityVM] */
            @Override // kotlin.jvm.functions.Function0
            public final UserInfoEditActivityVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(completeProfileActivity, Reflection.getOrCreateKotlinClass(UserInfoEditActivityVM.class), qualifier, objArr);
            }
        });
        this.defaultIndex = 2;
        this.year = 1995;
        this.month = 1;
        this.day = 1;
    }

    private final UserInfoEditActivityVM getMViewModel() {
        return (UserInfoEditActivityVM) this.mViewModel.getValue();
    }

    @Override // com.glasssutdio.wear.manager.BaseFullActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompleteProfileBinding activityCompleteProfileBindingInflate = ActivityCompleteProfileBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityCompleteProfileBindingInflate, "inflate(...)");
        this.binding = activityCompleteProfileBindingInflate;
        if (activityCompleteProfileBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteProfileBindingInflate = null;
        }
        setContentView(activityCompleteProfileBindingInflate.getRoot());
        super.onCreate(savedInstanceState);
    }

    @Override // com.glasssutdio.wear.manager.BaseFullActivity
    public void initView() {
        ActivityCompleteProfileBinding activityCompleteProfileBinding;
        final ActivityCompleteProfileBinding activityCompleteProfileBinding2 = this.binding;
        if (activityCompleteProfileBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteProfileBinding2 = null;
        }
        CompleteProfileActivity completeProfileActivity = this;
        StatusBarUtil.setTranslucentStatus(completeProfileActivity);
        StatusBarUtil.setRootViewFitsSystemWindows(completeProfileActivity, false);
        StatusBarUtil.setStatusBarDarkTheme(completeProfileActivity, true);
        TextView tvTitle = activityCompleteProfileBinding2.tvTitle;
        Intrinsics.checkNotNullExpressionValue(tvTitle, "tvTitle");
        ViewKt.statusMargin$default(tvTitle, false, GlobalKt.getDp((Number) 10), 1, null);
        TextView tvTitle2 = activityCompleteProfileBinding2.tvTitle;
        Intrinsics.checkNotNullExpressionValue(tvTitle2, "tvTitle");
        TextViewExtKt.setupMarquee(tvTitle2);
        TextView tvSkip = activityCompleteProfileBinding2.tvSkip;
        Intrinsics.checkNotNullExpressionValue(tvSkip, "tvSkip");
        TextViewExtKt.setupMarquee(tvSkip);
        activityCompleteProfileBinding2.tvConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.CompleteProfileActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CompleteProfileActivity.initView$lambda$6$lambda$0(this.f$0, view);
            }
        });
        activityCompleteProfileBinding2.tvSkip.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.CompleteProfileActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CompleteProfileActivity.initView$lambda$6$lambda$1(this.f$0, view);
            }
        });
        TextView tvConfirm = activityCompleteProfileBinding2.tvConfirm;
        Intrinsics.checkNotNullExpressionValue(tvConfirm, "tvConfirm");
        ViewKt.statusMargin$default(tvConfirm, false, 0, 3, null);
        String string = getString(C0775R.string.h_glass_282);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = getString(C0775R.string.h_glass_283);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        String string3 = getString(C0775R.string.h_glass_283_no);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        final List listListOf = CollectionsKt.listOf((Object[]) new CommonSelectModel[]{new CommonSelectModel(string, false, 0, null, 14, null), new CommonSelectModel(string2, false, 0, null, 14, null), new CommonSelectModel(string3, false, 0, null, 14, null)});
        activityCompleteProfileBinding2.tvSex.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.CompleteProfileActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CompleteProfileActivity.initView$lambda$6$lambda$2(this.f$0, listListOf, activityCompleteProfileBinding2, view);
            }
        });
        activityCompleteProfileBinding2.tvBirthday.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.CompleteProfileActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CompleteProfileActivity.initView$lambda$6$lambda$3(this.f$0, activityCompleteProfileBinding2, view);
            }
        });
        activityCompleteProfileBinding2.ivClearEt.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.CompleteProfileActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CompleteProfileActivity.initView$lambda$6$lambda$4(activityCompleteProfileBinding2, view);
            }
        });
        EditText etUsername = activityCompleteProfileBinding2.etUsername;
        Intrinsics.checkNotNullExpressionValue(etUsername, "etUsername");
        etUsername.addTextChangedListener(new TextWatcher() { // from class: com.glasssutdio.wear.home.activity.CompleteProfileActivity$initView$lambda$6$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                ImageView imageView = activityCompleteProfileBinding2.ivClearEt;
                EditText etUsername2 = activityCompleteProfileBinding2.etUsername;
                Intrinsics.checkNotNullExpressionValue(etUsername2, "etUsername");
                ViewKt.goneOrVisible(imageView, !EditTextViewExtKt.isEmpty(etUsername2));
            }
        });
        ActivityCompleteProfileBinding activityCompleteProfileBinding3 = this.binding;
        if (activityCompleteProfileBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteProfileBinding = null;
        } else {
            activityCompleteProfileBinding = activityCompleteProfileBinding3;
        }
        TextView tvTitle3 = activityCompleteProfileBinding.tvTitle;
        Intrinsics.checkNotNullExpressionValue(tvTitle3, "tvTitle");
        GlobalKt.textViewScore(tvTitle3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$6$lambda$0(CompleteProfileActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.confirm();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$6$lambda$1(CompleteProfileActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CompleteProfileActivity completeProfileActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(completeProfileActivity, (Class<?>) MainActivity.class);
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
        completeProfileActivity.startActivity(intent);
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$6$lambda$2(final CompleteProfileActivity this$0, List sexData, final ActivityCompleteProfileBinding this_run, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(sexData, "$sexData");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        CommonSelectDialog.Builder builderIsLight = new CommonSelectDialog.Builder().isLight(true);
        String string = this$0.getString(C0775R.string.h_glass_281);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        CommonSelectDialog commonSelectDialogBuild = builderIsLight.setTitle(string).setDefaultChecked(this$0.defaultIndex).setData(sexData).build();
        commonSelectDialogBuild.setOnItemCheckedListener(new Function2<Integer, CommonSelectModel, Unit>() { // from class: com.glasssutdio.wear.home.activity.CompleteProfileActivity$initView$1$3$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, CommonSelectModel commonSelectModel) {
                invoke(num.intValue(), commonSelectModel);
                return Unit.INSTANCE;
            }

            public final void invoke(int i, CommonSelectModel model) {
                int i2;
                Intrinsics.checkNotNullParameter(model, "model");
                this_run.tvSex.setText(model.getName());
                CompleteProfileActivity completeProfileActivity = this$0;
                String name = model.getName();
                if (Intrinsics.areEqual(name, this$0.getString(C0775R.string.h_glass_282))) {
                    i2 = 1;
                } else if (Intrinsics.areEqual(name, this$0.getString(C0775R.string.h_glass_283))) {
                    i2 = 2;
                } else {
                    i2 = 3;
                }
                completeProfileActivity.sexType = i2;
                CompleteProfileActivity completeProfileActivity2 = this$0;
                Integer num = completeProfileActivity2.sexType;
                Intrinsics.checkNotNull(num);
                completeProfileActivity2.defaultIndex = num.intValue() - 1;
            }
        });
        commonSelectDialogBuild.show(this$0.getSupportFragmentManager(), "sexDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$6$lambda$3(final CompleteProfileActivity this$0, final ActivityCompleteProfileBinding this_run, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        DateSelectDialog.Builder builder = new DateSelectDialog.Builder();
        String string = this$0.getString(C0775R.string.h_glass_235);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        final DateSelectDialog dateSelectDialogBuild = builder.setTitle(string).setDefaultSelectDate(this$0.year, this$0.month, this$0.day).isLight(true).build();
        dateSelectDialogBuild.setOnDateSelectedListener(new Function3<Integer, Integer, Integer, Unit>() { // from class: com.glasssutdio.wear.home.activity.CompleteProfileActivity$initView$1$4$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2, Integer num3) {
                invoke(num.intValue(), num2.intValue(), num3.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, int i2, int i3) {
                this.this$0.year = i;
                this.this$0.month = i2;
                this.this$0.day = i3;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String string2 = this.this$0.getString(C0775R.string.h_glass_date);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String str = String.format(string2, Arrays.copyOf(new Object[]{String.valueOf(i), i2 < 10 ? "0" + i2 : String.valueOf(i2), i3 < 10 ? "0" + i3 : String.valueOf(i3)}, 3));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                this_run.tvBirthday.setText(str);
                dateSelectDialogBuild.dismiss();
            }
        });
        dateSelectDialogBuild.show(this$0.getSupportFragmentManager(), "birth");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$6$lambda$4(ActivityCompleteProfileBinding this_run, View view) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        this_run.etUsername.setText("");
    }

    private final void confirm() {
        ActivityCompleteProfileBinding activityCompleteProfileBinding = this.binding;
        if (activityCompleteProfileBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteProfileBinding = null;
        }
        String string = activityCompleteProfileBinding.etUsername.getText().toString();
        if (string.length() == 0) {
            String string2 = getString(C0775R.string.h_glass_306);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            GlobalKt.showToast$default(string2, 0, 1, null);
            return;
        }
        if (this.sexType == null) {
            String string3 = getString(C0775R.string.h_glass_317);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            GlobalKt.showToast$default(string3, 0, 1, null);
            return;
        }
        ActivityCompleteProfileBinding activityCompleteProfileBinding2 = this.binding;
        if (activityCompleteProfileBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteProfileBinding2 = null;
        }
        String string4 = activityCompleteProfileBinding2.tvBirthday.getText().toString();
        if (string4.length() == 0) {
            String string5 = getString(C0775R.string.h_glass_318);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
            GlobalKt.showToast$default(string5, 0, 1, null);
        } else {
            showLoadingDialog();
            getMViewModel().updateUserInfo(new UpdateUserReq(string4, string, this.sexType, String.valueOf(UserConfig.INSTANCE.getInstance().getUid())));
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (4 == keyCode) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override // com.glasssutdio.wear.manager.BaseFullActivity
    public void observer() {
        super.observer();
        UserInfoEditActivityVM mViewModel = getMViewModel();
        CompleteProfileActivity completeProfileActivity = this;
        mViewModel.getUpdateLD().observe(completeProfileActivity, new CompleteProfileActivity$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: com.glasssutdio.wear.home.activity.CompleteProfileActivity$observer$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean bool) {
                this.this$0.dismissLoadingDialog();
                CompleteProfileActivity completeProfileActivity2 = this.this$0;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(completeProfileActivity2, (Class<?>) MainActivity.class);
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
                completeProfileActivity2.startActivity(intent);
            }
        }));
        mViewModel.getFailLD().observe(completeProfileActivity, new CompleteProfileActivity$sam$androidx_lifecycle_Observer$0(new Function1<RequestFailModel, Unit>() { // from class: com.glasssutdio.wear.home.activity.CompleteProfileActivity$observer$1$2
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
                this.this$0.dismissLoadingDialog();
                String msg = requestFailModel.getMsg();
                if (msg != null) {
                    GlobalKt.showToast$default(msg, 0, 1, null);
                }
            }
        }));
    }
}
