package com.google.firebase.crashlytics.buildtools.utils.p011io;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
public class ByteReader implements Closeable {
    private static final int BUFFER_SIZE = 64;
    private static final int INT_WIDTH = 4;
    private static final int LONG_WIDTH = 8;
    private static final int SHORT_WIDTH = 2;
    private final ByteBuffer _buffer;
    private final byte[] _bytes;
    private final SeekableInputStream _source;

    public ByteReader(SeekableInputStream seekableInputStream) {
        byte[] bArr = new byte[64];
        this._bytes = bArr;
        this._buffer = ByteBuffer.allocate(bArr.length);
        this._source = seekableInputStream;
    }

    public void seek(long j) throws IOException {
        this._source.seek(j);
    }

    public long getCurrentOffset() throws IOException {
        return this._source.getCurrentOffset();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this._source.close();
    }

    public byte readByte() throws IOException {
        int i = this._source.read();
        if (i >= 0) {
            return (byte) (i & 255);
        }
        throw new EOFException();
    }

    public byte[] readBytes(int i) throws IOException {
        byte[] bArr = new byte[i];
        this._source.readFully(bArr, 0, i);
        return bArr;
    }

    public short readShort(int i) throws IOException {
        ByteBuffer byteBuffer = this._buffer;
        byteBuffer.put(readNumber(this._bytes, i, 2, byteBuffer.order()));
        short s = this._buffer.getShort();
        return s;
    }

    public int readInt(int i) throws IOException {
        ByteBuffer byteBuffer = this._buffer;
        byteBuffer.put(readNumber(this._bytes, i, 4, byteBuffer.order()));
        int i2 = this._buffer.getInt();
        return i2;
    }

    public long readLong(int i) throws IOException {
        ByteBuffer byteBuffer = this._buffer;
        byteBuffer.put(readNumber(this._bytes, i, 8, byteBuffer.order()));
        long j = this._buffer.getLong();
        return j;
    }

    public String readNullTerminatedString(Charset charset) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int i = this._source.read();
            if (i == 0) {
                return new String(byteArrayOutputStream.toByteArray(), charset);
            }
            if (i < 0) {
                throw new EOFException();
            }
            byteArrayOutputStream.write(i);
        }
    }

    public int readULEB128() throws IOException {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte b = readByte();
            i |= (b & Byte.MAX_VALUE) << i2;
            if ((b & 128) == 0) {
                return i;
            }
            i2 += 7;
        }
    }

    public int readSLEB128() throws IOException {
        byte b;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        do {
            b = readByte();
            i++;
            i2 |= (b & Byte.MAX_VALUE) << i3;
            i3 += 7;
        } while ((b & 128) != 0);
        return (i3 >= i * 8 || (b & 64) == 0) ? i2 : i2 | (-(1 << i3));
    }

    public void setByteOrder(ByteOrder byteOrder) {
        this._buffer.order(byteOrder);
    }

    public ByteOrder getByteOrder() {
        return this._buffer.order();
    }

    private byte[] readNumber(byte[] bArr, int i, int i2, ByteOrder byteOrder) throws IOException {
        if (i > i2) {
            throw new IllegalArgumentException(String.format("Requested number of bytes (%d) was greater than available bytes (%d).", Integer.valueOf(i), Integer.valueOf(i2)));
        }
        this._source.readFully(bArr, 0, i);
        return padBytes(bArr, i, i2, byteOrder);
    }

    private byte[] padBytes(byte[] bArr, int i, int i2, ByteOrder byteOrder) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, byteOrder == ByteOrder.BIG_ENDIAN ? i2 - i : 0, i);
        return bArr2;
    }
}
