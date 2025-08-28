package com.glasssutdio.wear.home.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.home.bean.QuestionModel;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ModelAbilityActivityVM.kt */
@Metadata(m606d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0015R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR&\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R&\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010¨\u0006\u0017"}, m607d2 = {"Lcom/glasssutdio/wear/home/viewmodel/ModelAbilityActivityVM;", "Landroidx/lifecycle/ViewModel;", "()V", "pageType", "", "getPageType", "()I", "setPageType", "(I)V", "questionDataLD", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/glasssutdio/wear/home/bean/QuestionModel;", "getQuestionDataLD", "()Landroidx/lifecycle/MutableLiveData;", "setQuestionDataLD", "(Landroidx/lifecycle/MutableLiveData;)V", "visionDataLD", "getVisionDataLD", "setVisionDataLD", "getQuestionData", "", "getVisionData", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class ModelAbilityActivityVM extends ViewModel {
    private int pageType = 1;
    private MutableLiveData<List<QuestionModel>> questionDataLD = new MutableLiveData<>();
    private MutableLiveData<List<QuestionModel>> visionDataLD = new MutableLiveData<>();

    public final int getPageType() {
        return this.pageType;
    }

    public final void setPageType(int i) {
        this.pageType = i;
    }

    public final MutableLiveData<List<QuestionModel>> getQuestionDataLD() {
        return this.questionDataLD;
    }

    public final void setQuestionDataLD(MutableLiveData<List<QuestionModel>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.questionDataLD = mutableLiveData;
    }

    public final MutableLiveData<List<QuestionModel>> getVisionDataLD() {
        return this.visionDataLD;
    }

    public final void setVisionDataLD(MutableLiveData<List<QuestionModel>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.visionDataLD = mutableLiveData;
    }

    public final void getQuestionData() {
        this.questionDataLD.postValue(CollectionsKt.toMutableList((Collection) CollectionsKt.listOf((Object[]) new QuestionModel[]{new QuestionModel(GlobalKt.getString(C0775R.string.glasses_question_1), 0, 2, null), new QuestionModel(GlobalKt.getString(C0775R.string.glasses_question_2), 0, 2, null), new QuestionModel(GlobalKt.getString(C0775R.string.glasses_question_3), 0, 2, null), new QuestionModel(GlobalKt.getString(C0775R.string.glasses_question_4), 0, 2, null)})));
    }

    public final void getVisionData() {
        this.visionDataLD.postValue(CollectionsKt.toMutableList((Collection) CollectionsKt.listOf((Object[]) new QuestionModel[]{new QuestionModel(GlobalKt.getString(C0775R.string.glasses_question_5), 0, 2, null), new QuestionModel(GlobalKt.getString(C0775R.string.glasses_question_7), 0, 2, null), new QuestionModel(GlobalKt.getString(C0775R.string.glasses_question_8), 0, 2, null), new QuestionModel(GlobalKt.getString(C0775R.string.glasses_question_10), 0, 2, null)})));
    }
}
