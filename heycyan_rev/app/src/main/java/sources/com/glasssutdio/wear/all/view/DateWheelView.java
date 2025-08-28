package com.glasssutdio.wear.all.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.StringExtKt;
import com.glasssutdio.wear.all.adapter.WheelSelectAdapter;
import com.glasssutdio.wear.all.bean.WheelSelectModel;
import com.glasssutdio.wear.databinding.DateWheelViewBinding;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;

/* compiled from: DateWheelView.kt */
@Metadata(m606d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u0007H\u0002J\u001f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0007H\u0002¢\u0006\u0002\u0010!J\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070\u001cJ\u000e\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002J\u0010\u0010$\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u0007H\u0002J\u000e\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0015J\b\u0010(\u001a\u00020&H\u0014J\u001e\u0010)\u001a\u00020&2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0007J$\u0010+\u001a\u00020&2\u0006\u0010,\u001a\u00020-2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020&0/H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, m607d2 = {"Lcom/glasssutdio/wear/all/view/DateWheelView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/glasssutdio/wear/databinding/DateWheelViewBinding;", "currentDay", "currentMonth", "currentYear", "defaultSelectDay", "defaultSelectDayIndex", "defaultSelectMonth", "defaultSelectMonthIndex", "defaultSelectYear", "defaultSelectYearIndex", "isBirthday", "", "mDayAdapter", "Lcom/glasssutdio/wear/all/adapter/WheelSelectAdapter;", "mMonthAdapter", "mYearAdapter", "offsetItem", "getDayDataByYearMonth", "", "Lcom/glasssutdio/wear/all/bean/WheelSelectModel;", "year", "month", "getMonthDataByYear", "(Ljava/lang/Integer;)Ljava/util/List;", "getSelectDate", "getYearData", "getYearIndex", "isLight", "", "light", "onDetachedFromWindow", "setDefaultDate", "day", "setupScrollListener", "picker", "Landroidx/recyclerview/widget/RecyclerView;", "onSelected", "Lkotlin/Function1;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class DateWheelView extends ConstraintLayout {
    private DateWheelViewBinding binding;
    private int currentDay;
    private int currentMonth;
    private int currentYear;
    private int defaultSelectDay;
    private int defaultSelectDayIndex;
    private int defaultSelectMonth;
    private int defaultSelectMonthIndex;
    private int defaultSelectYear;
    private int defaultSelectYearIndex;
    private boolean isBirthday;
    private WheelSelectAdapter mDayAdapter;
    private WheelSelectAdapter mMonthAdapter;
    private WheelSelectAdapter mYearAdapter;
    private int offsetItem;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DateWheelView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DateWheelView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ DateWheelView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateWheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.defaultSelectYear = 2024;
        this.defaultSelectMonth = 1;
        this.defaultSelectDay = 1;
        this.currentMonth = 1;
        this.currentDay = 1;
        this.isBirthday = true;
        this.offsetItem = 2;
        DateWheelViewBinding dateWheelViewBindingInflate = DateWheelViewBinding.inflate(LayoutInflater.from(context), this, true);
        Intrinsics.checkNotNullExpressionValue(dateWheelViewBindingInflate, "inflate(...)");
        this.binding = dateWheelViewBindingInflate;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0775R.styleable.Qc_DateWheelView);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "obtainStyledAttributes(...)");
        boolean z = typedArrayObtainStyledAttributes.getBoolean(C0775R.styleable.Qc_DateWheelView_pdw_isLight, false);
        this.defaultSelectYear = typedArrayObtainStyledAttributes.getInteger(C0775R.styleable.Qc_DateWheelView_pdw_year, this.defaultSelectYear);
        this.mYearAdapter = new WheelSelectAdapter(z);
        this.mMonthAdapter = new WheelSelectAdapter(z);
        this.mDayAdapter = new WheelSelectAdapter(z);
        typedArrayObtainStyledAttributes.recycle();
        this.currentYear = this.defaultSelectYear;
        this.currentMonth = this.defaultSelectMonth;
        this.currentDay = this.defaultSelectDay;
        final RecyclerView recyclerView = this.binding.rcyYear;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(this.mYearAdapter);
        this.mYearAdapter.setList(getYearData());
        new LinearSnapHelper().attachToRecyclerView(recyclerView);
        Intrinsics.checkNotNull(recyclerView);
        setupScrollListener(recyclerView, new DateWheelView$1$1(this));
        this.defaultSelectYearIndex += this.offsetItem * 2;
        recyclerView.post(new Runnable() { // from class: com.glasssutdio.wear.all.view.DateWheelView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DateWheelView.lambda$1$lambda$0(recyclerView, this);
            }
        });
        final RecyclerView recyclerView2 = this.binding.rcyMonth;
        recyclerView2.setLayoutManager(new LinearLayoutManager(context));
        recyclerView2.setAdapter(this.mMonthAdapter);
        this.mMonthAdapter.setList(getMonthDataByYear$default(this, null, 1, null));
        new LinearSnapHelper().attachToRecyclerView(recyclerView2);
        Intrinsics.checkNotNull(recyclerView2);
        setupScrollListener(recyclerView2, new DateWheelView$2$1(this));
        this.defaultSelectMonthIndex += this.offsetItem * 2;
        recyclerView2.post(new Runnable() { // from class: com.glasssutdio.wear.all.view.DateWheelView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DateWheelView.lambda$3$lambda$2(recyclerView2, this);
            }
        });
        final RecyclerView recyclerView3 = this.binding.rcyDay;
        recyclerView3.setLayoutManager(new LinearLayoutManager(context));
        recyclerView3.setAdapter(this.mDayAdapter);
        this.mDayAdapter.setList(getDayDataByYearMonth(this.defaultSelectYear, this.defaultSelectMonth));
        new LinearSnapHelper().attachToRecyclerView(recyclerView3);
        Intrinsics.checkNotNull(recyclerView3);
        setupScrollListener(recyclerView3, new Function1<Integer, Unit>() { // from class: com.glasssutdio.wear.all.view.DateWheelView$3$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i2) {
                Integer intOrNull;
                int i3 = i2 + 2;
                String name = this.this$0.mDayAdapter.getData().get(i3).getName();
                if (name != null) {
                    StringExtKt.log(name);
                }
                DateWheelView dateWheelView = this.this$0;
                String name2 = dateWheelView.mDayAdapter.getData().get(i3).getName();
                dateWheelView.currentDay = (name2 == null || (intOrNull = StringsKt.toIntOrNull(name2)) == null) ? 1 : intOrNull.intValue();
                DateWheelView dateWheelView2 = this.this$0;
                dateWheelView2.defaultSelectDay = dateWheelView2.currentDay;
                List<WheelSelectModel> data = this.this$0.mDayAdapter.getData();
                DateWheelView dateWheelView3 = this.this$0;
                int i4 = 0;
                for (Object obj : data) {
                    int i5 = i4 + 1;
                    if (i4 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    ((WheelSelectModel) obj).setChecked(i4 == i3);
                    dateWheelView3.mDayAdapter.notifyDataSetChanged();
                    i4 = i5;
                }
            }
        });
        this.defaultSelectDayIndex += this.offsetItem * 2;
        recyclerView3.post(new Runnable() { // from class: com.glasssutdio.wear.all.view.DateWheelView$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                DateWheelView.lambda$5$lambda$4(recyclerView3, this);
            }
        });
    }

    static final void lambda$1$lambda$0(RecyclerView this_apply, DateWheelView this$0) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this_apply.scrollToPosition(this$0.defaultSelectYearIndex);
    }

    static final void lambda$3$lambda$2(RecyclerView this_apply, DateWheelView this$0) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this_apply.scrollToPosition(this$0.defaultSelectMonthIndex);
    }

    static final void lambda$5$lambda$4(RecyclerView this_apply, DateWheelView this$0) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this_apply.scrollToPosition(this$0.defaultSelectDayIndex);
    }

    public final void setDefaultDate(int year, int month, int day) {
        this.defaultSelectYear = year;
        this.defaultSelectMonth = month;
        this.defaultSelectDay = day;
        this.currentYear = year;
        this.currentMonth = month;
        this.currentDay = day;
        this.mYearAdapter.setList(getYearData());
        this.defaultSelectYearIndex = getYearIndex(year) + (this.offsetItem * 2);
        this.binding.rcyYear.post(new Runnable() { // from class: com.glasssutdio.wear.all.view.DateWheelView$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                DateWheelView.setDefaultDate$lambda$6(this.f$0);
            }
        });
        this.mMonthAdapter.setList(getMonthDataByYear(Integer.valueOf(year)));
        this.defaultSelectMonthIndex = (month - 1) + (this.offsetItem * 2);
        this.binding.rcyMonth.post(new Runnable() { // from class: com.glasssutdio.wear.all.view.DateWheelView$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                DateWheelView.setDefaultDate$lambda$7(this.f$0);
            }
        });
        this.mDayAdapter.setList(getDayDataByYearMonth(year, month));
        this.defaultSelectDayIndex = (day - 1) + (this.offsetItem * 2);
        this.binding.rcyDay.post(new Runnable() { // from class: com.glasssutdio.wear.all.view.DateWheelView$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                DateWheelView.setDefaultDate$lambda$8(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setDefaultDate$lambda$6(DateWheelView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.binding.rcyYear.scrollToPosition(this$0.defaultSelectYearIndex);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setDefaultDate$lambda$7(DateWheelView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.binding.rcyMonth.scrollToPosition(this$0.defaultSelectMonthIndex);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setDefaultDate$lambda$8(DateWheelView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.binding.rcyDay.scrollToPosition(this$0.defaultSelectDayIndex);
    }

    private final int getYearIndex(int year) {
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance(...)");
        int i = calendar.get(1);
        Iterator<Integer> it = new IntRange(i - 100, i).iterator();
        int i2 = 0;
        while (it.hasNext()) {
            int iNextInt = ((IntIterator) it).nextInt();
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (iNextInt == year) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public final void isLight(boolean light) {
        this.mYearAdapter.setLight(light);
        this.mYearAdapter.notifyDataSetChanged();
        this.mMonthAdapter.setLight(light);
        this.mMonthAdapter.notifyDataSetChanged();
        this.mDayAdapter.setLight(light);
        this.mDayAdapter.notifyDataSetChanged();
    }

    private final List<WheelSelectModel> getYearData() {
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance(...)");
        int i = calendar.get(1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new WheelSelectModel(null, false, true, 3, null));
        arrayList.add(new WheelSelectModel(null, false, true, 3, null));
        Iterator<Integer> it = new IntRange(i - 100, i).iterator();
        int i2 = 0;
        while (it.hasNext()) {
            int iNextInt = ((IntIterator) it).nextInt();
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(new WheelSelectModel(String.valueOf(iNextInt), this.defaultSelectYear == iNextInt, false, 4, null));
            if (this.defaultSelectYear == iNextInt) {
                this.defaultSelectYearIndex = i2;
            }
            i2 = i3;
        }
        arrayList.add(new WheelSelectModel(null, false, true, 3, null));
        arrayList.add(new WheelSelectModel(null, false, true, 3, null));
        return arrayList;
    }

    static /* synthetic */ List getMonthDataByYear$default(DateWheelView dateWheelView, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        return dateWheelView.getMonthDataByYear(num);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<WheelSelectModel> getMonthDataByYear(Integer year) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new WheelSelectModel(null, false, true, 3, null));
        arrayList.add(new WheelSelectModel(null, false, true, 3, null));
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        if (this.isBirthday && year != null && year.intValue() == i) {
            Iterator<Integer> it = new IntRange(1, calendar.get(2) + 1).iterator();
            int i2 = 0;
            while (it.hasNext()) {
                int iNextInt = ((IntIterator) it).nextInt();
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Arrays.copyOf(new Object[]{Integer.valueOf(iNextInt)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                arrayList.add(new WheelSelectModel(str, this.defaultSelectMonth == iNextInt, false, 4, null));
                if (this.defaultSelectMonth == iNextInt) {
                    this.defaultSelectMonthIndex = i2;
                }
                i2 = i3;
            }
        } else {
            Iterator<Integer> it2 = new IntRange(1, 12).iterator();
            int i4 = 0;
            while (it2.hasNext()) {
                int iNextInt2 = ((IntIterator) it2).nextInt();
                int i5 = i4 + 1;
                if (i4 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str2 = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Arrays.copyOf(new Object[]{Integer.valueOf(iNextInt2)}, 1));
                Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
                arrayList.add(new WheelSelectModel(str2, this.defaultSelectMonth == iNextInt2, false, 4, null));
                if (this.defaultSelectMonth == iNextInt2) {
                    this.defaultSelectMonthIndex = i4;
                }
                i4 = i5;
            }
        }
        arrayList.add(new WheelSelectModel(null, false, true, 3, null));
        arrayList.add(new WheelSelectModel(null, false, true, 3, null));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<WheelSelectModel> getDayDataByYearMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, year);
        calendar.set(2, month - 1);
        int actualMaximum = calendar.getActualMaximum(5);
        Calendar calendar2 = Calendar.getInstance();
        int i = calendar2.get(1);
        int i2 = calendar2.get(2) + 1;
        int i3 = calendar2.get(5);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new WheelSelectModel(null, false, true, 3, null));
        arrayList.add(new WheelSelectModel(null, false, true, 3, null));
        if (this.isBirthday && year == i && month == i2) {
            Iterator<Integer> it = new IntRange(1, i3).iterator();
            int i4 = 0;
            while (it.hasNext()) {
                int iNextInt = ((IntIterator) it).nextInt();
                int i5 = i4 + 1;
                if (i4 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Arrays.copyOf(new Object[]{Integer.valueOf(iNextInt)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                arrayList.add(new WheelSelectModel(str, this.defaultSelectDay == iNextInt, false, 4, null));
                if (this.defaultSelectDay == iNextInt) {
                    this.defaultSelectDayIndex = i4;
                }
                i4 = i5;
            }
        } else {
            Iterator<Integer> it2 = new IntRange(1, actualMaximum).iterator();
            int i6 = 0;
            while (it2.hasNext()) {
                int iNextInt2 = ((IntIterator) it2).nextInt();
                int i7 = i6 + 1;
                if (i6 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str2 = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Arrays.copyOf(new Object[]{Integer.valueOf(iNextInt2)}, 1));
                Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
                arrayList.add(new WheelSelectModel(str2, this.defaultSelectDay == iNextInt2, false, 4, null));
                if (this.defaultSelectDay == iNextInt2) {
                    this.defaultSelectDayIndex = i6;
                }
                i6 = i7;
            }
        }
        arrayList.add(new WheelSelectModel(null, false, true, 3, null));
        arrayList.add(new WheelSelectModel(null, false, true, 3, null));
        return arrayList;
    }

    private final void setupScrollListener(RecyclerView picker, final Function1<? super Integer, Unit> onSelected) {
        picker.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.glasssutdio.wear.all.view.DateWheelView.setupScrollListener.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                if (newState == 0) {
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                    int iFindFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    View viewFindViewByPosition = linearLayoutManager.findViewByPosition(iFindFirstVisibleItemPosition);
                    if ((viewFindViewByPosition != null ? Math.abs(viewFindViewByPosition.getTop()) : 0.0f) / (viewFindViewByPosition != null ? viewFindViewByPosition.getHeight() : 0) > 0.9d) {
                        iFindFirstVisibleItemPosition++;
                    }
                    XLog.m137i("position:" + iFindFirstVisibleItemPosition);
                    onSelected.invoke(Integer.valueOf(iFindFirstVisibleItemPosition));
                }
            }
        });
    }

    public final List<Integer> getSelectDate() {
        return CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(this.currentYear), Integer.valueOf(this.currentMonth), Integer.valueOf(this.currentDay)});
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.binding.rcyYear.clearOnScrollListeners();
        this.binding.rcyMonth.clearOnScrollListeners();
        this.binding.rcyDay.clearOnScrollListeners();
    }
}
