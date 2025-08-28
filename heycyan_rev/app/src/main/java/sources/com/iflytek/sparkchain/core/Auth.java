package com.iflytek.sparkchain.core;

import android.content.Context;
import android.util.Log;
import com.iflytek.sparkchain.core.BaseLibrary;

/* loaded from: classes2.dex */
public class Auth {

    /* renamed from: i */
    private static String f435i;

    /* renamed from: a */
    private final String f436a;

    /* renamed from: b */
    private int f437b;

    /* renamed from: c */
    private int f438c;

    /* renamed from: d */
    private Context f439d;

    /* renamed from: e */
    private String f440e;

    /* renamed from: f */
    private CoreListener f441f;

    /* renamed from: g */
    private AuthListener f442g;

    /* renamed from: h */
    public boolean f443h;

    /* renamed from: com.iflytek.sparkchain.core.Auth$b */
    private static class C2187b {

        /* renamed from: a */
        private static Auth f444a = new Auth();
    }

    static {
        try {
            System.loadLibrary("SparkChain");
        } catch (Exception e) {
            Log.e("AEE", "loadLibrary:" + e.toString());
        }
        f435i = "1.0.0";
    }

    private Auth() {
        this.f436a = getClass().getSimpleName();
        this.f437b = -1;
        this.f438c = -1;
        this.f441f = null;
        this.f442g = null;
        this.f443h = false;
    }

    /* renamed from: c */
    public static Auth m510c() {
        return C2187b.f444a;
    }

    /* renamed from: a */
    public Context m511a() {
        return this.f439d;
    }

    /* renamed from: a */
    public void m512a(int i, int i2) {
        this.f437b = i;
        this.f438c = i2;
        AuthListener authListener = this.f442g;
        if (authListener == null && (authListener = this.f441f) == null) {
            return;
        }
        authListener.onAuthStateChange(ErrType.valueOf(i), i2);
    }

    /* renamed from: a */
    void m513a(Context context) {
        this.f439d = context;
        if (context == null) {
            this.f437b = -1;
            this.f438c = -1;
        }
    }

    /* renamed from: a */
    protected void m514a(Context context, BaseLibrary.Params params) {
        this.f438c = -1;
        int iInitAndCheck = initAndCheck(context, params.getAppId(), params.getApiKey(), params.getApiSecret(), params.getAuthType(), params.getLicenseFile(), params.getWorkDir(), params.isLogOpen(), params.isiLogOpen(), params.getiLogMaxCount(), params.getiLogMaxSize(), params.isRecordOpen(), params.getCustomDeviceId(), params.getAuthInterval(), params.getResDir(), params.getBatchID(), params.getCfgFile(), params.getAbility(), params.getApiType());
        Log.i(this.f436a, "auth init ret:" + iInitAndCheck);
        m512a(ErrType.AUTH.getValue(), iInitAndCheck);
        this.f443h = true;
    }

    /* renamed from: a */
    void m515a(AuthListener authListener) {
        this.f442g = authListener;
    }

    /* renamed from: a */
    void m516a(CoreListener coreListener) {
        this.f441f = coreListener;
    }

    /* renamed from: b */
    protected String m517b() {
        return getDeviceId(this.f439d);
    }

    /* renamed from: b */
    protected void m518b(Context context, BaseLibrary.Params params) {
        Log.i(this.f436a, "require oaid");
        m514a(context, params);
    }

    /* renamed from: d */
    public String m519d() {
        return this.f440e;
    }

    native String getDeviceId(Context context);

    native int getOaIdWeightValue();

    native int getState();

    native int initAndCheck(Context context, String str, String str2, String str3, int i, String str4, String str5, boolean z, boolean z2, int i2, long j, boolean z3, String str6, int i3, String str7, String str8, String str9, String str10, int i4);

    native int release();

    native void reset(Context context);
}
