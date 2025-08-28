package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;

/* loaded from: classes.dex */
public class ArcCurveFit extends CurveFit {
    public static final int ARC_ABOVE = 5;
    public static final int ARC_BELOW = 4;
    public static final int ARC_START_FLIP = 3;
    public static final int ARC_START_HORIZONTAL = 2;
    public static final int ARC_START_LINEAR = 0;
    public static final int ARC_START_VERTICAL = 1;
    private static final int DOWN_ARC = 4;
    private static final int START_HORIZONTAL = 2;
    private static final int START_LINEAR = 3;
    private static final int START_VERTICAL = 1;
    private static final int UP_ARC = 5;
    Arc[] mArcs;
    private boolean mExtrapolate = true;
    private final double[] mTime;

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double d, double[] dArr) {
        if (!this.mExtrapolate) {
            if (d < this.mArcs[0].mTime1) {
                d = this.mArcs[0].mTime1;
            }
            Arc[] arcArr = this.mArcs;
            if (d > arcArr[arcArr.length - 1].mTime2) {
                Arc[] arcArr2 = this.mArcs;
                d = arcArr2[arcArr2.length - 1].mTime2;
            }
        } else {
            if (d < this.mArcs[0].mTime1) {
                double d2 = this.mArcs[0].mTime1;
                double d3 = d - this.mArcs[0].mTime1;
                if (this.mArcs[0].mLinear) {
                    dArr[0] = this.mArcs[0].getLinearX(d2) + (this.mArcs[0].getLinearDX(d2) * d3);
                    dArr[1] = this.mArcs[0].getLinearY(d2) + (d3 * this.mArcs[0].getLinearDY(d2));
                    return;
                } else {
                    this.mArcs[0].setPoint(d2);
                    dArr[0] = this.mArcs[0].getX() + (this.mArcs[0].getDX() * d3);
                    dArr[1] = this.mArcs[0].getY() + (d3 * this.mArcs[0].getDY());
                    return;
                }
            }
            Arc[] arcArr3 = this.mArcs;
            if (d > arcArr3[arcArr3.length - 1].mTime2) {
                Arc[] arcArr4 = this.mArcs;
                double d4 = arcArr4[arcArr4.length - 1].mTime2;
                double d5 = d - d4;
                Arc[] arcArr5 = this.mArcs;
                int length = arcArr5.length - 1;
                if (arcArr5[length].mLinear) {
                    dArr[0] = this.mArcs[length].getLinearX(d4) + (this.mArcs[length].getLinearDX(d4) * d5);
                    dArr[1] = this.mArcs[length].getLinearY(d4) + (d5 * this.mArcs[length].getLinearDY(d4));
                    return;
                } else {
                    this.mArcs[length].setPoint(d);
                    dArr[0] = this.mArcs[length].getX() + (this.mArcs[length].getDX() * d5);
                    dArr[1] = this.mArcs[length].getY() + (d5 * this.mArcs[length].getDY());
                    return;
                }
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr6 = this.mArcs;
            if (i >= arcArr6.length) {
                return;
            }
            if (d <= arcArr6[i].mTime2) {
                if (this.mArcs[i].mLinear) {
                    dArr[0] = this.mArcs[i].getLinearX(d);
                    dArr[1] = this.mArcs[i].getLinearY(d);
                    return;
                } else {
                    this.mArcs[i].setPoint(d);
                    dArr[0] = this.mArcs[i].getX();
                    dArr[1] = this.mArcs[i].getY();
                    return;
                }
            }
            i++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double d, float[] fArr) {
        if (this.mExtrapolate) {
            if (d < this.mArcs[0].mTime1) {
                double d2 = this.mArcs[0].mTime1;
                double d3 = d - this.mArcs[0].mTime1;
                if (this.mArcs[0].mLinear) {
                    fArr[0] = (float) (this.mArcs[0].getLinearX(d2) + (this.mArcs[0].getLinearDX(d2) * d3));
                    fArr[1] = (float) (this.mArcs[0].getLinearY(d2) + (d3 * this.mArcs[0].getLinearDY(d2)));
                    return;
                } else {
                    this.mArcs[0].setPoint(d2);
                    fArr[0] = (float) (this.mArcs[0].getX() + (this.mArcs[0].getDX() * d3));
                    fArr[1] = (float) (this.mArcs[0].getY() + (d3 * this.mArcs[0].getDY()));
                    return;
                }
            }
            Arc[] arcArr = this.mArcs;
            if (d > arcArr[arcArr.length - 1].mTime2) {
                Arc[] arcArr2 = this.mArcs;
                double d4 = arcArr2[arcArr2.length - 1].mTime2;
                double d5 = d - d4;
                Arc[] arcArr3 = this.mArcs;
                int length = arcArr3.length - 1;
                if (arcArr3[length].mLinear) {
                    fArr[0] = (float) (this.mArcs[length].getLinearX(d4) + (this.mArcs[length].getLinearDX(d4) * d5));
                    fArr[1] = (float) (this.mArcs[length].getLinearY(d4) + (d5 * this.mArcs[length].getLinearDY(d4)));
                    return;
                } else {
                    this.mArcs[length].setPoint(d);
                    fArr[0] = (float) this.mArcs[length].getX();
                    fArr[1] = (float) this.mArcs[length].getY();
                    return;
                }
            }
        } else if (d < this.mArcs[0].mTime1) {
            d = this.mArcs[0].mTime1;
        } else {
            Arc[] arcArr4 = this.mArcs;
            if (d > arcArr4[arcArr4.length - 1].mTime2) {
                Arc[] arcArr5 = this.mArcs;
                d = arcArr5[arcArr5.length - 1].mTime2;
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr6 = this.mArcs;
            if (i >= arcArr6.length) {
                return;
            }
            if (d <= arcArr6[i].mTime2) {
                if (this.mArcs[i].mLinear) {
                    fArr[0] = (float) this.mArcs[i].getLinearX(d);
                    fArr[1] = (float) this.mArcs[i].getLinearY(d);
                    return;
                } else {
                    this.mArcs[i].setPoint(d);
                    fArr[0] = (float) this.mArcs[i].getX();
                    fArr[1] = (float) this.mArcs[i].getY();
                    return;
                }
            }
            i++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getSlope(double d, double[] dArr) {
        if (d < this.mArcs[0].mTime1) {
            d = this.mArcs[0].mTime1;
        } else {
            Arc[] arcArr = this.mArcs;
            if (d > arcArr[arcArr.length - 1].mTime2) {
                Arc[] arcArr2 = this.mArcs;
                d = arcArr2[arcArr2.length - 1].mTime2;
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i >= arcArr3.length) {
                return;
            }
            if (d <= arcArr3[i].mTime2) {
                if (this.mArcs[i].mLinear) {
                    dArr[0] = this.mArcs[i].getLinearDX(d);
                    dArr[1] = this.mArcs[i].getLinearDY(d);
                    return;
                } else {
                    this.mArcs[i].setPoint(d);
                    dArr[0] = this.mArcs[i].getDX();
                    dArr[1] = this.mArcs[i].getDY();
                    return;
                }
            }
            i++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getPos(double d, int i) {
        double d2;
        double linearY;
        double linearDY;
        double y;
        double dy;
        int i2 = 0;
        if (this.mExtrapolate) {
            if (d < this.mArcs[0].mTime1) {
                double d3 = this.mArcs[0].mTime1;
                d2 = d - this.mArcs[0].mTime1;
                if (!this.mArcs[0].mLinear) {
                    this.mArcs[0].setPoint(d3);
                    if (i == 0) {
                        y = this.mArcs[0].getX();
                        dy = this.mArcs[0].getDX();
                    } else {
                        y = this.mArcs[0].getY();
                        dy = this.mArcs[0].getDY();
                    }
                    return y + (d2 * dy);
                }
                if (i == 0) {
                    linearY = this.mArcs[0].getLinearX(d3);
                    linearDY = this.mArcs[0].getLinearDX(d3);
                } else {
                    linearY = this.mArcs[0].getLinearY(d3);
                    linearDY = this.mArcs[0].getLinearDY(d3);
                }
            } else {
                if (d > this.mArcs[r0.length - 1].mTime2) {
                    double d4 = this.mArcs[r0.length - 1].mTime2;
                    d2 = d - d4;
                    Arc[] arcArr = this.mArcs;
                    int length = arcArr.length - 1;
                    if (i == 0) {
                        linearY = arcArr[length].getLinearX(d4);
                        linearDY = this.mArcs[length].getLinearDX(d4);
                    } else {
                        linearY = arcArr[length].getLinearY(d4);
                        linearDY = this.mArcs[length].getLinearDY(d4);
                    }
                }
            }
            return linearY + (d2 * linearDY);
        }
        if (d < this.mArcs[0].mTime1) {
            d = this.mArcs[0].mTime1;
        } else {
            if (d > this.mArcs[r0.length - 1].mTime2) {
                d = this.mArcs[r7.length - 1].mTime2;
            }
        }
        while (true) {
            Arc[] arcArr2 = this.mArcs;
            if (i2 >= arcArr2.length) {
                return Double.NaN;
            }
            if (d <= arcArr2[i2].mTime2) {
                if (this.mArcs[i2].mLinear) {
                    if (i == 0) {
                        return this.mArcs[i2].getLinearX(d);
                    }
                    return this.mArcs[i2].getLinearY(d);
                }
                this.mArcs[i2].setPoint(d);
                if (i == 0) {
                    return this.mArcs[i2].getX();
                }
                return this.mArcs[i2].getY();
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getSlope(double d, int i) {
        int i2 = 0;
        if (d < this.mArcs[0].mTime1) {
            d = this.mArcs[0].mTime1;
        }
        if (d > this.mArcs[r0.length - 1].mTime2) {
            d = this.mArcs[r5.length - 1].mTime2;
        }
        while (true) {
            Arc[] arcArr = this.mArcs;
            if (i2 >= arcArr.length) {
                return Double.NaN;
            }
            if (d <= arcArr[i2].mTime2) {
                if (this.mArcs[i2].mLinear) {
                    if (i == 0) {
                        return this.mArcs[i2].getLinearDX(d);
                    }
                    return this.mArcs[i2].getLinearDY(d);
                }
                this.mArcs[i2].setPoint(d);
                if (i == 0) {
                    return this.mArcs[i2].getDX();
                }
                return this.mArcs[i2].getDY();
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double[] getTimePoints() {
        return this.mTime;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0035 A[PHI: r9
      0x0035: PHI (r9v1 int) = (r9v0 int), (r9v3 int), (r9v4 int) binds: [B:6:0x001e, B:12:0x0028, B:14:0x002b] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ArcCurveFit(int[] iArr, double[] dArr, double[][] dArr2) {
        this.mTime = dArr;
        this.mArcs = new Arc[dArr.length - 1];
        int i = 1;
        int i2 = 1;
        int i3 = 0;
        while (true) {
            Arc[] arcArr = this.mArcs;
            if (i3 >= arcArr.length) {
                return;
            }
            int i4 = iArr[i3];
            int i5 = 3;
            if (i4 == 0) {
                i2 = i5;
            } else if (i4 == 1) {
                i = 1;
                i2 = i;
            } else {
                if (i4 != 2) {
                    if (i4 != 3) {
                        i5 = 4;
                        if (i4 != 4) {
                            i5 = 5;
                            if (i4 != 5) {
                            }
                        }
                    } else {
                        if (i == 1) {
                        }
                        i2 = i;
                    }
                }
                i = 2;
                i2 = i;
            }
            double d = dArr[i3];
            int i6 = i3 + 1;
            double d2 = dArr[i6];
            double[] dArr3 = dArr2[i3];
            double d3 = dArr3[0];
            double d4 = dArr3[1];
            double[] dArr4 = dArr2[i6];
            arcArr[i3] = new Arc(i2, d, d2, d3, d4, dArr4[0], dArr4[1]);
            i3 = i6;
        }
    }

    private static class Arc {
        private static final double EPSILON = 0.001d;
        private static final String TAG = "Arc";
        private static double[] sOurPercent = new double[91];
        double mArcDistance;
        double mArcVelocity;
        double mEllipseA;
        double mEllipseB;
        double mEllipseCenterX;
        double mEllipseCenterY;
        boolean mLinear;
        double[] mLut;
        double mOneOverDeltaTime;
        double mTime1;
        double mTime2;
        double mTmpCosAngle;
        double mTmpSinAngle;
        boolean mVertical;
        double mX1;
        double mX2;
        double mY1;
        double mY2;

        Arc(int i, double d, double d2, double d3, double d4, double d5, double d6) {
            this.mLinear = false;
            double d7 = d5 - d3;
            double d8 = d6 - d4;
            if (i == 1) {
                this.mVertical = true;
            } else if (i == 4) {
                this.mVertical = d8 > 0.0d;
            } else if (i == 5) {
                this.mVertical = d8 < 0.0d;
            } else {
                this.mVertical = false;
            }
            this.mTime1 = d;
            this.mTime2 = d2;
            this.mOneOverDeltaTime = 1.0d / (d2 - d);
            if (3 == i) {
                this.mLinear = true;
            }
            if (this.mLinear || Math.abs(d7) < EPSILON || Math.abs(d8) < EPSILON) {
                this.mLinear = true;
                this.mX1 = d3;
                this.mX2 = d5;
                this.mY1 = d4;
                this.mY2 = d6;
                double dHypot = Math.hypot(d8, d7);
                this.mArcDistance = dHypot;
                this.mArcVelocity = dHypot * this.mOneOverDeltaTime;
                double d9 = this.mTime2;
                double d10 = this.mTime1;
                this.mEllipseCenterX = d7 / (d9 - d10);
                this.mEllipseCenterY = d8 / (d9 - d10);
                return;
            }
            this.mLut = new double[101];
            boolean z = this.mVertical;
            this.mEllipseA = d7 * (z ? -1 : 1);
            this.mEllipseB = d8 * (z ? 1 : -1);
            this.mEllipseCenterX = z ? d5 : d3;
            this.mEllipseCenterY = z ? d4 : d6;
            buildTable(d3, d4, d5, d6);
            this.mArcVelocity = this.mArcDistance * this.mOneOverDeltaTime;
        }

        void setPoint(double d) {
            double dLookup = lookup((this.mVertical ? this.mTime2 - d : d - this.mTime1) * this.mOneOverDeltaTime) * 1.5707963267948966d;
            this.mTmpSinAngle = Math.sin(dLookup);
            this.mTmpCosAngle = Math.cos(dLookup);
        }

        double getX() {
            return this.mEllipseCenterX + (this.mEllipseA * this.mTmpSinAngle);
        }

        double getY() {
            return this.mEllipseCenterY + (this.mEllipseB * this.mTmpCosAngle);
        }

        double getDX() {
            double d = this.mEllipseA * this.mTmpCosAngle;
            double dHypot = this.mArcVelocity / Math.hypot(d, (-this.mEllipseB) * this.mTmpSinAngle);
            if (this.mVertical) {
                d = -d;
            }
            return d * dHypot;
        }

        double getDY() {
            double d = this.mEllipseA * this.mTmpCosAngle;
            double d2 = (-this.mEllipseB) * this.mTmpSinAngle;
            double dHypot = this.mArcVelocity / Math.hypot(d, d2);
            return this.mVertical ? (-d2) * dHypot : d2 * dHypot;
        }

        public double getLinearX(double d) {
            double d2 = (d - this.mTime1) * this.mOneOverDeltaTime;
            double d3 = this.mX1;
            return d3 + (d2 * (this.mX2 - d3));
        }

        public double getLinearY(double d) {
            double d2 = (d - this.mTime1) * this.mOneOverDeltaTime;
            double d3 = this.mY1;
            return d3 + (d2 * (this.mY2 - d3));
        }

        public double getLinearDX(double d) {
            return this.mEllipseCenterX;
        }

        public double getLinearDY(double d) {
            return this.mEllipseCenterY;
        }

        double lookup(double d) {
            if (d <= 0.0d) {
                return 0.0d;
            }
            if (d >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.mLut;
            double length = d * (dArr.length - 1);
            int i = (int) length;
            double d2 = length - i;
            double d3 = dArr[i];
            return d3 + (d2 * (dArr[i + 1] - d3));
        }

        private void buildTable(double d, double d2, double d3, double d4) {
            double dHypot;
            double d5 = d3 - d;
            double d6 = d2 - d4;
            int i = 0;
            double d7 = 0.0d;
            double d8 = 0.0d;
            double d9 = 0.0d;
            while (true) {
                if (i >= sOurPercent.length) {
                    break;
                }
                double d10 = d7;
                double radians = Math.toRadians((i * 90.0d) / (r15.length - 1));
                double dSin = Math.sin(radians) * d5;
                double dCos = Math.cos(radians) * d6;
                if (i > 0) {
                    dHypot = Math.hypot(dSin - d8, dCos - d9) + d10;
                    sOurPercent[i] = dHypot;
                } else {
                    dHypot = d10;
                }
                i++;
                d9 = dCos;
                d7 = dHypot;
                d8 = dSin;
            }
            double d11 = d7;
            this.mArcDistance = d11;
            int i2 = 0;
            while (true) {
                double[] dArr = sOurPercent;
                if (i2 >= dArr.length) {
                    break;
                }
                dArr[i2] = dArr[i2] / d11;
                i2++;
            }
            int i3 = 0;
            while (true) {
                if (i3 >= this.mLut.length) {
                    return;
                }
                double length = i3 / (r1.length - 1);
                int iBinarySearch = Arrays.binarySearch(sOurPercent, length);
                if (iBinarySearch >= 0) {
                    this.mLut[i3] = iBinarySearch / (sOurPercent.length - 1);
                } else if (iBinarySearch == -1) {
                    this.mLut[i3] = 0.0d;
                } else {
                    int i4 = -iBinarySearch;
                    int i5 = i4 - 2;
                    double[] dArr2 = sOurPercent;
                    double d12 = dArr2[i5];
                    this.mLut[i3] = (i5 + ((length - d12) / (dArr2[i4 - 1] - d12))) / (dArr2.length - 1);
                }
                i3++;
            }
        }
    }
}
