package com.glasssutdio.wear.all.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import androidx.core.content.ContextCompat;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import java.util.Locale;

/* loaded from: classes.dex */
public class PcmWaveformView extends View {
    private static final int DISPLAY_DURATION_MS = 4000;
    private static final float MAX_OVERSCROLL_RATIO = 0.5f;
    private static final int MILLIS_INTERVAL = 500;
    private static final int SAFE_MARGIN = 20;
    private static final float WAVEFORM_HEIGHT_RATIO = 0.8f;
    private final Paint bgPaint;
    private final Paint centerLinePaint;
    private float currentPositionMs;
    private float density;
    private GestureDetector gestureDetector;
    private boolean isDragging;
    private SampleExtremes lastExtremes;
    private OnWaveformScrollListener listener;
    private long mDownTime;
    private float mDownX;
    private float mDownY;
    private final Rect mTextBounds;
    private short[] pcmData;
    private int sampleRate;
    private final Paint secondMarkerPaint;
    private int sparseFactor;
    private final Paint timePaint;
    private long totalDurationMs;
    private final Paint waveformPaint;

    public interface OnWaveformScrollListener {
        void onTimeChanged(int currentTimeMs);
    }

    private int calculateVisibleWidth(int width, float leftBlankRatio, float rightBlankRatio) {
        return (int) (width * ((1.0f - leftBlankRatio) - rightBlankRatio));
    }

    public void setSparseFactor(int factor) {
        this.sparseFactor = Math.max(1, factor);
        postInvalidate();
    }

    public PcmWaveformView(Context context) {
        super(context);
        this.mTextBounds = new Rect();
        this.pcmData = new short[0];
        this.sampleRate = 16000;
        this.currentPositionMs = 0.0f;
        this.totalDurationMs = 0L;
        this.isDragging = false;
        this.waveformPaint = new Paint();
        this.centerLinePaint = new Paint();
        this.timePaint = new Paint();
        this.bgPaint = new Paint();
        this.secondMarkerPaint = new Paint();
        this.sparseFactor = 10;
        init();
    }

