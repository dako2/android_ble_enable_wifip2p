package com.glasssutdio.wear.all.bean.Req;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdateUserReq.kt */
@Metadata(m606d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J<\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\n¨\u0006\u001b"}, m607d2 = {"Lcom/glasssutdio/wear/all/bean/Req/UpdateUserReq;", "", "birthday", "", "nickname", "sex", "", "uid", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "getBirthday", "()Ljava/lang/String;", "getNickname", "getSex", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getUid", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lcom/glasssutdio/wear/all/bean/Req/UpdateUserReq;", "equals", "", "other", "hashCode", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class UpdateUserReq {
    private final String birthday;
    private final String nickname;
    private final Integer sex;
    private final String uid;

    public static /* synthetic */ UpdateUserReq copy$default(UpdateUserReq updateUserReq, String str, String str2, Integer num, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = updateUserReq.birthday;
        }
        if ((i & 2) != 0) {
            str2 = updateUserReq.nickname;
        }
        if ((i & 4) != 0) {
            num = updateUserReq.sex;
        }
        if ((i & 8) != 0) {
            str3 = updateUserReq.uid;
        }
        return updateUserReq.copy(str, str2, num, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getBirthday() {
        return this.birthday;
    }

    /* renamed from: component2, reason: from getter */
    public final String getNickname() {
        return this.nickname;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getSex() {
        return this.sex;
    }

    /* renamed from: component4, reason: from getter */
    public final String getUid() {
        return this.uid;
    }

    public final UpdateUserReq copy(String birthday, String nickname, Integer sex, String uid) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        return new UpdateUserReq(birthday, nickname, sex, uid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UpdateUserReq)) {
            return false;
        }
        UpdateUserReq updateUserReq = (UpdateUserReq) other;
        return Intrinsics.areEqual(this.birthday, updateUserReq.birthday) && Intrinsics.areEqual(this.nickname, updateUserReq.nickname) && Intrinsics.areEqual(this.sex, updateUserReq.sex) && Intrinsics.areEqual(this.uid, updateUserReq.uid);
    }

    public int hashCode() {
        String str = this.birthday;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.nickname;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.sex;
        return ((iHashCode2 + (num != null ? num.hashCode() : 0)) * 31) + this.uid.hashCode();
    }

    public String toString() {
        return "UpdateUserReq(birthday=" + this.birthday + ", nickname=" + this.nickname + ", sex=" + this.sex + ", uid=" + this.uid + ')';
    }

    public UpdateUserReq(String str, String str2, Integer num, String uid) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        this.birthday = str;
        this.nickname = str2;
        this.sex = num;
        this.uid = uid;
    }

    public final String getBirthday() {
        return this.birthday;
    }

    public final String getNickname() {
        return this.nickname;
    }

    public /* synthetic */ UpdateUserReq(String str, String str2, Integer num, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? 1 : num, str3);
    }

    public final Integer getSex() {
        return this.sex;
    }

    public final String getUid() {
        return this.uid;
    }
}
