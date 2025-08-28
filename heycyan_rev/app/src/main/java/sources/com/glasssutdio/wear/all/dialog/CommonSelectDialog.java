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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.adapter.CommonSelectAdapter;
import com.glasssutdio.wear.all.bean.CommonSelectModel;
import com.glasssutdio.wear.databinding.DialogFragmentCommonSelectBinding;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CommonSelectDialog.kt */
@Metadata(m606d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\u0018\u0000 *2\u00020\u0001:\u0002)*B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0014H\u0003J\u0012\u0010\u0018\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J$\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020#H\u0016J\u0014\u0010$\u001a\u00020\u00142\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\r0&J>\u0010'\u001a\u00020\u001426\u0010(\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00140\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e¢\u0006\u0002\n\u0000R@\u0010\u000e\u001a4\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/CommonSelectDialog;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/DialogFragmentCommonSelectBinding;", "defaultIndex", "", "Ljava/lang/Integer;", "isLight", "", "isShowSaveIcon", "mData", "", "Lcom/glasssutdio/wear/all/bean/CommonSelectModel;", "onItemChecked", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "position", "model", "", "title", "", "fillData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "setData", "list", "", "setOnItemCheckedListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Builder", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class CommonSelectDialog extends BottomSheetDialogFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String DEFAULT_INDEX = "default_index";
    public static final String DIALOG_TITLE = "dialog_title";
    public static final String IS_LIGHT = "is_light";
    public static final String SHOW_SAVE_ICON = "show_save_icon";
    private DialogFragmentCommonSelectBinding binding;
    private Integer defaultIndex;
    private boolean isLight;
    private Function2<? super Integer, ? super CommonSelectModel, Unit> onItemChecked;
    private String title;
    private List<CommonSelectModel> mData = new ArrayList();
    private boolean isShowSaveIcon = true;

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, C0775R.style.Customer_Dialog);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.defaultIndex = Integer.valueOf(arguments.getInt(DEFAULT_INDEX));
            this.title = arguments.getString("dialog_title");
            this.isLight = arguments.getBoolean("is_light");
            this.isShowSaveIcon = arguments.getBoolean(SHOW_SAVE_ICON, true);
        }
    }

    public final void setData(List<CommonSelectModel> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.mData.clear();
        this.mData.addAll(list);
    }

    public final void setOnItemCheckedListener(Function2<? super Integer, ? super CommonSelectModel, Unit> listener) {
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
        DialogFragmentCommonSelectBinding dialogFragmentCommonSelectBindingInflate = DialogFragmentCommonSelectBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(dialogFragmentCommonSelectBindingInflate, "inflate(...)");
        this.binding = dialogFragmentCommonSelectBindingInflate;
        fillData();
        DialogFragmentCommonSelectBinding dialogFragmentCommonSelectBinding = this.binding;
        if (dialogFragmentCommonSelectBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogFragmentCommonSelectBinding = null;
        }
        ConstraintLayout root = dialogFragmentCommonSelectBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    private final void fillData() {
        final CommonSelectAdapter commonSelectAdapter = new CommonSelectAdapter(this.isLight);
        DialogFragmentCommonSelectBinding dialogFragmentCommonSelectBinding = this.binding;
        DialogFragmentCommonSelectBinding dialogFragmentCommonSelectBinding2 = null;
        if (dialogFragmentCommonSelectBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogFragmentCommonSelectBinding = null;
        }
        RecyclerView recyclerView = dialogFragmentCommonSelectBinding.recyclerView;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(commonSelectAdapter);
        commonSelectAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.glasssutdio.wear.all.dialog.CommonSelectDialog$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                CommonSelectDialog.fillData$lambda$2(commonSelectAdapter, this, baseQuickAdapter, view, i);
            }
        });
        commonSelectAdapter.setList(this.mData);
        DialogFragmentCommonSelectBinding dialogFragmentCommonSelectBinding3 = this.binding;
        if (dialogFragmentCommonSelectBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogFragmentCommonSelectBinding3 = null;
        }
        dialogFragmentCommonSelectBinding3.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.all.dialog.CommonSelectDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CommonSelectDialog.fillData$lambda$3(this.f$0, view);
            }
        });
        DialogFragmentCommonSelectBinding dialogFragmentCommonSelectBinding4 = this.binding;
        if (dialogFragmentCommonSelectBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogFragmentCommonSelectBinding4 = null;
        }
        dialogFragmentCommonSelectBinding4.ivConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.all.dialog.CommonSelectDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CommonSelectDialog.fillData$lambda$5(this.f$0, commonSelectAdapter, view);
            }
        });
        Integer num = this.defaultIndex;
        if (num != null) {
            int iIntValue = num.intValue();
            int i = 0;
            for (Object obj : commonSelectAdapter.getData()) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                ((CommonSelectModel) obj).setChecked(i == iIntValue);
                commonSelectAdapter.notifyDataSetChanged();
                i = i2;
            }
        }
        DialogFragmentCommonSelectBinding dialogFragmentCommonSelectBinding5 = this.binding;
        if (dialogFragmentCommonSelectBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogFragmentCommonSelectBinding2 = dialogFragmentCommonSelectBinding5;
        }
        dialogFragmentCommonSelectBinding2.tvTitle.setText(this.title);
        if (this.isLight) {
            dialogFragmentCommonSelectBinding2.dialogView.setBackgroundResource(C0775R.drawable.bg_white_20_shape);
            dialogFragmentCommonSelectBinding2.ivClose.setImageResource(C0775R.mipmap.ic_dialog_close_black);
            dialogFragmentCommonSelectBinding2.ivConfirm.setImageResource(C0775R.mipmap.ic_dialog_confirm_black);
            dialogFragmentCommonSelectBinding2.tvTitle.setTextColor(ContextCompat.getColor(requireContext(), C0775R.color.color_141414));
            if (this.isShowSaveIcon) {
                ViewKt.visible(dialogFragmentCommonSelectBinding2.ivClose);
                ViewKt.visible(dialogFragmentCommonSelectBinding2.ivConfirm);
                return;
            } else {
                ViewKt.gone(dialogFragmentCommonSelectBinding2.ivClose);
                ViewKt.visible(dialogFragmentCommonSelectBinding2.ivConfirm);
                dialogFragmentCommonSelectBinding2.ivConfirm.setImageResource(C0775R.mipmap.ic_dialog_close_black);
                return;
            }
        }
        dialogFragmentCommonSelectBinding2.dialogView.setBackgroundResource(C0775R.drawable.main_setting_bg);
        dialogFragmentCommonSelectBinding2.ivClose.setImageResource(C0775R.mipmap.ic_dialog_close_white);
        dialogFragmentCommonSelectBinding2.ivConfirm.setImageResource(C0775R.mipmap.ic_dialog_confirm_white);
        dialogFragmentCommonSelectBinding2.tvTitle.setTextColor(ContextCompat.getColor(requireContext(), C0775R.color.g_white));
        if (this.isShowSaveIcon) {
            ViewKt.visible(dialogFragmentCommonSelectBinding2.ivClose);
            ViewKt.visible(dialogFragmentCommonSelectBinding2.ivConfirm);
        } else {
            ViewKt.gone(dialogFragmentCommonSelectBinding2.ivClose);
            ViewKt.visible(dialogFragmentCommonSelectBinding2.ivConfirm);
            dialogFragmentCommonSelectBinding2.ivConfirm.setImageResource(C0775R.mipmap.ic_dialog_close_white);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fillData$lambda$2(CommonSelectAdapter mAdapter, CommonSelectDialog this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
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
            ((CommonSelectModel) obj).setChecked(i2 == i);
            mAdapter.notifyDataSetChanged();
            i2 = i3;
        }
        this$0.defaultIndex = Integer.valueOf(i);
        if (this$0.isShowSaveIcon) {
            return;
        }
        Function2<? super Integer, ? super CommonSelectModel, Unit> function2 = this$0.onItemChecked;
        if (function2 != null) {
            function2.invoke(Integer.valueOf(i), mAdapter.getData().get(i));
        }
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fillData$lambda$3(CommonSelectDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fillData$lambda$5(CommonSelectDialog this$0, CommonSelectAdapter mAdapter, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mAdapter, "$mAdapter");
        if (this$0.isShowSaveIcon) {
            Integer num = this$0.defaultIndex;
            if (num != null) {
                int iIntValue = num.intValue();
                Function2<? super Integer, ? super CommonSelectModel, Unit> function2 = this$0.onItemChecked;
                if (function2 != null) {
                    function2.invoke(Integer.valueOf(iIntValue), mAdapter.getData().get(iIntValue));
                }
                this$0.dismiss();
                return;
            }
            return;
        }
        this$0.dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onDismiss(dialog);
    }

    /* compiled from: CommonSelectDialog.kt */
    @Metadata(m606d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/CommonSelectDialog$Companion;", "", "()V", "DEFAULT_INDEX", "", "DIALOG_TITLE", "IS_LIGHT", "SHOW_SAVE_ICON", "newInstance", "Lcom/glasssutdio/wear/all/dialog/CommonSelectDialog;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CommonSelectDialog newInstance() {
            return new CommonSelectDialog();
        }
    }

    /* compiled from: CommonSelectDialog.kt */
    @Metadata(m606d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u0014\u0010\u0011\u001a\u00020\u00002\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0013J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, m607d2 = {"Lcom/glasssutdio/wear/all/dialog/CommonSelectDialog$Builder;", "", "()V", "isLight", "", "mList", "Ljava/util/ArrayList;", "Lcom/glasssutdio/wear/all/bean/CommonSelectModel;", "Lkotlin/collections/ArrayList;", "position", "", "Ljava/lang/Integer;", "showSaveIcon", "title", "", "build", "Lcom/glasssutdio/wear/all/dialog/CommonSelectDialog;", "setData", "data", "", "setDefaultChecked", "setTitle", "show", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Builder {
        private boolean isLight;
        private Integer position;
        private String title;
        private ArrayList<CommonSelectModel> mList = new ArrayList<>();
        private boolean showSaveIcon = true;

        public final Builder setDefaultChecked(int position) {
            this.position = Integer.valueOf(position);
            return this;
        }

        public final Builder isLight(boolean isLight) {
            this.isLight = isLight;
            return this;
        }

        public final Builder setData(List<CommonSelectModel> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.mList.clear();
            this.mList.addAll(data);
            return this;
        }

        public final Builder setTitle(String title) {
            Intrinsics.checkNotNullParameter(title, "title");
            this.title = title;
            return this;
        }

        public final Builder showSaveIcon(boolean show) {
            this.showSaveIcon = show;
            return this;
        }

        public final CommonSelectDialog build() {
            CommonSelectDialog commonSelectDialogNewInstance = CommonSelectDialog.INSTANCE.newInstance();
            Bundle bundle = new Bundle();
            Integer num = this.position;
            if (num != null) {
                bundle.putInt(CommonSelectDialog.DEFAULT_INDEX, num.intValue());
            }
            String str = this.title;
            if (str != null) {
                bundle.putString("dialog_title", str);
            }
            bundle.putBoolean(CommonSelectDialog.SHOW_SAVE_ICON, this.showSaveIcon);
            bundle.putBoolean("is_light", this.isLight);
            commonSelectDialogNewInstance.setData(this.mList);
            commonSelectDialogNewInstance.setArguments(bundle);
            return commonSelectDialogNewInstance;
        }
    }
}
