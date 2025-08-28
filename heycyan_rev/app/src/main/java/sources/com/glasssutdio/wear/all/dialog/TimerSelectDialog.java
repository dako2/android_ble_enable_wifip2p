package com.glasssutdio.wear.all.dialog;

import android.R;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.adapter.TimbreSelectAdapter;
import com.glasssutdio.wear.all.bean.TimbreModel;
import com.glasssutdio.wear.databinding.DialogFragmentTimbreBinding;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TimerSelectDialog.kt */
@Metadata(m606d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0003J\u0012\u0010\u0011\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J$\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J>\u0010\u001d\u001a\u00020\u000f26\u0010\u001e\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007R@\u0010\b\u001a4\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/TimerSelectDialog;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/DialogFragmentTimbreBinding;", "defaultCheck", "", "Ljava/lang/Integer;", "onItemChecked", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "position", "Lcom/glasssutdio/wear/all/bean/TimbreModel;", "model", "", "fillData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "setOnItemCheckedListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Builder", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class TimerSelectDialog extends BottomSheetDialogFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String DIALOG_DEFAULT_CHECK = "dialog_default_check";
    private DialogFragmentTimbreBinding binding;
    private Integer defaultCheck;
    private Function2<? super Integer, ? super TimbreModel, Unit> onItemChecked;

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, C0775R.style.Customer_Dialog);
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            this.defaultCheck = arguments != null ? Integer.valueOf(arguments.getInt(DIALOG_DEFAULT_CHECK, -1)) : null;
        }
    }

    public final void setOnItemCheckedListener(Function2<? super Integer, ? super TimbreModel, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onItemChecked = listener;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Window window;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Dialog dialog = getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setBackgroundDrawableResource(R.color.transparent);
        }
        DialogFragmentTimbreBinding dialogFragmentTimbreBindingInflate = DialogFragmentTimbreBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(dialogFragmentTimbreBindingInflate, "inflate(...)");
        this.binding = dialogFragmentTimbreBindingInflate;
        fillData();
        DialogFragmentTimbreBinding dialogFragmentTimbreBinding = this.binding;
        if (dialogFragmentTimbreBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogFragmentTimbreBinding = null;
        }
        ConstraintLayout root = dialogFragmentTimbreBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    private final void fillData() {
        String string = getString(C0775R.string.h_glass_203);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        List listListOf = CollectionsKt.listOf(new TimbreModel(string, C0775R.mipmap.ic_voice_women_a, C0775R.drawable.bg_timbre_3_shape, false, 8, null));
        int i = 0;
        for (Object obj : listListOf) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TimbreModel timbreModel = (TimbreModel) obj;
            Integer num = this.defaultCheck;
            timbreModel.setChecked(num != null && i == num.intValue());
            i = i2;
        }
        final TimbreSelectAdapter timbreSelectAdapter = new TimbreSelectAdapter();
        DialogFragmentTimbreBinding dialogFragmentTimbreBinding = this.binding;
        DialogFragmentTimbreBinding dialogFragmentTimbreBinding2 = null;
        if (dialogFragmentTimbreBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogFragmentTimbreBinding = null;
        }
        RecyclerView rcyTimbre = dialogFragmentTimbreBinding.rcyTimbre;
        Intrinsics.checkNotNullExpressionValue(rcyTimbre, "rcyTimbre");
        rcyTimbre.setLayoutManager(new LinearLayoutManager(requireContext()));
        rcyTimbre.setAdapter(timbreSelectAdapter);
        timbreSelectAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.glasssutdio.wear.all.dialog.TimerSelectDialog$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i3) {
                TimerSelectDialog.fillData$lambda$3(timbreSelectAdapter, this, baseQuickAdapter, view, i3);
            }
        });
        timbreSelectAdapter.setList(listListOf);
        DialogFragmentTimbreBinding dialogFragmentTimbreBinding3 = this.binding;
        if (dialogFragmentTimbreBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogFragmentTimbreBinding2 = dialogFragmentTimbreBinding3;
        }
        ImageView ivClose = dialogFragmentTimbreBinding2.ivClose;
        Intrinsics.checkNotNullExpressionValue(ivClose, "ivClose");
        ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.all.dialog.TimerSelectDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TimerSelectDialog.fillData$lambda$4(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fillData$lambda$3(TimbreSelectAdapter mAdapter, TimerSelectDialog this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(mAdapter, "$mAdapter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        int i2 = 0;
        for (Object obj : mAdapter.getData()) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ((TimbreModel) obj).setChecked(i2 == i);
            mAdapter.notifyDataSetChanged();
            i2 = i3;
        }
        Function2<? super Integer, ? super TimbreModel, Unit> function2 = this$0.onItemChecked;
        if (function2 != null) {
            function2.invoke(Integer.valueOf(i), mAdapter.getData().get(i));
        }
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fillData$lambda$4(TimerSelectDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onDismiss(dialog);
    }

    /* compiled from: TimerSelectDialog.kt */
    @Metadata(m606d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/TimerSelectDialog$Companion;", "", "()V", "DIALOG_DEFAULT_CHECK", "", "newInstance", "Lcom/glasssutdio/wear/all/dialog/TimerSelectDialog;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TimerSelectDialog newInstance() {
            return new TimerSelectDialog();
        }
    }

    /* compiled from: TimerSelectDialog.kt */
    @Metadata(m606d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0006\u001a\u00020\u0007J\u0015\u0010\b\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\nR\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\u000b"}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/TimerSelectDialog$Builder;", "", "()V", "check", "", "Ljava/lang/Integer;", "build", "Lcom/glasssutdio/wear/all/dialog/TimerSelectDialog;", "setDefaultCheck", "position", "(Ljava/lang/Integer;)Lcom/glasssutdio/wear/all/dialog/TimerSelectDialog$Builder;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Builder {
        private Integer check;

        public final Builder setDefaultCheck(Integer position) {
            this.check = position;
            return this;
        }

        public final TimerSelectDialog build() {
            TimerSelectDialog timerSelectDialogNewInstance = TimerSelectDialog.INSTANCE.newInstance();
            Bundle bundle = new Bundle();
            Integer num = this.check;
            if (num != null) {
                bundle.putInt(TimerSelectDialog.DIALOG_DEFAULT_CHECK, num.intValue());
            }
            timerSelectDialogNewInstance.setArguments(bundle);
            return timerSelectDialogNewInstance;
        }
    }
}
