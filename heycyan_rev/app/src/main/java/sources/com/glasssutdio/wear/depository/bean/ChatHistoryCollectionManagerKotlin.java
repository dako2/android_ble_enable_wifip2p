package com.glasssutdio.wear.depository.bean;

import com.glasssutdio.wear.api.request.AiChatBean;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChatHistoryCollectionManagerKotlin.kt */
@Metadata(m606d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u0006\u0010\t\u001a\u00020\u0007J\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, m607d2 = {"Lcom/glasssutdio/wear/depository/bean/ChatHistoryCollectionManagerKotlin;", "", "()V", "messages", "", "Lcom/glasssutdio/wear/api/request/AiChatBean;", "addMessage", "", "message", "clear", "getMessages", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class ChatHistoryCollectionManagerKotlin {
    private final List<AiChatBean> messages = new ArrayList();

    public final void addMessage(AiChatBean message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (this.messages.size() >= 10) {
            CollectionsKt.removeFirst(this.messages);
        }
        this.messages.add(message);
    }

    public final List<AiChatBean> getMessages() {
        return this.messages;
    }

    public final void clear() {
        this.messages.clear();
    }
}
