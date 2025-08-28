package com.glasssutdio.wear.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.GlassApplication;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.TextViewExtKt;
import com.glasssutdio.wear.all.ThreadExtKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.dialog.CommonDialog;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.GsonInstance;
import com.glasssutdio.wear.all.utils.PermissionUtilKt;
import com.glasssutdio.wear.api.SSEHandler;
import com.glasssutdio.wear.ble.connect.BindGuideActivity;
import com.glasssutdio.wear.bus.AiHistoryEditEvent;
import com.glasssutdio.wear.bus.BluetoothEvent;
import com.glasssutdio.wear.bus.BusEvent;
import com.glasssutdio.wear.bus.EventType;
import com.glasssutdio.wear.bus.RefreshUserEvent;
import com.glasssutdio.wear.bus.UpdateAiLanguageEvent;
import com.glasssutdio.wear.database.entity.AiChatEntity;
import com.glasssutdio.wear.databinding.FragmentAiBinding;
import com.glasssutdio.wear.depository.AiChatDepository;
import com.glasssutdio.wear.home.AiFragment;
import com.glasssutdio.wear.home.activity.AILanguageActivity;
import com.glasssutdio.wear.p003ai.AiShowImageDetailActivity;
import com.glasssutdio.wear.p003ai.adapter.ChatAdapter;
import com.glasssutdio.wear.p003ai.bean.ChatMessage;
import com.glasssutdio.wear.p003ai.p004vm.AiChatViewModel;
import com.glasssutdio.wear.p003ai.spark.AudioTrackManager;
import com.glasssutdio.wear.p003ai.spark.SparkChainRecognizer;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.hjq.permissions.OnPermissionCallback;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: AiFragment.kt */
@Metadata(m606d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 (2\u00020\u0001:\u0002()B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\b\u0010\u0016\u001a\u00020\u0014H\u0002J\b\u0010\u0017\u001a\u00020\u0014H\u0002J$\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0014H\u0016J\u0010\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020#H\u0007J\b\u0010$\u001a\u00020\u0014H\u0016J\b\u0010%\u001a\u00020\u0014H\u0002J+\u0010&\u001a\u00020\u00142#\u0010'\u001a\u001f\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, m607d2 = {"Lcom/glasssutdio/wear/home/AiFragment;", "Lcom/glasssutdio/wear/home/BaseFragment;", "()V", "aiChatViewModel", "Lcom/glasssutdio/wear/ai/vm/AiChatViewModel;", "getAiChatViewModel", "()Lcom/glasssutdio/wear/ai/vm/AiChatViewModel;", "aiChatViewModel$delegate", "Lkotlin/Lazy;", "binding", "Lcom/glasssutdio/wear/databinding/FragmentAiBinding;", "chatAdapter", "Lcom/glasssutdio/wear/ai/adapter/ChatAdapter;", "firstLoad", "", "historyEditChange", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "isEdit", "", "loadDataData", "loadInitialData", "loadMoreData", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/glasssutdio/wear/bus/BusEvent;", "onResume", "playStateCallback", "setOnHistoryEditChangeListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Companion", "LocationPermissionCallback", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AiFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* renamed from: aiChatViewModel$delegate, reason: from kotlin metadata */
    private final Lazy aiChatViewModel;
    private FragmentAiBinding binding;
    private ChatAdapter chatAdapter;
    private boolean firstLoad;
    private Function1<? super Boolean, Unit> historyEditChange;
    private boolean isEdit;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadDataData$lambda$5$lambda$0(View view) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AiFragment() {
        final AiFragment aiFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.aiChatViewModel = LazyKt.lazy(new Function0<AiChatViewModel>() { // from class: com.glasssutdio.wear.home.AiFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.glasssutdio.wear.ai.vm.AiChatViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final AiChatViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(aiFragment, Reflection.getOrCreateKotlinClass(AiChatViewModel.class), qualifier, objArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AiChatViewModel getAiChatViewModel() {
        return (AiChatViewModel) this.aiChatViewModel.getValue();
    }

    public final void setOnHistoryEditChangeListener(Function1<? super Boolean, Unit> listener) {
        this.historyEditChange = listener;
    }

    @Override // com.glasssutdio.wear.home.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws SecurityException {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentAiBinding fragmentAiBindingInflate = FragmentAiBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentAiBindingInflate, "inflate(...)");
        this.binding = fragmentAiBindingInflate;
        EventBus.getDefault().register(this);
        FragmentAiBinding fragmentAiBinding = this.binding;
        if (fragmentAiBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAiBinding = null;
        }
        ConstraintLayout root = fragmentAiBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    /* compiled from: AiFragment.kt */
    @Metadata(m606d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, m607d2 = {"Lcom/glasssutdio/wear/home/AiFragment$LocationPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/glasssutdio/wear/home/AiFragment;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class LocationPermissionCallback implements OnPermissionCallback {
        public LocationPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            XLog.m136i(permissions);
            AiFragment aiFragment = AiFragment.this;
            FragmentActivity activity = aiFragment.getActivity();
            if (activity != null) {
                ArrayList<Pair> arrayList = new ArrayList();
                Intrinsics.checkNotNull(activity);
                Intent intent = new Intent(activity, (Class<?>) BindGuideActivity.class);
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
                aiFragment.startActivity(intent);
            }
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            super.onDenied(permissions, never);
            String string = AiFragment.this.getString(C0775R.string.ble_glass_20);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            GlobalKt.showToast$default(string, 0, 1, null);
            XLog.m136i(permissions);
            XLog.m136i(Boolean.valueOf(never));
        }
    }

    @Override // com.glasssutdio.wear.home.BaseFragment
    public void loadDataData() {
        super.loadDataData();
        final FragmentAiBinding fragmentAiBinding = this.binding;
        ChatAdapter chatAdapter = null;
        if (fragmentAiBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAiBinding = null;
        }
        TextView tvTitle = fragmentAiBinding.tvTitle;
        Intrinsics.checkNotNullExpressionValue(tvTitle, "tvTitle");
        ViewKt.statusMargin$default(tvTitle, false, 0, 3, null);
        TextView tvTitle2 = fragmentAiBinding.tvTitle;
        Intrinsics.checkNotNullExpressionValue(tvTitle2, "tvTitle");
        TextViewExtKt.setupMarqueeWithClick(tvTitle2);
        TextView tvTitle22 = fragmentAiBinding.tvTitle2;
        Intrinsics.checkNotNullExpressionValue(tvTitle22, "tvTitle2");
        ViewKt.statusMargin$default(tvTitle22, false, 0, 3, null);
        TextView tvTitle23 = fragmentAiBinding.tvTitle2;
        Intrinsics.checkNotNullExpressionValue(tvTitle23, "tvTitle2");
        TextViewExtKt.setupMarqueeWithClick(tvTitle23);
        fragmentAiBinding.noBindDevice.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.AiFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AiFragment.loadDataData$lambda$5$lambda$0(view);
            }
        });
        fragmentAiBinding.tvToBind.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.AiFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws NoSuchMethodException, SecurityException {
                AiFragment.loadDataData$lambda$5$lambda$1(this.f$0, view);
            }
        });
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.chatAdapter = new ChatAdapter(getActivity());
        linearLayoutManager.setReverseLayout(true);
        fragmentAiBinding.rcvChat.setItemAnimator(null);
        fragmentAiBinding.rcvChat.setLayoutManager(linearLayoutManager);
        RecyclerView recyclerView = fragmentAiBinding.rcvChat;
        ChatAdapter chatAdapter2 = this.chatAdapter;
        if (chatAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
            chatAdapter2 = null;
        }
        recyclerView.setAdapter(chatAdapter2);
        loadInitialData();
        ChatAdapter chatAdapter3 = this.chatAdapter;
        if (chatAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
            chatAdapter3 = null;
        }
        chatAdapter3.setOnItemClickListener(new Function2<View, Integer, Unit>() { // from class: com.glasssutdio.wear.home.AiFragment$loadDataData$1$3
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(View view, Integer num) {
                invoke(view, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(View view, int i) {
                Intrinsics.checkNotNullParameter(view, "<anonymous parameter 0>");
                if (this.this$0.isEdit) {
                    ChatAdapter chatAdapter4 = this.this$0.chatAdapter;
                    ChatAdapter chatAdapter5 = null;
                    if (chatAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                        chatAdapter4 = null;
                    }
                    chatAdapter4.clickChecked(i);
                    ChatAdapter chatAdapter6 = this.this$0.chatAdapter;
                    if (chatAdapter6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                        chatAdapter6 = null;
                    }
                    int size = chatAdapter6.getSelectData().size();
                    ChatAdapter chatAdapter7 = this.this$0.chatAdapter;
                    if (chatAdapter7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                    } else {
                        chatAdapter5 = chatAdapter7;
                    }
                    if (size == chatAdapter5.getData().size()) {
                        EventBus.getDefault().post(new AiHistoryEditEvent(5));
                    } else {
                        EventBus.getDefault().post(new AiHistoryEditEvent(4));
                    }
                }
            }
        });
        fragmentAiBinding.ivChatLanguage.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.AiFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AiFragment.loadDataData$lambda$5$lambda$2(this.f$0, view);
            }
        });
        ChatAdapter chatAdapter4 = this.chatAdapter;
        if (chatAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
            chatAdapter4 = null;
        }
        AiFragment aiFragment = this;
        chatAdapter4.getOnImageClick().observe(aiFragment, new AiFragment$sam$androidx_lifecycle_Observer$0(new Function1<ChatMessage, Unit>() { // from class: com.glasssutdio.wear.home.AiFragment$loadDataData$1$5
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ChatMessage chatMessage) {
                invoke2(chatMessage);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ChatMessage chatMessage) {
                if (chatMessage.getMessageType() == 2) {
                    Bundle bundle = new Bundle();
                    bundle.putString("file_path", chatMessage.getImagePath());
                    AiFragment aiFragment2 = this.this$0;
                    FragmentActivity activity = aiFragment2.getActivity();
                    if (activity != null) {
                        ArrayList<Pair> arrayList = new ArrayList();
                        Intrinsics.checkNotNull(activity);
                        Intent intent = new Intent(activity, (Class<?>) AiShowImageDetailActivity.class);
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
                        aiFragment2.startActivity(intent);
                    }
                }
            }
        }));
        ChatAdapter chatAdapter5 = this.chatAdapter;
        if (chatAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
            chatAdapter5 = null;
        }
        chatAdapter5.setOnLikeClickListener(new Function2<Integer, ChatMessage, Unit>() { // from class: com.glasssutdio.wear.home.AiFragment$loadDataData$1$6
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, ChatMessage chatMessage) {
                invoke(num.intValue(), chatMessage);
                return Unit.INSTANCE;
            }

            public final void invoke(int i, ChatMessage model) {
                Intrinsics.checkNotNullParameter(model, "model");
                this.this$0.getAiChatViewModel().updateAiChatModelLikeOrOnLike(model.getUid(), model.getChatTimeStamp(), Boolean.valueOf(model.isLike()), Boolean.valueOf(model.isUnLike()));
            }
        });
        ChatAdapter chatAdapter6 = this.chatAdapter;
        if (chatAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
            chatAdapter6 = null;
        }
        chatAdapter6.setOnUnLikeClickListener(new Function2<Integer, ChatMessage, Unit>() { // from class: com.glasssutdio.wear.home.AiFragment$loadDataData$1$7
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, ChatMessage chatMessage) {
                invoke(num.intValue(), chatMessage);
                return Unit.INSTANCE;
            }

            public final void invoke(int i, ChatMessage model) {
                Intrinsics.checkNotNullParameter(model, "model");
                this.this$0.getAiChatViewModel().updateAiChatModelLikeOrOnLike(model.getUid(), model.getChatTimeStamp(), Boolean.valueOf(model.isLike()), Boolean.valueOf(model.isUnLike()));
            }
        });
        ChatAdapter chatAdapter7 = this.chatAdapter;
        if (chatAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
        } else {
            chatAdapter = chatAdapter7;
        }
        chatAdapter.setSoundClickListener(new Function2<View, Integer, Unit>() { // from class: com.glasssutdio.wear.home.AiFragment$loadDataData$1$8
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(View view, Integer num) throws IllegalStateException {
                invoke(view, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(View view, int i) throws IllegalStateException {
                Intrinsics.checkNotNullParameter(view, "<anonymous parameter 0>");
                SparkChainRecognizer.getInstance().stopTTS();
                AiChatDepository.INSTANCE.getGetInstance().cleanRealTimeTTSQueue();
                AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).restartPlay();
                SSEHandler.INSTANCE.getInstance().stop();
            }
        });
        fragmentAiBinding.rcvChat.addOnScrollListener(new ChatAdapter.EndlessRecyclerViewScrollListener(linearLayoutManager) { // from class: com.glasssutdio.wear.home.AiFragment$loadDataData$1$9
            @Override // com.glasssutdio.wear.ai.adapter.ChatAdapter.EndlessRecyclerViewScrollListener
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Intrinsics.checkNotNullParameter(view, "view");
                XLog.m137i("11111" + page);
                this.loadMoreData();
            }
        });
        fragmentAiBinding.ivEdit.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.AiFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AiFragment.loadDataData$lambda$5$lambda$3(this.f$0, fragmentAiBinding, view);
            }
        });
        fragmentAiBinding.tvEditFinish.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.AiFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AiFragment.loadDataData$lambda$5$lambda$4(this.f$0, fragmentAiBinding, view);
            }
        });
        getAiChatViewModel().setChatGptCallback(new AiChatDepository.ChatGptCallback() { // from class: com.glasssutdio.wear.home.AiFragment.loadDataData.2
            @Override // com.glasssutdio.wear.depository.AiChatDepository.ChatGptCallback
            public void chatGptSuccess(final int type, final String content, final AiChatEntity entity) {
                Intrinsics.checkNotNullParameter(content, "content");
                final AiFragment aiFragment2 = AiFragment.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<C09672, Unit>() { // from class: com.glasssutdio.wear.home.AiFragment$loadDataData$2$chatGptSuccess$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(AiFragment.C09672 c09672) {
                        invoke2(c09672);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(AiFragment.C09672 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        XLog.m137i("ai正常返回-加载更多" + type + "---" + content);
                        FragmentAiBinding fragmentAiBinding2 = null;
                        if (UserConfig.INSTANCE.getInstance().getDebug()) {
                            GlobalKt.showToast$default("ai正常返回" + type + "---" + content, 0, 1, null);
                        }
                        switch (type) {
                            case 0:
                            case 1:
                            case 9:
                                if (content.length() > 0) {
                                    SparkChainRecognizer.getInstance().startTTS(content);
                                    break;
                                }
                                break;
                            case 2:
                                aiFragment2.getAiChatViewModel().visionAiStart();
                                break;
                            case 3:
                                aiFragment2.getAiChatViewModel().takePicture();
                                String string = aiFragment2.getString(C0775R.string.ble_glass_36);
                                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                                GlobalKt.showToast$default(string, 0, 1, null);
                                break;
                            case 4:
                                SparkChainRecognizer.getInstance().startTTS("我不能播放歌曲");
                                break;
                            case 5:
                                SparkChainRecognizer.getInstance().exitAi();
                                String string2 = aiFragment2.getString(C0775R.string.ble_glass_42);
                                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                                GlobalKt.showToast$default(string2, 0, 1, null);
                                break;
                            case 7:
                                String string3 = aiFragment2.getString(C0775R.string.ble_glass_43);
                                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                                GlobalKt.showToast$default(string3, 0, 1, null);
                                break;
                            case 8:
                                EventBus.getDefault().post(new EventType(18));
                                break;
                            case 11:
                                aiFragment2.getAiChatViewModel().startVideo();
                                break;
                            case 12:
                                aiFragment2.getAiChatViewModel().startRecord();
                                break;
                        }
                        if (entity != null) {
                            FragmentAiBinding fragmentAiBinding3 = aiFragment2.binding;
                            if (fragmentAiBinding3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                fragmentAiBinding3 = null;
                            }
                            ViewKt.gone(fragmentAiBinding3.chatNoData);
                            FragmentAiBinding fragmentAiBinding4 = aiFragment2.binding;
                            if (fragmentAiBinding4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                fragmentAiBinding4 = null;
                            }
                            TextView tvEditFinish = fragmentAiBinding4.tvEditFinish;
                            Intrinsics.checkNotNullExpressionValue(tvEditFinish, "tvEditFinish");
                            if (tvEditFinish.getVisibility() != 0) {
                                FragmentAiBinding fragmentAiBinding5 = aiFragment2.binding;
                                if (fragmentAiBinding5 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    fragmentAiBinding5 = null;
                                }
                                ViewKt.visible(fragmentAiBinding5.ivEdit);
                            }
                            ChatAdapter chatAdapter8 = aiFragment2.chatAdapter;
                            if (chatAdapter8 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                                chatAdapter8 = null;
                            }
                            chatAdapter8.addTheLast(entity);
                            FragmentAiBinding fragmentAiBinding6 = aiFragment2.binding;
                            if (fragmentAiBinding6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                fragmentAiBinding2 = fragmentAiBinding6;
                            }
                            fragmentAiBinding2.rcvChat.scrollToPosition(0);
                        }
                    }
                });
            }

            @Override // com.glasssutdio.wear.depository.AiChatDepository.ChatGptCallback
            public void chatGptFail(int reason) {
                XLog.m137i("chat fail:" + reason);
            }

            @Override // com.glasssutdio.wear.depository.AiChatDepository.ChatGptCallback
            public void chatGptTextStream(boolean isComplete, AiChatEntity entity, final String text) {
                Intrinsics.checkNotNullParameter(text, "text");
                if (isComplete) {
                    ChatAdapter chatAdapter8 = AiFragment.this.chatAdapter;
                    if (chatAdapter8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                        chatAdapter8 = null;
                    }
                    chatAdapter8.setStreaming(false);
                    return;
                }
                final AiFragment aiFragment2 = AiFragment.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<C09672, Unit>() { // from class: com.glasssutdio.wear.home.AiFragment$loadDataData$2$chatGptTextStream$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(AiFragment.C09672 c09672) {
                        invoke2(c09672);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(AiFragment.C09672 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        ChatAdapter chatAdapter9 = aiFragment2.chatAdapter;
                        if (chatAdapter9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                            chatAdapter9 = null;
                        }
                        chatAdapter9.addTheLastTextStream(text);
                    }
                });
            }
        });
        getAiChatViewModel().getUiState().observe(aiFragment, new AiFragment$sam$androidx_lifecycle_Observer$0(new Function1<AiChatViewModel.AiChatUI, Unit>() { // from class: com.glasssutdio.wear.home.AiFragment.loadDataData.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AiChatViewModel.AiChatUI aiChatUI) {
                invoke2(aiChatUI);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AiChatViewModel.AiChatUI aiChatUI) {
                ChatAdapter chatAdapter8 = null;
                if (AiFragment.this.getAiChatViewModel().getChatList().isEmpty()) {
                    FragmentAiBinding fragmentAiBinding2 = AiFragment.this.binding;
                    if (fragmentAiBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentAiBinding2 = null;
                    }
                    ViewKt.visible(fragmentAiBinding2.chatNoData);
                    FragmentAiBinding fragmentAiBinding3 = AiFragment.this.binding;
                    if (fragmentAiBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentAiBinding3 = null;
                    }
                    ViewKt.invisible(fragmentAiBinding3.ivEdit);
                } else {
                    FragmentAiBinding fragmentAiBinding4 = AiFragment.this.binding;
                    if (fragmentAiBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentAiBinding4 = null;
                    }
                    ViewKt.gone(fragmentAiBinding4.chatNoData);
                    FragmentAiBinding fragmentAiBinding5 = AiFragment.this.binding;
                    if (fragmentAiBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentAiBinding5 = null;
                    }
                    TextView tvEditFinish = fragmentAiBinding5.tvEditFinish;
                    Intrinsics.checkNotNullExpressionValue(tvEditFinish, "tvEditFinish");
                    if (tvEditFinish.getVisibility() != 0) {
                        FragmentAiBinding fragmentAiBinding6 = AiFragment.this.binding;
                        if (fragmentAiBinding6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            fragmentAiBinding6 = null;
                        }
                        ViewKt.visible(fragmentAiBinding6.ivEdit);
                    }
                }
                if (AiFragment.this.firstLoad) {
                    ChatAdapter chatAdapter9 = AiFragment.this.chatAdapter;
                    if (chatAdapter9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                        chatAdapter9 = null;
                    }
                    chatAdapter9.clearAllData();
                    ChatAdapter chatAdapter10 = AiFragment.this.chatAdapter;
                    if (chatAdapter10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                    } else {
                        chatAdapter8 = chatAdapter10;
                    }
                    chatAdapter8.addMessages(AiFragment.this.getAiChatViewModel().getChatList());
                    return;
                }
                if (AiFragment.this.getAiChatViewModel().getLoadMore().isEmpty()) {
                    return;
                }
                ChatAdapter chatAdapter11 = AiFragment.this.chatAdapter;
                if (chatAdapter11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                } else {
                    chatAdapter8 = chatAdapter11;
                }
                chatAdapter8.addMessages(AiFragment.this.getAiChatViewModel().getLoadMore());
            }
        }));
        getAiChatViewModel().getEmptyLD().observe(aiFragment, new AiFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: com.glasssutdio.wear.home.AiFragment.loadDataData.4
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
                FragmentAiBinding fragmentAiBinding2 = null;
                if (bool.booleanValue()) {
                    FragmentAiBinding fragmentAiBinding3 = AiFragment.this.binding;
                    if (fragmentAiBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentAiBinding3 = null;
                    }
                    ViewKt.visible(fragmentAiBinding3.chatNoData);
                    FragmentAiBinding fragmentAiBinding4 = AiFragment.this.binding;
                    if (fragmentAiBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentAiBinding2 = fragmentAiBinding4;
                    }
                    ViewKt.invisible(fragmentAiBinding2.ivEdit);
                    return;
                }
                FragmentAiBinding fragmentAiBinding5 = AiFragment.this.binding;
                if (fragmentAiBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentAiBinding5 = null;
                }
                ViewKt.gone(fragmentAiBinding5.chatNoData);
                FragmentAiBinding fragmentAiBinding6 = AiFragment.this.binding;
                if (fragmentAiBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentAiBinding6 = null;
                }
                TextView tvEditFinish = fragmentAiBinding6.tvEditFinish;
                Intrinsics.checkNotNullExpressionValue(tvEditFinish, "tvEditFinish");
                if (tvEditFinish.getVisibility() == 0) {
                    return;
                }
                FragmentAiBinding fragmentAiBinding7 = AiFragment.this.binding;
                if (fragmentAiBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentAiBinding2 = fragmentAiBinding7;
                }
                ViewKt.visible(fragmentAiBinding2.ivEdit);
            }
        }));
        playStateCallback();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadDataData$lambda$5$lambda$1(AiFragment this$0, View view) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Activity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        PermissionUtilKt.requestLocationPermission((FragmentActivity) activity, this$0.new LocationPermissionCallback());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadDataData$lambda$5$lambda$2(AiFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AiFragment aiFragment = this$0;
        FragmentActivity activity = aiFragment.getActivity();
        if (activity != null) {
            ArrayList<Pair> arrayList = new ArrayList();
            Intrinsics.checkNotNull(activity);
            Intent intent = new Intent(activity, (Class<?>) AILanguageActivity.class);
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
            aiFragment.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadDataData$lambda$5$lambda$3(AiFragment this$0, FragmentAiBinding this_run, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        this$0.isEdit = true;
        ChatAdapter chatAdapter = this$0.chatAdapter;
        ChatAdapter chatAdapter2 = null;
        if (chatAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
            chatAdapter = null;
        }
        chatAdapter.reSetSelectData();
        Function1<? super Boolean, Unit> function1 = this$0.historyEditChange;
        if (function1 != null) {
            function1.invoke(true);
        }
        ChatAdapter chatAdapter3 = this$0.chatAdapter;
        if (chatAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
        } else {
            chatAdapter2 = chatAdapter3;
        }
        chatAdapter2.clickEditView(true);
        ViewKt.visible(this_run.tvEditFinish);
        ViewKt.invisible(this_run.ivEdit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadDataData$lambda$5$lambda$4(AiFragment this$0, FragmentAiBinding this_run, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        this$0.isEdit = false;
        ChatAdapter chatAdapter = this$0.chatAdapter;
        ChatAdapter chatAdapter2 = null;
        if (chatAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
            chatAdapter = null;
        }
        chatAdapter.setSelectAll(false);
        ChatAdapter chatAdapter3 = this$0.chatAdapter;
        if (chatAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
            chatAdapter3 = null;
        }
        chatAdapter3.reSetSelectData();
        Function1<? super Boolean, Unit> function1 = this$0.historyEditChange;
        if (function1 != null) {
            function1.invoke(false);
        }
        ChatAdapter chatAdapter4 = this$0.chatAdapter;
        if (chatAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
        } else {
            chatAdapter2 = chatAdapter4;
        }
        chatAdapter2.clickEditView(false);
        ViewKt.gone(this_run.tvEditFinish);
        ViewKt.visible(this_run.ivEdit);
    }

    private final void playStateCallback() {
        SparkChainRecognizer.getInstance().setPlayStateCallback(new SparkChainRecognizer.PlayStateCallback() { // from class: com.glasssutdio.wear.home.AiFragment.playStateCallback.1
            @Override // com.glasssutdio.wear.ai.spark.SparkChainRecognizer.PlayStateCallback
            public void audioPlaying(String sid, String text) {
                Intrinsics.checkNotNullParameter(text, "text");
            }

            @Override // com.glasssutdio.wear.ai.spark.SparkChainRecognizer.PlayStateCallback
            public void startAudio(String sid, String text) {
                Intrinsics.checkNotNullParameter(text, "text");
                final AiFragment aiFragment = AiFragment.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<C09731, Unit>() { // from class: com.glasssutdio.wear.home.AiFragment$playStateCallback$1$startAudio$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(AiFragment.C09731 c09731) {
                        invoke2(c09731);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(AiFragment.C09731 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        ChatAdapter chatAdapter = aiFragment.chatAdapter;
                        ChatAdapter chatAdapter2 = null;
                        if (chatAdapter == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                            chatAdapter = null;
                        }
                        if (chatAdapter.getData().size() > 0) {
                            ChatAdapter chatAdapter3 = aiFragment.chatAdapter;
                            if (chatAdapter3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                                chatAdapter3 = null;
                            }
                            chatAdapter3.setStreaming(true);
                            ChatAdapter chatAdapter4 = aiFragment.chatAdapter;
                            if (chatAdapter4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                                chatAdapter4 = null;
                            }
                            chatAdapter4.getData().get(0).setPlay(true);
                            ChatAdapter chatAdapter5 = aiFragment.chatAdapter;
                            if (chatAdapter5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                            } else {
                                chatAdapter2 = chatAdapter5;
                            }
                            chatAdapter2.notifyItemChanged(0);
                        }
                    }
                });
            }

            @Override // com.glasssutdio.wear.ai.spark.SparkChainRecognizer.PlayStateCallback
            public void endAudio(String sid, boolean complete) {
                final AiFragment aiFragment = AiFragment.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<C09731, Unit>() { // from class: com.glasssutdio.wear.home.AiFragment$playStateCallback$1$endAudio$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(AiFragment.C09731 c09731) {
                        invoke2(c09731);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(AiFragment.C09731 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        ChatAdapter chatAdapter = aiFragment.chatAdapter;
                        ChatAdapter chatAdapter2 = null;
                        if (chatAdapter == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                            chatAdapter = null;
                        }
                        if (chatAdapter.getData().size() > 0) {
                            ChatAdapter chatAdapter3 = aiFragment.chatAdapter;
                            if (chatAdapter3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                                chatAdapter3 = null;
                            }
                            chatAdapter3.setStreaming(false);
                            ChatAdapter chatAdapter4 = aiFragment.chatAdapter;
                            if (chatAdapter4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                                chatAdapter4 = null;
                            }
                            chatAdapter4.getData().get(0).setPlay(false);
                            ChatAdapter chatAdapter5 = aiFragment.chatAdapter;
                            if (chatAdapter5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                            } else {
                                chatAdapter2 = chatAdapter5;
                            }
                            chatAdapter2.notifyItemChanged(0);
                        }
                    }
                });
            }
        });
    }

    private final void loadInitialData() {
        this.firstLoad = true;
        ChatAdapter chatAdapter = this.chatAdapter;
        if (chatAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
            chatAdapter = null;
        }
        chatAdapter.clearAllData();
        getAiChatViewModel().queryAiData((System.currentTimeMillis() / 1000) + 5, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadMoreData() {
        this.firstLoad = false;
        if (getAiChatViewModel().getChatList().isEmpty()) {
            return;
        }
        getAiChatViewModel().queryAiData(getAiChatViewModel().getChatList().get(getAiChatViewModel().getChatList().size() - 1).getChatTimeStamp(), false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(BusEvent messageEvent) {
        ChatAdapter chatAdapter;
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        ChatAdapter chatAdapter2 = null;
        ChatAdapter chatAdapter3 = null;
        FragmentAiBinding fragmentAiBinding = null;
        FragmentAiBinding fragmentAiBinding2 = null;
        FragmentAiBinding fragmentAiBinding3 = null;
        FragmentAiBinding fragmentAiBinding4 = null;
        if (messageEvent instanceof RefreshUserEvent) {
            if (((RefreshUserEvent) messageEvent).getRefreshType() != 1 || (chatAdapter = this.chatAdapter) == null) {
                return;
            }
            if (chatAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                chatAdapter = null;
            }
            ChatAdapter chatAdapter4 = this.chatAdapter;
            if (chatAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
            } else {
                chatAdapter3 = chatAdapter4;
            }
            chatAdapter.notifyItemRangeChanged(0, chatAdapter3.getData().size(), "avatar");
            return;
        }
        if (messageEvent instanceof BluetoothEvent) {
            if (!((BluetoothEvent) messageEvent).getConnect()) {
                if (!UserConfig.INSTANCE.getInstance().getDeviceBind()) {
                    FragmentAiBinding fragmentAiBinding5 = this.binding;
                    if (fragmentAiBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentAiBinding5 = null;
                    }
                    ViewKt.visible(fragmentAiBinding5.noBindDevice);
                    if (UserConfig.INSTANCE.getInstance().getDeviceAddress().length() == 0) {
                        FragmentAiBinding fragmentAiBinding6 = this.binding;
                        if (fragmentAiBinding6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            fragmentAiBinding = fragmentAiBinding6;
                        }
                        ViewKt.visible(fragmentAiBinding.noBindDevice);
                        return;
                    }
                    FragmentAiBinding fragmentAiBinding7 = this.binding;
                    if (fragmentAiBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentAiBinding2 = fragmentAiBinding7;
                    }
                    ViewKt.gone(fragmentAiBinding2.noBindDevice);
                    return;
                }
                FragmentAiBinding fragmentAiBinding8 = this.binding;
                if (fragmentAiBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentAiBinding3 = fragmentAiBinding8;
                }
                ViewKt.gone(fragmentAiBinding3.noBindDevice);
                return;
            }
            FragmentAiBinding fragmentAiBinding9 = this.binding;
            if (fragmentAiBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentAiBinding4 = fragmentAiBinding9;
            }
            ViewKt.gone(fragmentAiBinding4.noBindDevice);
            return;
        }
        if (messageEvent instanceof UpdateAiLanguageEvent) {
            if (UserConfig.INSTANCE.getInstance().getAiIsSystemLanguage()) {
                UserConfig companion = UserConfig.INSTANCE.getInstance();
                String language = Locale.getDefault().getLanguage();
                Intrinsics.checkNotNullExpressionValue(language, "getLanguage(...)");
                companion.setAiLanguageCode(language);
            }
            XLog.m137i(UserConfig.INSTANCE.getInstance().getAiLanguageCode());
            SparkChainRecognizer.getInstance().setTranslateTo(AiChatDepository.INSTANCE.getGetInstance().switchAsrLanguage(UserConfig.INSTANCE.getInstance().getAiLanguageCode()).getAppLanguage());
            SparkChainRecognizer.getInstance().setAiLanguage(AiChatDepository.INSTANCE.getGetInstance().switchAsrLanguage(UserConfig.INSTANCE.getInstance().getAiLanguageCode()).getAsrLanguage());
            XLog.m137i(UserConfig.INSTANCE.getInstance().getAiLanguageCode());
            XLog.m137i(GsonInstance.INSTANCE.getGson().toJson(AiChatDepository.INSTANCE.getGetInstance().switchAsrLanguage(UserConfig.INSTANCE.getInstance().getAiLanguageCode())));
            SparkChainRecognizer.getInstance().resetParams();
            return;
        }
        if (messageEvent instanceof AiHistoryEditEvent) {
            int type = ((AiHistoryEditEvent) messageEvent).getType();
            if (type == 1) {
                ChatAdapter chatAdapter5 = this.chatAdapter;
                if (chatAdapter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                } else {
                    chatAdapter2 = chatAdapter5;
                }
                chatAdapter2.checkedAll();
                return;
            }
            if (type != 2) {
                return;
            }
            ChatAdapter chatAdapter6 = this.chatAdapter;
            if (chatAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                chatAdapter6 = null;
            }
            if (chatAdapter6.getSelectData().isEmpty()) {
                String string = getString(C0775R.string.h_glass_323);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                GlobalKt.showToast$default(string, 0, 1, null);
                return;
            }
            CommonDialog.Builder builder = new CommonDialog.Builder();
            String string2 = getString(C0775R.string.h_glass_322);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            CommonDialog.Builder builderTitleMarginTop = builder.setTitle(string2).setContent("").titleMarginTop(GlobalKt.getDp((Number) 54));
            String string3 = getString(C0775R.string.h_glass_confirm);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            CommonDialog.Builder confirmMessage = builderTitleMarginTop.setConfirmMessage(string3);
            String string4 = getString(C0775R.string.h_glass_cancel);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
            final CommonDialog commonDialogBuild = confirmMessage.setCancelMessage(string4).build();
            commonDialogBuild.show(getChildFragmentManager(), "delete");
            commonDialogBuild.setOnCancelListener(new Function1<View, Unit>() { // from class: com.glasssutdio.wear.home.AiFragment.onMessageEvent.1
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
                    Intrinsics.checkNotNullParameter(it, "it");
                    commonDialogBuild.dismiss();
                }
            });
            commonDialogBuild.setOnConfirmListener(new Function1<View, Unit>() { // from class: com.glasssutdio.wear.home.AiFragment.onMessageEvent.2
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
                    Intrinsics.checkNotNullParameter(it, "it");
                    AiChatViewModel aiChatViewModel = AiFragment.this.getAiChatViewModel();
                    ChatAdapter chatAdapter7 = AiFragment.this.chatAdapter;
                    ChatAdapter chatAdapter8 = null;
                    if (chatAdapter7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                        chatAdapter7 = null;
                    }
                    aiChatViewModel.deleteAiHistoryList(chatAdapter7.getSelectData());
                    ChatAdapter chatAdapter9 = AiFragment.this.chatAdapter;
                    if (chatAdapter9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                        chatAdapter9 = null;
                    }
                    chatAdapter9.deleteSelectData();
                    FragmentAiBinding fragmentAiBinding10 = AiFragment.this.binding;
                    if (fragmentAiBinding10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentAiBinding10 = null;
                    }
                    fragmentAiBinding10.tvEditFinish.performClick();
                    ChatAdapter chatAdapter10 = AiFragment.this.chatAdapter;
                    if (chatAdapter10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                        chatAdapter10 = null;
                    }
                    if (chatAdapter10.getData().isEmpty()) {
                        FragmentAiBinding fragmentAiBinding11 = AiFragment.this.binding;
                        if (fragmentAiBinding11 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            fragmentAiBinding11 = null;
                        }
                        ViewKt.visible(fragmentAiBinding11.chatNoData);
                        FragmentAiBinding fragmentAiBinding12 = AiFragment.this.binding;
                        if (fragmentAiBinding12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            fragmentAiBinding12 = null;
                        }
                        ViewKt.invisible(fragmentAiBinding12.ivEdit);
                    } else {
                        FragmentAiBinding fragmentAiBinding13 = AiFragment.this.binding;
                        if (fragmentAiBinding13 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            fragmentAiBinding13 = null;
                        }
                        ViewKt.gone(fragmentAiBinding13.chatNoData);
                        FragmentAiBinding fragmentAiBinding14 = AiFragment.this.binding;
                        if (fragmentAiBinding14 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            fragmentAiBinding14 = null;
                        }
                        ViewKt.visible(fragmentAiBinding14.ivEdit);
                    }
                    ChatAdapter chatAdapter11 = AiFragment.this.chatAdapter;
                    if (chatAdapter11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                    } else {
                        chatAdapter8 = chatAdapter11;
                    }
                    if (chatAdapter8.getIsSelectAll()) {
                        EventBus.getDefault().post(new AiHistoryEditEvent(6));
                    }
                }
            });
            return;
        }
        if ((messageEvent instanceof EventType) && ((EventType) messageEvent).getType() == 20) {
            try {
                SparkChainRecognizer.getInstance().stopTTS();
                AiChatDepository.INSTANCE.getGetInstance().cleanRealTimeTTSQueue();
                AudioTrackManager.getInstance(GlassApplication.INSTANCE.getCONTEXT()).restartPlay();
                SSEHandler.INSTANCE.getInstance().stop();
                ThreadExtKt.ktxRunOnUi(this, new Function1<AiFragment, Unit>() { // from class: com.glasssutdio.wear.home.AiFragment.onMessageEvent.3
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(AiFragment aiFragment) {
                        invoke2(aiFragment);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(AiFragment ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        ChatAdapter chatAdapter7 = ktxRunOnUi.chatAdapter;
                        ChatAdapter chatAdapter8 = null;
                        if (chatAdapter7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                            chatAdapter7 = null;
                        }
                        if (chatAdapter7.getData().size() > 0) {
                            ChatAdapter chatAdapter9 = ktxRunOnUi.chatAdapter;
                            if (chatAdapter9 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                                chatAdapter9 = null;
                            }
                            chatAdapter9.setStreaming(false);
                            ChatAdapter chatAdapter10 = ktxRunOnUi.chatAdapter;
                            if (chatAdapter10 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                                chatAdapter10 = null;
                            }
                            chatAdapter10.getData().get(0).setPlay(false);
                            ChatAdapter chatAdapter11 = ktxRunOnUi.chatAdapter;
                            if (chatAdapter11 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                            } else {
                                chatAdapter8 = chatAdapter11;
                            }
                            chatAdapter8.notifyItemChanged(0);
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.glasssutdio.wear.home.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentAiBinding fragmentAiBinding = null;
        if (!UserConfig.INSTANCE.getInstance().getDeviceBind()) {
            FragmentAiBinding fragmentAiBinding2 = this.binding;
            if (fragmentAiBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentAiBinding = fragmentAiBinding2;
            }
            ViewKt.visible(fragmentAiBinding.noBindDevice);
        } else {
            FragmentAiBinding fragmentAiBinding3 = this.binding;
            if (fragmentAiBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentAiBinding = fragmentAiBinding3;
            }
            ViewKt.gone(fragmentAiBinding.noBindDevice);
        }
        try {
            playStateCallback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.glasssutdio.wear.home.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* compiled from: AiFragment.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, m607d2 = {"Lcom/glasssutdio/wear/home/AiFragment$Companion;", "", "()V", "newInstance", "Lcom/glasssutdio/wear/home/AiFragment;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AiFragment newInstance() {
            return new AiFragment();
        }
    }
}
