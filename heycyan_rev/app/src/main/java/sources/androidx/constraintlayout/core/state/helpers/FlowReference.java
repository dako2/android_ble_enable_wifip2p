package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.HelperReference;
import androidx.constraintlayout.core.state.State;
import androidx.constraintlayout.core.widgets.Flow;
import androidx.constraintlayout.core.widgets.HelperWidget;
import java.util.HashMap;

/* loaded from: classes.dex */
public class FlowReference extends HelperReference {
    protected float mFirstHorizontalBias;
    protected int mFirstHorizontalStyle;
    protected float mFirstVerticalBias;
    protected int mFirstVerticalStyle;
    protected Flow mFlow;
    protected int mHorizontalAlign;
    protected int mHorizontalGap;
    protected int mHorizontalStyle;
    protected float mLastHorizontalBias;
    protected int mLastHorizontalStyle;
    protected float mLastVerticalBias;
    protected int mLastVerticalStyle;
    protected HashMap<String, Float> mMapPostMargin;
    protected HashMap<String, Float> mMapPreMargin;
    protected HashMap<String, Float> mMapWeights;
    protected int mMaxElementsWrap;
    protected int mOrientation;
    protected int mPaddingBottom;
    protected int mPaddingLeft;
    protected int mPaddingRight;
    protected int mPaddingTop;
    protected int mVerticalAlign;
    protected int mVerticalGap;
    protected int mVerticalStyle;
    protected int mWrapMode;

    public FlowReference(State state, State.Helper helper) {
        super(state, helper);
        this.mWrapMode = 0;
        this.mVerticalStyle = -1;
        this.mFirstVerticalStyle = -1;
        this.mLastVerticalStyle = -1;
        this.mHorizontalStyle = -1;
        this.mFirstHorizontalStyle = -1;
        this.mLastHorizontalStyle = -1;
        this.mVerticalAlign = 2;
        this.mHorizontalAlign = 2;
        this.mVerticalGap = 0;
        this.mHorizontalGap = 0;
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        this.mMaxElementsWrap = -1;
        this.mOrientation = 0;
        this.mFirstVerticalBias = 0.5f;
        this.mLastVerticalBias = 0.5f;
        this.mFirstHorizontalBias = 0.5f;
        this.mLastHorizontalBias = 0.5f;
        if (helper == State.Helper.VERTICAL_FLOW) {
            this.mOrientation = 1;
        }
    }

    public void addFlowElement(String str, float f, float f2, float f3) {
        super.add(str);
        if (!Float.isNaN(f)) {
            if (this.mMapWeights == null) {
                this.mMapWeights = new HashMap<>();
            }
            this.mMapWeights.put(str, Float.valueOf(f));
        }
        if (!Float.isNaN(f2)) {
            if (this.mMapPreMargin == null) {
                this.mMapPreMargin = new HashMap<>();
            }
            this.mMapPreMargin.put(str, Float.valueOf(f2));
        }
        if (Float.isNaN(f3)) {
            return;
        }
        if (this.mMapPostMargin == null) {
            this.mMapPostMargin = new HashMap<>();
        }
        this.mMapPostMargin.put(str, Float.valueOf(f3));
    }

    protected float getWeight(String str) {
        HashMap<String, Float> map = this.mMapWeights;
        if (map != null && map.containsKey(str)) {
            return this.mMapWeights.get(str).floatValue();
        }
        return -1.0f;
    }

    protected float getPostMargin(String str) {
        HashMap<String, Float> map = this.mMapPreMargin;
        if (map == null || !map.containsKey(str)) {
            return 0.0f;
        }
        return this.mMapPreMargin.get(str).floatValue();
    }

    protected float getPreMargin(String str) {
        HashMap<String, Float> map = this.mMapPostMargin;
        if (map == null || !map.containsKey(str)) {
            return 0.0f;
        }
        return this.mMapPostMargin.get(str).floatValue();
    }

    public int getWrapMode() {
        return this.mWrapMode;
    }

    public void setWrapMode(int i) {
        this.mWrapMode = i;
    }

    public int getPaddingLeft() {
        return this.mPaddingLeft;
    }

    public void setPaddingLeft(int i) {
        this.mPaddingLeft = i;
    }

    public int getPaddingTop() {
        return this.mPaddingTop;
    }

    public void setPaddingTop(int i) {
        this.mPaddingTop = i;
    }

    public int getPaddingRight() {
        return this.mPaddingRight;
    }

    public void setPaddingRight(int i) {
        this.mPaddingRight = i;
    }

    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public void setPaddingBottom(int i) {
        this.mPaddingBottom = i;
    }

    public int getVerticalStyle() {
        return this.mVerticalStyle;
    }

    public void setVerticalStyle(int i) {
        this.mVerticalStyle = i;
    }

    public int getFirstVerticalStyle() {
        return this.mFirstVerticalStyle;
    }

    public void setFirstVerticalStyle(int i) {
        this.mFirstVerticalStyle = i;
    }

    public int getLastVerticalStyle() {
        return this.mLastVerticalStyle;
    }

    public void setLastVerticalStyle(int i) {
        this.mLastVerticalStyle = i;
    }

    public int getHorizontalStyle() {
        return this.mHorizontalStyle;
    }

    public void setHorizontalStyle(int i) {
        this.mHorizontalStyle = i;
    }

    public int getFirstHorizontalStyle() {
        return this.mFirstHorizontalStyle;
    }

    public void setFirstHorizontalStyle(int i) {
        this.mFirstHorizontalStyle = i;
    }

    public int getLastHorizontalStyle() {
        return this.mLastHorizontalStyle;
    }

    public void setLastHorizontalStyle(int i) {
        this.mLastHorizontalStyle = i;
    }

