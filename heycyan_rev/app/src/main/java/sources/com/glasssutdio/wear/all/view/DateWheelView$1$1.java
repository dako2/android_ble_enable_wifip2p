package com.glasssutdio.wear.all.view;

import androidx.recyclerview.widget.RecyclerView;
import com.glasssutdio.wear.all.StringExtKt;
import com.glasssutdio.wear.all.adapter.WheelSelectAdapter;
import com.glasssutdio.wear.all.bean.WheelSelectModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: DateWheelView.kt */
@Metadata(m606d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m607d2 = {"<anonymous>", "", "position", "", "invoke"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
final class DateWheelView$1$1 extends Lambda implements Function1<Integer, Unit> {
    final /* synthetic */ DateWheelView this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DateWheelView$1$1(DateWheelView dateWheelView) {
        super(1);
        this.this$0 = dateWheelView;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
        invoke(num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(int i) {
        int i2 = i + 2;
        String name = this.this$0.mYearAdapter.getData().get(i2).getName();
        if (name != null) {
            StringExtKt.log(name);
        }
        DateWheelView dateWheelView = this.this$0;
        String name2 = dateWheelView.mYearAdapter.getData().get(i2).getName();
        dateWheelView.currentYear = name2 != null ? Integer.parseInt(name2) : this.this$0.defaultSelectYear;
        List<WheelSelectModel> data = this.this$0.mYearAdapter.getData();
        DateWheelView dateWheelView2 = this.this$0;
        int i3 = 0;
        for (Object obj : data) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ((WheelSelectModel) obj).setChecked(i3 == i2);
            dateWheelView2.mYearAdapter.notifyDataSetChanged();
            i3 = i4;
        }
        WheelSelectAdapter wheelSelectAdapter = this.this$0.mMonthAdapter;
        DateWheelView dateWheelView3 = this.this$0;
        wheelSelectAdapter.setList(dateWheelView3.getMonthDataByYear(Integer.valueOf(dateWheelView3.currentYear)));
        RecyclerView recyclerView = this.this$0.binding.rcyMonth;
        final DateWheelView dateWheelView4 = this.this$0;
        recyclerView.post(new Runnable() { // from class: com.glasssutdio.wear.all.view.DateWheelView$1$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DateWheelView$1$1.invoke$lambda$1(dateWheelView4);
            }
        });
        DateWheelView dateWheelView5 = this.this$0;
        dateWheelView5.currentMonth = dateWheelView5.defaultSelectMonth;
        WheelSelectAdapter wheelSelectAdapter2 = this.this$0.mDayAdapter;
        DateWheelView dateWheelView6 = this.this$0;
        wheelSelectAdapter2.setList(dateWheelView6.getDayDataByYearMonth(dateWheelView6.currentYear, this.this$0.currentMonth));
        RecyclerView recyclerView2 = this.this$0.binding.rcyDay;
        final DateWheelView dateWheelView7 = this.this$0;
        recyclerView2.post(new Runnable() { // from class: com.glasssutdio.wear.all.view.DateWheelView$1$1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DateWheelView$1$1.invoke$lambda$2(dateWheelView7);
            }
        });
        DateWheelView dateWheelView8 = this.this$0;
        dateWheelView8.currentDay = dateWheelView8.defaultSelectDay;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$1(DateWheelView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.binding.rcyMonth.scrollToPosition(this$0.defaultSelectMonthIndex + (this$0.offsetItem * 2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$2(DateWheelView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.binding.rcyDay.scrollToPosition(this$0.defaultSelectDayIndex + (this$0.offsetItem * 2));
    }
}
