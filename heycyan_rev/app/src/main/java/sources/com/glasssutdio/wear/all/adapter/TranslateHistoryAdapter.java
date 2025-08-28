package com.glasssutdio.wear.all.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.utils.DateUtil;
import com.glasssutdio.wear.database.entity.TranslateEntity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TranslateHistoryAdapter.kt */
@Metadata(m606d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0002H\u0014¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/all/adapter/TranslateHistoryAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/glasssutdio/wear/database/entity/TranslateEntity;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "()V", "convert", "", "holder", "item", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class TranslateHistoryAdapter extends BaseQuickAdapter<TranslateEntity, BaseViewHolder> {
    public TranslateHistoryAdapter() {
        super(C0775R.layout.item_translate_history, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, TranslateEntity item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        holder.setText(C0775R.id.tv_content, item.getSrcContent());
        DateUtil dateUtil = new DateUtil();
        dateUtil.setUnixTimestamp(item.getCreateTime() / 1000);
        holder.setText(C0775R.id.tv_time, dateUtil.getYyyyMMdd_HHmmDate());
        if (item.getTranslateTitle().length() > 0) {
            holder.setText(C0775R.id.tv_translate_title, item.getTranslateTitle());
        } else {
            holder.setText(C0775R.id.tv_translate_title, GlobalKt.getString(C0775R.string.g_translate_101) + (holder.getLayoutPosition() + 1));
        }
    }
}
