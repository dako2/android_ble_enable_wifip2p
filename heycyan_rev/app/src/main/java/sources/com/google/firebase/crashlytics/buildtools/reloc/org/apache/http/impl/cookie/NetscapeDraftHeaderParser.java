package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HeaderElement;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ParseException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicHeaderElement;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.ParserCursor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.TokenParser;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.CharArrayBuffer;
import java.util.ArrayList;
import java.util.BitSet;

/* loaded from: classes2.dex */
public class NetscapeDraftHeaderParser {
    private static final char PARAM_DELIMITER = ';';
    private final TokenParser tokenParser = TokenParser.INSTANCE;
    public static final NetscapeDraftHeaderParser DEFAULT = new NetscapeDraftHeaderParser();
    private static final BitSet TOKEN_DELIMS = TokenParser.INIT_BITSET(61, 59);
    private static final BitSet VALUE_DELIMS = TokenParser.INIT_BITSET(59);

    public HeaderElement parseHeader(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) throws ParseException {
        Args.notNull(charArrayBuffer, "Char array buffer");
        Args.notNull(parserCursor, "Parser cursor");
        NameValuePair nameValuePair = parseNameValuePair(charArrayBuffer, parserCursor);
        ArrayList arrayList = new ArrayList();
        while (!parserCursor.atEnd()) {
            arrayList.add(parseNameValuePair(charArrayBuffer, parserCursor));
        }
        return new BasicHeaderElement(nameValuePair.getName(), nameValuePair.getValue(), (NameValuePair[]) arrayList.toArray(new NameValuePair[arrayList.size()]));
    }

    private NameValuePair parseNameValuePair(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        String token = this.tokenParser.parseToken(charArrayBuffer, parserCursor, TOKEN_DELIMS);
        if (parserCursor.atEnd()) {
            return new BasicNameValuePair(token, null);
        }
        char cCharAt = charArrayBuffer.charAt(parserCursor.getPos());
        parserCursor.updatePos(parserCursor.getPos() + 1);
        if (cCharAt != '=') {
            return new BasicNameValuePair(token, null);
        }
        String token2 = this.tokenParser.parseToken(charArrayBuffer, parserCursor, VALUE_DELIMS);
        if (!parserCursor.atEnd()) {
            parserCursor.updatePos(parserCursor.getPos() + 1);
        }
        return new BasicNameValuePair(token, token2);
    }
}
