package com.glasssutdio.wear.all.utils;

import android.app.Activity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ActivityHelper.kt */
@Metadata(m606d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u0006\u0010\t\u001a\u00020\u0007J/\u0010\n\u001a\u00020\u00072\"\u0010\u000b\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\r0\f\"\n\u0012\u0006\b\u0001\u0012\u00020\u00050\r¢\u0006\u0002\u0010\u000eJ\u0006\u0010\u000f\u001a\u00020\u0007J\b\u0010\u0010\u001a\u00020\u0007H\u0002J\u000e\u0010\u0011\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u0006\u0010\u0012\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m607d2 = {"Lcom/glasssutdio/wear/all/utils/ActivityHelper;", "", "()V", "activities", "", "Landroid/app/Activity;", "addActivity", "", "activity", "exitApp", "finish", "clazz", "", "Ljava/lang/Class;", "([Ljava/lang/Class;)V", "finishAllActivitiesExceptNewest", "finishAllActivity", "removeActivity", "topActivity", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class ActivityHelper {
    public static final ActivityHelper INSTANCE = new ActivityHelper();
    private static final List<Activity> activities = new ArrayList();

    private ActivityHelper() {
    }

    public final void addActivity(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        activities.add(activity);
    }

    public final void removeActivity(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        activities.remove(activity);
    }

    public final Activity topActivity() {
        List<Activity> list = activities;
        if (list.isEmpty()) {
            throw new RuntimeException("List is empty.");
        }
        return list.get(list.size() - 1);
    }

    public final void exitApp() {
        finishAllActivity();
        System.exit(0);
    }

    private final void finishAllActivity() {
        Iterator<Activity> it = activities.iterator();
        while (it.hasNext()) {
            it.next().finish();
        }
    }

    public final void finish(Class<? extends Activity>... clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        for (Activity activity : activities) {
            if (ArraysKt.contains(clazz, activity.getClass())) {
                activity.finish();
            }
        }
    }

    public final void finishAllActivitiesExceptNewest() {
        List<Activity> list = activities;
        if (list.isEmpty()) {
            return;
        }
        Activity activity = (Activity) CollectionsKt.last((List) list);
        for (Activity activity2 : list) {
            if (!Intrinsics.areEqual(activity2, activity)) {
                activity2.finish();
            }
        }
        List<Activity> list2 = activities;
        list2.clear();
        list2.add(activity);
    }
}
