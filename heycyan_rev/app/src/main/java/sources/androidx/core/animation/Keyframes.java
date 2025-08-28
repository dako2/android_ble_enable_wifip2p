package androidx.core.animation;

import java.util.List;

/* loaded from: classes.dex */
interface Keyframes<T> extends Cloneable {

    public interface FloatKeyframes extends Keyframes<Float> {
        float getFloatValue(float f);
    }

    public interface IntKeyframes extends Keyframes<Integer> {
        int getIntValue(float f);
    }

    Keyframes clone();

    List<Keyframe<T>> getKeyframes();

    Class<?> getType();

    T getValue(float f);

    void setEvaluator(TypeEvaluator<T> typeEvaluator);
}
