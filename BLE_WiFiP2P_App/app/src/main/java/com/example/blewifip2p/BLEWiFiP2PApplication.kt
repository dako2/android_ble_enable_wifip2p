package com.example.blewifip2p

import android.app.Application
import android.util.Log
import com.elvishew.xlog.LogLevel
import com.elvishew.xlog.XLog
import com.oudmon.ble.base.bluetooth.BleOperateManager
import com.oudmon.ble.base.bluetooth.DeviceManager
import com.oudmon.ble.base.communication.LargeDataHandler

class BLEWiFiP2PApplication : Application() {
    
    companion object {
        private const val TAG = "BLEWiFiP2PApp"
        lateinit var instance: BLEWiFiP2PApplication
            private set
    }
    
    override fun onCreate() {
        super.onCreate()
        instance = this
        
        // Initialize logging
        initializeLogging()
        
        // Initialize BLE components
        initializeBLESDK()
        
        Log.d(TAG, "Application initialized")
    }
    
    /**
     * Initialize logging system
     */
    private fun initializeLogging() {
        XLog.init(LogLevel.ALL)
        XLog.d("Logging system initialized")
    }
    
    /**
     * Initialize BLE SDK components
     */
    private fun initializeBLESDK() {
        try {
            // Initialize BLE managers
            BleOperateManager.getInstance()
            DeviceManager.getInstance()
            LargeDataHandler.getInstance()
            
            XLog.d("BLE SDK components initialized")
        } catch (e: Exception) {
            XLog.e("Failed to initialize BLE SDK", e)
        }
    }
}
