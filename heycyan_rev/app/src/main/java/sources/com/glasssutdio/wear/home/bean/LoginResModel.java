package com.glasssutdio.wear.home.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoginResModel.kt */
@Metadata(m606d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\tHÆ\u0003J;\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\t2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000f¨\u0006\u001f"}, m607d2 = {"Lcom/glasssutdio/wear/home/bean/LoginResModel;", "", "registerDate", "", "registerTime", "", "token", "uid", "isCompleteProfile", "", "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Z)V", "()Z", "setCompleteProfile", "(Z)V", "getRegisterDate", "()Ljava/lang/String;", "getRegisterTime", "()J", "getToken", "getUid", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class LoginResModel {
    private boolean isCompleteProfile;
    private final String registerDate;
    private final long registerTime;
    private final String token;
    private final String uid;

    public static /* synthetic */ LoginResModel copy$default(LoginResModel loginResModel, String str, long j, String str2, String str3, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = loginResModel.registerDate;
        }
        if ((i & 2) != 0) {
            j = loginResModel.registerTime;
        }
        long j2 = j;
        if ((i & 4) != 0) {
            str2 = loginResModel.token;
        }
        String str4 = str2;
        if ((i & 8) != 0) {
            str3 = loginResModel.uid;
        }
        String str5 = str3;
        if ((i & 16) != 0) {
            z = loginResModel.isCompleteProfile;
        }
        return loginResModel.copy(str, j2, str4, str5, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getRegisterDate() {
        return this.registerDate;
    }

    /* renamed from: component2, reason: from getter */
    public final long getRegisterTime() {
        return this.registerTime;
    }

    /* renamed from: component3, reason: from getter */
    public final String getToken() {
        return this.token;
    }

    /* renamed from: component4, reason: from getter */
    public final String getUid() {
        return this.uid;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getIsCompleteProfile() {
        return this.isCompleteProfile;
    }

    public final LoginResModel copy(String registerDate, long registerTime, String token, String uid, boolean isCompleteProfile) {
        Intrinsics.checkNotNullParameter(registerDate, "registerDate");
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(uid, "uid");
        return new LoginResModel(registerDate, registerTime, token, uid, isCompleteProfile);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LoginResModel)) {
            return false;
        }
        LoginResModel loginResModel = (LoginResModel) other;
        return Intrinsics.areEqual(this.registerDate, loginResModel.registerDate) && this.registerTime == loginResModel.registerTime && Intrinsics.areEqual(this.token, loginResModel.token) && Intrinsics.areEqual(this.uid, loginResModel.uid) && this.isCompleteProfile == loginResModel.isCompleteProfile;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((((this.registerDate.hashCode() * 31) + Long.hashCode(this.registerTime)) * 31) + this.token.hashCode()) * 31) + this.uid.hashCode()) * 31;
        boolean z = this.isCompleteProfile;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return iHashCode + i;
    }

    public String toString() {
        return "LoginResModel(registerDate=" + this.registerDate + ", registerTime=" + this.registerTime + ", token=" + this.token + ", uid=" + this.uid + ", isCompleteProfile=" + this.isCompleteProfile + ')';
    }

    public LoginResModel(String registerDate, long j, String token, String uid, boolean z) {
        Intrinsics.checkNotNullParameter(registerDate, "registerDate");
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(uid, "uid");
        this.registerDate = registerDate;
        this.registerTime = j;
        this.token = token;
        this.uid = uid;
        this.isCompleteProfile = z;
    }

    public /* synthetic */ LoginResModel(String str, long j, String str2, String str3, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j, str2, str3, (i & 16) != 0 ? false : z);
    }

    public final String getRegisterDate() {
        return this.registerDate;
    }

    public final long getRegisterTime() {
        return this.registerTime;
    }

    public final String getToken() {
        return this.token;
    }

    public final String getUid() {
        return this.uid;
    }

    public final boolean isCompleteProfile() {
        return this.isCompleteProfile;
    }

    public final void setCompleteProfile(boolean z) {
        this.isCompleteProfile = z;
    }
}
