package androidx.constraintlayout.core;

import androidx.exifinterface.media.ExifInterface;
import java.util.Arrays;
import java.util.HashSet;

/* loaded from: classes.dex */
public class SolverVariable implements Comparable<SolverVariable> {
    private static final boolean DO_NOT_USE = false;
    private static final boolean INTERNAL_DEBUG = false;
    static final int MAX_STRENGTH = 9;
    public static final int STRENGTH_BARRIER = 6;
    public static final int STRENGTH_CENTERING = 7;
    public static final int STRENGTH_EQUALITY = 5;
    public static final int STRENGTH_FIXED = 8;
    public static final int STRENGTH_HIGH = 3;
    public static final int STRENGTH_HIGHEST = 4;
    public static final int STRENGTH_LOW = 1;
    public static final int STRENGTH_MEDIUM = 2;
    public static final int STRENGTH_NONE = 0;
    private static final boolean VAR_USE_HASH = false;
    private static int sUniqueConstantId = 1;
    private static int sUniqueErrorId = 1;
    private static int sUniqueId = 1;
    private static int sUniqueSlackId = 1;
    private static int sUniqueUnrestrictedId = 1;
    public float computedValue;

    /* renamed from: id */
    public int f16id;
    public boolean inGoal;
    public boolean isFinalValue;
    ArrayRow[] mClientEquations;
    int mClientEquationsCount;
    int mDefinitionId;
    float[] mGoalStrengthVector;
    HashSet<ArrayRow> mInRows;
    boolean mIsSynonym;
    private String mName;
    float[] mStrengthVector;
    int mSynonym;
    float mSynonymDelta;
    Type mType;
    public int strength;
    public int usageInRowCount;

    public enum Type {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    static void increaseErrorId() {
        sUniqueErrorId++;
    }

