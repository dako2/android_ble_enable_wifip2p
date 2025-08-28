package com.glasssutdio.wear.all.dialog;

import android.R;
import android.app.Dialog;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.EditTextViewExtKt;
import com.glasssutdio.wear.databinding.DialogEditCenterBinding;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EditCenterDialog.kt */
@Metadata(m606d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 !2\u00020\u0001:\u0002 !B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0011H\u0003J\u0012\u0010\u0015\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J$\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u001d\u001a\u00020\u0011H\u0016J>\u0010\u001e\u001a\u00020\u001126\u0010\u001f\u001a2\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00110\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R@\u0010\u000b\u001a4\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u0011\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/EditCenterDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/DialogEditCenterBinding;", "content", "", "isLight", "", "maxLength", "", "onConfirm", "Lkotlin/Function2;", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "view", "", "textHint", "title", "fillData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onStart", "setOnConfirmListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Builder", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class EditCenterDialog extends DialogFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String DIALOG_CONTENT = "dialog_content";
    public static final String DIALOG_EDIT_HINT = "dialog_edit_hint";
    public static final String DIALOG_EDIT_MAX_LENGTH = "dialog_edit_max_length";
    public static final String DIALOG_TITLE = "dialog_title";
    public static final String IS_LIGHT = "is_light";
    private DialogEditCenterBinding binding;
    private String content;
    private boolean isLight;
    private int maxLength = 18;
    private Function2<? super View, ? super String, Unit> onConfirm;
    private String textHint;
    private String title;

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, C0775R.style.Customer_Dialog);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.content = arguments.getString("dialog_content");
            this.title = arguments.getString("dialog_title");
            this.textHint = arguments.getString(DIALOG_EDIT_HINT);
            this.isLight = arguments.getBoolean("is_light");
            this.maxLength = arguments.getInt(DIALOG_EDIT_MAX_LENGTH);
        }
    }

    public final void setOnConfirmListener(Function2<? super View, ? super String, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onConfirm = listener;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Window window;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Dialog dialog = getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setBackgroundDrawableResource(R.color.transparent);
        }
        DialogEditCenterBinding dialogEditCenterBindingInflate = DialogEditCenterBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(dialogEditCenterBindingInflate, "inflate(...)");
        this.binding = dialogEditCenterBindingInflate;
        fillData();
        DialogEditCenterBinding dialogEditCenterBinding = this.binding;
        if (dialogEditCenterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogEditCenterBinding = null;
        }
        ConstraintLayout root = dialogEditCenterBinding.getRoot();
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
        final DialogEditCenterBinding dialogEditCenterBinding = this.binding;
        if (dialogEditCenterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogEditCenterBinding = null;
        }
        dialogEditCenterBinding.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.all.dialog.EditCenterDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditCenterDialog.fillData$lambda$6$lambda$1(this.f$0, view);
            }
        });
        dialogEditCenterBinding.tvTitle.setText(this.title);
        String str = this.textHint;
        if (str != null) {
            dialogEditCenterBinding.etContent.setHint(str);
        }
        if (this.content != null) {
            dialogEditCenterBinding.etContent.setText(this.content);
        }
        dialogEditCenterBinding.etContent.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(this.maxLength)});
        dialogEditCenterBinding.ivConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.all.dialog.EditCenterDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditCenterDialog.fillData$lambda$6$lambda$4(dialogEditCenterBinding, this, view);
            }
        });
        dialogEditCenterBinding.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.all.dialog.EditCenterDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditCenterDialog.fillData$lambda$6$lambda$5(this.f$0, view);
            }
        });
        if (this.isLight) {
            dialogEditCenterBinding.ivBg.setImageResource(C0775R.drawable.bg_white_20_shape);
            dialogEditCenterBinding.ivClose.setImageResource(C0775R.mipmap.ic_dialog_close_black);
            dialogEditCenterBinding.ivConfirm.setImageResource(C0775R.mipmap.ic_dialog_confirm_black);
            dialogEditCenterBinding.tvTitle.setTextColor(ContextCompat.getColor(requireContext(), C0775R.color.color_141414));
            dialogEditCenterBinding.etContent.setTextColor(ContextCompat.getColor(requireContext(), C0775R.color.color_141414));
            dialogEditCenterBinding.viewLine.setBackgroundResource(C0775R.color.color_BDBDBD);
            return;
        }
        dialogEditCenterBinding.ivBg.setImageResource(C0775R.drawable.main_setting_bg);
        dialogEditCenterBinding.ivClose.setImageResource(C0775R.mipmap.ic_dialog_close_white);
        dialogEditCenterBinding.ivConfirm.setImageResource(C0775R.mipmap.ic_dialog_confirm_white);
        dialogEditCenterBinding.tvTitle.setTextColor(ContextCompat.getColor(requireContext(), C0775R.color.g_white));
        dialogEditCenterBinding.etContent.setTextColor(ContextCompat.getColor(requireContext(), C0775R.color.g_white));
        dialogEditCenterBinding.viewLine.setBackgroundResource(C0775R.color.g_line_color);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fillData$lambda$6$lambda$1(EditCenterDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fillData$lambda$6$lambda$4(DialogEditCenterBinding this_run, EditCenterDialog this$0, View view) {
        Function2<? super View, ? super String, Unit> function2;
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EditText etContent = this_run.etContent;
        Intrinsics.checkNotNullExpressionValue(etContent, "etContent");
        if (!EditTextViewExtKt.isEmpty(etContent) && (function2 = this$0.onConfirm) != null) {
            Intrinsics.checkNotNull(view);
            EditText etContent2 = this_run.etContent;
            Intrinsics.checkNotNullExpressionValue(etContent2, "etContent");
            function2.invoke(view, EditTextViewExtKt.textString(etContent2));
        }
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fillData$lambda$6$lambda$5(EditCenterDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* compiled from: EditCenterDialog.kt */
    @Metadata(m606d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/EditCenterDialog$Companion;", "", "()V", "DIALOG_CONTENT", "", "DIALOG_EDIT_HINT", "DIALOG_EDIT_MAX_LENGTH", "DIALOG_TITLE", "IS_LIGHT", "newInstance", "Lcom/glasssutdio/wear/all/dialog/EditCenterDialog;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final EditCenterDialog newInstance() {
            return new EditCenterDialog();
        }
    }

    /* compiled from: EditCenterDialog.kt */
    @Metadata(m606d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0004J\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\bJ\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/EditCenterDialog$Builder;", "", "()V", "content", "", "isLight", "", "maxLength", "", "textHint", "title", "build", "Lcom/glasssutdio/wear/all/dialog/EditCenterDialog;", "setContent", "setHint", "hint", "setMaxLength", "length", "setTitle", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Builder {
        private String content;
        private boolean isLight;
        private int maxLength = 18;
        private String textHint;
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

        public final Builder setHint(String hint) {
            Intrinsics.checkNotNullParameter(hint, "hint");
            this.textHint = hint;
            return this;
        }

        public final Builder setMaxLength(int length) {
            this.maxLength = length;
            return this;
        }

        public final Builder isLight(boolean isLight) {
            this.isLight = isLight;
            return this;
        }

        public final EditCenterDialog build() {
            EditCenterDialog editCenterDialogNewInstance = EditCenterDialog.INSTANCE.newInstance();
            Bundle bundle = new Bundle();
            String str = this.content;
            if (str != null) {
                bundle.putString("dialog_content", str);
            }
            String str2 = this.title;
            if (str2 != null) {
                bundle.putString("dialog_title", str2);
            }
            String str3 = this.textHint;
            if (str3 != null) {
                bundle.putString(EditCenterDialog.DIALOG_EDIT_HINT, str3);
            }
            bundle.putBoolean("is_light", this.isLight);
            bundle.putInt(EditCenterDialog.DIALOG_EDIT_MAX_LENGTH, this.maxLength);
            editCenterDialogNewInstance.setArguments(bundle);
            return editCenterDialogNewInstance;
        }
    }
}
