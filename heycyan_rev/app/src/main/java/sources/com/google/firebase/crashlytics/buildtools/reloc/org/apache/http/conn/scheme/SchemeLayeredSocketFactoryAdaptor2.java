package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.scheme;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

@Deprecated
/* loaded from: classes2.dex */
class SchemeLayeredSocketFactoryAdaptor2 implements SchemeLayeredSocketFactory {
    private final LayeredSchemeSocketFactory factory;

    SchemeLayeredSocketFactoryAdaptor2(LayeredSchemeSocketFactory layeredSchemeSocketFactory) {
        this.factory = layeredSchemeSocketFactory;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.scheme.SchemeSocketFactory
    public Socket createSocket(HttpParams httpParams) throws IOException {
        return this.factory.createSocket(httpParams);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.scheme.SchemeSocketFactory
    public Socket connectSocket(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpParams httpParams) throws IOException {
        return this.factory.connectSocket(socket, inetSocketAddress, inetSocketAddress2, httpParams);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.scheme.SchemeSocketFactory
    public boolean isSecure(Socket socket) throws IllegalArgumentException {
        return this.factory.isSecure(socket);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.scheme.SchemeLayeredSocketFactory
    public Socket createLayeredSocket(Socket socket, String str, int i, HttpParams httpParams) throws IOException {
        return this.factory.createLayeredSocket(socket, str, i, true);
    }
}
