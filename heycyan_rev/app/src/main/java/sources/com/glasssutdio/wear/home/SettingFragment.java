package com.glasssutdio.wear.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.RequestBuilder;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.Constant;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.CommonUtils;
import com.glasssutdio.wear.all.utils.MoshiUtils;
import com.glasssutdio.wear.all.utils.MoshiUtilsKt;
import com.glasssutdio.wear.all.utils.TypeToken;
import com.glasssutdio.wear.all.utils.image.ImageExtKt;
import com.glasssutdio.wear.bus.BluetoothEvent;
import com.glasssutdio.wear.bus.BusEvent;
import com.glasssutdio.wear.bus.EditUserInfoEvent;
import com.glasssutdio.wear.bus.RefreshUserEvent;
import com.glasssutdio.wear.databinding.FragmentSettingBinding;
import com.glasssutdio.wear.home.activity.AboutActivity;
import com.glasssutdio.wear.home.activity.AccountSafetyActivity;
import com.glasssutdio.wear.home.activity.EditUserInfoActivity;
import com.glasssutdio.wear.home.activity.FeedbackPrepareActivity;
import com.glasssutdio.wear.home.activity.LoginActivity;
import com.glasssutdio.wear.home.activity.UserGuideActivity;
import com.glasssutdio.wear.home.activity.UserSplashGuideActivity;
import com.glasssutdio.wear.home.bean.RequestFailModel;
import com.glasssutdio.wear.home.bean.UserModel;
import com.glasssutdio.wear.home.viewmodel.SettingFragmentVM;
import com.glasssutdio.wear.setting.GuideBgSettingActivity;
import com.glasssutdio.wear.setting.WebActivity;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.squareup.moshi.JsonAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: SettingFragment.kt */
@Metadata(m606d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0002J$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\fH\u0016J\u0010\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J\b\u0010\u001a\u001a\u00020\fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u001c"}, m607d2 = {"Lcom/glasssutdio/wear/home/SettingFragment;", "Lcom/glasssutdio/wear/home/BaseFragment;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/FragmentSettingBinding;", "mViewModel", "Lcom/glasssutdio/wear/home/viewmodel/SettingFragmentVM;", "getMViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/SettingFragmentVM;", "mViewModel$delegate", "Lkotlin/Lazy;", "loadDataData", "", "observer", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/glasssutdio/wear/bus/BusEvent;", "onResume", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class SettingFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FragmentSettingBinding binding;

    /* renamed from: mViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mViewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public SettingFragment() {
        final SettingFragment settingFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewModel = LazyKt.lazy(new Function0<SettingFragmentVM>() { // from class: com.glasssutdio.wear.home.SettingFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.SettingFragmentVM] */
            @Override // kotlin.jvm.functions.Function0
            public final SettingFragmentVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(settingFragment, Reflection.getOrCreateKotlinClass(SettingFragmentVM.class), qualifier, objArr);
            }
        });
    }

    private final SettingFragmentVM getMViewModel() {
        return (SettingFragmentVM) this.mViewModel.getValue();
    }

    @Override // com.glasssutdio.wear.home.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentSettingBinding fragmentSettingBindingInflate = FragmentSettingBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentSettingBindingInflate, "inflate(...)");
        this.binding = fragmentSettingBindingInflate;
        if (fragmentSettingBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSettingBindingInflate = null;
        }
        ConstraintLayout root = fragmentSettingBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // com.glasssutdio.wear.home.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentSettingBinding fragmentSettingBinding = this.binding;
        FragmentSettingBinding fragmentSettingBinding2 = null;
        if (fragmentSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSettingBinding = null;
        }
        ViewKt.goneOrVisible(fragmentSettingBinding.clsGuide, BleOperateManager.getInstance().isConnected());
        if (GlobalKt.isLogin(this)) {
            FragmentSettingBinding fragmentSettingBinding3 = this.binding;
            if (fragmentSettingBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentSettingBinding3 = null;
            }
            fragmentSettingBinding3.tvEdit.setText(getString(C0775R.string.h_glass_226));
            FragmentSettingBinding fragmentSettingBinding4 = this.binding;
            if (fragmentSettingBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentSettingBinding2 = fragmentSettingBinding4;
            }
            ViewKt.visible(fragmentSettingBinding2.clsAccount);
            return;
        }
        FragmentSettingBinding fragmentSettingBinding5 = this.binding;
        if (fragmentSettingBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSettingBinding5 = null;
        }
        ViewKt.gone(fragmentSettingBinding5.clsAccount);
        FragmentSettingBinding fragmentSettingBinding6 = this.binding;
        if (fragmentSettingBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSettingBinding6 = null;
        }
        fragmentSettingBinding6.tvEdit.setText(getString(C0775R.string.h_glass_320));
        FragmentSettingBinding fragmentSettingBinding7 = this.binding;
        if (fragmentSettingBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentSettingBinding2 = fragmentSettingBinding7;
        }
        fragmentSettingBinding2.ivAvatar.setImageResource(C0775R.mipmap.ic_avatar_default_unknow);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(BusEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        FragmentSettingBinding fragmentSettingBinding = null;
        if (messageEvent instanceof RefreshUserEvent) {
            int refreshType = ((RefreshUserEvent) messageEvent).getRefreshType();
            if (refreshType == 1) {
                FragmentSettingBinding fragmentSettingBinding2 = this.binding;
                if (fragmentSettingBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentSettingBinding = fragmentSettingBinding2;
                }
                ImageFilterView ivAvatar = fragmentSettingBinding.ivAvatar;
                Intrinsics.checkNotNullExpressionValue(ivAvatar, "ivAvatar");
                ImageExtKt.displayAvatar(ivAvatar, CommonUtils.INSTANCE.getAvatarUrl(), new Function1<RequestBuilder<?>, RequestBuilder<?>>() { // from class: com.glasssutdio.wear.home.SettingFragment.onMessageEvent.1
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
            if (refreshType == 2) {
                FragmentSettingBinding fragmentSettingBinding3 = this.binding;
                if (fragmentSettingBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentSettingBinding3 = null;
                }
                fragmentSettingBinding3.tvUserName.setText(getString(C0775R.string.app_name));
                FragmentSettingBinding fragmentSettingBinding4 = this.binding;
                if (fragmentSettingBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentSettingBinding = fragmentSettingBinding4;
                }
                fragmentSettingBinding.ivAvatar.setImageResource(C0775R.mipmap.ic_avatar_default);
                return;
            }
            getMViewModel().getUserInfo(String.valueOf(UserConfig.INSTANCE.getInstance().getUid()));
            return;
        }
        if (messageEvent instanceof BluetoothEvent) {
            FragmentSettingBinding fragmentSettingBinding5 = this.binding;
            if (fragmentSettingBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentSettingBinding = fragmentSettingBinding5;
            }
            ViewKt.goneOrVisible(fragmentSettingBinding.clsGuide, ((BluetoothEvent) messageEvent).getConnect());
            return;
        }
        if (messageEvent instanceof EditUserInfoEvent) {
            FragmentSettingBinding fragmentSettingBinding6 = this.binding;
            if (fragmentSettingBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentSettingBinding6 = null;
            }
            EditUserInfoEvent editUserInfoEvent = (EditUserInfoEvent) messageEvent;
            fragmentSettingBinding6.tvUserName.setText(editUserInfoEvent.getReq().getNickname());
            try {
                String userJson = UserConfig.INSTANCE.getInstance().getUserJson();
                JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<UserModel>() { // from class: com.glasssutdio.wear.home.SettingFragment$onMessageEvent$$inlined$fromJson$1
                }.getType());
                Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "adapter(...)");
                UserModel userModel = (UserModel) jsonAdapterAdapter.fromJson(userJson);
                if (userModel != null) {
                    Integer sex = ((EditUserInfoEvent) messageEvent).getReq().getSex();
                    userModel.setGender(sex != null ? sex.intValue() : 0);
                }
                String str = "";
                if (userModel != null) {
                    String nickname = ((EditUserInfoEvent) messageEvent).getReq().getNickname();
                    if (nickname == null) {
                        nickname = "";
                    }
                    userModel.setNickname(nickname);
                }
                if (userModel != null) {
                    String birthday = ((EditUserInfoEvent) messageEvent).getReq().getBirthday();
                    if (birthday != null) {
                        str = birthday;
                    }
                    userModel.setBirthday(str);
                }
                if (userModel != null) {
                    UserConfig.INSTANCE.getInstance().setUserJson(MoshiUtilsKt.toJson(userModel));
                }
                FragmentSettingBinding fragmentSettingBinding7 = this.binding;
                if (fragmentSettingBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentSettingBinding = fragmentSettingBinding7;
                }
                ImageFilterView ivAvatar2 = fragmentSettingBinding.ivAvatar;
                Intrinsics.checkNotNullExpressionValue(ivAvatar2, "ivAvatar");
                ImageExtKt.displayAvatar(ivAvatar2, CommonUtils.INSTANCE.getAvatarUrl(), new Function1<RequestBuilder<?>, RequestBuilder<?>>() { // from class: com.glasssutdio.wear.home.SettingFragment.onMessageEvent.2
                    @Override // kotlin.jvm.functions.Function1
                    public final RequestBuilder<?> invoke(RequestBuilder<?> displayAvatar) {
                        Intrinsics.checkNotNullParameter(displayAvatar, "$this$displayAvatar");
                        Cloneable cloneableOverride = displayAvatar.override(GlobalKt.getDp((Number) 50), GlobalKt.getDp((Number) 50));
                        Intrinsics.checkNotNullExpressionValue(cloneableOverride, "override(...)");
                        return (RequestBuilder) cloneableOverride;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            getMViewModel().updateUserInfo(editUserInfoEvent.getReq());
        }
    }

    @Override // com.glasssutdio.wear.home.BaseFragment
    public void loadDataData() throws SecurityException {
        super.loadDataData();
        observer();
        EventBus.getDefault().register(this);
        FragmentSettingBinding fragmentSettingBinding = this.binding;
        if (fragmentSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSettingBinding = null;
        }
        TextView tvTitle = fragmentSettingBinding.tvTitle;
        Intrinsics.checkNotNullExpressionValue(tvTitle, "tvTitle");
        ViewKt.statusMargin$default(tvTitle, false, 0, 3, null);
        ViewKt.goneOrVisible(fragmentSettingBinding.ivNew, UserConfig.INSTANCE.getInstance().getHasNewVersion());
        SettingFragment settingFragment = this;
        if (GlobalKt.isLogin(settingFragment)) {
            if (GlobalKt.isNetworkAvailable(settingFragment)) {
                getMViewModel().getUserInfo(String.valueOf(UserConfig.INSTANCE.getInstance().getUid()));
            } else {
                try {
                    String userJson = UserConfig.INSTANCE.getInstance().getUserJson();
                    JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<UserModel>() { // from class: com.glasssutdio.wear.home.SettingFragment$loadDataData$lambda$8$$inlined$fromJson$1
                    }.getType());
                    Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "adapter(...)");
                    UserModel userModel = (UserModel) jsonAdapterAdapter.fromJson(userJson);
                    if (Intrinsics.areEqual(userModel != null ? userModel.getUid() : null, String.valueOf(UserConfig.INSTANCE.getInstance().getUid()))) {
                        getMViewModel().getUserInfoLD().setValue(userModel);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            getMViewModel().getUserInfo(String.valueOf(UserConfig.INSTANCE.getInstance().getUid()));
        }
        fragmentSettingBinding.tvEdit.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.SettingFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingFragment.loadDataData$lambda$8$lambda$0(this.f$0, view);
            }
        });
        fragmentSettingBinding.clsAccount.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.SettingFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingFragment.loadDataData$lambda$8$lambda$1(this.f$0, view);
            }
        });
        fragmentSettingBinding.clsGuide.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.SettingFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingFragment.loadDataData$lambda$8$lambda$2(this.f$0, view);
            }
        });
        fragmentSettingBinding.clsPermission.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.SettingFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingFragment.loadDataData$lambda$8$lambda$3(this.f$0, view);
            }
        });
        fragmentSettingBinding.clsFeedback.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.SettingFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingFragment.loadDataData$lambda$8$lambda$4(this.f$0, view);
            }
        });
        fragmentSettingBinding.clsAbout.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.SettingFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingFragment.loadDataData$lambda$8$lambda$5(this.f$0, view);
            }
        });
        fragmentSettingBinding.clsFaq.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.SettingFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingFragment.loadDataData$lambda$8$lambda$7(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadDataData$lambda$8$lambda$0(SettingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingFragment settingFragment = this$0;
        if (GlobalKt.isLogin(settingFragment)) {
            FragmentActivity activity = settingFragment.getActivity();
            if (activity != null) {
                ArrayList<Pair> arrayList = new ArrayList();
                Intrinsics.checkNotNull(activity);
                Intent intent = new Intent(activity, (Class<?>) EditUserInfoActivity.class);
                for (Pair pair : arrayList) {
                    if (pair != null) {
                        String str = (String) pair.getFirst();
                        Object second = pair.getSecond();
                        if (second instanceof Integer) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(...)");
                        } else if (second instanceof Byte) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(...)");
                        } else if (second instanceof Character) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(...)");
                        } else if (second instanceof Short) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(...)");
                        } else if (second instanceof Boolean) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(...)");
                        } else if (second instanceof Long) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(...)");
                        } else if (second instanceof Float) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(...)");
                        } else if (second instanceof Double) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(...)");
                        } else if (second instanceof String) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(...)");
                        } else if (second instanceof CharSequence) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(...)");
                        } else if (second instanceof Parcelable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                        } else if (second instanceof Object[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                        } else if (second instanceof ArrayList) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                        } else if (second instanceof Serializable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                        } else if (second instanceof boolean[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(...)");
                        } else if (second instanceof byte[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(...)");
                        } else if (second instanceof short[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(...)");
                        } else if (second instanceof char[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(...)");
                        } else if (second instanceof int[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(...)");
                        } else if (second instanceof long[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(...)");
                        } else if (second instanceof float[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(...)");
                        } else if (second instanceof double[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(...)");
                        } else if (second instanceof Bundle) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(...)");
                        } else if (second instanceof Intent) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                        } else {
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                }
                settingFragment.startActivity(intent);
                return;
            }
            return;
        }
        FragmentActivity activity2 = settingFragment.getActivity();
        if (activity2 != null) {
            ArrayList<Pair> arrayList2 = new ArrayList();
            Intrinsics.checkNotNull(activity2);
            Intent intent2 = new Intent(activity2, (Class<?>) LoginActivity.class);
            for (Pair pair2 : arrayList2) {
                if (pair2 != null) {
                    String str2 = (String) pair2.getFirst();
                    Object second2 = pair2.getSecond();
                    if (second2 instanceof Integer) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).intValue()), "putExtra(...)");
                    } else if (second2 instanceof Byte) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).byteValue()), "putExtra(...)");
                    } else if (second2 instanceof Character) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Character) second2).charValue()), "putExtra(...)");
                    } else if (second2 instanceof Short) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).shortValue()), "putExtra(...)");
                    } else if (second2 instanceof Boolean) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Boolean) second2).booleanValue()), "putExtra(...)");
                    } else if (second2 instanceof Long) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).longValue()), "putExtra(...)");
                    } else if (second2 instanceof Float) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).floatValue()), "putExtra(...)");
                    } else if (second2 instanceof Double) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).doubleValue()), "putExtra(...)");
                    } else if (second2 instanceof String) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (String) second2), "putExtra(...)");
                    } else if (second2 instanceof CharSequence) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (CharSequence) second2), "putExtra(...)");
                    } else if (second2 instanceof Parcelable) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(...)");
                    } else if (second2 instanceof Object[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                    } else if (second2 instanceof ArrayList) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                    } else if (second2 instanceof Serializable) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                    } else if (second2 instanceof boolean[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (boolean[]) second2), "putExtra(...)");
                    } else if (second2 instanceof byte[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (byte[]) second2), "putExtra(...)");
                    } else if (second2 instanceof short[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (short[]) second2), "putExtra(...)");
                    } else if (second2 instanceof char[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (char[]) second2), "putExtra(...)");
                    } else if (second2 instanceof int[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (int[]) second2), "putExtra(...)");
                    } else if (second2 instanceof long[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (long[]) second2), "putExtra(...)");
                    } else if (second2 instanceof float[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (float[]) second2), "putExtra(...)");
                    } else if (second2 instanceof double[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (double[]) second2), "putExtra(...)");
                    } else if (second2 instanceof Bundle) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Bundle) second2), "putExtra(...)");
                    } else if (second2 instanceof Intent) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(...)");
                    } else {
                        Unit unit2 = Unit.INSTANCE;
                    }
                }
            }
            settingFragment.startActivity(intent2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadDataData$lambda$8$lambda$1(SettingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingFragment settingFragment = this$0;
        FragmentActivity activity = settingFragment.getActivity();
        if (activity != null) {
            ArrayList<Pair> arrayList = new ArrayList();
            Intrinsics.checkNotNull(activity);
            Intent intent = new Intent(activity, (Class<?>) AccountSafetyActivity.class);
            for (Pair pair : arrayList) {
                if (pair != null) {
                    String str = (String) pair.getFirst();
                    Object second = pair.getSecond();
                    if (second instanceof Integer) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(...)");
                    } else if (second instanceof Byte) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(...)");
                    } else if (second instanceof Character) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(...)");
                    } else if (second instanceof Short) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(...)");
                    } else if (second instanceof Boolean) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(...)");
                    } else if (second instanceof Long) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(...)");
                    } else if (second instanceof Float) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(...)");
                    } else if (second instanceof Double) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(...)");
                    } else if (second instanceof String) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(...)");
                    } else if (second instanceof CharSequence) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(...)");
                    } else if (second instanceof Parcelable) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                    } else if (second instanceof Object[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof ArrayList) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof Serializable) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof boolean[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(...)");
                    } else if (second instanceof byte[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(...)");
                    } else if (second instanceof short[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(...)");
                    } else if (second instanceof char[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(...)");
                    } else if (second instanceof int[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(...)");
                    } else if (second instanceof long[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(...)");
                    } else if (second instanceof float[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(...)");
                    } else if (second instanceof double[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(...)");
                    } else if (second instanceof Bundle) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(...)");
                    } else if (second instanceof Intent) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                    } else {
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            settingFragment.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadDataData$lambda$8$lambda$2(SettingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!UserConfig.INSTANCE.getInstance().isShowedDeviceGuidedNew()) {
            UserConfig.INSTANCE.getInstance().setShowedDeviceGuidedNew(true);
            SettingFragment settingFragment = this$0;
            FragmentActivity activity = settingFragment.getActivity();
            if (activity != null) {
                ArrayList<Pair> arrayList = new ArrayList();
                Intrinsics.checkNotNull(activity);
                Intent intent = new Intent(activity, (Class<?>) UserSplashGuideActivity.class);
                for (Pair pair : arrayList) {
                    if (pair != null) {
                        String str = (String) pair.getFirst();
                        Object second = pair.getSecond();
                        if (second instanceof Integer) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(...)");
                        } else if (second instanceof Byte) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(...)");
                        } else if (second instanceof Character) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(...)");
                        } else if (second instanceof Short) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(...)");
                        } else if (second instanceof Boolean) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(...)");
                        } else if (second instanceof Long) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(...)");
                        } else if (second instanceof Float) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(...)");
                        } else if (second instanceof Double) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(...)");
                        } else if (second instanceof String) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(...)");
                        } else if (second instanceof CharSequence) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(...)");
                        } else if (second instanceof Parcelable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                        } else if (second instanceof Object[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                        } else if (second instanceof ArrayList) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                        } else if (second instanceof Serializable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                        } else if (second instanceof boolean[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(...)");
                        } else if (second instanceof byte[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(...)");
                        } else if (second instanceof short[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(...)");
                        } else if (second instanceof char[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(...)");
                        } else if (second instanceof int[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(...)");
                        } else if (second instanceof long[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(...)");
                        } else if (second instanceof float[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(...)");
                        } else if (second instanceof double[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(...)");
                        } else if (second instanceof Bundle) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(...)");
                        } else if (second instanceof Intent) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                        } else {
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                }
                settingFragment.startActivity(intent);
                return;
            }
            return;
        }
        SettingFragment settingFragment2 = this$0;
        Pair pair2 = new Pair(Constant.PAGE_GUIDE_KEY, 3);
        FragmentActivity activity2 = settingFragment2.getActivity();
        if (activity2 != null) {
            ArrayList<Pair> arrayList2 = new ArrayList();
            arrayList2.add(pair2);
            Intrinsics.checkNotNull(activity2);
            Intent intent2 = new Intent(activity2, (Class<?>) UserGuideActivity.class);
            for (Pair pair3 : arrayList2) {
                if (pair3 != null) {
                    String str2 = (String) pair3.getFirst();
                    Object second2 = pair3.getSecond();
                    if (second2 instanceof Integer) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).intValue()), "putExtra(...)");
                    } else if (second2 instanceof Byte) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).byteValue()), "putExtra(...)");
                    } else if (second2 instanceof Character) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Character) second2).charValue()), "putExtra(...)");
                    } else if (second2 instanceof Short) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).shortValue()), "putExtra(...)");
                    } else if (second2 instanceof Boolean) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Boolean) second2).booleanValue()), "putExtra(...)");
                    } else if (second2 instanceof Long) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).longValue()), "putExtra(...)");
                    } else if (second2 instanceof Float) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).floatValue()), "putExtra(...)");
                    } else if (second2 instanceof Double) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).doubleValue()), "putExtra(...)");
                    } else if (second2 instanceof String) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (String) second2), "putExtra(...)");
                    } else if (second2 instanceof CharSequence) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (CharSequence) second2), "putExtra(...)");
                    } else if (second2 instanceof Parcelable) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(...)");
                    } else if (second2 instanceof Object[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                    } else if (second2 instanceof ArrayList) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                    } else if (second2 instanceof Serializable) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                    } else if (second2 instanceof boolean[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (boolean[]) second2), "putExtra(...)");
                    } else if (second2 instanceof byte[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (byte[]) second2), "putExtra(...)");
                    } else if (second2 instanceof short[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (short[]) second2), "putExtra(...)");
                    } else if (second2 instanceof char[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (char[]) second2), "putExtra(...)");
                    } else if (second2 instanceof int[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (int[]) second2), "putExtra(...)");
                    } else if (second2 instanceof long[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (long[]) second2), "putExtra(...)");
                    } else if (second2 instanceof float[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (float[]) second2), "putExtra(...)");
                    } else if (second2 instanceof double[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (double[]) second2), "putExtra(...)");
                    } else if (second2 instanceof Bundle) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Bundle) second2), "putExtra(...)");
                    } else if (second2 instanceof Intent) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(...)");
                    } else {
                        Unit unit2 = Unit.INSTANCE;
                    }
                }
            }
            settingFragment2.startActivity(intent2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadDataData$lambda$8$lambda$3(SettingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingFragment settingFragment = this$0;
        FragmentActivity activity = settingFragment.getActivity();
        if (activity != null) {
            ArrayList<Pair> arrayList = new ArrayList();
            Intrinsics.checkNotNull(activity);
            Intent intent = new Intent(activity, (Class<?>) GuideBgSettingActivity.class);
            for (Pair pair : arrayList) {
                if (pair != null) {
                    String str = (String) pair.getFirst();
                    Object second = pair.getSecond();
                    if (second instanceof Integer) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(...)");
                    } else if (second instanceof Byte) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(...)");
                    } else if (second instanceof Character) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(...)");
                    } else if (second instanceof Short) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(...)");
                    } else if (second instanceof Boolean) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(...)");
                    } else if (second instanceof Long) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(...)");
                    } else if (second instanceof Float) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(...)");
                    } else if (second instanceof Double) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(...)");
                    } else if (second instanceof String) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(...)");
                    } else if (second instanceof CharSequence) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(...)");
                    } else if (second instanceof Parcelable) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                    } else if (second instanceof Object[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof ArrayList) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof Serializable) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof boolean[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(...)");
                    } else if (second instanceof byte[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(...)");
                    } else if (second instanceof short[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(...)");
                    } else if (second instanceof char[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(...)");
                    } else if (second instanceof int[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(...)");
                    } else if (second instanceof long[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(...)");
                    } else if (second instanceof float[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(...)");
                    } else if (second instanceof double[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(...)");
                    } else if (second instanceof Bundle) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(...)");
                    } else if (second instanceof Intent) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                    } else {
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            settingFragment.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadDataData$lambda$8$lambda$4(SettingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingFragment settingFragment = this$0;
        if (GlobalKt.isLogin(settingFragment)) {
            FragmentActivity activity = settingFragment.getActivity();
            if (activity != null) {
                ArrayList<Pair> arrayList = new ArrayList();
                Intrinsics.checkNotNull(activity);
                Intent intent = new Intent(activity, (Class<?>) FeedbackPrepareActivity.class);
                for (Pair pair : arrayList) {
                    if (pair != null) {
                        String str = (String) pair.getFirst();
                        Object second = pair.getSecond();
                        if (second instanceof Integer) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(...)");
                        } else if (second instanceof Byte) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(...)");
                        } else if (second instanceof Character) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(...)");
                        } else if (second instanceof Short) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(...)");
                        } else if (second instanceof Boolean) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(...)");
                        } else if (second instanceof Long) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(...)");
                        } else if (second instanceof Float) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(...)");
                        } else if (second instanceof Double) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(...)");
                        } else if (second instanceof String) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(...)");
                        } else if (second instanceof CharSequence) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(...)");
                        } else if (second instanceof Parcelable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                        } else if (second instanceof Object[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                        } else if (second instanceof ArrayList) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                        } else if (second instanceof Serializable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                        } else if (second instanceof boolean[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(...)");
                        } else if (second instanceof byte[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(...)");
                        } else if (second instanceof short[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(...)");
                        } else if (second instanceof char[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(...)");
                        } else if (second instanceof int[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(...)");
                        } else if (second instanceof long[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(...)");
                        } else if (second instanceof float[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(...)");
                        } else if (second instanceof double[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(...)");
                        } else if (second instanceof Bundle) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(...)");
                        } else if (second instanceof Intent) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                        } else {
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                }
                settingFragment.startActivity(intent);
                return;
            }
            return;
        }
        FragmentActivity activity2 = settingFragment.getActivity();
        if (activity2 != null) {
            ArrayList<Pair> arrayList2 = new ArrayList();
            Intrinsics.checkNotNull(activity2);
            Intent intent2 = new Intent(activity2, (Class<?>) LoginActivity.class);
            for (Pair pair2 : arrayList2) {
                if (pair2 != null) {
                    String str2 = (String) pair2.getFirst();
                    Object second2 = pair2.getSecond();
                    if (second2 instanceof Integer) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).intValue()), "putExtra(...)");
                    } else if (second2 instanceof Byte) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).byteValue()), "putExtra(...)");
                    } else if (second2 instanceof Character) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Character) second2).charValue()), "putExtra(...)");
                    } else if (second2 instanceof Short) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).shortValue()), "putExtra(...)");
                    } else if (second2 instanceof Boolean) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Boolean) second2).booleanValue()), "putExtra(...)");
                    } else if (second2 instanceof Long) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).longValue()), "putExtra(...)");
                    } else if (second2 instanceof Float) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).floatValue()), "putExtra(...)");
                    } else if (second2 instanceof Double) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).doubleValue()), "putExtra(...)");
                    } else if (second2 instanceof String) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (String) second2), "putExtra(...)");
                    } else if (second2 instanceof CharSequence) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (CharSequence) second2), "putExtra(...)");
                    } else if (second2 instanceof Parcelable) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(...)");
                    } else if (second2 instanceof Object[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                    } else if (second2 instanceof ArrayList) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                    } else if (second2 instanceof Serializable) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(...)");
                    } else if (second2 instanceof boolean[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (boolean[]) second2), "putExtra(...)");
                    } else if (second2 instanceof byte[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (byte[]) second2), "putExtra(...)");
                    } else if (second2 instanceof short[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (short[]) second2), "putExtra(...)");
                    } else if (second2 instanceof char[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (char[]) second2), "putExtra(...)");
                    } else if (second2 instanceof int[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (int[]) second2), "putExtra(...)");
                    } else if (second2 instanceof long[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (long[]) second2), "putExtra(...)");
                    } else if (second2 instanceof float[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (float[]) second2), "putExtra(...)");
                    } else if (second2 instanceof double[]) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (double[]) second2), "putExtra(...)");
                    } else if (second2 instanceof Bundle) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Bundle) second2), "putExtra(...)");
                    } else if (second2 instanceof Intent) {
                        Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(...)");
                    } else {
                        Unit unit2 = Unit.INSTANCE;
                    }
                }
            }
            settingFragment.startActivity(intent2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadDataData$lambda$8$lambda$5(SettingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingFragment settingFragment = this$0;
        FragmentActivity activity = settingFragment.getActivity();
        if (activity != null) {
            ArrayList<Pair> arrayList = new ArrayList();
            Intrinsics.checkNotNull(activity);
            Intent intent = new Intent(activity, (Class<?>) AboutActivity.class);
            for (Pair pair : arrayList) {
                if (pair != null) {
                    String str = (String) pair.getFirst();
                    Object second = pair.getSecond();
                    if (second instanceof Integer) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(...)");
                    } else if (second instanceof Byte) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(...)");
                    } else if (second instanceof Character) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(...)");
                    } else if (second instanceof Short) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(...)");
                    } else if (second instanceof Boolean) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(...)");
                    } else if (second instanceof Long) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(...)");
                    } else if (second instanceof Float) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(...)");
                    } else if (second instanceof Double) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(...)");
                    } else if (second instanceof String) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(...)");
                    } else if (second instanceof CharSequence) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(...)");
                    } else if (second instanceof Parcelable) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                    } else if (second instanceof Object[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof ArrayList) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof Serializable) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof boolean[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(...)");
                    } else if (second instanceof byte[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(...)");
                    } else if (second instanceof short[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(...)");
                    } else if (second instanceof char[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(...)");
                    } else if (second instanceof int[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(...)");
                    } else if (second instanceof long[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(...)");
                    } else if (second instanceof float[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(...)");
                    } else if (second instanceof double[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(...)");
                    } else if (second instanceof Bundle) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(...)");
                    } else if (second instanceof Intent) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                    } else {
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            settingFragment.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadDataData$lambda$8$lambda$7(SettingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Bundle bundle = new Bundle();
        bundle.putString("url", "https://www.qlifesnap.com/guide/HeyCyan/faq.html");
        SettingFragment settingFragment = this$0;
        FragmentActivity activity = settingFragment.getActivity();
        if (activity != null) {
            ArrayList<Pair> arrayList = new ArrayList();
            Intrinsics.checkNotNull(activity);
            Intent intent = new Intent(activity, (Class<?>) WebActivity.class);
            intent.setFlags(1);
            intent.putExtras(bundle);
            for (Pair pair : arrayList) {
                if (pair != null) {
                    String str = (String) pair.getFirst();
                    Object second = pair.getSecond();
                    if (second instanceof Integer) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(...)");
                    } else if (second instanceof Byte) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(...)");
                    } else if (second instanceof Character) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(...)");
                    } else if (second instanceof Short) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(...)");
                    } else if (second instanceof Boolean) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(...)");
                    } else if (second instanceof Long) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(...)");
                    } else if (second instanceof Float) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(...)");
                    } else if (second instanceof Double) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(...)");
                    } else if (second instanceof String) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(...)");
                    } else if (second instanceof CharSequence) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(...)");
                    } else if (second instanceof Parcelable) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                    } else if (second instanceof Object[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof ArrayList) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof Serializable) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(...)");
                    } else if (second instanceof boolean[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(...)");
                    } else if (second instanceof byte[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(...)");
                    } else if (second instanceof short[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(...)");
                    } else if (second instanceof char[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(...)");
                    } else if (second instanceof int[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(...)");
                    } else if (second instanceof long[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(...)");
                    } else if (second instanceof float[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(...)");
                    } else if (second instanceof double[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(...)");
                    } else if (second instanceof Bundle) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(...)");
                    } else if (second instanceof Intent) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(...)");
                    } else {
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            settingFragment.startActivity(intent);
        }
    }

    private final void observer() {
        SettingFragmentVM mViewModel = getMViewModel();
        mViewModel.getUserInfoLD().observe(getViewLifecycleOwner(), new SettingFragment$sam$androidx_lifecycle_Observer$0(new Function1<UserModel, Unit>() { // from class: com.glasssutdio.wear.home.SettingFragment$observer$1$1
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
                FragmentSettingBinding fragmentSettingBinding = this.this$0.binding;
                FragmentSettingBinding fragmentSettingBinding2 = null;
                if (fragmentSettingBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentSettingBinding = null;
                }
                ImageFilterView ivAvatar = fragmentSettingBinding.ivAvatar;
                Intrinsics.checkNotNullExpressionValue(ivAvatar, "ivAvatar");
                ImageExtKt.displayAvatar(ivAvatar, CommonUtils.INSTANCE.getAvatarUrl(), new Function1<RequestBuilder<?>, RequestBuilder<?>>() { // from class: com.glasssutdio.wear.home.SettingFragment$observer$1$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final RequestBuilder<?> invoke(RequestBuilder<?> displayAvatar) {
                        Intrinsics.checkNotNullParameter(displayAvatar, "$this$displayAvatar");
                        Cloneable cloneableOverride = displayAvatar.override(GlobalKt.getDp((Number) 50), GlobalKt.getDp((Number) 50));
                        Intrinsics.checkNotNullExpressionValue(cloneableOverride, "override(...)");
                        return (RequestBuilder) cloneableOverride;
                    }
                });
                if (userModel.getNickname().length() == 0) {
                    FragmentSettingBinding fragmentSettingBinding3 = this.this$0.binding;
                    if (fragmentSettingBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentSettingBinding2 = fragmentSettingBinding3;
                    }
                    fragmentSettingBinding2.tvUserName.setText(this.this$0.getString(C0775R.string.app_name));
                    return;
                }
                FragmentSettingBinding fragmentSettingBinding4 = this.this$0.binding;
                if (fragmentSettingBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentSettingBinding2 = fragmentSettingBinding4;
                }
                fragmentSettingBinding2.tvUserName.setText(userModel.getNickname());
            }
        }));
        mViewModel.getFailLD().observe(getViewLifecycleOwner(), new SettingFragment$sam$androidx_lifecycle_Observer$0(new Function1<RequestFailModel, Unit>() { // from class: com.glasssutdio.wear.home.SettingFragment$observer$1$2
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
                    FragmentSettingBinding fragmentSettingBinding = this.this$0.binding;
                    FragmentSettingBinding fragmentSettingBinding2 = null;
                    if (fragmentSettingBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentSettingBinding = null;
                    }
                    fragmentSettingBinding.ivAvatar.setImageResource(C0775R.mipmap.ic_avatar_default_unknow);
                    FragmentSettingBinding fragmentSettingBinding3 = this.this$0.binding;
                    if (fragmentSettingBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentSettingBinding2 = fragmentSettingBinding3;
                    }
                    fragmentSettingBinding2.tvUserName.setText(this.this$0.getString(C0775R.string.app_name));
                }
            }
        }));
    }

    /* compiled from: SettingFragment.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, m607d2 = {"Lcom/glasssutdio/wear/home/SettingFragment$Companion;", "", "()V", "newInstance", "Lcom/glasssutdio/wear/home/SettingFragment;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SettingFragment newInstance() {
            return new SettingFragment();
        }
    }

    @Override // com.glasssutdio.wear.home.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
