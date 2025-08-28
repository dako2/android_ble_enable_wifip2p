# BLE WiFiP2P Android App

A complete Android application that demonstrates BLE-enabled WiFiP2P functionality for transferring media files from glasses to a local device.

## 🎯 **Overview**

This app implements the integrated approach combining GlassesSDKSample's working BLE implementation with WiFiP2P functionality for high-speed file transfer and OTA updates.

## 🏗️ **Architecture**

```
BLE_WiFiP2P_App/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/blewifip2p/
│   │   │   ├── BLEWiFiP2PApplication.kt          # Application class
│   │   │   ├── ui/
│   │   │   │   ├── MainActivity.kt               # Main activity with tabs
│   │   │   │   └── fragments/
│   │   │   │       ├── BleFragment.kt            # BLE device control
│   │   │   │       ├── WiFiP2PFragment.kt        # WiFiP2P management
│   │   │   │       └── TransferFragment.kt       # File transfer & OTA
│   │   │   ├── manager/
│   │   │   │   ├── BLEManager.kt                 # BLE operations
│   │   │   │   ├── WiFiP2PManager.kt             # WiFiP2P operations
│   │   │   │   └── FileTransferManager.kt        # File transfer operations
│   │   │   └── utils/
│   │   │       └── PermissionHelper.kt           # Permission utilities
│   │   ├── res/
│   │   │   ├── layout/                           # UI layouts
│   │   │   ├── values/                           # Strings, colors, themes
│   │   │   └── ...
│   │   └── AndroidManifest.xml                   # App manifest
│   ├── libs/
│   │   └── glasses_sdk_20250723_v01.aar          # Glasses SDK (required)
│   └── build.gradle.kts                          # App dependencies
├── build.gradle.kts                              # Project configuration
├── settings.gradle.kts                           # Project settings
└── README.md                                     # This file
```

## 🔧 **Features**

### **BLE Functionality**
- ✅ Device scanning and discovery
- ✅ Connection management
- ✅ Media control (camera, video, record)
- ✅ Device information retrieval
- ✅ Battery and volume monitoring
- ✅ OTA command sending

### **WiFiP2P Functionality**
- ✅ Peer discovery
- ✅ Connection establishment
- ✅ TCP socket communication
- ✅ Automatic reconnection
- ✅ Timeout handling

### **File Transfer**
- ✅ Media file transfer from glasses
- ✅ Progress tracking
- ✅ Error handling
- ✅ Local storage management

### **OTA Updates**
- ✅ Firmware update initiation
- ✅ Progress monitoring
- ✅ WiFiP2P-based transfer

## 📱 **UI Components**

### **Main Activity**
- Tab-based navigation (BLE, WiFiP2P, Transfer)
- Real-time status display
- Progress indicators
- Modern Material Design 3 UI

### **BLE Fragment**
- Device connection controls
- Media control buttons
- Device information display
- Battery status

### **WiFiP2P Fragment**
- Discovery and connection controls
- Device list
- Connection status

### **Transfer Fragment**
- File transfer controls
- OTA update controls
- Transfer progress display

## 🚀 **Setup Instructions**

### **Prerequisites**
1. Android Studio
2. Android SDK (API level 24+)
3. Kotlin 1.8.0+
4. Glasses SDK AAR file

### **Installation Steps**

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd BLE_WiFiP2P_App
   ```

2. **Add Glasses SDK**
   - Copy `glasses_sdk_20250723_v01.aar` to `app/libs/`
   - The SDK is already referenced in `build.gradle.kts`

3. **Build and Run**
   ```bash
   ./gradlew build
   ```

4. **Install on Device**
   ```bash
   ./gradlew installDebug
   ```

### **Required Permissions**

The app automatically requests these permissions:
- Bluetooth permissions (CONNECT, SCAN, ADVERTISE)
- WiFiP2P permissions (ACCESS_WIFI_STATE, CHANGE_WIFI_STATE, NEARBY_WIFI_DEVICES)
- Location permissions (required for BLE scanning)
- Storage permissions (for file transfer)

## 🔄 **Usage Flow**

### **1. BLE Connection**
1. Launch the app
2. Navigate to "BLE" tab
3. Tap "Scan" to discover devices
4. Select and connect to glasses
5. Use media controls (camera, video, record)

### **2. WiFiP2P Connection**
1. Navigate to "WiFiP2P" tab
2. Tap "Discover Devices" (requires BLE connection)
3. Connect to glasses via WiFiP2P
4. Monitor connection status

### **3. File Transfer**
1. Navigate to "Transfer" tab
2. Tap "Transfer Files" (requires both BLE and WiFiP2P connections)
3. Monitor transfer progress
4. Files are saved to device storage

### **4. OTA Update**
1. Navigate to "Transfer" tab
2. Tap "OTA Update"
3. Select firmware file
4. Monitor update progress

## 📋 **Dependencies**

### **Core Dependencies**
```kotlin
// Glasses SDK
implementation(files("libs/glasses_sdk_20250723_v01.aar"))

// Android core
implementation("androidx.core:core-ktx:1.13.1")
implementation("androidx.appcompat:appcompat:1.7.0")
implementation("com.google.android.material:material:1.12.0")

// Permission handling
implementation("com.github.getActivity:XXPermissions:20.0")

// Event bus
implementation("org.greenrobot:eventbus:3.3.1")

// Logging
implementation("com.elvishew:xlog:1.11.0")

// Networking
implementation("com.squareup.okhttp3:okhttp:4.12.0")

// Bluetooth and BLE
implementation("com.polidea.rxandroidble2:rxandroidble:1.18.0")

// Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
```

## 🔍 **Key Components**

### **BLEManager**
- Handles BLE device scanning and connection
- Manages media control commands
- Integrates with Glasses SDK

### **WiFiP2PManager**
- Manages WiFi Direct peer discovery
- Handles connection establishment
- Provides TCP socket communication

### **FileTransferManager**
- Orchestrates file transfer process
- Handles progress tracking
- Manages OTA updates

### **MainActivity**
- Coordinates all managers
- Provides tab navigation
- Displays real-time status

## 🛠️ **Development**

### **Adding New Features**
1. Create new manager class in `manager/` package
2. Add corresponding fragment in `ui/fragments/`
3. Update MainActivity to integrate new functionality
4. Add necessary permissions to AndroidManifest.xml

### **Testing**
- Test on real Android device (Bluetooth/WiFiP2P not available in emulator)
- Ensure glasses device is available for testing
- Verify all permissions are granted

## 📝 **Notes**

### **Important Considerations**
1. **Glasses SDK Required**: The app requires the specific glasses SDK AAR file
2. **Real Device Testing**: Bluetooth and WiFiP2P functionality requires a physical device
3. **Permissions**: All required permissions are automatically requested
4. **Error Handling**: Comprehensive error handling and user feedback

### **Known Limitations**
- WiFiP2P connection requires BLE connection first
- File transfer protocol is simplified (may need customization for specific glasses)
- OTA update requires firmware file selection

## 🤝 **Contributing**

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 **License**

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 **Support**

For issues and questions:
1. Check the existing issues
2. Create a new issue with detailed description
3. Include device information and error logs

---

**Note**: This app is designed to work with specific glasses hardware and requires the corresponding SDK. Ensure you have the necessary hardware and SDK before testing.
