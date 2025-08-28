package com.example.blewifip2p.manager

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.elvishew.xlog.XLog
import com.oudmon.ble.base.communication.LargeDataHandler
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.Socket
import java.util.concurrent.Executors

class FileTransferManager(private val context: Context) {
    
    companion object {
        private const val TAG = "FileTransferManager"
        private const val TRANSFER_PORT = 8888
        private const val BUFFER_SIZE = 8192
        
        // File transfer protocol constants
        private const val CMD_REQUEST_FILE_LIST = 0x01
        private const val CMD_REQUEST_FILE = 0x02
        private const val CMD_FILE_DATA = 0x03
        private const val CMD_TRANSFER_COMPLETE = 0x04
        private const val CMD_OTA_START = 0x05
        private const val CMD_OTA_DATA = 0x06
        private const val CMD_OTA_COMPLETE = 0x07
    }
    
    // Transfer state
    private var isTransferring = false
    private var currentTransferProgress = 0
    private var totalFilesToTransfer = 0
    private var transferredFiles = 0
    
    // Network components
    private var clientSocket: Socket? = null
    private val networkExecutor = Executors.newSingleThreadExecutor()
    private val mainHandler = Handler(Looper.getMainLooper())
    
    // Callbacks
    private var transferCallback: FileTransferCallback? = null
    private var otaCallback: OtaUpdateCallback? = null
    
    /**
     * Start file transfer process
     */
    fun startFileTransfer(callback: FileTransferCallback? = null) {
        if (isTransferring) {
            XLog.w("File transfer already in progress")
            return
        }
        
        transferCallback = callback
        isTransferring = true
        currentTransferProgress = 0
        transferredFiles = 0
        
        XLog.d("Starting file transfer")
        transferCallback?.onTransferStarted()
        
        // Request file list from glasses via BLE
        requestFileListFromGlasses()
    }
    
    /**
     * Start OTA update process
     */
    fun startOtaUpdate(firmwareFile: File, callback: OtaUpdateCallback? = null) {
        otaCallback = callback
        
        XLog.d("Starting OTA update with file: ${firmwareFile.name}")
        otaCallback?.onOtaStarted()
        
        // Send OTA command via BLE
        sendOtaCommandToGlasses()
    }
    
    /**
     * Request file list from glasses via BLE
     */
    private fun requestFileListFromGlasses() {
        // Use LargeDataHandler to request media count
        LargeDataHandler.getInstance().glassesControl(byteArrayOf(0x02, 0x04)) { _, response ->
            if (response.dataType == 4) {
                totalFilesToTransfer = response.imageCount + response.videoCount + response.recordCount
                
                if (totalFilesToTransfer > 0) {
                    XLog.d("Found $totalFilesToTransfer files to transfer")
                    transferCallback?.onFileListReceived(totalFilesToTransfer)
                    
                    // Start WiFiP2P connection for file transfer
                    connectToGlassesForTransfer()
                } else {
                    XLog.d("No files to transfer")
                    transferCallback?.onTransferComplete(0)
                    isTransferring = false
                }
            } else {
                XLog.e("Failed to get file list")
                transferCallback?.onTransferError("Failed to get file list")
                isTransferring = false
            }
        }
    }
    
    /**
     * Connect to glasses via WiFiP2P for file transfer
     */
    private fun connectToGlassesForTransfer() {
        // This would be handled by WifiP2pManagerSingleton
        // Once connected, start TCP transfer
        networkExecutor.execute {
            try {
                // Connect to glasses via TCP
                clientSocket = Socket("192.168.49.1", TRANSFER_PORT) // Default P2P IP
                
                if (clientSocket?.isConnected == true) {
                    XLog.d("Connected to glasses for file transfer")
                    transferCallback?.onConnected()
                    
                    // Start file transfer protocol
                    startFileTransferProtocol()
                } else {
                    XLog.e("Failed to connect to glasses")
                    transferCallback?.onTransferError("Failed to connect to glasses")
                    isTransferring = false
                }
            } catch (e: Exception) {
                XLog.e("Error connecting to glasses", e)
                transferCallback?.onTransferError("Connection error: ${e.message}")
                isTransferring = false
            }
        }
    }
    
