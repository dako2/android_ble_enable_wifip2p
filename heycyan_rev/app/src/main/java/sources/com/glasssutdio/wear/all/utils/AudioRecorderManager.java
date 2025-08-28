package com.glasssutdio.wear.all.utils;

import android.media.AudioRecord;
import com.glasssutdio.wear.p003ai.spark.SparkChainRecognizer;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AudioRecorderManager.kt */
@Metadata(m606d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u0013H\u0007J\u0006\u0010\u0015\u001a\u00020\u0013R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m607d2 = {"Lcom/glasssutdio/wear/all/utils/AudioRecorderManager;", "", "recognizer", "Lcom/glasssutdio/wear/ai/spark/SparkChainRecognizer;", "(Lcom/glasssutdio/wear/ai/spark/SparkChainRecognizer;)V", "audioFormat", "", "audioRecord", "Landroid/media/AudioRecord;", "bufferSize", "channelConfig", "executor", "Ljava/util/concurrent/ExecutorService;", "isRecording", "", "recordingRunnable", "Ljava/lang/Runnable;", "sampleRate", "release", "", "startRecording", "stopRecording", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AudioRecorderManager {
    private final int audioFormat;
    private AudioRecord audioRecord;
    private final int bufferSize;
    private final int channelConfig;
    private final ExecutorService executor;
    private boolean isRecording;
    private final SparkChainRecognizer recognizer;
    private final Runnable recordingRunnable;
    private final int sampleRate;

    public AudioRecorderManager(SparkChainRecognizer recognizer) {
        Intrinsics.checkNotNullParameter(recognizer, "recognizer");
        this.recognizer = recognizer;
        this.sampleRate = 16000;
        this.channelConfig = 16;
        this.audioFormat = 2;
        this.bufferSize = AudioRecord.getMinBufferSize(16000, 16, 2);
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor(...)");
        this.executor = executorServiceNewSingleThreadExecutor;
        this.recordingRunnable = new Runnable() { // from class: com.glasssutdio.wear.all.utils.AudioRecorderManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AudioRecorderManager.recordingRunnable$lambda$3(this.f$0);
            }
        };
    }

    public final void startRecording() {
        if (this.isRecording) {
            return;
        }
        this.executor.execute(new Runnable() { // from class: com.glasssutdio.wear.all.utils.AudioRecorderManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws IllegalStateException {
                AudioRecorderManager.startRecording$lambda$0(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startRecording$lambda$0(AudioRecorderManager this$0) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            AudioRecord audioRecord = new AudioRecord(1, this$0.sampleRate, this$0.channelConfig, this$0.audioFormat, this$0.bufferSize * 2);
            audioRecord.startRecording();
            this$0.audioRecord = audioRecord;
            this$0.isRecording = true;
            this$0.executor.execute(this$0.recordingRunnable);
        } catch (Exception e) {
            e.printStackTrace();
            this$0.stopRecording();
        }
    }

    public final void stopRecording() {
        if (this.isRecording) {
            this.isRecording = false;
            this.executor.execute(new Runnable() { // from class: com.glasssutdio.wear.all.utils.AudioRecorderManager$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() throws IllegalStateException {
                    AudioRecorderManager.stopRecording$lambda$2(this.f$0);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void stopRecording$lambda$2(AudioRecorderManager this$0) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AudioRecord audioRecord = this$0.audioRecord;
        if (audioRecord != null) {
            audioRecord.stop();
            audioRecord.release();
        }
        this$0.audioRecord = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void recordingRunnable$lambda$3(AudioRecorderManager this$0) {
        AudioRecord audioRecord;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        byte[] bArr = new byte[1280];
        while (this$0.isRecording && (audioRecord = this$0.audioRecord) != null && audioRecord.getRecordingState() == 3) {
            AudioRecord audioRecord2 = this$0.audioRecord;
            int i = audioRecord2 != null ? audioRecord2.read(bArr, 0, 1280) : 0;
            if (i > 0) {
                SparkChainRecognizer sparkChainRecognizer = this$0.recognizer;
                byte[] bArrCopyOf = Arrays.copyOf(bArr, i);
                Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
                sparkChainRecognizer.receiveData(bArrCopyOf);
            }
        }
    }

    public final void release() {
        stopRecording();
        this.executor.shutdownNow();
    }
}
