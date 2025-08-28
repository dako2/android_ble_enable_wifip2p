/**
 * OTA Activity - Orchestrates BLE and WiFiP2P connection process with file transfer
 * File: heycyan_rev/_log_review/app/src/main/java/sources/com/glasssutdio/wear/ota/OTAActivity.java
 */

import android.content.Context;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.motion.widget.Key;
import androidx.core.animation.LinearInterpolator;
import androidx.core.animation.ObjectAnimator;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.ThreadExtKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.GFileUtilKt;
import com.glasssutdio.wear.all.utils.GsonInstance;
import com.glasssutdio.wear.all.utils.NetWorkUtils;
import com.glasssutdio.wear.bus.BluetoothEvent;
import com.glasssutdio.wear.bus.BusEvent;
import com.glasssutdio.wear.bus.EventType;
import com.glasssutdio.wear.bus.OTAFileStatusEvent;
import com.glasssutdio.wear.databinding.ActivityOtaactivityBinding;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import com.glasssutdio.wear.ota.OTAViewModel;
import com.glasssutdio.wear.wifi.p2p.WifiP2pManagerSingleton;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.FilenameUtils;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import com.hjq.permissions.Permission;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.DfuHandle;
import com.oudmon.ble.base.communication.ILargeDataResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.resp.BaseResponse;
import com.oudmon.ble.base.communication.bigData.resp.BatteryResponse;
import com.oudmon.ble.base.communication.bigData.resp.DeviceInfoResponse;
import com.oudmon.ble.base.communication.bigData.resp.GlassModelControlResponse;
import com.oudmon.ble.base.communication.bigData.resp.GlassesDeviceNotifyListener;
import com.oudmon.ble.base.communication.bigData.resp.GlassesDeviceNotifyRsp;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import com.oudmon.ble.base.util.AppUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/**
 * OTA Activity - Handles BLE to WiFiP2P transition and file transfer
 */
public class OTAActivity extends BaseSettingActivity implements WifiP2pManagerSingleton.WifiP2pCallback {
    
    private final long FLAG_HOMEKEY_DISPATCHED = 2147483648L;
    private final int PORT = 8888;
    private ActivityOtaactivityBinding binding;
    private boolean bleCallbackSuccess;
    private DfuHandle dfuHandle;
    private final DfuHandle.IOpResult dfuOpResult;
    private int failRetry;
    private final Handler handler;
    private boolean isP2PConnecting;
    private MyDeviceNotifyListener otaListener;
    private OTAViewModel otaViewModel;
    private P2pConnectFailRunnable p2pConnectFailRunnable;
    private String progressValue;
    private final ActivityResultLauncher<String[]> requestPermissionLaunch;
    private final String[] requestedPermissions;
    private MyRunnable runnable;
    private ServerSocket serverSocket;
    private boolean startServer;
    private boolean systemSuccess;
    private PowerManager.WakeLock wakeLock;
    private String wifiFirmWareName;
    
    /**
     * BLE OTA process methods
     */
    private void startBleOta() {
        // Implementation for starting BLE OTA
        XLog.d("Starting BLE OTA process");
        
        // Check if BLE is connected
        if (!BleOperateManager.getInstance().isConnected()) {
            XLog.e("BLE not connected, cannot start OTA");
            return;
        }
        
        // Trigger WiFiP2P on glasses via BLE
        LargeDataHandler.getInstance().writeIpToSoc(serverUrl, new ILargeDataResponse() {
            @Override
            public void parseData(int cmdType, BaseResponse response) {
                if (response != null && response.getErrorCode() == 0) {
                    XLog.d("WiFiP2P triggered successfully on glasses");
                    bleCallbackSuccess = true;
                    startWiFiP2PDiscovery();
                } else {
                    XLog.e("Failed to trigger WiFiP2P on glasses");
                    bleCallbackSuccess = false;
                }
            }
        });
    }
    
