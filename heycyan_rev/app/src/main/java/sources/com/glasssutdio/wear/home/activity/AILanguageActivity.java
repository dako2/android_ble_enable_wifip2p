package com.glasssutdio.wear.home.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.adapter.SelectLanguageAdapter;
import com.glasssutdio.wear.all.bean.QLanguageType;
import com.glasssutdio.wear.all.bean.SelectLanguageModel;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.bus.UpdateAiLanguageEvent;
import com.glasssutdio.wear.databinding.ActivityAiLanguageBinding;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;

/* compiled from: AILanguageActivity.kt */
@Metadata(m606d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002J\b\u0010\b\u001a\u00020\tH\u0002J\u0012\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\r"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/AILanguageActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityAiLanguageBinding;", "getLanguageData", "", "Lcom/glasssutdio/wear/all/bean/SelectLanguageModel;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AILanguageActivity extends BaseSettingActivity {
    private ActivityAiLanguageBinding binding;

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityAiLanguageBinding activityAiLanguageBindingInflate = ActivityAiLanguageBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityAiLanguageBindingInflate, "inflate(...)");
        this.binding = activityAiLanguageBindingInflate;
        if (activityAiLanguageBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAiLanguageBindingInflate = null;
        }
        setContentView(activityAiLanguageBindingInflate.getRoot());
        initView();
    }

    private final void initView() {
        final ActivityAiLanguageBinding activityAiLanguageBinding = this.binding;
        if (activityAiLanguageBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAiLanguageBinding = null;
        }
        activityAiLanguageBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.AILanguageActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AILanguageActivity.initView$lambda$9$lambda$0(this.f$0, view);
            }
        });
        activityAiLanguageBinding.title.tvTitle.setText(getString(C0775R.string.g_fire_47));
        ViewKt.goneOrVisible(activityAiLanguageBinding.ivChecked, UserConfig.INSTANCE.getInstance().getAiIsSystemLanguage());
        final SelectLanguageAdapter selectLanguageAdapter = new SelectLanguageAdapter();
        RecyclerView recyclerView = activityAiLanguageBinding.rcyLanguage;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(selectLanguageAdapter);
        List<SelectLanguageModel> languageData = getLanguageData();
        if (!UserConfig.INSTANCE.getInstance().getAiIsSystemLanguage()) {
            int i = 0;
            for (Object obj : languageData) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                SelectLanguageModel selectLanguageModel = (SelectLanguageModel) obj;
                selectLanguageModel.setChecked(Intrinsics.areEqual(selectLanguageModel.getLanguage().getCode(), UserConfig.INSTANCE.getInstance().getAiLanguageCode()));
                i = i2;
            }
        }
        selectLanguageAdapter.setList(languageData);
        selectLanguageAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.glasssutdio.wear.home.activity.AILanguageActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i3) {
                AILanguageActivity.initView$lambda$9$lambda$4(selectLanguageAdapter, activityAiLanguageBinding, this, baseQuickAdapter, view, i3);
            }
        });
        activityAiLanguageBinding.clsSystem.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.AILanguageActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AILanguageActivity.initView$lambda$9$lambda$6(activityAiLanguageBinding, selectLanguageAdapter, this, view);
            }
        });
        EditText etSearch = activityAiLanguageBinding.etSearch;
        Intrinsics.checkNotNullExpressionValue(etSearch, "etSearch");
        etSearch.addTextChangedListener(new TextWatcher() { // from class: com.glasssutdio.wear.home.activity.AILanguageActivity$initView$lambda$9$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String string = activityAiLanguageBinding.etSearch.getText().toString();
                if (string.length() <= 0) {
                    selectLanguageAdapter.setList(this.getLanguageData());
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (SelectLanguageModel selectLanguageModel2 : this.getLanguageData()) {
                    if (StringsKt.contains((CharSequence) selectLanguageModel2.getLanguage().getLanguageName(), (CharSequence) string, true)) {
                        arrayList.add(selectLanguageModel2);
                    }
                }
                selectLanguageAdapter.setList(arrayList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$9$lambda$0(AILanguageActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$9$lambda$4(SelectLanguageAdapter mAdapter, ActivityAiLanguageBinding this_run, AILanguageActivity this$0, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(mAdapter, "$mAdapter");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        QLanguageType language = mAdapter.getData().get(i).getLanguage();
        UserConfig.INSTANCE.getInstance().setAiIsSystemLanguage(false);
        UserConfig.INSTANCE.getInstance().setAiLanguageCode(language.getCode());
        ViewKt.gone(this_run.ivChecked);
        int i2 = 0;
        for (Object obj : mAdapter.getData()) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ((SelectLanguageModel) obj).setChecked(i == i2);
            i2 = i3;
        }
        EventBus.getDefault().post(new UpdateAiLanguageEvent());
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$9$lambda$6(ActivityAiLanguageBinding this_run, SelectLanguageAdapter mAdapter, AILanguageActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(mAdapter, "$mAdapter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UserConfig.INSTANCE.getInstance().setAiIsSystemLanguage(true);
        ViewKt.visible(this_run.ivChecked);
        Iterator<T> it = mAdapter.getData().iterator();
        while (it.hasNext()) {
            ((SelectLanguageModel) it.next()).setChecked(false);
        }
        UserConfig.INSTANCE.getInstance().setAiLanguageCode("");
        EventBus.getDefault().post(new UpdateAiLanguageEvent());
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<SelectLanguageModel> getLanguageData() {
        ArrayList arrayList = new ArrayList();
        Iterator<QLanguageType> it = QLanguageType.getEntries().iterator();
        while (it.hasNext()) {
            arrayList.add(new SelectLanguageModel(it.next(), false, 2, null));
        }
        return arrayList;
    }
}
