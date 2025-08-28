package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpInetConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.SocketInputBuffer;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.SocketOutputBuffer;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionInputBuffer;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionOutputBuffer;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.CoreConnectionPNames;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Asserts;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

@Deprecated
/* loaded from: classes2.dex */
public class SocketHttpClientConnection extends AbstractHttpClientConnection implements HttpInetConnection {
    private volatile boolean open;
    private volatile Socket socket = null;

    protected void assertNotOpen() {
        Asserts.check(!this.open, "Connection is already open");
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.AbstractHttpClientConnection
    protected void assertOpen() {
        Asserts.check(this.open, "Connection is not open");
    }

    protected SessionInputBuffer createSessionInputBuffer(Socket socket, int i, HttpParams httpParams) throws IOException {
        return new SocketInputBuffer(socket, i, httpParams);
    }

    protected SessionOutputBuffer createSessionOutputBuffer(Socket socket, int i, HttpParams httpParams) throws IOException {
        return new SocketOutputBuffer(socket, i, httpParams);
    }

    protected void bind(Socket socket, HttpParams httpParams) throws IOException {
        Args.notNull(socket, "Socket");
        Args.notNull(httpParams, "HTTP parameters");
        this.socket = socket;
        int intParameter = httpParams.getIntParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, -1);
        init(createSessionInputBuffer(socket, intParameter, httpParams), createSessionOutputBuffer(socket, intParameter, httpParams), httpParams);
        this.open = true;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public boolean isOpen() {
        return this.open;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Socket getSocket() {
        return this.socket;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpInetConnection
    public InetAddress getLocalAddress() {
        if (this.socket != null) {
            return this.socket.getLocalAddress();
        }
        return null;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpInetConnection
    public int getLocalPort() {
        if (this.socket != null) {
            return this.socket.getLocalPort();
        }
        return -1;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpInetConnection
    public InetAddress getRemoteAddress() {
        if (this.socket != null) {
            return this.socket.getInetAddress();
        }
        return null;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpInetConnection
    public int getRemotePort() {
        if (this.socket != null) {
            return this.socket.getPort();
        }
        return -1;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public void setSocketTimeout(int i) throws SocketException {
        assertOpen();
        if (this.socket != null) {
            try {
                this.socket.setSoTimeout(i);
            } catch (SocketException unused) {
            }
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public int getSocketTimeout() {
        if (this.socket != null) {
            try {
                return this.socket.getSoTimeout();
            } catch (SocketException unused) {
            }
        }
        return -1;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public void shutdown() throws IOException {
        this.open = false;
        Socket socket = this.socket;
        if (socket != null) {
            socket.close();
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.open) {
            this.open = false;
            Socket socket = this.socket;
            try {
                doFlush();
                try {
                    try {
                        socket.shutdownOutput();
                    } catch (IOException unused) {
                    }
                    socket.shutdownInput();
                } catch (IOException | UnsupportedOperationException unused2) {
                }
            } finally {
                socket.close();
            }
        }
    }

    private static void formatAddress(StringBuilder sb, SocketAddress socketAddress) {
        if (socketAddress instanceof InetSocketAddress) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
            sb.append(inetSocketAddress.getAddress() != null ? inetSocketAddress.getAddress().getHostAddress() : inetSocketAddress.getAddress()).append(':').append(inetSocketAddress.getPort());
        } else {
            sb.append(socketAddress);
        }
    }

    public String toString() {
        if (this.socket != null) {
            StringBuilder sb = new StringBuilder();
            SocketAddress remoteSocketAddress = this.socket.getRemoteSocketAddress();
            SocketAddress localSocketAddress = this.socket.getLocalSocketAddress();
            if (remoteSocketAddress != null && localSocketAddress != null) {
                formatAddress(sb, localSocketAddress);
                sb.append("<->");
                formatAddress(sb, remoteSocketAddress);
            }
            return sb.toString();
        }
        return super.toString();
    }
}
