package androidx.core.animation;

import androidx.core.animation.Keyframe;
import androidx.core.animation.Keyframes;
import java.util.List;

/* loaded from: classes.dex */
class IntKeyframeSet extends KeyframeSet<Integer> implements Keyframes.IntKeyframes {
    IntKeyframeSet(Keyframe.IntKeyframe... intKeyframeArr) {
        super(intKeyframeArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.core.animation.KeyframeSet, androidx.core.animation.Keyframes
    public Integer getValue(float f) {
        return Integer.valueOf(getIntValue(f));
    }

    @Override // androidx.core.animation.KeyframeSet
    /* renamed from: clone */
    public IntKeyframeSet mo674clone() {
        List<Keyframe<T>> list = this.mKeyframes;
        int size = this.mKeyframes.size();
        Keyframe.IntKeyframe[] intKeyframeArr = new Keyframe.IntKeyframe[size];
        for (int i = 0; i < size; i++) {
            intKeyframeArr[i] = (Keyframe.IntKeyframe) ((Keyframe) list.get(i)).mo675clone();
        }
        return new IntKeyframeSet(intKeyframeArr);
    }

    @Override // androidx.core.animation.Keyframes.IntKeyframes
    public int getIntValue(float f) {
        if (f <= 0.0f) {
            Keyframe.IntKeyframe intKeyframe = (Keyframe.IntKeyframe) this.mKeyframes.get(0);
            Keyframe.IntKeyframe intKeyframe2 = (Keyframe.IntKeyframe) this.mKeyframes.get(1);
            int intValue = intKeyframe.getIntValue();
            int intValue2 = intKeyframe2.getIntValue();
            float fraction = intKeyframe.getFraction();
            float fraction2 = intKeyframe2.getFraction();
            Interpolator interpolator = intKeyframe2.getInterpolator();
            if (interpolator != null) {
                f = interpolator.getInterpolation(f);
            }
            float f2 = (f - fraction) / (fraction2 - fraction);
            return this.mEvaluator == null ? intValue + ((int) (f2 * (intValue2 - intValue))) : ((Integer) this.mEvaluator.evaluate(f2, Integer.valueOf(intValue), Integer.valueOf(intValue2))).intValue();
        }
        if (f >= 1.0f) {
            Keyframe.IntKeyframe intKeyframe3 = (Keyframe.IntKeyframe) this.mKeyframes.get(this.mNumKeyframes - 2);
            Keyframe.IntKeyframe intKeyframe4 = (Keyframe.IntKeyframe) this.mKeyframes.get(this.mNumKeyframes - 1);
            int intValue3 = intKeyframe3.getIntValue();
            int intValue4 = intKeyframe4.getIntValue();
            float fraction3 = intKeyframe3.getFraction();
            float fraction4 = intKeyframe4.getFraction();
            Interpolator interpolator2 = intKeyframe4.getInterpolator();
            if (interpolator2 != null) {
                f = interpolator2.getInterpolation(f);
            }
            float f3 = (f - fraction3) / (fraction4 - fraction3);
            return this.mEvaluator == null ? intValue3 + ((int) (f3 * (intValue4 - intValue3))) : ((Integer) this.mEvaluator.evaluate(f3, Integer.valueOf(intValue3), Integer.valueOf(intValue4))).intValue();
        }
        Keyframe.IntKeyframe intKeyframe5 = (Keyframe.IntKeyframe) this.mKeyframes.get(0);
        int i = 1;
        while (i < this.mNumKeyframes) {
            Keyframe.IntKeyframe intKeyframe6 = (Keyframe.IntKeyframe) this.mKeyframes.get(i);
            if (f < intKeyframe6.getFraction()) {
                Interpolator interpolator3 = intKeyframe6.getInterpolator();
                float fraction5 = (f - intKeyframe5.getFraction()) / (intKeyframe6.getFraction() - intKeyframe5.getFraction());
                int intValue5 = intKeyframe5.getIntValue();
                int intValue6 = intKeyframe6.getIntValue();
                if (interpolator3 != null) {
                    fraction5 = interpolator3.getInterpolation(fraction5);
                }
                if (this.mEvaluator == null) {
                    return intValue5 + Math.round(fraction5 * (intValue6 - intValue5));
                }
                return ((Integer) this.mEvaluator.evaluate(fraction5, Integer.valueOf(intValue5), Integer.valueOf(intValue6))).intValue();
            }
            i++;
            intKeyframe5 = intKeyframe6;
        }
        return ((Integer) ((Keyframe) this.mKeyframes.get(this.mNumKeyframes - 1)).getValue()).intValue();
    }

    @Override // androidx.core.animation.KeyframeSet, androidx.core.animation.Keyframes
    public Class<Integer> getType() {
        return Integer.class;
    }
}
