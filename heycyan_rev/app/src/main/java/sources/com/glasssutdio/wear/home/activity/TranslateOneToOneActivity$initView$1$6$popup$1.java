package com.glasssutdio.wear.home.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.glasssutdio.wear.all.adapter.SelectLanguageAdapter;
import com.glasssutdio.wear.all.bean.QLanguageType;
import com.glasssutdio.wear.all.bean.SelectLanguageModel;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.MoshiUtils;
import com.glasssutdio.wear.all.utils.MoshiUtilsKt;
import com.glasssutdio.wear.all.utils.TypeToken;
import com.glasssutdio.wear.databinding.ActivityTranslateOneToOneBinding;
import com.glasssutdio.wear.databinding.PopupSelectLanguageBinding;
import com.glasssutdio.wear.home.bean.SelectLanguageBean;
import com.squareup.moshi.JsonAdapter;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

/* compiled from: TranslateOneToOneActivity.kt */
@Metadata(m606d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m607d2 = {"<anonymous>", "", "view", "Lcom/glasssutdio/wear/databinding/PopupSelectLanguageBinding;", "window", "Landroid/widget/PopupWindow;", "invoke"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
final class TranslateOneToOneActivity$initView$1$6$popup$1 extends Lambda implements Function2<PopupSelectLanguageBinding, PopupWindow, Unit> {
    final /* synthetic */ ActivityTranslateOneToOneBinding $this_run;
    final /* synthetic */ TranslateOneToOneActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TranslateOneToOneActivity$initView$1$6$popup$1(TranslateOneToOneActivity translateOneToOneActivity, ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding) {
        super(2);
        this.this$0 = translateOneToOneActivity;
        this.$this_run = activityTranslateOneToOneBinding;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(PopupSelectLanguageBinding popupSelectLanguageBinding, PopupWindow popupWindow) {
        invoke2(popupSelectLanguageBinding, popupWindow);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(final PopupSelectLanguageBinding view, final PopupWindow window) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(window, "window");
        view.clsContent.setLayoutParams(view.clsContent.getLayoutParams());
        final SelectLanguageAdapter selectLanguageAdapter = new SelectLanguageAdapter();
        RecyclerView recyclerView = view.rcyLanguage;
        TranslateOneToOneActivity translateOneToOneActivity = this.this$0;
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getRoot().getContext()));
        recyclerView.setAdapter(selectLanguageAdapter);
        List languageData = translateOneToOneActivity.getLanguageData();
        int i = 0;
        for (Object obj : languageData) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            SelectLanguageModel selectLanguageModel = (SelectLanguageModel) obj;
            selectLanguageModel.setChecked(translateOneToOneActivity.defaultLanguageBottom == selectLanguageModel.getLanguage());
            i = i2;
        }
        selectLanguageAdapter.setList(languageData);
        final ActivityTranslateOneToOneBinding activityTranslateOneToOneBinding = this.$this_run;
        final TranslateOneToOneActivity translateOneToOneActivity2 = this.this$0;
        selectLanguageAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$initView$1$6$popup$1$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view2, int i3) {
                TranslateOneToOneActivity$initView$1$6$popup$1.invoke$lambda$2(selectLanguageAdapter, activityTranslateOneToOneBinding, translateOneToOneActivity2, window, baseQuickAdapter, view2, i3);
            }
        });
        EditText etSearch = view.etSearch;
        Intrinsics.checkNotNullExpressionValue(etSearch, "etSearch");
        final TranslateOneToOneActivity translateOneToOneActivity3 = this.this$0;
        etSearch.addTextChangedListener(new TextWatcher() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$initView$1$6$popup$1$invoke$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String string = view.etSearch.getText().toString();
                if (string.length() <= 0) {
                    selectLanguageAdapter.setList(translateOneToOneActivity3.getLanguageData());
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (SelectLanguageModel selectLanguageModel2 : translateOneToOneActivity3.getLanguageData()) {
                    if (StringsKt.contains((CharSequence) selectLanguageModel2.getLanguage().getLanguageName(), (CharSequence) string, true)) {
                        arrayList.add(selectLanguageModel2);
                    }
                }
                selectLanguageAdapter.setList(arrayList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$2(SelectLanguageAdapter mAdapter, ActivityTranslateOneToOneBinding this_run, TranslateOneToOneActivity this$0, PopupWindow window, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(mAdapter, "$mAdapter");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(window, "$window");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        QLanguageType language = mAdapter.getData().get(i).getLanguage();
        this_run.tvLanguageBottom.setText(language.getLanguageName());
        this$0.defaultLanguageBottom = language;
        String languageJson = UserConfig.INSTANCE.getInstance().getLanguageJson();
        if (languageJson.length() <= 0) {
            SelectLanguageBean selectLanguageBean = new SelectLanguageBean(null, null, null, null, 15, null);
            selectLanguageBean.setOneBottom(language);
            UserConfig.INSTANCE.getInstance().setLanguageJson(MoshiUtilsKt.toJson(selectLanguageBean));
        } else {
            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<SelectLanguageBean>() { // from class: com.glasssutdio.wear.home.activity.TranslateOneToOneActivity$initView$1$6$popup$1$invoke$lambda$2$$inlined$fromJson$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "adapter(...)");
            SelectLanguageBean selectLanguageBean2 = (SelectLanguageBean) jsonAdapterAdapter.fromJson(languageJson);
            if (selectLanguageBean2 != null) {
                selectLanguageBean2.setOneBottom(language);
                UserConfig.INSTANCE.getInstance().setLanguageJson(MoshiUtilsKt.toJson(selectLanguageBean2));
            }
        }
        window.dismiss();
    }
}
