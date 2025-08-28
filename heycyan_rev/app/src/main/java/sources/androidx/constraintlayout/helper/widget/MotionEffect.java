package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.widget.Debug;
import androidx.constraintlayout.motion.widget.Key;
import androidx.constraintlayout.motion.widget.KeyAttributes;
import androidx.constraintlayout.motion.widget.KeyPosition;
import androidx.constraintlayout.motion.widget.MotionController;
import androidx.constraintlayout.motion.widget.MotionHelper;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.C0171R;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.HashMap;

/* loaded from: classes.dex */
public class MotionEffect extends MotionHelper {
    public static final int AUTO = -1;
    public static final int EAST = 2;
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final String TAG = "FadeMove";
    private static final int UNSET = -1;
    public static final int WEST = 3;
    private int mFadeMove;
    private float mMotionEffectAlpha;
    private int mMotionEffectEnd;
    private int mMotionEffectStart;
    private boolean mMotionEffectStrictMove;
    private int mMotionEffectTranslationX;
    private int mMotionEffectTranslationY;
    private int mViewTransitionId;

    @Override // androidx.constraintlayout.motion.widget.MotionHelper, androidx.constraintlayout.motion.widget.MotionHelperInterface
    public boolean isDecorator() {
        return true;
    }

    public MotionEffect(Context context) {
        super(context);
        this.mMotionEffectAlpha = 0.1f;
        this.mMotionEffectStart = 49;
        this.mMotionEffectEnd = 50;
        this.mMotionEffectTranslationX = 0;
        this.mMotionEffectTranslationY = 0;
        this.mMotionEffectStrictMove = true;
        this.mViewTransitionId = -1;
        this.mFadeMove = -1;
    }

    public MotionEffect(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMotionEffectAlpha = 0.1f;
        this.mMotionEffectStart = 49;
        this.mMotionEffectEnd = 50;
        this.mMotionEffectTranslationX = 0;
        this.mMotionEffectTranslationY = 0;
        this.mMotionEffectStrictMove = true;
        this.mViewTransitionId = -1;
        this.mFadeMove = -1;
        init(context, attributeSet);
    }

