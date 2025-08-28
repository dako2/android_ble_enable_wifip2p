package androidx.core.animation;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
class PathUtils {
    private static final float EPSILON = 1.0E-4f;
    private static final int MAX_NUM_POINTS = 100;
    private static final int NUM_COMPONENTS = 3;

    private PathUtils() {
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x018d A[LOOP:1: B:10:0x005f->B:38:0x018d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x019a A[EDGE_INSN: B:48:0x019a->B:40:0x019a BREAK  A[LOOP:1: B:10:0x005f->B:38:0x018d], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static float[] createKeyFrameData(Path path, float f) {
        int i;
        int i2;
        int i3;
        float[] fArr;
        int i4;
        char c;
        if (Build.VERSION.SDK_INT >= 26) {
            return Api26Impl.approximate(path, f);
        }
        int i5 = 0;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        ArrayList arrayList = new ArrayList();
        float f2 = 0.0f;
        arrayList.add(Float.valueOf(0.0f));
        float length = 0.0f;
        do {
            length += pathMeasure.getLength();
            arrayList.add(Float.valueOf(length));
        } while (pathMeasure.nextContour());
        PathMeasure pathMeasure2 = new PathMeasure(path, false);
        char c2 = 1;
        int iMin = Math.min(100, ((int) (length / f)) + 1);
        ArrayList arrayList2 = new ArrayList(iMin * 3);
        float[] fArr2 = new float[2];
        float size = length / ((iMin - 1) - arrayList.size());
        float[] fArr3 = new float[2];
        float[] fArr4 = new float[2];
        int i6 = 0;
        int i7 = 0;
        boolean z = false;
        while (true) {
            if (i7 >= iMin) {
                i = i5;
                break;
            }
            pathMeasure2.getPosTan(f2 - ((Float) arrayList.get(i6)).floatValue(), fArr2, fArr4);
            int size2 = arrayList2.size();
            int i8 = size2 - 1;
            if (i7 > 0) {
                int i9 = size2 - 2;
                i2 = i6;
                i3 = i7;
                fArr = fArr3;
                if (twoPointsOnTheSameLinePath(fArr4, fArr3, fArr2[i5], fArr2[c2], ((Float) arrayList2.get(i9)).floatValue(), ((Float) arrayList2.get(i8)).floatValue())) {
                    if (z) {
                        arrayList2.set(size2 - 3, Float.valueOf(f2 / length));
                        arrayList2.set(i9, Float.valueOf(fArr2[i5]));
                        arrayList2.set(i8, Float.valueOf(fArr2[1]));
                        i4 = i2;
                    } else {
                        addDataEntry(arrayList2, f2 / length, fArr2[i5], fArr2[1]);
                        i4 = i2;
                        z = true;
                    }
                }
                f2 += size;
                i6 = i4 + 1;
                if (i6 < arrayList.size() || f2 <= ((Float) arrayList.get(i6)).floatValue()) {
                    i = 0;
                    c = 1;
                    i6 = i4;
                } else {
                    float fFloatValue = ((Float) arrayList.get(i6)).floatValue();
                    pathMeasure2.getPosTan(fFloatValue - ((Float) arrayList.get(i4)).floatValue(), fArr2, fArr4);
                    i = 0;
                    c = 1;
                    addDataEntry(arrayList2, fFloatValue / length, fArr2[0], fArr2[1]);
                    pathMeasure2.nextContour();
                }
                fArr[i] = fArr4[i];
                fArr[c] = fArr4[c];
                if (f2 <= length) {
                    break;
                }
                fArr3 = fArr;
                char c3 = c;
                i7 = i3 + 1;
                i5 = i;
                c2 = c3;
            } else {
                i2 = i6;
                i3 = i7;
                fArr = fArr3;
            }
            if (i3 - (arrayList2.size() / 3) > 0 && z) {
                float fFloatValue2 = ((Float) arrayList2.get(arrayList2.size() - 3)).floatValue() * length;
                float fMin = size / Math.min(r14, 4);
                while (true) {
                    fFloatValue2 += fMin;
                    if (fFloatValue2 >= f2) {
                        break;
                    }
                    pathMeasure2.getPosTan(fFloatValue2 - ((Float) arrayList.get(i2)).floatValue(), fArr2, fArr4);
                    addDataEntry(arrayList2, fFloatValue2 / length, fArr2[i5], fArr2[1]);
                    i5 = 0;
                }
                i4 = i2;
            } else {
                i4 = i2;
                addDataEntry(arrayList2, f2 / length, fArr2[0], fArr2[1]);
            }
            z = false;
            f2 += size;
            i6 = i4 + 1;
            if (i6 < arrayList.size()) {
                i = 0;
                c = 1;
                i6 = i4;
                fArr[i] = fArr4[i];
                fArr[c] = fArr4[c];
                if (f2 <= length) {
                }
            }
        }
        float[] fArr5 = new float[arrayList2.size()];
        for (int i10 = i; i10 < arrayList2.size(); i10++) {
            fArr5[i10] = ((Float) arrayList2.get(i10)).floatValue();
        }
        return fArr5;
    }

    private static boolean twoPointsOnTheSameLinePath(float[] fArr, float[] fArr2, float f, float f2, float f3, float f4) {
        return Math.abs(fArr[0] - fArr2[0]) <= 1.0E-4f && Math.abs(fArr[1] - fArr2[1]) <= 1.0E-4f && Math.abs(((f - f3) * fArr[1]) - ((f2 - f4) * fArr[0])) < 1.0E-4f;
    }

    private static void addDataEntry(List<Float> list, float f, float f2, float f3) {
        list.add(Float.valueOf(f));
        list.add(Float.valueOf(f2));
        list.add(Float.valueOf(f3));
    }

    static class Api26Impl {
        private Api26Impl() {
        }

        static float[] approximate(Path path, float f) {
            return path.approximate(f);
        }
    }
}
