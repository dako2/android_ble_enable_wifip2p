package com.iflytek.sparkchain.core;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
abstract class AiBuilder {
    private static final AtomicInteger atomicInteger = new AtomicInteger(0);
    private InputData inputData;
    protected int paramCount = 0;
    protected AiStatus status = AiStatus.BEGIN;
    protected int handle = getAtomicHandle();

    private static class InputData {
        private ByteBuffer buffer;
        private InputData lastData;

        private InputData() {
        }

        public ByteBuffer getBuffer() {
            return this.buffer;
        }

        public InputData getLastData() {
            return this.lastData;
        }

        public void setBuffer(ByteBuffer byteBuffer) {
            this.buffer = byteBuffer;
        }

        public void setLastData(InputData inputData) {
            this.lastData = inputData;
        }
    }

    protected AiBuilder() {
    }

    private void clearInputData() {
        while (true) {
            InputData inputData = this.inputData;
            if (inputData == null) {
                return;
            }
            inputData.getBuffer().clear();
            this.inputData.setBuffer(null);
            this.inputData = this.inputData.getLastData();
        }
    }

    public static int getAtomicHandle() {
        return atomicInteger.incrementAndGet();
    }

    protected void add(String str, ByteBuffer byteBuffer, EnumC2198b enumC2198b, EnumC2199c enumC2199c) {
        if (byteBuffer == null) {
            return;
        }
        if (enumC2199c.equals(EnumC2199c.OTHER)) {
            AiHelper.getInst().newBuffer(this.handle, str, byteBuffer.array(), enumC2198b.getValue(), enumC2199c.getValue(), this.status.getValue());
        } else {
            addDirectBuffer(str, byteBuffer, enumC2198b, enumC2199c.getValue());
        }
    }

    protected void add(String str, ByteBuffer byteBuffer, EnumC2199c enumC2199c) {
        if (byteBuffer == null) {
            return;
        }
        add(str, byteBuffer, EnumC2198b.MEM, enumC2199c);
    }

    protected void add(String str, byte[] bArr) {
        if (bArr == null) {
            return;
        }
        add(str, bArr, EnumC2199c.OTHER);
    }

    protected void add(String str, byte[] bArr, EnumC2198b enumC2198b, EnumC2199c enumC2199c) {
        if (bArr == null) {
            return;
        }
        if (enumC2199c.equals(EnumC2199c.OTHER)) {
            AiHelper.getInst().newBuffer(this.handle, str, bArr, enumC2198b.getValue(), enumC2199c.getValue(), this.status.getValue());
            return;
        }
        int length = bArr.length;
        EnumC2199c enumC2199c2 = EnumC2199c.TEXT;
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(length + (enumC2199c == enumC2199c2 ? 2 : 0));
        byteBufferAllocateDirect.put(bArr);
        if (enumC2199c == enumC2199c2) {
            byteBufferAllocateDirect.put((byte) 0);
        }
        if (this.inputData == null) {
            InputData inputData = new InputData();
            this.inputData = inputData;
            inputData.buffer = byteBufferAllocateDirect;
        } else {
            InputData inputData2 = new InputData();
            inputData2.setBuffer(byteBufferAllocateDirect);
            inputData2.setLastData(this.inputData);
            this.inputData = inputData2;
        }
        addDirectBuffer(str, byteBufferAllocateDirect, enumC2198b, enumC2199c.getValue());
    }

    protected void add(String str, byte[] bArr, EnumC2199c enumC2199c) {
        if (bArr == null) {
            return;
        }
        add(str, bArr, EnumC2198b.MEM, enumC2199c);
    }

    protected void addDirectBuffer(String str, ByteBuffer byteBuffer, EnumC2198b enumC2198b, int i) {
        AiHelper.getInst().newDirectBuffer(this.handle, str, byteBuffer, enumC2198b.getValue(), i, this.status.getValue(), byteBuffer.capacity());
    }

    public void clean() {
        AiHelper.getInst().paramClear(this.handle);
        AiHelper.getInst().inputClear(this.handle);
        clearInputData();
        this.paramCount = 0;
    }

    protected void custom(String str, byte[] bArr, EnumC2198b enumC2198b, int i, int i2) {
        if (bArr != null) {
            AiHelper.getInst().newCustom(this.handle, str, bArr, enumC2198b.getValue(), i, i2);
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        clearInputData();
    }
}
