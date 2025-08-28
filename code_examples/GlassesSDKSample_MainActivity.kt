/**
 * GlassesSDKSample MainActivity - Demonstrates BLE functionality using glasses SDK
 * File: GlassesSDKSample/app/src/main/java/com/sdk/glassessdksample/MainActivity.kt
 */

package com.sdk.glassessdksample

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
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

class MainActivity : AppCompatActivity() {
    private lateinit var binding: AcitivytMainBinding
    private val deviceNotifyListener by lazy { MyDeviceNotifyListener() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcitivytMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }
    
    /**
     * Permission callback
     */
    inner class PermissionCallback : OnPermissionCallback {
        override fun onGranted(permissions: MutableList<String>, all: Boolean) {
            if (!all) {
                // Handle partial permission grants
            } else {
                startKtxActivity<DeviceBindActivity>()
            }
        }

        override fun onDenied(permissions: MutableList<String>, never: Boolean) {
            super.onDenied(permissions, never)
            if (never) {
                XXPermissions.startPermissionActivity(this@MainActivity, permissions)
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
            Log.e("MainActivity", "Error requesting Bluetooth enable", e)
        }
        
        if (!hasBluetooth(this)) {
            requestBluetoothPermission(this, BluetoothPermissionCallback())
        }

        requestAllPermission(this, OnPermissionCallback { permissions, all ->  })
    }

    inner class BluetoothPermissionCallback : OnPermissionCallback {
        override fun onGranted(permissions: MutableList<String>, all: Boolean) {
            if (!all) {
                // Handle partial permission grants
            }
        }

        override fun onDenied(permissions: MutableList<String>, never: Boolean) {
            super.onDenied(permissions, never)
            if (never) {
                XXPermissions.startPermissionActivity(this@MainActivity, permissions)
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
        ) {
            when (this) {
                binding.btnScan -> {
                    requestLocationPermission(this@MainActivity, PermissionCallback())
                }

                binding.btnConnect -> {
                    BleOperateManager.getInstance()
                        .connectDirectly(DeviceManager.getInstance().deviceAddress)
                }

                binding.btnDisconnect -> {
                    BleOperateManager.getInstance().unBindDevice()
                }

                binding.btnAddListener -> {
                    LargeDataHandler.getInstance().addOutDeviceListener(100, deviceNotifyListener)
                }

                binding.btnSetTime -> {
                    Log.i("setTime", "setTime" + BleOperateManager.getInstance().isConnected)
                    LargeDataHandler.getInstance().syncTime { _, _ -> }
                }

                binding.btnVersion -> {
                    LargeDataHandler.getInstance().syncDeviceInfo { _, response ->
                        if (response != null) {
                            // WiFi firmware version
                            response.wifiFirmwareVersion
                            // WiFi product version
                            response.wifiHardwareVersion
                            // Bluetooth product version
                            response.hardwareVersion
                            // Bluetooth firmware version
                            response.firmwareVersion
                        }
                    }
                }

                binding.btnCamera -> {
                    LargeDataHandler.getInstance().glassesControl(
                        byteArrayOf(0x02, 0x01, 0x01)
                    ) { _, it ->
                        if (it.dataType == 1 && it.errorCode == 0) {
                            when (it.workTypeIng) {
                                2 -> {
                                    // Glasses are recording video
                                }
                                4 -> {
                                    // Glasses are in transfer mode
                                }
                                5 -> {
                                    // Glasses are in OTA mode
                                }
                                1, 6 -> {
                                    // Glasses are in camera mode
                                }
                                7 -> {
                                    // Glasses are in AI dialogue
                                }
                                8 -> {
                                    // Glasses are in recording mode
                                }
                            }
                        } else {
                            // Execute start and stop
                        }
                    }
                }

                binding.btnVideo -> {
                    // videoStart true start recording false stop recording
                    val videoStart = true
                    val value = if (videoStart) 0x02 else 0x03
                    LargeDataHandler.getInstance().glassesControl(
                        byteArrayOf(0x02, 0x01, value.toByte())
                    ) { _, it ->
                        if (it.dataType == 1) {
                            if (it.errorCode == 0) {
                                when (it.workTypeIng) {
                                    2 -> {
                                        // Glasses are recording video
                                    }
                                    4 -> {
                                        // Glasses are in transfer mode
                                    }
                                    5 -> {
                                        // Glasses are in OTA mode
                                    }
                                    1, 6 -> {
                                        // Glasses are in camera mode
                                    }
                                    7 -> {
                                        // Glasses are in AI dialogue
                                    }
                                    8 -> {
                                        // Glasses are in recording mode
                                    }
                                }
                            } else {
                                // Execute start and stop
                            }
                        }
                    }
                }

                binding.btnRecord -> {
                    // recordStart true start recording false stop recording
                    val recordStart = true
                    val value = if (recordStart) 0x08 else 0x0c
                    LargeDataHandler.getInstance().glassesControl(
                        byteArrayOf(0x02, 0x01, value.toByte())
                    ) { _, it ->
                        if (it.dataType == 1) {
                            if (it.errorCode == 0) {
                                when (it.workTypeIng) {
                                    2 -> {
                                        // Glasses are recording video
                                    }
                                    4 -> {
                                        // Glasses are in transfer mode
                                    }
                                    5 -> {
                                        // Glasses are in OTA mode
                                    }
                                    1, 6 -> {
                                        // Glasses are in camera mode
                                    }
                                    7 -> {
                                        // Glasses are in AI dialogue
                                    }
                                    8 -> {
                                        // Glasses are in recording mode
                                    }
                                }
                            } else {
                                // Execute start and stop
                            }
                        }
                    }
                }

                binding.btnThumbnail -> {
                    // thumbnailSize 0..6
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
                                when (it.workTypeIng) {
                                    2 -> {
                                        // Glasses are recording video
                                    }
                                    4 -> {
                                        // Glasses are in transfer mode
                                    }
                                    5 -> {
                                        // Glasses are in OTA mode
                                    }
                                    1, 6 -> {
                                        // Glasses are in camera mode
                                    }
                                    7 -> {
                                        // Glasses are in AI dialogue
                                    }
                                    8 -> {
                                        // Glasses are in recording mode
                                    }
                                }
                            } else {
                                // Trigger AI photo, thumbnail report will receive notification
                            }
                        }
                    }
                }

                binding.btnBt -> {
                    // BT scanning
                    BleOperateManager.getInstance().classicBluetoothStartScan()
                }
                
                binding.btnBattery -> {
                    // Add battery monitoring
                    LargeDataHandler.getInstance().addBatteryCallBack("init") { _, response ->
                        // Handle battery response
                    }
                    // Battery level
                    LargeDataHandler.getInstance().syncBattery()
                }
                
                binding.btnVolume -> {
                    // Read volume control
                    LargeDataHandler.getInstance().getVolumeControl { _, response ->
                        if (response != null) {
                            // Glasses volume - music min value max value current value
                            response.minVolumeMusic
                            response.maxVolumeMusic
                            response.currVolumeMusic
                            // Glasses call - call min value max value current value
                            response.minVolumeCall
                            response.maxVolumeCall
                            response.currVolumeCall
                            // Glasses system - system min value max value current value
                            response.minVolumeSystem
                            response.maxVolumeSystem
                            response.currVolumeSystem
                            // Current volume mode
                            response.currVolumeType
                        }
                    }
                }
                
                binding.btnMediaCount -> {
                    LargeDataHandler.getInstance().glassesControl(byteArrayOf(0x02, 0x04)) { _, it ->
                        if (it.dataType == 4) {
                            val mediaCount = it.imageCount + it.videoCount + it.recordCount
                            if (mediaCount > 0) {
                                // Number of media files not uploaded on glasses
                            } else {
                                // None
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Device notification listener
     */
    inner class MyDeviceNotifyListener : GlassesDeviceNotifyListener() {

        @RequiresApi(Build.VERSION_CODES.O)
        override fun parseData(cmdType: Int, response: GlassesDeviceNotifyRsp) {
            when (response.loadData[6].toInt()) {
                // Glasses battery notification
                0x05 -> {
                    // Current battery level
                    val battery = response.loadData[7].toInt()
                    // Whether charging
                    val changing = response.loadData[8].toInt()
                }
                // Glasses quick recognition
                0x02 -> {
                    if (response.loadData.size > 9 && response.loadData[9].toInt() == 0x02) {
                        // Set recognition intent: eg please help me see what's in front, content in the image
                    }
                    // Get picture thumbnail
                    LargeDataHandler.getInstance().getPictureThumbnails { cmdType, success, data ->
                        // Save data to path, jpg image
                    }
                }

                0x03 -> {
                    if (response.loadData[7].toInt() == 1) {
                        // Glasses start microphone and start speaking
                    }
                }
                // OTA upgrade
                0x04 -> {
                    try {
                        val download = response.loadData[7].toInt()
                        val soc = response.loadData[8].toInt()
                        val nor = response.loadData[9].toInt()
                        // download firmware download progress soc download progress nor upgrade progress
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                0x0c -> {
                    // Glasses trigger pause event, voice broadcast
                    if (response.loadData[7].toInt() == 1) {
                        // to do
                    }
                }

                0x0d -> {
                    // Unbind APP event
                    if (response.loadData[7].toInt() == 1) {
                        // to do
                    }
                }
                // Glasses insufficient memory event
                0x0e -> {
                    // Handle insufficient memory
                }
                // Translation pause event
                0x10 -> {
                    // Handle translation pause
                }
                // Glasses volume change event
                0x12 -> {
                    // Music volume
                    // Min volume
                    response.loadData[8].toInt()
                    // Max volume
                    response.loadData[9].toInt()
                    // Current volume
                    response.loadData[10].toInt()

                    // Call volume
                    // Min volume
                    response.loadData[12].toInt()
                    // Max volume
                    response.loadData[13].toInt()
                    // Current volume
                    response.loadData[14].toInt()

                    // Glasses system volume
                    // Min volume
                    response.loadData[16].toInt()
                    // Max volume
                    response.loadData[17].toInt()
                    // Current volume
                    response.loadData[18].toInt()

                    // Current volume mode
                    response.loadData[19].toInt()
                }
            }
        }
    }
}
