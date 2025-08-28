package com.glasssutdio.wear.all.view;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import com.glasssutdio.wear.C0775R;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: VerificationCodeEditText.kt */
@Metadata(m606d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\r\n\u0002\b\u0012\u0018\u0000 =2\u00020\u0001:\u0003<=>B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001f\u001a\u00020\u001aH\u0002J\b\u0010 \u001a\u00020\u001aH\u0014J\b\u0010!\u001a\u00020\u001aH\u0014J\u0010\u0010\"\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0014J\"\u0010#\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u00072\b\u0010&\u001a\u0004\u0018\u00010'H\u0014J\u0018\u0010(\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0007H\u0014J*\u0010+\u001a\u00020\u001a2\b\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u00072\u0006\u00100\u001a\u00020\u0007H\u0014J\u0010\u00101\u001a\u00020\u00172\u0006\u00102\u001a\u00020\u0007H\u0016J\u0010\u00103\u001a\u00020\u001a2\u0006\u00104\u001a\u00020\u0017H\u0016J\b\u00105\u001a\u00020\u001aH\u0002J\u0010\u00106\u001a\u00020\u001a2\u0006\u00107\u001a\u00020\u0007H\u0002J\u000e\u00108\u001a\u00020\u001a2\u0006\u00109\u001a\u00020\nJ\b\u0010:\u001a\u00020\u0017H\u0002J\b\u0010;\u001a\u00020\u001aH\u0002R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0018\u00010\fR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006?"}, m607d2 = {"Lcom/glasssutdio/wear/all/view/VerificationCodeEditText;", "Landroidx/appcompat/widget/AppCompatEditText;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "inputTextListener", "Lcom/glasssutdio/wear/all/view/VerificationCodeEditText$OnInputTextListener;", "mBlink", "Lcom/glasssutdio/wear/all/view/VerificationCodeEditText$Blink;", "mCodeBackground", "Landroid/graphics/drawable/Drawable;", "mCodeHeight", "mCodeLength", "mCodeMargin", "", "mCodeWidth", "mCursorDrawable", "mCursorDrawableRes", "mCursorFlag", "", "mCursorVisible", "drawBackground", "", "canvas", "Landroid/graphics/Canvas;", "drawCursor", "drawText", "makeBlink", "onAttachedToWindow", "onDetachedFromWindow", "onDraw", "onFocusChanged", "focused", "direction", "previouslyFocusedRect", "Landroid/graphics/Rect;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onTextChanged", "text", "", "start", "lengthBefore", "lengthAfter", "onTextContextMenuItem", BreakpointSQLiteKey.f521ID, "onWindowFocusChanged", "hasWindowFocus", "resumeBlink", "setMaxLength", "maxLength", "setOnInputTextListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "shouldBlink", "suspendBlink", "Blink", "Companion", "OnInputTextListener", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class VerificationCodeEditText extends AppCompatEditText {
    public static final long BLINK = 500;
    public static final int DEFAULT_CODE_HEIGHT = 150;
    public static final int DEFAULT_CODE_LENGTH = 6;
    public static final float DEFAULT_CODE_MARGIN = 20.0f;
    public static final int DEFAULT_CODE_WIDTH = 150;
    private OnInputTextListener inputTextListener;
    private Blink mBlink;
    private Drawable mCodeBackground;
    private int mCodeHeight;
    private int mCodeLength;
    private float mCodeMargin;
    private int mCodeWidth;
    private Drawable mCursorDrawable;
    private int mCursorDrawableRes;
    private boolean mCursorFlag;
    private boolean mCursorVisible;

    /* compiled from: VerificationCodeEditText.kt */
    @Metadata(m606d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m607d2 = {"Lcom/glasssutdio/wear/all/view/VerificationCodeEditText$OnInputTextListener;", "", "onInputTextComplete", "", "text", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public interface OnInputTextListener {
        void onInputTextComplete(CharSequence text);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VerificationCodeEditText(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VerificationCodeEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // androidx.appcompat.widget.AppCompatEditText, android.widget.EditText, android.widget.TextView
    public boolean onTextContextMenuItem(int id) {
        return false;
    }

    public /* synthetic */ VerificationCodeEditText(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VerificationCodeEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mCodeLength = 6;
        this.mCodeMargin = 20.0f;
        this.mCodeWidth = 150;
        this.mCodeHeight = 150;
        this.mCursorVisible = true;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0775R.styleable.VerificationCodeEditText);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "obtainStyledAttributes(...)");
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i2);
            if (index == C0775R.styleable.VerificationCodeEditText_codeLength) {
                this.mCodeLength = typedArrayObtainStyledAttributes.getInteger(index, 6);
            } else if (index == C0775R.styleable.VerificationCodeEditText_codeBackground) {
                this.mCodeBackground = typedArrayObtainStyledAttributes.getDrawable(index);
            } else if (index == C0775R.styleable.VerificationCodeEditText_codeMargin) {
                this.mCodeMargin = typedArrayObtainStyledAttributes.getDimension(index, 20.0f);
            } else if (index == C0775R.styleable.VerificationCodeEditText_codeWidth) {
                this.mCodeWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 150);
            } else if (index == C0775R.styleable.VerificationCodeEditText_codeCursorVisible) {
                this.mCursorVisible = typedArrayObtainStyledAttributes.getBoolean(index, false);
            } else if (index == C0775R.styleable.VerificationCodeEditText_codeCursorDrawable) {
                this.mCursorDrawableRes = typedArrayObtainStyledAttributes.getResourceId(index, 0);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        if (this.mCodeLength <= 0) {
            throw new IllegalArgumentException("code length must large than 0!!!");
        }
        if (this.mCodeBackground == null) {
            throw new NullPointerException("code background drawable not allowed to be null!!!");
        }
        if (this.mCursorVisible && this.mCursorDrawable == null && this.mCursorDrawableRes == 0) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(ContextCompat.getColor(context, C0775R.color.black));
            gradientDrawable.setSize((int) (context.getResources().getDisplayMetrics().density * 1), 0);
            this.mCursorDrawable = gradientDrawable;
        }
        setLongClickable(false);
        setCursorVisible(false);
        setMaxLength(this.mCodeLength);
        setBackgroundColor(0);
    }

    private final void setMaxLength(int maxLength) {
        if (maxLength >= 0) {
            setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        if (mode == Integer.MIN_VALUE) {
            this.mCodeHeight = this.mCodeWidth;
            int i = this.mCodeLength;
            setMeasuredDimension(View.MeasureSpec.makeMeasureSpec((int) ((r5 * i) + ((i - 1) * this.mCodeMargin)), 1073741824), View.MeasureSpec.makeMeasureSpec(this.mCodeHeight, 1073741824));
            return;
        }
        float f = size;
        float f2 = this.mCodeMargin;
        int i2 = (int) ((f - (f2 * (r1 - 1))) / this.mCodeLength);
        this.mCodeWidth = i2;
        this.mCodeHeight = i2;
        setMeasuredDimension(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(i2, 1073741824));
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        drawBackground(canvas);
        drawText(canvas);
        drawCursor(canvas);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        resumeBlink();
    }

    @Override // androidx.appcompat.widget.AppCompatEditText, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        suspendBlink();
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (text != null) {
            if (text.length() >= this.mCodeLength) {
                suspendBlink();
                Object systemService = getContext().getSystemService("input_method");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                ((InputMethodManager) systemService).hideSoftInputFromWindow(getWindowToken(), 0);
                OnInputTextListener onInputTextListener = this.inputTextListener;
                if (onInputTextListener != null) {
                    onInputTextListener.onInputTextComplete(text);
                    return;
                }
                return;
            }
            if (text.length() + 1 == this.mCodeLength && lengthBefore == 1) {
                resumeBlink();
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            Blink blink = this.mBlink;
            if (blink != null) {
                blink.unCancel();
            }
            makeBlink();
            return;
        }
        Blink blink2 = this.mBlink;
        if (blink2 != null) {
            blink2.cancel();
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            makeBlink();
        }
    }

    private final void drawBackground(Canvas canvas) {
        Drawable drawable = this.mCodeBackground;
        if (drawable != null) {
            int iCoerceAtLeast = RangesKt.coerceAtLeast(0, getEditableText().length());
            int iSave = canvas.save();
            int i = this.mCodeLength;
            for (int i2 = 0; i2 < i; i2++) {
                drawable.setBounds(new Rect(0, 0, this.mCodeWidth, this.mCodeHeight));
                if (iCoerceAtLeast == i2) {
                    drawable.setState(new int[]{R.attr.state_selected});
                } else {
                    drawable.setState(new int[]{R.attr.state_enabled});
                }
                drawable.draw(canvas);
                canvas.translate(this.mCodeWidth + this.mCodeMargin, 0.0f);
            }
            canvas.restoreToCount(iSave);
        }
    }

    private final void drawText(Canvas canvas) {
        int iSave = canvas.save();
        canvas.translate(0.0f, 0.0f);
        int currentTextColor = getCurrentTextColor();
        int length = getEditableText().length();
        for (int i = 0; i < length; i++) {
            float fMeasureText = getPaint().measureText(String.valueOf(getEditableText().charAt(i)));
            Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
            getPaint().getFontMetrics(fontMetrics);
            getPaint().setColor(currentTextColor);
            int i2 = this.mCodeWidth;
            canvas.drawText(String.valueOf(getEditableText().charAt(i)), (((i2 + this.mCodeMargin) * i) + (i2 / 2.0f)) - (fMeasureText / 2.0f), (this.mCodeHeight / 2.0f) - ((fontMetrics.top + fontMetrics.bottom) / 2.0f), getPaint());
        }
        canvas.restoreToCount(iSave);
    }

    private final void drawCursor(Canvas canvas) {
        if (this.mCursorVisible) {
            boolean z = this.mCursorFlag;
            this.mCursorFlag = !z;
            if (z) {
                return;
            }
            if (this.mCursorDrawable == null && this.mCursorDrawableRes != 0) {
                this.mCursorDrawable = getContext().getResources().getDrawable(this.mCursorDrawableRes);
            }
            Drawable drawable = this.mCursorDrawable;
            if (drawable != null) {
                int iCoerceAtLeast = RangesKt.coerceAtLeast(0, getEditableText().length());
                int iSave = canvas.save();
                int lineForOffset = getLayout().getLineForOffset(getSelectionStart());
                int lineTop = getLayout().getLineTop(lineForOffset);
                int lineBottom = getLayout().getLineBottom(lineForOffset);
                Rect rect = new Rect();
                drawable.getPadding(rect);
                drawable.setBounds(new Rect(0, lineTop - rect.top, drawable.getIntrinsicWidth(), lineBottom + rect.bottom));
                int i = this.mCodeWidth;
                canvas.translate((((i + this.mCodeMargin) * iCoerceAtLeast) + (i / 2.0f)) - (drawable.getIntrinsicWidth() / 2.0f), (this.mCodeHeight - drawable.getBounds().height()) / 2.0f);
                drawable.draw(canvas);
                canvas.restoreToCount(iSave);
            }
        }
    }

    private final void suspendBlink() {
        Blink blink = this.mBlink;
        if (blink != null) {
            blink.cancel();
        }
    }

    private final void resumeBlink() {
        Blink blink = this.mBlink;
        if (blink != null) {
            if (blink != null) {
                blink.unCancel();
            }
            makeBlink();
        }
    }

    private final void makeBlink() {
        if (shouldBlink()) {
            if (this.mBlink == null) {
                this.mBlink = new Blink();
            }
            removeCallbacks(this.mBlink);
            postDelayed(this.mBlink, 500L);
            return;
        }
        Blink blink = this.mBlink;
        if (blink != null) {
            removeCallbacks(blink);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldBlink() {
        int selectionStart;
        int selectionEnd;
        return this.mCursorVisible && isFocused() && (selectionStart = getSelectionStart()) >= 0 && (selectionEnd = getSelectionEnd()) >= 0 && selectionStart == selectionEnd;
    }

    /* compiled from: VerificationCodeEditText.kt */
    @Metadata(m606d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u0006\u0010\b\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/all/view/VerificationCodeEditText$Blink;", "Ljava/lang/Runnable;", "(Lcom/glasssutdio/wear/all/view/VerificationCodeEditText;)V", "mCancelled", "", "cancel", "", "run", "unCancel", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class Blink implements Runnable {
        private boolean mCancelled;

        public Blink() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mCancelled) {
                return;
            }
            Blink blink = this;
            VerificationCodeEditText.this.removeCallbacks(blink);
            if (VerificationCodeEditText.this.shouldBlink()) {
                if (VerificationCodeEditText.this.getLayout() != null) {
                    VerificationCodeEditText.this.invalidate();
                }
                VerificationCodeEditText.this.postDelayed(blink, 500L);
            }
        }

        public final void cancel() {
            if (this.mCancelled) {
                return;
            }
            VerificationCodeEditText.this.removeCallbacks(this);
            this.mCancelled = true;
        }

        public final void unCancel() {
            this.mCancelled = false;
        }
    }

    public final void setOnInputTextListener(OnInputTextListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.inputTextListener = listener;
    }
}
