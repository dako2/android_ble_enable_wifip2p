package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityEditAlbumBinding implements ViewBinding {
    public final ConstraintLayout ctlEdit;
    public final ConstraintLayout main;
    public final RecyclerView rcvAlbumAll;
    private final ConstraintLayout rootView;
    public final TextView tvDelete;
    public final TextView tvEdit;
    public final TextView tvLike;
    public final TextView tvSave;
    public final TextView tvSelectAll;
    public final TextView tvSelectSave;
    public final TextView tvShare;
    public final TextView tvTitle;

    private ActivityEditAlbumBinding(ConstraintLayout rootView, ConstraintLayout ctlEdit, ConstraintLayout main, RecyclerView rcvAlbumAll, TextView tvDelete, TextView tvEdit, TextView tvLike, TextView tvSave, TextView tvSelectAll, TextView tvSelectSave, TextView tvShare, TextView tvTitle) {
        this.rootView = rootView;
        this.ctlEdit = ctlEdit;
        this.main = main;
        this.rcvAlbumAll = rcvAlbumAll;
        this.tvDelete = tvDelete;
        this.tvEdit = tvEdit;
        this.tvLike = tvLike;
        this.tvSave = tvSave;
        this.tvSelectAll = tvSelectAll;
        this.tvSelectSave = tvSelectSave;
        this.tvShare = tvShare;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityEditAlbumBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityEditAlbumBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_edit_album, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityEditAlbumBinding bind(View rootView) {
        int i = C0775R.id.ctl_edit;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            ConstraintLayout constraintLayout2 = (ConstraintLayout) rootView;
            i = C0775R.id.rcv_album_all;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i);
            if (recyclerView != null) {
                i = C0775R.id.tv_delete;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView != null) {
                    i = C0775R.id.tv_edit;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView2 != null) {
                        i = C0775R.id.tv_like;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView3 != null) {
                            i = C0775R.id.tv_save;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView4 != null) {
                                i = C0775R.id.tv_select_all;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                if (textView5 != null) {
                                    i = C0775R.id.tv_select_save;
                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                    if (textView6 != null) {
                                        i = C0775R.id.tv_share;
                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                        if (textView7 != null) {
                                            i = C0775R.id.tvTitle;
                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                            if (textView8 != null) {
                                                return new ActivityEditAlbumBinding(constraintLayout2, constraintLayout, constraintLayout2, recyclerView, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8);
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
