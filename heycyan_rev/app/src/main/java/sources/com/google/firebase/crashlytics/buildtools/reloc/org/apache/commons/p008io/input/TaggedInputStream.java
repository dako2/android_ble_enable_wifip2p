package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.input;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.TaggedIOException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.UUID;

/* loaded from: classes2.dex */
public class TaggedInputStream extends ProxyInputStream {
    private final Serializable tag;

    public TaggedInputStream(InputStream inputStream) {
        super(inputStream);
        this.tag = UUID.randomUUID();
    }

    public boolean isCauseOf(Throwable th) {
        return TaggedIOException.isTaggedWith(th, this.tag);
    }

    public void throwIfCauseOf(Throwable th) throws IOException {
        TaggedIOException.throwCauseIfTaggedWith(th, this.tag);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.input.ProxyInputStream
    protected void handleIOException(IOException iOException) throws IOException {
        throw new TaggedIOException(iOException, this.tag);
    }
}
