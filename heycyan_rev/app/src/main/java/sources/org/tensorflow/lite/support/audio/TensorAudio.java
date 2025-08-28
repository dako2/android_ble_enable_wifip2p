package org.tensorflow.lite.support.audio;

import android.media.AudioFormat;
import android.media.AudioRecord;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import kotlin.jvm.internal.ShortCompanionObject;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.audio.AutoValue_TensorAudio_TensorAudioFormat;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes3.dex */
public class TensorAudio {
    private static final String TAG = "TensorAudio";
    private final FloatRingBuffer buffer;
    private final TensorAudioFormat format;

    public static TensorAudio create(TensorAudioFormat tensorAudioFormat, int i) {
        return new TensorAudio(tensorAudioFormat, i);
    }

    public static TensorAudio create(AudioFormat audioFormat, int i) {
        return new TensorAudio(TensorAudioFormat.create(audioFormat), i);
    }

    public static abstract class TensorAudioFormat {
        private static final int DEFAULT_CHANNELS = 1;

        public abstract int getChannels();

        public abstract int getSampleRate();

        public static TensorAudioFormat create(AudioFormat audioFormat) {
            return builder().setChannels(audioFormat.getChannelCount()).setSampleRate(audioFormat.getSampleRate()).build();
        }

        public static Builder builder() {
            return new AutoValue_TensorAudio_TensorAudioFormat.Builder().setChannels(1);
        }

        public static abstract class Builder {
            abstract TensorAudioFormat autoBuild();

            public abstract Builder setChannels(int i);

            public abstract Builder setSampleRate(int i);

            public TensorAudioFormat build() {
                TensorAudioFormat tensorAudioFormatAutoBuild = autoBuild();
                SupportPreconditions.checkArgument(tensorAudioFormatAutoBuild.getChannels() > 0, "Number of channels should be greater than 0");
                SupportPreconditions.checkArgument(tensorAudioFormatAutoBuild.getSampleRate() > 0, "Sample rate should be greater than 0");
                return tensorAudioFormatAutoBuild;
            }
        }
    }

    public void load(float[] fArr) {
        load(fArr, 0, fArr.length);
    }

    public void load(float[] fArr, int i, int i2) {
        SupportPreconditions.checkArgument(i2 % this.format.getChannels() == 0, String.format("Size (%d) needs to be a multiplier of the number of channels (%d)", Integer.valueOf(i2), Integer.valueOf(this.format.getChannels())));
        this.buffer.load(fArr, i, i2);
    }

    public void load(short[] sArr) {
        load(sArr, 0, sArr.length);
    }

    public void load(short[] sArr, int i, int i2) {
        SupportPreconditions.checkArgument(i + i2 <= sArr.length, String.format("Index out of range. offset (%d) + size (%d) should <= newData.length (%d)", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(sArr.length)));
        float[] fArr = new float[i2];
        while (i < i2) {
            fArr[i] = sArr[i] / ShortCompanionObject.MAX_VALUE;
            i++;
        }
        load(fArr);
    }

    public int load(AudioRecord audioRecord) {
        int i;
        SupportPreconditions.checkArgument(this.format.equals(TensorAudioFormat.create(audioRecord.getFormat())), "Incompatible audio format.");
        if (audioRecord.getAudioFormat() == 4) {
            int channelCount = audioRecord.getChannelCount() * audioRecord.getBufferSizeInFrames();
            float[] fArr = new float[channelCount];
            i = audioRecord.read(fArr, 0, channelCount, 1);
            if (i > 0) {
                load(fArr, 0, i);
                return i;
            }
        } else if (audioRecord.getAudioFormat() == 2) {
            int channelCount2 = audioRecord.getChannelCount() * audioRecord.getBufferSizeInFrames();
            short[] sArr = new short[channelCount2];
            i = audioRecord.read(sArr, 0, channelCount2, 1);
            if (i > 0) {
                load(sArr, 0, i);
                return i;
            }
        } else {
            throw new IllegalArgumentException("Unsupported encoding. Requires ENCODING_PCM_16BIT or ENCODING_PCM_FLOAT.");
        }
        if (i == -6) {
            throw new IllegalStateException("AudioRecord.ERROR_DEAD_OBJECT");
        }
        if (i == -3) {
            throw new IllegalStateException("AudioRecord.ERROR_INVALID_OPERATION");
        }
        if (i == -2) {
            throw new IllegalStateException("AudioRecord.ERROR_BAD_VALUE");
        }
        if (i != -1) {
            return 0;
        }
        throw new IllegalStateException("AudioRecord.ERROR");
    }

    public TensorBuffer getTensorBuffer() {
        ByteBuffer buffer = this.buffer.getBuffer();
        TensorBuffer tensorBufferCreateFixedSize = TensorBuffer.createFixedSize(new int[]{1, buffer.asFloatBuffer().limit()}, DataType.FLOAT32);
        tensorBufferCreateFixedSize.loadBuffer(buffer);
        return tensorBufferCreateFixedSize;
    }

    public TensorAudioFormat getFormat() {
        return this.format;
    }

    private TensorAudio(TensorAudioFormat tensorAudioFormat, int i) {
        this.format = tensorAudioFormat;
        this.buffer = new FloatRingBuffer(i * tensorAudioFormat.getChannels());
    }

    private static class FloatRingBuffer {
        private final float[] buffer;
        private int nextIndex = 0;

        public FloatRingBuffer(int i) {
            this.buffer = new float[i];
        }

        public void load(float[] fArr) {
            load(fArr, 0, fArr.length);
        }

        public void load(float[] fArr, int i, int i2) {
            SupportPreconditions.checkArgument(i + i2 <= fArr.length, String.format("Index out of range. offset (%d) + size (%d) should <= newData.length (%d)", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(fArr.length)));
            float[] fArr2 = this.buffer;
            if (i2 > fArr2.length) {
                i = i2 - fArr2.length;
                i2 = fArr2.length;
            }
            int i3 = this.nextIndex;
            if (i3 + i2 < fArr2.length) {
                System.arraycopy(fArr, i, fArr2, i3, i2);
            } else {
                int length = fArr2.length - i3;
                System.arraycopy(fArr, i, fArr2, i3, length);
                System.arraycopy(fArr, i + length, this.buffer, 0, i2 - length);
            }
            this.nextIndex = (this.nextIndex + i2) % this.buffer.length;
        }

        public ByteBuffer getBuffer() {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(DataType.FLOAT32.byteSize() * this.buffer.length);
            byteBufferAllocate.order(ByteOrder.nativeOrder());
            FloatBuffer floatBufferAsFloatBuffer = byteBufferAllocate.asFloatBuffer();
            float[] fArr = this.buffer;
            int i = this.nextIndex;
            floatBufferAsFloatBuffer.put(fArr, i, fArr.length - i);
            floatBufferAsFloatBuffer.put(this.buffer, 0, this.nextIndex);
            byteBufferAllocate.rewind();
            return byteBufferAllocate;
        }

        public int getCapacity() {
            return this.buffer.length;
        }
    }
}
