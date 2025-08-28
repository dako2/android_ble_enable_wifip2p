package com.glasssutdio.wear.home.album;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.viewpager2.widget.ViewPager2;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.ThreadExtKt;
import com.glasssutdio.wear.all.dialog.DeleteOrNotDialog;
import com.glasssutdio.wear.all.lifecycle.GlassLifeCycle;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.DateUtil;
import com.glasssutdio.wear.all.utils.GFileUtilKt;
import com.glasssutdio.wear.bus.BusEvent;
import com.glasssutdio.wear.bus.EventType;
import com.glasssutdio.wear.bus.RecordingToPcmSuccessfullyEvent;
import com.glasssutdio.wear.bus.VideoEisSuccessfullyEvent;
import com.glasssutdio.wear.database.entity.GlassAlbumEntity;
import com.glasssutdio.wear.databinding.ActivityShowMediaDetailBinding;
import com.glasssutdio.wear.depository.AlbumDepository;
import com.glasssutdio.wear.home.activity.LoginActivity;
import com.glasssutdio.wear.home.adapter.ImageAdapter;
import com.glasssutdio.wear.home.album.p005vm.AlbumListViewModel;
import com.glasssutdio.wear.home.album.update.PcmToMp3Kt;
import com.glasssutdio.wear.home.album.water.WatermarkGenerator;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.FilenameUtils;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import com.hjq.permissions.Permission;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.p014io.ByteStreamsKt;
import kotlin.p014io.CloseableKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: ShowMediaDetailActivity.kt */
@Metadata(m606d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020\u001cH\u0002J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\u0010\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020(H\u0002J\u000e\u0010)\u001a\u00020*2\u0006\u0010$\u001a\u00020%J\u0018\u0010+\u001a\u00020\u001c2\u0006\u0010,\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020#H\u0003J\u0012\u0010.\u001a\u00020\u001c2\b\u0010/\u001a\u0004\u0018\u000100H\u0014J\b\u00101\u001a\u00020\u001cH\u0014J\u0010\u00102\u001a\u00020\u001c2\u0006\u00103\u001a\u000204H\u0017J\u0010\u00105\u001a\u00020\u001c2\u0006\u00106\u001a\u00020\u0016H\u0016J\b\u00107\u001a\u00020\u001cH\u0014J\u0018\u00108\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020%2\u0006\u00109\u001a\u00020(H\u0002J\u0018\u0010:\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020%2\u0006\u0010;\u001a\u00020(H\u0002J\u0010\u0010<\u001a\u00020\u001c2\u0006\u0010=\u001a\u00020#H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006>"}, m607d2 = {"Lcom/glasssutdio/wear/home/album/ShowMediaDetailActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "Lcom/glasssutdio/wear/home/adapter/ImageAdapter$PhotoViewScaleListener;", "()V", "adapter", "Lcom/glasssutdio/wear/home/adapter/ImageAdapter;", "albumViewModel", "Lcom/glasssutdio/wear/home/album/vm/AlbumListViewModel;", "getAlbumViewModel", "()Lcom/glasssutdio/wear/home/album/vm/AlbumListViewModel;", "albumViewModel$delegate", "Lkotlin/Lazy;", "binding", "Lcom/glasssutdio/wear/databinding/ActivityShowMediaDetailBinding;", "currentPosition", "", "fileSize", "glassAlbumEntity", "Lcom/glasssutdio/wear/database/entity/GlassAlbumEntity;", "index", "Ljava/lang/Integer;", "isPhotoViewScaling", "", "isScrolling", "pageChangeCallback", "Landroidx/viewpager2/widget/ViewPager2$OnPageChangeCallback;", "userLike", "copyFile", "", "inputStream", "Ljava/io/InputStream;", "outputStream", "Ljava/io/OutputStream;", "deleteMedia", "getAppName", "", "context", "Landroid/content/Context;", "getMimeType", "file", "Ljava/io/File;", "getSystemMemoryInfo", "", "initViews", "refreshType", "name", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/glasssutdio/wear/bus/BusEvent;", "onPhotoViewScaling", "isScaling", "onStop", "saveFileToAppGalleryFolder", "sourceFile", "savePcmAsWavAndAddToMediaStore", "pcmFile", "shareToSystem", "imagePath", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class ShowMediaDetailActivity extends BaseSettingActivity implements ImageAdapter.PhotoViewScaleListener {
    private ImageAdapter adapter;

    /* renamed from: albumViewModel$delegate, reason: from kotlin metadata */
    private final Lazy albumViewModel;
    private ActivityShowMediaDetailBinding binding;
    private int currentPosition;
    private int fileSize;
    private GlassAlbumEntity glassAlbumEntity;
    private Integer index;
    private boolean isPhotoViewScaling;
    private boolean isScrolling;
    private ViewPager2.OnPageChangeCallback pageChangeCallback;
    private boolean userLike;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void savePcmAsWavAndAddToMediaStore$lambda$14$lambda$13(String str, Uri uri) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ShowMediaDetailActivity() {
        final ShowMediaDetailActivity showMediaDetailActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = null == true ? 1 : 0;
        this.albumViewModel = LazyKt.lazy(new Function0<AlbumListViewModel>() { // from class: com.glasssutdio.wear.home.album.ShowMediaDetailActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.album.vm.AlbumListViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final AlbumListViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(showMediaDetailActivity, Reflection.getOrCreateKotlinClass(AlbumListViewModel.class), qualifier, objArr);
            }
        });
        this.index = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AlbumListViewModel getAlbumViewModel() {
        return (AlbumListViewModel) this.albumViewModel.getValue();
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityShowMediaDetailBinding activityShowMediaDetailBindingInflate = ActivityShowMediaDetailBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityShowMediaDetailBindingInflate, "inflate(...)");
        this.binding = activityShowMediaDetailBindingInflate;
        if (activityShowMediaDetailBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityShowMediaDetailBindingInflate = null;
        }
        setContentView(activityShowMediaDetailBindingInflate.getRoot());
        EventBus.getDefault().post(this);
        initViews(1, "");
    }

    private final void initViews(int refreshType, String name) {
        Bundle extras = getIntent().getExtras();
        ActivityShowMediaDetailBinding activityShowMediaDetailBinding = null;
        String string = extras != null ? extras.getString("file_name") : null;
        Intrinsics.checkNotNull(string);
        if (refreshType != 2) {
            name = string;
        }
        XLog.m137i(name);
        this.index = getAlbumViewModel().getIndexByFileName(name);
        this.fileSize = getAlbumViewModel().initDetailData().size();
        this.adapter = new ImageAdapter(this, getAlbumViewModel().initDetailData(), this);
        final ActivityShowMediaDetailBinding activityShowMediaDetailBinding2 = this.binding;
        if (activityShowMediaDetailBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityShowMediaDetailBinding2 = null;
        }
        activityShowMediaDetailBinding2.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.album.ShowMediaDetailActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShowMediaDetailActivity.initViews$lambda$2$lambda$0(this.f$0, view);
            }
        });
        ViewPager2 viewPager2 = activityShowMediaDetailBinding2.viewPager2;
        ImageAdapter imageAdapter = this.adapter;
        if (imageAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            imageAdapter = null;
        }
        viewPager2.setAdapter(imageAdapter);
        this.pageChangeCallback = new ViewPager2.OnPageChangeCallback() { // from class: com.glasssutdio.wear.home.album.ShowMediaDetailActivity$initViews$1$2
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int position) throws IllegalStateException {
                if (this.this$0.getAlbumViewModel().initDetailData().size() != 0) {
                    this.this$0.currentPosition = position;
                    final GlassAlbumEntity glassAlbumEntity = this.this$0.getAlbumViewModel().initDetailData().get(position);
                    this.this$0.glassAlbumEntity = glassAlbumEntity;
                    ImageAdapter imageAdapter2 = null;
                    if (!StringsKt.endsWith$default(glassAlbumEntity.getFilePath(), "opus", false, 2, (Object) null)) {
                        ImageAdapter imageAdapter3 = this.this$0.adapter;
                        if (imageAdapter3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        } else {
                            imageAdapter2 = imageAdapter3;
                        }
                        imageAdapter2.handlePageChange(position);
                        final ShowMediaDetailActivity showMediaDetailActivity = this.this$0;
                        ThreadExtKt.ktxRunOnUi(this, new Function1<ShowMediaDetailActivity$initViews$1$2, Unit>() { // from class: com.glasssutdio.wear.home.album.ShowMediaDetailActivity$initViews$1$2$onPageSelected$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(ShowMediaDetailActivity$initViews$1$2 showMediaDetailActivity$initViews$1$2) {
                                invoke2(showMediaDetailActivity$initViews$1$2);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(ShowMediaDetailActivity$initViews$1$2 ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                ActivityShowMediaDetailBinding activityShowMediaDetailBinding3 = showMediaDetailActivity.binding;
                                ActivityShowMediaDetailBinding activityShowMediaDetailBinding4 = null;
                                if (activityShowMediaDetailBinding3 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityShowMediaDetailBinding3 = null;
                                }
                                activityShowMediaDetailBinding3.tvTitle.setText(new DateUtil(glassAlbumEntity.getTimestamp(), false).getY_M_D());
                                ActivityShowMediaDetailBinding activityShowMediaDetailBinding5 = showMediaDetailActivity.binding;
                                if (activityShowMediaDetailBinding5 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityShowMediaDetailBinding5 = null;
                                }
                                activityShowMediaDetailBinding5.tvSubTitle.setText(new DateUtil(glassAlbumEntity.getTimestamp(), false).getHHmmDate());
                                ActivityShowMediaDetailBinding activityShowMediaDetailBinding6 = showMediaDetailActivity.binding;
                                if (activityShowMediaDetailBinding6 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                } else {
                                    activityShowMediaDetailBinding4 = activityShowMediaDetailBinding6;
                                }
                                activityShowMediaDetailBinding4.tvLike.setSelected(glassAlbumEntity.getUserLike() == 1);
                            }
                        });
                        return;
                    }
                    AlbumDepository.Companion.getGetInstance().decodeOpusStream(glassAlbumEntity);
                    String string2 = this.this$0.getString(C0775R.string.album_glass_43);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    GlobalKt.showToast$default(string2, 0, 1, null);
                    this.this$0.finish();
                    return;
                }
                this.this$0.finish();
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrollStateChanged(int state) {
                if (state == 0) {
                    this.this$0.isScrolling = false;
                } else {
                    if (state != 1) {
                        return;
                    }
                    this.this$0.isScrolling = true;
                }
            }
        };
        ViewPager2 viewPager22 = activityShowMediaDetailBinding2.viewPager2;
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.pageChangeCallback;
        Intrinsics.checkNotNull(onPageChangeCallback);
        viewPager22.registerOnPageChangeCallback(onPageChangeCallback);
        if (this.index != null) {
            ViewPager2 viewPager23 = activityShowMediaDetailBinding2.viewPager2;
            Integer num = this.index;
            Intrinsics.checkNotNull(num);
            viewPager23.setCurrentItem(num.intValue(), false);
        }
        activityShowMediaDetailBinding2.viewPager2.setOnTouchListener(new View.OnTouchListener() { // from class: com.glasssutdio.wear.home.album.ShowMediaDetailActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return ShowMediaDetailActivity.initViews$lambda$2$lambda$1(this.f$0, activityShowMediaDetailBinding2, view, motionEvent);
            }
        });
        View[] viewArr = new View[5];
        ActivityShowMediaDetailBinding activityShowMediaDetailBinding3 = this.binding;
        if (activityShowMediaDetailBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityShowMediaDetailBinding3 = null;
        }
        viewArr[0] = activityShowMediaDetailBinding3.tvSave;
        ActivityShowMediaDetailBinding activityShowMediaDetailBinding4 = this.binding;
        if (activityShowMediaDetailBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityShowMediaDetailBinding4 = null;
        }
        viewArr[1] = activityShowMediaDetailBinding4.tvShare;
        ActivityShowMediaDetailBinding activityShowMediaDetailBinding5 = this.binding;
        if (activityShowMediaDetailBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityShowMediaDetailBinding5 = null;
        }
        viewArr[2] = activityShowMediaDetailBinding5.tvDelete;
        ActivityShowMediaDetailBinding activityShowMediaDetailBinding6 = this.binding;
        if (activityShowMediaDetailBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityShowMediaDetailBinding6 = null;
        }
        viewArr[3] = activityShowMediaDetailBinding6.tvLike;
        ActivityShowMediaDetailBinding activityShowMediaDetailBinding7 = this.binding;
        if (activityShowMediaDetailBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityShowMediaDetailBinding = activityShowMediaDetailBinding7;
        }
        viewArr[4] = activityShowMediaDetailBinding.tvEdit;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.glasssutdio.wear.home.album.ShowMediaDetailActivity.initViews.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) throws IOException {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View setOnClickListener) throws IOException {
                Intrinsics.checkNotNullParameter(setOnClickListener, "$this$setOnClickListener");
                ActivityShowMediaDetailBinding activityShowMediaDetailBinding8 = ShowMediaDetailActivity.this.binding;
                ActivityShowMediaDetailBinding activityShowMediaDetailBinding9 = null;
                if (activityShowMediaDetailBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityShowMediaDetailBinding8 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityShowMediaDetailBinding8.tvSave)) {
                    if (ShowMediaDetailActivity.this.glassAlbumEntity != null) {
                        if (UserConfig.INSTANCE.getInstance().getPictureWatermark()) {
                            GlassAlbumEntity glassAlbumEntity = ShowMediaDetailActivity.this.glassAlbumEntity;
                            Intrinsics.checkNotNull(glassAlbumEntity);
                            if (glassAlbumEntity.getFileType() == 1) {
                                ShowMediaDetailActivity.this.showLoadingDialog();
                                WatermarkGenerator.WatermarkConfig watermarkConfig = new WatermarkGenerator.WatermarkConfig(null, null, null, null, 0, 31, null);
                                ShowMediaDetailActivity showMediaDetailActivity = ShowMediaDetailActivity.this;
                                watermarkConfig.setNameImage(BitmapFactory.decodeResource(setOnClickListener.getResources(), C0775R.mipmap.app_name_icon_black));
                                String str = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss").format(new Date());
                                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                                watermarkConfig.setDateTime(str);
                                watermarkConfig.setLogo(BitmapFactory.decodeResource(setOnClickListener.getResources(), C0775R.mipmap.ic_album_logo));
                                String string2 = showMediaDetailActivity.getString(C0775R.string.album_glass_32);
                                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                                watermarkConfig.setExtraText(string2);
                                GlassAlbumEntity glassAlbumEntity2 = ShowMediaDetailActivity.this.glassAlbumEntity;
                                Intrinsics.checkNotNull(glassAlbumEntity2);
                                Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(glassAlbumEntity2.getFilePath());
                                WatermarkGenerator watermarkGenerator = WatermarkGenerator.INSTANCE;
                                ShowMediaDetailActivity showMediaDetailActivity2 = ShowMediaDetailActivity.this;
                                Intrinsics.checkNotNull(bitmapDecodeFile);
                                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(ShowMediaDetailActivity.this), null, null, new AnonymousClass1(ShowMediaDetailActivity.this, GFileUtilKt.getAlbumDirFile().getAbsolutePath(), watermarkGenerator.addBottomWatermark(showMediaDetailActivity2, bitmapDecodeFile, watermarkConfig), null), 3, null);
                                return;
                            }
                            ShowMediaDetailActivity showMediaDetailActivity3 = ShowMediaDetailActivity.this;
                            GlassAlbumEntity glassAlbumEntity3 = ShowMediaDetailActivity.this.glassAlbumEntity;
                            Intrinsics.checkNotNull(glassAlbumEntity3);
                            showMediaDetailActivity3.saveFileToAppGalleryFolder(showMediaDetailActivity3, new File(glassAlbumEntity3.getFilePath()));
                            return;
                        }
                        try {
                            ShowMediaDetailActivity showMediaDetailActivity4 = ShowMediaDetailActivity.this;
                            GlassAlbumEntity glassAlbumEntity4 = ShowMediaDetailActivity.this.glassAlbumEntity;
                            Intrinsics.checkNotNull(glassAlbumEntity4);
                            showMediaDetailActivity4.saveFileToAppGalleryFolder(showMediaDetailActivity4, new File(glassAlbumEntity4.getFilePath()));
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            String string3 = ShowMediaDetailActivity.this.getString(C0775R.string.album_glass_10_1);
                            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                            GlobalKt.showToast$default(string3, 0, 1, null);
                            return;
                        }
                    }
                    return;
                }
                ActivityShowMediaDetailBinding activityShowMediaDetailBinding10 = ShowMediaDetailActivity.this.binding;
                if (activityShowMediaDetailBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityShowMediaDetailBinding10 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityShowMediaDetailBinding10.tvShare)) {
                    if (ShowMediaDetailActivity.this.glassAlbumEntity != null) {
                        GlassAlbumEntity glassAlbumEntity5 = ShowMediaDetailActivity.this.glassAlbumEntity;
                        Intrinsics.checkNotNull(glassAlbumEntity5);
                        if (glassAlbumEntity5.getFileType() == 3) {
                            ShowMediaDetailActivity showMediaDetailActivity5 = ShowMediaDetailActivity.this;
                            GlassAlbumEntity glassAlbumEntity6 = showMediaDetailActivity5.glassAlbumEntity;
                            Intrinsics.checkNotNull(glassAlbumEntity6);
                            showMediaDetailActivity5.shareToSystem(glassAlbumEntity6.getFilePath());
                            return;
                        }
                        if (UserConfig.INSTANCE.getInstance().getPictureWatermark()) {
                            GlassAlbumEntity glassAlbumEntity7 = ShowMediaDetailActivity.this.glassAlbumEntity;
                            Intrinsics.checkNotNull(glassAlbumEntity7);
                            if (glassAlbumEntity7.getFileType() == 1) {
                                ShowMediaDetailActivity.this.showLoadingDialog();
                                WatermarkGenerator.WatermarkConfig watermarkConfig2 = new WatermarkGenerator.WatermarkConfig(null, null, null, null, 0, 31, null);
                                ShowMediaDetailActivity showMediaDetailActivity6 = ShowMediaDetailActivity.this;
                                watermarkConfig2.setNameImage(BitmapFactory.decodeResource(setOnClickListener.getResources(), C0775R.mipmap.app_name_icon_black));
                                String str2 = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss").format(new Date());
                                Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
                                watermarkConfig2.setDateTime(str2);
                                watermarkConfig2.setLogo(BitmapFactory.decodeResource(setOnClickListener.getResources(), C0775R.mipmap.ic_album_logo));
                                String string4 = showMediaDetailActivity6.getString(C0775R.string.album_glass_32);
                                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                                watermarkConfig2.setExtraText(string4);
                                GlassAlbumEntity glassAlbumEntity8 = ShowMediaDetailActivity.this.glassAlbumEntity;
                                Intrinsics.checkNotNull(glassAlbumEntity8);
                                Bitmap bitmapDecodeFile2 = BitmapFactory.decodeFile(glassAlbumEntity8.getFilePath());
                                WatermarkGenerator watermarkGenerator2 = WatermarkGenerator.INSTANCE;
                                ShowMediaDetailActivity showMediaDetailActivity7 = ShowMediaDetailActivity.this;
                                Intrinsics.checkNotNull(bitmapDecodeFile2);
                                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(ShowMediaDetailActivity.this), null, null, new AnonymousClass2(ShowMediaDetailActivity.this, GFileUtilKt.getCacheFolder().getAbsolutePath(), watermarkGenerator2.addBottomWatermark(showMediaDetailActivity7, bitmapDecodeFile2, watermarkConfig2), null), 3, null);
                                return;
                            }
                            ShowMediaDetailActivity showMediaDetailActivity8 = ShowMediaDetailActivity.this;
                            GlassAlbumEntity glassAlbumEntity9 = showMediaDetailActivity8.glassAlbumEntity;
                            Intrinsics.checkNotNull(glassAlbumEntity9);
                            showMediaDetailActivity8.shareToSystem(glassAlbumEntity9.getFilePath());
                            return;
                        }
                        ShowMediaDetailActivity showMediaDetailActivity9 = ShowMediaDetailActivity.this;
                        GlassAlbumEntity glassAlbumEntity10 = showMediaDetailActivity9.glassAlbumEntity;
                        Intrinsics.checkNotNull(glassAlbumEntity10);
                        showMediaDetailActivity9.shareToSystem(glassAlbumEntity10.getFilePath());
                        return;
                    }
                    return;
                }
                ActivityShowMediaDetailBinding activityShowMediaDetailBinding11 = ShowMediaDetailActivity.this.binding;
                if (activityShowMediaDetailBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityShowMediaDetailBinding11 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityShowMediaDetailBinding11.tvDelete)) {
                    ShowMediaDetailActivity.this.deleteMedia();
                    return;
                }
                ActivityShowMediaDetailBinding activityShowMediaDetailBinding12 = ShowMediaDetailActivity.this.binding;
                if (activityShowMediaDetailBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityShowMediaDetailBinding12 = null;
                }
                if (!Intrinsics.areEqual(setOnClickListener, activityShowMediaDetailBinding12.tvLike)) {
                    ActivityShowMediaDetailBinding activityShowMediaDetailBinding13 = ShowMediaDetailActivity.this.binding;
                    if (activityShowMediaDetailBinding13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityShowMediaDetailBinding9 = activityShowMediaDetailBinding13;
                    }
                    Intrinsics.areEqual(setOnClickListener, activityShowMediaDetailBinding9.tvEdit);
                    return;
                }
                if (GlobalKt.isLogin(ShowMediaDetailActivity.this)) {
                    ActivityShowMediaDetailBinding activityShowMediaDetailBinding14 = ShowMediaDetailActivity.this.binding;
                    if (activityShowMediaDetailBinding14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityShowMediaDetailBinding14 = null;
                    }
                    if (activityShowMediaDetailBinding14.tvLike.isSelected()) {
                        GlassAlbumEntity glassAlbumEntity11 = ShowMediaDetailActivity.this.glassAlbumEntity;
                        Intrinsics.checkNotNull(glassAlbumEntity11);
                        glassAlbumEntity11.setUserLike(2);
                    } else {
                        GlassAlbumEntity glassAlbumEntity12 = ShowMediaDetailActivity.this.glassAlbumEntity;
                        Intrinsics.checkNotNull(glassAlbumEntity12);
                        glassAlbumEntity12.setUserLike(1);
                    }
                    AlbumListViewModel albumViewModel = ShowMediaDetailActivity.this.getAlbumViewModel();
                    GlassAlbumEntity glassAlbumEntity13 = ShowMediaDetailActivity.this.glassAlbumEntity;
                    Intrinsics.checkNotNull(glassAlbumEntity13);
                    albumViewModel.updateLikeOrNot(glassAlbumEntity13);
                    List<GlassAlbumEntity> listInitDetailData = ShowMediaDetailActivity.this.getAlbumViewModel().initDetailData();
                    Integer num2 = ShowMediaDetailActivity.this.index;
                    Intrinsics.checkNotNull(num2);
                    int iIntValue = num2.intValue();
                    GlassAlbumEntity glassAlbumEntity14 = ShowMediaDetailActivity.this.glassAlbumEntity;
                    Intrinsics.checkNotNull(glassAlbumEntity14);
                    listInitDetailData.set(iIntValue, glassAlbumEntity14);
                    ActivityShowMediaDetailBinding activityShowMediaDetailBinding15 = ShowMediaDetailActivity.this.binding;
                    if (activityShowMediaDetailBinding15 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityShowMediaDetailBinding15 = null;
                    }
                    TextView textView = activityShowMediaDetailBinding15.tvLike;
                    ActivityShowMediaDetailBinding activityShowMediaDetailBinding16 = ShowMediaDetailActivity.this.binding;
                    if (activityShowMediaDetailBinding16 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityShowMediaDetailBinding9 = activityShowMediaDetailBinding16;
                    }
                    textView.setSelected(!activityShowMediaDetailBinding9.tvLike.isSelected());
                    EventBus.getDefault().post(new EventType(10));
                    return;
                }
                ShowMediaDetailActivity showMediaDetailActivity10 = ShowMediaDetailActivity.this;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(showMediaDetailActivity10, (Class<?>) LoginActivity.class);
                for (Pair pair : arrayList) {
                    if (pair != null) {
                        String str3 = (String) pair.getFirst();
                        Object second = pair.getSecond();
                        if (second instanceof Integer) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, ((Number) second).intValue()), "putExtra(...)");
                        } else if (second instanceof Byte) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, ((Number) second).byteValue()), "putExtra(...)");
                        } else if (second instanceof Character) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, ((Character) second).charValue()), "putExtra(...)");
                        } else if (second instanceof Short) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, ((Number) second).shortValue()), "putExtra(...)");
                        } else if (second instanceof Boolean) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, ((Boolean) second).booleanValue()), "putExtra(...)");
                        } else if (second instanceof Long) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, ((Number) second).longValue()), "putExtra(...)");
                        } else if (second instanceof Float) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, ((Number) second).floatValue()), "putExtra(...)");
                        } else if (second instanceof Double) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, ((Number) second).doubleValue()), "putExtra(...)");
                        } else if (second instanceof String) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (String) second), "putExtra(...)");
                        } else if (second instanceof CharSequence) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (CharSequence) second), "putExtra(...)");
                        } else if (second instanceof Parcelable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (Parcelable) second), "putExtra(...)");
                        } else if (second instanceof Object[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (Serializable) second), "putExtra(...)");
                        } else if (second instanceof ArrayList) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (Serializable) second), "putExtra(...)");
                        } else if (second instanceof Serializable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (Serializable) second), "putExtra(...)");
                        } else if (second instanceof boolean[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (boolean[]) second), "putExtra(...)");
                        } else if (second instanceof byte[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (byte[]) second), "putExtra(...)");
                        } else if (second instanceof short[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (short[]) second), "putExtra(...)");
                        } else if (second instanceof char[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (char[]) second), "putExtra(...)");
                        } else if (second instanceof int[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (int[]) second), "putExtra(...)");
                        } else if (second instanceof long[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (long[]) second), "putExtra(...)");
                        } else if (second instanceof float[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (float[]) second), "putExtra(...)");
                        } else if (second instanceof double[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (double[]) second), "putExtra(...)");
                        } else if (second instanceof Bundle) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (Bundle) second), "putExtra(...)");
                        } else if (second instanceof Intent) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str3, (Parcelable) second), "putExtra(...)");
                        } else {
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                }
                showMediaDetailActivity10.startActivity(intent);
            }

            /* compiled from: ShowMediaDetailActivity.kt */
            @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
            @DebugMetadata(m619c = "com.glasssutdio.wear.home.album.ShowMediaDetailActivity$initViews$2$1", m620f = "ShowMediaDetailActivity.kt", m621i = {}, m622l = {EMachine.EM_TILE64}, m623m = "invokeSuspend", m624n = {}, m625s = {})
            /* renamed from: com.glasssutdio.wear.home.album.ShowMediaDetailActivity$initViews$2$1, reason: invalid class name */
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $path;
                final /* synthetic */ Bitmap $watermarkedBitmap;
                int label;
                final /* synthetic */ ShowMediaDetailActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(ShowMediaDetailActivity showMediaDetailActivity, String str, Bitmap bitmap, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.this$0 = showMediaDetailActivity;
                    this.$path = str;
                    this.$watermarkedBitmap = bitmap;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.this$0, this.$path, this.$watermarkedBitmap, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) throws Throwable {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    try {
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.label = 1;
                            obj = BuildersKt.withContext(Dispatchers.getIO(), new ShowMediaDetailActivity$initViews$2$1$compressedFile$1(this.this$0, this.$path, this.$watermarkedBitmap, null), this);
                            if (obj == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        ShowMediaDetailActivity showMediaDetailActivity = this.this$0;
                        showMediaDetailActivity.saveFileToAppGalleryFolder(showMediaDetailActivity, (File) obj);
                        this.this$0.dismissLoadingDialog();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return Unit.INSTANCE;
                }
            }

            /* compiled from: ShowMediaDetailActivity.kt */
            @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
            @DebugMetadata(m619c = "com.glasssutdio.wear.home.album.ShowMediaDetailActivity$initViews$2$2", m620f = "ShowMediaDetailActivity.kt", m621i = {}, m622l = {243}, m623m = "invokeSuspend", m624n = {}, m625s = {})
            /* renamed from: com.glasssutdio.wear.home.album.ShowMediaDetailActivity$initViews$2$2, reason: invalid class name */
            static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $path;
                final /* synthetic */ Bitmap $watermarkedBitmap;
                int label;
                final /* synthetic */ ShowMediaDetailActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass2(ShowMediaDetailActivity showMediaDetailActivity, String str, Bitmap bitmap, Continuation<? super AnonymousClass2> continuation) {
                    super(2, continuation);
                    this.this$0 = showMediaDetailActivity;
                    this.$path = str;
                    this.$watermarkedBitmap = bitmap;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass2(this.this$0, this.$path, this.$watermarkedBitmap, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) throws Throwable {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    try {
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.label = 1;
                            obj = BuildersKt.withContext(Dispatchers.getIO(), new ShowMediaDetailActivity$initViews$2$2$compressedFile$1(this.this$0, this.$path, this.$watermarkedBitmap, null), this);
                            if (obj == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        ShowMediaDetailActivity showMediaDetailActivity = this.this$0;
                        String absolutePath = ((File) obj).getAbsolutePath();
                        Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
                        showMediaDetailActivity.shareToSystem(absolutePath);
                        this.this$0.dismissLoadingDialog();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return Unit.INSTANCE;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$2$lambda$0(ShowMediaDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initViews$lambda$2$lambda$1(ShowMediaDetailActivity this$0, ActivityShowMediaDetailBinding this_run, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        if (this$0.isPhotoViewScaling) {
            return false;
        }
        this_run.viewPager2.dispatchTouchEvent(motionEvent);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteMedia() {
        DeleteOrNotDialog.Builder builder = new DeleteOrNotDialog.Builder();
        String string = getString(C0775R.string.album_glass_15);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        DeleteOrNotDialog.Builder content = builder.setTitle(string).setContent("");
        String string2 = getString(C0775R.string.album_glass_9);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        DeleteOrNotDialog.Builder confirmMessage = content.setConfirmMessage(string2);
        String string3 = getString(C0775R.string.h_glass_cancel);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        DeleteOrNotDialog deleteOrNotDialogBuild = confirmMessage.setCancelMessage(string3).build();
        deleteOrNotDialogBuild.show(getSupportFragmentManager(), "showRestFactoryDialog");
        deleteOrNotDialogBuild.setOnConfirmListener(new Function1<View, Unit>() { // from class: com.glasssutdio.wear.home.album.ShowMediaDetailActivity.deleteMedia.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                int itemCount;
                ViewPager2.OnPageChangeCallback onPageChangeCallback;
                Intrinsics.checkNotNullParameter(it, "it");
                ActivityShowMediaDetailBinding activityShowMediaDetailBinding = ShowMediaDetailActivity.this.binding;
                ImageAdapter imageAdapter = null;
                if (activityShowMediaDetailBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityShowMediaDetailBinding = null;
                }
                int currentItem = activityShowMediaDetailBinding.viewPager2.getCurrentItem();
                AlbumListViewModel albumViewModel = ShowMediaDetailActivity.this.getAlbumViewModel();
                GlassAlbumEntity glassAlbumEntity = ShowMediaDetailActivity.this.glassAlbumEntity;
                Intrinsics.checkNotNull(glassAlbumEntity);
                albumViewModel.deleteMediaFile(glassAlbumEntity);
                ShowMediaDetailActivity.this.getAlbumViewModel().initDetailData().remove(currentItem);
                ImageAdapter imageAdapter2 = ShowMediaDetailActivity.this.adapter;
                if (imageAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    imageAdapter2 = null;
                }
                imageAdapter2.notifyItemRemoved(currentItem);
                ImageAdapter imageAdapter3 = ShowMediaDetailActivity.this.adapter;
                if (imageAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    imageAdapter3 = null;
                }
                if (imageAdapter3.getItemCount() != 0) {
                    ImageAdapter imageAdapter4 = ShowMediaDetailActivity.this.adapter;
                    if (imageAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        imageAdapter4 = null;
                    }
                    if (currentItem >= imageAdapter4.getItemCount()) {
                        ImageAdapter imageAdapter5 = ShowMediaDetailActivity.this.adapter;
                        if (imageAdapter5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                            imageAdapter5 = null;
                        }
                        itemCount = imageAdapter5.getItemCount() - 1;
                    } else {
                        itemCount = currentItem;
                    }
                    int i = currentItem + 1;
                    ImageAdapter imageAdapter6 = ShowMediaDetailActivity.this.adapter;
                    if (imageAdapter6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        imageAdapter6 = null;
                    }
                    if (i < imageAdapter6.getItemCount()) {
                        ActivityShowMediaDetailBinding activityShowMediaDetailBinding2 = ShowMediaDetailActivity.this.binding;
                        if (activityShowMediaDetailBinding2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityShowMediaDetailBinding2 = null;
                        }
                        activityShowMediaDetailBinding2.viewPager2.setCurrentItem(i, false);
                    } else if (currentItem > 0) {
                        ActivityShowMediaDetailBinding activityShowMediaDetailBinding3 = ShowMediaDetailActivity.this.binding;
                        if (activityShowMediaDetailBinding3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityShowMediaDetailBinding3 = null;
                        }
                        activityShowMediaDetailBinding3.viewPager2.setCurrentItem(currentItem - 1, false);
                    }
                    ActivityShowMediaDetailBinding activityShowMediaDetailBinding4 = ShowMediaDetailActivity.this.binding;
                    if (activityShowMediaDetailBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityShowMediaDetailBinding4 = null;
                    }
                    activityShowMediaDetailBinding4.viewPager2.setCurrentItem(itemCount, false);
                    ImageAdapter imageAdapter7 = ShowMediaDetailActivity.this.adapter;
                    if (imageAdapter7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    } else {
                        imageAdapter = imageAdapter7;
                    }
                    if (imageAdapter.getItemCount() == 1 && (onPageChangeCallback = ShowMediaDetailActivity.this.pageChangeCallback) != null) {
                        onPageChangeCallback.onPageSelected(0);
                    }
                } else {
                    ShowMediaDetailActivity.this.finish();
                }
                EventBus.getDefault().post(new EventType(9));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveFileToAppGalleryFolder(Context context, File sourceFile) throws IOException {
        FileOutputStream fileInputStream;
        Pair pairM614to;
        String appName = getAppName(context);
        String mimeType = getMimeType(sourceFile);
        if (StringsKt.startsWith$default(mimeType, "audio/", false, 2, (Object) null)) {
            savePcmAsWavAndAddToMediaStore(context, sourceFile);
            String string = getString(C0775R.string.album_glass_10_2, new Object[]{Environment.DIRECTORY_MUSIC + IOUtils.DIR_SEPARATOR_UNIX + appName});
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            if (StringsKt.startsWith$default(mimeType, "image/", false, 2, (Object) null)) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("_display_name", sourceFile.getName());
                contentValues.put("mime_type", mimeType);
                contentValues.put("relative_path", Environment.DIRECTORY_DCIM + IOUtils.DIR_SEPARATOR_UNIX + appName);
                pairM614to = TuplesKt.m614to(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            } else if (StringsKt.startsWith$default(mimeType, "video/", false, 2, (Object) null)) {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("_display_name", sourceFile.getName());
                contentValues2.put("mime_type", mimeType);
                contentValues2.put("relative_path", Environment.DIRECTORY_DCIM + IOUtils.DIR_SEPARATOR_UNIX + appName);
                pairM614to = TuplesKt.m614to(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues2);
            } else {
                Toast.makeText(context, "不支持的文件类型", 0).show();
                return;
            }
            Uri uri = (Uri) pairM614to.component1();
            ContentValues contentValues3 = (ContentValues) pairM614to.component2();
            ContentResolver contentResolver = context.getContentResolver();
            Uri uriInsert = contentResolver.insert(uri, contentValues3);
            if (uriInsert != null) {
                try {
                    fileInputStream = new FileInputStream(sourceFile);
                    try {
                        FileInputStream fileInputStream2 = fileInputStream;
                        OutputStream outputStreamOpenOutputStream = contentResolver.openOutputStream(uriInsert);
                        if (outputStreamOpenOutputStream != null) {
                            fileInputStream = outputStreamOpenOutputStream;
                            try {
                                OutputStream outputStream = fileInputStream;
                                Intrinsics.checkNotNull(outputStream);
                                copyFile(fileInputStream2, outputStream);
                                Unit unit = Unit.INSTANCE;
                                CloseableKt.closeFinally(fileInputStream, null);
                                Unit unit2 = Unit.INSTANCE;
                            } finally {
                                try {
                                    throw th;
                                } finally {
                                }
                            }
                        }
                        CloseableKt.closeFinally(fileInputStream, null);
                        XLog.m137i("文件保存成功");
                        String string2 = getString(C0775R.string.album_glass_10);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                        GlobalKt.showToast$default(string2, 0, 1, null);
                        return;
                    } finally {
                        try {
                            throw th;
                        } finally {
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    XLog.m137i("文件保存失败");
                    return;
                }
            }
            return;
        }
        if (ContextCompat.checkSelfPermission(context, Permission.WRITE_EXTERNAL_STORAGE) != 0) {
            XLog.m137i("缺少写入外部存储权限");
            return;
        }
        if (StringsKt.startsWith$default(mimeType, "image/", false, 2, (Object) null) || StringsKt.startsWith$default(mimeType, "video/", false, 2, (Object) null)) {
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            File file = new File(externalStoragePublicDirectory, appName);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, sourceFile.getName());
            try {
                fileInputStream = new FileInputStream(sourceFile);
                try {
                    FileInputStream fileInputStream3 = fileInputStream;
                    fileInputStream = new FileOutputStream(file2);
                    try {
                        copyFile(fileInputStream3, fileInputStream);
                        Unit unit3 = Unit.INSTANCE;
                        CloseableKt.closeFinally(fileInputStream, null);
                        Unit unit4 = Unit.INSTANCE;
                        CloseableKt.closeFinally(fileInputStream, null);
                        context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file2)));
                        String string3 = getString(C0775R.string.album_glass_10);
                        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                        GlobalKt.showToast$default(string3, 0, 1, null);
                    } finally {
                    }
                } finally {
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                XLog.m137i("文件保存失败");
            }
        } else {
            XLog.m137i("不支持的文件类型");
        }
    }

    private final void savePcmAsWavAndAddToMediaStore(Context context, File pcmFile) throws IOException {
        File fileConvertPcmToWav = PcmToMp3Kt.convertPcmToWav(pcmFile);
        String appName = getAppName(context);
        if (fileConvertPcmToWav != null) {
            if (Build.VERSION.SDK_INT >= 29) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("_display_name", fileConvertPcmToWav.getName());
                contentValues.put("mime_type", "audio/x-wav");
                contentValues.put("relative_path", Environment.DIRECTORY_MUSIC + IOUtils.DIR_SEPARATOR_UNIX + appName);
                ContentResolver contentResolver = context.getContentResolver();
                Uri uriInsert = contentResolver.insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, contentValues);
                if (uriInsert != null) {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(fileConvertPcmToWav);
                        OutputStream outputStreamOpenOutputStream = contentResolver.openOutputStream(uriInsert);
                        if (outputStreamOpenOutputStream != null) {
                            OutputStream outputStream = outputStreamOpenOutputStream;
                            try {
                                Long.valueOf(ByteStreamsKt.copyTo$default(fileInputStream, outputStream, 0, 2, null));
                                CloseableKt.closeFinally(outputStream, null);
                            } finally {
                            }
                        }
                        fileInputStream.close();
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                return;
            }
            MediaScannerConnection.scanFile(context, new String[]{fileConvertPcmToWav.getAbsolutePath()}, new String[]{"audio/x-wav"}, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.glasssutdio.wear.home.album.ShowMediaDetailActivity$$ExternalSyntheticLambda2
                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public final void onScanCompleted(String str, Uri uri) {
                    ShowMediaDetailActivity.savePcmAsWavAndAddToMediaStore$lambda$14$lambda$13(str, uri);
                }
            });
        }
    }

    private final void copyFile(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i <= 0) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    private final String getAppName(Context context) {
        String string = context.getString(C0775R.string.app_name);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BusEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if (messageEvent instanceof VideoEisSuccessfullyEvent) {
            return;
        }
        if (messageEvent instanceof RecordingToPcmSuccessfullyEvent) {
            try {
                if (this.adapter != null) {
                    GlassAlbumEntity glassAlbumEntity = this.glassAlbumEntity;
                    Intrinsics.checkNotNull(glassAlbumEntity);
                    glassAlbumEntity.setFilePath(((RecordingToPcmSuccessfullyEvent) messageEvent).getPath());
                    ImageAdapter imageAdapter = this.adapter;
                    if (imageAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        imageAdapter = null;
                    }
                    Integer num = this.index;
                    Intrinsics.checkNotNull(num);
                    imageAdapter.notifyItemChanged(num.intValue());
                    XLog.m137i("opus 转pcm");
                    return;
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if ((messageEvent instanceof EventType) && ((EventType) messageEvent).getType() == 13) {
            GlassAlbumEntity glassAlbumEntity2 = this.glassAlbumEntity;
            Intrinsics.checkNotNull(glassAlbumEntity2);
            initViews(2, glassAlbumEntity2.getFileName());
        }
    }

    @Override // com.glasssutdio.wear.home.adapter.ImageAdapter.PhotoViewScaleListener
    public void onPhotoViewScaling(boolean isScaling) {
        this.isPhotoViewScaling = isScaling;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void shareToSystem(String imagePath) {
        File file = new File(imagePath);
        if (file.exists()) {
            try {
                String mimeType = getMimeType(file);
                Uri uriForFile = FileProvider.getUriForFile(this, getPackageName() + ".provider", file);
                if (StringsKt.startsWith$default(mimeType, "audio", false, 2, (Object) null)) {
                    try {
                        File fileConvertPcmToWav = PcmToMp3Kt.convertPcmToWav(file);
                        String str = getPackageName() + ".provider";
                        Intrinsics.checkNotNull(fileConvertPcmToWav);
                        uriForFile = FileProvider.getUriForFile(this, str, fileConvertPcmToWav);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType(mimeType);
                intent.putExtra("android.intent.extra.STREAM", uriForFile);
                intent.addFlags(1);
                List<ResolveInfo> listQueryIntentActivities = getPackageManager().queryIntentActivities(intent, 65536);
                Intrinsics.checkNotNullExpressionValue(listQueryIntentActivities, "queryIntentActivities(...)");
                Iterator<ResolveInfo> it = listQueryIntentActivities.iterator();
                while (it.hasNext()) {
                    grantUriPermission(it.next().activityInfo.packageName, uriForFile, 1);
                }
                startActivity(Intent.createChooser(intent, "分享文件到"));
                return;
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
                XLog.m137i("获取文件 Uri 失败，无法分享文件");
                return;
            }
        }
        XLog.m137i("文件不存在，没有可分享的文件");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007c A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0088 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008b A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final String getMimeType(File file) {
        String name = file.getName();
        Intrinsics.checkNotNull(name);
        String strSubstringAfterLast = StringsKt.substringAfterLast(name, FilenameUtils.EXTENSION_SEPARATOR, "");
        Locale ROOT = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
        String lowerCase = strSubstringAfterLast.toLowerCase(ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        switch (lowerCase.hashCode()) {
            case 96980:
                if (lowerCase.equals("avi")) {
                    return "video/*";
                }
                return "*/*";
            case 97669:
                if (lowerCase.equals("bmp")) {
                    return "image/*";
                }
                break;
            case 102340:
                if (!lowerCase.equals("gif")) {
                }
                break;
            case 105441:
                if (!lowerCase.equals("jpg")) {
                }
                break;
            case 108184:
                if (!lowerCase.equals("mkv")) {
                }
                break;
            case 108273:
                if (!lowerCase.equals("mp4")) {
                }
                break;
            case 108308:
                if (!lowerCase.equals("mov")) {
                }
                break;
            case 110810:
                if (lowerCase.equals("pcm")) {
                    return "audio/*";
                }
                break;
            case 111145:
                if (!lowerCase.equals("png")) {
                }
                break;
            case 3268712:
                if (!lowerCase.equals("jpeg")) {
                }
                break;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        if (GlassLifeCycle.INSTANCE.isForeground() || getSystemMemoryInfo(this) > 2.0d) {
            return;
        }
        finish();
    }

    public final double getSystemMemoryInfo(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        ((ActivityManager) systemService).getMemoryInfo(new ActivityManager.MemoryInfo());
        return ((r0.availMem / 1024.0d) / 1024.0d) / 1024.0d;
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        try {
            EventBus.getDefault().unregister(this);
            ImageAdapter imageAdapter = this.adapter;
            if (imageAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                imageAdapter = null;
            }
            imageAdapter.releaseAllResources();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
