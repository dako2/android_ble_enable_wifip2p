package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.scheme;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import java.io.IOException;
import java.net.Socket;

@Deprecated
/* loaded from: classes2.dex */
class SchemeLayeredSocketFactoryAdaptor extends SchemeSocketFactoryAdaptor implements SchemeLayeredSocketFactory {
    private final LayeredSocketFactory factory;

    SchemeLayeredSocketFactoryAdaptor(LayeredSocketFactory layeredSocketFactory) {
        super(layeredSocketFactory);
        this.factory = layeredSocketFactory;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.scheme.SchemeLayeredSocketFactory
    public Socket createLayeredSocket(Socket socket, String str, int i, HttpParams httpParams) throws IOException {
        return this.factory.createSocket(socket, str, i, true);
    }
}
