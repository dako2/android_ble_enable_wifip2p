package com.glasssutdio.wear.database.entity;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AiChatEntity.kt */
@Metadata(m606d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u001e\b\u0087\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0010J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u000eHÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\bHÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\bHÆ\u0003J\t\u0010&\u001a\u00020\u000eHÆ\u0003Jm\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000eHÆ\u0001J\u0013\u0010(\u001a\u00020\u000e2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020\bHÖ\u0001J\t\u0010+\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u000b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0016\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0018R\u0016\u0010\u000f\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0018R\u0016\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0016\u0010\f\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014¨\u0006,"}, m607d2 = {"Lcom/glasssutdio/wear/database/entity/AiChatEntity;", "", "uid", "", "role", "", "content", "chatType", "", "filePath", "localFilePath", "chatTimestamp", "uploaded", "isLike", "", "isUnlike", "(JLjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;JIZZ)V", "getChatTimestamp", "()J", "getChatType", "()I", "getContent", "()Ljava/lang/String;", "getFilePath", "()Z", "getLocalFilePath", "getRole", "getUid", "getUploaded", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class AiChatEntity {
    private final long chatTimestamp;
    private final int chatType;
    private final String content;
    private final String filePath;
    private final boolean isLike;
    private final boolean isUnlike;
    private final String localFilePath;
    private final String role;
    private final long uid;
    private final int uploaded;

    /* renamed from: component1, reason: from getter */
    public final long getUid() {
        return this.uid;
    }

    /* renamed from: component10, reason: from getter */
    public final boolean getIsUnlike() {
        return this.isUnlike;
    }

    /* renamed from: component2, reason: from getter */
    public final String getRole() {
        return this.role;
    }

    /* renamed from: component3, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* renamed from: component4, reason: from getter */
    public final int getChatType() {
        return this.chatType;
    }

    /* renamed from: component5, reason: from getter */
    public final String getFilePath() {
        return this.filePath;
    }

    /* renamed from: component6, reason: from getter */
    public final String getLocalFilePath() {
        return this.localFilePath;
    }

    /* renamed from: component7, reason: from getter */
    public final long getChatTimestamp() {
        return this.chatTimestamp;
    }

    /* renamed from: component8, reason: from getter */
    public final int getUploaded() {
        return this.uploaded;
    }

    /* renamed from: component9, reason: from getter */
    public final boolean getIsLike() {
        return this.isLike;
    }

    public final AiChatEntity copy(long uid, String role, String content, int chatType, String filePath, String localFilePath, long chatTimestamp, int uploaded, boolean isLike, boolean isUnlike) {
        Intrinsics.checkNotNullParameter(role, "role");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        Intrinsics.checkNotNullParameter(localFilePath, "localFilePath");
        return new AiChatEntity(uid, role, content, chatType, filePath, localFilePath, chatTimestamp, uploaded, isLike, isUnlike);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AiChatEntity)) {
            return false;
        }
        AiChatEntity aiChatEntity = (AiChatEntity) other;
        return this.uid == aiChatEntity.uid && Intrinsics.areEqual(this.role, aiChatEntity.role) && Intrinsics.areEqual(this.content, aiChatEntity.content) && this.chatType == aiChatEntity.chatType && Intrinsics.areEqual(this.filePath, aiChatEntity.filePath) && Intrinsics.areEqual(this.localFilePath, aiChatEntity.localFilePath) && this.chatTimestamp == aiChatEntity.chatTimestamp && this.uploaded == aiChatEntity.uploaded && this.isLike == aiChatEntity.isLike && this.isUnlike == aiChatEntity.isUnlike;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((((((((((((Long.hashCode(this.uid) * 31) + this.role.hashCode()) * 31) + this.content.hashCode()) * 31) + Integer.hashCode(this.chatType)) * 31) + this.filePath.hashCode()) * 31) + this.localFilePath.hashCode()) * 31) + Long.hashCode(this.chatTimestamp)) * 31) + Integer.hashCode(this.uploaded)) * 31;
        boolean z = this.isLike;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (iHashCode + i) * 31;
        boolean z2 = this.isUnlike;
        return i2 + (z2 ? 1 : z2 ? 1 : 0);
    }

    public String toString() {
        return "AiChatEntity(uid=" + this.uid + ", role=" + this.role + ", content=" + this.content + ", chatType=" + this.chatType + ", filePath=" + this.filePath + ", localFilePath=" + this.localFilePath + ", chatTimestamp=" + this.chatTimestamp + ", uploaded=" + this.uploaded + ", isLike=" + this.isLike + ", isUnlike=" + this.isUnlike + ')';
    }

    public AiChatEntity(long j, String role, String content, int i, String filePath, String localFilePath, long j2, int i2, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(role, "role");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        Intrinsics.checkNotNullParameter(localFilePath, "localFilePath");
        this.uid = j;
        this.role = role;
        this.content = content;
        this.chatType = i;
        this.filePath = filePath;
        this.localFilePath = localFilePath;
        this.chatTimestamp = j2;
        this.uploaded = i2;
        this.isLike = z;
        this.isUnlike = z2;
    }

    public /* synthetic */ AiChatEntity(long j, String str, String str2, int i, String str3, String str4, long j2, int i2, boolean z, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, str2, i, str3, str4, j2, i2, (i3 & 256) != 0 ? false : z, (i3 & 512) != 0 ? false : z2);
    }

    public final long getUid() {
        return this.uid;
    }

    public final String getRole() {
        return this.role;
    }

    public final String getContent() {
        return this.content;
    }

    public final int getChatType() {
        return this.chatType;
    }

    public final String getFilePath() {
        return this.filePath;
    }

    public final String getLocalFilePath() {
        return this.localFilePath;
    }

    public final long getChatTimestamp() {
        return this.chatTimestamp;
    }

    public final int getUploaded() {
        return this.uploaded;
    }

    public final boolean isLike() {
        return this.isLike;
    }

    public final boolean isUnlike() {
        return this.isUnlike;
    }
}
