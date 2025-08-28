package com.iflytek.sparkchain.core;

/* loaded from: classes2.dex */
public interface AgentCallbacks {
    void onAgentError(AgentError agentError, Object obj);

    void onAgentEvent(AgentEvent agentEvent, Object obj);

    void onAgentResult(AgentResult agentResult, Object obj);
}
