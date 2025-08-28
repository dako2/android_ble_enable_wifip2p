package com.glasssutdio.wear.stabilization;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.view.Surface;
import com.google.android.gms.location.DeviceOrientationRequest;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: VideoEncoder.kt */
@Metadata(m606d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0007H\u0002J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u0017J\u0010\u0010\u001d\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0007H\u0002J\u0006\u0010\u001e\u001a\u00020\u0017R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m607d2 = {"Lcom/glasssutdio/wear/stabilization/VideoEncoder;", "", "width", "", "height", "frameRate", "outputFile", "", "(IIILjava/lang/String;)V", "isMuxerStarted", "", "logger", "Ljava/util/logging/Logger;", "kotlin.jvm.PlatformType", "mediaCodec", "Landroid/media/MediaCodec;", "mediaMuxer", "Landroid/media/MediaMuxer;", "trackIndex", "createVideoFormat", "Landroid/media/MediaFormat;", "mimeType", "encodeFrame", "", "yuvData", "", "presentationTimeUs", "", "init", "isCodecSupported", "release", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class VideoEncoder {
    private final int frameRate;
    private final int height;
    private boolean isMuxerStarted;
    private final Logger logger;
    private MediaCodec mediaCodec;
    private MediaMuxer mediaMuxer;
    private final String outputFile;
    private int trackIndex;
    private final int width;

    public VideoEncoder(int i, int i2, int i3, String outputFile) {
        Intrinsics.checkNotNullParameter(outputFile, "outputFile");
        this.width = i;
        this.height = i2;
        this.frameRate = i3;
        this.outputFile = outputFile;
        this.trackIndex = -1;
        this.logger = Logger.getLogger(VideoEncoder.class.getName());
    }

    public final void init() throws IOException {
        if (!isCodecSupported("video/avc")) {
            this.logger.log(Level.SEVERE, "设备不支持 video/avc 编解码器");
            return;
        }
        try {
            MediaFormat mediaFormatCreateVideoFormat = createVideoFormat("video/avc");
            MediaCodec mediaCodecCreateEncoderByType = MediaCodec.createEncoderByType("video/avc");
            Intrinsics.checkNotNullExpressionValue(mediaCodecCreateEncoderByType, "createEncoderByType(...)");
            mediaCodecCreateEncoderByType.configure(mediaFormatCreateVideoFormat, (Surface) null, (MediaCrypto) null, 1);
            mediaCodecCreateEncoderByType.start();
            this.mediaCodec = mediaCodecCreateEncoderByType;
            this.mediaMuxer = new MediaMuxer(this.outputFile, 0);
        } catch (IOException e) {
            this.logger.log(Level.SEVERE, "初始化编码器或复用器时出错", (Throwable) e);
        } catch (IllegalArgumentException e2) {
            this.logger.log(Level.SEVERE, "编码器配置参数不支持", (Throwable) e2);
        }
    }

    private final boolean isCodecSupported(String mimeType) {
        MediaCodecInfo[] codecInfos = new MediaCodecList(1).getCodecInfos();
        Intrinsics.checkNotNull(codecInfos);
        for (MediaCodecInfo mediaCodecInfo : codecInfos) {
            if (mediaCodecInfo.isEncoder()) {
                String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
                Intrinsics.checkNotNull(supportedTypes);
                for (String str : supportedTypes) {
                    if (StringsKt.equals(str, mimeType, true)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private final MediaFormat createVideoFormat(String mimeType) {
        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(mimeType, this.width, this.height);
        Intrinsics.checkNotNullExpressionValue(mediaFormatCreateVideoFormat, "createVideoFormat(...)");
        return mediaFormatCreateVideoFormat;
    }

    public final void encodeFrame(byte[] yuvData, long presentationTimeUs) throws MediaCodec.CryptoException {
        Intrinsics.checkNotNullParameter(yuvData, "yuvData");
        try {
            MediaCodec mediaCodec = this.mediaCodec;
            if (mediaCodec == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaCodec");
                mediaCodec = null;
            }
            int iDequeueInputBuffer = mediaCodec.dequeueInputBuffer(DeviceOrientationRequest.OUTPUT_PERIOD_MEDIUM);
            if (iDequeueInputBuffer >= 0) {
                ByteBuffer inputBuffer = mediaCodec.getInputBuffer(iDequeueInputBuffer);
                if (inputBuffer != null) {
                    inputBuffer.clear();
                }
                if (inputBuffer != null) {
                    inputBuffer.put(yuvData);
                }
                mediaCodec.queueInputBuffer(iDequeueInputBuffer, 0, yuvData.length, presentationTimeUs, 0);
            }
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            for (int iDequeueOutputBuffer = mediaCodec.dequeueOutputBuffer(bufferInfo, DeviceOrientationRequest.OUTPUT_PERIOD_MEDIUM); iDequeueOutputBuffer >= 0; iDequeueOutputBuffer = mediaCodec.dequeueOutputBuffer(bufferInfo, 0L)) {
                ByteBuffer outputBuffer = mediaCodec.getOutputBuffer(iDequeueOutputBuffer);
                if (outputBuffer != null) {
                    MediaMuxer mediaMuxer = this.mediaMuxer;
                    if (mediaMuxer == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mediaMuxer");
                        mediaMuxer = null;
                    }
                    if (!this.isMuxerStarted) {
                        this.trackIndex = mediaMuxer.addTrack(mediaCodec.getOutputFormat());
                        mediaMuxer.start();
                        this.isMuxerStarted = true;
                    }
                    outputBuffer.position(bufferInfo.offset);
                    outputBuffer.limit(bufferInfo.offset + bufferInfo.size);
                    mediaMuxer.writeSampleData(this.trackIndex, outputBuffer, bufferInfo);
                }
                mediaCodec.releaseOutputBuffer(iDequeueOutputBuffer, false);
            }
        } catch (Exception e) {
            this.logger.log(Level.SEVERE, "编码帧时出错", (Throwable) e);
        }
    }

    public final void release() {
        try {
            MediaCodec mediaCodec = this.mediaCodec;
            MediaMuxer mediaMuxer = null;
            if (mediaCodec == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaCodec");
                mediaCodec = null;
            }
            mediaCodec.stop();
            MediaCodec mediaCodec2 = this.mediaCodec;
            if (mediaCodec2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaCodec");
                mediaCodec2 = null;
            }
            mediaCodec2.release();
            MediaMuxer mediaMuxer2 = this.mediaMuxer;
            if (mediaMuxer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaMuxer");
                mediaMuxer2 = null;
            }
            mediaMuxer2.stop();
            MediaMuxer mediaMuxer3 = this.mediaMuxer;
            if (mediaMuxer3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaMuxer");
            } else {
                mediaMuxer = mediaMuxer3;
            }
            mediaMuxer.release();
        } catch (Exception e) {
            this.logger.log(Level.SEVERE, "释放资源时出错", (Throwable) e);
        }
    }
}
