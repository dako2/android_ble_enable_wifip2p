package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.TypedBundle;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.parser.CLArray;
import androidx.constraintlayout.core.parser.CLContainer;
import androidx.constraintlayout.core.parser.CLElement;
import androidx.constraintlayout.core.parser.CLKey;
import androidx.constraintlayout.core.parser.CLNumber;
import androidx.constraintlayout.core.parser.CLObject;
import androidx.constraintlayout.core.parser.CLParsingException;
import androidx.constraintlayout.core.state.Transition;
import io.reactivex.annotations.SchedulerSupport;
import java.lang.reflect.Array;

/* loaded from: classes.dex */
public class TransitionParser {
    @Deprecated
    public static void parse(CLObject cLObject, Transition transition, CorePixelDp corePixelDp) throws CLParsingException {
        parse(cLObject, transition);
    }

    public static void parse(CLObject cLObject, Transition transition) throws CLParsingException {
        transition.resetProperties();
        String stringOrNull = cLObject.getStringOrNull(TypedValues.TransitionType.S_PATH_MOTION_ARC);
        TypedBundle typedBundle = new TypedBundle();
        boolean z = true;
        boolean z2 = false;
        if (stringOrNull != null) {
            stringOrNull.hashCode();
            switch (stringOrNull) {
                case "startVertical":
                    typedBundle.add(509, 1);
                    break;
                case "startHorizontal":
                    typedBundle.add(509, 2);
                    break;
                case "flip":
                    typedBundle.add(509, 3);
                    break;
                case "none":
                    typedBundle.add(509, 0);
                    break;
                case "above":
                    typedBundle.add(509, 5);
                    break;
                case "below":
                    typedBundle.add(509, 4);
                    break;
            }
            z2 = true;
        }
        String stringOrNull2 = cLObject.getStringOrNull("interpolator");
        if (stringOrNull2 != null) {
            typedBundle.add(TypedValues.TransitionType.TYPE_INTERPOLATOR, stringOrNull2);
            z2 = true;
        }
        float floatOrNaN = cLObject.getFloatOrNaN(TypedValues.TransitionType.S_STAGGERED);
        if (Float.isNaN(floatOrNaN)) {
            z = z2;
        } else {
            typedBundle.add(TypedValues.TransitionType.TYPE_STAGGERED, floatOrNaN);
        }
        if (z) {
            transition.setTransitionProperties(typedBundle);
        }
        CLObject objectOrNull = cLObject.getObjectOrNull("onSwipe");
        if (objectOrNull != null) {
            parseOnSwipe(objectOrNull, transition);
        }
        parseKeyFrames(cLObject, transition);
    }

    private static void parseOnSwipe(CLContainer cLContainer, Transition transition) {
        String stringOrNull = cLContainer.getStringOrNull("anchor");
        int map = map(cLContainer.getStringOrNull("side"), Transition.OnSwipe.SIDES);
        int map2 = map(cLContainer.getStringOrNull("direction"), Transition.OnSwipe.DIRECTIONS);
        float floatOrNaN = cLContainer.getFloatOrNaN("scale");
        float floatOrNaN2 = cLContainer.getFloatOrNaN("threshold");
        float floatOrNaN3 = cLContainer.getFloatOrNaN("maxVelocity");
        float floatOrNaN4 = cLContainer.getFloatOrNaN("maxAccel");
        String stringOrNull2 = cLContainer.getStringOrNull("limitBounds");
        int map3 = map(cLContainer.getStringOrNull("mode"), Transition.OnSwipe.MODE);
        int map4 = map(cLContainer.getStringOrNull("touchUp"), Transition.OnSwipe.TOUCH_UP);
        float floatOrNaN5 = cLContainer.getFloatOrNaN("springMass");
        float floatOrNaN6 = cLContainer.getFloatOrNaN("springStiffness");
        float floatOrNaN7 = cLContainer.getFloatOrNaN("springDamping");
        float floatOrNaN8 = cLContainer.getFloatOrNaN("stopThreshold");
        int map5 = map(cLContainer.getStringOrNull("springBoundary"), Transition.OnSwipe.BOUNDARY);
        String stringOrNull3 = cLContainer.getStringOrNull("around");
        Transition.OnSwipe onSwipeCreateOnSwipe = transition.createOnSwipe();
        onSwipeCreateOnSwipe.setAnchorId(stringOrNull);
        onSwipeCreateOnSwipe.setAnchorSide(map);
        onSwipeCreateOnSwipe.setDragDirection(map2);
        onSwipeCreateOnSwipe.setDragScale(floatOrNaN);
        onSwipeCreateOnSwipe.setDragThreshold(floatOrNaN2);
        onSwipeCreateOnSwipe.setMaxVelocity(floatOrNaN3);
        onSwipeCreateOnSwipe.setMaxAcceleration(floatOrNaN4);
        onSwipeCreateOnSwipe.setLimitBoundsTo(stringOrNull2);
        onSwipeCreateOnSwipe.setAutoCompleteMode(map3);
        onSwipeCreateOnSwipe.setOnTouchUp(map4);
        onSwipeCreateOnSwipe.setSpringMass(floatOrNaN5);
        onSwipeCreateOnSwipe.setSpringStiffness(floatOrNaN6);
        onSwipeCreateOnSwipe.setSpringDamping(floatOrNaN7);
        onSwipeCreateOnSwipe.setSpringStopThreshold(floatOrNaN8);
        onSwipeCreateOnSwipe.setSpringBoundary(map5);
        onSwipeCreateOnSwipe.setRotationCenterId(stringOrNull3);
    }

