package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpMessage;
import java.io.IOException;

/* loaded from: classes2.dex */
public interface HttpMessageParser<T extends HttpMessage> {
    T parse() throws HttpException, IOException;
}
