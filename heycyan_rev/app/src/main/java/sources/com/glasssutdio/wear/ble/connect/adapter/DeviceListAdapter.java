package com.glasssutdio.wear.ble.connect.adapter;

import android.content.Context;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.ble.connect.bean.SmartWatch;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: DeviceListAdapter.kt */
@Metadata(m606d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0002H\u0014¨\u0006\r"}, m607d2 = {"Lcom/glasssutdio/wear/ble/connect/adapter/DeviceListAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/glasssutdio/wear/ble/connect/bean/SmartWatch;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "context", "Landroid/content/Context;", "data", "", "(Landroid/content/Context;Ljava/util/List;)V", "convert", "", "holder", "item", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class DeviceListAdapter extends BaseQuickAdapter<SmartWatch, BaseViewHolder> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceListAdapter(Context context, List<SmartWatch> data) {
        super(C0775R.layout.recycleview_item_device, data);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, SmartWatch item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        String deviceName = item.getDeviceName();
        if (StringsKt.startsWith$default(deviceName, "O_", false, 2, (Object) null)) {
            deviceName = deviceName.substring(2, deviceName.length());
            Intrinsics.checkNotNullExpressionValue(deviceName, "substring(...)");
        }
        holder.setText(C0775R.id.rcv_device_name, deviceName);
        holder.setText(C0775R.id.rcv_device_address, item.getDeviceAddress());
    }
}
