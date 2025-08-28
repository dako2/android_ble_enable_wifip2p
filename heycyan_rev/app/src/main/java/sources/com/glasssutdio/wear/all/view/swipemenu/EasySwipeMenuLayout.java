package com.glasssutdio.wear.all.view.swipemenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;
import com.glasssutdio.wear.C0775R;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class EasySwipeMenuLayout extends ViewGroup {
    private static final String TAG = "EasySwipeMenuLayout";
    private static State mStateCache;
    private static EasySwipeMenuLayout mViewCache;
    private float distanceX;
    private float finalyDistanceX;
    private boolean isSwipeing;
    private boolean mCanLeftSwipe;
    private boolean mCanRightSwipe;
    private View mContentView;
    private ViewGroup.MarginLayoutParams mContentViewLp;
    private int mContentViewResID;
    private PointF mFirstP;
    private float mFraction;
    private PointF mLastP;
    private View mLeftView;
    private int mLeftViewResID;
    private final ArrayList<View> mMatchParentChildren;
    private View mRightView;
    private int mRightViewResID;
    private int mScaledTouchSlop;
    private Scroller mScroller;
    State result;

    public EasySwipeMenuLayout(Context context) {
        this(context, null);
    }

    public EasySwipeMenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EasySwipeMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mMatchParentChildren = new ArrayList<>(1);
        this.mFraction = 0.3f;
        this.mCanLeftSwipe = true;
        this.mCanRightSwipe = true;
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mScroller = new Scroller(context);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attrs, C0775R.styleable.EasySwipeMenuLayout, defStyleAttr, 0);
        try {
            try {
                int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
                for (int i = 0; i < indexCount; i++) {
                    int index = typedArrayObtainStyledAttributes.getIndex(i);
                    if (index == C0775R.styleable.EasySwipeMenuLayout_leftMenuView) {
                        this.mLeftViewResID = typedArrayObtainStyledAttributes.getResourceId(C0775R.styleable.EasySwipeMenuLayout_leftMenuView, -1);
                    } else if (index == C0775R.styleable.EasySwipeMenuLayout_rightMenuView) {
                        this.mRightViewResID = typedArrayObtainStyledAttributes.getResourceId(C0775R.styleable.EasySwipeMenuLayout_rightMenuView, -1);
                    } else if (index == C0775R.styleable.EasySwipeMenuLayout_contentView) {
                        this.mContentViewResID = typedArrayObtainStyledAttributes.getResourceId(C0775R.styleable.EasySwipeMenuLayout_contentView, -1);
                    } else if (index == C0775R.styleable.EasySwipeMenuLayout_canLeftSwipe) {
                        this.mCanLeftSwipe = typedArrayObtainStyledAttributes.getBoolean(C0775R.styleable.EasySwipeMenuLayout_canLeftSwipe, true);
                    } else if (index == C0775R.styleable.EasySwipeMenuLayout_canRightSwipe) {
                        this.mCanRightSwipe = typedArrayObtainStyledAttributes.getBoolean(C0775R.styleable.EasySwipeMenuLayout_canRightSwipe, true);
                    } else if (index == C0775R.styleable.EasySwipeMenuLayout_fraction) {
                        this.mFraction = typedArrayObtainStyledAttributes.getFloat(C0775R.styleable.EasySwipeMenuLayout_fraction, 0.5f);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childMeasureSpec;
        int childMeasureSpec2;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setClickable(true);
        int childCount = getChildCount();
        boolean z = (View.MeasureSpec.getMode(widthMeasureSpec) == 1073741824 && View.MeasureSpec.getMode(heightMeasureSpec) == 1073741824) ? false : true;
        this.mMatchParentChildren.clear();
        int iCombineMeasuredStates = 0;
        int iMax = 0;
        int iMax2 = 0;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                measureChildWithMargins(childAt, widthMeasureSpec, 0, heightMeasureSpec, 0);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                iMax = Math.max(iMax, childAt.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
                iMax2 = Math.max(iMax2, childAt.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin);
                iCombineMeasuredStates = combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
                if (z && (marginLayoutParams.width == -1 || marginLayoutParams.height == -1)) {
                    this.mMatchParentChildren.add(childAt);
                }
            }
        }
        int i2 = iCombineMeasuredStates;
        setMeasuredDimension(resolveSizeAndState(Math.max(iMax, getSuggestedMinimumWidth()), widthMeasureSpec, i2), resolveSizeAndState(Math.max(iMax2, getSuggestedMinimumHeight()), heightMeasureSpec, i2 << 16));
        int size = this.mMatchParentChildren.size();
        if (size > 1) {
            for (int i3 = 0; i3 < size; i3++) {
                View view = this.mMatchParentChildren.get(i3);
                ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                if (marginLayoutParams2.width == -1) {
                    childMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, (getMeasuredWidth() - marginLayoutParams2.leftMargin) - marginLayoutParams2.rightMargin), 1073741824);
                } else {
                    childMeasureSpec = getChildMeasureSpec(widthMeasureSpec, marginLayoutParams2.leftMargin + marginLayoutParams2.rightMargin, marginLayoutParams2.width);
                }
                if (marginLayoutParams2.height == -1) {
                    childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, (getMeasuredHeight() - marginLayoutParams2.topMargin) - marginLayoutParams2.bottomMargin), 1073741824);
                } else {
                    childMeasureSpec2 = getChildMeasureSpec(heightMeasureSpec, marginLayoutParams2.topMargin + marginLayoutParams2.bottomMargin, marginLayoutParams2.height);
                }
                view.measure(childMeasureSpec, childMeasureSpec2);
            }
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new ViewGroup.MarginLayoutParams(getContext(), attrs);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        getPaddingLeft();
        int paddingTop = getPaddingTop();
        getPaddingTop();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (this.mLeftView == null && childAt.getId() == this.mLeftViewResID) {
                this.mLeftView = childAt;
                childAt.setClickable(true);
            } else if (this.mRightView == null && childAt.getId() == this.mRightViewResID) {
                this.mRightView = childAt;
                childAt.setClickable(true);
            } else if (this.mContentView == null && childAt.getId() == this.mContentViewResID) {
                this.mContentView = childAt;
                childAt.setClickable(true);
            }
        }
        View view = this.mContentView;
        if (view != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            this.mContentViewLp = marginLayoutParams;
            int i2 = marginLayoutParams.topMargin + paddingTop;
            this.mContentView.layout(this.mContentViewLp.leftMargin + paddingLeft, i2, paddingLeft + this.mContentViewLp.leftMargin + this.mContentView.getMeasuredWidth(), this.mContentView.getMeasuredHeight() + i2);
        }
        View view2 = this.mLeftView;
        if (view2 != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view2.getLayoutParams();
            int i3 = marginLayoutParams2.topMargin + paddingTop;
            this.mLeftView.layout((0 - this.mLeftView.getMeasuredWidth()) + marginLayoutParams2.leftMargin + marginLayoutParams2.rightMargin, i3, 0 - marginLayoutParams2.rightMargin, this.mLeftView.getMeasuredHeight() + i3);
        }
        View view3 = this.mRightView;
        if (view3 != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) view3.getLayoutParams();
            int i4 = paddingTop + marginLayoutParams3.topMargin;
            int right = this.mContentView.getRight() + this.mContentViewLp.rightMargin + marginLayoutParams3.leftMargin;
            this.mRightView.layout(right, i4, this.mRightView.getMeasuredWidth() + right, this.mRightView.getMeasuredHeight() + i4);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00ca  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == 0) {
            this.isSwipeing = false;
            if (this.mLastP == null) {
                this.mLastP = new PointF();
            }
            this.mLastP.set(ev.getRawX(), ev.getRawY());
            if (this.mFirstP == null) {
                this.mFirstP = new PointF();
            }
            this.mFirstP.set(ev.getRawX(), ev.getRawY());
            EasySwipeMenuLayout easySwipeMenuLayout = mViewCache;
            if (easySwipeMenuLayout != null) {
                if (easySwipeMenuLayout != this) {
                    easySwipeMenuLayout.handlerSwipeMenu(State.CLOSE);
                }
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        } else if (action == 1) {
            float rawX = this.mFirstP.x - ev.getRawX();
            this.finalyDistanceX = rawX;
            if (Math.abs(rawX) > this.mScaledTouchSlop) {
                this.isSwipeing = true;
            }
            State stateIsShouldOpen = isShouldOpen(getScrollX());
            this.result = stateIsShouldOpen;
            handlerSwipeMenu(stateIsShouldOpen);
        } else if (action == 2) {
            float rawX2 = this.mLastP.x - ev.getRawX();
            float rawY = this.mLastP.y - ev.getRawY();
            if (Math.abs(rawY) <= this.mScaledTouchSlop || Math.abs(rawY) <= Math.abs(rawX2)) {
                scrollBy((int) rawX2, 0);
                if (getScrollX() < 0) {
                    if (!this.mCanRightSwipe || this.mLeftView == null) {
                        scrollTo(0, 0);
                    } else if (getScrollX() < this.mLeftView.getLeft()) {
                        scrollTo(this.mLeftView.getLeft(), 0);
                    }
                } else if (getScrollX() > 0) {
                    if (!this.mCanLeftSwipe || this.mRightView == null) {
                        scrollTo(0, 0);
                    } else if (getScrollX() > (this.mRightView.getRight() - this.mContentView.getRight()) - this.mContentViewLp.rightMargin) {
                        scrollTo((this.mRightView.getRight() - this.mContentView.getRight()) - this.mContentViewLp.rightMargin, 0);
                    }
                }
                if (Math.abs(rawX2) > this.mScaledTouchSlop) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                this.mLastP.set(ev.getRawX(), ev.getRawY());
            }
        } else if (action == 3) {
        }
        return super.dispatchTouchEvent(ev);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001c  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == 1) {
            if (this.isSwipeing) {
                this.isSwipeing = false;
                this.finalyDistanceX = 0.0f;
                return true;
            }
        } else if (action != 2) {
            if (action == 3) {
            }
        } else if (Math.abs(this.finalyDistanceX) > this.mScaledTouchSlop) {
            return true;
        }
        return super.onInterceptTouchEvent(event);
    }

    private void handlerSwipeMenu(State result) {
        if (result == State.LEFTOPEN) {
            this.mScroller.startScroll(getScrollX(), 0, this.mLeftView.getLeft() - getScrollX(), 0);
            mViewCache = this;
            mStateCache = result;
        } else if (result == State.RIGHTOPEN) {
            mViewCache = this;
            this.mScroller.startScroll(getScrollX(), 0, ((this.mRightView.getRight() - this.mContentView.getRight()) - this.mContentViewLp.rightMargin) - getScrollX(), 0);
            mStateCache = result;
        } else {
            this.mScroller.startScroll(getScrollX(), 0, -getScrollX(), 0);
            mViewCache = null;
            mStateCache = null;
        }
        invalidate();
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            scrollTo(this.mScroller.getCurrX(), this.mScroller.getCurrY());
            invalidate();
        }
    }

    private State isShouldOpen(int scrollX) {
        View view;
        View view2;
        if (this.mScaledTouchSlop >= Math.abs(this.finalyDistanceX)) {
            return mStateCache;
        }
        Log.i(TAG, ">>>finalyDistanceX:" + this.finalyDistanceX);
        float f = this.finalyDistanceX;
        if (f < 0.0f) {
            if (getScrollX() < 0 && (view2 = this.mLeftView) != null && Math.abs(view2.getWidth() * this.mFraction) < Math.abs(getScrollX())) {
                return State.LEFTOPEN;
            }
            if (getScrollX() > 0 && this.mRightView != null) {
                return State.CLOSE;
            }
        } else if (f > 0.0f) {
            if (getScrollX() > 0 && (view = this.mRightView) != null && Math.abs(view.getWidth() * this.mFraction) < Math.abs(getScrollX())) {
                return State.RIGHTOPEN;
            }
            if (getScrollX() < 0 && this.mLeftView != null) {
                return State.CLOSE;
            }
        }
        return State.CLOSE;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        EasySwipeMenuLayout easySwipeMenuLayout = mViewCache;
        if (this == easySwipeMenuLayout) {
            easySwipeMenuLayout.handlerSwipeMenu(State.CLOSE);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        EasySwipeMenuLayout easySwipeMenuLayout = mViewCache;
        if (this == easySwipeMenuLayout) {
            easySwipeMenuLayout.handlerSwipeMenu(mStateCache);
        }
    }

    public void resetStatus() {
        State state;
        Scroller scroller;
        if (mViewCache == null || (state = mStateCache) == null || state == State.CLOSE || (scroller = this.mScroller) == null) {
            return;
        }
        scroller.startScroll(mViewCache.getScrollX(), 0, -mViewCache.getScrollX(), 0);
        mViewCache.invalidate();
        mViewCache = null;
        mStateCache = null;
    }

    public float getFraction() {
        return this.mFraction;
    }

    public void setFraction(float mFraction) {
        this.mFraction = mFraction;
    }

    public boolean isCanLeftSwipe() {
        return this.mCanLeftSwipe;
    }

    public void setCanLeftSwipe(boolean mCanLeftSwipe) {
        this.mCanLeftSwipe = mCanLeftSwipe;
    }

    public boolean isCanRightSwipe() {
        return this.mCanRightSwipe;
    }

    public void setCanRightSwipe(boolean mCanRightSwipe) {
        this.mCanRightSwipe = mCanRightSwipe;
    }

    public static EasySwipeMenuLayout getViewCache() {
        return mViewCache;
    }

    public static State getStateCache() {
        return mStateCache;
    }

    private boolean isLeftToRight() {
        return this.distanceX < 0.0f;
    }
}
