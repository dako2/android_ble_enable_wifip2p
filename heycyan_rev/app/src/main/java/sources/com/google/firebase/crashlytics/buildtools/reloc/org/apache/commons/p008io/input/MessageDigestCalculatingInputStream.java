package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.input;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.digest.MessageDigestAlgorithms;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.input.ObservableInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes2.dex */
public class MessageDigestCalculatingInputStream extends ObservableInputStream {
    private final MessageDigest messageDigest;

    public static class MessageDigestMaintainingObserver extends ObservableInputStream.Observer {

        /* renamed from: md */
        private final MessageDigest f361md;

        public MessageDigestMaintainingObserver(MessageDigest messageDigest) {
            this.f361md = messageDigest;
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.input.ObservableInputStream.Observer
        void data(int i) throws IOException {
            this.f361md.update((byte) i);
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.input.ObservableInputStream.Observer
        void data(byte[] bArr, int i, int i2) throws IOException {
            this.f361md.update(bArr, i, i2);
        }
    }

    public MessageDigestCalculatingInputStream(InputStream inputStream, MessageDigest messageDigest) {
        super(inputStream);
        this.messageDigest = messageDigest;
        add(new MessageDigestMaintainingObserver(messageDigest));
    }

    public MessageDigestCalculatingInputStream(InputStream inputStream, String str) throws NoSuchAlgorithmException {
        this(inputStream, MessageDigest.getInstance(str));
    }

    public MessageDigestCalculatingInputStream(InputStream inputStream) throws NoSuchAlgorithmException {
        this(inputStream, MessageDigest.getInstance(MessageDigestAlgorithms.MD5));
    }

    public MessageDigest getMessageDigest() {
        return this.messageDigest;
    }
}
