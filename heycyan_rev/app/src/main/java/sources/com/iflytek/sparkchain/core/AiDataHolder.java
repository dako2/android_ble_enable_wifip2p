package com.iflytek.sparkchain.core;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.iflytek.sparkchain.core.AbstractC2195a;
import java.nio.ByteBuffer;
import java.security.InvalidParameterException;

/* loaded from: classes2.dex */
public abstract class AiDataHolder<T, O extends AbstractC2195a> {
    protected ByteBuffer buffer;
    protected int handle;
    private final String key;
    protected O object;
    protected EnumC2198b from = EnumC2198b.MEM;
    private AiStatus status = AiStatus.BEGIN;
    private boolean updated = false;
    protected boolean isData = false;

    public AiDataHolder(O o, String str) {
        this.key = str;
        this.object = o;
        o.ref(this);
        this.handle = AiBuilder.getAtomicHandle();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private T response() {
        return this;
    }

    private void sync(int i) {
        if (this.isData) {
            sync(this.handle, NotificationCompat.CATEGORY_STATUS, Integer.valueOf(this.status.getValue()));
        }
        paramSync(this.handle);
        if (this.buffer != null) {
            int value = type().getValue();
            Log.i("DataHolder", "type=" + value);
            AiHelper.getInst().newDirectBuffer(i, this.key, this.buffer, this.from.getValue(), value, this.status.getValue(), this.buffer.capacity());
        }
    }

    public T begin() {
        this.status = (AiStatus) update(this.status, AiStatus.BEGIN);
        return response();
    }

    public T cont() {
        this.status = (AiStatus) update(this.status, AiStatus.CONTINUE);
        return response();
    }

    public T data(String str) {
        return data(str.getBytes());
    }

    public T data(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2 = this.buffer;
        if (byteBuffer2 != null) {
            byteBuffer2.clear();
        }
        this.buffer = byteBuffer;
        this.isData = true;
        return response();
    }

    public T data(byte[] bArr) {
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(bArr.length);
        byteBufferAllocateDirect.put(bArr);
        return data(byteBufferAllocateDirect);
    }

    public T end() {
        this.status = (AiStatus) update(this.status, AiStatus.END);
        return response();
    }

    public T file(String str) {
        this.from = EnumC2198b.FILE;
        return data(str);
    }

    public T file(ByteBuffer byteBuffer) {
        this.from = EnumC2198b.FILE;
        return data(byteBuffer);
    }

    public T file(byte[] bArr) {
        this.from = EnumC2198b.FILE;
        return data(bArr);
    }

    protected void finalize() throws Throwable {
        try {
            super.finalize();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        AiHelper.getInst().delBuilder(this.handle);
    }

    public T once() {
        this.status = (AiStatus) update(this.status, AiStatus.ONCE);
        return response();
    }

    protected abstract void paramSync(int i);

    public T status(AiStatus aiStatus) {
        this.status = (AiStatus) update(this.status, aiStatus);
        return response();
    }

    protected void sync(int i, String str, Boolean bool) {
        AiHelper.getInst().newBoolean(i, str, bool.booleanValue());
    }

    protected void sync(int i, String str, Double d) {
        AiHelper.getInst().newDouble(i, str, d.doubleValue());
    }

    protected void sync(int i, String str, Integer num) {
        AiHelper.getInst().newInteger(i, str, num.intValue());
    }

    protected void sync(int i, String str, byte[] bArr) {
        AiHelper.getInst().newBuffer(i, str, bArr, EnumC2198b.MEM.getValue(), EnumC2199c.OTHER.getValue(), AiStatus.ONCE.getValue());
    }

    protected void syncCtrl(int i) {
        sync(i);
        AiHelper.getInst().newBuilder(i, this.key, this.handle);
    }

    protected void syncDesc(int i) {
        sync(i);
        AiHelper.getInst().newDesc(i, this.key, this.handle);
    }

    protected abstract EnumC2199c type();

    protected <P> P update(P p, P p2) {
        if (p != p2) {
            this.updated = true;
        }
        return p2;
    }

    public O valid() {
        if (this.isData && this.buffer == null) {
            throw new InvalidParameterException();
        }
        return this.object;
    }
}
