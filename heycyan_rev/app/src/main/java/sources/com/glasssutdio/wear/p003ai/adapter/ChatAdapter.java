package com.glasssutdio.wear.p003ai.adapter;

import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.utils.CommonUtils;
import com.glasssutdio.wear.all.utils.image.ImageExtKt;
import com.glasssutdio.wear.database.entity.AiChatEntity;
import com.glasssutdio.wear.depository.bean.ChatEnumAction;
import com.glasssutdio.wear.p003ai.adapter.ChatAdapter;
import com.glasssutdio.wear.p003ai.bean.ChatMessage;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okio.Utf8;

/* compiled from: ChatAdapter.kt */
@Metadata(m606d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 V2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0005VWXYZB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0014\u0010+\u001a\u00020\u001e2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\b0-J\u000e\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u000200J\u000e\u00101\u001a\u00020\u001e2\u0006\u00102\u001a\u000203JT\u00104\u001a\u00020\u001e2\u0006\u00105\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001c2\n\b\u0002\u00106\u001a\u0004\u0018\u0001072\n\b\u0002\u00108\u001a\u0004\u0018\u0001072\n\b\u0002\u00109\u001a\u0004\u0018\u0001072\n\b\u0002\u0010:\u001a\u0004\u0018\u0001072\n\b\u0002\u0010;\u001a\u0004\u0018\u000107H\u0002J\u0006\u0010<\u001a\u00020\u001eJ\u0006\u0010=\u001a\u00020\u001eJ\u000e\u0010>\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u001cJ\u000e\u0010?\u001a\u00020\u001e2\u0006\u0010@\u001a\u00020\fJ\u0010\u0010A\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002J\u0010\u0010B\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002J\u0006\u0010C\u001a\u00020\u001eJ\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\b0\nJ\b\u0010E\u001a\u00020\u001cH\u0016J\u0010\u0010F\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0016J\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\b0-J\u0018\u0010H\u001a\u00020\u001e2\u0006\u0010I\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001cH\u0016J&\u0010H\u001a\u00020\u001e2\u0006\u0010I\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001c2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020K0\nH\u0016J\u0018\u0010L\u001a\u00020\u00022\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020\u001cH\u0016J\u0006\u0010P\u001a\u00020\u001eJ@\u0010Q\u001a\u00020\u001e28\u0010R\u001a4\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u0017J@\u0010S\u001a\u00020\u001e28\u0010R\u001a4\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u001e\u0018\u00010\u0017J@\u0010T\u001a\u00020\u001e28\u0010R\u001a4\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u001e\u0018\u00010\u0017J@\u0010U\u001a\u00020\u001e28\u0010R\u001a4\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u0017R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R@\u0010\u0016\u001a4\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R@\u0010\u001f\u001a4\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u001e\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R@\u0010!\u001a4\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R@\u0010\"\u001a4\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u001e\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\r\"\u0004\b'\u0010\u000fR\u0014\u0010(\u001a\u00020\u001cX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*¨\u0006["}, m607d2 = {"Lcom/glasssutdio/wear/ai/adapter/ChatAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_onImageClick", "Landroidx/lifecycle/MutableLiveData;", "Lcom/glasssutdio/wear/ai/bean/ChatMessage;", "innerMessages", "", "isSelectAll", "", "()Z", "setSelectAll", "(Z)V", "isStreaming", "setStreaming", "onImageClick", "Landroidx/lifecycle/LiveData;", "getOnImageClick", "()Landroidx/lifecycle/LiveData;", "onItemClick", "Lkotlin/Function2;", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "view", "", "position", "", "onLikeClick", "model", "onSoundClick", "onUnLikeClick", "requestOptions", "Lcom/bumptech/glide/request/RequestOptions;", "supportStreaming", "getSupportStreaming", "setSupportStreaming", "width", "getWidth", "()I", "addMessages", "newMessages", "", "addTheLast", "it", "Lcom/glasssutdio/wear/database/entity/AiChatEntity;", "addTheLastTextStream", "newText", "", "bindCommon", "chatMessage", "ivChecked", "Landroid/widget/ImageView;", "ivCopy", "ivLike", "ivUnlike", "ivAvatar", "checkedAll", "clearAllData", "clickChecked", "clickEditView", "isEdit", "clickLike", "clickUnlike", "deleteSelectData", "getData", "getItemCount", "getItemViewType", "getSelectData", "onBindViewHolder", "holder", "payloads", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "reSetSelectData", "setOnItemClickListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setOnLikeClickListener", "setOnUnLikeClickListener", "setSoundClickListener", "Companion", "EndlessRecyclerViewScrollListener", "ImageMessageViewHolder", "TextMessageViewHolder", "VideoMessageViewHolder", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_IMAGE = 2;
    private static final int VIEW_TYPE_TEXT = 1;
    private static final int VIEW_TYPE_VIDEO = 3;
    private final MutableLiveData<ChatMessage> _onImageClick;
    private final Context context;
    private final List<ChatMessage> innerMessages;
    private boolean isSelectAll;
    private boolean isStreaming;
    private Function2<? super View, ? super Integer, Unit> onItemClick;
    private Function2<? super Integer, ? super ChatMessage, Unit> onLikeClick;
    private Function2<? super View, ? super Integer, Unit> onSoundClick;
    private Function2<? super Integer, ? super ChatMessage, Unit> onUnLikeClick;
    private final RequestOptions requestOptions;
    private boolean supportStreaming;
    private final int width;

    public ChatAdapter(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.innerMessages = new ArrayList();
        this.width = 300;
        RequestOptions requestOptionsTransform = new RequestOptions().placeholder(R.color.darker_gray).transform(new RoundedCorners(GlobalKt.getDp((Number) 10)));
        Intrinsics.checkNotNullExpressionValue(requestOptionsTransform, "transform(...)");
        this.requestOptions = requestOptionsTransform;
        this._onImageClick = new MutableLiveData<>();
    }

    public final int getWidth() {
        return this.width;
    }

    /* renamed from: isSelectAll, reason: from getter */
    public final boolean getIsSelectAll() {
        return this.isSelectAll;
    }

    public final void setSelectAll(boolean z) {
        this.isSelectAll = z;
    }

    /* renamed from: isStreaming, reason: from getter */
    public final boolean getIsStreaming() {
        return this.isStreaming;
    }

    public final void setStreaming(boolean z) {
        this.isStreaming = z;
    }

    public final boolean getSupportStreaming() {
        return this.supportStreaming;
    }

    public final void setSupportStreaming(boolean z) {
        this.supportStreaming = z;
    }

    public final void checkedAll() {
        this.isSelectAll = !this.isSelectAll;
        int i = 0;
        for (Object obj : this.innerMessages) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ((ChatMessage) obj).setChecked(this.isSelectAll);
            i = i2;
        }
        notifyDataSetChanged();
    }

    private final void clickLike(int position) {
        this.innerMessages.get(position).setLike(!this.innerMessages.get(position).isLike());
        if (this.innerMessages.get(position).isLike()) {
            this.innerMessages.get(position).setUnLike(false);
        }
        Function2<? super Integer, ? super ChatMessage, Unit> function2 = this.onLikeClick;
        if (function2 != null) {
            function2.invoke(Integer.valueOf(position), this.innerMessages.get(position));
        }
        XLog.m137i("喜欢：" + position + ",like:" + this.innerMessages.get(position).isLike() + ",unlike:" + this.innerMessages.get(position).isUnLike());
        notifyItemChanged(position, "isLike");
    }

    private final void clickUnlike(int position) {
        this.innerMessages.get(position).setUnLike(!this.innerMessages.get(position).isUnLike());
        if (this.innerMessages.get(position).isUnLike()) {
            this.innerMessages.get(position).setLike(false);
        }
        Function2<? super Integer, ? super ChatMessage, Unit> function2 = this.onUnLikeClick;
        if (function2 != null) {
            function2.invoke(Integer.valueOf(position), this.innerMessages.get(position));
        }
        XLog.m137i("不喜欢：" + position + ",like:" + this.innerMessages.get(position).isLike() + ",unlike:" + this.innerMessages.get(position).isUnLike());
        notifyItemChanged(position, "isLike");
    }

    public final void clickChecked(int position) {
        this.innerMessages.get(position).setChecked(!this.innerMessages.get(position).isChecked());
        notifyItemChanged(position, "checked");
    }

    public final void clickEditView(boolean isEdit) {
        Iterator<T> it = this.innerMessages.iterator();
        while (it.hasNext()) {
            ((ChatMessage) it.next()).setEdit(isEdit);
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        int messageType = this.innerMessages.get(position).getMessageType();
        if (messageType == 1) {
            return 1;
        }
        int i = 2;
        if (messageType != 2) {
            i = 3;
            if (messageType != 3) {
                return 1;
            }
        }
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (viewType == 1) {
            View viewInflate = LayoutInflater.from(parent.getContext()).inflate(C0775R.layout.item_chat_text, parent, false);
            Intrinsics.checkNotNull(viewInflate);
            return new TextMessageViewHolder(this, viewInflate);
        }
        if (viewType == 2) {
            View viewInflate2 = LayoutInflater.from(parent.getContext()).inflate(C0775R.layout.item_chat_image, parent, false);
            Intrinsics.checkNotNull(viewInflate2);
            return new ImageMessageViewHolder(this, viewInflate2);
        }
        if (viewType == 3) {
            View viewInflate3 = LayoutInflater.from(parent.getContext()).inflate(C0775R.layout.item_chat_video, parent, false);
            Intrinsics.checkNotNull(viewInflate3);
            return new VideoMessageViewHolder(this, viewInflate3);
        }
        throw new IllegalArgumentException("Invalid view type");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final ChatMessage chatMessage = this.innerMessages.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.ai.adapter.ChatAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChatAdapter.onBindViewHolder$lambda$2(holder, this, view);
            }
        });
        if (holder instanceof TextMessageViewHolder) {
            TextMessageViewHolder textMessageViewHolder = (TextMessageViewHolder) holder;
            bindCommon(chatMessage, textMessageViewHolder.getBindingAdapterPosition(), textMessageViewHolder.getIvChecked(), textMessageViewHolder.getIvCopy(), textMessageViewHolder.getIvLike(), textMessageViewHolder.getIvUnlike(), textMessageViewHolder.getPhoto());
            textMessageViewHolder.getIvCopy().setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.ai.adapter.ChatAdapter$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ChatAdapter.onBindViewHolder$lambda$3(position, chatMessage, holder, view);
                }
            });
            textMessageViewHolder.getIvLike().setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.ai.adapter.ChatAdapter$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ChatAdapter.onBindViewHolder$lambda$4(position, this, holder, view);
                }
            });
            textMessageViewHolder.getIvUnlike().setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.ai.adapter.ChatAdapter$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ChatAdapter.onBindViewHolder$lambda$5(position, this, holder, view);
                }
            });
            textMessageViewHolder.bind(chatMessage, position == 0 && this.isStreaming);
            if (position == 0 && !chatMessage.isMe()) {
                if (chatMessage.getPlay()) {
                    ViewKt.visible(textMessageViewHolder.getIvSound());
                    textMessageViewHolder.getIvSound().setImageResource(C0775R.mipmap.ai_sound);
                    textMessageViewHolder.getIvSound().setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.ai.adapter.ChatAdapter$$ExternalSyntheticLambda4
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ChatAdapter.onBindViewHolder$lambda$6(holder, this, view);
                        }
                    });
                    return;
                }
                ViewKt.gone(textMessageViewHolder.getIvSound());
                return;
            }
            ViewKt.gone(textMessageViewHolder.getIvSound());
            return;
        }
        if (holder instanceof ImageMessageViewHolder) {
            ImageMessageViewHolder imageMessageViewHolder = (ImageMessageViewHolder) holder;
            bindCommon$default(this, chatMessage, position, imageMessageViewHolder.getIvChecked(), null, null, null, imageMessageViewHolder.getPhoto(), 56, null);
            imageMessageViewHolder.bind(chatMessage);
        } else if (holder instanceof VideoMessageViewHolder) {
            VideoMessageViewHolder videoMessageViewHolder = (VideoMessageViewHolder) holder;
            bindCommon$default(this, chatMessage, position, videoMessageViewHolder.getIvChecked(), null, null, null, null, EMachine.EM_M32C, null);
            videoMessageViewHolder.bind(chatMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$2(RecyclerView.ViewHolder holder, ChatAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.m137i("itemClick:" + holder.getBindingAdapterPosition());
        Function2<? super View, ? super Integer, Unit> function2 = this$0.onItemClick;
        if (function2 != null) {
            Intrinsics.checkNotNull(view);
            function2.invoke(view, Integer.valueOf(holder.getBindingAdapterPosition()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$3(int i, ChatMessage chatMessage, RecyclerView.ViewHolder holder, View view) {
        Intrinsics.checkNotNullParameter(chatMessage, "$chatMessage");
        Intrinsics.checkNotNullParameter(holder, "$holder");
        XLog.m137i("点击复制text：" + i);
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        String message = chatMessage.getMessage();
        Context context = ((TextMessageViewHolder) holder).getIvCopy().getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        commonUtils.copyClip(message, context);
        GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.h_glass_324), 0, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$4(int i, ChatAdapter this$0, RecyclerView.ViewHolder holder, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(holder, "$holder");
        XLog.m137i("点击喜欢text：" + i);
        this$0.clickLike(((TextMessageViewHolder) holder).getBindingAdapterPosition());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$5(int i, ChatAdapter this$0, RecyclerView.ViewHolder holder, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(holder, "$holder");
        XLog.m137i("点击拉黑text：" + i);
        this$0.clickUnlike(((TextMessageViewHolder) holder).getBindingAdapterPosition());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$6(RecyclerView.ViewHolder holder, ChatAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextMessageViewHolder textMessageViewHolder = (TextMessageViewHolder) holder;
        ViewKt.gone(textMessageViewHolder.getIvSound());
        textMessageViewHolder.getIvSound().setImageResource(C0775R.mipmap.ai_not_sound);
        Function2<? super View, ? super Integer, Unit> function2 = this$0.onSoundClick;
        if (function2 != null) {
            function2.invoke(textMessageViewHolder.getIvSound(), Integer.valueOf(textMessageViewHolder.getBindingAdapterPosition()));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        Object next;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(payloads, "payloads");
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
            return;
        }
        ChatMessage chatMessage = this.innerMessages.get(position);
        if (holder instanceof TextMessageViewHolder) {
            if (payloads.contains("isLike")) {
                XLog.m137i("更新喜欢状态：like:" + chatMessage.isLike() + ",unlike:" + chatMessage.isUnLike());
                TextMessageViewHolder textMessageViewHolder = (TextMessageViewHolder) holder;
                textMessageViewHolder.getIvLike().setSelected(chatMessage.isLike());
                textMessageViewHolder.getIvUnlike().setSelected(chatMessage.isUnLike());
                return;
            }
            if (payloads.contains("checked")) {
                ((TextMessageViewHolder) holder).getIvChecked().setSelected(chatMessage.isChecked());
                return;
            }
            if (payloads.contains("avatar")) {
                if (chatMessage.isMe()) {
                    ImageExtKt.displayAvatar(((TextMessageViewHolder) holder).getPhoto(), CommonUtils.INSTANCE.getAvatarUrl(), new Function1<RequestBuilder<?>, RequestBuilder<?>>() { // from class: com.glasssutdio.wear.ai.adapter.ChatAdapter.onBindViewHolder.6
                        @Override // kotlin.jvm.functions.Function1
                        public final RequestBuilder<?> invoke(RequestBuilder<?> displayAvatar) {
                            Intrinsics.checkNotNullParameter(displayAvatar, "$this$displayAvatar");
                            Cloneable cloneableTransform = displayAvatar.override(GlobalKt.getDp((Number) 50), GlobalKt.getDp((Number) 50)).transform(new CenterCrop(), new RoundedCorners(100));
                            Intrinsics.checkNotNullExpressionValue(cloneableTransform, "transform(...)");
                            return (RequestBuilder) cloneableTransform;
                        }
                    });
                    return;
                } else {
                    ((TextMessageViewHolder) holder).getPhoto().setImageResource(C0775R.mipmap.chat_ai_photo);
                    return;
                }
            }
            Iterator<T> it = payloads.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                } else {
                    next = it.next();
                    if (next instanceof String) {
                        break;
                    }
                }
            }
            if ((next instanceof String ? (String) next : null) != null) {
                if (!chatMessage.getStreamComplete()) {
                    TextStreamer.append$default(((TextMessageViewHolder) holder).getStreamer(), chatMessage, null, 2, null);
                    return;
                } else {
                    ((TextMessageViewHolder) holder).getStreamer().display(chatMessage);
                    return;
                }
            }
            TextMessageViewHolder textMessageViewHolder2 = (TextMessageViewHolder) holder;
            textMessageViewHolder2.getStreamer().reset();
            textMessageViewHolder2.getTextMessage().setText(chatMessage.getMessage());
            return;
        }
        if (holder instanceof ImageMessageViewHolder) {
            if (payloads.contains("checked")) {
                ((ImageMessageViewHolder) holder).getIvChecked().setSelected(chatMessage.isChecked());
                return;
            } else {
                if (payloads.contains("avatar")) {
                    if (chatMessage.isMe()) {
                        ImageExtKt.displayAvatar(((ImageMessageViewHolder) holder).getPhoto(), CommonUtils.INSTANCE.getAvatarUrl(), new Function1<RequestBuilder<?>, RequestBuilder<?>>() { // from class: com.glasssutdio.wear.ai.adapter.ChatAdapter.onBindViewHolder.7
                            @Override // kotlin.jvm.functions.Function1
                            public final RequestBuilder<?> invoke(RequestBuilder<?> displayAvatar) {
                                Intrinsics.checkNotNullParameter(displayAvatar, "$this$displayAvatar");
                                Cloneable cloneableTransform = displayAvatar.override(GlobalKt.getDp((Number) 50), GlobalKt.getDp((Number) 50)).transform(new CenterCrop(), new RoundedCorners(100));
                                Intrinsics.checkNotNullExpressionValue(cloneableTransform, "transform(...)");
                                return (RequestBuilder) cloneableTransform;
                            }
                        });
                        return;
                    } else {
                        ((ImageMessageViewHolder) holder).getPhoto().setImageResource(C0775R.mipmap.chat_ai_photo);
                        return;
                    }
                }
                return;
            }
        }
        boolean z = holder instanceof VideoMessageViewHolder;
    }

    static /* synthetic */ void bindCommon$default(ChatAdapter chatAdapter, ChatMessage chatMessage, int i, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, int i2, Object obj) {
        chatAdapter.bindCommon(chatMessage, i, (i2 & 4) != 0 ? null : imageView, (i2 & 8) != 0 ? null : imageView2, (i2 & 16) != 0 ? null : imageView3, (i2 & 32) != 0 ? null : imageView4, (i2 & 64) != 0 ? null : imageView5);
    }

    private final void bindCommon(ChatMessage chatMessage, int position, ImageView ivChecked, ImageView ivCopy, ImageView ivLike, ImageView ivUnlike, ImageView ivAvatar) {
        if (ivChecked != null) {
            ViewKt.goneOrVisible(ivChecked, chatMessage.isEdit());
        }
        if (ivChecked != null) {
            ivChecked.setSelected(chatMessage.isChecked());
        }
        if (ivLike != null) {
            ivLike.setSelected(chatMessage.isLike());
        }
        if (ivUnlike != null) {
            ivUnlike.setSelected(chatMessage.isUnLike());
        }
        if (chatMessage.isMe()) {
            if (ivAvatar != null) {
                ImageExtKt.displayAvatar(ivAvatar, CommonUtils.INSTANCE.getAvatarUrl(), new Function1<RequestBuilder<?>, RequestBuilder<?>>() { // from class: com.glasssutdio.wear.ai.adapter.ChatAdapter.bindCommon.1
                    @Override // kotlin.jvm.functions.Function1
                    public final RequestBuilder<?> invoke(RequestBuilder<?> displayAvatar) {
                        Intrinsics.checkNotNullParameter(displayAvatar, "$this$displayAvatar");
                        Cloneable cloneableTransform = displayAvatar.override(GlobalKt.getDp((Number) 50), GlobalKt.getDp((Number) 50)).transform(new CenterCrop(), new RoundedCorners(100));
                        Intrinsics.checkNotNullExpressionValue(cloneableTransform, "transform(...)");
                        return (RequestBuilder) cloneableTransform;
                    }
                });
            }
        } else if (ivAvatar != null) {
            ivAvatar.setImageResource(C0775R.mipmap.chat_ai_photo);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.innerMessages.size();
    }

    public final void clearAllData() {
        this.innerMessages.clear();
        notifyDataSetChanged();
    }

    public final List<ChatMessage> getSelectData() {
        ArrayList arrayList = new ArrayList();
        for (ChatMessage chatMessage : this.innerMessages) {
            if (chatMessage.isChecked()) {
                arrayList.add(chatMessage);
            }
        }
        return arrayList;
    }

    public final void reSetSelectData() {
        Iterator<T> it = this.innerMessages.iterator();
        while (it.hasNext()) {
            ((ChatMessage) it.next()).setChecked(false);
        }
    }

    public final void deleteSelectData() {
        this.isSelectAll = !this.isSelectAll;
        Iterator<T> it = getSelectData().iterator();
        while (it.hasNext()) {
            this.innerMessages.remove((ChatMessage) it.next());
        }
        notifyDataSetChanged();
    }

    public final void addTheLast(AiChatEntity it) {
        Intrinsics.checkNotNullParameter(it, "it");
        this.supportStreaming = false;
        String localFilePath = it.getLocalFilePath();
        if (it.getLocalFilePath().length() == 0) {
            localFilePath = it.getFilePath();
        }
        this.innerMessages.add(0, new ChatMessage(it.getUid(), it.getContent(), localFilePath, Intrinsics.areEqual(it.getRole(), ChatEnumAction.USER), it.getChatType(), it.getChatTimestamp(), false, false, false, false, false, false, false, null, 0, false, 65472, null));
        notifyItemInserted(0);
    }

    public final void addTheLastTextStream(String newText) {
        Intrinsics.checkNotNullParameter(newText, "newText");
        this.supportStreaming = true;
        if (this.innerMessages.isEmpty()) {
            return;
        }
        ChatMessage chatMessage = this.innerMessages.get(0);
        this.innerMessages.set(0, chatMessage.copy((Utf8.REPLACEMENT_CODE_POINT & 1) != 0 ? chatMessage.uid : 0L, (Utf8.REPLACEMENT_CODE_POINT & 2) != 0 ? chatMessage.message : chatMessage.getMessage() + newText, (Utf8.REPLACEMENT_CODE_POINT & 4) != 0 ? chatMessage.imagePath : null, (Utf8.REPLACEMENT_CODE_POINT & 8) != 0 ? chatMessage.isMe : false, (Utf8.REPLACEMENT_CODE_POINT & 16) != 0 ? chatMessage.messageType : 0, (Utf8.REPLACEMENT_CODE_POINT & 32) != 0 ? chatMessage.chatTimeStamp : 0L, (Utf8.REPLACEMENT_CODE_POINT & 64) != 0 ? chatMessage.isChecked : false, (Utf8.REPLACEMENT_CODE_POINT & 128) != 0 ? chatMessage.isLike : false, (Utf8.REPLACEMENT_CODE_POINT & 256) != 0 ? chatMessage.isUnLike : false, (Utf8.REPLACEMENT_CODE_POINT & 512) != 0 ? chatMessage.isEdit : false, (Utf8.REPLACEMENT_CODE_POINT & 1024) != 0 ? chatMessage.play : false, (Utf8.REPLACEMENT_CODE_POINT & 2048) != 0 ? chatMessage.isComplete : false, (Utf8.REPLACEMENT_CODE_POINT & 4096) != 0 ? chatMessage.textStreamComplete : false, (Utf8.REPLACEMENT_CODE_POINT & 8192) != 0 ? chatMessage.streamedContent : null, (Utf8.REPLACEMENT_CODE_POINT & 16384) != 0 ? chatMessage.streamIndex : 0, (Utf8.REPLACEMENT_CODE_POINT & 32768) != 0 ? chatMessage.streamComplete : false));
        notifyItemChanged(0, newText);
    }

    public final void addMessages(List<ChatMessage> newMessages) {
        Intrinsics.checkNotNullParameter(newMessages, "newMessages");
        List<ChatMessage> list = newMessages;
        if (list.isEmpty()) {
            return;
        }
        int size = this.innerMessages.size();
        this.innerMessages.addAll(list);
        notifyItemRangeInserted(size, newMessages.size());
    }

    public final List<ChatMessage> getData() {
        return this.innerMessages;
    }

    public final void setOnItemClickListener(Function2<? super View, ? super Integer, Unit> listener) {
        this.onItemClick = listener;
    }

    public final void setSoundClickListener(Function2<? super View, ? super Integer, Unit> listener) {
        this.onSoundClick = listener;
    }

    public final LiveData<ChatMessage> getOnImageClick() {
        return this._onImageClick;
    }

    public final void setOnLikeClickListener(Function2<? super Integer, ? super ChatMessage, Unit> listener) {
        this.onLikeClick = listener;
    }

    public final void setOnUnLikeClickListener(Function2<? super Integer, ? super ChatMessage, Unit> listener) {
        this.onUnLikeClick = listener;
    }

    /* compiled from: ChatAdapter.kt */
    @Metadata(m606d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u0011\u0010\u0012\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000bR\u0011\u0010\u0014\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000bR\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, m607d2 = {"Lcom/glasssutdio/wear/ai/adapter/ChatAdapter$TextMessageViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/glasssutdio/wear/ai/adapter/ChatAdapter;Landroid/view/View;)V", "clsAnswer", "clsItem", "Landroidx/constraintlayout/widget/ConstraintLayout;", "ivChecked", "Landroid/widget/ImageView;", "getIvChecked", "()Landroid/widget/ImageView;", "ivCopy", "getIvCopy", "ivLike", "getIvLike", "ivSound", "getIvSound", "ivUnlike", "getIvUnlike", "photo", "getPhoto", "streamer", "Lcom/glasssutdio/wear/ai/adapter/TextStreamer;", "getStreamer", "()Lcom/glasssutdio/wear/ai/adapter/TextStreamer;", "textMessage", "Landroid/widget/TextView;", "getTextMessage", "()Landroid/widget/TextView;", "textQuestion", "bind", "", "chatMessage", "Lcom/glasssutdio/wear/ai/bean/ChatMessage;", "isStreaming", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class TextMessageViewHolder extends RecyclerView.ViewHolder {
        private final View clsAnswer;
        private final ConstraintLayout clsItem;
        private final ImageView ivChecked;
        private final ImageView ivCopy;
        private final ImageView ivLike;
        private final ImageView ivSound;
        private final ImageView ivUnlike;
        private final ImageView photo;
        private final TextStreamer streamer;
        private final TextView textMessage;
        private final TextView textQuestion;
        final /* synthetic */ ChatAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TextMessageViewHolder(ChatAdapter chatAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = chatAdapter;
            View viewFindViewById = itemView.findViewById(C0775R.id.textMessage);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            TextView textView = (TextView) viewFindViewById;
            this.textMessage = textView;
            View viewFindViewById2 = itemView.findViewById(C0775R.id.textQuestion);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
            this.textQuestion = (TextView) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(C0775R.id.iv_like);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
            this.ivLike = (ImageView) viewFindViewById3;
            View viewFindViewById4 = itemView.findViewById(C0775R.id.iv_unlike);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
            this.ivUnlike = (ImageView) viewFindViewById4;
            View viewFindViewById5 = itemView.findViewById(C0775R.id.iv_copy);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(...)");
            this.ivCopy = (ImageView) viewFindViewById5;
            View viewFindViewById6 = itemView.findViewById(C0775R.id.cls_item);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(...)");
            this.clsItem = (ConstraintLayout) viewFindViewById6;
            View viewFindViewById7 = itemView.findViewById(C0775R.id.cls_answer);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "findViewById(...)");
            this.clsAnswer = viewFindViewById7;
            View viewFindViewById8 = itemView.findViewById(C0775R.id.photo_1);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "findViewById(...)");
            this.photo = (ImageView) viewFindViewById8;
            View viewFindViewById9 = itemView.findViewById(C0775R.id.iv_checked);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById9, "findViewById(...)");
            this.ivChecked = (ImageView) viewFindViewById9;
            View viewFindViewById10 = itemView.findViewById(C0775R.id.ai_iv_sound);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById10, "findViewById(...)");
            this.ivSound = (ImageView) viewFindViewById10;
            this.streamer = new TextStreamer(textView);
        }

        public final TextView getTextMessage() {
            return this.textMessage;
        }

        public final ImageView getIvLike() {
            return this.ivLike;
        }

        public final ImageView getIvUnlike() {
            return this.ivUnlike;
        }

        public final ImageView getIvCopy() {
            return this.ivCopy;
        }

        public final ImageView getPhoto() {
            return this.photo;
        }

        public final ImageView getIvChecked() {
            return this.ivChecked;
        }

        public final ImageView getIvSound() {
            return this.ivSound;
        }

        public final TextStreamer getStreamer() {
            return this.streamer;
        }

        public final void bind(final ChatMessage chatMessage, boolean isStreaming) {
            Intrinsics.checkNotNullParameter(chatMessage, "chatMessage");
            if (chatMessage.isMe()) {
                this.textQuestion.setText(chatMessage.getMessage());
                this.textQuestion.setTextColor(ContextCompat.getColor(this.this$0.context, C0775R.color.chat_bg_text_1));
                ViewKt.visible(this.textQuestion);
                ViewKt.gone(this.clsAnswer);
                ViewKt.gone(this.ivSound);
                this.clsItem.setPadding(0, GlobalKt.getDp((Number) 28), 0, 0);
                return;
            }
            if (isStreaming && this.this$0.getSupportStreaming() && !chatMessage.getStreamComplete()) {
                this.streamer.append(chatMessage, new Function0<Unit>() { // from class: com.glasssutdio.wear.ai.adapter.ChatAdapter$TextMessageViewHolder$bind$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        chatMessage.setStreamComplete(true);
                    }
                });
            } else {
                this.streamer.display(chatMessage);
            }
            ViewKt.gone(this.textQuestion);
            ViewKt.visible(this.clsAnswer);
            this.clsItem.setPadding(0, GlobalKt.getDp((Number) 10), 0, 0);
        }
    }

    /* compiled from: ChatAdapter.kt */
    @Metadata(m606d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b¨\u0006\u0012"}, m607d2 = {"Lcom/glasssutdio/wear/ai/adapter/ChatAdapter$ImageMessageViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/glasssutdio/wear/ai/adapter/ChatAdapter;Landroid/view/View;)V", "clsItem", "Landroidx/constraintlayout/widget/ConstraintLayout;", "imageContent", "Landroid/widget/ImageView;", "ivChecked", "getIvChecked", "()Landroid/widget/ImageView;", "photo", "getPhoto", "bind", "", "chatMessage", "Lcom/glasssutdio/wear/ai/bean/ChatMessage;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class ImageMessageViewHolder extends RecyclerView.ViewHolder {
        private final ConstraintLayout clsItem;
        private final ImageView imageContent;
        private final ImageView ivChecked;
        private final ImageView photo;
        final /* synthetic */ ChatAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ImageMessageViewHolder(ChatAdapter chatAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = chatAdapter;
            View viewFindViewById = itemView.findViewById(C0775R.id.photo_1);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            this.photo = (ImageView) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(C0775R.id.image_content);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
            this.imageContent = (ImageView) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(C0775R.id.iv_checked);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
            this.ivChecked = (ImageView) viewFindViewById3;
            View viewFindViewById4 = itemView.findViewById(C0775R.id.cls_item);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
            this.clsItem = (ConstraintLayout) viewFindViewById4;
        }

        public final ImageView getPhoto() {
            return this.photo;
        }

        public final ImageView getIvChecked() {
            return this.ivChecked;
        }

        public final void bind(final ChatMessage chatMessage) {
            Intrinsics.checkNotNullParameter(chatMessage, "chatMessage");
            Glide.with(this.itemView.getContext()).load(chatMessage.getImagePath()).apply((BaseRequestOptions<?>) this.this$0.requestOptions).into(this.imageContent);
            this.clsItem.setPadding(0, GlobalKt.getDp((Number) 4), 0, 0);
            ImageView imageView = this.imageContent;
            final ChatAdapter chatAdapter = this.this$0;
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.ai.adapter.ChatAdapter$ImageMessageViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ChatAdapter.ImageMessageViewHolder.bind$lambda$0(chatAdapter, chatMessage, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$0(ChatAdapter this$0, ChatMessage chatMessage, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(chatMessage, "$chatMessage");
            this$0._onImageClick.postValue(chatMessage);
        }
    }

    /* compiled from: ChatAdapter.kt */
    @Metadata(m606d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m607d2 = {"Lcom/glasssutdio/wear/ai/adapter/ChatAdapter$VideoMessageViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/glasssutdio/wear/ai/adapter/ChatAdapter;Landroid/view/View;)V", "ivChecked", "Landroid/widget/ImageView;", "getIvChecked", "()Landroid/widget/ImageView;", "videoMessage", "Landroid/widget/VideoView;", "bind", "", "chatMessage", "Lcom/glasssutdio/wear/ai/bean/ChatMessage;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class VideoMessageViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivChecked;
        final /* synthetic */ ChatAdapter this$0;
        private final VideoView videoMessage;

        public final void bind(ChatMessage chatMessage) {
            Intrinsics.checkNotNullParameter(chatMessage, "chatMessage");
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public VideoMessageViewHolder(ChatAdapter chatAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = chatAdapter;
            View viewFindViewById = itemView.findViewById(C0775R.id.videoMessage);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            this.videoMessage = (VideoView) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(C0775R.id.iv_checked);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
            this.ivChecked = (ImageView) viewFindViewById2;
        }

        public final ImageView getIvChecked() {
            return this.ivChecked;
        }
    }

    /* compiled from: ChatAdapter.kt */
    @Metadata(m606d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H&J \u0010\u0012\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m607d2 = {"Lcom/glasssutdio/wear/ai/adapter/ChatAdapter$EndlessRecyclerViewScrollListener;", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "layoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "(Landroidx/recyclerview/widget/LinearLayoutManager;)V", "currentPage", "", "loading", "", "previousTotalItemCount", "startingPageIndex", "visibleThreshold", "onLoadMore", "", "page", "totalItemsCount", "view", "Landroidx/recyclerview/widget/RecyclerView;", "onScrolled", "dx", "dy", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
        private int currentPage;
        private final LinearLayoutManager layoutManager;
        private boolean loading;
        private int previousTotalItemCount;
        private final int startingPageIndex;
        private final int visibleThreshold;

        public abstract void onLoadMore(int page, int totalItemsCount, RecyclerView view);

        public EndlessRecyclerViewScrollListener(LinearLayoutManager layoutManager) {
            Intrinsics.checkNotNullParameter(layoutManager, "layoutManager");
            this.layoutManager = layoutManager;
            this.visibleThreshold = 5;
            this.loading = true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView view, int dx, int dy) {
            int iFindFirstVisibleItemPosition;
            int i;
            Intrinsics.checkNotNullParameter(view, "view");
            try {
                int itemCount = this.layoutManager.getItemCount();
                if (this.layoutManager.getReverseLayout()) {
                    iFindFirstVisibleItemPosition = this.layoutManager.findLastVisibleItemPosition();
                } else {
                    iFindFirstVisibleItemPosition = this.layoutManager.findFirstVisibleItemPosition();
                }
                if (itemCount < this.previousTotalItemCount) {
                    this.currentPage = this.startingPageIndex;
                    this.previousTotalItemCount = itemCount;
                    if (itemCount == 0) {
                        this.loading = true;
                    }
                }
                if (this.loading && itemCount > this.previousTotalItemCount) {
                    this.loading = false;
                    this.previousTotalItemCount = itemCount;
                }
                if (this.loading) {
                    return;
                }
                if (this.layoutManager.getReverseLayout()) {
                    i = iFindFirstVisibleItemPosition - this.visibleThreshold;
                } else {
                    i = iFindFirstVisibleItemPosition + this.visibleThreshold;
                }
                if (i > itemCount - this.visibleThreshold) {
                    int i2 = this.currentPage + 1;
                    this.currentPage = i2;
                    onLoadMore(i2, itemCount, view);
                    this.loading = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
