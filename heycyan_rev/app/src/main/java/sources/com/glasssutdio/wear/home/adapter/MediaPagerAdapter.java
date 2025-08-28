package com.glasssutdio.wear.home.adapter;

import android.util.SparseArray;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.glasssutdio.wear.database.entity.GlassAlbumEntity;
import com.glasssutdio.wear.home.adapter.ImageAdapter;
import com.glasssutdio.wear.home.album.detail.DetailImageFragment;
import com.glasssutdio.wear.home.album.detail.DetailRecordFragment;
import com.glasssutdio.wear.home.album.detail.DetailVideoFragment;
import com.glasssutdio.wear.home.album.detail.MediaFragment;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaPagerAdapter.kt */
@Metadata(m606d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\fJ\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0010R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m607d2 = {"Lcom/glasssutdio/wear/home/adapter/MediaPagerAdapter;", "Landroidx/viewpager2/adapter/FragmentStateAdapter;", "fragmentActivity", "Landroidx/fragment/app/FragmentActivity;", "items", "", "Lcom/glasssutdio/wear/database/entity/GlassAlbumEntity;", "scaleListener", "Lcom/glasssutdio/wear/home/adapter/ImageAdapter$PhotoViewScaleListener;", "(Landroidx/fragment/app/FragmentActivity;Ljava/util/List;Lcom/glasssutdio/wear/home/adapter/ImageAdapter$PhotoViewScaleListener;)V", "activeFragments", "Landroid/util/SparseArray;", "Lcom/glasssutdio/wear/home/album/detail/MediaFragment;", "createFragment", "Landroidx/fragment/app/Fragment;", "position", "", "getItemCount", "pauseAt", "", "playAt", "registerFragment", "fragment", "unregisterFragment", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class MediaPagerAdapter extends FragmentStateAdapter {
    private static final int VIEW_TYPE_IMAGE = 1;
    private static final int VIEW_TYPE_RECORDING = 3;
    private static final int VIEW_TYPE_VIDEO = 2;
    private final SparseArray<MediaFragment> activeFragments;
    private final List<GlassAlbumEntity> items;
    private final ImageAdapter.PhotoViewScaleListener scaleListener;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaPagerAdapter(FragmentActivity fragmentActivity, List<GlassAlbumEntity> items, ImageAdapter.PhotoViewScaleListener scaleListener) {
        super(fragmentActivity);
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(scaleListener, "scaleListener");
        this.items = items;
        this.scaleListener = scaleListener;
        this.activeFragments = new SparseArray<>();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    public Fragment createFragment(int position) {
        DetailImageFragment detailImageFragmentNewInstance;
        int fileType = this.items.get(position).getFileType();
        if (fileType == 1) {
            DetailImageFragment detailImageFragmentNewInstance2 = DetailImageFragment.INSTANCE.newInstance(this.items.get(position));
            detailImageFragmentNewInstance2.setup(this.scaleListener);
            detailImageFragmentNewInstance = detailImageFragmentNewInstance2;
        } else if (fileType == 2) {
            detailImageFragmentNewInstance = DetailVideoFragment.INSTANCE.newInstance(this.items.get(position));
        } else if (fileType == 3) {
            detailImageFragmentNewInstance = DetailRecordFragment.INSTANCE.newInstance(this.items.get(position));
        } else {
            throw new IllegalArgumentException("Invalid view type");
        }
        MediaFragment mediaFragment = detailImageFragmentNewInstance instanceof MediaFragment ? detailImageFragmentNewInstance : null;
        if (mediaFragment != null) {
            registerFragment(position, mediaFragment);
        }
        return detailImageFragmentNewInstance;
    }

    public final void registerFragment(int position, MediaFragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        this.activeFragments.put(position, fragment);
    }

    public final void unregisterFragment(int position) {
        this.activeFragments.remove(position);
    }

    public final void playAt(int position) {
        MediaFragment mediaFragment = this.activeFragments.get(position);
        if (mediaFragment != null) {
            mediaFragment.play();
        }
    }

    public final void pauseAt(int position) {
        MediaFragment mediaFragment = this.activeFragments.get(position);
        if (mediaFragment != null) {
            mediaFragment.pause();
        }
    }
}
