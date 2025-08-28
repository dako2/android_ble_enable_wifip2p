# Integrated BLE + WiFiP2P Approach

## 🎯 **Strategy: Reuse GlassesSDKSample's BLE + Add WiFiP2P**

Instead of creating missing dependencies, we'll use the **working GlassesSDKSample approach** as the foundation and add WiFiP2P functionality to it.

## 📋 **Core Dependencies (Working)**

```gradle
dependencies {
    // Glasses SDK (provides all com.oudmon.ble.* classes)
    implementation files("libs/glasses_sdk_20250723_v01.aar")
    
    // Android core
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.core:core-ktx:1.13.1")
    
    // Permission handling
    implementation("com.github.getActivity:XXPermissions:20.0")
    
    // Event bus
    implementation("org.greenrobot:eventbus:3.2.0")
    
    // UI components
    implementation("com.google.android.material:material:1.12.0")
    implementation("com.github.ForgetAll:LoadingDialog:v1.1.2")
    implementation("androidx.localbroadcastmanager:localbroadcastmanager:1.1.0")
}
```

## 🏗️ **Architecture Overview**

```
GlassesSDKSample BLE Foundation
├── BleOperateManager (Connection Management)
├── LargeDataHandler (Data Communication)
├── DeviceManager (Device State)
├── GlassesDeviceNotifyListener (Event Handling)
└── + WiFiP2P Integration
    ├── WifiP2pManagerSingleton (WiFiP2P Management)
    ├── FileTransferManager (Media Transfer)
    └── OTAActivity (OTA Updates)
```

## 🔧 **Implementation Steps**

### **Step 1: Extend MainActivity with WiFiP2P**

```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var binding: AcitivytMainBinding
    private val deviceNotifyListener by lazy { MyDeviceNotifyListener() }
    
    // Add WiFiP2P components
    private lateinit var wifiP2pManager: WifiP2pManagerSingleton
    private lateinit var fileTransferManager: FileTransferManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcitivytMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Initialize WiFiP2P
        initWiFiP2P()
        initView()
    }
    
    private fun initWiFiP2P() {
        wifiP2pManager = WifiP2pManagerSingleton.getInstance(this)
        fileTransferManager = FileTransferManager(this)
    }
}
```

### **Step 2: Add WiFiP2P UI Controls**

```xml
<!-- Add to acitivyt_main.xml -->
<Button android:id="@+id/btnWifiP2pScan" android:text="WiFiP2P Scan" />
<Button android:id="@+id/btnWifiP2pConnect" android:text="WiFiP2P Connect" />
<Button android:id="@+id/btnTransferFiles" android:text="Transfer Files" />
<Button android:id="@+id/btnOtaUpdate" android:text="OTA Update" />
```

### **Step 3: Integrate BLE + WiFiP2P Flow**

```kotlin
private fun initView() {
    setOnClickListener(
        binding.btnScan,
        binding.btnConnect,
        // ... existing BLE buttons ...
        binding.btnWifiP2pScan,
        binding.btnWifiP2pConnect,
        binding.btnTransferFiles,
        binding.btnOtaUpdate
    ) {
        when (this) {
            // Existing BLE functionality
            binding.btnScan -> { /* BLE scan */ }
            binding.btnConnect -> { /* BLE connect */ }
            
            // New WiFiP2P functionality
            binding.btnWifiP2pScan -> {
                // Only scan if BLE is connected
                if (BleOperateManager.getInstance().isConnected) {
                    wifiP2pManager.startPeerDiscovery()
                } else {
                    showMessage("Please connect BLE first")
                }
            }
            
            binding.btnWifiP2pConnect -> {
                // Connect to glasses via WiFiP2P
                val device = wifiP2pManager.discoveredPeers.firstOrNull()
                device?.let { wifiP2pManager.connectToDevice(it) }
            }
            
            binding.btnTransferFiles -> {
                // Transfer files when both BLE and WiFiP2P are connected
                if (BleOperateManager.getInstance().isConnected && wifiP2pManager.isConnected) {
                    startFileTransfer()
                } else {
                    showMessage("Both BLE and WiFiP2P must be connected")
                }
            }
            
            binding.btnOtaUpdate -> {
                // Start OTA update
                if (BleOperateManager.getInstance().isConnected) {
                    startOtaUpdate()
                } else {
                    showMessage("BLE must be connected for OTA")
                }
            }
        }
    }
}
```

### **Step 4: Enhanced Device Notify Listener**

