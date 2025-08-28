/**
 * Integrated MainActivity - Combines GlassesSDKSample BLE with WiFiP2P functionality
 * Based on: GlassesSDKSample/app/src/main/java/com/sdk/glassessdksample/MainActivity.kt
 */

package com.sdk.glassessdksample

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import android.net.wifi.p2p.WifiP2pDevice
import android.net.wifi.p2p.WifiP2pInfo
import android.net.wifi.p2p.WifiP2pManager
import android.net.wifi.p2p.WifiP2pManagerSingleton
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions
import com.oudmon.ble.base.bluetooth.BleOperateManager
import com.oudmon.ble.base.bluetooth.DeviceManager
import com.oudmon.ble.base.communication.LargeDataHandler
import com.oudmon.ble.base.communication.bigData.resp.GlassesDeviceNotifyListener
import com.oudmon.ble.base.communication.bigData.resp.GlassesDeviceNotifyRsp
import com.sdk.glassessdksample.databinding.AcitivytMainBinding
import com.sdk.glassessdksample.ui.BluetoothUtils
import com.sdk.glassessdksample.ui.DeviceBindActivity
import com.sdk.glassessdksample.ui.hasBluetooth
import com.sdk.glassessdksample.ui.requestAllPermission
import com.sdk.glassessdksample.ui.requestBluetoothPermission
import com.sdk.glassessdksample.ui.requestLocationPermission
import com.sdk.glassessdksample.ui.setOnClickListener
import com.sdk.glassessdksample.ui.startKtxActivity
import org.greenrobot.eventbus.EventBus

class IntegratedMainActivity : AppCompatActivity() {
    private lateinit var binding: AcitivytMainBinding
    private val deviceNotifyListener by lazy { IntegratedDeviceNotifyListener() }
    
    // WiFiP2P components
    private lateinit var wifiP2pManager: WifiP2pManagerSingleton
    private lateinit var fileTransferManager: FileTransferManager
    
    // Status tracking
    private var bleConnected = false
    private var wifiP2pConnected = false
    
    companion object {
        private const val TAG = "IntegratedMainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcitivytMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Initialize WiFiP2P components
        initWiFiP2P()
        initView()
        setupStatusDisplay()
    }
    
    /**
     * Initialize WiFiP2P functionality
     */
    private fun initWiFiP2P() {
        wifiP2pManager = WifiP2pManagerSingleton.getInstance(this)
        fileTransferManager = FileTransferManager(this)
        
        // Set up WiFiP2P callbacks
        wifiP2pManager.setCallback(object : WifiP2pManagerSingleton.WifiP2pCallback {
            override fun onPeerDiscoveryStarted() {
                Log.d(TAG, "WiFiP2P peer discovery started")
                showMessage("WiFiP2P scanning...")
            }
            
            override fun onPeersChanged(peers: Collection<WifiP2pDevice>?) {
                Log.d(TAG, "WiFiP2P peers changed: ${peers?.size} devices found")
                peers?.let { deviceList ->
                    if (deviceList.isNotEmpty()) {
                        showMessage("Found ${deviceList.size} WiFiP2P device(s)")
                    }
                }
            }
            
            override fun onConnectRequestSent() {
                Log.d(TAG, "WiFiP2P connect request sent")
                showMessage("Connecting to glasses via WiFiP2P...")
            }
            
            override fun onConnected(info: WifiP2pInfo?) {
                Log.d(TAG, "WiFiP2P connected: isGroupOwner=${info?.isGroupOwner}")
                wifiP2pConnected = true
                showMessage("WiFiP2P connected!")
                updateStatus()
            }
            
            override fun onDisconnected() {
                Log.d(TAG, "WiFiP2P disconnected")
                wifiP2pConnected = false
                showMessage("WiFiP2P disconnected")
                updateStatus()
            }
            
            override fun onConnectRequestFailed(reason: Int) {
                Log.e(TAG, "WiFiP2P connect failed: $reason")
                showMessage("WiFiP2P connect failed: $reason")
            }
            
            override fun onPeerDiscoveryFailed(reason: Int) {
                Log.e(TAG, "WiFiP2P discovery failed: $reason")
                showMessage("WiFiP2P discovery failed: $reason")
            }
            
            // Other callback methods with default implementations
            override fun connecting() {}
            override fun onWifiP2pEnabled() {}
            override fun onWifiP2pDisabled() {}
            override fun onThisDeviceChanged(device: WifiP2pDevice?) {}
            override fun cancelConnect() {}
            override fun cancelConnectFail(reason: Int) {}
            override fun retryAlsoFailed() {}
        })
    }
    
