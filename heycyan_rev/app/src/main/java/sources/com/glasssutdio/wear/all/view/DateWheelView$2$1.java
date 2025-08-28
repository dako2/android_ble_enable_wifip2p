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
import kotlin.text.StringsKt;

/* compiled from: DateWheelView.kt */
@Metadata(m606d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m607d2 = {"<anonymous>", "", "position", "", "invoke"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
final class DateWheelView$2$1 extends Lambda implements Function1<Integer, Unit> {
    final /* synthetic */ DateWheelView this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DateWheelView$2$1(DateWheelView dateWheelView) {
        super(1);
        this.this$0 = dateWheelView;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
        invoke(num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(int i) {
        Integer intOrNull;
        int i2 = i + 2;
        String name = this.this$0.mMonthAdapter.getData().get(i2).getName();
        if (name != null) {
            StringExtKt.log(name);
        }
        DateWheelView dateWheelView = this.this$0;
        String name2 = dateWheelView.mMonthAdapter.getData().get(i2).getName();
        dateWheelView.currentMonth = (name2 == null || (intOrNull = StringsKt.toIntOrNull(name2)) == null) ? 1 : intOrNull.intValue();
        DateWheelView dateWheelView2 = this.this$0;
        dateWheelView2.defaultSelectMonth = dateWheelView2.currentMonth;
        List<WheelSelectModel> data = this.this$0.mMonthAdapter.getData();
        DateWheelView dateWheelView3 = this.this$0;
        int i3 = 0;
        for (Object obj : data) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ((WheelSelectModel) obj).setChecked(i3 == i2);
            dateWheelView3.mMonthAdapter.notifyDataSetChanged();
            i3 = i4;
        }
        DateWheelView dateWheelView4 = this.this$0;
        dateWheelView4.currentDay = dateWheelView4.defaultSelectDay;
        WheelSelectAdapter wheelSelectAdapter = this.this$0.mDayAdapter;
        DateWheelView dateWheelView5 = this.this$0;
        wheelSelectAdapter.setList(dateWheelView5.getDayDataByYearMonth(dateWheelView5.currentYear, this.this$0.currentMonth));
        RecyclerView recyclerView = this.this$0.binding.rcyDay;
        final DateWheelView dateWheelView6 = this.this$0;
        recyclerView.post(new Runnable() { // from class: com.glasssutdio.wear.all.view.DateWheelView$2$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DateWheelView$2$1.invoke$lambda$1(dateWheelView6);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$1(DateWheelView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.binding.rcyDay.scrollToPosition(this$0.defaultSelectDayIndex + (this$0.offsetItem * 2));
    }
}
