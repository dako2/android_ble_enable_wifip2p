package com.example.blewifip2p.manager

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.util.Log
import com.elvishew.xlog.XLog
import com.oudmon.ble.base.bluetooth.BleOperateManager
import com.oudmon.ble.base.bluetooth.DeviceManager
import com.oudmon.ble.base.communication.LargeDataHandler
import com.oudmon.ble.base.communication.bigData.resp.GlassesDeviceNotifyListener
import com.oudmon.ble.base.communication.bigData.resp.GlassesDeviceNotifyRsp

class BLEManager(private val context: Context) {
    
    companion object {
        private const val TAG = "BLEManager"
    }
    
    // Callbacks
    private var connectionCallback: ((Boolean) -> Unit)? = null
    private var deviceFoundCallback: ((String) -> Unit)? = null
    private var notificationCallback: ((Int, GlassesDeviceNotifyRsp) -> Unit)? = null
    
    // BLE components
    private val bluetoothManager: BluetoothManager by lazy {
        context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    }
    
    private val bluetoothAdapter: BluetoothAdapter by lazy {
        bluetoothManager.adapter
    }
    
    // State
    private var isInitialized = false
    private var isScanning = false
    private var isConnected = false
    private var selectedDeviceAddress: String? = null
    
    /**
     * Initialize BLE manager
     */
    fun initialize() {
        if (isInitialized) return
        
        try {
            // Initialize BLE managers
            BleOperateManager.getInstance()
            DeviceManager.getInstance()
            LargeDataHandler.getInstance()
            
            isInitialized = true
            XLog.d("BLE Manager initialized")
        } catch (e: Exception) {
            XLog.e("Failed to initialize BLE Manager", e)
        }
    }
    
    /**
     * Check if Bluetooth is enabled
     */
    fun isBluetoothEnabled(): Boolean {
        return bluetoothAdapter.isEnabled
    }
    
