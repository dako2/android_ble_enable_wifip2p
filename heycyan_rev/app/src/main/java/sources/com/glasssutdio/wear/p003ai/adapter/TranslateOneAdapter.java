package com.glasssutdio.wear.p003ai.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.databinding.ItemTranslateLeftBinding;
import com.glasssutdio.wear.databinding.ItemTranslateRightBinding;
import com.glasssutdio.wear.p003ai.adapter.ChatAdapter;
import com.glasssutdio.wear.p003ai.bean.TranslateOneModel;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TranslateOneAdapter.kt */
@Metadata(m606d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 $2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003$%&B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0006J\u000e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015J\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\b\u0010\u0017\u001a\u00020\u000eH\u0016J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\u0018\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J&\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0005H\u0016J\u0018\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u000eH\u0016J\u000e\u0010!\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eJU\u0010\"\u001a\u00020\u00102M\u0010#\u001aI\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\bR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000RU\u0010\u0007\u001aI\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, m607d2 = {"Lcom/glasssutdio/wear/ai/adapter/TranslateOneAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "()V", "innerMessages", "", "Lcom/glasssutdio/wear/ai/bean/TranslateOneModel;", "onItemPlayClick", "Lkotlin/Function3;", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "view", "model", "", "position", "", "addTheLast", "it", "addTheLastTextStream", "data", "", "getData", "getItemCount", "getItemViewType", "onBindViewHolder", "holder", "payloads", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "refreshPlayingStatus", "setOnItemPlayClickListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Companion", "LeftViewViewHolder", "RightViewHolder", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class TranslateOneAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_LEFT = 0;
    public static final int VIEW_TYPE_RIGHT = 1;
    private final List<TranslateOneModel> innerMessages = new ArrayList();
    private Function3<? super View, ? super TranslateOneModel, ? super Integer, Unit> onItemPlayClick;

    public final void refreshPlayingStatus(int position) {
        notifyItemChanged(position, "playing");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        int mItemType = this.innerMessages.get(position).getMItemType();
        return (mItemType == 0 || mItemType != 1) ? 0 : 1;
    }

    public final List<TranslateOneModel> getData() {
        return this.innerMessages;
    }

    public final void addTheLast(TranslateOneModel it) {
        Intrinsics.checkNotNullParameter(it, "it");
        this.innerMessages.add(it);
        notifyItemInserted(this.innerMessages.size() - 1);
    }

    public final void addTheLastTextStream(String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (this.innerMessages.isEmpty()) {
            return;
        }
        List<TranslateOneModel> list = this.innerMessages;
        list.set(list.size() - 1, TranslateOneModel.copy$default(this.innerMessages.get(r2.size() - 1), 0, data, false, false, null, null, 0, 125, null));
        notifyItemChanged(this.innerMessages.size() - 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (viewType == 0) {
            ItemTranslateLeftBinding itemTranslateLeftBindingInflate = ItemTranslateLeftBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            Intrinsics.checkNotNullExpressionValue(itemTranslateLeftBindingInflate, "inflate(...)");
            return new LeftViewViewHolder(this, itemTranslateLeftBindingInflate);
        }
        if (viewType == 1) {
            ItemTranslateRightBinding itemTranslateRightBindingInflate = ItemTranslateRightBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            Intrinsics.checkNotNullExpressionValue(itemTranslateRightBindingInflate, "inflate(...)");
            return new RightViewHolder(this, itemTranslateRightBindingInflate);
        }
        throw new IllegalArgumentException("Invalid view type");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        TranslateOneModel translateOneModel = this.innerMessages.get(position);
        if (holder instanceof LeftViewViewHolder) {
            ((LeftViewViewHolder) holder).bind(translateOneModel, position);
        } else if (holder instanceof RightViewHolder) {
            ((RightViewHolder) holder).bind(translateOneModel, position);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(payloads, "payloads");
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
            return;
        }
        TranslateOneModel translateOneModel = this.innerMessages.get(position);
        if (holder instanceof LeftViewViewHolder) {
            if (payloads.contains("playing")) {
                ItemTranslateLeftBinding binding = ((LeftViewViewHolder) holder).getBinding();
                if (translateOneModel.isBottom()) {
                    binding.ivPlay.setImageResource(translateOneModel.isPlaying() ? C0775R.mipmap.iv_pause_red : C0775R.mipmap.iv_play_red);
                    return;
                } else {
                    binding.ivPlay.setImageResource(translateOneModel.isPlaying() ? C0775R.mipmap.iv_pause_blue : C0775R.mipmap.iv_play_blue);
                    return;
                }
            }
            return;
        }
        if (holder instanceof RightViewHolder) {
            if (payloads.contains("playing")) {
                ItemTranslateRightBinding binding2 = ((RightViewHolder) holder).getBinding();
                if (translateOneModel.isBottom()) {
                    binding2.ivPlay.setImageResource(translateOneModel.isPlaying() ? C0775R.mipmap.iv_pause_blue : C0775R.mipmap.iv_play_blue);
                    return;
                } else {
                    binding2.ivPlay.setImageResource(translateOneModel.isPlaying() ? C0775R.mipmap.iv_pause_red : C0775R.mipmap.iv_play_red);
                    return;
                }
            }
            return;
        }
        boolean z = holder instanceof ChatAdapter.VideoMessageViewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.innerMessages.size();
    }

    /* compiled from: TranslateOneAdapter.kt */
    @Metadata(m606d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, m607d2 = {"Lcom/glasssutdio/wear/ai/adapter/TranslateOneAdapter$LeftViewViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/glasssutdio/wear/databinding/ItemTranslateLeftBinding;", "(Lcom/glasssutdio/wear/ai/adapter/TranslateOneAdapter;Lcom/glasssutdio/wear/databinding/ItemTranslateLeftBinding;)V", "getBinding", "()Lcom/glasssutdio/wear/databinding/ItemTranslateLeftBinding;", "bind", "", "model", "Lcom/glasssutdio/wear/ai/bean/TranslateOneModel;", "position", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class LeftViewViewHolder extends RecyclerView.ViewHolder {
        private final ItemTranslateLeftBinding binding;
        final /* synthetic */ TranslateOneAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LeftViewViewHolder(TranslateOneAdapter translateOneAdapter, ItemTranslateLeftBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = translateOneAdapter;
            this.binding = binding;
        }

        public final ItemTranslateLeftBinding getBinding() {
            return this.binding;
        }

        public final void bind(final TranslateOneModel model, final int position) {
            Intrinsics.checkNotNullParameter(model, "model");
            ItemTranslateLeftBinding itemTranslateLeftBinding = this.binding;
            final TranslateOneAdapter translateOneAdapter = this.this$0;
            itemTranslateLeftBinding.tvContent.setText(model.getContent());
            if (model.isBottom()) {
                itemTranslateLeftBinding.ivPlay.setImageResource(model.isPlaying() ? C0775R.mipmap.iv_pause_red : C0775R.mipmap.iv_play_red);
                itemTranslateLeftBinding.tvContent.setBackgroundResource(C0775R.drawable.bg_red_6_shape);
            } else {
                itemTranslateLeftBinding.ivPlay.setImageResource(model.isPlaying() ? C0775R.mipmap.iv_pause_blue : C0775R.mipmap.iv_play_blue);
                itemTranslateLeftBinding.tvContent.setBackgroundResource(C0775R.drawable.bg_blue_6_shape);
            }
            ImageView ivPlay = itemTranslateLeftBinding.ivPlay;
            Intrinsics.checkNotNullExpressionValue(ivPlay, "ivPlay");
            ViewKt.click$default(ivPlay, 0L, new Function1<View, Unit>() { // from class: com.glasssutdio.wear.ai.adapter.TranslateOneAdapter$LeftViewViewHolder$bind$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    Function3 function3 = translateOneAdapter.onItemPlayClick;
                    if (function3 != null) {
                        function3.invoke(it, model, Integer.valueOf(position));
                    }
                }
            }, 1, null);
        }
    }

    /* compiled from: TranslateOneAdapter.kt */
    @Metadata(m606d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, m607d2 = {"Lcom/glasssutdio/wear/ai/adapter/TranslateOneAdapter$RightViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/glasssutdio/wear/databinding/ItemTranslateRightBinding;", "(Lcom/glasssutdio/wear/ai/adapter/TranslateOneAdapter;Lcom/glasssutdio/wear/databinding/ItemTranslateRightBinding;)V", "getBinding", "()Lcom/glasssutdio/wear/databinding/ItemTranslateRightBinding;", "bind", "", "model", "Lcom/glasssutdio/wear/ai/bean/TranslateOneModel;", "position", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class RightViewHolder extends RecyclerView.ViewHolder {
        private final ItemTranslateRightBinding binding;
        final /* synthetic */ TranslateOneAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RightViewHolder(TranslateOneAdapter translateOneAdapter, ItemTranslateRightBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = translateOneAdapter;
            this.binding = binding;
        }

        public final ItemTranslateRightBinding getBinding() {
            return this.binding;
        }

        public final void bind(final TranslateOneModel model, final int position) {
            Intrinsics.checkNotNullParameter(model, "model");
            ItemTranslateRightBinding itemTranslateRightBinding = this.binding;
            final TranslateOneAdapter translateOneAdapter = this.this$0;
            itemTranslateRightBinding.tvContent.setText(model.getContent());
            if (model.isBottom()) {
                itemTranslateRightBinding.ivPlay.setImageResource(model.isPlaying() ? C0775R.mipmap.iv_pause_blue : C0775R.mipmap.iv_play_blue);
                itemTranslateRightBinding.tvContent.setBackgroundResource(C0775R.drawable.bg_blue_6_shape);
            } else {
                itemTranslateRightBinding.ivPlay.setImageResource(model.isPlaying() ? C0775R.mipmap.iv_pause_red : C0775R.mipmap.iv_play_red);
                itemTranslateRightBinding.tvContent.setBackgroundResource(C0775R.drawable.bg_red_6_shape);
            }
            ImageView ivPlay = itemTranslateRightBinding.ivPlay;
            Intrinsics.checkNotNullExpressionValue(ivPlay, "ivPlay");
            ViewKt.click$default(ivPlay, 0L, new Function1<View, Unit>() { // from class: com.glasssutdio.wear.ai.adapter.TranslateOneAdapter$RightViewHolder$bind$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    Function3 function3 = translateOneAdapter.onItemPlayClick;
                    if (function3 != null) {
                        function3.invoke(it, model, Integer.valueOf(position));
                    }
                }
            }, 1, null);
        }
    }

    public final void setOnItemPlayClickListener(Function3<? super View, ? super TranslateOneModel, ? super Integer, Unit> listener) {
        this.onItemPlayClick = listener;
    }
}
