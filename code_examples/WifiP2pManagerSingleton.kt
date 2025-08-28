/**
 * WiFiP2P Manager Singleton (Kotlin Version)
 * Integrated with GlassesSDKSample's BLE approach
 * Based on: heycyan_rev/_log_review/app/src/main/java/sources/com/glasssutdio/wear/wifi/p2p/WifiP2pManagerSingleton.java
 */

package com.sdk.glassessdksample.wifip2p

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.p2p.WifiP2pConfig
import android.net.wifi.p2p.WifiP2pDevice
import android.net.wifi.p2p.WifiP2pInfo
import android.net.wifi.p2p.WifiP2pManager
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.elvishew.xlog.XLog
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.Executors

class WifiP2pManagerSingleton private constructor(private val context: Context) {
    
    companion object {
        private const val TAG = "WifiP2pManagerSingleton"
        private const val DEFAULT_PORT = 8888
        
        @Volatile
        private var instance: WifiP2pManagerSingleton? = null
        
        fun getInstance(context: Context): WifiP2pManagerSingleton {
            return instance ?: synchronized(this) {
                instance ?: WifiP2pManagerSingleton(context).also { instance = it }
            }
        }
    }
    
    // WiFiP2P components
    private val wifiP2pManager: WifiP2pManager by lazy {
        context.getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager
    }
    
    private var wifiP2pChannel: WifiP2pManager.Channel? = null
    private var callback: WifiP2pCallback? = null
    
    // Connection state
    private var isConnected = false
    private var isConnecting = false
    private var connectRetry = 0
    private var discoveryRetry = 0
    private var wifiP2pDevice: WifiP2pDevice? = null
    
    // Network components
    private var serverSocket: ServerSocket? = null
    private var clientSocket: Socket? = null
    private val networkExecutor = Executors.newSingleThreadExecutor()
    
    // Timeout handlers
    private val handler = Handler(Looper.getMainLooper())
    private val discoveryTimeOut = DiscoveryTimeOut(this)
    private val connectTimeOut = ConnectTimeOut(this)
    
