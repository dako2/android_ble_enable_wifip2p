package com.glasssutdio.wear.all;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: EditTextViewExt.kt */
@Metadata(m606d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a(\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0007\u001a\u001a\u0010\u0003\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0007\u001a\u001a\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0007\u001a\u001e\u0010\u0006\u001a\u00020\u0001*\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t\u001a\n\u0010\u000b\u001a\u00020\f*\u00020\u0007\u001a\n\u0010\u000b\u001a\u00020\f*\u00020\r\u001a\n\u0010\u000e\u001a\u00020\f*\u00020\u0007\u001a\n\u0010\u000e\u001a\u00020\f*\u00020\r\u001a\n\u0010\u000f\u001a\u00020\n*\u00020\u0007\u001a\n\u0010\u000f\u001a\u00020\n*\u00020\r\u001a\n\u0010\u0010\u001a\u00020\n*\u00020\u0007\u001a\n\u0010\u0011\u001a\u00020\n*\u00020\u0007\u001a\n\u0010\u0011\u001a\u00020\n*\u00020\r¨\u0006\u0012"}, m607d2 = {"action", "", "Landroid/view/View;", "actionDown", "Lkotlin/Function0;", "actionUp", "afterTextChange", "Landroid/widget/EditText;", "afterTextChanged", "Lkotlin/Function1;", "", "isEmpty", "", "Landroid/widget/TextView;", "isTrimEmpty", "textString", "textStringAllTrim", "textStringTrim", "app_release"}, m608k = 2, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class EditTextViewExtKt {
    public static final void afterTextChange(EditText editText, final Function1<? super String, Unit> afterTextChanged) {
        Intrinsics.checkNotNullParameter(editText, "<this>");
        Intrinsics.checkNotNullParameter(afterTextChanged, "afterTextChanged");
        editText.addTextChangedListener(new TextWatcher() { // from class: com.glasssutdio.wear.all.EditTextViewExtKt.afterTextChange.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                afterTextChanged.invoke(String.valueOf(s));
            }
        });
    }

    public static final void actionDown(View view, final Function0<Unit> actionDown) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(actionDown, "actionDown");
        view.setOnTouchListener(new View.OnTouchListener() { // from class: com.glasssutdio.wear.all.EditTextViewExtKt$$ExternalSyntheticLambda2
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return EditTextViewExtKt.actionDown$lambda$0(actionDown, view2, motionEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean actionDown$lambda$0(Function0 actionDown, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(actionDown, "$actionDown");
        if (motionEvent == null || motionEvent.getAction() != 0) {
            return false;
        }
        actionDown.invoke();
        return false;
    }

    public static final void actionUp(View view, final Function0<Unit> actionUp) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(actionUp, "actionUp");
        view.setOnTouchListener(new View.OnTouchListener() { // from class: com.glasssutdio.wear.all.EditTextViewExtKt$$ExternalSyntheticLambda1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return EditTextViewExtKt.actionUp$lambda$1(actionUp, view2, motionEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean actionUp$lambda$1(Function0 actionUp, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(actionUp, "$actionUp");
        if (motionEvent == null || motionEvent.getAction() != 1) {
            return false;
        }
        actionUp.invoke();
        return false;
    }

    public static final void action(View view, final Function0<Unit> actionDown, final Function0<Unit> actionUp) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(actionDown, "actionDown");
        Intrinsics.checkNotNullParameter(actionUp, "actionUp");
        view.setOnTouchListener(new View.OnTouchListener() { // from class: com.glasssutdio.wear.all.EditTextViewExtKt$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return EditTextViewExtKt.action$lambda$2(actionDown, actionUp, view2, motionEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean action$lambda$2(Function0 actionDown, Function0 actionUp, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(actionDown, "$actionDown");
        Intrinsics.checkNotNullParameter(actionUp, "$actionUp");
        if (motionEvent != null && motionEvent.getAction() == 0) {
            actionDown.invoke();
        }
        if (motionEvent == null || motionEvent.getAction() != 1) {
            return false;
        }
        actionUp.invoke();
        return false;
    }

    public static final String textString(EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "<this>");
        return editText.getText().toString();
    }

    public static final String textStringTrim(EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "<this>");
        return StringsKt.trim((CharSequence) textString(editText)).toString();
    }

    public static final String textStringAllTrim(EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "<this>");
        return StringExtKt.deleteWhitespace(textString(editText));
    }

    public static final boolean isEmpty(EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "<this>");
        return textString(editText).length() == 0;
    }

    public static final boolean isTrimEmpty(EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "<this>");
        return textStringTrim(editText).length() == 0;
    }

    public static final String textString(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        return textView.getText().toString();
    }

    public static final String textStringTrim(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        return StringsKt.trim((CharSequence) textString(textView)).toString();
    }

    public static final boolean isEmpty(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        return textString(textView).length() == 0;
    }

    public static final boolean isTrimEmpty(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        return textStringTrim(textView).length() == 0;
    }
}
