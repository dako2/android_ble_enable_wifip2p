package com.google.firebase.crashlytics.buildtools.api.net.proxy;

import java.io.IOException;

/* loaded from: classes2.dex */
public interface ProxyFactory {
    ProxySettings create(ProtocolScheme protocolScheme) throws IOException;
}
