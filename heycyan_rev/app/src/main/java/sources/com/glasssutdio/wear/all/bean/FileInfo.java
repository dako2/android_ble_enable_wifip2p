package com.glasssutdio.wear.all.bean;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileInfo.kt */
@Metadata(m606d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J3\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, m607d2 = {"Lcom/glasssutdio/wear/all/bean/FileInfo;", "", "name", "", "size", "", "type", "uri", "Landroid/net/Uri;", "(Ljava/lang/String;JLjava/lang/String;Landroid/net/Uri;)V", "getName", "()Ljava/lang/String;", "getSize", "()J", "getType", "getUri", "()Landroid/net/Uri;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class FileInfo {
    private final String name;
    private final long size;
    private final String type;
    private final Uri uri;

    public static /* synthetic */ FileInfo copy$default(FileInfo fileInfo, String str, long j, String str2, Uri uri, int i, Object obj) {
        if ((i & 1) != 0) {
            str = fileInfo.name;
        }
        if ((i & 2) != 0) {
            j = fileInfo.size;
        }
        long j2 = j;
        if ((i & 4) != 0) {
            str2 = fileInfo.type;
        }
        String str3 = str2;
        if ((i & 8) != 0) {
            uri = fileInfo.uri;
        }
        return fileInfo.copy(str, j2, str3, uri);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final long getSize() {
        return this.size;
    }

    /* renamed from: component3, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component4, reason: from getter */
    public final Uri getUri() {
        return this.uri;
    }

    public final FileInfo copy(String name, long size, String type, Uri uri) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(uri, "uri");
        return new FileInfo(name, size, type, uri);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FileInfo)) {
            return false;
        }
        FileInfo fileInfo = (FileInfo) other;
        return Intrinsics.areEqual(this.name, fileInfo.name) && this.size == fileInfo.size && Intrinsics.areEqual(this.type, fileInfo.type) && Intrinsics.areEqual(this.uri, fileInfo.uri);
    }

    public int hashCode() {
        int iHashCode = ((this.name.hashCode() * 31) + Long.hashCode(this.size)) * 31;
        String str = this.type;
        return ((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.uri.hashCode();
    }

    public String toString() {
        return "FileInfo(name=" + this.name + ", size=" + this.size + ", type=" + this.type + ", uri=" + this.uri + ')';
    }

    public FileInfo(String name, long j, String str, Uri uri) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.name = name;
        this.size = j;
        this.type = str;
        this.uri = uri;
    }

    public final String getName() {
        return this.name;
    }

    public final long getSize() {
        return this.size;
    }

    public final String getType() {
        return this.type;
    }

    public final Uri getUri() {
        return this.uri;
    }
}
