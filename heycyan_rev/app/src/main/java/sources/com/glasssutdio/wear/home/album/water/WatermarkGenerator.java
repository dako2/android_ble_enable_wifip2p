package com.glasssutdio.wear.home.album.water;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import androidx.core.view.ViewCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WatermarkGenerator.kt */
@Metadata(m606d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0011B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rJ\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m607d2 = {"Lcom/glasssutdio/wear/home/album/water/WatermarkGenerator;", "", "()V", "PADDING_DP", "", "TEXT_SIZE_SP", "WATERMARK_HEIGHT_DP", "addBottomWatermark", "Landroid/graphics/Bitmap;", "context", "Landroid/content/Context;", "source", "config", "Lcom/glasssutdio/wear/home/album/water/WatermarkGenerator$WatermarkConfig;", "dpToPx", "", "dp", "WatermarkConfig", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class WatermarkGenerator {
    public static final WatermarkGenerator INSTANCE = new WatermarkGenerator();
    public static final int PADDING_DP = 26;
    public static final int TEXT_SIZE_SP = 26;
    public static final int WATERMARK_HEIGHT_DP = 130;

    private WatermarkGenerator() {
    }

    /* compiled from: WatermarkGenerator.kt */
    @Metadata(m606d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0003\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\tHÆ\u0003J?\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0003\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\tHÖ\u0001J\t\u0010%\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014¨\u0006&"}, m607d2 = {"Lcom/glasssutdio/wear/home/album/water/WatermarkGenerator$WatermarkConfig;", "", "nameImage", "Landroid/graphics/Bitmap;", "dateTime", "", "logo", "extraText", "logoColor", "", "(Landroid/graphics/Bitmap;Ljava/lang/String;Landroid/graphics/Bitmap;Ljava/lang/String;I)V", "getDateTime", "()Ljava/lang/String;", "setDateTime", "(Ljava/lang/String;)V", "getExtraText", "setExtraText", "getLogo", "()Landroid/graphics/Bitmap;", "setLogo", "(Landroid/graphics/Bitmap;)V", "getLogoColor", "()I", "setLogoColor", "(I)V", "getNameImage", "setNameImage", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final /* data */ class WatermarkConfig {
        private String dateTime;
        private String extraText;
        private Bitmap logo;
        private int logoColor;
        private Bitmap nameImage;

        public WatermarkConfig() {
            this(null, null, null, null, 0, 31, null);
        }

        public static /* synthetic */ WatermarkConfig copy$default(WatermarkConfig watermarkConfig, Bitmap bitmap, String str, Bitmap bitmap2, String str2, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                bitmap = watermarkConfig.nameImage;
            }
            if ((i2 & 2) != 0) {
                str = watermarkConfig.dateTime;
            }
            String str3 = str;
            if ((i2 & 4) != 0) {
                bitmap2 = watermarkConfig.logo;
            }
            Bitmap bitmap3 = bitmap2;
            if ((i2 & 8) != 0) {
                str2 = watermarkConfig.extraText;
            }
            String str4 = str2;
            if ((i2 & 16) != 0) {
                i = watermarkConfig.logoColor;
            }
            return watermarkConfig.copy(bitmap, str3, bitmap3, str4, i);
        }

        /* renamed from: component1, reason: from getter */
        public final Bitmap getNameImage() {
            return this.nameImage;
        }

        /* renamed from: component2, reason: from getter */
        public final String getDateTime() {
            return this.dateTime;
        }

        /* renamed from: component3, reason: from getter */
        public final Bitmap getLogo() {
            return this.logo;
        }

        /* renamed from: component4, reason: from getter */
        public final String getExtraText() {
            return this.extraText;
        }

        /* renamed from: component5, reason: from getter */
        public final int getLogoColor() {
            return this.logoColor;
        }

        public final WatermarkConfig copy(Bitmap nameImage, String dateTime, Bitmap logo, String extraText, int logoColor) {
            Intrinsics.checkNotNullParameter(dateTime, "dateTime");
            Intrinsics.checkNotNullParameter(extraText, "extraText");
            return new WatermarkConfig(nameImage, dateTime, logo, extraText, logoColor);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof WatermarkConfig)) {
                return false;
            }
            WatermarkConfig watermarkConfig = (WatermarkConfig) other;
            return Intrinsics.areEqual(this.nameImage, watermarkConfig.nameImage) && Intrinsics.areEqual(this.dateTime, watermarkConfig.dateTime) && Intrinsics.areEqual(this.logo, watermarkConfig.logo) && Intrinsics.areEqual(this.extraText, watermarkConfig.extraText) && this.logoColor == watermarkConfig.logoColor;
        }

        public int hashCode() {
            Bitmap bitmap = this.nameImage;
            int iHashCode = (((bitmap == null ? 0 : bitmap.hashCode()) * 31) + this.dateTime.hashCode()) * 31;
            Bitmap bitmap2 = this.logo;
            return ((((iHashCode + (bitmap2 != null ? bitmap2.hashCode() : 0)) * 31) + this.extraText.hashCode()) * 31) + Integer.hashCode(this.logoColor);
        }

        public String toString() {
            return "WatermarkConfig(nameImage=" + this.nameImage + ", dateTime=" + this.dateTime + ", logo=" + this.logo + ", extraText=" + this.extraText + ", logoColor=" + this.logoColor + ')';
        }

        public WatermarkConfig(Bitmap bitmap, String dateTime, Bitmap bitmap2, String extraText, int i) {
            Intrinsics.checkNotNullParameter(dateTime, "dateTime");
            Intrinsics.checkNotNullParameter(extraText, "extraText");
            this.nameImage = bitmap;
            this.dateTime = dateTime;
            this.logo = bitmap2;
            this.extraText = extraText;
            this.logoColor = i;
        }

        public final Bitmap getNameImage() {
            return this.nameImage;
        }

        public final void setNameImage(Bitmap bitmap) {
            this.nameImage = bitmap;
        }

        public /* synthetic */ WatermarkConfig(Bitmap bitmap, String str, Bitmap bitmap2, String str2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? null : bitmap, (i2 & 2) != 0 ? "2025.02.11 15:34:59" : str, (i2 & 4) == 0 ? bitmap2 : null, (i2 & 8) != 0 ? "Shot on AI Glasses" : str2, (i2 & 16) != 0 ? -16776961 : i);
        }

        public final String getDateTime() {
            return this.dateTime;
        }

        public final void setDateTime(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.dateTime = str;
        }

        public final Bitmap getLogo() {
            return this.logo;
        }

        public final void setLogo(Bitmap bitmap) {
            this.logo = bitmap;
        }

        public final String getExtraText() {
            return this.extraText;
        }

        public final void setExtraText(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.extraText = str;
        }

        public final int getLogoColor() {
            return this.logoColor;
        }

        public final void setLogoColor(int i) {
            this.logoColor = i;
        }
    }

    public final Bitmap addBottomWatermark(Context context, Bitmap source, WatermarkConfig config) {
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(config, "config");
        float fDpToPx = dpToPx(context, 26);
        float fDpToPx2 = dpToPx(context, WATERMARK_HEIGHT_DP);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(source.getWidth(), source.getHeight() + ((int) fDpToPx2), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(source, 0.0f, 0.0f, (Paint) null);
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0.0f, source.getHeight(), source.getWidth(), source.getHeight() + fDpToPx2, paint);
        float fDpToPx3 = dpToPx(context, 50);
        Bitmap nameImage = config.getNameImage();
        if (nameImage != null) {
            double d = (fDpToPx2 * 0.7d) - (r11 * fDpToPx);
            Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(nameImage, (int) ((nameImage.getWidth() * d) / nameImage.getHeight()), (int) d, true);
            str = "createScaledBitmap(...)";
            Intrinsics.checkNotNullExpressionValue(bitmapCreateScaledBitmap, str);
            canvas.drawBitmap(bitmapCreateScaledBitmap, fDpToPx3, source.getHeight() + ((fDpToPx2 - bitmapCreateScaledBitmap.getHeight()) / 2), (Paint) null);
            bitmapCreateScaledBitmap.getWidth();
        } else {
            str = "createScaledBitmap(...)";
        }
        float width = source.getWidth() - fDpToPx;
        String extraText = config.getExtraText();
        Paint paint2 = new Paint(1);
        paint2.setColor(ViewCompat.MEASURED_STATE_MASK);
        WatermarkGenerator watermarkGenerator = INSTANCE;
        paint2.setTextSize(watermarkGenerator.dpToPx(context, 26));
        float height = (0.4f * fDpToPx2) + source.getHeight();
        canvas.drawText(extraText, (width - paint2.measureText(extraText)) - dpToPx(context, 26), height, paint2);
        Paint paint3 = new Paint(paint2);
        paint3.setColor(-7829368);
        paint3.setTypeface(Typeface.DEFAULT);
        paint3.setTextSize(watermarkGenerator.dpToPx(context, 26) * 0.9f);
        String dateTime = config.getDateTime();
        canvas.drawText(dateTime, (width - paint3.measureText(dateTime)) - dpToPx(context, 26), (4 * 31.2f) + height, paint3);
        float f = 2;
        float fMeasureText = (width - paint3.measureText(dateTime)) - (dpToPx(context, 26) * f);
        String str2 = str;
        canvas.drawLine(fMeasureText, height, fMeasureText, (3 * 31.2f) + height, paint3);
        Bitmap logo = config.getLogo();
        if (logo != null) {
            double d2 = (fDpToPx2 * 0.9d) - (fDpToPx * f);
            Bitmap bitmapCreateScaledBitmap2 = Bitmap.createScaledBitmap(logo, (int) ((logo.getWidth() * d2) / logo.getHeight()), (int) d2, true);
            Intrinsics.checkNotNullExpressionValue(bitmapCreateScaledBitmap2, str2);
            canvas.drawBitmap(bitmapCreateScaledBitmap2, (fMeasureText - (bitmapCreateScaledBitmap2.getWidth() / 2)) - (watermarkGenerator.dpToPx(context, 26) * f), source.getHeight() + ((fDpToPx2 - bitmapCreateScaledBitmap2.getHeight()) / f), (Paint) null);
        }
        return bitmapCreateBitmap;
    }

    private final float dpToPx(Context context, int dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
