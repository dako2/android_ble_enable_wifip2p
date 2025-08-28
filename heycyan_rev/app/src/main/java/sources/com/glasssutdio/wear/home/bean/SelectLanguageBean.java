package com.glasssutdio.wear.home.bean;

import com.glasssutdio.wear.all.bean.QLanguageType;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelectLanguageBean.kt */
@JsonClass(generateAdapter = true)
@Metadata(m606d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u0012"}, m607d2 = {"Lcom/glasssutdio/wear/home/bean/SelectLanguageBean;", "", "listenerTop", "Lcom/glasssutdio/wear/all/bean/QLanguageType;", "listenerBottom", "oneTop", "oneBottom", "(Lcom/glasssutdio/wear/all/bean/QLanguageType;Lcom/glasssutdio/wear/all/bean/QLanguageType;Lcom/glasssutdio/wear/all/bean/QLanguageType;Lcom/glasssutdio/wear/all/bean/QLanguageType;)V", "getListenerBottom", "()Lcom/glasssutdio/wear/all/bean/QLanguageType;", "setListenerBottom", "(Lcom/glasssutdio/wear/all/bean/QLanguageType;)V", "getListenerTop", "setListenerTop", "getOneBottom", "setOneBottom", "getOneTop", "setOneTop", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class SelectLanguageBean {
    private QLanguageType listenerBottom;
    private QLanguageType listenerTop;
    private QLanguageType oneBottom;
    private QLanguageType oneTop;

    public SelectLanguageBean() {
        this(null, null, null, null, 15, null);
    }

    public SelectLanguageBean(QLanguageType listenerTop, QLanguageType listenerBottom, QLanguageType oneTop, QLanguageType oneBottom) {
        Intrinsics.checkNotNullParameter(listenerTop, "listenerTop");
        Intrinsics.checkNotNullParameter(listenerBottom, "listenerBottom");
        Intrinsics.checkNotNullParameter(oneTop, "oneTop");
        Intrinsics.checkNotNullParameter(oneBottom, "oneBottom");
        this.listenerTop = listenerTop;
        this.listenerBottom = listenerBottom;
        this.oneTop = oneTop;
        this.oneBottom = oneBottom;
    }

    public /* synthetic */ SelectLanguageBean(QLanguageType qLanguageType, QLanguageType qLanguageType2, QLanguageType qLanguageType3, QLanguageType qLanguageType4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? QLanguageType.English : qLanguageType, (i & 2) != 0 ? QLanguageType.Chinese : qLanguageType2, (i & 4) != 0 ? QLanguageType.Chinese : qLanguageType3, (i & 8) != 0 ? QLanguageType.English : qLanguageType4);
    }

    public final QLanguageType getListenerTop() {
        return this.listenerTop;
    }

    public final void setListenerTop(QLanguageType qLanguageType) {
        Intrinsics.checkNotNullParameter(qLanguageType, "<set-?>");
        this.listenerTop = qLanguageType;
    }

    public final QLanguageType getListenerBottom() {
        return this.listenerBottom;
    }

    public final void setListenerBottom(QLanguageType qLanguageType) {
        Intrinsics.checkNotNullParameter(qLanguageType, "<set-?>");
        this.listenerBottom = qLanguageType;
    }

    public final QLanguageType getOneTop() {
        return this.oneTop;
    }

    public final void setOneTop(QLanguageType qLanguageType) {
        Intrinsics.checkNotNullParameter(qLanguageType, "<set-?>");
        this.oneTop = qLanguageType;
    }

    public final QLanguageType getOneBottom() {
        return this.oneBottom;
    }

    public final void setOneBottom(QLanguageType qLanguageType) {
        Intrinsics.checkNotNullParameter(qLanguageType, "<set-?>");
        this.oneBottom = qLanguageType;
    }
}
