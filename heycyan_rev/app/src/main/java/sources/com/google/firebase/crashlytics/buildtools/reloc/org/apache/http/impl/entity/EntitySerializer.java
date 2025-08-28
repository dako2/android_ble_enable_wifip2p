package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.entity;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpMessage;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.ContentLengthStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.ChunkedOutputStream;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.ContentLengthOutputStream;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.IdentityOutputStream;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionOutputBuffer;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.io.IOException;
import java.io.OutputStream;

@Deprecated
/* loaded from: classes2.dex */
public class EntitySerializer {
    private final ContentLengthStrategy lenStrategy;

    public EntitySerializer(ContentLengthStrategy contentLengthStrategy) {
        this.lenStrategy = (ContentLengthStrategy) Args.notNull(contentLengthStrategy, "Content length strategy");
    }

    protected OutputStream doSerialize(SessionOutputBuffer sessionOutputBuffer, HttpMessage httpMessage) throws HttpException, IOException {
        long jDetermineLength = this.lenStrategy.determineLength(httpMessage);
        if (jDetermineLength == -2) {
            return new ChunkedOutputStream(sessionOutputBuffer);
        }
        if (jDetermineLength == -1) {
            return new IdentityOutputStream(sessionOutputBuffer);
        }
        return new ContentLengthOutputStream(sessionOutputBuffer, jDetermineLength);
    }

    public void serialize(SessionOutputBuffer sessionOutputBuffer, HttpMessage httpMessage, HttpEntity httpEntity) throws HttpException, IOException {
        Args.notNull(sessionOutputBuffer, "Session output buffer");
        Args.notNull(httpMessage, "HTTP message");
        Args.notNull(httpEntity, "HTTP entity");
        OutputStream outputStreamDoSerialize = doSerialize(sessionOutputBuffer, httpMessage);
        httpEntity.writeTo(outputStreamDoSerialize);
        outputStreamDoSerialize.close();
    }
}
