package com.glasssutdio.wear.depository.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AsrParamsBean.kt */
@Metadata(m606d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, m607d2 = {"Lcom/glasssutdio/wear/depository/bean/AsrParamsBean;", "", "appLanguage", "", "asrLanguage", "accent", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccent", "()Ljava/lang/String;", "getAppLanguage", "getAsrLanguage", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class AsrParamsBean {
    private final String accent;
    private final String appLanguage;
    private final String asrLanguage;

    public static /* synthetic */ AsrParamsBean copy$default(AsrParamsBean asrParamsBean, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = asrParamsBean.appLanguage;
        }
        if ((i & 2) != 0) {
            str2 = asrParamsBean.asrLanguage;
        }
        if ((i & 4) != 0) {
            str3 = asrParamsBean.accent;
        }
        return asrParamsBean.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAppLanguage() {
        return this.appLanguage;
    }

    /* renamed from: component2, reason: from getter */
    public final String getAsrLanguage() {
        return this.asrLanguage;
    }

    /* renamed from: component3, reason: from getter */
    public final String getAccent() {
        return this.accent;
    }

    public final AsrParamsBean copy(String appLanguage, String asrLanguage, String accent) {
        Intrinsics.checkNotNullParameter(appLanguage, "appLanguage");
        Intrinsics.checkNotNullParameter(asrLanguage, "asrLanguage");
        Intrinsics.checkNotNullParameter(accent, "accent");
        return new AsrParamsBean(appLanguage, asrLanguage, accent);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AsrParamsBean)) {
            return false;
        }
        AsrParamsBean asrParamsBean = (AsrParamsBean) other;
        return Intrinsics.areEqual(this.appLanguage, asrParamsBean.appLanguage) && Intrinsics.areEqual(this.asrLanguage, asrParamsBean.asrLanguage) && Intrinsics.areEqual(this.accent, asrParamsBean.accent);
    }

    public int hashCode() {
        return (((this.appLanguage.hashCode() * 31) + this.asrLanguage.hashCode()) * 31) + this.accent.hashCode();
    }

    public String toString() {
        return "AsrParamsBean(appLanguage=" + this.appLanguage + ", asrLanguage=" + this.asrLanguage + ", accent=" + this.accent + ')';
    }

    public AsrParamsBean(String appLanguage, String asrLanguage, String accent) {
        Intrinsics.checkNotNullParameter(appLanguage, "appLanguage");
        Intrinsics.checkNotNullParameter(asrLanguage, "asrLanguage");
        Intrinsics.checkNotNullParameter(accent, "accent");
        this.appLanguage = appLanguage;
        this.asrLanguage = asrLanguage;
        this.accent = accent;
    }

    public final String getAppLanguage() {
        return this.appLanguage;
    }

    public final String getAsrLanguage() {
        return this.asrLanguage;
    }

    public final String getAccent() {
        return this.accent;
    }
}
