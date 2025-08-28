package com.glasssutdio.wear.all.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomScrollbarHelper.kt */
@Metadata(m606d1 = {"\u0000i\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0012\u0018\u0000 >2\u00020\u0001:\u0002=>B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020\u001fH\u0002J\b\u0010#\u001a\u00020!H\u0002J\u0006\u0010$\u001a\u00020\u001bJ\b\u0010%\u001a\u00020\u0018H\u0002J\u0010\u0010&\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020\fH\u0002J\u0010\u0010(\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020\fH\u0002J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010)\u001a\u00020\u001bJ\b\u0010*\u001a\u00020\u001bH\u0002J\b\u0010+\u001a\u00020\u001bH\u0002J\b\u0010,\u001a\u00020\u001bH\u0002J\u0006\u0010-\u001a\u00020\u001bJ\u0018\u0010.\u001a\u00020\u001b2\u0006\u0010/\u001a\u00020\f2\u0006\u00100\u001a\u00020\nH\u0002J\u000e\u00101\u001a\u00020\u001b2\u0006\u00102\u001a\u00020\nJ\b\u00103\u001a\u00020\u001bH\u0002J\b\u00104\u001a\u00020\u001bH\u0002J\b\u00105\u001a\u00020\u001bH\u0002J\u0006\u00106\u001a\u00020\u001bJ\b\u00107\u001a\u00020\u001bH\u0002J\u001f\u00108\u001a\u00020\u001b2\u0017\u00109\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001b0:¢\u0006\u0002\b;J\b\u0010<\u001a\u00020\u001bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006?"}, m607d2 = {"Lcom/glasssutdio/wear/all/view/CustomScrollbarHelper;", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "config", "Lcom/glasssutdio/wear/all/view/ScrollbarConfig;", "(Landroidx/recyclerview/widget/RecyclerView;Lcom/glasssutdio/wear/all/view/ScrollbarConfig;)V", "fadeAnimator", "Landroid/animation/ValueAnimator;", "hasShownOnce", "", "initialThumbTop", "", "initialTouchY", "isDragging", "isInitialized", "isScrollbarVisible", "scrollListener", "com/glasssutdio/wear/all/view/CustomScrollbarHelper$scrollListener$1", "Lcom/glasssutdio/wear/all/view/CustomScrollbarHelper$scrollListener$1;", "scrollThumb", "Landroid/view/View;", "scrollTrack", "thumbHeight", "", "trackHeight", "createScrollbarViews", "", "parent", "Landroidx/constraintlayout/widget/ConstraintLayout;", "createThumbDrawable", "Landroid/graphics/drawable/GradientDrawable;", "createThumbLayoutParams", "Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;", "createTrackDrawable", "createTrackLayoutParams", "destroy", "getMaxScrollY", "handleThumbDrag", "rawY", "handleTrackClick", "hide", "hideScrollbar", "hideScrollbarWithDelay", "initialize", "resetToInitialState", "scrollRecyclerViewToRatio", "ratio", "smooth", "setVisible", "visible", "setupScrollbar", "setupThumbTouchListener", "setupTrackTouchListener", "show", "showScrollbar", "updateConfig", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "updateThumbPosition", "Builder", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class CustomScrollbarHelper {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ScrollbarConfig config;
    private ValueAnimator fadeAnimator;
    private boolean hasShownOnce;
    private float initialThumbTop;
    private float initialTouchY;
    private boolean isDragging;
    private boolean isInitialized;
    private boolean isScrollbarVisible;
    private final RecyclerView recyclerView;
    private final CustomScrollbarHelper$scrollListener$1 scrollListener;
    private View scrollThumb;
    private View scrollTrack;
    private int thumbHeight;
    private int trackHeight;

    public /* synthetic */ CustomScrollbarHelper(RecyclerView recyclerView, ScrollbarConfig scrollbarConfig, DefaultConstructorMarker defaultConstructorMarker) {
        this(recyclerView, scrollbarConfig);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.glasssutdio.wear.all.view.CustomScrollbarHelper$scrollListener$1] */
    private CustomScrollbarHelper(RecyclerView recyclerView, ScrollbarConfig scrollbarConfig) {
        this.recyclerView = recyclerView;
        this.config = scrollbarConfig;
        this.scrollListener = new RecyclerView.OnScrollListener() { // from class: com.glasssutdio.wear.all.view.CustomScrollbarHelper$scrollListener$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView2, int dx, int dy) {
                Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                super.onScrolled(recyclerView2, dx, dy);
                if (!this.this$0.isDragging && this.this$0.isInitialized) {
                    this.this$0.updateThumbPosition();
                }
                this.this$0.showScrollbar();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int newState) {
                Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                super.onScrollStateChanged(recyclerView2, newState);
                if (newState != 0 || this.this$0.isDragging) {
                    return;
                }
                this.this$0.hideScrollbarWithDelay();
            }
        };
    }

    /* compiled from: CustomScrollbarHelper.kt */
    @Metadata(m606d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m607d2 = {"Lcom/glasssutdio/wear/all/view/CustomScrollbarHelper$Companion;", "", "()V", "create", "Lcom/glasssutdio/wear/all/view/CustomScrollbarHelper$Builder;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Builder create(RecyclerView recyclerView) {
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            return new Builder(recyclerView);
        }
    }

    /* compiled from: CustomScrollbarHelper.kt */
    @Metadata(m606d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000eJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\tJ\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0015J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0015J\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0015J\u000e\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010 \u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0015J\u000e\u0010!\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0015R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, m607d2 = {"Lcom/glasssutdio/wear/all/view/CustomScrollbarHelper$Builder;", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "(Landroidx/recyclerview/widget/RecyclerView;)V", "config", "Lcom/glasssutdio/wear/all/view/ScrollbarConfig;", "autoHide", "enable", "", "build", "Lcom/glasssutdio/wear/all/view/CustomScrollbarHelper;", "fadeDelay", "delay", "", "fadeDuration", TypedValues.TransitionType.S_DURATION, "initiallyHidden", "hidden", "thumbColor", TypedValues.Custom.S_COLOR, "", "thumbHeight", "height", "thumbMarginEnd", "margin", "thumbRadius", "radius", "", "thumbWidth", "width", "trackColor", "trackMarginEnd", "trackWidth", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Builder {
        private final ScrollbarConfig config;
        private final RecyclerView recyclerView;

        public Builder(RecyclerView recyclerView) {
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            this.recyclerView = recyclerView;
            this.config = new ScrollbarConfig(0, 0, 0, 0, 0, 0, 0.0f, 0, false, 0L, 0L, false, 4095, null);
        }

        public final Builder trackWidth(int width) {
            this.config.setTrackWidth(width);
            return this;
        }

        public final Builder trackColor(int color) {
            this.config.setTrackColor(color);
            return this;
        }

        public final Builder trackMarginEnd(int margin) {
            this.config.setTrackMarginEnd(margin);
            return this;
        }

        public final Builder thumbWidth(int width) {
            this.config.setThumbWidth(width);
            return this;
        }

        public final Builder thumbHeight(int height) {
            this.config.setThumbHeight(height);
            return this;
        }

        public final Builder thumbColor(int color) {
            this.config.setThumbColor(color);
            return this;
        }

        public final Builder thumbRadius(float radius) {
            this.config.setThumbRadius(radius);
            return this;
        }

        public final Builder thumbMarginEnd(int margin) {
            this.config.setThumbMarginEnd(margin);
            return this;
        }

        public final Builder autoHide(boolean enable) {
            this.config.setAutoHide(enable);
            return this;
        }

        public final Builder fadeDelay(long delay) {
            this.config.setFadeDelay(delay);
            return this;
        }

        public final Builder fadeDuration(long duration) {
            this.config.setFadeDuration(duration);
            return this;
        }

        public final Builder initiallyHidden(boolean hidden) {
            this.config.setInitiallyHidden(hidden);
            return this;
        }

        public final CustomScrollbarHelper build() {
            CustomScrollbarHelper customScrollbarHelper = new CustomScrollbarHelper(this.recyclerView, this.config, null);
            customScrollbarHelper.initialize();
            return customScrollbarHelper;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initialize() {
        ViewParent parent = this.recyclerView.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup == null) {
            throw new IllegalStateException("RecyclerView must have a ViewGroup parent");
        }
        if (!(viewGroup instanceof ConstraintLayout)) {
            throw new IllegalStateException("Currently only supports ConstraintLayout as parent");
        }
        createScrollbarViews((ConstraintLayout) viewGroup);
        setupScrollbar();
        this.recyclerView.addOnScrollListener(this.scrollListener);
        this.isInitialized = true;
    }

    private final void createScrollbarViews(ConstraintLayout parent) {
        Context context = parent.getContext();
        View view = new View(context);
        view.setId(View.generateViewId());
        view.setBackground(createTrackDrawable());
        view.setVisibility(0);
        view.setAlpha(0.0f);
        this.scrollTrack = view;
        View view2 = new View(context);
        view2.setId(View.generateViewId());
        view2.setBackground(createThumbDrawable());
        view2.setVisibility(0);
        view2.setAlpha(0.0f);
        this.scrollThumb = view2;
        parent.addView(this.scrollTrack, createTrackLayoutParams());
        parent.addView(this.scrollThumb, createThumbLayoutParams());
        this.isScrollbarVisible = false;
    }

    private final ConstraintLayout.LayoutParams createTrackLayoutParams() {
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(this.config.getTrackWidth(), 0);
        layoutParams.topToTop = 0;
        layoutParams.bottomToBottom = 0;
        layoutParams.endToEnd = 0;
        layoutParams.setMarginEnd(this.config.getTrackMarginEnd());
        return layoutParams;
    }

    private final ConstraintLayout.LayoutParams createThumbLayoutParams() {
        View view = this.scrollTrack;
        if (view == null) {
            return new ConstraintLayout.LayoutParams(0, 0);
        }
        view.getId();
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(this.config.getThumbWidth(), this.config.getThumbHeight());
        layoutParams.topToTop = 0;
        layoutParams.endToEnd = 0;
        layoutParams.setMarginEnd(this.config.getTrackMarginEnd() - ((this.config.getThumbWidth() - this.config.getTrackWidth()) / 2));
        return layoutParams;
    }

    private final GradientDrawable createTrackDrawable() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(this.config.getTrackColor());
        gradientDrawable.setCornerRadius(this.config.getTrackWidth() / 2.0f);
        return gradientDrawable;
    }

    private final GradientDrawable createThumbDrawable() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(this.config.getThumbColor());
        gradientDrawable.setCornerRadius(this.config.getThumbRadius());
        return gradientDrawable;
    }

    private final void setupScrollbar() {
        final View view;
        final View view2 = this.scrollTrack;
        if (view2 == null || (view = this.scrollThumb) == null) {
            return;
        }
        view2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.glasssutdio.wear.all.view.CustomScrollbarHelper.setupScrollbar.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                view2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                this.trackHeight = view2.getHeight();
                this.thumbHeight = view.getHeight();
                this.updateThumbPosition();
                this.setupThumbTouchListener();
                this.setupTrackTouchListener();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupThumbTouchListener() {
        final View view = this.scrollThumb;
        if (view == null) {
            return;
        }
        view.setOnTouchListener(new View.OnTouchListener() { // from class: com.glasssutdio.wear.all.view.CustomScrollbarHelper$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return CustomScrollbarHelper.setupThumbTouchListener$lambda$6(this.f$0, view, view2, motionEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean setupThumbTouchListener$lambda$6(CustomScrollbarHelper this$0, View thumb, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(thumb, "$thumb");
        int action = motionEvent.getAction();
        if (action == 0) {
            this$0.isDragging = true;
            this$0.initialTouchY = motionEvent.getRawY();
            Intrinsics.checkNotNull(thumb.getLayoutParams(), "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
            this$0.initialThumbTop = ((ConstraintLayout.LayoutParams) r3).topMargin;
            this$0.showScrollbar();
            return true;
        }
        if (action != 1) {
            if (action == 2) {
                if (!this$0.isDragging) {
                    return true;
                }
                this$0.handleThumbDrag(motionEvent.getRawY());
                return true;
            }
            if (action != 3) {
                return false;
            }
        }
        this$0.isDragging = false;
        if (!this$0.config.getAutoHide()) {
            return true;
        }
        this$0.hideScrollbarWithDelay();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupTrackTouchListener() {
        View view = this.scrollTrack;
        if (view == null) {
            return;
        }
        view.setOnTouchListener(new View.OnTouchListener() { // from class: com.glasssutdio.wear.all.view.CustomScrollbarHelper$$ExternalSyntheticLambda1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return CustomScrollbarHelper.setupTrackTouchListener$lambda$7(this.f$0, view2, motionEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean setupTrackTouchListener$lambda$7(CustomScrollbarHelper this$0, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (motionEvent.getAction() != 0) {
            return false;
        }
        this$0.showScrollbar();
        View view2 = this$0.scrollThumb;
        if (view2 == null) {
            return false;
        }
        int[] iArr = new int[2];
        view2.getLocationOnScreen(iArr);
        int i = iArr[1];
        int height = view2.getHeight() + i;
        if (motionEvent.getRawY() < i || motionEvent.getRawY() > height) {
            this$0.handleTrackClick(motionEvent.getRawY());
        }
        return true;
    }

    private final void handleTrackClick(float rawY) {
        View view = this.scrollTrack;
        if (view == null) {
            return;
        }
        view.getLocationOnScreen(new int[2]);
        scrollRecyclerViewToRatio(Math.max(0.0f, Math.min(1.0f, (rawY - r1[1]) / this.trackHeight)), true);
    }

    private final void handleThumbDrag(float rawY) {
        View view = this.scrollThumb;
        if (view == null) {
            return;
        }
        float fMax = Math.max(0.0f, Math.min(this.initialThumbTop + (rawY - this.initialTouchY), this.trackHeight - this.thumbHeight));
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        layoutParams2.topMargin = (int) fMax;
        view.setLayoutParams(layoutParams2);
        scrollRecyclerViewToRatio(this.trackHeight > this.thumbHeight ? fMax / (r0 - r2) : 0.0f, false);
    }

    private final void scrollRecyclerViewToRatio(float ratio, boolean smooth) {
        int itemCount;
        RecyclerView.LayoutManager layoutManager = this.recyclerView.getLayoutManager();
        LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
        if (linearLayoutManager == null || (itemCount = linearLayoutManager.getItemCount()) == 0) {
            return;
        }
        int i = itemCount - 1;
        int iMax = Math.max(0, Math.min(i, (int) (i * ratio)));
        if (smooth) {
            this.recyclerView.smoothScrollToPosition(iMax);
        } else {
            linearLayoutManager.scrollToPositionWithOffset(iMax, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateThumbPosition() {
        View view;
        if (this.trackHeight <= 0 || this.thumbHeight <= 0 || (view = this.scrollThumb) == null) {
            return;
        }
        int iComputeVerticalScrollOffset = this.recyclerView.computeVerticalScrollOffset();
        int maxScrollY = getMaxScrollY();
        if (maxScrollY <= 0) {
            if (this.config.getAutoHide()) {
                hideScrollbar();
            }
        } else {
            float f = (iComputeVerticalScrollOffset / maxScrollY) * (this.trackHeight - this.thumbHeight);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            layoutParams2.topMargin = (int) f;
            view.setLayoutParams(layoutParams2);
        }
    }

    private final int getMaxScrollY() {
        return Math.max(0, this.recyclerView.computeVerticalScrollRange() - this.recyclerView.computeVerticalScrollExtent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showScrollbar() {
        ValueAnimator valueAnimator = this.fadeAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.isScrollbarVisible = true;
        this.hasShownOnce = true;
        View view = this.scrollTrack;
        if (view != null) {
            view.setAlpha(1.0f);
        }
        View view2 = this.scrollThumb;
        if (view2 == null) {
            return;
        }
        view2.setAlpha(1.0f);
    }

    private final void hideScrollbar() {
        if (this.config.getAutoHide()) {
            this.isScrollbarVisible = false;
            View view = this.scrollTrack;
            if (view != null) {
                view.setAlpha(0.0f);
            }
            View view2 = this.scrollThumb;
            if (view2 == null) {
                return;
            }
            view2.setAlpha(0.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideScrollbarWithDelay() {
        if (!this.config.getAutoHide() || this.isDragging) {
            return;
        }
        ValueAnimator valueAnimator = this.fadeAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat.setDuration(this.config.getFadeDuration());
        valueAnimatorOfFloat.setStartDelay(this.config.getFadeDelay());
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.glasssutdio.wear.all.view.CustomScrollbarHelper$$ExternalSyntheticLambda2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                CustomScrollbarHelper.hideScrollbarWithDelay$lambda$9$lambda$8(this.f$0, valueAnimator2);
            }
        });
        valueAnimatorOfFloat.start();
        this.fadeAnimator = valueAnimatorOfFloat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void hideScrollbarWithDelay$lambda$9$lambda$8(CustomScrollbarHelper this$0, ValueAnimator animation) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(animation, "animation");
        Object animatedValue = animation.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        float fFloatValue = ((Float) animatedValue).floatValue();
        if (this$0.isDragging) {
            return;
        }
        View view = this$0.scrollTrack;
        if (view != null) {
            view.setAlpha(fFloatValue);
        }
        View view2 = this$0.scrollThumb;
        if (view2 != null) {
            view2.setAlpha(fFloatValue);
        }
        if (fFloatValue == 0.0f) {
            this$0.isScrollbarVisible = false;
        }
    }

    public final void show() {
        showScrollbar();
    }

    public final void hide() {
        hideScrollbar();
    }

    public final void setVisible(boolean visible) {
        if (visible) {
            show();
        } else {
            hide();
        }
    }

    /* renamed from: hasShownOnce, reason: from getter */
    public final boolean getHasShownOnce() {
        return this.hasShownOnce;
    }

    public final void resetToInitialState() {
        this.hasShownOnce = false;
        this.isScrollbarVisible = false;
        ValueAnimator valueAnimator = this.fadeAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        View view = this.scrollTrack;
        if (view != null) {
            view.setAlpha(0.0f);
        }
        View view2 = this.scrollThumb;
        if (view2 == null) {
            return;
        }
        view2.setAlpha(0.0f);
    }

    public final void updateConfig(Function1<? super ScrollbarConfig, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        block.invoke(this.config);
        View view = this.scrollTrack;
        if (view != null) {
            view.setBackground(createTrackDrawable());
        }
        View view2 = this.scrollThumb;
        if (view2 != null) {
            view2.setBackground(createThumbDrawable());
        }
        ViewParent parent = this.recyclerView.getParent();
        if (parent instanceof ConstraintLayout) {
        }
        View view3 = this.scrollTrack;
        if (view3 != null) {
            view3.setLayoutParams(createTrackLayoutParams());
        }
        View view4 = this.scrollThumb;
        if (view4 == null) {
            return;
        }
        view4.setLayoutParams(createThumbLayoutParams());
    }

    public final void destroy() {
        ValueAnimator valueAnimator = this.fadeAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.recyclerView.removeOnScrollListener(this.scrollListener);
        ViewParent parent = this.recyclerView.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        View view = this.scrollTrack;
        if (view != null && viewGroup != null) {
            viewGroup.removeView(view);
        }
        View view2 = this.scrollThumb;
        if (view2 != null && viewGroup != null) {
            viewGroup.removeView(view2);
        }
        this.scrollTrack = null;
        this.scrollThumb = null;
        this.isInitialized = false;
    }
}
