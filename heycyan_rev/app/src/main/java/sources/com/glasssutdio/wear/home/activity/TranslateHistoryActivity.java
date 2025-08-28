package com.glasssutdio.wear.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.adapter.TranslateHistoryAdapter;
import com.glasssutdio.wear.all.dialog.DeleteOrNotDialog;
import com.glasssutdio.wear.all.dialog.EditCenterDialog;
import com.glasssutdio.wear.database.entity.TranslateEntity;
import com.glasssutdio.wear.databinding.ActivityTranslateHistoryBinding;
import com.glasssutdio.wear.home.viewmodel.AiTranslateVM;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: TranslateHistoryActivity.kt */
@Metadata(m606d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u001a\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/TranslateHistoryActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityTranslateHistoryBinding;", "mAdapter", "Lcom/glasssutdio/wear/all/adapter/TranslateHistoryAdapter;", "mViewModel", "Lcom/glasssutdio/wear/home/viewmodel/AiTranslateVM;", "getMViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/AiTranslateVM;", "mViewModel$delegate", "Lkotlin/Lazy;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "showDeleteConfirm", "position", "", "model1", "Lcom/glasssutdio/wear/database/entity/TranslateEntity;", "showEditNameDialog", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class TranslateHistoryActivity extends BaseSettingActivity {
    private ActivityTranslateHistoryBinding binding;
    private TranslateHistoryAdapter mAdapter;

    /* renamed from: mViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mViewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public TranslateHistoryActivity() {
        final TranslateHistoryActivity translateHistoryActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewModel = LazyKt.lazy(new Function0<AiTranslateVM>() { // from class: com.glasssutdio.wear.home.activity.TranslateHistoryActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.AiTranslateVM] */
            @Override // kotlin.jvm.functions.Function0
            public final AiTranslateVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(translateHistoryActivity, Reflection.getOrCreateKotlinClass(AiTranslateVM.class), qualifier, objArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AiTranslateVM getMViewModel() {
        return (AiTranslateVM) this.mViewModel.getValue();
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityTranslateHistoryBinding activityTranslateHistoryBindingInflate = ActivityTranslateHistoryBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityTranslateHistoryBindingInflate, "inflate(...)");
        this.binding = activityTranslateHistoryBindingInflate;
        if (activityTranslateHistoryBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateHistoryBindingInflate = null;
        }
        setContentView(activityTranslateHistoryBindingInflate.getRoot());
        initView();
    }

    private final void showDeleteConfirm(final int position, final TranslateEntity model1) {
        DeleteOrNotDialog.Builder builder = new DeleteOrNotDialog.Builder();
        String string = getString(C0775R.string.album_glass_15);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        DeleteOrNotDialog.Builder content = builder.setTitle(string).setContent("");
        String string2 = getString(C0775R.string.album_glass_9);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        DeleteOrNotDialog.Builder confirmMessage = content.setConfirmMessage(string2);
        String string3 = getString(C0775R.string.h_glass_cancel);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        DeleteOrNotDialog deleteOrNotDialogBuild = confirmMessage.setCancelMessage(string3).build();
        deleteOrNotDialogBuild.show(getSupportFragmentManager(), "deleteHistory");
        deleteOrNotDialogBuild.setOnConfirmListener(new Function1<View, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateHistoryActivity.showDeleteConfirm.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
                AiTranslateVM mViewModel = TranslateHistoryActivity.this.getMViewModel();
                TranslateEntity translateEntity = model1;
                mViewModel.deleteByUidAndTimestamp(translateEntity != null ? translateEntity.getCreateTime() : 0L);
                TranslateHistoryAdapter translateHistoryAdapter = TranslateHistoryActivity.this.mAdapter;
                if (translateHistoryAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
                    translateHistoryAdapter = null;
                }
                translateHistoryAdapter.removeAt(position);
            }
        });
    }

    private final void initView() {
        final ActivityTranslateHistoryBinding activityTranslateHistoryBinding = this.binding;
        TranslateHistoryAdapter translateHistoryAdapter = null;
        if (activityTranslateHistoryBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTranslateHistoryBinding = null;
        }
        activityTranslateHistoryBinding.title.tvTitle.setText(getString(C0775R.string.g_translate_14));
        activityTranslateHistoryBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.TranslateHistoryActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TranslateHistoryActivity.initView$lambda$2$lambda$0(this.f$0, view);
            }
        });
        TranslateHistoryAdapter translateHistoryAdapter2 = new TranslateHistoryAdapter();
        this.mAdapter = translateHistoryAdapter2;
        translateHistoryAdapter2.addChildClickViewIds(C0775R.id.tv_translate_title);
        activityTranslateHistoryBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView = activityTranslateHistoryBinding.recyclerView;
        TranslateHistoryAdapter translateHistoryAdapter3 = this.mAdapter;
        if (translateHistoryAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            translateHistoryAdapter3 = null;
        }
        recyclerView.setAdapter(translateHistoryAdapter3);
        getMViewModel().getAllTranslateHistory();
        getMViewModel().getTranslateHistoryLD().observe(this, new TranslateHistoryActivity$sam$androidx_lifecycle_Observer$0(new Function1<List<TranslateEntity>, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateHistoryActivity$initView$1$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<TranslateEntity> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<TranslateEntity> list) {
                ViewKt.goneOrVisible(activityTranslateHistoryBinding.chatNoData, list.isEmpty());
                TranslateHistoryAdapter translateHistoryAdapter4 = this.mAdapter;
                if (translateHistoryAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
                    translateHistoryAdapter4 = null;
                }
                translateHistoryAdapter4.setList(list);
            }
        }));
        TranslateHistoryAdapter translateHistoryAdapter4 = this.mAdapter;
        if (translateHistoryAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            translateHistoryAdapter4 = null;
        }
        translateHistoryAdapter4.addChildClickViewIds(C0775R.id.cls_item);
        TranslateHistoryAdapter translateHistoryAdapter5 = this.mAdapter;
        if (translateHistoryAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            translateHistoryAdapter5 = null;
        }
        translateHistoryAdapter5.addChildClickViewIds(C0775R.id.iv_delete);
        TranslateHistoryAdapter translateHistoryAdapter6 = this.mAdapter;
        if (translateHistoryAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        } else {
            translateHistoryAdapter = translateHistoryAdapter6;
        }
        translateHistoryAdapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.glasssutdio.wear.home.activity.TranslateHistoryActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TranslateHistoryActivity.initView$lambda$2$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$2$lambda$0(TranslateHistoryActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$2$lambda$1(TranslateHistoryActivity this$0, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        TranslateHistoryAdapter translateHistoryAdapter = this$0.mAdapter;
        if (translateHistoryAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            translateHistoryAdapter = null;
        }
        TranslateEntity translateEntity = (TranslateEntity) CollectionsKt.getOrNull(translateHistoryAdapter.getData(), i);
        if (view.getId() == C0775R.id.cls_item) {
            TranslateHistoryActivity translateHistoryActivity = this$0;
            Pair pair = new Pair(TranslateHistoryDetailActivity.HISTORY_CREATE_TIME, Long.valueOf(translateEntity != null ? translateEntity.getCreateTime() : 0L));
            ArrayList<Pair> arrayList = new ArrayList();
            arrayList.add(pair);
            Intent intent = new Intent(translateHistoryActivity, (Class<?>) TranslateHistoryDetailActivity.class);
            for (Pair pair2 : arrayList) {
                if (pair2 != null) {
                    String str = (String) pair2.getFirst();
                    Object second = pair2.getSecond();
                    if (second instanceof Integer) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(...)");
                    } else if (second instanceof Byte) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(...)");
                    } else if (second instanceof Character) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(...)");
                    } else if (second instanceof Short) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(...)");
                    } else if (second instanceof Boolean) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(...)");
                    } else if (second instanceof Long) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(...)");
                    } else if (second instanceof Float) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(...)");
                    } else if (second instanceof Double) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(...)");
                    } else if (second instanceof String) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(...)");
                    } else if (second instanceof CharSequence) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(...)");
                    } else if (second instanceof Parcelable) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                    } else if (second instanceof Object[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof ArrayList) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof Serializable) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof boolean[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(...)");
                    } else if (second instanceof byte[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(...)");
                    } else if (second instanceof short[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(...)");
                    } else if (second instanceof char[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(...)");
                    } else if (second instanceof int[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(...)");
                    } else if (second instanceof long[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(...)");
                    } else if (second instanceof float[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(...)");
                    } else if (second instanceof double[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(...)");
                    } else if (second instanceof Bundle) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(...)");
                    } else if (second instanceof Intent) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                    } else {
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            translateHistoryActivity.startActivity(intent);
            return;
        }
        if (view.getId() == C0775R.id.tv_translate_title) {
            this$0.showEditNameDialog(i);
        } else {
            this$0.showDeleteConfirm(i, translateEntity);
        }
    }

    private final void showEditNameDialog(int position) {
        TranslateHistoryAdapter translateHistoryAdapter = this.mAdapter;
        if (translateHistoryAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            translateHistoryAdapter = null;
        }
        final TranslateEntity translateEntity = translateHistoryAdapter.getData().get(position);
        EditCenterDialog.Builder builder = new EditCenterDialog.Builder();
        String string = getString(C0775R.string.g_translate_18);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        EditCenterDialog.Builder maxLength = builder.setTitle(string).setMaxLength(30);
        String string2 = getString(C0775R.string.g_translate_18);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        final EditCenterDialog editCenterDialogBuild = maxLength.setHint(string2).setContent(translateEntity.getTranslateTitle()).isLight(false).build();
        editCenterDialogBuild.setOnConfirmListener(new Function2<View, String, Unit>() { // from class: com.glasssutdio.wear.home.activity.TranslateHistoryActivity.showEditNameDialog.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(View view, String str) {
                invoke2(view, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View view, String content) {
                Intrinsics.checkNotNullParameter(view, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(content, "content");
                editCenterDialogBuild.dismiss();
                if (content.length() > 0) {
                    this.getMViewModel().updateTranslateTitle(translateEntity.getCreateTime(), content);
                    translateEntity.setTranslateTitle(content);
                    TranslateHistoryAdapter translateHistoryAdapter2 = this.mAdapter;
                    if (translateHistoryAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
                        translateHistoryAdapter2 = null;
                    }
                    translateHistoryAdapter2.notifyDataSetChanged();
                }
            }
        });
        editCenterDialogBuild.show(getSupportFragmentManager(), "translate_title");
    }
}
