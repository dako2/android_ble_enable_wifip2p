package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.view.BatteryImage;

/* loaded from: classes.dex */
public final class FragmentHomeBinding implements ViewBinding {
    public final BatteryImage batteryView;
    public final ConstraintLayout clsRecording;
    public final ConstraintLayout clsSmartPicture;
    public final ConstraintLayout clsTakePhoto;
    public final ConstraintLayout clsVideo;
    public final ConstraintLayout clsVolume;
    public final ConstraintLayout cslAiCamera;
    public final ConstraintLayout cslAiGesture;
    public final ConstraintLayout cslAiTranslate;
    public final ConstraintLayout cslAiVoice;
    public final ConstraintLayout ctlBt;
    public final ConstraintLayout ctlOta;
    public final ConstraintLayout glassStatus1;
    public final ImageView imageAppIcon1;
    public final ImageView imageGlasses1;
    public final ImageView imageNotification;
    public final ImageView ivBg;
    public final ImageView ivHomeVolume;
    public final ImageView ivRecording;
    public final ImageView ivSmartImg;
    public final ImageView ivTakePhoto;
    public final ImageView ivTranslateSub;
    public final ImageView ivVideo;
    public final ImageView ivVoiceHint;
    public final ImageView ivVoiceSub;
    public final ImageView ivVolumeMore;
    public final NestedScrollView nestedScrollDayUp;
    public final ConstraintLayout noBindDevice;
    public final SeekBar pbVolume;
    public final SeekBar pbVolumeDis;
    private final ConstraintLayout rootView;
    public final ImageView showImageSrc;
    public final ImageView showImageSrc1;
    public final ImageView showImageSrc2;
    public final ImageView showImageSrc3;
    public final TextView textDeviceBattery;
    public final TextView textTitle1;
    public final TextView textTitle2;
    public final TextView tvBleStatus;
    public final TextView tvBtInfo1;
    public final TextView tvBtInfo2;
    public final TextView tvDeviceName;
    public final TextView tvOtaInfo1;
    public final TextView tvOtaInfo2;
    public final TextView tvRecording;
    public final TextView tvSmartImg;
    public final TextView tvTakePhoto;
    public final TextView tvTitleSubText;
    public final TextView tvTitleSubText1;
    public final TextView tvTitleSubText2;
    public final TextView tvTitleSubText3;
    public final TextView tvTitleText;
    public final TextView tvTitleText1;
    public final TextView tvTitleText2;
    public final TextView tvTitleText3;
    public final TextView tvToBind;
    public final TextView tvToBt;
    public final TextView tvToOta;
    public final TextView tvTranslateHint1;
    public final TextView tvTranslateHint2;
    public final TextView tvTranslateHint3;
    public final TextView tvVideo;
    public final TextView tvVolumeTitle;
    public final View view1;
    public final View viewDisableVolume;

