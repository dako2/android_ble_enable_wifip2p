package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.Cache;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.state.WidgetFrame;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.analyzer.ChainRun;
import androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun;
import androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ConstraintWidget {
    public static final int ANCHOR_BASELINE = 4;
    public static final int ANCHOR_BOTTOM = 3;
    public static final int ANCHOR_LEFT = 0;
    public static final int ANCHOR_RIGHT = 1;
    public static final int ANCHOR_TOP = 2;
    private static final boolean AUTOTAG_CENTER = false;
    public static final int BOTH = 2;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    static final int DIMENSION_HORIZONTAL = 0;
    static final int DIMENSION_VERTICAL = 1;
    protected static final int DIRECT = 2;
    private static final boolean DO_NOT_USE = false;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_RATIO = 3;
    public static final int MATCH_CONSTRAINT_RATIO_RESOLVED = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    protected static final int SOLVER = 1;
    public static final int UNKNOWN = -1;
    private static final boolean USE_WRAP_DIMENSION_FOR_SPREAD = false;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    private static final int WRAP = -2;
    public static final int WRAP_BEHAVIOR_HORIZONTAL_ONLY = 1;
    public static final int WRAP_BEHAVIOR_INCLUDED = 0;
    public static final int WRAP_BEHAVIOR_SKIPPED = 3;
    public static final int WRAP_BEHAVIOR_VERTICAL_ONLY = 2;
    public WidgetFrame frame;
    public ChainRun horizontalChainRun;
    public int horizontalGroup;
    public boolean[] isTerminalWidget;
    protected ArrayList<ConstraintAnchor> mAnchors;
    private boolean mAnimated;
    public ConstraintAnchor mBaseline;
    int mBaselineDistance;
    public ConstraintAnchor mBottom;
    boolean mBottomHasCentered;
    public ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX;
    ConstraintAnchor mCenterY;
    public float mCircleConstraintAngle;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    public float mDimensionRatio;
    protected int mDimensionRatioSide;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    boolean mGroupsToSolver;
    private boolean mHasBaseline;
    int mHeight;
    private int mHeightOverride;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution;
    public HorizontalWidgetRun mHorizontalRun;
    private boolean mHorizontalSolvingPass;
    boolean mHorizontalWrapVisited;
    private boolean mInPlaceholder;
    private boolean mInVirtualLayout;
    public boolean mIsHeightWrapContent;
    private boolean[] mIsInBarrier;
    public boolean mIsWidthWrapContent;
    private int mLastHorizontalMeasureSpec;
    private int mLastVerticalMeasureSpec;
    public ConstraintAnchor mLeft;
    boolean mLeftHasCentered;
    public ConstraintAnchor[] mListAnchors;
    public DimensionBehaviour[] mListDimensionBehaviors;
    protected ConstraintWidget[] mListNextMatchConstraintsWidget;
    public int mMatchConstraintDefaultHeight;
    public int mMatchConstraintDefaultWidth;
    public int mMatchConstraintMaxHeight;
    public int mMatchConstraintMaxWidth;
    public int mMatchConstraintMinHeight;
    public int mMatchConstraintMinWidth;
    public float mMatchConstraintPercentHeight;
    public float mMatchConstraintPercentWidth;
    private int[] mMaxDimension;
    private boolean mMeasureRequested;
    protected int mMinHeight;
    protected int mMinWidth;
    protected ConstraintWidget[] mNextChainWidget;
    protected int mOffsetX;
    protected int mOffsetY;
    private boolean mOptimizeWrapO;
    private boolean mOptimizeWrapOnResolved;
    public ConstraintWidget mParent;
    int mRelX;
    int mRelY;
    float mResolvedDimensionRatio;
    int mResolvedDimensionRatioSide;
    boolean mResolvedHasRatio;
    private boolean mResolvedHorizontal;
    public int[] mResolvedMatchConstraintDefault;
    private boolean mResolvedVertical;
    public ConstraintAnchor mRight;
    boolean mRightHasCentered;
    public ConstraintAnchor mTop;
    boolean mTopHasCentered;
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution;
    public VerticalWidgetRun mVerticalRun;
    private boolean mVerticalSolvingPass;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    public float[] mWeight;
    int mWidth;
    private int mWidthOverride;
    private int mWrapBehaviorInParent;

    /* renamed from: mX */
    protected int f36mX;

    /* renamed from: mY */
    protected int f37mY;
    public boolean measured;
    public WidgetRun[] run;
    public String stringId;
    public ChainRun verticalChainRun;
    public int verticalGroup;

    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public WidgetRun getRun(int i) {
        if (i == 0) {
            return this.mHorizontalRun;
        }
        if (i == 1) {
            return this.mVerticalRun;
        }
        return null;
    }

    public void setFinalFrame(int i, int i2, int i3, int i4, int i5, int i6) {
        setFrame(i, i2, i3, i4);
        setBaselineDistance(i5);
        if (i6 == 0) {
            this.mResolvedHorizontal = true;
            this.mResolvedVertical = false;
        } else if (i6 == 1) {
            this.mResolvedHorizontal = false;
            this.mResolvedVertical = true;
        } else if (i6 == 2) {
            this.mResolvedHorizontal = true;
            this.mResolvedVertical = true;
        } else {
            this.mResolvedHorizontal = false;
            this.mResolvedVertical = false;
        }
    }

    public void setFinalLeft(int i) {
        this.mLeft.setFinalValue(i);
        this.f36mX = i;
    }

    public void setFinalTop(int i) {
        this.mTop.setFinalValue(i);
        this.f37mY = i;
    }

    public void resetSolvingPassFlag() {
        this.mHorizontalSolvingPass = false;
        this.mVerticalSolvingPass = false;
    }

    public boolean isHorizontalSolvingPassDone() {
        return this.mHorizontalSolvingPass;
    }

    public boolean isVerticalSolvingPassDone() {
        return this.mVerticalSolvingPass;
    }

    public void markHorizontalSolvingPassDone() {
        this.mHorizontalSolvingPass = true;
    }

    public void markVerticalSolvingPassDone() {
        this.mVerticalSolvingPass = true;
    }

    public void setFinalHorizontal(int i, int i2) {
        if (this.mResolvedHorizontal) {
            return;
        }
        this.mLeft.setFinalValue(i);
        this.mRight.setFinalValue(i2);
        this.f36mX = i;
        this.mWidth = i2 - i;
        this.mResolvedHorizontal = true;
    }

    public void setFinalVertical(int i, int i2) {
        if (this.mResolvedVertical) {
            return;
        }
        this.mTop.setFinalValue(i);
        this.mBottom.setFinalValue(i2);
        this.f37mY = i;
        this.mHeight = i2 - i;
        if (this.mHasBaseline) {
            this.mBaseline.setFinalValue(i + this.mBaselineDistance);
        }
        this.mResolvedVertical = true;
    }

    public void setFinalBaseline(int i) {
        if (this.mHasBaseline) {
            int i2 = i - this.mBaselineDistance;
            int i3 = this.mHeight + i2;
            this.f37mY = i2;
            this.mTop.setFinalValue(i2);
            this.mBottom.setFinalValue(i3);
            this.mBaseline.setFinalValue(i);
            this.mResolvedVertical = true;
        }
    }

    public boolean isResolvedHorizontally() {
        return this.mResolvedHorizontal || (this.mLeft.hasFinalValue() && this.mRight.hasFinalValue());
    }

    public boolean isResolvedVertically() {
        return this.mResolvedVertical || (this.mTop.hasFinalValue() && this.mBottom.hasFinalValue());
    }

    public void resetFinalResolution() {
        this.mResolvedHorizontal = false;
        this.mResolvedVertical = false;
        this.mHorizontalSolvingPass = false;
        this.mVerticalSolvingPass = false;
        int size = this.mAnchors.size();
        for (int i = 0; i < size; i++) {
            this.mAnchors.get(i).resetFinalResolution();
        }
    }

    public void ensureMeasureRequested() {
        this.mMeasureRequested = true;
    }

    public boolean hasDependencies() {
        int size = this.mAnchors.size();
        for (int i = 0; i < size; i++) {
            if (this.mAnchors.get(i).hasDependents()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDanglingDimension(int i) {
        if (i == 0) {
            return (this.mLeft.mTarget != null ? 1 : 0) + (this.mRight.mTarget != null ? 1 : 0) < 2;
        }
        return ((this.mTop.mTarget != null ? 1 : 0) + (this.mBottom.mTarget != null ? 1 : 0)) + (this.mBaseline.mTarget != null ? 1 : 0) < 2;
    }

    public boolean hasResolvedTargets(int i, int i2) {
        return i == 0 ? this.mLeft.mTarget != null && this.mLeft.mTarget.hasFinalValue() && this.mRight.mTarget != null && this.mRight.mTarget.hasFinalValue() && (this.mRight.mTarget.getFinalValue() - this.mRight.getMargin()) - (this.mLeft.mTarget.getFinalValue() + this.mLeft.getMargin()) >= i2 : this.mTop.mTarget != null && this.mTop.mTarget.hasFinalValue() && this.mBottom.mTarget != null && this.mBottom.mTarget.hasFinalValue() && (this.mBottom.mTarget.getFinalValue() - this.mBottom.getMargin()) - (this.mTop.mTarget.getFinalValue() + this.mTop.getMargin()) >= i2;
        return false;
    }

    public boolean isInVirtualLayout() {
        return this.mInVirtualLayout;
    }

    public void setInVirtualLayout(boolean z) {
        this.mInVirtualLayout = z;
    }

    public int getMaxHeight() {
        return this.mMaxDimension[1];
    }

    public int getMaxWidth() {
        return this.mMaxDimension[0];
    }

    public void setMaxWidth(int i) {
        this.mMaxDimension[0] = i;
    }

    public void setMaxHeight(int i) {
        this.mMaxDimension[1] = i;
    }

    public boolean isSpreadWidth() {
        return this.mMatchConstraintDefaultWidth == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMaxWidth == 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isSpreadHeight() {
        return this.mMatchConstraintDefaultHeight == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinHeight == 0 && this.mMatchConstraintMaxHeight == 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public void setHasBaseline(boolean z) {
        this.mHasBaseline = z;
    }

    public boolean getHasBaseline() {
        return this.mHasBaseline;
    }

    public boolean isInPlaceholder() {
        return this.mInPlaceholder;
    }

    public void setInPlaceholder(boolean z) {
        this.mInPlaceholder = z;
    }

    protected void setInBarrier(int i, boolean z) {
        this.mIsInBarrier[i] = z;
    }

    public boolean isInBarrier(int i) {
        return this.mIsInBarrier[i];
    }

    public void setMeasureRequested(boolean z) {
        this.mMeasureRequested = z;
    }

    public boolean isMeasureRequested() {
        return this.mMeasureRequested && this.mVisibility != 8;
    }

    public void setWrapBehaviorInParent(int i) {
        if (i < 0 || i > 3) {
            return;
        }
        this.mWrapBehaviorInParent = i;
    }

    public int getWrapBehaviorInParent() {
        return this.mWrapBehaviorInParent;
    }

    public int getLastHorizontalMeasureSpec() {
        return this.mLastHorizontalMeasureSpec;
    }

    public int getLastVerticalMeasureSpec() {
        return this.mLastVerticalMeasureSpec;
    }

    public void setLastMeasureSpec(int i, int i2) {
        this.mLastHorizontalMeasureSpec = i;
        this.mLastVerticalMeasureSpec = i2;
        setMeasureRequested(false);
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = Float.NaN;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.f36mX = 0;
        this.f37mY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mListDimensionBehaviors[0] = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors[1] = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        float[] fArr = this.mWeight;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        int[] iArr = this.mMaxDimension;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
        this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedHasRatio = false;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mGroupsToSolver = false;
        boolean[] zArr = this.isTerminalWidget;
        zArr[0] = true;
        zArr[1] = true;
        this.mInVirtualLayout = false;
        boolean[] zArr2 = this.mIsInBarrier;
        zArr2[0] = false;
        zArr2[1] = false;
        this.mMeasureRequested = true;
        int[] iArr2 = this.mResolvedMatchConstraintDefault;
        iArr2[0] = 0;
        iArr2[1] = 0;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
    }

    private void serializeAnchor(StringBuilder sb, String str, ConstraintAnchor constraintAnchor) {
        if (constraintAnchor.mTarget == null) {
            return;
        }
        sb.append(str);
        sb.append(" : [ '");
        sb.append(constraintAnchor.mTarget);
        sb.append("',");
        sb.append(constraintAnchor.mMargin);
        sb.append(",");
        sb.append(constraintAnchor.mGoneMargin);
        sb.append(",");
        sb.append(" ] ,\n");
    }

    private void serializeCircle(StringBuilder sb, ConstraintAnchor constraintAnchor, float f) {
        if (constraintAnchor.mTarget == null || Float.isNaN(f)) {
            return;
        }
        sb.append("circle : [ '");
        sb.append(constraintAnchor.mTarget);
        sb.append("',");
        sb.append(constraintAnchor.mMargin);
        sb.append(",");
        sb.append(f);
        sb.append(",");
        sb.append(" ] ,\n");
    }

    private void serializeAttribute(StringBuilder sb, String str, float f, float f2) {
        if (f == f2) {
            return;
        }
        sb.append(str);
        sb.append(" :   ");
        sb.append(f);
        sb.append(",\n");
    }

    private void serializeAttribute(StringBuilder sb, String str, int i, int i2) {
        if (i == i2) {
            return;
        }
        sb.append(str);
        sb.append(" :   ");
        sb.append(i);
        sb.append(",\n");
    }

    private void serializeAttribute(StringBuilder sb, String str, String str2, String str3) {
        if (str3.equals(str2)) {
            return;
        }
        sb.append(str);
        sb.append(" :   ");
        sb.append(str2);
        sb.append(",\n");
    }

    private void serializeDimensionRatio(StringBuilder sb, String str, float f, int i) {
        if (f == 0.0f) {
            return;
        }
        sb.append(str);
        sb.append(" :  [");
        sb.append(f);
        sb.append(",");
        sb.append(i);
        sb.append("");
        sb.append("],\n");
    }

    private void serializeSize(StringBuilder sb, String str, int i, int i2, int i3, int i4, int i5, int i6, float f, float f2) {
        sb.append(str);
        sb.append(" :  {\n");
        serializeAttribute(sb, "size", i, Integer.MIN_VALUE);
        serializeAttribute(sb, "min", i2, 0);
        serializeAttribute(sb, "max", i3, Integer.MAX_VALUE);
        serializeAttribute(sb, "matchMin", i5, 0);
        serializeAttribute(sb, "matchDef", i6, 0);
        serializeAttribute(sb, "matchPercent", i6, 1);
        serializeAttribute(sb, "matchConstraintPercent", f, 1.0f);
        serializeAttribute(sb, "weight", f2, 1.0f);
        serializeAttribute(sb, "override", i4, 1);
        sb.append("},\n");
    }

    public StringBuilder serialize(StringBuilder sb) {
        sb.append("{\n");
        serializeAnchor(sb, "left", this.mLeft);
        serializeAnchor(sb, "top", this.mTop);
        serializeAnchor(sb, "right", this.mRight);
        serializeAnchor(sb, "bottom", this.mBottom);
        serializeAnchor(sb, "baseline", this.mBaseline);
        serializeAnchor(sb, "centerX", this.mCenterX);
        serializeAnchor(sb, "centerY", this.mCenterY);
        serializeCircle(sb, this.mCenter, this.mCircleConstraintAngle);
        serializeSize(sb, "width", this.mWidth, this.mMinWidth, this.mMaxDimension[0], this.mWidthOverride, this.mMatchConstraintMinWidth, this.mMatchConstraintDefaultWidth, this.mMatchConstraintPercentWidth, this.mWeight[0]);
        serializeSize(sb, "height", this.mHeight, this.mMinHeight, this.mMaxDimension[1], this.mHeightOverride, this.mMatchConstraintMinHeight, this.mMatchConstraintDefaultHeight, this.mMatchConstraintPercentHeight, this.mWeight[1]);
        serializeDimensionRatio(sb, "dimensionRatio", this.mDimensionRatio, this.mDimensionRatioSide);
        serializeAttribute(sb, "horizontalBias", this.mHorizontalBiasPercent, DEFAULT_BIAS);
        serializeAttribute(sb, "verticalBias", this.mVerticalBiasPercent, DEFAULT_BIAS);
        sb.append("}\n");
        return sb;
    }

    public boolean oppositeDimensionDependsOn(int i) {
        char c = i == 0 ? (char) 1 : (char) 0;
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        return dimensionBehaviourArr[i] == DimensionBehaviour.MATCH_CONSTRAINT && dimensionBehaviourArr[c] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean oppositeDimensionsTied() {
        return this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean hasDimensionOverride() {
        return (this.mWidthOverride == -1 && this.mHeightOverride == -1) ? false : true;
    }

    public ConstraintWidget() {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.mHorizontalRun = null;
        this.mVerticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.mResolvedHasRatio = false;
        this.mMeasureRequested = true;
        this.mOptimizeWrapO = false;
        this.mOptimizeWrapOnResolved = true;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
        this.frame = new WidgetFrame(this);
        this.mResolvedHorizontal = false;
        this.mResolvedVertical = false;
        this.mHorizontalSolvingPass = false;
        this.mVerticalSolvingPass = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mWrapBehaviorInParent = 0;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = Float.NaN;
        this.mHasBaseline = false;
        this.mInVirtualLayout = false;
        this.mLastHorizontalMeasureSpec = 0;
        this.mLastVerticalMeasureSpec = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.mAnchors = new ArrayList<>();
        this.mIsInBarrier = new boolean[2];
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.f36mX = 0;
        this.f37mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mAnimated = false;
        this.mDebugName = null;
        this.mType = null;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        addAnchors();
    }

    public ConstraintWidget(String str) {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.mHorizontalRun = null;
        this.mVerticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.mResolvedHasRatio = false;
        this.mMeasureRequested = true;
        this.mOptimizeWrapO = false;
        this.mOptimizeWrapOnResolved = true;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
        this.frame = new WidgetFrame(this);
        this.mResolvedHorizontal = false;
        this.mResolvedVertical = false;
        this.mHorizontalSolvingPass = false;
        this.mVerticalSolvingPass = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mWrapBehaviorInParent = 0;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = Float.NaN;
        this.mHasBaseline = false;
        this.mInVirtualLayout = false;
        this.mLastHorizontalMeasureSpec = 0;
        this.mLastVerticalMeasureSpec = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.mAnchors = new ArrayList<>();
        this.mIsInBarrier = new boolean[2];
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.f36mX = 0;
        this.f37mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mAnimated = false;
        this.mDebugName = null;
        this.mType = null;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        addAnchors();
        setDebugName(str);
    }

    public ConstraintWidget(int i, int i2, int i3, int i4) {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.mHorizontalRun = null;
        this.mVerticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.mResolvedHasRatio = false;
        this.mMeasureRequested = true;
        this.mOptimizeWrapO = false;
        this.mOptimizeWrapOnResolved = true;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
        this.frame = new WidgetFrame(this);
        this.mResolvedHorizontal = false;
        this.mResolvedVertical = false;
        this.mHorizontalSolvingPass = false;
        this.mVerticalSolvingPass = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mWrapBehaviorInParent = 0;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = Float.NaN;
        this.mHasBaseline = false;
        this.mInVirtualLayout = false;
        this.mLastHorizontalMeasureSpec = 0;
        this.mLastVerticalMeasureSpec = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.mAnchors = new ArrayList<>();
        this.mIsInBarrier = new boolean[2];
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mAnimated = false;
        this.mDebugName = null;
        this.mType = null;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        this.f36mX = i;
        this.f37mY = i2;
        this.mWidth = i3;
        this.mHeight = i4;
        addAnchors();
    }

    public ConstraintWidget(String str, int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4);
        setDebugName(str);
    }

    public ConstraintWidget(int i, int i2) {
        this(0, 0, i, i2);
    }

    public void ensureWidgetRuns() {
        if (this.mHorizontalRun == null) {
            this.mHorizontalRun = new HorizontalWidgetRun(this);
        }
        if (this.mVerticalRun == null) {
            this.mVerticalRun = new VerticalWidgetRun(this);
        }
    }

    public ConstraintWidget(String str, int i, int i2) {
        this(i, i2);
        setDebugName(str);
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mCenter);
        this.mAnchors.add(this.mBaseline);
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public void setParent(ConstraintWidget constraintWidget) {
        this.mParent = constraintWidget;
    }

    public void setWidthWrapContent(boolean z) {
        this.mIsWidthWrapContent = z;
    }

    public boolean isWidthWrapContent() {
        return this.mIsWidthWrapContent;
    }

    public void setHeightWrapContent(boolean z) {
        this.mIsHeightWrapContent = z;
    }

    public boolean isHeightWrapContent() {
        return this.mIsHeightWrapContent;
    }

    public void connectCircularConstraint(ConstraintWidget constraintWidget, float f, int i) {
        immediateConnect(ConstraintAnchor.Type.CENTER, constraintWidget, ConstraintAnchor.Type.CENTER, i, 0);
        this.mCircleConstraintAngle = f;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setVisibility(int i) {
        this.mVisibility = i;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public void setAnimated(boolean z) {
        this.mAnimated = z;
    }

    public boolean isAnimated() {
        return this.mAnimated;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public void setDebugName(String str) {
        this.mDebugName = str;
    }

    public void setDebugSolverName(LinearSystem linearSystem, String str) {
        this.mDebugName = str;
        SolverVariable solverVariableCreateObjectVariable = linearSystem.createObjectVariable(this.mLeft);
        SolverVariable solverVariableCreateObjectVariable2 = linearSystem.createObjectVariable(this.mTop);
        SolverVariable solverVariableCreateObjectVariable3 = linearSystem.createObjectVariable(this.mRight);
        SolverVariable solverVariableCreateObjectVariable4 = linearSystem.createObjectVariable(this.mBottom);
        solverVariableCreateObjectVariable.setName(str + ".left");
        solverVariableCreateObjectVariable2.setName(str + ".top");
        solverVariableCreateObjectVariable3.setName(str + ".right");
        solverVariableCreateObjectVariable4.setName(str + ".bottom");
        linearSystem.createObjectVariable(this.mBaseline).setName(str + ".baseline");
    }

    public void createObjectVariables(LinearSystem linearSystem) {
        linearSystem.createObjectVariable(this.mLeft);
        linearSystem.createObjectVariable(this.mTop);
        linearSystem.createObjectVariable(this.mRight);
        linearSystem.createObjectVariable(this.mBottom);
        if (this.mBaselineDistance > 0) {
            linearSystem.createObjectVariable(this.mBaseline);
        }
    }

    public String toString() {
        return (this.mType != null ? "type: " + this.mType + HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR : "") + (this.mDebugName != null ? "id: " + this.mDebugName + HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR : "") + "(" + this.f36mX + ", " + this.f37mY + ") - (" + this.mWidth + " x " + this.mHeight + ")";
    }

    public int getX() {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget != null && (constraintWidget instanceof ConstraintWidgetContainer)) {
            return ((ConstraintWidgetContainer) constraintWidget).mPaddingLeft + this.f36mX;
        }
        return this.f36mX;
    }

    public int getY() {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget != null && (constraintWidget instanceof ConstraintWidgetContainer)) {
            return ((ConstraintWidgetContainer) constraintWidget).mPaddingTop + this.f37mY;
        }
        return this.f37mY;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getOptimizerWrapWidth() {
        int iMax;
        int i = this.mWidth;
        if (this.mListDimensionBehaviors[0] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i;
        }
        if (this.mMatchConstraintDefaultWidth == 1) {
            iMax = Math.max(this.mMatchConstraintMinWidth, i);
        } else {
            iMax = this.mMatchConstraintMinWidth;
            if (iMax > 0) {
                this.mWidth = iMax;
            } else {
                iMax = 0;
            }
        }
        int i2 = this.mMatchConstraintMaxWidth;
        return (i2 <= 0 || i2 >= iMax) ? iMax : i2;
    }

    public int getOptimizerWrapHeight() {
        int iMax;
        int i = this.mHeight;
        if (this.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i;
        }
        if (this.mMatchConstraintDefaultHeight == 1) {
            iMax = Math.max(this.mMatchConstraintMinHeight, i);
        } else {
            iMax = this.mMatchConstraintMinHeight;
            if (iMax > 0) {
                this.mHeight = iMax;
            } else {
                iMax = 0;
            }
        }
        int i2 = this.mMatchConstraintMaxHeight;
        return (i2 <= 0 || i2 >= iMax) ? iMax : i2;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public int getLength(int i) {
        if (i == 0) {
            return getWidth();
        }
        if (i == 1) {
            return getHeight();
        }
        return 0;
    }

    protected int getRootX() {
        return this.f36mX + this.mOffsetX;
    }

    protected int getRootY() {
        return this.f37mY + this.mOffsetY;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getLeft() {
        return getX();
    }

    public int getTop() {
        return getY();
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public int getHorizontalMargin() {
        ConstraintAnchor constraintAnchor = this.mLeft;
        int i = constraintAnchor != null ? constraintAnchor.mMargin : 0;
        ConstraintAnchor constraintAnchor2 = this.mRight;
        return constraintAnchor2 != null ? i + constraintAnchor2.mMargin : i;
    }

    public int getVerticalMargin() {
        int i = this.mLeft != null ? this.mTop.mMargin : 0;
        return this.mRight != null ? i + this.mBottom.mMargin : i;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public float getVerticalBiasPercent() {
        return this.mVerticalBiasPercent;
    }

    public float getBiasPercent(int i) {
        if (i == 0) {
            return this.mHorizontalBiasPercent;
        }
        if (i == 1) {
            return this.mVerticalBiasPercent;
        }
        return -1.0f;
    }

    public boolean hasBaseline() {
        return this.mHasBaseline;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setX(int i) {
        this.f36mX = i;
    }

    public void setY(int i) {
        this.f37mY = i;
    }

    public void setOrigin(int i, int i2) {
        this.f36mX = i;
        this.f37mY = i2;
    }

    public void setOffset(int i, int i2) {
        this.mOffsetX = i;
        this.mOffsetY = i2;
    }

    /* renamed from: androidx.constraintlayout.core.widgets.ConstraintWidget$1 */
    static /* synthetic */ class C01451 {

        /* renamed from: $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type */
        static final /* synthetic */ int[] f38x6930e354;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f38x6930e354 = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f38x6930e354[ConstraintAnchor.Type.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f38x6930e354[ConstraintAnchor.Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f38x6930e354[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f38x6930e354[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f38x6930e354[ConstraintAnchor.Type.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f38x6930e354[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f38x6930e354[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f38x6930e354[ConstraintAnchor.Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public void setGoneMargin(ConstraintAnchor.Type type, int i) {
        int i2 = C01451.f38x6930e354[type.ordinal()];
        if (i2 == 1) {
            this.mLeft.mGoneMargin = i;
            return;
        }
        if (i2 == 2) {
            this.mTop.mGoneMargin = i;
            return;
        }
        if (i2 == 3) {
            this.mRight.mGoneMargin = i;
        } else if (i2 == 4) {
            this.mBottom.mGoneMargin = i;
        } else {
            if (i2 != 5) {
                return;
            }
            this.mBaseline.mGoneMargin = i;
        }
    }

    public void setWidth(int i) {
        this.mWidth = i;
        int i2 = this.mMinWidth;
        if (i < i2) {
            this.mWidth = i2;
        }
    }

    public void setHeight(int i) {
        this.mHeight = i;
        int i2 = this.mMinHeight;
        if (i < i2) {
            this.mHeight = i2;
        }
    }

    public void setLength(int i, int i2) {
        if (i2 == 0) {
            setWidth(i);
        } else if (i2 == 1) {
            setHeight(i);
        }
    }

    public void setHorizontalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultWidth = i;
        this.mMatchConstraintMinWidth = i2;
        if (i3 == Integer.MAX_VALUE) {
            i3 = 0;
        }
        this.mMatchConstraintMaxWidth = i3;
        this.mMatchConstraintPercentWidth = f;
        if (f <= 0.0f || f >= 1.0f || i != 0) {
            return;
        }
        this.mMatchConstraintDefaultWidth = 2;
    }

    public void setVerticalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultHeight = i;
        this.mMatchConstraintMinHeight = i2;
        if (i3 == Integer.MAX_VALUE) {
            i3 = 0;
        }
        this.mMatchConstraintMaxHeight = i3;
        this.mMatchConstraintPercentHeight = f;
        if (f <= 0.0f || f >= 1.0f || i != 0) {
            return;
        }
        this.mMatchConstraintDefaultHeight = 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0086 A[PHI: r0
      0x0086: PHI (r0v2 int) = (r0v1 int), (r0v0 int), (r0v0 int), (r0v0 int), (r0v0 int), (r0v0 int) binds: [B:46:0x0086, B:36:0x007f, B:24:0x0051, B:26:0x0057, B:28:0x0063, B:30:0x0067] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x0086 -> B:40:0x0087). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void setDimensionRatio(String str) throws NumberFormatException {
        float fAbs;
        int i = 0;
        if (str == null || str.length() == 0) {
            this.mDimensionRatio = 0.0f;
            return;
        }
        int length = str.length();
        int iIndexOf = str.indexOf(44);
        int i2 = 0;
        int i3 = -1;
        if (iIndexOf > 0 && iIndexOf < length - 1) {
            String strSubstring = str.substring(0, iIndexOf);
            if (!strSubstring.equalsIgnoreCase(ExifInterface.LONGITUDE_WEST)) {
                i2 = strSubstring.equalsIgnoreCase("H") ? 1 : -1;
            }
            i3 = i2;
            i2 = iIndexOf + 1;
        }
        int iIndexOf2 = str.indexOf(58);
        if (iIndexOf2 >= 0 && iIndexOf2 < length - 1) {
            String strSubstring2 = str.substring(i2, iIndexOf2);
            String strSubstring3 = str.substring(iIndexOf2 + 1);
            if (strSubstring2.length() > 0 && strSubstring3.length() > 0) {
                float f = Float.parseFloat(strSubstring2);
                float f2 = Float.parseFloat(strSubstring3);
                if (f > 0.0f && f2 > 0.0f) {
                    if (i3 == 1) {
                        fAbs = Math.abs(f2 / f);
                    } else {
                        fAbs = Math.abs(f / f2);
                    }
                }
            }
        } else {
            String strSubstring4 = str.substring(i2);
            fAbs = strSubstring4.length() > 0 ? Float.parseFloat(strSubstring4) : i;
        }
        i = (fAbs > i ? 1 : (fAbs == i ? 0 : -1));
        if (i > 0) {
            this.mDimensionRatio = fAbs;
            this.mDimensionRatioSide = i3;
        }
    }

    public void setDimensionRatio(float f, int i) {
        this.mDimensionRatio = f;
        this.mDimensionRatioSide = i;
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.mDimensionRatioSide;
    }

    public void setHorizontalBiasPercent(float f) {
        this.mHorizontalBiasPercent = f;
    }

    public void setVerticalBiasPercent(float f) {
        this.mVerticalBiasPercent = f;
    }

    public void setMinWidth(int i) {
        if (i < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = i;
        }
    }

    public void setMinHeight(int i) {
        if (i < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = i;
        }
    }

    public void setDimension(int i, int i2) {
        this.mWidth = i;
        int i3 = this.mMinWidth;
        if (i < i3) {
            this.mWidth = i3;
        }
        this.mHeight = i2;
        int i4 = this.mMinHeight;
        if (i2 < i4) {
            this.mHeight = i4;
        }
    }

    public void setFrame(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i3 - i;
        int i8 = i4 - i2;
        this.f36mX = i;
        this.f37mY = i2;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && i7 < (i6 = this.mWidth)) {
            i7 = i6;
        }
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && i8 < (i5 = this.mHeight)) {
            i8 = i5;
        }
        this.mWidth = i7;
        this.mHeight = i8;
        int i9 = this.mMinHeight;
        if (i8 < i9) {
            this.mHeight = i9;
        }
        int i10 = this.mMinWidth;
        if (i7 < i10) {
            this.mWidth = i10;
        }
        if (this.mMatchConstraintMaxWidth > 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT) {
            this.mWidth = Math.min(this.mWidth, this.mMatchConstraintMaxWidth);
        }
        if (this.mMatchConstraintMaxHeight > 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
            this.mHeight = Math.min(this.mHeight, this.mMatchConstraintMaxHeight);
        }
        int i11 = this.mWidth;
        if (i7 != i11) {
            this.mWidthOverride = i11;
        }
        int i12 = this.mHeight;
        if (i8 != i12) {
            this.mHeightOverride = i12;
        }
    }

    public void setFrame(int i, int i2, int i3) {
        if (i3 == 0) {
            setHorizontalDimension(i, i2);
        } else if (i3 == 1) {
            setVerticalDimension(i, i2);
        }
    }

    public void setHorizontalDimension(int i, int i2) {
        this.f36mX = i;
        int i3 = i2 - i;
        this.mWidth = i3;
        int i4 = this.mMinWidth;
        if (i3 < i4) {
            this.mWidth = i4;
        }
    }

    public void setVerticalDimension(int i, int i2) {
        this.f37mY = i;
        int i3 = i2 - i;
        this.mHeight = i3;
        int i4 = this.mMinHeight;
        if (i3 < i4) {
            this.mHeight = i4;
        }
    }

    int getRelativePositioning(int i) {
        if (i == 0) {
            return this.mRelX;
        }
        if (i == 1) {
            return this.mRelY;
        }
        return 0;
    }

    void setRelativePositioning(int i, int i2) {
        if (i2 == 0) {
            this.mRelX = i;
        } else if (i2 == 1) {
            this.mRelY = i;
        }
    }

    public void setBaselineDistance(int i) {
        this.mBaselineDistance = i;
        this.mHasBaseline = i > 0;
    }

    public void setCompanionWidget(Object obj) {
        this.mCompanionWidget = obj;
    }

    public void setContainerItemSkip(int i) {
        if (i >= 0) {
            this.mContainerItemSkip = i;
        } else {
            this.mContainerItemSkip = 0;
        }
    }

    public int getContainerItemSkip() {
        return this.mContainerItemSkip;
    }

    public void setHorizontalWeight(float f) {
        this.mWeight[0] = f;
    }

    public void setVerticalWeight(float f) {
        this.mWeight[1] = f;
    }

    public void setHorizontalChainStyle(int i) {
        this.mHorizontalChainStyle = i;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public void setVerticalChainStyle(int i) {
        this.mVerticalChainStyle = i;
    }

    public int getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public boolean allowedInBarrier() {
        return this.mVisibility != 8;
    }

    public void immediateConnect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i, int i2) {
        getAnchor(type).connect(constraintWidget.getAnchor(type2), i, i2, true);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i) {
        if (constraintAnchor.getOwner() == this) {
            connect(constraintAnchor.getType(), constraintAnchor2.getOwner(), constraintAnchor2.getType(), i);
        }
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2) {
        connect(type, constraintWidget, type2, 0);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i) {
        boolean z;
        if (type == ConstraintAnchor.Type.CENTER) {
            if (type2 == ConstraintAnchor.Type.CENTER) {
                ConstraintAnchor anchor = getAnchor(ConstraintAnchor.Type.LEFT);
                ConstraintAnchor anchor2 = getAnchor(ConstraintAnchor.Type.RIGHT);
                ConstraintAnchor anchor3 = getAnchor(ConstraintAnchor.Type.TOP);
                ConstraintAnchor anchor4 = getAnchor(ConstraintAnchor.Type.BOTTOM);
                boolean z2 = true;
                if ((anchor == null || !anchor.isConnected()) && (anchor2 == null || !anchor2.isConnected())) {
                    connect(ConstraintAnchor.Type.LEFT, constraintWidget, ConstraintAnchor.Type.LEFT, 0);
                    connect(ConstraintAnchor.Type.RIGHT, constraintWidget, ConstraintAnchor.Type.RIGHT, 0);
                    z = true;
                } else {
                    z = false;
                }
                if ((anchor3 == null || !anchor3.isConnected()) && (anchor4 == null || !anchor4.isConnected())) {
                    connect(ConstraintAnchor.Type.TOP, constraintWidget, ConstraintAnchor.Type.TOP, 0);
                    connect(ConstraintAnchor.Type.BOTTOM, constraintWidget, ConstraintAnchor.Type.BOTTOM, 0);
                } else {
                    z2 = false;
                }
                if (z && z2) {
                    getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.CENTER), 0);
                    return;
                } else if (z) {
                    getAnchor(ConstraintAnchor.Type.CENTER_X).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.CENTER_X), 0);
                    return;
                } else {
                    if (z2) {
                        getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.CENTER_Y), 0);
                        return;
                    }
                    return;
                }
            }
            if (type2 == ConstraintAnchor.Type.LEFT || type2 == ConstraintAnchor.Type.RIGHT) {
                connect(ConstraintAnchor.Type.LEFT, constraintWidget, type2, 0);
                connect(ConstraintAnchor.Type.RIGHT, constraintWidget, type2, 0);
                getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintWidget.getAnchor(type2), 0);
                return;
            } else {
                if (type2 == ConstraintAnchor.Type.TOP || type2 == ConstraintAnchor.Type.BOTTOM) {
                    connect(ConstraintAnchor.Type.TOP, constraintWidget, type2, 0);
                    connect(ConstraintAnchor.Type.BOTTOM, constraintWidget, type2, 0);
                    getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintWidget.getAnchor(type2), 0);
                    return;
                }
                return;
            }
        }
        if (type == ConstraintAnchor.Type.CENTER_X && (type2 == ConstraintAnchor.Type.LEFT || type2 == ConstraintAnchor.Type.RIGHT)) {
            ConstraintAnchor anchor5 = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor anchor6 = constraintWidget.getAnchor(type2);
            ConstraintAnchor anchor7 = getAnchor(ConstraintAnchor.Type.RIGHT);
            anchor5.connect(anchor6, 0);
            anchor7.connect(anchor6, 0);
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(anchor6, 0);
            return;
        }
        if (type == ConstraintAnchor.Type.CENTER_Y && (type2 == ConstraintAnchor.Type.TOP || type2 == ConstraintAnchor.Type.BOTTOM)) {
            ConstraintAnchor anchor8 = constraintWidget.getAnchor(type2);
            getAnchor(ConstraintAnchor.Type.TOP).connect(anchor8, 0);
            getAnchor(ConstraintAnchor.Type.BOTTOM).connect(anchor8, 0);
            getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(anchor8, 0);
            return;
        }
        if (type == ConstraintAnchor.Type.CENTER_X && type2 == ConstraintAnchor.Type.CENTER_X) {
            getAnchor(ConstraintAnchor.Type.LEFT).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT), 0);
            getAnchor(ConstraintAnchor.Type.RIGHT).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT), 0);
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(constraintWidget.getAnchor(type2), 0);
            return;
        }
        if (type == ConstraintAnchor.Type.CENTER_Y && type2 == ConstraintAnchor.Type.CENTER_Y) {
            getAnchor(ConstraintAnchor.Type.TOP).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.TOP), 0);
            getAnchor(ConstraintAnchor.Type.BOTTOM).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM), 0);
            getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(constraintWidget.getAnchor(type2), 0);
            return;
        }
        ConstraintAnchor anchor9 = getAnchor(type);
        ConstraintAnchor anchor10 = constraintWidget.getAnchor(type2);
        if (anchor9.isValidConnection(anchor10)) {
            if (type == ConstraintAnchor.Type.BASELINE) {
                ConstraintAnchor anchor11 = getAnchor(ConstraintAnchor.Type.TOP);
                ConstraintAnchor anchor12 = getAnchor(ConstraintAnchor.Type.BOTTOM);
                if (anchor11 != null) {
                    anchor11.reset();
                }
                if (anchor12 != null) {
                    anchor12.reset();
                }
            } else if (type == ConstraintAnchor.Type.TOP || type == ConstraintAnchor.Type.BOTTOM) {
                ConstraintAnchor anchor13 = getAnchor(ConstraintAnchor.Type.BASELINE);
                if (anchor13 != null) {
                    anchor13.reset();
                }
                ConstraintAnchor anchor14 = getAnchor(ConstraintAnchor.Type.CENTER);
                if (anchor14.getTarget() != anchor10) {
                    anchor14.reset();
                }
                ConstraintAnchor opposite = getAnchor(type).getOpposite();
                ConstraintAnchor anchor15 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
                if (anchor15.isConnected()) {
                    opposite.reset();
                    anchor15.reset();
                }
            } else if (type == ConstraintAnchor.Type.LEFT || type == ConstraintAnchor.Type.RIGHT) {
                ConstraintAnchor anchor16 = getAnchor(ConstraintAnchor.Type.CENTER);
                if (anchor16.getTarget() != anchor10) {
                    anchor16.reset();
                }
                ConstraintAnchor opposite2 = getAnchor(type).getOpposite();
                ConstraintAnchor anchor17 = getAnchor(ConstraintAnchor.Type.CENTER_X);
                if (anchor17.isConnected()) {
                    opposite2.reset();
                    anchor17.reset();
                }
            }
            anchor9.connect(anchor10, i);
        }
    }

    public void resetAllConstraints() {
        resetAnchors();
        setVerticalBiasPercent(DEFAULT_BIAS);
        setHorizontalBiasPercent(DEFAULT_BIAS);
    }

    public void resetAnchor(ConstraintAnchor constraintAnchor) {
        if (getParent() != null && (getParent() instanceof ConstraintWidgetContainer) && ((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            return;
        }
        ConstraintAnchor anchor = getAnchor(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor anchor2 = getAnchor(ConstraintAnchor.Type.RIGHT);
        ConstraintAnchor anchor3 = getAnchor(ConstraintAnchor.Type.TOP);
        ConstraintAnchor anchor4 = getAnchor(ConstraintAnchor.Type.BOTTOM);
        ConstraintAnchor anchor5 = getAnchor(ConstraintAnchor.Type.CENTER);
        ConstraintAnchor anchor6 = getAnchor(ConstraintAnchor.Type.CENTER_X);
        ConstraintAnchor anchor7 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
        if (constraintAnchor == anchor5) {
            if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                anchor.reset();
                anchor2.reset();
            }
            if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                anchor3.reset();
                anchor4.reset();
            }
            this.mHorizontalBiasPercent = 0.5f;
            this.mVerticalBiasPercent = 0.5f;
        } else if (constraintAnchor == anchor6) {
            if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget().getOwner() == anchor2.getTarget().getOwner()) {
                anchor.reset();
                anchor2.reset();
            }
            this.mHorizontalBiasPercent = 0.5f;
        } else if (constraintAnchor == anchor7) {
            if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget().getOwner() == anchor4.getTarget().getOwner()) {
                anchor3.reset();
                anchor4.reset();
            }
            this.mVerticalBiasPercent = 0.5f;
        } else if (constraintAnchor == anchor || constraintAnchor == anchor2) {
            if (anchor.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                anchor5.reset();
            }
        } else if ((constraintAnchor == anchor3 || constraintAnchor == anchor4) && anchor3.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
            anchor5.reset();
        }
        constraintAnchor.reset();
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent != null && (parent instanceof ConstraintWidgetContainer) && ((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            return;
        }
        int size = this.mAnchors.size();
        for (int i = 0; i < size; i++) {
            this.mAnchors.get(i).reset();
        }
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type type) {
        switch (C01451.f38x6930e354[type.ordinal()]) {
            case 1:
                return this.mLeft;
            case 2:
                return this.mTop;
            case 3:
                return this.mRight;
            case 4:
                return this.mBottom;
            case 5:
                return this.mBaseline;
            case 6:
                return this.mCenter;
            case 7:
                return this.mCenterX;
            case 8:
                return this.mCenterY;
            case 9:
                return null;
            default:
                throw new AssertionError(type.name());
        }
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public DimensionBehaviour getDimensionBehaviour(int i) {
        if (i == 0) {
            return getHorizontalDimensionBehaviour();
        }
        if (i == 1) {
            return getVerticalDimensionBehaviour();
        }
        return null;
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[0] = dimensionBehaviour;
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[1] = dimensionBehaviour;
    }

    public boolean isInHorizontalChain() {
        if (this.mLeft.mTarget == null || this.mLeft.mTarget.mTarget != this.mLeft) {
            return this.mRight.mTarget != null && this.mRight.mTarget.mTarget == this.mRight;
        }
        return true;
    }

    public ConstraintWidget getPreviousChainMember(int i) {
        if (i == 0) {
            if (this.mLeft.mTarget == null) {
                return null;
            }
            ConstraintAnchor constraintAnchor = this.mLeft.mTarget.mTarget;
            ConstraintAnchor constraintAnchor2 = this.mLeft;
            if (constraintAnchor == constraintAnchor2) {
                return constraintAnchor2.mTarget.mOwner;
            }
            return null;
        }
        if (i != 1 || this.mTop.mTarget == null) {
            return null;
        }
        ConstraintAnchor constraintAnchor3 = this.mTop.mTarget.mTarget;
        ConstraintAnchor constraintAnchor4 = this.mTop;
        if (constraintAnchor3 == constraintAnchor4) {
            return constraintAnchor4.mTarget.mOwner;
        }
        return null;
    }

    public ConstraintWidget getNextChainMember(int i) {
        if (i == 0) {
            if (this.mRight.mTarget == null) {
                return null;
            }
            ConstraintAnchor constraintAnchor = this.mRight.mTarget.mTarget;
            ConstraintAnchor constraintAnchor2 = this.mRight;
            if (constraintAnchor == constraintAnchor2) {
                return constraintAnchor2.mTarget.mOwner;
            }
            return null;
        }
        if (i != 1 || this.mBottom.mTarget == null) {
            return null;
        }
        ConstraintAnchor constraintAnchor3 = this.mBottom.mTarget.mTarget;
        ConstraintAnchor constraintAnchor4 = this.mBottom;
        if (constraintAnchor3 == constraintAnchor4) {
            return constraintAnchor4.mTarget.mOwner;
        }
        return null;
    }

    public ConstraintWidget getHorizontalChainControlWidget() {
        if (!isInHorizontalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget = this;
        ConstraintWidget constraintWidget2 = null;
        while (constraintWidget2 == null && constraintWidget != null) {
            ConstraintAnchor anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor target = anchor == null ? null : anchor.getTarget();
            ConstraintWidget owner = target == null ? null : target.getOwner();
            if (owner == getParent()) {
                return constraintWidget;
            }
            ConstraintAnchor target2 = owner == null ? null : owner.getAnchor(ConstraintAnchor.Type.RIGHT).getTarget();
            if (target2 == null || target2.getOwner() == constraintWidget) {
                constraintWidget = owner;
            } else {
                constraintWidget2 = constraintWidget;
            }
        }
        return constraintWidget2;
    }

    public boolean isInVerticalChain() {
        if (this.mTop.mTarget == null || this.mTop.mTarget.mTarget != this.mTop) {
            return this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == this.mBottom;
        }
        return true;
    }

    public ConstraintWidget getVerticalChainControlWidget() {
        if (!isInVerticalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget = this;
        ConstraintWidget constraintWidget2 = null;
        while (constraintWidget2 == null && constraintWidget != null) {
            ConstraintAnchor anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.TOP);
            ConstraintAnchor target = anchor == null ? null : anchor.getTarget();
            ConstraintWidget owner = target == null ? null : target.getOwner();
            if (owner == getParent()) {
                return constraintWidget;
            }
            ConstraintAnchor target2 = owner == null ? null : owner.getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget();
            if (target2 == null || target2.getOwner() == constraintWidget) {
                constraintWidget = owner;
            } else {
                constraintWidget2 = constraintWidget;
            }
        }
        return constraintWidget2;
    }

    private boolean isChainHead(int i) {
        int i2 = i * 2;
        if (this.mListAnchors[i2].mTarget != null) {
            ConstraintAnchor constraintAnchor = this.mListAnchors[i2].mTarget.mTarget;
            ConstraintAnchor[] constraintAnchorArr = this.mListAnchors;
            if (constraintAnchor != constraintAnchorArr[i2]) {
                int i3 = i2 + 1;
                if (constraintAnchorArr[i3].mTarget != null && this.mListAnchors[i3].mTarget.mTarget == this.mListAnchors[i3]) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0320  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0340  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0460  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x04bb  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x04cd  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x04cf  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x04d2  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x056f  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x0572  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x05b3  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x05bb  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x05e9  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x060a  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x0615  */
    /* JADX WARN: Removed duplicated region for block: B:326:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void addToSolver(LinearSystem linearSystem, boolean z) {
        boolean z2;
        boolean z3;
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2;
        boolean z4;
        boolean z5;
        int i;
        int i2;
        int i3;
        int i4;
        boolean z6;
        int i5;
        boolean z7;
        boolean z8;
        boolean z9;
        SolverVariable solverVariable;
        SolverVariable solverVariable2;
        SolverVariable solverVariable3;
        SolverVariable solverVariable4;
        SolverVariable solverVariable5;
        LinearSystem linearSystem2;
        SolverVariable solverVariable6;
        SolverVariable solverVariable7;
        SolverVariable solverVariable8;
        int i6;
        int i7;
        ?? r11;
        int i8;
        SolverVariable solverVariable9;
        SolverVariable solverVariable10;
        boolean z10;
        VerticalWidgetRun verticalWidgetRun;
        boolean z11;
        HorizontalWidgetRun horizontalWidgetRun;
        int i9;
        boolean zIsInHorizontalChain;
        boolean zIsInVerticalChain;
        HorizontalWidgetRun horizontalWidgetRun2;
        SolverVariable solverVariableCreateObjectVariable = linearSystem.createObjectVariable(this.mLeft);
        SolverVariable solverVariableCreateObjectVariable2 = linearSystem.createObjectVariable(this.mRight);
        SolverVariable solverVariableCreateObjectVariable3 = linearSystem.createObjectVariable(this.mTop);
        SolverVariable solverVariableCreateObjectVariable4 = linearSystem.createObjectVariable(this.mBottom);
        SolverVariable solverVariableCreateObjectVariable5 = linearSystem.createObjectVariable(this.mBaseline);
        ConstraintWidget constraintWidget3 = this.mParent;
        if (constraintWidget3 == null) {
            z2 = false;
            z3 = false;
        } else {
            boolean z12 = constraintWidget3 != null && constraintWidget3.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT;
            ConstraintWidget constraintWidget4 = this.mParent;
            boolean z13 = constraintWidget4 != null && constraintWidget4.mListDimensionBehaviors[1] == DimensionBehaviour.WRAP_CONTENT;
            int i10 = this.mWrapBehaviorInParent;
            if (i10 == 1) {
                z2 = z12;
                z3 = false;
            } else if (i10 == 2) {
                z3 = z13;
                z2 = false;
            } else if (i10 != 3) {
                z2 = z12;
                z3 = z13;
            }
        }
        if (this.mVisibility == 8 && !this.mAnimated && !hasDependencies()) {
            boolean[] zArr = this.mIsInBarrier;
            if (!zArr[0] && !zArr[1]) {
                return;
            }
        }
        boolean z14 = this.mResolvedHorizontal;
        if (z14 || this.mResolvedVertical) {
            if (z14) {
                linearSystem.addEquality(solverVariableCreateObjectVariable, this.f36mX);
                linearSystem.addEquality(solverVariableCreateObjectVariable2, this.f36mX + this.mWidth);
                if (z2 && (constraintWidget2 = this.mParent) != null) {
                    if (this.mOptimizeWrapOnResolved) {
                        ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) constraintWidget2;
                        constraintWidgetContainer.addHorizontalWrapMinVariable(this.mLeft);
                        constraintWidgetContainer.addHorizontalWrapMaxVariable(this.mRight);
                    } else {
                        linearSystem.addGreaterThan(linearSystem.createObjectVariable(constraintWidget2.mRight), solverVariableCreateObjectVariable2, 0, 5);
                    }
                }
            }
            if (this.mResolvedVertical) {
                linearSystem.addEquality(solverVariableCreateObjectVariable3, this.f37mY);
                linearSystem.addEquality(solverVariableCreateObjectVariable4, this.f37mY + this.mHeight);
                if (this.mBaseline.hasDependents()) {
                    linearSystem.addEquality(solverVariableCreateObjectVariable5, this.f37mY + this.mBaselineDistance);
                }
                if (z3 && (constraintWidget = this.mParent) != null) {
                    if (this.mOptimizeWrapOnResolved) {
                        ConstraintWidgetContainer constraintWidgetContainer2 = (ConstraintWidgetContainer) constraintWidget;
                        constraintWidgetContainer2.addVerticalWrapMinVariable(this.mTop);
                        constraintWidgetContainer2.addVerticalWrapMaxVariable(this.mBottom);
                    } else {
                        linearSystem.addGreaterThan(linearSystem.createObjectVariable(constraintWidget.mBottom), solverVariableCreateObjectVariable4, 0, 5);
                    }
                }
            }
            if (this.mResolvedHorizontal && this.mResolvedVertical) {
                this.mResolvedHorizontal = false;
                this.mResolvedVertical = false;
                return;
            }
        }
        if (LinearSystem.sMetrics != null) {
            LinearSystem.sMetrics.widgets++;
        }
        if (z && (horizontalWidgetRun2 = this.mHorizontalRun) != null && this.mVerticalRun != null && horizontalWidgetRun2.start.resolved && this.mHorizontalRun.end.resolved && this.mVerticalRun.start.resolved && this.mVerticalRun.end.resolved) {
            if (LinearSystem.sMetrics != null) {
                LinearSystem.sMetrics.graphSolved++;
            }
            linearSystem.addEquality(solverVariableCreateObjectVariable, this.mHorizontalRun.start.value);
            linearSystem.addEquality(solverVariableCreateObjectVariable2, this.mHorizontalRun.end.value);
            linearSystem.addEquality(solverVariableCreateObjectVariable3, this.mVerticalRun.start.value);
            linearSystem.addEquality(solverVariableCreateObjectVariable4, this.mVerticalRun.end.value);
            linearSystem.addEquality(solverVariableCreateObjectVariable5, this.mVerticalRun.baseline.value);
            if (this.mParent != null) {
                if (z2 && this.isTerminalWidget[0] && !isInHorizontalChain()) {
                    linearSystem.addGreaterThan(linearSystem.createObjectVariable(this.mParent.mRight), solverVariableCreateObjectVariable2, 0, 8);
                }
                if (z3 && this.isTerminalWidget[1] && !isInVerticalChain()) {
                    linearSystem.addGreaterThan(linearSystem.createObjectVariable(this.mParent.mBottom), solverVariableCreateObjectVariable4, 0, 8);
                }
            }
            this.mResolvedHorizontal = false;
            this.mResolvedVertical = false;
            return;
        }
        if (LinearSystem.sMetrics != null) {
            LinearSystem.sMetrics.linearSolved++;
        }
        if (this.mParent != null) {
            if (isChainHead(0)) {
                ((ConstraintWidgetContainer) this.mParent).addChain(this, 0);
                zIsInHorizontalChain = true;
            } else {
                zIsInHorizontalChain = isInHorizontalChain();
            }
            if (isChainHead(1)) {
                ((ConstraintWidgetContainer) this.mParent).addChain(this, 1);
                zIsInVerticalChain = true;
            } else {
                zIsInVerticalChain = isInVerticalChain();
            }
            if (!zIsInHorizontalChain && z2 && this.mVisibility != 8 && this.mLeft.mTarget == null && this.mRight.mTarget == null) {
                linearSystem.addGreaterThan(linearSystem.createObjectVariable(this.mParent.mRight), solverVariableCreateObjectVariable2, 0, 1);
            }
            if (!zIsInVerticalChain && z3 && this.mVisibility != 8 && this.mTop.mTarget == null && this.mBottom.mTarget == null && this.mBaseline == null) {
                linearSystem.addGreaterThan(linearSystem.createObjectVariable(this.mParent.mBottom), solverVariableCreateObjectVariable4, 0, 1);
            }
            z5 = zIsInHorizontalChain;
            z4 = zIsInVerticalChain;
        } else {
            z4 = false;
            z5 = false;
        }
        int i11 = this.mWidth;
        int i12 = this.mMinWidth;
        if (i11 < i12) {
            i11 = i12;
        }
        int i13 = this.mHeight;
        int i14 = this.mMinHeight;
        if (i13 < i14) {
            i13 = i14;
        }
        boolean z15 = this.mListDimensionBehaviors[0] != DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z16 = this.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT;
        this.mResolvedDimensionRatioSide = this.mDimensionRatioSide;
        float f = this.mDimensionRatio;
        this.mResolvedDimensionRatio = f;
        int i15 = this.mMatchConstraintDefaultWidth;
        int i16 = this.mMatchConstraintDefaultHeight;
        int i17 = i11;
        if (f <= 0.0f || this.mVisibility == 8) {
            i = i13;
            i2 = i16;
            i3 = i15;
        } else {
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && i15 == 0) {
                i15 = 3;
            }
            if (this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && i16 == 0) {
                i16 = 3;
            }
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && i15 == 3 && i16 == 3) {
                setupDimensionRatio(z2, z3, z15, z16);
            } else if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && i15 == 3) {
                this.mResolvedDimensionRatioSide = 0;
                i4 = (int) (this.mResolvedDimensionRatio * this.mHeight);
                i = i13;
                i2 = i16;
                if (this.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT) {
                    i3 = 4;
                    z6 = false;
                    int[] iArr = this.mResolvedMatchConstraintDefault;
                    iArr[0] = i3;
                    iArr[1] = i2;
                    this.mResolvedHasRatio = z6;
                    if (!z6) {
                        int i18 = this.mResolvedDimensionRatioSide;
                        i5 = -1;
                        boolean z17 = i18 == 0 || i18 == -1;
                        boolean z18 = z6 && ((i9 = this.mResolvedDimensionRatioSide) == 1 || i9 == i5);
                        boolean z19 = this.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT && (this instanceof ConstraintWidgetContainer);
                        int i19 = z19 ? 0 : i4;
                        boolean z20 = !this.mCenter.isConnected();
                        boolean[] zArr2 = this.mIsInBarrier;
                        boolean z21 = zArr2[0];
                        boolean z22 = zArr2[1];
                        if (this.mHorizontalResolution == 2 || this.mResolvedHorizontal) {
                            z7 = z2;
                            z8 = z3;
                            z9 = z6;
                            solverVariable = solverVariableCreateObjectVariable5;
                            solverVariable2 = solverVariableCreateObjectVariable4;
                            solverVariable3 = solverVariableCreateObjectVariable3;
                            solverVariable4 = solverVariableCreateObjectVariable2;
                            solverVariable5 = solverVariableCreateObjectVariable;
                        } else if (z && (horizontalWidgetRun = this.mHorizontalRun) != null && horizontalWidgetRun.start.resolved && this.mHorizontalRun.end.resolved) {
                            if (z) {
                                linearSystem.addEquality(solverVariableCreateObjectVariable, this.mHorizontalRun.start.value);
                                linearSystem.addEquality(solverVariableCreateObjectVariable2, this.mHorizontalRun.end.value);
                                if (this.mParent != null && z2 && this.isTerminalWidget[0] && !isInHorizontalChain()) {
                                    linearSystem.addGreaterThan(linearSystem.createObjectVariable(this.mParent.mRight), solverVariableCreateObjectVariable2, 0, 8);
                                }
                            }
                            z7 = z2;
                            z8 = z3;
                            z9 = z6;
                            solverVariable = solverVariableCreateObjectVariable5;
                            solverVariable2 = solverVariableCreateObjectVariable4;
                            solverVariable3 = solverVariableCreateObjectVariable3;
                            solverVariable4 = solverVariableCreateObjectVariable2;
                            solverVariable5 = solverVariableCreateObjectVariable;
                        } else {
                            ConstraintWidget constraintWidget5 = this.mParent;
                            SolverVariable solverVariableCreateObjectVariable6 = constraintWidget5 != null ? linearSystem.createObjectVariable(constraintWidget5.mRight) : null;
                            ConstraintWidget constraintWidget6 = this.mParent;
                            SolverVariable solverVariableCreateObjectVariable7 = constraintWidget6 != null ? linearSystem.createObjectVariable(constraintWidget6.mLeft) : null;
                            boolean z23 = this.isTerminalWidget[0];
                            DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
                            z7 = z2;
                            z8 = z3;
                            z9 = z6;
                            solverVariable = solverVariableCreateObjectVariable5;
                            solverVariable2 = solverVariableCreateObjectVariable4;
                            solverVariable3 = solverVariableCreateObjectVariable3;
                            solverVariable4 = solverVariableCreateObjectVariable2;
                            solverVariable5 = solverVariableCreateObjectVariable;
                            applyConstraints(linearSystem, true, z2, z3, z23, solverVariableCreateObjectVariable7, solverVariableCreateObjectVariable6, dimensionBehaviourArr[0], z19, this.mLeft, this.mRight, this.f36mX, i19, this.mMinWidth, this.mMaxDimension[0], this.mHorizontalBiasPercent, z17, dimensionBehaviourArr[1] == DimensionBehaviour.MATCH_CONSTRAINT, z5, z4, z21, i3, i2, this.mMatchConstraintMinWidth, this.mMatchConstraintMaxWidth, this.mMatchConstraintPercentWidth, z20);
                        }
                        if (z && (verticalWidgetRun = this.mVerticalRun) != null && verticalWidgetRun.start.resolved && this.mVerticalRun.end.resolved) {
                            linearSystem2 = linearSystem;
                            solverVariable8 = solverVariable3;
                            linearSystem2.addEquality(solverVariable8, this.mVerticalRun.start.value);
                            solverVariable7 = solverVariable2;
                            linearSystem2.addEquality(solverVariable7, this.mVerticalRun.end.value);
                            solverVariable6 = solverVariable;
                            linearSystem2.addEquality(solverVariable6, this.mVerticalRun.baseline.value);
                            ConstraintWidget constraintWidget7 = this.mParent;
                            if (constraintWidget7 == null || z4 || !z8) {
                                i6 = 8;
                                i7 = 0;
                                z11 = true;
                            } else {
                                z11 = true;
                                z11 = true;
                                if (this.isTerminalWidget[1]) {
                                    i6 = 8;
                                    i7 = 0;
                                    linearSystem2.addGreaterThan(linearSystem2.createObjectVariable(constraintWidget7.mBottom), solverVariable7, 0, 8);
                                } else {
                                    i6 = 8;
                                    i7 = 0;
                                }
                            }
                            i8 = i7;
                            r11 = z11;
                        } else {
                            linearSystem2 = linearSystem;
                            solverVariable6 = solverVariable;
                            solverVariable7 = solverVariable2;
                            solverVariable8 = solverVariable3;
                            i6 = 8;
                            i7 = 0;
                            r11 = 1;
                            i8 = 1;
                        }
                        if ((this.mVerticalResolution == 2 ? i7 : i8) == 0 || this.mResolvedVertical) {
                            solverVariable9 = solverVariable7;
                            solverVariable10 = solverVariable8;
                        } else {
                            boolean z24 = (this.mListDimensionBehaviors[r11] == DimensionBehaviour.WRAP_CONTENT && (this instanceof ConstraintWidgetContainer)) ? r11 : i7;
                            if (z24) {
                                i = i7;
                            }
                            ConstraintWidget constraintWidget8 = this.mParent;
                            SolverVariable solverVariableCreateObjectVariable8 = constraintWidget8 != null ? linearSystem2.createObjectVariable(constraintWidget8.mBottom) : null;
                            ConstraintWidget constraintWidget9 = this.mParent;
                            SolverVariable solverVariableCreateObjectVariable9 = constraintWidget9 != null ? linearSystem2.createObjectVariable(constraintWidget9.mTop) : null;
                            if (this.mBaselineDistance > 0 || this.mVisibility == i6) {
                                if (this.mBaseline.mTarget != null) {
                                    linearSystem2.addEquality(solverVariable6, solverVariable8, getBaselineDistance(), i6);
                                    linearSystem2.addEquality(solverVariable6, linearSystem2.createObjectVariable(this.mBaseline.mTarget), this.mBaseline.getMargin(), i6);
                                    if (z8) {
                                        linearSystem2.addGreaterThan(solverVariableCreateObjectVariable8, linearSystem2.createObjectVariable(this.mBottom), i7, 5);
                                    }
                                    z10 = i7;
                                    boolean z25 = this.isTerminalWidget[r11];
                                    DimensionBehaviour[] dimensionBehaviourArr2 = this.mListDimensionBehaviors;
                                    solverVariable9 = solverVariable7;
                                    solverVariable10 = solverVariable8;
                                    applyConstraints(linearSystem, false, z8, z7, z25, solverVariableCreateObjectVariable9, solverVariableCreateObjectVariable8, dimensionBehaviourArr2[r11], z24, this.mTop, this.mBottom, this.f37mY, i, this.mMinHeight, this.mMaxDimension[r11], this.mVerticalBiasPercent, z18, dimensionBehaviourArr2[0] == DimensionBehaviour.MATCH_CONSTRAINT, z4, z5, z22, i2, i3, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight, this.mMatchConstraintPercentHeight, z10);
                                } else {
                                    if (this.mVisibility == i6) {
                                        linearSystem2.addEquality(solverVariable6, solverVariable8, this.mBaseline.getMargin(), i6);
                                    } else {
                                        linearSystem2.addEquality(solverVariable6, solverVariable8, getBaselineDistance(), i6);
                                    }
                                    z10 = z20;
                                    boolean z252 = this.isTerminalWidget[r11];
                                    DimensionBehaviour[] dimensionBehaviourArr22 = this.mListDimensionBehaviors;
                                    solverVariable9 = solverVariable7;
                                    solverVariable10 = solverVariable8;
                                    applyConstraints(linearSystem, false, z8, z7, z252, solverVariableCreateObjectVariable9, solverVariableCreateObjectVariable8, dimensionBehaviourArr22[r11], z24, this.mTop, this.mBottom, this.f37mY, i, this.mMinHeight, this.mMaxDimension[r11], this.mVerticalBiasPercent, z18, dimensionBehaviourArr22[0] == DimensionBehaviour.MATCH_CONSTRAINT, z4, z5, z22, i2, i3, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight, this.mMatchConstraintPercentHeight, z10);
                                }
                            } else {
                                z10 = z20;
                                boolean z2522 = this.isTerminalWidget[r11];
                                DimensionBehaviour[] dimensionBehaviourArr222 = this.mListDimensionBehaviors;
                                solverVariable9 = solverVariable7;
                                solverVariable10 = solverVariable8;
                                applyConstraints(linearSystem, false, z8, z7, z2522, solverVariableCreateObjectVariable9, solverVariableCreateObjectVariable8, dimensionBehaviourArr222[r11], z24, this.mTop, this.mBottom, this.f37mY, i, this.mMinHeight, this.mMaxDimension[r11], this.mVerticalBiasPercent, z18, dimensionBehaviourArr222[0] == DimensionBehaviour.MATCH_CONSTRAINT, z4, z5, z22, i2, i3, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight, this.mMatchConstraintPercentHeight, z10);
                            }
                        }
                        if (z9) {
                            if (this.mResolvedDimensionRatioSide == 1) {
                                linearSystem.addRatio(solverVariable9, solverVariable10, solverVariable4, solverVariable5, this.mResolvedDimensionRatio, 8);
                            } else {
                                linearSystem.addRatio(solverVariable4, solverVariable5, solverVariable9, solverVariable10, this.mResolvedDimensionRatio, 8);
                            }
                        }
                        if (this.mCenter.isConnected()) {
                            linearSystem.addCenterPoint(this, this.mCenter.getTarget().getOwner(), (float) Math.toRadians(this.mCircleConstraintAngle + 90.0f), this.mCenter.getMargin());
                        }
                        this.mResolvedHorizontal = false;
                        this.mResolvedVertical = false;
                        if (LinearSystem.sMetrics != null) {
                            LinearSystem.sMetrics.mEquations = linearSystem.getNumEquations();
                            LinearSystem.sMetrics.mVariables = linearSystem.getNumVariables();
                            return;
                        }
                        return;
                    }
                    i5 = -1;
                    if (z6) {
                    }
                    if (this.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT) {
                    }
                    if (z19) {
                    }
                    boolean z202 = !this.mCenter.isConnected();
                    boolean[] zArr22 = this.mIsInBarrier;
                    boolean z212 = zArr22[0];
                    boolean z222 = zArr22[1];
                    if (this.mHorizontalResolution == 2) {
                        z7 = z2;
                        z8 = z3;
                        z9 = z6;
                        solverVariable = solverVariableCreateObjectVariable5;
                        solverVariable2 = solverVariableCreateObjectVariable4;
                        solverVariable3 = solverVariableCreateObjectVariable3;
                        solverVariable4 = solverVariableCreateObjectVariable2;
                        solverVariable5 = solverVariableCreateObjectVariable;
                    }
                    if (z) {
                        linearSystem2 = linearSystem;
                        solverVariable6 = solverVariable;
                        solverVariable7 = solverVariable2;
                        solverVariable8 = solverVariable3;
                        i6 = 8;
                        i7 = 0;
                        r11 = 1;
                        i8 = 1;
                    }
                    if ((this.mVerticalResolution == 2 ? i7 : i8) == 0) {
                        solverVariable9 = solverVariable7;
                        solverVariable10 = solverVariable8;
                    }
                    if (z9) {
                    }
                    if (this.mCenter.isConnected()) {
                    }
                    this.mResolvedHorizontal = false;
                    this.mResolvedVertical = false;
                    if (LinearSystem.sMetrics != null) {
                    }
                } else {
                    i3 = i15;
                    z6 = true;
                    int[] iArr2 = this.mResolvedMatchConstraintDefault;
                    iArr2[0] = i3;
                    iArr2[1] = i2;
                    this.mResolvedHasRatio = z6;
                    if (!z6) {
                    }
                    if (z6) {
                    }
                    if (this.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT) {
                    }
                    if (z19) {
                    }
                    boolean z2022 = !this.mCenter.isConnected();
                    boolean[] zArr222 = this.mIsInBarrier;
                    boolean z2122 = zArr222[0];
                    boolean z2222 = zArr222[1];
                    if (this.mHorizontalResolution == 2) {
                    }
                    if (z) {
                    }
                    if ((this.mVerticalResolution == 2 ? i7 : i8) == 0) {
                    }
                    if (z9) {
                    }
                    if (this.mCenter.isConnected()) {
                    }
                    this.mResolvedHorizontal = false;
                    this.mResolvedVertical = false;
                    if (LinearSystem.sMetrics != null) {
                    }
                }
            } else if (this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && i16 == 3) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
                i13 = (int) (this.mResolvedDimensionRatio * this.mWidth);
                if (this.mListDimensionBehaviors[0] != DimensionBehaviour.MATCH_CONSTRAINT) {
                    i = i13;
                    i3 = i15;
                    i2 = 4;
                }
            }
            i = i13;
            i2 = i16;
            i3 = i15;
            i4 = i17;
            z6 = true;
            int[] iArr22 = this.mResolvedMatchConstraintDefault;
            iArr22[0] = i3;
            iArr22[1] = i2;
            this.mResolvedHasRatio = z6;
            if (!z6) {
            }
            if (z6) {
            }
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT) {
            }
            if (z19) {
            }
            boolean z20222 = !this.mCenter.isConnected();
            boolean[] zArr2222 = this.mIsInBarrier;
            boolean z21222 = zArr2222[0];
            boolean z22222 = zArr2222[1];
            if (this.mHorizontalResolution == 2) {
            }
            if (z) {
            }
            if ((this.mVerticalResolution == 2 ? i7 : i8) == 0) {
            }
            if (z9) {
            }
            if (this.mCenter.isConnected()) {
            }
            this.mResolvedHorizontal = false;
            this.mResolvedVertical = false;
            if (LinearSystem.sMetrics != null) {
            }
        }
        i4 = i17;
        z6 = false;
        int[] iArr222 = this.mResolvedMatchConstraintDefault;
        iArr222[0] = i3;
        iArr222[1] = i2;
        this.mResolvedHasRatio = z6;
        if (!z6) {
        }
        if (z6) {
        }
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT) {
        }
        if (z19) {
        }
        boolean z202222 = !this.mCenter.isConnected();
        boolean[] zArr22222 = this.mIsInBarrier;
        boolean z212222 = zArr22222[0];
        boolean z222222 = zArr22222[1];
        if (this.mHorizontalResolution == 2) {
        }
        if (z) {
        }
        if ((this.mVerticalResolution == 2 ? i7 : i8) == 0) {
        }
        if (z9) {
        }
        if (this.mCenter.isConnected()) {
        }
        this.mResolvedHorizontal = false;
        this.mResolvedVertical = false;
        if (LinearSystem.sMetrics != null) {
        }
    }

    boolean addFirst() {
        return (this instanceof VirtualLayout) || (this instanceof Guideline);
    }

    public void setupDimensionRatio(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z3 && !z4) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z3 && z4) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
            }
        }
        if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.mResolvedDimensionRatioSide = 1;
        } else if (this.mResolvedDimensionRatioSide == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
            this.mResolvedDimensionRatioSide = 0;
        }
        if (this.mResolvedDimensionRatioSide == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            int i = this.mMatchConstraintMinWidth;
            if (i > 0 && this.mMatchConstraintMinHeight == 0) {
                this.mResolvedDimensionRatioSide = 0;
            } else {
                if (i != 0 || this.mMatchConstraintMinHeight <= 0) {
                    return;
                }
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:236:0x03d3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:239:0x03dc  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x03e0  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0422  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x043b  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x045d  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0475  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x047b  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x04c8  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x04da A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:324:0x04f5  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x0500 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:378:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void applyConstraints(LinearSystem linearSystem, boolean z, boolean z2, boolean z3, boolean z4, SolverVariable solverVariable, SolverVariable solverVariable2, DimensionBehaviour dimensionBehaviour, boolean z5, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i, int i2, int i3, int i4, float f, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, int i5, int i6, int i7, int i8, float f2, boolean z11) {
        int i9;
        SolverVariable solverVariable3;
        boolean z12;
        boolean z13;
        boolean z14;
        int iMin;
        boolean z15;
        int i10;
        int i11;
        int i12;
        SolverVariable solverVariable4;
        SolverVariable solverVariable5;
        int i13;
        SolverVariable solverVariable6;
        int i14;
        boolean z16;
        boolean z17;
        SolverVariable solverVariableCreateObjectVariable;
        SolverVariable solverVariableCreateObjectVariable2;
        SolverVariable solverVariable7;
        SolverVariable solverVariable8;
        SolverVariable solverVariable9;
        int i15;
        SolverVariable solverVariable10;
        int i16;
        int i17;
        int i18;
        int i19;
        SolverVariable solverVariable11;
        SolverVariable solverVariable12;
        int margin;
        int i20;
        int i21;
        int i22;
        boolean z18;
        boolean z19;
        boolean z20;
        boolean z21;
        ConstraintWidget constraintWidget;
        SolverVariable solverVariable13;
        int i23;
        ConstraintWidget constraintWidget2;
        SolverVariable solverVariable14;
        boolean z22;
        SolverVariable solverVariable15;
        ConstraintWidget constraintWidget3;
        int iMin2;
        int i24;
        int margin2;
        int i25;
        boolean z23;
        int i26;
        int i27;
        int i28;
        boolean z24;
        int i29;
        boolean z25;
        ConstraintWidget constraintWidget4;
        int i30;
        ConstraintWidget constraintWidget5;
        SolverVariable solverVariableCreateObjectVariable3 = linearSystem.createObjectVariable(constraintAnchor);
        SolverVariable solverVariableCreateObjectVariable4 = linearSystem.createObjectVariable(constraintAnchor2);
        SolverVariable solverVariableCreateObjectVariable5 = linearSystem.createObjectVariable(constraintAnchor.getTarget());
        SolverVariable solverVariableCreateObjectVariable6 = linearSystem.createObjectVariable(constraintAnchor2.getTarget());
        if (LinearSystem.getMetrics() != null) {
            LinearSystem.getMetrics().nonresolvedWidgets++;
        }
        boolean zIsConnected = constraintAnchor.isConnected();
        boolean zIsConnected2 = constraintAnchor2.isConnected();
        boolean zIsConnected3 = this.mCenter.isConnected();
        int i31 = zIsConnected2 ? (zIsConnected ? 1 : 0) + 1 : zIsConnected ? 1 : 0;
        if (zIsConnected3) {
            i31++;
        }
        if (z6) {
            solverVariable3 = solverVariableCreateObjectVariable6;
            i9 = 3;
        } else {
            i9 = i5;
            solverVariable3 = solverVariableCreateObjectVariable6;
        }
        int iOrdinal = dimensionBehaviour.ordinal();
        boolean z26 = (iOrdinal == 0 || iOrdinal == 1 || iOrdinal != 2 || i9 == 4) ? false : true;
        int i32 = this.mWidthOverride;
        if (i32 == -1 || !z) {
            i32 = i2;
            z12 = z26;
        } else {
            this.mWidthOverride = -1;
            z12 = false;
        }
        int i33 = this.mHeightOverride;
        if (i33 == -1 || z) {
            z13 = z12;
        } else {
            this.mHeightOverride = -1;
            i32 = i33;
            z13 = false;
        }
        int i34 = i32;
        if (this.mVisibility == 8) {
            iMin = 0;
            z14 = false;
        } else {
            z14 = z13;
            iMin = i34;
        }
        if (z11) {
            if (!zIsConnected && !zIsConnected2 && !zIsConnected3) {
                linearSystem.addEquality(solverVariableCreateObjectVariable3, i);
            } else if (zIsConnected && !zIsConnected2) {
                z15 = zIsConnected2;
                i10 = 8;
                linearSystem.addEquality(solverVariableCreateObjectVariable3, solverVariableCreateObjectVariable5, constraintAnchor.getMargin(), 8);
            }
            z15 = zIsConnected2;
            i10 = 8;
        } else {
            z15 = zIsConnected2;
            i10 = 8;
        }
        if (!z14) {
            if (z5) {
                linearSystem.addEquality(solverVariableCreateObjectVariable4, solverVariableCreateObjectVariable3, 0, 3);
                if (i3 > 0) {
                    linearSystem.addGreaterThan(solverVariableCreateObjectVariable4, solverVariableCreateObjectVariable3, i3, 8);
                }
                if (i4 < Integer.MAX_VALUE) {
                    linearSystem.addLowerThan(solverVariableCreateObjectVariable4, solverVariableCreateObjectVariable3, i4, 8);
                }
            } else {
                linearSystem.addEquality(solverVariableCreateObjectVariable4, solverVariableCreateObjectVariable3, iMin, i10);
            }
            z17 = z4;
            i14 = i7;
            i11 = i8;
            i12 = i31;
            solverVariable6 = solverVariableCreateObjectVariable5;
            solverVariable4 = solverVariableCreateObjectVariable4;
            z16 = z14;
            solverVariable5 = solverVariable3;
            i13 = 2;
        } else if (i31 == 2 || z6 || !(i9 == 1 || i9 == 0)) {
            int i35 = i7 == -2 ? iMin : i7;
            i11 = i8 == -2 ? iMin : i8;
            if (iMin > 0 && i9 != 1) {
                iMin = 0;
            }
            if (i35 > 0) {
                linearSystem.addGreaterThan(solverVariableCreateObjectVariable4, solverVariableCreateObjectVariable3, i35, 8);
                iMin = Math.max(iMin, i35);
            }
            if (i11 > 0) {
                if (!z2 || i9 != 1) {
                    linearSystem.addLowerThan(solverVariableCreateObjectVariable4, solverVariableCreateObjectVariable3, i11, 8);
                }
                iMin = Math.min(iMin, i11);
            }
            if (i9 == 1) {
                if (z2) {
                    linearSystem.addEquality(solverVariableCreateObjectVariable4, solverVariableCreateObjectVariable3, iMin, 8);
                } else if (z8) {
                    linearSystem.addEquality(solverVariableCreateObjectVariable4, solverVariableCreateObjectVariable3, iMin, 5);
                    linearSystem.addLowerThan(solverVariableCreateObjectVariable4, solverVariableCreateObjectVariable3, iMin, 8);
                } else {
                    linearSystem.addEquality(solverVariableCreateObjectVariable4, solverVariableCreateObjectVariable3, iMin, 5);
                    linearSystem.addLowerThan(solverVariableCreateObjectVariable4, solverVariableCreateObjectVariable3, iMin, 8);
                }
                z17 = z4;
                i12 = i31;
                solverVariable4 = solverVariableCreateObjectVariable4;
                z16 = z14;
                i14 = i35;
                solverVariable5 = solverVariable3;
                i13 = 2;
                solverVariable6 = solverVariableCreateObjectVariable5;
            } else if (i9 == 2) {
                if (constraintAnchor.getType() == ConstraintAnchor.Type.TOP || constraintAnchor.getType() == ConstraintAnchor.Type.BOTTOM) {
                    solverVariableCreateObjectVariable = linearSystem.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.TOP));
                    solverVariableCreateObjectVariable2 = linearSystem.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.BOTTOM));
                } else {
                    solverVariableCreateObjectVariable = linearSystem.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.LEFT));
                    solverVariableCreateObjectVariable2 = linearSystem.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.RIGHT));
                }
                boolean z27 = z14;
                solverVariable5 = solverVariable3;
                i13 = 2;
                int i36 = i35;
                solverVariable6 = solverVariableCreateObjectVariable5;
                i12 = i31;
                solverVariable4 = solverVariableCreateObjectVariable4;
                linearSystem.addConstraint(linearSystem.createRow().createRowDimensionRatio(solverVariableCreateObjectVariable4, solverVariableCreateObjectVariable3, solverVariableCreateObjectVariable2, solverVariableCreateObjectVariable, f2));
                if (z2) {
                    z27 = false;
                }
                i14 = i36;
                z16 = z27;
                z17 = z4;
            } else {
                i12 = i31;
                solverVariable4 = solverVariableCreateObjectVariable4;
                boolean z28 = z14;
                int i37 = i35;
                solverVariable5 = solverVariable3;
                i13 = 2;
                solverVariable6 = solverVariableCreateObjectVariable5;
                i14 = i37;
                z16 = z28;
                z17 = true;
            }
        } else {
            int iMax = Math.max(i7, iMin);
            if (i8 > 0) {
                iMax = Math.min(i8, iMax);
            }
            linearSystem.addEquality(solverVariableCreateObjectVariable4, solverVariableCreateObjectVariable3, iMax, 8);
            z17 = z4;
            i14 = i7;
            i11 = i8;
            i12 = i31;
            solverVariable6 = solverVariableCreateObjectVariable5;
            z16 = false;
            solverVariable5 = solverVariable3;
            i13 = 2;
            solverVariable4 = solverVariableCreateObjectVariable4;
        }
        if (!z11) {
            solverVariable7 = solverVariable;
            solverVariable8 = solverVariable2;
            solverVariable9 = solverVariable4;
            i15 = 0;
            solverVariable10 = solverVariableCreateObjectVariable3;
            i16 = i13;
            i17 = i12;
        } else if (z8) {
            solverVariable7 = solverVariable;
            solverVariable8 = solverVariable2;
            solverVariable9 = solverVariable4;
            i16 = i13;
            i17 = i12;
            i15 = 0;
            solverVariable10 = solverVariableCreateObjectVariable3;
        } else {
            if (!zIsConnected && !z15 && !zIsConnected3) {
                solverVariable15 = solverVariable4;
                i24 = 5;
                margin2 = 0;
            } else if (!zIsConnected || z15) {
                if (!zIsConnected && z15) {
                    linearSystem.addEquality(solverVariable4, solverVariable5, -constraintAnchor2.getMargin(), 8);
                    if (z2) {
                        if (this.mOptimizeWrapO && solverVariableCreateObjectVariable3.isFinalValue && (constraintWidget4 = this.mParent) != null) {
                            ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) constraintWidget4;
                            if (z) {
                                constraintWidgetContainer.addHorizontalWrapMinVariable(constraintAnchor);
                            } else {
                                constraintWidgetContainer.addVerticalWrapMinVariable(constraintAnchor);
                            }
                        } else {
                            i24 = 5;
                            linearSystem.addGreaterThan(solverVariableCreateObjectVariable3, solverVariable, 0, 5);
                            margin2 = 0;
                            solverVariable15 = solverVariable4;
                        }
                    }
                    if (z22) {
                    }
                } else if (zIsConnected && z15) {
                    ConstraintWidget constraintWidget6 = constraintAnchor.mTarget.mOwner;
                    ConstraintWidget constraintWidget7 = constraintAnchor2.mTarget.mOwner;
                    ConstraintWidget parent = getParent();
                    int i38 = 6;
                    if (z16) {
                        if (i9 == 0) {
                            if (i11 != 0 || i14 != 0) {
                                z24 = false;
                                i27 = 5;
                                i29 = 5;
                                z25 = true;
                                z18 = true;
                            } else if (solverVariable6.isFinalValue && solverVariable5.isFinalValue) {
                                linearSystem.addEquality(solverVariableCreateObjectVariable3, solverVariable6, constraintAnchor.getMargin(), 8);
                                linearSystem.addEquality(solverVariable4, solverVariable5, -constraintAnchor2.getMargin(), 8);
                                return;
                            } else {
                                z25 = false;
                                z18 = false;
                                i27 = 8;
                                i29 = 8;
                                z24 = true;
                            }
                            if ((constraintWidget6 instanceof Barrier) || (constraintWidget7 instanceof Barrier)) {
                                solverVariable11 = solverVariable2;
                                i19 = i9;
                                z20 = z24;
                                i20 = i27;
                                i21 = 6;
                                z19 = z25;
                                i22 = 4;
                                if (z18 || solverVariable6 != solverVariable5 || constraintWidget6 == parent) {
                                    z21 = true;
                                } else {
                                    z18 = false;
                                    z21 = false;
                                }
                                if (z19) {
                                    constraintWidget = constraintWidget7;
                                    solverVariable13 = solverVariable4;
                                    i23 = i19;
                                    constraintWidget2 = parent;
                                    solverVariable14 = solverVariableCreateObjectVariable3;
                                    z22 = z2;
                                } else {
                                    if (z16 || z7 || z9 || solverVariable6 != solverVariable || solverVariable5 != solverVariable11) {
                                        z22 = z2;
                                        i25 = i21;
                                        z23 = z21;
                                        i26 = i20;
                                    } else {
                                        z22 = false;
                                        i26 = 8;
                                        i25 = 8;
                                        z23 = false;
                                    }
                                    i23 = i19;
                                    constraintWidget2 = parent;
                                    constraintWidget = constraintWidget7;
                                    SolverVariable solverVariable16 = solverVariable4;
                                    solverVariable13 = solverVariable4;
                                    solverVariable14 = solverVariableCreateObjectVariable3;
                                    linearSystem.addCentering(solverVariableCreateObjectVariable3, solverVariable6, constraintAnchor.getMargin(), f, solverVariable5, solverVariable16, constraintAnchor2.getMargin(), i25);
                                    i20 = i26;
                                    z21 = z23;
                                }
                                if (this.mVisibility != 8 && !constraintAnchor2.hasDependents()) {
                                    return;
                                }
                                if (z18) {
                                    solverVariable15 = solverVariable13;
                                } else {
                                    if (z22 && solverVariable6 != solverVariable5 && !z16 && ((constraintWidget6 instanceof Barrier) || (constraintWidget instanceof Barrier))) {
                                        i20 = 6;
                                    }
                                    linearSystem.addGreaterThan(solverVariable14, solverVariable6, constraintAnchor.getMargin(), i20);
                                    solverVariable15 = solverVariable13;
                                    linearSystem.addLowerThan(solverVariable15, solverVariable5, -constraintAnchor2.getMargin(), i20);
                                }
                                if (z22 || !z10 || (constraintWidget6 instanceof Barrier) || (constraintWidget instanceof Barrier)) {
                                    constraintWidget3 = constraintWidget2;
                                } else {
                                    constraintWidget3 = constraintWidget2;
                                    if (constraintWidget != constraintWidget3) {
                                        i20 = 6;
                                        iMin2 = 6;
                                        z21 = true;
                                    }
                                    if (z21) {
                                        if (z20 && (!z9 || z3)) {
                                            if (constraintWidget6 != constraintWidget3 && constraintWidget != constraintWidget3) {
                                                i38 = iMin2;
                                            }
                                            if ((constraintWidget6 instanceof Guideline) || (constraintWidget instanceof Guideline)) {
                                                i38 = 5;
                                            }
                                            if ((constraintWidget6 instanceof Barrier) || (constraintWidget instanceof Barrier)) {
                                                i38 = 5;
                                            }
                                            iMin2 = Math.max(z9 ? 5 : i38, iMin2);
                                        }
                                        if (z22) {
                                            iMin2 = (z6 && !z9 && (constraintWidget6 == constraintWidget3 || constraintWidget == constraintWidget3)) ? 4 : Math.min(i20, iMin2);
                                        }
                                        linearSystem.addEquality(solverVariable14, solverVariable6, constraintAnchor.getMargin(), iMin2);
                                        linearSystem.addEquality(solverVariable15, solverVariable5, -constraintAnchor2.getMargin(), iMin2);
                                    }
                                    if (z22) {
                                        int margin3 = solverVariable == solverVariable6 ? constraintAnchor.getMargin() : 0;
                                        if (solverVariable6 != solverVariable) {
                                            linearSystem.addGreaterThan(solverVariable14, solverVariable, margin3, 5);
                                        }
                                    }
                                    if (!z22 || !z16 || i3 != 0 || i14 != 0) {
                                        i24 = 5;
                                        margin2 = 0;
                                    } else if (z16 && i23 == 3) {
                                        margin2 = 0;
                                        linearSystem.addGreaterThan(solverVariable15, solverVariable14, 0, 8);
                                        i24 = 5;
                                    } else {
                                        margin2 = 0;
                                        i24 = 5;
                                        linearSystem.addGreaterThan(solverVariable15, solverVariable14, 0, 5);
                                    }
                                    i30 = i24;
                                }
                                iMin2 = i22;
                                if (z21) {
                                }
                                if (z22) {
                                }
                                if (!z22) {
                                    i24 = 5;
                                    margin2 = 0;
                                    i30 = i24;
                                }
                            } else {
                                solverVariable11 = solverVariable2;
                                z20 = z24;
                                z19 = z25;
                                i22 = i29;
                                i19 = i9;
                                i20 = i27;
                                i21 = 6;
                                if (z18) {
                                    z21 = true;
                                    if (z19) {
                                    }
                                    if (this.mVisibility != 8) {
                                    }
                                    if (z18) {
                                    }
                                    if (z22) {
                                        constraintWidget3 = constraintWidget2;
                                        iMin2 = i22;
                                        if (z21) {
                                        }
                                        if (z22) {
                                        }
                                        if (!z22) {
                                        }
                                    }
                                }
                            }
                        } else if (i9 == 2) {
                            if ((constraintWidget6 instanceof Barrier) || (constraintWidget7 instanceof Barrier)) {
                                solverVariable11 = solverVariable2;
                                i19 = i9;
                            } else {
                                solverVariable11 = solverVariable2;
                                i19 = i9;
                                i21 = 6;
                                i20 = 5;
                                i22 = 5;
                                z19 = true;
                                z18 = true;
                                z20 = false;
                                if (z18) {
                                }
                            }
                        } else if (i9 == 1) {
                            solverVariable11 = solverVariable2;
                            i19 = i9;
                            i21 = 6;
                            i20 = 8;
                            i22 = 4;
                            z19 = true;
                            z18 = true;
                            z20 = false;
                            if (z18) {
                            }
                        } else if (i9 == 3) {
                            i19 = i9;
                            if (this.mResolvedDimensionRatioSide == -1) {
                                if (z9) {
                                    solverVariable11 = solverVariable2;
                                    i21 = z2 ? 5 : 4;
                                } else {
                                    solverVariable11 = solverVariable2;
                                    i21 = 8;
                                }
                                i20 = 8;
                            } else if (z6) {
                                if (i6 == 2 || i6 == 1) {
                                    i27 = 5;
                                    i28 = 4;
                                } else {
                                    i27 = 8;
                                    i28 = 5;
                                }
                                solverVariable11 = solverVariable2;
                                i22 = i28;
                                z19 = true;
                                z18 = true;
                                z20 = true;
                                i20 = i27;
                                i21 = 6;
                                if (z18) {
                                }
                            } else if (i11 > 0) {
                                solverVariable11 = solverVariable2;
                                i21 = 6;
                                i20 = 5;
                            } else {
                                if (i11 != 0 || i14 != 0) {
                                    solverVariable11 = solverVariable2;
                                    i21 = 6;
                                    i20 = 5;
                                } else if (z9) {
                                    solverVariable11 = solverVariable2;
                                    i20 = (constraintWidget6 == parent || constraintWidget7 == parent) ? 5 : 4;
                                    i21 = 6;
                                } else {
                                    solverVariable11 = solverVariable2;
                                    i21 = 6;
                                    i20 = 5;
                                    i22 = 8;
                                    z19 = true;
                                    z18 = true;
                                    z20 = true;
                                    if (z18) {
                                    }
                                }
                                i22 = 4;
                                z19 = true;
                                z18 = true;
                                z20 = true;
                                if (z18) {
                                }
                            }
                            i22 = 5;
                            z19 = true;
                            z18 = true;
                            z20 = true;
                            if (z18) {
                            }
                        } else {
                            i19 = i9;
                            solverVariable11 = solverVariable2;
                            i21 = 6;
                            i20 = 5;
                            i22 = 4;
                            z19 = false;
                            z18 = false;
                            z20 = false;
                            if (z18) {
                            }
                        }
                        if (z22 && z17) {
                            if (constraintAnchor2.mTarget != null) {
                                margin2 = constraintAnchor2.getMargin();
                            }
                            if (solverVariable5 != solverVariable2) {
                                if (this.mOptimizeWrapO && solverVariable15.isFinalValue && (constraintWidget5 = this.mParent) != null) {
                                    ConstraintWidgetContainer constraintWidgetContainer2 = (ConstraintWidgetContainer) constraintWidget5;
                                    if (z) {
                                        constraintWidgetContainer2.addHorizontalWrapMaxVariable(constraintAnchor2);
                                        return;
                                    } else {
                                        constraintWidgetContainer2.addVerticalWrapMaxVariable(constraintAnchor2);
                                        return;
                                    }
                                }
                                linearSystem.addGreaterThan(solverVariable2, solverVariable15, margin2, i30);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    i19 = i9;
                    if (solverVariable6.isFinalValue && solverVariable5.isFinalValue) {
                        linearSystem.addCentering(solverVariableCreateObjectVariable3, solverVariable6, constraintAnchor.getMargin(), f, solverVariable5, solverVariable4, constraintAnchor2.getMargin(), 8);
                        if (z2 && z17) {
                            if (constraintAnchor2.mTarget != null) {
                                margin = constraintAnchor2.getMargin();
                                solverVariable12 = solverVariable2;
                            } else {
                                solverVariable12 = solverVariable2;
                                margin = 0;
                            }
                            if (solverVariable5 != solverVariable12) {
                                linearSystem.addGreaterThan(solverVariable12, solverVariable4, margin, 5);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    solverVariable11 = solverVariable2;
                    i21 = 6;
                    i20 = 5;
                    i22 = 4;
                    z19 = true;
                    z18 = true;
                    z20 = false;
                    if (z18) {
                    }
                    if (z22) {
                        return;
                    } else {
                        return;
                    }
                }
                margin2 = 0;
                solverVariable15 = solverVariable4;
                i24 = 5;
            } else {
                i30 = (z2 && (constraintAnchor.mTarget.mOwner instanceof Barrier)) ? 8 : 5;
                z22 = z2;
                margin2 = 0;
                solverVariable15 = solverVariable4;
                if (z22) {
                }
            }
            z22 = z2;
            i30 = i24;
            if (z22) {
            }
        }
        if (i17 < i16 && z2 && z17) {
            linearSystem.addGreaterThan(solverVariable10, solverVariable7, i15, 8);
            int i39 = (z || this.mBaseline.mTarget == null) ? 1 : i15;
            if (z || this.mBaseline.mTarget == null) {
                i18 = i39;
            } else {
                ConstraintWidget constraintWidget8 = this.mBaseline.mTarget.mOwner;
                i18 = (constraintWidget8.mDimensionRatio != 0.0f && constraintWidget8.mListDimensionBehaviors[i15] == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget8.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) ? 1 : i15;
            }
            if (i18 != 0) {
                linearSystem.addGreaterThan(solverVariable8, solverVariable9, i15, 8);
            }
        }
    }

    public void updateFromSolver(LinearSystem linearSystem, boolean z) {
        VerticalWidgetRun verticalWidgetRun;
        HorizontalWidgetRun horizontalWidgetRun;
        int objectVariableValue = linearSystem.getObjectVariableValue(this.mLeft);
        int objectVariableValue2 = linearSystem.getObjectVariableValue(this.mTop);
        int objectVariableValue3 = linearSystem.getObjectVariableValue(this.mRight);
        int objectVariableValue4 = linearSystem.getObjectVariableValue(this.mBottom);
        if (z && (horizontalWidgetRun = this.mHorizontalRun) != null && horizontalWidgetRun.start.resolved && this.mHorizontalRun.end.resolved) {
            objectVariableValue = this.mHorizontalRun.start.value;
            objectVariableValue3 = this.mHorizontalRun.end.value;
        }
        if (z && (verticalWidgetRun = this.mVerticalRun) != null && verticalWidgetRun.start.resolved && this.mVerticalRun.end.resolved) {
            objectVariableValue2 = this.mVerticalRun.start.value;
            objectVariableValue4 = this.mVerticalRun.end.value;
        }
        int i = objectVariableValue4 - objectVariableValue2;
        if (objectVariableValue3 - objectVariableValue < 0 || i < 0 || objectVariableValue == Integer.MIN_VALUE || objectVariableValue == Integer.MAX_VALUE || objectVariableValue2 == Integer.MIN_VALUE || objectVariableValue2 == Integer.MAX_VALUE || objectVariableValue3 == Integer.MIN_VALUE || objectVariableValue3 == Integer.MAX_VALUE || objectVariableValue4 == Integer.MIN_VALUE || objectVariableValue4 == Integer.MAX_VALUE) {
            objectVariableValue = 0;
            objectVariableValue4 = 0;
            objectVariableValue2 = 0;
            objectVariableValue3 = 0;
        }
        setFrame(objectVariableValue, objectVariableValue2, objectVariableValue3, objectVariableValue4);
    }

    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        this.mHorizontalResolution = constraintWidget.mHorizontalResolution;
        this.mVerticalResolution = constraintWidget.mVerticalResolution;
        this.mMatchConstraintDefaultWidth = constraintWidget.mMatchConstraintDefaultWidth;
        this.mMatchConstraintDefaultHeight = constraintWidget.mMatchConstraintDefaultHeight;
        int[] iArr = this.mResolvedMatchConstraintDefault;
        int[] iArr2 = constraintWidget.mResolvedMatchConstraintDefault;
        iArr[0] = iArr2[0];
        iArr[1] = iArr2[1];
        this.mMatchConstraintMinWidth = constraintWidget.mMatchConstraintMinWidth;
        this.mMatchConstraintMaxWidth = constraintWidget.mMatchConstraintMaxWidth;
        this.mMatchConstraintMinHeight = constraintWidget.mMatchConstraintMinHeight;
        this.mMatchConstraintMaxHeight = constraintWidget.mMatchConstraintMaxHeight;
        this.mMatchConstraintPercentHeight = constraintWidget.mMatchConstraintPercentHeight;
        this.mIsWidthWrapContent = constraintWidget.mIsWidthWrapContent;
        this.mIsHeightWrapContent = constraintWidget.mIsHeightWrapContent;
        this.mResolvedDimensionRatioSide = constraintWidget.mResolvedDimensionRatioSide;
        this.mResolvedDimensionRatio = constraintWidget.mResolvedDimensionRatio;
        int[] iArr3 = constraintWidget.mMaxDimension;
        this.mMaxDimension = Arrays.copyOf(iArr3, iArr3.length);
        this.mCircleConstraintAngle = constraintWidget.mCircleConstraintAngle;
        this.mHasBaseline = constraintWidget.mHasBaseline;
        this.mInPlaceholder = constraintWidget.mInPlaceholder;
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mListDimensionBehaviors = (DimensionBehaviour[]) Arrays.copyOf(this.mListDimensionBehaviors, 2);
        this.mParent = this.mParent == null ? null : map.get(constraintWidget.mParent);
        this.mWidth = constraintWidget.mWidth;
        this.mHeight = constraintWidget.mHeight;
        this.mDimensionRatio = constraintWidget.mDimensionRatio;
        this.mDimensionRatioSide = constraintWidget.mDimensionRatioSide;
        this.f36mX = constraintWidget.f36mX;
        this.f37mY = constraintWidget.f37mY;
        this.mRelX = constraintWidget.mRelX;
        this.mRelY = constraintWidget.mRelY;
        this.mOffsetX = constraintWidget.mOffsetX;
        this.mOffsetY = constraintWidget.mOffsetY;
        this.mBaselineDistance = constraintWidget.mBaselineDistance;
        this.mMinWidth = constraintWidget.mMinWidth;
        this.mMinHeight = constraintWidget.mMinHeight;
        this.mHorizontalBiasPercent = constraintWidget.mHorizontalBiasPercent;
        this.mVerticalBiasPercent = constraintWidget.mVerticalBiasPercent;
        this.mCompanionWidget = constraintWidget.mCompanionWidget;
        this.mContainerItemSkip = constraintWidget.mContainerItemSkip;
        this.mVisibility = constraintWidget.mVisibility;
        this.mAnimated = constraintWidget.mAnimated;
        this.mDebugName = constraintWidget.mDebugName;
        this.mType = constraintWidget.mType;
        this.mDistToTop = constraintWidget.mDistToTop;
        this.mDistToLeft = constraintWidget.mDistToLeft;
        this.mDistToRight = constraintWidget.mDistToRight;
        this.mDistToBottom = constraintWidget.mDistToBottom;
        this.mLeftHasCentered = constraintWidget.mLeftHasCentered;
        this.mRightHasCentered = constraintWidget.mRightHasCentered;
        this.mTopHasCentered = constraintWidget.mTopHasCentered;
        this.mBottomHasCentered = constraintWidget.mBottomHasCentered;
        this.mHorizontalWrapVisited = constraintWidget.mHorizontalWrapVisited;
        this.mVerticalWrapVisited = constraintWidget.mVerticalWrapVisited;
        this.mHorizontalChainStyle = constraintWidget.mHorizontalChainStyle;
        this.mVerticalChainStyle = constraintWidget.mVerticalChainStyle;
        this.mHorizontalChainFixedPosition = constraintWidget.mHorizontalChainFixedPosition;
        this.mVerticalChainFixedPosition = constraintWidget.mVerticalChainFixedPosition;
        float[] fArr = this.mWeight;
        float[] fArr2 = constraintWidget.mWeight;
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[1];
        ConstraintWidget[] constraintWidgetArr = this.mListNextMatchConstraintsWidget;
        ConstraintWidget[] constraintWidgetArr2 = constraintWidget.mListNextMatchConstraintsWidget;
        constraintWidgetArr[0] = constraintWidgetArr2[0];
        constraintWidgetArr[1] = constraintWidgetArr2[1];
        ConstraintWidget[] constraintWidgetArr3 = this.mNextChainWidget;
        ConstraintWidget[] constraintWidgetArr4 = constraintWidget.mNextChainWidget;
        constraintWidgetArr3[0] = constraintWidgetArr4[0];
        constraintWidgetArr3[1] = constraintWidgetArr4[1];
        ConstraintWidget constraintWidget2 = constraintWidget.mHorizontalNextWidget;
        this.mHorizontalNextWidget = constraintWidget2 == null ? null : map.get(constraintWidget2);
        ConstraintWidget constraintWidget3 = constraintWidget.mVerticalNextWidget;
        this.mVerticalNextWidget = constraintWidget3 != null ? map.get(constraintWidget3) : null;
    }

    public void updateFromRuns(boolean z, boolean z2) {
        int i;
        int i2;
        boolean zIsResolved = z & this.mHorizontalRun.isResolved();
        boolean zIsResolved2 = z2 & this.mVerticalRun.isResolved();
        int i3 = this.mHorizontalRun.start.value;
        int i4 = this.mVerticalRun.start.value;
        int i5 = this.mHorizontalRun.end.value;
        int i6 = this.mVerticalRun.end.value;
        int i7 = i6 - i4;
        if (i5 - i3 < 0 || i7 < 0 || i3 == Integer.MIN_VALUE || i3 == Integer.MAX_VALUE || i4 == Integer.MIN_VALUE || i4 == Integer.MAX_VALUE || i5 == Integer.MIN_VALUE || i5 == Integer.MAX_VALUE || i6 == Integer.MIN_VALUE || i6 == Integer.MAX_VALUE) {
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
        }
        int i8 = i5 - i3;
        int i9 = i6 - i4;
        if (zIsResolved) {
            this.f36mX = i3;
        }
        if (zIsResolved2) {
            this.f37mY = i4;
        }
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (zIsResolved) {
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && i8 < (i2 = this.mWidth)) {
                i8 = i2;
            }
            this.mWidth = i8;
            int i10 = this.mMinWidth;
            if (i8 < i10) {
                this.mWidth = i10;
            }
        }
        if (zIsResolved2) {
            if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && i9 < (i = this.mHeight)) {
                i9 = i;
            }
            this.mHeight = i9;
            int i11 = this.mMinHeight;
            if (i9 < i11) {
                this.mHeight = i11;
            }
        }
    }

    public void addChildrenToSolverByDependency(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, HashSet<ConstraintWidget> hashSet, int i, boolean z) {
        if (z) {
            if (!hashSet.contains(this)) {
                return;
            }
            Optimizer.checkMatchParent(constraintWidgetContainer, linearSystem, this);
            hashSet.remove(this);
            addToSolver(linearSystem, constraintWidgetContainer.optimizeFor(64));
        }
        if (i == 0) {
            HashSet<ConstraintAnchor> dependents = this.mLeft.getDependents();
            if (dependents != null) {
                Iterator<ConstraintAnchor> it = dependents.iterator();
                while (it.hasNext()) {
                    it.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i, true);
                }
            }
            HashSet<ConstraintAnchor> dependents2 = this.mRight.getDependents();
            if (dependents2 != null) {
                Iterator<ConstraintAnchor> it2 = dependents2.iterator();
                while (it2.hasNext()) {
                    it2.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i, true);
                }
                return;
            }
            return;
        }
        HashSet<ConstraintAnchor> dependents3 = this.mTop.getDependents();
        if (dependents3 != null) {
            Iterator<ConstraintAnchor> it3 = dependents3.iterator();
            while (it3.hasNext()) {
                it3.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i, true);
            }
        }
        HashSet<ConstraintAnchor> dependents4 = this.mBottom.getDependents();
        if (dependents4 != null) {
            Iterator<ConstraintAnchor> it4 = dependents4.iterator();
            while (it4.hasNext()) {
                it4.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i, true);
            }
        }
        HashSet<ConstraintAnchor> dependents5 = this.mBaseline.getDependents();
        if (dependents5 != null) {
            Iterator<ConstraintAnchor> it5 = dependents5.iterator();
            while (it5.hasNext()) {
                it5.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i, true);
            }
        }
    }

    public void getSceneString(StringBuilder sb) {
        sb.append("  " + this.stringId + ":{\n");
        sb.append("    actualWidth:" + this.mWidth);
        sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        sb.append("    actualHeight:" + this.mHeight);
        sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        sb.append("    actualLeft:" + this.f36mX);
        sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        sb.append("    actualTop:" + this.f37mY);
        sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        getSceneString(sb, "left", this.mLeft);
        getSceneString(sb, "top", this.mTop);
        getSceneString(sb, "right", this.mRight);
        getSceneString(sb, "bottom", this.mBottom);
        getSceneString(sb, "baseline", this.mBaseline);
        getSceneString(sb, "centerX", this.mCenterX);
        getSceneString(sb, "centerY", this.mCenterY);
        getSceneString(sb, "    width", this.mWidth, this.mMinWidth, this.mMaxDimension[0], this.mWidthOverride, this.mMatchConstraintMinWidth, this.mMatchConstraintDefaultWidth, this.mMatchConstraintPercentWidth, this.mListDimensionBehaviors[0], this.mWeight[0]);
        getSceneString(sb, "    height", this.mHeight, this.mMinHeight, this.mMaxDimension[1], this.mHeightOverride, this.mMatchConstraintMinHeight, this.mMatchConstraintDefaultHeight, this.mMatchConstraintPercentHeight, this.mListDimensionBehaviors[1], this.mWeight[1]);
        serializeDimensionRatio(sb, "    dimensionRatio", this.mDimensionRatio, this.mDimensionRatioSide);
        serializeAttribute(sb, "    horizontalBias", this.mHorizontalBiasPercent, DEFAULT_BIAS);
        serializeAttribute(sb, "    verticalBias", this.mVerticalBiasPercent, DEFAULT_BIAS);
        serializeAttribute(sb, "    horizontalChainStyle", this.mHorizontalChainStyle, 0);
        serializeAttribute(sb, "    verticalChainStyle", this.mVerticalChainStyle, 0);
        sb.append("  }");
    }

    private void getSceneString(StringBuilder sb, String str, int i, int i2, int i3, int i4, int i5, int i6, float f, DimensionBehaviour dimensionBehaviour, float f2) {
        sb.append(str);
        sb.append(" :  {\n");
        serializeAttribute(sb, "      behavior", dimensionBehaviour.toString(), DimensionBehaviour.FIXED.toString());
        serializeAttribute(sb, "      size", i, 0);
        serializeAttribute(sb, "      min", i2, 0);
        serializeAttribute(sb, "      max", i3, Integer.MAX_VALUE);
        serializeAttribute(sb, "      matchMin", i5, 0);
        serializeAttribute(sb, "      matchDef", i6, 0);
        serializeAttribute(sb, "      matchPercent", f, 1.0f);
        sb.append("    },\n");
    }

    private void getSceneString(StringBuilder sb, String str, ConstraintAnchor constraintAnchor) {
        if (constraintAnchor.mTarget == null) {
            return;
        }
        sb.append("    ");
        sb.append(str);
        sb.append(" : [ '");
        sb.append(constraintAnchor.mTarget);
        sb.append("'");
        if (constraintAnchor.mGoneMargin != Integer.MIN_VALUE || constraintAnchor.mMargin != 0) {
            sb.append(",");
            sb.append(constraintAnchor.mMargin);
            if (constraintAnchor.mGoneMargin != Integer.MIN_VALUE) {
                sb.append(",");
                sb.append(constraintAnchor.mGoneMargin);
                sb.append(",");
            }
        }
        sb.append(" ] ,\n");
    }
}
