package com.glasssutdio.wear.all.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssetsAudioPlayer.kt */
@Metadata(m606d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nJ\u0006\u0010\f\u001a\u00020\u0006J\u0006\u0010\r\u001a\u00020\u000eJ2\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\u0013\u001a\u00020\u00062\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0015J\b\u0010\u0016\u001a\u00020\u000eH\u0002J\u0006\u0010\u0017\u001a\u00020\u000eJ\u000e\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\nJ\u0006\u0010\u001a\u001a\u00020\u000eR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m607d2 = {"Lcom/glasssutdio/wear/all/utils/AssetsAudioPlayer;", "", "()V", "currentAssetPath", "", "isPrepared", "", "mediaPlayer", "Landroid/media/MediaPlayer;", "getCurrentPosition", "", "getDuration", "isPlaying", "pause", "", "playAsset", "context", "Landroid/content/Context;", "assetPath", "loop", "completionListener", "Lkotlin/Function0;", "releaseMediaPlayer", "resume", "seekTo", "position", "stop", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AssetsAudioPlayer {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static volatile AssetsAudioPlayer instance;
    private String currentAssetPath;
    private boolean isPrepared;
    private MediaPlayer mediaPlayer;

    public /* synthetic */ AssetsAudioPlayer(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private AssetsAudioPlayer() {
    }

    /* compiled from: AssetsAudioPlayer.kt */
    @Metadata(m606d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m607d2 = {"Lcom/glasssutdio/wear/all/utils/AssetsAudioPlayer$Companion;", "", "()V", "instance", "Lcom/glasssutdio/wear/all/utils/AssetsAudioPlayer;", "getInstance", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AssetsAudioPlayer getInstance() {
            AssetsAudioPlayer assetsAudioPlayer = AssetsAudioPlayer.instance;
            if (assetsAudioPlayer == null) {
                synchronized (this) {
                    assetsAudioPlayer = AssetsAudioPlayer.instance;
                    if (assetsAudioPlayer == null) {
                        assetsAudioPlayer = new AssetsAudioPlayer(null);
                        Companion companion = AssetsAudioPlayer.INSTANCE;
                        AssetsAudioPlayer.instance = assetsAudioPlayer;
                    }
                }
            }
            return assetsAudioPlayer;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void playAsset$default(AssetsAudioPlayer assetsAudioPlayer, Context context, String str, boolean z, Function0 function0, int i, Object obj) throws IllegalStateException, IOException, IllegalArgumentException {
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            function0 = null;
        }
        assetsAudioPlayer.playAsset(context, str, z, function0);
    }

    public final void playAsset(Context context, String assetPath, final boolean loop, final Function0<Unit> completionListener) throws IllegalStateException, IOException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(assetPath, "assetPath");
        if (Intrinsics.areEqual(this.currentAssetPath, assetPath) && this.isPrepared) {
            MediaPlayer mediaPlayer = this.mediaPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.start();
                return;
            }
            return;
        }
        releaseMediaPlayer();
        try {
            MediaPlayer mediaPlayer2 = new MediaPlayer();
            AssetFileDescriptor assetFileDescriptorOpenFd = context.getAssets().openFd(assetPath);
            Intrinsics.checkNotNullExpressionValue(assetFileDescriptorOpenFd, "openFd(...)");
            mediaPlayer2.setDataSource(assetFileDescriptorOpenFd.getFileDescriptor(), assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getLength());
            assetFileDescriptorOpenFd.close();
            mediaPlayer2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.glasssutdio.wear.all.utils.AssetsAudioPlayer$$ExternalSyntheticLambda0
                @Override // android.media.MediaPlayer.OnPreparedListener
                public final void onPrepared(MediaPlayer mediaPlayer3) throws IllegalStateException {
                    AssetsAudioPlayer.playAsset$lambda$3$lambda$0(this.f$0, loop, mediaPlayer3);
                }
            });
            mediaPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.glasssutdio.wear.all.utils.AssetsAudioPlayer$$ExternalSyntheticLambda1
                @Override // android.media.MediaPlayer.OnCompletionListener
                public final void onCompletion(MediaPlayer mediaPlayer3) {
                    AssetsAudioPlayer.playAsset$lambda$3$lambda$1(completionListener, this, mediaPlayer3);
                }
            });
            mediaPlayer2.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.glasssutdio.wear.all.utils.AssetsAudioPlayer$$ExternalSyntheticLambda2
                @Override // android.media.MediaPlayer.OnErrorListener
                public final boolean onError(MediaPlayer mediaPlayer3, int i, int i2) {
                    return AssetsAudioPlayer.playAsset$lambda$3$lambda$2(this.f$0, mediaPlayer3, i, i2);
                }
            });
            mediaPlayer2.prepareAsync();
            this.mediaPlayer = mediaPlayer2;
            this.currentAssetPath = assetPath;
        } catch (IOException e) {
            e.printStackTrace();
            releaseMediaPlayer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void playAsset$lambda$3$lambda$0(AssetsAudioPlayer this$0, boolean z, MediaPlayer mediaPlayer) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.isPrepared = true;
        mediaPlayer.start();
        mediaPlayer.setLooping(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void playAsset$lambda$3$lambda$1(Function0 function0, AssetsAudioPlayer this$0, MediaPlayer mediaPlayer) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (function0 != null) {
            function0.invoke();
        }
        if (mediaPlayer.isLooping()) {
            return;
        }
        this$0.releaseMediaPlayer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean playAsset$lambda$3$lambda$2(AssetsAudioPlayer this$0, MediaPlayer mediaPlayer, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.releaseMediaPlayer();
        return false;
    }

    public final void pause() throws IllegalStateException {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer = null;
            }
            if (mediaPlayer != null) {
                mediaPlayer.pause();
            }
        }
    }

    public final void resume() throws IllegalStateException {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying() || !this.isPrepared) {
                mediaPlayer = null;
            }
            if (mediaPlayer != null) {
                mediaPlayer.start();
            }
        }
    }

    public final void stop() {
        releaseMediaPlayer();
    }

    public final boolean isPlaying() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            return mediaPlayer.isPlaying();
        }
        return false;
    }

    public final int getCurrentPosition() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public final int getDuration() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            return mediaPlayer.getDuration();
        }
        return 0;
    }

    public final void seekTo(int position) throws IllegalStateException {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(position);
        }
    }

    private final void releaseMediaPlayer() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
        }
        this.mediaPlayer = null;
        this.currentAssetPath = null;
        this.isPrepared = false;
    }
}
