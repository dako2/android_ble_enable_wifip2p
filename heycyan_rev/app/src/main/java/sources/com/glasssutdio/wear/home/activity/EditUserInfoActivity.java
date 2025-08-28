package com.glasssutdio.wear.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Lifecycle;
import com.bumptech.glide.RequestBuilder;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.Constant;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.bean.CommonSelectModel;
import com.glasssutdio.wear.all.bean.Req.UpdateUserReq;
import com.glasssutdio.wear.all.dialog.CommonSelectDialog;
import com.glasssutdio.wear.all.dialog.DateSelectDialog;
import com.glasssutdio.wear.all.dialog.EditCenterDialog;
import com.glasssutdio.wear.all.dialog.SelectPhotoTypeDialog;
import com.glasssutdio.wear.all.pref.MMKVConfig;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.CommonUtils;
import com.glasssutdio.wear.all.utils.MoshiUtils;
import com.glasssutdio.wear.all.utils.PermissionUtilKt;
import com.glasssutdio.wear.all.utils.TypeToken;
import com.glasssutdio.wear.all.utils.image.CameraUtils;
import com.glasssutdio.wear.all.utils.image.ImageExtKt;
import com.glasssutdio.wear.all.utils.image.ImageUtils;
import com.glasssutdio.wear.all.utils.time.Interval;
import com.glasssutdio.wear.bus.EditUserInfoEvent;
import com.glasssutdio.wear.bus.RefreshUserEvent;
import com.glasssutdio.wear.databinding.ActivityEditUserInfoBinding;
import com.glasssutdio.wear.home.activity.EditUserInfoActivity.CameraPermissionCallback;
import com.glasssutdio.wear.home.bean.RequestFailModel;
import com.glasssutdio.wear.home.bean.UserModel;
import com.glasssutdio.wear.home.viewmodel.UserInfoEditActivityVM;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import com.google.android.material.timepicker.TimeModel;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.ClientCookie;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.squareup.moshi.JsonAdapter;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: EditUserInfoActivity.kt */
@Metadata(m606d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0002-.B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u0010\u0010\"\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\rH\u0002J\b\u0010#\u001a\u00020\u001fH\u0002J\b\u0010$\u001a\u00020\u001fH\u0002J\u0012\u0010%\u001a\u00020\u001f2\b\u0010&\u001a\u0004\u0018\u00010'H\u0014J\b\u0010(\u001a\u00020\u001fH\u0014J\b\u0010)\u001a\u00020\u001fH\u0002J\b\u0010*\u001a\u00020\u001fH\u0002J\u000e\u0010+\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u001a\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/EditUserInfoActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityEditUserInfoBinding;", "birthday", "", "cameraUtils", "Lcom/glasssutdio/wear/all/utils/image/CameraUtils;", "cropImageLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "day", "", "galleryLauncher", "gender", "interval", "Lcom/glasssutdio/wear/all/utils/time/Interval;", "isUpdated", "", "mViewModel", "Lcom/glasssutdio/wear/home/viewmodel/UserInfoEditActivityVM;", "getMViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/UserInfoEditActivityVM;", "mViewModel$delegate", "Lkotlin/Lazy;", "month", "nickname", "takePictureLauncher", "year", "cropImage", "", "uri", "Landroid/net/Uri;", "getSexStr", "initView", "observer", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "openGallery", "takePhoto", "uploadImg", ClientCookie.PATH_ATTR, "AlBumPermissionCallback", "CameraPermissionCallback", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class EditUserInfoActivity extends BaseSettingActivity {
    private ActivityEditUserInfoBinding binding;
    private String birthday;
    private CameraUtils cameraUtils;
    private ActivityResultLauncher<Intent> cropImageLauncher;
    private int day;
    private final ActivityResultLauncher<Intent> galleryLauncher;
    private int gender;
    private Interval interval;
    private boolean isUpdated;

    /* renamed from: mViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mViewModel;
    private int month;
    private String nickname;
    private final ActivityResultLauncher<Intent> takePictureLauncher;
    private int year;

    /* JADX WARN: Multi-variable type inference failed */
    public EditUserInfoActivity() {
        final EditUserInfoActivity editUserInfoActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewModel = LazyKt.lazy(new Function0<UserInfoEditActivityVM>() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.UserInfoEditActivityVM] */
            @Override // kotlin.jvm.functions.Function0
            public final UserInfoEditActivityVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(editUserInfoActivity, Reflection.getOrCreateKotlinClass(UserInfoEditActivityVM.class), qualifier, objArr);
            }
        });
        this.gender = 3;
        this.birthday = "";
        this.nickname = "";
        this.year = 1995;
        this.month = 1;
        this.day = 1;
        this.takePictureLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$$ExternalSyntheticLambda5
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                EditUserInfoActivity.takePictureLauncher$lambda$2(this.f$0, (ActivityResult) obj);
            }
        });
        this.galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$$ExternalSyntheticLambda6
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                EditUserInfoActivity.galleryLauncher$lambda$6(this.f$0, (ActivityResult) obj);
            }
        });
    }

    private final UserInfoEditActivityVM getMViewModel() {
        return (UserInfoEditActivityVM) this.mViewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void takePictureLauncher$lambda$2(EditUserInfoActivity this$0, ActivityResult result) {
        EditUserInfoActivity editUserInfoActivity;
        String strSaveUriToExternalPrivateStorage;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(result, "result");
        ActivityEditUserInfoBinding activityEditUserInfoBinding = null;
        if (result.getResultCode() == -1) {
            CameraUtils cameraUtils = this$0.cameraUtils;
            if (cameraUtils == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cameraUtils");
                cameraUtils = null;
            }
            Uri imageUri = cameraUtils.getImageUri();
            if (imageUri == null || (strSaveUriToExternalPrivateStorage = ImageUtils.INSTANCE.saveUriToExternalPrivateStorage((editUserInfoActivity = this$0), imageUri)) == null) {
                return;
            }
            this$0.uploadImg(strSaveUriToExternalPrivateStorage);
            Uri uriForFile = FileProvider.getUriForFile(editUserInfoActivity, GlobalKt.getPackageName(editUserInfoActivity) + ".provider", new File(strSaveUriToExternalPrivateStorage));
            ActivityEditUserInfoBinding activityEditUserInfoBinding2 = this$0.binding;
            if (activityEditUserInfoBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityEditUserInfoBinding = activityEditUserInfoBinding2;
            }
            ImageFilterView ivAvatar = activityEditUserInfoBinding.ivAvatar;
            Intrinsics.checkNotNullExpressionValue(ivAvatar, "ivAvatar");
            ImageExtKt.displayAvatar(ivAvatar, uriForFile, new Function1<RequestBuilder<?>, RequestBuilder<?>>() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$takePictureLauncher$1$1$1$1
                @Override // kotlin.jvm.functions.Function1
                public final RequestBuilder<?> invoke(RequestBuilder<?> displayAvatar) {
                    Intrinsics.checkNotNullParameter(displayAvatar, "$this$displayAvatar");
                    Cloneable cloneableOverride = displayAvatar.override(GlobalKt.getDp((Number) 50), GlobalKt.getDp((Number) 50));
                    Intrinsics.checkNotNullExpressionValue(cloneableOverride, "override(...)");
                    return (RequestBuilder) cloneableOverride;
                }
            });
            return;
        }
        String string = this$0.getString(C0775R.string.h_glass_319);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        GlobalKt.showToast$default(string, 0, 1, null);
    }

    public final void uploadImg(final String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        Interval intervalLife$default = Interval.life$default(new Interval(0L, 1000L, TimeUnit.MILLISECONDS, 1L, 0L, 16, null), this, (Lifecycle.Event) null, 2, (Object) null);
        this.interval = intervalLife$default;
        if (intervalLife$default != null) {
            intervalLife$default.subscribe(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$uploadImg$1$1
                public final void invoke(Interval subscribe, long j) {
                    Intrinsics.checkNotNullParameter(subscribe, "$this$subscribe");
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Interval interval, Long l) {
                    invoke(interval, l.longValue());
                    return Unit.INSTANCE;
                }
            }).finish(new Function2<Interval, Long, Unit>() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$uploadImg$1$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Interval interval, Long l) {
                    invoke(interval, l.longValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Interval finish, long j) {
                    Intrinsics.checkNotNullParameter(finish, "$this$finish");
                    this.this$0.dismissLoadingDialog();
                    MMKVConfig.INSTANCE.getInstance().putString(Constant.AVATAR_KEY + UserConfig.INSTANCE.getInstance().getUid(), path);
                    EventBus eventBus = EventBus.getDefault();
                    RefreshUserEvent refreshUserEvent = new RefreshUserEvent();
                    refreshUserEvent.setRefreshType(1);
                    eventBus.post(refreshUserEvent);
                }
            }).start();
        }
    }

    public final void cropImage(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 256);
        intent.putExtra("outputY", 256);
        intent.putExtra("return-data", true);
        ActivityResultLauncher<Intent> activityResultLauncher = this.cropImageLauncher;
        if (activityResultLauncher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cropImageLauncher");
            activityResultLauncher = null;
        }
        activityResultLauncher.launch(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void galleryLauncher$lambda$6(EditUserInfoActivity this$0, ActivityResult result) {
        EditUserInfoActivity editUserInfoActivity;
        String strSaveUriToExternalPrivateStorage;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.getResultCode() == -1) {
            Intent data = result.getData();
            ActivityEditUserInfoBinding activityEditUserInfoBinding = null;
            Uri data2 = data != null ? data.getData() : null;
            if (data2 == null || (strSaveUriToExternalPrivateStorage = ImageUtils.INSTANCE.saveUriToExternalPrivateStorage((editUserInfoActivity = this$0), data2)) == null) {
                return;
            }
            this$0.uploadImg(strSaveUriToExternalPrivateStorage);
            Uri uriForFile = FileProvider.getUriForFile(editUserInfoActivity, GlobalKt.getPackageName(editUserInfoActivity) + ".provider", new File(strSaveUriToExternalPrivateStorage));
            ActivityEditUserInfoBinding activityEditUserInfoBinding2 = this$0.binding;
            if (activityEditUserInfoBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityEditUserInfoBinding = activityEditUserInfoBinding2;
            }
            ImageFilterView ivAvatar = activityEditUserInfoBinding.ivAvatar;
            Intrinsics.checkNotNullExpressionValue(ivAvatar, "ivAvatar");
            ImageExtKt.displayAvatar(ivAvatar, uriForFile, new Function1<RequestBuilder<?>, RequestBuilder<?>>() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$galleryLauncher$1$1$1
                @Override // kotlin.jvm.functions.Function1
                public final RequestBuilder<?> invoke(RequestBuilder<?> displayAvatar) {
                    Intrinsics.checkNotNullParameter(displayAvatar, "$this$displayAvatar");
                    Cloneable cloneableOverride = displayAvatar.override(GlobalKt.getDp((Number) 50), GlobalKt.getDp((Number) 50));
                    Intrinsics.checkNotNullExpressionValue(cloneableOverride, "override(...)");
                    return (RequestBuilder) cloneableOverride;
                }
            });
        }
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityEditUserInfoBinding activityEditUserInfoBindingInflate = ActivityEditUserInfoBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityEditUserInfoBindingInflate, "inflate(...)");
        this.binding = activityEditUserInfoBindingInflate;
        if (activityEditUserInfoBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEditUserInfoBindingInflate = null;
        }
        setContentView(activityEditUserInfoBindingInflate.getRoot());
        initView();
        observer();
        this.cropImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$$ExternalSyntheticLambda7
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                EditUserInfoActivity.onCreate$lambda$7(this.f$0, (ActivityResult) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$7(EditUserInfoActivity this$0, ActivityResult result) {
        Bundle extras;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.getResultCode() == -1) {
            Intent data = result.getData();
            ActivityEditUserInfoBinding activityEditUserInfoBinding = null;
            Bitmap bitmap = (data == null || (extras = data.getExtras()) == null) ? null : (Bitmap) extras.getParcelable("data");
            ActivityEditUserInfoBinding activityEditUserInfoBinding2 = this$0.binding;
            if (activityEditUserInfoBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityEditUserInfoBinding = activityEditUserInfoBinding2;
            }
            activityEditUserInfoBinding.ivAvatar.setImageBitmap(bitmap);
        }
    }

    private final void observer() {
        UserInfoEditActivityVM mViewModel = getMViewModel();
        EditUserInfoActivity editUserInfoActivity = this;
        mViewModel.getFailLD().observe(editUserInfoActivity, new EditUserInfoActivity$sam$androidx_lifecycle_Observer$0(new Function1<RequestFailModel, Unit>() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$observer$1$1
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
            }
        }));
        mViewModel.getUserInfoFailLD().observe(editUserInfoActivity, new EditUserInfoActivity$sam$androidx_lifecycle_Observer$0(new Function1<RequestFailModel, Unit>() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$observer$1$2
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
                if (requestFailModel.getCode() == 10404) {
                    EditUserInfoActivity editUserInfoActivity2 = this.this$0;
                    String string = editUserInfoActivity2.getString(C0775R.string.app_name);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    editUserInfoActivity2.nickname = string;
                    ActivityEditUserInfoBinding activityEditUserInfoBinding = this.this$0.binding;
                    if (activityEditUserInfoBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityEditUserInfoBinding = null;
                    }
                    activityEditUserInfoBinding.tvNickname.setText(this.this$0.getString(C0775R.string.app_name));
                }
            }
        }));
        mViewModel.getUserInfoLD().observe(editUserInfoActivity, new EditUserInfoActivity$sam$androidx_lifecycle_Observer$0(new Function1<UserModel, Unit>() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$observer$1$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UserModel userModel) {
                invoke2(userModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UserModel userModel) {
                ActivityEditUserInfoBinding activityEditUserInfoBinding = this.this$0.binding;
                ActivityEditUserInfoBinding activityEditUserInfoBinding2 = null;
                if (activityEditUserInfoBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityEditUserInfoBinding = null;
                }
                activityEditUserInfoBinding.tvBirthday.setText(userModel.getBirthday());
                ActivityEditUserInfoBinding activityEditUserInfoBinding3 = this.this$0.binding;
                if (activityEditUserInfoBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityEditUserInfoBinding3 = null;
                }
                activityEditUserInfoBinding3.tvSex.setText(this.this$0.getSexStr(userModel.getGender()));
                ActivityEditUserInfoBinding activityEditUserInfoBinding4 = this.this$0.binding;
                if (activityEditUserInfoBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityEditUserInfoBinding2 = activityEditUserInfoBinding4;
                }
                activityEditUserInfoBinding2.tvNickname.setText(userModel.getNickname());
                this.this$0.gender = userModel.getGender();
                this.this$0.birthday = userModel.getBirthday();
                this.this$0.nickname = userModel.getNickname();
            }
        }));
        mViewModel.getUploadImgLD().observe(editUserInfoActivity, new EditUserInfoActivity$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$observer$1$4
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
                this.this$0.dismissLoadingDialog();
                EventBus eventBus = EventBus.getDefault();
                RefreshUserEvent refreshUserEvent = new RefreshUserEvent();
                refreshUserEvent.setRefreshType(1);
                eventBus.post(refreshUserEvent);
                XLog.m137i("上传成功！");
            }
        }));
        mViewModel.getFailLD().observe(editUserInfoActivity, new EditUserInfoActivity$sam$androidx_lifecycle_Observer$0(new Function1<RequestFailModel, Unit>() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$observer$1$5
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
                this.this$0.dismissLoadingDialog();
                String msg = requestFailModel.getMsg();
                if (msg != null) {
                    GlobalKt.showToast$default(msg, 0, 1, null);
                }
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getSexStr(int gender) {
        String string;
        if (gender == 1) {
            string = getString(C0775R.string.h_glass_282);
        } else if (gender == 2) {
            string = getString(C0775R.string.h_glass_283);
        } else {
            string = getString(C0775R.string.h_glass_283_no);
        }
        Intrinsics.checkNotNull(string);
        return string;
    }

    private final void initView() {
        Integer intOrNull;
        Integer intOrNull2;
        Integer intOrNull3;
        final ActivityEditUserInfoBinding activityEditUserInfoBinding = this.binding;
        ActivityEditUserInfoBinding activityEditUserInfoBinding2 = null;
        if (activityEditUserInfoBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEditUserInfoBinding = null;
        }
        activityEditUserInfoBinding.title.tvTitle.setText(getString(C0775R.string.h_glass_226));
        activityEditUserInfoBinding.title.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditUserInfoActivity.initView$lambda$15$lambda$9(this.f$0, view);
            }
        });
        try {
            String userJson = UserConfig.INSTANCE.getInstance().getUserJson();
            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<UserModel>() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$initView$lambda$15$$inlined$fromJson$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "adapter(...)");
            UserModel userModel = (UserModel) jsonAdapterAdapter.fromJson(userJson);
            if (Intrinsics.areEqual(userModel != null ? userModel.getUid() : null, String.valueOf(UserConfig.INSTANCE.getInstance().getUid()))) {
                ActivityEditUserInfoBinding activityEditUserInfoBinding3 = this.binding;
                if (activityEditUserInfoBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityEditUserInfoBinding3 = null;
                }
                activityEditUserInfoBinding3.tvBirthday.setText(userModel.getBirthday());
                ActivityEditUserInfoBinding activityEditUserInfoBinding4 = this.binding;
                if (activityEditUserInfoBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityEditUserInfoBinding4 = null;
                }
                activityEditUserInfoBinding4.tvSex.setText(getSexStr(userModel.getGender()));
                ActivityEditUserInfoBinding activityEditUserInfoBinding5 = this.binding;
                if (activityEditUserInfoBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityEditUserInfoBinding5 = null;
                }
                activityEditUserInfoBinding5.tvNickname.setText(userModel.getNickname());
                if (userModel.getBirthday().length() > 0) {
                    List listSplit$default = StringsKt.split$default((CharSequence) userModel.getBirthday(), new String[]{HelpFormatter.DEFAULT_OPT_PREFIX}, false, 0, 6, (Object) null);
                    String str = (String) CollectionsKt.getOrNull(listSplit$default, 0);
                    this.year = (str == null || (intOrNull3 = StringsKt.toIntOrNull(str)) == null) ? this.year : intOrNull3.intValue();
                    String str2 = (String) CollectionsKt.getOrNull(listSplit$default, 1);
                    this.month = (str2 == null || (intOrNull2 = StringsKt.toIntOrNull(str2)) == null) ? this.month : intOrNull2.intValue();
                    String str3 = (String) CollectionsKt.getOrNull(listSplit$default, 2);
                    this.day = (str3 == null || (intOrNull = StringsKt.toIntOrNull(str3)) == null) ? this.day : intOrNull.intValue();
                }
                this.gender = userModel.getGender();
                this.birthday = userModel.getBirthday();
                this.nickname = userModel.getNickname();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ActivityEditUserInfoBinding activityEditUserInfoBinding6 = this.binding;
        if (activityEditUserInfoBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityEditUserInfoBinding2 = activityEditUserInfoBinding6;
        }
        ImageFilterView ivAvatar = activityEditUserInfoBinding2.ivAvatar;
        Intrinsics.checkNotNullExpressionValue(ivAvatar, "ivAvatar");
        ImageExtKt.displayAvatar(ivAvatar, CommonUtils.INSTANCE.getAvatarUrl(), new Function1<RequestBuilder<?>, RequestBuilder<?>>() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$initView$1$3
            @Override // kotlin.jvm.functions.Function1
            public final RequestBuilder<?> invoke(RequestBuilder<?> displayAvatar) {
                Intrinsics.checkNotNullParameter(displayAvatar, "$this$displayAvatar");
                Cloneable cloneableOverride = displayAvatar.override(GlobalKt.getDp((Number) 50), GlobalKt.getDp((Number) 50));
                Intrinsics.checkNotNullExpressionValue(cloneableOverride, "override(...)");
                return (RequestBuilder) cloneableOverride;
            }
        });
        this.cameraUtils = new CameraUtils(this);
        activityEditUserInfoBinding.clsAvatar.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditUserInfoActivity.initView$lambda$15$lambda$11(this.f$0, view);
            }
        });
        activityEditUserInfoBinding.clsNickname.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditUserInfoActivity.initView$lambda$15$lambda$12(this.f$0, activityEditUserInfoBinding, view);
            }
        });
        activityEditUserInfoBinding.clsBirthday.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditUserInfoActivity.initView$lambda$15$lambda$13(this.f$0, activityEditUserInfoBinding, view);
            }
        });
        activityEditUserInfoBinding.clsSex.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditUserInfoActivity.initView$lambda$15$lambda$14(this.f$0, activityEditUserInfoBinding, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$15$lambda$9(EditUserInfoActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$15$lambda$11(final EditUserInfoActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SelectPhotoTypeDialog selectPhotoTypeDialogBuild = new SelectPhotoTypeDialog.Builder().build();
        selectPhotoTypeDialogBuild.setOnTypeSelectListener(new Function1<Boolean, Unit>() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$initView$1$4$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) throws NoSuchMethodException, SecurityException {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) throws NoSuchMethodException, SecurityException {
                if (!z) {
                    this.this$0.openGallery();
                } else {
                    PermissionUtilKt.requestCameraPermission(this.this$0, this.this$0.new CameraPermissionCallback());
                }
            }
        });
        selectPhotoTypeDialogBuild.show(this$0.getSupportFragmentManager(), "avatar");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$15$lambda$12(final EditUserInfoActivity this$0, final ActivityEditUserInfoBinding this_run, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        EditCenterDialog.Builder builder = new EditCenterDialog.Builder();
        String string = this$0.getString(C0775R.string.h_glass_286);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        final EditCenterDialog editCenterDialogBuild = builder.setTitle(string).setMaxLength(18).setContent(this$0.nickname).isLight(false).build();
        editCenterDialogBuild.setOnConfirmListener(new Function2<View, String, Unit>() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$initView$1$5$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(View view2, String str) {
                invoke2(view2, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View view2, String content) {
                Intrinsics.checkNotNullParameter(view2, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(content, "content");
                this_run.tvNickname.setText(content);
                this$0.nickname = content;
                editCenterDialogBuild.dismiss();
                this$0.isUpdated = true;
            }
        });
        editCenterDialogBuild.show(this$0.getSupportFragmentManager(), "nickname");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$15$lambda$13(final EditUserInfoActivity this$0, final ActivityEditUserInfoBinding this_run, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        DateSelectDialog.Builder builder = new DateSelectDialog.Builder();
        String string = this$0.getString(C0775R.string.h_glass_235);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        final DateSelectDialog dateSelectDialogBuild = builder.setTitle(string).setDefaultSelectDate(this$0.year, this$0.month, this$0.day).isLight(false).build();
        dateSelectDialogBuild.setOnDateSelectedListener(new Function3<Integer, Integer, Integer, Unit>() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$initView$1$6$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2, Integer num3) {
                invoke(num.intValue(), num2.intValue(), num3.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, int i2, int i3) {
                this.this$0.year = i;
                this.this$0.month = i2;
                this.this$0.day = i3;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String string2 = this.this$0.getString(C0775R.string.h_glass_date);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String strValueOf = String.valueOf(i);
                String str = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Arrays.copyOf(new Object[]{Integer.valueOf(i2)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                String str2 = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Arrays.copyOf(new Object[]{Integer.valueOf(i3)}, 1));
                Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
                String str3 = String.format(string2, Arrays.copyOf(new Object[]{strValueOf, str, str2}, 3));
                Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
                this.this$0.birthday = str3;
                this_run.tvBirthday.setText(str3);
                dateSelectDialogBuild.dismiss();
                this.this$0.isUpdated = true;
            }
        });
        dateSelectDialogBuild.show(this$0.getSupportFragmentManager(), "birth");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$15$lambda$14(final EditUserInfoActivity this$0, final ActivityEditUserInfoBinding this_run, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        String string = this$0.getString(C0775R.string.h_glass_282);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = this$0.getString(C0775R.string.h_glass_283);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        String string3 = this$0.getString(C0775R.string.h_glass_283_no);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        List<CommonSelectModel> listListOf = CollectionsKt.listOf((Object[]) new CommonSelectModel[]{new CommonSelectModel(string, false, 0, null, 14, null), new CommonSelectModel(string2, false, 0, null, 14, null), new CommonSelectModel(string3, false, 0, null, 14, null)});
        CommonSelectDialog.Builder builder = new CommonSelectDialog.Builder();
        int i = this$0.gender;
        CommonSelectDialog.Builder builderIsLight = builder.setDefaultChecked(i + (-1) < 0 ? 0 : i - 1).isLight(false);
        String string4 = this$0.getString(C0775R.string.h_glass_281);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        CommonSelectDialog commonSelectDialogBuild = builderIsLight.setTitle(string4).setData(listListOf).build();
        commonSelectDialogBuild.setOnItemCheckedListener(new Function2<Integer, CommonSelectModel, Unit>() { // from class: com.glasssutdio.wear.home.activity.EditUserInfoActivity$initView$1$7$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, CommonSelectModel commonSelectModel) {
                invoke(num.intValue(), commonSelectModel);
                return Unit.INSTANCE;
            }

            public final void invoke(int i2, CommonSelectModel model) {
                int i3;
                Intrinsics.checkNotNullParameter(model, "model");
                this.this$0.gender = i2 + 1;
                this_run.tvSex.setText(model.getName());
                this.this$0.isUpdated = true;
                if (CommonUtils.INSTANCE.getAvatarUrl().length() == 0) {
                    ActivityEditUserInfoBinding activityEditUserInfoBinding = this.this$0.binding;
                    if (activityEditUserInfoBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityEditUserInfoBinding = null;
                    }
                    ImageFilterView imageFilterView = activityEditUserInfoBinding.ivAvatar;
                    int i4 = this.this$0.gender;
                    if (i4 == 1) {
                        i3 = C0775R.mipmap.ic_avatar_default_man;
                    } else if (i4 == 2) {
                        i3 = C0775R.mipmap.ic_avatar_default_women;
                    } else {
                        i3 = C0775R.mipmap.ic_avatar_default_unknow;
                    }
                    imageFilterView.setImageResource(i3);
                }
            }
        });
        commonSelectDialogBuild.show(this$0.getSupportFragmentManager(), "cslDirection");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openGallery() {
        Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        this.galleryLauncher.launch(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void takePhoto() {
        CameraUtils cameraUtils = this.cameraUtils;
        CameraUtils cameraUtils2 = null;
        if (cameraUtils == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraUtils");
            cameraUtils = null;
        }
        if (cameraUtils.createImageUri() != null) {
            ActivityResultLauncher<Intent> activityResultLauncher = this.takePictureLauncher;
            CameraUtils cameraUtils3 = this.cameraUtils;
            if (cameraUtils3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cameraUtils");
            } else {
                cameraUtils2 = cameraUtils3;
            }
            Intent takePictureIntent = cameraUtils2.getTakePictureIntent();
            Intrinsics.checkNotNull(takePictureIntent);
            activityResultLauncher.launch(takePictureIntent);
            return;
        }
        String string = getString(C0775R.string.h_glass_319);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        GlobalKt.showToast$default(string, 0, 1, null);
    }

    /* compiled from: EditUserInfoActivity.kt */
    @Metadata(m606d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/EditUserInfoActivity$AlBumPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/glasssutdio/wear/home/activity/EditUserInfoActivity;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class AlBumPermissionCallback implements OnPermissionCallback {
        public AlBumPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            if (all) {
                EditUserInfoActivity.this.openGallery();
                return;
            }
            String string = EditUserInfoActivity.this.getString(C0775R.string.h_glass_101);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            super.onDenied(permissions, never);
            if (never) {
                String string = EditUserInfoActivity.this.getString(C0775R.string.h_glass_103);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                GlobalKt.showToast$default(string, 0, 1, null);
                XXPermissions.startPermissionActivity((Activity) EditUserInfoActivity.this, permissions);
            }
        }
    }

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.isUpdated) {
            EventBus.getDefault().post(new EditUserInfoEvent(new UpdateUserReq(this.birthday, this.nickname, Integer.valueOf(this.gender), String.valueOf(UserConfig.INSTANCE.getInstance().getUid()))));
        }
    }

    /* compiled from: EditUserInfoActivity.kt */
    @Metadata(m606d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, m607d2 = {"Lcom/glasssutdio/wear/home/activity/EditUserInfoActivity$CameraPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/glasssutdio/wear/home/activity/EditUserInfoActivity;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class CameraPermissionCallback implements OnPermissionCallback {
        public CameraPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            if (all) {
                EditUserInfoActivity.this.takePhoto();
                return;
            }
            String string = EditUserInfoActivity.this.getString(C0775R.string.h_glass_101);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            super.onDenied(permissions, never);
            if (never) {
                String string = EditUserInfoActivity.this.getString(C0775R.string.h_glass_103);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                GlobalKt.showToast$default(string, 0, 1, null);
                XXPermissions.startPermissionActivity((Activity) EditUserInfoActivity.this, permissions);
            }
        }
    }
}
