package com.glasssutdio.wear.api.request;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VisionChatRequest.kt */
@Metadata(m606d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\f"}, m607d2 = {"Lcom/glasssutdio/wear/api/request/VisionChatRequest;", "", "text", "", "imageBase64", "(Ljava/lang/String;Ljava/lang/String;)V", "getImageBase64", "()Ljava/lang/String;", "setImageBase64", "(Ljava/lang/String;)V", "getText", "setText", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class VisionChatRequest {
    private String imageBase64;
    private String text;

    public VisionChatRequest(String text, String imageBase64) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(imageBase64, "imageBase64");
        this.text = text;
        this.imageBase64 = imageBase64;
    }

    public final String getImageBase64() {
        return this.imageBase64;
    }

    public final String getText() {
        return this.text;
    }

    public final void setImageBase64(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.imageBase64 = str;
    }

    public final void setText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.text = str;
    }
}
