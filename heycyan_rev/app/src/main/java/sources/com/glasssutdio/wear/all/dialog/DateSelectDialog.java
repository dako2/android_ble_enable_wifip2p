package com.glasssutdio.wear.all.dialog;

import android.R;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.databinding.DialogDateSelectBinding;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.material.C1174R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DateSelectDialog.kt */
@Metadata(m606d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 '2\u00020\u0001:\u0002&'B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0012H\u0003J\u0012\u0010\u0016\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J$\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0010\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#H\u0016JS\u0010$\u001a\u00020\u00122K\u0010%\u001aG\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00120\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000RU\u0010\u000b\u001aI\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/DateSelectDialog;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/DialogDateSelectBinding;", "defaultDay", "", "defaultMonth", "defaultYear", "isLight", "", "onDateSelect", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "year", "month", "day", "", "title", "", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateDialog", "Landroid/app/Dialog;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "setOnDateSelectedListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Builder", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class DateSelectDialog extends BottomSheetDialogFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String DIALOG_DEFAULT_DAY = "dialog_default_day";
    public static final String DIALOG_DEFAULT_MONTH = "dialog_default_month";
    public static final String DIALOG_DEFAULT_YEAR = "dialog_default_year";
    public static final String DIALOG_TITLE = "dialog_title";
    public static final String IS_LIGHT = "is_light";
    private DialogDateSelectBinding binding;
    private boolean isLight;
    private Function3<? super Integer, ? super Integer, ? super Integer, Unit> onDateSelect;
    private String title;
    private int defaultYear = 2024;
    private int defaultMonth = 1;
    private int defaultDay = 1;

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, C0775R.style.Customer_Dialog);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.title = arguments.getString("dialog_title");
            this.isLight = arguments.getBoolean("is_light");
            this.defaultYear = arguments.getInt(DIALOG_DEFAULT_YEAR);
            this.defaultMonth = arguments.getInt(DIALOG_DEFAULT_MONTH);
            this.defaultDay = arguments.getInt(DIALOG_DEFAULT_DAY);
        }
    }

    public final void setOnDateSelectedListener(Function3<? super Integer, ? super Integer, ? super Integer, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onDateSelect = listener;
    }

    @Override // com.google.android.material.bottomsheet.BottomSheetDialogFragment, androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialogOnCreateDialog = super.onCreateDialog(savedInstanceState);
        Intrinsics.checkNotNull(dialogOnCreateDialog, "null cannot be cast to non-null type com.google.android.material.bottomsheet.BottomSheetDialog");
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogOnCreateDialog;
        bottomSheetDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.glasssutdio.wear.all.dialog.DateSelectDialog$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                DateSelectDialog.onCreateDialog$lambda$1(dialogInterface);
            }
        });
        return bottomSheetDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$1(DialogInterface dialogInterface) {
        Intrinsics.checkNotNull(dialogInterface, "null cannot be cast to non-null type com.google.android.material.bottomsheet.BottomSheetDialog");
        FrameLayout frameLayout = (FrameLayout) ((BottomSheetDialog) dialogInterface).findViewById(C1174R.id.design_bottom_sheet);
        if (frameLayout != null) {
            BottomSheetBehavior bottomSheetBehaviorFrom = BottomSheetBehavior.from(frameLayout);
            Intrinsics.checkNotNullExpressionValue(bottomSheetBehaviorFrom, "from(...)");
            bottomSheetBehaviorFrom.setHideable(false);
            bottomSheetBehaviorFrom.setState(3);
            bottomSheetBehaviorFrom.setDraggable(false);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Window window;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Dialog dialog = getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setBackgroundDrawableResource(R.color.transparent);
        }
        DialogDateSelectBinding dialogDateSelectBindingInflate = DialogDateSelectBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(dialogDateSelectBindingInflate, "inflate(...)");
        this.binding = dialogDateSelectBindingInflate;
        initView();
        DialogDateSelectBinding dialogDateSelectBinding = this.binding;
        if (dialogDateSelectBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDateSelectBinding = null;
        }
        ConstraintLayout root = dialogDateSelectBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    private final void initView() {
        final DialogDateSelectBinding dialogDateSelectBinding = this.binding;
        DialogDateSelectBinding dialogDateSelectBinding2 = null;
        if (dialogDateSelectBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDateSelectBinding = null;
        }
        dialogDateSelectBinding.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.all.dialog.DateSelectDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DateSelectDialog.initView$lambda$4$lambda$2(this.f$0, view);
            }
        });
        dialogDateSelectBinding.tvTitle.setText(this.title);
        dialogDateSelectBinding.ivConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.all.dialog.DateSelectDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DateSelectDialog.initView$lambda$4$lambda$3(this.f$0, dialogDateSelectBinding, view);
            }
        });
        dialogDateSelectBinding.dateWheel.setDefaultDate(this.defaultYear, this.defaultMonth, this.defaultDay);
        if (this.isLight) {
            dialogDateSelectBinding.dialogView.setBackgroundResource(C0775R.drawable.bg_white_20_shape);
            dialogDateSelectBinding.ivClose.setImageResource(C0775R.mipmap.ic_dialog_close_black);
            dialogDateSelectBinding.ivConfirm.setImageResource(C0775R.mipmap.ic_dialog_confirm_black);
            dialogDateSelectBinding.tvTitle.setTextColor(ContextCompat.getColor(requireContext(), C0775R.color.color_141414));
        } else {
            dialogDateSelectBinding.dialogView.setBackgroundResource(C0775R.drawable.main_setting_bg);
            dialogDateSelectBinding.ivClose.setImageResource(C0775R.mipmap.ic_dialog_close_white);
            dialogDateSelectBinding.ivConfirm.setImageResource(C0775R.mipmap.ic_dialog_confirm_white);
            dialogDateSelectBinding.tvTitle.setTextColor(ContextCompat.getColor(requireContext(), C0775R.color.g_white));
        }
        DialogDateSelectBinding dialogDateSelectBinding3 = this.binding;
        if (dialogDateSelectBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogDateSelectBinding2 = dialogDateSelectBinding3;
        }
        dialogDateSelectBinding2.dateWheel.isLight(this.isLight);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$4$lambda$2(DateSelectDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$4$lambda$3(DateSelectDialog this$0, DialogDateSelectBinding this_run, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Function3<? super Integer, ? super Integer, ? super Integer, Unit> function3 = this$0.onDateSelect;
        if (function3 != null) {
            function3.invoke(this_run.dateWheel.getSelectDate().get(0), this_run.dateWheel.getSelectDate().get(1), this_run.dateWheel.getSelectDate().get(2));
        }
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onDismiss(dialog);
    }

    /* compiled from: DateSelectDialog.kt */
    @Metadata(m606d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/DateSelectDialog$Companion;", "", "()V", "DIALOG_DEFAULT_DAY", "", "DIALOG_DEFAULT_MONTH", "DIALOG_DEFAULT_YEAR", "DIALOG_TITLE", "IS_LIGHT", "newInstance", "Lcom/glasssutdio/wear/all/dialog/DateSelectDialog;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DateSelectDialog newInstance() {
            return new DateSelectDialog();
        }
    }

    /* compiled from: DateSelectDialog.kt */
    @Metadata(m606d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u001e\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/DateSelectDialog$Builder;", "", "()V", "defaultD", "", "defaultM", "defaultY", "isLight", "", "title", "", "build", "Lcom/glasssutdio/wear/all/dialog/DateSelectDialog;", "setDefaultSelectDate", "year", "month", "day", "setTitle", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Builder {
        private boolean isLight;
        private String title;
        private int defaultY = 2024;
        private int defaultM = 1;
        private int defaultD = 1;

        public final Builder isLight(boolean isLight) {
            this.isLight = isLight;
            return this;
        }

        public final Builder setDefaultSelectDate(int year, int month, int day) {
            this.defaultY = year;
            this.defaultM = month;
            this.defaultD = day;
            return this;
        }

        public final Builder setTitle(String title) {
            Intrinsics.checkNotNullParameter(title, "title");
            this.title = title;
            return this;
        }

        public final DateSelectDialog build() {
            DateSelectDialog dateSelectDialogNewInstance = DateSelectDialog.INSTANCE.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString("dialog_title", this.title);
            bundle.putBoolean("is_light", this.isLight);
            bundle.putInt(DateSelectDialog.DIALOG_DEFAULT_YEAR, this.defaultY);
            bundle.putInt(DateSelectDialog.DIALOG_DEFAULT_MONTH, this.defaultM);
            bundle.putInt(DateSelectDialog.DIALOG_DEFAULT_DAY, this.defaultD);
            dateSelectDialogNewInstance.setArguments(bundle);
            return dateSelectDialogNewInstance;
        }
    }
}
