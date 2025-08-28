package com.glasssutdio.wear.all.view.rippleview.cycle;

import kotlin.Metadata;

/* compiled from: BaseLifecycle.kt */
@Metadata(m606d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&¨\u0006\u0006"}, m607d2 = {"Lcom/glasssutdio/wear/all/view/rippleview/cycle/BaseLifecycle;", "", "onDestroy", "", "onPause", "onResume", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public interface BaseLifecycle {

    /* compiled from: BaseLifecycle.kt */
    @Metadata(m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class DefaultImpls {
        public static void onDestroy(BaseLifecycle baseLifecycle) {
        }
    }

    void onDestroy();

    void onPause();

    void onResume();
}
