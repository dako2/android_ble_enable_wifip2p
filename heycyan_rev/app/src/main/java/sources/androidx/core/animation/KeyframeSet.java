package androidx.core.animation;

import android.graphics.Path;
import android.util.Log;
import androidx.core.animation.Keyframe;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
class KeyframeSet<T> implements Keyframes<T> {
    TypeEvaluator<T> mEvaluator;
    Keyframe<T> mFirstKeyframe;
    Interpolator mInterpolator;
    List<Keyframe<T>> mKeyframes;
    Keyframe<T> mLastKeyframe;
    int mNumKeyframes;

    @SafeVarargs
    KeyframeSet(Keyframe<T>... keyframeArr) {
        this.mNumKeyframes = keyframeArr.length;
        this.mKeyframes = Arrays.asList(keyframeArr);
        this.mFirstKeyframe = keyframeArr[0];
        Keyframe<T> keyframe = keyframeArr[this.mNumKeyframes - 1];
        this.mLastKeyframe = keyframe;
        this.mInterpolator = keyframe.getInterpolator();
    }

    KeyframeSet(List<Keyframe<T>> list) {
        this.mKeyframes = list;
        this.mNumKeyframes = list.size();
        this.mFirstKeyframe = list.get(0);
        Keyframe<T> keyframe = list.get(this.mNumKeyframes - 1);
        this.mLastKeyframe = keyframe;
        this.mInterpolator = keyframe.getInterpolator();
    }

    @Override // androidx.core.animation.Keyframes
    public List<Keyframe<T>> getKeyframes() {
        return this.mKeyframes;
    }

    static KeyframeSet<Integer> ofInt(int... iArr) {
        int length = iArr.length;
        Keyframe.IntKeyframe[] intKeyframeArr = new Keyframe.IntKeyframe[Math.max(length, 2)];
        if (length == 1) {
            intKeyframeArr[0] = (Keyframe.IntKeyframe) Keyframe.ofInt(0.0f);
            intKeyframeArr[1] = (Keyframe.IntKeyframe) Keyframe.ofInt(1.0f, iArr[0]);
        } else {
            intKeyframeArr[0] = (Keyframe.IntKeyframe) Keyframe.ofInt(0.0f, iArr[0]);
            for (int i = 1; i < length; i++) {
                intKeyframeArr[i] = (Keyframe.IntKeyframe) Keyframe.ofInt(i / (length - 1), iArr[i]);
            }
        }
        return new IntKeyframeSet(intKeyframeArr);
    }

    static KeyframeSet<Float> ofFloat(float... fArr) {
        int length = fArr.length;
        Keyframe.FloatKeyframe[] floatKeyframeArr = new Keyframe.FloatKeyframe[Math.max(length, 2)];
        boolean z = false;
        if (length == 1) {
            floatKeyframeArr[0] = (Keyframe.FloatKeyframe) Keyframe.ofFloat(0.0f);
            floatKeyframeArr[1] = (Keyframe.FloatKeyframe) Keyframe.ofFloat(1.0f, fArr[0]);
            if (Float.isNaN(fArr[0])) {
                z = true;
            }
        } else {
            floatKeyframeArr[0] = (Keyframe.FloatKeyframe) Keyframe.ofFloat(0.0f, fArr[0]);
            for (int i = 1; i < length; i++) {
                floatKeyframeArr[i] = (Keyframe.FloatKeyframe) Keyframe.ofFloat(i / (length - 1), fArr[i]);
                if (Float.isNaN(fArr[i])) {
                    z = true;
                }
            }
        }
        if (z) {
            Log.w("Animator", "Bad value (NaN) in float animator");
        }
        return new FloatKeyframeSet(floatKeyframeArr);
    }

    @SafeVarargs
    public static <T> KeyframeSet<T> ofKeyframe(Keyframe<T>... keyframeArr) {
        int length = keyframeArr.length;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (Keyframe<T> keyframe : keyframeArr) {
            if (keyframe instanceof Keyframe.FloatKeyframe) {
                z = true;
            } else if (keyframe instanceof Keyframe.IntKeyframe) {
                z2 = true;
            } else {
                z3 = true;
            }
        }
        if (z && !z2 && !z3) {
            Keyframe.FloatKeyframe[] floatKeyframeArr = new Keyframe.FloatKeyframe[length];
            while (i < length) {
                floatKeyframeArr[i] = (Keyframe.FloatKeyframe) keyframeArr[i];
                i++;
            }
            return new FloatKeyframeSet(floatKeyframeArr);
        }
        if (z2 && !z && !z3) {
            Keyframe.IntKeyframe[] intKeyframeArr = new Keyframe.IntKeyframe[length];
            while (i < length) {
                intKeyframeArr[i] = (Keyframe.IntKeyframe) keyframeArr[i];
                i++;
            }
            return new IntKeyframeSet(intKeyframeArr);
        }
        return new KeyframeSet<>(keyframeArr);
    }