    // Broadcast receiver
    private val receiver = WifiP2pBroadcastReceiver(this)
    private val intentFilter = IntentFilter().apply {
        addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION)
        addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION)
        addAction(WifiP2pManager.WIFI_P2P_CONNECTION_STATE_CHANGE_ACTION)
        addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION)
    }
    
    // Discovered peers
    val discoveredPeers = mutableListOf<WifiP2pDevice>()
    
    init {
        initializeWiFiP2P()
    }
    
    /**
     * Initialize WiFiP2P manager
     */
    private fun initializeWiFiP2P() {
        wifiP2pChannel = wifiP2pManager.initialize(context, context.mainLooper) {
            XLog.e("wifiP2pChannel disconnect")
            callback?.onDisconnected()
        }
    }
    
    /**
     * Set callback for WiFiP2P events
     */
    fun setCallback(callback: WifiP2pCallback) {
        this.callback = callback
    }
    
    /**
     * Register broadcast receiver
     */
    fun registerReceiver() {
        try {
            context.registerReceiver(receiver, intentFilter)
        } catch (e: Exception) {
            Log.e(TAG, "Error registering receiver", e)
        }
    }
    
    /**
     * Unregister broadcast receiver
     */
    fun unregisterReceiver() {
        try {
            context.unregisterReceiver(receiver)
        } catch (e: Exception) {
            Log.e(TAG, "Error unregistering receiver", e)
        }
    }
    
    /**
     * Start peer discovery
     */
    fun startPeerDiscovery() {
        if (!isConnected && !isConnecting) {
            XLog.d("Start peer discovery")
            callback?.onPeerDiscoveryStarted()
            
            wifiP2pManager.discoverPeers(wifiP2pChannel, object : WifiP2pManager.ActionListener {
                override fun onSuccess() {
                    XLog.d("Peer discovery started successfully")
                    startDiscoveryTimeout()
                }
                
                override fun onFailure(reason: Int) {
                    XLog.e("Peer discovery failed: $reason")
                    callback?.onPeerDiscoveryFailed(reason)
                    handleDiscoveryFailure()
                }
            })
        }
    }
    
    /**
     * Connect to a specific device
     */
    fun connectToDevice(device: WifiP2pDevice) {
        if (!isConnected && !isConnecting) {
            XLog.d("Connecting to device: ${device.deviceName}")
            callback?.connecting()
            
            wifiP2pDevice = device
            isConnecting = true
            
            val config = WifiP2pConfig().apply {
                deviceAddress = device.deviceAddress
            }
            
            wifiP2pManager.connect(wifiP2pChannel, config, object : WifiP2pManager.ActionListener {
                override fun onSuccess() {
                    XLog.d("Connect request sent successfully")
                    callback?.onConnectRequestSent()
                    startConnectTimeout()
                }
                
                override fun onFailure(reason: Int) {
                    XLog.e("Connect request failed: $reason")
                    callback?.onConnectRequestFailed(reason)
                    handleConnectFailure()
                }
            })
        }
    }
    
    /**
     * Cancel P2P connection
     */
    fun cancelP2pConnection() {
        if (isConnecting) {
            XLog.d("Canceling P2P connection")
            wifiP2pManager.cancelConnect(wifiP2pChannel, object : WifiP2pManager.ActionListener {
                override fun onSuccess() {
                    XLog.d("Cancel connect request sent")
                    callback?.cancelConnect()
                    resetConnectionState()
                }
                
                override fun onFailure(reason: Int) {
                    XLog.e("Cancel connect failed: $reason")
                    callback?.cancelConnectFail(reason)
                }
            })
        }
    }
    
    /**
     * Handle connection success
     */
    fun onConnectionSuccess(info: WifiP2pInfo?) {
        isConnecting = false
        isConnected = true
        connectRetry = 0
        XLog.d("WiFiP2P connected successfully")
        callback?.onConnected(info)
        
        // Start server socket for file transfer
        startServerSocket()
    }
    
    /**
     * Handle connection failure
     */
    private fun handleConnectFailure() {
        isConnecting = false
        connectRetry++
        
        if (connectRetry < MAX_CONNECT_RETRY) {
            XLog.d("Retrying connection: $connectRetry/$MAX_CONNECT_RETRY")
            handler.postDelayed({
                wifiP2pDevice?.let { connectToDevice(it) }
            }, CONNECT_RETRY_DELAY)
        } else {
            XLog.e("Max connect retries reached")
            callback?.retryAlsoFailed()
            resetConnectionState()
        }
    }
    
    /**
     * Handle discovery failure
     */
    private fun handleDiscoveryFailure() {
        discoveryRetry++
        
        if (discoveryRetry < MAX_DISCOVERY_RETRY) {
            XLog.d("Retrying discovery: $discoveryRetry/$MAX_DISCOVERY_RETRY")
            handler.postDelayed({
                startPeerDiscovery()
            }, DISCOVERY_RETRY_DELAY)
        } else {
            XLog.e("Max discovery retries reached")
            resetDiscoveryState()
        }
    }
    
    /**
     * Reset connection state
     */
    private fun resetConnectionState() {
        isConnecting = false
        isConnected = false
        wifiP2pDevice = null
        stopServerSocket()
    }
    
    /**
     * Reset discovery state
     */
    private fun resetDiscoveryState() {
        discoveryRetry = 0
        discoveredPeers.clear()
    }
    
    /**
     * Start discovery timeout
     */
    private fun startDiscoveryTimeout() {
        handler.postDelayed(discoveryTimeOut, DISCOVERY_TIMEOUT)
    }
    
    /**
     * Start connect timeout
     */
    private fun startConnectTimeout() {
        handler.postDelayed(connectTimeOut, CONNECT_TIMEOUT)
    }
    
    /**
     * Start server socket for file transfer
     */
    private fun startServerSocket() {
        networkExecutor.execute {
            try {
                serverSocket = ServerSocket(DEFAULT_PORT)
                XLog.d("Server socket started on port $DEFAULT_PORT")
                
                while (isConnected) {
                    try {
                        val client = serverSocket?.accept()
                        client?.let {
                            XLog.d("Client connected: ${it.inetAddress}")
                            handleClientConnection(it)
                        }
                    } catch (e: Exception) {
                        if (isConnected) {
                            XLog.e("Error accepting client", e)
                        }
                    }
                }
            } catch (e: Exception) {
                XLog.e("Error starting server socket", e)
            }
        }
    }
    
    /**
     * Handle client connection
     */
    private fun handleClientConnection(client: Socket) {
        // Handle file transfer communication
        try {
            // Read file transfer requests and handle accordingly
            val input = client.getInputStream()
            val buffer = ByteArray(1024)
            var bytesRead: Int
            
            while (client.isConnected && input.read(buffer).also { bytesRead = it } != -1) {
                // Process incoming data
                val data = buffer.copyOf(bytesRead)
                processReceivedData(data)
            }
        } catch (e: Exception) {
            XLog.e("Error handling client connection", e)
        } finally {
            try {
                client.close()
            } catch (e: Exception) {
                XLog.e("Error closing client socket", e)
            }
        }
    }
    
    /**
     * Process received data
     */
    private fun processReceivedData(data: ByteArray) {
        // Process file transfer commands from glasses
        XLog.d("Received data: ${data.size} bytes")
        // TODO: Implement file transfer protocol
    }
    
    /**
     * Stop server socket
     */
    private fun stopServerSocket() {
        try {
            serverSocket?.close()
            serverSocket = null
        } catch (e: Exception) {
            XLog.e("Error closing server socket", e)
        }
    }
    
    /**
     * Check if connected
     */
    fun isConnected(): Boolean = isConnected
    
    /**
     * Get discovered peers
     */
    fun getDiscoveredPeers(): List<WifiP2pDevice> = discoveredPeers.toList()
    
    /**
     * WiFiP2P Callback Interface
     */
    interface WifiP2pCallback {
        fun onPeerDiscoveryStarted()
        fun onPeerDiscoveryFailed(reason: Int)
        fun onPeersChanged(peers: Collection<WifiP2pDevice>?)
        fun onThisDeviceChanged(device: WifiP2pDevice?)
        fun onWifiP2pEnabled()
        fun onWifiP2pDisabled()
        fun onConnectRequestSent()
        fun onConnectRequestFailed(reason: Int)
        fun onConnected(info: WifiP2pInfo?)
        fun onDisconnected()
        fun connecting()
        fun cancelConnect()
        fun cancelConnectFail(reason: Int)
        fun retryAlsoFailed()
    }
    
    /**
     * Discovery timeout runnable
     */
    private class DiscoveryTimeOut(private val manager: WifiP2pManagerSingleton) : Runnable {
        override fun run() {
            XLog.d("Discovery timeout")
            manager.wifiP2pManager.stopPeerDiscovery(manager.wifiP2pChannel, null)
        }
    }
    
    /**
     * Connect timeout runnable
     */
    private class ConnectTimeOut(private val manager: WifiP2pManagerSingleton) : Runnable {
        override fun run() {
            XLog.d("Connect timeout")
            manager.handleConnectFailure()
        }
    }
    
    /**
     * Broadcast receiver for WiFiP2P events
     */
    private class WifiP2pBroadcastReceiver(private val manager: WifiP2pManagerSingleton) : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let { handleIntent(it) }
        }
        
        private fun handleIntent(intent: Intent) {
            when (intent.action) {
                WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION -> {
                    val state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1)
                    if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                        XLog.d("WiFiP2P enabled")
                        manager.callback?.onWifiP2pEnabled()
                    } else {
                        XLog.d("WiFiP2P disabled")
                        manager.callback?.onWifiP2pDisabled()
                    }
                }
                
                WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION -> {
                    manager.wifiP2pManager.requestPeers(manager.wifiP2pChannel) { peers ->
                        manager.discoveredPeers.clear()
                        peers.deviceList.values.forEach { device ->
                            manager.discoveredPeers.add(device)
                        }
                        XLog.d("Peers changed: ${manager.discoveredPeers.size} devices")
                        manager.callback?.onPeersChanged(manager.discoveredPeers)
                    }
                }
                
                WifiP2pManager.WIFI_P2P_CONNECTION_STATE_CHANGE_ACTION -> {
                    manager.wifiP2pManager.requestConnectionInfo(manager.wifiP2pChannel) { info ->
                        if (info.groupFormed) {
                            XLog.d("Group formed, connected: ${info.isGroupOwner}")
                            manager.onConnectionSuccess(info)
                        } else {
                            XLog.d("Group not formed, disconnected")
                            manager.isConnected = false
                            manager.isConnecting = false
                            manager.callback?.onDisconnected()
                        }
                    }
                }
                
                WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION -> {
                    val device = intent.getParcelableExtra<WifiP2pDevice>(WifiP2pManager.EXTRA_WIFI_P2P_DEVICE)
                    XLog.d("This device changed: ${device?.deviceName}")
                    manager.callback?.onThisDeviceChanged(device)
                }
            }
        }
    }
    
    companion object {
        private const val MAX_CONNECT_RETRY = 3
        private const val MAX_DISCOVERY_RETRY = 3
        private const val CONNECT_RETRY_DELAY = 2000L
        private const val DISCOVERY_RETRY_DELAY = 1000L
        private const val DISCOVERY_TIMEOUT = 30000L
        private const val CONNECT_TIMEOUT = 10000L
    }
}
