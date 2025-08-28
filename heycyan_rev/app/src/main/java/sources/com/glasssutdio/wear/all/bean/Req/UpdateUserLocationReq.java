package com.glasssutdio.wear.all.bean.Req;

import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdateUserLocationReq.kt */
@JsonClass(generateAdapter = true)
@Metadata(m606d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001a"}, m607d2 = {"Lcom/glasssutdio/wear/all/bean/Req/UpdateUserLocationReq;", "", "city", "", "uid", "latitude", "", "longitude", "(Ljava/lang/String;Ljava/lang/String;DD)V", "getCity", "()Ljava/lang/String;", "getLatitude", "()D", "getLongitude", "getUid", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class UpdateUserLocationReq {
    private final String city;
    private final double latitude;
    private final double longitude;
    private final String uid;

    public static /* synthetic */ UpdateUserLocationReq copy$default(UpdateUserLocationReq updateUserLocationReq, String str, String str2, double d, double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = updateUserLocationReq.city;
        }
        if ((i & 2) != 0) {
            str2 = updateUserLocationReq.uid;
        }
        String str3 = str2;
        if ((i & 4) != 0) {
            d = updateUserLocationReq.latitude;
        }
        double d3 = d;
        if ((i & 8) != 0) {
            d2 = updateUserLocationReq.longitude;
        }
        return updateUserLocationReq.copy(str, str3, d3, d2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getCity() {
        return this.city;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUid() {
        return this.uid;
    }

    /* renamed from: component3, reason: from getter */
    public final double getLatitude() {
        return this.latitude;
    }

    /* renamed from: component4, reason: from getter */
    public final double getLongitude() {
        return this.longitude;
    }

    public final UpdateUserLocationReq copy(String city, String uid, double latitude, double longitude) {
        Intrinsics.checkNotNullParameter(city, "city");
        Intrinsics.checkNotNullParameter(uid, "uid");
        return new UpdateUserLocationReq(city, uid, latitude, longitude);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UpdateUserLocationReq)) {
            return false;
        }
        UpdateUserLocationReq updateUserLocationReq = (UpdateUserLocationReq) other;
        return Intrinsics.areEqual(this.city, updateUserLocationReq.city) && Intrinsics.areEqual(this.uid, updateUserLocationReq.uid) && Double.compare(this.latitude, updateUserLocationReq.latitude) == 0 && Double.compare(this.longitude, updateUserLocationReq.longitude) == 0;
    }

    public int hashCode() {
        return (((((this.city.hashCode() * 31) + this.uid.hashCode()) * 31) + Double.hashCode(this.latitude)) * 31) + Double.hashCode(this.longitude);
    }

    public String toString() {
        return "UpdateUserLocationReq(city=" + this.city + ", uid=" + this.uid + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ')';
    }

    public UpdateUserLocationReq(String city, String uid, double d, double d2) {
        Intrinsics.checkNotNullParameter(city, "city");
        Intrinsics.checkNotNullParameter(uid, "uid");
        this.city = city;
        this.uid = uid;
        this.latitude = d;
        this.longitude = d2;
    }

    public final String getCity() {
        return this.city;
    }

    public final String getUid() {
        return this.uid;
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final double getLongitude() {
        return this.longitude;
    }
}
