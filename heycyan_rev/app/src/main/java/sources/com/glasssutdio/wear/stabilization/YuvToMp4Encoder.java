package com.glasssutdio.wear.stabilization;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.util.Log;
import android.view.Surface;
import com.google.android.gms.location.DeviceOrientationRequest;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: YuvToMp4Encoder.kt */
@Metadata(m606d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0016\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u0006\u0010\"\u001a\u00020\u001dJ\u0006\u0010#\u001a\u00020\u001dJ\u0016\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, m607d2 = {"Lcom/glasssutdio/wear/stabilization/YuvToMp4Encoder;", "", "colorFormat", "", "audioFormat", "Landroid/media/MediaFormat;", "width", "height", "frameRate", "outputFile", "", "(ILandroid/media/MediaFormat;IIILjava/lang/String;)V", "getAudioFormat", "()Landroid/media/MediaFormat;", "setAudioFormat", "(Landroid/media/MediaFormat;)V", "audioTrackIndex", "getColorFormat", "()I", "setColorFormat", "(I)V", "isMuxerStarted", "", "mediaCodec", "Landroid/media/MediaCodec;", "mediaMuxer", "Landroid/media/MediaMuxer;", "trackIndex", "encodeFrame", "", "yuvData", "", "presentationTimeUs", "", "init", "release", "writeSampleData", "byteBuf", "Ljava/nio/ByteBuffer;", "bufferInfo", "Landroid/media/MediaCodec$BufferInfo;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class YuvToMp4Encoder {
    private MediaFormat audioFormat;
    private int audioTrackIndex;
    private int colorFormat;
    private final int frameRate;
    private final int height;
    private boolean isMuxerStarted;
    private MediaCodec mediaCodec;
    private MediaMuxer mediaMuxer;
    private final String outputFile;
    private int trackIndex;
    private final int width;

    public YuvToMp4Encoder(int i, MediaFormat mediaFormat, int i2, int i3, int i4, String outputFile) {
        Intrinsics.checkNotNullParameter(outputFile, "outputFile");
        this.colorFormat = i;
        this.audioFormat = mediaFormat;
        this.width = i2;
        this.height = i3;
        this.frameRate = i4;
        this.outputFile = outputFile;
        this.trackIndex = -1;
        this.audioTrackIndex = -1;
    }

    public final int getColorFormat() {
        return this.colorFormat;
    }

    public final void setColorFormat(int i) {
        this.colorFormat = i;
    }

    public final MediaFormat getAudioFormat() {
        return this.audioFormat;
    }

    public final void setAudioFormat(MediaFormat mediaFormat) {
        this.audioFormat = mediaFormat;
    }

    public final void init() throws IOException {
        MediaCodec mediaCodecCreateEncoderByType = MediaCodec.createEncoderByType("video/avc");
        Intrinsics.checkNotNullExpressionValue(mediaCodecCreateEncoderByType, "createEncoderByType(...)");
        this.mediaCodec = mediaCodecCreateEncoderByType;
        MediaCodec mediaCodec = null;
        if (mediaCodecCreateEncoderByType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaCodec");
            mediaCodecCreateEncoderByType = null;
        }
        MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecCreateEncoderByType.getCodecInfo().getCapabilitiesForType("video/avc");
        StringBuilder sb = new StringBuilder("Encoder support colorFormats: ");
        String string = Arrays.toString(capabilitiesForType.colorFormats);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        Log.i("YUV", sb.append(string).toString());
        int[] colorFormats = capabilitiesForType.colorFormats;
        Intrinsics.checkNotNullExpressionValue(colorFormats, "colorFormats");
        if (!ArraysKt.contains(colorFormats, this.colorFormat)) {
            Log.e("YUV", "Encoder unsupported color format: " + this.colorFormat);
            this.colorFormat = 2135033992;
        }
        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat("video/avc", this.width, this.height);
        mediaFormatCreateVideoFormat.setInteger("color-format", this.colorFormat);
        mediaFormatCreateVideoFormat.setInteger("bitrate", this.width * this.height * 12);
        mediaFormatCreateVideoFormat.setInteger("frame-rate", this.frameRate);
        mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 30);
        Intrinsics.checkNotNullExpressionValue(mediaFormatCreateVideoFormat, "apply(...)");
        MediaCodec mediaCodec2 = this.mediaCodec;
        if (mediaCodec2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaCodec");
            mediaCodec2 = null;
        }
        mediaCodec2.configure(mediaFormatCreateVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        mediaCodec2.start();
        MediaCodec mediaCodec3 = this.mediaCodec;
        if (mediaCodec3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaCodec");
        } else {
            mediaCodec = mediaCodec3;
        }
        MediaFormat inputFormat = mediaCodec.getInputFormat();
        Intrinsics.checkNotNullExpressionValue(inputFormat, "getInputFormat(...)");
        Log.i("YUV", "Encoder input format: " + inputFormat);
        this.colorFormat = inputFormat.getInteger("color-format");
        Log.e("YUV", "Encoder final color format: " + this.colorFormat);
        this.mediaMuxer = new MediaMuxer(this.outputFile, 0);
    }

    public final void encodeFrame(byte[] yuvData, long presentationTimeUs) throws MediaCodec.CryptoException {
        Intrinsics.checkNotNullParameter(yuvData, "yuvData");
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
                    MediaFormat mediaFormat = this.audioFormat;
                    this.audioTrackIndex = mediaFormat != null ? Integer.valueOf(mediaMuxer.addTrack(mediaFormat)).intValue() : -1;
                    mediaMuxer.start();
                    this.isMuxerStarted = true;
                }
                outputBuffer.position(bufferInfo.offset);
                outputBuffer.limit(bufferInfo.offset + bufferInfo.size);
                mediaMuxer.writeSampleData(this.trackIndex, outputBuffer, bufferInfo);
            }
            mediaCodec.releaseOutputBuffer(iDequeueOutputBuffer, false);
        }
    }

    public final void writeSampleData(ByteBuffer byteBuf, MediaCodec.BufferInfo bufferInfo) {
        Intrinsics.checkNotNullParameter(byteBuf, "byteBuf");
        Intrinsics.checkNotNullParameter(bufferInfo, "bufferInfo");
        if (this.audioTrackIndex != -1) {
            MediaMuxer mediaMuxer = this.mediaMuxer;
            if (mediaMuxer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaMuxer");
                mediaMuxer = null;
            }
            mediaMuxer.writeSampleData(this.audioTrackIndex, byteBuf, bufferInfo);
        }
    }

    public final void release() {
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
    }
}
