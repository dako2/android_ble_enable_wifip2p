package com.glasssutdio.wear.home.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.home.bean.AiHelperModel;
import com.glasssutdio.wear.home.bean.QuestionModel;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AIGuideActivityVM.kt */
@Metadata(m606d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013R&\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR&\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\nR&\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\b\"\u0004\b\u0011\u0010\n¨\u0006\u0015"}, m607d2 = {"Lcom/glasssutdio/wear/home/viewmodel/AIGuideActivityVM;", "Landroidx/lifecycle/ViewModel;", "()V", "aiHelperDataLD", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/glasssutdio/wear/home/bean/AiHelperModel;", "getAiHelperDataLD", "()Landroidx/lifecycle/MutableLiveData;", "setAiHelperDataLD", "(Landroidx/lifecycle/MutableLiveData;)V", "photoDataLD", "Lcom/glasssutdio/wear/home/bean/QuestionModel;", "getPhotoDataLD", "setPhotoDataLD", "voiceDataLD", "getVoiceDataLD", "setVoiceDataLD", "getAiHelperData", "", "getPageData", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AIGuideActivityVM extends ViewModel {
    private MutableLiveData<List<QuestionModel>> voiceDataLD = new MutableLiveData<>();
    private MutableLiveData<List<QuestionModel>> photoDataLD = new MutableLiveData<>();
    private MutableLiveData<List<AiHelperModel>> aiHelperDataLD = new MutableLiveData<>();

    public final MutableLiveData<List<QuestionModel>> getVoiceDataLD() {
        return this.voiceDataLD;
    }

    public final void setVoiceDataLD(MutableLiveData<List<QuestionModel>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.voiceDataLD = mutableLiveData;
    }

    public final MutableLiveData<List<QuestionModel>> getPhotoDataLD() {
        return this.photoDataLD;
    }

    public final void setPhotoDataLD(MutableLiveData<List<QuestionModel>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.photoDataLD = mutableLiveData;
    }

    public final MutableLiveData<List<AiHelperModel>> getAiHelperDataLD() {
        return this.aiHelperDataLD;
    }

    public final void setAiHelperDataLD(MutableLiveData<List<AiHelperModel>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.aiHelperDataLD = mutableLiveData;
    }

    public final void getPageData() {
        this.voiceDataLD.postValue(CollectionsKt.toMutableList((Collection) CollectionsKt.listOf((Object[]) new QuestionModel[]{new QuestionModel(GlobalKt.getString(C0775R.string.h_glass_150), 0, 2, null), new QuestionModel(GlobalKt.getString(C0775R.string.h_glass_151), 0, 2, null), new QuestionModel(GlobalKt.getString(C0775R.string.h_glass_152), 0, 2, null)})));
        this.photoDataLD.postValue(CollectionsKt.toMutableList((Collection) CollectionsKt.listOf((Object[]) new QuestionModel[]{new QuestionModel(GlobalKt.getString(C0775R.string.h_glass_155), 0, 2, null), new QuestionModel(GlobalKt.getString(C0775R.string.h_glass_156), 0, 2, null)})));
    }

    public final void getAiHelperData() {
        this.aiHelperDataLD.postValue(CollectionsKt.toMutableList((Collection) CollectionsKt.listOf((Object[]) new AiHelperModel[]{new AiHelperModel(GlobalKt.getString(C0775R.string.h_glass_171), GlobalKt.getString(C0775R.string.h_glass_172), C0775R.mipmap.ic_ai_helper_voice), new AiHelperModel(GlobalKt.getString(C0775R.string.h_glass_175), GlobalKt.getString(C0775R.string.h_glass_176), C0775R.mipmap.ic_ai_helper_capacity), new AiHelperModel(GlobalKt.getString(C0775R.string.h_glass_177), GlobalKt.getString(C0775R.string.h_glass_178), C0775R.mipmap.ic_ai_helper_vision)})));
    }
}
