package com.glasssutdio.wear.all.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.bean.CommonSelectModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CommonSelectAdapter.kt */
@Metadata(m606d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0002H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m607d2 = {"Lcom/glasssutdio/wear/all/adapter/CommonSelectAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/glasssutdio/wear/all/bean/CommonSelectModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "isLight", "", "(Z)V", "convert", "", "holder", "item", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class CommonSelectAdapter extends BaseQuickAdapter<CommonSelectModel, BaseViewHolder> {
    private final boolean isLight;

    public CommonSelectAdapter() {
        this(false, 1, null);
    }

    public /* synthetic */ CommonSelectAdapter(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public CommonSelectAdapter(boolean z) {
        super(C0775R.layout.item_common_select_view, null, 2, null);
        this.isLight = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, CommonSelectModel item) {
        int color;
        int color2;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        TextView textView = (TextView) holder.getView(C0775R.id.tv_name);
        textView.setText(item.getName());
        if (this.isLight) {
            if (item.getChecked()) {
                color = ContextCompat.getColor(getContext(), C0775R.color.g_connect_text_1);
            } else {
                color = ContextCompat.getColor(getContext(), C0775R.color.g_black);
            }
        } else if (item.getChecked()) {
            color = ContextCompat.getColor(getContext(), C0775R.color.g_connect_text_1);
        } else {
            color = ContextCompat.getColor(getContext(), C0775R.color.g_white);
        }
        textView.setTextColor(color);
        int i = C0775R.id.tv_desc;
        String desc = item.getDesc();
        holder.setGone(i, desc == null || desc.length() == 0);
        holder.setText(C0775R.id.tv_desc, item.getDesc());
        ViewKt.goneOrVisible((ImageView) holder.getView(C0775R.id.iv_checked), item.getChecked());
        ViewKt.goneOrVisible(holder.getView(C0775R.id.view_line), holder.getLayoutPosition() != getData().size() - 1);
        View view = holder.getView(C0775R.id.view_line);
        if (this.isLight) {
            color2 = ContextCompat.getColor(getContext(), C0775R.color.color_EBEBEB);
        } else {
            color2 = ContextCompat.getColor(getContext(), C0775R.color.color_3D3D3D);
        }
        view.setBackgroundColor(color2);
    }
}
