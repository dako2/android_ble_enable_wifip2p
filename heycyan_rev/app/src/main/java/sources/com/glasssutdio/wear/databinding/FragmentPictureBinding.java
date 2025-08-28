package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.view.CircleProgressView;
import com.glasssutdio.wear.all.view.ConstraintRadioGroup;

/* loaded from: classes.dex */
public final class FragmentPictureBinding implements ViewBinding {
    public final ConstraintRadioGroup diyRadioGroup;
    public final ConstraintLayout glassStatus1;
    public final ImageView imageEdit;
    public final ImageView imageImportDefault;
    public final ImageView imageSetting;
    public final ImageView ivNoPicture;
    public final CircleProgressView mediaProgress;
    public final ConstraintLayout noBindDevice;
    public final RadioButton rb1;
    public final RadioButton rb2;
    public final RadioButton rb3;
    public final RadioButton rb4;
    public final RadioButton rb5;
    private final ConstraintLayout rootView;
    public final ConstraintLayout titleBar;
    public final TextView tvCount;
    public final TextView tvImportBtn;
    public final TextView tvSpeed;
    public final TextView tvTemperature;
    public final TextView tvTitle;
    public final TextView tvTitle2;
    public final TextView tvToBind;
    public final ViewPager2 viewPager1;

    private FragmentPictureBinding(ConstraintLayout rootView, ConstraintRadioGroup diyRadioGroup, ConstraintLayout glassStatus1, ImageView imageEdit, ImageView imageImportDefault, ImageView imageSetting, ImageView ivNoPicture, CircleProgressView mediaProgress, ConstraintLayout noBindDevice, RadioButton rb1, RadioButton rb2, RadioButton rb3, RadioButton rb4, RadioButton rb5, ConstraintLayout titleBar, TextView tvCount, TextView tvImportBtn, TextView tvSpeed, TextView tvTemperature, TextView tvTitle, TextView tvTitle2, TextView tvToBind, ViewPager2 viewPager1) {
        this.rootView = rootView;
        this.diyRadioGroup = diyRadioGroup;
        this.glassStatus1 = glassStatus1;
        this.imageEdit = imageEdit;
        this.imageImportDefault = imageImportDefault;
        this.imageSetting = imageSetting;
        this.ivNoPicture = ivNoPicture;
        this.mediaProgress = mediaProgress;
        this.noBindDevice = noBindDevice;
        this.rb1 = rb1;
        this.rb2 = rb2;
        this.rb3 = rb3;
        this.rb4 = rb4;
        this.rb5 = rb5;
        this.titleBar = titleBar;
        this.tvCount = tvCount;
        this.tvImportBtn = tvImportBtn;
        this.tvSpeed = tvSpeed;
        this.tvTemperature = tvTemperature;
        this.tvTitle = tvTitle;
        this.tvTitle2 = tvTitle2;
        this.tvToBind = tvToBind;
        this.viewPager1 = viewPager1;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPictureBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentPictureBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.fragment_picture, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPictureBinding bind(View rootView) {
        int i = C0775R.id.diy_radio_group;
        ConstraintRadioGroup constraintRadioGroup = (ConstraintRadioGroup) ViewBindings.findChildViewById(rootView, i);
        if (constraintRadioGroup != null) {
            i = C0775R.id.glass_status_1;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout != null) {
                i = C0775R.id.image_edit;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                if (imageView != null) {
                    i = C0775R.id.image_import_default;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                    if (imageView2 != null) {
                        i = C0775R.id.image_setting;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                        if (imageView3 != null) {
                            i = C0775R.id.iv_no_picture;
                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                            if (imageView4 != null) {
                                i = C0775R.id.media_progress;
                                CircleProgressView circleProgressView = (CircleProgressView) ViewBindings.findChildViewById(rootView, i);
                                if (circleProgressView != null) {
                                    i = C0775R.id.no_bind_device;
                                    ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                                    if (constraintLayout2 != null) {
                                        i = C0775R.id.rb_1;
                                        RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(rootView, i);
                                        if (radioButton != null) {
                                            i = C0775R.id.rb_2;
                                            RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(rootView, i);
                                            if (radioButton2 != null) {
                                                i = C0775R.id.rb_3;
                                                RadioButton radioButton3 = (RadioButton) ViewBindings.findChildViewById(rootView, i);
                                                if (radioButton3 != null) {
                                                    i = C0775R.id.rb_4;
                                                    RadioButton radioButton4 = (RadioButton) ViewBindings.findChildViewById(rootView, i);
                                                    if (radioButton4 != null) {
                                                        i = C0775R.id.rb_5;
                                                        RadioButton radioButton5 = (RadioButton) ViewBindings.findChildViewById(rootView, i);
                                                        if (radioButton5 != null) {
                                                            i = C0775R.id.title_bar;
                                                            ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                                                            if (constraintLayout3 != null) {
                                                                i = C0775R.id.tv_count;
                                                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                if (textView != null) {
                                                                    i = C0775R.id.tv_import_btn;
                                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                    if (textView2 != null) {
                                                                        i = C0775R.id.tv_speed;
                                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                        if (textView3 != null) {
                                                                            i = C0775R.id.tv_temperature;
                                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                            if (textView4 != null) {
                                                                                i = C0775R.id.tvTitle;
                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                if (textView5 != null) {
                                                                                    i = C0775R.id.tvTitle2;
                                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                    if (textView6 != null) {
                                                                                        i = C0775R.id.tv_to_bind;
                                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                        if (textView7 != null) {
                                                                                            i = C0775R.id.view_pager_1;
                                                                                            ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(rootView, i);
                                                                                            if (viewPager2 != null) {
                                                                                                return new FragmentPictureBinding((ConstraintLayout) rootView, constraintRadioGroup, constraintLayout, imageView, imageView2, imageView3, imageView4, circleProgressView, constraintLayout2, radioButton, radioButton2, radioButton3, radioButton4, radioButton5, constraintLayout3, textView, textView2, textView3, textView4, textView5, textView6, textView7, viewPager2);
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
