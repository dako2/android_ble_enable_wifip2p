package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.LogFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpMessage;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponseFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NoHttpResponseException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ParseException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ProtocolException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.params.ConnConnectionPNames;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.AbstractMessageParser;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.LineParser;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.ParserCursor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionInputBuffer;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.CharArrayBuffer;
import java.io.IOException;

@Deprecated
/* loaded from: classes2.dex */
public class DefaultResponseParser extends AbstractMessageParser<HttpMessage> {
    private final CharArrayBuffer lineBuf;
    private final Log log;
    private final int maxGarbageLines;
    private final HttpResponseFactory responseFactory;

    public DefaultResponseParser(SessionInputBuffer sessionInputBuffer, LineParser lineParser, HttpResponseFactory httpResponseFactory, HttpParams httpParams) {
        super(sessionInputBuffer, lineParser, httpParams);
        this.log = LogFactory.getLog(getClass());
        Args.notNull(httpResponseFactory, "Response factory");
        this.responseFactory = httpResponseFactory;
        this.lineBuf = new CharArrayBuffer(128);
        this.maxGarbageLines = getMaxGarbageLines(httpParams);
    }

    protected int getMaxGarbageLines(HttpParams httpParams) {
        return httpParams.getIntParameter(ConnConnectionPNames.MAX_STATUS_LINE_GARBAGE, Integer.MAX_VALUE);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.AbstractMessageParser
    protected HttpMessage parseHead(SessionInputBuffer sessionInputBuffer) throws ParseException, HttpException, IOException {
        int i = 0;
        while (true) {
            this.lineBuf.clear();
            int line = sessionInputBuffer.readLine(this.lineBuf);
            if (line == -1 && i == 0) {
                throw new NoHttpResponseException("The target server failed to respond");
            }
            ParserCursor parserCursor = new ParserCursor(0, this.lineBuf.length());
            if (!this.lineParser.hasProtocolVersion(this.lineBuf, parserCursor)) {
                if (line == -1 || i >= this.maxGarbageLines) {
                    break;
                }
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Garbage in response: " + this.lineBuf.toString());
                }
                i++;
            } else {
                return this.responseFactory.newHttpResponse(this.lineParser.parseStatusLine(this.lineBuf, parserCursor), null);
            }
        }
        throw new ProtocolException("The server failed to respond with a valid HTTP response");
    }
}
