package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpMessage;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponseFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NoHttpResponseException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ParseException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.LineParser;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.ParserCursor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionInputBuffer;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.CharArrayBuffer;
import java.io.IOException;

@Deprecated
/* loaded from: classes2.dex */
public class HttpResponseParser extends AbstractMessageParser<HttpMessage> {
    private final CharArrayBuffer lineBuf;
    private final HttpResponseFactory responseFactory;

    public HttpResponseParser(SessionInputBuffer sessionInputBuffer, LineParser lineParser, HttpResponseFactory httpResponseFactory, HttpParams httpParams) {
        super(sessionInputBuffer, lineParser, httpParams);
        this.responseFactory = (HttpResponseFactory) Args.notNull(httpResponseFactory, "Response factory");
        this.lineBuf = new CharArrayBuffer(128);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.AbstractMessageParser
    protected HttpMessage parseHead(SessionInputBuffer sessionInputBuffer) throws ParseException, HttpException, IOException {
        this.lineBuf.clear();
        if (sessionInputBuffer.readLine(this.lineBuf) == -1) {
            throw new NoHttpResponseException("The target server failed to respond");
        }
        return this.responseFactory.newHttpResponse(this.lineParser.parseStatusLine(this.lineBuf, new ParserCursor(0, this.lineBuf.length())), null);
    }
}
