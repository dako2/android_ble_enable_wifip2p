/**
 * WiFiP2P Manager Singleton - Manages WiFiP2P device discovery and connections
 * File: heycyan_rev/_log_review/app/src/main/java/sources/com/glasssutdio/wear/wifi/p2p/WifiP2pManagerSingleton.java
 */

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Handler;
import android.os.Looper;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.ILargeDataResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.resp.BaseResponse;
import com.oudmon.ble.base.communication.bigData.resp.GlassModelControlResponse;

import java.util.Collection;

public final class WifiP2pManagerSingleton {
    
    public static final Companion INSTANCE = new Companion();
    private static volatile WifiP2pManagerSingleton instance;
    
    private WifiP2pCallback callback;
    private int connectRetry;
    private final ConnectTimeOut connectTimeOut;
    private boolean connected;
    private boolean connecting;
    private final Context context;
    private int discoveryRetry;
    private final DiscoveryTimeOut discoveryTimeOut;
    private final Handler handler;
    private final IntentFilter intentFilter;
    private final WifiP2pBroadcastReceiver receiver;
    private WifiP2pManager.Channel wifiP2pChannel;
    private WifiP2pDevice wifiP2pDevice;
    private WifiP2pManager wifiP2pManager;
    
    /**
     * WiFiP2P Callback Interface
     */
    public interface WifiP2pCallback {
        void cancelConnect();
        void cancelConnectFail(int reason);
        void connecting();
        void onConnectRequestFailed(int reason);
        void onConnectRequestSent();
        void onConnected(WifiP2pInfo info);
        void onDisconnected();
        void onPeerDiscoveryFailed(int reason);
        void onPeerDiscoveryStarted();
        void onPeersChanged(Collection<? extends WifiP2pDevice> peers);
        void onThisDeviceChanged(WifiP2pDevice device);
        void onWifiP2pDisabled();
        void onWifiP2pEnabled();
        void retryAlsoFailed();
    }
    
    /**
     * Constructor
     */
    private WifiP2pManagerSingleton(Context context) {
        this.context = context;
        Object systemService = context.getSystemService("wifip2p");
        this.wifiP2pManager = (WifiP2pManager) systemService;
        
        this.wifiP2pChannel = wifiP2pManager.initialize(context, context.getMainLooper(), 
            new WifiP2pManager.ChannelListener() {
                @Override
                public void onChannelDisconnected() {
                    XLog.e("wifiP2pChannel disconnect");
                }
            });
            
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_STATE_CHANGE_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        this.intentFilter = intentFilter;
        
        this.handler = new Handler(context.getMainLooper());
        this.discoveryTimeOut = new DiscoveryTimeOut(this);
        this.connectTimeOut = new ConnectTimeOut(this);
        this.receiver = new WifiP2pBroadcastReceiver(this);
    }
    
    /**
     * Public methods
     */
    public void registerReceiver() {
        this.context.registerReceiver(this.receiver, this.intentFilter);
    }
    
    public void unregisterReceiver() {
        this.context.unregisterReceiver(this.receiver);
    }
    
    public void resetPeerDiscovery() {
        this.handler.removeCallbacks(this.discoveryTimeOut);
    }
    
    public void resetFailCount() {
        this.connectRetry = 0;
        this.discoveryRetry = 0;
        this.connecting = false;
        setConnect(false);
        this.handler.removeCallbacks(this.discoveryTimeOut);
        this.handler.removeCallbacks(this.connectTimeOut);
    }
    