    private static int map(String str, String... strArr) {
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals(str)) {
                return i;
            }
        }
        return 0;
    }

    private static void map(TypedBundle typedBundle, int i, String str, String... strArr) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (strArr[i2].equals(str)) {
                typedBundle.add(i, i2);
            }
        }
    }

    public static void parseKeyFrames(CLObject cLObject, Transition transition) throws CLParsingException {
        CLObject objectOrNull = cLObject.getObjectOrNull("KeyFrames");
        if (objectOrNull == null) {
            return;
        }
        CLArray arrayOrNull = objectOrNull.getArrayOrNull("KeyPositions");
        if (arrayOrNull != null) {
            for (int i = 0; i < arrayOrNull.size(); i++) {
                CLElement cLElement = arrayOrNull.get(i);
                if (cLElement instanceof CLObject) {
                    parseKeyPosition((CLObject) cLElement, transition);
                }
            }
        }
        CLArray arrayOrNull2 = objectOrNull.getArrayOrNull(TypedValues.AttributesType.NAME);
        if (arrayOrNull2 != null) {
            for (int i2 = 0; i2 < arrayOrNull2.size(); i2++) {
                CLElement cLElement2 = arrayOrNull2.get(i2);
                if (cLElement2 instanceof CLObject) {
                    parseKeyAttribute((CLObject) cLElement2, transition);
                }
            }
        }
        CLArray arrayOrNull3 = objectOrNull.getArrayOrNull("KeyCycles");
        if (arrayOrNull3 != null) {
            for (int i3 = 0; i3 < arrayOrNull3.size(); i3++) {
                CLElement cLElement3 = arrayOrNull3.get(i3);
                if (cLElement3 instanceof CLObject) {
                    parseKeyCycle((CLObject) cLElement3, transition);
                }
            }
        }
    }

    private static void parseKeyPosition(CLObject cLObject, Transition transition) throws CLParsingException {
        int i;
        TypedBundle typedBundle = new TypedBundle();
        CLArray array = cLObject.getArray(TypedValues.AttributesType.S_TARGET);
        CLArray array2 = cLObject.getArray("frames");
        CLArray arrayOrNull = cLObject.getArrayOrNull("percentX");
        CLArray arrayOrNull2 = cLObject.getArrayOrNull("percentY");
        CLArray arrayOrNull3 = cLObject.getArrayOrNull("percentWidth");
        CLArray arrayOrNull4 = cLObject.getArrayOrNull("percentHeight");
        String stringOrNull = cLObject.getStringOrNull(TypedValues.TransitionType.S_PATH_MOTION_ARC);
        String stringOrNull2 = cLObject.getStringOrNull("transitionEasing");
        String stringOrNull3 = cLObject.getStringOrNull("curveFit");
        String stringOrNull4 = cLObject.getStringOrNull("type");
        if (stringOrNull4 == null) {
            stringOrNull4 = "parentRelative";
        }
        if (arrayOrNull == null || array2.size() == arrayOrNull.size()) {
            if (arrayOrNull2 == null || array2.size() == arrayOrNull2.size()) {
                int i2 = 0;
                int i3 = 0;
                while (i3 < array.size()) {
                    String string = array.getString(i3);
                    CLArray cLArray = array;
                    String[] strArr = new String[3];
                    strArr[i2] = "deltaRelative";
                    strArr[1] = "pathRelative";
                    strArr[2] = "parentRelative";
                    int map = map(stringOrNull4, strArr);
                    typedBundle.clear();
                    typedBundle.add(TypedValues.PositionType.TYPE_POSITION_TYPE, map);
                    if (stringOrNull3 != null) {
                        map(typedBundle, TypedValues.PositionType.TYPE_CURVE_FIT, stringOrNull3, "spline", "linear");
                    }
                    typedBundle.addIfNotNull(501, stringOrNull2);
                    if (stringOrNull != null) {
                        i = 0;
                        map(typedBundle, 509, stringOrNull, SchedulerSupport.NONE, "startVertical", "startHorizontal", "flip", "below", "above");
                    } else {
                        i = 0;
                    }
                    for (int i4 = i; i4 < array2.size(); i4++) {
                        typedBundle.add(100, array2.getInt(i4));
                        set(typedBundle, TypedValues.PositionType.TYPE_PERCENT_X, arrayOrNull, i4);
                        set(typedBundle, 507, arrayOrNull2, i4);
                        set(typedBundle, 503, arrayOrNull3, i4);
                        set(typedBundle, 504, arrayOrNull4, i4);
                        transition.addKeyPosition(string, typedBundle);
                    }
                    i3++;
                    array = cLArray;
                    i2 = i;
                }
            }
        }
    }

    private static void set(TypedBundle typedBundle, int i, CLArray cLArray, int i2) throws CLParsingException {
        if (cLArray != null) {
            typedBundle.add(i, cLArray.getFloat(i2));
        }
    }

    private static void parseKeyAttribute(CLObject cLObject, Transition transition) throws CLParsingException {
        CLArray arrayOrNull;
        CustomVariable[][] customVariableArr;
        CLObject cLObject2;
        int i;
        CLArray arrayOrNull2 = cLObject.getArrayOrNull(TypedValues.AttributesType.S_TARGET);
        if (arrayOrNull2 == null || (arrayOrNull = cLObject.getArrayOrNull("frames")) == null) {
            return;
        }
        String stringOrNull = cLObject.getStringOrNull("transitionEasing");
        String[] strArr = {"scaleX", "scaleY", "translationX", "translationY", "translationZ", "rotationX", "rotationY", "rotationZ", "alpha"};
        int[] iArr = {311, 312, 304, 305, 306, 308, 309, 310, 303};
        boolean[] zArr = {false, false, true, true, true, false, false, false, false};
        int size = arrayOrNull.size();
        TypedBundle[] typedBundleArr = new TypedBundle[size];
        for (int i2 = 0; i2 < arrayOrNull.size(); i2++) {
            typedBundleArr[i2] = new TypedBundle();
        }
        int i3 = 0;
        for (int i4 = 9; i3 < i4; i4 = 9) {
            String str = strArr[i3];
            int i5 = iArr[i3];
            boolean z = zArr[i3];
            CLArray arrayOrNull3 = cLObject.getArrayOrNull(str);
            if (arrayOrNull3 != null && arrayOrNull3.size() != size) {
                throw new CLParsingException("incorrect size for " + str + " array, not matching targets array!", cLObject);
            }
            if (arrayOrNull3 != null) {
                for (int i6 = 0; i6 < size; i6++) {
                    float pixels = arrayOrNull3.getFloat(i6);
                    if (z) {
                        pixels = transition.mToPixel.toPixels(pixels);
                    }
                    typedBundleArr[i6].add(i5, pixels);
                }
            } else {
                float floatOrNaN = cLObject.getFloatOrNaN(str);
                if (!Float.isNaN(floatOrNaN)) {
                    if (z) {
                        floatOrNaN = transition.mToPixel.toPixels(floatOrNaN);
                    }
                    for (int i7 = 0; i7 < size; i7++) {
                        typedBundleArr[i7].add(i5, floatOrNaN);
                    }
                }
            }
            i3++;
        }
        CLElement orNull = cLObject.getOrNull(SchedulerSupport.CUSTOM);
        if (orNull == null || !(orNull instanceof CLObject)) {
            customVariableArr = null;
        } else {
            CLObject cLObject3 = (CLObject) orNull;
            int size2 = cLObject3.size();
            customVariableArr = (CustomVariable[][]) Array.newInstance((Class<?>) CustomVariable.class, arrayOrNull.size(), size2);
            int i8 = 0;
            while (i8 < size2) {
                CLKey cLKey = (CLKey) cLObject3.get(i8);
                String strContent = cLKey.content();
                if (cLKey.getValue() instanceof CLArray) {
                    CLArray cLArray = (CLArray) cLKey.getValue();
                    int size3 = cLArray.size();
                    if (size3 != size || size3 <= 0) {
                        cLObject2 = cLObject3;
                        i = size2;
                    } else if (cLArray.get(0) instanceof CLNumber) {
                        int i9 = 0;
                        while (i9 < size) {
                            customVariableArr[i9][i8] = new CustomVariable(strContent, TypedValues.Custom.TYPE_FLOAT, cLArray.get(i9).getFloat());
                            i9++;
                            cLObject3 = cLObject3;
                            size2 = size2;
                        }
                        cLObject2 = cLObject3;
                        i = size2;
                    } else {
                        cLObject2 = cLObject3;
                        i = size2;
                        for (int i10 = 0; i10 < size; i10++) {
                            long colorString = ConstraintSetParser.parseColorString(cLArray.get(i10).content());
                            if (colorString != -1) {
                                customVariableArr[i10][i8] = new CustomVariable(strContent, TypedValues.Custom.TYPE_COLOR, (int) colorString);
                            }
                        }
                    }
                } else {
                    cLObject2 = cLObject3;
                    i = size2;
                    CLElement value = cLKey.getValue();
                    if (value instanceof CLNumber) {
                        float f = value.getFloat();
                        for (int i11 = 0; i11 < size; i11++) {
                            customVariableArr[i11][i8] = new CustomVariable(strContent, TypedValues.Custom.TYPE_FLOAT, f);
                        }
                    } else {
                        long colorString2 = ConstraintSetParser.parseColorString(value.content());
                        if (colorString2 != -1) {
                            int i12 = 0;
                            while (i12 < size) {
                                customVariableArr[i12][i8] = new CustomVariable(strContent, TypedValues.Custom.TYPE_COLOR, (int) colorString2);
                                i12++;
                                colorString2 = colorString2;
                            }
                        }
                    }
                }
                i8++;
                cLObject3 = cLObject2;
                size2 = i;
            }
        }
        String stringOrNull2 = cLObject.getStringOrNull("curveFit");
        for (int i13 = 0; i13 < arrayOrNull2.size(); i13++) {
            for (int i14 = 0; i14 < size; i14++) {
                String string = arrayOrNull2.getString(i13);
                TypedBundle typedBundle = typedBundleArr[i14];
                if (stringOrNull2 != null) {
                    typedBundle.add(TypedValues.PositionType.TYPE_CURVE_FIT, map(stringOrNull2, "spline", "linear"));
                }
                typedBundle.addIfNotNull(501, stringOrNull);
                typedBundle.add(100, arrayOrNull.getInt(i14));
                transition.addKeyAttribute(string, typedBundle, customVariableArr != null ? customVariableArr[i14] : null);
            }
        }
    }

    private static void parseKeyCycle(CLObject cLObject, Transition transition) throws CLParsingException {
        int[] iArr;
        char c;
        CLArray array = cLObject.getArray(TypedValues.AttributesType.S_TARGET);
        CLArray array2 = cLObject.getArray("frames");
        String stringOrNull = cLObject.getStringOrNull("transitionEasing");
        String[] strArr = {"scaleX", "scaleY", "translationX", "translationY", "translationZ", "rotationX", "rotationY", "rotationZ", "alpha", TypedValues.CycleType.S_WAVE_PERIOD, TypedValues.CycleType.S_WAVE_OFFSET, TypedValues.CycleType.S_WAVE_PHASE};
        int[] iArr2 = {311, 312, 304, 305, 306, 308, 309, 310, 403, 423, 424, TypedValues.CycleType.TYPE_WAVE_PHASE};
        int[] iArr3 = {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 2, 0};
        int size = array2.size();
        TypedBundle[] typedBundleArr = new TypedBundle[size];
        for (int i = 0; i < size; i++) {
            typedBundleArr[i] = new TypedBundle();
        }
        boolean z = false;
        for (int i2 = 0; i2 < 12; i2++) {
            if (cLObject.has(strArr[i2]) && iArr3[i2] == 1) {
                z = true;
            }
        }
        int i3 = 0;
        for (int i4 = 12; i3 < i4; i4 = 12) {
            String str = strArr[i3];
            int i5 = iArr2[i3];
            int i6 = iArr3[i3];
            CLArray arrayOrNull = cLObject.getArrayOrNull(str);
            String[] strArr2 = strArr;
            if (arrayOrNull != null && arrayOrNull.size() != size) {
                throw new CLParsingException("incorrect size for $attrName array, not matching targets array!", cLObject);
            }
            if (arrayOrNull != null) {
                int i7 = 0;
                while (i7 < size) {
                    float pixels = arrayOrNull.getFloat(i7);
                    int[] iArr4 = iArr2;
                    if (i6 == 1) {
                        pixels = transition.mToPixel.toPixels(pixels);
                    } else if (i6 == 2 && z) {
                        pixels = transition.mToPixel.toPixels(pixels);
                    }
                    typedBundleArr[i7].add(i5, pixels);
                    i7++;
                    iArr2 = iArr4;
                }
                iArr = iArr2;
            } else {
                iArr = iArr2;
                float floatOrNaN = cLObject.getFloatOrNaN(str);
                if (!Float.isNaN(floatOrNaN)) {
                    if (i6 == 1) {
                        floatOrNaN = transition.mToPixel.toPixels(floatOrNaN);
                        c = 2;
                    } else {
                        c = 2;
                        if (i6 == 2 && z) {
                            floatOrNaN = transition.mToPixel.toPixels(floatOrNaN);
                        }
                    }
                    for (int i8 = 0; i8 < size; i8++) {
                        typedBundleArr[i8].add(i5, floatOrNaN);
                    }
                }
                i3++;
                strArr = strArr2;
                iArr2 = iArr;
            }
            c = 2;
            i3++;
            strArr = strArr2;
            iArr2 = iArr;
        }
        String stringOrNull2 = cLObject.getStringOrNull("curveFit");
        String stringOrNull3 = cLObject.getStringOrNull("easing");
        String stringOrNull4 = cLObject.getStringOrNull("waveShape");
        String stringOrNull5 = cLObject.getStringOrNull(TypedValues.CycleType.S_CUSTOM_WAVE_SHAPE);
        for (int i9 = 0; i9 < array.size(); i9++) {
            for (int i10 = 0; i10 < size; i10++) {
                String string = array.getString(i9);
                TypedBundle typedBundle = typedBundleArr[i10];
                if (stringOrNull2 != null) {
                    stringOrNull2.hashCode();
                    if (stringOrNull2.equals("linear")) {
                        typedBundle.add(401, 1);
                    } else if (stringOrNull2.equals("spline")) {
                        typedBundle.add(401, 0);
                    }
                }
                typedBundle.addIfNotNull(501, stringOrNull);
                if (stringOrNull3 != null) {
                    typedBundle.add(420, stringOrNull3);
                }
                if (stringOrNull4 != null) {
                    typedBundle.add(421, stringOrNull4);
                }
                if (stringOrNull5 != null) {
                    typedBundle.add(422, stringOrNull5);
                }
                typedBundle.add(100, array2.getInt(i10));
                transition.addKeyCycle(string, typedBundle);
            }
        }
    }
}