    private static String getUniqueName(Type type, String str) {
        if (str != null) {
            return str + sUniqueErrorId;
        }
        int iOrdinal = type.ordinal();
        if (iOrdinal == 0) {
            StringBuilder sb = new StringBuilder("U");
            int i = sUniqueUnrestrictedId + 1;
            sUniqueUnrestrictedId = i;
            return sb.append(i).toString();
        }
        if (iOrdinal == 1) {
            StringBuilder sb2 = new StringBuilder("C");
            int i2 = sUniqueConstantId + 1;
            sUniqueConstantId = i2;
            return sb2.append(i2).toString();
        }
        if (iOrdinal == 2) {
            StringBuilder sb3 = new StringBuilder(ExifInterface.LATITUDE_SOUTH);
            int i3 = sUniqueSlackId + 1;
            sUniqueSlackId = i3;
            return sb3.append(i3).toString();
        }
        if (iOrdinal == 3) {
            StringBuilder sb4 = new StringBuilder("e");
            int i4 = sUniqueErrorId + 1;
            sUniqueErrorId = i4;
            return sb4.append(i4).toString();
        }
        if (iOrdinal == 4) {
            StringBuilder sb5 = new StringBuilder(ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
            int i5 = sUniqueId + 1;
            sUniqueId = i5;
            return sb5.append(i5).toString();
        }
        throw new AssertionError(type.name());
    }

    public SolverVariable(String str, Type type) {
        this.f16id = -1;
        this.mDefinitionId = -1;
        this.strength = 0;
        this.isFinalValue = false;
        this.mStrengthVector = new float[9];
        this.mGoalStrengthVector = new float[9];
        this.mClientEquations = new ArrayRow[16];
        this.mClientEquationsCount = 0;
        this.usageInRowCount = 0;
        this.mIsSynonym = false;
        this.mSynonym = -1;
        this.mSynonymDelta = 0.0f;
        this.mInRows = null;
        this.mName = str;
        this.mType = type;
    }

    public SolverVariable(Type type, String str) {
        this.f16id = -1;
        this.mDefinitionId = -1;
        this.strength = 0;
        this.isFinalValue = false;
        this.mStrengthVector = new float[9];
        this.mGoalStrengthVector = new float[9];
        this.mClientEquations = new ArrayRow[16];
        this.mClientEquationsCount = 0;
        this.usageInRowCount = 0;
        this.mIsSynonym = false;
        this.mSynonym = -1;
        this.mSynonymDelta = 0.0f;
        this.mInRows = null;
        this.mType = type;
    }

    void clearStrengths() {
        for (int i = 0; i < 9; i++) {
            this.mStrengthVector[i] = 0.0f;
        }
    }

    String strengthsToString() {
        String str = this + "[";
        boolean z = false;
        boolean z2 = true;
        for (int i = 0; i < this.mStrengthVector.length; i++) {
            String str2 = str + this.mStrengthVector[i];
            float[] fArr = this.mStrengthVector;
            float f = fArr[i];
            if (f > 0.0f) {
                z = false;
            } else if (f < 0.0f) {
                z = true;
            }
            if (f != 0.0f) {
                z2 = false;
            }
            if (i < fArr.length - 1) {
                str = str2 + ", ";
            } else {
                str = str2 + "] ";
            }
        }
        if (z) {
            str = str + " (-)";
        }
        return z2 ? str + " (*)" : str;
    }

    public final void addToRow(ArrayRow arrayRow) {
        int i = 0;
        while (true) {
            int i2 = this.mClientEquationsCount;
            if (i < i2) {
                if (this.mClientEquations[i] == arrayRow) {
                    return;
                } else {
                    i++;
                }
            } else {
                ArrayRow[] arrayRowArr = this.mClientEquations;
                if (i2 >= arrayRowArr.length) {
                    this.mClientEquations = (ArrayRow[]) Arrays.copyOf(arrayRowArr, arrayRowArr.length * 2);
                }
                ArrayRow[] arrayRowArr2 = this.mClientEquations;
                int i3 = this.mClientEquationsCount;
                arrayRowArr2[i3] = arrayRow;
                this.mClientEquationsCount = i3 + 1;
                return;
            }
        }
    }

    public final void removeFromRow(ArrayRow arrayRow) {
        int i = this.mClientEquationsCount;
        int i2 = 0;
        while (i2 < i) {
            if (this.mClientEquations[i2] == arrayRow) {
                while (i2 < i - 1) {
                    ArrayRow[] arrayRowArr = this.mClientEquations;
                    int i3 = i2 + 1;
                    arrayRowArr[i2] = arrayRowArr[i3];
                    i2 = i3;
                }
                this.mClientEquationsCount--;
                return;
            }
            i2++;
        }
    }

    public final void updateReferencesWithNewDefinition(LinearSystem linearSystem, ArrayRow arrayRow) {
        int i = this.mClientEquationsCount;
        for (int i2 = 0; i2 < i; i2++) {
            this.mClientEquations[i2].updateFromRow(linearSystem, arrayRow, false);
        }
        this.mClientEquationsCount = 0;
    }

    public void setFinalValue(LinearSystem linearSystem, float f) {
        this.computedValue = f;
        this.isFinalValue = true;
        this.mIsSynonym = false;
        this.mSynonym = -1;
        this.mSynonymDelta = 0.0f;
        int i = this.mClientEquationsCount;
        this.mDefinitionId = -1;
        for (int i2 = 0; i2 < i; i2++) {
            this.mClientEquations[i2].updateFromFinalVariable(linearSystem, this, false);
        }
        this.mClientEquationsCount = 0;
    }

    public void setSynonym(LinearSystem linearSystem, SolverVariable solverVariable, float f) {
        this.mIsSynonym = true;
        this.mSynonym = solverVariable.f16id;
        this.mSynonymDelta = f;
        int i = this.mClientEquationsCount;
        this.mDefinitionId = -1;
        for (int i2 = 0; i2 < i; i2++) {
            this.mClientEquations[i2].updateFromSynonymVariable(linearSystem, this, false);
        }
        this.mClientEquationsCount = 0;
        linearSystem.displayReadableRows();
    }

    public void reset() {
        this.mName = null;
        this.mType = Type.UNKNOWN;
        this.strength = 0;
        this.f16id = -1;
        this.mDefinitionId = -1;
        this.computedValue = 0.0f;
        this.isFinalValue = false;
        this.mIsSynonym = false;
        this.mSynonym = -1;
        this.mSynonymDelta = 0.0f;
        int i = this.mClientEquationsCount;
        for (int i2 = 0; i2 < i; i2++) {
            this.mClientEquations[i2] = null;
        }
        this.mClientEquationsCount = 0;
        this.usageInRowCount = 0;
        this.inGoal = false;
        Arrays.fill(this.mGoalStrengthVector, 0.0f);
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setType(Type type, String str) {
        this.mType = type;
    }

    @Override // java.lang.Comparable
    public int compareTo(SolverVariable solverVariable) {
        return this.f16id - solverVariable.f16id;
    }

    public String toString() {
        if (this.mName != null) {
            return "" + this.mName;
        }
        return "" + this.f16id;
    }
}
