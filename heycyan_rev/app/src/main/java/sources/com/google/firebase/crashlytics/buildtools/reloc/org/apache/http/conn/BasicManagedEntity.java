package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.HttpEntityWrapper;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

@Deprecated
/* loaded from: classes2.dex */
public class BasicManagedEntity extends HttpEntityWrapper implements ConnectionReleaseTrigger, EofSensorWatcher {
    protected final boolean attemptReuse;
    protected ManagedClientConnection managedConn;

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.HttpEntityWrapper, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public boolean isRepeatable() {
        return false;
    }

    public BasicManagedEntity(HttpEntity httpEntity, ManagedClientConnection managedClientConnection, boolean z) {
        super(httpEntity);
        Args.notNull(managedClientConnection, "Connection");
        this.managedConn = managedClientConnection;
        this.attemptReuse = z;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.HttpEntityWrapper, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public InputStream getContent() throws IOException {
        return new EofSensorInputStream(this.wrappedEntity.getContent(), this);
    }

    private void ensureConsumed() throws IOException {
        ManagedClientConnection managedClientConnection = this.managedConn;
        if (managedClientConnection == null) {
            return;
        }
        try {
            if (this.attemptReuse) {
                EntityUtils.consume(this.wrappedEntity);
                this.managedConn.markReusable();
            } else {
                managedClientConnection.unmarkReusable();
            }
        } finally {
            releaseManagedConnection();
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.HttpEntityWrapper, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    @Deprecated
    public void consumeContent() throws IOException {
        ensureConsumed();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.HttpEntityWrapper, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IOException {
        super.writeTo(outputStream);
        ensureConsumed();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ConnectionReleaseTrigger
    public void releaseConnection() throws IOException {
        ensureConsumed();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ConnectionReleaseTrigger
    public void abortConnection() throws IOException {
        ManagedClientConnection managedClientConnection = this.managedConn;
        if (managedClientConnection != null) {
            try {
                managedClientConnection.abortConnection();
            } finally {
                this.managedConn = null;
            }
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.EofSensorWatcher
    public boolean eofDetected(InputStream inputStream) throws IOException {
        try {
            ManagedClientConnection managedClientConnection = this.managedConn;
            if (managedClientConnection != null) {
                if (this.attemptReuse) {
                    inputStream.close();
                    this.managedConn.markReusable();
                } else {
                    managedClientConnection.unmarkReusable();
                }
            }
            releaseManagedConnection();
            return false;
        } catch (Throwable th) {
            releaseManagedConnection();
            throw th;
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.EofSensorWatcher
    public boolean streamClosed(InputStream inputStream) throws IOException {
        try {
            ManagedClientConnection managedClientConnection = this.managedConn;
            if (managedClientConnection != null) {
                if (this.attemptReuse) {
                    boolean zIsOpen = managedClientConnection.isOpen();
                    try {
                        inputStream.close();
                        this.managedConn.markReusable();
                    } catch (SocketException e) {
                        if (zIsOpen) {
                            throw e;
                        }
                    }
                } else {
                    managedClientConnection.unmarkReusable();
                }
            }
            releaseManagedConnection();
            return false;
        } catch (Throwable th) {
            releaseManagedConnection();
            throw th;
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.EofSensorWatcher
    public boolean streamAbort(InputStream inputStream) throws IOException {
        ManagedClientConnection managedClientConnection = this.managedConn;
        if (managedClientConnection == null) {
            return false;
        }
        managedClientConnection.abortConnection();
        return false;
    }

    protected void releaseManagedConnection() throws IOException {
        ManagedClientConnection managedClientConnection = this.managedConn;
        if (managedClientConnection != null) {
            try {
                managedClientConnection.releaseConnection();
            } finally {
                this.managedConn = null;
            }
        }
    }
}