    @SafeVarargs
    public static <T> KeyframeSet<T> ofObject(T... tArr) {
        int length = tArr.length;
        ArrayList arrayList = new ArrayList(Math.max(length, 2));
        if (length == 1) {
            arrayList.add(Keyframe.ofObject(0.0f));
            arrayList.add(Keyframe.ofObject(1.0f, tArr[0]));
        } else {
            arrayList.add(Keyframe.ofObject(0.0f, tArr[0]));
            for (int i = 1; i < length; i++) {
                arrayList.add(Keyframe.ofObject(i / (length - 1), tArr[i]));
            }
        }
        return new KeyframeSet<>(arrayList);
    }

    public static PathKeyframes ofPath(Path path) {
        return new PathKeyframes(path);
    }

    public static PathKeyframes ofPath(Path path, float f) {
        return new PathKeyframes(path, f);
    }

    @Override // androidx.core.animation.Keyframes
    public void setEvaluator(TypeEvaluator<T> typeEvaluator) {
        this.mEvaluator = typeEvaluator;
    }

    @Override // androidx.core.animation.Keyframes
    public Class<?> getType() {
        return this.mFirstKeyframe.getType();
    }

    @Override // 
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public KeyframeSet<T> mo674clone() {
        List<Keyframe<T>> list = this.mKeyframes;
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(list.get(i).mo675clone());
        }
        return new KeyframeSet<>(arrayList);
    }

    @Override // androidx.core.animation.Keyframes
    public T getValue(float f) {
        int i = this.mNumKeyframes;
        if (i == 2) {
            Interpolator interpolator = this.mInterpolator;
            if (interpolator != null) {
                f = interpolator.getInterpolation(f);
            }
            return (T) this.mEvaluator.evaluate(f, this.mFirstKeyframe.getValue(), this.mLastKeyframe.getValue());
        }
        int i2 = 1;
        if (f <= 0.0f) {
            Keyframe<T> keyframe = this.mKeyframes.get(1);
            Interpolator interpolator2 = keyframe.getInterpolator();
            if (interpolator2 != null) {
                f = interpolator2.getInterpolation(f);
            }
            float fraction = this.mFirstKeyframe.getFraction();
            return (T) this.mEvaluator.evaluate((f - fraction) / (keyframe.getFraction() - fraction), this.mFirstKeyframe.getValue(), keyframe.getValue());
        }
        if (f >= 1.0f) {
            Keyframe<T> keyframe2 = this.mKeyframes.get(i - 2);
            Interpolator interpolator3 = this.mLastKeyframe.getInterpolator();
            if (interpolator3 != null) {
                f = interpolator3.getInterpolation(f);
            }
            float fraction2 = keyframe2.getFraction();
            return (T) this.mEvaluator.evaluate((f - fraction2) / (this.mLastKeyframe.getFraction() - fraction2), keyframe2.getValue(), this.mLastKeyframe.getValue());
        }
        Keyframe<T> keyframe3 = this.mFirstKeyframe;
        while (i2 < this.mNumKeyframes) {
            Keyframe<T> keyframe4 = this.mKeyframes.get(i2);
            if (f < keyframe4.getFraction()) {
                Interpolator interpolator4 = keyframe4.getInterpolator();
                float fraction3 = keyframe3.getFraction();
                float fraction4 = (f - fraction3) / (keyframe4.getFraction() - fraction3);
                if (interpolator4 != null) {
                    fraction4 = interpolator4.getInterpolation(fraction4);
                }
                return this.mEvaluator.evaluate(fraction4, keyframe3.getValue(), keyframe4.getValue());
            }
            i2++;
            keyframe3 = keyframe4;
        }
        return this.mLastKeyframe.getValue();
    }

    public String toString() {
        String str = HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR;
        for (int i = 0; i < this.mNumKeyframes; i++) {
            str = str + this.mKeyframes.get(i).getValue() + "  ";
        }
        return str;
    }
}
