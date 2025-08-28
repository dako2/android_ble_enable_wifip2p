package com.glasssutdio.wear.all.view.rippleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.internal.view.SupportMenu;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.view.rippleview.cycle.RippleLifecycle;
import com.glasssutdio.wear.all.view.rippleview.cycle.RippleLifecycleAdapter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: RippleView.kt */
@Metadata(m606d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 ?2\u00020\u0001:\u0001?B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010$\u001a\u00020%H\u0002J\u0012\u0010&\u001a\u00020%2\b\u0010'\u001a\u0004\u0018\u00010(H\u0002J\u0012\u0010)\u001a\u00020%2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\b\u0010*\u001a\u00020%H\u0002J\b\u0010+\u001a\u00020%H\u0002J\u0018\u0010,\u001a\u00020\t2\u0006\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00020\tH\u0002J\u0010\u0010/\u001a\u00020%2\u0006\u00100\u001a\u000201H\u0014J\u0010\u00102\u001a\u00020%2\u0006\u00100\u001a\u000201H\u0002J\u0018\u00103\u001a\u00020%2\u0006\u00104\u001a\u00020\t2\u0006\u00105\u001a\u00020\tH\u0014J\u0006\u00106\u001a\u00020%J\u0006\u00107\u001a\u00020%J(\u00108\u001a\u00020%2\u0006\u00109\u001a\u00020\t2\u0006\u0010:\u001a\u00020\t2\u0006\u0010;\u001a\u00020\t2\u0006\u0010<\u001a\u00020\tH\u0014J\u0012\u0010=\u001a\u00020%2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(J\u0006\u0010>\u001a\u00020%R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00020\t8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001d\u001a\u00020\u001e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b\u001f\u0010 R\u000e\u0010#\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006@"}, m607d2 = {"Lcom/glasssutdio/wear/all/view/rippleview/RippleView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "circleCenterX", "", "circleCenterY", "circleColor", "circleCount", "circleList", "", "Lcom/glasssutdio/wear/all/view/rippleview/RippleCircle;", "circleMaxRadius", "circleMinRadius", "circleStrokeWidth", "circleStyle", "Landroid/graphics/Paint$Style;", "isPause", "", "isStart", "paint", "Landroid/graphics/Paint;", "rippleLifecycle", "Lcom/glasssutdio/wear/all/view/rippleview/cycle/RippleLifecycle;", "getRippleLifecycle", "()Lcom/glasssutdio/wear/all/view/rippleview/cycle/RippleLifecycle;", "rippleLifecycle$delegate", "Lkotlin/Lazy;", "speed", "addNewRippleCircle", "", "bindLifecycle", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "initAttributes", "initCircle", "initPaint", "measureSize", "measureSpec", "defaultSize", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onDrawRippleCircle", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onPause", "onResume", "onSizeChanged", "w", "h", "oldw", "oldh", "onStart", "onStop", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class RippleView extends View {
    private static final int MAX_ALPHA = 255;
    private float circleCenterX;
    private float circleCenterY;
    private int circleColor;
    private int circleCount;
    private List<RippleCircle> circleList;
    private int circleMaxRadius;
    private float circleMinRadius;
    private float circleStrokeWidth;
    private Paint.Style circleStyle;
    private boolean isPause;
    private boolean isStart;
    private Paint paint;

    /* renamed from: rippleLifecycle$delegate, reason: from kotlin metadata */
    private final Lazy rippleLifecycle;
    private float speed;

    public RippleView(Context context) {
        this(context, null);
    }

    public RippleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RippleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.paint = new Paint();
        this.circleList = new LinkedList();
        this.rippleLifecycle = LazyKt.lazy(new Function0<RippleLifecycle>() { // from class: com.glasssutdio.wear.all.view.rippleview.RippleView$rippleLifecycle$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RippleLifecycle invoke() {
                return new RippleLifecycle(this.this$0);
            }
        });
        this.circleColor = SupportMenu.CATEGORY_MASK;
        this.circleCount = 5;
        this.circleStyle = Paint.Style.FILL;
        this.speed = 0.5f;
        this.circleStrokeWidth = 3.0f;
        initAttributes(attributeSet);
        initPaint();
        if (this.isStart) {
            onStart$default(this, null, 1, null);
        }
    }

    private final RippleLifecycle getRippleLifecycle() {
        return (RippleLifecycle) this.rippleLifecycle.getValue();
    }

    private final void initAttributes(AttributeSet attrs) {
        Paint.Style style;
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attrs, C0775R.styleable.RippleView);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "obtainStyledAttributes(...)");
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == C0775R.styleable.RippleView_ripple_circle_color) {
                this.circleColor = typedArrayObtainStyledAttributes.getColor(index, SupportMenu.CATEGORY_MASK);
            } else if (index == C0775R.styleable.RippleView_ripple_circle_min_radius) {
                this.circleMinRadius = typedArrayObtainStyledAttributes.getDimension(index, 0.0f);
            } else if (index == C0775R.styleable.RippleView_ripple_circle_count) {
                this.circleCount = typedArrayObtainStyledAttributes.getInt(index, 2);
            } else if (index == C0775R.styleable.RippleView_ripple_speed) {
                this.speed = typedArrayObtainStyledAttributes.getFloat(index, this.speed);
            } else if (index == C0775R.styleable.RippleView_ripple_circle_stroke_width) {
                this.circleStrokeWidth = typedArrayObtainStyledAttributes.getDimension(index, this.circleStrokeWidth);
            } else if (index == C0775R.styleable.RippleView_ripple_circle_style) {
                if (typedArrayObtainStyledAttributes.getInt(index, Paint.Style.FILL.ordinal()) == Paint.Style.FILL.ordinal()) {
                    style = Paint.Style.FILL;
                } else {
                    style = Paint.Style.STROKE;
                }
                this.circleStyle = style;
            } else if (index == C0775R.styleable.RippleView_ripple_circle_start) {
                this.isStart = typedArrayObtainStyledAttributes.getBoolean(index, false);
            }
        }
    }

    private final void initCircle() {
        this.circleMaxRadius = (int) ((getWidth() / 2) - this.circleStrokeWidth);
        this.circleCenterX = getWidth() / 2.0f;
        this.circleCenterY = getHeight() / 2.0f;
        this.circleList.add(new RippleCircle(this.circleMinRadius, 255));
    }

    private final void initPaint() {
        this.paint.setStyle(this.circleStyle);
        this.paint.setStrokeWidth(this.circleStrokeWidth);
        this.paint.setDither(true);
        this.paint.setAntiAlias(true);
        this.paint.setColor(this.circleColor);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int iMin = Math.min(measureSize(widthMeasureSpec, 200), measureSize(heightMeasureSpec, 200));
        setMeasuredDimension(iMin, iMin);
    }

    private final int measureSize(int measureSpec, int defaultSize) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        int size = View.MeasureSpec.getSize(measureSpec);
        return mode == 1073741824 ? size : mode == Integer.MIN_VALUE ? RangesKt.coerceAtMost(defaultSize, size) : defaultSize;
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initCircle();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        onDrawRippleCircle(canvas);
    }

    private final void onDrawRippleCircle(Canvas canvas) {
        if (this.isStart) {
            Iterator<RippleCircle> it = this.circleList.iterator();
            while (it.hasNext()) {
                RippleCircle next = it.next();
                this.paint.setAlpha(next.getAlpha());
                canvas.drawCircle(this.circleCenterX, this.circleCenterY, next.getRadius(), this.paint);
                next.setRadius(next.getRadius() + this.speed);
                if (next.getRadius() > this.circleMaxRadius) {
                    it.remove();
                } else {
                    float f = 255;
                    next.setAlpha((int) (f - ((next.getRadius() / this.circleMaxRadius) * f)));
                }
            }
            addNewRippleCircle();
            postInvalidate();
        }
    }

    private final void addNewRippleCircle() {
        if (this.circleList.size() <= 0) {
            return;
        }
        if (((RippleCircle) CollectionsKt.last((List) this.circleList)).getRadius() > ((this.circleMaxRadius - this.circleMinRadius) / this.circleCount) + this.circleMinRadius) {
            this.circleList.add(new RippleCircle(this.circleMinRadius, 255));
        }
    }

    private final void bindLifecycle(LifecycleOwner lifecycleOwner) {
        Lifecycle lifecycle;
        if (lifecycleOwner == null || (lifecycle = lifecycleOwner.getLifecycle()) == null) {
            return;
        }
        lifecycle.addObserver(new RippleLifecycleAdapter(getRippleLifecycle()));
    }

    public static /* synthetic */ void onStart$default(RippleView rippleView, LifecycleOwner lifecycleOwner, int i, Object obj) {
        if ((i & 1) != 0) {
            lifecycleOwner = null;
        }
        rippleView.onStart(lifecycleOwner);
    }

    public final void onStart(LifecycleOwner lifecycleOwner) {
        bindLifecycle(lifecycleOwner);
        this.isStart = true;
        this.circleList.add(new RippleCircle(this.circleMinRadius, 255));
        postInvalidate();
    }

    public final void onResume() {
        if (this.isPause) {
            this.isStart = true;
            this.isPause = false;
            postInvalidate();
        }
    }

    public final void onStop() {
        this.isPause = false;
        this.isStart = false;
        this.circleList.clear();
    }

    public final void onPause() {
        if (this.isStart) {
            this.isPause = true;
            this.isStart = false;
        }
    }
}
