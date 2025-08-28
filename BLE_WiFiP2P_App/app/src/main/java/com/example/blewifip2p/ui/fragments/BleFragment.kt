package com.example.blewifip2p.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blewifip2p.R
import com.example.blewifip2p.databinding.FragmentBleBinding
import com.example.blewifip2p.manager.BLEManager
import com.elvishew.xlog.XLog

class BleFragment : Fragment() {
    
    private var _binding: FragmentBleBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var bleManager: BLEManager
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBleBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupBLEManager()
        setupUI()
        setupClickListeners()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    /**
     * Setup BLE manager
     */
    private fun setupBLEManager() {
        // Get BLE manager from MainActivity
        bleManager = (requireActivity() as? com.example.blewifip2p.ui.MainActivity)?.let { activity ->
            activity.bleManager
        } ?: BLEManager(requireContext())
    }
    
    /**
     * Setup UI components
     */
    private fun setupUI() {
        // Setup RecyclerView for devices
        binding.rvDevices.layoutManager = LinearLayoutManager(context)
        // TODO: Add device adapter
        
        updateUI()
    }
    
    /**
     * Setup click listeners
     */
    private fun setupClickListeners() {
        binding.btnScan.setOnClickListener {
            startScan()
        }
        
        binding.btnConnect.setOnClickListener {
            // TODO: Connect to selected device
            showMessage("Connect functionality to be implemented")
        }
        
        binding.btnDisconnect.setOnClickListener {
            disconnect()
        }
        
        binding.btnCamera.setOnClickListener {
            sendCameraCommand()
        }
        
        binding.btnVideo.setOnClickListener {
            sendVideoCommand()
        }
        
        binding.btnRecord.setOnClickListener {
            sendRecordCommand()
        }
        
        binding.btnBattery.setOnClickListener {
            getBatteryInfo()
        }
        
        binding.btnVolume.setOnClickListener {
            getVolumeInfo()
        }
    }
    
    /**
     * Start BLE scanning
     */
    private fun startScan() {
        try {
            bleManager.startScan()
            updateScanningState(true)
            showMessage("Scanning started")
        } catch (e: Exception) {
            XLog.e("Error starting scan", e)
            showMessage("Failed to start scan")
        }
    }
    
    /**
     * Disconnect from device
     */
    private fun disconnect() {
        try {
            bleManager.disconnect()
            updateConnectionState(false)
            showMessage("Disconnected")
        } catch (e: Exception) {
            XLog.e("Error disconnecting", e)
            showMessage("Failed to disconnect")
        }
    }
    
    /**
     * Send camera command
     */
    private fun sendCameraCommand() {
        try {
            bleManager.sendCameraCommand()
            showMessage("Camera command sent")
        } catch (e: Exception) {
            XLog.e("Error sending camera command", e)
            showMessage("Failed to send camera command")
        }
    }
    
    /**
     * Send video command
     */
    private fun sendVideoCommand() {
        try {
            bleManager.sendVideoCommand(true) // Start recording
            showMessage("Video command sent")
        } catch (e: Exception) {
            XLog.e("Error sending video command", e)
            showMessage("Failed to send video command")
        }
    }
    
    /**
     * Send record command
     */
    private fun sendRecordCommand() {
        try {
            bleManager.sendRecordCommand(true) // Start recording
            showMessage("Record command sent")
        } catch (e: Exception) {
            XLog.e("Error sending record command", e)
            showMessage("Failed to send record command")
        }
    }
    
    /**
     * Get battery information
     */
    private fun getBatteryInfo() {
        try {
            bleManager.getBatteryInfo()
            showMessage("Battery info requested")
        } catch (e: Exception) {
            XLog.e("Error getting battery info", e)
            showMessage("Failed to get battery info")
        }
    }
    
    /**
     * Get volume information
     */
    private fun getVolumeInfo() {
        try {
            bleManager.getVolumeInfo()
            showMessage("Volume info requested")
        } catch (e: Exception) {
            XLog.e("Error getting volume info", e)
            showMessage("Failed to get volume info")
        }
    }
    
    /**
     * Update UI state
     */
    private fun updateUI() {
        updateConnectionState(bleManager.isConnected())
        updateScanningState(false)
    }
    
    /**
     * Update connection state UI
     */
    private fun updateConnectionState(connected: Boolean) {
        binding.btnConnect.isEnabled = !connected
        binding.btnDisconnect.isEnabled = connected
        binding.btnCamera.isEnabled = connected
        binding.btnVideo.isEnabled = connected
        binding.btnRecord.isEnabled = connected
        binding.btnBattery.isEnabled = connected
        binding.btnVolume.isEnabled = connected
        
        binding.tvDeviceInfo.text = if (connected) {
            "Device connected"
        } else {
            "No device connected"
        }
    }
    
    /**
     * Update scanning state UI
     */
    private fun updateScanningState(scanning: Boolean) {
        binding.btnScan.isEnabled = !scanning
        binding.btnScan.text = if (scanning) {
            "Scanning..."
        } else {
            getString(R.string.btn_scan)
        }
    }
    
    /**
     * Show message to user
     */
    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        XLog.d("BleFragment: $message")
    }
}
