package com.glasssutdio.wear.home.album.detail;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.glasssutdio.wear.all.view.photoview.OnMatrixChangedListener;
import com.glasssutdio.wear.all.view.photoview.PhotoViewAttacher;
import com.glasssutdio.wear.database.entity.GlassAlbumEntity;
import com.glasssutdio.wear.databinding.FragmentDetailImageBinding;
import com.glasssutdio.wear.home.adapter.ImageAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DetailImageFragment.kt */
@Metadata(m606d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J$\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\u0013\u001a\u00020\nH\u0016J\b\u0010\u0014\u001a\u00020\nH\u0016J\b\u0010\u0015\u001a\u00020\nH\u0016J\u000e\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m607d2 = {"Lcom/glasssutdio/wear/home/album/detail/DetailImageFragment;", "Lcom/glasssutdio/wear/home/album/detail/MediaFragment;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/FragmentDetailImageBinding;", "glassAlbumEntity", "Lcom/glasssutdio/wear/database/entity/GlassAlbumEntity;", "scaleListener", "Lcom/glasssutdio/wear/home/adapter/ImageAdapter$PhotoViewScaleListener;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "pause", "play", "release", "setup", "setupImageViw", "mediaItem", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class DetailImageFragment extends MediaFragment {
    private static final String ARG_MEDIA_ITEM = "media_item";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FragmentDetailImageBinding binding;
    private GlassAlbumEntity glassAlbumEntity;
    private ImageAdapter.PhotoViewScaleListener scaleListener;

    @Override // com.glasssutdio.wear.home.album.detail.MediaFragment
    public void pause() {
    }

    @Override // com.glasssutdio.wear.home.album.detail.MediaFragment
    public void play() {
    }

    @Override // com.glasssutdio.wear.home.album.detail.MediaFragment
    public void release() {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        GlassAlbumEntity glassAlbumEntity = arguments != null ? (GlassAlbumEntity) arguments.getParcelable("media_item") : null;
        if (glassAlbumEntity == null) {
            throw new IllegalArgumentException("NewsItem must be provided");
        }
        this.glassAlbumEntity = glassAlbumEntity;
    }

    public final void setup(ImageAdapter.PhotoViewScaleListener scaleListener) {
        Intrinsics.checkNotNullParameter(scaleListener, "scaleListener");
        this.scaleListener = scaleListener;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentDetailImageBinding fragmentDetailImageBindingInflate = FragmentDetailImageBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentDetailImageBindingInflate, "inflate(...)");
        this.binding = fragmentDetailImageBindingInflate;
        GlassAlbumEntity glassAlbumEntity = this.glassAlbumEntity;
        FragmentDetailImageBinding fragmentDetailImageBinding = null;
        if (glassAlbumEntity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("glassAlbumEntity");
            glassAlbumEntity = null;
        }
        setupImageViw(glassAlbumEntity);
        FragmentDetailImageBinding fragmentDetailImageBinding2 = this.binding;
        if (fragmentDetailImageBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentDetailImageBinding = fragmentDetailImageBinding2;
        }
        ConstraintLayout root = fragmentDetailImageBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    private final void setupImageViw(GlassAlbumEntity mediaItem) {
        try {
            RequestBuilder<Drawable> requestBuilderLoad = Glide.with(this).load(mediaItem.getFilePath());
            FragmentDetailImageBinding fragmentDetailImageBinding = this.binding;
            FragmentDetailImageBinding fragmentDetailImageBinding2 = null;
            if (fragmentDetailImageBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDetailImageBinding = null;
            }
            requestBuilderLoad.into(fragmentDetailImageBinding.showImageSrc);
            FragmentDetailImageBinding fragmentDetailImageBinding3 = this.binding;
            if (fragmentDetailImageBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentDetailImageBinding2 = fragmentDetailImageBinding3;
            }
            final PhotoViewAttacher attacher = fragmentDetailImageBinding2.showImageSrc.getAttacher();
            if (attacher != null) {
                attacher.setOnMatrixChangeListener(new OnMatrixChangedListener() { // from class: com.glasssutdio.wear.home.album.detail.DetailImageFragment$$ExternalSyntheticLambda0
                    @Override // com.glasssutdio.wear.all.view.photoview.OnMatrixChangedListener
                    public final void onMatrixChanged(RectF rectF) {
                        DetailImageFragment.setupImageViw$lambda$1$lambda$0(this.f$0, attacher, rectF);
                    }
                });
                attacher.update();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupImageViw$lambda$1$lambda$0(DetailImageFragment this$0, PhotoViewAttacher this_apply, RectF rectF) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        ImageAdapter.PhotoViewScaleListener photoViewScaleListener = this$0.scaleListener;
        if (photoViewScaleListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scaleListener");
            photoViewScaleListener = null;
        }
        photoViewScaleListener.onPhotoViewScaling(!(this_apply.getScale() == 1.0f));
        this_apply.setMaximumScale(2.0f);
        this_apply.setMediumScale(1.5f);
    }

    /* compiled from: DetailImageFragment.kt */
    @Metadata(m606d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/home/album/detail/DetailImageFragment$Companion;", "", "()V", "ARG_MEDIA_ITEM", "", "newInstance", "Lcom/glasssutdio/wear/home/album/detail/DetailImageFragment;", "glassAlbumEntity", "Lcom/glasssutdio/wear/database/entity/GlassAlbumEntity;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DetailImageFragment newInstance(GlassAlbumEntity glassAlbumEntity) {
            Intrinsics.checkNotNullParameter(glassAlbumEntity, "glassAlbumEntity");
            DetailImageFragment detailImageFragment = new DetailImageFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("media_item", glassAlbumEntity);
            detailImageFragment.setArguments(bundle);
            return detailImageFragment;
        }
    }
}
