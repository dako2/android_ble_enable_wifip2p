package com.glasssutdio.wear.all.adapter;

import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.bean.WheelSelectModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WheelSelectAdapter.kt */
@Metadata(m606d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0002H\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0007\"\u0004\b\b\u0010\u0006¨\u0006\r"}, m607d2 = {"Lcom/glasssutdio/wear/all/adapter/WheelSelectAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/glasssutdio/wear/all/bean/WheelSelectModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "isLight", "", "(Z)V", "()Z", "setLight", "convert", "", "holder", "item", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class WheelSelectAdapter extends BaseQuickAdapter<WheelSelectModel, BaseViewHolder> {
    private boolean isLight;

    public WheelSelectAdapter() {
        this(false, 1, null);
    }

    public /* synthetic */ WheelSelectAdapter(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    /* renamed from: isLight, reason: from getter */
    public final boolean getIsLight() {
        return this.isLight;
    }

    public final void setLight(boolean z) {
        this.isLight = z;
    }

    public WheelSelectAdapter(boolean z) {
        super(C0775R.layout.item_wheel_select, null, 2, null);
        this.isLight = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, WheelSelectModel item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        TextView textView = (TextView) holder.getView(C0775R.id.tv_name);
        textView.setText(item.getName());
        if (this.isLight) {
            if (item.getIsChecked()) {
                textView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), C0775R.color.g_white));
                return;
            } else {
                textView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), C0775R.color.color_141414));
                return;
            }
        }
        if (item.getIsChecked()) {
            textView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), C0775R.color.g_white));
        } else {
            textView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), C0775R.color.g_white));
        }
    }
}
