package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.entity;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpMessage;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ProtocolException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.ContentLengthStrategy;

/* loaded from: classes2.dex */
public class DisallowIdentityContentLengthStrategy implements ContentLengthStrategy {
    public static final DisallowIdentityContentLengthStrategy INSTANCE = new DisallowIdentityContentLengthStrategy(new LaxContentLengthStrategy(0));
    private final ContentLengthStrategy contentLengthStrategy;

    public DisallowIdentityContentLengthStrategy(ContentLengthStrategy contentLengthStrategy) {
        this.contentLengthStrategy = contentLengthStrategy;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.ContentLengthStrategy
    public long determineLength(HttpMessage httpMessage) throws HttpException {
        long jDetermineLength = this.contentLengthStrategy.determineLength(httpMessage);
        if (jDetermineLength != -1) {
            return jDetermineLength;
        }
        throw new ProtocolException("Identity transfer encoding cannot be used");
    }
}