    /**
     * Start scanning for BLE devices
     */
    fun startScan() {
        if (!isInitialized || isScanning) return
        
        try {
            isScanning = true
            XLog.d("Starting BLE scan")
            
            // Start scanning using the BLE SDK
            BleOperateManager.getInstance().classicBluetoothStartScan()
            
            // Schedule scan timeout
            android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                stopScan()
            }, 30000) // 30 seconds timeout
            
        } catch (e: Exception) {
            XLog.e("Failed to start BLE scan", e)
            isScanning = false
        }
    }
    
    /**
     * Stop scanning for BLE devices
     */
    fun stopScan() {
        if (!isScanning) return
        
        try {
            isScanning = false
            XLog.d("Stopping BLE scan")
            
            // Stop scanning using the BLE SDK
            // Note: The SDK handles scan stopping automatically
            
        } catch (e: Exception) {
            XLog.e("Failed to stop BLE scan", e)
        }
    }
    
    /**
     * Connect to a specific device
     */
    fun connectToDevice(address: String) {
        if (!isInitialized || isConnected) return
        
        try {
            selectedDeviceAddress = address
            XLog.d("Connecting to device: $address")
            
            // Set device address and connect
            DeviceManager.getInstance().deviceAddress = address
            BleOperateManager.getInstance().connectDirectly(address)
            
        } catch (e: Exception) {
            XLog.e("Failed to connect to device", e)
            connectionCallback?.invoke(false)
        }
    }
    
    /**
     * Disconnect from current device
     */
    fun disconnect() {
        if (!isConnected) return
        
        try {
            XLog.d("Disconnecting from device")
            BleOperateManager.getInstance().unBindDevice()
            
        } catch (e: Exception) {
            XLog.e("Failed to disconnect from device", e)
        }
    }
    
    /**
     * Check if connected to a device
     */
    fun isConnected(): Boolean {
        return try {
            BleOperateManager.getInstance().isConnected
        } catch (e: Exception) {
            false
        }
    }
    
    /**
     * Send camera control command
     */
    fun sendCameraCommand() {
        if (!isConnected()) return
        
        try {
            XLog.d("Sending camera command")
            LargeDataHandler.getInstance().glassesControl(
                byteArrayOf(0x02, 0x01, 0x01)
            ) { _, response ->
                handleCommandResponse(response)
            }
        } catch (e: Exception) {
            XLog.e("Failed to send camera command", e)
        }
    }
    
    /**
     * Send video recording command
     */
    fun sendVideoCommand(startRecording: Boolean) {
        if (!isConnected()) return
        
        try {
            val value = if (startRecording) 0x02 else 0x03
            XLog.d("Sending video command: $value")
            LargeDataHandler.getInstance().glassesControl(
                byteArrayOf(0x02, 0x01, value.toByte())
            ) { _, response ->
                handleCommandResponse(response)
            }
        } catch (e: Exception) {
            XLog.e("Failed to send video command", e)
        }
    }
    
    /**
     * Send audio recording command
     */
    fun sendRecordCommand(startRecording: Boolean) {
        if (!isConnected()) return
        
        try {
            val value = if (startRecording) 0x08 else 0x0c
            XLog.d("Sending record command: $value")
            LargeDataHandler.getInstance().glassesControl(
                byteArrayOf(0x02, 0x01, value.toByte())
            ) { _, response ->
                handleCommandResponse(response)
            }
        } catch (e: Exception) {
            XLog.e("Failed to send record command", e)
        }
    }
    
    /**
     * Get device information
     */
    fun getDeviceInfo() {
        if (!isConnected()) return
        
        try {
            XLog.d("Getting device info")
            LargeDataHandler.getInstance().syncDeviceInfo { _, response ->
                handleDeviceInfoResponse(response)
            }
        } catch (e: Exception) {
            XLog.e("Failed to get device info", e)
        }
    }
    
    /**
     * Get battery information
     */
    fun getBatteryInfo() {
        if (!isConnected()) return
        
        try {
            XLog.d("Getting battery info")
            LargeDataHandler.getInstance().syncBattery()
        } catch (e: Exception) {
            XLog.e("Failed to get battery info", e)
        }
    }
    
    /**
     * Get volume information
     */
    fun getVolumeInfo() {
        if (!isConnected()) return
        
        try {
            XLog.d("Getting volume info")
            LargeDataHandler.getInstance().getVolumeControl { _, response ->
                handleVolumeResponse(response)
            }
        } catch (e: Exception) {
            XLog.e("Failed to get volume info", e)
        }
    }
    
    /**
     * Send OTA command
     */
    fun sendOtaCommand() {
        if (!isConnected()) return
        
        try {
            XLog.d("Sending OTA command")
            LargeDataHandler.getInstance().glassesControl(
                byteArrayOf(0x05, 0x01)
            ) { _, response ->
                handleCommandResponse(response)
            }
        } catch (e: Exception) {
            XLog.e("Failed to send OTA command", e)
        }
    }
    
    /**
     * Add device notification listener
     */
    fun addDeviceListener(listener: GlassesDeviceNotifyListener) {
        try {
            LargeDataHandler.getInstance().addOutDeviceListener(100, listener)
            XLog.d("Device notification listener added")
        } catch (e: Exception) {
            XLog.e("Failed to add device listener", e)
        }
    }
    
    /**
     * Handle command response
     */
    private fun handleCommandResponse(response: Any) {
        XLog.d("Command response received: $response")
        // Handle different command responses
    }
    
    /**
     * Handle device info response
     */
    private fun handleDeviceInfoResponse(response: Any?) {
        if (response != null) {
            XLog.d("Device info received: $response")
            // Handle device info
        }
    }
    
    /**
     * Handle volume response
     */
    private fun handleVolumeResponse(response: Any?) {
        if (response != null) {
            XLog.d("Volume info received: $response")
            // Handle volume info
        }
    }
    
    /**
     * Set connection callback
     */
    fun setConnectionCallback(callback: (Boolean) -> Unit) {
        this.connectionCallback = callback
    }
    
    /**
     * Set device found callback
     */
    fun setDeviceFoundCallback(callback: (String) -> Unit) {
        this.deviceFoundCallback = callback
    }
    
    /**
     * Set notification callback
     */
    fun setNotificationCallback(callback: (Int, GlassesDeviceNotifyRsp) -> Unit) {
        this.notificationCallback = callback
    }
    
    /**
     * Cleanup resources
     */
    fun cleanup() {
        try {
            if (isConnected) {
                disconnect()
            }
            if (isScanning) {
                stopScan()
            }
            XLog.d("BLE Manager cleaned up")
        } catch (e: Exception) {
            XLog.e("Error during BLE Manager cleanup", e)
        }
    }
}
