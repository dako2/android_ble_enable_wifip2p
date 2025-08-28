package androidx.constraintlayout.core.dsl;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpStatus;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.config.CookieSpecs;

/* loaded from: classes.dex */
public class Transition {
    private String mConstraintSetEnd;
    private String mConstraintSetStart;
    private String mId;
    private OnSwipe mOnSwipe = null;
    final int UNSET = -1;
    private final int DEFAULT_DURATION = HttpStatus.SC_BAD_REQUEST;
    private final float DEFAULT_STAGGER = 0.0f;
    private int mDefaultInterpolator = 0;
    private String mDefaultInterpolatorString = null;
    private int mDefaultInterpolatorID = -1;
    private int mDuration = HttpStatus.SC_BAD_REQUEST;
    private float mStagger = 0.0f;
    private KeyFrames mKeyFrames = new KeyFrames();

    public void setOnSwipe(OnSwipe onSwipe) {
        this.mOnSwipe = onSwipe;
    }

    public void setKeyFrames(Keys keys) {
        this.mKeyFrames.add(keys);
    }

    public Transition(String str, String str2) {
        this.mId = null;
        this.mConstraintSetEnd = null;
        this.mConstraintSetStart = null;
        this.mId = CookieSpecs.DEFAULT;
        this.mConstraintSetStart = str;
        this.mConstraintSetEnd = str2;
    }

    public Transition(String str, String str2, String str3) {
        this.mId = null;
        this.mConstraintSetEnd = null;
        this.mConstraintSetStart = null;
        this.mId = str;
        this.mConstraintSetStart = str2;
        this.mConstraintSetEnd = str3;
    }

    String toJson() {
        return toString();
    }

    public void setId(String str) {
        this.mId = str;
    }

    public void setTo(String str) {
        this.mConstraintSetEnd = str;
    }

    public void setFrom(String str) {
        this.mConstraintSetStart = str;
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }

    public void setStagger(float f) {
        this.mStagger = f;
    }

    public String toString() {
        String str = this.mId + ":{\nfrom:'" + this.mConstraintSetStart + "',\nto:'" + this.mConstraintSetEnd + "',\n";
        if (this.mDuration != 400) {
            str = str + "duration:" + this.mDuration + ",\n";
        }
        if (this.mStagger != 0.0f) {
            str = str + "stagger:" + this.mStagger + ",\n";
        }
        if (this.mOnSwipe != null) {
            str = str + this.mOnSwipe.toString();
        }
        return (str + this.mKeyFrames.toString()) + "},\n";
    }

    public String getId() {
        return this.mId;
    }
}
