package com.glasssutdio.wear.setting;

import android.app.Activity;
import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.PermissionUtilKt;
import com.glasssutdio.wear.databinding.ActivityWiFiConnectBinding;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import com.glasssutdio.wear.wifi.TypeEnum;
import com.glasssutdio.wear.wifi.WifiConnectorBuilder;
import com.glasssutdio.wear.wifi.WifiUtils;
import com.glasssutdio.wear.wifi.wifiConnect.ConnectionErrorCode;
import com.glasssutdio.wear.wifi.wifiConnect.ConnectionSuccessListener;
import com.glasssutdio.wear.wifi.wifiDisconnect.DisconnectionErrorCode;
import com.glasssutdio.wear.wifi.wifiDisconnect.DisconnectionSuccessListener;
import com.glasssutdio.wear.wifi.wifiRemove.RemoveErrorCode;
import com.glasssutdio.wear.wifi.wifiRemove.RemoveSuccessListener;
import com.google.android.gms.common.ConnectionResult;
import com.hjq.permissions.OnPermissionCallback;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WiFiConnectActivity.kt */
@Metadata(m606d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0006H\u0002J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\u0013\u001a\u00020\tH\u0002J\b\u0010\u0014\u001a\u00020\tH\u0002J\u0012\u0010\u0015\u001a\u00020\t2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\u0010\u0010\u0018\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m607d2 = {"Lcom/glasssutdio/wear/setting/WiFiConnectActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityWiFiConnectBinding;", "wifiName", "", "wifiPassword", "check", "", "context", "Landroid/content/Context;", "checkInternetConnection", "connectHidden", "connectToWifi", "ssid", "password", "connectWithWpa", "disconnect", "getDeviceIp", "initViews", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "remove", "WifiPermissionCallback", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class WiFiConnectActivity extends BaseSettingActivity {
    private ActivityWiFiConnectBinding binding;
    private String wifiName;
    private String wifiPassword;

    private final void connectToWifi(String ssid, String password) {
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws NoSuchMethodException, SecurityException {
        super.onCreate(savedInstanceState);
        ActivityWiFiConnectBinding activityWiFiConnectBindingInflate = ActivityWiFiConnectBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityWiFiConnectBindingInflate, "inflate(...)");
        this.binding = activityWiFiConnectBindingInflate;
        if (activityWiFiConnectBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWiFiConnectBindingInflate = null;
        }
        setContentView(activityWiFiConnectBindingInflate.getRoot());
        this.wifiName = "QGlasses_0F46";
        this.wifiPassword = "123456789";
        Activity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        PermissionUtilKt.requestWifiPermission((FragmentActivity) activity, new WifiPermissionCallback());
        initViews();
    }

    private final void initViews() {
        ActivityWiFiConnectBinding activityWiFiConnectBinding = this.binding;
        if (activityWiFiConnectBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWiFiConnectBinding = null;
        }
        activityWiFiConnectBinding.btnConnect.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.setting.WiFiConnectActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                XLog.m137i("btnConnect");
            }
        });
        activityWiFiConnectBinding.btnScan.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.setting.WiFiConnectActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WiFiConnectActivity.initViews$lambda$2$lambda$1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$2$lambda$1(WiFiConnectActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.connectWithWpa(this$0);
    }

    /* compiled from: WiFiConnectActivity.kt */
    @Metadata(m606d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, m607d2 = {"Lcom/glasssutdio/wear/setting/WiFiConnectActivity$WifiPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/glasssutdio/wear/setting/WiFiConnectActivity;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class WifiPermissionCallback implements OnPermissionCallback {
        public WifiPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            XLog.m136i(permissions);
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            super.onDenied(permissions, never);
            XLog.m136i(permissions);
            XLog.m136i(Boolean.valueOf(never));
        }
    }

    private final void connectWithWpa(final Context context) {
        WifiConnectorBuilder.WifiUtilsBuilder wifiUtilsBuilderWithContext = WifiUtils.withContext(context);
        String str = this.wifiName;
        String str2 = null;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wifiName");
            str = null;
        }
        String str3 = this.wifiPassword;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wifiPassword");
        } else {
            str2 = str3;
        }
        wifiUtilsBuilderWithContext.connectWith(str, str2).setTimeout(15000L).onConnectionResult(new ConnectionSuccessListener() { // from class: com.glasssutdio.wear.setting.WiFiConnectActivity.connectWithWpa.1
            @Override // com.glasssutdio.wear.wifi.wifiConnect.ConnectionSuccessListener
            public void success() {
                Toast.makeText(context, "SUCCESS!", 0).show();
                this.getDeviceIp();
            }

            @Override // com.glasssutdio.wear.wifi.wifiConnect.ConnectionSuccessListener
            public void failed(ConnectionErrorCode errorCode) {
                Intrinsics.checkNotNullParameter(errorCode, "errorCode");
                Toast.makeText(context, "EPIC FAIL!" + errorCode, 0).show();
            }
        }).start();
    }

    private final void connectHidden(final Context context) {
        WifiConnectorBuilder.WifiUtilsBuilder wifiUtilsBuilderWithContext = WifiUtils.withContext(context);
        String str = this.wifiName;
        String str2 = null;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wifiName");
            str = null;
        }
        String str3 = this.wifiPassword;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wifiPassword");
        } else {
            str2 = str3;
        }
        wifiUtilsBuilderWithContext.connectWith(str, str2, TypeEnum.EAP).onConnectionResult(new ConnectionSuccessListener() { // from class: com.glasssutdio.wear.setting.WiFiConnectActivity.connectHidden.1
            @Override // com.glasssutdio.wear.wifi.wifiConnect.ConnectionSuccessListener
            public void success() {
                Toast.makeText(context, "SUCCESS!", 0).show();
            }

            @Override // com.glasssutdio.wear.wifi.wifiConnect.ConnectionSuccessListener
            public void failed(ConnectionErrorCode errorCode) {
                Intrinsics.checkNotNullParameter(errorCode, "errorCode");
                Toast.makeText(context, "EPIC FAIL!" + errorCode, 0).show();
            }
        }).start();
    }

    private final void disconnect(final Context context) {
        WifiUtils.withContext(context).disconnect(new DisconnectionSuccessListener() { // from class: com.glasssutdio.wear.setting.WiFiConnectActivity.disconnect.1
            @Override // com.glasssutdio.wear.wifi.wifiDisconnect.DisconnectionSuccessListener
            public void success() {
                Toast.makeText(context, "Disconnect success!", 0).show();
            }

            @Override // com.glasssutdio.wear.wifi.wifiDisconnect.DisconnectionSuccessListener
            public void failed(DisconnectionErrorCode errorCode) {
                Intrinsics.checkNotNullParameter(errorCode, "errorCode");
                Toast.makeText(context, "Failed to disconnect: " + errorCode, 0).show();
            }
        });
    }

    private final void remove(final Context context) {
        WifiConnectorBuilder.WifiUtilsBuilder wifiUtilsBuilderWithContext = WifiUtils.withContext(context);
        String str = this.wifiName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wifiName");
            str = null;
        }
        wifiUtilsBuilderWithContext.remove(str, new RemoveSuccessListener() { // from class: com.glasssutdio.wear.setting.WiFiConnectActivity.remove.1
            @Override // com.glasssutdio.wear.wifi.wifiRemove.RemoveSuccessListener
            public void success() {
                Toast.makeText(context, "Remove success!", 0).show();
            }

            @Override // com.glasssutdio.wear.wifi.wifiRemove.RemoveSuccessListener
            public void failed(RemoveErrorCode errorCode) {
                Intrinsics.checkNotNullParameter(errorCode, "errorCode");
                Toast.makeText(context, "Failed to disconnect and remove: " + errorCode, 0).show();
            }
        });
    }

    private final void check(Context context) {
        WifiConnectorBuilder.WifiUtilsBuilder wifiUtilsBuilderWithContext = WifiUtils.withContext(context);
        String str = this.wifiName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wifiName");
            str = null;
        }
        Toast.makeText(context, "Wifi Connect State: " + wifiUtilsBuilderWithContext.isWifiConnected(str), 0).show();
    }

    private final void checkInternetConnection(final Context context) {
        ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(4);
        Intrinsics.checkNotNullExpressionValue(executorServiceNewFixedThreadPool, "newFixedThreadPool(...)");
        executorServiceNewFixedThreadPool.execute(new Runnable() { // from class: com.glasssutdio.wear.setting.WiFiConnectActivity$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() throws IOException {
                WiFiConnectActivity.checkInternetConnection$lambda$5(this.f$0, context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkInternetConnection$lambda$5(WiFiConnectActivity this$0, final Context context) throws IOException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("8.8.8.8", 53), ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
            socket.close();
            this$0.runOnUiThread(new Runnable() { // from class: com.glasssutdio.wear.setting.WiFiConnectActivity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    WiFiConnectActivity.checkInternetConnection$lambda$5$lambda$3(context);
                }
            });
        } catch (Exception unused) {
            this$0.runOnUiThread(new Runnable() { // from class: com.glasssutdio.wear.setting.WiFiConnectActivity$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    WiFiConnectActivity.checkInternetConnection$lambda$5$lambda$4(context);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkInternetConnection$lambda$5$lambda$3(Context context) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Toast.makeText(context, "Connecting to internet successfully", 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkInternetConnection$lambda$5$lambda$4(Context context) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Toast.makeText(context, "Cant connect to internet", 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getDeviceIp() {
        Object systemService = getApplicationContext().getSystemService("wifi");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.wifi.WifiManager");
        DhcpInfo dhcpInfo = ((WifiManager) systemService).getDhcpInfo();
        String ipAddress = Formatter.formatIpAddress(dhcpInfo.gateway);
        Intrinsics.checkNotNullExpressionValue(ipAddress, "formatIpAddress(...)");
        Log.d("ServerIP", ipAddress);
        UserConfig.INSTANCE.getInstance().setGlassDeviceWifiIP(ipAddress);
        Log.d("DhcpInfo", "Netmask: " + Formatter.formatIpAddress(dhcpInfo.netmask));
        Log.d("DhcpInfo", "DNS 1: " + Formatter.formatIpAddress(dhcpInfo.dns1));
        Log.d("DhcpInfo", "DNS 2: " + Formatter.formatIpAddress(dhcpInfo.dns2));
        Log.d("DhcpInfo", "Server Address: " + Formatter.formatIpAddress(dhcpInfo.serverAddress));
    }
}
