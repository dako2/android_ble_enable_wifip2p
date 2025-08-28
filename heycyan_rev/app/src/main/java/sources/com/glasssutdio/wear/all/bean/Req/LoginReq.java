package com.glasssutdio.wear.all.bean.Req;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoginReq.kt */
@Metadata(m606d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J3\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u0019"}, m607d2 = {"Lcom/glasssutdio/wear/all/bean/Req/LoginReq;", "", "account", "", "password", "type", "", "verificationCode", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getAccount", "()Ljava/lang/String;", "getPassword", "getType", "()I", "getVerificationCode", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class LoginReq {
    private final String account;
    private final String password;
    private final int type;
    private final String verificationCode;

    public static /* synthetic */ LoginReq copy$default(LoginReq loginReq, String str, String str2, int i, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = loginReq.account;
        }
        if ((i2 & 2) != 0) {
            str2 = loginReq.password;
        }
        if ((i2 & 4) != 0) {
            i = loginReq.type;
        }
        if ((i2 & 8) != 0) {
            str3 = loginReq.verificationCode;
        }
        return loginReq.copy(str, str2, i, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAccount() {
        return this.account;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPassword() {
        return this.password;
    }

    /* renamed from: component3, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* renamed from: component4, reason: from getter */
    public final String getVerificationCode() {
        return this.verificationCode;
    }

    public final LoginReq copy(String account, String password, int type, String verificationCode) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(password, "password");
        return new LoginReq(account, password, type, verificationCode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LoginReq)) {
            return false;
        }
        LoginReq loginReq = (LoginReq) other;
        return Intrinsics.areEqual(this.account, loginReq.account) && Intrinsics.areEqual(this.password, loginReq.password) && this.type == loginReq.type && Intrinsics.areEqual(this.verificationCode, loginReq.verificationCode);
    }

    public int hashCode() {
        int iHashCode = ((((this.account.hashCode() * 31) + this.password.hashCode()) * 31) + Integer.hashCode(this.type)) * 31;
        String str = this.verificationCode;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "LoginReq(account=" + this.account + ", password=" + this.password + ", type=" + this.type + ", verificationCode=" + this.verificationCode + ')';
    }

    public LoginReq(String account, String password, int i, String str) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(password, "password");
        this.account = account;
        this.password = password;
        this.type = i;
        this.verificationCode = str;
    }

    public /* synthetic */ LoginReq(String str, String str2, int i, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? null : str3);
    }

    public final String getAccount() {
        return this.account;
    }

    public final String getPassword() {
        return this.password;
    }

    public final int getType() {
        return this.type;
    }

    public final String getVerificationCode() {
        return this.verificationCode;
    }
}
