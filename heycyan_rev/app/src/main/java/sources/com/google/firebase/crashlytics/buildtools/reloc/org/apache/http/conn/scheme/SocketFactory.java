package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.scheme;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

@Deprecated
/* loaded from: classes2.dex */
public interface SocketFactory {
    Socket connectSocket(Socket socket, String str, int i, InetAddress inetAddress, int i2, HttpParams httpParams) throws IOException;

    Socket createSocket() throws IOException;

    boolean isSecure(Socket socket) throws IllegalArgumentException;
}
