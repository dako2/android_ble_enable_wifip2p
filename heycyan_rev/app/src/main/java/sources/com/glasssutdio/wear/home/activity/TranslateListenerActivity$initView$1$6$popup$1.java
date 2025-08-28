package com.glasssutdio.wear.home.activity;

import android.view.View;
import android.widget.PopupWindow;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.glasssutdio.wear.all.adapter.SelectLanguageAdapter;
import com.glasssutdio.wear.all.bean.QLanguageType;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.databinding.ActivityTranslateListenerBinding;
import com.glasssutdio.wear.databinding.PopupSelectLanguageBinding;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: TranslateListenerActivity.kt */
@Metadata(m606d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m607d2 = {"<anonymous>", "", "view", "Lcom/glasssutdio/wear/databinding/PopupSelectLanguageBinding;", "window", "Landroid/widget/PopupWindow;", "invoke"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
final class TranslateListenerActivity$initView$1$6$popup$1 extends Lambda implements Function2<PopupSelectLanguageBinding, PopupWindow, Unit> {
    final /* synthetic */ ActivityTranslateListenerBinding $this_run;
    final /* synthetic */ TranslateListenerActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TranslateListenerActivity$initView$1$6$popup$1(TranslateListenerActivity translateListenerActivity, ActivityTranslateListenerBinding activityTranslateListenerBinding) {
        super(2);
        this.this$0 = translateListenerActivity;
        this.$this_run = activityTranslateListenerBinding;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(PopupSelectLanguageBinding popupSelectLanguageBinding, PopupWindow popupWindow) {
        invoke2(popupSelectLanguageBinding, popupWindow);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(PopupSelectLanguageBinding view, final PopupWindow window) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(window, "window");
        final SelectLanguageAdapter selectLanguageAdapter = new SelectLanguageAdapter();
        RecyclerView recyclerView = view.rcyLanguage;
        TranslateListenerActivity translateListenerActivity = this.this$0;
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getRoot().getContext()));
        recyclerView.setAdapter(selectLanguageAdapter);
        selectLanguageAdapter.setList(translateListenerActivity.getLanguageData(true));
        final ActivityTranslateListenerBinding activityTranslateListenerBinding = this.$this_run;
        final TranslateListenerActivity translateListenerActivity2 = this.this$0;
        selectLanguageAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.glasssutdio.wear.home.activity.TranslateListenerActivity$initView$1$6$popup$1$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view2, int i) {
                TranslateListenerActivity$initView$1$6$popup$1.invoke$lambda$1(selectLanguageAdapter, activityTranslateListenerBinding, translateListenerActivity2, window, baseQuickAdapter, view2, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$1(SelectLanguageAdapter mAdapter, ActivityTranslateListenerBinding this_run, TranslateListenerActivity this$0, PopupWindow window, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(mAdapter, "$mAdapter");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(window, "$window");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        QLanguageType language = mAdapter.getData().get(i).getLanguage();
        this_run.tvLanguageTop.setText(language.getLanguageName());
        this$0.defaultLanguageTop = language;
        UserConfig.INSTANCE.getInstance().setTranslateFromDefault(language.getCode());
        if (this$0.translateIng) {
            this$0.lastCreateTime = 0L;
            ActivityTranslateListenerBinding activityTranslateListenerBinding = this$0.binding;
            ActivityTranslateListenerBinding activityTranslateListenerBinding2 = null;
            if (activityTranslateListenerBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTranslateListenerBinding = null;
            }
            activityTranslateListenerBinding.etTop.setText("");
            ActivityTranslateListenerBinding activityTranslateListenerBinding3 = this$0.binding;
            if (activityTranslateListenerBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTranslateListenerBinding3 = null;
            }
            activityTranslateListenerBinding3.etBottom.setText("");
            ActivityTranslateListenerBinding activityTranslateListenerBinding4 = this$0.binding;
            if (activityTranslateListenerBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityTranslateListenerBinding2 = activityTranslateListenerBinding4;
            }
            activityTranslateListenerBinding2.cclStart.performClick();
        }
        window.dismiss();
    }
}
