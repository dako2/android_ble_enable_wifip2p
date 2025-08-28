package com.glasssutdio.wear.home.bean;

import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UserModel.kt */
@JsonClass(generateAdapter = true)
@Metadata(m606d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BK\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u000bHÆ\u0003JO\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020\u000bHÖ\u0001J\t\u0010(\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u000e¨\u0006)"}, m607d2 = {"Lcom/glasssutdio/wear/home/bean/UserModel;", "", "registerDate", "", "registerTime", "", "token", "uid", "birthday", "nickname", "gender", "", "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getBirthday", "()Ljava/lang/String;", "setBirthday", "(Ljava/lang/String;)V", "getGender", "()I", "setGender", "(I)V", "getNickname", "setNickname", "getRegisterDate", "getRegisterTime", "()J", "getToken", "getUid", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class UserModel {
    private String birthday;
    private int gender;
    private String nickname;
    private final String registerDate;
    private final long registerTime;
    private final String token;
    private final String uid;

    public UserModel() {
        this(null, 0L, null, null, null, null, 0, 127, null);
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
    public final String getBirthday() {
        return this.birthday;
    }

    /* renamed from: component6, reason: from getter */
    public final String getNickname() {
        return this.nickname;
    }

    /* renamed from: component7, reason: from getter */
    public final int getGender() {
        return this.gender;
    }

    public final UserModel copy(String registerDate, long registerTime, String token, String uid, String birthday, String nickname, int gender) {
        Intrinsics.checkNotNullParameter(registerDate, "registerDate");
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(uid, "uid");
        Intrinsics.checkNotNullParameter(birthday, "birthday");
        Intrinsics.checkNotNullParameter(nickname, "nickname");
        return new UserModel(registerDate, registerTime, token, uid, birthday, nickname, gender);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserModel)) {
            return false;
        }
        UserModel userModel = (UserModel) other;
        return Intrinsics.areEqual(this.registerDate, userModel.registerDate) && this.registerTime == userModel.registerTime && Intrinsics.areEqual(this.token, userModel.token) && Intrinsics.areEqual(this.uid, userModel.uid) && Intrinsics.areEqual(this.birthday, userModel.birthday) && Intrinsics.areEqual(this.nickname, userModel.nickname) && this.gender == userModel.gender;
    }

    public int hashCode() {
        return (((((((((((this.registerDate.hashCode() * 31) + Long.hashCode(this.registerTime)) * 31) + this.token.hashCode()) * 31) + this.uid.hashCode()) * 31) + this.birthday.hashCode()) * 31) + this.nickname.hashCode()) * 31) + Integer.hashCode(this.gender);
    }

    public String toString() {
        return "UserModel(registerDate=" + this.registerDate + ", registerTime=" + this.registerTime + ", token=" + this.token + ", uid=" + this.uid + ", birthday=" + this.birthday + ", nickname=" + this.nickname + ", gender=" + this.gender + ')';
    }

    public UserModel(String registerDate, long j, String token, String uid, String birthday, String nickname, int i) {
        Intrinsics.checkNotNullParameter(registerDate, "registerDate");
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(uid, "uid");
        Intrinsics.checkNotNullParameter(birthday, "birthday");
        Intrinsics.checkNotNullParameter(nickname, "nickname");
        this.registerDate = registerDate;
        this.registerTime = j;
        this.token = token;
        this.uid = uid;
        this.birthday = birthday;
        this.nickname = nickname;
        this.gender = i;
    }

    public /* synthetic */ UserModel(String str, long j, String str2, String str3, String str4, String str5, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? 0L : j, (i2 & 4) != 0 ? "" : str2, (i2 & 8) != 0 ? "" : str3, (i2 & 16) != 0 ? "" : str4, (i2 & 32) == 0 ? str5 : "", (i2 & 64) != 0 ? 0 : i);
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

    public final String getBirthday() {
        return this.birthday;
    }

    public final void setBirthday(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.birthday = str;
    }

    public final String getNickname() {
        return this.nickname;
    }

    public final void setNickname(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.nickname = str;
    }

    public final int getGender() {
        return this.gender;
    }

    public final void setGender(int i) {
        this.gender = i;
    }
}
