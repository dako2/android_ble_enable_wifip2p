package com.iflytek.sparkchain.core;

import com.iflytek.sparkchain.core.common.ApiType;

/* loaded from: classes2.dex */
public class SparkChainConfig {
    private ApiType apiType = ApiType.TYPE_CH;
    private String appID = "";
    private String apiKey = "";
    private String apiSecret = "";
    private String uid = "";
    private String workDir = "";
    private int logLevel = 100;
    private String logPath = "";

    private SparkChainConfig() {
    }

    public static SparkChainConfig builder() {
        return new SparkChainConfig();
    }

    public SparkChainConfig apiKey(String str) {
        this.apiKey = str;
        return this;
    }

    public SparkChainConfig apiSecret(String str) {
        this.apiSecret = str;
        return this;
    }

    public SparkChainConfig apiType(ApiType apiType) {
        this.apiType = apiType;
        return this;
    }

    public SparkChainConfig appID(String str) {
        this.appID = str;
        return this;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public String getApiSecret() {
        return this.apiSecret;
    }

    public ApiType getApiType() {
        return this.apiType;
    }

    public String getAppID() {
        return this.appID;
    }

    public int getLogLevel() {
        return this.logLevel;
    }

    public String getLogPath() {
        return this.logPath;
    }

    public String getUid() {
        return this.uid;
    }

    public String getWorkDir() {
        return this.workDir;
    }

    public SparkChainConfig logLevel(int i) {
        this.logLevel = i;
        return this;
    }

    public SparkChainConfig logPath(String str) {
        this.logPath = str;
        return this;
    }

    public String toString() {
        return "SparkChainConfig{appID='" + this.appID + "', apiKey='" + this.apiKey + "', apiSecret='" + this.apiSecret + "', uid='" + this.uid + "', workDir='" + this.workDir + "', logLevel=" + this.logLevel + ", logPath='" + this.logPath + "', apiType='" + this.apiType.name() + "'}";
    }

    public SparkChainConfig uid(String str) {
        this.uid = str;
        return this;
    }

    public SparkChainConfig workDir(String str) {
        this.workDir = str;
        return this;
    }
}
