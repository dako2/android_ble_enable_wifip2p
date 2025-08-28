package com.glasssutdio.wear.depository.bean;

import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AiJsonBean.kt */
@JsonClass(generateAdapter = true)
@Metadata(m606d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, m607d2 = {"Lcom/glasssutdio/wear/depository/bean/AiJsonBean;", "", "type", "", "result", "", "(ILjava/lang/String;)V", "getResult", "()Ljava/lang/String;", "getType", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class AiJsonBean {
    private final String result;
    private final int type;

    public static /* synthetic */ AiJsonBean copy$default(AiJsonBean aiJsonBean, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = aiJsonBean.type;
        }
        if ((i2 & 2) != 0) {
            str = aiJsonBean.result;
        }
        return aiJsonBean.copy(i, str);
    }

    /* renamed from: component1, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* renamed from: component2, reason: from getter */
    public final String getResult() {
        return this.result;
    }

    public final AiJsonBean copy(int type, String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        return new AiJsonBean(type, result);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AiJsonBean)) {
            return false;
        }
        AiJsonBean aiJsonBean = (AiJsonBean) other;
        return this.type == aiJsonBean.type && Intrinsics.areEqual(this.result, aiJsonBean.result);
    }

    public int hashCode() {
        return (Integer.hashCode(this.type) * 31) + this.result.hashCode();
    }

    public String toString() {
        return "AiJsonBean(type=" + this.type + ", result=" + this.result + ')';
    }

    public AiJsonBean(int i, String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        this.type = i;
        this.result = result;
    }

    public final int getType() {
        return this.type;
    }

    public final String getResult() {
        return this.result;
    }
}
