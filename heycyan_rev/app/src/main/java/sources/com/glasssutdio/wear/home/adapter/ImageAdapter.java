package com.glasssutdio.wear.home.adapter;

import android.content.Context;
import android.graphics.RectF;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.utils.audio.ADAudioTrackPlayer;
import com.glasssutdio.wear.all.utils.time.Interval;
import com.glasssutdio.wear.all.view.PcmWaveformView;
import com.glasssutdio.wear.all.view.photoview.OnMatrixChangedListener;
import com.glasssutdio.wear.all.view.photoview.PhotoView;
import com.glasssutdio.wear.all.view.photoview.PhotoViewAttacher;
import com.glasssutdio.wear.database.entity.GlassAlbumEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpStatus;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.ClientCookie;
import com.liulishuo.okdownload.DownloadTask;
import com.oudmon.qc_utils.date.DateUtil;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: ImageAdapter.kt */
@Metadata(m606d1 = {"\u0000¡\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0017\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0012\n\u0002\b\u0007*\u0001%\u0018\u0000 N2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0006MNOPQRB#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001c\u0010\u001c\u001a\u00020\u001d2\n\u0010\u001e\u001a\u00060\u001fR\u00020\u00002\u0006\u0010 \u001a\u00020!H\u0002J\u001c\u0010\"\u001a\u00020\u001d2\n\u0010\u001e\u001a\u00060#R\u00020\u00002\u0006\u0010 \u001a\u00020!H\u0002J\u0019\u0010$\u001a\u00020%2\n\u0010\u001e\u001a\u00060&R\u00020\u0000H\u0002¢\u0006\u0002\u0010'J\u0010\u0010(\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020!H\u0016J\u0010\u0010,\u001a\u00020!2\u0006\u0010 \u001a\u00020!H\u0016J\u000e\u0010-\u001a\u00020\u001d2\u0006\u0010.\u001a\u00020!J$\u0010/\u001a\u00020\u001d2\n\u0010\u001e\u001a\u00060#R\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u00100\u001a\u00020\u0007H\u0002J\u001c\u00101\u001a\u00020\u001d2\n\u0010\u001e\u001a\u00060&R\u00020\u00002\u0006\u0010 \u001a\u00020!H\u0002J$\u00102\u001a\u00020\u001d2\u0006\u00103\u001a\u00020\u001a2\u0012\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001d05H\u0002J\u0018\u00106\u001a\u00020\u001d2\u0006\u00107\u001a\u00020\u001a2\u0006\u00108\u001a\u000209H\u0002J\u0018\u0010:\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00022\u0006\u0010 \u001a\u00020!H\u0016J\u0018\u0010;\u001a\u00020\u00022\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020!H\u0016J\u0010\u0010?\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0002H\u0016J\u0006\u0010@\u001a\u00020\u001dJ\u0018\u0010A\u001a\u00020\u001d2\u0006\u0010B\u001a\u00020\u00152\u0006\u0010C\u001a\u00020!H\u0007J\u0010\u0010D\u001a\u00020\u001d2\u0006\u0010E\u001a\u000209H\u0002J\u0014\u0010F\u001a\u00020\u001d2\n\u0010\u001e\u001a\u00060&R\u00020\u0000H\u0002J,\u0010G\u001a\u00020\u001d2\n\u0010\u001e\u001a\u00060#R\u00020\u00002\u0006\u0010H\u001a\u00020*2\u0006\u0010I\u001a\u00020*2\u0006\u0010J\u001a\u00020!H\u0002J\f\u0010K\u001a\u00020\u001b*\u00020LH\u0002R\u0012\u0010\u000b\u001a\u00060\fR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006S"}, m607d2 = {"Lcom/glasssutdio/wear/home/adapter/ImageAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "context", "Landroid/content/Context;", "mediaList", "", "Lcom/glasssutdio/wear/database/entity/GlassAlbumEntity;", "scaleListener", "Lcom/glasssutdio/wear/home/adapter/ImageAdapter$PhotoViewScaleListener;", "(Landroid/content/Context;Ljava/util/List;Lcom/glasssutdio/wear/home/adapter/ImageAdapter$PhotoViewScaleListener;)V", "audioPlayController", "Lcom/glasssutdio/wear/home/adapter/ImageAdapter$AudioPlayController;", "interval", "Lcom/glasssutdio/wear/all/utils/time/Interval;", "isMuted", "", "()Z", "setMuted", "(Z)V", "mVideoView", "Landroid/widget/VideoView;", "mediaPlayer", "Landroid/media/MediaPlayer;", "pcmCache", "Landroid/util/LruCache;", "", "", "bindImage", "", "holder", "Lcom/glasssutdio/wear/home/adapter/ImageAdapter$ImageViewHolder;", "position", "", "bindRecording", "Lcom/glasssutdio/wear/home/adapter/ImageAdapter$RecordViewHolder;", "createSeekListener", "com/glasssutdio/wear/home/adapter/ImageAdapter$createSeekListener$1", "Lcom/glasssutdio/wear/home/adapter/ImageAdapter$VideoViewHolder;", "(Lcom/glasssutdio/wear/home/adapter/ImageAdapter$VideoViewHolder;)Lcom/glasssutdio/wear/home/adapter/ImageAdapter$createSeekListener$1;", "formatMillis", "millis", "", "getItemCount", "getItemViewType", "handlePageChange", "newPosition", "initAudioPlayer", "mediaItem", "initVideoPlayer", "loadPcmData", ClientCookie.PATH_ATTR, "callback", "Lkotlin/Function1;", "loadVideoFirstFrame", "videoPath", "imageView", "Landroid/widget/ImageView;", "onBindViewHolder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onViewRecycled", "releaseAllResources", "safeSeekTo", "videoView", "positionMs", "toggleSoundMute", "view", "toggleVideoPlayback", "updateRecordUI", "current", "total", "state", "toShortArray", "", "AudioPlayController", "Companion", "ImageViewHolder", "PhotoViewScaleListener", "RecordViewHolder", "VideoViewHolder", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int MAX_CACHE_SIZE = 5;
    private static final int VIEW_TYPE_IMAGE = 1;
    private static final int VIEW_TYPE_RECORDING = 3;
    private static final int VIEW_TYPE_VIDEO = 2;
    private final AudioPlayController audioPlayController;
    private final Context context;
    private Interval interval;
    private boolean isMuted;
    private VideoView mVideoView;
    private final List<GlassAlbumEntity> mediaList;
    private MediaPlayer mediaPlayer;
    private final LruCache<String, short[]> pcmCache;
    private final PhotoViewScaleListener scaleListener;

    /* compiled from: ImageAdapter.kt */
    @Metadata(m606d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m607d2 = {"Lcom/glasssutdio/wear/home/adapter/ImageAdapter$PhotoViewScaleListener;", "", "onPhotoViewScaling", "", "isScaling", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public interface PhotoViewScaleListener {
        void onPhotoViewScaling(boolean isScaling);
    }

    public ImageAdapter(Context context, List<GlassAlbumEntity> mediaList, PhotoViewScaleListener scaleListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaList, "mediaList");
        Intrinsics.checkNotNullParameter(scaleListener, "scaleListener");
        this.context = context;
        this.mediaList = mediaList;
        this.scaleListener = scaleListener;
        this.audioPlayController = new AudioPlayController();
        this.pcmCache = new LruCache<>(5);
        this.interval = new Interval(-1L, 400L, TimeUnit.MILLISECONDS, 0L, 0L, 16, null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (viewType == 1) {
            View viewInflate = LayoutInflater.from(parent.getContext()).inflate(C0775R.layout.recycle_view_image_show_detail_image, parent, false);
            Intrinsics.checkNotNullExpressionValue(viewInflate, "inflate(...)");
            return new ImageViewHolder(this, viewInflate);
        }
        if (viewType == 2) {
            View viewInflate2 = LayoutInflater.from(parent.getContext()).inflate(C0775R.layout.recycle_view_image_show_detail_video, parent, false);
            Intrinsics.checkNotNullExpressionValue(viewInflate2, "inflate(...)");
            return new VideoViewHolder(this, viewInflate2);
        }
        if (viewType == 3) {
            View viewInflate3 = LayoutInflater.from(parent.getContext()).inflate(C0775R.layout.recycle_view_image_show_detail_recording, parent, false);
            Intrinsics.checkNotNullExpressionValue(viewInflate3, "inflate(...)");
            return new RecordViewHolder(this, viewInflate3);
        }
        throw new IllegalArgumentException("Invalid view type");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) throws Exception {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof ImageViewHolder) {
            bindImage((ImageViewHolder) holder, position);
        } else if (holder instanceof VideoViewHolder) {
            initVideoPlayer((VideoViewHolder) holder, position);
        } else if (holder instanceof RecordViewHolder) {
            bindRecording((RecordViewHolder) holder, position);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        super.onViewRecycled(holder);
        if (holder instanceof VideoViewHolder) {
            ((VideoViewHolder) holder).resetPlayer();
        } else if (holder instanceof RecordViewHolder) {
            RecordViewHolder recordViewHolder = (RecordViewHolder) holder;
            this.audioPlayController.unregister(recordViewHolder.getBindingAdapterPosition());
            recordViewHolder.resetUI();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mediaList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        int fileType = this.mediaList.get(position).getFileType();
        int i = 1;
        if (fileType != 1) {
            i = 2;
            if (fileType != 2) {
                i = 3;
                if (fileType != 3) {
                    throw new IllegalArgumentException("Invalid media type");
                }
            }
        }
        return i;
    }

    private final void bindImage(ImageViewHolder holder, int position) {
        try {
            Glide.with(holder.itemView).load(this.mediaList.get(position).getFilePath()).into(holder.getImageView());
            final PhotoViewAttacher attacher = holder.getImageView().getAttacher();
            if (attacher != null) {
                attacher.setOnMatrixChangeListener(new OnMatrixChangedListener() { // from class: com.glasssutdio.wear.home.adapter.ImageAdapter$$ExternalSyntheticLambda0
                    @Override // com.glasssutdio.wear.all.view.photoview.OnMatrixChangedListener
                    public final void onMatrixChanged(RectF rectF) {
                        ImageAdapter.bindImage$lambda$1$lambda$0(this.f$0, attacher, rectF);
                    }
                });
                attacher.update();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindImage$lambda$1$lambda$0(ImageAdapter this$0, PhotoViewAttacher this_apply, RectF rectF) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this$0.scaleListener.onPhotoViewScaling(!(this_apply.getScale() == 1.0f));
        this_apply.setMaximumScale(2.0f);
        this_apply.setMediumScale(1.5f);
    }

    /* renamed from: isMuted, reason: from getter */
    public final boolean getIsMuted() {
        return this.isMuted;
    }

    public final void setMuted(boolean z) {
        this.isMuted = z;
    }

    private final void initVideoPlayer(final VideoViewHolder holder, int position) throws Exception {
        final GlassAlbumEntity glassAlbumEntity = this.mediaList.get(position);
        holder.getPlayerView().setVideoURI(Uri.parse(glassAlbumEntity.getFilePath()));
        loadVideoFirstFrame(glassAlbumEntity.getFilePath(), holder.getThumbnail());
        holder.getTvEnd().setText(DateUtil.secondToStr(glassAlbumEntity.getVideoLength()));
        this.interval.subscribe(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.adapter.ImageAdapter$initVideoPlayer$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Interval interval, Long l) {
                invoke(interval, l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Interval subscribe, long j) {
                Intrinsics.checkNotNullParameter(subscribe, "$this$subscribe");
                holder.getIvSound().setSelected(this.getIsMuted());
                if (holder.getPlayerView().getCurrentPosition() > 400) {
                    XLog.m137i("playerView.currentPosition:" + holder.getPlayerView().getCurrentPosition());
                    holder.getPbProgress().setProgress(holder.getPlayerView().getCurrentPosition());
                    holder.getIvPlay().setSelected(!holder.getPlayerView().isPlaying());
                    holder.getTvStart().setText(DateUtil.secondToStr((int) (holder.getPlayerView().getCurrentPosition() / 1000.0f)));
                    return;
                }
                holder.getPbProgress().setProgress(0);
            }
        });
        holder.getPlayerView().setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.glasssutdio.wear.home.adapter.ImageAdapter$$ExternalSyntheticLambda1
            @Override // android.media.MediaPlayer.OnPreparedListener
            public final void onPrepared(MediaPlayer mediaPlayer) {
                ImageAdapter.initVideoPlayer$lambda$6$lambda$2(holder, this, holder, glassAlbumEntity, mediaPlayer);
            }
        });
        holder.getPlayerView().setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.glasssutdio.wear.home.adapter.ImageAdapter$$ExternalSyntheticLambda2
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer) {
                ImageAdapter.initVideoPlayer$lambda$6$lambda$3(holder, glassAlbumEntity, this, mediaPlayer);
            }
        });
        holder.getIvPlay().setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.adapter.ImageAdapter$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ImageAdapter.initVideoPlayer$lambda$6$lambda$4(this.f$0, holder, view);
            }
        });
        holder.getIvSound().setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.adapter.ImageAdapter$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ImageAdapter.initVideoPlayer$lambda$6$lambda$5(this.f$0, view);
            }
        });
        holder.getPbProgress().setOnSeekBarChangeListener(createSeekListener(holder));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initVideoPlayer$lambda$6$lambda$2(VideoViewHolder holder, ImageAdapter this$0, VideoViewHolder this_run, GlassAlbumEntity mediaItem, MediaPlayer mediaPlayer) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(mediaItem, "$mediaItem");
        ViewKt.gone(holder.getThumbnail());
        this$0.mediaPlayer = mediaPlayer;
        this_run.getPbProgress().setMax(mediaItem.getVideoLength() * 1000);
        this$0.interval.start();
        this_run.getPlayerView().start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initVideoPlayer$lambda$6$lambda$3(VideoViewHolder this_run, GlassAlbumEntity mediaItem, ImageAdapter this$0, MediaPlayer mediaPlayer) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(mediaItem, "$mediaItem");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this_run.getPbProgress().setProgress((mediaItem.getVideoLength() * 1000) + HttpStatus.SC_BAD_REQUEST);
        this$0.interval.pause();
        this_run.getIvPlay().setSelected(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initVideoPlayer$lambda$6$lambda$4(ImageAdapter this$0, VideoViewHolder holder, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(holder, "$holder");
        this$0.toggleVideoPlayback(holder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initVideoPlayer$lambda$6$lambda$5(ImageAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.ImageView");
        this$0.toggleSoundMute((ImageView) view);
    }

    public final void safeSeekTo(VideoView videoView, int positionMs) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter(videoView, "videoView");
        try {
            Field declaredField = VideoView.class.getDeclaredField("mMediaPlayer");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(videoView);
            MediaPlayer mediaPlayer = obj instanceof MediaPlayer ? (MediaPlayer) obj : null;
            if (mediaPlayer != null) {
                mediaPlayer.seekTo(positionMs, 3);
            }
        } catch (Exception unused) {
            videoView.seekTo(positionMs);
        }
    }

    private final void bindRecording(final RecordViewHolder holder, final int position) throws IllegalStateException {
        ADAudioTrackPlayer.initialize(this.context);
        final GlassAlbumEntity glassAlbumEntity = this.mediaList.get(position);
        holder.resetUI();
        short[] sArr = this.pcmCache.get(glassAlbumEntity.getFilePath());
        if (sArr != null) {
            holder.getWaveView().setPcmData(sArr, 16000);
            initAudioPlayer(holder, position, glassAlbumEntity);
        } else {
            loadPcmData(glassAlbumEntity.getFilePath(), (Function1) new Function1<short[], Unit>() { // from class: com.glasssutdio.wear.home.adapter.ImageAdapter.bindRecording.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(short[] sArr2) throws IllegalStateException {
                    invoke2(sArr2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(short[] pcmData) throws IllegalStateException {
                    Intrinsics.checkNotNullParameter(pcmData, "pcmData");
                    ImageAdapter.this.pcmCache.put(glassAlbumEntity.getFilePath(), pcmData);
                    holder.getWaveView().setPcmData(pcmData, 16000);
                    ImageAdapter.this.initAudioPlayer(holder, position, glassAlbumEntity);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initAudioPlayer(final RecordViewHolder holder, final int position, GlassAlbumEntity mediaItem) throws IllegalStateException {
        this.audioPlayController.register(position, mediaItem.getFilePath(), new ADAudioTrackPlayer.ProgressCallback() { // from class: com.glasssutdio.wear.home.adapter.ImageAdapter$$ExternalSyntheticLambda5
            @Override // com.glasssutdio.wear.all.utils.audio.ADAudioTrackPlayer.ProgressCallback
            public final void onProgressUpdate(long j, long j2, int i) {
                ImageAdapter.initAudioPlayer$lambda$8(holder, position, this, j, j2, i);
            }
        });
        final ADAudioTrackPlayer aDAudioTrackPlayer = ADAudioTrackPlayer.getInstance();
        holder.getIvSound().setSelected(this.audioPlayController.isMuted());
        holder.getIvPlay().setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.adapter.ImageAdapter$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IllegalStateException {
                ImageAdapter.initAudioPlayer$lambda$11$lambda$9(aDAudioTrackPlayer, view);
            }
        });
        holder.getIvSound().setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.adapter.ImageAdapter$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ImageAdapter.initAudioPlayer$lambda$11$lambda$10(this.f$0, holder, view);
            }
        });
        holder.getPbProgress().setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.glasssutdio.wear.home.adapter.ImageAdapter$initAudioPlayer$2$3
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar sb) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) throws IOException {
                Intrinsics.checkNotNullParameter(sb, "sb");
                if (fromUser) {
                    aDAudioTrackPlayer.seekTo(progress / 100, true);
                    this.updateRecordUI(holder, aDAudioTrackPlayer.getPlayedTime(), aDAudioTrackPlayer.getTotalTime(), aDAudioTrackPlayer.state);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar sb) throws IllegalStateException {
                aDAudioTrackPlayer.pause();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initAudioPlayer$lambda$8(RecordViewHolder holder, int i, ImageAdapter this$0, long j, long j2, int i2) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (holder.getBindingAdapterPosition() == i) {
            this$0.updateRecordUI(holder, j, j2, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initAudioPlayer$lambda$11$lambda$9(ADAudioTrackPlayer aDAudioTrackPlayer, View view) throws IllegalStateException {
        ADAudioTrackPlayer.getInstance().isFromUserChangeProgress = false;
        int i = aDAudioTrackPlayer.state;
        if (i == ADAudioTrackPlayer.PLAYING) {
            aDAudioTrackPlayer.pause();
        } else if (i == ADAudioTrackPlayer.PAUSED) {
            aDAudioTrackPlayer.resume();
        } else if (i == ADAudioTrackPlayer.COMPLETED) {
            aDAudioTrackPlayer.restart();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initAudioPlayer$lambda$11$lambda$10(ImageAdapter this$0, RecordViewHolder this_with, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        this$0.audioPlayController.toggleMute(!this_with.getIvSound().isSelected());
        this_with.getIvSound().setSelected(this$0.audioPlayController.isMuted());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateRecordUI(final RecordViewHolder holder, final long current, final long total, final int state) {
        holder.itemView.post(new Runnable() { // from class: com.glasssutdio.wear.home.adapter.ImageAdapter$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                ImageAdapter.updateRecordUI$lambda$12(holder, this, current, total, state);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateRecordUI$lambda$12(RecordViewHolder holder, ImageAdapter this$0, long j, long j2, int i) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (holder.getBindingAdapterPosition() == this$0.audioPlayController.getCurrentPosition()) {
            holder.getPbProgress().setProgress(RangesKt.coerceIn((int) ((j * 100.0f) / j2), 0, 100));
            holder.getTvCurrent().setText(this$0.formatMillis(j));
            holder.getTvStart().setText(this$0.formatMillis(j));
            holder.getTvEnd().setText(DateUtil.secondToStr((int) (j2 / 1000)));
            holder.getIvPlay().setSelected(i == ADAudioTrackPlayer.PLAYING);
            holder.getIvSound().setSelected(this$0.audioPlayController.isMuted());
            holder.getWaveView().setCurrentPosition(j - DownloadTask.Builder.DEFAULT_SYNC_BUFFER_INTERVAL_MILLIS);
        }
    }

    public final void handlePageChange(int newPosition) throws IllegalStateException {
        this.audioPlayController.switchPosition(newPosition);
        XLog.m136i(Integer.valueOf(this.mediaList.get(newPosition).getFileType()));
        int fileType = this.mediaList.get(newPosition).getFileType();
        if (fileType != 2) {
            if (fileType == 3) {
                this.interval.resume();
                ADAudioTrackPlayer.getInstance().setVolume(1.0f);
                return;
            } else {
                this.interval.pause();
                return;
            }
        }
        try {
            this.interval.resume();
            this.isMuted = false;
            MediaPlayer mediaPlayer = this.mediaPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(1.0f, 1.0f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void releaseAllResources() throws IllegalStateException {
        this.audioPlayController.release();
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        this.mediaPlayer = null;
        this.interval.stop();
        ADAudioTrackPlayer.getInstance().stop();
        ADAudioTrackPlayer.getInstance().setInstanceNull();
    }

    private final String formatMillis(long millis) {
        String millisToMinutesSecondsTenths = DateUtil.formatMillisToMinutesSecondsTenths(millis);
        Intrinsics.checkNotNullExpressionValue(millisToMinutesSecondsTenths, "formatMillisToMinutesSecondsTenths(...)");
        return millisToMinutesSecondsTenths;
    }

    private final void toggleVideoPlayback(VideoViewHolder holder) {
        VideoView playerView = holder.getPlayerView();
        if (playerView.isPlaying()) {
            playerView.pause();
            this.interval.pause();
        } else {
            playerView.start();
            this.interval.resume();
        }
        holder.getIvPlay().setSelected(!playerView.isPlaying());
    }

    private final void toggleSoundMute(ImageView view) {
        view.setSelected(!view.isSelected());
        this.isMuted = view.isSelected();
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(view.isSelected() ? 0.0f : 1.0f, view.isSelected() ? 0.0f : 1.0f);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.glasssutdio.wear.home.adapter.ImageAdapter$createSeekListener$1] */
    private final C10361 createSeekListener(final VideoViewHolder holder) {
        return new SeekBar.OnSeekBarChangeListener() { // from class: com.glasssutdio.wear.home.adapter.ImageAdapter.createSeekListener.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                if (fromUser) {
                    if (Build.VERSION.SDK_INT >= 26) {
                        ImageAdapter.this.safeSeekTo(holder.getPlayerView(), progress);
                    } else {
                        holder.getPlayerView().seekTo(progress);
                    }
                    XLog.m137i("seek to " + progress + ",playerView Max:" + holder.getPlayerView().getDuration() + ",playerView:" + holder.getPlayerView().getCurrentPosition());
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                ImageAdapter.this.interval.pause();
                holder.getPlayerView().pause();
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                holder.getPlayerView().start();
                ImageAdapter.this.interval.resume();
            }
        };
    }

    /* compiled from: ImageAdapter.kt */
    @Metadata(m606d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/home/adapter/ImageAdapter$ImageViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/glasssutdio/wear/home/adapter/ImageAdapter;Landroid/view/View;)V", "imageView", "Lcom/glasssutdio/wear/all/view/photoview/PhotoView;", "getImageView", "()Lcom/glasssutdio/wear/all/view/photoview/PhotoView;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class ImageViewHolder extends RecyclerView.ViewHolder {
        private final PhotoView imageView;
        final /* synthetic */ ImageAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ImageViewHolder(ImageAdapter imageAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = imageAdapter;
            View viewFindViewById = itemView.findViewById(C0775R.id.show_image_src);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            this.imageView = (PhotoView) viewFindViewById;
        }

        public final PhotoView getImageView() {
            return this.imageView;
        }
    }

    /* compiled from: ImageAdapter.kt */
    @Metadata(m606d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u001b\u001a\u00020\u001cR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018¨\u0006\u001d"}, m607d2 = {"Lcom/glasssutdio/wear/home/adapter/ImageAdapter$VideoViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/glasssutdio/wear/home/adapter/ImageAdapter;Landroid/view/View;)V", "ivPlay", "Landroid/widget/ImageView;", "getIvPlay", "()Landroid/widget/ImageView;", "ivSound", "getIvSound", "pbProgress", "Landroid/widget/SeekBar;", "getPbProgress", "()Landroid/widget/SeekBar;", "playerView", "Landroid/widget/VideoView;", "getPlayerView", "()Landroid/widget/VideoView;", "thumbnail", "getThumbnail", "tvEnd", "Landroid/widget/TextView;", "getTvEnd", "()Landroid/widget/TextView;", "tvStart", "getTvStart", "resetPlayer", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class VideoViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivPlay;
        private final ImageView ivSound;
        private final SeekBar pbProgress;
        private final VideoView playerView;
        final /* synthetic */ ImageAdapter this$0;
        private final ImageView thumbnail;
        private final TextView tvEnd;
        private final TextView tvStart;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public VideoViewHolder(ImageAdapter imageAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = imageAdapter;
            View viewFindViewById = itemView.findViewById(C0775R.id.videoView);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            this.playerView = (VideoView) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(C0775R.id.iv_thumbnail);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
            this.thumbnail = (ImageView) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(C0775R.id.iv_play);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
            this.ivPlay = (ImageView) viewFindViewById3;
            View viewFindViewById4 = itemView.findViewById(C0775R.id.iv_sound);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
            this.ivSound = (ImageView) viewFindViewById4;
            View viewFindViewById5 = itemView.findViewById(C0775R.id.pb_progress);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(...)");
            this.pbProgress = (SeekBar) viewFindViewById5;
            View viewFindViewById6 = itemView.findViewById(C0775R.id.tv_start);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(...)");
            this.tvStart = (TextView) viewFindViewById6;
            View viewFindViewById7 = itemView.findViewById(C0775R.id.tv_end);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "findViewById(...)");
            this.tvEnd = (TextView) viewFindViewById7;
        }

        public final VideoView getPlayerView() {
            return this.playerView;
        }

        public final ImageView getThumbnail() {
            return this.thumbnail;
        }

        public final ImageView getIvPlay() {
            return this.ivPlay;
        }

        public final ImageView getIvSound() {
            return this.ivSound;
        }

        public final SeekBar getPbProgress() {
            return this.pbProgress;
        }

        public final TextView getTvStart() {
            return this.tvStart;
        }

        public final TextView getTvEnd() {
            return this.tvEnd;
        }

        public final void resetPlayer() {
            this.playerView.stopPlayback();
            this.pbProgress.setProgress(0);
            this.tvStart.setText("00:00");
        }
    }

    /* compiled from: ImageAdapter.kt */
    @Metadata(m606d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u001b\u001a\u00020\u001cR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0015\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, m607d2 = {"Lcom/glasssutdio/wear/home/adapter/ImageAdapter$RecordViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/glasssutdio/wear/home/adapter/ImageAdapter;Landroid/view/View;)V", "ivPlay", "Landroid/widget/ImageView;", "getIvPlay", "()Landroid/widget/ImageView;", "ivSound", "getIvSound", "pbProgress", "Landroid/widget/SeekBar;", "getPbProgress", "()Landroid/widget/SeekBar;", "tvCurrent", "Landroid/widget/TextView;", "getTvCurrent", "()Landroid/widget/TextView;", "tvEnd", "getTvEnd", "tvStart", "getTvStart", "waveView", "Lcom/glasssutdio/wear/all/view/PcmWaveformView;", "getWaveView", "()Lcom/glasssutdio/wear/all/view/PcmWaveformView;", "resetUI", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class RecordViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivPlay;
        private final ImageView ivSound;
        private final SeekBar pbProgress;
        final /* synthetic */ ImageAdapter this$0;
        private final TextView tvCurrent;
        private final TextView tvEnd;
        private final TextView tvStart;
        private final PcmWaveformView waveView;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RecordViewHolder(ImageAdapter imageAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = imageAdapter;
            View viewFindViewById = itemView.findViewById(C0775R.id.iv_play_recording);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            this.ivPlay = (ImageView) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(C0775R.id.iv_sound_recording);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
            this.ivSound = (ImageView) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(C0775R.id.pb_progress_recording);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
            this.pbProgress = (SeekBar) viewFindViewById3;
            View viewFindViewById4 = itemView.findViewById(C0775R.id.wave_view);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
            this.waveView = (PcmWaveformView) viewFindViewById4;
            View viewFindViewById5 = itemView.findViewById(C0775R.id.tv_start_recording);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(...)");
            this.tvStart = (TextView) viewFindViewById5;
            View viewFindViewById6 = itemView.findViewById(C0775R.id.tv_end_recording);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(...)");
            this.tvEnd = (TextView) viewFindViewById6;
            View viewFindViewById7 = itemView.findViewById(C0775R.id.tv_current_recording);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "findViewById(...)");
            this.tvCurrent = (TextView) viewFindViewById7;
        }

        public final ImageView getIvPlay() {
            return this.ivPlay;
        }

        public final ImageView getIvSound() {
            return this.ivSound;
        }

        public final SeekBar getPbProgress() {
            return this.pbProgress;
        }

        public final PcmWaveformView getWaveView() {
            return this.waveView;
        }

        public final TextView getTvStart() {
            return this.tvStart;
        }

        public final TextView getTvEnd() {
            return this.tvEnd;
        }

        public final TextView getTvCurrent() {
            return this.tvCurrent;
        }

        public final void resetUI() {
            this.pbProgress.setProgress(0);
            this.waveView.setPcmData(new short[0], 0);
            this.tvStart.setText("00:00");
            this.tvEnd.setText("00:00");
            this.tvCurrent.setText("00:00");
        }
    }

    /* compiled from: ImageAdapter.kt */
    @Metadata(m606d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0006\u0010\u0011\u001a\u00020\u0012J\u001e\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0006J\u0006\u0010\u0015\u001a\u00020\rJ\u000e\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u0005J\u000e\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u0012J\u000e\u0010\u001a\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u001b"}, m607d2 = {"Lcom/glasssutdio/wear/home/adapter/ImageAdapter$AudioPlayController;", "", "(Lcom/glasssutdio/wear/home/adapter/ImageAdapter;)V", "callbacks", "", "", "Lcom/glasssutdio/wear/all/utils/audio/ADAudioTrackPlayer$ProgressCallback;", "currentPosition", "getCurrentPosition", "()I", "setCurrentPosition", "(I)V", "activateCallback", "", "position", "filePath", "", "isMuted", "", "register", "callback", "release", "switchPosition", "newPosition", "toggleMute", "muted", "unregister", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    private final class AudioPlayController {
        private int currentPosition = -1;
        private final Map<Integer, ADAudioTrackPlayer.ProgressCallback> callbacks = new LinkedHashMap();

        public AudioPlayController() {
        }

        public final int getCurrentPosition() {
            return this.currentPosition;
        }

        public final void setCurrentPosition(int i) {
            this.currentPosition = i;
        }

        public final void register(int position, String filePath, ADAudioTrackPlayer.ProgressCallback callback) throws IllegalStateException {
            Intrinsics.checkNotNullParameter(filePath, "filePath");
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.callbacks.put(Integer.valueOf(position), callback);
            if (position == this.currentPosition) {
                activateCallback(position, filePath);
            }
        }

        public final void switchPosition(int newPosition) throws IllegalStateException {
            int i = this.currentPosition;
            if (newPosition == i) {
                return;
            }
            if (this.callbacks.get(Integer.valueOf(i)) != null) {
                ADAudioTrackPlayer.getInstance().setProgressCallback(null);
            }
            this.currentPosition = newPosition;
            GlassAlbumEntity glassAlbumEntity = (GlassAlbumEntity) CollectionsKt.getOrNull(ImageAdapter.this.mediaList, newPosition);
            if (glassAlbumEntity == null || glassAlbumEntity.getFileType() != 3) {
                return;
            }
            activateCallback(newPosition, glassAlbumEntity.getFilePath());
        }

        public final void unregister(int position) {
            this.callbacks.remove(Integer.valueOf(position));
            if (position == this.currentPosition) {
                ADAudioTrackPlayer.getInstance().setProgressCallback(null);
            }
        }

        public final void release() throws IllegalStateException {
            ADAudioTrackPlayer.getInstance().restart();
            this.callbacks.clear();
            this.currentPosition = -1;
        }

        public final boolean isMuted() {
            return ADAudioTrackPlayer.getInstance().isMuted();
        }

        public final void toggleMute(boolean muted) {
            ADAudioTrackPlayer.getInstance().setVolume(muted ? 0.0f : 1.0f);
        }

        private final void activateCallback(int position, String filePath) throws IllegalStateException {
            ADAudioTrackPlayer.initialize(ImageAdapter.this.context);
            ADAudioTrackPlayer aDAudioTrackPlayer = ADAudioTrackPlayer.getInstance();
            if (!Intrinsics.areEqual(aDAudioTrackPlayer.getPath(), filePath)) {
                aDAudioTrackPlayer.stop();
                aDAudioTrackPlayer.setPath(filePath);
                aDAudioTrackPlayer.play();
            }
            ADAudioTrackPlayer.ProgressCallback progressCallback = this.callbacks.get(Integer.valueOf(position));
            if (progressCallback != null) {
                aDAudioTrackPlayer.setProgressCallback(progressCallback);
            }
        }
    }

    private final void loadVideoFirstFrame(String videoPath, ImageView imageView) throws Exception {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            MediaMetadataRetriever mediaMetadataRetriever2 = mediaMetadataRetriever;
            try {
                mediaMetadataRetriever2.setDataSource(videoPath);
                imageView.setImageBitmap(mediaMetadataRetriever2.getFrameAtTime(1L, 2));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Unit unit = Unit.INSTANCE;
            AutoCloseableKt.closeFinally(mediaMetadataRetriever, null);
        } finally {
        }
    }

    /* compiled from: ImageAdapter.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.adapter.ImageAdapter$loadPcmData$1", m620f = "ImageAdapter.kt", m621i = {}, m622l = {HttpStatus.SC_INTERNAL_SERVER_ERROR}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.home.adapter.ImageAdapter$loadPcmData$1 */
    static final class C10371 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<short[], Unit> $callback;
        final /* synthetic */ String $path;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C10371(String str, Function1<? super short[], Unit> function1, Continuation<? super C10371> continuation) {
            super(2, continuation);
            this.$path = str;
            this.$callback = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C10371(this.$path, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10371) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws IOException {
            short[] sArr;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    File file = new File(this.$path);
                    MappedByteBuffer map = new RandomAccessFile(file, "r").getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
                    sArr = new short[map.remaining() / 2];
                    map.order(ByteOrder.LITTLE_ENDIAN);
                    map.asShortBuffer().get(sArr);
                } catch (Exception e) {
                    Log.e("PCMLoader", "Error loading PCM", e);
                    sArr = new short[0];
                }
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1(this.$callback, sArr, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* compiled from: ImageAdapter.kt */
        @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.home.adapter.ImageAdapter$loadPcmData$1$1", m620f = "ImageAdapter.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.home.adapter.ImageAdapter$loadPcmData$1$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<short[], Unit> $callback;
            final /* synthetic */ short[] $result;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(Function1<? super short[], Unit> function1, short[] sArr, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$callback = function1;
                this.$result = sArr;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.$callback, this.$result, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.$callback.invoke(this.$result);
                return Unit.INSTANCE;
            }
        }
    }

    private final void loadPcmData(String path, Function1<? super short[], Unit> callback) {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C10371(path, callback, null), 3, null);
    }

    private final short[] toShortArray(byte[] bArr) {
        int length = bArr.length / 2;
        short[] sArr = new short[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            sArr[i] = (short) (bArr[i2] | (bArr[i2 + 1] << 8));
        }
        return sArr;
    }
}
