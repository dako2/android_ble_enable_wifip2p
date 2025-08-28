package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class FragmentAiBinding implements ViewBinding {
    public final ConstraintLayout chatNoData;
    public final Guideline guideLine;
    public final Guideline guideLineRight;
    public final ImageView ivCatch;
    public final ImageView ivChatLanguage;
    public final ImageView ivControl;
    public final ImageView ivEdit;
    public final ImageView ivImage;
    public final ImageView ivNote;
    public final ConstraintLayout noBindDevice;
    public final RecyclerView rcvChat;
    private final ConstraintLayout rootView;
    public final TextView tvCatch;
    public final TextView tvCatchHint;
    public final TextView tvControl;
    public final TextView tvControlHint;
    public final TextView tvDesc;
    public final TextView tvEditFinish;
    public final TextView tvNoDataHint;
    public final TextView tvNote;
    public final TextView tvNoteHint;
    public final TextView tvTitle;
    public final TextView tvTitle2;
    public final TextView tvToBind;

    private FragmentAiBinding(ConstraintLayout rootView, ConstraintLayout chatNoData, Guideline guideLine, Guideline guideLineRight, ImageView ivCatch, ImageView ivChatLanguage, ImageView ivControl, ImageView ivEdit, ImageView ivImage, ImageView ivNote, ConstraintLayout noBindDevice, RecyclerView rcvChat, TextView tvCatch, TextView tvCatchHint, TextView tvControl, TextView tvControlHint, TextView tvDesc, TextView tvEditFinish, TextView tvNoDataHint, TextView tvNote, TextView tvNoteHint, TextView tvTitle, TextView tvTitle2, TextView tvToBind) {
        this.rootView = rootView;
        this.chatNoData = chatNoData;
        this.guideLine = guideLine;
        this.guideLineRight = guideLineRight;
        this.ivCatch = ivCatch;
        this.ivChatLanguage = ivChatLanguage;
        this.ivControl = ivControl;
        this.ivEdit = ivEdit;
        this.ivImage = ivImage;
        this.ivNote = ivNote;
        this.noBindDevice = noBindDevice;
        this.rcvChat = rcvChat;
        this.tvCatch = tvCatch;
        this.tvCatchHint = tvCatchHint;
        this.tvControl = tvControl;
        this.tvControlHint = tvControlHint;
        this.tvDesc = tvDesc;
        this.tvEditFinish = tvEditFinish;
        this.tvNoDataHint = tvNoDataHint;
        this.tvNote = tvNote;
        this.tvNoteHint = tvNoteHint;
        this.tvTitle = tvTitle;
        this.tvTitle2 = tvTitle2;
        this.tvToBind = tvToBind;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentAiBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentAiBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.fragment_ai, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentAiBinding bind(View rootView) {
        int i = C0775R.id.chat_no_data;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.guideLine;
            Guideline guideline = (Guideline) ViewBindings.findChildViewById(rootView, i);
            if (guideline != null) {
                i = C0775R.id.guideLineRight;
                Guideline guideline2 = (Guideline) ViewBindings.findChildViewById(rootView, i);
                if (guideline2 != null) {
                    i = C0775R.id.iv_catch;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                    if (imageView != null) {
                        i = C0775R.id.iv_chat_language;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                        if (imageView2 != null) {
                            i = C0775R.id.iv_control;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                            if (imageView3 != null) {
                                i = C0775R.id.iv_edit;
                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                if (imageView4 != null) {
                                    i = C0775R.id.ivImage;
                                    ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                    if (imageView5 != null) {
                                        i = C0775R.id.iv_note;
                                        ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                        if (imageView6 != null) {
                                            i = C0775R.id.no_bind_device;
                                            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                                            if (constraintLayout2 != null) {
                                                i = C0775R.id.rcv_chat;
                                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i);
                                                if (recyclerView != null) {
                                                    i = C0775R.id.tv_catch;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                    if (textView != null) {
                                                        i = C0775R.id.tv_catch_hint;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                        if (textView2 != null) {
                                                            i = C0775R.id.tv_control;
                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                            if (textView3 != null) {
                                                                i = C0775R.id.tv_control_hint;
                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                if (textView4 != null) {
                                                                    i = C0775R.id.tv_desc;
                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                    if (textView5 != null) {
                                                                        i = C0775R.id.tv_edit_finish;
                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                        if (textView6 != null) {
                                                                            i = C0775R.id.tv_no_data_hint;
                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                            if (textView7 != null) {
                                                                                i = C0775R.id.tv_note;
                                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                if (textView8 != null) {
                                                                                    i = C0775R.id.tv_note_hint;
                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                    if (textView9 != null) {
                                                                                        i = C0775R.id.tvTitle;
                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                        if (textView10 != null) {
                                                                                            i = C0775R.id.tvTitle2;
                                                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                            if (textView11 != null) {
                                                                                                i = C0775R.id.tv_to_bind;
                                                                                                TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                                if (textView12 != null) {
                                                                                                    return new FragmentAiBinding((ConstraintLayout) rootView, constraintLayout, guideline, guideline2, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, constraintLayout2, recyclerView, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12);
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
