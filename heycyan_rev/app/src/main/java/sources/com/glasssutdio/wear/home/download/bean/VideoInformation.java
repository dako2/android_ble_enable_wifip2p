package com.glasssutdio.wear.home.download.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoInformation.kt */
@Metadata(m606d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001d"}, m607d2 = {"Lcom/glasssutdio/wear/home/download/bean/VideoInformation;", "", "videoUrl", "", "hardwareVersion", "name", "preImageUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getHardwareVersion", "()Ljava/lang/String;", "setHardwareVersion", "(Ljava/lang/String;)V", "getName", "setName", "getPreImageUrl", "setPreImageUrl", "getVideoUrl", "setVideoUrl", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class VideoInformation {
    private String hardwareVersion;
    private String name;
    private String preImageUrl;
    private String videoUrl;

    public static /* synthetic */ VideoInformation copy$default(VideoInformation videoInformation, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = videoInformation.videoUrl;
        }
        if ((i & 2) != 0) {
            str2 = videoInformation.hardwareVersion;
        }
        if ((i & 4) != 0) {
            str3 = videoInformation.name;
        }
        if ((i & 8) != 0) {
            str4 = videoInformation.preImageUrl;
        }
        return videoInformation.copy(str, str2, str3, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getVideoUrl() {
        return this.videoUrl;
    }

    /* renamed from: component2, reason: from getter */
    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    /* renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component4, reason: from getter */
    public final String getPreImageUrl() {
        return this.preImageUrl;
    }

    public final VideoInformation copy(String videoUrl, String hardwareVersion, String name, String preImageUrl) {
        Intrinsics.checkNotNullParameter(videoUrl, "videoUrl");
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(preImageUrl, "preImageUrl");
        return new VideoInformation(videoUrl, hardwareVersion, name, preImageUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VideoInformation)) {
            return false;
        }
        VideoInformation videoInformation = (VideoInformation) other;
        return Intrinsics.areEqual(this.videoUrl, videoInformation.videoUrl) && Intrinsics.areEqual(this.hardwareVersion, videoInformation.hardwareVersion) && Intrinsics.areEqual(this.name, videoInformation.name) && Intrinsics.areEqual(this.preImageUrl, videoInformation.preImageUrl);
    }

    public int hashCode() {
        return (((((this.videoUrl.hashCode() * 31) + this.hardwareVersion.hashCode()) * 31) + this.name.hashCode()) * 31) + this.preImageUrl.hashCode();
    }

    public String toString() {
        return "VideoInformation(videoUrl=" + this.videoUrl + ", hardwareVersion=" + this.hardwareVersion + ", name=" + this.name + ", preImageUrl=" + this.preImageUrl + ')';
    }

    public VideoInformation(String videoUrl, String hardwareVersion, String name, String preImageUrl) {
        Intrinsics.checkNotNullParameter(videoUrl, "videoUrl");
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(preImageUrl, "preImageUrl");
        this.videoUrl = videoUrl;
        this.hardwareVersion = hardwareVersion;
        this.name = name;
        this.preImageUrl = preImageUrl;
    }

    public final String getVideoUrl() {
        return this.videoUrl;
    }

    public final void setVideoUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.videoUrl = str;
    }

    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public final void setHardwareVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.hardwareVersion = str;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final String getPreImageUrl() {
        return this.preImageUrl;
    }

    public final void setPreImageUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.preImageUrl = str;
    }
}
