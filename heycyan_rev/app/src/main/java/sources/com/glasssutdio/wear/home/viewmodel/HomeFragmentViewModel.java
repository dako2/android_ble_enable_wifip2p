package com.glasssutdio.wear.home.viewmodel;

import androidx.lifecycle.ViewModel;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.home.bean.ShowDetailBean;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HomeFragmentViewModel.kt */
@Metadata(m606d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\u000bR \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\f"}, m607d2 = {"Lcom/glasssutdio/wear/home/viewmodel/HomeFragmentViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "homeImagesList", "", "Lcom/glasssutdio/wear/home/bean/ShowDetailBean;", "getHomeImagesList", "()Ljava/util/List;", "setHomeImagesList", "(Ljava/util/List;)V", "initData", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class HomeFragmentViewModel extends ViewModel {
    private List<ShowDetailBean> homeImagesList = new ArrayList();

    public final List<ShowDetailBean> getHomeImagesList() {
        return this.homeImagesList;
    }

    public final void setHomeImagesList(List<ShowDetailBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.homeImagesList = list;
    }

    public final void initData() {
        this.homeImagesList.clear();
        this.homeImagesList.add(new ShowDetailBean("", GlobalKt.getString(C0775R.string.h_glass_3), GlobalKt.getString(C0775R.string.home_glass_1), C0775R.mipmap.home_show_image_1));
        this.homeImagesList.add(new ShowDetailBean("", GlobalKt.getString(C0775R.string.h_glass_4), GlobalKt.getString(C0775R.string.home_glass_2), C0775R.mipmap.home_show_image_2));
        this.homeImagesList.add(new ShowDetailBean("", GlobalKt.getString(C0775R.string.h_glass_5), GlobalKt.getString(C0775R.string.home_glass_3), C0775R.mipmap.home_show_image_3));
    }
}
