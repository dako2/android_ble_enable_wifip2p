package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.scheme;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

@Deprecated
/* loaded from: classes2.dex */
public interface SchemeSocketFactory {
    Socket connectSocket(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpParams httpParams) throws IOException;

    Socket createSocket(HttpParams httpParams) throws IOException;

    boolean isSecure(Socket socket) throws IllegalArgumentException;
}
