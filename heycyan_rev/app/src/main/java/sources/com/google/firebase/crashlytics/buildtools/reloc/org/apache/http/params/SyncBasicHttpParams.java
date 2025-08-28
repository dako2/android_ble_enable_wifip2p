package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params;

@Deprecated
/* loaded from: classes2.dex */
public class SyncBasicHttpParams extends BasicHttpParams {
    private static final long serialVersionUID = 5387834869062660642L;

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.BasicHttpParams, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams
    public synchronized boolean removeParameter(String str) {
        return super.removeParameter(str);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.BasicHttpParams, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams
    public synchronized HttpParams setParameter(String str, Object obj) {
        return super.setParameter(str, obj);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.BasicHttpParams, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams
    public synchronized Object getParameter(String str) {
        return super.getParameter(str);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.BasicHttpParams
    public synchronized boolean isParameterSet(String str) {
        return super.isParameterSet(str);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.BasicHttpParams
    public synchronized boolean isParameterSetLocally(String str) {
        return super.isParameterSetLocally(str);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.BasicHttpParams
    public synchronized void setParameters(String[] strArr, Object obj) {
        super.setParameters(strArr, obj);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.BasicHttpParams
    public synchronized void clear() {
        super.clear();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.BasicHttpParams
    public synchronized Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
