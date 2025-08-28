package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.scheme;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import java.io.IOException;
import java.net.Socket;

@Deprecated
/* loaded from: classes2.dex */
public interface SchemeLayeredSocketFactory extends SchemeSocketFactory {
    Socket createLayeredSocket(Socket socket, String str, int i, HttpParams httpParams) throws IOException;
}
