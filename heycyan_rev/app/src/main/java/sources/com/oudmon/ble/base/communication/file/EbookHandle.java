package com.oudmon.ble.base.communication.file;

import android.app.Application;
import com.elvishew.xlog.XLog;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.CharEncoding;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import com.iflytek.sparkchain.utils.DataUtil;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.OnGattEventCallback;
import com.oudmon.ble.base.bluetooth.queue.BleDataBean;
import com.oudmon.ble.base.bluetooth.queue.BleThreadManager;
import com.oudmon.ble.base.communication.CompressUtils;
import com.oudmon.ble.base.communication.JPackageManager;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import com.oudmon.ble.base.communication.utils.CRC16;
import com.oudmon.ble.base.request.EnableNotifyRequest;
import com.oudmon.qc_utils.bytes.DataTransferUtils;
import com.oudmon.qc_utils.date.DateUtil;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
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
public class EbookHandle {
    private static final String TAG = "EbookHandle";
    private static EbookHandle mInstance;
    private byte[] mFileSend;
    private byte[] mFileSendA;
    private byte[] mFileSendB;
    private byte[] mReceivedData;
    private boolean mReceiving;
    private static final UUID SERIAL_PORT_SERVICE = UUID.fromString("de5bf728-d711-4e47-af26-65e3012a5dc7");
    private static final UUID SERIAL_PORT_CHARACTER_NOTIFY = UUID.fromString("de5bf729-d711-4e47-af26-65e3012a5dc7");
    private static final UUID SERIAL_PORT_CHARACTER_WRITE = UUID.fromString("de5bf72a-d711-4e47-af26-65e3012a5dc7");
    public static int FileTypeEbook = 4;
    public static int FileTypeAlbum = 5;
    public static int FileTypeLYRICS = 6;
    private ArrayList<String> fileNames = new ArrayList<>();
    private EnableNotifyRequest enableNotifyRequest = new EnableNotifyRequest(SERIAL_PORT_SERVICE, SERIAL_PORT_CHARACTER_NOTIFY, new EnableNotifyRequest.ListenerCallback() { // from class: com.oudmon.ble.base.communication.file.EbookHandle.1
        @Override // com.oudmon.ble.base.request.EnableNotifyRequest.ListenerCallback
        public void enable(boolean z) {
        }
    });
    private CopyOnWriteArraySet<IEbookCallback> mCallbackArray = new CopyOnWriteArraySet<>();
    private int currFileType = 0;
    private String logPath = "";
    private String logPath1 = "";
    private IEbookCallback mCallback = new IEbookCallback() { // from class: com.oudmon.ble.base.communication.file.EbookHandle.2
        @Override // com.oudmon.ble.base.communication.file.IEbookCallback
        public void onFileNames(ArrayList<String> arrayList) {
            Iterator it = EbookHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((IEbookCallback) it.next()).onFileNames(arrayList);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.IEbookCallback
        public void onProgress(float f) {
            Iterator it = EbookHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((IEbookCallback) it.next()).onProgress(f);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.IEbookCallback
        public void onComplete() {
            Iterator it = EbookHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((IEbookCallback) it.next()).onComplete();
            }
        }

        @Override // com.oudmon.ble.base.communication.file.IEbookCallback
        public void onDeleteSuccess(int i) {
            Iterator it = EbookHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((IEbookCallback) it.next()).onDeleteSuccess(i);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.IEbookCallback
        public void onActionResult(int i) {
            Iterator it = EbookHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((IEbookCallback) it.next()).onActionResult(i);
            }
        }
    };
    private short mPocketIndex = 0;
    private short mPocketIndexA = 0;
    private short mPocketIndexB = 0;
    private int totalSize = 1;
    private int sizeA = 1;
    private int sizeB = 1;
    private int mTotalCount = 0;
    private int mReceivedCount = 0;
    private OnGattEventCallback callback = new OnGattEventCallback() { // from class: com.oudmon.ble.base.communication.file.EbookHandle.3
        @Override // com.oudmon.ble.base.bluetooth.OnGattEventCallback
        public void onReceivedData(String str, byte[] bArr) throws NumberFormatException {
            if (bArr != null) {
                byte b = bArr[0];
                if ((b & 255) == 188 && bArr[1] == 49) {
                    XLog.m137i("初始化完成，开始向手环发送实际文件");
                    if (bArr[6] > 0) {
                        EbookHandle.this.mCallback.onActionResult(bArr[6]);
                        return;
                    } else {
                        EbookHandle.this.cmdSendPacket();
                        return;
                    }
                }
                if ((b & 255) == 188 && bArr[1] == 50) {
                    if (!EbookHandle.this.readNextBigPocket()) {
                        XLog.m137i("向手环发送数据完毕, 包序: " + ((int) EbookHandle.this.mPocketIndex));
                        EbookHandle.this.cmdCheck();
                        return;
                    } else {
                        float f = Float.parseFloat(new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.US)).format((((EbookHandle.this.mPocketIndex * 1024) * 1.0f) * 100.0f) / EbookHandle.this.totalSize));
                        XLog.m137i("向手环发送数据进度: " + f + ", 包序: " + ((int) EbookHandle.this.mPocketIndex) + "总包:" + EbookHandle.this.totalSize);
                        EbookHandle.this.mCallback.onProgress(Math.min(f, 100.0f));
                        return;
                    }
                }
                if ((b & 255) == 188 && bArr[1] == 51) {
                    XLog.m137i("===============回调 onComplete");
                    EbookHandle.this.mFileSend = new byte[0];
                    if (bArr[6] > 0) {
                        EbookHandle.this.mCallback.onActionResult(bArr[6]);
                        return;
                    } else {
                        EbookHandle.this.mCallback.onComplete();
                        return;
                    }
                }
                if ((b & 255) != 188 || bArr[1] != Byte.MIN_VALUE) {
                    if (EbookHandle.this.mReceiving) {
                        System.arraycopy(bArr, 0, EbookHandle.this.mReceivedData, EbookHandle.this.mReceivedCount, bArr.length);
                        EbookHandle.access$812(EbookHandle.this, bArr.length);
                        EbookHandle ebookHandle = EbookHandle.this;
                        ebookHandle.mReceiving = ebookHandle.mReceivedCount < EbookHandle.this.mTotalCount;
                        XLog.m137i("文件：->3 mTotalCount: " + EbookHandle.this.mTotalCount + ", mReceivedCount: " + EbookHandle.this.mReceivedCount + ", mReceiving: " + EbookHandle.this.mReceiving);
                        if (EbookHandle.this.mReceiving) {
                            return;
                        }
                        XLog.m137i("文件：->4 ->" + DataTransferUtils.getHexString(EbookHandle.this.mReceivedData));
                        int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(EbookHandle.this.mReceivedData, 1, 2));
                        if (ByteUtil.bytesToInt(Arrays.copyOfRange(EbookHandle.this.mReceivedData, 2, 3)) == 0) {
                            EbookHandle.this.fileNames = new ArrayList();
                        }
                        XLog.m136i(Integer.valueOf(iBytesToInt));
                        if (iBytesToInt > 0) {
                            int iBytesToInt2 = ByteUtil.bytesToInt(Arrays.copyOfRange(EbookHandle.this.mReceivedData, 4, 5));
                            EbookHandle ebookHandle2 = EbookHandle.this;
                            ebookHandle2.parseEbookData(ebookHandle2.mReceivedData, 5, iBytesToInt2, iBytesToInt, 0);
                            return;
                        }
                        EbookHandle.this.mCallback.onFileNames(EbookHandle.this.fileNames);
                        return;
                    }
                    if ((bArr[0] & 255) == 188 && bArr[1] == -127) {
                        EbookHandle.this.mCallback.onDeleteSuccess(ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 6, 7)));
                        return;
                    }
                    return;
                }
                EbookHandle.this.mTotalCount = DataTransferUtils.bytesToShort(bArr, 2);
                EbookHandle.this.mReceivedCount = bArr.length - 6;
                EbookHandle ebookHandle3 = EbookHandle.this;
                ebookHandle3.mReceivedData = new byte[ebookHandle3.mTotalCount];
                System.arraycopy(bArr, 6, EbookHandle.this.mReceivedData, 0, EbookHandle.this.mReceivedCount);
                EbookHandle ebookHandle4 = EbookHandle.this;
                ebookHandle4.mReceiving = ebookHandle4.mReceivedCount < EbookHandle.this.mTotalCount;
                XLog.m137i("文件：-> 1mTotalCount: " + EbookHandle.this.mTotalCount + ", mReceivedCount: " + EbookHandle.this.mReceivedCount + ", mReceiving: " + EbookHandle.this.mReceiving);
                if (EbookHandle.this.mReceiving) {
                    return;
                }
                XLog.m137i("文件：->2->" + DataTransferUtils.getHexString(EbookHandle.this.mReceivedData));
                int iBytesToInt3 = ByteUtil.bytesToInt(Arrays.copyOfRange(EbookHandle.this.mReceivedData, 1, 2));
                if (ByteUtil.bytesToInt(Arrays.copyOfRange(EbookHandle.this.mReceivedData, 2, 3)) == 0) {
                    EbookHandle.this.fileNames = new ArrayList();
                }
                XLog.m136i(Integer.valueOf(iBytesToInt3));
                if (iBytesToInt3 > 0) {
                    int iBytesToInt4 = ByteUtil.bytesToInt(Arrays.copyOfRange(EbookHandle.this.mReceivedData, 4, 5));
                    EbookHandle ebookHandle5 = EbookHandle.this;
                    ebookHandle5.parseEbookData(ebookHandle5.mReceivedData, 5, iBytesToInt4, iBytesToInt3, 0);
                    return;
                }
                EbookHandle.this.mCallback.onFileNames(EbookHandle.this.fileNames);
            }
        }
    };
    boolean mPocketIndexAFlag = false;
    private int mPackageLength = JPackageManager.getInstance().getLength();

    static /* synthetic */ int access$812(EbookHandle ebookHandle, int i) {
        int i2 = ebookHandle.mReceivedCount + i;
        ebookHandle.mReceivedCount = i2;
        return i2;
    }

    public int getCurrFileType() {
        return this.currFileType;
    }

    public void setCurrFileType(int i) {
        this.currFileType = i;
    }

    public static EbookHandle getInstance() {
        if (mInstance == null) {
            synchronized (EbookHandle.class) {
                if (mInstance == null) {
                    mInstance = new EbookHandle();
                }
            }
        }
        return mInstance;
    }

    private EbookHandle() {
        XLog.m137i("create FileHandle.. mPackageLength: " + this.mPackageLength);
    }

    public void initRegister() {
        BleOperateManager.getInstance().setCallback(this.callback);
    }

    public void setDeviceOperateManagerCallback() {
        BleOperateManager.getInstance().setCallback(this.callback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseEbookData(byte[] bArr, int i, int i2, int i3, int i4) {
        int i5 = i4 + 1;
        int i6 = i2 + i;
        try {
            this.fileNames.add(unicodeByteToStr(Arrays.copyOfRange(bArr, i, i6)));
            if (i5 < i3) {
                parseEbookData(bArr, i6 + 1, bArr[i6], i3, i5);
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

    public void registerCallback(IEbookCallback iEbookCallback) {
        try {
            this.mCallbackArray.remove(iEbookCallback);
            this.mCallbackArray.add(iEbookCallback);
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
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(128, new byte[]{2, (byte) i}), this.mPackageLength));
    }

    public void deleteEbook(int i, String str) {
        try {
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
            BleThreadManager.getInstance().addData(new BleDataBean(addHeader(129, ByteUtil.concat(new byte[]{2, (byte) i, (byte) bytes.length}, bytes)), this.mPackageLength));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v23 */
    /* JADX WARN: Type inference failed for: r4v29 */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v30 */
    /* JADX WARN: Type inference failed for: r4v31 */
    /* JADX WARN: Type inference failed for: r4v32 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:92:0x022d -> B:100:0x0230). Please report as a decompilation issue!!! */
    public boolean executeFilePrepare(String str) throws Throwable {
        BufferedReader bufferedReader;
        RandomAccessFile randomAccessFile;
        int i = this.currFileType;
        ?? r4 = 0;
        randomAccessFile = null;
        RandomAccessFile randomAccessFile2 = null;
        length = 0;
        int length = 0;
        BufferedReader bufferedReader2 = null;
        r4 = 0;
        if (i != FileTypeEbook && i != FileTypeLYRICS) {
            if (i != FileTypeAlbum) {
                return false;
            }
            if (!new File(str).exists()) {
                XLog.m137i("准备发送的文件不存在！");
                return false;
            }
            try {
                try {
                    randomAccessFile = new RandomAccessFile(str, "r");
                } catch (Exception e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                byte[] bArr = new byte[(int) randomAccessFile.length()];
                this.mFileSend = bArr;
                this.totalSize = bArr.length;
                this.mPocketIndex = (short) 0;
                XLog.m132e("准备发送的文件.. dataSize = " + this.mFileSend.length + ", readSize = " + randomAccessFile.read(bArr, 0, bArr.length));
                try {
                    randomAccessFile.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                return true;
            } catch (Exception e3) {
                e = e3;
                randomAccessFile2 = randomAccessFile;
                e.printStackTrace();
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                return false;
            } catch (Throwable th2) {
                th = th2;
                randomAccessFile2 = randomAccessFile;
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        }
        File file = new File(str);
        if (!file.exists()) {
            XLog.m137i("准备发送的文件不存在！");
            return false;
        }
        String strCharset = charset(str);
        XLog.m137i(strCharset);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            try {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str), strCharset));
                    while (true) {
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            stringBuffer.append(line).append(IOUtils.LINE_SEPARATOR_UNIX);
                        } catch (IOException e6) {
                            e = e6;
                            bufferedReader2 = bufferedReader;
                            e.printStackTrace();
                            r4 = bufferedReader2;
                            if (bufferedReader2 != null) {
                                bufferedReader2.close();
                                r4 = bufferedReader2;
                            }
                            return false;
                        } catch (Throwable th3) {
                            th = th3;
                            r4 = bufferedReader;
                            if (r4 != 0) {
                                try {
                                    r4.close();
                                } catch (IOException e7) {
                                    e7.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                    bufferedReader.close();
                    String string = stringBuffer.toString();
                    this.mPocketIndexA = (short) 0;
                    this.mPocketIndexB = (short) 0;
                    this.mPocketIndex = (short) 0;
                    if (file.length() > 10485760) {
                        this.mFileSend = new byte[0];
                        XLog.m136i(Integer.valueOf(string.length()));
                        int i2 = 2;
                        int length2 = string.length() / 2;
                        String strSubstring = string.substring(0, length2);
                        String strSubstring2 = string.substring(length2);
                        this.mFileSendA = strSubstring.getBytes(Charset.forName(DataUtil.UNICODE));
                        byte[] bytes = strSubstring2.getBytes(Charset.forName(DataUtil.UNICODE));
                        this.mFileSendB = bytes;
                        byte[] bArr2 = this.mFileSendA;
                        this.sizeA = bArr2.length;
                        this.sizeB = bytes.length;
                        if (!ByteUtil.byteArraySubToString(bArr2).startsWith("fffe")) {
                            int i3 = 0;
                            while (i3 < this.sizeA) {
                                int i4 = i3 + 2;
                                int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(this.mFileSendA, i3, i4));
                                this.mFileSendA[i3] = (byte) ByteUtil.hiword(iBytesToInt);
                                this.mFileSendA[i3 + 1] = (byte) ByteUtil.loword(iBytesToInt);
                                i3 = i4;
                            }
                        }
                        if (!ByteUtil.byteArraySubToString(this.mFileSendB).startsWith("fffe")) {
                            while (i2 < this.sizeB) {
                                int i5 = i2 + 2;
                                int iBytesToInt2 = ByteUtil.bytesToInt(Arrays.copyOfRange(this.mFileSendB, i2, i5));
                                this.mFileSendB[i2 - 2] = (byte) ByteUtil.hiword(iBytesToInt2);
                                this.mFileSendB[i2 - 1] = (byte) ByteUtil.loword(iBytesToInt2);
                                i2 = i5;
                            }
                        }
                        int i6 = 1024 - (this.sizeA % 1024);
                        byte[] bArr3 = new byte[i6];
                        System.arraycopy(this.mFileSendB, 0, bArr3, 0, i6);
                        byte[] bArr4 = this.mFileSendB;
                        byte[] bArr5 = new byte[bArr4.length - i6];
                        length = bArr4.length - i6;
                        System.arraycopy(bArr4, 0, bArr5, 0, length);
                        byte[] bArrConcat = ByteUtil.concat(this.mFileSendA, bArr3);
                        this.mFileSendA = bArrConcat;
                        this.sizeA = bArrConcat.length;
                        this.mFileSendB = bArr5;
                        this.sizeB = bArr5.length;
                        XLog.m137i(this.sizeA + "----" + this.sizeB);
                        this.totalSize = this.sizeA + this.sizeB;
                    } else {
                        byte[] bytes2 = string.getBytes(Charset.forName(DataUtil.UNICODE));
                        this.mFileSend = bytes2;
                        this.totalSize = bytes2.length;
                        if (!ByteUtil.byteArraySubToString(bytes2).startsWith("fffe")) {
                            int i7 = 0;
                            while (i7 < this.totalSize) {
                                int i8 = i7 + 2;
                                int iBytesToInt3 = ByteUtil.bytesToInt(Arrays.copyOfRange(this.mFileSend, i7, i8));
                                byte[] bArr6 = this.mFileSend;
                                byte bHiword = (byte) ByteUtil.hiword(iBytesToInt3);
                                bArr6[i7] = bHiword;
                                this.mFileSend[i7 + 1] = (byte) ByteUtil.loword(iBytesToInt3);
                                i7 = i8;
                                length = bHiword;
                            }
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (IOException e8) {
                e = e8;
            }
        } catch (IOException e9) {
            e9.printStackTrace();
            r4 = r4;
        }
        if (this.totalSize <= 0) {
            bufferedReader.close();
            r4 = length;
            return false;
        }
        try {
            bufferedReader.close();
        } catch (IOException e10) {
            e10.printStackTrace();
        }
        return true;
    }

    public boolean executeFilePrepareAlbum(byte[] bArr) {
        try {
            this.mFileSend = bArr;
            this.totalSize = bArr.length;
            this.mPocketIndex = (short) 0;
            XLog.m132e("准备发送的文件.. dataSize = " + this.mFileSend.length + ", readSize = " + this.totalSize);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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

    public void cmdFileInit(String str) {
        try {
            setDeviceOperateManagerCallback();
            byte[] bytes = str.getBytes(Charset.forName(DataUtil.UNICODE));
            if (!ByteUtil.byteArrayToString(bytes).startsWith("fffe")) {
                int i = 0;
                while (i < bytes.length) {
                    int i2 = i + 2;
                    int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bytes, i, i2));
                    bytes[i] = (byte) ByteUtil.hiword(iBytesToInt);
                    bytes[i + 1] = (byte) ByteUtil.loword(iBytesToInt);
                    i = i2;
                }
            }
            XLog.m137i(ByteUtil.byteArrayToString(bytes));
            byte[] bArr = new byte[bytes.length + 10];
            bArr[0] = (byte) this.currFileType;
            System.arraycopy(DataTransferUtils.intToBytes(this.totalSize), 0, bArr, 1, 4);
            bArr[9] = (byte) bytes.length;
            System.arraycopy(bytes, 0, bArr, 10, bytes.length);
            sendPocketToBle(addHeader(49, bArr));
            XLog.m137i("cmdFileInit.. 完成");
        } catch (Exception e) {
            XLog.m137i("cmdFileInit.. Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void cmdSendPacket() {
        setDeviceOperateManagerCallback();
        if (this.mFileSend == null) {
            return;
        }
        XLog.m137i("cmdSendPacket.. 开始发送数据，数据长度: " + this.totalSize);
        readNextBigPocket();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean readNextBigPocket() {
        try {
            setDeviceOperateManagerCallback();
            short s = this.mPocketIndex;
            int i = s * 1024;
            int i2 = this.totalSize;
            if (i < i2) {
                if (this.mFileSend.length > 0) {
                    int iMin = Math.min(1024, i2 - (s * 1024));
                    byte[] bArr = new byte[iMin];
                    System.arraycopy(this.mFileSend, this.mPocketIndex * 1024, bArr, 0, iMin);
                    byte[] bArrCompress = CompressUtils.compress(bArr);
                    byte[] bArr2 = new byte[bArrCompress.length + 2];
                    System.arraycopy(DataTransferUtils.shortToBytes((short) (this.mPocketIndex + 1)), 0, bArr2, 0, 2);
                    System.arraycopy(bArrCompress, 0, bArr2, 2, bArrCompress.length);
                    sendPocketToBle(addHeader(50, bArr2));
                    this.mPocketIndex = (short) (this.mPocketIndex + 1);
                    return true;
                }
                short s2 = this.mPocketIndexA;
                int i3 = s2 * 1024;
                int i4 = this.sizeA;
                if (i3 < i4) {
                    int iMin2 = Math.min(1024, i4 - (s2 * 1024));
                    byte[] bArr3 = new byte[iMin2];
                    System.arraycopy(this.mFileSendA, this.mPocketIndexA * 1024, bArr3, 0, iMin2);
                    byte[] bArrCompress2 = CompressUtils.compress(bArr3);
                    byte[] bArr4 = new byte[bArrCompress2.length + 2];
                    System.arraycopy(DataTransferUtils.shortToBytes((short) (this.mPocketIndexA + 1)), 0, bArr4, 0, 2);
                    System.arraycopy(bArrCompress2, 0, bArr4, 2, bArrCompress2.length);
                    sendPocketToBle(addHeader(50, bArr4));
                    short s3 = (short) (this.mPocketIndexA + 1);
                    this.mPocketIndexA = s3;
                    this.mPocketIndex = (short) (s3 + this.mPocketIndexB);
                    return true;
                }
                int iMin3 = Math.min(1024, this.sizeB - (this.mPocketIndexB * 1024));
                byte[] bArr5 = new byte[iMin3];
                System.arraycopy(this.mFileSendB, this.mPocketIndexB * 1024, bArr5, 0, iMin3);
                byte[] bArrCompress3 = CompressUtils.compress(bArr5);
                byte[] bArr6 = new byte[bArrCompress3.length + 2];
                System.arraycopy(DataTransferUtils.shortToBytes((short) (this.mPocketIndexA + this.mPocketIndexB + 1)), 0, bArr6, 0, 2);
                System.arraycopy(bArrCompress3, 0, bArr6, 2, bArrCompress3.length);
                sendPocketToBle(addHeader(50, bArr6));
                short s4 = (short) (this.mPocketIndexB + 1);
                this.mPocketIndexB = s4;
                this.mPocketIndex = (short) (this.mPocketIndexA + s4);
                return true;
            }
            XLog.m137i("文件发送完毕");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            XLog.m137i("文件发送异常: " + e.getMessage());
            return false;
        }
    }

    private void sendPocketToBle(byte[] bArr) {
        setDeviceOperateManagerCallback();
        resetPackageLength();
        BleThreadManager.getInstance().addData(new BleDataBean(bArr, this.mPackageLength));
    }

    private void resetPackageLength() {
        this.mPackageLength = JPackageManager.getInstance().getLength();
        XLog.m137i("resetPackageLength.. mPackageLength: " + this.mPackageLength);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cmdCheck() {
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

    public void initPath(Application application) {
        this.logPath = application.getExternalFilesDir("") + "/log/" + new DateUtil().getY_M_D() + "1_log.txt";
        this.logPath1 = application.getExternalFilesDir("") + "/log/" + new DateUtil().getY_M_D() + "2_log.txt";
    }
}
