package com.example.blewifip2p.manager

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

class WiFiP2PManager(private val context: Context) {
    
    companion object {
        private const val TAG = "WiFiP2PManager"
        private const val DEFAULT_PORT = 8888
        private const val MAX_CONNECT_RETRY = 3
        private const val MAX_DISCOVERY_RETRY = 3
        private const val CONNECT_RETRY_DELAY = 2000L
        private const val DISCOVERY_RETRY_DELAY = 1000L
        private const val DISCOVERY_TIMEOUT = 30000L
        private const val CONNECT_TIMEOUT = 10000L
    }
    
    // Callbacks
    private var connectionCallback: ((Boolean) -> Unit)? = null
    private var deviceFoundCallback: ((List<WifiP2pDevice>) -> Unit)? = null
    
    // WiFiP2P components
    private val wifiP2pManager: WifiP2pManager by lazy {
        context.getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager
    }
    
    private var wifiP2pChannel: WifiP2pManager.Channel? = null
    private var callback: WiFiP2PCallback? = null
    
    // Connection state
    private var isInitialized = false
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
    private val discoveryTimeOut = DiscoveryTimeOut()
    private val connectTimeOut = ConnectTimeOut()
    
    // Broadcast receiver
    private val receiver = WiFiP2PBroadcastReceiver()
    private val intentFilter = IntentFilter().apply {
        addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION)
        addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION)
        addAction("android.net.wifi.p2p.CONNECTION_STATE_CHANGE")
        addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION)
    }
    
    // Discovered peers
    private val discoveredPeers = mutableListOf<WifiP2pDevice>()
    
    /**
     * Initialize WiFiP2P manager
     */
    fun initialize() {
        if (isInitialized) return
        
        try {
            wifiP2pChannel = wifiP2pManager.initialize(context, context.mainLooper) {
                XLog.e("wifiP2pChannel disconnect")
                callback?.onDisconnected()
                isConnected = false
                connectionCallback?.invoke(false)
            }
            
            isInitialized = true
            XLog.d("WiFiP2P Manager initialized")
        } catch (e: Exception) {
            XLog.e("Failed to initialize WiFiP2P Manager", e)
        }
    }
    
    /**
     * Register broadcast receiver
     */
    fun registerReceiver() {
        try {
            context.registerReceiver(receiver, intentFilter)
            XLog.d("WiFiP2P broadcast receiver registered")
        } catch (e: Exception) {
            XLog.e("Error registering WiFiP2P receiver", e)
        }
    }
    
    /**
     * Unregister broadcast receiver
     */
    fun unregisterReceiver() {
        try {
            context.unregisterReceiver(receiver)
            XLog.d("WiFiP2P broadcast receiver unregistered")
        } catch (e: Exception) {
            XLog.e("Error unregistering WiFiP2P receiver", e)
        }
    }
    
    /**
     * Start peer discovery
     */
    fun startDiscovery() {
        if (!isInitialized || isConnecting) return
        
        try {
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
        } catch (e: Exception) {
            XLog.e("Error starting peer discovery", e)
        }
    }
    
    /**
     * Connect to a specific device
     */
    fun connectToDevice(device: WifiP2pDevice) {
        if (!isInitialized || isConnecting) return
        
        try {
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
        } catch (e: Exception) {
            XLog.e("Error connecting to device", e)
            handleConnectFailure()
        }
    }
    
    /**
     * Cancel P2P connection
     */
    fun cancelConnection() {
        if (isConnecting) {
            try {
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
            } catch (e: Exception) {
                XLog.e("Error canceling connection", e)
            }
        }
    }
    
    /**
     * Handle connection success
     */
    private fun onConnectionSuccess(info: WifiP2pInfo?) {
        isConnecting = false
        isConnected = true
        connectRetry = 0
        XLog.d("WiFiP2P connected successfully")
        callback?.onConnected(info)
        connectionCallback?.invoke(true)
        
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
            connectionCallback?.invoke(false)
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
                startDiscovery()
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
            var bytesRead: Int = 0
            
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
     * Set connection callback
     */
    fun setConnectionCallback(callback: (Boolean) -> Unit) {
        this.connectionCallback = callback
    }
    
    /**
     * Set device found callback
     */
    fun setDeviceFoundCallback(callback: (List<WifiP2pDevice>) -> Unit) {
        this.deviceFoundCallback = callback
    }
    
    /**
     * Set WiFiP2P callback
     */
    fun setCallback(callback: WiFiP2PCallback) {
        this.callback = callback
    }
    
    /**
     * Cleanup resources
     */
    fun cleanup() {
        try {
            unregisterReceiver()
            if (isConnected || isConnecting) {
                cancelConnection()
            }
            stopServerSocket()
            XLog.d("WiFiP2P Manager cleaned up")
        } catch (e: Exception) {
            XLog.e("Error during WiFiP2P Manager cleanup", e)
        }
    }
    
    /**
     * WiFiP2P Callback Interface
     */
    interface WiFiP2PCallback {
        fun onPeerDiscoveryStarted()
        fun onPeerDiscoveryFailed(reason: Int)
        fun onPeersChanged(peers: List<WifiP2pDevice>)
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
    private inner class DiscoveryTimeOut : Runnable {
        override fun run() {
            XLog.d("Discovery timeout")
            try {
                wifiP2pManager.stopPeerDiscovery(wifiP2pChannel, null)
            } catch (e: Exception) {
                XLog.e("Error stopping peer discovery", e)
            }
        }
    }
    
    /**
     * Connect timeout runnable
     */
    private inner class ConnectTimeOut : Runnable {
        override fun run() {
            XLog.d("Connect timeout")
            handleConnectFailure()
        }
    }
    
    /**
     * Broadcast receiver for WiFiP2P events
     */
    private inner class WiFiP2PBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let { handleIntent(it) }
        }
        
        private fun handleIntent(intent: Intent) {
            when (intent.action) {
                WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION -> {
                    val state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1)
                    if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                        XLog.d("WiFiP2P enabled")
                        callback?.onWifiP2pEnabled()
                    } else {
                        XLog.d("WiFiP2P disabled")
                        callback?.onWifiP2pDisabled()
                    }
                }
                
                WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION -> {
                    wifiP2pManager.requestPeers(wifiP2pChannel) { peers ->
                        discoveredPeers.clear()
                        val deviceList = peers.deviceList
                        for (device in deviceList) {
                            discoveredPeers.add(device)
                        }
                        XLog.d("Peers changed: ${discoveredPeers.size} devices")
                        callback?.onPeersChanged(discoveredPeers.toList())
                        deviceFoundCallback?.invoke(discoveredPeers.toList())
                    }
                }
                
                "android.net.wifi.p2p.CONNECTION_STATE_CHANGE" -> {
                    wifiP2pManager.requestConnectionInfo(wifiP2pChannel) { info ->
                        if (info.groupFormed) {
                            XLog.d("Group formed, connected: ${info.isGroupOwner}")
                            onConnectionSuccess(info)
                        } else {
                            XLog.d("Group not formed, disconnected")
                            isConnected = false
                            isConnecting = false
                            callback?.onDisconnected()
                            connectionCallback?.invoke(false)
                        }
                    }
                }
                
                WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION -> {
                    val device = intent.getParcelableExtra<WifiP2pDevice>(WifiP2pManager.EXTRA_WIFI_P2P_DEVICE)
                    XLog.d("This device changed: ${device?.deviceName}")
                    callback?.onThisDeviceChanged(device)
                }
            }
        }
    }
}
