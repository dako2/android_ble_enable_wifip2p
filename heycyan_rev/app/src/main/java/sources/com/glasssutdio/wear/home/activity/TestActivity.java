package com.glasssutdio.wear.home.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.all.ThreadExtKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.utils.GFileUtilKt;
import com.glasssutdio.wear.all.utils.image.ImageUtils;
import com.glasssutdio.wear.databinding.ActivityTestBinding;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TestActivity.kt */
@Metadata(m606d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\u0006H\u0003J\u0012\u0010\f\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/TestActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityTestBinding;", "generateWatermark", "", "originFile", "Ljava/io/File;", "type", "", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class TestActivity extends BaseSettingActivity {
    private ActivityTestBinding binding;

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityTestBinding activityTestBindingInflate = ActivityTestBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityTestBindingInflate, "inflate(...)");
        this.binding = activityTestBindingInflate;
        ActivityTestBinding activityTestBinding = null;
        if (activityTestBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTestBindingInflate = null;
        }
        setContentView(activityTestBindingInflate.getRoot());
        initView();
        ActivityTestBinding activityTestBinding2 = this.binding;
        if (activityTestBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityTestBinding = activityTestBinding2;
        }
        activityTestBinding.btnSave.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.TestActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TestActivity.onCreate$lambda$0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(TestActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityTestBinding activityTestBinding = this$0.binding;
        if (activityTestBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTestBinding = null;
        }
        ImageView ivSearching = activityTestBinding.ivSearching;
        Intrinsics.checkNotNullExpressionValue(ivSearching, "ivSearching");
        ViewKt.rotate(ivSearching, (54 & 1) != 0 ? 1400L : 2500L, (54 & 2) != 0 ? -1 : 0, (54 & 4) != 0, (54 & 8) != 0 ? null : this$0.getLifecycle(), (54 & 16) != 0 ? 0L : 0L, (54 & 32) == 0 ? null : null);
    }

    private final void initView() {
        ActivityTestBinding activityTestBinding = this.binding;
        if (activityTestBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTestBinding = null;
        }
        activityTestBinding.tvSave.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.TestActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TestActivity.initView$lambda$1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$1(TestActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThreadExtKt.ktxRunOnBgSingle(this$0, new Function1<TestActivity, Unit>() { // from class: com.glasssutdio.wear.home.activity.TestActivity$initView$1$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TestActivity ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TestActivity testActivity) {
                invoke2(testActivity);
                return Unit.INSTANCE;
            }
        });
    }

    private final void generateWatermark(File originFile, int type) {
        int width;
        ImageUtils imageUtils = ImageUtils.INSTANCE;
        String path = originFile.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
        final Bitmap bitmapFromPath = imageUtils.getBitmapFromPath(path);
        ActivityTestBinding activityTestBinding = this.binding;
        ActivityTestBinding activityTestBinding2 = null;
        if (activityTestBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTestBinding = null;
        }
        ViewGroup.LayoutParams layoutParams = activityTestBinding.llWatermark.getLayoutParams();
        if (bitmapFromPath != null) {
            width = bitmapFromPath.getWidth();
        } else {
            ActivityTestBinding activityTestBinding3 = this.binding;
            if (activityTestBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTestBinding3 = null;
            }
            width = activityTestBinding3.llWatermark.getWidth();
        }
        layoutParams.width = width;
        ActivityTestBinding activityTestBinding4 = this.binding;
        if (activityTestBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTestBinding4 = null;
        }
        activityTestBinding4.llWatermark.setLayoutParams(layoutParams);
        ActivityTestBinding activityTestBinding5 = this.binding;
        if (activityTestBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityTestBinding2 = activityTestBinding5;
        }
        activityTestBinding2.llWatermark.postDelayed(new Runnable() { // from class: com.glasssutdio.wear.home.activity.TestActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() throws IOException {
                TestActivity.generateWatermark$lambda$2(this.f$0, bitmapFromPath);
            }
        }, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void generateWatermark$lambda$2(TestActivity this$0, Bitmap bitmap) throws IOException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ImageUtils imageUtils = ImageUtils.INSTANCE;
        ActivityTestBinding activityTestBinding = this$0.binding;
        if (activityTestBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTestBinding = null;
        }
        LinearLayout llWatermark = activityTestBinding.llWatermark;
        Intrinsics.checkNotNullExpressionValue(llWatermark, "llWatermark");
        Bitmap bitmapViewToBitmap = imageUtils.viewToBitmap(llWatermark);
        if (bitmap != null) {
            Bitmap bitmapCombineBitmaps = ImageUtils.INSTANCE.combineBitmaps(bitmap, bitmapViewToBitmap);
            File file = new File(GFileUtilKt.getCacheFolder().getAbsolutePath(), UUID.randomUUID() + ".jpg");
            XLog.m137i("图片地址：" + file.getAbsolutePath());
            if (!file.exists()) {
                file.createNewFile();
            }
            ImageUtils.INSTANCE.saveBitmapToFile(bitmapCombineBitmaps, file);
        }
    }
}