    public MotionEffect(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMotionEffectAlpha = 0.1f;
        this.mMotionEffectStart = 49;
        this.mMotionEffectEnd = 50;
        this.mMotionEffectTranslationX = 0;
        this.mMotionEffectTranslationY = 0;
        this.mMotionEffectStrictMove = true;
        this.mViewTransitionId = -1;
        this.mFadeMove = -1;
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0171R.styleable.MotionEffect);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == C0171R.styleable.MotionEffect_motionEffect_start) {
                    int i2 = typedArrayObtainStyledAttributes.getInt(index, this.mMotionEffectStart);
                    this.mMotionEffectStart = i2;
                    this.mMotionEffectStart = Math.max(Math.min(i2, 99), 0);
                } else if (index == C0171R.styleable.MotionEffect_motionEffect_end) {
                    int i3 = typedArrayObtainStyledAttributes.getInt(index, this.mMotionEffectEnd);
                    this.mMotionEffectEnd = i3;
                    this.mMotionEffectEnd = Math.max(Math.min(i3, 99), 0);
                } else if (index == C0171R.styleable.MotionEffect_motionEffect_translationX) {
                    this.mMotionEffectTranslationX = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.mMotionEffectTranslationX);
                } else if (index == C0171R.styleable.MotionEffect_motionEffect_translationY) {
                    this.mMotionEffectTranslationY = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.mMotionEffectTranslationY);
                } else if (index == C0171R.styleable.MotionEffect_motionEffect_alpha) {
                    this.mMotionEffectAlpha = typedArrayObtainStyledAttributes.getFloat(index, this.mMotionEffectAlpha);
                } else if (index == C0171R.styleable.MotionEffect_motionEffect_move) {
                    this.mFadeMove = typedArrayObtainStyledAttributes.getInt(index, this.mFadeMove);
                } else if (index == C0171R.styleable.MotionEffect_motionEffect_strict) {
                    this.mMotionEffectStrictMove = typedArrayObtainStyledAttributes.getBoolean(index, this.mMotionEffectStrictMove);
                } else if (index == C0171R.styleable.MotionEffect_motionEffect_viewTransition) {
                    this.mViewTransitionId = typedArrayObtainStyledAttributes.getResourceId(index, this.mViewTransitionId);
                }
            }
            int i4 = this.mMotionEffectStart;
            int i5 = this.mMotionEffectEnd;
            if (i4 == i5) {
                if (i4 > 0) {
                    this.mMotionEffectStart = i4 - 1;
                } else {
                    this.mMotionEffectEnd = i5 + 1;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x0188, code lost:
    
        if (r14 == 0.0f) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x019b, code lost:
    
        if (r14 == 0.0f) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01ab, code lost:
    
        if (r15 == 0.0f) goto L56;
     */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01e6  */
    @Override // androidx.constraintlayout.motion.widget.MotionHelper, androidx.constraintlayout.motion.widget.MotionHelperInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onPreSetup(MotionLayout motionLayout, HashMap<View, MotionController> map) {
        Key keyAttributes;
        Key keyAttributes2;
        Key keyAttributes3;
        int i;
        HashMap<View, MotionController> map2 = map;
        View[] views = getViews((ConstraintLayout) getParent());
        if (views == null) {
            Log.v(TAG, Debug.getLoc() + " views = null");
            return;
        }
        Key keyAttributes4 = new KeyAttributes();
        Key keyAttributes5 = new KeyAttributes();
        keyAttributes4.setValue("alpha", Float.valueOf(this.mMotionEffectAlpha));
        keyAttributes5.setValue("alpha", Float.valueOf(this.mMotionEffectAlpha));
        keyAttributes4.setFramePosition(this.mMotionEffectStart);
        keyAttributes5.setFramePosition(this.mMotionEffectEnd);
        KeyPosition keyPosition = new KeyPosition();
        keyPosition.setFramePosition(this.mMotionEffectStart);
        keyPosition.setType(0);
        keyPosition.setValue("percentX", 0);
        keyPosition.setValue("percentY", 0);
        KeyPosition keyPosition2 = new KeyPosition();
        keyPosition2.setFramePosition(this.mMotionEffectEnd);
        keyPosition2.setType(0);
        keyPosition2.setValue("percentX", 1);
        keyPosition2.setValue("percentY", 1);
        Key keyAttributes6 = null;
        if (this.mMotionEffectTranslationX > 0) {
            keyAttributes = new KeyAttributes();
            keyAttributes2 = new KeyAttributes();
            keyAttributes.setValue("translationX", Integer.valueOf(this.mMotionEffectTranslationX));
            keyAttributes.setFramePosition(this.mMotionEffectEnd);
            keyAttributes2.setValue("translationX", 0);
            keyAttributes2.setFramePosition(this.mMotionEffectEnd - 1);
        } else {
            keyAttributes = null;
            keyAttributes2 = null;
        }
        if (this.mMotionEffectTranslationY > 0) {
            keyAttributes6 = new KeyAttributes();
            keyAttributes3 = new KeyAttributes();
            keyAttributes6.setValue("translationY", Integer.valueOf(this.mMotionEffectTranslationY));
            keyAttributes6.setFramePosition(this.mMotionEffectEnd);
            keyAttributes3.setValue("translationY", 0);
            keyAttributes3.setFramePosition(this.mMotionEffectEnd - 1);
        } else {
            keyAttributes3 = null;
        }
        int i2 = this.mFadeMove;
        if (i2 == -1) {
            int[] iArr = new int[4];
            for (View view : views) {
                MotionController motionController = map2.get(view);
                if (motionController != null) {
                    float finalX = motionController.getFinalX() - motionController.getStartX();
                    float finalY = motionController.getFinalY() - motionController.getStartY();
                    if (finalY < 0.0f) {
                        iArr[1] = iArr[1] + 1;
                    }
                    if (finalY > 0.0f) {
                        iArr[0] = iArr[0] + 1;
                    }
                    if (finalX > 0.0f) {
                        iArr[3] = iArr[3] + 1;
                    }
                    if (finalX < 0.0f) {
                        iArr[2] = iArr[2] + 1;
                    }
                }
            }
            int i3 = iArr[0];
            i2 = 0;
            for (int i4 = 1; i4 < 4; i4++) {
                int i5 = iArr[i4];
                if (i3 < i5) {
                    i2 = i4;
                    i3 = i5;
                }
            }
        }
        int i6 = 0;
        while (i6 < views.length) {
            MotionController motionController2 = map2.get(views[i6]);
            if (motionController2 != null) {
                float finalX2 = motionController2.getFinalX() - motionController2.getStartX();
                float finalY2 = motionController2.getFinalY() - motionController2.getStartY();
                if (i2 == 0) {
                    if (finalY2 > 0.0f) {
                        if (this.mMotionEffectStrictMove) {
                        }
                    }
                    i = this.mViewTransitionId;
                    if (i != -1) {
                        motionController2.addKey(keyAttributes4);
                        motionController2.addKey(keyAttributes5);
                        motionController2.addKey(keyPosition);
                        motionController2.addKey(keyPosition2);
                        if (this.mMotionEffectTranslationX > 0) {
                            motionController2.addKey(keyAttributes);
                            motionController2.addKey(keyAttributes2);
                        }
                        if (this.mMotionEffectTranslationY > 0) {
                            motionController2.addKey(keyAttributes6);
                            motionController2.addKey(keyAttributes3);
                        }
                    } else {
                        motionLayout.applyViewTransition(i, motionController2);
                    }
                } else if (i2 == 1) {
                    if (finalY2 < 0.0f) {
                        if (this.mMotionEffectStrictMove) {
                        }
                    }
                    i = this.mViewTransitionId;
                    if (i != -1) {
                    }
                } else if (i2 != 2) {
                    if (i2 != 3 || finalX2 <= 0.0f || (this.mMotionEffectStrictMove && finalY2 != 0.0f)) {
                        i = this.mViewTransitionId;
                        if (i != -1) {
                        }
                    }
                } else {
                    if (finalX2 < 0.0f) {
                        if (this.mMotionEffectStrictMove) {
                        }
                    }
                    i = this.mViewTransitionId;
                    if (i != -1) {
                    }
                }
            }
            i6++;
            map2 = map;
        }
    }
}
