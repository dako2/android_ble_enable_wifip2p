package com.glasssutdio.wear.all.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public class BatteryImage extends View {
    private int batteryPercentage;
    private Context context;
    private int height;
    private boolean isCharging;
    private boolean isLowBattery;
    private float paddingImg;
    private Paint paint;
    private int width;

    public BatteryImage(Context context) {
        this(context, null);
        this.context = context;
    }

    public BatteryImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
    }

    public BatteryImage(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
        this.context = context;
    }

    public BatteryImage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.isCharging = false;
        this.isLowBattery = false;
        this.paddingImg = 3.0f;
        this.context = context;
        initView(context);
    }

    private void initView(Context context) {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setStrokeWidth(dp2px(context, 10.0f - this.paddingImg));
        this.paint.setColor(ContextCompat.getColor(context, C0775R.color.ble_text_1));
    }

    public void setBatteryPercentage(int percentage) {
        if (percentage < 0) {
            percentage = 0;
        } else if (percentage > 100) {
            percentage = 100;
        }
        if (this.batteryPercentage != percentage) {
            this.batteryPercentage = percentage;
            postInvalidate();
        }
    }

    public void isLowBattery(boolean isLow) {
        this.isLowBattery = isLow;
        postInvalidate();
    }

    public void isCharging(boolean value) {
        this.isCharging = value;
        postInvalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = ContextCompat.getDrawable(this.context, C0775R.mipmap.battery_rect);
        if (this.isLowBattery) {
            drawable = ContextCompat.getDrawable(this.context, C0775R.mipmap.ic_low_battery_stroke);
            this.paint.setColor(ContextCompat.getColor(this.context, C0775R.color.color_FF0303));
        } else {
            this.paint.setColor(ContextCompat.getColor(this.context, C0775R.color.ble_text_1));
        }
        if (drawable instanceof BitmapDrawable) {
            canvas.drawBitmap(((BitmapDrawable) drawable).getBitmap(), (Rect) null, new Rect(0, 0, (int) dp2px(this.context, 18.0f), (int) dp2px(this.context, 10.0f)), this.paint);
            int iMax = Math.max(0, Math.min(100, this.batteryPercentage));
            this.batteryPercentage = iMax;
            float fDp2px = (iMax * dp2px(this.context, 14.0f)) / 100.0f;
            float fHeight = r6.top + (r6.height() / 2.0f);
            Rect rect = new Rect(((int) dp2px(this.context, this.paddingImg)) / 2, 0, (int) dp2px(this.context, 18.0f - (this.paddingImg / 2.0f)), (int) dp2px(this.context, 10.0f));
            canvas.drawLine(rect.left, fHeight, rect.left + fDp2px, fHeight, this.paint);
            if (this.isCharging) {
                Drawable drawable2 = ContextCompat.getDrawable(this.context, C0775R.mipmap.ic_charging_battery);
                if (drawable2 instanceof BitmapDrawable) {
                    int iDp2px = (int) dp2px(this.context, 4.0f);
                    int iDp2px2 = (int) dp2px(this.context, 6.0f);
                    int iWidth = (int) ((r6.width() / 2.0f) - (iDp2px / 2.0f));
                    int iHeight = (int) ((r6.height() / 2.0f) - (iDp2px2 / 2.0f));
                    canvas.drawBitmap(((BitmapDrawable) drawable2).getBitmap(), (Rect) null, new Rect(iWidth, iHeight, iDp2px + iWidth, iDp2px2 + iHeight), this.paint);
                }
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.width = getMySize(50, widthMeasureSpec);
        if (getLayoutParams().height == -2) {
            this.height = (int) dp2px(getContext(), 30.0f);
        } else {
            this.height = getMySize(30, heightMeasureSpec);
        }
        setMeasuredDimension(this.width, this.height);
    }

    public static float dp2px(Context context, float dp) {
        return (dp * context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.getSize(measureSpec) : defaultSize;
    }
}
