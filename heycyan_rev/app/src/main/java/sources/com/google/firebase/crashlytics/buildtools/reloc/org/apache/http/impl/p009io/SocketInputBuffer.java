package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.EofSensor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.io.IOException;
import java.net.Socket;

@Deprecated
/* loaded from: classes2.dex */
public class SocketInputBuffer extends AbstractSessionInputBuffer implements EofSensor {
    private boolean eof;
    private final Socket socket;

    public SocketInputBuffer(Socket socket, int i, HttpParams httpParams) throws IOException {
        Args.notNull(socket, "Socket");
        this.socket = socket;
        this.eof = false;
        i = i < 0 ? socket.getReceiveBufferSize() : i;
        init(socket.getInputStream(), i < 1024 ? 1024 : i, httpParams);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.AbstractSessionInputBuffer
    protected int fillBuffer() throws IOException {
        int iFillBuffer = super.fillBuffer();
        this.eof = iFillBuffer == -1;
        return iFillBuffer;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionInputBuffer
    public boolean isDataAvailable(int i) throws IOException {
        boolean zHasBufferedData = hasBufferedData();
        if (zHasBufferedData) {
            return zHasBufferedData;
        }
        int soTimeout = this.socket.getSoTimeout();
        try {
            this.socket.setSoTimeout(i);
            fillBuffer();
            return hasBufferedData();
        } finally {
            this.socket.setSoTimeout(soTimeout);
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.EofSensor
    public boolean isEof() {
        return this.eof;
    }
}
