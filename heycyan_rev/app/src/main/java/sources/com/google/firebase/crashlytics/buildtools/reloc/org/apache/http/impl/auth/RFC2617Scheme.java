package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.auth;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.Consts;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HeaderElement;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ParseException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.ChallengeState;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.MalformedChallengeException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.params.AuthPNames;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicHeaderValueParser;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.ParserCursor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.CharArrayBuffer;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.CharsetUtils;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class RFC2617Scheme extends AuthSchemeBase implements Serializable {
    private static final long serialVersionUID = -2845454858205884623L;
    private transient Charset credentialsCharset;
    private final Map<String, String> params;

    private void readObjectNoData() throws ObjectStreamException {
    }

    @Deprecated
    public RFC2617Scheme(ChallengeState challengeState) {
        super(challengeState);
        this.params = new HashMap();
        this.credentialsCharset = Consts.ASCII;
    }

    public RFC2617Scheme(Charset charset) {
        this.params = new HashMap();
        this.credentialsCharset = charset == null ? Consts.ASCII : charset;
    }

    public RFC2617Scheme() {
        this(Consts.ASCII);
    }

    public Charset getCredentialsCharset() {
        Charset charset = this.credentialsCharset;
        return charset != null ? charset : Consts.ASCII;
    }

    String getCredentialsCharset(HttpRequest httpRequest) {
        String str = (String) httpRequest.getParams().getParameter(AuthPNames.CREDENTIAL_CHARSET);
        return str == null ? getCredentialsCharset().name() : str;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.auth.AuthSchemeBase
    protected void parseChallenge(CharArrayBuffer charArrayBuffer, int i, int i2) throws ParseException, MalformedChallengeException {
        HeaderElement[] elements = BasicHeaderValueParser.INSTANCE.parseElements(charArrayBuffer, new ParserCursor(i, charArrayBuffer.length()));
        this.params.clear();
        for (HeaderElement headerElement : elements) {
            this.params.put(headerElement.getName().toLowerCase(Locale.ROOT), headerElement.getValue());
        }
    }

    protected Map<String, String> getParameters() {
        return this.params;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthScheme
    public String getParameter(String str) {
        if (str == null) {
            return null;
        }
        return this.params.get(str.toLowerCase(Locale.ROOT));
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthScheme
    public String getRealm() {
        return getParameter("realm");
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeUTF(this.credentialsCharset.name());
        objectOutputStream.writeObject(this.challengeState);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        Charset charset = CharsetUtils.get(objectInputStream.readUTF());
        this.credentialsCharset = charset;
        if (charset == null) {
            this.credentialsCharset = Consts.ASCII;
        }
        this.challengeState = (ChallengeState) objectInputStream.readObject();
    }
}
