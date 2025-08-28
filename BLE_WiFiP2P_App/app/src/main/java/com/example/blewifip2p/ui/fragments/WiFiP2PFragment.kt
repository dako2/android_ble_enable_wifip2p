package com.example.blewifip2p.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blewifip2p.R
import com.example.blewifip2p.databinding.FragmentWifip2pBinding
import com.example.blewifip2p.manager.WiFiP2PManager
import com.elvishew.xlog.XLog

class WiFiP2PFragment : Fragment() {
    
    private var _binding: FragmentWifip2pBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var wifiP2PManager: WiFiP2PManager
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWifip2pBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupWiFiP2PManager()
        setupUI()
        setupClickListeners()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    /**
     * Setup WiFiP2P manager
     */
    private fun setupWiFiP2PManager() {
        // Get WiFiP2P manager from MainActivity
        wifiP2PManager = (requireActivity() as? com.example.blewifip2p.ui.MainActivity)?.let { activity ->
            activity.wifiP2PManager
        } ?: WiFiP2PManager(requireContext())
    }
    
    /**
     * Setup UI components
     */
    private fun setupUI() {
        // Setup RecyclerView for WiFiP2P devices
        binding.rvDevices.layoutManager = LinearLayoutManager(context)
        // TODO: Add WiFiP2P device adapter
        
        updateUI()
    }
    
    /**
     * Setup click listeners
     */
    private fun setupClickListeners() {
        binding.btnScan.setOnClickListener {
            startDiscovery()
        }
        
        binding.btnConnect.setOnClickListener {
            // TODO: Connect to selected WiFiP2P device
            showMessage("Connect functionality to be implemented")
        }
        
        binding.btnDisconnect.setOnClickListener {
            disconnect()
        }
    }
    
    /**
     * Start WiFiP2P discovery
     */
    private fun startDiscovery() {
        try {
            wifiP2PManager.startDiscovery()
            updateDiscoveryState(true)
            showMessage("WiFiP2P discovery started")
        } catch (e: Exception) {
            XLog.e("Error starting WiFiP2P discovery", e)
            showMessage("Failed to start WiFiP2P discovery")
        }
    }
    
    /**
     * Disconnect from WiFiP2P device
     */
    private fun disconnect() {
        try {
            wifiP2PManager.cancelConnection()
            updateConnectionState(false)
            showMessage("WiFiP2P disconnected")
        } catch (e: Exception) {
            XLog.e("Error disconnecting WiFiP2P", e)
            showMessage("Failed to disconnect WiFiP2P")
        }
    }
    
    /**
     * Update UI state
     */
    private fun updateUI() {
        updateConnectionState(wifiP2PManager.isConnected())
        updateDiscoveryState(false)
    }
    
    /**
     * Update connection state UI
     */
    private fun updateConnectionState(connected: Boolean) {
        binding.btnConnect.isEnabled = !connected
        binding.btnDisconnect.isEnabled = connected
        
        binding.tvStatus.text = if (connected) {
            "WiFiP2P connected"
        } else {
            "WiFiP2P disconnected"
        }
    }
    
    /**
     * Update discovery state UI
     */
    private fun updateDiscoveryState(discovering: Boolean) {
        binding.btnScan.isEnabled = !discovering
        binding.btnScan.text = if (discovering) {
            "Discovering..."
        } else {
            "Discover Devices"
        }
    }
    
    /**
     * Show message to user
     */
    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        XLog.d("WiFiP2PFragment: $message")
    }
}