    private void startSocOtaServer(String ipAddress) {
        XLog.e("systemSuccess: " + systemSuccess + ", bleCallbackSuccess: " + bleCallbackSuccess);
        
        if (systemSuccess && bleCallbackSuccess) {
            if (startServer) {
                return;
            }
            
            WifiP2pManagerSingleton.INSTANCE.getInstance().setConnect(true);
            failRetry = 0;
            handler.removeCallbacks(p2pConnectFailRunnable);
            systemSuccess = false;
            bleCallbackSuccess = false;
            startServer(ipAddress);
            
            String serverUrl = "http://" + ipAddress + ':' + PORT + "/" + wifiFirmWareName;
            XLog.e("Server URL: " + serverUrl);
            
            // Notify glasses about server URL
            LargeDataHandler.getInstance().writeIpToSoc(serverUrl, new ILargeDataResponse() {
                @Override
                public void parseData(int cmdType, BaseResponse response) {
                    // Handle response
                }
            });
        } else {
            XLog.e("systemSuccess: " + systemSuccess + ", bleCallbackSuccess: " + bleCallbackSuccess);
        }
    }
    
    /**
     * WiFiP2P callback implementations
     */
    @Override
    public void onConnected(WifiP2pInfo info) throws SocketException {
        XLog.d("WiFiP2P connected");
        
        try {
            String ipAddress = info.getGroupOwnerAddress().getHostAddress();
            XLog.d("Group owner IP: " + ipAddress);
            
            if (info.isGroupOwner()) {
                // Android device is group owner - start server
                startSocOtaServer(ipAddress);
            } else {
                // Android device is client - connect to glasses server
                // Implementation for client connection
            }
        } catch (Exception e) {
            XLog.e("Error in onConnected: " + e.getMessage());
        }
    }
    
    @Override
    public void onPeersChanged(Collection<? extends WifiP2pDevice> peers) {
        XLog.d("Peers changed: " + peers.size() + " devices");
        
        for (WifiP2pDevice wifiP2pDevice : peers) {
            if (!wifiP2pDevice.getDeviceName().equalsIgnoreCase(
                UserConfig.INSTANCE.getInstance().getGlassDeviceWifiName())) {
                
                String deviceName = wifiP2pDevice.getDeviceName();
                XLog.i(deviceName + " 1+++++ " + wifiP2pDevice.getDeviceAddress());
                WifiP2pManagerSingleton.INSTANCE.getInstance().connectToDevice(wifiP2pDevice);
                break;
            }
        }
    }
    
    @Override
    public void onWifiP2pEnabled() {
        XLog.i("onWifiP2pEnabled");
    }
    
    @Override
    public void onWifiP2pDisabled() {
        XLog.i("onWifiP2pDisabled");
    }
    
    /**
     * File transfer methods
     */
    private void startServer(String ipAddress) {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(PORT);
                XLog.d("File transfer server started on port " + PORT);
                startServer = true;
                
                while (startServer) {
                    Socket clientSocket = serverSocket.accept();
                    XLog.d("Client connected: " + clientSocket.getInetAddress());
                    handleClient(clientSocket);
                }
            } catch (IOException e) {
                XLog.e("Server error: " + e.getMessage());
            }
        }).start();
    }
    
    private void handleClient(Socket socket) {
        new Thread(() -> {
            try {
                // Handle file transfer from glasses
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                
                // Implement file transfer logic here
                // This would include receiving media.config and individual files
                
            } catch (IOException e) {
                XLog.e("Client handling error: " + e.getMessage());
            }
        }).start();
    }
    
    /**
     * Helper methods
     */
    private void startWiFiP2PDiscovery() {
        WifiP2pManagerSingleton.INSTANCE.getInstance().resetFailCount();
        WifiP2pManagerSingleton.INSTANCE.getInstance().startPeerDiscovery();
    }
    
    /**
     * Inner classes
     */
    private class MyDeviceNotifyListener extends GlassesDeviceNotifyListener {
        @Override
        public void parseData(int cmdType, GlassesDeviceNotifyRsp response) {
            // Handle device notifications
        }
    }
    
    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            // Implementation for periodic tasks
        }
    }
    
    private class P2pConnectFailRunnable implements Runnable {
        @Override
        public void run() {
            // Handle P2P connection failures
            XLog.e("P2P connection failed, retrying...");
            startWiFiP2PDiscovery();
        }
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        // Cleanup resources
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                XLog.e("Error closing server socket: " + e.getMessage());
            }
        }
        
        WifiP2pManagerSingleton.INSTANCE.getInstance().unregisterReceiver();
        WifiP2pManagerSingleton.INSTANCE.getInstance().removeCallback();
        
        if (wakeLock != null && wakeLock.isHeld()) {
            wakeLock.release();
        }
    }
}