    /**
     * Start file transfer protocol
     */
    private fun startFileTransferProtocol() {
        networkExecutor.execute {
            try {
                val socket = clientSocket ?: return@execute
                val input = socket.getInputStream()
                val output = socket.getOutputStream()
                
                // Send file list request
                val request = byteArrayOf(CMD_REQUEST_FILE_LIST.toByte())
                output.write(request)
                output.flush()
                
                // Read file list response
                val response = ByteArray(1024)
                val bytesRead = input.read(response)
                
                if (bytesRead > 0) {
                    processFileListResponse(response.copyOf(bytesRead))
                }
                
            } catch (e: Exception) {
                XLog.e("Error in file transfer protocol", e)
                transferCallback?.onTransferError("Protocol error: ${e.message}")
                isTransferring = false
            }
        }
    }
    
    /**
     * Process file list response from glasses
     */
    private fun processFileListResponse(data: ByteArray) {
        // Parse file list and start transferring each file
        // This is a simplified implementation
        XLog.d("Processing file list response")
        
        // For each file in the list, request and receive it
        for (i in 0 until totalFilesToTransfer) {
            requestFile(i)
        }
    }
    
    /**
     * Request a specific file from glasses
     */
    private fun requestFile(fileIndex: Int) {
        networkExecutor.execute {
            try {
                val socket = clientSocket ?: return@execute
                val output = socket.getOutputStream()
                
                // Send file request
                val request = byteArrayOf(CMD_REQUEST_FILE.toByte(), fileIndex.toByte())
                output.write(request)
                output.flush()
                
                XLog.d("Requested file $fileIndex")
                
            } catch (e: Exception) {
                XLog.e("Error requesting file $fileIndex", e)
            }
        }
    }
    
    /**
     * Send OTA command to glasses via BLE
     */
    private fun sendOtaCommandToGlasses() {
        // Send OTA command using LargeDataHandler
        LargeDataHandler.getInstance().glassesControl(byteArrayOf(0x05, 0x01)) { _, response ->
            if (response.errorCode == 0) {
                XLog.d("OTA command sent successfully")
                otaCallback?.onOtaCommandSent()
                
                // Wait for glasses to enter OTA mode, then start WiFiP2P transfer
                // This will be handled by the device notify listener
            } else {
                XLog.e("OTA command failed")
                otaCallback?.onOtaError("OTA command failed")
            }
        }
    }
    
    /**
     * Handle file transfer status updates from BLE notifications
     */
    fun handleFileTransferStatus(status: Int) {
        when (status) {
            0 -> {
                XLog.d("File transfer started")
                transferCallback?.onTransferStarted()
            }
            1 -> {
                // Update progress
                currentTransferProgress++
                val progress = (currentTransferProgress.toFloat() / totalFilesToTransfer * 100).toInt()
                transferCallback?.onTransferProgress(progress)
            }
            2 -> {
                XLog.d("File transfer completed")
                transferredFiles++
                transferCallback?.onFileTransferred(transferredFiles, totalFilesToTransfer)
                
                if (transferredFiles >= totalFilesToTransfer) {
                    transferCallback?.onTransferComplete(transferredFiles)
                    isTransferring = false
                }
            }
            3 -> {
                XLog.e("File transfer failed")
                transferCallback?.onTransferError("File transfer failed")
                isTransferring = false
            }
        }
    }
    
    /**
     * Handle OTA progress updates from BLE notifications
     */
    fun handleOtaProgress(download: Int, soc: Int, nor: Int) {
        val progress = (download + soc + nor) / 3 // Average of three progress values
        otaCallback?.onOtaProgress(progress)
        
        if (soc == 4) { // Transfer mode
            XLog.d("Glasses entered transfer mode, starting OTA transfer")
            startOtaFileTransfer()
        }
    }
    
