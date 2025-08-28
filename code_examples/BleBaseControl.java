/**
 * BLE Base Control - Manages BLE device connections and GATT services
 * File: heycyan_rev/_log_review/app/src/main/java/sources/com/oudmon/ble/base/bluetooth/BleBaseControl.java
 */

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import com.elvishew.xlog.XLog;
import com.hjq.permissions.Permission;
import com.oudmon.ble.base.bluetooth.queue.BleThreadManager;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.scan.BleScannerCompat;
import com.oudmon.ble.base.scan.BleScannerHelper;
import com.oudmon.ble.base.scan.OnTheScanResult;
import com.oudmon.ble.base.util.AppUtil;
import com.oudmon.qc_utils.bluetooth.BluetoothUtils;
import com.oudmon.qc_utils.bytes.DataTransferUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class BleBaseControl {
    private static final int GATT_CLOSE_DELAY_MILLIS = 500;
    private static final String TAG = "BleBaseControl";
    private static BleBaseControl bleBaseControl;
    
    BluetoothDevice bleConnectDevice;
    private volatile boolean connecting;
    private IBleListener listener;
    private BluetoothAdapter mBluetoothAdapter;
    private Context mContext;
    private String mDeviceAddress;
    private volatile boolean mIsConnected;
    private final Object mLock = new Object();
    protected Map<String, BluetoothGatt> mBluetoothGatt = new HashMap();
    private boolean isNeedReconnect = true;
    private int maxReconnect = 10;
    private int maxFail = 6;
    private AtomicInteger count = new AtomicInteger(0);
    private AtomicInteger failCount = new AtomicInteger(0);
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean bluetoothTurnOff = false;
    private HashMap<UUID, BluetoothGattCharacteristic> cacheGattCharacteristicHashMap = new HashMap<>();
    private SystemProxyTimeoutRunnable systemProxyTimeoutRunnable = new SystemProxyTimeoutRunnable();
    private Boolean rtkBindTag = false;
    
    /**
     * Main GATT callback implementation
     */
    private BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int status, int newState) {
            XLog.e("onConnectionStateChange-->status = [" + status + "], newState = [" + newState + "]");
            if (listener != null) {
                listener.bleStatus(status, newState);
            }
            String address = bluetoothGatt.getDevice().getAddress();
            bleConnectDevice = bluetoothGatt.getDevice();
            mHandler.removeCallbacks(mDiscoverServiceTimeoutRunnable);
            mHandler.removeCallbacks(mTimeoutRunnable);
            BleThreadManager.getInstance().clean();
            
            if (status != 0) {
                notifyMyAll();
                mIsConnected = false;
                if (listener != null) {
                    listener.bleGattDisconnect(bluetoothGatt.getDevice());
                }
                disconnectDevice(address);
                failCount.incrementAndGet();
                reconnectFromStateChangeNoAutoConnect();
                return;
            }
            
            if (newState != 2) {
                if (newState == 0) {
                    mIsConnected = false;
                    notifyMyAll();
                    disconnectDevice(address);
                    reconnectDevice();
                    if (listener != null) {
                        listener.bleGattDisconnect(bluetoothGatt.getDevice());
                    }
                    return;
                }
                return;
            }
            
            cacheGattCharacteristicHashMap.clear();
            mHandler.removeCallbacks(mReconnectRunnable);
            waitFor(500L);
            
            if (bluetoothGatt.discoverServices()) {
                mHandler.removeCallbacks(mDiscoverServiceTimeoutRunnable);
                mHandler.postDelayed(mDiscoverServiceTimeoutRunnable, 40000L);
            } else {
                waitFor(1000L);
                boolean discoverServices = bluetoothGatt.discoverServices();
                XLog.i("-------1---" + discoverServices);
                if (!discoverServices) {
                    disconnectDevice(address);
                    return;
                }
            }
            
            count.getAndSet(0);
            failCount.getAndSet(0);
            mIsConnected = true;
            bluetoothTurnOff = false;
            mHandler.removeCallbacks(mReconnectRunnable);
            if (listener != null) {
                listener.bleGattConnected(bluetoothGatt.getDevice());
            }
        }

        private void reconnectFromStateChangeNoAutoConnect() {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    reconnectDevice();
                }
            }, 2000L);
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int status) {
            String address = bluetoothGatt.getDevice().getAddress();
            mHandler.removeCallbacks(mDiscoverServiceTimeoutRunnable);
            if (status != 0) {
                disconnectDevice(address);
                return;
            }
            Iterator<BluetoothGattService> it = bluetoothGatt.getServices().iterator();
            while (it.hasNext()) {
                XLog.i("servicesUUID:" + it.next().getUuid().toString());
            }
            connecting = false;
            if (listener != null) {
                listener.bleServiceDiscovered(status, address);
            }
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            XLog.i("a->w：" + DataTransferUtils.getHexString(characteristic.getValue()));
            if (listener != null) {
                listener.bleCharacteristicWrite(gatt.getDevice().getAddress(), 
                    characteristic.getUuid().toString(), status, characteristic.getValue());
            }
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            if (listener != null) {
                listener.bleCharacteristicRead(gatt.getDevice().getAddress(), 
                    characteristic.getUuid().toString(), status, characteristic.getValue());
            }
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            XLog.i("w->a：" + DataTransferUtils.getHexString(characteristic.getValue()));
            if (listener != null) {
                listener.bleCharacteristicChanged(gatt.getDevice().getAddress(), 
                    characteristic.getUuid().toString(), characteristic.getValue());
            }
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            if (listener != null) {
                listener.onDescriptorWrite(gatt, descriptor, status);
            }
            if (status == 0) {
                checkIsNotifyConfigAndRegisterCallback(descriptor, gatt);
            }
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            if (listener != null) {
                listener.onDescriptorRead(gatt, descriptor, status);
            }
        }

        @Override
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
            if (listener != null) {
                listener.onReadRemoteRssi(gatt, rssi, status);
            }
        }

        @Override
        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
            super.onMtuChanged(gatt, mtu, status);
            XLog.i(mtu + "");
        }
    };
    
    /**
     * Timeout and retry runnables
     */
    private Runnable mDiscoverServiceTimeoutRunnable = new Runnable() {
        @Override
        public void run() {
            disconnectDevice(mDeviceAddress);
        }
    };
    
    private Runnable mTimeoutRunnable = new Runnable() {
        @Override
        public void run() {
            mIsConnected = false;
            connecting = false;
            XLog.i("没有收到系统回调，直接断开");
            disconnectDevice(mDeviceAddress);
            if (listener != null) {
                listener.bleNoCallback();
            }
        }
    };
    
    private Runnable mReconnectRunnable = new Runnable() {
        @Override
        public void run() {
            if (count.get() < maxReconnect) {
                count.incrementAndGet();
                XLog.e("正在重连,重连次数：" + count.get());
                connect(mDeviceAddress);
                return;
            }
            connecting = false;
            XLog.e("超出了重连次数:" + count.get());
        }
    };
    
    /**
     * Singleton pattern implementation
     */
    public static BleBaseControl getInstance() {
        return bleBaseControl;
    }

    public static BleBaseControl getInstance(Context context) {
        if (bleBaseControl == null) {
            synchronized (BleBaseControl.class) {
                if (bleBaseControl == null) {
                    bleBaseControl = new BleBaseControl(context);
                }
            }
        }
        return bleBaseControl;
    }
    
    /**
     * Constructor and initialization
     */
    private BleBaseControl(Context context) {
        this.mContext = context;
        initialize();
    }

    private void initialize() {
        BluetoothManager bluetoothManager = (BluetoothManager) this.mContext.getSystemService("bluetooth");
        if (bluetoothManager != null) {
            this.mBluetoothAdapter = bluetoothManager.getAdapter();
        } else {
            Log.e(TAG, "Unable to initialize BluetoothManager...");
        }
    }
    
    /**
     * Connection management methods
     */
    public boolean connect(String address) {
        if (!BluetoothUtils.isEnabledBluetooth(this.mContext)) {
            this.connecting = false;
            this.isNeedReconnect = false;
            return false;
        }
        if (TextUtils.isEmpty(address)) {
            XLog.i("address 空返回");
            this.mIsConnected = false;
            this.connecting = false;
            return false;
        }
        if (isConnecting() || ismIsConnected()) {
            XLog.i("再次检查的时候返回了");
            return false;
        }
        this.mHandler.removeCallbacks(this.mTimeoutRunnable);
        this.mHandler.postDelayed(this.mTimeoutRunnable, 40000L);
        this.connecting = true;
        this.isNeedReconnect = true;
        BleScannerHelper.getInstance().stopScan(this.mContext);
        this.mDeviceAddress = address;
        BluetoothDevice remoteDevice = this.mBluetoothAdapter.getRemoteDevice(address);
        XLog.e("---------------【开始GATT连接】--------------");
        IBleListener iBleListener = this.listener;
        if (iBleListener != null) {
            iBleListener.startConnect();
        }
        if (Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(this.mContext, Permission.BLUETOOTH_CONNECT) != 0) {
            return false;
        }
        BluetoothGatt bluetoothGattConnectGatt = remoteDevice.connectGatt(this.mContext, false, this.mGattCallback, 2);
        if (bluetoothGattConnectGatt == null) {
            this.mBluetoothGatt.remove(address);
            return false;
        }
        this.mBluetoothGatt.put(address, bluetoothGattConnectGatt);
        return true;
    }

    public void disconnectDevice(String address) {
        try {
            this.connecting = false;
            this.mIsConnected = false;
            XLog.i(address + "  gatt map size:" + this.mBluetoothGatt.size());
            for (final BluetoothGatt bluetoothGatt : this.mBluetoothGatt.values()) {
                if (bluetoothGatt != null) {
                    XLog.i("gatt disconnect it");
                    if (Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(this.mContext, Permission.BLUETOOTH_CONNECT) != 0) {
                        return;
                    }
                    bluetoothGatt.disconnect();
                    this.mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                refreshDeviceCache(bluetoothGatt);
                                if (Build.VERSION.SDK_INT < 31 || ActivityCompat.checkSelfPermission(BleBaseControl.this.mContext, Permission.BLUETOOTH_CONNECT) == 0) {
                                    bluetoothGatt.close();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, 500L);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reconnectDevice() {
        synchronized (BleBaseControl.class) {
            if (!this.isNeedReconnect) {
                this.connecting = false;
                return;
            }
            if (BluetoothUtils.isEnabledBluetooth(this.mContext) && !TextUtils.isEmpty(this.mDeviceAddress)) {
                if (this.failCount.get() >= this.maxFail) {
                    this.mIsConnected = false;
                    this.connecting = false;
                    XLog.i("内部失败循环大于" + this.maxFail + "次直接返回");
                    return;
                }
                if (!isConnecting() && !ismIsConnected()) {
                    this.mHandler.removeCallbacks(this.mReconnectRunnable);
                    if (BleScannerCompat.getScanner(this.mContext).isScanning()) {
                        return;
                    }
                    doConnectClone();
                    return;
                }
                XLog.i("正在连接:" + isConnecting() + " 已经连上:" + ismIsConnected());
                return;
            }
            this.mIsConnected = false;
            this.connecting = false;
        }
    }
    
    /**
     * Helper methods
     */
    private void doConnectClone() {
        boolean zIsBackground = AppUtil.isBackground(this.mContext);
        boolean zIsApplicationBroughtToBackground = AppUtil.isApplicationBroughtToBackground(this.mContext);
        if (zIsBackground || zIsApplicationBroughtToBackground) {
            XLog.e("后台重连调用");
            doConnect();
        } else {
            XLog.e("前台重连调用");
            BleScannerHelper.getInstance().scanTheDevice(this.mContext, this.mDeviceAddress, new OnTheScanResult() {
                @Override
                public void onResult(BluetoothDevice bluetoothDevice) {
                    if (count.get() >= maxReconnect) {
                        count.getAndSet(0);
                    }
                    if (bluetoothDevice != null) {
                        mHandler.postDelayed(mReconnectRunnable, 200L);
                        return;
                    }
                    connecting = false;
                    count.incrementAndGet();
                    doConnect();
                }

                @Override
                public void onScanFailed(int reason) {
                    connecting = false;
                    count.incrementAndGet();
                    doConnect();
                }
            });
        }
    }

    private void doConnect() {
        if (!this.isNeedReconnect) {
            this.connecting = false;
            return;
        }
        if (this.count.get() % 3 == 0 && !this.bluetoothTurnOff) {
            this.mHandler.postDelayed(this.mReconnectRunnable, 1000L);
            XLog.i("--直连");
        } else {
            XLog.i("--扫连");
            this.bluetoothTurnOff = false;
            BleScannerHelper.getInstance().scanTheDevice(this.mContext, this.mDeviceAddress, new OnTheScanResult() {
                @Override
                public void onResult(BluetoothDevice bluetoothDevice) {
                    if (count.get() >= maxReconnect) {
                        count.getAndSet(0);
                    }
                    if (bluetoothDevice != null) {
                        mHandler.postDelayed(mReconnectRunnable, 200L);
                        return;
                    }
                    connecting = false;
                    count.incrementAndGet();
                    doConnect();
                }

                @Override
                public void onScanFailed(int reason) {
                    connecting = false;
                    count.incrementAndGet();
                    doConnect();
                }
            });
        }
    }
    
    /**
     * State management methods
     */
    public boolean isConnected() {
        return mIsConnected;
    }
    
    public boolean isConnecting() {
        return connecting;
    }
    
    public boolean ismIsConnected() {
        return this.mIsConnected;
    }
    
    /**
     * GATT operations
     */
    public BluetoothGattCharacteristic findTheGattCharacteristic(UUID serviceUUID, UUID characteristicUUID) {
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.cacheGattCharacteristicHashMap.get(characteristicUUID);
        if (bluetoothGattCharacteristic != null) {
            return bluetoothGattCharacteristic;
        }
        BluetoothGattCharacteristic bluetoothGattCharacteristicInitTheCharacteristic = initTheCharacteristic(serviceUUID, characteristicUUID);
        if (bluetoothGattCharacteristicInitTheCharacteristic != null) {
            this.cacheGattCharacteristicHashMap.put(characteristicUUID, bluetoothGattCharacteristicInitTheCharacteristic);
        }
        return bluetoothGattCharacteristicInitTheCharacteristic;
    }

    private BluetoothGattCharacteristic initTheCharacteristic(UUID serviceUUID, UUID characteristicUUID) {
        BluetoothGatt gatt;
        if (!BleOperateManager.getInstance().isConnected()) {
            return null;
        }
        String address = getInstance().getmDeviceAddress();
        if (TextUtils.isEmpty(address) || (gatt = getInstance().getGatt(address)) == null) {
            return null;
        }
        BluetoothGattService service = gatt.getService(serviceUUID);
        if (service == null) {
            Log.e(TAG, "initTheCharacteristic: can't find service uuid=" + serviceUUID);
            return null;
        }
        return service.getCharacteristic(characteristicUUID);
    }
    
    /**
     * Utility methods
     */
    private void notifyMyAll() {
        synchronized (this.mLock) {
            this.mLock.notifyAll();
        }
    }

    private void waitFor(long delay) {
        synchronized (this.mLock) {
            try {
                this.mLock.wait(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected boolean refreshDeviceCache(BluetoothGatt bluetoothGatt) {
        if (!BluetoothUtils.isEnabledBluetooth(this.mContext)) {
            return false;
        }
        try {
            Method method = BluetoothGatt.class.getMethod("refresh", new Class[0]);
            if (method != null) {
                boolean zBooleanValue = ((Boolean) method.invoke(bluetoothGatt, new Object[0])).booleanValue();
                XLog.e("Refreshing result: " + zBooleanValue);
                return zBooleanValue;
            }
        } catch (Exception e) {
            XLog.e("An exception occured while refreshing device " + e.toString());
        }
        return false;
    }

    private void checkIsNotifyConfigAndRegisterCallback(BluetoothGattDescriptor descriptor, BluetoothGatt bluetoothGatt) {
        byte[] value;
        IBleListener iBleListener;
        if (descriptor.getUuid().compareTo(Constants.GATT_NOTIFY_CONFIG) == 0 && (value = descriptor.getValue()) != null && value.length == 2 && value[1] == 0 && value[0] == 1 && (iBleListener = this.listener) != null) {
            iBleListener.bleCharacteristicNotification();
        }
    }
    
    /**
     * Getter and setter methods
     */
    public Context getmContext() {
        return this.mContext;
    }

    public void setmContext(Context context) {
        this.mContext = context;
    }

    public String getmDeviceAddress() {
        return this.mDeviceAddress;
    }

    public void setmDeviceAddress(String address) {
        this.mDeviceAddress = address;
    }

    public IBleListener getListener() {
        return this.listener;
    }

    public void setListener(IBleListener listener) {
        this.listener = listener;
    }

    public void setNeedReconnect(boolean needReconnect) {
        this.isNeedReconnect = needReconnect;
    }

    public void setBluetoothTurnOff(boolean bluetoothTurnOff) {
        this.bluetoothTurnOff = bluetoothTurnOff;
        if (!bluetoothTurnOff) {
            this.mIsConnected = false;
            this.connecting = false;
        }
    }
    
    /**
     * Inner classes
     */
    private class SystemProxyTimeoutRunnable implements Runnable {
        private SystemProxyTimeoutRunnable() {
        }

        @Override
        public void run() {
            try {
                doConnectClone();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
