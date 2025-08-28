package androidx.core.animation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import androidx.core.graphics.PathParser;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class AnimatorInflater {
    private static final int SEQUENTIALLY = 1;
    private static final String TAG = "AnimatorInflater";
    private static final int TOGETHER = 0;
    private static final int VALUE_TYPE_COLOR = 3;
    private static final int VALUE_TYPE_FLOAT = 0;
    private static final int VALUE_TYPE_INT = 1;
    private static final int VALUE_TYPE_PATH = 2;
    private static final int VALUE_TYPE_UNDEFINED = 4;

    private static boolean isColorType(int i) {
        return i >= 28 && i <= 31;
    }

    private AnimatorInflater() {
    }

    public static Animator loadAnimator(Context context, int i) throws Resources.NotFoundException {
        return loadAnimator(context.getResources(), context.getTheme(), i);
    }

    public static Animator loadAnimator(Resources resources, Resources.Theme theme, int i) throws Resources.NotFoundException {
        return loadAnimator(resources, theme, i, 1.0f);
    }

    static Animator loadAnimator(Resources resources, Resources.Theme theme, int i, float f) throws Resources.NotFoundException {
        XmlResourceParser animation = null;
        try {
            try {
                try {
                    animation = resources.getAnimation(i);
                    return createAnimatorFromXml(resources, theme, animation, f);
                } catch (XmlPullParserException e) {
                    Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                    notFoundException.initCause(e);
                    throw notFoundException;
                }
            } catch (IOException e2) {
                Resources.NotFoundException notFoundException2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                notFoundException2.initCause(e2);
                throw notFoundException2;
            }
        } finally {
            if (animation != null) {
                animation.close();
            }
        }
    }

    static class PathDataEvaluator implements TypeEvaluator<PathParser.PathDataNode[]> {
        private PathParser.PathDataNode[] mPathData;

        PathDataEvaluator() {
        }

        @Override // androidx.core.animation.TypeEvaluator
        public PathParser.PathDataNode[] evaluate(float f, PathParser.PathDataNode[] pathDataNodeArr, PathParser.PathDataNode[] pathDataNodeArr2) {
            if (this.mPathData == null) {
                this.mPathData = PathParser.deepCopyNodes(pathDataNodeArr2);
            }
            PathParser.interpolatePathDataNodes(this.mPathData, f, pathDataNodeArr, pathDataNodeArr2);
            return this.mPathData;
        }
    }

    private static PropertyValuesHolder getPVH(TypedArray typedArray, int i, int i2, int i3, String str) {
        int color;
        int color2;
        int color3;
        float dimension;
        PropertyValuesHolder propertyValuesHolderOfFloat;
        float dimension2;
        float dimension3;
        PropertyValuesHolder propertyValuesHolderOfObject;
        TypedValue typedValuePeekValue = typedArray.peekValue(i2);
        boolean z = typedValuePeekValue != null;
        int i4 = z ? typedValuePeekValue.type : 0;
        TypedValue typedValuePeekValue2 = typedArray.peekValue(i3);
        boolean z2 = typedValuePeekValue2 != null;
        int i5 = z2 ? typedValuePeekValue2.type : 0;
        if (i == 4) {
            i = ((z && isColorType(i4)) || (z2 && isColorType(i5))) ? 3 : 0;
        }
        boolean z3 = i == 0;
        PropertyValuesHolder propertyValuesHolderOfInt = null;
        if (i == 2) {
            String string = typedArray.getString(i2);
            String string2 = typedArray.getString(i3);
            PathParser.PathDataNode[] pathDataNodeArrCreateNodesFromPathData = string == null ? null : PathParser.createNodesFromPathData(string);
            PathParser.PathDataNode[] pathDataNodeArrCreateNodesFromPathData2 = string2 == null ? null : PathParser.createNodesFromPathData(string2);
            if (pathDataNodeArrCreateNodesFromPathData == null && pathDataNodeArrCreateNodesFromPathData2 == null) {
                return null;
            }
            if (pathDataNodeArrCreateNodesFromPathData == null) {
                if (pathDataNodeArrCreateNodesFromPathData2 != null) {
                    return PropertyValuesHolder.ofObject(str, new PathDataEvaluator(), pathDataNodeArrCreateNodesFromPathData2);
                }
                return null;
            }
            PathDataEvaluator pathDataEvaluator = new PathDataEvaluator();
            if (pathDataNodeArrCreateNodesFromPathData2 != null) {
                if (!PathParser.canMorph(pathDataNodeArrCreateNodesFromPathData, pathDataNodeArrCreateNodesFromPathData2)) {
                    throw new InflateException(" Can't morph from " + string + " to " + string2);
                }
                propertyValuesHolderOfObject = PropertyValuesHolder.ofObject(str, pathDataEvaluator, pathDataNodeArrCreateNodesFromPathData, pathDataNodeArrCreateNodesFromPathData2);
            } else {
                propertyValuesHolderOfObject = PropertyValuesHolder.ofObject(str, pathDataEvaluator, pathDataNodeArrCreateNodesFromPathData);
            }
            return propertyValuesHolderOfObject;
        }
        ArgbEvaluator argbEvaluator = i == 3 ? ArgbEvaluator.getInstance() : null;
        if (z3) {
            if (z) {
                if (i4 == 5) {
                    dimension2 = typedArray.getDimension(i2, 0.0f);
                } else {
                    dimension2 = typedArray.getFloat(i2, 0.0f);
                }
                if (z2) {
                    if (i5 == 5) {
                        dimension3 = typedArray.getDimension(i3, 0.0f);
                    } else {
                        dimension3 = typedArray.getFloat(i3, 0.0f);
                    }
                    propertyValuesHolderOfFloat = PropertyValuesHolder.ofFloat(str, dimension2, dimension3);
                } else {
                    propertyValuesHolderOfFloat = PropertyValuesHolder.ofFloat(str, dimension2);
                }
            } else {
                if (i5 == 5) {
                    dimension = typedArray.getDimension(i3, 0.0f);
                } else {
                    dimension = typedArray.getFloat(i3, 0.0f);
                }
                propertyValuesHolderOfFloat = PropertyValuesHolder.ofFloat(str, dimension);
            }
            propertyValuesHolderOfInt = propertyValuesHolderOfFloat;
        } else if (z) {
            if (i4 == 5) {
                color2 = (int) typedArray.getDimension(i2, 0.0f);
            } else if (isColorType(i4)) {
                color2 = typedArray.getColor(i2, 0);
            } else {
                color2 = typedArray.getInt(i2, 0);
            }
            if (z2) {
                if (i5 == 5) {
                    color3 = (int) typedArray.getDimension(i3, 0.0f);
                } else if (isColorType(i5)) {
                    color3 = typedArray.getColor(i3, 0);
                } else {
                    color3 = typedArray.getInt(i3, 0);
                }
                propertyValuesHolderOfInt = PropertyValuesHolder.ofInt(str, color2, color3);
            } else {
                propertyValuesHolderOfInt = PropertyValuesHolder.ofInt(str, color2);
            }
        } else if (z2) {
            if (i5 == 5) {
                color = (int) typedArray.getDimension(i3, 0.0f);
            } else if (isColorType(i5)) {
                color = typedArray.getColor(i3, 0);
            } else {
                color = typedArray.getInt(i3, 0);
            }
            propertyValuesHolderOfInt = PropertyValuesHolder.ofInt(str, color);
        }
        if (propertyValuesHolderOfInt == null || argbEvaluator == null) {
            return propertyValuesHolderOfInt;
        }
        propertyValuesHolderOfInt.setEvaluator(argbEvaluator);
        return propertyValuesHolderOfInt;
    }

    private static void parseAnimatorFromTypeArray(ValueAnimator valueAnimator, TypedArray typedArray, TypedArray typedArray2, float f) {
        long j = typedArray.getInt(1, 300);
        long j2 = typedArray.getInt(2, 0);
        int iInferValueTypeFromValues = typedArray.getInt(7, 4);
        if (iInferValueTypeFromValues == 4) {
            iInferValueTypeFromValues = inferValueTypeFromValues(typedArray, 5, 6);
        }
        PropertyValuesHolder pvh = getPVH(typedArray, iInferValueTypeFromValues, 5, 6, "");
        if (pvh != null) {
            valueAnimator.setValues(pvh);
        }
        valueAnimator.setDuration(j);
        valueAnimator.setStartDelay(j2);
        if (typedArray.hasValue(3)) {
            valueAnimator.setRepeatCount(typedArray.getInt(3, 0));
        }
        if (typedArray.hasValue(4)) {
            valueAnimator.setRepeatMode(typedArray.getInt(4, 1));
        }
        if (typedArray2 != null) {
            setupObjectAnimator(valueAnimator, typedArray2, iInferValueTypeFromValues, f);
        }
    }

    private static void setupObjectAnimator(ValueAnimator valueAnimator, TypedArray typedArray, int i, float f) {
        Keyframes keyframesCreateXIntKeyframes;
        Keyframes keyframesCreateYIntKeyframes;
        ObjectAnimator objectAnimator = (ObjectAnimator) valueAnimator;
        TypedValue typedValue = new TypedValue();
        typedArray.getValue(1, typedValue);
        String string = typedValue.type == 3 ? typedValue.string.toString() : null;
        if (string != null) {
            String string2 = typedArray.getString(2);
            String string3 = typedArray.getString(3);
            if (i == 2 || i == 4) {
                i = 0;
            }
            if (string2 == null && string3 == null) {
                throw new InflateException(typedArray.getPositionDescription() + " propertyXName or propertyYName is needed for PathData");
            }
            PathKeyframes pathKeyframesOfPath = KeyframeSet.ofPath(PathParser.createPathFromPathData(string), f * 0.5f);
            if (i == 0) {
                keyframesCreateXIntKeyframes = pathKeyframesOfPath.createXFloatKeyframes();
                keyframesCreateYIntKeyframes = pathKeyframesOfPath.createYFloatKeyframes();
            } else {
                keyframesCreateXIntKeyframes = pathKeyframesOfPath.createXIntKeyframes();
                keyframesCreateYIntKeyframes = pathKeyframesOfPath.createYIntKeyframes();
            }
            PropertyValuesHolder propertyValuesHolderOfKeyframes = string2 != null ? PropertyValuesHolder.ofKeyframes(string2, keyframesCreateXIntKeyframes) : null;
            PropertyValuesHolder propertyValuesHolderOfKeyframes2 = string3 != null ? PropertyValuesHolder.ofKeyframes(string3, keyframesCreateYIntKeyframes) : null;
            if (propertyValuesHolderOfKeyframes == null) {
                objectAnimator.setValues(propertyValuesHolderOfKeyframes2);
                return;
            } else if (propertyValuesHolderOfKeyframes2 == null) {
                objectAnimator.setValues(propertyValuesHolderOfKeyframes);
                return;
            } else {
                objectAnimator.setValues(propertyValuesHolderOfKeyframes, propertyValuesHolderOfKeyframes2);
                return;
            }
        }
        objectAnimator.setPropertyName(typedArray.getString(0));
    }

    private static Animator createAnimatorFromXml(Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser, float f) throws XmlPullParserException, IOException {
        return createAnimatorFromXml(resources, theme, xmlPullParser, Xml.asAttributeSet(xmlPullParser), null, 0, f);
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00d1, code lost:
    
        if (r22 == null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00d3, code lost:
    
        if (r14 == null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00d5, code lost:
    
        r1 = new androidx.core.animation.Animator[r14.size()];
        r2 = r14.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00e3, code lost:
    
        if (r2.hasNext() == false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00e5, code lost:
    
        r1[r15] = (androidx.core.animation.Animator) r2.next();
        r15 = r15 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00f1, code lost:
    
        if (r23 != 0) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00f3, code lost:
    
        r22.playTogether(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00f7, code lost:
    
        r22.playSequentially(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00fa, code lost:
    
        return r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Animator createAnimatorFromXml(Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser, AttributeSet attributeSet, AnimatorSet animatorSet, int i, float f) throws XmlPullParserException, Resources.NotFoundException, IOException {
        TypedArray typedArrayObtainAttributes;
        int depth = xmlPullParser.getDepth();
        ValueAnimator valueAnimatorLoadAnimator = null;
        ArrayList arrayList = null;
        while (true) {
            int next = xmlPullParser.next();
            int i2 = 0;
            if ((next == 3 && xmlPullParser.getDepth() <= depth) || next == 1) {
                break;
            }
            if (next == 2) {
                String name = xmlPullParser.getName();
                if (name.equals("objectAnimator")) {
                    valueAnimatorLoadAnimator = loadObjectAnimator(resources, theme, attributeSet, f);
                } else if (name.equals("animator")) {
                    valueAnimatorLoadAnimator = loadAnimator(resources, theme, attributeSet, null, f);
                } else {
                    if (name.equals("set")) {
                        AnimatorSet animatorSet2 = new AnimatorSet();
                        if (theme != null) {
                            typedArrayObtainAttributes = theme.obtainStyledAttributes(attributeSet, AndroidResources.STYLEABLE_ANIMATOR_SET, 0, 0);
                        } else {
                            typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, AndroidResources.STYLEABLE_ANIMATOR_SET);
                        }
                        TypedArray typedArray = typedArrayObtainAttributes;
                        createAnimatorFromXml(resources, theme, xmlPullParser, attributeSet, animatorSet2, typedArray.getInt(0, 0), f);
                        typedArray.recycle();
                        valueAnimatorLoadAnimator = animatorSet2;
                    } else if (name.equals("propertyValuesHolder")) {
                        PropertyValuesHolder[] propertyValuesHolderArrLoadValues = loadValues(resources, theme, xmlPullParser, Xml.asAttributeSet(xmlPullParser));
                        if (propertyValuesHolderArrLoadValues != null && valueAnimatorLoadAnimator != null && (valueAnimatorLoadAnimator instanceof ValueAnimator)) {
                            valueAnimatorLoadAnimator.setValues(propertyValuesHolderArrLoadValues);
                        }
                        i2 = 1;
                    } else {
                        throw new RuntimeException("Unknown animator name: " + xmlPullParser.getName());
                    }
                    if (animatorSet == null && i2 == 0) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(valueAnimatorLoadAnimator);
                    }
                }
                if (animatorSet == null) {
                }
            }
        }
    }

    private static PropertyValuesHolder[] loadValues(Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        int i;
        TypedArray typedArrayObtainAttributes;
        PropertyValuesHolder[] propertyValuesHolderArr = null;
        ArrayList arrayList = null;
        while (true) {
            int eventType = xmlPullParser.getEventType();
            if (eventType == 3 || eventType == 1) {
                break;
            }
            if (eventType != 2) {
                xmlPullParser.next();
            } else {
                if (xmlPullParser.getName().equals("propertyValuesHolder")) {
                    if (theme != null) {
                        typedArrayObtainAttributes = theme.obtainStyledAttributes(attributeSet, AndroidResources.STYLEABLE_PROPERTY_VALUES_HOLDER, 0, 0);
                    } else {
                        typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, AndroidResources.STYLEABLE_PROPERTY_VALUES_HOLDER);
                    }
                    String string = typedArrayObtainAttributes.getString(3);
                    int i2 = typedArrayObtainAttributes.getInt(2, 4);
                    PropertyValuesHolder propertyValuesHolderLoadPvh = loadPvh(resources, theme, xmlPullParser, string, i2);
                    if (propertyValuesHolderLoadPvh == null) {
                        propertyValuesHolderLoadPvh = getPVH(typedArrayObtainAttributes, i2, 0, 1, string);
                    }
                    if (propertyValuesHolderLoadPvh != null) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(propertyValuesHolderLoadPvh);
                    }
                    typedArrayObtainAttributes.recycle();
                }
                xmlPullParser.next();
            }
        }
        if (arrayList != null) {
            int size = arrayList.size();
            propertyValuesHolderArr = new PropertyValuesHolder[size];
            for (i = 0; i < size; i++) {
                propertyValuesHolderArr[i] = (PropertyValuesHolder) arrayList.get(i);
            }
        }
        return propertyValuesHolderArr;
    }

    private static int inferValueTypeOfKeyframe(Resources resources, Resources.Theme theme, AttributeSet attributeSet) {
        TypedArray typedArrayObtainAttributes;
        int i = 0;
        if (theme != null) {
            typedArrayObtainAttributes = theme.obtainStyledAttributes(attributeSet, AndroidResources.STYLEABLE_KEYFRAME, 0, 0);
        } else {
            typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, AndroidResources.STYLEABLE_KEYFRAME);
        }
        TypedValue typedValuePeekValue = typedArrayObtainAttributes.peekValue(0);
        if (typedValuePeekValue != null && isColorType(typedValuePeekValue.type)) {
            i = 3;
        }
        typedArrayObtainAttributes.recycle();
        return i;
    }

    private static int inferValueTypeFromValues(TypedArray typedArray, int i, int i2) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i);
        boolean z = typedValuePeekValue != null;
        int i3 = z ? typedValuePeekValue.type : 0;
        TypedValue typedValuePeekValue2 = typedArray.peekValue(i2);
        boolean z2 = typedValuePeekValue2 != null;
        return ((z && isColorType(i3)) || (z2 && isColorType(z2 ? typedValuePeekValue2.type : 0))) ? 3 : 0;
    }

    private static PropertyValuesHolder loadPvh(Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser, String str, int i) throws XmlPullParserException, IOException {
        int size;
        PropertyValuesHolder propertyValuesHolderOfKeyframe = null;
        ArrayList arrayList = null;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 3 || next == 1) {
                break;
            }
            if (xmlPullParser.getName().equals("keyframe")) {
                if (i == 4) {
                    i = inferValueTypeOfKeyframe(resources, theme, Xml.asAttributeSet(xmlPullParser));
                }
                Keyframe keyframeLoadKeyframe = loadKeyframe(resources, theme, Xml.asAttributeSet(xmlPullParser), i);
                if (keyframeLoadKeyframe != null) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(keyframeLoadKeyframe);
                }
                xmlPullParser.next();
            }
        }
        if (arrayList != null && (size = arrayList.size()) > 0) {
            Keyframe keyframe = (Keyframe) arrayList.get(0);
            Keyframe keyframe2 = (Keyframe) arrayList.get(size - 1);
            float fraction = keyframe2.getFraction();
            if (fraction < 1.0f) {
                if (fraction < 0.0f) {
                    keyframe2.setFraction(1.0f);
                } else {
                    arrayList.add(arrayList.size(), createNewKeyframe(keyframe2, 1.0f));
                    size++;
                }
            }
            float fraction2 = keyframe.getFraction();
            if (fraction2 != 0.0f) {
                if (fraction2 < 0.0f) {
                    keyframe.setFraction(0.0f);
                } else {
                    arrayList.add(0, createNewKeyframe(keyframe, 0.0f));
                    size++;
                }
            }
            Keyframe[] keyframeArr = new Keyframe[size];
            arrayList.toArray(keyframeArr);
            for (int i2 = 0; i2 < size; i2++) {
                Keyframe keyframe3 = keyframeArr[i2];
                if (keyframe3.getFraction() < 0.0f) {
                    if (i2 == 0) {
                        keyframe3.setFraction(0.0f);
                    } else {
                        int i3 = size - 1;
                        if (i2 == i3) {
                            keyframe3.setFraction(1.0f);
                        } else {
                            int i4 = i2;
                            for (int i5 = i2 + 1; i5 < i3 && keyframeArr[i5].getFraction() < 0.0f; i5++) {
                                i4 = i5;
                            }
                            distributeKeyframes(keyframeArr, keyframeArr[i4 + 1].getFraction() - keyframeArr[i2 - 1].getFraction(), i2, i4);
                        }
                    }
                }
            }
            propertyValuesHolderOfKeyframe = PropertyValuesHolder.ofKeyframe(str, keyframeArr);
            if (i == 3) {
                propertyValuesHolderOfKeyframe.setEvaluator(ArgbEvaluator.getInstance());
            }
        }
        return propertyValuesHolderOfKeyframe;
    }

    private static Keyframe createNewKeyframe(Keyframe keyframe, float f) {
        Class<?> type = keyframe.getType();
        if (type == Float.TYPE) {
            return Keyframe.ofFloat(f);
        }
        if (type == Integer.TYPE) {
            return Keyframe.ofInt(f);
        }
        return Keyframe.ofObject(f);
    }

    private static void distributeKeyframes(Keyframe[] keyframeArr, float f, int i, int i2) {
        float f2 = f / ((i2 - i) + 2);
        while (i <= i2) {
            keyframeArr[i].setFraction(keyframeArr[i - 1].getFraction() + f2);
            i++;
        }
    }

    private static Keyframe loadKeyframe(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int i) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes;
        Keyframe keyframeOfInt;
        if (theme != null) {
            typedArrayObtainAttributes = theme.obtainStyledAttributes(attributeSet, AndroidResources.STYLEABLE_KEYFRAME, 0, 0);
        } else {
            typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, AndroidResources.STYLEABLE_KEYFRAME);
        }
        float f = typedArrayObtainAttributes.getFloat(3, -1.0f);
        TypedValue typedValuePeekValue = typedArrayObtainAttributes.peekValue(0);
        boolean z = typedValuePeekValue != null;
        if (i == 4) {
            i = (z && isColorType(typedValuePeekValue.type)) ? 3 : 0;
        }
        if (z) {
            if (i == 0) {
                keyframeOfInt = Keyframe.ofFloat(f, typedArrayObtainAttributes.getFloat(0, 0.0f));
            } else {
                keyframeOfInt = (i == 1 || i == 3) ? Keyframe.ofInt(f, typedArrayObtainAttributes.getInt(0, 0)) : null;
            }
        } else if (i == 0) {
            keyframeOfInt = Keyframe.ofFloat(f);
        } else {
            keyframeOfInt = Keyframe.ofInt(f);
        }
        int resourceId = typedArrayObtainAttributes.getResourceId(1, 0);
        if (resourceId > 0) {
            keyframeOfInt.setInterpolator(loadInterpolator(resources, theme, resourceId));
        }
        typedArrayObtainAttributes.recycle();
        return keyframeOfInt;
    }

    private static ObjectAnimator loadObjectAnimator(Resources resources, Resources.Theme theme, AttributeSet attributeSet, float f) throws Resources.NotFoundException {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        loadAnimator(resources, theme, attributeSet, objectAnimator, f);
        return objectAnimator;
    }

    private static ValueAnimator loadAnimator(Resources resources, Resources.Theme theme, AttributeSet attributeSet, ValueAnimator valueAnimator, float f) throws Resources.NotFoundException {
        TypedArray typedArrayObtainAttributes;
        TypedArray typedArrayObtainAttributes2;
        if (theme != null) {
            typedArrayObtainAttributes = theme.obtainStyledAttributes(attributeSet, AndroidResources.STYLEABLE_ANIMATOR, 0, 0);
        } else {
            typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, AndroidResources.STYLEABLE_ANIMATOR);
        }
        if (valueAnimator == null) {
            typedArrayObtainAttributes2 = null;
        } else if (theme != null) {
            typedArrayObtainAttributes2 = theme.obtainStyledAttributes(attributeSet, AndroidResources.STYLEABLE_PROPERTY_ANIMATOR, 0, 0);
        } else {
            typedArrayObtainAttributes2 = resources.obtainAttributes(attributeSet, AndroidResources.STYLEABLE_PROPERTY_ANIMATOR);
        }
        if (valueAnimator == null) {
            valueAnimator = new ValueAnimator();
        }
        parseAnimatorFromTypeArray(valueAnimator, typedArrayObtainAttributes, typedArrayObtainAttributes2, f);
        int resourceId = typedArrayObtainAttributes.getResourceId(0, 0);
        if (resourceId > 0) {
            valueAnimator.setInterpolator(loadInterpolator(resources, theme, resourceId));
        }
        typedArrayObtainAttributes.recycle();
        if (typedArrayObtainAttributes2 != null) {
            typedArrayObtainAttributes2.recycle();
        }
        return valueAnimator;
    }

    public static Interpolator loadInterpolator(Context context, int i) throws Resources.NotFoundException {
        XmlResourceParser animation = null;
        try {
            try {
                animation = context.getResources().getAnimation(i);
                return createInterpolatorFromXml(context.getResources(), context.getTheme(), animation);
            } catch (IOException e) {
                Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                notFoundException.initCause(e);
                throw notFoundException;
            } catch (XmlPullParserException e2) {
                Resources.NotFoundException notFoundException2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                notFoundException2.initCause(e2);
                throw notFoundException2;
            }
        } finally {
            if (animation != null) {
                animation.close();
            }
        }
    }

    static Interpolator loadInterpolator(Resources resources, Resources.Theme theme, int i) throws Resources.NotFoundException {
        if (i == 17563663) {
            return new PathInterpolator(0.4f, 0.0f, 1.0f, 1.0f);
        }
        if (i == 17563661) {
            return new PathInterpolator(0.4f, 0.0f, 0.2f, 1.0f);
        }
        if (i == 17563662) {
            return new PathInterpolator(0.0f, 0.0f, 0.2f, 1.0f);
        }
        XmlResourceParser animation = null;
        try {
            try {
                try {
                    animation = resources.getAnimation(i);
                    return createInterpolatorFromXml(resources, theme, animation);
                } catch (IOException e) {
                    Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                    notFoundException.initCause(e);
                    throw notFoundException;
                }
            } catch (XmlPullParserException e2) {
                Resources.NotFoundException notFoundException2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                notFoundException2.initCause(e2);
                throw notFoundException2;
            }
        } finally {
            if (animation != null) {
                animation.close();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x00c4, code lost:
    
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Interpolator createInterpolatorFromXml(Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Interpolator accelerateInterpolator;
        int depth = xmlPullParser.getDepth();
        Interpolator linearInterpolator = null;
        while (true) {
            int next = xmlPullParser.next();
            if ((next == 3 && xmlPullParser.getDepth() <= depth) || next == 1) {
                break;
            }
            if (next == 2) {
                AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xmlPullParser);
                String name = xmlPullParser.getName();
                if (name.equals("linearInterpolator")) {
                    linearInterpolator = new LinearInterpolator();
                } else {
                    if (name.equals("accelerateInterpolator")) {
                        accelerateInterpolator = new AccelerateInterpolator(resources, theme, attributeSetAsAttributeSet);
                    } else if (name.equals("decelerateInterpolator")) {
                        accelerateInterpolator = new DecelerateInterpolator(resources, theme, attributeSetAsAttributeSet);
                    } else if (name.equals("accelerateDecelerateInterpolator")) {
                        linearInterpolator = new AccelerateDecelerateInterpolator();
                    } else if (name.equals("cycleInterpolator")) {
                        accelerateInterpolator = new CycleInterpolator(resources, theme, attributeSetAsAttributeSet);
                    } else if (name.equals("anticipateInterpolator")) {
                        accelerateInterpolator = new AnticipateInterpolator(resources, theme, attributeSetAsAttributeSet);
                    } else if (name.equals("overshootInterpolator")) {
                        accelerateInterpolator = new OvershootInterpolator(resources, theme, attributeSetAsAttributeSet);
                    } else if (name.equals("anticipateOvershootInterpolator")) {
                        accelerateInterpolator = new AnticipateOvershootInterpolator(resources, theme, attributeSetAsAttributeSet);
                    } else if (name.equals("bounceInterpolator")) {
                        linearInterpolator = new BounceInterpolator();
                    } else if (name.equals("pathInterpolator")) {
                        accelerateInterpolator = new PathInterpolator(resources, theme, attributeSetAsAttributeSet, xmlPullParser);
                    } else {
                        throw new RuntimeException("Unknown interpolator name: " + name);
                    }
                    linearInterpolator = accelerateInterpolator;
                }
            }
        }
    }
}
