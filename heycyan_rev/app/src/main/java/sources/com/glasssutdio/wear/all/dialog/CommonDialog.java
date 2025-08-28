package com.glasssutdio.wear.all.dialog;

import android.R;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.databinding.DialogFragmentCommonBinding;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CommonDialog.kt */
@Metadata(m606d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 /2\u00020\u0001:\u0002./B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001a\u001a\u00020\u000eH\u0003J\u0012\u0010\u001b\u001a\u00020\u000e2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0012\u0010\u001e\u001a\u00020\u001f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J$\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0010\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020\u000eH\u0016J)\u0010)\u001a\u00020\u000e2!\u0010*\u001a\u001d\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u000e0\u0011J)\u0010+\u001a\u00020\u000e2!\u0010*\u001a\u001d\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u000e0\u0011J\u0014\u0010,\u001a\u00020\u000e2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u000e0\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0010\u001a\u001f\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0016\u001a\u001f\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/CommonDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/DialogFragmentCommonBinding;", "cancelMessage", "", "cancelable", "", "canceledOnTouchOutside", "confirmMessage", "content", "dismiss", "Lkotlin/Function0;", "", "hideCancelButton", "onCancel", "Lkotlin/Function1;", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "view", "onConfirm", "title", "titleMarginTop", "", "fillData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateDialog", "Landroid/app/Dialog;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "onStart", "setOnCancelListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setOnConfirmListener", "setOnDismissListener", "action", "Builder", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class CommonDialog extends DialogFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String DIALOG_CANCELABLE = "dialog_cancelable";
    public static final String DIALOG_CANCELABLE_OUTSIDE = "dialog_cancelable_outside";
    public static final String DIALOG_CANCEL_MSG = "dialog_cancel_msg";
    public static final String DIALOG_CONFIRM_MSG = "dialog_confirm_msg";
    public static final String DIALOG_CONTENT = "dialog_content";
    public static final String DIALOG_HIDE_CANCEL_BUTTON = "dialog_hide_cancel_button";
    public static final String DIALOG_TITLE = "dialog_title";
    public static final String DIALOG_TITLE_MARGIN_TOP = "dialog_title_margin_top";
    private DialogFragmentCommonBinding binding;
    private String cancelMessage;
    private String confirmMessage;
    private String content;
    private Function0<Unit> dismiss;
    private boolean hideCancelButton;
    private Function1<? super View, Unit> onCancel;
    private Function1<? super View, Unit> onConfirm;
    private String title;
    private boolean cancelable = true;
    private boolean canceledOnTouchOutside = true;
    private int titleMarginTop = GlobalKt.getDp((Number) 26);

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, C0775R.style.Customer_Dialog);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.content = arguments.getString("dialog_content");
            this.title = arguments.getString("dialog_title");
            this.confirmMessage = arguments.getString("dialog_confirm_msg");
            this.cancelMessage = arguments.getString("dialog_cancel_msg");
            this.cancelable = arguments.getBoolean(DIALOG_CANCELABLE, true);
            this.canceledOnTouchOutside = arguments.getBoolean(DIALOG_CANCELABLE_OUTSIDE, true);
            this.hideCancelButton = arguments.getBoolean(DIALOG_HIDE_CANCEL_BUTTON);
            this.titleMarginTop = arguments.getInt(DIALOG_TITLE_MARGIN_TOP);
        }
    }

    public final void setOnConfirmListener(Function1<? super View, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onConfirm = listener;
    }

    public final void setOnCancelListener(Function1<? super View, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onCancel = listener;
    }

    public final void setOnDismissListener(Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        this.dismiss = action;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onDismiss(dialog);
        Function0<Unit> function0 = this.dismiss;
        if (function0 != null) {
            function0.invoke();
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
        DialogFragmentCommonBinding dialogFragmentCommonBindingInflate = DialogFragmentCommonBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(dialogFragmentCommonBindingInflate, "inflate(...)");
        this.binding = dialogFragmentCommonBindingInflate;
        fillData();
        DialogFragmentCommonBinding dialogFragmentCommonBinding = this.binding;
        if (dialogFragmentCommonBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogFragmentCommonBinding = null;
        }
        ConstraintLayout root = dialogFragmentCommonBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialogOnCreateDialog = super.onCreateDialog(savedInstanceState);
        Intrinsics.checkNotNullExpressionValue(dialogOnCreateDialog, "onCreateDialog(...)");
        dialogOnCreateDialog.setCancelable(this.cancelable);
        dialogOnCreateDialog.setCanceledOnTouchOutside(this.canceledOnTouchOutside);
        dialogOnCreateDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.glasssutdio.wear.all.dialog.CommonDialog$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnKeyListener
            public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return CommonDialog.onCreateDialog$lambda$1(this.f$0, dialogInterface, i, keyEvent);
            }
        });
        return dialogOnCreateDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreateDialog$lambda$1(CommonDialog this$0, DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return i == 4 && !this$0.cancelable;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.setLayout(-1, -2);
    }

    private final void fillData() {
        DialogFragmentCommonBinding dialogFragmentCommonBinding = this.binding;
        DialogFragmentCommonBinding dialogFragmentCommonBinding2 = null;
        if (dialogFragmentCommonBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogFragmentCommonBinding = null;
        }
        ImageView ivClose = dialogFragmentCommonBinding.ivClose;
        Intrinsics.checkNotNullExpressionValue(ivClose, "ivClose");
        ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.all.dialog.CommonDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CommonDialog.fillData$lambda$2(this.f$0, view);
            }
        });
        DialogFragmentCommonBinding dialogFragmentCommonBinding3 = this.binding;
        if (dialogFragmentCommonBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogFragmentCommonBinding3 = null;
        }
        ViewKt.goneOrVisible(dialogFragmentCommonBinding3.tvCancel, !this.hideCancelButton);
        DialogFragmentCommonBinding dialogFragmentCommonBinding4 = this.binding;
        if (dialogFragmentCommonBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogFragmentCommonBinding4 = null;
        }
        dialogFragmentCommonBinding4.tvTitle.setText(this.title);
        dialogFragmentCommonBinding4.tvContent.setText(this.content);
        dialogFragmentCommonBinding4.tvConfirm.setText(this.confirmMessage);
        dialogFragmentCommonBinding4.tvCancel.setText(this.cancelMessage);
        TextView tvTitle = dialogFragmentCommonBinding4.tvTitle;
        Intrinsics.checkNotNullExpressionValue(tvTitle, "tvTitle");
        ViewKt.setMargin$default(tvTitle, null, Integer.valueOf(this.titleMarginTop), null, null, 13, null);
        DialogFragmentCommonBinding dialogFragmentCommonBinding5 = this.binding;
        if (dialogFragmentCommonBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogFragmentCommonBinding5 = null;
        }
        dialogFragmentCommonBinding5.tvConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.all.dialog.CommonDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CommonDialog.fillData$lambda$4(this.f$0, view);
            }
        });
        DialogFragmentCommonBinding dialogFragmentCommonBinding6 = this.binding;
        if (dialogFragmentCommonBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogFragmentCommonBinding2 = dialogFragmentCommonBinding6;
        }
        dialogFragmentCommonBinding2.tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.all.dialog.CommonDialog$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CommonDialog.fillData$lambda$5(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fillData$lambda$2(CommonDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fillData$lambda$4(CommonDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function1<? super View, Unit> function1 = this$0.onConfirm;
        if (function1 != null) {
            Intrinsics.checkNotNull(view);
            function1.invoke(view);
        }
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fillData$lambda$5(CommonDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function1<? super View, Unit> function1 = this$0.onCancel;
        if (function1 != null) {
            Intrinsics.checkNotNull(view);
            function1.invoke(view);
        }
        this$0.dismiss();
    }

    /* compiled from: CommonDialog.kt */
    @Metadata(m606d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/CommonDialog$Companion;", "", "()V", "DIALOG_CANCELABLE", "", "DIALOG_CANCELABLE_OUTSIDE", "DIALOG_CANCEL_MSG", "DIALOG_CONFIRM_MSG", "DIALOG_CONTENT", "DIALOG_HIDE_CANCEL_BUTTON", "DIALOG_TITLE", "DIALOG_TITLE_MARGIN_TOP", "newInstance", "Lcom/glasssutdio/wear/all/dialog/CommonDialog;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CommonDialog newInstance() {
            return new CommonDialog();
        }
    }

    /* compiled from: CommonDialog.kt */
    @Metadata(m606d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0006J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0006J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0004J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0004J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0004J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0004J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u000eR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007R\u0012\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/CommonDialog$Builder;", "", "()V", "cancelMessage", "", "cancelable", "", "Ljava/lang/Boolean;", "canceledOnTouchOutside", "confirmMessage", "content", "hideCancelButton", "title", "titleMarginTop", "", "build", "Lcom/glasssutdio/wear/all/dialog/CommonDialog;", "isCancelable", "isCanceledOnTouchOutside", "setCancelMessage", NotificationCompat.CATEGORY_MESSAGE, "setConfirmMessage", "setContent", "setTitle", "margin", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Builder {
        private String cancelMessage;
        private Boolean cancelable;
        private Boolean canceledOnTouchOutside;
        private String confirmMessage;
        private String content;
        private boolean hideCancelButton;
        private String title;
        private int titleMarginTop = GlobalKt.getDp((Number) 26);

        public final Builder isCancelable(boolean cancelable) {
            this.cancelable = Boolean.valueOf(cancelable);
            return this;
        }

        public final Builder hideCancelButton(boolean hideCancelButton) {
            this.hideCancelButton = hideCancelButton;
            return this;
        }

        public final Builder titleMarginTop(int margin) {
            this.titleMarginTop = margin;
            return this;
        }

        public final Builder isCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = Boolean.valueOf(canceledOnTouchOutside);
            return this;
        }

        public final Builder setContent(String content) {
            Intrinsics.checkNotNullParameter(content, "content");
            this.content = content;
            return this;
        }

        public final Builder setTitle(String title) {
            Intrinsics.checkNotNullParameter(title, "title");
            this.title = title;
            return this;
        }

        public final Builder setConfirmMessage(String msg) {
            Intrinsics.checkNotNullParameter(msg, "msg");
            this.confirmMessage = msg;
            return this;
        }

        public final Builder setCancelMessage(String msg) {
            Intrinsics.checkNotNullParameter(msg, "msg");
            this.cancelMessage = msg;
            return this;
        }

        public final CommonDialog build() {
            CommonDialog commonDialogNewInstance = CommonDialog.INSTANCE.newInstance();
            Bundle bundle = new Bundle();
            String str = this.content;
            if (str != null) {
                bundle.putString("dialog_content", str);
            }
            String str2 = this.title;
            if (str2 != null) {
                bundle.putString("dialog_title", str2);
            }
            String str3 = this.confirmMessage;
            if (str3 != null) {
                bundle.putString("dialog_confirm_msg", str3);
            }
            String str4 = this.cancelMessage;
            if (str4 != null) {
                bundle.putString("dialog_cancel_msg", str4);
            }
            Boolean bool = this.cancelable;
            if (bool != null) {
                bundle.putBoolean(CommonDialog.DIALOG_CANCELABLE, bool.booleanValue());
            }
            Boolean bool2 = this.canceledOnTouchOutside;
            if (bool2 != null) {
                bundle.putBoolean(CommonDialog.DIALOG_CANCELABLE_OUTSIDE, bool2.booleanValue());
            }
            bundle.putInt(CommonDialog.DIALOG_TITLE_MARGIN_TOP, this.titleMarginTop);
            bundle.putBoolean(CommonDialog.DIALOG_HIDE_CANCEL_BUTTON, this.hideCancelButton);
            commonDialogNewInstance.setArguments(bundle);
            return commonDialogNewInstance;
        }
    }
}
