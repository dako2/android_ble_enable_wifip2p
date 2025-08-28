package com.iflytek.sparkchain.core;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.iflytek.sparkchain.core.BaseLibrary;
import kotlinx.coroutines.DebugKt;

/* loaded from: classes2.dex */
public class SparkChain {
    private static final String TAG = "SparkChain";
    private static AuthListener authListener = new C2193a();
    private static int initCode = -1;
    private Context context;

    /* renamed from: com.iflytek.sparkchain.core.SparkChain$a */
    class C2193a implements AuthListener {
        C2193a() {
        }

        @Override // com.iflytek.sparkchain.core.AuthListener
        public void onAuthStateChange(ErrType errType, int i) {
            int unused = SparkChain.initCode = i;
        }
    }

    /* renamed from: com.iflytek.sparkchain.core.SparkChain$b */
    private static class C2194b {

        /* renamed from: a */
        private static final SparkChain f454a = new SparkChain(null);
    }

    private SparkChain() {
    }

    /* synthetic */ SparkChain(C2193a c2193a) {
        this();
    }

    public static SparkChain getInst() {
        return C2194b.f454a;
    }

    public SparkChainConfig createConfig(Context context) {
        SparkChainConfig sparkChainConfigBuilder = SparkChainConfig.builder();
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            Object obj = bundle.get("SPARKCHAIN_APPID");
            if (obj != null) {
                sparkChainConfigBuilder.appID(String.valueOf(obj));
            }
            Object obj2 = bundle.get("SPARKCHAIN_APIKEY");
            if (obj2 != null) {
                sparkChainConfigBuilder.apiKey(String.valueOf(obj2));
            }
            Object obj3 = bundle.get("SPARKCHAIN_APISECRET");
            if (obj3 != null) {
                sparkChainConfigBuilder.apiSecret(String.valueOf(obj3));
            }
            Object obj4 = bundle.get("SPARKCHAIN_WORKDIR");
            if (obj4 != null) {
                sparkChainConfigBuilder.workDir(String.valueOf(obj4));
            }
            String string = bundle.getString("SPARKCHAIN_LOGPATH");
            if (string != null) {
                sparkChainConfigBuilder.logPath(string);
            }
            Object obj5 = bundle.get("SPARKCHAIN_UID");
            if (obj5 != null) {
                String strValueOf = String.valueOf(obj5);
                if (strValueOf.contains(".")) {
                    throw new ClassCastException("SparkChain: UID添加失败，在使用时避免纯数字格式或包含小数点。若需使用,请换用SparkChianConfig配置");
                }
                sparkChainConfigBuilder.uid(strValueOf);
            }
            Object obj6 = bundle.get("SPARKCHAIN_LOGLEVEL");
            if (obj6 != null) {
                if (!(obj6 instanceof Integer)) {
                    throw new ClassCastException("SparkChain: logLevel添加失败，请检查配置文件中属性是否设置正确！");
                }
                sparkChainConfigBuilder.logLevel(((Integer) obj6).intValue());
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return sparkChainConfigBuilder;
    }

    public Context getContext() {
        return this.context;
    }

    public int getInitCode() {
        return initCode;
    }

    public int init(Context context) {
        return init(context, createConfig(context));
    }

    public int init(Context context, SparkChainConfig sparkChainConfig) {
        if (initCode == 0) {
            Log.i(TAG, "SparkChain sdk already init");
            return initCode;
        }
        if (context == null || sparkChainConfig == null) {
            return 18509;
        }
        this.context = context;
        if (sparkChainConfig.getAppID() == null || sparkChainConfig.getAppID().isEmpty()) {
            Log.e(TAG, "sparkchain err param required missed 18503");
            return 18503;
        }
        if (sparkChainConfig.getApiKey() == null || sparkChainConfig.getApiKey().isEmpty()) {
            Log.e(TAG, "sparkchain err param null apikey ptr 18504");
            return 18504;
        }
        if (sparkChainConfig.getApiSecret() == null || sparkChainConfig.getApiSecret().isEmpty()) {
            Log.e(TAG, "sparkchain err param null apisecret ptr 18505");
            return 18505;
        }
        if (sparkChainConfig.getUid() == null || sparkChainConfig.getLogPath() == null || sparkChainConfig.getWorkDir() == null) {
            return 18501;
        }
        String uid = sparkChainConfig.getUid();
        String string = sparkChainConfig.getWorkDir().isEmpty() ? getContext().getFilesDir().toString() : sparkChainConfig.getWorkDir();
        int logLevel = sparkChainConfig.getLogLevel();
        int i = sparkChainConfig.getLogPath().isEmpty() ? 1 : 2;
        String logPath = sparkChainConfig.getLogPath().isEmpty() ? string : sparkChainConfig.getLogPath();
        Log.i(TAG, "SparkChain init");
        BaseLibrary.Params.Builder builder = BaseLibrary.Params.builder();
        builder.appId(sparkChainConfig.getAppID()).apiKey(sparkChainConfig.getApiKey()).apiSecret(sparkChainConfig.getApiSecret()).apiType(sparkChainConfig.getApiType().ordinal()).workDir(string);
        if (!uid.isEmpty()) {
            builder.customDeviceId(uid);
        }
        AiHelper.getInst().registerListener(authListener);
        AiHelper.getInst().setLogInfo(LogLvl.valueOf(logLevel == 666 ? 0 : logLevel), i, logPath);
        if (logLevel == 666) {
            AiHelper.getInst().setConfig("godLog", DebugKt.DEBUG_PROPERTY_VALUE_ON);
        }
        AiHelper.getInst().init(getContext(), builder.build());
        return initCode;
    }

    public int unInit() {
        if (initCode != 0) {
            Log.e(TAG, "SparkChain sdk not init");
            return 18301;
        }
        AiHelper.getInst().unInit();
        Log.i(TAG, "SparkChain unInit");
        initCode = 18301;
        return 0;
    }
}
