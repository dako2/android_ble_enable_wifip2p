package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.LineFormatter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionOutputBuffer;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import java.io.IOException;

@Deprecated
/* loaded from: classes2.dex */
public class HttpResponseWriter extends AbstractMessageWriter<HttpResponse> {
    public HttpResponseWriter(SessionOutputBuffer sessionOutputBuffer, LineFormatter lineFormatter, HttpParams httpParams) {
        super(sessionOutputBuffer, lineFormatter, httpParams);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.AbstractMessageWriter
    public void writeHeadLine(HttpResponse httpResponse) throws IOException {
        this.lineFormatter.formatStatusLine(this.lineBuf, httpResponse.getStatusLine());
        this.sessionBuffer.writeLine(this.lineBuf);
    }
}
