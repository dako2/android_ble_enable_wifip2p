package com.glasssutdio.wear.home.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.TextViewExtKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.bean.CommonSelectModel;
import com.glasssutdio.wear.all.bean.FileInfo;
import com.glasssutdio.wear.all.bean.ImageLocalModel;
import com.glasssutdio.wear.all.bean.Req.FeedbackReq;
import com.glasssutdio.wear.all.dialog.CommonSelectDialog;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.image.ImageUtils;
import com.glasssutdio.wear.all.view.GridImageRecyclerView;
import com.glasssutdio.wear.databinding.ActivityFeedbackBinding;
import com.glasssutdio.wear.home.bean.RequestFailModel;
import com.glasssutdio.wear.home.viewmodel.MainActivityVM;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.p014io.CloseableKt;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: FeedbackActivity.kt */
@Metadata(m606d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 >2\u00020\u0001:\u0001>B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u00101\u001a\u0002022\u0006\u00103\u001a\u000204H\u0002J\b\u00105\u001a\u000206H\u0003J\b\u00107\u001a\u000206H\u0002J\u0012\u00108\u001a\u0002062\b\u00109\u001a\u0004\u0018\u00010:H\u0014J\b\u0010;\u001a\u000206H\u0002J\b\u0010<\u001a\u000206H\u0002J\b\u0010=\u001a\u000206H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\b\"\u0004\b\u0014\u0010\nR\u001a\u0010\u0015\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\b\"\u0004\b\u0017\u0010\nR\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0010R\u001b\u0010\u001f\u001a\u00020 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b!\u0010\"R\u001a\u0010%\u001a\u00020&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010.\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\b\"\u0004\b0\u0010\n¨\u0006?"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/FeedbackActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityFeedbackBinding;", "defaultSelect", "", "getDefaultSelect", "()I", "setDefaultSelect", "(I)V", "feedbackType", "fileLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "getFileLauncher", "()Landroidx/activity/result/ActivityResultLauncher;", "galleryLauncher", "index", "getIndex", "setIndex", "indexSub", "getIndexSub", "setIndexSub", "isOther", "", "()Z", "setOther", "(Z)V", "launcher", "getLauncher", "mViewModel", "Lcom/glasssutdio/wear/home/viewmodel/MainActivityVM;", "getMViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/MainActivityVM;", "mViewModel$delegate", "Lkotlin/Lazy;", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "selectedImageList", "", "Lcom/glasssutdio/wear/all/bean/ImageLocalModel;", "times", "getTimes", "setTimes", "getFileInfoFromUri", "Lcom/glasssutdio/wear/all/bean/FileInfo;", "uri", "Landroid/net/Uri;", "initView", "", "observer", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "openFilePicker", "openGallery", "showTimesDialog", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class FeedbackActivity extends BaseSettingActivity {
    public static final String FEED_TYPE_INDEX = "feed_type_index";
    public static final String FEED_TYPE_INDEX_SUB = "feed_type_index_sub";
    public static final String FEED_TYPE_IS_OTHER = "feed_type_is_other";
    public static final String FEED_TYPE_NAME = "feed_type_name";
    private ActivityFeedbackBinding binding;
    private int defaultSelect;
    private int feedbackType;
    private final ActivityResultLauncher<Intent> fileLauncher;
    private final ActivityResultLauncher<Intent> galleryLauncher;
    private int index;
    private int indexSub;
    private boolean isOther;
    private final ActivityResultLauncher<Intent> launcher;

    /* renamed from: mViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mViewModel;
    private String name;
    private List<ImageLocalModel> selectedImageList;
    private int times;

    /* JADX WARN: Multi-variable type inference failed */
    public FeedbackActivity() {
        final FeedbackActivity feedbackActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewModel = LazyKt.lazy(new Function0<MainActivityVM>() { // from class: com.glasssutdio.wear.home.activity.FeedbackActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.MainActivityVM] */
            @Override // kotlin.jvm.functions.Function0
            public final MainActivityVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(feedbackActivity, Reflection.getOrCreateKotlinClass(MainActivityVM.class), qualifier, objArr);
            }
        });
        this.index = -1;
        this.indexSub = -1;
        this.name = "";
        this.times = -1;
        this.launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.glasssutdio.wear.home.activity.FeedbackActivity$$ExternalSyntheticLambda6
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                FeedbackActivity.launcher$lambda$0(this.f$0, (ActivityResult) obj);
            }
        });
        this.fileLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.glasssutdio.wear.home.activity.FeedbackActivity$$ExternalSyntheticLambda7
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                FeedbackActivity.fileLauncher$lambda$2(this.f$0, (ActivityResult) obj);
            }
        });
        this.selectedImageList = new ArrayList();
        this.galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.glasssutdio.wear.home.activity.FeedbackActivity$$ExternalSyntheticLambda8
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) throws FileNotFoundException {
                FeedbackActivity.galleryLauncher$lambda$15(this.f$0, (ActivityResult) obj);
            }
        });
    }

    private final MainActivityVM getMViewModel() {
        return (MainActivityVM) this.mViewModel.getValue();
    }

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int i) {
        this.index = i;
    }

    public final int getIndexSub() {
        return this.indexSub;
    }

    public final void setIndexSub(int i) {
        this.indexSub = i;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    /* renamed from: isOther, reason: from getter */
    public final boolean getIsOther() {
        return this.isOther;
    }

    public final void setOther(boolean z) {
        this.isOther = z;
    }

    public final int getTimes() {
        return this.times;
    }

    public final void setTimes(int i) {
        this.times = i;
    }

    public final ActivityResultLauncher<Intent> getLauncher() {
        return this.launcher;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void launcher$lambda$0(FeedbackActivity this$0, ActivityResult it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.getResultCode() == -1) {
            Intent data = it.getData();
            this$0.index = data != null ? data.getIntExtra("feed_type_index", -1) : -1;
            Intent data2 = it.getData();
            this$0.indexSub = data2 != null ? data2.getIntExtra("feed_type_index_sub", -1) : -1;
            Intent data3 = it.getData();
            ActivityFeedbackBinding activityFeedbackBinding = null;
            String stringExtra = data3 != null ? data3.getStringExtra("feed_type_name") : null;
            if (stringExtra == null) {
                stringExtra = "";
            }
            this$0.name = stringExtra;
            Intent data4 = it.getData();
            boolean z = false;
            if (data4 != null && data4.getBooleanExtra("feed_type_is_other", false)) {
                z = true;
            }
            this$0.isOther = z;
            ActivityFeedbackBinding activityFeedbackBinding2 = this$0.binding;
            if (activityFeedbackBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityFeedbackBinding = activityFeedbackBinding2;
            }
            activityFeedbackBinding.tvTypeName.setText(this$0.name);
        }
    }

    public final ActivityResultLauncher<Intent> getFileLauncher() {
        return this.fileLauncher;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fileLauncher$lambda$2(FeedbackActivity this$0, ActivityResult result) {
        Intent data;
        Uri data2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.getResultCode() != -1 || (data = result.getData()) == null || (data2 = data.getData()) == null) {
            return;
        }
        FileInfo fileInfoFromUri = this$0.getFileInfoFromUri(data2);
        ActivityFeedbackBinding activityFeedbackBinding = this$0.binding;
        ActivityFeedbackBinding activityFeedbackBinding2 = null;
        if (activityFeedbackBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFeedbackBinding = null;
        }
        activityFeedbackBinding.tvFileName.setText(fileInfoFromUri.getName());
        ActivityFeedbackBinding activityFeedbackBinding3 = this$0.binding;
        if (activityFeedbackBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityFeedbackBinding2 = activityFeedbackBinding3;
        }
        ViewKt.visible(activityFeedbackBinding2.tvFileName);
    }

    private final FileInfo getFileInfoFromUri(Uri uri) {
        ContentResolver contentResolver = getApplicationContext().getContentResolver();
        Cursor cursorQuery = contentResolver.query(uri, null, null, null, null);
        String string = "";
        long j = 0;
        if (cursorQuery != null) {
            Cursor cursor = cursorQuery;
            try {
                Cursor cursor2 = cursor;
                if (cursor2.moveToFirst()) {
                    string = cursor2.getString(cursor2.getColumnIndexOrThrow("_display_name"));
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    j = cursor2.getLong(cursor2.getColumnIndexOrThrow("_size"));
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursor, null);
            } finally {
            }
        }
        return new FileInfo(string, j, contentResolver.getType(uri), uri);
    }

    private final void openFilePicker() {
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        this.fileLauncher.launch(intent);
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityFeedbackBinding activityFeedbackBindingInflate = ActivityFeedbackBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityFeedbackBindingInflate, "inflate(...)");
        this.binding = activityFeedbackBindingInflate;
        if (activityFeedbackBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFeedbackBindingInflate = null;
        }
        setContentView(activityFeedbackBindingInflate.getRoot());
        initView();
        observer();
    }

    private final void observer() {
        MainActivityVM mViewModel = getMViewModel();
        FeedbackActivity feedbackActivity = this;
        mViewModel.getFailLD().observe(feedbackActivity, new FeedbackActivity$sam$androidx_lifecycle_Observer$0(new Function1<RequestFailModel, Unit>() { // from class: com.glasssutdio.wear.home.activity.FeedbackActivity$observer$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(RequestFailModel requestFailModel) {
                invoke2(requestFailModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RequestFailModel requestFailModel) {
                String msg = requestFailModel.getMsg();
                if (msg != null) {
                    GlobalKt.showToast$default(msg, 0, 1, null);
                }
                this.this$0.dismissLoadingDialog();
            }
        }));
        mViewModel.getFeedbackLD().observe(feedbackActivity, new FeedbackActivity$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: com.glasssutdio.wear.home.activity.FeedbackActivity$observer$1$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean bool) {
                Intrinsics.checkNotNull(bool);
                if (bool.booleanValue()) {
                    String string = this.this$0.getString(C0775R.string.feedback_2);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    GlobalKt.showToast$default(string, 0, 1, null);
                }
                this.this$0.dismissLoadingDialog();
            }
        }));
    }

    private final void initView() {
        final ActivityFeedbackBinding activityFeedbackBinding = this.binding;
        ActivityFeedbackBinding activityFeedbackBinding2 = null;
        if (activityFeedbackBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFeedbackBinding = null;
        }
        activityFeedbackBinding.title.tvTitle.setText(getString(C0775R.string.h_glass_267));
        activityFeedbackBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.FeedbackActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedbackActivity.initView$lambda$13$lambda$6(this.f$0, view);
            }
        });
        activityFeedbackBinding.tvDeviceName.setText(UserConfig.INSTANCE.getInstance().getDeviceName());
        EditText etContent = activityFeedbackBinding.etContent;
        Intrinsics.checkNotNullExpressionValue(etContent, "etContent");
        etContent.addTextChangedListener(new TextWatcher() { // from class: com.glasssutdio.wear.home.activity.FeedbackActivity$initView$lambda$13$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                activityFeedbackBinding.tvNum.setText(activityFeedbackBinding.etContent.getText().toString().length() + "/600");
            }
        });
        TextView tvTypeName = activityFeedbackBinding.tvTypeName;
        Intrinsics.checkNotNullExpressionValue(tvTypeName, "tvTypeName");
        TextViewExtKt.setupMarquee(tvTypeName);
        TextView tvTypeHint = activityFeedbackBinding.tvTypeHint;
        Intrinsics.checkNotNullExpressionValue(tvTypeHint, "tvTypeHint");
        TextViewExtKt.setupMarquee(tvTypeHint);
        TextView tvFrequencyHint = activityFeedbackBinding.tvFrequencyHint;
        Intrinsics.checkNotNullExpressionValue(tvFrequencyHint, "tvFrequencyHint");
        TextViewExtKt.setupMarquee(tvFrequencyHint);
        TextView tvFrequency = activityFeedbackBinding.tvFrequency;
        Intrinsics.checkNotNullExpressionValue(tvFrequency, "tvFrequency");
        TextViewExtKt.setupMarquee(tvFrequency);
        activityFeedbackBinding.clsFeedbackType.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.FeedbackActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedbackActivity.initView$lambda$13$lambda$8(this.f$0, view);
            }
        });
        activityFeedbackBinding.etSummary.setOnTouchListener(new View.OnTouchListener() { // from class: com.glasssutdio.wear.home.activity.FeedbackActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return FeedbackActivity.initView$lambda$13$lambda$9(view, motionEvent);
            }
        });
        activityFeedbackBinding.btnSelectFile.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.FeedbackActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedbackActivity.initView$lambda$13$lambda$10(this.f$0, view);
            }
        });
        activityFeedbackBinding.clsTimes.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.FeedbackActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedbackActivity.initView$lambda$13$lambda$11(this.f$0, view);
            }
        });
        this.index = getIntent().getIntExtra("feed_type_index", -1);
        this.indexSub = getIntent().getIntExtra("feed_type_index_sub", -1);
        String stringExtra = getIntent().getStringExtra("feed_type_name");
        if (stringExtra == null) {
            stringExtra = "";
        } else {
            Intrinsics.checkNotNull(stringExtra);
        }
        this.name = stringExtra;
        this.isOther = getIntent().getBooleanExtra("feed_type_is_other", false);
        activityFeedbackBinding.tvTypeName.setText(this.name);
        activityFeedbackBinding.tvConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.FeedbackActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedbackActivity.initView$lambda$13$lambda$12(activityFeedbackBinding, this, view);
            }
        });
        ActivityFeedbackBinding activityFeedbackBinding3 = this.binding;
        if (activityFeedbackBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityFeedbackBinding2 = activityFeedbackBinding3;
        }
        activityFeedbackBinding2.rcyImage.setOnItemClickListener(new GridImageRecyclerView.OnGridImageListener() { // from class: com.glasssutdio.wear.home.activity.FeedbackActivity$initView$1$8
            @Override // com.glasssutdio.wear.all.view.GridImageRecyclerView.OnGridImageListener
            public void onItemClick(ImageView imageView, int position) {
            }

            @Override // com.glasssutdio.wear.all.view.GridImageRecyclerView.OnGridImageListener
            public void onDelete(int position) {
                this.this$0.selectedImageList.remove(position);
            }

            @Override // com.glasssutdio.wear.all.view.GridImageRecyclerView.OnGridImageListener
            public void openPicture() {
                this.this$0.openGallery();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$13$lambda$6(FeedbackActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$13$lambda$8(FeedbackActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.launcher.launch(new Intent(this$0, (Class<?>) FeedbackSelectTypeActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initView$lambda$13$lambda$9(View view, MotionEvent motionEvent) {
        view.getParent().requestDisallowInterceptTouchEvent(true);
        if (motionEvent.getActionMasked() == 1) {
            view.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$13$lambda$10(FeedbackActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.openFilePicker();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$13$lambda$11(FeedbackActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showTimesDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$13$lambda$12(ActivityFeedbackBinding this_run, FeedbackActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this_run.etContent.getText().toString();
        if (string.length() == 0) {
            String string2 = this$0.getString(C0775R.string.feedback_1);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            GlobalKt.showToast$default(string2, 0, 1, null);
        } else {
            this$0.showLoadingDialog();
            MainActivityVM mViewModel = this$0.getMViewModel();
            int i = this$0.feedbackType;
            mViewModel.feedback(new FeedbackReq(i, i, null, string, null, 20, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void galleryLauncher$lambda$15(FeedbackActivity this$0, ActivityResult result) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.getResultCode() == -1) {
            Intent data = result.getData();
            ActivityFeedbackBinding activityFeedbackBinding = null;
            Uri data2 = data != null ? data.getData() : null;
            if (data2 != null) {
                String strSaveUriToExternalPrivateStorage = ImageUtils.INSTANCE.saveUriToExternalPrivateStorage(this$0, data2);
                List<ImageLocalModel> list = this$0.selectedImageList;
                ImageLocalModel imageLocalModel = new ImageLocalModel();
                if (strSaveUriToExternalPrivateStorage == null) {
                    strSaveUriToExternalPrivateStorage = "";
                }
                imageLocalModel.setPath(strSaveUriToExternalPrivateStorage);
                list.add(imageLocalModel);
                ActivityFeedbackBinding activityFeedbackBinding2 = this$0.binding;
                if (activityFeedbackBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityFeedbackBinding = activityFeedbackBinding2;
                }
                activityFeedbackBinding.rcyImage.setImageList(this$0.selectedImageList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openGallery() {
        Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        this.galleryLauncher.launch(intent);
    }

    public final int getDefaultSelect() {
        return this.defaultSelect;
    }

    public final void setDefaultSelect(int i) {
        this.defaultSelect = i;
    }

    private final void showTimesDialog() {
        String string = getString(C0775R.string.g_feedback_times_1);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = getString(C0775R.string.g_feedback_times_2);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        String string3 = getString(C0775R.string.g_feedback_times_3);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        final List listListOf = CollectionsKt.listOf((Object[]) new String[]{string, string2, string3});
        ArrayList arrayList = new ArrayList();
        Iterator it = listListOf.iterator();
        while (it.hasNext()) {
            arrayList.add(new CommonSelectModel((String) it.next(), false, 0, null, 14, null));
        }
        CommonSelectDialog.Builder defaultChecked = new CommonSelectDialog.Builder().setDefaultChecked(this.defaultSelect);
        String string4 = getString(C0775R.string.g_feedback_11);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        CommonSelectDialog commonSelectDialogBuild = defaultChecked.setTitle(string4).setData(arrayList).build();
        commonSelectDialogBuild.setOnItemCheckedListener(new Function2<Integer, CommonSelectModel, Unit>() { // from class: com.glasssutdio.wear.home.activity.FeedbackActivity.showTimesDialog.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, CommonSelectModel commonSelectModel) {
                invoke(num.intValue(), commonSelectModel);
                return Unit.INSTANCE;
            }

            public final void invoke(int i, CommonSelectModel subModel) {
                Intrinsics.checkNotNullParameter(subModel, "subModel");
                FeedbackActivity.this.setDefaultSelect(i);
                ActivityFeedbackBinding activityFeedbackBinding = FeedbackActivity.this.binding;
                if (activityFeedbackBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityFeedbackBinding = null;
                }
                activityFeedbackBinding.tvFrequency.setText(listListOf.get(i));
            }
        });
        commonSelectDialogBuild.show(getSupportFragmentManager(), "FrequencyDialog");
    }
}
