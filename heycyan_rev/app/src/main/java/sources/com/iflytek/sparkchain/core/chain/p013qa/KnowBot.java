package com.iflytek.sparkchain.core.chain.p013qa;

import android.util.Log;
import com.iflytek.sparkchain.core.Memory;
import com.iflytek.sparkchain.core.chain.base.Chain;
import com.iflytek.sparkchain.core.chain.base.ChainCallbacks;
import ifla.AbstractC2437a;

/* loaded from: classes2.dex */
public class KnowBot implements Chain<KnowBotCallbacks> {
    private static final String TAG = "SparkChain";
    private ChainCallbacks callback;
    private EnumC2201a chainType = EnumC2201a.REFINE;
    private int index;
    private AbstractC2437a prompt;

    public class KnowBotErrorImpl implements KnowBotError {
        String sid = "";
        int errCode = 0;
        String errMsg = "";
        Object userContext = null;

        public KnowBotErrorImpl() {
        }

        public void clear() {
            this.sid = "";
            this.errCode = 0;
            this.errMsg = "";
            this.userContext = null;
        }

        @Override // com.iflytek.sparkchain.core.chain.p013qa.KnowBotError
        public int getErrCode() {
            return this.errCode;
        }

        @Override // com.iflytek.sparkchain.core.chain.p013qa.KnowBotError
        public String getErrMsg() {
            return this.errMsg;
        }

        @Override // com.iflytek.sparkchain.core.AgentBaseOutput
        public String getSid() {
            return this.sid;
        }

        @Override // com.iflytek.sparkchain.core.chain.p013qa.KnowBotError
        public Object getUserContext() {
            return this.userContext;
        }

        public void setErrCode(int i) {
            this.errCode = i;
        }

        public void setErrMsg(String str) {
            this.errMsg = str;
        }

        public void setSid(String str) {
            this.sid = str;
        }

        public void setUserContext(int i) {
            this.userContext = Integer.valueOf(i);
        }
    }

    public class KnowBotEventImpl implements KnowBotEvent {
        String sid = "";
        int eventID = 0;
        String eventMsg = "";
        Object userContext = null;

        public KnowBotEventImpl() {
        }

        public void clear() {
            this.sid = "";
            this.eventID = 0;
            this.eventMsg = "";
            this.userContext = null;
        }

        @Override // com.iflytek.sparkchain.core.chain.p013qa.KnowBotEvent
        public int getEventID() {
            return this.eventID;
        }

        @Override // com.iflytek.sparkchain.core.chain.p013qa.KnowBotEvent
        public String getEventMsg() {
            return this.eventMsg;
        }

        @Override // com.iflytek.sparkchain.core.AgentBaseOutput
        public String getSid() {
            return this.sid;
        }

        @Override // com.iflytek.sparkchain.core.chain.p013qa.KnowBotEvent
        public Object getUserContext() {
            return this.userContext;
        }

        public void setEventID(int i) {
            this.eventID = i;
        }

        public void setEventMsg(String str) {
            this.eventMsg = str;
        }

        public void setSid(String str) {
            this.sid = str;
        }

        public void setUserContext(Object obj) {
            this.userContext = obj;
        }
    }

    public class KnowBotResultImpl implements KnowBotResult {
        String sid = "";
        String result = "";
        int status = 0;
        String msg = "";
        Object userContext = null;

        public KnowBotResultImpl() {
        }

        public void clear() {
            this.sid = "";
            this.result = "";
            this.status = 0;
            this.msg = "";
            this.userContext = null;
        }

        @Override // com.iflytek.sparkchain.core.chain.p013qa.KnowBotResult
        public String getMsg() {
            return this.msg;
        }

        @Override // com.iflytek.sparkchain.core.chain.p013qa.KnowBotResult
        public String getResult() {
            return this.result;
        }

        @Override // com.iflytek.sparkchain.core.AgentBaseOutput
        public String getSid() {
            return this.sid;
        }

        @Override // com.iflytek.sparkchain.core.chain.p013qa.KnowBotResult
        public int getStatus() {
            return this.status;
        }

        @Override // com.iflytek.sparkchain.core.chain.p013qa.KnowBotResult
        public Object getUserContext() {
            return this.userContext;
        }

        public void setMsg(String str) {
            this.msg = str;
        }

        public void setResult(String str) {
            this.result = str;
        }

        public void setSid(String str) {
            this.sid = str;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public void setUserContext(Object obj) {
            this.userContext = obj;
        }
    }

    public KnowBot(String str) {
        this.index = knowBotCreate(str, null);
    }

    public KnowBot(String str, Memory memory) {
        this.index = knowBotCreate(str, memory);
    }

    public int arun(String str) {
        return arun(str, null);
    }

    public int arun(final String str, final Object obj) {
        new Thread(new Runnable() { // from class: com.iflytek.sparkchain.core.chain.qa.KnowBot.1
            @Override // java.lang.Runnable
            public void run() {
                KnowBot.this.knowBotArun(str, obj);
            }
        }).start();
        return 0;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        knowBotDestroy();
    }

    native int knowBotArun(String str, Object obj);

    native int knowBotCreate(String str, Memory memory);

    native int knowBotDestroy();

    public void knowBotErrorCallback(KnowBotErrorImpl knowBotErrorImpl, Object obj) {
        Log.i(TAG, "Java klChainErrorCallback");
        ChainCallbacks chainCallbacks = this.callback;
        if (chainCallbacks != null) {
            chainCallbacks.onError(knowBotErrorImpl, obj);
        }
    }

    public void knowBotEventCallback(KnowBotEventImpl knowBotEventImpl, Object obj) {
        Log.i(TAG, "Java KnowBotChainEventCallback");
        ChainCallbacks chainCallbacks = this.callback;
        if (chainCallbacks != null) {
            chainCallbacks.onEvent(knowBotEventImpl, obj);
        }
    }

    public void knowBotResultCallback(KnowBotResultImpl knowBotResultImpl, Object obj) {
        Log.i(TAG, "Java KnowBotChainResultCallback");
        ChainCallbacks chainCallbacks = this.callback;
        if (chainCallbacks != null) {
            chainCallbacks.onOutput(knowBotResultImpl, obj);
        }
    }

    public void prompt(AbstractC2437a abstractC2437a) {
    }

    @Override // com.iflytek.sparkchain.core.chain.base.Chain
    public void registerCallback(KnowBotCallbacks knowBotCallbacks) {
        this.callback = knowBotCallbacks;
    }

    public void setBotId(String str) {
        setKnowBotString("botId", str);
    }

    public void setChat_id(String str) {
        setKnowBotString("chat_id", str);
    }

    native void setKnowBotDouble(String str, double d);

    native void setKnowBotInteger(String str, int i);

    native void setKnowBotString(String str, String str2);

    public void setMax_tokens(int i) {
        setKnowBotInteger("max_tokens", i);
    }

    public void setPrompt(String str) {
        setKnowBotString("prompt", str);
    }

    public void setScore(double d) {
        setKnowBotDouble("score", d);
    }

    public void setTemperature(double d) {
        setKnowBotDouble("temperature", d);
    }

    public void setTop_k(int i) {
        setKnowBotInteger("top_k", i);
    }
}
