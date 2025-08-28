package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.view.BatteryImage;
import com.glasssutdio.wear.all.view.GlassSwitchCompat;

/* loaded from: classes.dex */
public final class ActivityYourGlassBinding implements ViewBinding {
    public final ImageView appBack;
    public final BatteryImage batteryView;
    public final ImageView bleImageConnected;
    public final ConstraintLayout ctlDeiceAbout;
    public final ConstraintLayout ctlOta;
    public final ConstraintLayout ctlReset;
    public final ConstraintLayout ctlRestart;
    public final ConstraintLayout ctlVideoSetting;
    public final ConstraintLayout ctlWearCheck;
    public final ConstraintLayout glassStatus1;
    public final GlassSwitchCompat gsc1;
    public final ImageView imageNotification;
    public final ImageView imageOta1;
    public final ImageView iv1;
    public final LinearLayout ll2;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final TextView textDeviceBattery;
    public final TextView tv1;
    public final TextView tv1Tip;
    public final TextView tvAbout;
    public final TextView tvBleStatus;
    public final TextView tvDeviceAddress;
    public final TextView tvDeviceFirmware;
    public final TextView tvDeviceFirmwareWifi;
    public final TextView tvDeviceName;
    public final TextView tvOta;
    public final TextView tvReset;
    public final TextView tvRestart;
    public final TextView tvTitle;
    public final TextView tvToUnbind;
    public final TextView tvVideoSetting;

    private ActivityYourGlassBinding(ConstraintLayout rootView, ImageView appBack, BatteryImage batteryView, ImageView bleImageConnected, ConstraintLayout ctlDeiceAbout, ConstraintLayout ctlOta, ConstraintLayout ctlReset, ConstraintLayout ctlRestart, ConstraintLayout ctlVideoSetting, ConstraintLayout ctlWearCheck, ConstraintLayout glassStatus1, GlassSwitchCompat gsc1, ImageView imageNotification, ImageView imageOta1, ImageView iv1, LinearLayout ll2, ConstraintLayout main, TextView textDeviceBattery, TextView tv1, TextView tv1Tip, TextView tvAbout, TextView tvBleStatus, TextView tvDeviceAddress, TextView tvDeviceFirmware, TextView tvDeviceFirmwareWifi, TextView tvDeviceName, TextView tvOta, TextView tvReset, TextView tvRestart, TextView tvTitle, TextView tvToUnbind, TextView tvVideoSetting) {
        this.rootView = rootView;
        this.appBack = appBack;
        this.batteryView = batteryView;
        this.bleImageConnected = bleImageConnected;
        this.ctlDeiceAbout = ctlDeiceAbout;
        this.ctlOta = ctlOta;
        this.ctlReset = ctlReset;
        this.ctlRestart = ctlRestart;
        this.ctlVideoSetting = ctlVideoSetting;
        this.ctlWearCheck = ctlWearCheck;
        this.glassStatus1 = glassStatus1;
        this.gsc1 = gsc1;
        this.imageNotification = imageNotification;
        this.imageOta1 = imageOta1;
        this.iv1 = iv1;
        this.ll2 = ll2;
        this.main = main;
        this.textDeviceBattery = textDeviceBattery;
        this.tv1 = tv1;
        this.tv1Tip = tv1Tip;
        this.tvAbout = tvAbout;
        this.tvBleStatus = tvBleStatus;
        this.tvDeviceAddress = tvDeviceAddress;
        this.tvDeviceFirmware = tvDeviceFirmware;
        this.tvDeviceFirmwareWifi = tvDeviceFirmwareWifi;
        this.tvDeviceName = tvDeviceName;
        this.tvOta = tvOta;
        this.tvReset = tvReset;
        this.tvRestart = tvRestart;
        this.tvTitle = tvTitle;
        this.tvToUnbind = tvToUnbind;
        this.tvVideoSetting = tvVideoSetting;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityYourGlassBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityYourGlassBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_your_glass, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityYourGlassBinding bind(View rootView) {
        int i = C0775R.id.app_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.battery_view;
            BatteryImage batteryImage = (BatteryImage) ViewBindings.findChildViewById(rootView, i);
            if (batteryImage != null) {
                i = C0775R.id.ble_image_connected;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                if (imageView2 != null) {
                    i = C0775R.id.ctl_deice_about;
                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                    if (constraintLayout != null) {
                        i = C0775R.id.ctl_ota;
                        ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                        if (constraintLayout2 != null) {
                            i = C0775R.id.ctl_reset;
                            ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                            if (constraintLayout3 != null) {
                                i = C0775R.id.ctl_restart;
                                ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                                if (constraintLayout4 != null) {
                                    i = C0775R.id.ctl_video_setting;
                                    ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                                    if (constraintLayout5 != null) {
                                        i = C0775R.id.ctl_wear_check;
                                        ConstraintLayout constraintLayout6 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                                        if (constraintLayout6 != null) {
                                            i = C0775R.id.glass_status_1;
                                            ConstraintLayout constraintLayout7 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                                            if (constraintLayout7 != null) {
                                                i = C0775R.id.gsc_1;
                                                GlassSwitchCompat glassSwitchCompat = (GlassSwitchCompat) ViewBindings.findChildViewById(rootView, i);
                                                if (glassSwitchCompat != null) {
                                                    i = C0775R.id.image_notification;
                                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                    if (imageView3 != null) {
                                                        i = C0775R.id.image_ota_1;
                                                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                        if (imageView4 != null) {
                                                            i = C0775R.id.iv_1;
                                                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                            if (imageView5 != null) {
                                                                i = C0775R.id.ll_2;
                                                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i);
                                                                if (linearLayout != null) {
                                                                    ConstraintLayout constraintLayout8 = (ConstraintLayout) rootView;
                                                                    i = C0775R.id.text_device_battery;
                                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                    if (textView != null) {
                                                                        i = C0775R.id.tv_1;
                                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                        if (textView2 != null) {
                                                                            i = C0775R.id.tv_1_tip;
                                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                            if (textView3 != null) {
                                                                                i = C0775R.id.tv_about;
                                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                if (textView4 != null) {
                                                                                    i = C0775R.id.tv_ble_status;
                                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                    if (textView5 != null) {
                                                                                        i = C0775R.id.tv_device_address;
                                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                        if (textView6 != null) {
                                                                                            i = C0775R.id.tv_device_firmware;
                                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                            if (textView7 != null) {
                                                                                                i = C0775R.id.tv_device_firmware_wifi;
                                                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                if (textView8 != null) {
                                                                                                    i = C0775R.id.tv_device_name;
                                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                    if (textView9 != null) {
                                                                                                        i = C0775R.id.tv_ota;
                                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                        if (textView10 != null) {
                                                                                                            i = C0775R.id.tv_reset;
                                                                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                            if (textView11 != null) {
                                                                                                                i = C0775R.id.tv_restart;
                                                                                                                TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                if (textView12 != null) {
                                                                                                                    i = C0775R.id.tvTitle;
                                                                                                                    TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                    if (textView13 != null) {
                                                                                                                        i = C0775R.id.tv_to_unbind;
                                                                                                                        TextView textView14 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                        if (textView14 != null) {
                                                                                                                            i = C0775R.id.tv_video_setting;
                                                                                                                            TextView textView15 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                            if (textView15 != null) {
                                                                                                                                return new ActivityYourGlassBinding(constraintLayout8, imageView, batteryImage, imageView2, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, constraintLayout5, constraintLayout6, constraintLayout7, glassSwitchCompat, imageView3, imageView4, imageView5, linearLayout, constraintLayout8, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15);
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
