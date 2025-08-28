package org.tensorflow.lite.support.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;

/* loaded from: classes3.dex */
public class SequentialProcessor<T> implements Processor<T> {
    protected final Map<String, List<Integer>> operatorIndex;
    protected final List<Operator<T>> operatorList;

    protected SequentialProcessor(Builder<T> builder) {
        this.operatorList = ((Builder) builder).operatorList;
        this.operatorIndex = Collections.unmodifiableMap(((Builder) builder).operatorIndex);
    }

    @Override // org.tensorflow.lite.support.common.Processor
    public T process(T t) {
        Iterator<Operator<T>> it = this.operatorList.iterator();
        while (it.hasNext()) {
            t = it.next().apply(t);
        }
        return t;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static class Builder<T> {
        private final List<Operator<T>> operatorList = new ArrayList();
        private final Map<String, List<Integer>> operatorIndex = new HashMap();

        protected Builder() {
        }

        public Builder<T> add(@NonNull Operator<T> operator) {
            SupportPreconditions.checkNotNull(operator, "Adding null Op is illegal.");
            this.operatorList.add(operator);
            String name = operator.getClass().getName();
            if (!this.operatorIndex.containsKey(name)) {
                this.operatorIndex.put(name, new ArrayList());
            }
            this.operatorIndex.get(name).add(Integer.valueOf(this.operatorList.size() - 1));
            return this;
        }

        public SequentialProcessor<T> build() {
            return new SequentialProcessor<>(this);
        }
    }
}
