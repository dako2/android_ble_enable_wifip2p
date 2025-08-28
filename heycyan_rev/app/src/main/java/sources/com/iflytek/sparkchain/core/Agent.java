package com.iflytek.sparkchain.core;

import android.util.Log;
import iflc.AbstractC2442a;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class Agent {
    private static String TAG = "SparkChain";
    private static AtomicInteger runningCount;
    private AgentType agentType;

    /* renamed from: id */
    private int f412id;
    private LLM llm;
    private Memory memory;
    private Tools tools;
    private C2170b baseOutput = new C2170b();
    private C2173e result = new C2173e();
    private C2172d event = new C2172d();
    private C2171c error = new C2171c();
    private C2174f syncOutput = new C2174f(this, null);
    private AtomicInteger runningStatus = new AtomicInteger(0);
    private AgentCallbacks agentCallbacks = null;
    private PluginCallbacks pluginCallbacks = null;
    private Object usrContext = null;

    /* renamed from: com.iflytek.sparkchain.core.Agent$a */
    class RunnableC2169a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ String f413a;

        RunnableC2169a(String str) {
            this.f413a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Agent.this.agentRun(this.f413a);
        }
    }

    /* renamed from: com.iflytek.sparkchain.core.Agent$b */
    private class C2170b implements AgentBaseOutput {

        /* renamed from: a */
        private String f415a = "";

        C2170b() {
        }

        @Override // com.iflytek.sparkchain.core.AgentBaseOutput
        public String getSid() {
            return this.f415a;
        }
    }

    /* renamed from: com.iflytek.sparkchain.core.Agent$c */
    private class C2171c implements AgentError {

        /* renamed from: a */
        private int f417a = 0;

        /* renamed from: b */
        private String f418b = "";

        C2171c() {
        }

        /* renamed from: a */
        C2171c m497a(int i) {
            this.f417a = i;
            return this;
        }

        /* renamed from: a */
        C2171c m498a(String str) {
            this.f418b = str;
            return this;
        }

        /* renamed from: a */
        void m499a() {
            this.f417a = 0;
            this.f418b = "";
        }

        @Override // com.iflytek.sparkchain.core.AgentError
        public int getErrCode() {
            return this.f417a;
        }

        @Override // com.iflytek.sparkchain.core.AgentError
        public String getErrMsg() {
            return this.f418b;
        }

        @Override // com.iflytek.sparkchain.core.AgentBaseOutput
        public String getSid() {
            return Agent.this.baseOutput.getSid();
        }
    }

    /* renamed from: com.iflytek.sparkchain.core.Agent$d */
    private class C2172d implements AgentEvent {

        /* renamed from: a */
        private int f420a = 0;

        /* renamed from: b */
        private String f421b = "";

        C2172d() {
        }

        /* renamed from: a */
        C2172d m500a(int i) {
            this.f420a = i;
            return this;
        }

        /* renamed from: a */
        C2172d m501a(String str) {
            this.f421b = str;
            return this;
        }

        /* renamed from: a */
        void m502a() {
            this.f420a = 0;
            this.f421b = "";
        }

        @Override // com.iflytek.sparkchain.core.AgentEvent
        public int getEventID() {
            return this.f420a;
        }

        @Override // com.iflytek.sparkchain.core.AgentEvent
        public String getEventMsg() {
            return this.f421b;
        }

        @Override // com.iflytek.sparkchain.core.AgentBaseOutput
        public String getSid() {
            return Agent.this.baseOutput.getSid();
        }
    }

    /* renamed from: com.iflytek.sparkchain.core.Agent$e */
    private class C2173e implements AgentResult {

        /* renamed from: a */
        private String f423a = "";

        /* renamed from: b */
        private String f424b = "";

        public C2173e() {
        }

        /* renamed from: a */
        C2173e m503a(String str) {
            this.f423a = str;
            return this;
        }

        /* renamed from: a */
        void m504a() {
            this.f423a = "";
            this.f424b = "";
        }

        /* renamed from: b */
        C2173e m505b(String str) {
            this.f424b = str;
            return this;
        }

        @Override // com.iflytek.sparkchain.core.AgentResult
        public String getRequest() {
            return this.f423a;
        }

        @Override // com.iflytek.sparkchain.core.AgentResult
        public String getResponse() {
            return this.f424b;
        }

        @Override // com.iflytek.sparkchain.core.AgentBaseOutput
        public String getSid() {
            return Agent.this.baseOutput.getSid();
        }
    }

    /* renamed from: com.iflytek.sparkchain.core.Agent$f */
    private class C2174f implements AgentSyncOutput {
        private C2174f() {
        }

        /* synthetic */ C2174f(Agent agent, RunnableC2169a runnableC2169a) {
            this();
        }

        @Override // com.iflytek.sparkchain.core.AgentError
        public int getErrCode() {
            return Agent.this.error.getErrCode();
        }

        @Override // com.iflytek.sparkchain.core.AgentError
        public String getErrMsg() {
            return Agent.this.error.getErrMsg();
        }

        @Override // com.iflytek.sparkchain.core.AgentResult
        public String getRequest() {
            return Agent.this.result.getRequest();
        }

        @Override // com.iflytek.sparkchain.core.AgentResult
        public String getResponse() {
            return Agent.this.result.getResponse();
        }

        @Override // com.iflytek.sparkchain.core.AgentBaseOutput
        public String getSid() {
            return Agent.this.baseOutput.getSid();
        }
    }

    /* renamed from: com.iflytek.sparkchain.core.Agent$g */
    private class C2175g implements PluginResult {

        /* renamed from: a */
        private String f427a = "";

        /* renamed from: b */
        private String f428b = "";

        /* renamed from: c */
        private String f429c = "";

        C2175g() {
        }

        /* renamed from: a */
        C2175g m506a(String str) {
            this.f427a = str;
            return this;
        }

        /* renamed from: b */
        C2175g m507b(String str) {
            this.f428b = str;
            return this;
        }

        /* renamed from: c */
        C2175g m508c(String str) {
            this.f429c = str;
            return this;
        }

        @Override // com.iflytek.sparkchain.core.PluginResult
        public String getName() {
            return this.f427a;
        }

        @Override // com.iflytek.sparkchain.core.PluginResult
        public String getRequest() {
            return this.f428b;
        }

        @Override // com.iflytek.sparkchain.core.PluginResult
        public String getResponse() {
            return this.f429c;
        }
    }

    static {
        try {
            System.loadLibrary("SparkChain");
            Log.v(TAG, "loadLibrary: success");
        } catch (Exception e) {
            Log.e(TAG, "loadLibrary:" + e.toString());
        }
        runningCount = new AtomicInteger(0);
    }

    public Agent(AgentType agentType, LLM llm, Tools tools) throws JSONException {
        this.agentType = agentType;
        this.llm = llm;
        this.tools = tools;
        String string = tools.toString();
        Log.v(TAG, "Plugin Info:" + string);
        Log.v(TAG, "Plugin Info  domain:" + this.llm.getParams().getDomain());
        this.f412id = agentInit(string, this.agentType.getValue(), this.llm.getParams().getDomain());
    }

    native int agentInit(String str, int i, String str2);

    native String agentRun(String str);

    native int agentUnInit();

    public int arun(String str) {
        return arun(str, null, null);
    }

    public int arun(String str, Memory memory) {
        return arun(str, null, memory);
    }

    public int arun(String str, Object obj) {
        return arun(str, obj, null);
    }

    public int arun(String str, Object obj, Memory memory) {
        if (obj != null) {
            this.usrContext = obj;
        }
        if (memory != null) {
            this.memory = memory;
        }
        int initCode = SparkChain.getInst().getInitCode();
        if (initCode != 0) {
            Log.e(TAG, "SparkChain not init");
            return initCode;
        }
        if (this.runningStatus.get() != 0) {
            return 20107;
        }
        if (runningCount.get() >= 2) {
            return 20108;
        }
        this.runningStatus.set(2);
        runningCount.incrementAndGet();
        new Thread(new RunnableC2169a(str)).start();
        return 0;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        agentUnInit();
    }

    public void onAgentError(int i, String str) {
        if (this.agentCallbacks != null && this.runningStatus.get() == 2) {
            C2171c c2171c = new C2171c();
            c2171c.m497a(i);
            c2171c.m498a(str);
            this.agentCallbacks.onAgentError(c2171c, this.usrContext);
        }
        this.runningStatus.set(0);
        runningCount.decrementAndGet();
    }

    public void onAgentEvent(int i, String str) {
        if (this.agentCallbacks != null && this.runningStatus.get() == 2) {
            C2172d c2172d = new C2172d();
            c2172d.m500a(i);
            c2172d.m501a(str);
            this.agentCallbacks.onAgentEvent(c2172d, this.usrContext);
        }
        if (i == 2) {
            this.runningStatus.set(0);
            runningCount.decrementAndGet();
        }
    }

    public void onAgentResult(String str, String str2) {
        if (this.agentCallbacks == null || this.runningStatus.get() != 2) {
            return;
        }
        C2173e c2173e = new C2173e();
        c2173e.m503a(str);
        c2173e.m505b(str2);
        this.agentCallbacks.onAgentResult(c2173e, this.usrContext);
    }

    public void onPluginOutput(String str, String str2, String str3) {
        if (this.pluginCallbacks == null || this.runningStatus.get() != 2) {
            return;
        }
        C2175g c2175g = new C2175g();
        c2175g.m506a(str);
        c2175g.m507b(str2);
        c2175g.m508c(str3);
        this.pluginCallbacks.onPluginOutput(c2175g, this.usrContext);
    }

    public String pluginRun(String str, String str2) {
        Log.i(TAG, "agent plugin called start: " + str);
        if (this.tools == null) {
            Log.e(TAG, "tools is null");
        }
        AbstractC2442a plugin = this.tools.getPlugin(str);
        if (plugin == null) {
            Log.e(TAG, "plugin not found");
            return "plugin not found";
        }
        Log.v(TAG, "agent plugin input: " + str2);
        String string = plugin.m597a(str2, new String[0]).toString();
        if (string == null) {
            return "plugin return null";
        }
        Log.i(TAG, "agent plugin called finish: " + str);
        Log.v(TAG, "agent plugin output: " + string);
        return string;
    }

    public void registerAgentCallbacks(AgentCallbacks agentCallbacks) {
        this.agentCallbacks = agentCallbacks;
    }

    public void registerPluginCallbacks(PluginCallbacks pluginCallbacks) {
        this.pluginCallbacks = pluginCallbacks;
    }

    public AgentSyncOutput run(String str) {
        return run(str, null);
    }

    public AgentSyncOutput run(String str, Memory memory) throws NumberFormatException {
        String str2;
        C2171c c2171c;
        int i;
        if (memory != null) {
            this.memory = memory;
        }
        this.result.m504a();
        this.event.m502a();
        this.error.m499a();
        int initCode = SparkChain.getInst().getInitCode();
        if (initCode != 0) {
            Log.e(TAG, "SparkChain not init");
            this.error.m497a(initCode);
            this.error.m498a("SparkChain not init");
        } else {
            if (this.runningStatus.get() != 0) {
                str2 = "llm inst is running";
                Log.e(TAG, "llm inst is running");
                c2171c = this.error;
                i = 20107;
            } else if (runningCount.get() >= 2) {
                str2 = "llm concurrent overflow";
                Log.e(TAG, "llm concurrent overflow");
                c2171c = this.error;
                i = 20108;
            } else {
                this.runningStatus.set(1);
                runningCount.incrementAndGet();
                String strAgentRun = agentRun(str);
                Log.d(TAG, "agentRun ret:" + strAgentRun);
                String[] strArrSplit = strAgentRun.split(";");
                int i2 = Integer.parseInt(strArrSplit[0]);
                String str3 = strArrSplit[1];
                if (i2 != 0) {
                    this.error.m497a(i2);
                    this.error.m498a(str3);
                } else {
                    this.result.m503a(str);
                    this.result.m505b(str3);
                }
                this.runningStatus.set(0);
                runningCount.decrementAndGet();
            }
            c2171c.m497a(i);
            this.error.m498a(str2);
        }
        return this.syncOutput;
    }
}
