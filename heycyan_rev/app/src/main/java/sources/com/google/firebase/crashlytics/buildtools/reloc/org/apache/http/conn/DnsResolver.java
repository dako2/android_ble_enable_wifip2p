package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn;

import java.net.InetAddress;
import java.net.UnknownHostException;

/* loaded from: classes2.dex */
public interface DnsResolver {
    InetAddress[] resolve(String str) throws UnknownHostException;
}
