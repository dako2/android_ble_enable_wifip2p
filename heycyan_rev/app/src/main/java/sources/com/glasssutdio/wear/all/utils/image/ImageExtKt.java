package com.glasssutdio.wear.all.utils.image;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.widget.ImageView;
import androidx.core.content.FileProvider;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.MoshiUtils;
import com.glasssutdio.wear.all.utils.TypeToken;
import com.glasssutdio.wear.home.bean.UserModel;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.ClientCookie;
import com.squareup.moshi.JsonAdapter;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ImageExt.kt */
@Metadata(m606d1 = {"\u0000@\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0006\u0010\f\u001a\u00020\u0001\u001a9\u0010\r\u001a\u00020\u000e*\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112#\b\u0002\u0010\u0012\u001a\u001d\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0018\u00010\u0013¢\u0006\u0002\b\u0015\u001al\u0010\u0016\u001a\u00020\u000e*\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00012#\b\u0002\u0010\u0012\u001a\u001d\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0018\u00010\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\u0010\u001d\u001a\u001c\u0010\u001e\u001a\u00020\u000e*\u00020\u000f2\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\u0001\"\u001a\u0010\u0000\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005\"\u001a\u0010\u0006\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u0003\"\u0004\b\b\u0010\u0005\"\u001a\u0010\t\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0003\"\u0004\b\u000b\u0010\u0005¨\u0006\""}, m607d2 = {"avatarDefault", "", "getAvatarDefault", "()I", "setAvatarDefault", "(I)V", "errorHolder", "getErrorHolder", "setErrorHolder", "placeHolderDefault", "getPlaceHolderDefault", "setPlaceHolderDefault", "getAvatarDefaultRes", "displayAvatar", "", "Landroid/widget/ImageView;", ClientCookie.PATH_ATTR, "", "configBlock", "Lkotlin/Function1;", "Lcom/bumptech/glide/RequestBuilder;", "Lkotlin/ExtensionFunctionType;", "displayImage", "needAnimation", "", "loadContext", "Landroid/content/Context;", "placeHolder", "errorDefault", "(Landroid/widget/ImageView;Ljava/lang/Object;ZLandroid/content/Context;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)V", "loadGifFromAssets", "fileName", "", "loopCount", "app_release"}, m608k = 2, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class ImageExtKt {
    private static int placeHolderDefault = C0775R.drawable.default_placeholder_transparent;
    private static int errorHolder = C0775R.drawable.default_placeholder_transparent;
    private static int avatarDefault = getAvatarDefaultRes();

    public static final int getPlaceHolderDefault() {
        return placeHolderDefault;
    }

    public static final void setPlaceHolderDefault(int i) {
        placeHolderDefault = i;
    }

    public static final int getErrorHolder() {
        return errorHolder;
    }

    public static final void setErrorHolder(int i) {
        errorHolder = i;
    }

    public static final int getAvatarDefault() {
        return avatarDefault;
    }

    public static final void setAvatarDefault(int i) {
        avatarDefault = i;
    }

    public static /* synthetic */ void displayAvatar$default(ImageView imageView, Object obj, Function1 function1, int i, Object obj2) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        displayAvatar(imageView, obj, function1);
    }

    public static final void displayAvatar(ImageView imageView, Object obj, Function1<? super RequestBuilder<?>, ? extends RequestBuilder<?>> function1) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        int avatarDefaultRes = getAvatarDefaultRes();
        avatarDefault = avatarDefaultRes;
        if (obj instanceof String) {
            String str = (String) obj;
            if (StringsKt.startsWith$default(str, HttpHost.DEFAULT_SCHEME_NAME, false, 2, (Object) null)) {
                displayImage$default(imageView, obj, true, null, null, Integer.valueOf(avatarDefault), function1, 12, null);
                return;
            }
            if (((CharSequence) obj).length() == 0) {
                imageView.setImageResource(avatarDefault);
                return;
            }
            File file = new File(str);
            Context context = imageView.getContext();
            StringBuilder sb = new StringBuilder();
            Context context2 = imageView.getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
            displayImage$default(imageView, FileProvider.getUriForFile(context, sb.append(GlobalKt.getPackageName(context2)).append(".provider").toString(), file), true, null, null, Integer.valueOf(avatarDefault), function1, 12, null);
            return;
        }
        displayImage$default(imageView, obj, true, null, null, Integer.valueOf(avatarDefaultRes), function1, 12, null);
    }

    public static final int getAvatarDefaultRes() {
        try {
            if (UserConfig.INSTANCE.getInstance().getUserJson().length() > 0) {
                String userJson = UserConfig.INSTANCE.getInstance().getUserJson();
                JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<UserModel>() { // from class: com.glasssutdio.wear.all.utils.image.ImageExtKt$getAvatarDefaultRes$$inlined$fromJson$1
                }.getType());
                Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "adapter(...)");
                UserModel userModel = (UserModel) jsonAdapterAdapter.fromJson(userJson);
                Integer numValueOf = userModel != null ? Integer.valueOf(userModel.getGender()) : null;
                if (numValueOf != null && numValueOf.intValue() == 1) {
                    return C0775R.mipmap.ic_avatar_default_man;
                }
                if (numValueOf != null && numValueOf.intValue() == 2) {
                    return C0775R.mipmap.ic_avatar_default_women;
                }
                return C0775R.mipmap.ic_avatar_default_unknow;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return C0775R.mipmap.ic_avatar_default_unknow;
    }

    public static /* synthetic */ void displayImage$default(ImageView imageView, Object obj, boolean z, Context context, Integer num, Integer num2, Function1 function1, int i, Object obj2) {
        if ((i & 2) != 0) {
            z = false;
        }
        displayImage(imageView, obj, z, (i & 4) != 0 ? null : context, (i & 8) != 0 ? null : num, (i & 16) != 0 ? null : num2, (i & 32) != 0 ? null : function1);
    }

    public static final void displayImage(ImageView imageView, Object obj, boolean z, Context context, Integer num, Integer num2, Function1<? super RequestBuilder<?>, ? extends RequestBuilder<?>> function1) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        if (context == null && !(imageView.getContext() instanceof Application)) {
            Context context2 = imageView.getContext();
            Activity activity = null;
            if (context2 instanceof Activity) {
                Context context3 = imageView.getContext();
                if (context3 instanceof Activity) {
                    activity = (Activity) context3;
                }
            } else if (context2 instanceof ContextThemeWrapper) {
                Context context4 = imageView.getContext();
                Intrinsics.checkNotNull(context4, "null cannot be cast to non-null type android.view.ContextThemeWrapper");
                Context baseContext = ((ContextThemeWrapper) context4).getBaseContext();
                if (baseContext instanceof Activity) {
                    activity = (Activity) baseContext;
                }
            } else if (context2 != null) {
                Context context5 = imageView.getContext();
                if (context5 instanceof Activity) {
                    activity = (Activity) context5;
                }
            }
            if (GlobalKt.isDestroy(activity)) {
                return;
            }
        }
        if (context == null) {
            context = imageView.getContext();
        }
        RequestBuilder requestBuilderPlaceholder = Glide.with(context).load(obj).error(num2 != null ? num2.intValue() : placeHolderDefault).placeholder(num != null ? num.intValue() : placeHolderDefault);
        Intrinsics.checkNotNull(requestBuilderPlaceholder);
        if (function1 != null) {
            function1.invoke(requestBuilderPlaceholder);
        }
        if (z) {
            requestBuilderPlaceholder = requestBuilderPlaceholder.transition(DrawableTransitionOptions.withCrossFade());
            Intrinsics.checkNotNullExpressionValue(requestBuilderPlaceholder, "transition(...)");
        }
        requestBuilderPlaceholder.into(imageView);
    }

    public static /* synthetic */ void loadGifFromAssets$default(ImageView imageView, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = -1;
        }
        loadGifFromAssets(imageView, str, i);
    }

    public static final void loadGifFromAssets(final ImageView imageView, String fileName, final int i) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Glide.with(imageView).asGif().load("file:///android_asset/" + fileName).into((RequestBuilder<GifDrawable>) new CustomTarget<GifDrawable>() { // from class: com.glasssutdio.wear.all.utils.image.ImageExtKt.loadGifFromAssets.1
            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable placeholder) {
            }

            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((GifDrawable) obj, (Transition<? super GifDrawable>) transition);
            }

            public void onResourceReady(GifDrawable resource, Transition<? super GifDrawable> transition) {
                Intrinsics.checkNotNullParameter(resource, "resource");
                imageView.setImageDrawable(resource);
                resource.setLoopCount(i);
                resource.start();
            }
        });
    }
}
