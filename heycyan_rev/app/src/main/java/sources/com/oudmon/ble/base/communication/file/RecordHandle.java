package com.oudmon.ble.base.communication.file;

import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.home.album.water.WatermarkGenerator;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.CharEncoding;
import com.iflytek.sparkchain.utils.DataUtil;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.OnGattEventCallback;
import com.oudmon.ble.base.bluetooth.queue.BleDataBean;
import com.oudmon.ble.base.bluetooth.queue.BleThreadManager;
import com.oudmon.ble.base.communication.JPackageManager;
import com.oudmon.ble.base.communication.entity.RecordEntity;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import com.oudmon.ble.base.communication.utils.CRC16;
import com.oudmon.ble.base.request.EnableNotifyRequest;
import com.oudmon.qc_utils.bytes.DataTransferUtils;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes2.dex */
public class RecordHandle {
    private static final String TAG = "RecondHandle";
    private static RecordHandle mInstance;
    private String fileName;
    private byte[] mDetails;
    private byte[] mDetailsName;
    private boolean mReceiving;
    private boolean mReceivingName;
    private static final UUID SERIAL_PORT_SERVICE = UUID.fromString("de5bf728-d711-4e47-af26-65e3012a5dc7");
    private static final UUID SERIAL_PORT_CHARACTER_NOTIFY = UUID.fromString("de5bf729-d711-4e47-af26-65e3012a5dc7");
    private static final UUID SERIAL_PORT_CHARACTER_WRITE = UUID.fromString("de5bf72a-d711-4e47-af26-65e3012a5dc7");
    private ArrayList<RecordEntity> fileNames = new ArrayList<>();
    private EnableNotifyRequest enableNotifyRequest = new EnableNotifyRequest(SERIAL_PORT_SERVICE, SERIAL_PORT_CHARACTER_NOTIFY, new EnableNotifyRequest.ListenerCallback() { // from class: com.oudmon.ble.base.communication.file.RecordHandle.1
        @Override // com.oudmon.ble.base.request.EnableNotifyRequest.ListenerCallback
        public void enable(boolean z) {
        }
    });
    private CopyOnWriteArraySet<IRecordCallback> mCallbackArray = new CopyOnWriteArraySet<>();
    private int currFileType = 0;
    private int mReceivedCount = 0;
    private int mTotalCount = 0;
    private int mReceivedCountName = 0;
    private int mTotalCountName = 0;
    private IRecordCallback mCallback = new IRecordCallback() { // from class: com.oudmon.ble.base.communication.file.RecordHandle.2
        @Override // com.oudmon.ble.base.communication.file.IRecordCallback
        public void onFileNames(ArrayList<RecordEntity> arrayList) {
            Iterator it = RecordHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((IRecordCallback) it.next()).onFileNames(arrayList);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.IRecordCallback
        public void onProgress(float f) {
            Iterator it = RecordHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((IRecordCallback) it.next()).onProgress(f);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.IRecordCallback
        public void onComplete() {
            Iterator it = RecordHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((IRecordCallback) it.next()).onComplete();
            }
        }

        @Override // com.oudmon.ble.base.communication.file.IRecordCallback
        public void onReceiver(byte[] bArr) {
            Iterator it = RecordHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((IRecordCallback) it.next()).onReceiver(bArr);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.IRecordCallback
        public void onActionResult(int i) {
            Iterator it = RecordHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((IRecordCallback) it.next()).onActionResult(i);
            }
        }
    };
    private OnGattEventCallback callback = new OnGattEventCallback() { // from class: com.oudmon.ble.base.communication.file.RecordHandle.3
        @Override // com.oudmon.ble.base.bluetooth.OnGattEventCallback
        public void onReceivedData(String str, byte[] bArr) throws NumberFormatException {
            if (bArr != null) {
                if ((bArr[0] & 255) != 188 || bArr[1] != -126) {
                    if (RecordHandle.this.mReceiving) {
                        System.arraycopy(bArr, 0, RecordHandle.this.mDetails, RecordHandle.this.mReceivedCount, bArr.length);
                        RecordHandle.access$212(RecordHandle.this, bArr.length);
                        RecordHandle recordHandle = RecordHandle.this;
                        recordHandle.mReceiving = recordHandle.mReceivedCount < RecordHandle.this.mTotalCount;
                        if (RecordHandle.this.mReceiving) {
                            return;
                        }
                        RecordHandle recordHandle2 = RecordHandle.this;
                        recordHandle2.parseRecord(recordHandle2.mDetails);
                        return;
                    }
                    if ((bArr[0] & 255) != 188 || bArr[1] != Byte.MIN_VALUE) {
                        if (RecordHandle.this.mReceivingName) {
                            System.arraycopy(bArr, 0, RecordHandle.this.mDetailsName, RecordHandle.this.mReceivedCountName, bArr.length);
                            RecordHandle.access$712(RecordHandle.this, bArr.length);
                            RecordHandle recordHandle3 = RecordHandle.this;
                            recordHandle3.mReceivingName = recordHandle3.mReceivedCountName < RecordHandle.this.mTotalCountName;
                            if (RecordHandle.this.mReceivingName) {
                                return;
                            }
                            XLog.m137i(ByteUtil.byteArrayToString(RecordHandle.this.mDetailsName));
                            int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(RecordHandle.this.mDetailsName, 1, 2));
                            if (ByteUtil.bytesToInt(Arrays.copyOfRange(RecordHandle.this.mDetailsName, 2, 3)) == 0) {
                                RecordHandle.this.fileNames = new ArrayList();
                            }
                            if (iBytesToInt > 0) {
                                int iBytesToInt2 = ByteUtil.bytesToInt(Arrays.copyOfRange(RecordHandle.this.mDetailsName, 8, 9));
                                RecordHandle recordHandle4 = RecordHandle.this;
                                recordHandle4.parseRecordData(recordHandle4.mDetailsName, 4, 9, iBytesToInt2, iBytesToInt, 0);
                                return;
                            }
                            RecordHandle.this.mCallback.onFileNames(RecordHandle.this.fileNames);
                            return;
                        }
                        return;
                    }
                    RecordHandle.this.mTotalCountName = DataTransferUtils.bytesToShort(bArr, 2);
                    RecordHandle.this.mReceivedCountName = bArr.length - 6;
                    RecordHandle recordHandle5 = RecordHandle.this;
                    recordHandle5.mReceivingName = recordHandle5.mReceivedCountName < RecordHandle.this.mTotalCountName;
                    RecordHandle recordHandle6 = RecordHandle.this;
                    recordHandle6.mDetailsName = new byte[recordHandle6.mTotalCountName];
                    System.arraycopy(bArr, 6, RecordHandle.this.mDetailsName, 0, RecordHandle.this.mReceivedCountName);
                    if (RecordHandle.this.mReceivingName) {
                        return;
                    }
                    int iBytesToInt3 = ByteUtil.bytesToInt(Arrays.copyOfRange(RecordHandle.this.mDetailsName, 1, 2));
                    if (ByteUtil.bytesToInt(Arrays.copyOfRange(RecordHandle.this.mDetailsName, 2, 3)) == 0) {
                        RecordHandle.this.fileNames = new ArrayList();
                    }
                    if (iBytesToInt3 > 0) {
                        int iBytesToInt4 = ByteUtil.bytesToInt(Arrays.copyOfRange(RecordHandle.this.mDetailsName, 8, 9));
                        RecordHandle recordHandle7 = RecordHandle.this;
                        recordHandle7.parseRecordData(recordHandle7.mDetailsName, 4, 9, iBytesToInt4, iBytesToInt3, 0);
                        return;
                    }
                    RecordHandle.this.mCallback.onFileNames(RecordHandle.this.fileNames);
                    return;
                }
                RecordHandle.this.mTotalCount = DataTransferUtils.bytesToShort(bArr, 2);
                RecordHandle.this.mReceivedCount = bArr.length - 6;
                RecordHandle recordHandle8 = RecordHandle.this;
                recordHandle8.mReceiving = recordHandle8.mReceivedCount < RecordHandle.this.mTotalCount;
                RecordHandle recordHandle9 = RecordHandle.this;
                recordHandle9.mDetails = new byte[recordHandle9.mTotalCount];
                System.arraycopy(bArr, 6, RecordHandle.this.mDetails, 0, RecordHandle.this.mReceivedCount);
                if (RecordHandle.this.mReceiving) {
                    return;
                }
                RecordHandle recordHandle10 = RecordHandle.this;
                recordHandle10.parseRecord(recordHandle10.mDetails);
            }
        }
    };
    private int mPackageLength = JPackageManager.getInstance().getLength();

