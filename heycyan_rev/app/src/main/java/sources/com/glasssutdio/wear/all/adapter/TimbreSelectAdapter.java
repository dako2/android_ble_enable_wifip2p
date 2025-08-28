package com.glasssutdio.wear.all.adapter;

import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.TextViewExtKt;
import com.glasssutdio.wear.all.bean.TimbreModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TimbreSelectAdapter.kt */
@Metadata(m606d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0002H\u0014¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/all/adapter/TimbreSelectAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/glasssutdio/wear/all/bean/TimbreModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "()V", "convert", "", "holder", "item", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class TimbreSelectAdapter extends BaseQuickAdapter<TimbreModel, BaseViewHolder> {
    public TimbreSelectAdapter() {
        super(C0775R.layout.item_timbre_view, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, TimbreModel item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        TextView textView = (TextView) holder.getView(C0775R.id.tv_timbre);
        textView.setText(item.getTitle());
        TextViewExtKt.setupMarquee(textView);
        holder.getView(C0775R.id.cls_root).setBackgroundResource(item.getItemBgRes());
        ((ImageView) holder.getView(C0775R.id.iv_people)).setImageResource(item.getIcon());
        ((ImageView) holder.getView(C0775R.id.iv_checked)).setImageResource(item.getChecked() ? C0775R.mipmap.ic_timbre_checked : C0775R.mipmap.ic_timbre_checked_un);
    }
}
