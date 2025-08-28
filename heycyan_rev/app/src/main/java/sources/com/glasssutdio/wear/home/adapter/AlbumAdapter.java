package com.glasssutdio.wear.home.adapter;

import android.R;
import android.content.res.Resources;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.ThreadExtKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.view.CircleProgressView;
import com.glasssutdio.wear.database.entity.GlassAlbumEntity;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.ClientCookie;
import com.oudmon.qc_utils.date.DateUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AlbumAdapter.kt */
@Metadata(m606d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u000589:;<B\u001f\u0012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0004¢\u0006\u0002\u0010\bJ&\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020\u0005J\b\u0010$\u001a\u0004\u0018\u00010 J\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\b\u0010&\u001a\u00020\u0005H\u0016J\u0010\u0010'\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u0005H\u0016J\u0018\u0010)\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020,H\u0002J\u0018\u0010-\u001a\u00020\u001e2\u0006\u0010.\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u0005H\u0016J\u0018\u0010/\u001a\u00020\u00022\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u0005H\u0016J \u00103\u001a\u00020\u001e2\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0004J\u000e\u00104\u001a\u00020\u001e2\u0006\u00105\u001a\u00020\u0007J\u000e\u00106\u001a\u00020\u001e2\u0006\u00107\u001a\u00020\u0017R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\n¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006="}, m607d2 = {"Lcom/glasssutdio/wear/home/adapter/AlbumAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "groupedAlbums", "", "", "", "Lcom/glasssutdio/wear/database/entity/GlassAlbumEntity;", "(Ljava/util/Map;)V", "allItems", "", "", "getAllItems", "()Ljava/util/List;", "allItemsNoKey", "getAllItemsNoKey", "edit", "", "getEdit", "()Z", "setEdit", "(Z)V", "itemClickListener", "Lcom/glasssutdio/wear/home/adapter/AlbumAdapter$OnItemClickListener;", "requestOptions", "Lcom/bumptech/glide/request/RequestOptions;", "width", "getWidth", "()I", "findPositionByName", "", "targetName", "", "eis", ClientCookie.PATH_ATTR, TypedValues.TransitionType.S_DURATION, "getAddressByAlbumModel", "getAllAlbum", "getItemCount", "getItemViewType", "position", "loadVideoFirstFrame", "videoPath", "imageView", "Landroid/widget/ImageView;", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "refresh", "refreshFirst", "entity", "setOnItemClickListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "AlbumViewHolder", "AlbumViewType", "DateHeaderViewHolder", "FooterViewHolder", "OnItemClickListener", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AlbumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Object> allItems;
    private final List<GlassAlbumEntity> allItemsNoKey;
    private boolean edit;
    private final Map<Integer, List<GlassAlbumEntity>> groupedAlbums;
    private OnItemClickListener itemClickListener;
    private final RequestOptions requestOptions;
    private final int width;

    /* compiled from: AlbumAdapter.kt */
    @Metadata(m606d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, m607d2 = {"Lcom/glasssutdio/wear/home/adapter/AlbumAdapter$OnItemClickListener;", "", "onItemClick", "", "position", "", "item", "Lcom/glasssutdio/wear/database/entity/GlassAlbumEntity;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public interface OnItemClickListener {
        void onItemClick(int position, GlassAlbumEntity item);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AlbumAdapter(Map<Integer, ? extends List<GlassAlbumEntity>> groupedAlbums) {
        Intrinsics.checkNotNullParameter(groupedAlbums, "groupedAlbums");
        this.groupedAlbums = groupedAlbums;
        this.allItems = new ArrayList();
        this.allItemsNoKey = new ArrayList();
        this.width = 300;
        RequestOptions requestOptionsTransform = new RequestOptions().override(300, 300).placeholder(R.color.darker_gray).transform(new CenterCrop(), new RoundedCorners(40));
        Intrinsics.checkNotNullExpressionValue(requestOptionsTransform, "transform(...)");
        this.requestOptions = requestOptionsTransform;
        List listSortedDescending = CollectionsKt.sortedDescending(groupedAlbums.keySet());
        DateUtil dateUtil = new DateUtil();
        Iterator it = listSortedDescending.iterator();
        while (it.hasNext()) {
            int iIntValue = ((Number) it.next()).intValue();
            List<GlassAlbumEntity> list = this.groupedAlbums.get(Integer.valueOf(iIntValue));
            if (list != null) {
                dateUtil.setUnixTimestamp(iIntValue);
                List<Object> list2 = this.allItems;
                String y_m_d = dateUtil.getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "getY_M_D(...)");
                list2.add(y_m_d);
                List<GlassAlbumEntity> list3 = list;
                this.allItems.addAll(list3);
                this.allItemsNoKey.addAll(list3);
            }
        }
    }

    /* compiled from: AlbumAdapter.kt */
    @Metadata(m606d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, m607d2 = {"Lcom/glasssutdio/wear/home/adapter/AlbumAdapter$AlbumViewType;", "", "()V", "TYPE_FOOTER", "", "VIEW_TYPE_ALBUM", "VIEW_TYPE_HEADER", "VIEW_TYPE_RECORDING", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class AlbumViewType {
        public static final AlbumViewType INSTANCE = new AlbumViewType();
        public static final int TYPE_FOOTER = 2;
        public static final int VIEW_TYPE_ALBUM = 1;
        public static final int VIEW_TYPE_HEADER = 0;
        public static final int VIEW_TYPE_RECORDING = 3;

        private AlbumViewType() {
        }
    }

    public final boolean getEdit() {
        return this.edit;
    }

    public final void setEdit(boolean z) {
        this.edit = z;
    }

    public final List<Object> getAllItems() {
        return this.allItems;
    }

    public final List<GlassAlbumEntity> getAllItemsNoKey() {
        return this.allItemsNoKey;
    }

    public final int getWidth() {
        return this.width;
    }

    public final List<Object> getAllAlbum() {
        return this.allItems;
    }

    public final void refreshFirst(GlassAlbumEntity entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        try {
            if (this.allItems.size() <= 0) {
                this.allItems.add(GlobalKt.getString(C0775R.string.album_glass_2));
            } else if (!Intrinsics.areEqual(this.allItems.get(0), GlobalKt.getString(C0775R.string.album_glass_2))) {
                this.allItems.add(0, GlobalKt.getString(C0775R.string.album_glass_2));
            }
            this.allItemsNoKey.add(0, entity);
            this.allItems.add(1, entity);
            notifyItemInserted(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void findPositionByName(String targetName, boolean eis, String path, int duration) {
        Intrinsics.checkNotNullParameter(targetName, "targetName");
        Intrinsics.checkNotNullParameter(path, "path");
        int i = 0;
        for (Object obj : this.allItems) {
            int i2 = i + 1;
            if (obj instanceof GlassAlbumEntity) {
                GlassAlbumEntity glassAlbumEntity = (GlassAlbumEntity) obj;
                if (Intrinsics.areEqual(glassAlbumEntity.getFileName(), targetName)) {
                    if (glassAlbumEntity.getFileType() == 2) {
                        glassAlbumEntity.setEisInProgress(eis);
                    } else if (glassAlbumEntity.getFileType() == 3) {
                        glassAlbumEntity.setVideoLength(duration);
                    }
                    glassAlbumEntity.setFilePath(path);
                    notifyItemRangeChanged(i, 2);
                    return;
                }
            }
            i = i2;
        }
    }

    public final void refresh(Map<Integer, ? extends List<GlassAlbumEntity>> groupedAlbums) throws Resources.NotFoundException {
        ArrayList arrayListEmptyList;
        String string;
        Intrinsics.checkNotNullParameter(groupedAlbums, "groupedAlbums");
        this.allItemsNoKey.clear();
        this.allItems.clear();
        DateUtil dateUtil = new DateUtil();
        Set<Map.Entry<Integer, ? extends List<GlassAlbumEntity>>> setEntrySet = groupedAlbums.entrySet();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : setEntrySet) {
            dateUtil.setUnixTimestamp(((Number) ((Map.Entry) obj).getKey()).intValue());
            if (dateUtil.isToday()) {
                string = GlobalKt.getString(C0775R.string.album_glass_2);
            } else {
                string = dateUtil.isYesterday() ? GlobalKt.getString(C0775R.string.album_glass_3) : dateUtil.getY_M_D();
            }
            Object obj2 = linkedHashMap.get(string);
            if (obj2 == null) {
                obj2 = (List) new ArrayList();
                linkedHashMap.put(string, obj2);
            }
            ((List) obj2).add(obj);
        }
        for (String str : CollectionsKt.sortedDescending(linkedHashMap.keySet())) {
            List<Object> list = this.allItems;
            Intrinsics.checkNotNull(str);
            list.add(str);
            List list2 = (List) linkedHashMap.get(str);
            if (list2 == null) {
                arrayListEmptyList = CollectionsKt.emptyList();
            } else {
                ArrayList arrayList = new ArrayList();
                Iterator it = list2.iterator();
                while (it.hasNext()) {
                    CollectionsKt.addAll(arrayList, (List) ((Map.Entry) it.next()).getValue());
                }
                arrayListEmptyList = arrayList;
            }
            List list3 = arrayListEmptyList;
            this.allItems.addAll(list3);
            this.allItemsNoKey.addAll(list3);
        }
        if (this.allItems.size() > 0) {
            this.allItems.add(1);
        }
    }

    public final void setOnItemClickListener(OnItemClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.itemClickListener = listener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (viewType == 0) {
            View viewInflate = LayoutInflater.from(parent.getContext()).inflate(C0775R.layout.item_date_header, parent, false);
            Intrinsics.checkNotNull(viewInflate);
            return new DateHeaderViewHolder(this, viewInflate);
        }
        if (viewType == 1) {
            View viewInflate2 = LayoutInflater.from(parent.getContext()).inflate(C0775R.layout.recycleview_album_media_item, parent, false);
            Intrinsics.checkNotNull(viewInflate2);
            return new AlbumViewHolder(this, viewInflate2);
        }
        if (viewType == 2) {
            View viewInflate3 = LayoutInflater.from(parent.getContext()).inflate(C0775R.layout.recycleview_album_footer, parent, false);
            Intrinsics.checkNotNull(viewInflate3);
            return new FooterViewHolder(viewInflate3);
        }
        throw new IllegalArgumentException("Invalid view type");
    }

    public final String getAddressByAlbumModel() {
        if (this.allItems.isEmpty()) {
            return null;
        }
        Object obj = this.allItems.get(0);
        if (obj instanceof GlassAlbumEntity) {
            return ((GlassAlbumEntity) obj).getMac();
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) throws IOException {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final Object obj = this.allItems.get(position);
        if (holder instanceof DateHeaderViewHolder) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
            ((DateHeaderViewHolder) holder).getDateTextView().setText((String) obj);
            return;
        }
        if (holder instanceof AlbumViewHolder) {
            if (this.edit) {
                ViewKt.visible(((AlbumViewHolder) holder).getImageSelect());
            }
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.glasssutdio.wear.database.entity.GlassAlbumEntity");
            GlassAlbumEntity glassAlbumEntity = (GlassAlbumEntity) obj;
            if (glassAlbumEntity.getFileType() == 1) {
                AlbumViewHolder albumViewHolder = (AlbumViewHolder) holder;
                ViewKt.gone(albumViewHolder.getVideoLength());
                ViewKt.gone(albumViewHolder.getShowVideoIcon());
                ViewKt.gone(albumViewHolder.getEisCircleProgress());
                Glide.with(holder.itemView.getContext()).load(glassAlbumEntity.getFilePath()).placeholder(C0775R.drawable.bg_album_shape).transform(new FitCenter()).apply((BaseRequestOptions<?>) this.requestOptions).into(albumViewHolder.getImage());
            } else if (glassAlbumEntity.getFileType() == 2) {
                AlbumViewHolder albumViewHolder2 = (AlbumViewHolder) holder;
                ViewKt.visible(albumViewHolder2.getVideoLength());
                ViewKt.visible(albumViewHolder2.getShowVideoIcon());
                if (glassAlbumEntity.getVideoFirstFrame().length() > 0) {
                    Glide.with(holder.itemView.getContext()).load("file://" + glassAlbumEntity.getVideoFirstFrame()).placeholder(C0775R.drawable.bg_album_shape).transform(new FitCenter()).apply((BaseRequestOptions<?>) this.requestOptions).into(albumViewHolder2.getImage());
                } else {
                    loadVideoFirstFrame(glassAlbumEntity.getFilePath(), albumViewHolder2.getImage());
                }
                albumViewHolder2.getVideoLength().setText(DateUtil.secondToStr(glassAlbumEntity.getVideoLength()));
                albumViewHolder2.getShowVideoIcon().setImageResource(C0775R.mipmap.album_video_icon);
                if (glassAlbumEntity.getEisInProgress() && System.currentTimeMillis() - glassAlbumEntity.getTimestamp() < 60000) {
                    ViewKt.visible(albumViewHolder2.getEisCircleProgress());
                    albumViewHolder2.getEisCircleProgress().startProgressAnimation();
                    ThreadExtKt.ktxRunOnUiDelay(this, 180000L, new Function1<AlbumAdapter, Unit>() { // from class: com.glasssutdio.wear.home.adapter.AlbumAdapter.onBindViewHolder.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(AlbumAdapter albumAdapter) {
                            invoke2(albumAdapter);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(AlbumAdapter ktxRunOnUiDelay) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUiDelay, "$this$ktxRunOnUiDelay");
                            XLog.m137i("防抖超时了");
                            ViewKt.gone(((AlbumViewHolder) holder).getEisCircleProgress());
                            ((AlbumViewHolder) holder).getEisCircleProgress().stopRotation();
                        }
                    });
                } else {
                    ViewKt.gone(albumViewHolder2.getEisCircleProgress());
                    albumViewHolder2.getEisCircleProgress().stopRotation();
                }
            } else if (glassAlbumEntity.getFileType() == 3) {
                AlbumViewHolder albumViewHolder3 = (AlbumViewHolder) holder;
                Glide.with(holder.itemView.getContext()).load(Integer.valueOf(C0775R.mipmap.album_record_bg)).placeholder(C0775R.drawable.bg_album_shape).transform(new FitCenter()).apply((BaseRequestOptions<?>) this.requestOptions).into(albumViewHolder3.getImage());
                ViewKt.visible(albumViewHolder3.getShowVideoIcon());
                ViewKt.visible(albumViewHolder3.getVideoLength());
                ViewKt.gone(albumViewHolder3.getEisCircleProgress());
                if (glassAlbumEntity.getVideoLength() > 0) {
                    albumViewHolder3.getVideoLength().setText(DateUtil.secondToStr(glassAlbumEntity.getVideoLength() / 1000));
                }
                albumViewHolder3.getShowVideoIcon().setImageResource(C0775R.mipmap.album_record_ing);
            }
            if (glassAlbumEntity.getEditSelect()) {
                ((AlbumViewHolder) holder).getImageSelect().setImageResource(C0775R.mipmap.album_edit_select);
            } else {
                ((AlbumViewHolder) holder).getImageSelect().setImageResource(C0775R.mipmap.album_edit_unselect);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.home.adapter.AlbumAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AlbumAdapter.onBindViewHolder$lambda$2(this.f$0, position, obj, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$2(AlbumAdapter this$0, int i, Object item, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        OnItemClickListener onItemClickListener = this$0.itemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(i, (GlassAlbumEntity) item);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.allItems.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        if (this.allItems.get(position) instanceof String) {
            return 0;
        }
        return this.allItems.get(position) instanceof Integer ? 2 : 1;
    }

    /* compiled from: AlbumAdapter.kt */
    @Metadata(m606d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/home/adapter/AlbumAdapter$DateHeaderViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/glasssutdio/wear/home/adapter/AlbumAdapter;Landroid/view/View;)V", "dateTextView", "Landroid/widget/TextView;", "getDateTextView", "()Landroid/widget/TextView;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class DateHeaderViewHolder extends RecyclerView.ViewHolder {
        private final TextView dateTextView;
        final /* synthetic */ AlbumAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DateHeaderViewHolder(AlbumAdapter albumAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = albumAdapter;
            View viewFindViewById = itemView.findViewById(C0775R.id.tv_title_text);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            this.dateTextView = (TextView) viewFindViewById;
        }

        public final TextView getDateTextView() {
            return this.dateTextView;
        }
    }

    /* compiled from: AlbumAdapter.kt */
    @Metadata(m606d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, m607d2 = {"Lcom/glasssutdio/wear/home/adapter/AlbumAdapter$AlbumViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/glasssutdio/wear/home/adapter/AlbumAdapter;Landroid/view/View;)V", "eisCircleProgress", "Lcom/glasssutdio/wear/all/view/CircleProgressView;", "getEisCircleProgress", "()Lcom/glasssutdio/wear/all/view/CircleProgressView;", "image", "Landroid/widget/ImageView;", "getImage", "()Landroid/widget/ImageView;", "imageSelect", "getImageSelect", "showVideoIcon", "getShowVideoIcon", "videoLength", "Landroid/widget/TextView;", "getVideoLength", "()Landroid/widget/TextView;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class AlbumViewHolder extends RecyclerView.ViewHolder {
        private final CircleProgressView eisCircleProgress;
        private final ImageView image;
        private final ImageView imageSelect;
        private final ImageView showVideoIcon;
        final /* synthetic */ AlbumAdapter this$0;
        private final TextView videoLength;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AlbumViewHolder(AlbumAdapter albumAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = albumAdapter;
            View viewFindViewById = itemView.findViewById(C0775R.id.show_image_src);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            this.image = (ImageView) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(C0775R.id.tv_video_length);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
            this.videoLength = (TextView) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(C0775R.id.image_select);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
            this.imageSelect = (ImageView) viewFindViewById3;
            View viewFindViewById4 = itemView.findViewById(C0775R.id.show_image_icon);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
            this.showVideoIcon = (ImageView) viewFindViewById4;
            View viewFindViewById5 = itemView.findViewById(C0775R.id.eis_progress);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(...)");
            this.eisCircleProgress = (CircleProgressView) viewFindViewById5;
        }

        public final ImageView getImage() {
            return this.image;
        }

        public final TextView getVideoLength() {
            return this.videoLength;
        }

        public final ImageView getImageSelect() {
            return this.imageSelect;
        }

        public final ImageView getShowVideoIcon() {
            return this.showVideoIcon;
        }

        public final CircleProgressView getEisCircleProgress() {
            return this.eisCircleProgress;
        }
    }

    private final void loadVideoFirstFrame(String videoPath, ImageView imageView) throws IOException {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                mediaMetadataRetriever.setDataSource(videoPath);
                Glide.with(imageView.getContext()).asBitmap().load(mediaMetadataRetriever.getFrameAtTime(1L, 2)).placeholder(C0775R.drawable.bg_album_shape).transform(new FitCenter()).apply((BaseRequestOptions<?>) this.requestOptions).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            mediaMetadataRetriever.release();
        }
    }

    /* compiled from: AlbumAdapter.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, m607d2 = {"Lcom/glasssutdio/wear/home/adapter/AlbumAdapter$FooterViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class FooterViewHolder extends RecyclerView.ViewHolder {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FooterViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
        }
    }
}
