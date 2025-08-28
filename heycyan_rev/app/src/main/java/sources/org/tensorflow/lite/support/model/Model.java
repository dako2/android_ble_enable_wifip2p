package org.tensorflow.lite.support.model;

import android.content.Context;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.tensorflow.lite.InterpreterApi;
import org.tensorflow.lite.InterpreterFactory;
import org.tensorflow.lite.Tensor;
import org.tensorflow.lite.support.common.FileUtil;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;

/* loaded from: classes3.dex */
public class Model {
    private final MappedByteBuffer byteModel;
    private final GpuDelegateProxy gpuDelegateProxy;
    private final InterpreterApi interpreter;
    private final String modelPath;

    public enum Device {
        CPU,
        NNAPI,
        GPU
    }

    public static class Options {
        private final Device device;
        private final int numThreads;

        /* synthetic */ Options(Builder builder, C30001 c30001) {
            this(builder);
        }

        public static class Builder {
            private Device device = Device.CPU;
            private int numThreads = 1;

            public Builder setDevice(Device device) {
                this.device = device;
                return this;
            }

            public Builder setNumThreads(int i) {
                this.numThreads = i;
                return this;
            }

            public Options build() {
                return new Options(this, null);
            }
        }

        private Options(Builder builder) {
            this.device = builder.device;
            this.numThreads = builder.numThreads;
        }
    }

    @Deprecated
    public static class Builder {
        private final MappedByteBuffer byteModel;
        private final String modelPath;
        private Device device = Device.CPU;
        private int numThreads = 1;

        @NonNull
        public Builder(@NonNull Context context, @NonNull String str) throws IOException {
            this.modelPath = str;
            this.byteModel = FileUtil.loadMappedFile(context, str);
        }

        @NonNull
        public Builder setDevice(Device device) {
            this.device = device;
            return this;
        }

        @NonNull
        public Builder setNumThreads(int i) {
            this.numThreads = i;
            return this;
        }

        @NonNull
        public Model build() {
            return Model.createModel(this.byteModel, this.modelPath, new Options.Builder().setNumThreads(this.numThreads).setDevice(this.device).build());
        }
    }

    public static Model createModel(@NonNull Context context, @NonNull String str) throws IOException {
        return createModel(context, str, new Options.Builder().build());
    }

    public static Model createModel(@NonNull Context context, @NonNull String str, @NonNull Options options) throws IOException {
        SupportPreconditions.checkNotEmpty(str, "Model path in the asset folder cannot be empty.");
        return createModel(FileUtil.loadMappedFile(context, str), str, options);
    }

    public static Model createModel(@NonNull MappedByteBuffer mappedByteBuffer, @NonNull String str, @NonNull Options options) {
        GpuDelegateProxy gpuDelegateProxyMaybeNewInstance;
        InterpreterApi.Options options2 = new InterpreterApi.Options();
        int i = C30001.$SwitchMap$org$tensorflow$lite$support$model$Model$Device[options.device.ordinal()];
        if (i != 1) {
            if (i == 2) {
                gpuDelegateProxyMaybeNewInstance = GpuDelegateProxy.maybeNewInstance();
                SupportPreconditions.checkArgument(gpuDelegateProxyMaybeNewInstance != null, "Cannot inference with GPU. Did you add \"tensorflow-lite-gpu\" as dependency?");
                options2.addDelegate(gpuDelegateProxyMaybeNewInstance);
            }
            options2.setNumThreads(options.numThreads);
            return new Model(str, mappedByteBuffer, new InterpreterFactory().create(mappedByteBuffer, options2), gpuDelegateProxyMaybeNewInstance);
        }
        options2.setUseNNAPI(true);
        gpuDelegateProxyMaybeNewInstance = null;
        options2.setNumThreads(options.numThreads);
        return new Model(str, mappedByteBuffer, new InterpreterFactory().create(mappedByteBuffer, options2), gpuDelegateProxyMaybeNewInstance);
    }

    /* renamed from: org.tensorflow.lite.support.model.Model$1 */
    static /* synthetic */ class C30001 {
        static final /* synthetic */ int[] $SwitchMap$org$tensorflow$lite$support$model$Model$Device;

        static {
            int[] iArr = new int[Device.values().length];
            $SwitchMap$org$tensorflow$lite$support$model$Model$Device = iArr;
            try {
                iArr[Device.NNAPI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$tensorflow$lite$support$model$Model$Device[Device.GPU.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$tensorflow$lite$support$model$Model$Device[Device.CPU.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @NonNull
    public MappedByteBuffer getData() {
        return this.byteModel;
    }

    @NonNull
    public String getPath() {
        return this.modelPath;
    }

    public Tensor getInputTensor(int i) {
        return this.interpreter.getInputTensor(i);
    }

    public Tensor getOutputTensor(int i) {
        return this.interpreter.getOutputTensor(i);
    }

    public int[] getOutputTensorShape(int i) {
        return this.interpreter.getOutputTensor(i).shape();
    }

    public void run(@NonNull Object[] objArr, @NonNull Map<Integer, Object> map) {
        this.interpreter.runForMultipleInputsOutputs(objArr, map);
    }

    public void close() throws IOException {
        InterpreterApi interpreterApi = this.interpreter;
        if (interpreterApi != null) {
            interpreterApi.close();
        }
        GpuDelegateProxy gpuDelegateProxy = this.gpuDelegateProxy;
        if (gpuDelegateProxy != null) {
            gpuDelegateProxy.close();
        }
    }

    private Model(@NonNull String str, @NonNull MappedByteBuffer mappedByteBuffer, @NonNull InterpreterApi interpreterApi, @Nullable GpuDelegateProxy gpuDelegateProxy) {
        this.modelPath = str;
        this.byteModel = mappedByteBuffer;
        this.interpreter = interpreterApi;
        this.gpuDelegateProxy = gpuDelegateProxy;
    }
}
