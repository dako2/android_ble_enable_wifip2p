package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.FormattedHeader;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HeaderElement;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ParseException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.CharArrayBuffer;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class BufferedHeader implements FormattedHeader, Cloneable, Serializable {
    private static final long serialVersionUID = -2768352615787625448L;
    private final CharArrayBuffer buffer;
    private final String name;
    private final int valuePos;

    public BufferedHeader(CharArrayBuffer charArrayBuffer) throws ParseException {
        Args.notNull(charArrayBuffer, "Char array buffer");
        int iIndexOf = charArrayBuffer.indexOf(58);
        if (iIndexOf == -1) {
            throw new ParseException("Invalid header: " + charArrayBuffer.toString());
        }
        String strSubstringTrimmed = charArrayBuffer.substringTrimmed(0, iIndexOf);
        if (strSubstringTrimmed.length() == 0) {
            throw new ParseException("Invalid header: " + charArrayBuffer.toString());
        }
        this.buffer = charArrayBuffer;
        this.name = strSubstringTrimmed;
        this.valuePos = iIndexOf + 1;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair
    public String getName() {
        return this.name;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair
    public String getValue() {
        CharArrayBuffer charArrayBuffer = this.buffer;
        return charArrayBuffer.substringTrimmed(this.valuePos, charArrayBuffer.length());
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.Header
    public HeaderElement[] getElements() throws ParseException {
        ParserCursor parserCursor = new ParserCursor(0, this.buffer.length());
        parserCursor.updatePos(this.valuePos);
        return BasicHeaderValueParser.INSTANCE.parseElements(this.buffer, parserCursor);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.FormattedHeader
    public int getValuePos() {
        return this.valuePos;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.FormattedHeader
    public CharArrayBuffer getBuffer() {
        return this.buffer;
    }

    public String toString() {
        return this.buffer.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
