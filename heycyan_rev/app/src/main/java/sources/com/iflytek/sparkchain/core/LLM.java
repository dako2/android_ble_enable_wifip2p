package com.iflytek.sparkchain.core;

import android.util.Log;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class LLM {
    private static final String TAG = "SparkChain";
    static AtomicInteger sessionId = new AtomicInteger(0);
    private LLMCallbacks cbs;
    private HashMap<Integer, Object> contextMap;
    private int index;
    private Memory memory;
    private EnumC2202d modelType;
    private String name;
    private LLMConfig params;

    public class LLMBaseOutputImpl implements LLMBaseOutput {
        String sid = "";

        LLMBaseOutputImpl() {
        }

        void clear() {
            this.sid = "";
        }

        @Override // com.iflytek.sparkchain.core.LLMBaseOutput
        public String getSid() {
            return this.sid;
        }

        protected LLMBaseOutputImpl setSid(String str) {
            this.sid = str;
            return this;
        }
    }

    public class LLMErrorImpl implements LLMError {
        String sid = "";
        int errCode = 0;
        String errMsg = "";

        public LLMErrorImpl() {
        }

        public void clear() {
            this.sid = "";
            this.errCode = 0;
            this.errMsg = "";
        }

        @Override // com.iflytek.sparkchain.core.LLMError
        public int getErrCode() {
            return this.errCode;
        }

        @Override // com.iflytek.sparkchain.core.LLMError
        public String getErrMsg() {
            return this.errMsg;
        }

        @Override // com.iflytek.sparkchain.core.LLMBaseOutput
        public String getSid() {
            return this.sid;
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
    }

    public class LLMEventImpl implements LLMEvent {
        String sid = "";
        int eventID = 0;
        String eventMsg = "";

        public LLMEventImpl() {
        }

        public void clear() {
            this.sid = "";
            this.eventID = 0;
            this.eventMsg = "";
        }

        @Override // com.iflytek.sparkchain.core.LLMEvent
        public int getEventID() {
            return this.eventID;
        }

        @Override // com.iflytek.sparkchain.core.LLMEvent
        public String getEventMsg() {
            return this.eventMsg;
        }

        @Override // com.iflytek.sparkchain.core.LLMBaseOutput
        public String getSid() {
            return this.sid;
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
    }

    public class LLMOutputImpl implements LLMOutput {
        byte[] bytes;
        String sid = "";
        String role = "";
        String content = "";
        String raw = "";
        String functionCall = "";
        int status = 0;
        int completionTokens = 0;
        int promptTokens = 0;
        int totalTokens = 0;
        int errCode = 0;
        String errMsg = "";

        public LLMOutputImpl() {
        }

        public void appendContent(String str) {
            this.content += str;
        }

        public void clear() {
            this.sid = "";
            this.role = "";
            this.content = "";
            this.raw = "";
            this.functionCall = "";
            this.status = 0;
            this.completionTokens = 0;
            this.promptTokens = 0;
            this.totalTokens = 0;
            this.errCode = 0;
            this.errMsg = "";
        }

        @Override // com.iflytek.sparkchain.core.LLMResult
        public int getCompletionTokens() {
            return this.completionTokens;
        }

        @Override // com.iflytek.sparkchain.core.LLMResult
        public String getContent() {
            return this.content;
        }

        @Override // com.iflytek.sparkchain.core.LLMError
        public int getErrCode() {
            return this.errCode;
        }

        @Override // com.iflytek.sparkchain.core.LLMError
        public String getErrMsg() {
            return this.errMsg;
        }

        @Override // com.iflytek.sparkchain.core.LLMResult
        public String getFunctionCall() {
            return this.functionCall;
        }

        @Override // com.iflytek.sparkchain.core.LLMResult
        public byte[] getImage() {
            return this.bytes;
        }

        @Override // com.iflytek.sparkchain.core.LLMResult
        public int getPromptTokens() {
            return this.promptTokens;
        }

        @Override // com.iflytek.sparkchain.core.LLMResult
        public String getRaw() {
            return this.raw;
        }

        @Override // com.iflytek.sparkchain.core.LLMResult
        public String getRole() {
            return this.role;
        }

        @Override // com.iflytek.sparkchain.core.LLMBaseOutput
        public String getSid() {
            return this.sid;
        }

        @Override // com.iflytek.sparkchain.core.LLMResult
        public int getStatus() {
            return this.status;
        }

        @Override // com.iflytek.sparkchain.core.LLMResult
        public int getTotalTokens() {
            return this.totalTokens;
        }

        public void setCompletionTokens(int i) {
            this.completionTokens = i;
        }

        public void setContent(String str) {
            this.content = str;
        }

        public void setErrCode(int i) {
            this.errCode = i;
        }

        public void setErrMsg(String str) {
            this.errMsg = str;
        }

        public void setFunctionCall(String str) {
            this.functionCall = str;
        }

        public void setPromptTokens(int i) {
            this.promptTokens = i;
        }

        public void setRaw(String str) {
            this.raw = str;
        }

        public void setRole(String str) {
            this.role = str;
        }

        public void setSid(String str) {
            this.sid = str;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public void setTotalTokens(int i) {
            this.totalTokens = i;
        }
    }

    public class LLMResultImpl implements LLMResult {
        byte[] bytes;
        String sid = "";
        String role = "";
        String content = "";
        String raw = "";
        String functionCall = "";
        int status = 0;
        int completionTokens = 0;
        int promptTokens = 0;
        int totalTokens = 0;

        public LLMResultImpl() {
        }

        public void appendContent(String str) {
            this.content += str;
        }

        public void clear() {
            this.sid = "";
            this.role = "";
            this.content = "";
            this.raw = "";
            this.functionCall = "";
            this.status = 0;
            this.completionTokens = 0;
            this.promptTokens = 0;
            this.totalTokens = 0;
        }

        @Override // com.iflytek.sparkchain.core.LLMResult
        public int getCompletionTokens() {
            return this.completionTokens;
        }

        @Override // com.iflytek.sparkchain.core.LLMResult
        public String getContent() {
            return this.content;
        }

        @Override // com.iflytek.sparkchain.core.LLMResult
        public String getFunctionCall() {
            return this.functionCall;
        }

        @Override // com.iflytek.sparkchain.core.LLMResult
        public byte[] getImage() {
            return this.bytes;
        }

        @Override // com.iflytek.sparkchain.core.LLMResult
        public int getPromptTokens() {
            return this.promptTokens;
        }

        @Override // com.iflytek.sparkchain.core.LLMResult
        public String getRaw() {
            return this.raw;
        }

        @Override // com.iflytek.sparkchain.core.LLMResult
        public String getRole() {
            return this.role;
        }

        @Override // com.iflytek.sparkchain.core.LLMBaseOutput
        public String getSid() {
            return this.sid;
        }

        @Override // com.iflytek.sparkchain.core.LLMResult
        public int getStatus() {
            return this.status;
        }

        @Override // com.iflytek.sparkchain.core.LLMResult
        public int getTotalTokens() {
            return this.totalTokens;
        }

        public void setCompletionTokens(int i) {
            this.completionTokens = i;
        }

        public void setContent(String str) {
            this.content = str;
        }

        public void setFunctionCall(String str) {
            this.functionCall = str;
        }

        public void setPromptTokens(int i) {
            this.promptTokens = i;
        }

        public void setRaw(String str) {
            this.raw = str;
        }

        public void setRole(String str) {
            this.role = str;
        }

        public void setSid(String str) {
            this.sid = str;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public void setTotalTokens(int i) {
            this.totalTokens = i;
        }
    }

    public LLM() {
        this(EnumC2202d.TEXT_GENERATION, LLMConfig.builder(), null);
    }

    public LLM(LLMConfig lLMConfig) {
        this(EnumC2202d.TEXT_GENERATION, lLMConfig, null);
    }

    public LLM(LLMConfig lLMConfig, Memory memory) {
        this(EnumC2202d.TEXT_GENERATION, lLMConfig, memory);
    }

    public LLM(Memory memory) {
        this(EnumC2202d.TEXT_GENERATION, LLMConfig.builder(), memory);
    }

    public LLM(EnumC2202d enumC2202d, LLMConfig lLMConfig, Memory memory) {
        this.name = "LLM";
        this.contextMap = new HashMap<>();
        this.params = lLMConfig == null ? LLMConfig.builder() : lLMConfig;
        this.memory = memory;
        this.index = llmCreate(enumC2202d.getValue(), this.params, this.memory);
    }

    public int addSystemPrompt(String str) {
        return llmAddSystemPrompt(str);
    }

    public int arun(String str) {
        return arun(str, (Object) null);
    }

    public int arun(String str, LLMTools lLMTools, Object obj) {
        if (str == null) {
            Log.e(TAG, "question is null object");
            return 18501;
        }
        int initCode = SparkChain.getInst().getInitCode();
        if (initCode != 0) {
            Log.e(TAG, "SparkChain not init");
            return initCode;
        }
        String type = lLMTools.getType();
        String description = lLMTools.getDescription();
        int iIncrementAndGet = sessionId.incrementAndGet();
        this.contextMap.put(Integer.valueOf(iIncrementAndGet), obj);
        return llmARun(str, type, description, iIncrementAndGet);
    }

    public int arun(String str, Object obj) {
        if (str == null) {
            Log.e(TAG, "question is null object");
            return 18501;
        }
        int initCode = SparkChain.getInst().getInitCode();
        if (initCode != 0) {
            Log.e(TAG, "SparkChain not init");
            return initCode;
        }
        int iIncrementAndGet = sessionId.incrementAndGet();
        this.contextMap.put(Integer.valueOf(iIncrementAndGet), obj);
        return llmARun(str, "", "", iIncrementAndGet);
    }

    public int arun(String str, byte[] bArr) {
        return arun(str, bArr, (Object) null);
    }

    public int arun(String str, byte[] bArr, Object obj) {
        if (str == null) {
            Log.e(TAG, "question is null object");
            return 18501;
        }
        int initCode = SparkChain.getInst().getInitCode();
        if (initCode != 0) {
            Log.e(TAG, "SparkChain not init");
            return initCode;
        }
        int iIncrementAndGet = sessionId.incrementAndGet();
        this.contextMap.put(Integer.valueOf(iIncrementAndGet), obj);
        return llmARunImage(str, bArr, iIncrementAndGet);
    }

    public int arunWithJson(String str) {
        return arunWithJson(str, null);
    }

    public int arunWithJson(String str, Object obj) {
        if (str == null) {
            Log.e(TAG, "question is null xxx object");
            return 18501;
        }
        int initCode = SparkChain.getInst().getInitCode();
        if (initCode != 0) {
            Log.e(TAG, "SparkChain not init");
            return initCode;
        }
        int iIncrementAndGet = sessionId.incrementAndGet();
        this.contextMap.put(Integer.valueOf(iIncrementAndGet), obj);
        return llmARunWithJson(str, iIncrementAndGet);
    }

    public void clearHistory() {
        llmClearHistory();
    }

    protected void finalize() throws Throwable {
        super.finalize();
        llmDestroy();
    }

    public String getName() {
        return this.name;
    }

    public LLMConfig getParams() {
        return this.params;
    }

    native int llmARun(String str, String str2, String str3, int i);

    native int llmARunImage(String str, byte[] bArr, int i);

    native int llmARunWithJson(String str, int i);

    native int llmAddSystemPrompt(String str);

    native void llmClearHistory();

    native int llmCreate(int i, LLMConfig lLMConfig, Memory memory);

    native int llmDestroy();

    public void llmErrorCallback(LLMErrorImpl lLMErrorImpl, int i) {
        Log.i(TAG, "Java llmErrorCallback");
        LLMCallbacks lLMCallbacks = this.cbs;
        if (lLMCallbacks != null) {
            lLMCallbacks.onLLMError(lLMErrorImpl, this.contextMap.get(Integer.valueOf(i)));
        }
        this.contextMap.remove(Integer.valueOf(i));
    }

    public void llmEventCallback(LLMEventImpl lLMEventImpl, int i) {
        Log.i(TAG, "Java llmEventCallback");
        LLMCallbacks lLMCallbacks = this.cbs;
        if (lLMCallbacks != null) {
            lLMCallbacks.onLLMEvent(lLMEventImpl, this.contextMap.get(Integer.valueOf(i)));
        }
    }

    public void llmResultCallback(LLMResultImpl lLMResultImpl, int i) {
        Log.i(TAG, "Java llmResultCallback");
        LLMCallbacks lLMCallbacks = this.cbs;
        if (lLMCallbacks != null) {
            lLMCallbacks.onLLMResult(lLMResultImpl, this.contextMap.get(Integer.valueOf(i)));
        }
        if (lLMResultImpl.getStatus() == 2) {
            this.contextMap.remove(Integer.valueOf(i));
        }
    }

    native LLMOutputImpl llmRun(String str, String str2, String str3, int i);

    native LLMOutputImpl llmRunImage(String str, byte[] bArr, int i);

    native LLMOutputImpl llmRunWithJson(String str, int i);

    native int llmStop();

    public void registerLLMCallbacks(LLMCallbacks lLMCallbacks) {
        this.cbs = lLMCallbacks;
    }

    public LLMOutput run(String str) {
        return run(str, 60);
    }

    public LLMOutput run(String str, int i) {
        String str2;
        LLMOutputImpl lLMOutputImpl;
        int initCode = SparkChain.getInst().getInitCode();
        if (str == null) {
            str2 = "question is null object";
            Log.e(TAG, "question is null object");
            lLMOutputImpl = new LLMOutputImpl();
            initCode = 18501;
        } else {
            if (initCode == 0) {
                return llmRun(str, "", "", i);
            }
            str2 = "SparkChain not init";
            Log.e(TAG, "SparkChain not init");
            lLMOutputImpl = new LLMOutputImpl();
        }
        lLMOutputImpl.setErrCode(initCode);
        lLMOutputImpl.setErrMsg(str2);
        return lLMOutputImpl;
    }

    public LLMOutput run(String str, LLMTools lLMTools, int i) {
        String str2;
        LLMOutputImpl lLMOutputImpl;
        int initCode = SparkChain.getInst().getInitCode();
        if (str == null) {
            str2 = "question is null object";
            Log.e(TAG, "question is null object");
            lLMOutputImpl = new LLMOutputImpl();
            lLMOutputImpl.setErrCode(18501);
        } else {
            if (initCode == 0) {
                return llmRun(str, lLMTools.getType(), lLMTools.getDescription(), i);
            }
            str2 = "SparkChain not init";
            Log.e(TAG, "SparkChain not init");
            lLMOutputImpl = new LLMOutputImpl();
            lLMOutputImpl.setErrCode(initCode);
        }
        lLMOutputImpl.setErrMsg(str2);
        return lLMOutputImpl;
    }

    public LLMOutput run(String str, byte[] bArr) {
        return run(str, bArr, 60);
    }

    public LLMOutput run(String str, byte[] bArr, int i) {
        String str2;
        LLMOutputImpl lLMOutputImpl;
        int initCode = SparkChain.getInst().getInitCode();
        if (str == null) {
            str2 = "question is null object";
            Log.e(TAG, "question is null object");
            lLMOutputImpl = new LLMOutputImpl();
            lLMOutputImpl.setErrCode(18501);
        } else {
            if (initCode == 0) {
                return llmRunImage(str, bArr, i);
            }
            str2 = "SparkChain not init";
            Log.e(TAG, "SparkChain not init");
            lLMOutputImpl = new LLMOutputImpl();
            lLMOutputImpl.setErrCode(initCode);
        }
        lLMOutputImpl.setErrMsg(str2);
        return lLMOutputImpl;
    }

    public LLMOutputImpl runWithJson(String str) {
        return runWithJson(str, 60);
    }

    public LLMOutputImpl runWithJson(String str, int i) {
        String str2;
        LLMOutputImpl lLMOutputImpl;
        int initCode = SparkChain.getInst().getInitCode();
        if (str == null) {
            str2 = "body is null object";
            Log.e(TAG, "body is null object");
            lLMOutputImpl = new LLMOutputImpl();
            initCode = 18501;
        } else {
            if (initCode == 0) {
                return llmRunWithJson(str, i);
            }
            str2 = "SparkChain not init";
            Log.e(TAG, "SparkChain not init");
            lLMOutputImpl = new LLMOutputImpl();
        }
        lLMOutputImpl.setErrCode(initCode);
        lLMOutputImpl.setErrMsg(str2);
        return lLMOutputImpl;
    }

    public int stop() {
        return llmStop();
    }
}