    public void startPeerDiscovery() {
        this.handler.postDelayed(this.discoveryTimeOut, 16000L);
        this.wifiP2pManager.discoverPeers(this.wifiP2pChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                if (callback != null) {
                    callback.onPeerDiscoveryStarted();
                }
            }
            
            @Override
            public void onFailure(int reason) {
                handler.removeCallbacks(discoveryTimeOut);
                handler.postDelayed(discoveryTimeOut, 2000L);
                if (callback != null) {
                    callback.onPeerDiscoveryFailed(reason);
                }
            }
        });
    }
    
    public void setConnect(boolean connected) {
        this.connected = connected;
    }
    
    public void connectToDevice(WifiP2pDevice device) {
        try {
            resetPeerDiscovery();
            if (this.connected) {
                XLog.i("P2P已经连接上了，直接返回");
                return;
            }
            if (this.connecting) {
                if (callback != null) {
                    callback.connecting();
                }
                XLog.i("P2P正在连接,不调用连接返回");
                return;
            }
            this.handler.postDelayed(this.connectTimeOut, 15000L);
            this.wifiP2pDevice = device;
            WifiP2pConfig wifiP2pConfig = new WifiP2pConfig();
            wifiP2pConfig.deviceAddress = device.deviceAddress;
            wifiP2pConfig.wps.setup = 0;
            this.connecting = true;
            XLog.i("已经在连接设备:" + device.deviceName);
            this.wifiP2pManager.connect(this.wifiP2pChannel, wifiP2pConfig, 
                new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess() {
                        if (callback != null) {
                            callback.onConnectRequestSent();
                        }
                    }
                    
                    @Override
                    public void onFailure(int reason) {
                        connecting = false;
                        if (callback != null) {
                            callback.onConnectRequestFailed(reason);
                        }
                    }
                });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cancelP2pConnection() {
        try {
            initP2P();
            this.wifiP2pManager.cancelConnect(this.wifiP2pChannel, 
                new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess() {
                        if (callback != null) {
                            callback.cancelConnect();
                        }
                    }
                    
                    @Override
                    public void onFailure(int reason) {
                        if (callback != null) {
                            callback.cancelConnectFail(reason);
                        }
                    }
                });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addCallback(WifiP2pCallback callback) {
        this.callback = callback;
    }
    
    public void removeCallback() {
        instance = null;
    }
    
    /**
     * Private helper methods
     */
    private void resetDeviceP2p() {
        LargeDataHandler.getInstance().glassesControl(new byte[]{2, 1, 15}, 
            new ILargeDataResponse() {
                @Override
                public void parseData(int cmdType, BaseResponse response) {
                    // Handle response
                }
            });
    }
    
    private void initP2P() {
        this.wifiP2pChannel.close();
        Object systemService = this.context.getSystemService("wifip2p");
        this.wifiP2pManager = (WifiP2pManager) systemService;
        this.wifiP2pChannel = wifiP2pManager.initialize(this.context, Looper.getMainLooper(), 
            new WifiP2pManager.ChannelListener() {
                @Override
                public void onChannelDisconnected() {
                    XLog.i("wifiP2pChannel initP2P");
                }
            });
    }
    
    /**
     * Inner classes
     */
    private static final class DiscoveryTimeOut implements Runnable {
        private final WifiP2pManagerSingleton outer;
        
        public DiscoveryTimeOut(WifiP2pManagerSingleton outer) {
            this.outer = outer;
        }
        
        @Override
        public void run() {
            XLog.i("内部扫描重试连接:" + this.outer.discoveryRetry);
            if (this.outer.discoveryRetry < 1) {
                XLog.i("内部扫描重试连接一次");
                this.outer.resetDeviceP2p();
                this.outer.initP2P();
                this.outer.startPeerDiscovery();
                this.outer.discoveryRetry++;
            }
        }
    }
    
    private static final class ConnectTimeOut implements Runnable {
        private final WifiP2pManagerSingleton outer;
        
        public ConnectTimeOut(WifiP2pManagerSingleton outer) {
            this.outer = outer;
        }
        
        @Override
        public void run() {
            this.outer.connecting = false;
            if (this.outer.connectRetry < 1) {
                WifiP2pDevice wifiP2pDevice = this.outer.wifiP2pDevice;
                if (wifiP2pDevice != null) {
                    XLog.i("内部连接重试连接一次");
                    this.outer.connectToDevice(wifiP2pDevice);
                }
                this.outer.connectRetry++;
            } else {
                XLog.i("不重连，等外部超时");
                if (this.outer.callback != null) {
                    this.outer.callback.retryAlsoFailed();
                }
            }
        }
    }
    
    private static final class WifiP2pBroadcastReceiver extends BroadcastReceiver {
        private final WifiP2pManagerSingleton outer;
        
        public WifiP2pBroadcastReceiver(WifiP2pManagerSingleton outer) {
            this.outer = outer;
        }
        
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            
            if (action == null) return;
            
            switch (action) {
                case WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION:
                    int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
                    if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                        XLog.d("WiFi P2P enabled");
                        if (outer.callback != null) {
                            outer.callback.onWifiP2pEnabled();
                        }
                    } else {
                        XLog.d("WiFi P2P disabled");
                        if (outer.callback != null) {
                            outer.callback.onWifiP2pDisabled();
                        }
                    }
                    break;
                    
                case WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION:
                    outer.wifiP2pManager.requestPeers(outer.wifiP2pChannel, peers -> {
                        Collection<WifiP2pDevice> deviceList = peers.getDeviceList();
                        XLog.d("Peers changed: " + deviceList.size() + " devices");
                        if (outer.callback != null) {
                            outer.callback.onPeersChanged(deviceList);
                        }
                    });
                    break;
                    
                case WifiP2pManager.WIFI_P2P_CONNECTION_STATE_CHANGE_ACTION:
                    outer.wifiP2pManager.requestConnectionInfo(outer.wifiP2pChannel, info -> {
                        if (info.getGroupFormed()) {
                            XLog.d("Connected to group");
                            outer.connecting = false;
                            outer.connected = true;
                            outer.handler.removeCallbacks(outer.connectTimeOut);
                            if (outer.callback != null) {
                                outer.callback.onConnected(info);
                            }
                        } else {
                            XLog.d("Disconnected from group");
                            outer.connecting = false;
                            outer.connected = false;
                            if (outer.callback != null) {
                                outer.callback.onDisconnected();
                            }
                        }
                    });
                    break;
                    
                case WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION:
                    WifiP2pDevice device = intent.getParcelableExtra(WifiP2pManager.EXTRA_WIFI_P2P_DEVICE);
                    if (device != null && outer.callback != null) {
                        outer.callback.onThisDeviceChanged(device);
                    }
                    break;
            }
        }
    }
    
    public static final class Companion {
        private Companion() {}
        
        public WifiP2pManagerSingleton getInstance() {
            WifiP2pManagerSingleton wifiP2pManagerSingleton = WifiP2pManagerSingleton.instance;
            if (wifiP2pManagerSingleton == null) {
                synchronized (this) {
                    wifiP2pManagerSingleton = WifiP2pManagerSingleton.instance;
                    if (wifiP2pManagerSingleton == null) {
                        Application application = GlassesWearJavaApplication.getInstance().getApplication();
                        wifiP2pManagerSingleton = new WifiP2pManagerSingleton(application);
                        WifiP2pManagerSingleton.instance = wifiP2pManagerSingleton;
                    }
                }
            }
            return wifiP2pManagerSingleton;
        }
    }
}
