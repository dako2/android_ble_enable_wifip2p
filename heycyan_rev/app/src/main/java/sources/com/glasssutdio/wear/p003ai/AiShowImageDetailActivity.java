package com.glasssutdio.wear.p003ai;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.glasssutdio.wear.databinding.ActivityAiShowImageDetailBinding;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AiShowImageDetailActivity.kt */
@Metadata(m606d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u0012\u0010\n\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\r"}, m607d2 = {"Lcom/glasssutdio/wear/ai/AiShowImageDetailActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityAiShowImageDetailBinding;", "initView", "", "loadImageWithGlide", "url", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AiShowImageDetailActivity extends BaseSettingActivity {
    private ActivityAiShowImageDetailBinding binding;

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityAiShowImageDetailBinding activityAiShowImageDetailBindingInflate = ActivityAiShowImageDetailBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityAiShowImageDetailBindingInflate, "inflate(...)");
        this.binding = activityAiShowImageDetailBindingInflate;
        if (activityAiShowImageDetailBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAiShowImageDetailBindingInflate = null;
        }
        setContentView(activityAiShowImageDetailBindingInflate.getRoot());
        initView();
    }

    private final void initView() {
        Bundle extras = getIntent().getExtras();
        ActivityAiShowImageDetailBinding activityAiShowImageDetailBinding = null;
        String string = extras != null ? extras.getString("file_path") : null;
        Intrinsics.checkNotNull(string);
        if (this.binding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        try {
            loadImageWithGlide(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ActivityAiShowImageDetailBinding activityAiShowImageDetailBinding2 = this.binding;
        if (activityAiShowImageDetailBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAiShowImageDetailBinding = activityAiShowImageDetailBinding2;
        }
        activityAiShowImageDetailBinding.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.ai.AiShowImageDetailActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AiShowImageDetailActivity.initView$lambda$1$lambda$0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$1$lambda$0(AiShowImageDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    private final void loadImageWithGlide(String url) {
        RequestBuilder<Drawable> requestBuilderLoad = Glide.with((FragmentActivity) this).load(url);
        ActivityAiShowImageDetailBinding activityAiShowImageDetailBinding = this.binding;
        if (activityAiShowImageDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAiShowImageDetailBinding = null;
        }
        requestBuilderLoad.into(activityAiShowImageDetailBinding.showImageSrc);
    }
}
