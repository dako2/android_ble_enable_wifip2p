package com.glasssutdio.wear.all.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.databinding.PasswordEdittextViewBinding;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PasswordEditText.kt */
@Metadata(m606d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ+\u0010\r\u001a\u00020\u000e2#\u0010\u000f\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u000e0\u0010J\u0006\u0010\u0015\u001a\u00020\u0016J\b\u0010\u0017\u001a\u00020\u000eH\u0014J\u000e\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m607d2 = {"Lcom/glasssutdio/wear/all/view/PasswordEditText;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/glasssutdio/wear/databinding/PasswordEdittextViewBinding;", "isPasswordVisible", "", "doAfterTextChanged", "", "action", "Lkotlin/Function1;", "Landroid/text/Editable;", "Lkotlin/ParameterName;", "name", "text", "getText", "", "onDetachedFromWindow", "setHint", "hint", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class PasswordEditText extends ConstraintLayout {
    private PasswordEdittextViewBinding binding;
    private boolean isPasswordVisible;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PasswordEditText(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PasswordEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ PasswordEditText(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PasswordEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        PasswordEdittextViewBinding passwordEdittextViewBindingInflate = PasswordEdittextViewBinding.inflate(LayoutInflater.from(context), this, true);
        Intrinsics.checkNotNullExpressionValue(passwordEdittextViewBindingInflate, "inflate(...)");
        this.binding = passwordEdittextViewBindingInflate;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0775R.styleable.PasswordEditText);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "obtainStyledAttributes(...)");
        final int resourceId = typedArrayObtainStyledAttributes.getResourceId(C0775R.styleable.PasswordEditText_pet_displayImg, C0775R.mipmap.ic_pwd_open);
        final int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(C0775R.styleable.PasswordEditText_pet_hideImg, C0775R.mipmap.ic_pwd_close);
        this.binding.etPwd.setTextSize(GlobalKt.pxToSp(context, typedArrayObtainStyledAttributes.getDimensionPixelSize(C0775R.styleable.PasswordEditText_pet_textSize, 16)));
        this.binding.etPwd.setText(typedArrayObtainStyledAttributes.getString(C0775R.styleable.PasswordEditText_pet_text));
        this.binding.etPwd.setHint(typedArrayObtainStyledAttributes.getString(C0775R.styleable.PasswordEditText_pet_hintText));
        this.binding.etPwd.setTextColor(typedArrayObtainStyledAttributes.getColor(C0775R.styleable.PasswordEditText_pet_textColor, ViewCompat.MEASURED_STATE_MASK));
        this.binding.etPwd.setHintTextColor(typedArrayObtainStyledAttributes.getColor(C0775R.styleable.PasswordEditText_pet_hintTextColor, -7829368));
        this.binding.etPwd.setBackgroundResource(typedArrayObtainStyledAttributes.getResourceId(C0775R.styleable.PasswordEditText_pet_bgRes, C0775R.drawable.bg_white_20_shape));
        typedArrayObtainStyledAttributes.recycle();
        this.binding.ivSeePwd.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.all.view.PasswordEditText$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PasswordEditText._init_$lambda$0(this.f$0, resourceId2, resourceId, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(PasswordEditText this$0, int i, int i2, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isPasswordVisible) {
            this$0.binding.etPwd.setInputType(129);
            this$0.binding.ivSeePwd.setImageResource(i);
            this$0.isPasswordVisible = false;
        } else {
            this$0.binding.etPwd.setInputType(145);
            this$0.binding.ivSeePwd.setImageResource(i2);
            this$0.isPasswordVisible = true;
        }
        this$0.binding.etPwd.setSelection(this$0.binding.etPwd.getText().length());
    }

    public final String getText() {
        return this.binding.etPwd.getText().toString();
    }

    public final void setHint(String hint) {
        Intrinsics.checkNotNullParameter(hint, "hint");
        this.binding.etPwd.setHint(hint);
    }

    public final void doAfterTextChanged(final Function1<? super Editable, Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        EditText etPwd = this.binding.etPwd;
        Intrinsics.checkNotNullExpressionValue(etPwd, "etPwd");
        etPwd.addTextChangedListener(new TextWatcher() { // from class: com.glasssutdio.wear.all.view.PasswordEditText$doAfterTextChanged$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                action.invoke(s);
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clearAnimation();
    }
}
