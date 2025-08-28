package com.glasssutdio.wear.home.album;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.all.ThreadExtKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.view.ScrollbarConfig;
import com.glasssutdio.wear.bus.AlbumDownloadSuccessfullyEvent;
import com.glasssutdio.wear.bus.AlbumEditEvent;
import com.glasssutdio.wear.bus.AlbumRefreshEvent;
import com.glasssutdio.wear.bus.BusEvent;
import com.glasssutdio.wear.bus.EventType;
import com.glasssutdio.wear.bus.RecordingToPcmSuccessfullyEvent;
import com.glasssutdio.wear.bus.VideoEisSuccessfullyEvent;
import com.glasssutdio.wear.database.entity.GlassAlbumEntity;
import com.glasssutdio.wear.databinding.FragmentAlbumAllBinding;
import com.glasssutdio.wear.home.BaseFragment;
import com.glasssutdio.wear.home.adapter.AlbumAdapter;
import com.glasssutdio.wear.home.album.p005vm.AlbumListViewModel;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: AlbumAllFragment.kt */
@Metadata(m606d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J&\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J\b\u0010\u001c\u001a\u00020\u0010H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001d"}, m607d2 = {"Lcom/glasssutdio/wear/home/album/AlbumAllFragment;", "Lcom/glasssutdio/wear/home/BaseFragment;", "()V", "adapter", "Lcom/glasssutdio/wear/home/adapter/AlbumAdapter;", "albumViewModel", "Lcom/glasssutdio/wear/home/album/vm/AlbumListViewModel;", "getAlbumViewModel", "()Lcom/glasssutdio/wear/home/album/vm/AlbumListViewModel;", "albumViewModel$delegate", "Lkotlin/Lazy;", "binding", "Lcom/glasssutdio/wear/databinding/FragmentAlbumAllBinding;", "isFragmentVisible", "", "loadDataData", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/glasssutdio/wear/bus/BusEvent;", "onResume", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AlbumAllFragment extends BaseFragment {
    private AlbumAdapter adapter;

    /* renamed from: albumViewModel$delegate, reason: from kotlin metadata */
    private final Lazy albumViewModel;
    private FragmentAlbumAllBinding binding;

    /* JADX WARN: Multi-variable type inference failed */
    public AlbumAllFragment() {
        final AlbumAllFragment albumAllFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.albumViewModel = LazyKt.lazy(new Function0<AlbumListViewModel>() { // from class: com.glasssutdio.wear.home.album.AlbumAllFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.home.album.vm.AlbumListViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final AlbumListViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(albumAllFragment, Reflection.getOrCreateKotlinClass(AlbumListViewModel.class), qualifier, objArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AlbumListViewModel getAlbumViewModel() {
        return (AlbumListViewModel) this.albumViewModel.getValue();
    }

    @Override // com.glasssutdio.wear.home.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws SecurityException {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentAlbumAllBinding fragmentAlbumAllBindingInflate = FragmentAlbumAllBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentAlbumAllBindingInflate, "inflate(...)");
        this.binding = fragmentAlbumAllBindingInflate;
        EventBus.getDefault().register(this);
        FragmentAlbumAllBinding fragmentAlbumAllBinding = this.binding;
        if (fragmentAlbumAllBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAlbumAllBinding = null;
        }
        return fragmentAlbumAllBinding.getRoot();
    }

    @Override // com.glasssutdio.wear.home.BaseFragment
    public void loadDataData() throws InterruptedException {
        super.loadDataData();
        this.adapter = new AlbumAdapter(getAlbumViewModel().getData());
        final FragmentAlbumAllBinding fragmentAlbumAllBinding = this.binding;
        AlbumAdapter albumAdapter = null;
        if (fragmentAlbumAllBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAlbumAllBinding = null;
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.glasssutdio.wear.home.album.AlbumAllFragment$loadDataData$1$1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int position) {
                RecyclerView.Adapter adapter = fragmentAlbumAllBinding.rcvAlbumAll.getAdapter();
                Intrinsics.checkNotNull(adapter, "null cannot be cast to non-null type com.glasssutdio.wear.home.adapter.AlbumAdapter");
                AlbumAdapter albumAdapter2 = (AlbumAdapter) adapter;
                return (albumAdapter2.getItemViewType(position) == 0 || albumAdapter2.getItemViewType(position) == 2) ? 4 : 1;
            }
        });
        fragmentAlbumAllBinding.rcvAlbumAll.setItemAnimator(null);
        fragmentAlbumAllBinding.rcvAlbumAll.setLayoutManager(gridLayoutManager);
        RecyclerView recyclerView = fragmentAlbumAllBinding.rcvAlbumAll;
        AlbumAdapter albumAdapter2 = this.adapter;
        if (albumAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            albumAdapter2 = null;
        }
        recyclerView.setAdapter(albumAdapter2);
        AlbumAdapter albumAdapter3 = this.adapter;
        if (albumAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            albumAdapter = albumAdapter3;
        }
        albumAdapter.setOnItemClickListener(new AlbumAdapter.OnItemClickListener() { // from class: com.glasssutdio.wear.home.album.AlbumAllFragment$loadDataData$1$2
            @Override // com.glasssutdio.wear.home.adapter.AlbumAdapter.OnItemClickListener
            public void onItemClick(int position, GlassAlbumEntity item) {
                Intrinsics.checkNotNullParameter(item, "item");
                Bundle bundle = new Bundle();
                bundle.putString("file_name", item.getFileName());
                bundle.putInt("input_type", 0);
                AlbumAllFragment albumAllFragment = this.this$0;
                FragmentActivity activity = albumAllFragment.getActivity();
                if (activity != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNull(activity);
                    Intent intent = new Intent(activity, (Class<?>) ShowMediaDetailActivity.class);
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
                    albumAllFragment.startActivity(intent);
                }
            }
        });
        RecyclerView rcvAlbumAll = fragmentAlbumAllBinding.rcvAlbumAll;
        Intrinsics.checkNotNullExpressionValue(rcvAlbumAll, "rcvAlbumAll");
        ViewKt.addCustomScrollbar(rcvAlbumAll, ScrollbarConfig.INSTANCE.prominent());
        getAlbumViewModel().getUiState().observe(this, new AlbumAllFragment$sam$androidx_lifecycle_Observer$0(new Function1<AlbumListViewModel.DeviceAlbumUI, Unit>() { // from class: com.glasssutdio.wear.home.album.AlbumAllFragment.loadDataData.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AlbumListViewModel.DeviceAlbumUI deviceAlbumUI) throws Resources.NotFoundException {
                invoke2(deviceAlbumUI);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AlbumListViewModel.DeviceAlbumUI deviceAlbumUI) throws Resources.NotFoundException {
                AlbumAdapter albumAdapter4 = AlbumAllFragment.this.adapter;
                AlbumAdapter albumAdapter5 = null;
                if (albumAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    albumAdapter4 = null;
                }
                albumAdapter4.refresh(AlbumAllFragment.this.getAlbumViewModel().getData());
                AlbumListViewModel albumViewModel = AlbumAllFragment.this.getAlbumViewModel();
                AlbumAdapter albumAdapter6 = AlbumAllFragment.this.adapter;
                if (albumAdapter6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    albumAdapter6 = null;
                }
                albumViewModel.initDetailList(albumAdapter6.getAllItemsNoKey());
                if (AlbumAllFragment.this.getAlbumViewModel().getData().isEmpty()) {
                    FragmentAlbumAllBinding fragmentAlbumAllBinding2 = AlbumAllFragment.this.binding;
                    if (fragmentAlbumAllBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentAlbumAllBinding2 = null;
                    }
                    ViewKt.visible(fragmentAlbumAllBinding2.ctlNoData);
                } else {
                    FragmentAlbumAllBinding fragmentAlbumAllBinding3 = AlbumAllFragment.this.binding;
                    if (fragmentAlbumAllBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentAlbumAllBinding3 = null;
                    }
                    ViewKt.gone(fragmentAlbumAllBinding3.ctlNoData);
                }
                AlbumAdapter albumAdapter7 = AlbumAllFragment.this.adapter;
                if (albumAdapter7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    albumAdapter5 = albumAdapter7;
                }
                albumAdapter5.notifyDataSetChanged();
            }
        }));
        getAlbumViewModel().initAllData();
    }

    @Override // com.glasssutdio.wear.home.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        EventBus.getDefault().post(new AlbumEditEvent(getAlbumViewModel().getData().isEmpty()));
        AlbumListViewModel albumViewModel = getAlbumViewModel();
        AlbumAdapter albumAdapter = this.adapter;
        if (albumAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            albumAdapter = null;
        }
        albumViewModel.initDetailList(albumAdapter.getAllItemsNoKey());
    }

    private final boolean isFragmentVisible() {
        return isAdded() && !isHidden() && getView() != null && requireView().getVisibility() == 0 && isResumed();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(BusEvent messageEvent) throws InterruptedException {
        AlbumAdapter albumAdapter;
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if (messageEvent instanceof EventType) {
            EventType eventType = (EventType) messageEvent;
            if (eventType.getType() == 6) {
                XLog.m137i("messageEvent.type->" + eventType.getType());
                ThreadExtKt.ktxRunOnUiDelay(this, 500L, new Function1<AlbumAllFragment, Unit>() { // from class: com.glasssutdio.wear.home.album.AlbumAllFragment.onMessageEvent.1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(AlbumAllFragment albumAllFragment) throws InterruptedException {
                        invoke2(albumAllFragment);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(AlbumAllFragment ktxRunOnUiDelay) throws InterruptedException {
                        Intrinsics.checkNotNullParameter(ktxRunOnUiDelay, "$this$ktxRunOnUiDelay");
                        ktxRunOnUiDelay.getAlbumViewModel().initAllData();
                    }
                });
                if (isFragmentVisible()) {
                    return;
                }
                try {
                    ThreadExtKt.ktxRunOnUiDelay(this, 1000L, new Function1<AlbumAllFragment, Unit>() { // from class: com.glasssutdio.wear.home.album.AlbumAllFragment.onMessageEvent.2
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(AlbumAllFragment albumAllFragment) {
                            invoke2(albumAllFragment);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(AlbumAllFragment ktxRunOnUiDelay) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUiDelay, "$this$ktxRunOnUiDelay");
                            AlbumListViewModel albumViewModel = ktxRunOnUiDelay.getAlbumViewModel();
                            AlbumAdapter albumAdapter2 = ktxRunOnUiDelay.adapter;
                            if (albumAdapter2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                                albumAdapter2 = null;
                            }
                            albumViewModel.initDetailList(albumAdapter2.getAllItemsNoKey());
                            EventBus.getDefault().post(new EventType(13));
                        }
                    });
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            if (eventType.getType() == 10 || eventType.getType() == 9) {
                getAlbumViewModel().initAllData();
                return;
            }
            return;
        }
        AlbumAdapter albumAdapter2 = null;
        if (messageEvent instanceof AlbumRefreshEvent) {
            AlbumAdapter albumAdapter3 = this.adapter;
            if (albumAdapter3 != null) {
                if (albumAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    albumAdapter2 = albumAdapter3;
                }
                String addressByAlbumModel = albumAdapter2.getAddressByAlbumModel();
                if (addressByAlbumModel == null || Intrinsics.areEqual(addressByAlbumModel, UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear())) {
                    return;
                }
                getAlbumViewModel().initAllData();
                return;
            }
            return;
        }
        if (messageEvent instanceof AlbumDownloadSuccessfullyEvent) {
            if (this.adapter != null) {
                FragmentAlbumAllBinding fragmentAlbumAllBinding = this.binding;
                if (fragmentAlbumAllBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentAlbumAllBinding = null;
                }
                ViewKt.gone(fragmentAlbumAllBinding.ctlNoData);
                AlbumAdapter albumAdapter4 = this.adapter;
                if (albumAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    albumAdapter2 = albumAdapter4;
                }
                albumAdapter2.refreshFirst(((AlbumDownloadSuccessfullyEvent) messageEvent).getEntity());
                return;
            }
            return;
        }
        if (messageEvent instanceof VideoEisSuccessfullyEvent) {
            AlbumAdapter albumAdapter5 = this.adapter;
            if (albumAdapter5 != null) {
                if (albumAdapter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    albumAdapter2 = albumAdapter5;
                }
                VideoEisSuccessfullyEvent videoEisSuccessfullyEvent = (VideoEisSuccessfullyEvent) messageEvent;
                albumAdapter2.findPositionByName(videoEisSuccessfullyEvent.getFileName(), videoEisSuccessfullyEvent.getSuccess(), videoEisSuccessfullyEvent.getPath(), 0);
                XLog.m137i("更新为防抖path");
                return;
            }
            return;
        }
        if (!(messageEvent instanceof RecordingToPcmSuccessfullyEvent) || (albumAdapter = this.adapter) == null) {
            return;
        }
        if (albumAdapter == null) {
            try {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        } else {
            albumAdapter2 = albumAdapter;
        }
        albumAdapter2.findPositionByName(((RecordingToPcmSuccessfullyEvent) messageEvent).getFileName(), false, ((RecordingToPcmSuccessfullyEvent) messageEvent).getPath(), ((RecordingToPcmSuccessfullyEvent) messageEvent).getDuration());
        XLog.m137i("opus 转pcm" + ((RecordingToPcmSuccessfullyEvent) messageEvent).getDuration());
    }
}
