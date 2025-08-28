package com.glasssutdio.wear.home.adapter;

import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.home.bean.QuestionModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FeedbackTypeAdapter.kt */
@Metadata(m606d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0002H\u0014¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/home/adapter/FeedbackTypeAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/glasssutdio/wear/home/bean/QuestionModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "()V", "convert", "", "holder", "item", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class FeedbackTypeAdapter extends BaseQuickAdapter<QuestionModel, BaseViewHolder> {
    public FeedbackTypeAdapter() {
        super(C0775R.layout.item_feedback_type, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, QuestionModel item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ((TextView) holder.getView(C0775R.id.tv_title)).setText(item.getTitle());
    }
}