    public int getVerticalAlign() {
        return this.mVerticalAlign;
    }

    public void setVerticalAlign(int i) {
        this.mVerticalAlign = i;
    }

    public int getHorizontalAlign() {
        return this.mHorizontalAlign;
    }

    public void setHorizontalAlign(int i) {
        this.mHorizontalAlign = i;
    }

    public int getVerticalGap() {
        return this.mVerticalGap;
    }

    public void setVerticalGap(int i) {
        this.mVerticalGap = i;
    }

    public int getHorizontalGap() {
        return this.mHorizontalGap;
    }

    public void setHorizontalGap(int i) {
        this.mHorizontalGap = i;
    }

    public int getMaxElementsWrap() {
        return this.mMaxElementsWrap;
    }

    public void setMaxElementsWrap(int i) {
        this.mMaxElementsWrap = i;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setOrientation(int i) {
        this.mOrientation = i;
    }

    public float getVerticalBias() {
        return this.mVerticalBias;
    }

    public float getFirstVerticalBias() {
        return this.mFirstVerticalBias;
    }

    public void setFirstVerticalBias(float f) {
        this.mFirstVerticalBias = f;
    }

    public float getLastVerticalBias() {
        return this.mLastVerticalBias;
    }

    public void setLastVerticalBias(float f) {
        this.mLastVerticalBias = f;
    }

    public float getHorizontalBias() {
        return this.mHorizontalBias;
    }

    public float getFirstHorizontalBias() {
        return this.mFirstHorizontalBias;
    }

    public void setFirstHorizontalBias(float f) {
        this.mFirstHorizontalBias = f;
    }

    public float getLastHorizontalBias() {
        return this.mLastHorizontalBias;
    }

    public void setLastHorizontalBias(float f) {
        this.mLastHorizontalBias = f;
    }

    @Override // androidx.constraintlayout.core.state.HelperReference
    public HelperWidget getHelperWidget() {
        if (this.mFlow == null) {
            this.mFlow = new Flow();
        }
        return this.mFlow;
    }

    @Override // androidx.constraintlayout.core.state.HelperReference
    public void setHelperWidget(HelperWidget helperWidget) {
        if (helperWidget instanceof Flow) {
            this.mFlow = (Flow) helperWidget;
        } else {
            this.mFlow = null;
        }
    }

    @Override // androidx.constraintlayout.core.state.HelperReference, androidx.constraintlayout.core.state.ConstraintReference, androidx.constraintlayout.core.state.Reference
    public void apply() {
        getHelperWidget();
        setConstraintWidget(this.mFlow);
        this.mFlow.setOrientation(this.mOrientation);
        this.mFlow.setWrapMode(this.mWrapMode);
        int i = this.mMaxElementsWrap;
        if (i != -1) {
            this.mFlow.setMaxElementsWrap(i);
        }
        int i2 = this.mPaddingLeft;
        if (i2 != 0) {
            this.mFlow.setPaddingLeft(i2);
        }
        int i3 = this.mPaddingTop;
        if (i3 != 0) {
            this.mFlow.setPaddingTop(i3);
        }
        int i4 = this.mPaddingRight;
        if (i4 != 0) {
            this.mFlow.setPaddingRight(i4);
        }
        int i5 = this.mPaddingBottom;
        if (i5 != 0) {
            this.mFlow.setPaddingBottom(i5);
        }
        int i6 = this.mHorizontalGap;
        if (i6 != 0) {
            this.mFlow.setHorizontalGap(i6);
        }
        int i7 = this.mVerticalGap;
        if (i7 != 0) {
            this.mFlow.setVerticalGap(i7);
        }
        if (this.mHorizontalBias != 0.5f) {
            this.mFlow.setHorizontalBias(this.mHorizontalBias);
        }
        float f = this.mFirstHorizontalBias;
        if (f != 0.5f) {
            this.mFlow.setFirstHorizontalBias(f);
        }
        float f2 = this.mLastHorizontalBias;
        if (f2 != 0.5f) {
            this.mFlow.setLastHorizontalBias(f2);
        }
        if (this.mVerticalBias != 0.5f) {
            this.mFlow.setVerticalBias(this.mVerticalBias);
        }
        float f3 = this.mFirstVerticalBias;
        if (f3 != 0.5f) {
            this.mFlow.setFirstVerticalBias(f3);
        }
        float f4 = this.mLastVerticalBias;
        if (f4 != 0.5f) {
            this.mFlow.setLastVerticalBias(f4);
        }
        int i8 = this.mHorizontalAlign;
        if (i8 != 2) {
            this.mFlow.setHorizontalAlign(i8);
        }
        int i9 = this.mVerticalAlign;
        if (i9 != 2) {
            this.mFlow.setVerticalAlign(i9);
        }
        int i10 = this.mVerticalStyle;
        if (i10 != -1) {
            this.mFlow.setVerticalStyle(i10);
        }
        int i11 = this.mFirstVerticalStyle;
        if (i11 != -1) {
            this.mFlow.setFirstVerticalStyle(i11);
        }
        int i12 = this.mLastVerticalStyle;
        if (i12 != -1) {
            this.mFlow.setLastVerticalStyle(i12);
        }
        int i13 = this.mHorizontalStyle;
        if (i13 != -1) {
            this.mFlow.setHorizontalStyle(i13);
        }
        int i14 = this.mFirstHorizontalStyle;
        if (i14 != -1) {
            this.mFlow.setFirstHorizontalStyle(i14);
        }
        int i15 = this.mLastHorizontalStyle;
        if (i15 != -1) {
            this.mFlow.setLastHorizontalStyle(i15);
        }
        applyBase();
    }
}