    static /* synthetic */ int access$212(RecordHandle recordHandle, int i) {
        int i2 = recordHandle.mReceivedCount + i;
        recordHandle.mReceivedCount = i2;
        return i2;
    }

    static /* synthetic */ int access$712(RecordHandle recordHandle, int i) {
        int i2 = recordHandle.mReceivedCountName + i;
        recordHandle.mReceivedCountName = i2;
        return i2;
    }

    public int getCurrFileType() {
        return this.currFileType;
    }

    public void setCurrFileType(int i) {
        this.currFileType = i;
    }

    public static RecordHandle getInstance() {
        if (mInstance == null) {
            synchronized (RecordHandle.class) {
                if (mInstance == null) {
                    mInstance = new RecordHandle();
                }
            }
        }
        return mInstance;
    }

    private RecordHandle() {
        XLog.m137i("create FileHandle.. mPackageLength: " + this.mPackageLength);
    }

    public void initRegister() {
        BleOperateManager.getInstance().setCallback(this.callback);
    }

    public void setDeviceOperateManagerCallback() {
        BleOperateManager.getInstance().setCallback(this.callback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseRecord(byte[] bArr) throws NumberFormatException {
        int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 0, 4));
        int iBytesToInt2 = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 4, 8));
        XLog.m137i(iBytesToInt + "---" + iBytesToInt2);
        try {
            this.mCallback.onProgress(Math.min(Float.parseFloat(new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.US)).format(((iBytesToInt2 * 1.0f) * 100.0f) / iBytesToInt)), 100.0f));
            if (iBytesToInt2 < iBytesToInt) {
                this.mCallback.onReceiver(Arrays.copyOfRange(bArr, 8, bArr.length));
                readRecordFile(iBytesToInt2 + 1, this.fileName);
            } else {
                this.mCallback.onComplete();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseRecordData(byte[] bArr, int i, int i2, int i3, int i4, int i5) {
        int i6 = i5 + 1;
        try {
            int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, i, i + 4));
            int i7 = i2 + i3;
            String strUnicodeByteToStr = unicodeByteToStr(Arrays.copyOfRange(bArr, i2, i7));
            RecordEntity recordEntity = new RecordEntity();
            recordEntity.setFileName(strUnicodeByteToStr);
            recordEntity.setLength(iBytesToInt);
            this.fileNames.add(recordEntity);
            if (i6 < i4) {
                int i8 = i2 + 4 + i3;
                parseRecordData(bArr, i7, i8 + 1, bArr[i8], i4, i6);
            } else {
                this.mCallback.onFileNames(this.fileNames);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String unicodeByteToStr(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < bArr.length) {
            int i2 = i + 2;
            sb.append((char) ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, i, i2)));
            i = i2;
        }
        return sb.toString();
    }

    public void registerCallback(IRecordCallback iRecordCallback) {
        try {
            this.mCallbackArray.remove(iRecordCallback);
            this.mCallbackArray.add(iRecordCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeCallback(ICallback iCallback) {
        try {
            this.mCallbackArray.remove(iCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearCallback() {
        try {
            this.mCallbackArray.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(int i) {
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(128, new byte[]{3, (byte) i}), this.mPackageLength));
    }

    public void readRecordFile(int i, String str) {
        resetPackageLength();
        this.fileName = str;
        byte[] bArrIntToByte = ByteUtil.intToByte(i, 4);
        byte[] bytes = str.getBytes(Charset.forName(DataUtil.UNICODE));
        if (!ByteUtil.byteArrayToString(bytes).startsWith("fffe")) {
            int i2 = 0;
            while (i2 < bytes.length) {
                int i3 = i2 + 2;
                int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bytes, i2, i3));
                bytes[i2] = (byte) ByteUtil.hiword(iBytesToInt);
                bytes[i2 + 1] = (byte) ByteUtil.loword(iBytesToInt);
                i2 = i3;
            }
        }
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(WatermarkGenerator.WATERMARK_HEIGHT_DP, ByteUtil.concat(new byte[]{bArrIntToByte[0], bArrIntToByte[1], bArrIntToByte[2], bArrIntToByte[3], (byte) bytes.length}, bytes)), this.mPackageLength));
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x004f A[Catch: Exception -> 0x0091, LOOP:0: B:27:0x004f->B:71:0x004f, LOOP_START, TryCatch #0 {Exception -> 0x0091, blocks: (B:3:0x0005, B:5:0x001a, B:7:0x001e, B:10:0x0026, B:25:0x004a, B:27:0x004f, B:40:0x006b, B:48:0x007c, B:51:0x0084, B:55:0x008d, B:15:0x0030, B:20:0x003b, B:22:0x0041), top: B:60:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String charset(String str) throws IOException {
        BufferedInputStream bufferedInputStream;
        boolean z;
        int i;
        String str2 = "GBK";
        byte[] bArr = new byte[3];
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
            z = false;
            bufferedInputStream.mark(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bufferedInputStream.read(bArr, 0, 3) == -1) {
            bufferedInputStream.close();
            return "GBK";
        }
        byte b = bArr[0];
        if (b == -1 && bArr[1] == -2) {
            str2 = CharEncoding.UTF_16LE;
        } else if (b == -2 && bArr[1] == -1) {
            str2 = CharEncoding.UTF_16BE;
        } else {
            if (b == -17 && bArr[1] == -69 && bArr[2] == -65) {
                str2 = "UTF-8";
            }
            bufferedInputStream.reset();
            if (!z) {
                while (true) {
                    int i2 = bufferedInputStream.read();
                    if (i2 == -1 || i2 >= 240 || (128 <= i2 && i2 <= 191)) {
                        break;
                    }
                    if (192 <= i2 && i2 <= 223) {
                        int i3 = bufferedInputStream.read();
                        if (128 > i3 || i3 > 191) {
                            break;
                        }
                    } else if (224 <= i2 && i2 <= 239) {
                        int i4 = bufferedInputStream.read();
                        if (128 <= i4 && i4 <= 191 && 128 <= (i = bufferedInputStream.read()) && i <= 191) {
                            str2 = "UTF-8";
                        }
                    }
                }
            }
            bufferedInputStream.close();
            return str2;
        }
        z = true;
        bufferedInputStream.reset();
        if (!z) {
        }
        bufferedInputStream.close();
        return str2;
    }

    private void resetPackageLength() {
        this.mPackageLength = JPackageManager.getInstance().getLength();
        XLog.m137i("resetPackageLength.. mPackageLength: " + this.mPackageLength);
    }

    private void cmdCheck() {
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(51, null), this.mPackageLength));
    }

    public void endAndRelease() {
        this.enableNotifyRequest.setEnable(false);
        BleOperateManager.getInstance().execute(this.enableNotifyRequest);
        BleOperateManager.getInstance().setCallback(null);
    }

    private byte[] addHeader(int i, byte[] bArr) {
        byte[] bArr2 = new byte[(bArr == null ? 0 : bArr.length) + 6];
        bArr2[0] = -68;
        bArr2[1] = (byte) i;
        if (bArr != null && bArr.length > 0) {
            System.arraycopy(DataTransferUtils.shortToBytes((short) bArr.length), 0, bArr2, 2, 2);
            System.arraycopy(DataTransferUtils.shortToBytes((short) CRC16.calcCrc16(bArr)), 0, bArr2, 4, 2);
            System.arraycopy(bArr, 0, bArr2, 6, bArr.length);
        } else {
            bArr2[4] = -1;
            bArr2[5] = -1;
        }
        return bArr2;
    }

    public List<List<byte[]>> subListBySegment(List<byte[]> list, int i) {
        List<byte[]> listSubList;
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        if (size > 0 && i > 0) {
            int i2 = size / i;
            for (int i3 = 0; i3 < i; i3++) {
                if (i3 == i - 1) {
                    listSubList = list.subList(i2 * i3, size);
                } else {
                    listSubList = list.subList(i2 * i3, (i3 + 1) * i2);
                }
                arrayList.add(listSubList);
            }
        } else {
            arrayList.add(list);
        }
        return arrayList;
    }
}
