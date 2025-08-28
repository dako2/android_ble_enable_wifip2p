package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.execchain;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.EofSensorInputStream;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.EofSensorWatcher;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.HttpEntityWrapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

/* loaded from: classes2.dex */
class ResponseEntityProxy extends HttpEntityWrapper implements EofSensorWatcher {
    private final ConnectionHolder connHolder;

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.HttpEntityWrapper, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public boolean isRepeatable() {
        return false;
    }

    public static void enchance(HttpResponse httpResponse, ConnectionHolder connectionHolder) {
        HttpEntity entity = httpResponse.getEntity();
        if (entity == null || !entity.isStreaming() || connectionHolder == null) {
            return;
        }
        httpResponse.setEntity(new ResponseEntityProxy(entity, connectionHolder));
    }

    ResponseEntityProxy(HttpEntity httpEntity, ConnectionHolder connectionHolder) {
        super(httpEntity);
        this.connHolder = connectionHolder;
    }

    private void cleanup() throws IOException {
        ConnectionHolder connectionHolder = this.connHolder;
        if (connectionHolder != null) {
            connectionHolder.close();
        }
    }

    private void abortConnection() throws IOException {
        ConnectionHolder connectionHolder = this.connHolder;
        if (connectionHolder != null) {
            connectionHolder.abortConnection();
        }
    }

    public void releaseConnection() throws IOException {
        ConnectionHolder connectionHolder = this.connHolder;
        if (connectionHolder != null) {
            connectionHolder.releaseConnection();
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.HttpEntityWrapper, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public InputStream getContent() throws IOException {
        return new EofSensorInputStream(this.wrappedEntity.getContent(), this);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.HttpEntityWrapper, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    @Deprecated
    public void consumeContent() throws IOException {
        releaseConnection();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.HttpEntityWrapper, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IOException {
        try {
            if (outputStream != null) {
                try {
                    this.wrappedEntity.writeTo(outputStream);
                } catch (IOException e) {
                    abortConnection();
                    throw e;
                } catch (RuntimeException e2) {
                    abortConnection();
                    throw e2;
                }
            }
            releaseConnection();
        } finally {
            cleanup();
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.EofSensorWatcher
    public boolean eofDetected(InputStream inputStream) throws IOException {
        try {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    abortConnection();
                    throw e;
                } catch (RuntimeException e2) {
                    abortConnection();
                    throw e2;
                }
            }
            releaseConnection();
            cleanup();
            return false;
        } catch (Throwable th) {
            cleanup();
            throw th;
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.EofSensorWatcher
    public boolean streamClosed(InputStream inputStream) throws IOException {
        try {
            try {
                ConnectionHolder connectionHolder = this.connHolder;
                boolean z = (connectionHolder == null || connectionHolder.isReleased()) ? false : true;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (SocketException e) {
                        if (z) {
                            throw e;
                        }
                    }
                }
                releaseConnection();
                return false;
            } catch (IOException e2) {
                abortConnection();
                throw e2;
            } catch (RuntimeException e3) {
                abortConnection();
                throw e3;
            }
        } finally {
            cleanup();
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.EofSensorWatcher
    public boolean streamAbort(InputStream inputStream) throws IOException {
        cleanup();
        return false;
    }

    public String toString() {
        return "ResponseEntityProxy{" + this.wrappedEntity + '}';
    }
}