```kotlin
inner class MyDeviceNotifyListener : GlassesDeviceNotifyListener() {
    override fun parseData(cmdType: Int, response: GlassesDeviceNotifyRsp) {
        when (response.loadData[6].toInt()) {
            // Existing BLE notifications...
            
            // New WiFiP2P related notifications
            0x04 -> {
                // OTA upgrade progress
                try {
                    val download = response.loadData[7].toInt()
                    val soc = response.loadData[8].toInt()
                    val nor = response.loadData[9].toInt()
                    
                    // Update UI with progress
                    updateOtaProgress(download, soc, nor)
                    
                    // Enable WiFiP2P when glasses enter transfer mode
                    if (response.loadData[8].toInt() == 4) { // Transfer mode
                        wifiP2pManager.startPeerDiscovery()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            
            // File transfer notifications
            0x0f -> {
                // File transfer status
                val transferStatus = response.loadData[7].toInt()
                handleFileTransferStatus(transferStatus)
            }
        }
    }
}
```

### **Step 5: WiFiP2P Integration Classes**

#### **WifiP2pManagerSingleton (Kotlin Version)**

```kotlin
class WifiP2pManagerSingleton private constructor(private val context: Context) {
    companion object {
        @Volatile
        private var instance: WifiP2pManagerSingleton? = null
        
        fun getInstance(context: Context): WifiP2pManagerSingleton {
            return instance ?: synchronized(this) {
                instance ?: WifiP2pManagerSingleton(context).also { instance = it }
            }
        }
    }
    
    private val wifiP2pManager: WifiP2pManager by lazy {
        context.getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager
    }
    
    private var wifiP2pChannel: WifiP2pManager.Channel? = null
    private val discoveredPeers = mutableListOf<WifiP2pDevice>()
    private var isConnected = false
    
    init {
        wifiP2pChannel = wifiP2pManager.initialize(context, context.mainLooper) {
            // Channel disconnected
        }
    }
    
    fun startPeerDiscovery() {
        // Implement peer discovery
    }
    
    fun connectToDevice(device: WifiP2pDevice) {
        // Implement device connection
    }
    
    fun isConnected(): Boolean = isConnected
}
```

#### **FileTransferManager (Kotlin Version)**

```kotlin
class FileTransferManager(private val context: Context) {
    
    fun startFileTransfer() {
        // Implement file transfer using TCP sockets
        // This will communicate with glasses to transfer media files
    }
    
    fun startOtaUpdate() {
        // Implement OTA update process
        // This will handle firmware updates
    }
    
    private fun handleFileTransferStatus(status: Int) {
        // Handle file transfer progress and status
    }
    
    private fun updateOtaProgress(download: Int, soc: Int, nor: Int) {
        // Update OTA progress in UI
    }
}
```

## 🔄 **Complete Flow Integration**

### **1. BLE Connection Flow**
```
1. Scan for BLE devices
2. Connect to glasses via BLE
3. Register device notify listener
4. Enable notifications for WiFiP2P events
```

### **2. WiFiP2P Connection Flow**
```
1. Wait for BLE connection
2. Start WiFiP2P peer discovery
3. Connect to glasses via WiFiP2P
4. Establish TCP socket connection
```

### **3. File Transfer Flow**
```
1. Check both BLE and WiFiP2P connections
2. Request file list from glasses
3. Initiate file transfer via TCP
4. Monitor transfer progress via BLE notifications
5. Update UI with progress
```

### **4. OTA Update Flow**
```
1. Connect via BLE
2. Send OTA command to glasses
3. Glasses enter transfer mode
4. Connect via WiFiP2P
5. Transfer firmware file
6. Monitor update progress via BLE
```

## 📱 **Enhanced UI Integration**

### **Status Display**
```kotlin
private fun updateStatus() {
    val bleStatus = if (BleOperateManager.getInstance().isConnected) "Connected" else "Disconnected"
    val wifiStatus = if (wifiP2pManager.isConnected()) "Connected" else "Disconnected"
    
    binding.tvStatus.text = "BLE: $bleStatus | WiFiP2P: $wifiStatus"
}
```

### **Progress Indicators**
```kotlin
private fun showTransferProgress(progress: Int) {
    binding.progressBar.progress = progress
    binding.tvProgress.text = "$progress%"
}
```

## 🎯 **Benefits of This Approach**

1. **✅ Working Foundation**: Uses proven GlassesSDKSample BLE implementation
2. **✅ Complete Dependencies**: All required libraries are available
3. **✅ Real-world Testing**: Based on actual working code
4. **✅ Scalable**: Easy to extend with additional features
5. **✅ Maintainable**: Clear separation of BLE and WiFiP2P concerns

## 🚀 **Implementation Priority**

1. **Phase 1**: Extend MainActivity with WiFiP2P buttons
2. **Phase 2**: Implement WifiP2pManagerSingleton in Kotlin
3. **Phase 3**: Add FileTransferManager for media transfer
4. **Phase 4**: Integrate OTA update functionality
5. **Phase 5**: Add comprehensive error handling and UI feedback

This approach gives you a **working implementation** using the available SDK while adding the WiFiP2P functionality for media transfer and OTA updates.
