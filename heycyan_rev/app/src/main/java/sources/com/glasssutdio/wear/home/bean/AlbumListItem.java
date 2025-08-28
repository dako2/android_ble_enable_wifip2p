package com.glasssutdio.wear.home.bean;

import com.glasssutdio.wear.database.entity.GlassAlbumEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AlbumListItem.kt */
@Metadata(m606d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, m607d2 = {"Lcom/glasssutdio/wear/home/bean/AlbumListItem;", "", "title", "", "list", "", "Lcom/glasssutdio/wear/database/entity/GlassAlbumEntity;", "(Ljava/lang/String;Ljava/util/List;)V", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AlbumListItem {
    private List<GlassAlbumEntity> list;
    private String title;

    public AlbumListItem(String title, List<GlassAlbumEntity> list) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(list, "list");
        this.title = title;
        this.list = list;
    }

    public final List<GlassAlbumEntity> getList() {
        return this.list;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setList(List<GlassAlbumEntity> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.list = list;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }
}
