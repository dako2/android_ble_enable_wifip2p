package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.view.GridImageRecyclerView;

/* loaded from: classes.dex */
public final class ActivityFeedbackBinding implements ViewBinding {
    public final TextView btnSelectFile;
    public final ConstraintLayout clsDevice;
    public final ConstraintLayout clsFeedbackType;
    public final ConstraintLayout clsLog;
    public final ConstraintLayout clsPhoto;
    public final ConstraintLayout clsTimes;
    public final EditText etContent;
    public final EditText etSummary;
    public final ImageView ivArrow2;
    public final ImageView ivArrow3;
    public final ImageView ivArrow4;
    public final ConstraintLayout main;
    public final GridImageRecyclerView rcyImage;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvConfirm;
    public final TextView tvDeviceName;
    public final TextView tvFileName;
    public final TextView tvFrequency;
    public final TextView tvFrequencyHint;
    public final TextView tvHint;
    public final TextView tvHint2;
    public final TextView tvHint3;
    public final TextView tvLogHint;
    public final TextView tvLogHint2;
    public final TextView tvLogHint3;
    public final TextView tvNum;
    public final TextView tvPhotoHint;
    public final TextView tvTypeHint;
    public final TextView tvTypeName;

    private ActivityFeedbackBinding(ConstraintLayout rootView, TextView btnSelectFile, ConstraintLayout clsDevice, ConstraintLayout clsFeedbackType, ConstraintLayout clsLog, ConstraintLayout clsPhoto, ConstraintLayout clsTimes, EditText etContent, EditText etSummary, ImageView ivArrow2, ImageView ivArrow3, ImageView ivArrow4, ConstraintLayout main, GridImageRecyclerView rcyImage, LayoutTitleBarBinding title, TextView tvConfirm, TextView tvDeviceName, TextView tvFileName, TextView tvFrequency, TextView tvFrequencyHint, TextView tvHint, TextView tvHint2, TextView tvHint3, TextView tvLogHint, TextView tvLogHint2, TextView tvLogHint3, TextView tvNum, TextView tvPhotoHint, TextView tvTypeHint, TextView tvTypeName) {
        this.rootView = rootView;
        this.btnSelectFile = btnSelectFile;
        this.clsDevice = clsDevice;
        this.clsFeedbackType = clsFeedbackType;
        this.clsLog = clsLog;
        this.clsPhoto = clsPhoto;
        this.clsTimes = clsTimes;
        this.etContent = etContent;
        this.etSummary = etSummary;
        this.ivArrow2 = ivArrow2;
        this.ivArrow3 = ivArrow3;
        this.ivArrow4 = ivArrow4;
        this.main = main;
        this.rcyImage = rcyImage;
        this.title = title;
        this.tvConfirm = tvConfirm;
        this.tvDeviceName = tvDeviceName;
        this.tvFileName = tvFileName;
        this.tvFrequency = tvFrequency;
        this.tvFrequencyHint = tvFrequencyHint;
        this.tvHint = tvHint;
        this.tvHint2 = tvHint2;
        this.tvHint3 = tvHint3;
        this.tvLogHint = tvLogHint;
        this.tvLogHint2 = tvLogHint2;
        this.tvLogHint3 = tvLogHint3;
        this.tvNum = tvNum;
        this.tvPhotoHint = tvPhotoHint;
        this.tvTypeHint = tvTypeHint;
        this.tvTypeName = tvTypeName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityFeedbackBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityFeedbackBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_feedback, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityFeedbackBinding bind(View rootView) {
        View viewFindChildViewById;
        int i = C0775R.id.btn_select_file;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
        if (textView != null) {
            i = C0775R.id.cls_device;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout != null) {
                i = C0775R.id.cls_feedback_type;
                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                if (constraintLayout2 != null) {
                    i = C0775R.id.cls_log;
                    ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                    if (constraintLayout3 != null) {
                        i = C0775R.id.cls_photo;
                        ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                        if (constraintLayout4 != null) {
                            i = C0775R.id.cls_times;
                            ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                            if (constraintLayout5 != null) {
                                i = C0775R.id.et_content;
                                EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i);
                                if (editText != null) {
                                    i = C0775R.id.et_summary;
                                    EditText editText2 = (EditText) ViewBindings.findChildViewById(rootView, i);
                                    if (editText2 != null) {
                                        i = C0775R.id.iv_arrow2;
                                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                        if (imageView != null) {
                                            i = C0775R.id.iv_arrow3;
                                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                            if (imageView2 != null) {
                                                i = C0775R.id.iv_arrow4;
                                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                                if (imageView3 != null) {
                                                    ConstraintLayout constraintLayout6 = (ConstraintLayout) rootView;
                                                    i = C0775R.id.rcy_image;
                                                    GridImageRecyclerView gridImageRecyclerView = (GridImageRecyclerView) ViewBindings.findChildViewById(rootView, i);
                                                    if (gridImageRecyclerView != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i = C0775R.id.title))) != null) {
                                                        LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                                        i = C0775R.id.tv_confirm;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                        if (textView2 != null) {
                                                            i = C0775R.id.tv_device_name;
                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                            if (textView3 != null) {
                                                                i = C0775R.id.tv_file_name;
                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                if (textView4 != null) {
                                                                    i = C0775R.id.tv_frequency;
                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                    if (textView5 != null) {
                                                                        i = C0775R.id.tv_frequency_hint;
                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                        if (textView6 != null) {
                                                                            i = C0775R.id.tv_hint;
                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                            if (textView7 != null) {
                                                                                i = C0775R.id.tv_hint2;
                                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                if (textView8 != null) {
                                                                                    i = C0775R.id.tv_hint3;
                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                    if (textView9 != null) {
                                                                                        i = C0775R.id.tv_log_hint;
                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                        if (textView10 != null) {
                                                                                            i = C0775R.id.tv_log_hint_2;
                                                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                            if (textView11 != null) {
                                                                                                i = C0775R.id.tv_log_hint_3;
                                                                                                TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                if (textView12 != null) {
                                                                                                    i = C0775R.id.tv_num;
                                                                                                    TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                    if (textView13 != null) {
                                                                                                        i = C0775R.id.tv_photo_hint;
                                                                                                        TextView textView14 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                        if (textView14 != null) {
                                                                                                            i = C0775R.id.tv_type_hint;
                                                                                                            TextView textView15 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                            if (textView15 != null) {
                                                                                                                i = C0775R.id.tv_type_name;
                                                                                                                TextView textView16 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                                if (textView16 != null) {
                                                                                                                    return new ActivityFeedbackBinding(constraintLayout6, textView, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, constraintLayout5, editText, editText2, imageView, imageView2, imageView3, constraintLayout6, gridImageRecyclerView, layoutTitleBarBindingBind, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16);
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
