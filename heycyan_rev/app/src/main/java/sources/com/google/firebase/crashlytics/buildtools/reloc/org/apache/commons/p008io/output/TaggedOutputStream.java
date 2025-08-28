package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.output;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.TaggedIOException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.UUID;

/* loaded from: classes2.dex */
public class TaggedOutputStream extends ProxyOutputStream {
    private final Serializable tag;

    public TaggedOutputStream(OutputStream outputStream) {
        super(outputStream);
        this.tag = UUID.randomUUID();
    }

    public boolean isCauseOf(Exception exc) {
        return TaggedIOException.isTaggedWith(exc, this.tag);
    }

    public void throwIfCauseOf(Exception exc) throws IOException {
        TaggedIOException.throwCauseIfTaggedWith(exc, this.tag);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.output.ProxyOutputStream
    protected void handleIOException(IOException iOException) throws IOException {
        throw new TaggedIOException(iOException, this.tag);
    }
}
