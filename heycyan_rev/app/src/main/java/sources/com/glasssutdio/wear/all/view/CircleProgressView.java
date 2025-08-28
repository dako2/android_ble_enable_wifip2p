package com.glasssutdio.wear.all.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.core.content.ContextCompat;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import java.util.Random;

/* loaded from: classes.dex */
public class CircleProgressView extends View {
    private static final int ANIMATION_DURATION = 3000;
    private static final int END_PROGRESS = 100;
    private static final int START_PROGRESS = 0;
    private Context context;
    private Paint mBackgroundPaint;
    private ValueAnimator mEndAngleAnimator;
    private int mProgress;
    private ValueAnimator mProgressAnimator;
    private int mProgressBgColor;
    private int mProgressColor;
    private Paint mProgressPaint;
    private float mProgressStrokeWidth;
    private RectF mRectF;
    private ValueAnimator mRotationAnimator;
    private float mStartAngle;
    private ValueAnimator mStartAngleAnimator;
    private float mStrokeWidth;
    private float mSweepAngle;

    private static boolean isWhite(int r, int g, int b) {
        return r > 220 && g > 220 && b > 220;
    }

    private static boolean isYellow(int r, int g, int b) {
        return r > 200 && g > 200 && b < 100;
    }

    public CircleProgressView(Context context) {
        super(context);
        this.mStrokeWidth = 5.0f;
        this.mProgressStrokeWidth = 5.5f;
        this.mStartAngle = 0.0f;
        this.mSweepAngle = 0.0f;
        this.mProgress = 0;
        this.mProgressColor = 0;
        this.mProgressBgColor = -1;
        this.context = context;
        init(null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mStrokeWidth = 5.0f;
        this.mProgressStrokeWidth = 5.5f;
        this.mStartAngle = 0.0f;
        this.mSweepAngle = 0.0f;
        this.mProgress = 0;
        this.mProgressColor = 0;
        this.mProgressBgColor = -1;
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArrayObtainStyledAttributes = this.context.obtainStyledAttributes(attrs, C0775R.styleable.CircleProgressView);
            this.mStrokeWidth = typedArrayObtainStyledAttributes.getDimension(C0775R.styleable.CircleProgressView_strokeWidth, this.mStrokeWidth);
            this.mProgressColor = typedArrayObtainStyledAttributes.getColor(C0775R.styleable.CircleProgressView_cpv_progress_color, 0);
            this.mProgressBgColor = typedArrayObtainStyledAttributes.getColor(C0775R.styleable.CircleProgressView_cpv_progress_bg_color, ContextCompat.getColor(this.context, C0775R.color.loading_bg_color));
            this.mProgressStrokeWidth = this.mStrokeWidth + 0.5f;
            typedArrayObtainStyledAttributes.recycle();
        }
        Paint paint = new Paint();
        this.mBackgroundPaint = paint;
        paint.setColor(this.mProgressBgColor);
        this.mBackgroundPaint.setStrokeWidth(this.mStrokeWidth);
        this.mBackgroundPaint.setAntiAlias(true);
        this.mBackgroundPaint.setStyle(Paint.Style.STROKE);
        this.mBackgroundPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mBackgroundPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mProgressPaint = new Paint();
        int iGenerateRandomColorWithAlpha = generateRandomColorWithAlpha();
        int i = this.mProgressColor;
        if (i != 0) {
            iGenerateRandomColorWithAlpha = i;
        }
        this.mProgressPaint.setColor(iGenerateRandomColorWithAlpha);
        this.mProgressPaint.setStrokeWidth(this.mProgressStrokeWidth);
        this.mProgressPaint.setAntiAlias(true);
        this.mProgressPaint.setStyle(Paint.Style.STROKE);
        this.mProgressPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    public void setProgressColor(int color) {
        this.mProgressColor = color;
        invalidate();
    }

    public void setStrokeWith(int strokeWith) {
        float f = strokeWith;
        this.mStrokeWidth = f;
        this.mProgressStrokeWidth = f;
        this.mProgressPaint.setStrokeWidth(f);
        invalidate();
    }

    public int generateRandomColorWithAlpha() {
        return new int[]{ContextCompat.getColor(getContext(), C0775R.color.loading_color_1), ContextCompat.getColor(getContext(), C0775R.color.loading_color_2), ContextCompat.getColor(getContext(), C0775R.color.loading_color_3), ContextCompat.getColor(getContext(), C0775R.color.loading_color_4), ContextCompat.getColor(getContext(), C0775R.color.loading_color_5), ContextCompat.getColor(getContext(), C0775R.color.loading_color_6)}[new Random().nextInt(5)];
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float f = this.mStrokeWidth / 2.0f;
        this.mRectF = new RectF(f, f, w - f, h - f);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(this.mRectF, 0.0f, 360.0f, false, this.mBackgroundPaint);
        canvas.drawArc(this.mRectF, this.mStartAngle, this.mSweepAngle, false, this.mProgressPaint);
    }

    public void startRotation() {
        ValueAnimator valueAnimator = this.mRotationAnimator;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 360.0f);
            this.mRotationAnimator = valueAnimatorOfFloat;
            valueAnimatorOfFloat.setDuration(2000L);
            this.mRotationAnimator.setRepeatCount(-1);
            this.mRotationAnimator.setInterpolator(new LinearInterpolator());
            this.mRotationAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.glasssutdio.wear.all.view.CircleProgressView$$ExternalSyntheticLambda2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    this.f$0.m174x54e1468a(valueAnimator2);
                }
            });
            this.mRotationAnimator.start();
        }
    }

    /* renamed from: lambda$startRotation$0$com-glasssutdio-wear-all-view-CircleProgressView */
    /* synthetic */ void m174x54e1468a(ValueAnimator valueAnimator) {
        this.mStartAngle = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        invalidate();
    }

    public void setProgress(int progress) {
        this.mProgress = Math.max(0, Math.min(progress, 100));
        this.mSweepAngle = (r2 * 360) / 100.0f;
        invalidate();
    }

    public void stopRotation() {
        ValueAnimator valueAnimator = this.mRotationAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimator2 = this.mProgressAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        ValueAnimator valueAnimator3 = this.mStartAngleAnimator;
        if (valueAnimator3 != null) {
            valueAnimator3.cancel();
        }
        ValueAnimator valueAnimator4 = this.mEndAngleAnimator;
        if (valueAnimator4 != null) {
            valueAnimator4.cancel();
        }
    }

    public void startProgressAnimation() {
        ValueAnimator valueAnimator = this.mProgressAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            XLog.m137i(" startProgressAnimation return");
            return;
        }
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(0, 100);
        this.mProgressAnimator = valueAnimatorOfInt;
        valueAnimatorOfInt.setDuration(3000L);
        this.mProgressAnimator.setRepeatCount(-1);
        this.mProgressAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        this.mProgressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.glasssutdio.wear.all.view.CircleProgressView$$ExternalSyntheticLambda3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                this.f$0.m173x9fdacf1a(valueAnimator2);
            }
        });
        this.mProgressAnimator.start();
        startEndpointsRotation();
    }

    /* renamed from: lambda$startProgressAnimation$1$com-glasssutdio-wear-all-view-CircleProgressView */
    /* synthetic */ void m173x9fdacf1a(ValueAnimator valueAnimator) {
        try {
            setProgress(((Integer) valueAnimator.getAnimatedValue()).intValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startEndpointsRotation() {
        ValueAnimator valueAnimator = this.mStartAngleAnimator;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            ValueAnimator valueAnimator2 = this.mEndAngleAnimator;
            if (valueAnimator2 == null || !valueAnimator2.isRunning()) {
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 360.0f);
                this.mStartAngleAnimator = valueAnimatorOfFloat;
                valueAnimatorOfFloat.setDuration(3000L);
                this.mStartAngleAnimator.setRepeatCount(-1);
                this.mStartAngleAnimator.setInterpolator(new LinearInterpolator());
                this.mStartAngleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.glasssutdio.wear.all.view.CircleProgressView$$ExternalSyntheticLambda0
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator3) {
                        this.f$0.m171x6b43a1d4(valueAnimator3);
                    }
                });
                this.mStartAngleAnimator.start();
                ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, 360.0f);
                this.mEndAngleAnimator = valueAnimatorOfFloat2;
                valueAnimatorOfFloat2.setDuration(3000L);
                this.mEndAngleAnimator.setRepeatCount(-1);
                this.mEndAngleAnimator.setInterpolator(new LinearInterpolator());
                this.mEndAngleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.glasssutdio.wear.all.view.CircleProgressView$$ExternalSyntheticLambda1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator3) {
                        this.f$0.m172x5ced47f3(valueAnimator3);
                    }
                });
                this.mEndAngleAnimator.start();
            }
        }
    }

    /* renamed from: lambda$startEndpointsRotation$2$com-glasssutdio-wear-all-view-CircleProgressView */
    /* synthetic */ void m171x6b43a1d4(ValueAnimator valueAnimator) {
        this.mStartAngle = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        invalidate();
    }

    /* renamed from: lambda$startEndpointsRotation$3$com-glasssutdio-wear-all-view-CircleProgressView */
    /* synthetic */ void m172x5ced47f3(ValueAnimator valueAnimator) {
        ((Float) valueAnimator.getAnimatedValue()).floatValue();
        invalidate();
    }
}
