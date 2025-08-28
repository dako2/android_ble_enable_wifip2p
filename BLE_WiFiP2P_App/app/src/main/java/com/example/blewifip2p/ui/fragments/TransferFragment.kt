package com.example.blewifip2p.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.blewifip2p.R
import com.example.blewifip2p.databinding.FragmentTransferBinding
import com.example.blewifip2p.manager.FileTransferManager
import com.elvishew.xlog.XLog

class TransferFragment : Fragment() {
    
    private var _binding: FragmentTransferBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var fileTransferManager: FileTransferManager
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransferBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupFileTransferManager()
        setupUI()
        setupClickListeners()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    /**
     * Setup file transfer manager
     */
    private fun setupFileTransferManager() {
        // Get file transfer manager from MainActivity
        fileTransferManager = (requireActivity() as? com.example.blewifip2p.ui.MainActivity)?.let { activity ->
            activity.fileTransferManager
        } ?: FileTransferManager(requireContext())
    }
    
    /**
     * Setup UI components
     */
    private fun setupUI() {
        updateUI()
    }
    
    /**
     * Setup click listeners
     */
    private fun setupClickListeners() {
        binding.btnTransferFiles.setOnClickListener {
            startFileTransfer()
        }
        
        binding.btnOtaUpdate.setOnClickListener {
            startOtaUpdate()
        }
    }
    
    /**
     * Start file transfer
     */
    private fun startFileTransfer() {
        try {
            fileTransferManager.startFileTransfer(
                object : FileTransferManager.FileTransferCallback {
                    override fun onTransferStarted() {
                        updateTransferState(true)
                        showMessage("File transfer started")
                    }
                    
                    override fun onFileListReceived(fileCount: Int) {
                        showMessage("Found $fileCount files to transfer")
                    }
                    
                    override fun onConnected() {
                        showMessage("Connected for file transfer")
                    }
                    
                    override fun onTransferProgress(progress: Int) {
                        updateProgress(progress)
                    }
                    
                    override fun onFileTransferred(current: Int, total: Int) {
                        showMessage("Transferred $current/$total files")
                    }
                    
                    override fun onTransferComplete(totalFiles: Int) {
                        updateTransferState(false)
                        showMessage("Transfer completed: $totalFiles files")
                    }
                    
                    override fun onTransferError(error: String) {
                        updateTransferState(false)
                        showMessage("Transfer error: $error")
                    }
                    
                    override fun onTransferStopped() {
                        updateTransferState(false)
                        showMessage("Transfer stopped")
                    }
                }
            )
        } catch (e: Exception) {
            XLog.e("Error starting file transfer", e)
            showMessage("Failed to start file transfer")
        }
    }
    
    /**
     * Start OTA update
     */
    private fun startOtaUpdate() {
        try {
            // TODO: Select firmware file
            showMessage("OTA update functionality to be implemented")
        } catch (e: Exception) {
            XLog.e("Error starting OTA update", e)
            showMessage("Failed to start OTA update")
        }
    }
    
    /**
     * Update UI state
     */
    private fun updateUI() {
        updateTransferState(false)
    }
    
    /**
     * Update transfer state UI
     */
    private fun updateTransferState(transferring: Boolean) {
        binding.btnTransferFiles.isEnabled = !transferring
        binding.btnOtaUpdate.isEnabled = !transferring
        
        if (transferring) {
            binding.progressBar.visibility = View.VISIBLE
            binding.tvProgress.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.tvProgress.visibility = View.GONE
        }
    }
    
    /**
     * Update progress display
     */
    private fun updateProgress(progress: Int) {
        binding.progressBar.progress = progress
        binding.tvProgress.text = "$progress%"
    }
    
    /**
     * Show message to user
     */
    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        XLog.d("TransferFragment: $message")
    }
}
