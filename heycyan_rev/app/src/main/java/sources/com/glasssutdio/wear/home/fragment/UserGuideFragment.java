package com.glasssutdio.wear.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.Constant;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.utils.image.ImageExtKt;
import com.glasssutdio.wear.databinding.FragmentUserGuideBinding;
import com.glasssutdio.wear.home.BaseFragment;
import com.glasssutdio.wear.home.bean.UserGuideModel;
import com.glasssutdio.wear.home.viewmodel.UserGuideFragmentVM;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: UserGuideFragment.kt */
@Metadata(m606d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0003J\b\u0010\u000f\u001a\u00020\fH\u0016J$\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u001a\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0014\u0010\u001a\u001a\u00020\f*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u001f"}, m607d2 = {"Lcom/glasssutdio/wear/home/fragment/UserGuideFragment;", "Lcom/glasssutdio/wear/home/BaseFragment;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/FragmentUserGuideBinding;", "mViewModel", "Lcom/glasssutdio/wear/home/viewmodel/UserGuideFragmentVM;", "getMViewModel", "()Lcom/glasssutdio/wear/home/viewmodel/UserGuideFragmentVM;", "mViewModel$delegate", "Lkotlin/Lazy;", "fillData", "", "index", "", "loadDataData", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "setDimensionRatio", "Landroid/widget/ImageView;", "ratio", "", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class UserGuideFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String PAGE_INDEX = "page_index";
    private FragmentUserGuideBinding binding;

    /* renamed from: mViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mViewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public UserGuideFragment() {
        final UserGuideFragment userGuideFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.mViewModel = LazyKt.lazy(new Function0<UserGuideFragmentVM>() { // from class: com.glasssutdio.wear.home.fragment.UserGuideFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.viewmodel.UserGuideFragmentVM] */
            @Override // kotlin.jvm.functions.Function0
            public final UserGuideFragmentVM invoke() {
                return LifecycleOwnerExtKt.getViewModel(userGuideFragment, Reflection.getOrCreateKotlinClass(UserGuideFragmentVM.class), qualifier, objArr);
            }
        });
    }

    private final UserGuideFragmentVM getMViewModel() {
        return (UserGuideFragmentVM) this.mViewModel.getValue();
    }

    @Override // com.glasssutdio.wear.home.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentUserGuideBinding fragmentUserGuideBindingInflate = FragmentUserGuideBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentUserGuideBindingInflate, "inflate(...)");
        this.binding = fragmentUserGuideBindingInflate;
        if (fragmentUserGuideBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentUserGuideBindingInflate = null;
        }
        NestedScrollView root = fragmentUserGuideBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // com.glasssutdio.wear.home.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        UserGuideFragmentVM mViewModel = getMViewModel();
        Bundle arguments = getArguments();
        mViewModel.setPageGuideType(arguments != null ? arguments.getInt(Constant.PAGE_GUIDE_KEY) : 1);
        getMViewModel().initData();
        Bundle arguments2 = getArguments();
        fillData(arguments2 != null ? arguments2.getInt(PAGE_INDEX) : 0);
    }

    @Override // com.glasssutdio.wear.home.BaseFragment
    public void loadDataData() {
        super.loadDataData();
    }

    private final void fillData(int index) {
        int color;
        FragmentUserGuideBinding fragmentUserGuideBinding = this.binding;
        if (fragmentUserGuideBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentUserGuideBinding = null;
        }
        UserGuideModel userGuideModel = (UserGuideModel) CollectionsKt.getOrNull(getMViewModel().getGuideDataList(), index);
        if (userGuideModel != null) {
            fragmentUserGuideBinding.tvTime.setText(userGuideModel.getTime());
            fragmentUserGuideBinding.tvIndex.setText(new StringBuilder().append(index + 1).append(IOUtils.DIR_SEPARATOR_UNIX).append(getMViewModel().getFragmentTotalsByType()).toString());
            String gifFileName = userGuideModel.getGifFileName();
            if (gifFileName != null && gifFileName.length() != 0) {
                ImageView ivImg = fragmentUserGuideBinding.ivImg;
                Intrinsics.checkNotNullExpressionValue(ivImg, "ivImg");
                ImageExtKt.loadGifFromAssets$default(ivImg, userGuideModel.getGifFileName(), 0, 2, null);
                ImageView ivImg2 = fragmentUserGuideBinding.ivImg;
                Intrinsics.checkNotNullExpressionValue(ivImg2, "ivImg");
                setDimensionRatio(ivImg2, new StringBuilder().append(userGuideModel.getImgWidth()).append(':').append(userGuideModel.getImgHeight()).toString());
            } else {
                Integer img = userGuideModel.getImg();
                if (img != null) {
                    int iIntValue = img.intValue();
                    ImageView ivImg3 = fragmentUserGuideBinding.ivImg;
                    Intrinsics.checkNotNullExpressionValue(ivImg3, "ivImg");
                    setDimensionRatio(ivImg3, new StringBuilder().append(userGuideModel.getImgWidth()).append(':').append(userGuideModel.getImgHeight()).toString());
                    fragmentUserGuideBinding.ivImg.setImageResource(iIntValue);
                }
            }
            fragmentUserGuideBinding.tvTitle.setText(userGuideModel.getText());
            fragmentUserGuideBinding.tvSubTitle.setText(userGuideModel.getSubText());
            fragmentUserGuideBinding.tvDesc.setText(userGuideModel.getDesc());
            TextView textView = fragmentUserGuideBinding.tvTime;
            String time = userGuideModel.getTime();
            ViewKt.goneOrVisible(textView, !(time == null || time.length() == 0));
            TextView textView2 = fragmentUserGuideBinding.tvTitle;
            if (userGuideModel.getTitleIsLightColor()) {
                color = ContextCompat.getColor(requireContext(), C0775R.color.color_FF4026);
            } else {
                color = ContextCompat.getColor(requireContext(), C0775R.color.white);
            }
            textView2.setTextColor(color);
            ImageView ivImg4 = fragmentUserGuideBinding.ivImg;
            Intrinsics.checkNotNullExpressionValue(ivImg4, "ivImg");
            ViewKt.setMargin$default(ivImg4, null, Integer.valueOf(GlobalKt.getDp(Integer.valueOf(userGuideModel.getImageMarginTop()))), null, null, 13, null);
            TextView tvTitle = fragmentUserGuideBinding.tvTitle;
            Intrinsics.checkNotNullExpressionValue(tvTitle, "tvTitle");
            ViewKt.setMargin$default(tvTitle, null, Integer.valueOf(GlobalKt.getDp(Integer.valueOf(userGuideModel.getTextMarginTop()))), null, null, 13, null);
            if (getMViewModel().getPageGuideType() != 3) {
                return;
            }
            ViewKt.gone(fragmentUserGuideBinding.tvIndex);
        }
    }

    private final void setDimensionRatio(ImageView imageView, String str) {
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        layoutParams2.dimensionRatio = str;
        imageView.setLayoutParams(layoutParams2);
    }

    /* compiled from: UserGuideFragment.kt */
    @Metadata(m606d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, m607d2 = {"Lcom/glasssutdio/wear/home/fragment/UserGuideFragment$Companion;", "", "()V", "PAGE_INDEX", "", "newInstance", "Lcom/glasssutdio/wear/home/fragment/UserGuideFragment;", "index", "", "guideType", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ UserGuideFragment newInstance$default(Companion companion, int i, int i2, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                i2 = 1;
            }
            return companion.newInstance(i, i2);
        }

        public final UserGuideFragment newInstance(int index, int guideType) {
            Bundle bundle = new Bundle();
            bundle.putInt(UserGuideFragment.PAGE_INDEX, index);
            bundle.putInt(Constant.PAGE_GUIDE_KEY, guideType);
            UserGuideFragment userGuideFragment = new UserGuideFragment();
            userGuideFragment.setArguments(bundle);
            return userGuideFragment;
        }
    }
}