    public PcmWaveformView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mTextBounds = new Rect();
        this.pcmData = new short[0];
        this.sampleRate = 16000;
        this.currentPositionMs = 0.0f;
        this.totalDurationMs = 0L;
        this.isDragging = false;
        this.waveformPaint = new Paint();
        this.centerLinePaint = new Paint();
        this.timePaint = new Paint();
        this.bgPaint = new Paint();
        this.secondMarkerPaint = new Paint();
        this.sparseFactor = 10;
        init();
    }

    public void setListener(OnWaveformScrollListener listener) {
        this.listener = listener;
    }

    private void init() {
        this.density = getResources().getDisplayMetrics().density;
        setupPaints();
        this.gestureDetector = new GestureDetector(getContext(), new WaveformGestureListener());
    }

    private void setupPaints() {
        this.waveformPaint.setColor(ContextCompat.getColor(getContext(), C0775R.color.g_album_record_2));
        this.waveformPaint.setStrokeWidth(this.density * 2.5f);
        this.waveformPaint.setAntiAlias(true);
        this.centerLinePaint.setColor(ContextCompat.getColor(getContext(), C0775R.color.g_album_record_1));
        this.centerLinePaint.setStrokeWidth(this.density * 1.0f);
        this.centerLinePaint.setAntiAlias(true);
        this.timePaint.setColor(ContextCompat.getColor(getContext(), C0775R.color.g_album_record_2));
        this.timePaint.setTextSize(this.density * 8.0f);
        this.timePaint.setAntiAlias(true);
        this.bgPaint.setColor(ContextCompat.getColor(getContext(), C0775R.color.g_album_record));
        this.bgPaint.setStyle(Paint.Style.FILL);
        this.secondMarkerPaint.setColor(ContextCompat.getColor(getContext(), C0775R.color.g_album_record_2));
        this.secondMarkerPaint.setStrokeWidth(this.density * 1.0f);
        this.secondMarkerPaint.setAntiAlias(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00e8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void drawSecondMarkers(Canvas canvas, int width, int height, DisplayParams params) {
        float f;
        char c;
        long j;
        if (params.dataEnd <= params.dataStart || width <= 40 || height <= 0) {
            return;
        }
        long j2 = 1000;
        long j3 = (params.endSample * 1000) / this.sampleRate;
        int i = width - 40;
        float f2 = width - 20;
        float fApplyDimension = TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics());
        long jMax = Math.max(0L, (((params.startSample * 1000) / this.sampleRate) / 500) * 500);
        int i2 = ((int) ((((j3 / 500) * 500) - jMax) / 500)) + 1;
        int i3 = 0;
        while (i3 < i2) {
            long j4 = (i3 * 500) + jMax;
            if (j4 > j3) {
                return;
            }
            int i4 = i2;
            long j5 = (this.sampleRate * j4) / j2;
            if (j5 < params.startSample || j5 >= params.endSample) {
                f = fApplyDimension;
                c = 0;
                j = 0;
            } else {
                float f3 = (i * ((j5 - params.startSample) / params.displaySamples)) + 20.0f;
                if (f3 >= 20.0f && f3 <= f2) {
                    long j6 = j4 % 1000;
                    j = 0;
                    canvas.drawLine(f3, 0.0f, f3, j6 == 0 ? 20 : 10, this.secondMarkerPaint);
                    if (j6 == 0) {
                        String time = formatTime(j4);
                        this.timePaint.getTextBounds(time, 0, time.length(), this.mTextBounds);
                        float fMeasureText = this.timePaint.measureText(time);
                        float fMin = Math.min(f3 - (fMeasureText / 2.0f), f2 - fMeasureText);
                        c = 0;
                        f = fApplyDimension;
                        canvas.drawText(time, Math.max(20.0f, fMin), f, this.timePaint);
                    } else {
                        f = fApplyDimension;
                        c = 0;
                    }
                }
            }
            i3++;
            fApplyDimension = f;
            i2 = i4;
            j2 = 1000;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001d  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0015 A[Catch: all -> 0x0029, TryCatch #0 {, blocks: (B:6:0x0005, B:7:0x0007, B:9:0x0015, B:11:0x001f), top: B:17:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized void setPcmData(short[] data, int sampleRate) {
        if (data == null) {
            data = new short[0];
            this.pcmData = data;
            int iMax = Math.max(1, sampleRate);
            this.sampleRate = iMax;
            this.totalDurationMs = this.pcmData.length <= 0 ? (r6.length * 1000) / iMax : 0L;
            resetPosition();
            postInvalidate();
        } else {
            this.pcmData = data;
            int iMax2 = Math.max(1, sampleRate);
            this.sampleRate = iMax2;
            this.totalDurationMs = this.pcmData.length <= 0 ? (r6.length * 1000) / iMax2 : 0L;
            resetPosition();
            postInvalidate();
        }
    }

    private void resetPosition() {
        this.currentPositionMs = -2000.0f;
    }

    public synchronized void setCurrentPosition(long positionMs) {
        this.currentPositionMs = constrainPosition(positionMs, -2000.0f, Math.max(0L, this.totalDurationMs - 4000) + 2000.0f);
        postInvalidate();
    }

    private float constrainPosition(float position, float min, float max) {
        return Math.max(min, Math.min(max, position));
    }

    @Override // android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!validateDrawConditions()) {
            drawEmptyState(canvas);
            return;
        }
        int width = getWidth();
        int height = getHeight();
        DisplayParams displayParamsCalculateDisplayParams = calculateDisplayParams(width);
        drawWaveform(canvas, width, height, height / 2, displayParamsCalculateDisplayParams);
        drawCenterLine(canvas, width, height);
        drawSecondMarkers(canvas, width, height, displayParamsCalculateDisplayParams);
    }

    private boolean validateDrawConditions() {
        short[] sArr = this.pcmData;
        return sArr != null && sArr.length > 0 && getWidth() > 40 && this.sampleRate > 0;
    }

    private DisplayParams calculateDisplayParams(int width) {
        DisplayParams displayParams = new DisplayParams();
        displayParams.displaySamples = Math.min((this.sampleRate * DISPLAY_DURATION_MS) / 1000, this.pcmData.length);
        displayParams.startSample = (int) ((this.currentPositionMs * this.sampleRate) / 1000.0f);
        displayParams.endSample = displayParams.startSample + displayParams.displaySamples;
        displayParams.leftBlankRatio = calculateBlankRatio(displayParams.startSample, displayParams.displaySamples, true);
        displayParams.rightBlankRatio = calculateBlankRatio(displayParams.endSample - this.pcmData.length, displayParams.displaySamples, false);
        displayParams.dataStart = Math.max(0, displayParams.startSample);
        displayParams.dataEnd = Math.min(this.pcmData.length, displayParams.endSample);
        return displayParams;
    }

    private float calculateBlankRatio(int overflow, int displaySamples, boolean isLeft) {
        if (!isLeft ? overflow > 0 : overflow < 0) {
            return 0.0f;
        }
        return constrain(Math.abs(overflow) / displaySamples, 0.0f, 1.0f);
    }

    private void drawWaveform(Canvas canvas, int width, int height, int centerY, DisplayParams params) {
        int iCalculateVisibleWidth;
        drawBlankAreas(canvas, width, height, params.leftBlankRatio, params.rightBlankRatio);
        if (params.dataEnd <= params.dataStart || (iCalculateVisibleWidth = calculateVisibleWidth(width, params.leftBlankRatio, params.rightBlankRatio)) <= 40) {
            return;
        }
        drawWaveformLines(canvas, width, height, centerY, params, iCalculateVisibleWidth);
    }

    private void drawBlankAreas(Canvas canvas, int width, int height, float leftBlankRatio, float rightBlankRatio) {
        if (leftBlankRatio > 0.0f) {
            canvas.drawRect(0.0f, 0.0f, width * leftBlankRatio, height, this.bgPaint);
        }
        if (rightBlankRatio > 0.0f) {
            float f = width;
            canvas.drawRect(f * (1.0f - rightBlankRatio), 0.0f, f, height, this.bgPaint);
        }
    }

    private void drawWaveformLines(Canvas canvas, int width, int height, int centerY, DisplayParams params, int visibleWidth) {
        int iMax = Math.max(1, ((params.dataEnd - params.dataStart) / visibleWidth) * this.sparseFactor);
        float f = width;
        float f2 = f * params.leftBlankRatio;
        int i = (int) f2;
        while (true) {
            float f3 = i;
            if (f3 >= (1.0f - params.rightBlankRatio) * f) {
                return;
            }
            if (i >= 20 && i <= width - 20) {
                WaveformHeight waveformHeightCalculateWaveformHeight = calculateWaveformHeight(params.dataStart, iMax, (f3 - f2) / this.sparseFactor, centerY, height);
                this.waveformPaint.setStrokeWidth(this.density * 1.5f);
                float f4 = height;
                canvas.drawLine(f3, constrain(waveformHeightCalculateWaveformHeight.top, 0.0f, f4), f3, constrain(waveformHeightCalculateWaveformHeight.bottom, 0.0f, f4), this.waveformPaint);
                this.waveformPaint.setStrokeWidth(this.density * 1.0f);
            }
            i += this.sparseFactor;
        }
    }

    private WaveformHeight calculateWaveformHeight(int dataStart, int samplesPerPixel, float xPos, int centerY, int viewHeight) {
        float f = samplesPerPixel;
        float f2 = dataStart + (xPos * f);
        float f3 = f * 0.5f;
        SampleExtremes sampleExtremesFindSampleExtremes = findSampleExtremes(Math.max(dataStart, (int) Math.floor(f2 - f3)), Math.min(this.pcmData.length, (int) Math.ceil(f2 + f3)));
        float fSignum = Math.signum(sampleExtremesFindSampleExtremes.max) * Math.abs(sampleExtremesFindSampleExtremes.max / 32767.0f);
        float fSignum2 = Math.signum(sampleExtremesFindSampleExtremes.min) * Math.abs(sampleExtremesFindSampleExtremes.min / 32767.0f);
        float f4 = viewHeight;
        float f5 = 0.45f * f4 * WAVEFORM_HEIGHT_RATIO;
        float f6 = centerY;
        float fMax = Math.max(0.0f, Math.min(f4, f6 - (fSignum * f5)));
        float fMax2 = Math.max(0.0f, Math.min(f4, f6 - (fSignum2 * f5)));
        float f7 = this.density;
        if (f7 > 0.0f) {
            fMax = ((int) (fMax * f7)) / f7;
            fMax2 = ((int) (fMax2 * f7)) / f7;
        }
        return new WaveformHeight(fMax, fMax2);
    }

    private SampleExtremes findSampleExtremes(int start, int end) {
        if (start >= end) {
            return new SampleExtremes((short) 0, (short) 0);
        }
        short sMax = this.pcmData[start];
        int iMax = Math.max(1, (end - start) / 100);
        int i = start;
        short sMin = sMax;
        while (i < end) {
            int i2 = i + iMax;
            int iMin = Math.min(i2, end);
            short sMax2 = this.pcmData[i];
            short sMin2 = sMax2;
            while (i < iMin) {
                sMax2 = (short) Math.max((int) sMax2, (int) this.pcmData[i]);
                sMin2 = (short) Math.min((int) sMin2, (int) this.pcmData[i]);
                i++;
            }
            sMax = (short) Math.max((int) sMax, (int) sMax2);
            sMin = (short) Math.min((int) sMin, (int) sMin2);
            i = i2;
        }
        for (int iMax2 = Math.max(start, end - iMax); iMax2 < end; iMax2++) {
            sMax = (short) Math.max((int) sMax, (int) this.pcmData[iMax2]);
            sMin = (short) Math.min((int) sMin, (int) this.pcmData[iMax2]);
        }
        return new SampleExtremes(sMax, sMin);
    }

    private void drawCenterLine(Canvas canvas, int width, int height) {
        int i = width / 2;
        if (i <= 20 || i >= width - 20) {
            return;
        }
        float f = i;
        canvas.drawLine(f, 0.0f, f, height, this.centerLinePaint);
    }

    private void drawEmptyState(Canvas canvas) {
        float fMeasureText = this.timePaint.measureText("no data");
        canvas.drawText("no data", constrain((getWidth() / 2.0f) - (fMeasureText / 2.0f), 20.0f, (getWidth() - fMeasureText) - 20.0f), constrain(getHeight() / 2.0f, 20.0f, getHeight() - 20), this.timePaint);
    }

    private void handleTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            this.mDownX = event.getX();
            this.mDownY = event.getY();
            this.mDownTime = System.currentTimeMillis();
            startDragging();
        } else if (action == 1 || action == 3) {
            float fAbs = Math.abs(event.getX() - this.mDownX);
            float fAbs2 = Math.abs(event.getY() - this.mDownY);
            long jCurrentTimeMillis = System.currentTimeMillis() - this.mDownTime;
            if (fAbs < 10.0f && fAbs2 < 10.0f && jCurrentTimeMillis < 500) {
                XLog.m137i("点击---");
            } else {
                stopDragging();
            }
        }
        GestureDetector gestureDetector = this.gestureDetector;
        if (gestureDetector != null) {
            gestureDetector.onTouchEvent(event);
        }
    }

    private void startDragging() {
        this.isDragging = true;
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    private void stopDragging() {
        this.isDragging = false;
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        OnWaveformScrollListener onWaveformScrollListener = this.listener;
        if (onWaveformScrollListener != null) {
            onWaveformScrollListener.onTimeChanged((int) (this.currentPositionMs + 2000.0f));
        }
    }

    private class WaveformGestureListener extends GestureDetector.SimpleOnGestureListener {
        private float accumulatedScroll;

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent e) {
            return true;
        }

        private WaveformGestureListener() {
            this.accumulatedScroll = 0.0f;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (!PcmWaveformView.this.isDragging) {
                return false;
            }
            this.accumulatedScroll += distanceX;
            if (Math.abs(this.accumulatedScroll) <= PcmWaveformView.this.density * 2.0f) {
                return true;
            }
            PcmWaveformView.this.updatePosition((this.accumulatedScroll * 4000.0f) / PcmWaveformView.this.getWidth());
            this.accumulatedScroll = 0.0f;
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (!PcmWaveformView.this.isDragging) {
                return false;
            }
            PcmWaveformView.this.updatePosition(((velocityX * 4000.0f) / PcmWaveformView.this.getWidth()) / 10.0f);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void updatePosition(float timeDeltaMs) {
        this.currentPositionMs = constrainPosition(this.currentPositionMs + timeDeltaMs, -2000.0f, Math.max(0L, this.totalDurationMs - 4000) + 2000.0f);
        postInvalidate();
    }

    private String formatTime(long milliseconds) {
        try {
            return String.format(Locale.US, "%02d:%02d", Integer.valueOf((int) ((milliseconds / 60000) % 60)), Integer.valueOf(((int) (milliseconds / 1000)) % 60));
        } catch (Exception unused) {
            return "--:--";
        }
    }

    private float constrain(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    private static class DisplayParams {
        int dataEnd;
        int dataStart;
        int displaySamples;
        int endSample;
        float leftBlankRatio;
        float rightBlankRatio;
        int startSample;

        private DisplayParams() {
        }
    }

    private static class SampleExtremes {
        short max;
        short min;

        SampleExtremes(short max, short min) {
            this.max = max;
            this.min = min;
        }
    }

    private static class WaveformHeight {
        final float bottom;
        final float top;

        WaveformHeight(float top, float bottom) {
            this.top = top;
            this.bottom = bottom;
        }
    }
}
