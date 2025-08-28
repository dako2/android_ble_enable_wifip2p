package androidx.core.animation;

import android.graphics.Path;
import android.graphics.PointF;
import androidx.core.animation.Keyframes;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
class PathKeyframes implements Keyframes<PointF> {
    private static final ArrayList<Keyframe<PointF>> EMPTY_KEYFRAMES = new ArrayList<>();
    private static final int FRACTION_OFFSET = 0;
    private static final int NUM_COMPONENTS = 3;
    private static final int X_OFFSET = 1;
    private static final int Y_OFFSET = 2;
    private final float[] mKeyframeData;
    private PointF mTempPointF;

    private static float interpolate(float f, float f2, float f3) {
        return f2 + ((f3 - f2) * f);
    }

    @Override // androidx.core.animation.Keyframes
    public void setEvaluator(TypeEvaluator<PointF> typeEvaluator) {
    }

    PathKeyframes(Path path) {
        this(path, 0.5f);
    }

    PathKeyframes(Path path, float f) {
        this.mTempPointF = new PointF();
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("The path must not be null or empty");
        }
        this.mKeyframeData = PathUtils.createKeyFrameData(path, f);
    }

    @Override // androidx.core.animation.Keyframes
    public List<Keyframe<PointF>> getKeyframes() {
        return EMPTY_KEYFRAMES;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.core.animation.Keyframes
    public PointF getValue(float f) {
        int length = this.mKeyframeData.length / 3;
        int i = 0;
        if (f < 0.0f) {
            return interpolateInRange(f, 0, 1);
        }
        if (f > 1.0f) {
            return interpolateInRange(f, length - 2, length - 1);
        }
        if (f == 0.0f) {
            return pointForIndex(0);
        }
        if (f == 1.0f) {
            return pointForIndex(length - 1);
        }
        int i2 = length - 1;
        while (i <= i2) {
            int i3 = (i + i2) / 2;
            float f2 = this.mKeyframeData[i3 * 3];
            if (f < f2) {
                i2 = i3 - 1;
            } else {
                if (f <= f2) {
                    return pointForIndex(i3);
                }
                i = i3 + 1;
            }
        }
        return interpolateInRange(f, i2, i);
    }

    private PointF interpolateInRange(float f, int i, int i2) {
        int i3 = i * 3;
        int i4 = i2 * 3;
        float[] fArr = this.mKeyframeData;
        float f2 = fArr[i3];
        float f3 = (f - f2) / (fArr[i4] - f2);
        float f4 = fArr[i3 + 1];
        float f5 = fArr[i4 + 1];
        float f6 = fArr[i3 + 2];
        float f7 = fArr[i4 + 2];
        this.mTempPointF.set(interpolate(f3, f4, f5), interpolate(f3, f6, f7));
        return this.mTempPointF;
    }

    @Override // androidx.core.animation.Keyframes
    public Class<PointF> getType() {
        return PointF.class;
    }

    @Override // androidx.core.animation.Keyframes
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Keyframes m676clone() {
        try {
            return (Keyframes) super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    private PointF pointForIndex(int i) {
        int i2 = i * 3;
        PointF pointF = this.mTempPointF;
        float[] fArr = this.mKeyframeData;
        pointF.set(fArr[i2 + 1], fArr[i2 + 2]);
        return this.mTempPointF;
    }

    public Keyframes.FloatKeyframes createXFloatKeyframes() {
        return new FloatKeyframesBase() { // from class: androidx.core.animation.PathKeyframes.1
            @Override // androidx.core.animation.Keyframes.FloatKeyframes
            public float getFloatValue(float f) {
                return PathKeyframes.this.getValue(f).x;
            }
        };
    }

    public Keyframes.FloatKeyframes createYFloatKeyframes() {
        return new FloatKeyframesBase() { // from class: androidx.core.animation.PathKeyframes.2
            @Override // androidx.core.animation.Keyframes.FloatKeyframes
            public float getFloatValue(float f) {
                return PathKeyframes.this.getValue(f).y;
            }
        };
    }

    public Keyframes.IntKeyframes createXIntKeyframes() {
        return new IntKeyframesBase() { // from class: androidx.core.animation.PathKeyframes.3
            @Override // androidx.core.animation.Keyframes.IntKeyframes
            public int getIntValue(float f) {
                return Math.round(PathKeyframes.this.getValue(f).x);
            }
        };
    }

    public Keyframes.IntKeyframes createYIntKeyframes() {
        return new IntKeyframesBase() { // from class: androidx.core.animation.PathKeyframes.4
            @Override // androidx.core.animation.Keyframes.IntKeyframes
            public int getIntValue(float f) {
                return Math.round(PathKeyframes.this.getValue(f).y);
            }
        };
    }

    private static abstract class SimpleKeyframes<T> implements Keyframes<T> {
        private final ArrayList<Keyframe<T>> mEmptyFrames;

        @Override // androidx.core.animation.Keyframes
        public void setEvaluator(TypeEvaluator<T> typeEvaluator) {
        }

        private SimpleKeyframes() {
            this.mEmptyFrames = new ArrayList<>();
        }

        @Override // androidx.core.animation.Keyframes
        public List<Keyframe<T>> getKeyframes() {
            return this.mEmptyFrames;
        }

        @Override // androidx.core.animation.Keyframes
        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public Keyframes<T> m677clone() {
            try {
                return (Keyframes) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    static abstract class IntKeyframesBase extends SimpleKeyframes<Integer> implements Keyframes.IntKeyframes {
        IntKeyframesBase() {
            super();
        }

        @Override // androidx.core.animation.Keyframes
        public Class<Integer> getType() {
            return Integer.class;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.core.animation.Keyframes
        public Integer getValue(float f) {
            return Integer.valueOf(getIntValue(f));
        }
    }

    static abstract class FloatKeyframesBase extends SimpleKeyframes<Float> implements Keyframes.FloatKeyframes {
        FloatKeyframesBase() {
            super();
        }

        @Override // androidx.core.animation.Keyframes
        public Class<Float> getType() {
            return Float.class;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.core.animation.Keyframes
        public Float getValue(float f) {
            return Float.valueOf(getFloatValue(f));
        }
    }
}
