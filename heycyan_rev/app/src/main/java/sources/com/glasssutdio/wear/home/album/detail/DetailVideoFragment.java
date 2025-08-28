package com.glasssutdio.wear.home.album.detail;

import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.utils.time.Interval;
import com.glasssutdio.wear.database.entity.GlassAlbumEntity;
import com.glasssutdio.wear.databinding.FragmentDetailVideoBinding;
import com.oudmon.qc_utils.date.DateUtil;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: DetailVideoFragment.kt */
@Metadata(m606d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H\u0002J\u0018\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0012\u0010\u0015\u001a\u00020\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J$\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u001e\u001a\u00020\u000eH\u0016J\b\u0010\u001f\u001a\u00020\u000eH\u0016J\b\u0010 \u001a\u00020\u000eH\u0016J\b\u0010!\u001a\u00020\u000eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, m607d2 = {"Lcom/glasssutdio/wear/home/album/detail/DetailVideoFragment;", "Lcom/glasssutdio/wear/home/album/detail/MediaFragment;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/FragmentDetailVideoBinding;", "glassAlbumEntity", "Lcom/glasssutdio/wear/database/entity/GlassAlbumEntity;", "interval", "Lcom/glasssutdio/wear/all/utils/time/Interval;", "isMuted", "", "mediaPlayer", "Landroid/media/MediaPlayer;", "initVideoPlayer", "", "mediaItem", "loadVideoFirstFrame", "videoPath", "", "imageView", "Landroid/widget/ImageView;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "pause", "play", "release", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class DetailVideoFragment extends MediaFragment {
    public static final String ARG_MEDIA_ITEM = "media_item";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FragmentDetailVideoBinding binding;
    private GlassAlbumEntity glassAlbumEntity;
    private Interval interval;
    private boolean isMuted;
    private MediaPlayer mediaPlayer;

    @Override // com.glasssutdio.wear.home.album.detail.MediaFragment
    public void pause() {
    }

    @Override // com.glasssutdio.wear.home.album.detail.MediaFragment
    public void play() {
    }

    @Override // com.glasssutdio.wear.home.album.detail.MediaFragment
    public void release() {
        Interval interval = this.interval;
        if (interval != null) {
            interval.cancel();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        GlassAlbumEntity glassAlbumEntity = arguments != null ? (GlassAlbumEntity) arguments.getParcelable(ARG_MEDIA_ITEM) : null;
        if (glassAlbumEntity == null) {
            throw new IllegalArgumentException("NewsItem must be provided");
        }
        this.glassAlbumEntity = glassAlbumEntity;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws IOException {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentDetailVideoBinding fragmentDetailVideoBindingInflate = FragmentDetailVideoBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentDetailVideoBindingInflate, "inflate(...)");
        this.binding = fragmentDetailVideoBindingInflate;
        GlassAlbumEntity glassAlbumEntity = this.glassAlbumEntity;
        FragmentDetailVideoBinding fragmentDetailVideoBinding = null;
        if (glassAlbumEntity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("glassAlbumEntity");
            glassAlbumEntity = null;
        }
        initVideoPlayer(glassAlbumEntity);
        FragmentDetailVideoBinding fragmentDetailVideoBinding2 = this.binding;
        if (fragmentDetailVideoBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentDetailVideoBinding = fragmentDetailVideoBinding2;
        }
        ConstraintLayout root = fragmentDetailVideoBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    private final void initVideoPlayer(GlassAlbumEntity mediaItem) throws IOException {
        final FragmentDetailVideoBinding fragmentDetailVideoBinding = this.binding;
        if (fragmentDetailVideoBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDetailVideoBinding = null;
        }
        fragmentDetailVideoBinding.videoView.setVideoURI(Uri.parse(mediaItem.getFilePath()));
        XLog.m137i(mediaItem.getFilePath());
        String filePath = mediaItem.getFilePath();
        FragmentDetailVideoBinding fragmentDetailVideoBinding2 = this.binding;
        if (fragmentDetailVideoBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDetailVideoBinding2 = null;
        }
        ImageView ivThumbnail = fragmentDetailVideoBinding2.ivThumbnail;
        Intrinsics.checkNotNullExpressionValue(ivThumbnail, "ivThumbnail");
        loadVideoFirstFrame(filePath, ivThumbnail);
        fragmentDetailVideoBinding.tvEnd.setText(DateUtil.secondToStr(mediaItem.getVideoLength()));
        Interval interval = this.interval;
        if (interval != null) {
            interval.cancel();
        }
        Interval intervalLife$default = Interval.life$default(new Interval(-1L, 1L, TimeUnit.SECONDS, 0L, 0L, 16, null), (Fragment) this, (Lifecycle.Event) null, 2, (Object) null);
        this.interval = intervalLife$default;
        if (intervalLife$default != null) {
            intervalLife$default.subscribe(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.album.detail.DetailVideoFragment$initVideoPlayer$1$1$1
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Interval interval2, Long l) {
                    invoke(interval2, l.longValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Interval subscribe, long j) {
                    Intrinsics.checkNotNullParameter(subscribe, "$this$subscribe");
                    if (fragmentDetailVideoBinding.videoView.getCurrentPosition() > 500) {
                        fragmentDetailVideoBinding.pbProgress.setProgress(fragmentDetailVideoBinding.videoView.getCurrentPosition());
                        fragmentDetailVideoBinding.ivPlay.setSelected(!fragmentDetailVideoBinding.videoView.isPlaying());
                        int iRoundToInt = MathKt.roundToInt(fragmentDetailVideoBinding.videoView.getCurrentPosition() / 1000.0f);
                        fragmentDetailVideoBinding.tvStart.setText(DateUtil.secondToStr(iRoundToInt));
                        XLog.m137i("进度：" + fragmentDetailVideoBinding.videoView.getCurrentPosition() + ",开始时间：" + iRoundToInt + ",格式化时间：" + DateUtil.secondToStr(iRoundToInt));
                    }
                }
            }).finish(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.album.detail.DetailVideoFragment$initVideoPlayer$1$1$2
                public final void invoke(Interval finish, long j) {
                    Intrinsics.checkNotNullParameter(finish, "$this$finish");
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Interval interval2, Long l) {
                    invoke(interval2, l.longValue());
                    return Unit.INSTANCE;
                }
            });
        }
        fragmentDetailVideoBinding.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.glasssutdio.wear.home.album.detail.DetailVideoFragment$$ExternalSyntheticLambda0
            @Override // android.media.MediaPlayer.OnPreparedListener
            public final void onPrepared(MediaPlayer mediaPlayer) {
                DetailVideoFragment.initVideoPlayer$lambda$5$lambda$1(this.f$0, fragmentDetailVideoBinding, mediaPlayer);
            }
        });
        fragmentDetailVideoBinding.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.glasssutdio.wear.home.album.detail.DetailVideoFragment$$ExternalSyntheticLambda1
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer) {
                DetailVideoFragment.initVideoPlayer$lambda$5$lambda$2(fragmentDetailVideoBinding, mediaPlayer);
            }
        });
        fragmentDetailVideoBinding.ivPlay.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.album.detail.DetailVideoFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DetailVideoFragment.initVideoPlayer$lambda$5$lambda$3(fragmentDetailVideoBinding, this, view);
            }
        });
        fragmentDetailVideoBinding.ivSound.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.album.detail.DetailVideoFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DetailVideoFragment.initVideoPlayer$lambda$5$lambda$4(this.f$0, fragmentDetailVideoBinding, view);
            }
        });
        fragmentDetailVideoBinding.pbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.glasssutdio.wear.home.album.detail.DetailVideoFragment$initVideoPlayer$1$6
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    fragmentDetailVideoBinding.videoView.seekTo(progress);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initVideoPlayer$lambda$5$lambda$1(DetailVideoFragment this$0, FragmentDetailVideoBinding this_run, MediaPlayer mediaPlayer) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        FragmentDetailVideoBinding fragmentDetailVideoBinding = this$0.binding;
        if (fragmentDetailVideoBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDetailVideoBinding = null;
        }
        ViewKt.gone(fragmentDetailVideoBinding.ivThumbnail);
        this$0.mediaPlayer = mediaPlayer;
        this_run.pbProgress.setMax(mediaPlayer.getDuration());
        Interval interval = this$0.interval;
        if (interval != null) {
            interval.start();
        }
        this_run.videoView.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initVideoPlayer$lambda$5$lambda$2(FragmentDetailVideoBinding this_run, MediaPlayer mediaPlayer) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        XLog.m137i("播放完成");
        this_run.pbProgress.setProgress(0);
        this_run.tvStart.setText(DateUtil.secondToStr(0));
        this_run.ivPlay.setSelected(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initVideoPlayer$lambda$5$lambda$3(FragmentDetailVideoBinding this_run, DetailVideoFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this_run.videoView.isPlaying()) {
            this_run.videoView.pause();
            Interval interval = this$0.interval;
            if (interval != null) {
                interval.pause();
            }
        } else {
            this_run.videoView.start();
            Interval interval2 = this$0.interval;
            if (interval2 != null) {
                interval2.resume();
            }
        }
        this_run.ivPlay.setSelected(!this_run.videoView.isPlaying());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initVideoPlayer$lambda$5$lambda$4(DetailVideoFragment this$0, FragmentDetailVideoBinding this_run, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        boolean z = this$0.isMuted;
        this$0.isMuted = !z;
        if (!z) {
            MediaPlayer mediaPlayer = this$0.mediaPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(0.0f, 0.0f);
            }
        } else {
            MediaPlayer mediaPlayer2 = this$0.mediaPlayer;
            if (mediaPlayer2 != null) {
                mediaPlayer2.setVolume(1.0f, 1.0f);
            }
        }
        this_run.ivSound.setSelected(this$0.isMuted);
    }

    private final void loadVideoFirstFrame(String videoPath, ImageView imageView) throws IOException {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                mediaMetadataRetriever.setDataSource(videoPath);
                imageView.setImageBitmap(mediaMetadataRetriever.getFrameAtTime(1L, 2));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            mediaMetadataRetriever.release();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Interval interval = this.interval;
        if (interval == null || interval == null) {
            return;
        }
        interval.cancel();
    }

    /* compiled from: DetailVideoFragment.kt */
    @Metadata(m606d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/home/album/detail/DetailVideoFragment$Companion;", "", "()V", "ARG_MEDIA_ITEM", "", "newInstance", "Lcom/glasssutdio/wear/home/album/detail/DetailVideoFragment;", "glassAlbumEntity", "Lcom/glasssutdio/wear/database/entity/GlassAlbumEntity;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DetailVideoFragment newInstance(GlassAlbumEntity glassAlbumEntity) {
            Intrinsics.checkNotNullParameter(glassAlbumEntity, "glassAlbumEntity");
            DetailVideoFragment detailVideoFragment = new DetailVideoFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(DetailVideoFragment.ARG_MEDIA_ITEM, glassAlbumEntity);
            detailVideoFragment.setArguments(bundle);
            return detailVideoFragment;
        }
    }
}
