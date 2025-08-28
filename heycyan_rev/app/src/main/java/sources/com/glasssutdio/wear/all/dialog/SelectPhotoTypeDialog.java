package com.glasssutdio.wear.all.dialog;

import android.R;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.databinding.DialogPhotoSelectTypeBinding;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelectPhotoTypeDialog.kt */
@Metadata(m606d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001b\u001cB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\u000bH\u0003J\u0012\u0010\r\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J$\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J)\u0010\u0019\u001a\u00020\u000b2!\u0010\u001a\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R+\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/SelectPhotoTypeDialog;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/DialogPhotoSelectTypeBinding;", "onTypeSelect", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "isTakePhoto", "", "fillData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "setOnTypeSelectListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Builder", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class SelectPhotoTypeDialog extends BottomSheetDialogFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private DialogPhotoSelectTypeBinding binding;
    private Function1<? super Boolean, Unit> onTypeSelect;

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, C0775R.style.Customer_Dialog);
    }

    public final void setOnTypeSelectListener(Function1<? super Boolean, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onTypeSelect = listener;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Window window;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Dialog dialog = getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setBackgroundDrawableResource(R.color.transparent);
        }
        DialogPhotoSelectTypeBinding dialogPhotoSelectTypeBindingInflate = DialogPhotoSelectTypeBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(dialogPhotoSelectTypeBindingInflate, "inflate(...)");
        this.binding = dialogPhotoSelectTypeBindingInflate;
        fillData();
        DialogPhotoSelectTypeBinding dialogPhotoSelectTypeBinding = this.binding;
        if (dialogPhotoSelectTypeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPhotoSelectTypeBinding = null;
        }
        ConstraintLayout root = dialogPhotoSelectTypeBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    private final void fillData() {
        DialogPhotoSelectTypeBinding dialogPhotoSelectTypeBinding = this.binding;
        if (dialogPhotoSelectTypeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPhotoSelectTypeBinding = null;
        }
        dialogPhotoSelectTypeBinding.tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.all.dialog.SelectPhotoTypeDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SelectPhotoTypeDialog.fillData$lambda$3$lambda$0(this.f$0, view);
            }
        });
        dialogPhotoSelectTypeBinding.tvTake.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.all.dialog.SelectPhotoTypeDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SelectPhotoTypeDialog.fillData$lambda$3$lambda$1(this.f$0, view);
            }
        });
        dialogPhotoSelectTypeBinding.tvAlbums.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.all.dialog.SelectPhotoTypeDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SelectPhotoTypeDialog.fillData$lambda$3$lambda$2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fillData$lambda$3$lambda$0(SelectPhotoTypeDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fillData$lambda$3$lambda$1(SelectPhotoTypeDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function1<? super Boolean, Unit> function1 = this$0.onTypeSelect;
        if (function1 != null) {
            function1.invoke(true);
        }
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fillData$lambda$3$lambda$2(SelectPhotoTypeDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function1<? super Boolean, Unit> function1 = this$0.onTypeSelect;
        if (function1 != null) {
            function1.invoke(false);
        }
        this$0.dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onDismiss(dialog);
    }

    /* compiled from: SelectPhotoTypeDialog.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/SelectPhotoTypeDialog$Companion;", "", "()V", "newInstance", "Lcom/glasssutdio/wear/all/dialog/SelectPhotoTypeDialog;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SelectPhotoTypeDialog newInstance() {
            return new SelectPhotoTypeDialog();
        }
    }

    /* compiled from: SelectPhotoTypeDialog.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/SelectPhotoTypeDialog$Builder;", "", "()V", "build", "Lcom/glasssutdio/wear/all/dialog/SelectPhotoTypeDialog;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Builder {
        public final SelectPhotoTypeDialog build() {
            SelectPhotoTypeDialog selectPhotoTypeDialogNewInstance = SelectPhotoTypeDialog.INSTANCE.newInstance();
            selectPhotoTypeDialogNewInstance.setArguments(new Bundle());
            return selectPhotoTypeDialogNewInstance;
        }
    }
}
