package com.glasssutdio.wear.all.dialog;

import android.R;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.databinding.DialogFragmentDeleteOrNotBinding;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeleteOrNotDialog.kt */
@Metadata(m606d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u000fH\u0003J\u0012\u0010\u0013\u001a\u00020\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J$\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u001b\u001a\u00020\u000fH\u0016J)\u0010\u001c\u001a\u00020\u000f2!\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\nJ)\u0010\u001e\u001a\u00020\u000f2!\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\t\u001a\u001f\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0010\u001a\u001f\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/DeleteOrNotDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/DialogFragmentDeleteOrNotBinding;", "cancelMessage", "", "confirmMessage", "content", "onCancel", "Lkotlin/Function1;", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "view", "", "onConfirm", "title", "fillData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onStart", "setOnCancelListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setOnConfirmListener", "Builder", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class DeleteOrNotDialog extends DialogFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String DIALOG_CANCEL_MSG = "dialog_cancel_msg";
    public static final String DIALOG_CONFIRM_MSG = "dialog_confirm_msg";
    public static final String DIALOG_CONTENT = "dialog_content";
    public static final String DIALOG_TITLE = "dialog_title";
    private DialogFragmentDeleteOrNotBinding binding;
    private String cancelMessage;
    private String confirmMessage;
    private String content;
    private Function1<? super View, Unit> onCancel;
    private Function1<? super View, Unit> onConfirm;
    private String title;

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

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Window window;
        Window window2;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Dialog dialog = getDialog();
        if (dialog != null && (window2 = dialog.getWindow()) != null) {
            window2.setBackgroundDrawableResource(R.color.transparent);
        }
        Dialog dialog2 = getDialog();
        DialogFragmentDeleteOrNotBinding dialogFragmentDeleteOrNotBinding = null;
        WindowManager.LayoutParams attributes = (dialog2 == null || (window = dialog2.getWindow()) == null) ? null : window.getAttributes();
        if (attributes != null) {
            attributes.gravity = 80;
        }
        DialogFragmentDeleteOrNotBinding dialogFragmentDeleteOrNotBindingInflate = DialogFragmentDeleteOrNotBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(dialogFragmentDeleteOrNotBindingInflate, "inflate(...)");
        this.binding = dialogFragmentDeleteOrNotBindingInflate;
        fillData();
        DialogFragmentDeleteOrNotBinding dialogFragmentDeleteOrNotBinding2 = this.binding;
        if (dialogFragmentDeleteOrNotBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogFragmentDeleteOrNotBinding = dialogFragmentDeleteOrNotBinding2;
        }
        ConstraintLayout root = dialogFragmentDeleteOrNotBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
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
        DialogFragmentDeleteOrNotBinding dialogFragmentDeleteOrNotBinding = this.binding;
        DialogFragmentDeleteOrNotBinding dialogFragmentDeleteOrNotBinding2 = null;
        if (dialogFragmentDeleteOrNotBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogFragmentDeleteOrNotBinding = null;
        }
        ImageView ivClose = dialogFragmentDeleteOrNotBinding.ivClose;
        Intrinsics.checkNotNullExpressionValue(ivClose, "ivClose");
        ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.all.dialog.DeleteOrNotDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeleteOrNotDialog.fillData$lambda$1(this.f$0, view);
            }
        });
        DialogFragmentDeleteOrNotBinding dialogFragmentDeleteOrNotBinding3 = this.binding;
        if (dialogFragmentDeleteOrNotBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogFragmentDeleteOrNotBinding3 = null;
        }
        dialogFragmentDeleteOrNotBinding3.tvTitle.setText(this.title);
        dialogFragmentDeleteOrNotBinding3.tvContent.setText(this.content);
        dialogFragmentDeleteOrNotBinding3.tvConfirm.setText(this.confirmMessage);
        dialogFragmentDeleteOrNotBinding3.tvCancel.setText(this.cancelMessage);
        DialogFragmentDeleteOrNotBinding dialogFragmentDeleteOrNotBinding4 = this.binding;
        if (dialogFragmentDeleteOrNotBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogFragmentDeleteOrNotBinding4 = null;
        }
        dialogFragmentDeleteOrNotBinding4.tvConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.all.dialog.DeleteOrNotDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeleteOrNotDialog.fillData$lambda$3(this.f$0, view);
            }
        });
        DialogFragmentDeleteOrNotBinding dialogFragmentDeleteOrNotBinding5 = this.binding;
        if (dialogFragmentDeleteOrNotBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogFragmentDeleteOrNotBinding2 = dialogFragmentDeleteOrNotBinding5;
        }
        dialogFragmentDeleteOrNotBinding2.tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.all.dialog.DeleteOrNotDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeleteOrNotDialog.fillData$lambda$4(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fillData$lambda$1(DeleteOrNotDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fillData$lambda$3(DeleteOrNotDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function1<? super View, Unit> function1 = this$0.onConfirm;
        if (function1 != null) {
            Intrinsics.checkNotNull(view);
            function1.invoke(view);
        }
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fillData$lambda$4(DeleteOrNotDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function1<? super View, Unit> function1 = this$0.onCancel;
        if (function1 != null) {
            Intrinsics.checkNotNull(view);
            function1.invoke(view);
        }
        this$0.dismiss();
    }

    /* compiled from: DeleteOrNotDialog.kt */
    @Metadata(m606d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/DeleteOrNotDialog$Companion;", "", "()V", "DIALOG_CANCEL_MSG", "", "DIALOG_CONFIRM_MSG", "DIALOG_CONTENT", "DIALOG_TITLE", "newInstance", "Lcom/glasssutdio/wear/all/dialog/DeleteOrNotDialog;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DeleteOrNotDialog newInstance() {
            return new DeleteOrNotDialog();
        }
    }

    /* compiled from: DeleteOrNotDialog.kt */
    @Metadata(m606d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0004J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/DeleteOrNotDialog$Builder;", "", "()V", "cancelMessage", "", "confirmMessage", "content", "title", "build", "Lcom/glasssutdio/wear/all/dialog/DeleteOrNotDialog;", "setCancelMessage", NotificationCompat.CATEGORY_MESSAGE, "setConfirmMessage", "setContent", "setTitle", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Builder {
        private String cancelMessage;
        private String confirmMessage;
        private String content;
        private String title;

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

        public final DeleteOrNotDialog build() {
            DeleteOrNotDialog deleteOrNotDialogNewInstance = DeleteOrNotDialog.INSTANCE.newInstance();
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
            deleteOrNotDialogNewInstance.setArguments(bundle);
            return deleteOrNotDialogNewInstance;
        }
    }
}
