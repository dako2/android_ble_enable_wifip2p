package com.example.blewifip2p.ui

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.blewifip2p.R
import com.example.blewifip2p.databinding.ActivityMainBinding
import com.example.blewifip2p.manager.BLEManager
import com.example.blewifip2p.manager.WiFiP2PManager
import com.example.blewifip2p.manager.FileTransferManager
import com.example.blewifip2p.ui.fragments.BleFragment
import com.example.blewifip2p.ui.fragments.WiFiP2PFragment
import com.example.blewifip2p.ui.fragments.TransferFragment
import com.example.blewifip2p.utils.PermissionHelper
import com.google.android.material.tabs.TabLayoutMediator
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions
import com.oudmon.ble.base.bluetooth.BleOperateManager
import com.oudmon.ble.base.communication.LargeDataHandler
import com.oudmon.ble.base.communication.bigData.resp.GlassesDeviceNotifyListener
import com.oudmon.ble.base.communication.bigData.resp.GlassesDeviceNotifyRsp
import com.elvishew.xlog.XLog
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    
    // Managers
    lateinit var bleManager: BLEManager
    lateinit var wifiP2PManager: WiFiP2PManager
    lateinit var fileTransferManager: FileTransferManager
    
    // Status tracking
    private var bleConnected = false
    private var wifiP2pConnected = false
    private var isTransferring = false
    
    // Device notification listener
    private val deviceNotifyListener = object : GlassesDeviceNotifyListener() {
        override fun parseData(cmdType: Int, response: GlassesDeviceNotifyRsp) {
            handleDeviceNotification(cmdType, response)
        }
    }
    
    // Permission launcher
    private val bluetoothEnableLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            XLog.d("Bluetooth enabled by user")
            requestPermissions()
        } else {
            XLog.w("Bluetooth enable request denied")
            showMessage("Bluetooth is required for this app")
        }
    }
    
    companion object {
        private const val TAG = "MainActivity"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        initializeManagers()
        setupViewPager()
        setupStatusBar()
        requestPermissions()
    }
    
    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }
    
    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
    
    override fun onResume() {
        super.onResume()
        updateStatus()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        bleManager.cleanup()
        wifiP2PManager.cleanup()
        fileTransferManager.cleanup()
    }
    
    /**
     * Setup toolbar
     */
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }
    
    /**
     * Initialize managers
     */
    private fun initializeManagers() {
        bleManager = BLEManager(this)
        wifiP2PManager = WiFiP2PManager(this)
        fileTransferManager = FileTransferManager(this)
        
        // Set up callbacks
        bleManager.setConnectionCallback { connected ->
            bleConnected = connected
            updateStatus()
            if (connected) {
                addDeviceListener()
            }
        }
        
        wifiP2PManager.setConnectionCallback { connected ->
            wifiP2pConnected = connected
            updateStatus()
        }
    }
    
    /**
     * Setup ViewPager with fragments
     */
    private fun setupViewPager() {
        val adapter = MainPagerAdapter(this)
        binding.viewPager.adapter = adapter
        
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.tab_ble)
                1 -> getString(R.string.tab_wifip2p)
                2 -> getString(R.string.tab_transfer)
                else -> ""
            }
        }.attach()
    }
    
    /**
     * Setup status bar
     */
    private fun setupStatusBar() {
        updateStatus()
    }
    
    /**
     * Request necessary permissions
     */
    private fun requestPermissions() {
        // Check if Bluetooth is enabled
        if (!bleManager.isBluetoothEnabled()) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            bluetoothEnableLauncher.launch(enableBtIntent)
            return
        }
        
        // Request runtime permissions
        val permissions = PermissionHelper.getRequiredPermissions()
        XXPermissions.with(this)
            .permission(permissions)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, all: Boolean) {
                    if (all) {
                        XLog.d("All permissions granted")
                        startBLEManager()
                    } else {
                        XLog.w("Partial permissions granted")
                        showMessage("Some permissions are required for full functionality")
                    }
                }
                
                override fun onDenied(permissions: MutableList<String>, never: Boolean) {
                    if (never) {
                        XXPermissions.startPermissionActivity(this@MainActivity, permissions)
                    } else {
                        showMessage("Permissions are required for this app to work")
                    }
                }
            })
    }
    
    /**
     * Start BLE manager
     */
    private fun startBLEManager() {
        bleManager.initialize()
        wifiP2PManager.initialize()
    }
    
    /**
     * Add device notification listener
     */
    private fun addDeviceListener() {
        LargeDataHandler.getInstance().addOutDeviceListener(100, deviceNotifyListener)
        XLog.d("Device notification listener added")
    }
    
    /**
     * Handle device notifications
     */
    private fun handleDeviceNotification(cmdType: Int, response: GlassesDeviceNotifyRsp) {
        when (response.loadData[6].toInt()) {
            0x04 -> {
                // OTA upgrade progress
                try {
                    val download = response.loadData[7].toInt()
                    val soc = response.loadData[8].toInt()
                    val nor = response.loadData[9].toInt()
                    
                    val progress = (download + soc + nor) / 3
                    updateProgress(progress)
                    
                    // Enable WiFiP2P when glasses enter transfer mode
                    if (soc == 4) {
                        XLog.d("Glasses entering transfer mode, starting WiFiP2P")
                        wifiP2PManager.startDiscovery()
                    }
                } catch (e: Exception) {
                    XLog.e("Error parsing OTA progress", e)
                }
            }
            0x0f -> {
                // File transfer status
                val transferStatus = response.loadData[7].toInt()
                handleFileTransferStatus(transferStatus)
            }
            0x05 -> {
                // Battery notification
                val battery = response.loadData[7].toInt()
                updateBatteryInfo(battery)
            }
        }
    }
    
    /**
     * Handle file transfer status
     */
    private fun handleFileTransferStatus(status: Int) {
        when (status) {
            0 -> {
                showMessage("File transfer started")
                isTransferring = true
                updateStatus()
            }
            1 -> {
                // Transfer in progress
                showProgress(true)
            }
            2 -> {
                showMessage("Transfer completed")
                isTransferring = false
                showProgress(false)
                updateStatus()
            }
            3 -> {
                showMessage("Transfer failed")
                isTransferring = false
                showProgress(false)
                updateStatus()
            }
        }
    }
    
    /**
     * Update status display
     */
    private fun updateStatus() {
        val statusText = buildString {
            append("BLE: ${if (bleConnected) "Connected" else "Disconnected"}")
            append(" | WiFiP2P: ${if (wifiP2pConnected) "Connected" else "Disconnected"}")
            if (isTransferring) {
                append(" | Transferring")
            }
        }
        
        binding.tvStatus.text = statusText
        XLog.d("Status updated: $statusText")
    }
    
    /**
     * Update progress display
     */
    private fun updateProgress(progress: Int) {
        runOnUiThread {
            binding.progressBar.progress = progress
            binding.tvProgress.text = "$progress%"
        }
    }
    
    /**
     * Update battery info
     */
    private fun updateBatteryInfo(battery: Int) {
        runOnUiThread {
            binding.tvStatus.text = "Battery: $battery%"
        }
    }
    
    /**
     * Show/hide progress bar
     */
    private fun showProgress(show: Boolean) {
        runOnUiThread {
            binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
            binding.tvProgress.visibility = if (show) View.VISIBLE else View.GONE
        }
    }
    
    /**
     * Show message to user
     */
    private fun showMessage(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            XLog.d("Message: $message")
        }
    }
    
    /**
     * EventBus subscriber for status updates
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onStatusUpdate(event: StatusUpdateEvent) {
        updateStatus()
    }
    
    /**
     * Main ViewPager adapter
     */
    inner class MainPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 3
        
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> BleFragment()
                1 -> WiFiP2PFragment()
                2 -> TransferFragment()
                else -> throw IllegalArgumentException("Invalid position $position")
            }
        }
    }
    
    /**
     * Status update event
     */
    data class StatusUpdateEvent(val bleConnected: Boolean, val wifiP2pConnected: Boolean)
}
