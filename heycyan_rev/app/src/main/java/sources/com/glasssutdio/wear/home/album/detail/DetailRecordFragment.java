package com.glasssutdio.wear.home.album.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.all.ThreadExtKt;
import com.glasssutdio.wear.all.utils.audio.ADAudioTrackPlayer;
import com.glasssutdio.wear.all.utils.time.Interval;
import com.glasssutdio.wear.all.view.PcmWaveformView;
import com.glasssutdio.wear.database.entity.GlassAlbumEntity;
import com.glasssutdio.wear.databinding.FragmentDetailRecordBinding;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.ClientCookie;
import com.liulishuo.okdownload.DownloadTask;
import com.oudmon.qc_utils.date.DateUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p014io.CloseableKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: DetailRecordFragment.kt */
@Metadata(m606d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0017\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0002J$\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\r0\u0013H\u0002J\u0012\u0010\u0015\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J$\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u001e\u001a\u00020\rH\u0016J\b\u0010\u001f\u001a\u00020\rH\u0016J\b\u0010 \u001a\u00020\rH\u0016J\b\u0010!\u001a\u00020\rH\u0016J\u0010\u0010\"\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0002J \u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020(H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, m607d2 = {"Lcom/glasssutdio/wear/home/album/detail/DetailRecordFragment;", "Lcom/glasssutdio/wear/home/album/detail/MediaFragment;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/FragmentDetailRecordBinding;", "glassAlbumEntity", "Lcom/glasssutdio/wear/database/entity/GlassAlbumEntity;", "interval", "Lcom/glasssutdio/wear/all/utils/time/Interval;", "player", "Lcom/glasssutdio/wear/all/utils/audio/ADAudioTrackPlayer;", "kotlin.jvm.PlatformType", "bindRecording", "", "mediaItem", "loadPcmData", ClientCookie.PATH_ATTR, "", "callback", "Lkotlin/Function1;", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "pause", "play", "release", "setupAudioControls", "updateRecordUI", "current", "", "total", "state", "", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class DetailRecordFragment extends MediaFragment {
    private static final String ARG_MEDIA_ITEM = "media_item";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FragmentDetailRecordBinding binding;
    private GlassAlbumEntity glassAlbumEntity;
    private Interval interval;
    private ADAudioTrackPlayer player = ADAudioTrackPlayer.getInstance();

    @Override // com.glasssutdio.wear.home.album.detail.MediaFragment
    public void play() {
        Interval interval = this.interval;
        if (interval == null || interval == null) {
            return;
        }
        interval.start();
    }

    @Override // com.glasssutdio.wear.home.album.detail.MediaFragment
    public void pause() {
        XLog.m137i("DetailRecordFragment pause");
        Interval interval = this.interval;
        if (interval == null || interval == null) {
            return;
        }
        interval.pause();
    }

    @Override // com.glasssutdio.wear.home.album.detail.MediaFragment
    public void release() {
        XLog.m137i("DetailRecordFragment release");
        Interval interval = this.interval;
        if (interval == null || interval == null) {
            return;
        }
        interval.cancel();
    }

    private final void bindRecording(final GlassAlbumEntity mediaItem) {
        loadPcmData(mediaItem.getFilePath(), (Function1) new Function1<short[], Unit>() { // from class: com.glasssutdio.wear.home.album.detail.DetailRecordFragment.bindRecording.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(short[] sArr) throws IllegalStateException {
                invoke2(sArr);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(short[] pcmData) throws IllegalStateException {
                Intrinsics.checkNotNullParameter(pcmData, "pcmData");
                FragmentDetailRecordBinding fragmentDetailRecordBinding = DetailRecordFragment.this.binding;
                if (fragmentDetailRecordBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDetailRecordBinding = null;
                }
                fragmentDetailRecordBinding.waveView.setPcmData(pcmData, 16000);
                DetailRecordFragment.this.setupAudioControls(mediaItem);
            }
        });
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

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentDetailRecordBinding fragmentDetailRecordBindingInflate = FragmentDetailRecordBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentDetailRecordBindingInflate, "inflate(...)");
        this.binding = fragmentDetailRecordBindingInflate;
        GlassAlbumEntity glassAlbumEntity = this.glassAlbumEntity;
        FragmentDetailRecordBinding fragmentDetailRecordBinding = null;
        if (glassAlbumEntity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("glassAlbumEntity");
            glassAlbumEntity = null;
        }
        bindRecording(glassAlbumEntity);
        FragmentDetailRecordBinding fragmentDetailRecordBinding2 = this.binding;
        if (fragmentDetailRecordBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentDetailRecordBinding = fragmentDetailRecordBinding2;
        }
        ConstraintLayout root = fragmentDetailRecordBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupAudioControls(GlassAlbumEntity mediaItem) throws IllegalStateException {
        XLog.m137i(mediaItem.getFilePath());
        final FragmentDetailRecordBinding fragmentDetailRecordBinding = this.binding;
        if (fragmentDetailRecordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDetailRecordBinding = null;
        }
        this.player.setPath(mediaItem.getFilePath());
        fragmentDetailRecordBinding.ivPlay.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.album.detail.DetailRecordFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IllegalStateException {
                DetailRecordFragment.setupAudioControls$lambda$5$lambda$0(this.f$0, fragmentDetailRecordBinding, view);
            }
        });
        fragmentDetailRecordBinding.waveView.setListener(new PcmWaveformView.OnWaveformScrollListener() { // from class: com.glasssutdio.wear.home.album.detail.DetailRecordFragment$$ExternalSyntheticLambda1
            @Override // com.glasssutdio.wear.all.view.PcmWaveformView.OnWaveformScrollListener
            public final void onTimeChanged(int i) throws IllegalStateException, IOException {
                DetailRecordFragment.setupAudioControls$lambda$5$lambda$1(this.f$0, i);
            }
        });
        fragmentDetailRecordBinding.ivSound.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.album.detail.DetailRecordFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DetailRecordFragment.setupAudioControls$lambda$5$lambda$2(fragmentDetailRecordBinding, this, view);
            }
        });
        Interval intervalLife$default = Interval.life$default(new Interval(-1L, 100L, TimeUnit.MILLISECONDS, 0L, 0L, 16, null), (Fragment) this, (Lifecycle.Event) null, 2, (Object) null);
        this.interval = intervalLife$default;
        if (intervalLife$default != null) {
            intervalLife$default.subscribe(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.album.detail.DetailRecordFragment$setupAudioControls$1$4$1
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
                    final DetailRecordFragment detailRecordFragment = this.this$0;
                    ThreadExtKt.ktxRunOnUi(subscribe, new Function1<Interval, Unit>() { // from class: com.glasssutdio.wear.home.album.detail.DetailRecordFragment$setupAudioControls$1$4$1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Interval interval) {
                            invoke2(interval);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Interval ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            DetailRecordFragment detailRecordFragment2 = detailRecordFragment;
                            detailRecordFragment2.updateRecordUI(detailRecordFragment2.player.getPlayedTime(), detailRecordFragment.player.getTotalTime(), detailRecordFragment.player.state);
                            XLog.m137i("currentTime: " + detailRecordFragment.player.getPlayedTime() + ", totalTime: " + detailRecordFragment.player.getTotalTime() + ", state: " + detailRecordFragment.player.state);
                            if (detailRecordFragment.player.getPlayedTime() == detailRecordFragment.player.getTotalTime()) {
                                final DetailRecordFragment detailRecordFragment3 = detailRecordFragment;
                                ThreadExtKt.ktxRunOnUiDelay(ktxRunOnUi, 1000L, new Function1<Interval, Unit>() { // from class: com.glasssutdio.wear.home.album.detail.DetailRecordFragment.setupAudioControls.1.4.1.1.1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Interval interval) {
                                        invoke2(interval);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Interval ktxRunOnUiDelay) {
                                        Intrinsics.checkNotNullParameter(ktxRunOnUiDelay, "$this$ktxRunOnUiDelay");
                                        Interval interval = detailRecordFragment3.interval;
                                        if (interval != null) {
                                            interval.pause();
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }).finish(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.album.detail.DetailRecordFragment$setupAudioControls$1$4$2
                public final void invoke(Interval finish, long j) {
                    Intrinsics.checkNotNullParameter(finish, "$this$finish");
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Interval interval, Long l) {
                    invoke(interval, l.longValue());
                    return Unit.INSTANCE;
                }
            });
        }
        Interval interval = this.interval;
        if (interval != null) {
            interval.start();
        }
        ADAudioTrackPlayer.getInstance().setProgressCallback(new ADAudioTrackPlayer.ProgressCallback() { // from class: com.glasssutdio.wear.home.album.detail.DetailRecordFragment$$ExternalSyntheticLambda3
            @Override // com.glasssutdio.wear.all.utils.audio.ADAudioTrackPlayer.ProgressCallback
            public final void onProgressUpdate(long j, long j2, int i) {
                DetailRecordFragment.setupAudioControls$lambda$5$lambda$4(fragmentDetailRecordBinding, this, j, j2, i);
            }
        });
        fragmentDetailRecordBinding.pbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.glasssutdio.wear.home.album.detail.DetailRecordFragment$setupAudioControls$1$6
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar sb) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) throws IOException {
                Intrinsics.checkNotNullParameter(sb, "sb");
                if (fromUser) {
                    this.this$0.player.seekTo(sb.getProgress() / 100);
                    XLog.m136i(Integer.valueOf(this.this$0.player.state));
                    final DetailRecordFragment detailRecordFragment = this.this$0;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<DetailRecordFragment$setupAudioControls$1$6, Unit>() { // from class: com.glasssutdio.wear.home.album.detail.DetailRecordFragment$setupAudioControls$1$6$onProgressChanged$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(DetailRecordFragment$setupAudioControls$1$6 detailRecordFragment$setupAudioControls$1$6) {
                            invoke2(detailRecordFragment$setupAudioControls$1$6);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(DetailRecordFragment$setupAudioControls$1$6 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            DetailRecordFragment detailRecordFragment2 = detailRecordFragment;
                            detailRecordFragment2.updateRecordUI(detailRecordFragment2.player.getPlayedTime(), detailRecordFragment.player.getTotalTime(), detailRecordFragment.player.state);
                        }
                    });
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar sb) throws IllegalStateException {
                if (this.this$0.player.state == ADAudioTrackPlayer.PLAYING) {
                    XLog.m137i("play.pause");
                    this.this$0.player.pause();
                }
            }
        });
        int i = this.player.state;
        if (i == 0) {
            this.player.play();
            fragmentDetailRecordBinding.ivPlay.setSelected(true);
            Interval interval2 = this.interval;
            if (interval2 != null) {
                interval2.start();
                return;
            }
            return;
        }
        if (i == ADAudioTrackPlayer.PLAYING) {
            this.player.pause();
            fragmentDetailRecordBinding.ivPlay.setSelected(false);
            Interval interval3 = this.interval;
            if (interval3 != null) {
                interval3.pause();
                return;
            }
            return;
        }
        if (i == ADAudioTrackPlayer.PAUSED) {
            this.player.resume();
            fragmentDetailRecordBinding.ivPlay.setSelected(true);
            Interval interval4 = this.interval;
            if (interval4 != null) {
                interval4.resume();
                return;
            }
            return;
        }
        if (i == ADAudioTrackPlayer.COMPLETED) {
            this.player.restart();
            fragmentDetailRecordBinding.ivPlay.setSelected(true);
            Interval interval5 = this.interval;
            if (interval5 != null) {
                interval5.start();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupAudioControls$lambda$5$lambda$0(DetailRecordFragment this$0, FragmentDetailRecordBinding this_run, View view) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        XLog.m136i(Integer.valueOf(this$0.player.state));
        int i = this$0.player.state;
        if (i == 0) {
            this$0.player.play();
            this_run.ivPlay.setSelected(true);
            Interval interval = this$0.interval;
            if (interval != null) {
                interval.start();
                return;
            }
            return;
        }
        if (i == ADAudioTrackPlayer.PLAYING) {
            this$0.player.pause();
            this_run.ivPlay.setSelected(false);
            Interval interval2 = this$0.interval;
            if (interval2 != null) {
                interval2.pause();
                return;
            }
            return;
        }
        if (i == ADAudioTrackPlayer.PAUSED) {
            this$0.player.resume();
            this_run.ivPlay.setSelected(true);
            Interval interval3 = this$0.interval;
            if (interval3 != null) {
                interval3.resume();
                return;
            }
            return;
        }
        if (i == ADAudioTrackPlayer.COMPLETED) {
            this$0.player.restart();
            this_run.ivPlay.setSelected(true);
            Interval interval4 = this$0.interval;
            if (interval4 != null) {
                interval4.start();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupAudioControls$lambda$5$lambda$1(DetailRecordFragment this$0, int i) throws IllegalStateException, IOException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int iCoerceIn = RangesKt.coerceIn((int) ((i * 100.0f) / ADAudioTrackPlayer.getInstance().getTotalTime()), 0, 100);
        XLog.m136i(Integer.valueOf(iCoerceIn));
        FragmentDetailRecordBinding fragmentDetailRecordBinding = this$0.binding;
        FragmentDetailRecordBinding fragmentDetailRecordBinding2 = null;
        if (fragmentDetailRecordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDetailRecordBinding = null;
        }
        fragmentDetailRecordBinding.pbProgress.setProgress(iCoerceIn);
        long totalTime = i;
        if (totalTime >= ADAudioTrackPlayer.getInstance().getTotalTime()) {
            totalTime = ADAudioTrackPlayer.getInstance().getTotalTime();
        }
        this$0.player.pause();
        this$0.player.seekTo((float) ((i * 1.0d) / ADAudioTrackPlayer.getInstance().getTotalTime()));
        FragmentDetailRecordBinding fragmentDetailRecordBinding3 = this$0.binding;
        if (fragmentDetailRecordBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDetailRecordBinding3 = null;
        }
        fragmentDetailRecordBinding3.tvCurrent.setText(DateUtil.formatMillisToMinutesSecondsTenths(totalTime));
        FragmentDetailRecordBinding fragmentDetailRecordBinding4 = this$0.binding;
        if (fragmentDetailRecordBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentDetailRecordBinding2 = fragmentDetailRecordBinding4;
        }
        fragmentDetailRecordBinding2.tvStart.setText(DateUtil.formatMillisToMinutesSecondsTenths(totalTime));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupAudioControls$lambda$5$lambda$2(FragmentDetailRecordBinding this_run, DetailRecordFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean zIsSelected = this_run.ivSound.isSelected();
        boolean z = !zIsSelected;
        this$0.player.setVolume(!zIsSelected ? 0.0f : 1.0f);
        this_run.ivSound.setSelected(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupAudioControls$lambda$5$lambda$4(FragmentDetailRecordBinding this_run, final DetailRecordFragment this$0, long j, long j2, int i) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (j == j2) {
            ThreadExtKt.ktxRunOnUiDelay(this_run, 1000L, new Function1<FragmentDetailRecordBinding, Unit>() { // from class: com.glasssutdio.wear.home.album.detail.DetailRecordFragment$setupAudioControls$1$5$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(FragmentDetailRecordBinding fragmentDetailRecordBinding) {
                    invoke2(fragmentDetailRecordBinding);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(FragmentDetailRecordBinding ktxRunOnUiDelay) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUiDelay, "$this$ktxRunOnUiDelay");
                    Interval interval = this.this$0.interval;
                    if (interval != null) {
                        interval.pause();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateRecordUI(long current, long total, int state) {
        int iCoerceIn = RangesKt.coerceIn((int) ((current * 100.0f) / total), 0, 100);
        FragmentDetailRecordBinding fragmentDetailRecordBinding = this.binding;
        if (fragmentDetailRecordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDetailRecordBinding = null;
        }
        fragmentDetailRecordBinding.pbProgress.setProgress(iCoerceIn);
        fragmentDetailRecordBinding.tvCurrent.setText(DateUtil.formatMillisToMinutesSecondsTenths(current));
        fragmentDetailRecordBinding.tvStart.setText(DateUtil.formatMillisToMinutesSecondsTenths(current));
        fragmentDetailRecordBinding.tvEnd.setText(DateUtil.formatMillisToMinutesSecondsTenths(total));
        fragmentDetailRecordBinding.ivPlay.setSelected(state == ADAudioTrackPlayer.PLAYING);
        fragmentDetailRecordBinding.waveView.setCurrentPosition(current - DownloadTask.Builder.DEFAULT_SYNC_BUFFER_INTERVAL_MILLIS);
    }

    /* compiled from: DetailRecordFragment.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.album.detail.DetailRecordFragment$loadPcmData$1", m620f = "DetailRecordFragment.kt", m621i = {}, m622l = {240}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.home.album.detail.DetailRecordFragment$loadPcmData$1 */
    static final class C10531 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<short[], Unit> $callback;
        final /* synthetic */ String $path;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C10531(String str, Function1<? super short[], Unit> function1, Continuation<? super C10531> continuation) {
            super(2, continuation);
            this.$path = str;
            this.$callback = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C10531(this.$path, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10531) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            short[] sArr;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    FileInputStream fileInputStream = new FileInputStream(new File(this.$path));
                    try {
                        FileInputStream fileInputStream2 = fileInputStream;
                        int iAvailable = fileInputStream2.available();
                        byte[] bArr = new byte[iAvailable];
                        fileInputStream2.read(bArr);
                        int i2 = iAvailable / 2;
                        sArr = new short[i2];
                        for (int i3 = 0; i3 < i2; i3++) {
                            int i4 = i3 * 2;
                            sArr[i3] = (short) (bArr[i4] | (bArr[i4 + 1] << 8));
                        }
                        CloseableKt.closeFinally(fileInputStream, null);
                    } finally {
                    }
                } catch (Exception unused) {
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

        /* compiled from: DetailRecordFragment.kt */
        @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.home.album.detail.DetailRecordFragment$loadPcmData$1$1", m620f = "DetailRecordFragment.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.home.album.detail.DetailRecordFragment$loadPcmData$1$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<short[], Unit> $callback;
            final /* synthetic */ short[] $data;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(Function1<? super short[], Unit> function1, short[] sArr, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$callback = function1;
                this.$data = sArr;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.$callback, this.$data, continuation);
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
                this.$callback.invoke(this.$data);
                return Unit.INSTANCE;
            }
        }
    }

    private final void loadPcmData(String path, Function1<? super short[], Unit> callback) {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C10531(path, callback, null), 3, null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Interval interval = this.interval;
        if (interval != null && interval != null) {
            interval.cancel();
        }
        try {
            ADAudioTrackPlayer.getInstance().release();
            ADAudioTrackPlayer.getInstance().setInstanceNull();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* compiled from: DetailRecordFragment.kt */
    @Metadata(m606d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/home/album/detail/DetailRecordFragment$Companion;", "", "()V", "ARG_MEDIA_ITEM", "", "newInstance", "Lcom/glasssutdio/wear/home/album/detail/DetailRecordFragment;", "glassAlbumEntity", "Lcom/glasssutdio/wear/database/entity/GlassAlbumEntity;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DetailRecordFragment newInstance(GlassAlbumEntity glassAlbumEntity) {
            Intrinsics.checkNotNullParameter(glassAlbumEntity, "glassAlbumEntity");
            DetailRecordFragment detailRecordFragment = new DetailRecordFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("media_item", glassAlbumEntity);
            detailRecordFragment.setArguments(bundle);
            return detailRecordFragment;
        }
    }
}
