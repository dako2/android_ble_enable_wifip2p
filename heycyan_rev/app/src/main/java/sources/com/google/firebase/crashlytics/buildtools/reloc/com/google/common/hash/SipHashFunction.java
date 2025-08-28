package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.hash;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Preconditions;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.errorprone.annotations.Immutable;
import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.io.Serializable;
import java.nio.ByteBuffer;

@Immutable
/* loaded from: classes2.dex */
final class SipHashFunction extends AbstractHashFunction implements Serializable {
    static final HashFunction SIP_HASH_24 = new SipHashFunction(2, 4, 506097522914230528L, 1084818905618843912L);
    private static final long serialVersionUID = 0;

    /* renamed from: c */
    private final int f320c;

    /* renamed from: d */
    private final int f321d;

    /* renamed from: k0 */
    private final long f322k0;

    /* renamed from: k1 */
    private final long f323k1;

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.hash.HashFunction
    public int bits() {
        return 64;
    }

    SipHashFunction(int i, int i2, long j, long j2) {
        Preconditions.checkArgument(i > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", i);
        Preconditions.checkArgument(i2 > 0, "The number of SipRound iterations (d=%s) during Finalization must be positive.", i2);
        this.f320c = i;
        this.f321d = i2;
        this.f322k0 = j;
        this.f323k1 = j2;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new SipHasher(this.f320c, this.f321d, this.f322k0, this.f323k1);
    }

    public String toString() {
        return "Hashing.sipHash" + this.f320c + "" + this.f321d + "(" + this.f322k0 + ", " + this.f323k1 + ")";
    }

    public boolean equals(@NullableDecl Object obj) {
        if (!(obj instanceof SipHashFunction)) {
            return false;
        }
        SipHashFunction sipHashFunction = (SipHashFunction) obj;
        return this.f320c == sipHashFunction.f320c && this.f321d == sipHashFunction.f321d && this.f322k0 == sipHashFunction.f322k0 && this.f323k1 == sipHashFunction.f323k1;
    }

    public int hashCode() {
        return (int) ((((getClass().hashCode() ^ this.f320c) ^ this.f321d) ^ this.f322k0) ^ this.f323k1);
    }

    private static final class SipHasher extends AbstractStreamingHasher {
        private static final int CHUNK_SIZE = 8;

        /* renamed from: b */
        private long f324b;

        /* renamed from: c */
        private final int f325c;

        /* renamed from: d */
        private final int f326d;
        private long finalM;

        /* renamed from: v0 */
        private long f327v0;

        /* renamed from: v1 */
        private long f328v1;

        /* renamed from: v2 */
        private long f329v2;

        /* renamed from: v3 */
        private long f330v3;

        SipHasher(int i, int i2, long j, long j2) {
            super(8);
            this.f324b = 0L;
            this.finalM = 0L;
            this.f325c = i;
            this.f326d = i2;
            this.f327v0 = 8317987319222330741L ^ j;
            this.f328v1 = 7237128888997146477L ^ j2;
            this.f329v2 = 7816392313619706465L ^ j;
            this.f330v3 = 8387220255154660723L ^ j2;
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.hash.AbstractStreamingHasher
        protected void process(ByteBuffer byteBuffer) {
            this.f324b += 8;
            processM(byteBuffer.getLong());
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.hash.AbstractStreamingHasher
        protected void processRemaining(ByteBuffer byteBuffer) {
            this.f324b += byteBuffer.remaining();
            int i = 0;
            while (byteBuffer.hasRemaining()) {
                this.finalM ^= (byteBuffer.get() & 255) << i;
                i += 8;
            }
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.hash.AbstractStreamingHasher
        public HashCode makeHash() {
            long j = this.finalM ^ (this.f324b << 56);
            this.finalM = j;
            processM(j);
            this.f329v2 ^= 255;
            sipRound(this.f326d);
            return HashCode.fromLong(((this.f327v0 ^ this.f328v1) ^ this.f329v2) ^ this.f330v3);
        }

        private void processM(long j) {
            this.f330v3 ^= j;
            sipRound(this.f325c);
            this.f327v0 = j ^ this.f327v0;
        }

        private void sipRound(int i) {
            for (int i2 = 0; i2 < i; i2++) {
                long j = this.f327v0;
                long j2 = this.f328v1;
                this.f327v0 = j + j2;
                this.f329v2 += this.f330v3;
                this.f328v1 = Long.rotateLeft(j2, 13);
                long jRotateLeft = Long.rotateLeft(this.f330v3, 16);
                long j3 = this.f328v1;
                long j4 = this.f327v0;
                this.f328v1 = j3 ^ j4;
                this.f330v3 = jRotateLeft ^ this.f329v2;
                long jRotateLeft2 = Long.rotateLeft(j4, 32);
                long j5 = this.f329v2;
                long j6 = this.f328v1;
                this.f329v2 = j5 + j6;
                this.f327v0 = jRotateLeft2 + this.f330v3;
                this.f328v1 = Long.rotateLeft(j6, 17);
                long jRotateLeft3 = Long.rotateLeft(this.f330v3, 21);
                long j7 = this.f328v1;
                long j8 = this.f329v2;
                this.f328v1 = j7 ^ j8;
                this.f330v3 = jRotateLeft3 ^ this.f327v0;
                this.f329v2 = Long.rotateLeft(j8, 32);
            }
        }
    }
}
