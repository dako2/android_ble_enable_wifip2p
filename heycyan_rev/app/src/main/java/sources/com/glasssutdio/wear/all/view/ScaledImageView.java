package com.glasssutdio.wear.all.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScaledImageView.kt */
@Metadata(m606d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0014J\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H\u0014R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m607d2 = {"Lcom/glasssutdio/wear/all/view/ScaledImageView;", "Landroidx/appcompat/widget/AppCompatImageView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "matrix", "Landroid/graphics/Matrix;", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class ScaledImageView extends AppCompatImageView {
    private final Matrix matrix;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ScaledImageView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ScaledImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ ScaledImageView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScaledImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.matrix = new Matrix();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int size = View.MeasureSpec.getSize(widthMeasureSpec);
            int mode = View.MeasureSpec.getMode(widthMeasureSpec);
            int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
            int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
            if (mode != 1073741824 || mode2 != 1073741824) {
                if (mode == 1073741824) {
                    intrinsicHeight = (int) (intrinsicHeight * (size / intrinsicWidth));
                    intrinsicWidth = size;
                } else if (mode2 == 1073741824) {
                    intrinsicWidth = (int) (intrinsicWidth * (size2 / intrinsicHeight));
                }
                setMeasuredDimension(intrinsicWidth, intrinsicHeight);
                return;
            }
            intrinsicWidth = size;
            intrinsicHeight = size2;
            setMeasuredDimension(intrinsicWidth, intrinsicHeight);
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Drawable drawable = getDrawable();
        if (drawable != null) {
            int width = getWidth();
            int height = getHeight();
            float f = width;
            float intrinsicWidth = drawable.getIntrinsicWidth();
            float f2 = f / intrinsicWidth;
            float f3 = height;
            float intrinsicHeight = drawable.getIntrinsicHeight();
            float f4 = f3 / intrinsicHeight;
            if (f2 >= f4) {
                f2 = f4;
            }
            this.matrix.reset();
            this.matrix.postScale(f2, f2);
            float f5 = f - (intrinsicWidth * f2);
            float f6 = 2;
            this.matrix.postTranslate(f5 / f6, (f3 - (intrinsicHeight * f2)) / f6);
            canvas.save();
            canvas.concat(this.matrix);
            drawable.draw(canvas);
            canvas.restore();
            return;
        }
        super.onDraw(canvas);
    }
}