    private FragmentHomeBinding(ConstraintLayout rootView, BatteryImage batteryView, ConstraintLayout clsRecording, ConstraintLayout clsSmartPicture, ConstraintLayout clsTakePhoto, ConstraintLayout clsVideo, ConstraintLayout clsVolume, ConstraintLayout cslAiCamera, ConstraintLayout cslAiGesture, ConstraintLayout cslAiTranslate, ConstraintLayout cslAiVoice, ConstraintLayout ctlBt, ConstraintLayout ctlOta, ConstraintLayout glassStatus1, ImageView imageAppIcon1, ImageView imageGlasses1, ImageView imageNotification, ImageView ivBg, ImageView ivHomeVolume, ImageView ivRecording, ImageView ivSmartImg, ImageView ivTakePhoto, ImageView ivTranslateSub, ImageView ivVideo, ImageView ivVoiceHint, ImageView ivVoiceSub, ImageView ivVolumeMore, NestedScrollView nestedScrollDayUp, ConstraintLayout noBindDevice, SeekBar pbVolume, SeekBar pbVolumeDis, ImageView showImageSrc, ImageView showImageSrc1, ImageView showImageSrc2, ImageView showImageSrc3, TextView textDeviceBattery, TextView textTitle1, TextView textTitle2, TextView tvBleStatus, TextView tvBtInfo1, TextView tvBtInfo2, TextView tvDeviceName, TextView tvOtaInfo1, TextView tvOtaInfo2, TextView tvRecording, TextView tvSmartImg, TextView tvTakePhoto, TextView tvTitleSubText, TextView tvTitleSubText1, TextView tvTitleSubText2, TextView tvTitleSubText3, TextView tvTitleText, TextView tvTitleText1, TextView tvTitleText2, TextView tvTitleText3, TextView tvToBind, TextView tvToBt, TextView tvToOta, TextView tvTranslateHint1, TextView tvTranslateHint2, TextView tvTranslateHint3, TextView tvVideo, TextView tvVolumeTitle, View view1, View viewDisableVolume) {
        this.rootView = rootView;
        this.batteryView = batteryView;
        this.clsRecording = clsRecording;
        this.clsSmartPicture = clsSmartPicture;
        this.clsTakePhoto = clsTakePhoto;
        this.clsVideo = clsVideo;
        this.clsVolume = clsVolume;
        this.cslAiCamera = cslAiCamera;
        this.cslAiGesture = cslAiGesture;
        this.cslAiTranslate = cslAiTranslate;
        this.cslAiVoice = cslAiVoice;
        this.ctlBt = ctlBt;
        this.ctlOta = ctlOta;
        this.glassStatus1 = glassStatus1;
        this.imageAppIcon1 = imageAppIcon1;
        this.imageGlasses1 = imageGlasses1;
        this.imageNotification = imageNotification;
        this.ivBg = ivBg;
        this.ivHomeVolume = ivHomeVolume;
        this.ivRecording = ivRecording;
        this.ivSmartImg = ivSmartImg;
        this.ivTakePhoto = ivTakePhoto;
        this.ivTranslateSub = ivTranslateSub;
        this.ivVideo = ivVideo;
        this.ivVoiceHint = ivVoiceHint;
        this.ivVoiceSub = ivVoiceSub;
        this.ivVolumeMore = ivVolumeMore;
        this.nestedScrollDayUp = nestedScrollDayUp;
        this.noBindDevice = noBindDevice;
        this.pbVolume = pbVolume;
        this.pbVolumeDis = pbVolumeDis;
        this.showImageSrc = showImageSrc;
        this.showImageSrc1 = showImageSrc1;
        this.showImageSrc2 = showImageSrc2;
        this.showImageSrc3 = showImageSrc3;
        this.textDeviceBattery = textDeviceBattery;
        this.textTitle1 = textTitle1;
        this.textTitle2 = textTitle2;
        this.tvBleStatus = tvBleStatus;
        this.tvBtInfo1 = tvBtInfo1;
        this.tvBtInfo2 = tvBtInfo2;
        this.tvDeviceName = tvDeviceName;
        this.tvOtaInfo1 = tvOtaInfo1;
        this.tvOtaInfo2 = tvOtaInfo2;
        this.tvRecording = tvRecording;
        this.tvSmartImg = tvSmartImg;
        this.tvTakePhoto = tvTakePhoto;
        this.tvTitleSubText = tvTitleSubText;
        this.tvTitleSubText1 = tvTitleSubText1;
        this.tvTitleSubText2 = tvTitleSubText2;
        this.tvTitleSubText3 = tvTitleSubText3;
        this.tvTitleText = tvTitleText;
        this.tvTitleText1 = tvTitleText1;
        this.tvTitleText2 = tvTitleText2;
        this.tvTitleText3 = tvTitleText3;
        this.tvToBind = tvToBind;
        this.tvToBt = tvToBt;
        this.tvToOta = tvToOta;
        this.tvTranslateHint1 = tvTranslateHint1;
        this.tvTranslateHint2 = tvTranslateHint2;
        this.tvTranslateHint3 = tvTranslateHint3;
        this.tvVideo = tvVideo;
        this.tvVolumeTitle = tvVolumeTitle;
        this.view1 = view1;
        this.viewDisableVolume = viewDisableVolume;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentHomeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentHomeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.fragment_home, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentHomeBinding bind(View rootView) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        int i = C0775R.id.battery_view;
        BatteryImage batteryImage = (BatteryImage) ViewBindings.findChildViewById(rootView, i);
        if (batteryImage != null) {
            i = C0775R.id.cls_recording;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout != null) {
                i = C0775R.id.cls_smart_picture;
                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                if (constraintLayout2 != null) {
                    i = C0775R.id.cls_take_photo;
                    ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                    if (constraintLayout3 != null) {
                        i = C0775R.id.cls_video;
                        ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                        if (constraintLayout4 != null) {
                            i = C0775R.id.cls_volume;
                            ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                            if (constraintLayout5 != null) {
                                i = C0775R.id.csl_ai_camera;
                                ConstraintLayout constraintLayout6 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                                if (constraintLayout6 != null) {
                                    i = C0775R.id.csl_ai_gesture;
                                    ConstraintLayout constraintLayout7 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                                    if (constraintLayout7 != null) {
                                        i = C0775R.id.csl_ai_translate;
                                        ConstraintLayout constraintLayout8 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                                        if (constraintLayout8 != null) {
                                            i = C0775R.id.csl_ai_voice;
                                            ConstraintLayout constraintLayout9 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                                            if (constraintLayout9 != null) {
                                                i = C0775R.id.ctl_bt;
                                                ConstraintLayout constraintLayout10 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                                                if (constraintLayout10 != null) {
                                                    i = C0775R.id.ctl_ota;
                                                    ConstraintLayout constraintLayout11 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                                                    if (constraintLayout11 != null) {
                                                        i = C0775R.id.glass_status_1;
                                                        ConstraintLayout constraintLayout12 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                                                        if (constraintLayout12 != null) {
                                                            i = C0775R.id.image_app_icon_1;
                                                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                            if (imageView != null) {
                                                                i = C0775R.id.image_glasses_1;
                                                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                                if (imageView2 != null) {
                                                                    i = C0775R.id.image_notification;
                                                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                                    if (imageView3 != null) {
                                                                        i = C0775R.id.iv_bg;
                                                                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                                        if (imageView4 != null) {
                                                                            i = C0775R.id.iv_home_volume;
                                                                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                                            if (imageView5 != null) {
                                                                                i = C0775R.id.iv_recording;
                                                                                ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                                                if (imageView6 != null) {
                                                                                    i = C0775R.id.iv_smart_img;
                                                                                    ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                                                    if (imageView7 != null) {
                                                                                        i = C0775R.id.iv_take_photo;
                                                                                        ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                                                        if (imageView8 != null) {
                                                                                            i = C0775R.id.iv_translate_sub;
                                                                                            ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                                                            if (imageView9 != null) {
                                                                                                i = C0775R.id.iv_video;
                                                                                                ImageView imageView10 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                                                                if (imageView10 != null) {
                                                                                                    i = C0775R.id.iv_voice_hint;
                                                                                                    ImageView imageView11 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                                                                    if (imageView11 != null) {
                                                                                                        i = C0775R.id.iv_voice_sub;
                                                                                                        ImageView imageView12 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                                                                        if (imageView12 != null) {
                                                                                                            i = C0775R.id.iv_volume_more;
                                                                                                            ImageView imageView13 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                                                                            if (imageView13 != null) {
                                                                                                                i = C0775R.id.nested_scroll_day_up;
                                                                                                                NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                if (nestedScrollView != null) {
                                                                                                                    i = C0775R.id.no_bind_device;
                                                                                                                    ConstraintLayout constraintLayout13 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                                                                                                                    if (constraintLayout13 != null) {
                                                                                                                        i = C0775R.id.pb_volume;
                                                                                                                        SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(rootView, i);
                                                                                                                        if (seekBar != null) {
                                                                                                                            i = C0775R.id.pb_volume_dis;
                                                                                                                            SeekBar seekBar2 = (SeekBar) ViewBindings.findChildViewById(rootView, i);
                                                                                                                            if (seekBar2 != null) {
                                                                                                                                i = C0775R.id.show_image_src;
                                                                                                                                ImageView imageView14 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                if (imageView14 != null) {
                                                                                                                                    i = C0775R.id.show_image_src_1;
                                                                                                                                    ImageView imageView15 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                    if (imageView15 != null) {
                                                                                                                                        i = C0775R.id.show_image_src_2;
                                                                                                                                        ImageView imageView16 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                        if (imageView16 != null) {
                                                                                                                                            i = C0775R.id.show_image_src_3;
                                                                                                                                            ImageView imageView17 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                            if (imageView17 != null) {
                                                                                                                                                i = C0775R.id.text_device_battery;
                                                                                                                                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                if (textView != null) {
                                                                                                                                                    i = C0775R.id.text_title_1;
                                                                                                                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                    if (textView2 != null) {
                                                                                                                                                        i = C0775R.id.text_title_2;
                                                                                                                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                        if (textView3 != null) {
                                                                                                                                                            i = C0775R.id.tv_ble_status;
                                                                                                                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                            if (textView4 != null) {
                                                                                                                                                                i = C0775R.id.tv_bt_info_1;
                                                                                                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                if (textView5 != null) {
                                                                                                                                                                    i = C0775R.id.tv_bt_info_2;
                                                                                                                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                    if (textView6 != null) {
                                                                                                                                                                        i = C0775R.id.tv_device_name;
                                                                                                                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                        if (textView7 != null) {
                                                                                                                                                                            i = C0775R.id.tv_ota_info_1;
                                                                                                                                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                            if (textView8 != null) {
                                                                                                                                                                                i = C0775R.id.tv_ota_info_2;
                                                                                                                                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                                if (textView9 != null) {
                                                                                                                                                                                    i = C0775R.id.tv_recording;
                                                                                                                                                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                                    if (textView10 != null) {
                                                                                                                                                                                        i = C0775R.id.tv_smart_img;
                                                                                                                                                                                        TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                                        if (textView11 != null) {
                                                                                                                                                                                            i = C0775R.id.tv_take_photo;
                                                                                                                                                                                            TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                                            if (textView12 != null) {
                                                                                                                                                                                                i = C0775R.id.tv_title_sub_text;
                                                                                                                                                                                                TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                                                if (textView13 != null) {
                                                                                                                                                                                                    i = C0775R.id.tv_title_sub_text_1;
                                                                                                                                                                                                    TextView textView14 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                                                    if (textView14 != null) {
                                                                                                                                                                                                        i = C0775R.id.tv_title_sub_text_2;
                                                                                                                                                                                                        TextView textView15 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                                                        if (textView15 != null) {
                                                                                                                                                                                                            i = C0775R.id.tv_title_sub_text_3;
                                                                                                                                                                                                            TextView textView16 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                                                            if (textView16 != null) {
                                                                                                                                                                                                                i = C0775R.id.tv_title_text;
                                                                                                                                                                                                                TextView textView17 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                                                                if (textView17 != null) {
                                                                                                                                                                                                                    i = C0775R.id.tv_title_text_1;
                                                                                                                                                                                                                    TextView textView18 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                                                                    if (textView18 != null) {
                                                                                                                                                                                                                        i = C0775R.id.tv_title_text_2;
                                                                                                                                                                                                                        TextView textView19 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                                                                        if (textView19 != null) {
                                                                                                                                                                                                                            i = C0775R.id.tv_title_text_3;
                                                                                                                                                                                                                            TextView textView20 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                                                                            if (textView20 != null) {
                                                                                                                                                                                                                                i = C0775R.id.tv_to_bind;
                                                                                                                                                                                                                                TextView textView21 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                                                                                if (textView21 != null) {
                                                                                                                                                                                                                                    i = C0775R.id.tv_to_bt;
                                                                                                                                                                                                                                    TextView textView22 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                                                                                    if (textView22 != null) {
                                                                                                                                                                                                                                        i = C0775R.id.tv_to_ota;
                                                                                                                                                                                                                                        TextView textView23 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                                                                                        if (textView23 != null) {
                                                                                                                                                                                                                                            i = C0775R.id.tv_translate_hint1;
                                                                                                                                                                                                                                            TextView textView24 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                                                                                            if (textView24 != null) {
                                                                                                                                                                                                                                                i = C0775R.id.tv_translate_hint2;
                                                                                                                                                                                                                                                TextView textView25 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                                                                                                if (textView25 != null) {
                                                                                                                                                                                                                                                    i = C0775R.id.tv_translate_hint3;
                                                                                                                                                                                                                                                    TextView textView26 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                                                                                                    if (textView26 != null) {
                                                                                                                                                                                                                                                        i = C0775R.id.tv_video;
                                                                                                                                                                                                                                                        TextView textView27 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                                                                                                        if (textView27 != null) {
                                                                                                                                                                                                                                                            i = C0775R.id.tv_volume_title;
                                                                                                                                                                                                                                                            TextView textView28 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                                                                                                                                                            if (textView28 != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_1))) != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_disable_volume))) != null) {
                                                                                                                                                                                                                                                                return new FragmentHomeBinding((ConstraintLayout) rootView, batteryImage, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, constraintLayout5, constraintLayout6, constraintLayout7, constraintLayout8, constraintLayout9, constraintLayout10, constraintLayout11, constraintLayout12, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, imageView11, imageView12, imageView13, nestedScrollView, constraintLayout13, seekBar, seekBar2, imageView14, imageView15, imageView16, imageView17, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19, textView20, textView21, textView22, textView23, textView24, textView25, textView26, textView27, textView28, viewFindChildViewById, viewFindChildViewById2);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
