package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.p006io;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Preconditions;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;

/* loaded from: classes2.dex */
final class ReaderInputStream extends InputStream {
    private ByteBuffer byteBuffer;
    private CharBuffer charBuffer;
    private boolean doneFlushing;
    private boolean draining;
    private final CharsetEncoder encoder;
    private boolean endOfInput;
    private final Reader reader;
    private final byte[] singleByte;

    ReaderInputStream(Reader reader, Charset charset, int i) {
        this(reader, charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE), i);
    }

    ReaderInputStream(Reader reader, CharsetEncoder charsetEncoder, int i) {
        this.singleByte = new byte[1];
        this.reader = (Reader) Preconditions.checkNotNull(reader);
        this.encoder = (CharsetEncoder) Preconditions.checkNotNull(charsetEncoder);
        Preconditions.checkArgument(i > 0, "bufferSize must be positive: %s", i);
        charsetEncoder.reset();
        CharBuffer charBufferAllocate = CharBuffer.allocate(i);
        this.charBuffer = charBufferAllocate;
        charBufferAllocate.flip();
        this.byteBuffer = ByteBuffer.allocate(i);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.reader.close();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.singleByte) == 1) {
            return UnsignedBytes.toInt(this.singleByte[0]);
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0029, code lost:
    
        if (r2 <= 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002c, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:?, code lost:
    
        return r2;
     */
    @Override // java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int read(byte[] bArr, int i, int i2) throws IOException {
        CoderResult coderResultEncode;
        Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
        if (i2 == 0) {
            return 0;
        }
        boolean z = this.endOfInput;
        int iDrain = 0;
        while (true) {
            if (this.draining) {
                iDrain += drain(bArr, i + iDrain, i2 - iDrain);
                if (iDrain == i2 || this.doneFlushing) {
                    break;
                }
                this.draining = false;
                this.byteBuffer.clear();
            }
            while (true) {
                if (this.doneFlushing) {
                    coderResultEncode = CoderResult.UNDERFLOW;
                } else if (z) {
                    coderResultEncode = this.encoder.flush(this.byteBuffer);
                } else {
                    coderResultEncode = this.encoder.encode(this.charBuffer, this.byteBuffer, this.endOfInput);
                }
                if (coderResultEncode.isOverflow()) {
                    startDraining(true);
                    break;
                }
                if (coderResultEncode.isUnderflow()) {
                    if (z) {
                        this.doneFlushing = true;
                        startDraining(false);
                        break;
                    }
                    if (this.endOfInput) {
                        z = true;
                    } else {
                        readMoreChars();
                    }
                } else if (coderResultEncode.isError()) {
                    coderResultEncode.throwException();
                    return 0;
                }
            }
        }
    }

    private static CharBuffer grow(CharBuffer charBuffer) {
        CharBuffer charBufferWrap = CharBuffer.wrap(Arrays.copyOf(charBuffer.array(), charBuffer.capacity() * 2));
        charBufferWrap.position(charBuffer.position());
        charBufferWrap.limit(charBuffer.limit());
        return charBufferWrap;
    }

    private void readMoreChars() throws IOException {
        if (availableCapacity(this.charBuffer) == 0) {
            if (this.charBuffer.position() > 0) {
                this.charBuffer.compact().flip();
            } else {
                this.charBuffer = grow(this.charBuffer);
            }
        }
        int iLimit = this.charBuffer.limit();
        int i = this.reader.read(this.charBuffer.array(), iLimit, availableCapacity(this.charBuffer));
        if (i == -1) {
            this.endOfInput = true;
        } else {
            this.charBuffer.limit(iLimit + i);
        }
    }

    private static int availableCapacity(Buffer buffer) {
        return buffer.capacity() - buffer.limit();
    }

    private void startDraining(boolean z) {
        this.byteBuffer.flip();
        if (z && this.byteBuffer.remaining() == 0) {
            this.byteBuffer = ByteBuffer.allocate(this.byteBuffer.capacity() * 2);
        } else {
            this.draining = true;
        }
    }

    private int drain(byte[] bArr, int i, int i2) {
        int iMin = Math.min(i2, this.byteBuffer.remaining());
        this.byteBuffer.get(bArr, i, iMin);
        return iMin;
    }
}