    /**
     * Start OTA file transfer via WiFiP2P
     */
    private fun startOtaFileTransfer() {
        networkExecutor.execute {
            try {
                // Connect to glasses via TCP for OTA
                clientSocket = Socket("192.168.49.1", TRANSFER_PORT)
                
                if (clientSocket?.isConnected == true) {
                    XLog.d("Connected to glasses for OTA transfer")
                    otaCallback?.onOtaConnected()
                    
                    // Start OTA transfer protocol
                    startOtaTransferProtocol()
                } else {
                    XLog.e("Failed to connect to glasses for OTA")
                    otaCallback?.onOtaError("Failed to connect to glasses")
                }
            } catch (e: Exception) {
                XLog.e("Error connecting to glasses for OTA", e)
                otaCallback?.onOtaError("OTA connection error: ${e.message}")
            }
        }
    }
    
    /**
     * Start OTA transfer protocol
     */
    private fun startOtaTransferProtocol() {
        // Implement OTA file transfer protocol
        // This would involve sending firmware file data to glasses
        XLog.d("Starting OTA transfer protocol")
        
        // TODO: Implement actual firmware file transfer
        otaCallback?.onOtaComplete()
    }
    
    /**
     * Save received file to local storage
     */
    private fun saveFile(fileName: String, fileData: ByteArray): Boolean {
        return try {
            val downloadDir = File(context.getExternalFilesDir(null), "downloads")
            if (!downloadDir.exists()) {
                downloadDir.mkdirs()
            }
            
            val file = File(downloadDir, fileName)
            FileOutputStream(file).use { output ->
                output.write(fileData)
                output.flush()
            }
            
            XLog.d("File saved: ${file.absolutePath}")
            true
        } catch (e: Exception) {
            XLog.e("Error saving file", e)
            false
        }
    }
    
    /**
     * Stop file transfer
     */
    fun stopFileTransfer() {
        isTransferring = false
        
        try {
            clientSocket?.close()
            clientSocket = null
        } catch (e: Exception) {
            XLog.e("Error closing socket", e)
        }
        
        transferCallback?.onTransferStopped()
    }
    
    /**
     * Stop OTA update
     */
    fun stopOtaUpdate() {
        try {
            clientSocket?.close()
            clientSocket = null
        } catch (e: Exception) {
            XLog.e("Error closing OTA socket", e)
        }
        
        otaCallback?.onOtaStopped()
    }
    
    /**
     * Get transfer progress
     */
    fun getTransferProgress(): Int = currentTransferProgress
    
    /**
     * Check if transferring
     */
    fun isTransferring(): Boolean = isTransferring
    
    /**
     * Cleanup resources
     */
    fun cleanup() {
        try {
            stopFileTransfer()
            stopOtaUpdate()
            XLog.d("FileTransferManager cleaned up")
        } catch (e: Exception) {
            XLog.e("Error during FileTransferManager cleanup", e)
        }
    }
    
    /**
     * File Transfer Callback Interface
     */
    interface FileTransferCallback {
        fun onTransferStarted()
        fun onFileListReceived(fileCount: Int)
        fun onConnected()
        fun onTransferProgress(progress: Int)
        fun onFileTransferred(current: Int, total: Int)
        fun onTransferComplete(totalFiles: Int)
        fun onTransferError(error: String)
        fun onTransferStopped()
    }
    
    /**
     * OTA Update Callback Interface
     */
    interface OtaUpdateCallback {
        fun onOtaStarted()
        fun onOtaCommandSent()
        fun onOtaConnected()
        fun onOtaProgress(progress: Int)
        fun onOtaComplete()
        fun onOtaError(error: String)
        fun onOtaStopped()
    }
}
