package retrofit2.adapter.rxjava2;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.plugins.RxJavaPlugins;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import retrofit2.Call;
import retrofit2.CallAdapter;

/* loaded from: classes3.dex */
final class RxJava2CallAdapter<R> implements CallAdapter<R, Object> {
    private final boolean isAsync;
    private final boolean isBody;
    private final boolean isCompletable;
    private final boolean isFlowable;
    private final boolean isMaybe;
    private final boolean isResult;
    private final boolean isSingle;
    private final Type responseType;

    @Nullable
    private final Scheduler scheduler;

    RxJava2CallAdapter(Type type, @Nullable Scheduler scheduler, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.responseType = type;
        this.scheduler = scheduler;
        this.isAsync = z;
        this.isResult = z2;
        this.isBody = z3;
        this.isFlowable = z4;
        this.isSingle = z5;
        this.isMaybe = z6;
        this.isCompletable = z7;
    }

    @Override // retrofit2.CallAdapter
    public Type responseType() {
        return this.responseType;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0037  */
    @Override // retrofit2.CallAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object adapt(Call<R> call) {
        Observable callExecuteObservable;
        Observable bodyObservable;
        Scheduler scheduler;
        if (this.isAsync) {
            callExecuteObservable = new CallEnqueueObservable(call);
        } else {
            callExecuteObservable = new CallExecuteObservable(call);
        }
        if (this.isResult) {
            bodyObservable = new ResultObservable(callExecuteObservable);
        } else {
            if (this.isBody) {
                bodyObservable = new BodyObservable(callExecuteObservable);
            }
            scheduler = this.scheduler;
            if (scheduler != null) {
                callExecuteObservable = callExecuteObservable.subscribeOn(scheduler);
            }
            if (!this.isFlowable) {
                return callExecuteObservable.toFlowable(BackpressureStrategy.LATEST);
            }
            if (this.isSingle) {
                return callExecuteObservable.singleOrError();
            }
            if (this.isMaybe) {
                return callExecuteObservable.singleElement();
            }
            if (this.isCompletable) {
                return callExecuteObservable.ignoreElements();
            }
            return RxJavaPlugins.onAssembly(callExecuteObservable);
        }
        callExecuteObservable = bodyObservable;
        scheduler = this.scheduler;
        if (scheduler != null) {
        }
        if (!this.isFlowable) {
        }
    }
}
