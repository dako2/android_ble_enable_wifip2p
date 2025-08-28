package androidx.constraintlayout.core.dsl;

import androidx.constraintlayout.core.motion.utils.TypedValues;

/* loaded from: classes.dex */
public class KeyPosition extends Keys {
    private int mFrame;
    private String mTarget;
    private String mTransitionEasing = null;
    private float mPercentWidth = Float.NaN;
    private float mPercentHeight = Float.NaN;
    private float mPercentX = Float.NaN;
    private float mPercentY = Float.NaN;
    private Type mPositionType = Type.CARTESIAN;

    public enum Type {
        CARTESIAN,
        SCREEN,
        PATH
    }

    public KeyPosition(String str, int i) {
        this.mTarget = null;
        this.mFrame = 0;
        this.mTarget = str;
        this.mFrame = i;
    }

    public String getTransitionEasing() {
        return this.mTransitionEasing;
    }

    public void setTransitionEasing(String str) {
        this.mTransitionEasing = str;
    }

    public int getFrames() {
        return this.mFrame;
    }

    public void setFrames(int i) {
        this.mFrame = i;
    }

    public float getPercentWidth() {
        return this.mPercentWidth;
    }

    public void setPercentWidth(float f) {
        this.mPercentWidth = f;
    }

    public float getPercentHeight() {
        return this.mPercentHeight;
    }

    public void setPercentHeight(float f) {
        this.mPercentHeight = f;
    }

    public float getPercentX() {
        return this.mPercentX;
    }

    public void setPercentX(float f) {
        this.mPercentX = f;
    }

    public float getPercentY() {
        return this.mPercentY;
    }

    public void setPercentY(float f) {
        this.mPercentY = f;
    }

    public Type getPositionType() {
        return this.mPositionType;
    }

    public void setPositionType(Type type) {
        this.mPositionType = type;
    }

    public String getTarget() {
        return this.mTarget;
    }

    public void setTarget(String str) {
        this.mTarget = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("KeyPositions:{\n");
        append(sb, TypedValues.AttributesType.S_TARGET, this.mTarget);
        sb.append("frame:").append(this.mFrame).append(",\n");
        if (this.mPositionType != null) {
            sb.append("type:'").append(this.mPositionType).append("',\n");
        }
        append(sb, "easing", this.mTransitionEasing);
        append(sb, "percentX", this.mPercentX);
        append(sb, "percentY", this.mPercentY);
        append(sb, "percentWidth", this.mPercentWidth);
        append(sb, "percentHeight", this.mPercentHeight);
        sb.append("},\n");
        return sb.toString();
    }
}