    /**
     * Set up status display
     */
    private fun setupStatusDisplay() {
        // Add status TextView if not exists
        // binding.tvStatus.text = "BLE: Disconnected | WiFiP2P: Disconnected"
        updateStatus()
    }
    
    /**
     * Update connection status display
     */
    private fun updateStatus() {
        val bleStatus = if (bleConnected) "Connected" else "Disconnected"
        val wifiStatus = if (wifiP2pConnected) "Connected" else "Disconnected"
        val statusText = "BLE: $bleStatus | WiFiP2P: $wifiStatus"
        
        // Update status display
        Log.d(TAG, statusText)
        showMessage(statusText)
    }
    
    /**
     * Show message to user
     */
    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Message: $message")
    }
    
    /**
     * Permission callback
     */
    inner class PermissionCallback : OnPermissionCallback {
        override fun onGranted(permissions: MutableList<String>, all: Boolean) {
            if (!all) {
                showMessage("Partial permissions granted")
            } else {
                startKtxActivity<DeviceBindActivity>()
            }
        }

        override fun onDenied(permissions: MutableList<String>, never: Boolean) {
            super.onDenied(permissions, never)
            if (never) {
                XXPermissions.startPermissionActivity(this@IntegratedMainActivity, permissions)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            if (!BluetoothUtils.isEnabledBluetooth(this)) {
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    if (ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.BLUETOOTH_CONNECT
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        return
                    }
                }
                startActivityForResult(intent, 300)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error requesting Bluetooth enable", e)
        }
        
        if (!hasBluetooth(this)) {
            requestBluetoothPermission(this, BluetoothPermissionCallback())
        }

        requestAllPermission(this, OnPermissionCallback { permissions, all ->  })
        
        // Register WiFiP2P receiver
        wifiP2pManager.registerReceiver()
    }
    
    override fun onPause() {
        super.onPause()
        // Unregister WiFiP2P receiver
        wifiP2pManager.unregisterReceiver()
    }

    inner class BluetoothPermissionCallback : OnPermissionCallback {
        override fun onGranted(permissions: MutableList<String>, all: Boolean) {
            if (!all) {
                showMessage("Partial Bluetooth permissions granted")
            }
        }

        override fun onDenied(permissions: MutableList<String>, never: Boolean) {
            super.onDenied(permissions, never)
            if (never) {
                XXPermissions.startPermissionActivity(this@IntegratedMainActivity, permissions)
            }
        }
    }

    /**
     * Initialize UI and button click handlers
     */
    private fun initView() {
        setOnClickListener(
            binding.btnScan,
            binding.btnConnect,
            binding.btnDisconnect,
            binding.btnAddListener,
            binding.btnSetTime,
            binding.btnVersion,
            binding.btnCamera,
            binding.btnVideo,
            binding.btnRecord,
            binding.btnThumbnail,
            binding.btnBt,
            binding.btnBattery,
            binding.btnVolume,
            binding.btnMediaCount
            // Add WiFiP2P buttons here when available
        ) {
            when (this) {
                binding.btnScan -> {
                    requestLocationPermission(this@IntegratedMainActivity, PermissionCallback())
                }

                binding.btnConnect -> {
                    BleOperateManager.getInstance()
                        .connectDirectly(DeviceManager.getInstance().deviceAddress)
                    bleConnected = true
                    updateStatus()
                }

                binding.btnDisconnect -> {
                    BleOperateManager.getInstance().unBindDevice()
                    bleConnected = false
                    updateStatus()
                }

                binding.btnAddListener -> {
                    LargeDataHandler.getInstance().addOutDeviceListener(100, deviceNotifyListener)
                    showMessage("Device listener added")
                }

                binding.btnSetTime -> {
                    Log.i(TAG, "setTime ${BleOperateManager.getInstance().isConnected}")
                    LargeDataHandler.getInstance().syncTime { _, _ -> }
                    showMessage("Time sync requested")
                }

                binding.btnVersion -> {
                    LargeDataHandler.getInstance().syncDeviceInfo { _, response ->
                        if (response != null) {
                            val versionInfo = """
                                WiFi Firmware: ${response.wifiFirmwareVersion}
                                WiFi Hardware: ${response.wifiHardwareVersion}
                                BLE Hardware: ${response.hardwareVersion}
                                BLE Firmware: ${response.firmwareVersion}
                            """.trimIndent()
                            showMessage("Version info retrieved")
                            Log.d(TAG, versionInfo)
                        }
                    }
                }

                binding.btnCamera -> {
                    LargeDataHandler.getInstance().glassesControl(
                        byteArrayOf(0x02, 0x01, 0x01)
                    ) { _, it ->
                        if (it.dataType == 1 && it.errorCode == 0) {
                            when (it.workTypeIng) {
                                2 -> showMessage("Glasses recording video")
                                4 -> {
                                    showMessage("Glasses in transfer mode - starting WiFiP2P")
                                    // Start WiFiP2P when glasses enter transfer mode
                                    wifiP2pManager.startPeerDiscovery()
                                }
                                5 -> showMessage("Glasses in OTA mode")
                                1, 6 -> showMessage("Glasses in camera mode")
                                7 -> showMessage("Glasses in AI dialogue")
                                8 -> showMessage("Glasses in recording mode")
                            }
                        } else {
                            showMessage("Camera control executed")
                        }
                    }
                }

                binding.btnVideo -> {
                    val videoStart = true
                    val value = if (videoStart) 0x02 else 0x03
                    LargeDataHandler.getInstance().glassesControl(
                        byteArrayOf(0x02, 0x01, value.toByte())
                    ) { _, it ->
                        if (it.dataType == 1) {
                            if (it.errorCode == 0) {
                                when (it.workTypeIng) {
                                    2 -> showMessage("Video recording active")
                                    4 -> {
                                        showMessage("Transfer mode - starting WiFiP2P")
                                        wifiP2pManager.startPeerDiscovery()
                                    }
                                    5 -> showMessage("OTA mode active")
                                    1, 6 -> showMessage("Camera mode active")
                                    7 -> showMessage("AI dialogue active")
                                    8 -> showMessage("Recording mode active")
                                }
                            } else {
                                showMessage("Video control executed")
                            }
                        }
                    }
                }

                binding.btnRecord -> {
                    val recordStart = true
                    val value = if (recordStart) 0x08 else 0x0c
                    LargeDataHandler.getInstance().glassesControl(
                        byteArrayOf(0x02, 0x01, value.toByte())
                    ) { _, it ->
                        if (it.dataType == 1) {
                            if (it.errorCode == 0) {
                                when (it.workTypeIng) {
                                    2 -> showMessage("Video recording active")
                                    4 -> {
                                        showMessage("Transfer mode - starting WiFiP2P")
                                        wifiP2pManager.startPeerDiscovery()
                                    }
                                    5 -> showMessage("OTA mode active")
                                    1, 6 -> showMessage("Camera mode active")
                                    7 -> showMessage("AI dialogue active")
                                    8 -> showMessage("Recording mode active")
                                }
                            } else {
                                showMessage("Record control executed")
                            }
                        }
                    }
                }

                binding.btnThumbnail -> {
                    val thumbnailSize = 0x02
                    LargeDataHandler.getInstance().glassesControl(
                        byteArrayOf(
                            0x02,
                            0x01,
                            0x06,
                            thumbnailSize.toByte(),
                            thumbnailSize.toByte(),
                            0x02
                        )
                    ) { _, it ->
                        if (it.dataType == 1) {
                            if (it.errorCode == 0) {
                                showMessage("Thumbnail requested")
                            } else {
                                showMessage("AI photo triggered")
                            }
                        }
                    }
                }

                binding.btnBt -> {
                    BleOperateManager.getInstance().classicBluetoothStartScan()
                    showMessage("Bluetooth scanning started")
                }
                
                binding.btnBattery -> {
                    LargeDataHandler.getInstance().addBatteryCallBack("init") { _, response ->
                        showMessage("Battery callback added")
                    }
                    LargeDataHandler.getInstance().syncBattery()
                    showMessage("Battery sync requested")
                }
                
                binding.btnVolume -> {
                    LargeDataHandler.getInstance().getVolumeControl { _, response ->
                        if (response != null) {
                            val volumeInfo = """
                                Music: ${response.currVolumeMusic}/${response.maxVolumeMusic}
                                Call: ${response.currVolumeCall}/${response.maxVolumeCall}
                                System: ${response.currVolumeSystem}/${response.maxVolumeSystem}
                            """.trimIndent()
                            showMessage("Volume info retrieved")
                            Log.d(TAG, volumeInfo)
                        }
                    }
                }
                
                binding.btnMediaCount -> {
                    LargeDataHandler.getInstance().glassesControl(byteArrayOf(0x02, 0x04)) { _, it ->
                        if (it.dataType == 4) {
                            val mediaCount = it.imageCount + it.videoCount + it.recordCount
                            if (mediaCount > 0) {
                                showMessage("$mediaCount media files available")
                                // If files available and both connections ready, offer transfer
                                if (bleConnected && wifiP2pConnected) {
                                    showMessage("Ready to transfer $mediaCount files")
                                }
                            } else {
                                showMessage("No media files available")
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Enhanced device notification listener with WiFiP2P integration
     */
    inner class IntegratedDeviceNotifyListener : GlassesDeviceNotifyListener() {

        @RequiresApi(Build.VERSION_CODES.O)
        override fun parseData(cmdType: Int, response: GlassesDeviceNotifyRsp) {
            when (response.loadData[6].toInt()) {
                // Glasses battery notification
                0x05 -> {
                    val battery = response.loadData[7].toInt()
                    val changing = response.loadData[8].toInt()
                    Log.d(TAG, "Battery: $battery%, Charging: ${changing == 1}")
                }
                
                // Glasses quick recognition
                0x02 -> {
                    if (response.loadData.size > 9 && response.loadData[9].toInt() == 0x02) {
                        showMessage("AI recognition triggered")
                    }
                    // Get picture thumbnail
                    LargeDataHandler.getInstance().getPictureThumbnails { cmdType, success, data ->
                        showMessage("Picture thumbnail received")
                    }
                }

                0x03 -> {
                    if (response.loadData[7].toInt() == 1) {
                        showMessage("Microphone activated")
                    }
                }
                
                // OTA upgrade progress
                0x04 -> {
                    try {
                        val download = response.loadData[7].toInt()
                        val soc = response.loadData[8].toInt()
                        val nor = response.loadData[9].toInt()
                        
                        val progressText = "OTA Progress - Download: $download%, SOC: $soc%, NOR: $nor%"
                        Log.d(TAG, progressText)
                        
                        // Enable WiFiP2P when glasses enter transfer mode
                        if (soc == 4) { // Transfer mode
                            showMessage("Glasses entering transfer mode - starting WiFiP2P")
                            wifiP2pManager.startPeerDiscovery()
                        }
                    } catch (e: Exception) {
                        Log.e(TAG, "Error parsing OTA progress", e)
                    }
                }

                0x0c -> {
                    if (response.loadData[7].toInt() == 1) {
                        showMessage("Pause event triggered")
                    }
                }

                0x0d -> {
                    if (response.loadData[7].toInt() == 1) {
                        showMessage("Device unbound")
                        bleConnected = false
                        updateStatus()
                    }
                }
                
                // Glasses insufficient memory event
                0x0e -> {
                    showMessage("Insufficient memory on glasses")
                }
                
                // Translation pause event
                0x10 -> {
                    showMessage("Translation paused")
                }
                
                // File transfer notifications
                0x0f -> {
                    val transferStatus = response.loadData[7].toInt()
                    when (transferStatus) {
                        0 -> showMessage("File transfer started")
                        1 -> showMessage("File transfer in progress")
                        2 -> showMessage("File transfer completed")
                        3 -> showMessage("File transfer failed")
                    }
                }
                
                // Glasses volume change event
                0x12 -> {
                    val musicVolume = response.loadData[10].toInt()
                    val callVolume = response.loadData[14].toInt()
                    val systemVolume = response.loadData[18].toInt()
                    val volumeMode = response.loadData[19].toInt()
                    
                    Log.d(TAG, "Volume changed - Music: $musicVolume, Call: $callVolume, System: $systemVolume, Mode: $volumeMode")
                }
            }
        }
    }
}
