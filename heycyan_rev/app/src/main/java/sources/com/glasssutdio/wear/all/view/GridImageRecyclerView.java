package com.glasssutdio.wear.all.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.ViewKt;
import com.glasssutdio.wear.all.bean.ImageLocalModel;
import com.glasssutdio.wear.all.utils.image.ImageExtKt;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GridImageRecyclerView.kt */
@Metadata(m606d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0002\"#B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0014\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00130\u001bJ\u0010\u0010 \u001a\u00020\u001e2\b\u0010!\u001a\u0004\u0018\u00010\u0018R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00060\fR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0012j\b\u0012\u0004\u0012\u00020\u0013`\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00130\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, m607d2 = {"Lcom/glasssutdio/wear/all/view/GridImageRecyclerView;", "Landroidx/recyclerview/widget/RecyclerView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "columnNumber", "", "columnSpace", "", "imageAdapter", "Lcom/glasssutdio/wear/all/view/GridImageRecyclerView$GridImageAdapter;", "imageAddDrawable", "Landroid/graphics/drawable/Drawable;", "imageDeleteDrawable", "imageRadius", "imageViewList", "Ljava/util/ArrayList;", "Lcom/glasssutdio/wear/all/bean/ImageLocalModel;", "Lkotlin/collections/ArrayList;", "isAddImage", "", "mOnGridImageListener", "Lcom/glasssutdio/wear/all/view/GridImageRecyclerView$OnGridImageListener;", "maxCount", "selectedImageList", "", "showDelete", "setImageList", "", "imageList", "setOnItemClickListener", "l", "GridImageAdapter", "OnGridImageListener", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class GridImageRecyclerView extends RecyclerView {
    private int columnNumber;
    private float columnSpace;
    private GridImageAdapter imageAdapter;
    private Drawable imageAddDrawable;
    private Drawable imageDeleteDrawable;
    private float imageRadius;
    private ArrayList<ImageLocalModel> imageViewList;
    private boolean isAddImage;
    private OnGridImageListener mOnGridImageListener;
    private int maxCount;
    private List<ImageLocalModel> selectedImageList;
    private boolean showDelete;

    /* compiled from: GridImageRecyclerView.kt */
    @Metadata(m606d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001a\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\t\u001a\u00020\u0003H&¨\u0006\n"}, m607d2 = {"Lcom/glasssutdio/wear/all/view/GridImageRecyclerView$OnGridImageListener;", "", "onDelete", "", "position", "", "onItemClick", "imageView", "Landroid/widget/ImageView;", "openPicture", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public interface OnGridImageListener {
        void onDelete(int position);

        void onItemClick(ImageView imageView, int position);

        void openPicture();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public GridImageRecyclerView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ GridImageRecyclerView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GridImageRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.columnNumber = 3;
        this.maxCount = 9;
        this.imageAddDrawable = ContextCompat.getDrawable(context, C0775R.mipmap.icon_select_img_add);
        this.imageDeleteDrawable = ContextCompat.getDrawable(context, C0775R.mipmap.icon_image_del_app);
        this.columnSpace = 10.0f;
        this.showDelete = true;
        this.imageViewList = new ArrayList<>();
        this.selectedImageList = new ArrayList();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0775R.styleable.GridImageRecyclerView);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "obtainStyledAttributes(...)");
        this.columnNumber = typedArrayObtainStyledAttributes.getInteger(C0775R.styleable.GridImageRecyclerView_columnNumber, 4);
        this.columnSpace = typedArrayObtainStyledAttributes.getDimension(C0775R.styleable.GridImageRecyclerView_columnSpace, 10.0f);
        this.imageRadius = typedArrayObtainStyledAttributes.getDimension(C0775R.styleable.GridImageRecyclerView_imageRadius, 0.0f);
        this.maxCount = typedArrayObtainStyledAttributes.getInteger(C0775R.styleable.GridImageRecyclerView_maxCount, 5);
        this.showDelete = typedArrayObtainStyledAttributes.getBoolean(C0775R.styleable.GridImageRecyclerView_showDelete, true);
        this.imageAddDrawable = typedArrayObtainStyledAttributes.getDrawable(C0775R.styleable.GridImageRecyclerView_imageAddDrawable);
        this.imageDeleteDrawable = typedArrayObtainStyledAttributes.getDrawable(C0775R.styleable.GridImageRecyclerView_imageDeleteDrawable);
        typedArrayObtainStyledAttributes.recycle();
        this.imageAdapter = new GridImageAdapter();
        setLayoutManager(new GridLayoutManager(context, this.columnNumber));
        setAdapter(this.imageAdapter);
        ArrayList<ImageLocalModel> arrayList = this.imageViewList;
        ImageLocalModel imageLocalModel = new ImageLocalModel();
        imageLocalModel.setPath("ADD");
        arrayList.add(imageLocalModel);
        this.imageAdapter.setList(this.imageViewList);
        this.imageAdapter.addChildClickViewIds(C0775R.id.iv_delete);
        this.imageAdapter.addChildClickViewIds(C0775R.id.image);
        this.imageAdapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.glasssutdio.wear.all.view.GridImageRecyclerView.2
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public void onItemChildClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                Intrinsics.checkNotNullParameter(adapter, "adapter");
                Intrinsics.checkNotNullParameter(view, "view");
                if (view.getId() == C0775R.id.iv_delete) {
                    if (position == -1 || GridImageRecyclerView.this.imageViewList.size() <= position) {
                        return;
                    }
                    GridImageRecyclerView.this.imageViewList.remove(position);
                    GridImageRecyclerView.this.imageAdapter.setList(GridImageRecyclerView.this.imageViewList);
                    OnGridImageListener onGridImageListener = GridImageRecyclerView.this.mOnGridImageListener;
                    if (onGridImageListener != null) {
                        onGridImageListener.onDelete(position);
                    }
                    if (GridImageRecyclerView.this.isAddImage || GridImageRecyclerView.this.imageViewList.size() >= GridImageRecyclerView.this.maxCount) {
                        return;
                    }
                    ArrayList arrayList2 = GridImageRecyclerView.this.imageViewList;
                    ImageLocalModel imageLocalModel2 = new ImageLocalModel();
                    imageLocalModel2.setPath("ADD");
                    arrayList2.add(imageLocalModel2);
                    GridImageRecyclerView.this.isAddImage = true;
                    GridImageRecyclerView.this.imageAdapter.setList(GridImageRecyclerView.this.imageViewList);
                    return;
                }
                if (view.getId() == C0775R.id.image) {
                    Object obj = GridImageRecyclerView.this.imageViewList.get(position);
                    Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                    if (Intrinsics.areEqual("ADD", ((ImageLocalModel) obj).getPath())) {
                        OnGridImageListener onGridImageListener2 = GridImageRecyclerView.this.mOnGridImageListener;
                        if (onGridImageListener2 != null) {
                            onGridImageListener2.openPicture();
                            return;
                        }
                        return;
                    }
                    OnGridImageListener onGridImageListener3 = GridImageRecyclerView.this.mOnGridImageListener;
                    if (onGridImageListener3 != null) {
                        onGridImageListener3.onItemClick((ImageView) view, position);
                    }
                }
            }
        });
    }

    /* compiled from: GridImageRecyclerView.kt */
    @Metadata(m606d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0002H\u0014¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/all/view/GridImageRecyclerView$GridImageAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/glasssutdio/wear/all/bean/ImageLocalModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "(Lcom/glasssutdio/wear/all/view/GridImageRecyclerView;)V", "convert", "", "holder", "model", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public final class GridImageAdapter extends BaseQuickAdapter<ImageLocalModel, BaseViewHolder> {
        public GridImageAdapter() {
            super(C0775R.layout.item_grid_image, null, 2, null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.chad.library.adapter.base.BaseQuickAdapter
        public void convert(BaseViewHolder holder, ImageLocalModel model) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            Intrinsics.checkNotNullParameter(model, "model");
            GridImageRecyclerView gridImageRecyclerView = GridImageRecyclerView.this;
            addChildClickViewIds(C0775R.id.iv_delete);
            addChildClickViewIds(C0775R.id.image);
            ImageFilterView imageFilterView = (ImageFilterView) holder.getView(C0775R.id.image);
            ImageView imageView = (ImageView) holder.getView(C0775R.id.iv_delete);
            imageFilterView.setRound(gridImageRecyclerView.imageRadius);
            if (Intrinsics.areEqual("ADD", model.getPath())) {
                imageFilterView.setImageDrawable(gridImageRecyclerView.imageAddDrawable);
                ViewKt.gone(imageView);
                return;
            }
            imageView.setImageDrawable(gridImageRecyclerView.imageDeleteDrawable);
            ViewKt.goneOrVisible(imageView, gridImageRecyclerView.showDelete);
            File file = new File(model.getPath());
            Context context = gridImageRecyclerView.getContext();
            StringBuilder sb = new StringBuilder();
            Context context2 = gridImageRecyclerView.getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
            ImageExtKt.displayImage$default(imageFilterView, FileProvider.getUriForFile(context, sb.append(GlobalKt.getPackageName(context2)).append(".provider").toString(), file), false, null, null, null, null, 62, null);
        }
    }

    public final void setImageList(List<ImageLocalModel> imageList) {
        Intrinsics.checkNotNullParameter(imageList, "imageList");
        this.selectedImageList = imageList;
        this.imageViewList.clear();
        this.isAddImage = false;
        if (imageList.isEmpty()) {
            ArrayList<ImageLocalModel> arrayList = this.imageViewList;
            ImageLocalModel imageLocalModel = new ImageLocalModel();
            imageLocalModel.setPath("ADD");
            arrayList.add(imageLocalModel);
            this.isAddImage = true;
        } else {
            this.imageViewList.addAll(imageList);
            if (imageList.size() < this.maxCount) {
                ArrayList<ImageLocalModel> arrayList2 = this.imageViewList;
                ImageLocalModel imageLocalModel2 = new ImageLocalModel();
                imageLocalModel2.setPath("ADD");
                arrayList2.add(imageLocalModel2);
                this.isAddImage = true;
            }
        }
        this.imageAdapter.setList(this.imageViewList);
    }

    public final void setOnItemClickListener(OnGridImageListener l) {
        this.mOnGridImageListener = l;
    }
}
